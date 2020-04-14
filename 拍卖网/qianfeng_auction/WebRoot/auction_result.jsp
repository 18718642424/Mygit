<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!--  jstl 标签库  它的诞生 就一个目的  希望 降低JSP 实现 网页动态化的难度  （
  之前我们的JSP 是通过 小脚本加表达式 来实现网页动态化的  这个JSTL标签库 就是 为了给JSP的实现 网页动态 提供更丰富的手段

）-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="wrap">
		<h2>
			<a style="font-style:italic;color: red;"
				href="javascript:history.go(-1)">返回</a>
		</h2>
		<!--
		   requestScope 如果写出来的话  就是指定去 请求报文抓取数据
		        如果不写的话  它会先去 requestScope 抓取数据 然后再去  sessionScope
		   requestScope.map  就等同于  request.getAttribute("map")
		   requestScope.map['end'] 就等同于 ((HashMap<String,Object>) request.getAttribute("map")).get("end")
		  -->
		<c:if test="${fn:length(requestScope.map['end'])!=0}">
			<h1>拍卖结束的商品</h1>
			<div class="items records">
				<ul class="rows even strong rowh">
					<li>名称</li>
					<li>开始时间</li>
					<li>结束时间</li>
					<li>起拍价</li>
					<li class="borderno record">图片</li>
					<div class="cl"></div>
				</ul>

                  <!--
                                                 以下foreach 循环 等同于 JAVA代码
                   Map<String,Object> map =(HashMap<String,Object>) request.getAttribute("map");
                   List<Auction> auctions = map.get("end");
                   for(Auction auction:auctions){
                      System.out.println(auction.getAuctionName);
                      System.out.println(auction.getAuctionStartTime);
                     ......... 
                   }
                    -->

				<c:forEach items="${requestScope.map['end'] }" var="auction">
					<ul class="rows">
						<li>${auction.auctionName }</li>
						<li>${auction.auctionStartTime }</li>
						<li>${auction.auctionEndTime }</li>
						<li>${auction.auctionStartPrice }</li>
						<li><img src="<%=basePath %>upload/${auction.auctionPICPath }"
							width="270" alt="" /></li>
						<li class="borderno blue record"></li>
					</ul>
				</c:forEach>
			</div>
		</c:if>

		<c:if test="${fn:length(requestScope.map['notend'])!=0}">
			<h1>拍卖中的商品</h1>
			<div class="items records">
				<ul class="rows even strong rowh">
					<li>名称</li>
					<li>开始时间</li>
					<li>结束时间</li>
					<li>起拍价</li>
					<li class="borderno record">图片</li>
					<div class="cl"></div>
				</ul>
				<c:forEach items="${requestScope.map['notend'] }" var="auction">
					<ul class="rows">
						<li>${auction.auctionName }</li>
						<li>${auction.auctionStartTime }</li>
						<li>${auction.auctionEndTime }</li>
						<li>${auction.auctionStartPrice }</li>
						<li><img src="<%=basePath %>upload/${auction.auctionPICPath }"
							width="270" alt="" /></li>
						</li>
					</ul>
				</c:forEach>
			</div>
		</c:if>

		<!-- main end-->
	</div>
</body>
</html>
