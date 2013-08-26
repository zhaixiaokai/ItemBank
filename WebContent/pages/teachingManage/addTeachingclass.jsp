<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
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

<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<script type="text/javascript">

var	TeacherList	=	"";

function	GetCourseBySpecialfield(){
	getMajor(this.innerHTML);
	getCurse(this);
}
function	GetTeacherByTeacherDepartment(){
	//alert(this.id);
	document.getElementById("TeacherDepartment").innerHTML=this.innerHTML;
	getTeacherList(this);
}
function getMajor(o){
	document.getElementById("College").innerHTML=o;
}
function getTeacherList(o){
	var	SelectedDepartmentId	=	o.id;
	var	innerHTML	= 	document.getElementById("SelectTeacher").innerHTML;
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "TeacherSelectByDepartmentId",
		data:"DepartmentId="+SelectedDepartmentId,
		success : function(result) {
			TeacherList	=	result.data;
			$("#SelectTeacher ").empty();
			$("#SelectTeacher").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectTeacher").append("<option value=\""+result.data[i].school_id+"\">"+
						result.data[i].name+"  "+result.data[i].school_id+
						"</option>");
			}
		},
		error:function(){
		}
	});
}
function getCurse(o){
	var	SelectedSpecialField	=	o.id;
	if(SelectedSpecialField	==	null){
		alert("请选择专业");
		return;
	}
	var	innerHTML	= 	document.getElementById("SelectCurse").innerHTML;
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "CurseSelectBySpecialFieldAction",
		data:"specialFieldId="+SelectedSpecialField,
		success : function(result) {
			$("#SelectCurse ").empty();
			$("#SelectCurse").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectCurse").append("<option id=\""+result.data[i].curse_id+"\" value=\""+result.data[i].curse_id+"\">"+
						result.data[i].curse_name+"</option>");
			}
		},
		error:function(){
		}
	});
	
}
function GetTeachingMaterialOptions(){
	var	selectedCurseId	=	document.getElementById("SelectCurse").value;
	
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "TeachingMaterialGetByCurseId",
		data:"CurseId="+selectedCurseId,
		success : function(result) {
			$("#SelectTeachingMaterial ").empty();
			$("#SelectTeachingMaterial").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectTeachingMaterial").append("<option id=\""+result.data[i].teaching_material_id+"\" value=\""+result.data[i].teaching_material_id+"\">"+
						result.data[i].name+"</option>");
			}
		 },
		error:function(){
		}
	});
}
    //加载多级菜单
	function loadMenu(target,zNodes,func,KeyId) {
		var menuStr = '';
		menuStr = CreateMenuContent(zNodes, 0, menuStr, 0,KeyId);
		if (menuStr != '') {
			menuStr = '<ul id="nav">' + menuStr + '</ul>';
				//alert(menuStr);
			document.getElementById(target).innerHTML = menuStr;
		}
		//只给叶子节点添加点击事件
		for(var i=0;i<zNodes.length;i++){
			if(IsChild(zNodes[i].id, zNodes)){
				continue;
			}
			document.getElementById(zNodes[i].id).onclick=func;
		}
	}
	//递归生成菜单
	var CreateMenuContent = function(zNodes, num, menuStr, pid,KeyId) {

		if (num == (zNodes.length)) {
			// alert(zNodes.length);
			return menuStr;
		} else {
			var codeStr = '';
			for ( var i = num; i < zNodes.length; i++) {
				if (pid == zNodes[i].pid) {
					if (pid == 0) {     //判断是否为根节点
						codeStr += '<li><a href="javascript:void(0);" name="'+"College"+'" id="'+KeyId+'">';
						codeStr += zNodes[i].text;
						codeStr += '</a>';
					} else {
						codeStr += '<li><a href="javascript:void(0);" id="'+zNodes[i].id+'">';
						codeStr += zNodes[i].text;
						codeStr += '</a>';
					}
		
					if (IsChild(zNodes[i].id, zNodes)) {  //判断是否为叶子节点
						codeStr += '<ul>'
								+ CreateMenuContent(zNodes, (i + 1), '',
										zNodes[i].id,KeyId) + '</ul>';
					}
					codeStr += '</li>';
				}
			}
			return menuStr += codeStr;

		}
	};
	//验证是否存在子项
	var IsChild = function(id, zNodes) {
		var isPass = false;
		for ( var i = 0; i < zNodes.length; i++) {
			if (id == zNodes[i].pid && !isPass) {
				isPass = true;
				break;
			}
		}
		return isPass;
	}
