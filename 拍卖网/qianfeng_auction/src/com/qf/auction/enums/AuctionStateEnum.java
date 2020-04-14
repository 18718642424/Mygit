package com.qf.auction.enums;

public enum AuctionStateEnum {

	AUCTION_ADD_SUCCESS("add_success", "添加成功"), AUCTION_ADD_FAIL("add_fail",
			"添加失败"), AUCTION_UPDATE_SUCCESS("update_success", "修改成功"), AUCTION_UPDATE_FAIL(
			"update_fail", "修改失败"), AUCTION_DEL_SUCCESS("del_success", "删除成功"), AUCTION_DEL_FAIL(
			"del_fail", "删除失败"),AUCTION_SUCCESS("auction_success","竟拍成功"),AUCTION_FAIL("auction_fail","竟拍失敗");

	private String value;
	private String desc;

	private AuctionStateEnum(String value, String desc) {
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
