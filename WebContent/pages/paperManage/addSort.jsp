<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
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
<style type="text/css">
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperDbManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div style="margin-top: 7px;">
		<table width="100%" class="CContent">
			<tr>
				<th class="pagehead">添加分类体系</th>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%">
						<tr>
							<td>
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">添加分类体系</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">
										<tr>
											<td nowrap align="right" width="100">分类体系名称:</td>
											<td><input name="text" class="text" style="width: 150px"
												type="text" size="40" /> <span class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right" width="100">标识符:</td>
											<td><input name="text" class="text" style="width: 150px"
												type="text" size="40" /> <span class="red"> *</span></td>
										</tr>
										<tr>
											<td></td>
										</tr>
										<tr>
											<td align="right" nowrap>默认分类体系:</td>
											<td><input type="radio" id="yes" name="radio">&nbsp;&nbsp;是 <input
												type="radio" id="no" checked name="radio">&nbsp;&nbsp;否</td>
										</tr>
										<tr>
											<td nowrap align="right" height="120px">分类体系说明:</td>
											<td colspan="3"><textarea id="textarea" name="textarea"
													rows="5" cols="70"></textarea></td>
										</tr>
										
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td align="center"><br> <input type="button" name="Submit"
					value="保存" class="button" onclick="save();" /> <input
					type="reset" name="Submit2" value="重置" class="button"
					/></td>
			</tr>
		</table>
		<script type="text/javascript">
			orelat('howl');
		</script>
	</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>