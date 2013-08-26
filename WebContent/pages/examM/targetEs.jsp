<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<style>
</style>
<script>
	function showTable() {
		document.getElementById("div").style.visibility = "visible";
	}
	
</script>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<div id="act_top">
		<a href="#">试题管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">试题管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">试题迁移</a>
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
				<td>目标试题库选择：</td>
				<td><jsp:include page="../document/selectEs.jsp"></jsp:include></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="button" class="button" value="查询" onclick="showTable()"></td>
			</tr>
		</table>
		<div id="div" style="visibility: hidden">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="tableList">
				<tr>
					<td align="center" colspan="9" class="tr1">试题库列表(考试)</td>
				</tr>
				<tr align="center">

					<td><table width="100%">
							<tr>
								<td style="border: 0px"><input type="radio" name="radio">试题库a</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库b</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库l</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库g</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库f</td>
							</tr>
							<tr>
								<td style="border: 0px"><input type="radio" name="radio">试题库k</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库a</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库d</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库g</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库f</td>
							</tr>
							<tr>
								<td style="border: 0px"><input type="radio" name="radio">试题库a</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库b</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库l</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库g</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库f</td>
							</tr>
							<tr>
								<td style="border: 0px"><input type="radio" name="radio">试题库k</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库a</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库d</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库g</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库f</td>
							</tr>

						</table></td>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="tableList">
				<tr>
					<td align="center" colspan="9" class="tr1">试题库列表(练习)</td>
				</tr>
				<tr align="center">

					<td><table width="100%">
							<tr>
								<td style="border: 0px"><input type="radio" name="radio">试题库a</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库b</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库l</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库g</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库f</td>
							</tr>
							<tr>
								<td style="border: 0px"><input type="radio" name="radio">试题库k</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库a</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库d</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库g</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库f</td>
							</tr>
							<tr>
								<td style="border: 0px"><input type="radio" name="radio">试题库a</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库b</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库l</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库g</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库f</td>
							</tr>
							<tr>
								<td style="border: 0px"><input type="radio" name="radio">试题库k</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库a</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库d</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库g</td>
								<td style="border: 0px"><input type="radio" name="radio">试题库f</td>
							</tr>

						</table></td>
			</table>
			<table width="100%">
				<tr>
					<td align="center"><input type="button" value="确定"
						class="button" onclick="addQ();"></td>

				</tr>
			</table>

		</div>


	</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>