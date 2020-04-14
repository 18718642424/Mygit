package com.qf.auction.biz;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.qf.auction.entity.User;
import com.qf.auction.servlet.UserUpdateServlet;

public interface UserBIZ {

	String userLogin(String username, String password, String sysCode,
			String userInput, HttpSession session);

	Set<User> getAll();

	List<User> findUserListByPage(BigDecimal pageIndex, BigDecimal pageSize);

	BigDecimal getAllCount();

	String userAdd(String userName, String passWord, String repassWord);

	String userUpdate(int id, String userName, String password);

	String userDel(int userid);

	List<User> searchUserByName(String username);

	User findUserListByAuctionId(int auctionId);

	String userRegister(User user, String repassword,int validateCode,String phonenumber);
	
	boolean checkUserNameIsExist(String userName);

}
