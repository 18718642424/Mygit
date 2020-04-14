package com.qf.auction.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qf.auction.dao.AuctionRecordDAO;
import com.qf.auction.entity.Auction;
import com.qf.auction.entity.AuctionRecord;
import com.qf.auction.entity.User;
import com.qf.auction.util.JDBCUtil;

public class AuctionRecordDAOImpl implements AuctionRecordDAO {

	@Override
	public List<AuctionRecord> findAuctionRecordListByAuctionId(int auctionId,
			int userId) {
		// 遵循 JDBC 的 7大操作流程
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<AuctionRecord> auctionRecords = new ArrayList<AuctionRecord>();
		try {
			connection = JDBCUtil.getConnection();
			// 难点2：这个查询语句 执行的是 一个 表连接语句 (参考上午的表连接语句 记住占位符的实现)
			preparedStatement = connection
					.prepareStatement("select act.id as userid2 , act.username , ard.*  from user as act left  join auctionrecord as ard on act.id = ard.USERID where ard.auctionid = ? order by ard.AUCTIONPRICE desc");
			preparedStatement.setInt(1, auctionId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// 难点1：在迭代的过程中 要去实例化 Auction(只要给它一个ID就可以) 以及 User(给它一个USERID 和
				// USERNAME)
				AuctionRecord auctionRecord = new AuctionRecord();
				Auction auction = new Auction();
				User user = new User();
				auctionRecord.setAuctionPrice(resultSet
						.getDouble("AuctionPrice"));
				auctionRecord.setAuctionTime(resultSet
						.getTimestamp("AuctionTime"));
				auction.setAuctionID(auctionId);
				auctionRecord.setAuction(auction);
				user.setId(resultSet.getInt("userid2"));
				user.setUserName(resultSet.getString("UserName"));
				auctionRecord.setUser(user);
				auctionRecords.add(auctionRecord);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return auctionRecords;
	}

	@Override
	public int addAuctionRecord(AuctionRecord auctionRecord) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into AuctionRecord (USERID,AUCTIONID,AUCTIONTIME,AUCTIONPRICE) values (?,?,?,?)");
			preparedStatement.setInt(1, auctionRecord.getUser().getId());
			preparedStatement.setInt(2, auctionRecord.getAuction()
					.getAuctionID());
			preparedStatement.setTimestamp(3, auctionRecord.getAuctionTime());
			preparedStatement.setDouble(4, auctionRecord.getAuctionPrice());
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

}
