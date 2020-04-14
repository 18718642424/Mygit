<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	/*
	 预习的内容 JSP的9大内置对象
	 */
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Random random = new Random();
	String sysCode = "1234567890QWERTYUIOPLKJHGFDSAZXCVBNM";
	StringBuilder stringBuilder = new StringBuilder(4);
	for (int i = 0; i <= stringBuilder.length(); i++) {
		char temp = sysCode.charAt(random.nextInt(sysCode.length()));
		
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'user_login_test01.jsp' starting page</title>

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
	<form action="user_login_test02.jsp" method="post">
		用户名： <input name="username"> </br> 密码：<input name="password"
			type="password">
		<button>登录</button>
	</form>
</body>
</html>
