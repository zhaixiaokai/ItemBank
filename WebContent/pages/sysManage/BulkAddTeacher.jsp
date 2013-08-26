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
		 function getTeacherDepartmentId() {

			$("#TeacherDepartment").empty();
			$("#TeacherDepartment").append(this.innerHTML);
			document.getElementById("TeacherDepartmentId").value = this.id;

		} 

		//生成教师机构多级菜单
		 var TeacherDepartmentNodes = null;
		function getTeacherDepartList() {
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "TeacherDepartmentOptionsGetAction",
				success : function(result) {
					TeacherDepartmentNodes = result.data;
				},
				error : function() {
				}
			});
		} 
		//上传文件
	/*  	{
			var jsonData = jQuery.parseJSON(responseText);
			var result = jQuery.parseJSON(jsonData.message)
			var dataArr = result[1].data;
			$.each(dataArr, function(key, value) {
				alert(key);
				alert(value.toString());
			});
			$("#test").html(result[0].data[0].name);
		}  */
		function uploadFile() {
			var Id=document.getElementById("TeacherDepartmentId").value;
			//alert(Id);

			var options = {
				type : "post",
				dataType : "json",
				async : false,
				url : "TeacherUploadAction?TeacherDepartmentId="+Id+" ",
				success : function(responseText){
					alert(responseText.text);
					document.location.href="BulkAddTeacher.jsp";
				}
			};

			$('#BulkAddTeacherForm').ajaxSubmit(options);
			return false;
		}
	/* 	function ShowSuccess(responseText, statusText) {
			alert(responseText.result);
			if(responseText!=null&&responseText!=""){
				alert(responseText.text);
				document.location.href="addTeacher1.jsp";
			}else{
			alert("添加教师失败");
			}
		} */
	/* 	function showStart() {
			return true;
		} */

		/* 	function jsonTest(){
		 var json = eval("("+"{'姓名':'高大全','性别':'男','年龄':'30'}"+")");
		 for(var key in json ){
		 alert(key);
		 alert(json[key]);
		 }
		 } 周工修改*/
		 
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
			<th class="pagehead">教师信息导入</th>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<fieldset style="height: 100%;">
								<legend style="font-size: 12px">教师信息导入</legend>
								<form id="BulkAddTeacherForm" enctype="multipart/form-data" method="post">
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">
										 <tr>
											<td nowrap align="left">所属机构：</td>
											<td width="150px"><div id="SelectTeacherDepartment"></div>
												<input type="hidden" name="TeacherDepartmentId"
												id="TeacherDepartmentId" value=""></td>
										</tr> 
										<tr>
											<td>&nbsp;&gt;&gt;&nbsp;批量导入教师信息：</td>
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
 getTeacherDepartList();
loadMenu("SelectTeacherDepartment",TeacherDepartmentNodes,getTeacherDepartmentId,"TeacherDepartment"); 
</script>
</body>
</html>





