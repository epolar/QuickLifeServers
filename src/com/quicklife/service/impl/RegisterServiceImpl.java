package com.quicklife.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quicklife.pojo.Business;
import com.quicklife.pojo.Tuser;
import com.quicklife.service.inter.RegisterService;
import com.quicklife.util.JsonUtil;
import com.quicklife.util.SqlHelper;
import com.quicklife.util.StringUtil;

/**
 * 注册服务
 * 
 * @author HackerD
 * 
 */
public class RegisterServiceImpl implements RegisterService {
	private Map<String, Object> map = null;

	public RegisterServiceImpl() {
		map = new HashMap<String, Object>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.quicklife.service.inter.RegisterService#webRegister(java.lang.String)
	 */
	@Override
	public String webRegister(String rqinfos) {
		try {
System.out.println(rqinfos);			
			Business buss = (Business) JsonUtil.toBean(rqinfos, Business.class);

			String result = null;

			// 判断登陆用户名是否已被注册
			result = checkBeExist(
					"select count(*) from business where LOGIN_NAME=?",
					new String[] { buss.getLogin_name() },
					StringUtil.FAIL_LNAME_EXIST);
			if (result != null) {
				return result;
			}

			// 判断手机号是否已被注册
			result = checkBeExist(
					"select count(*) from business where PHONE=?",
					new String[] { buss.getPhone() },
					StringUtil.FAIL_PHONE_EXIST);
			if (result != null) {
				return result;
			}

			String rtinfos = commRegister(
					"insert into business(ID,TYPE,LOGIN_NAME,PASSWORD,NAME,"
							+ "PHONE,ADDRESS,LOGO,LAT,LON) values"
							+ "(?,?,?,?,?,?,?,?,?,?)",
					new String[] { StringUtil.toString(SqlHelper.getTableId("business")),
							StringUtil.toString(buss.getType()),
							buss.getLogin_name(), buss.getPassword(),
							buss.getName(), buss.getPhone(), buss.getAddress(),
							buss.getLogo(), StringUtil.toString(buss.getLat()),
							StringUtil.toString(buss.getLon()) });

			return rtinfos;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.createRtJsonStr(StringUtil.RESULT_CODE_FAIL,
					StringUtil.RESULT_EXCEPTION);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.quicklife.service.inter.RegisterService#androidRegister(java.lang
	 * .String)
	 */
	@Override
	public String androidRegister(String rqinfos) {
		try {
			Tuser user = (Tuser) JsonUtil.toBean(rqinfos, Tuser.class);

			String result = null;

			// 判断登陆用户名是否已被注册
			result = checkBeExist(
					"select count(*) from Tuser where LOGIN_NAME=?",
					new String[] { user.getLogin_name() },
					StringUtil.FAIL_LNAME_EXIST);
			if (result != null) {
				return result;
			}

			// 判断手机号是否已被注册
			result = checkBeExist(
					"select count(*) from Tuser where BIND_PHONE=?",
					new String[] { user.getBind_phone() },
					StringUtil.FAIL_PHONE_EXIST);
			if (result != null) {
				return result;
			}

			// 判断邮箱是否已被注册
			result = checkBeExist("select count(*) from Tuser where PHONE=?",
					new String[] { user.getEmail() },
					StringUtil.FAIL_EMAIL_EXIST);
			if (result != null) {
				return result;
			}

			String rtinfos = commRegister(
					"insert into tuser(ID,LOGIN_NAME,NICKNAME,PASSWORD,SEX,"
							+ "BIND_PHONE,LAST_LOGIN_TIME,PHOTO,EMAIL)"
							+ "values(?,?,?,?,?,?,sysdate,?,?)",
					new String[] { StringUtil.toString(user.getId()),
							user.getLogin_name(), user.getNickname(),
							user.getPassword(),
							StringUtil.toString(user.getSex()),
							user.getBind_phone(), user.getPhoto(),
							user.getEmail() });

			return rtinfos;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.createRtJsonStr(StringUtil.RESULT_CODE_FAIL,
					StringUtil.RESULT_EXCEPTION);
		}
	}

	/**
	 * 将注册信息插入数据库
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	private String commRegister(String sql, String[] params) {
		try {
			SqlHelper.executeUpdate(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.createRtJsonStr(StringUtil.RESULT_CODE_FAIL,
					StringUtil.RESULT_EXCEPTION);
		}

		return JsonUtil.createRtJsonStr(StringUtil.RESULT_CODE_OK, null);
	}

	/**
	 * 验证登陆用户名/手机号/邮箱是否已被注册
	 * 
	 * @param sql
	 * @param params
	 * @param failInfo
	 *            已被注册返回的信息
	 * @return
	 */
	private String checkBeExist(String sql, String[] params, String failInfo) {
		List list = null;
		try {
			list = SqlHelper.executeQuery(sql, params, null);
			Map map = (Map) list.get(0);
			if (((BigDecimal)map.get("count(*)")).intValue()> 0) {
				return JsonUtil.createRtJsonStr(StringUtil.RESULT_CODE_FAIL,
						failInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
