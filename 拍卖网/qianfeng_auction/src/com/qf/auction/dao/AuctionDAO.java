package com.qf.auction.dao;

import java.math.BigDecimal;
import java.util.List;

import com.qf.auction.entity.Auction;
import com.qf.auction.entity.User;

public interface AuctionDAO {

	List<Auction> findAuctionListByPage(BigDecimal beginPageNum,
			BigDecimal pageSize);

	BigDecimal getAllCount();

	int addAuction(Auction auction);

	int updateAuction(Auction auction);

	Auction findAuctionById(int auctionid);

	int delAuctionById(int auctionid);

	List<Auction> searchAuction(String sql);

	List<Auction> getAll();

	int updateAuctionStateByAuctionId(int auctionId);

	List<Auction> findEndAuction();

	List<Auction> findNotEndAuction();

}
