package com.quicklife.util;

import java.util.Map;

import net.sf.json.JSONObject;

/**
 * Json工具类
 * 
 * @author HackerD
 * 
 */
public class JsonUtil {

	/**
	 * 创建Json格式字符串
	 * 
	 */
	public static String createJsonStr(String key, Object value) {
		JSONObject jsonObject = getJsonObject();
		jsonObject.put(key, value);

		return jsonObject.toString();
	}

	/**
	 * 创建Json格式字符串
	 * 
	 */
	public static String createJsonStr(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);

		return jsonObject.toString();
	}

	/**
	 * 创建Json格式的返回数据
	 * 
	 * @param result
	 *            返回结果码
	 * @param rtinfos
	 *            返回信息
	 * @return
	 */
	public static String createRtJsonStr(String result, Object rtinfos) {
		JSONObject jsonObject = getJsonObject();
		jsonObject.put("result", result);
		if (rtinfos != null) {
			jsonObject.put("rtinfos", rtinfos);
		}

		return jsonObject.toString();
	}

	/**
	 * 把jsonStr转为Bean
	 * 
	 * @param jsonObject
	 * @param beanClass
	 * @return
	 */
	public static Object toBean(String jsonStr, Class beanClass) {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return JSONObject.toBean(jsonObject, beanClass);
	}

	/**
	 * @return JSOnObject
	 */
	public static JSONObject getjsonObject(Object object) {
		return JSONObject.fromObject(object);
	}

	/**
	 * @return JSOnObject
	 */
	public static JSONObject getJsonObject() {
		return new JSONObject();
	}

}