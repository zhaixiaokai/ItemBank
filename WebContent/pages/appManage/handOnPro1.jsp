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
<script type="text/javascript">
function viewAdd(){
	/* paperView.jsp */
	self.parent.frames["mainFrame"].location="paperView.jsp";
}

</script>

</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a target="mainFrame" href="../functionList/appManage.jsp">应用管理</a>&nbsp;&gt;&gt;&nbsp;组卷管理<a href="selectBase.jsp">&nbsp;&gt;&gt;&nbsp;按进度手动组卷</a></div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
<div id="act_content2">
		<table align="left" style="font-size: 12px" width="700px"  height=50px>
			<tr>
				<td width="20" style="border: 0"><img src="image/refer.gif"
					width="20" height="18" /></td>

				<td width="40" style="border: 0" align="right">难度：</td>
				<td width="60px" align="left">
					<form id="form3" name="form3" method="post" action="">
						<select name="select2">
							<option>--请选择--</option>
							<option>简单</option>
							<option>一般</option>
							<option>中等</option>
							<option>困难</option>
						</select>
					</form>
				</td>
				<td width="40px" align="right">题型：</td>
				<td width="60px" align="left">
					<form id="form3" name="form3" method="post" action="">
						<select name="select3">
						<option>--请选择--</option>
							<option>选择题</option>
							<option>填空题</option>
							<option>判断题</option>
							<option>简答题</option>
						</select>
					</form>
				</td>
				<td width="60px" align="right">知识点：</td>
				<td width="100px" align="left"><jsp:include page="../frame/selectPoint1.jsp"></jsp:include></td>
				<td width="30px" align="left"><input type="button" value="查询" class="button"/></td>
				<td></td>
				<td align="right"><input type="button" value="批量添加" class="button"/><input type="button" value="已添加试题" class="button" onclick="viewAdd();"/></td>
			</tr>
		</table>

	<table border="0" class="tableList">
						<tr class="tr1">
							<td></td>
							<td>序号</td>
							<td>试题编号</td>
							<td>试题信息</td>
							<td>试题答案</td>
							<td>试题录入时间</td>
							<td>出题人</td>
							<td>试题分值</td>
							<td>操作</td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td>1</td>
							<td>080001</td>
							<td><br>
								<p>解释数据模型的概念，为什么要将数据模型分成两个层次？</p>
								<br></td>
							<td>&nbsp;</td>
							<td>2008-12</td>
							<td>xx</td>
							<td>2</td>
							<td><a href="#">添加</a></td>
						</tr>
						<tr>
							<td><input type="checkbox"></td>
							<td>2</td>
							<td>080002</td>
							<td><br>
								<p>概念层数据模型和组织层数据模型分别是针对什么进行抽象的？</p>
								<br></td>
							<td>&nbsp;</td>
							<td>2009-12</td>
							<td>xx</td>
							<td>2</td>
							<td><a href="#">添加</a></td>
				        </tr>
						<tr>
							<td><input type="checkbox"></td>
							<td>3</td>
							<td>080003</td>
							<td><br>
								<p>实体之间的联系有哪几种？</p>
								<br></td>
							<td>&nbsp;</td>
							<td>2007-12</td>
							<td>xx</td>
							<td>2</td>
							<td><a href="#">添加</a></td>
				        </tr>
						<tr>
							<td><input type="checkbox"></td>
							<td>4</td>
							<td>080004</td>
							<td><br>
								<p>数据库管理系统提供的两级映像是什么？</p>
								<br></td>
							<td>&nbsp;</td>
							<td>2008-12</td>
							<td>xx</td>
							<td>3</td>
							<td><a href="#">添加</a></td>
				        </tr>
						<tr>
							<td><input type="checkbox"></td>
							<td>5</td>
							<td>080005</td>
							<td><br>
								<p>数据库系统包含哪三级模式？</p>
								<br></td>
							<td>&nbsp;</td>
							<td>2008-12</td>
							<td>xx</td>
							<td>3</td>
							<td><a href="#">添加</a></td>
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
			<p>&nbsp;</p>
	  </div>
		<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>