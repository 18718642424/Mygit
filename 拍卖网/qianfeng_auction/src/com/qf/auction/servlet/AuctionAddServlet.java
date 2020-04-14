package com.qf.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.qf.auction.biz.AuctionBIZ;
import com.qf.auction.bizimpl.AuctionBIZImpl;
import com.qf.auction.entity.Auction;
import com.qf.auction.enums.AuctionStateEnum;

public class AuctionAddServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionAddServlet() {
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
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		// String result = AuctionStateEnum.AUCTION_ADD_FAIL.getValue();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = auctionBIZ.addAuction(getServletConfig(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = (String) map.get("msg");
		if (result != null
				&& result.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS
						.getValue())) {
			try {
				request.getRequestDispatcher(
						"AuctionListByPageServlet?msg=" + result + "").forward(
						request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				// 由于表单的提交是文件提交 是不能直接从 请求报文中抓取数据的
				// EL 表达式 只能获取 服务器行为 存储的数据
				// 之前的数据是从 smartupload中抓取到的
				Auction auction = (Auction) map.get("auction");
				request.setAttribute("auction", auction);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				request.getRequestDispatcher(
						"add_auction.jsp?msg=" + result + "").forward(request,
						response);
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
