package com.qf.auction.bizimpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.qf.auction.biz.NoteBIZ;
import com.qf.auction.biz.UserBIZ;
import com.qf.auction.dao.UserDAO;
import com.qf.auction.daoimpl.UserDAOImpl;
import com.qf.auction.entity.User;
import com.qf.auction.enums.UserLoginStateEnum;
import com.qf.auction.enums.UserStateEnum;
import com.qf.auction.util.StringUtil;
import com.qf.auction.util.ValidateUtil;

public class UserBIZImpl implements UserBIZ {

	UserDAO userDAO = new UserDAOImpl();

	@Override
	public String userLogin(String username, String password, String sysCode, String userInput, HttpSession session) {
		if (userInput == null || userInput.equals("") || sysCode == null) {
			return UserLoginStateEnum.USER_LOGIN_STATE_VALIDATE_CODE_ERROR.getValue();
		}
		if (!userInput.equals(sysCode)) {
			return UserLoginStateEnum.USER_LOGIN_STATE_VALIDATE_CODE_ERROR.getValue();
		}
		if (username == null || username.equals("")) {
			return UserLoginStateEnum.USER_LOGIN_STATE_USER_NAME_FAIL.getValue();
		}
		if (password == null || password.equals("")) {
			return UserLoginStateEnum.USER_LOGIN_STATE_USER_PASSWORD_FAIL.getValue();
		}

		User user = null;
		// 调用DAO层 实例化 user
		user = userDAO.userLogin(username, password);
		if (user == null) {
			return UserLoginStateEnum.USER_LOGIN_STATE_USER_OR_PASSWORD_FAIL.getValue();
		}
		// 如果代码能进入到这里说明 登录成功
		// 如果登录成功把 用户存入到SESSION中
		// request 也是用户级别的作用域 而且只限于一次HTTP请求 session 是用户级的作用域 application
		// 是服务器级别的作用域
		session.setAttribute("user", user);
		return UserLoginStateEnum.USER_LOGIN_STATE_SUCCESS.getValue();
	}

	@Override
	public Set<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public List<User> findUserListByPage(BigDecimal pageIndex, BigDecimal pageSize) {
		return userDAO.findUserListByPage(pageIndex.subtract(new BigDecimal(1)).multiply(pageSize), pageSize);
	}

	@Override
	public BigDecimal getAllCount() {
		return userDAO.getAllCount();
	}

	@Override
	public String userAdd(String userName, String passWord, String repassWord) {
		if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(passWord) || StringUtil.isEmpty(repassWord)) {
			return UserStateEnum.USER_ADD_FAIL.getValue();
		}
		if (passWord.length() < 6) {
			return UserStateEnum.USER_ADD_FAIL.getValue();
		}
		if (!passWord.equals(repassWord)) {
			return UserStateEnum.USER_ADD_PASSWORD_REPASSWORD_ERROR.getValue();
		}
		User user = new User();
		user.setUserName(userName);
		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
		user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		int executeCount = userDAO.addUser(user);
		if (executeCount == 0) {
			return UserStateEnum.USER_ADD_FAIL.getValue();
		}
		return UserStateEnum.USER_ADD_SUCCESS.getValue();
	}

	@Override
	public String userUpdate(int id, String userName, String password) {
		if (userName == null || userName.equals("") || password == null || password.equals("") || id == 0) {
			return UserStateEnum.USER_UPDATE_FAIL.getValue();
		}
		if (password.length() < 6) {
			return UserStateEnum.USER_UPDATE_FAIL.getValue();
		}
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setPassWord(password);
		user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		int executeCount = userDAO.updateUser(user);
		if (executeCount == 0) {
			return UserStateEnum.USER_UPDATE_FAIL.getValue();
		}
		return UserStateEnum.USER_UPDATE_SUCCESS.getValue();
	}

	@Override
	public String userDel(int userid) {
		int executeCount = userDAO.userDel(userid);
		if (executeCount == 0) {
			return UserStateEnum.USER_DEL_FAIL.getValue();
		}
		return UserStateEnum.USER_DEL_SUCCESS.getValue();
	}

	@Override
	public List<User> searchUserByName(String username) {
		return userDAO.searchUserByName(username);
	}

	@Override
	public User findUserListByAuctionId(int auctionId) {
		return userDAO.findUserListByAuctionId(auctionId);
	}

	@Override
	public String userRegister(User user, String repassword, int validateCode, String phonenumber) {
		if (StringUtil.isEmpty(user.getPassWord()) || StringUtil.isEmpty(repassword)
				|| StringUtil.isEmpty(user.getUserName()) || StringUtil.isEmpty(user.getPhoneNumber())
				|| StringUtil.isEmpty(user.getEmail())) {
			return UserStateEnum.USER_ADD_FAIL.getValue();
		}
		if (user.getUserName().length() < 6) {
			return UserStateEnum.USER_USER_NAME_FAIL.getValue();
		}
		if (user.getPassWord().length() < 6) {
			return UserStateEnum.USER_PASSWORD_FAIL.getValue();
		}
		if (!user.getPassWord().equals(repassword)) {
			return UserStateEnum.USER_ADD_PASSWORD_REPASSWORD_ERROR.getValue();
		}
		if (!ValidateUtil.isEmailValid(user.getEmail())) {
			return UserStateEnum.USER_EMAIL_FAIL.getValue();
		}
		if (!ValidateUtil.isMobileValid(user.getPhoneNumber())) {
			return UserStateEnum.USER_PHONE_FAIL.getValue();
		}
		if (userDAO.checkUserNameIsExist(user.getUserName())) {
			return UserStateEnum.USER_USER_NAME_EXIST.getValue();
		}
		NoteBIZ noteBIZ = new NoteBIZImpl();
		int sysValidateCode = noteBIZ.findValidateCodeByPhoneNumber(phonenumber);
		if (sysValidateCode != validateCode) {
			return UserStateEnum.USER_VALIDATE_CODE_FAIL.getValue();
		}
		// 超时这里不需要实现 注册本身 不需要这个业务处理 和安全相关的操作可以考虑添加这个业务
		// 实现超时判断的思路就是把当前手机号最后创建的那条数据抓出来 然后和当前系统时间做对比
		int executeCount = userDAO.userRegister(user);
		if (executeCount == 0) {
			return UserStateEnum.USER_ADD_FAIL.getValue();
		}
		return UserStateEnum.USER_ADD_SUCCESS.getValue();
	}

	@Override
	public boolean checkUserNameIsExist(String userName) {
		return userDAO.checkUserNameIsExist(userName);
	}

}
