package com.quicklife.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 数据库管理类
 * 
 * @author HackerD
 *
 */
public class DBUtil {
	private static DataSource dataSource=null;
	static{
		try {
			dataSource = (DataSource) new InitialContext()
					.lookup("java:comp/env/c3p0/orcl");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {

			try {
				return dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
	}

	
	/**
	 * 关闭资源
	 * 
	 * @param rs
	 * @param ps
	 * @param ct
	 */
	public static void close(ResultSet rs, Statement ps, Connection ct) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = null;
		}
		if (ct != null) {
			try {
				ct.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ct = null;
		}
	}
}
