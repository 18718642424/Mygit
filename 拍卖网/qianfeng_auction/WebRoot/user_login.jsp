<%@page import="com.qf.auction.enums.UserStateEnum"%>
<%@page import="com.qf.auction.enums.UserLoginStateEnum"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String result = request.getParameter("msg") == null ? "" : request
			.getParameter("msg");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户登录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css/common.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript">
	
<%if (result.equals(UserLoginStateEnum.USER_LOGIN_STATE_VALIDATE_CODE_ERROR.getValue())) {%>
	
alert('<%=UserLoginStateEnum.USER_LOGIN_STATE_VALIDATE_CODE_ERROR.getDesc()%>');
	
<%}%>
	
<%if (result.equals(UserLoginStateEnum.USER_LOGIN_STATE_USER_NAME_FAIL.getValue())) {%>
	
alert('<%=UserLoginStateEnum.USER_LOGIN_STATE_USER_NAME_FAIL.getDesc()%>');
	
<%}%>
	
<%if (result.equals(UserLoginStateEnum.USER_LOGIN_STATE_USER_PASSWORD_FAIL.getValue())) {%>
	
 alert('<%=UserLoginStateEnum.USER_LOGIN_STATE_USER_PASSWORD_FAIL.getDesc()%>');
	
<%}%>
	
<%if (result.equals(UserLoginStateEnum.USER_LOGIN_STATE_USER_OR_PASSWORD_FAIL.getValue())) {%>
	
 window.alert('<%=UserLoginStateEnum.USER_LOGIN_STATE_USER_OR_PASSWORD_FAIL.getDesc()%>');
	
<%}%>
	
<%if (result.equals(UserStateEnum.USER_ADD_SUCCESS.getValue())) {%>
	
 window.alert('<%=UserStateEnum.USER_ADD_SUCCESS.getDesc()%>');
	
<%}%>

</script>
</head>
<body>
	<div class="wrap">
		<!-- main begin-->
		<div class="main">
			<div class="sidebar">
				<p>
					<img src="images/img1.jpg" width="443" height="314" alt="" />
				</p>
			</div>
			<div class="sidebarg">
				<!-- form 表单代表向服务器提交数据的表单 之前我们知道了 用户向服务器提交数据的方式
     localhost:8080/auction_chapter/******?usname=test1&pwd=123
     
     FORM中的
     INPUT表单 代表向服务器提交  数据的元素 ！
     INPUT 也是以键值的方式去提交  提交数据到 请求行中
   
     -->
				<form action="UserLoginServlet" method="post">
					<div class="login">
						<dl>
							<dt class="blues">用户登陆</dt>
							<dd>
								<label for="name">用户名：</label> <input type="text"
									name="username" class="inputh" value="${username}" id="name" />
							</dd>
							<dd>
								<label for="password">密 码：</label> <input type="password"
									name="password" class="inputh" value="${userpassword}"
									id="password" />
							</dd>
							<dd>
								<label class="lf" for="passwords">验证码：</label> <input
									type="text" name="usercode" class="inputh inputs lf"
									value="验证码" id="passwords" /> <span class="wordp lf"><img
									id="validateCode" src="number.jsp" width="96" height="27"
									alt="" /> </span> <span class="blues lf"><a id="changeCode"
									href="javascript:void(0);" title="">看不清</a> </span>
							</dd>
							<dd>
								<input name="" type="checkbox" id="rem_u" /> <span
									class="rem_u">下次自动登录</span>
							</dd>
							<dd class="buttom">
								<input name="" type="submit" value="登 录"
									class="spbg buttombg f14 lf" /> <input id="register" name=""
									type="button" value="注 册" class="spbg buttombg f14 lf" /> <span
									class="blues  lf"><a href="" title="">忘记密码?</a> </span>
								<div class="cl"></div>
							</dd>
						</dl>
					</div>
				</form>
			</div>
			<div class="cl"></div>
		</div>
		<!-- main end-->
		<!-- footer begin-->
	</div>
	<!--footer end-->
</body>
</html>
