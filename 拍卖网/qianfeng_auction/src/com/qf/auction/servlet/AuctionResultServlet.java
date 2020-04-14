package com.qf.auction.servlet;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.AuctionBIZ;
import com.qf.auction.bizimpl.AuctionBIZImpl;

public class AuctionResultServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		Map<String, Object> map = null;
		try {
			map = auctionBIZ.searchAuctionResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 把查询出来的 拍卖品结果 （已经结束的拍卖品 和 还在进行中的拍卖） 存入到请求报文中
		request.setAttribute("map", map);
		// 转发文件到 auction_result.jsp 文件中
		try {
			request.getRequestDispatcher("auction_result.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
