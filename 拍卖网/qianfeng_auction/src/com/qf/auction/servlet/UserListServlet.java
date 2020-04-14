package com.qf.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.UserBIZ;
import com.qf.auction.bizimpl.UserBIZImpl;
import com.qf.auction.entity.User;

public class UserListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserListServlet() {
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
		// 别写成 doGet(request,response)
		doPost(request, response);
		System.out.println();
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
		UserBIZ userBIZ = new UserBIZImpl();
		Set<User> userSet = userBIZ.getAll();
		// 存入到请求报文中 区分大小写 关键是要对应 格式不重要
		request.setAttribute("userSet", userSet);
		// 转发到 user_list.jsp 文件中
		request.getRequestDispatcher("user_list.jsp")
				.forward(request, response);
		// 一个控制文件 只能 跳转一次 跳转多次会报错
		// request.getRequestDispatcher("login_success.jsp").forward(request,
		// response);

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
