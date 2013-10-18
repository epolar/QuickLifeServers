package com.quicklife.service.inter;

import com.quicklife.pojo.Tuser;

/**
 * 注册服务接口
 * 
 * @author HackerD
 *
 */
public interface RegisterService {
	/**
	 * web端注册
	 * 
	 * @param rqinfos 请求信息
	 * @return 注册结果
	 */
	public String webRegister(String rqinfos);
	
	/**
	 * 安卓端注册
	 * 
	 * @param rqinfos 请求信息
	 * @return 注册结果
	 */
	public String androidRegister(String rqinfos);
}
