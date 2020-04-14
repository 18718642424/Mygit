package com.qf.auction.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuctionRecord implements Serializable {
	private int id;
	// 在ORM 中 如果 字段是 另外一张表的主键 那么在这个字段中 应该是
	// 对应表的 entity
	private User user;
	private Auction auction;
	private Timestamp auctionTime;
	private double auctionPrice;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Auction getAuction() {
		return auction;
	}
	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	public Timestamp getAuctionTime() {
		return auctionTime;
	}
	public void setAuctionTime(Timestamp auctionTime) {
		this.auctionTime = auctionTime;
	}
	public double getAuctionPrice() {
		return auctionPrice;
	}
	public void setAuctionPrice(double auctionPrice) {
		this.auctionPrice = auctionPrice;
	}
    
	
	
    
}
