package com.qf.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.UserBIZ;
import com.qf.auction.bizimpl.UserBIZImpl;
import com.qf.auction.entity.User;
import com.qf.auction.enums.UserStateEnum;

public class UserRegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserRegisterServlet() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

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
		// 接收用戶傳過來的數據
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String validateCode = request.getParameter("validatecode");
		String phoneNumber = request.getParameter("phonenumber");
		// 實例化user
		User user = new User();
		user.setUserName(userName);
		user.setPassWord(password);
		user.setEmail(email);
		user.setPhoneNumber(phone);
		// 實例用戶的業務邏輯層
		UserBIZ userBIZ = new UserBIZImpl();
		String result = null;
		try {
			result = userBIZ.userRegister(user, repassword,
					Integer.parseInt(validateCode),phoneNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result.equals(UserStateEnum.USER_ADD_SUCCESS.getValue())) {
			try {
				// 把MSG （注冊狀態） 傳過去
				request.getRequestDispatcher(
						"user_login.jsp?msg=" + result + "").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				request.getRequestDispatcher("register.jsp?msg=" + result + "")
						.forward(request, response);
			} catch (Exception e) {
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
