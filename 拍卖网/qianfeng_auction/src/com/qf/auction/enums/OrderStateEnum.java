package com.qf.auction.enums;

public enum OrderStateEnum {

	ORDER_ADD_SUCCESS("order_add_success", "订单添加成功"), ORDER_ADD_FAIL(
			"order_add_fail", "订单添加失败");

	private String value;
	private String desc;

	// alt + shift + s
	private OrderStateEnum(String value, String desc) {
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
