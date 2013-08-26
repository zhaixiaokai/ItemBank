<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="s" uri="/struts-tags"%>

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
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link href="/css/multiMenu.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>

</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/sysManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<table width="100%" class="CContent">
		<tr>
			<th class="pagehead">教师信息导入</th>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">


					<tr>
						<td>

							<fieldset style="height: 100%;">
								<legend style="font-size: 12px">教师信息导入</legend>
								<table border="0" cellpadding="2" cellspacing="1"
									style="width: 80%; font-size: 12px">
									<tr>
										<td height="50px">所属机构： <select name="select" id="select">
												<option value="试题管理员">信息学院</option>
												<option value="试题库管理员">计算机学院</option>
												<option value="试题编制人员">人文学院</option>
												<option value="系统管理员">管理学院</option>

										</select> 所属中心： <select name="select" id="select">
												<option value="1">泛网无线教研中心</option>
												<option value="2">网络搜索教研中心</option>
										</select>
										</td>
									</tr>
								</table>
								<form id="myForm" action="FiletoSever.jsp" >

									<table border="0" cellpadding="0" cellspacing="0"
										style="width: 100%; font-size: 12px">

										<tr>
											<td>&nbsp;&gt;&gt;&nbsp;批量导入教师信息：</td>
										<td><input type="file" name="myFile" ></td> 
											
											<td><input type="submit" name="upload" value="upload" ></td>
									

										</tr>
									</table>
								</form>
							</fieldset>

						</td>
					</tr>

				</table>
			</td>
		</tr>


	</table>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>

</body>
</html>





