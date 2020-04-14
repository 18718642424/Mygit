package com.qf.auction.biz;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.entity.Auction;

public interface AuctionBIZ {

	List<Auction> findAuctionListByPage(BigDecimal pageIndex,
			BigDecimal pageSize);

	List<Auction> getAll();

	BigDecimal getAllCount();

	Map<String, Object> addAuction(ServletConfig servletConfig, HttpServletRequest request,
			HttpServletResponse response);

	String updateAuction(ServletConfig servletConfig,
			HttpServletRequest request, HttpServletResponse response);

	Auction findAuctionById(int auctionId);

	boolean delAuctionById(String hostPath, int auctionId);

	List<Auction> searchAuction(String auctionName, String startTime,
			String endTime, String startPrice);

	int updateAuctionStateByAuctionId(int auctionId);
	
	// 由于查詢的是 結束的拍賣品和未結束的拍賣品  所以不需要 任何参数   作为查詢的條件
	Map<String, Object> searchAuctionResult();

}
