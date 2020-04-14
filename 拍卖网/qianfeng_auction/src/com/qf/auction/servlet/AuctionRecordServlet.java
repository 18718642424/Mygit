package com.qf.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.AuctionBIZ;
import com.qf.auction.biz.AuctionRecordBIZ;
import com.qf.auction.bizimpl.AuctionBIZImpl;
import com.qf.auction.bizimpl.AuctionRecordBIZImpl;
import com.qf.auction.entity.Auction;
import com.qf.auction.entity.AuctionRecord;
import com.qf.auction.entity.User;

public class AuctionRecordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionRecordServlet() {
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
		String auctionId = request.getParameter("auctionId");
		int userId = ((User) request.getSession().getAttribute("user")).getId();
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		// 实例化 拍卖品详情 业务逻辑层
		AuctionRecordBIZ auctionRecordBIZ = new AuctionRecordBIZImpl();
		Auction auction = null;
		List<AuctionRecord> auctionRecords = new ArrayList<AuctionRecord>();
		try {
			auction = auctionBIZ.findAuctionById(Integer.parseInt(auctionId));
			// 调用查找拍卖详情的列表的函数 返回一个拍卖品列表
			auctionRecords = auctionRecordBIZ.findAuctionRecordListByAuctionId(
					Integer.parseInt(auctionId), userId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		request.setAttribute("AuctionObj", auction);
		// 将拍卖品列表存入到 请求报文中 但是要注意 和前端的KEY对应
		request.setAttribute("record_list", auctionRecords);
		try {
			request.getRequestDispatcher("auction_detail.jsp").forward(request,
					response);
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
