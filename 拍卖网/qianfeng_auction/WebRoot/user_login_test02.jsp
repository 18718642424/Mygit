<%@page import="java.sql.SQLException"%>
<%@page import="com.qf.auction.entity.User"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- 小脚本 -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String userName = request.getParameter("username");
	String passWord = request.getParameter("password");
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	User user = null;
	try {
		// 1: 加载驱动
		// 自动修复 快捷键是 ctrl + 1
		Class.forName("com.mysql.jdbc.Driver");
		// 2:创建数据库连接（实例化connection）
		// 执行一个的函数的根据目的 就是为了 实例化 返回值
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/qianfeng", "root", "root");
		// 3:实例化数据库实例 并执行SQL语句
		preparedStatement = connection
				.prepareStatement("select * from user where userName=? and passWord=?");
		preparedStatement.setString(1, userName);
		preparedStatement.setString(2, passWord);
		// 4：实例化结果集
		resultSet = preparedStatement.executeQuery();
		// 5: 迭代结果集
		// .next() 函数指的 有数据就返回 TRUE 没数据 就返回 FALSE
		while (resultSet.next()) {
			// 如果代码能够进入到这里说明 查到了 用户
			user = new User();
			// 持久层开发 有一个规范 只要是操作到 数据库的字段 就改成大写 目的是为了提高代码的可读性
			user.setId(resultSet.getInt("ID"));
			user.setUserName(resultSet.getString("USERNAME"));
			user.setPassWord(resultSet.getString("PASSWORD"));
			user.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
			user.setCreateTime(resultSet.getTimestamp("CREATETIME"));
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		// alt + shift +z 自动try cath (使用的前提是 要把代码 选中)
		// 不管代码 是否是 一行代码 都不要不写 { }
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'user_login_test02.jsp' starting page</title>

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
	<%
		if (userName == null || userName.equals("")) {
	%>
	<div style="color: gray;">用户名不能为空</div>
	<%
		} else if (passWord == null || passWord.equals("")) {
	%>
	<div style="color: gray;">密码不能为空</div>
	<%
		} else if (user == null) {
	%>
	<div style="color: gray;">用户名或密码错误</div>
	<%
		} else {
	%>
	<div style="color: red;">登录成功</div>
	<%
		}
	%>

	<br>
</body>
</html>
