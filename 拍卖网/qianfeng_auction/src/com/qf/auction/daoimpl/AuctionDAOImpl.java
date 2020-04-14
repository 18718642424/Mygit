package com.qf.auction.daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;
import com.qf.auction.dao.AuctionDAO;
import com.qf.auction.entity.Auction;
import com.qf.auction.entity.User;
import com.qf.auction.util.JDBCUtil;

public class AuctionDAOImpl implements AuctionDAO {

	@Override
	public List<Auction> findAuctionListByPage(BigDecimal beginPageNum, BigDecimal pageSize) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> lists = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from auction limit ?,?");
			preparedStatement.setBigDecimal(1, beginPageNum);
			preparedStatement.setBigDecimal(2, pageSize);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AuctionID"));
				auction.setAuctionName(resultSet.getString("AuctionName"));
				auction.setAuctionDESC(resultSet.getString("AuctionDESC"));
				auction.setAuctionEndTime(resultSet.getTimestamp("AuctionEndTime"));
				auction.setAuctionStartTime(resultSet.getTimestamp("AuctionStartTime"));
				auction.setAuctionStartPrice(resultSet.getDouble("AuctionStartPrice"));
				auction.setAuctionUpset(resultSet.getDouble("AuctionUpset"));
				auction.setCreateTime(resultSet.getTimestamp("CreateTime"));
				auction.setUpdateTime(resultSet.getTimestamp("UpdateTime"));
				auction.setAuctionPICPath(resultSet.getString("AuctionPICPath"));
				// 别忘了 把 auction 塞到 list 容器中
				lists.add(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		return lists;
	}

	@Override
	public BigDecimal getAllCount() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BigDecimal totalCount = new BigDecimal(1);
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement("select count(*) as auctiontotalcount from auction ");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				totalCount = resultSet.getBigDecimal("auctiontotalcount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		return totalCount;
	}

