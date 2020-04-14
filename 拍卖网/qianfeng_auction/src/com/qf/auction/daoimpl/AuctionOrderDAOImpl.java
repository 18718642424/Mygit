package com.qf.auction.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.qf.auction.dao.AuctionOrderDAO;
import com.qf.auction.entity.AuctionOrder;
import com.qf.auction.util.JDBCUtil;

public class AuctionOrderDAOImpl implements AuctionOrderDAO {

	@Override
	public int addOrder(AuctionOrder order) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into auction_order (orderid,auctionid,userid,price,createtime,state) values (?,?,?,?,?,?)");
			preparedStatement.setString(1, order.getOrderid());
			preparedStatement.setInt(2, order.getAuctionid());
			preparedStatement.setInt(3, order.getUserid());
			preparedStatement.setDouble(4, order.getPrice());
			preparedStatement.setTimestamp(5,
					new Timestamp(System.currentTimeMillis()));
			// 1 代表成功的订单
			preparedStatement.setInt(6, 1);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public int updateOrderState(int orderState) {
		return 0;
	}

	@Override
	public int addOrder(AuctionOrder order, int orderState) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into order (orderid,auctionid,userid,price,createtime,state) values (?,?,?,?,?,?)");
			preparedStatement.setString(1, order.getOrderid());
			preparedStatement.setInt(2, order.getAuctionid());
			preparedStatement.setInt(3, order.getUserid());
			preparedStatement.setDouble(4, order.getPrice());
			preparedStatement.setTimestamp(5,
					new Timestamp(System.currentTimeMillis()));
			// 1 代表成功的订单
			preparedStatement.setInt(6, orderState);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

}
