package com.qf.auction.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.AuctionBIZ;
import com.qf.auction.bizimpl.AuctionBIZImpl;
import com.qf.auction.entity.Auction;
import com.qf.auction.enums.AuctionStateEnum;
import com.qf.auction.util.StringUtil;
import com.qf.auction.vo.AuctionListPageVO;

public class AuctionListByPageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AuctionListByPageServlet() {
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
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String pageIndex = request.getParameter("pageIndex") == null ? "1"
				: request.getParameter("pageIndex");
		String pageNumber = request.getParameter("pageNum") == null ? "5"
				: request.getParameter("pageNum");
		String auctionName = request.getParameter("auctionName");
		String auctionStartTime = request.getParameter("auctionStartTime");
		String auctionEndTime = request.getParameter("auctionEndTime");
		String auctionStartPrice = request.getParameter("auctionStartPrice");
		// 获取 拍卖品的状态 然后执行不同的场景
		String msg = request.getParameter("msg");
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		List<Auction> lists = new ArrayList<Auction>();
		AuctionListPageVO<Auction> auctionListPageVO = new AuctionListPageVO<Auction>();
		try {
			BigDecimal totalCount = auctionBIZ.getAllCount();
			auctionListPageVO.setEndPage(totalCount.divide(new BigDecimal(
					pageNumber), 0, RoundingMode.UP));
			if (msg != null
					&& msg.equals(AuctionStateEnum.AUCTION_ADD_SUCCESS
							.getValue())) {
				// 进入到这里是 添加成功后的场景
				auctionListPageVO.setPageIndex(auctionListPageVO.getEndPage());
			} else if (msg != null
					&& msg.equals(AuctionStateEnum.AUCTION_UPDATE_SUCCESS
							.getValue())) {
				// 进入到这里是 修改成功后的场景
				auctionListPageVO.setPageIndex(new BigDecimal(pageIndex));
			} else {
				// 进入到这里 是 普通的分页场景
				auctionListPageVO.setPageIndex(new BigDecimal(pageIndex));
			}
			// 判断用户是否是查询操作
			if (StringUtil.notEmpty(auctionName)
					|| StringUtil.notEmpty(auctionStartTime)
					|| StringUtil.notEmpty(auctionEndTime)
					|| StringUtil.notEmpty(auctionStartPrice)) {
				// 进入到这里代表用户做的是查询操作
				lists = auctionBIZ.searchAuction(auctionName, auctionStartTime,
						auctionEndTime, auctionStartPrice);
			} else {
				// 进入到这里正常分页
				lists = auctionBIZ.findAuctionListByPage(auctionListPageVO
						.getPageIndex(), new BigDecimal(pageNumber));
			}
			auctionListPageVO.setLists(lists);
			auctionListPageVO.setPageNumber(new BigDecimal(pageNumber));
			auctionListPageVO.setTotal(totalCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {

			request.setAttribute("auctionPageInfo", auctionListPageVO);
			request.getRequestDispatcher("auction_datagrid.jsp").forward(
					request, response);
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

	public static void main(String[] args) {
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kun", "蔡徐坤");
		map.put("auction", new Auction(1));
		String username = null;
		try {
			username = (String) map.get("kun");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int auctionid = 0;
		try {
			auctionid = ((Auction) map.get("auction")).getAuctionID();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(username);
		System.out.println(auctionid);
	}

}
