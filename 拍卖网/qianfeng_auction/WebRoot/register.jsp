<%@page import="com.qf.auction.enums.UserStateEnum"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户注册</title>

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
<script type="text/javascript" src="js/register.js"></script>

<script type="text/javascript">

<%String msg = request.getParameter("msg") == null ? "":request.getParameter("msg");
if (msg.equals(UserStateEnum.USER_ADD_FAIL.getValue())) {%>
	
alert('<%=UserStateEnum.USER_ADD_FAIL.getDesc()%>');
	
<%}%>
	
<%if (msg.equals(UserStateEnum.USER_EMAIL_FAIL.getValue())) {%>
	
alert('<%=UserStateEnum.USER_EMAIL_FAIL.getDesc()%>');
	
<%}%>
	
<%if (msg.equals(UserStateEnum.USER_PHONE_FAIL.getValue())) {%>
	
 alert('<%=UserStateEnum.USER_PHONE_FAIL.getDesc()%>');
	
<%}%>
	
<%if (msg.equals(UserStateEnum.USER_USER_NAME_FAIL.getValue())) {%>
	
 window.alert('<%=UserStateEnum.USER_USER_NAME_FAIL.getDesc()%>');
	
<%}%>
	
<%if (msg.equals(UserStateEnum.USER_ADD_PASSWORD_REPASSWORD_ERROR.getValue())) {%>
	
 window.alert('<%=UserStateEnum.USER_ADD_PASSWORD_REPASSWORD_ERROR.getDesc()%>');
<%}%>

<%if (msg.equals(UserStateEnum.USER_VALIDATE_CODE_FAIL.getValue())) {%>
	
 window.alert('<%=UserStateEnum.USER_VALIDATE_CODE_FAIL.getDesc()%>');
	
<%}%>

<%if (msg.equals(UserStateEnum.USER_PASSWORD_FAIL.getValue())) {%>
	
 window.alert('<%=UserStateEnum.USER_PASSWORD_FAIL.getDesc()%>');
	
<%}%>
</script>

<script type="text/javascript">
  function checkUserName(args){
     var userData = {"username":args};
     $.ajax({
      url:"UserCheckUserNameServlet",
      type:"post",
      data:userData,
      dataType:"json",
      /* data 本質 就是  服務器 寫在  響應報文 中的數據  */
      success:function(data){
            $("#username2").show();
         if(data){
            $("#username2").html("該用戶名已經被注冊");  
         }else{
            $("#username2").html("用戶名可以使用");         
         }
      },
      error:function(){
          alert("網絡異常請稍後再試");
      }
     });
  
  }
  
  function sendNote(){
      var phone = $("#phoneNumber").val();
       var userData = {"phoneNumber":phone};
        $.ajax({
      url:"SendValidateCodeNoteServlet",
      type:"post",
      data:userData,
      dataType:"json",
      /* data 本質 就是  服務器 寫在  響應報文 中的數據  */
      success:function(data){
         if(!data){
            alert("网络异常请稍后再试") 
         }else{
         $("#validatecodemsg").html("用户可以使用");
         $("#validatecodemsg").html("短信已发送");
         }
         },
         error:function(){
         alert("网络异常请稍后再试");
         }
     });
  }

</script>

</head>

<body>
	<div class="wrap">
		<!-- main begin-->
		<form action="UserRegisterServlet" method="post">
			<div class="zclf login logns">
				<h1 class="blue">用户注册</h1>
				<dl>
					<dd>
						<label> <small>*</small>用户名</label> <input name="username"
							type="text" class="inputh lf" onblur="checkUserName(this.value)"
							value="<%=request.getParameter("username") == null ? "" : request.getParameter("username")%>" />
						<label id="username2" style="color: red;margin-left: 20px;display: none;" ></label>
						<div class="lf red laba">用户名要求不低于6个字符</div>
					</dd>
					<dd>
						<label> <small>*</small>密码</label> <input name="password" type="text" class="inputh lf" value="<%=request.getParameter("password") == null ? "" : request.getParameter("password")%>" />
						<div class="lf red laba">密码要求不低于6个字符</div>
					</dd>
					<dd>
						<label> <small>*</small>確認密碼</label> <input name="repassword"
							type="text" class="inputh lf"
							value="<%=request.getParameter("repassword") == null ? "" : request.getParameter("repassword")%>" />
						<div class="lf red laba">要求兩次輸入的密碼必須一直</div>
					</dd>
					<dd>
						<label> <small>*</small>电话</label> <input name="phone" type="text"
							class="inputh lf"
							value="<%=request.getParameter("phone") == null ? "" : request.getParameter("phone")%>" />
						<div class="lf red laba">电话号码必填</div>
					</dd>
					<dd>
						<label> <small>*</small>郵箱</label> <input name="email" type="text"
							class="inputh lf"
							value="<%=request.getParameter("email") == null ? "" : request.getParameter("email")%>" />
						<div class="lf red laba">郵箱必填</div>
					</dd>
					<dd class="hegas">
						<label> <small>*</small>手機號</label> <input id="phoneNumber" name="phonenumber"
							type="text" class="inputh inputs lf" value="" />
						<button onclick="sendNote()" type="button">發送短信</button>
						<label id="validatecodemsg"style="color:red;display:none; margin-left:20px"></label>
					</dd>

					<dd class="hegas">
						<label> <small>*</small>驗證碼</label> <input name="validatecode"
							type="text" class="inputh inputs lf" value="" />
					</dd>

					<dd class="hegas">
						<label>&nbsp;</label> <input name="" type="checkbox" id="rem_u"
							checked="checked" /> <label for="rem_u" class="labels">我同意<span
							class="blues">《服务条款》</span> </label>
					</dd>
					<dd class="hegas">
						<label>&nbsp;</label> <input name="" type="submit" value="立即注册"
							class="spbg buttombg buttombgs f14 lf" />
					</dd>
				</dl>
			</div>
		</form>

		<img alt=""
			src="C:\Users\Administrator\Desktop\tomcat7\webapps\auction\upload\20160922112934831.jpg">
		<!-- main end-->
		<!-- footer begin-->

	</div>
	<!--footer end-->
</body>
</html>

