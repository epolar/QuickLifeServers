package com.quicklife.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quicklife.pojo.Business;

/**
 * 操作数据库
 * 
 * @author HackerD
 * 
 */
public class SqlHelper {
	/**
	 * 查询
	 * 
	 * @param sql
	 * @param params
	 */
	public static ArrayList executeQuery(String sql, String[] params, Class type) {
		ArrayList al = null;
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ct = DBUtil.getConnection();
			ps = ct.prepareStatement(sql);
			// 给sql的问号赋值
			for (int i = 0; i < params.length; i++) {
				ps.setString(i + 1, params[i]);
			}
			rs = ps.executeQuery();

			// 这句话非常有用
			ResultSetMetaData rsmd = rs.getMetaData();
			// 通过rsmd可以得到该结果集有多少列
			int columnNum = rsmd.getColumnCount();

			// 获取列名
			List<String> colNames = new ArrayList<String>();
			for (int i = 1; i <= columnNum; i++) {
				colNames.add(rsmd.getColumnName(i).toLowerCase());
			}

			// 循环的从rs中取出数据，并封装到ArrayList中
			if (type == null) {
				al = reultSetToMap(rs, colNames);
			} else {
				al = resultSetToBean(rs, colNames, type);
			}

			return al;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			DBUtil.close(rs, ps, ct);
		}
	}


	/**
	 * 新增、修改、删除
	 */
	public static void executeUpdate(String sql, String[] params) {
		Connection ct = null;
		PreparedStatement ps = null;
		try {
			ct = DBUtil.getConnection();
			ct.setAutoCommit(false);
			ps = ct.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i =1 ; i <= params.length; i++) {
					ps.setString(i, params[i-1]);
				}
			}
			ps.executeUpdate();
			ct.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (ct != null) {
				try {
					ct.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DBUtil.close(null, ps, ct);
		}
	}

	/**
	 * 获取表的ID
	 * 
	 * @param tableName
	 *            表名
	 * @return 表的ID
	 */
	public static int getTableId(String tableName) {
		Connection ct = null;
		CallableStatement cs = null;
		try {
			ct = DBUtil.getConnection();
			cs = ct.prepareCall("call pnextid(?,?)");
			cs.setString(1, tableName);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.execute();
			int id = cs.getInt(2);

			return id;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cs.close();
				DBUtil.close(null, null, ct);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return 0;
	}

	/**
	 * 将结果集封装为Bean
	 * 
	 * @param rs
	 * @param colNames
	 * @param type
	 * @return
	 */
	public static ArrayList resultSetToBean(ResultSet rs,
			List<String> colNames, Class type) {
		ArrayList list = new ArrayList();
		Map<String, Field> fieldMap = new HashMap<String, Field>();
		Map<String, Method> setMethodMap = new HashMap<String, Method>();
		try {
			for (int i = 0; i < colNames.size(); i++) {
				// 获取列名,首字母转为大写
				char[] tempName = colNames.get(i).toLowerCase().toCharArray();
				tempName[0] = Character.toUpperCase(tempName[0]);
				String cloumnName = new String(tempName);
				// 构造set方法名
				String setter = "set" + cloumnName;
				// 获取类的所有属性
				Field field = type.getDeclaredField(cloumnName.toLowerCase());
				// 获取set方法
				Method setMethod = type.getMethod(setter, field.getType());

				fieldMap.put(cloumnName.toLowerCase(), field);
				setMethodMap.put(cloumnName.toLowerCase(), setMethod);
			}

			while (rs.next()) {
				// 创建一个对象
				Object object = type.newInstance();
				for (int i = 1; i <= colNames.size(); i++) {
					Field field = fieldMap.get(colNames.get(i - 1));
					Method setMethod = setMethodMap.get(colNames.get(i - 1));

					// 获取字段类型
					Class cs = field.getType();
					if (cs == int.class) {
						setMethod.invoke(object, rs.getInt(i));
					} else if (cs == Integer.class) {
						setMethod.invoke(object, rs.getInt(i));
					} else if (cs == Double.class) {
						setMethod.invoke(object, rs.getDouble(i));
					} else if (cs == String.class) {
						setMethod.invoke(object, rs.getString(i));
					} else if (cs == Date.class) {
						SimpleDateFormat sdft = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						String temp=rs.getString(i);
						if(temp!=null&&!"".equals(temp)){
							setMethod.invoke(object, sdft.parse(temp));
						}
					}
				}
				list.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(list.size()>0){
			return list;
		}

		return null;
	}
	
	/**
	 * 将混合查询结果集封装为Map
	 * 
	 * @param rs
	 * @param colNames
	 * @return
	 */
	private static ArrayList reultSetToMap(ResultSet rs, List<String> colNames) {
		ArrayList al = new ArrayList();
		try {
			while (rs.next()) {
				Map map=new HashMap();
				Object[] objects = new Object[colNames.size()];

				for (int i = 0; i < objects.length; i++) {
					map.put(colNames.get(i),  rs.getObject(i + 1));
				}
				al.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

}
