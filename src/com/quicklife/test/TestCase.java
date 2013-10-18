package com.quicklife.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.quicklife.pojo.Tuser;

public class TestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JSONObject jsonObject=JSONObject.fromObject("{'result':'SUCCESS','rtinfos':[{'bind_phone':'123456','email':'','id':1,'last_loc_time':null,'last_login_time':null,'lat':0,'login_name':'test','lon':0,'nickname':'test','password':'098F6BCD4621D373CADE4E832627B4F6','photo':'','score':0,'sex':1}]}");
		String result=jsonObject.getString("result");
		JSONArray jsonArray=jsonObject.getJSONArray("rtinfos");
		JSONObject js=jsonArray.getJSONObject(0);
		System.out.println(js.getString("id"));
	}

}
