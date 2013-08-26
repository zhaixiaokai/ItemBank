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
<link rel="stylesheet" type="text/css" href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link rel="stylesheet" type="text/css" href="../css/select.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>


</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a target="mainFrame" href="../functionList/appManage.jsp">应用管理&nbsp;</a>&gt;&gt;&nbsp;成绩管理<a
			href="selfSelectStu.jsp">&nbsp;&gt;&gt;&nbsp;学生查询</a>	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	
 <div id="act_content2">
<!-- 	<div class="div_style2"> -->
		<table   style="font-size: 12px" width="100%" height=50px>
			<tr>
				<td width="20" style="border: 0"><img src="image/refer.gif"
					width="20" height="18" /></td>
				<!-- <td style="border: 0">查询条件：</td> -->


				<td width="60px" style="border: 0">选择学期：</td>
				<td width="100px">
					<select name="select3">
								<option selected="selected">--请选择--</option>
								<option value="2012-2013秋学期">2012-2013秋学期</option>
								<option value="2011-2012春学期">2011-2012春学期</option>
								<option value="2011-2012秋学期">2011-2012秋学期</option>
								<option value="2010-2011春学期">2010-2011春学期</option>
								<option value="2010-2011秋学期">2010-2011秋学期</option>
								<option value="2009-2010春学期">2009-2010春学期</option>
								<option value="2009-2010秋学期">2009-2010秋学期</option>
								<option value="2008-2009春学期">2008-2009春学期</option>
								<option value="2008-2009秋学期">2008-2009秋学期</option>
					</select>
					
				</td>
				<td width="40"><input type=button class="button"
					name="button" value="查询"></td>
					<td></td>
			</tr>
		</table>

  
		<table width="100%" class="tableList">
			<tr class="tr1">
				<td>课程编号</td>
				<td>序号</td>
				<td>考生姓名</td>
				<td>课程名称</td>
				<td>课程学分</td>
				<td>开课学期</td>
				<td>考试时间</td>
				<td>考试成绩</td>
			</tr>
			<tr>
				<td>080001</td>
				<td>1</td>
				<td>赵</td>
				<td>大学英语</td>
				<td>3</td>
				<td>2008-2009秋学期</td>
				<td>2009年1月6日</td>
				<td>85</td>
			</tr>
			<tr>
				<td>080002</td>
				<td>2</td>
				<td>赵</td>
				<td>C++程序设计</td>
				<td>3.5</td>
				<td>2008-2009秋学期</td>
				<td>2009年1月7日</td>
				<td>88</td>
			</tr>
			<tr>
				<td>080003</td>
				<td>3</td>
				<td>赵</td>
				<td>电子电路基础</td>
				<td>4</td>
				<td>2008-2009秋学期</td>
				<td>2009年1月8日</td>
				<td>92</td>
			</tr>
			<tr>
				<td>080004</td>
				<td>4</td>
				<td>赵</td>
				<td>通信原理</td>
				<td>5</td>
				<td>2008-2009秋学期</td>
				<td>2009年1月10日</td>
				<td>94</td>
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