package com.qf.auction.biz;

import java.util.List;

import com.qf.auction.entity.AuctionRecord;

public interface AuctionRecordBIZ {

	List<AuctionRecord> findAuctionRecordListByAuctionId(int auctionId,
			int userId);

	String addAuctionRecord(AuctionRecord auctionRecord);
}
