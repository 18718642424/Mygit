package com.qf.auction.entity;

import java.sql.Timestamp;

public class Note {
	private int id;
	private String phone;
	private int validatecode;
	private Timestamp createTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getValidatecode() {
		return validatecode;
	}
	public void setValidatecode(int validatecode) {
		this.validatecode = validatecode;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


}
