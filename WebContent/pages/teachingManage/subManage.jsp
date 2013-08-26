<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>

<link href="../examM/css/style.css" rel="stylesheet" type="text/css" />
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

<script src="../js/checkAll.js" type="text/javascript"></script>

</head>


<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a target="mainFrame" href="../functionList/teachingManage.jsp">教学管理</a>&nbsp;&gt;&gt;&nbsp;课程管理<a href="subManage.jsp">&nbsp;&gt;&gt;&nbsp;课程管理</a></div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
<div id="act_content2">
		<table style="font-size: 12px" width="100%" height=50px>
			<tr>
				<td width="20" style="border: 0"><img src="../examM/image/refer.gif"
					width="20" height="18" /></td>
					<!--<td width="40" style="border: 0">查询:</td>-->
					<td width="80" style="border: 0">学院专业选择:</td>
					<td><jsp:include page="../document/basicsortSelect.jsp"></jsp:include></td>
					
				  <td width="40" align="right"><input type="button" class="button" value="查询"></td>
				  <td width="40" align="right"><input type="button" name="Submit" value="批量刪除" class="button" /></td>
			</tr>
		</table>
		
			<table width="100%" class="tableList">
				<tr class="tr1">
				  <td>
    	<input type="checkbox" name="checkAll" id="checkbox" onClick="check_all(this,'checkbox')"/> 全选
    </td>
    <td>课程编号</td>
    <td>课程名称</td>
    <td>课程学分</td>
	<td>考核方式</td>
	<td>备注说明</td>
    <td>管理</td>
  </tr>
  <tr align="center">
    <td height="26"><form id="form1" name="form1" method="post" action="">
      <input type="checkbox" name="checkbox" id="checkbox" />
      <label for="checkbox"></label>
    </form></td>
    <td>080001</td>
    <td>信号与系统</td>
    <td>4</td>
	<td>考试</td>
	<td>暂无</td>
    <td><a href="courseEdit.jsp">修改</a>&nbsp;/&nbsp;<a href="javascript:void(0)">删除</a></td>
  </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox" id="checkbox2" /></td>
    <td>080002</td>
    <td>通信原理</td>
    <td>5</td>
	<td>考试</td>
	<td>暂无</td>
    <td><a href="courseEdit.jsp">修改</a>&nbsp;/&nbsp;<a href="javascript:void(0)">删除</a></td>
  </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox" id="checkbox3" /></td>
    <td>080003</td>
    <td>数据库应用技术</td>
    <td>3</td>
	<td>考试</td>
	<td>暂无</td>
    <td><a href="courseEdit.jsp">修改</a>&nbsp;/&nbsp;<a href="javascript:void(0)">删除</a></td>
  </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox" id="checkbox4" /></td>
    <td>080004</td>
    <td>通信电子电路</td>
    <td>3</td>
	<td>考试</td>
	<td>暂无</td>
    <td><a href="courseEdit.jsp">修改</a>&nbsp;/&nbsp;<a href="javascript:void(0)">删除</a></td>
  </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox" id="checkbox5" /></td>
    <td>080005</td>
    <td>电子电路基础</td>
    <td>4</td>
	<td>考试</td>
	<td>暂无</td>
    <td><a href="courseEdit.jsp">修改</a>&nbsp;/&nbsp;<a href="javascript:void(0)">删除</a></td>
				</tr>
			</table>
			<table>
				<tr>
					<td style="border: 0"><a href="courseList1.html">&nbsp;1&nbsp;</a>
						<a href="courseList2.html">&nbsp;2&nbsp;</a> <a href="#">&nbsp;3&nbsp;</a>
						<a href="#">&nbsp;4&nbsp;</a> <a href="#">&nbsp;5&nbsp;</a> <a
						href="courseList2.html">&nbsp;下一页 ></a>
						&nbsp;&nbsp;共50页&nbsp;&nbsp; &nbsp;&nbsp; <a
						href="courseList1.html" class="page">首页</a> &nbsp;&nbsp; <a
						href="#" class="page">末页</a> &nbsp;&nbsp; 到第 <input type="text"
						size="1">页 &nbsp;&nbsp; <input type="button" value="确认"
						onclick="certainPage" style="font-size: 12px;"></td>
				</tr>
			</table>
		</div>
		<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>