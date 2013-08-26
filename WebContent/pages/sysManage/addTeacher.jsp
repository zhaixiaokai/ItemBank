<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
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
<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}
</style>
<script>

//点击某一级节点以后触发的事件
function getTeacherDepartmentId(){

	$("#TeacherDepartment").empty();
	$("#TeacherDepartment").append(this.innerHTML);
	document.getElementById("TeacherDepartmentId").value=this.id;
}

//生成教师机构多级菜单
var	TeacherDepartmentNodes=null;
function	getTeacherDepartList(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "TeacherDepartmentOptionsGetAction",
		success : function(result) {
			TeacherDepartmentNodes=result.data;
		},
		error:function(){
		}
	});
}
	
//检查数据的合法性并提交表单数据

function CheckAndSubmit(){
	var departmentId=document.getElementById("TeacherDepartmentId").value;
	var schoolID =document.getElementById("TeacherID").value;
	var name=document.getElementById("TeacherName").value;
	var birthday=document.getElementById("Birthday").value;
	var gender=document.getElementById("Gender").value;
	var id=document.getElementById("IdetiCard").value;
	var email=document.getElementById("Email").value;
	var phone=document.getElementById("Phone").value;

	//教工号为必填项，不能为空，且其长度不能超过20
	if(departmentId==""){
		alert("请选择教师所属的学院机构！");
		return false;
	}
	if(schoolID==""){
		alert("教工号为必填项，请重新输入 ");
		return false;
	}else{
		if(schoolID.length>20){
			alert("教工号长度不能超过20");
			return;
		}
		if(!CheckIfIsLetterNumber(schoolID)){
			alert("教工号必须由字母和数字组成");
			return;
		}
	}
	//教师姓名为必填项，不能为空
	if(name==""){
		alert("教师姓名为必填项，请重新输入 ");
		return;
	}else{
		if(name.length>10){
			alert("教师姓名的长度不能超过10");
			return;
		}
		if(!isZh(name)){
			if(!CheckIfIsLetter(name)){
				alert("教师姓名必须由汉字、字母组成");
				return;
				}
			
		}
	}
	//身份证等信息为可选项，在非空的情况下判断其格式问题
	if(id!=""){
		if(!isIDno(id)){
			alert("非法身份证号码");
			return;
		}
		
	}
	
	if(phone!=""){
		if(!isTel(phone)){
			if(!checkMobile(phone)){
				alert("请填写正确格式的电话号码");
				return;
			}
			
		}
	}
	
	if(email!=""){
		if(!isEmail(email)){
			alert("请填写正确格式的电子邮箱");
			return;
		}
	}		
	if(confirm("确认添加新的教师")){
		var options={
			type:"post",
			dataType:"json",
			success:ShowSuccess	
			}
		$('#AddTeacherForm').ajaxSubmit(options);
		return false;
		}
		
	
}
function ShowSuccess(responseText, statusText) {
	if(responseText!=null&&responseText!=""){
		alert(responseText.text);
		document.location.href="addTeacher.jsp";
	}else{
	alert("添加教师失败");
	}
}
</script>
<script>
	// 自动选择日期插件
	$(function() {
		$("#Birthday").datepicker({
			dateFormat: "yy-mm-dd",
			autoSize: true,
			changeMonth : true,
			changeYear : true,
			dayNamesMin: [ "日", "一", "二", "三", "四", "五", "六" ],
			firstDay: 1,
			monthNamesShort: [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" ],
			showMonthAfterYear: true,		//在“年”后显示“月”
			showOtherMonths: true,
			yearRange:"1900:2100",
			prevText:"上月",
			nextText:"下月"
		});
	});
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
			<th class="pagehead">添加教师</th>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<form id="AddTeacherForm" name="AddTeacherForm"
								action="TeacherAddAction" method="post">
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">添加教师</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">

										<tr>
											<td nowrap align="left">所属机构：</td>
											<td width="150px"><div id="SelectTeacherDepartment"></div>
												<input type="hidden" name="TeacherDepartmentId"
												id="TeacherDepartmentId" value=""></td>
										</tr>


										<tr>
											<td nowrap align="left" height="30px">教工号:</td>
											<td colspan="3"><input type="text" name="TeacherID"
												id="TeacherID" /><span class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="left" height="30px">教师姓名:</td>
											<td colspan="3"><input type="text" name="TeacherName"
												id="TeacherName" /><span class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="left" height="30px">出生日期：</td>
											<td><input type="text" name="Birthday" id="Birthday" /></td>
										</tr>
										<tr>
											<td nowrap align="left" height="30px">性别：</td>
											<td><input type="radio" name="Gender" id="Gender"
												value="0" />女 <input type="radio" name="Gender" id="Gender"
												value="1" />男</td>
										</tr>

										<tr>
											<td nowrap align="left" height="30px">身份证号码:</td>
											<td colspan="3"><input type="text" name="IdetiCard"
												id="IdetiCard" /></td>
										</tr>
										<tr>
											<td nowrap align="left" height="30px">电子邮箱：</td>
											<td colspan="3"><input type="text" name="Email"
												id="Email" /></td>
										</tr>
										<tr>
											<td nowrap align="left" height="30px">联系电话：</td>
											<td colspan="3"><input type="text" name="Phone"
												id="Phone" /></td>
										</tr>
										<tr>
											<td nowrap align="left" height="30px">办公地址：</td>
											<td colspan="3"><textarea id="Address" name="Address"
													rows="2" cols="70"></textarea></td>
										</tr>
										<tr>
											<td></td>
											<td align="center"><br> <input type="button"
												value="保存" class="button"
												onclick="javascript:CheckAndSubmit()" /> <input
												type="reset" value="重置" /></td>
										</tr>
									</table>
								</fieldset>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<jsp:include page="../frame/Frame3.jsp"></jsp:include>

	<script type="text/javascript">
	getTeacherDepartList();
	loadMenu("SelectTeacherDepartment",TeacherDepartmentNodes,getTeacherDepartmentId,"TeacherDepartment");
	
	</script>
</body>
</html>