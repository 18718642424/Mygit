package com.qf.auction.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.UserBIZ;
import com.qf.auction.bizimpl.UserBIZImpl;
import com.qf.auction.enums.UserLoginStateEnum;

public class UserLoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserLoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 接收用户的请求
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		String userCode = request.getParameter("usercode");
		// 获取服务器上面 存储的SESSION
		String sysCode = (String) request.getSession().getAttribute("systemcode");
		// 控制要调用业务逻辑层
		UserBIZ userBIZ = new UserBIZImpl();
		String result = null;
		try {
			result = userBIZ.userLogin(userName, passWord, sysCode, userCode,request.getSession());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result.equals(UserLoginStateEnum.USER_LOGIN_STATE_SUCCESS
				.getValue())) {
			// 代码能进入到这里说明登录成功
			// 对文件进行一个重定向操作
			// sendRedirect() 指的是重定向
			try {
				response.sendRedirect("AuctionListByPageServlet?pageindex=1&pagesize=5");
			} catch (IOException e) {	
				e.printStackTrace();
			}
		} else {
			// 代码如果进入到这里说明登录失败
			// 对文件做一个转发的操作
			try {
//				request.getRequestDispatcher("login.jsp?msg=" + result + "")
//						.forward(request, response)
				// 下面这一段代码是模仿用户行为 向服务器发送数据
				request.getRequestDispatcher("user_login.jsp?msg=" + result + "")
 				.forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
