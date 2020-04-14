<%@page import="com.qf.auction.enums.UserLoginStateEnum"%>
<%@page import="com.qf.auction.util.ValidataCodeUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String sysCode = ValidataCodeUtil.getValidataCode();
	request.getSession().setAttribute("sysCode", sysCode);
	// 这里获取登录状态
	// 之所以用了三元运算符 是为了防止 空指针的异常
	String result = request.getParameter("msg") == null ? "" : request
			.getParameter("msg");
	String userName = request.getParameter("username");
	String passWord = request.getParameter("password");
	System.out.print(userName);
	System.out.print(passWord);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action="UserLoginServlet" method="post">
		<div style="color: red;">
			<%
				if (result
						.equals(UserLoginStateEnum.USER_LOGIN_STATE_VALIDATE_CODE_ERROR
								.getValue())) {
			%>
			<%=UserLoginStateEnum.USER_LOGIN_STATE_VALIDATE_CODE_ERROR
						.getDesc()%>
			<%
				}
			%>
			<%
				if (result
						.equals(UserLoginStateEnum.USER_LOGIN_STATE_USER_NAME_FAIL
								.getValue())) {
			%>
			<%=UserLoginStateEnum.USER_LOGIN_STATE_USER_NAME_FAIL
						.getDesc()%>
			<%
				}
			%>
			<%
				if (result
						.equals(UserLoginStateEnum.USER_LOGIN_STATE_USER_PASSWORD_FAIL
								.getValue())) {
			%>
			<%=UserLoginStateEnum.USER_LOGIN_STATE_USER_PASSWORD_FAIL
						.getDesc()%>
			<%
				}
			%>
			<%
				if (result
						.equals(UserLoginStateEnum.USER_LOGIN_STATE_USER_OR_PASSWORD_FAIL
								.getValue())) {
			%>
			usname:
			<%=userName%>
			password:
			<%=passWord%>
			<%=UserLoginStateEnum.USER_LOGIN_STATE_USER_OR_PASSWORD_FAIL
						.getDesc()%>
			<%
				}
			%>
		</div>
		<div>
			用户名:<input name="username" />
		</div>
		<div>
			密码:<input name="password" type="password" />
		</div>
		<div>
			验证码:<input name="usercode" style="margin-right: 10px"> <span>
				系统验证码：<%=sysCode%> </span>
		</div>
		<div>
			<button>登录</button>
		</div>
	</form>
</body>
</html>
