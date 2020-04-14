package com.qf.auction.bizimpl;

import java.util.List;

import com.qf.auction.biz.AuctionRecordBIZ;
import com.qf.auction.dao.AuctionRecordDAO;
import com.qf.auction.daoimpl.AuctionRecordDAOImpl;
import com.qf.auction.entity.AuctionRecord;
import com.qf.auction.enums.AuctionStateEnum;

public class AuctionRecordBIZImpl implements AuctionRecordBIZ {

	AuctionRecordDAO auctionRecordDAO = new AuctionRecordDAOImpl();

	@Override
	public List<AuctionRecord> findAuctionRecordListByAuctionId(int auctionId,
			int userId) {
		// 直接调用DAO层就可以了
		return auctionRecordDAO.findAuctionRecordListByAuctionId(auctionId,
				userId);
	}

	@Override
	public String addAuctionRecord(AuctionRecord auctionRecord) {
		int executeCount = auctionRecordDAO.addAuctionRecord(auctionRecord);
		if (executeCount > 0) {
			return AuctionStateEnum.AUCTION_SUCCESS.getValue();
		} else {
			return AuctionStateEnum.AUCTION_FAIL.getValue();
		}
	}
}
