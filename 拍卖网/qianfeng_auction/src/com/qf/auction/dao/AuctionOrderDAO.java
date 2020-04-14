package com.qf.auction.dao;

import com.qf.auction.entity.AuctionOrder;

public interface AuctionOrderDAO {

	int addOrder(AuctionOrder order);

	int addOrder(AuctionOrder order,int orderState);
	
	int updateOrderState(int orderState);

}
