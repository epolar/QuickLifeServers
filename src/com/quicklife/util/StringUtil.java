package com.quicklife.util;

import java.util.UUID;

public class StringUtil {
	/**
	 * SUCCESS
	 */
	public static String RESULT_CODE_OK="SUCCESS";
	/**
	 * FAIL
	 */
	public static String RESULT_CODE_FAIL="FAIL";
	/**
	 * 服务器异常
	 */
	public static String RESULT_EXCEPTION="服务器异常";
	/**
	 * 登陆名已被注
	 */
	public static String FAIL_LNAME_EXIST = "登陆名已被注册";
	/**
	 * 手机号已被注册
	 */
	public static String FAIL_PHONE_EXIST = "手机号已被注册";
	/**
	 * 邮箱已被注册
	 */
	public static String FAIL_EMAIL_EXIST = "邮箱已被注册";

	public static String toString(Object object) {
		return String.valueOf(object);
	}

	/**
	 * 将Integer格式String转为Integer
	 * 
	 * @param s
	 * @return
	 */
	public static Integer toInt(String s) {
		return Integer.parseInt(s);
	}

	/**
	 * 将Double格式String转为Double
	 * 
	 * @param s
	 * @return
	 */
	public static Double toDouble(String s) {
		return Double.parseDouble(s);
	}
	
	/**
	 * @return  UUID
	 */
	public static String getUUID(){
		UUID uuid=UUID.randomUUID();
		
		return uuid.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
