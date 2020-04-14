package com.qf.auction.bizimpl;

import com.qf.auction.biz.AuctionOrderBIZ;
import com.qf.auction.dao.AuctionOrderDAO;
import com.qf.auction.daoimpl.AuctionOrderDAOImpl;
import com.qf.auction.entity.AuctionOrder;
import com.qf.auction.enums.OrderStateEnum;

public class AuctionOrderBIZImpl implements AuctionOrderBIZ {

	AuctionOrderDAO orderDAO = new AuctionOrderDAOImpl();

	@Override
	public String addOrder(AuctionOrder order) {
		int executeCount = orderDAO.addOrder(order);
		if (executeCount > 0) {
			return OrderStateEnum.ORDER_ADD_SUCCESS.getValue();
		} else {
			return OrderStateEnum.ORDER_ADD_FAIL.getValue();
		}
	}

	@Override
	public int updateOrderState(int orderState) {
		return 0;
	}

	@Override
	public String addOrder(AuctionOrder order, int orderState) {
		int executeCount = orderDAO.addOrder(order, orderState);
		if (executeCount > 0) {
			return OrderStateEnum.ORDER_ADD_SUCCESS.getValue();
		} else {
			return OrderStateEnum.ORDER_ADD_FAIL.getValue();
		}
	}

}
