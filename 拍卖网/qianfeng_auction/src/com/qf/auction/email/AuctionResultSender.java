package com.qf.auction.email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class AuctionResultSender extends Thread {

	private String auctionname;
	private String userName;
	private String userEmail;

	public AuctionResultSender(String auctionname, String userName,
			String userEmail) {
		this.auctionname = auctionname;
		this.userName = userName;
		this.userEmail = userEmail;
	}

	// alt + shift +s
	@Override
	public void run() {
		try {
			Properties properties = new Properties();
			properties.load(AuctionResultSender.class
					.getResourceAsStream("/email.properties"));
			// 实例化一个 session (这份是EMAIL 的SESSION和之前的 httpsession 完全是两个文件)
			Session session = Session.getInstance(properties);
			// 在控制台可以 查看邮件发送详情 需要把session debug 开启
			session.setDebug(true);
			// 实例化transport
			// transport 用来 确认 发送地址 确认发送邮箱的服务器 和 发送邮件
			Transport transport = session.getTransport();
			transport.connect(properties.getProperty("email.host"),
					properties.getProperty("username"),
					properties.getProperty("password"));
			// mimemsseage 这个文件用来设置我们 邮件的具体内容
			MimeMessage mimeMessage = new MimeMessage(session);
			// 这里设置标题
			mimeMessage.setSubject("拍卖竞拍结果通知");
			// 这里设置邮箱的内容
			mimeMessage
					.setContent(
							"恭喜您"
									+ userName
									+ "您成功拍得了 "
									+ auctionname
									+ " ,您可以 选择该连接查看商品详情<a style='color:red'>localhost:8080/qianfeng_auction/AuctionDetailServlet?auctionId=7</a>",
							"text/html;charset=utf-8");
			// 这里设置 是谁发送的邮件
			mimeMessage.setFrom(new InternetAddress(properties
					.getProperty("username")));
			// 这里设置 发送给谁
			mimeMessage.setRecipient(Message.RecipientType.TO,
					new InternetAddress(userEmail));
			// 发送邮件
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			// 发送完邮件不要忘记关闭
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 如何防止自己的邮箱 进入到垃圾邮件
	// 邮箱要尽量的简洁 减少图片
	public static void main(String[] args) {
		try {
			Properties properties = new Properties();
			properties.load(AuctionResultSender.class
					.getResourceAsStream("/email.properties"));
			// 实例化一个 session (这份是EMAIL 的SESSION和之前的 httpsession 完全是两个文件)
			Session session = Session.getInstance(properties);
			// 在控制台可以 查看邮件发送详情 需要把session debug 开启
			session.setDebug(true);
			// 实例化transport
			// transport 用来 确认 发送地址 确认发送邮箱的服务器 和 发送邮件
			Transport transport = session.getTransport();
			transport.connect(properties.getProperty("email.host"),
					properties.getProperty("username"),
					properties.getProperty("password"));
			// mimemsseage 这个文件用来设置我们 邮件的具体内容
			MimeMessage mimeMessage = new MimeMessage(session);
			// 这里设置标题
			mimeMessage.setSubject("来自雷军的问候");
			// 这里设置邮箱的内容
			mimeMessage.setContent(
					"<a style='color:red'> Are you ok???????</a>",
					"text/html;charset=utf-8");
			// 这里设置 是谁发送的邮件
			mimeMessage.setFrom(new InternetAddress(properties
					.getProperty("username")));
			// 这里设置 发送给谁
			mimeMessage.setRecipient(Message.RecipientType.TO,
					new InternetAddress("627764445@qq.com"));
			// 发送邮件
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			// 发送完邮件不要忘记关闭
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
