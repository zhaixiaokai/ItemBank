<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>

<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link rel="stylesheet" type="text/css" href="../css/select.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>

</head>


<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a target="mainFrame" href="../functionList/appManage.jsp">应用管理</a>&nbsp;&gt;&gt;&nbsp;成绩查询<a
			href="selectStu.jsp">&nbsp;&gt;&gt;&nbsp;成绩导入</a>	
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	
	<div id="act_content2">

		<table width="100%">
			<tr>
				<td  align="right"><input type="button" class="button" value="批量导入"></td>
			</tr>
		</table>
		<table width="100%" class="tableList">
			<tr class="tr1">
				<td><input type="checkbox">全选</td>
				<td>学生学号</td>
				<td>学生姓名</td>
				<td>课程名称</td>
				<td>学院</td>
				<td>专业</td>
				<td>班级</td>
				<td>开课学期</td>
				<td>考试时间</td>
				<td>考试成绩</td>
				<td>操作</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>08210001</td>
				<td>赵</td>
				<td>大学英语</td>
				<td>人文学院</td>
				<td>外语</td>
				<td>1班</td>
				<td>2008-2009秋学期</td>
				<td>2009年1月6日</td>
				<td>85</td>
				<td><a href="javascript:void(0)">导入</a></td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>08210005</td>
				<td>李</td>
				<td>大学英语</td>
				<td>信息学院</td>
				<td>通信工程</td>
				<td>1班</td>
				<td>2008-2009秋学期</td>
				<td>2009年1月6日</td>
				<td>85</td>
				<td><a href="javascript:void(0)">导入</a></td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>08210020</td>
				<td>王</td>
				<td>大学英语</td>
				<td>信息学院</td>
				<td>通信工程</td>
				<td>1班</td>
				<td>2008-2009秋学期</td>
				<td>2009年1月6日</td>
				<td>85</td>
				<td><a href="javascript:void(0)">导入</a></td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>08210030</td>
				<td>钱</td>
				<td>大学英语</td>
				<td>信息学院</td>
				<td>通信工程</td>
				<td>1班</td>
				<td>2008-2009秋学期</td>
				<td>2009年1月6日</td>
				<td>85</td>
				<td><a href="javascript:void(0)">导入</a></td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>08210040</td>
				<td>孙</td>
				<td>大学英语</td>
				<td>信息学院</td>
				<td>通信工程</td>
				<td>1班</td>
				<td>2008-2009秋学期</td>
				<td>2009年1月6日</td>
				<td>85</td>
				<td><a href="javascript:void(0)">导入</a></td>
			</tr>
		</table>
		
		
		<table>
			<tr>
				<td style="border: 0"><a href="selectSub.html">&nbsp;1&nbsp;</a>
					<a href="courseList2.html">&nbsp;2&nbsp;</a> <a href="#">&nbsp;3&nbsp;</a>
					<a href="#">&nbsp;4&nbsp;</a> <a href="#">&nbsp;5&nbsp;</a> <a
					href="courseList2.html">&nbsp;下一页 ></a>
					&nbsp;&nbsp;共50页&nbsp;&nbsp; &nbsp;&nbsp; <a href="selectSub.html"
					class="page">首页</a> &nbsp;&nbsp; <a href="#" class="page">末页</a>
					&nbsp;&nbsp; 到第 <input type="text" size="1">页 &nbsp;&nbsp;
					<input type="button" value="确认" onClick="certainPage"
					style="font-size: 12px;"></td>
			</tr>
		</table>
	</div>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>

</body>
</html>