	@Override
	public int addAuction(Auction auction) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(
					"insert into auction(AUCTIONNAME,AUCTIONSTARTPRICE,AUCTIONUPSET,AUCTIONSTARTTIME,AUCTIONENDTIME,AUCTIONDESC,AUCTIONPICPATH,CREATETIME,UPDATETIME) values(?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, auction.getAuctionName());
			preparedStatement.setDouble(2, auction.getAuctionStartPrice());
			preparedStatement.setDouble(3, auction.getAuctionUpset());
			preparedStatement.setTimestamp(4, auction.getAuctionStartTime());
			preparedStatement.setTimestamp(5, auction.getAuctionEndTime());
			preparedStatement.setString(6, auction.getAuctionDESC());
			preparedStatement.setString(7, auction.getAuctionPICPath());
			preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public Auction findAuctionById(int auctionid) {
		// 去数据库 根据 拍卖品的ID 查找出 对应的拍卖品
		// 实例化 拍卖品 （给它各种SET） 最后将实例化后的拍卖品 返回(return)
		// 最后不要忘记关闭
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Auction auction = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from auction where AUCTIONID = ?");
			preparedStatement.setInt(1, auctionid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AUCTIONID"));
				auction.setAuctionName(resultSet.getString("AuctionName"));
				auction.setAuctionDESC(resultSet.getString("AuctionDESC"));
				auction.setAuctionEndTime(resultSet.getTimestamp("AuctionEndTime"));
				auction.setAuctionStartTime(resultSet.getTimestamp("AuctionStartTime"));
				auction.setAuctionStartPrice(resultSet.getDouble("AuctionStartPrice"));
				auction.setAuctionUpset(resultSet.getDouble("AuctionUpset"));
				auction.setCreateTime(resultSet.getTimestamp("CreateTime"));
				auction.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				auction.setAuctionPICPath(resultSet.getString("AuctionPICPath"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return auction;
	}

	@Override
	public int updateAuction(Auction auction) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(
					"update  auction set AUCTIONNAME =? , AUCTIONSTARTPRICE=?,AUCTIONUPSET=?,AUCTIONSTARTTIME=?,AUCTIONENDTIME=?,AUCTIONDESC=?,            AUCTIONPICPATH=?,UPDATETIME=?     where     auctionid  = ?");
			preparedStatement.setString(1, auction.getAuctionName());
			preparedStatement.setDouble(2, auction.getAuctionStartPrice());
			preparedStatement.setDouble(3, auction.getAuctionUpset());
			preparedStatement.setTimestamp(4, auction.getAuctionStartTime());
			preparedStatement.setTimestamp(5, auction.getAuctionEndTime());
			preparedStatement.setString(6, auction.getAuctionDESC());
			preparedStatement.setString(7, auction.getAuctionPICPath());
			preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setInt(9, auction.getAuctionID());
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public int delAuctionById(int auctionid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			// ctrl + shift + x 或者 y 大小写转换
			preparedStatement = connection.prepareStatement("delete from auction where auctionid = ?");
			preparedStatement.setInt(1, auctionid);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public List<Auction> searchAuction(String sql) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> lists = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AuctionID"));
				auction.setAuctionName(resultSet.getString("AuctionName"));
				auction.setAuctionDESC(resultSet.getString("AuctionDESC"));
				auction.setAuctionEndTime(resultSet.getTimestamp("AuctionEndTime"));
				auction.setAuctionStartTime(resultSet.getTimestamp("AuctionStartTime"));
				auction.setAuctionStartPrice(resultSet.getDouble("AuctionStartPrice"));
				auction.setAuctionUpset(resultSet.getDouble("AuctionUpset"));
				auction.setCreateTime(resultSet.getTimestamp("CreateTime"));
				auction.setUpdateTime(resultSet.getTimestamp("UpdateTime"));
				auction.setAuctionPICPath(resultSet.getString("AuctionPICPath"));
				// 别忘了 把 auction 塞到 list 容器中
				lists.add(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		return lists;
	}

	@Override
	public List<Auction> getAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> lists = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement("select * from auction");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Auction auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AuctionID"));
				auction.setAuctionName(resultSet.getString("AuctionName"));
				auction.setAuctionDESC(resultSet.getString("AuctionDESC"));
				auction.setAuctionEndTime(resultSet.getTimestamp("AuctionEndTime"));
				auction.setAuctionStartTime(resultSet.getTimestamp("AuctionStartTime"));
				auction.setAuctionStartPrice(resultSet.getDouble("AuctionStartPrice"));
				auction.setAuctionUpset(resultSet.getDouble("AuctionUpset"));
				auction.setCreateTime(resultSet.getTimestamp("CreateTime"));
				auction.setUpdateTime(resultSet.getTimestamp("UpdateTime"));
				auction.setAuctionPICPath(resultSet.getString("AuctionPICPath"));
				auction.setState(resultSet.getInt("state"));
				// 别忘了 把 auction 塞到 list 容器中
				lists.add(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		return lists;
	}

	@Override
	public int updateAuctionStateByAuctionId(int auctionId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement("update auction set state = 1 where auctionid = ?");
			preparedStatement.setInt(1, auctionId);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public List<Auction> findEndAuction() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> lists = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			// preparedStatement = connection
			// .prepareStatement("select * from auction where AuctionEndTime <
			// '"
			// + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
			// + "'");

			// 雖然成立 但是 這樣寫 又限制了 state 未來的發展
			// preparedStatement = connection
			// .prepareStatement("select * from auction where state <> 0 ");

			// 除了 整型意外 其他 所有字段 都要 加 單引號 如果你 使用了 佔位符 就不必了
			preparedStatement = connection.prepareStatement("select * from auction where state = 1 or state = 2 ");

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				/*
				 * if (resultSet.getTimestamp("AuctionEndTime").getYear() < new
				 * Timestamp( System.currentTimeMillis()).getYear() &&
				 * resultSet.getTimestamp("AuctionEndTime").getMonth() < new
				 * Timestamp( System.currentTimeMillis()).getMonth() &&
				 * resultSet.getTimestamp("AuctionEndTime").getDate() < new
				 * Timestamp( System.currentTimeMillis()).getDate()) { Auction
				 * auction = new Auction();
				 * auction.setAuctionID(resultSet.getInt("AuctionID"));
				 * auction.setAuctionName(resultSet.getString("AuctionName"));
				 * auction.setAuctionDESC(resultSet.getString("AuctionDESC"));
				 * auction.setAuctionEndTime(resultSet
				 * .getTimestamp("AuctionEndTime"));
				 * auction.setAuctionStartTime(resultSet
				 * .getTimestamp("AuctionStartTime"));
				 * auction.setAuctionStartPrice(resultSet
				 * .getDouble("AuctionStartPrice"));
				 * auction.setAuctionUpset(resultSet.getDouble("AuctionUpset"));
				 * auction.setCreateTime(resultSet.getTimestamp("CreateTime"));
				 * auction.setUpdateTime(resultSet.getTimestamp("UpdateTime"));
				 * auction.setAuctionPICPath(resultSet
				 * .getString("AuctionPICPath"));
				 * auction.setState(resultSet.getInt("state")); // 别忘了 把 auction
				 * 塞到 list 容器中 lists.add(auction); }
				 */
				Auction auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AuctionID"));
				auction.setAuctionName(resultSet.getString("AuctionName"));
				auction.setAuctionDESC(resultSet.getString("AuctionDESC"));
				auction.setAuctionEndTime(resultSet.getTimestamp("AuctionEndTime"));
				auction.setAuctionStartTime(resultSet.getTimestamp("AuctionStartTime"));
				auction.setAuctionStartPrice(resultSet.getDouble("AuctionStartPrice"));
				auction.setAuctionUpset(resultSet.getDouble("AuctionUpset"));
				auction.setCreateTime(resultSet.getTimestamp("CreateTime"));
				auction.setUpdateTime(resultSet.getTimestamp("UpdateTime"));
				auction.setAuctionPICPath(resultSet.getString("AuctionPICPath"));
				auction.setState(resultSet.getInt("state"));
				// 别忘了 把 auction 塞到 list 容器中
				lists.add(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		return lists;
	}

	@Override
	public List<Auction> findNotEndAuction() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Auction> lists = new ArrayList<Auction>();
		try {
			connection = JDBCUtil.getConnection();
			// preparedStatement = connection
			// .prepareStatement("select * from auction where AuctionEndTime >
			// '"
			// + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
			// + "'");

			preparedStatement = connection.prepareStatement("select * from auction where state = 0");

			// 最符合
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				/*
				 * if (resultSet.getTimestamp("AuctionEndTime").getYear() < new
				 * Timestamp( System.currentTimeMillis()).getYear() &&
				 * resultSet.getTimestamp("AuctionEndTime").getMonth() < new
				 * Timestamp( System.currentTimeMillis()).getMonth() &&
				 * resultSet.getTimestamp("AuctionEndTime").getDate() < new
				 * Timestamp( System.currentTimeMillis()).getDate()) { Auction
				 * auction = new Auction();
				 * auction.setAuctionID(resultSet.getInt("AuctionID"));
				 * auction.setAuctionName(resultSet.getString("AuctionName"));
				 * auction.setAuctionDESC(resultSet.getString("AuctionDESC"));
				 * auction.setAuctionEndTime(resultSet
				 * .getTimestamp("AuctionEndTime"));
				 * auction.setAuctionStartTime(resultSet
				 * .getTimestamp("AuctionStartTime"));
				 * auction.setAuctionStartPrice(resultSet
				 * .getDouble("AuctionStartPrice"));
				 * auction.setAuctionUpset(resultSet.getDouble("AuctionUpset"));
				 * auction.setCreateTime(resultSet.getTimestamp("CreateTime"));
				 * auction.setUpdateTime(resultSet.getTimestamp("UpdateTime"));
				 * auction.setAuctionPICPath(resultSet
				 * .getString("AuctionPICPath"));
				 * auction.setState(resultSet.getInt("state")); // 别忘了 把 auction
				 * 塞到 list 容器中 lists.add(auction); }
				 */
				Auction auction = new Auction();
				auction.setAuctionID(resultSet.getInt("AuctionID"));
				auction.setAuctionName(resultSet.getString("AuctionName"));
				auction.setAuctionDESC(resultSet.getString("AuctionDESC"));
				auction.setAuctionEndTime(resultSet.getTimestamp("AuctionEndTime"));
				auction.setAuctionStartTime(resultSet.getTimestamp("AuctionStartTime"));
				auction.setAuctionStartPrice(resultSet.getDouble("AuctionStartPrice"));
				auction.setAuctionUpset(resultSet.getDouble("AuctionUpset"));
				auction.setCreateTime(resultSet.getTimestamp("CreateTime"));
				auction.setUpdateTime(resultSet.getTimestamp("UpdateTime"));
				auction.setAuctionPICPath(resultSet.getString("AuctionPICPath"));
				auction.setState(resultSet.getInt("state"));
				// 别忘了 把 auction 塞到 list 容器中
				lists.add(auction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		return lists;
	}

}
