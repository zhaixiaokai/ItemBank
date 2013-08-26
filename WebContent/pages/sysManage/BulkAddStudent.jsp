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
<link rel="stylesheet" type="text/css" href="../css/select.css">
<link href="../css/infoedit.css" rel="stylesheet" type="text/css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<script type="text/javascript" src="../js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../../servJs/CreateMultiMenu.js"></script>
 <script type="text/javascript">
 
		//点击某一级节点以后触发的事件
		 function getStudentDepartmentId() {

			$("#StudentDepartment").empty();
			$("#StudentDepartment").append(this.innerHTML);
			document.getElementById("StudentDepartmentId").value = this.id;

		} 

		//生成学生机构多级菜单
		 var StudentDepartmentNodes = null;
		function getStudentDepartList() {
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "StudentDepartmentOptionsGetAction",
				success : function(result) {
					StudentDepartmentNodes = result.data;
				},
				error : function() {
				}
			});
		} 

		function uploadFile() {
			var Id=document.getElementById("StudentDepartmentId").value;
			//alert(Id);

			var options = {
				type : "post",
				dataType : "json",
				async : false,
				url : "StudentUploadAction?TeacherDepartmentId="+Id+" ",
				success : function(responseText){
					alert(responseText.text);
					document.location.href="BulkAddStudent.jsp";
					
				}
			};

			$('#BulkAddStudentForm').ajaxSubmit(options);
			return false;
		}		 
	</script> 
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/sysManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<table width="100%" class="CContent">
		<tr>
			<th class="pagehead">学生信息批量导入</th>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<fieldset style="height: 100%;">
								<legend style="font-size: 12px">学生信息导入</legend>
								<form id="BulkAddStudentForm" enctype="multipart/form-data" method="post">
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">
										 <tr>
											<td nowrap align="left">所属机构：</td>
											<td width="150px"><div id="SelectStudentDepartment"></div>
												<input type="hidden" name="StudentDepartmentId"
												id="StudentDepartmentId" value=""></td>
										</tr> 
										<tr>
											<td>&nbsp;&gt;&gt;&nbsp;批量导入学生信息：</td>
											<td><input type="file" name="xlsFile"></td>
											<td><input type="button" name="upload" value="upload"
												onclick="uploadFile();"></td>
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
<script>
getStudentDepartList();
loadMenu("SelectStudentDepartment",StudentDepartmentNodes,getStudentDepartmentId,"StudentDepartment"); 
</script>
</body>
</html>





