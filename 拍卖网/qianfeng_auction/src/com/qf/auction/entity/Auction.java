package com.qf.auction.entity;

import java.io.Serializable;
import java.sql.Timestamp;

// 符合ORM
// 符合JAVABEAN
public class Auction implements Serializable {

	// ctrl + shift + y 转小写 ctrl + shift x 转大写
	private int auctionID;
	private String auctionName;
	private double auctionStartPrice;
	private double auctionUpset;
	private Timestamp auctionStartTime;
	private Timestamp auctionEndTime;
	private String auctionDESC;
	private String auctionPICPath;
	private Timestamp createTime;
	private Timestamp updateTime;
	private int state;

	public Auction() {
	}

	public Auction(int auctionID) {
		this.auctionID = auctionID;
	}

	public int getAuctionID() {
		return auctionID;
	}

	public void setAuctionID(int auctionID) {
		this.auctionID = auctionID;
	}

	public String getAuctionName() {
		return auctionName;
	}

	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}

	public double getAuctionStartPrice() {
		return auctionStartPrice;
	}

	public void setAuctionStartPrice(double auctionStartPrice) {
		this.auctionStartPrice = auctionStartPrice;
	}

	public double getAuctionUpset() {
		return auctionUpset;
	}

	public void setAuctionUpset(double auctionUpset) {
		this.auctionUpset = auctionUpset;
	}

	public Timestamp getAuctionStartTime() {
		return auctionStartTime;
	}

	public void setAuctionStartTime(Timestamp auctionStartTime) {
		this.auctionStartTime = auctionStartTime;
	}

	public Timestamp getAuctionEndTime() {
		return auctionEndTime;
	}

	public void setAuctionEndTime(Timestamp auctionEndTime) {
		this.auctionEndTime = auctionEndTime;
	}

	public String getAuctionDESC() {
		return auctionDESC;
	}

	public void setAuctionDESC(String auctionDESC) {
		this.auctionDESC = auctionDESC;
	}

	public String getAuctionPICPath() {
		return auctionPICPath;
	}

	public void setAuctionPICPath(String auctionPICPath) {
		this.auctionPICPath = auctionPICPath;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	

}
