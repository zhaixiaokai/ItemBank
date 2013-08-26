<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link href="../css/infoedit.css" rel="stylesheet" type="text/css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link href="/css/multiMenu.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../js/checkAll.js" type="text/javascript"></script>

</head>


<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/sysManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a target="mainFrame" href="../functionList/appManage.jsp">系统管理</a>&nbsp;&gt;&gt;&nbsp;用户管理<a
			href="selectStu.jsp">&nbsp;&gt;&gt;&nbsp;添加学生</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>

	<div id="act_content2">
		<table align="right">
			<tr>
				<td><input type="button" name="Submit" value="导入"
					class="button" /></td>
				<td><input type="button" name="Submit" value="批量刪除"
					class="button" /></td>
			</tr>
		</table>

		<table width="100%" class="tableList">
			<tr align="center" class="tr1">
				<td><input type="checkbox" name="checkAll" id="checkbox"
					onclick="check_all(this,'checkbox')" /> 全选</td>
				<td>编号</td>
				<td>学号</td>
				<td>姓名</td>
				<td>性别</td>
				<td>联系电话</td>
				<td>Email</td>
			</tr>
			<tr align="center">
				<td><input type="checkbox" name="checkbox" id="checkbox1" /></td>
				<td>1</td>
				<td>20111111</td>
				<td>张</td>
				<td>女</td>
				<td>13345678911</td>
				<td>11@163.com</td>
			</tr>
			<tr align="center">
				<td><input type="checkbox" name="checkbox" id="checkbox2" /></td>
				<td>2</td>
				<td>20111111</td>
				<td>李</td>
				<td>女</td>
				<td>13345678911</td>
				<td>11@163.com</td>

			</tr>
			<tr align="center">
				<td><input type="checkbox" name="checkbox" id="checkbox3" /></td>
				<td>3</td>
				<td>20111111</td>
				<td>张</td>
				<td>女</td>
				<td>13345678911</td>
				<td>11@163.com</td>

			</tr>
			<tr align="center">
				<td><input type="checkbox" name="checkbox" id="checkbox4" /></td>
				<td>4</td>
				<td>20111111</td>
				<td>张</td>
				<td>女</td>
				<td>13345678911</td>
				<td>11@163.com</td>

			</tr>
		</table>

	</div>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>

</body>
</html>