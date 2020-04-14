package com.qf.auction.entity;

import java.io.Serializable;
import java.sql.Timestamp;

// 实现了 序列化接口后 这个文件 可以被 序列化和反序列化（可以存储到硬盘中 同时也可以存到内存中）
public class User implements Serializable {

	// 属性名 必须要驼峰格式 开头的单词 必须小写后面的每一个单词的开头必须大写
	private int id;
	private String userName;
	private String passWord;
	private Timestamp createTime;
	private Timestamp updateTime;
	private boolean userIsAdmin;
	private String email;
	private String phoneNumber;
	// 无参构造函数 默认就有的 不需要 手动写出来 （前提是没有 有参构造函数的前提下）
	public User() {
	}

	// 一旦有了 有参构造函数 就会覆盖掉 无参构造函数
	public User(int id) {
		this.id = id;
	}

	public User(int id, String userName, String passWord, Timestamp createTime, Timestamp updateTime) {
		super();
		this.id = id;
		this.userName = userName;
		this.passWord = passWord;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// tostring 函数写在构造函数下面
	// 快捷方式还是 alt + shift + s

	// geter seter 一定是 写在代码的最下面 （永恒的真理）
	// 函数名必须驼峰规则
	// 不要在geter 和 seter 中 添加代码
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * 设置拍卖品创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isUserIsAdmin() {
		return userIsAdmin;
	}

	public void setUserIsAdmin(boolean userIsAdmin) {
		this.userIsAdmin = userIsAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
