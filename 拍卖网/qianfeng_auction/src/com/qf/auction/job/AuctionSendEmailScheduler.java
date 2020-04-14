package com.qf.auction.job;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

public class AuctionSendEmailScheduler extends Thread {

	// alt + shift + s
	@Override
	public void run() {
		// 实例化一个 jobdetail
		// 第一个参数指的是 任务的名字 第二个参数 是任务组的名字 第三个参数是 你要执行的任务文件
		try {
			JobDetail jobDetail = new JobDetail("AuctionSendEmailJob",
					"auction", AuctionSendEmailJob.class);
			// 现在写成这样就是 每五秒 执行一次 AuctionSendEmailJob execute 这个函数
			CronTrigger cronTrigger = new CronTrigger(
					"AuctionSendEmailJobTrigger", "auctionTrigger",
					"0/5 * * * * ?");
			// scheduler 是用來 管理job 和 trigger (對他們的添加，获取，和調用)
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.scheduleJob(jobDetail, cronTrigger);
			scheduler.start();
			// Thread.sleep(20000);
			// scheduler.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

}
