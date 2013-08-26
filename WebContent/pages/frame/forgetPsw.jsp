<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>忘记密码</title>
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<style>
#act_divLine {
	margin-top: 5px;
	background: url(../source/divLine.gif);
	width: 784px;
	margin-left: 20px
}
</style>
</head>
<body>

	<div id="act_top">
		<a href="../functionList/HomePage.html">首页</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">找回密码</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td></td>
		</tr>
	</table>
	<div id="act_content">
		<table class="tableList">
			<tr>
				<td><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td>输入用户名：</td>
							<td><input name="username" type="text"></td>
						</tr>
						<tr>
							<td>学号/教工号：</td>
							<td><input name="userID" type="text"></td>
						</tr>
						<tr>
							<td>邮箱：</td>
							<td><input name="Email" type="text"></td>
						</tr>
					</table></td>
			</tr>
		</table>
		<div align="center">
			<a href="#">提交</a>
		</div>
	</div>


</body>
</html>