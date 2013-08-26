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
<link rel="stylesheet" type="text/css" href="../css/select.css">
<link href="../css/infoedit.css" rel="stylesheet" type="text/css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../js/checkAll.js" type="text/javascript"></script>
<script>
function loadList(obj){
	if(obj.value=="teacher"){
		//document.getElementById("td2").style.top="10px";
		document.getElementById("td1").style.display="block";
		document.getElementById("td2").style.display="block";
		document.getElementById("td3").style.display="none";
		document.getElementById("td4").style.display="none";
	}else if(obj.value=="student"){
		//document.getElementById("td3").style.top="10px";
		document.getElementById("td1").style.display="none";
		document.getElementById("td2").style.display="none";
		document.getElementById("td3").style.display="block";
		document.getElementById("td4").style.display="block";
	}
}
</Script>

<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}

#act_divLine {
	margin-top: 5px;
	background: url(../source/divLine.gif);
	width: 784px;
	margin-left: 20px
}
</style>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/sysManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="../../functionList/HomePage.html">首页</a>&nbsp;&gt;&gt;&nbsp;<a
			href="../../functionList/sysManage.html">系统管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">角色管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">角色成员管理</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div id="act_content2">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			height="50px">
			<tr>
				<td>角色名称： <select name="select" id="select">
						<option value="试题管理员">试题管理员</option>
						<option value="试题库管理员">试题库管理员</option>
						<option value="试题编制人员">试题编制人员</option>
						<option value="系统管理员">系统管理员</option>

				</select>

				</td>
				<td>成员类型： <select name="mySelect" id="mySelect"
					onchange="loadList(this)">
						<option value="teacher">教师</option>
						<option value="student">学生</option>
				</select>
				</td>
				<td id="td1">学院教研中心选择:</td>
				<td id="td2" style="position: relative;"><jsp:include
						page="../document/ResearchCenter.jsp"></jsp:include></td>
				<td id="td3" style="position: relative; display: none">学院专业选择:</td>
				<td id="td4" style="position: relative; display: none"><jsp:include
						page="../document/StudentMajor.jsp"></jsp:include></td>
				<td><input type="button" name="Submit" value="查询"
					class="button" onclick="save();" /></td>
				<td style="text-align: right"><input type="button"
					name="Submit" value="批量删除" class="button" /></td>
			</tr>
		</table>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableList">

			<tr align="center" class="tr1">
				<td><input type="checkbox" name="checkAll" id="checkbox"
					onclick="check_all(this,'checkbox')" /> 全选</td>
				<td>用户编号</td>
				<td>用户名称</td>
				<td>用户信息</td>
				<td>管理</td>
			</tr>
			<tr align="center">
				<td><input type="checkbox" name="checkbox" id="checkbox" /> <label
					for="checkbox"></label></td>
				<td>1</td>
				<td>张</td>
				<td>教师</td>
				<td><a href="teacherEdit.jsp">修改</a>&nbsp;/&nbsp;<a
					href="javascript:void(0)">删除</a></td>
			</tr>
			<tr align="center">
				<td><input type="checkbox" name="checkbox" id="checkbox2" /></td>
				<td>2</td>
				<td>李</td>
				<td>教师</td>
				<td><a href="teacherEdit.jsp">修改</a>&nbsp;/&nbsp;<a
					href="javascript:void(0)">删除</a></td>
			</tr>
			<tr align="center">
				<td><input type="checkbox" name="checkbox" id="checkbox3" /></td>
				<td>3</td>
				<td>王</td>
				<td>学生</td>
				<td><a href="teacherEdit.jsp">修改</a>&nbsp;/&nbsp;<a
					href="javascript:void(0)">删除</a></td>
			</tr>
			<tr align="center">
				<td><input type="checkbox" name="checkbox" id="checkbox4" /></td>
				<td>4</td>
				<td>赵</td>
				<td>学生</td>
				<td><a href="teacherEdit.jsp">修改</a>&nbsp;/&nbsp;<a
					href="javascript:void(0)">删除</a></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<!-- <div style="text-align:center">
   <input type="button" name="Submit" value="删除" class="button" />
   </div> -->
	</div>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>