package com.qf.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.job.AuctionSendEmailScheduler;

public class AuctionJobServlet extends HttpServlet {

	// 初始化会执行这个 函数
	public void init() throws ServletException {
		 AuctionSendEmailScheduler auctionSendEmailScheduler = new
		 AuctionSendEmailScheduler();
		 auctionSendEmailScheduler.start();
	}

}
