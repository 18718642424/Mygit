package com.qf.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.AuctionBIZ;
import com.qf.auction.bizimpl.AuctionBIZImpl;
import com.qf.auction.entity.Auction;

public class AuctionFindByIdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionFindByIdServlet() {
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
		// 抓取到auctionid 然后实例化 auctionbiz 这个文件
		// 然后通过auctionbiz 来调用 根据拍卖品ID查找拍卖品的函数 通过这个函数返回一个 拍卖品（实例化拍卖品）
		// 然后再把实例化后的拍卖品赛到 请求报文中（一定要注意KEY 是否 和 前端对应）
		// 把请求体和响应体做转发 转到对应视图文件中
		// 需要注意控制层的开发事项
		String auctionId = request.getParameter("auctionid");
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		Auction auction = auctionBIZ.findAuctionById(Integer.parseInt( auctionId));
		request.setAttribute("auction", auction);
		try {
			request.getRequestDispatcher("add_auction.jsp").forward(request, response);
		} catch (Exception e) {
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
