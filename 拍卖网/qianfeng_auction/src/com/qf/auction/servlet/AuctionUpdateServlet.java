package com.qf.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.AuctionBIZ;
import com.qf.auction.bizimpl.AuctionBIZImpl;
import com.qf.auction.enums.AuctionStateEnum;

public class AuctionUpdateServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionUpdateServlet() {
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
		String result = AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
		try {
			result = auctionBIZ.updateAuction(getServletConfig(), request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result.equals(AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getValue())) {
			try {
				request.getRequestDispatcher(
						"AuctionListByPageServlet?msg=" + result + "").forward(
						request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				request.getRequestDispatcher(
						"add_auction.jsp?msg=" + result + "").forward(request,
						response);
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
