package com.qf.auction.enums;

public enum UserStateEnum {
	USER_ADD_SUCCESS("add_success","用户注冊成功"),
	USER_ADD_FAIL("add_fail","用户注冊失败"),
	USER_UPDATE_SUCCESS("update_success","修改成功"),
	USER_UPDATE_FAIL("update_fail","修改失败"),
	USER_DEL_SUCCESS("delete_success","删除成功"),
	USER_DEL_FAIL("del_fail","删除失败"),
	USER_ADD_PASSWORD_REPASSWORD_ERROR("add_repassword_fail","添加失败两次输入的密码不一致"),
	USER_EMAIL_FAIL("email_fail","邮箱不正确"),
	USER_PHONE_FAIL("phone_fail","手机号不正确"),
	USER_USER_NAME_FAIL("username_fail","用户名不正确"),
	USER_PASSWORD_FAIL("password_fail","密碼格式不正確必須大於6位數"),
	USER_VALIDATE_CODE_FAIL("user_validaye_code_fail","验证码错误"),
	USER_VALIDATE_CODE_TIME_OUT("user_validate_code_time_out","验证码过时"),
	USER_USER_NAME_EXIST("user_user_name_exist","用户名已存在");
	
	
	private String value;
	private String desc;
	// 接下来的代码都是自动生成的

	private UserStateEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	
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
