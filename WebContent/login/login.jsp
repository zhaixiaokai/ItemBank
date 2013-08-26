<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Login</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<form name="loginAction" action="loginAction" method="post">
		<font>系统登陆</font> <br> 用户名：<input type="text" name="username" /><br>
		用户名：<input type="password" name="password" /><br> 用户类型：<select
			name="usertype">
			<option value="0">请选择</option>
			<option value="teacher">教师</option>
			<option value="student">学生</option>
		</select> <input type="submit" value="登录" />
	</form>
</body>
</html>
