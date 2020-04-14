<%-- <%@page import="com.qianfeng.enums.AuctionStateEnum"%>
 --%><%@page import="com.qf.auction.enums.AuctionStateEnum"%>
<%@page import="com.qf.auction.vo.AuctionListPageVO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	int pageIndex = ((AuctionListPageVO) request
			.getAttribute("auctionPageInfo")).getPageIndex().intValue();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	function goToPage(pageIndex) {
		document.forms[1].action = document.forms[1].action + "?pageIndex="
				+ pageIndex + "&pageNum=${auctionPageInfo.pageNumber}";
		document.forms[1].submit();
	}

	function changePageNum(arg) {
		location.href = "AuctionListByPageServlet"
				+ "?pageIndex=${auctionPageInfo.pageIndex}&pageNum=" + arg + "";
	}

	function delAuction(arg) {

		if (window.confirm("您确认要删除吗?")) {
			var $userarg = $(arg);
			var auctionid = arg.getAttribute("auctionid");
			var userdata = {
				"auctionid" : auctionid
			};

			// ajax
			// 执行ajax操作的根本意义在于  获取 成功回调函数的 数据  (也就是第53行中的DATA)
			$.ajax({
				// 删除操作的 servlet 地址
				url : "AuctionDelByIdServlet",
				type : "post",
				// 向服务器发送的数据
				data : userdata,
				// 成功回调函数的数据的操作方式
				dataType : "json",
				// data 就是成功回调函数的数据（服务器写 响应报文中的数据）
				success : function(data) {
					if (data) {
						$userarg.parent().parent().remove();
					} else {
						alert("网络异常请重新再试");
					}
				},
				error : function() {
					alert("网络异常请重新再试");
				}
			});

		}

	}

	$(function() {
		$("#pagenum option[value=${auctionPageInfo.pageNumber}]").attr(
				"selected", "select");
	});
</script>
<script type="text/javascript">
	
<%String msg = request.getParameter("msg");
			if (AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue().equals(msg)) {
				out.print("alert('"
						+ AuctionStateEnum.AUCTION_ADD_SUCCESS.getDesc()
						+ "');");
			}
			if (AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getValue().equals(msg)) {
				out.print("alert('"
						+ AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getDesc()
						+ "');");
			}%>
	
</script>
</head>

<body>
	<form action="AuctionListByPageServlet" method="post">
		<div class="forms">
			<label for="name">名称</label> <input name="auctionName" type="text"
				class="nwinput" id="name" /> <label for="time">开始时间</label> <input
				name="auctionStartTime" type="text" id="time" class="nwinput" /> <label
				for="end-time">结束时间</label> <input name="auctionEndTime" type="text"
				id="end-time" class="nwinput" /> <label for="price">起拍价</label> <input
				name="auctionStartPrice" type="text" id="price" class="nwinput" />
			<input type="submit" value="查询"
				class="spbg buttombg f14  sale-buttom" />
			<c:if test="${sessionScope.user.userIsAdmin==true}">
				<input type="button"
					onclick="location='add_auction.jsp?pageNum=${auctionPageInfo.pageNumber}'"
					value="发布" class="spbg buttombg f14  sale-buttom buttomb" />
			</c:if>
			<br /> &nbsp;&nbsp;&nbsp;&nbsp;<a href="AuctionResultServlet"><b>查看竞拍结果</b>
			</a>
		</div>
	</form>


	<form method="post" action="AuctionListByPageServlet">
		<div class="wrap">
			<!-- main begin-->
			<div class="sale">
				<h1 class="lf">在线拍卖系统</h1>
				<c:if test="${not empty sessionScope.user}">
					<div class="logout right">
						<a href="AuctionLogoutServlet" title="注销">注销</a>
					</div>
				</c:if>
				<c:if test="${empty sessionScope.user}">
					<div class="logout right">
						<a href="login.jsp" title="登录">登录</a>
					</div>
				</c:if>
			</div>
			<div class="items" style="width: 1120px">
				<ul class="rows even strong">
					<li>名称</li>
					<li class="list-wd">描述</li>
					<li>开始时间</li>
					<li>结束时间</li>
					<li>起拍价</li>
					<li>拍卖品图片</li>
					<li class="borderno">操作</li>
				</ul>
				<c:forEach items="${auctionPageInfo.lists }" var="auction">
					<ul class="rows">
						<li>${auction.auctionName }</li>
						<!-- getAuctionDesc()  -->
						<li class="list-wd">${auction.auctionDESC }</li>
						<li>${auction.auctionStartTime }</li>
						<li>${auction.auctionEndTime }</li>
						<li>${auction.auctionStartPrice }</li>
						<li><img alt="该拍卖品暂无图片"
							src="<%=basePath%>upload/${auction.auctionPICPath }"
							style="width: 100%;height: 30px">
						</li>
						<li class="borderno red"><c:if
								test="${sessionScope.user.userIsAdmin==true }">
								<a
									href="AuctionFindByIdServlet?auctionid=${auction.auctionID }&pageIndex=${auctionPageInfo.pageIndex}">修改</a>
								<!-- this 指的当前的 整个元素  -->
								<!-- auctionid 代表 元素自定义属性  它的一个使用场景 通常出现在 动态的元素列表  操作的时候  -->
								<a auctionid="${auction.auctionID}" onclick="delAuction(this)">删除</a>
							</c:if> <c:if test="${sessionScope.user.userIsAdmin==false }">
								<a
									href="AuctionRecordServlet?auctionId=${auction.auctionID }&pageIndex=${auctionPageInfo.pageIndex}">竞拍</a>
							</c:if></li>
					</ul>
				</c:forEach>
				<%
					int count = 0;
				%>
				<div class="page">
					<select id="pagenum" onchange="changePageNum(this.value)">
						<option value="5">5</option>
						<option value="10">10</option>
						<option value="15">15</option>
						<option value="20">20</option>
						<option value="30">30</option>
					</select> <a href="javascript:goToPage(1)">首页</a>
					<c:if test="${auctionPageInfo.pageIndex!=1}">
						<a href="javascript:goToPage(${auctionPageInfo.pageIndex-1 })">上一页</a>
					</c:if>
					<c:forEach step="1" begin="1" end="${auctionPageInfo.endPage }"
						var="pageIndex">
						<%
							count++;
						%>
						<%
							if (count == pageIndex) {
						%>
						<a href="javascript:goToPage(<%=count%>)" style="color: red">
							<%=count%></a>
						<%
							} else {
						%>
						<a href="javascript:goToPage(<%=count%>)"> <%=count%></a>
						<%
							}
						%>
					</c:forEach>
					<c:if test="${auctionPageInfo.pageIndex!=auctionPageInfo.endPage}">
						<a href="javascript:goToPage(${auctionPageInfo.pageIndex+1 })">下一页</a>
					</c:if>
					<a href="javascript:goToPage(${auctionPageInfo.endPage })">尾页</a>
				</div>
			</div>
			<!-- main end-->
		</div>
	</form>


</body>
</html>
