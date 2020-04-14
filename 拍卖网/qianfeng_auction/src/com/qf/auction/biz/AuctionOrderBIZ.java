package com.qf.auction.biz;

import com.qf.auction.entity.AuctionOrder;

public interface AuctionOrderBIZ {

	String addOrder(AuctionOrder order);

	String addOrder(AuctionOrder order,int orderState);

	int updateOrderState(int orderState);
}
