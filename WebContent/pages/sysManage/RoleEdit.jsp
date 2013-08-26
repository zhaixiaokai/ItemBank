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
<script src="../js/formReset.js" type="text/javascript"></script>
<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}
</style>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/sysManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<table width="100%" class="CContent">
		<tr>
			<th class="pagehead">修改角色信息</th>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<form id="myForm">
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">角色信息修改</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">

										<tr>
											<td></td>
										</tr>

										<tr>
											<td nowrap align="left" height="120px">角色名称:</td>
											<td colspan="3"><input type="text" name="textfield"
												id="textfield" /></td>
										</tr>
										<tr>
											<td nowrap align="left" height="100px">角色说明:</td>
											<td colspan="3"><textarea id="textarea" name="textarea"
													rows="4" cols="70"></textarea></td>
										</tr>

									</table>
								</fieldset>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<tr>
			<td align="center"><br> <input type="button" name="Submit"
				value="保存" class="button" onclick="save();" /> <input type="button"
				name="Submit" value="重置" class="button"
				onclick="formReset( document.getElementById( 'myForm' ) );" /></td>
		</tr>
	</table>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>