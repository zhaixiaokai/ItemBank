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

<!--jQuery DatePicker   -->
<link rel="stylesheet" href="../js/jquery-ui.css" />
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<!--END  -->

<script>
function getStudentDepartmentId(){

	//$("#SchoolStructureId").value="";
	$("#StudentDepartment").empty();
	$("#StudentDepartment").append(this.innerHTML);
	document.getElementById("StudentDepartmentId").value=this.id;
	//alert(document.getElementById("StudentDepartmentId").value);
	
 
}

//生成学生机构多级菜单
var	StudentDepartmentNodes=null;
function	getStudentDepartList(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "StudentDepartmentOptionsGetAction",
		success : function(result) {
			StudentDepartmentNodes=result.data;
		},
		error:function(){
		}
	});
}
	
//检查数据的合法性并提交表单数据

function CheckAndSubmit(){
	var departmentId=document.getElementById("StudentDepartmentId").value;
	var StudentID =document.getElementById("StudentID").value;
	var StudentName=document.getElementById("StudentName").value;
	var TelPhone=document.getElementById("TelPhone").value;
	var Address=document.getElementById("Address").value;

	if(departmentId==""){
		alert("请选择学生所属的学院机构！");
		return false;
	}
	// 学号为必填项，不能为空，且其长度不能超过20
	if(StudentID==""){
		alert("学号为必填项，请重新输入 ");
		return false;
	}else{
		if(StudentID.length>20){
			alert("学号长度不能超过20");
			return;
		}
		if(!CheckIfIsLetterNumber(StudentID)){
			alert("学必须由字母和数字组成");
			return;
		}
	}
	//学生姓名为必填项，不能为空
	if(StudentName==""){
		alert("学生姓名为必填项，请重新输入 ");
		return;
	}else{
		if(!isZh(StudentName)){
			if(!CheckIfIsLetter(StudentName)){
				alert("学生姓名必须由汉字、字母组成");
				return;
				}
			
		}
	}
	
	if(TelPhone!=""){
		if(!isTel(TelPhone)){
			if(!checkMobile(TelPhone)){
				alert("请填写正确格式的电话号码");
				return;
			}
			
		}
	}
	if(Address!=""){
		
			if(Address.length>200){
				alert("联系地址的文字长度不超过200");
				return;
			}
			
		
	}
	
	if(confirm("确认添加新的学生")){
		var options={
			type:"post",
			dataType:"json",
			success:ShowSuccess	
			}
		$('#AddStudentForm').ajaxSubmit(options);
		return false;
		}
		
	
}
function ShowSuccess(responseText, statusText) {
	if(responseText!=null&&responseText!=""){
		alert(responseText.text);
		document.location.href="addStudent.jsp";
	}else{
	alert("添加学生失败");
	
	}
	

}
</script>
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

	<form id="AddStudentForm" action="StudentAddAction" method="post">
		<table width="100%" class="CContent">
			<tr>
				<th class="pagehead">添加学生</th>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%">
						<tr>
							<td>
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">添加学生</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">

										<tr>
											<td nowrap align="left">所属机构：</td>
											<td><div id="SelectStudentDepartment"></div> <input
												type="hidden" name="StudentDepartmentId"
												id="StudentDepartmentId" value=""></td>
										</tr>
										<tr>
											<td nowrap align="left" height="30px">学号:</td>
											<td colspan="3"><input type="text" name="StudentID"
												id="StudentID" /><span class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="left" height="30px">学生姓名:</td>
											<td colspan="3"><input type="text" name="StudentName"
												id="StudentName" /><span class="red"> *</span></td>
										</tr>

										<tr>
											<td nowrap align="left" height="30px">联系电话：</td>
											<td colspan="3"><input type="text" name="TelPhone"
												id="TelPhone" /></td>
										</tr>
										<tr>
											<td nowrap align="left" height="30px">联系地址：</td>
											<td colspan="3"><textarea id="Address" name="Address"
													rows="2" cols="70"></textarea></td>
										</tr>
										<tr>
											<td></td>
											<td align="center"><br> <input type="button"
												value="保存" class="button" onclick="CheckAndSubmit();" /> <input
												type="reset" value="重置" /></td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	<script type="text/javascript">
	getStudentDepartList();
	loadMenu("SelectStudentDepartment",StudentDepartmentNodes,getStudentDepartmentId,"StudentDepartment");
	
	</script>
</body>
</html>