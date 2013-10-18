package com.quicklife.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quicklife.service.impl.LoginServiceImpl;
import com.quicklife.service.inter.LoginService;
import com.quicklife.util.JsonUtil;
import com.quicklife.util.StringUtil;

/**
 * 登录验证
 * 
 * @author HackerD
 *
 */
public class LoginAction extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginAction() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String rtinfos = "";
		
		// 获得客户端请求参数
		String actionFlag=request.getParameter("actionflag");
		String rqInfos=request.getParameter("rqinfos");
		String validateCode=request.getParameter("validateCode");
		
		System.out.println(actionFlag);
		System.out.println(rqInfos);
		
		//执行验证
		LoginService ls=new LoginServiceImpl();
		if(actionFlag.equalsIgnoreCase("web")){
			if (validateCode.equals(request.getSession().getAttribute("validateCode"))) {
				rtinfos=ls.webLoginCheck(rqInfos);
			} else {
				rtinfos=JsonUtil.createRtJsonStr(StringUtil.RESULT_CODE_FAIL,
						"验证码错误");
			}
		}else{
			rtinfos=ls.androidLoginCheck(rqInfos);
		}
		
		//返回结果
		out.print(rtinfos);

		out.flush();
		out.close();
	}

}
