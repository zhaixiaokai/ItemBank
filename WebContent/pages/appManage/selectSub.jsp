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
		<a target="mainFrame" href="../functionList/appManage.jsp">应用管理</a>&nbsp;&gt;&gt;&nbsp;成绩管理<a href="selectSub.jsp">&nbsp;&gt;&gt;&nbsp;按教学班查询</a>	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
<div id="act_content2">
		<table  style="font-size: 12px" width="100%" height=50px>
			<tr>
				<td width="20" style="border: 0"><img src="image/refer.gif"
					width="20" height="18" /></td>
					<td width="70px">学院专业:</td>
					<td><jsp:include page="../document/basicsortSelect.jsp"></jsp:include></td>
					
					<td width="40px">课程:</td>
					<td><jsp:include page="../document/subjectSelect.jsp"></jsp:include></td>
				
					<td  width="70px">教学班：</td>
		 			<td><select name="select3">
								<option selected="selected">--请选择--</option>
								<option value="1班">1班</option>
								<option value="2班">2班</option>
								<option value="3班">3班</option>
								<option value="4班">4班</option>
						</select>
					</td>
					<td width="70px">学生学号:</td>
					<td ><input name="textfield" type="text" id="textfield" size="8"/></td>
				<td  align="center"><input type="button" class="button" value="查询"></td>
				<td></td>
			</tr>
		</table>
		
			<table width="100%" class="tableList">
				<tr class="tr1">
					<td>考生学号</td>
					<td>班内序号</td>
					<td>考生姓名</td>
					<td>课程学分</td>
					<td>考试时间</td>
					<td>考试成绩</td>
				</tr>
				<tr>
					<td>08210001</td>
					<td>1</td>
					<td>赵</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210002</td>
					<td>2</td>
					<td>钱</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210003</td>
					<td>3</td>
					<td>孙</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210004</td>
					<td>4</td>
					<td>李</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210005</td>
					<td>5</td>
					<td>周</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210006</td>
					<td>6</td>
					<td>吴</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210007</td>
					<td>7</td>
					<td>郑</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210008</td>
					<td>8</td>
					<td>王</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210009</td>
					<td>9</td>
					<td>冯</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210010</td>
					<td>10</td>
					<td>陈</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210001</td>
					<td>1</td>
					<td>0602003</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210001</td>
					<td>1</td>
					<td>0602003</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210001</td>
					<td>1</td>
					<td>0602003</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210001</td>
					<td>1</td>
					<td>0602003</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210001</td>
					<td>1</td>
					<td>0602003</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
				</tr>
				<tr>
					<td>08210001</td>
					<td>1</td>
					<td>0602003</td>
					<td>3</td>
					<td>2012年1月6日</td>
					<td>85</td>
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