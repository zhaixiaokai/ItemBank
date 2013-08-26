<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../esM/css/table.css" />
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
<style>
</style>
<script>
	function view_progress() {
		self.parent.frames["mainFrame"].location="viewProgress.jsp";
	}
	</script>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<div id="act_top">
		<a href="#">教学管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">教学进度管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">教学进度管理</a>
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div style="margin-left: 25px; margin-top: 10px">
		<table>
			<tr>
				<td>专业选择：</td>
				<td><jsp:include page="../document/basicsortSelect.jsp"></jsp:include></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程选择：</td>
				<td><jsp:include page="../document/subjectSelect.jsp"></jsp:include></td>
				<td><input type="button" class="button" value="查询"
					onclick="view_progress();"></td>
			</tr>
		</table>
	</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>