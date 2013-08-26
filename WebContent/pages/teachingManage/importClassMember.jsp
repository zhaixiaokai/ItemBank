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
<script>

function viewAdd(){
	self.parent.frames["mainFrame"].location="importStudentList.jsp";
}
</script>

</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<table width="100%" class="CContent" >
	<tr>
		<th class="pagehead"  >学生信息导入</th>
	</tr>
	<tr><td width="100%">
		<table width="100%">
					
  				
			<tr><td>
			<form id="myForm">
					<fieldset style="height:100%;">
				<legend style="font-size:12px">学生信息导入</legend>
<!-- 				<table border="0" cellpadding="2" cellspacing="1" style="width:80%;font-size:12px">
				 <tr>
					<td height="50px">所属机构：
						<select name="select" id="select">
  						<option value ="试题管理员">信息学院</option>
  						<option value ="试题库管理员">计算机学院</option>
  						<option value="试题编制人员">人文学院</option>
  						<option value="系统管理员">管理学院</option>

  						</select>
  					
						所属中心：
						<select name="select" id="select">
  						 <option value ="1">泛网无线教研中心</option>
  						 <option value ="2">网络搜索教研中心</option>
  						</select>
  						</td>
  					</tr>
  					</table> -->
				 <table border="0" cellpadding="2" cellspacing="1" style="width:80%;font-size:12px">
				
						<tr  height="50px">	
			 					<td>&nbsp;&gt;&gt;&nbsp;批量导入学生信息： </td>
								<td><input type="file" name="导入" id="导入" />
								<input type="button" name="Submit" value="导入" class="button" onclick="viewAdd()"/></td>
				
						</tr>
			
					
				
					  </table>
				</fieldset>
				</form>
			</td></tr>
	
		</table>
	</td></tr>
				

</table>
<jsp:include page="../frame/Frame3.jsp"></jsp:include>

</body>
</html>
	
	
	
	
	
	