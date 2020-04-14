package com.qf.auction.dao;

import java.util.List;

import com.qf.auction.entity.AuctionRecord;

public interface AuctionRecordDAO {
	List<AuctionRecord> findAuctionRecordListByAuctionId(int auctionId,
			int userId);

	int addAuctionRecord(AuctionRecord auctionRecord);
}
