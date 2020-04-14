package com.qf.auction.enums;
public enum UserLoginStateEnum {
	// 枚举的命名的规范是 纯大写 多个单词用_隔开 （不要舍不得用 下划线）
	USER_LOGIN_STATE_VALIDATE_CODE_ERROR("validate_error","验证码错误"),
	USER_LOGIN_STATE_USER_NAME_FAIL("user_name_fail","用户名格式不正确"), 
	USER_LOGIN_STATE_USER_PASSWORD_FAIL("password_fail","密码格式不正确"),
	USER_LOGIN_STATE_USER_OR_PASSWORD_FAIL("pwd_usname_fail","用户名或密码错误"),
	USER_LOGIN_STATE_SUCCESS("login_success","登录成功");
	
	// 需要有私有的属性
	private String value;
	private String desc;
	
	// 以下代码的快捷键 是 alt + shift + s
	// 提供私有的 有参构造函数
	private UserLoginStateEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	// 提供geter ,seter
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

