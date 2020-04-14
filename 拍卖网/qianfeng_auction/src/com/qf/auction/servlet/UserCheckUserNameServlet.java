package com.qf.auction.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.UserBIZ;
import com.qf.auction.bizimpl.UserBIZImpl;

public class UserCheckUserNameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		UserBIZ userBIZ = new UserBIZImpl();
		boolean isExist = false;
		// response.getWriter().print(userBIZ.checkUserNameIsExist(request.getParameter("username")));
		// 代碼肯定 越短 越好
		try {
			isExist = userBIZ.checkUserNameIsExist(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 写入到响应报文中
		try {
			response.getWriter().print(isExist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