/* ------------------------------------------------------------------------ */
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
	//生成学院专业多级菜单
	var	SchoolStructure=null;
	function	getSchoolStructureList(){
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "SchoolStructureOptionsGetAction",
			success : function(result) {
				SchoolStructure=result.data;
			},
			error:function(){
			}
		});
	}
	function	TeacherSelectAddEventListener(){
		for(var	i=0;i<TeacherList.length;i++){
			var	TeacherId	=	TeacherList[i].school_id;
			document.getElementById(TeacherId).onchanged=function(){
				//alert("clicked");
			}
		}
	}
	function EndSelectTeacher(e){
		//alert(this.value);
		document.getElementById("InputTeacher").value=e.value;
	}
	function	CheckAndSubmit(){
		//alert(document.AddTeachingClassForm.SelectCurse.value);
		if(document.AddTeachingClassForm.SelectCurse.value==""){
			alert("请选择课程");
			return;
		}
		if(document.AddTeachingClassForm.ClassName.value==""){
			alert("请填写班级名称");
			return;
		}
		if(!CheckIfChinaNumbLetter(document.AddTeachingClassForm.ClassName.value)){
			alert("班级名称只能由汉字、字母数字组成");
			return;
		}

		if(document.AddTeachingClassForm.ClassName.value.length>20){
			alert("班级名称长度最多20个字符");
			return;
		}
		if(document.AddTeachingClassForm.ClassId.value==""){
			alert("请填写班级编号");
			return;
		}
		if(!CheckIfIsLetter_Number(document.AddTeachingClassForm.ClassId.value)){
			alert("班级编号只能由字母、数字、'_'组成");
			return;
		}
		if(document.AddTeachingClassForm.ClassId.value.length>20){
			alert("班级编号长度最多20个字符");
			return;
		}
		if(document.AddTeachingClassForm.TeacherId.value==""){
			alert("请填写教师工作证号码");
			return;
		}
		if(!CheckIfIsLetterNumber(document.AddTeachingClassForm.TeacherId.value)){
			alert("教师工作证号码只能由字母、数字组成");
			return;
		}
		if(document.AddTeachingClassForm.TeacherId.value.length>20){
			alert("教师工作证号长度最多20个字符");
			return;
		}
		if(document.AddTeachingClassForm.TeachingMaterialId.value==""){
			alert("请选择班级使用的教材");
			return;
		}
		if($('#ClassExplain').val().replace(/[^\x00-\xFF]/g,'**').length>300){
			alert("班级说明最多300个字符");
			return;
		}
		$(function() {
			var options = {
				type : "post",
				dataType : "json",
				success : function(result){
					alert(result.text);
					if(result.result=="succ"){
						$('#AddTeachingClassForm')[0].reset();
					}
				}
			
			};
			$('#AddTeachingClassForm').ajaxSubmit(options);
			return false;
		});
	}
</script>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div style="margin-top: 10px">
		<form action="TeachingClassAddAction" name="AddTeachingClassForm" id="AddTeachingClassForm">
		<table width="100%" class="table1">
			<tr>
				<th class="pagehead">添加开课班级</th>
			</tr>
			<tr>
				<td>
					<table width="100%">
						<tr>
							<td>
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">添加开课班级</legend>
									<table style="width: 100%;">
										<tr>
											<td width="60px">学院专业:</td>
											<td width="150px"><div id="SpecialField"></div></td>
											<td width="100px"></td>
											<td></td>
										</tr>

										<tr>
											<td>所开课程:</td>
											<td><select name="SelectCurse" id="SelectCurse" onchange="GetTeachingMaterialOptions();">
													<option value="">--请选择--</option>
											</select><span class="red"> *</span></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td>班级编号:</td>
											<td><input name="ClassId" id="InputClassId" class="text" type="text"
												size="15" /> <span class="red"> *</span></td>
											<td></td>
										</tr>
										<tr>
											<td>班级名称:</td>
											<td><input name="ClassName" id="InputClassName" class="text" type="text"
												size="15" /> <span class="red"> *</span></td>
											<td></td>
											<td></td>
										</tr>
										<tr>
											<td>授课教师:</td>
											<td><input id="InputTeacher" name="TeacherId" class="text" type="text"
												size="15" /> <span class="red"> *</span></td>
											<td width="150px"><div id="SelectTeacherDepartment"></div></td>
											<td><select id="SelectTeacher" onchange="EndSelectTeacher(this);"><option value="">--请选择--</option></select></td>
										</tr>
										<tr>
											<td>授课教材:</td>
											<td><select name="TeachingMaterialId" id="SelectTeachingMaterial"><option value="">--请选择--</option></select><span class="red"> *</span></td>
											<td></td>
											<td></td>
										</tr>										
									</table>
									<table>
										<tr>
											<td>备注说明:</td>
											<td><textarea id="ClassExplain" name="ClassExplain"
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
					value="保存" class="button" onClick="CheckAndSubmit();" /> 
					<input type="reset" name="Submit" value="重置" class="button"/></td>
			</tr>
		</table>		
		
		</form>
		
	</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	
	<script type="text/javascript">
	

	getTeacherDepartList();
	getSchoolStructureList();
	loadMenu("SpecialField",SchoolStructure,GetCourseBySpecialfield,"College");
	loadMenu("SelectTeacherDepartment",TeacherDepartmentNodes,GetTeacherByTeacherDepartment,"TeacherDepartment");
	</script>
</body>
</html>