package com.quicklife.service.inter;

import com.quicklife.pojo.Tuser;

/**
 * 登陆服务接口
 * 
 * @author HackerD
 *
 */
public interface LoginService {
	/**
	 * web端登录检测
	 * 
	 * @param rqInfos 请求信息
	 * @return 验证结果
	 */
	public String webLoginCheck(String rqInfos);
	
	/**
	 * 安卓端登录检测
	 * 
	 * @param rqInfos  请求信息
	 * @return 验证结果
	 */
	public String androidLoginCheck(String rqInfos);
}
