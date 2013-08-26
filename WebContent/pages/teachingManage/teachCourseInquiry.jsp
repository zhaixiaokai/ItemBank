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
		<a target="mainFrame" href="../functionList/teachingManage.jsp">教学管理</a>&nbsp;&gt;&gt;&nbsp;授课管理<a href="classmemberInquiry.jsp">&nbsp;&gt;&gt;&nbsp;查看教学课程</a></div>
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
					<td>查询:</td>
					<td>选择学院:</td>
					<td><select name="select">
                      <option selected>--教师所属学院--</option>
                      <option>信息与通信工程学院</option>
                      <option>计算机学院</option>
                      <option>电子工程学院</option>
                      <option>理学院</option>
                      <option>自动化学院</option>
                    </select></td>
					<td>教研中心:</td>
					<td><select name="select">
					  <option>--选择教研中心--</option>
					  <option>泛网无线教研中心</option>
					  <option>网络搜索教研中心</option>
                    </select></td>
					<td>教工号:</td>
					<td><form name="form1" method="post" action="">
					  <label>
					    <input name="textfield" type="text" size="10">
				      </label>
					  </form>
					</td>
					
				  <td  align="center"><input type="button" class="button" value="查询"></td>
			</tr>
		</table>
		
			<table width="100%" class="tableList">
				<tr class="tr1">
				  <td>教师编号</td>
                  <td>教师姓名</td>
                  <td>学院</td>
	<td>教研中心</td>
	<td>教授课程课程</td>
    <td>班级人数</td>
    </tr>
  <tr align="center">
    <td height="26">7654321</td>
    <td>赵</td>
    <td>信息与通信工程学院</td>
	<td>泛网无线教研中心</td>
	<td>数据库应用技术</td>
    <td>100</td>
    </tr>
  <tr align="center">
    <td>7654321</td>
    <td>赵</td>
    <td>信息与通信工程学院</td>
	<td>泛网无线教研中心</td>
	<td>电子电路基础</td>
    <td>150</td>
    </tr>
  <tr align="center">
    <td>7654321</td>
    <td>赵</td>
    <td>信息与通信工程学院</td>
	<td>泛网无线教研中心</td>
	<td>通信电子电路</td>
    <td>200</td>
    </tr>
  <tr align="center">
    <td>7654321</td>
    <td>赵</td>
    <td>信息与通信工程学院</td>
	<td>泛网无线教研中心</td>
	<td>数字电路</td>
    <td>250</td>
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