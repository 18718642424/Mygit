package com.qf.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.AuctionBIZ;
import com.qf.auction.bizimpl.AuctionBIZImpl;

public class AuctionDelByIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionDelByIdServlet() {
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
		// 首先要抓取 拍卖品的id
		String auctionId = request.getParameter("auctionid");
		// 实例化 业务逻辑层 执行 删除拍卖品的方法 并返回一个 布尔类型
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		boolean isDelete = false;
		try {
			isDelete = auctionBIZ.delAuctionById(request.getSession().getServletContext().getRealPath("upload"),Integer.parseInt(auctionId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 最后将这个 布尔 类型 写入到 响应报文中 (开发API 也是 把数据写到 这里 （响应报文）)
        try {
			response.getWriter().print(isDelete);
		} catch (IOException e) {
			e.printStackTrace();
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
