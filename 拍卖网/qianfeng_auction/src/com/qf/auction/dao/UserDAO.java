package com.qf.auction.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.qf.auction.entity.User;

// 实际上围绕着user表的所有操作 都应该 这里声明
public interface UserDAO {
	// 定义返回值的跟本原则 是考虑上一层 需要什么
	User userLogin(String userName, String password);

	Set<User> findUserByPage(BigDecimal pageIndex, BigDecimal pageNum);

	Set<User> getAll();

	List<User> findUserListByPage(BigDecimal pageIndex, BigDecimal pageSize);

	BigDecimal getAllCount();

	int addUser(User user);

	int updateUser(User user);

	int userDel(int userid);

	List<User> searchUserByName(String username);

	User findUserListByAuctionId(int auctionId);

	int userRegister(User user);

	boolean checkUserNameIsExist(String userName);

}
