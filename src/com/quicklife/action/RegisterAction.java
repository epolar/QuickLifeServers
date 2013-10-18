package com.quicklife.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quicklife.service.impl.LoginServiceImpl;
import com.quicklife.service.impl.RegisterServiceImpl;
import com.quicklife.service.inter.LoginService;

/**
 * 注册
 * 
 * @author HackerD
 * 
 */
public class RegisterAction extends HttpServlet {

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
		String actionFlag = request.getParameter("actionflag");
		String rqinfos = request.getParameter("rqinfos");

		// 执行验证
		RegisterServiceImpl rs = new RegisterServiceImpl();
		if (actionFlag.equalsIgnoreCase("web")) {
			rtinfos = rs.webRegister(rqinfos);
		} else {
			rtinfos = rs.androidRegister(rqinfos);
		}

		// 返回结果
		out.print(rtinfos);

		out.flush();
		out.close();
	}

}
