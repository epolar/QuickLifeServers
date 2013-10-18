package com.quicklife.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.quicklife.pojo.Business;
import com.quicklife.pojo.Tuser;
import com.quicklife.service.inter.LoginService;
import com.quicklife.util.JsonUtil;
import com.quicklife.util.SqlHelper;
import com.quicklife.util.StringUtil;

/**
 * 登陆服务
 * 
 * @author HackerD
 * 
 */
public class LoginServiceImpl implements LoginService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.quicklife.service.inter.LoginService#webLoginCheck(java.lang.String)
	 */
	@Override
	public String webLoginCheck(String rqInfos) {
		if (rqInfos != null && !"".equals(rqInfos)) {
			Business buss = (Business) JsonUtil
					.toBean(rqInfos, Business.class);
			return commCheck(
					"select * from business where login_name=? and password=?",
					new String[] { buss.getLogin_name(), buss.getPassword() },
					Business.class);
		} else {
			return JsonUtil.createRtJsonStr(StringUtil.RESULT_CODE_FAIL,
					"用户名或密码错误");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.quicklife.service.inter.LoginService#androidLoginCheck(java.lang.
	 * String)
	 */
	@Override
	public String androidLoginCheck(String rqInfos) {
		if (rqInfos != null && !"".equals(rqInfos)) {
			Tuser user = (Tuser) JsonUtil.toBean(rqInfos, Tuser.class);

			return commCheck(
					"select * from tuser where (login_name=? or bind_phone=?) and password=?",
					new String[] { user.getLogin_name(), user.getLogin_name(),
							user.getPassword() }, Tuser.class);
		} else {
			return JsonUtil.createRtJsonStr(StringUtil.RESULT_CODE_FAIL,
					"用户名或密码错误");
		}
	}

	/**
	 * 通用检测
	 * 
	 * @param sql
	 *            查询语句
	 * @param params
	 *            where中的参数
	 * @return 验证结果
	 */
	private String commCheck(String sql, String[] params, Class type) {
		try {
			List object = SqlHelper.executeQuery(sql, params, type);
			if (object != null) {
				return JsonUtil.createRtJsonStr(StringUtil.RESULT_CODE_OK,
						object);
			} else {
				return JsonUtil.createRtJsonStr(StringUtil.RESULT_CODE_FAIL,
						"用户名或密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.createRtJsonStr(StringUtil.RESULT_CODE_FAIL,
					StringUtil.RESULT_EXCEPTION);
		}
	}

}
