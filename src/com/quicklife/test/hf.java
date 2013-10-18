package com.quicklife.test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JApplet;

import com.quicklife.pojo.Tuser;

public class hf {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String strUrl = "jdbc:oracle:thin:@192.168.1.65:1521:XINLIORA";
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(strUrl, "info", "123456");

			CallableStatement proc = null;
			// proc = conn
			// .prepareCall("{ call p_page.pagerecordscount(?,?) }");
			// proc.setString(1, "t_information");
			// proc.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
			// proc.execute();
			// Long countLong = proc.getLong(2);
			// System.out.println(countLong);

			/**
			 * Pindex in number, --分页索引 第几页 以0开始为第一页 Psize in number, --页面大小
			 * 1页显示多少行 Psql in varchar2, --产生dataset的sql语句 查询1页数据的sql
			 * allCountsql in varchar2, --查询总行数的语句 allCount out number, --总行数
			 * Pcount out number, --返回分页总数 v_cur2 out type_cur2 --返回当前页数据记录
			 */

			proc = conn
					.prepareCall("{ call p_page2.pagination2(?,?,?,?,?,?,?)}");
			proc.setString(1, "2");
			proc.setString(2, "5");
			proc.setString(3, "select * from (select a.*,rownum row_num from "
					+ "(select * from t_information t where adduserid=1 "
					+ "order by t.inserttime desc ) a) ");
			proc.setString(4, " t_information t where adduserid=1");
			proc.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
			proc.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
			proc.registerOutParameter(7, oracle.jdbc.OracleTypes.CURSOR);
			proc.execute();
			Long rowCount = proc.getLong(5);
			Long pageCount = proc.getLong(6);
			System.out.println("总行数:" + rowCount + ",总页数:" + pageCount);

			rs = (ResultSet) proc.getObject(7);
			hf hf = new hf();
			if (rs != null) {
				Collection collection = hf.resultSetToBean(rs,
						Tuser.class);//TInformation 转换的目标对象
				for (Iterator it = collection.iterator(); it.hasNext();) {
					Tuser ltest = (Tuser) it.next();
					System.out.println(ltest.getId() );
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将ResultSet中的数据转换为一个javabean并放到一个集合中
	 * 
	 * @param result
	 *            ResultSet数据 源数据
	 * @param clazz
	 *            转换的目标对象
	 * @return 存放目标对象的集合
	 */
	public Collection resultSetToBean(final ResultSet result, final Class clazz) {
		// 创建collection
		Collection collection = null;
		try {
			ResultSetMetaData rsmd = result.getMetaData();
			// 获得数据列数
			int cols = rsmd.getColumnCount();
			// 创建等同数据列数的arraylist类型collection实例
			collection = new ArrayList(cols);
			// 遍历结果集
			while (result.next()) {
				// 创建对象
				Object object = null;
				try {
					// 从class获得对象实体
					object = clazz.newInstance();
				} catch (Exception e) {
					System.out.println("对象转换失败!");
				}
				// 循环每条记录
				for (int i = 1; i <= cols; i++) {
					// 设置目标对象的属性
					beanRegister(object, rsmd.getColumnName(i), result
							.getString(i));
				}
				// 将数据插入collection
				collection.add(object);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {

		}
		return collection;
	}

	/**
	 * 为bean自动注入属性数据
	 * 
	 * @param object
	 *            目标对象
	 * @param beanProperty
	 *            属性名称
	 * @param value
	 *            属性的值
	 */
	private void beanRegister(Object object, String beanProperty, String value) {
		// 如果查询出来的列在目标对象中并没有则要筛选
		if (beanProperty.toLowerCase().equals("row_num")) {
			return;
		}
		// 为属性生成setter、getter方法
		Object[] beanObject = this.beanMatch(object.getClass(), beanProperty);
		Object[] cache = new Object[1];
		Method getter = (Method) beanObject[0];
		Method setter = (Method) beanObject[1];
		try {
			// 通过get获得方法类型，匹配类型并赋值
			String methodType = getter.getReturnType().getName();
			if (value != null && !value.equals("")) {
				if (methodType.equalsIgnoreCase("java.lang.Long")) {
					cache[0] = new Long(value);
					setter.invoke(object, cache);
				} else if (methodType.equalsIgnoreCase("java.lang.Int")
						|| methodType.equalsIgnoreCase("java.lang.Integer")) {
					cache[0] = new Integer(value);
					setter.invoke(object, cache);
				} else if (methodType.equalsIgnoreCase("java.lang.Short")) {
					cache[0] = new Short(value);
					setter.invoke(object, cache);
				} else if (methodType.equalsIgnoreCase("java.lang.Float")) {
					cache[0] = new Float(value);
					setter.invoke(object, cache);
				} else if (methodType.equalsIgnoreCase("java.lang.Double")) {
					cache[0] = new Double(value);
					setter.invoke(object, cache);
				} else if (methodType.equalsIgnoreCase("java.lang.Boolean")) {
					cache[0] = new Boolean(value);
					setter.invoke(object, cache);
				} else if (methodType.equalsIgnoreCase("java.util.Date")) {
					SimpleDateFormat sdft = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					cache[0] = sdft.parse(value);
					setter.invoke(object, cache);
				} else if (methodType.equalsIgnoreCase("java.lang.String")) {
					cache[0] = value;
					setter.invoke(object, cache);
				} else if (methodType.equalsIgnoreCase("java.io.InputStream")) {
				} else if (methodType.equalsIgnoreCase("char")) {
					cache[0] = (Character.valueOf(value.charAt(0)));
					setter.invoke(object, cache);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 匹配指定class中数据,并返回包含get和set方法的object,生成属性的setter、getter方法
	 * 
	 * @param clazz
	 *            目标对象
	 * @param beanProperty
	 *            属性名称
	 * @return
	 */
	private Object[] beanMatch(Class clazz, String beanProperty) {
		Object[] result = new Object[2];// 生成setter、getter
		beanProperty = beanProperty.toLowerCase();// 先转为小写
		char beanPropertyChars[] = beanProperty.toCharArray();
		beanPropertyChars[0] = Character.toUpperCase(beanPropertyChars[0]);// 首字母大写
		String s = new String(beanPropertyChars);
		String names[] = { ("get" + s).intern(), ("set" + s).intern() };
		Method getter = null;
		Method setter = null;
		Method methods[] = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			// 只取公共字段
			if (!Modifier.isPublic(method.getModifiers()))
				continue;
			String methodName = method.getName().intern();
			for (int j = 0; j < names.length; j++) {
				String name = names[j];
				if (!name.equals(methodName))
					continue;
				if (methodName.startsWith("set"))
					setter = method;
				else
					getter = method;
			}
		}
		result[0] = getter;
		result[1] = setter;
		return result;
	}

}
