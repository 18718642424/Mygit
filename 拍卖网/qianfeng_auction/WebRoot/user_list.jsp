<%@page import="com.qf.auction.enums.UserStateEnum"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.qf.auction.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
BigDecimal pageNum =  request.getAttribute("pagenum")!=null? (BigDecimal)request.getAttribute("pagenum"):new BigDecimal(1);
// msg 就是用户的 添加 修改 删除 的状态
String msg = request.getParameter("msg") == null ? "":request.getParameter("msg");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'user_list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
#userTable {
	margin-top: 100px;
}

.userlist_td {
	border: solid 1px red;
}

.user_a {
	margin-right: 5px;
}

.add_li {
	margin-top: 50px;
	margin-left: 80px;
}
</style>

<script type="text/javascript">
	function addShow() {
		document.getElementById("user_merge").style.display = "";
		document.getElementById("repassword_li").style.display = "";
		document.getElementById("user_add_button").innerHTML = "添加";
		document.forms[1].action = "UserAddServlet";
	}

	function addHide() {
		document.getElementById("user_merge").style.display = "none";
	}
	function updateUser(arg) {
		document.getElementById("user_merge").style.display = "";
		document.getElementById("repassword_li").style.display = "none";
		document.getElementById("user_add_button").innerHTML = "修改";
		document.forms[1].action = "UserUpdateServlet";
		// 所有的修改操作 都涉及到数据的回显
		// 数据的回显的分为两种
		// (1)数据的来源  源自于 数据网格
		// (2)数据的来源  源自于 数据库
		// 分别适用于什么场景？ 当要展示的数据是重要数据 （不允许出现并发的数据  比如财务数据 不适合做） 
		// 这种情况就使用 (2) 反之 使用（1）性能更高速度更快
		// getAttribute获取元素的属性（所有元素 都有该函数）  getAttribute（"username12345"）  就等于
		// <div username12345="哈哈"></div>
		document.getElementById("username").value = arg
				.getAttribute("username");
		document.getElementById("password").value = arg
				.getAttribute("password");
		document.getElementById("userid").value = arg.getAttribute("userid");
	}

	function userDel(arg) {
		if (confirm("您确认要删除吗?")) {
			var userid = arg.getAttribute("userid");
			location.href = "UserDeleteByIdServlet?userid=" + userid + "";
		}
	}
</script>

<script type="text/javascript">
	
<%if(msg.equals(UserStateEnum.USER_ADD_SUCCESS.getValue())){
       out.print("alert('"+ UserStateEnum.USER_ADD_SUCCESS.getDesc() +"')");
     }
     if(msg.equals(UserStateEnum.USER_ADD_FAIL.getValue())){
       out.print("alert('"+UserStateEnum.USER_ADD_FAIL.getDesc()+"')");
     }
     if(msg.equals(UserStateEnum.USER_ADD_PASSWORD_REPASSWORD_ERROR.getValue())){
       out.print("alert('"+UserStateEnum.USER_ADD_PASSWORD_REPASSWORD_ERROR.getDesc()+"')");
     }
     if(msg.equals(UserStateEnum.USER_UPDATE_FAIL.getValue())){
       out.print("alert('"+UserStateEnum.USER_UPDATE_FAIL.getDesc()+"')");
     }
     if(msg.equals(UserStateEnum.USER_UPDATE_SUCCESS.getValue())){
       out.print("alert('"+UserStateEnum.USER_UPDATE_SUCCESS.getDesc()+"')");
     }
          if(msg.equals(UserStateEnum.USER_DEL_FAIL.getValue())){
       out.print("alert('"+UserStateEnum.USER_DEL_FAIL.getDesc()+"')");
     }
          if(msg.equals(UserStateEnum.USER_DEL_SUCCESS.getValue())){
       out.print("alert('"+UserStateEnum.USER_DEL_SUCCESS.getDesc()+"')");
     }%>
	
</script>

</head>

<body>
	<div id="userTable">
		<!-- 网页动态化 就是 JSP 的小脚本 结合  JSP的表达式 来实现     -->
		<table style="text-align: center;margin: 0 auto;" align="center">
			<tr>
				<td class="userlist_td" colspan="4">用户列表</td>
			</tr>

			<tr>
				<td colspan="4" class="userlist_td">
					<!-- 
                在现实开发场景中 搜索和 分页  很多人选择做在一起 当然也可以分开做
                做在一起省时省力 但是 拓展性肯定 不如分开做高
                -->
					<form action="UserListByPageServlet" method="post"
						style="margin-bottom: 0px">
						根据用户查找用户: <input name="username_search" />
						<button style="margin-left: 20px">搜索</button>
					</form></td>
			</tr>

			<tr>
				<td class="userlist_td">用户名</td>
				<td class="userlist_td">创建时间</td>
				<td class="userlist_td">修改时间</td>
				<td class="userlist_td">操作</td>
			</tr>
			<!--
			  
			  -->
			<c:forEach items="${userlist}" var="user">
				<tr>
					<td class="userlist_td">${user.userName}</td>
					<td class="userlist_td">${user.createTime}</td>
					<td class="userlist_td">${user.updateTime}</td>
					<td class="userlist_td"><button onclick="addShow()">添加</button>
						<!-- this 代表触发当前函数的 整个元素  --> <!-- 元素总黄色的属性 意味着 该元素没有这个属性 HTML 会把它 当成自定义属性  -->
						<button onclick="updateUser(this)" userid="${user.id}"
							username="${user.userName}"
							password="${user.passWord}">修改</button>
						<button onclick="userDel(this)" userid="${user.id}">删除</button>
					</td>
				</tr>
			</c:forEach>
			<%-- <%
				for(User user:userSet){
			%>
			<tr>
				<td class="userlist_td"><%=user.getUserName()%></td>
				<td class="userlist_td"><%=user.getCreateTime()%></td>
				<td class="userlist_td"><%=user.getUpdateTime()%></td>
				<td class="userlist_td"><button onclick="addShow()">添加</button>
					<!-- this 代表触发当前函数的 整个元素  --> <!-- 元素总黄色的属性 意味着 该元素没有这个属性 HTML 会把它 当成自定义属性  -->
					<button onclick="updateUser(this)" userid="<%=user.getId()%>"
						username="<%=user.getUserName()%>"
						password="<%=user.getPassWord()%>">修改</button>
					<button onclick="userDel(this)" userid="<%=user.getId()%>">删除</button>
				</td>
			</tr>
			<%
				}
			%> --%>
			<tr>
				<td colspan="4" style="border: solid 1px red;">
					<%
						for(int i = 1;i <= pageNum.intValue(); i++) {
					%> <a class="user_a"
					href="UserListByPageServlet?pageindex=<%=i%>&pagesize=5"><%=i%></a>
					<%
						}
					%>
				</td>
			</tr>
		</table>
	</div>

	<div id="user_merge"
		style="width: 500px;height: 300px;border: solid 5px red;margin: 0 auto;display: none;">
		<form action="UserAddServlet" method="post">
			<ul style="list-style: none;margin: 0 auto;float: left">
				<li><input name="userid" id="userid" style="display: none" />
				</li>
				<li class="add_li" style="margin-top: 20px">用户名:<input
					name="username" id="username" /></li>
				<li class="add_li">密码：<input name="password" type="password"
					id="password" value="" />
				</li>
				<li id="repassword_li" class="add_li">确认密码：<input
					name="repassword" type="password" /></li>
				<li class="add_li">
					<button id="user_add_button" style="margin-right: 20px">添加</button>
					<button onclick="addHide()" type="button">取消</button></li>
			</ul>
		</form>
	</div>
</body>
</html>

