package com.qf.auction.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

// VO文件也要符合JAVABEAN
public class AuctionListPageVO<T> implements Serializable {

	private List<T> lists;
	private BigDecimal pageIndex;
	private BigDecimal pageNumber;
	private BigDecimal total;
	private BigDecimal endPage;

	public List<T> getlists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	public BigDecimal getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(BigDecimal pageIndex) {
		this.pageIndex = pageIndex;
	}

	public BigDecimal getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(BigDecimal pageNumber) {
		this.pageNumber = pageNumber;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getEndPage() {
		return endPage;
	}

	public void setEndPage(BigDecimal endPage) {
		this.endPage = endPage;
	}

	public List<T> getLists() {
		return lists;
	}
	
	

}
