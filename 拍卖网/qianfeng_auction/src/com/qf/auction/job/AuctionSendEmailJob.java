package com.qf.auction.job;

import java.sql.Timestamp;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.qf.auction.biz.AuctionBIZ;
import com.qf.auction.biz.UserBIZ;
import com.qf.auction.bizimpl.AuctionBIZImpl;
import com.qf.auction.bizimpl.UserBIZImpl;
import com.qf.auction.email.AuctionResultSender;
import com.qf.auction.entity.Auction;
import com.qf.auction.entity.User;

public class AuctionSendEmailJob implements Job {

	// execute 这个函数是用来 执行具体的 任务
	// JobExecutionContext 可以获取 任务执行的上下文
	@SuppressWarnings("deprecation")
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//		System.out.println("执行了 : 运行时间为  "
//				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(arg0
//						.getFireTime()) + "");
//		System.out.println("执行了 : 任务名为  " + arg0.getJobDetail().getFullName()
//				+ "");
		// 获取到所有的拍卖品
		AuctionBIZ auctionBIZ = new AuctionBIZImpl();
		List<Auction> auctions = auctionBIZ.getAll();
		// 同时还匹配出 拍卖品的结束时间等于当天时间的拍卖品
		for (Auction auction : auctions) {
			// 打横代表这个函数 过时了
			if (auction.getAuctionEndTime().getYear() == new Timestamp(
					System.currentTimeMillis()).getYear()
					&& auction.getAuctionEndTime().getMonth() == new Timestamp(
							System.currentTimeMillis()).getMonth()
					&& auction.getAuctionEndTime().getDate() == new Timestamp(
							System.currentTimeMillis()).getDate()
					&& auction.getState() == 0) {
				// 如果进入到这里说明 拍卖品的结束日期等于系统当前的时时间
				// 要根據這個拍卖品ID 查找到 对应的用户
				UserBIZ userBIZ = new UserBIZImpl();
				User user = userBIZ.findUserListByAuctionId(auction
						.getAuctionID());
				if (user != null) {
					AuctionResultSender auctionResultSender = new AuctionResultSender(
							auction.getAuctionName(), user.getUserName(),
							user.getEmail());
					auctionResultSender.start();
					// 改變拍賣品的狀態 狀態改爲1 （已經有主人了）
					auctionBIZ.updateAuctionStateByAuctionId(auction
							.getAuctionID());
				}
			}
		}
		// 并且按照 价格的从高到底进行排序
	}

}
