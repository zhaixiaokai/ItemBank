<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../esM/css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link rel="stylesheet" type="text/css" href="../esM/css/menu.css">
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../esM/css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
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

<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<script type="text/javascript">

//生成学院专业多级菜单
var	SchoolStructure=null;
var SpecialFieldId=null;
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


function	GetCourseBySpecialfield(){
	getMajor(this.innerHTML);
	getCurse(this);
}
function getMajor(o){
	document.getElementById("College").innerHTML=o;
}
function getCurse(o){
	var	SelectedSpecialField	=	o.id;
	SpecialFieldId	=	SelectedSpecialField;
	if(SelectedSpecialField	==	null){
		alert("请选择专业");
		return;
	}
//	alert(SpecialFieldId);
	/* var	innerHTML	= 	document.getElementById("SelectCurse").innerHTML;
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
	}); */
	
}
function CheckAndSubmit(){
//	var	curse	=	document.getElementById("SelectCurse").value;
/* 	if(curse==""){
		alert("请选择课程");
		return;
	} */
	var	id		=	document.getElementById("CurseID").value;
	var	name	=	document.getElementById("CurseName").value;
	var	credit	=	document.getElementById("CurseCredit").value;
 	
	var special = document.getElementById("GetSpecial");
	special.value = SpecialFieldId; 
//	alert(document.getElementById("GetSpecial").value);

	if(SpecialFieldId==null){
		alert("请选择专业");
		return;
	}
	if(id==""){
		alert("课程编号不能为空");
		return;
	}
	if(id.length>20){
		alert("课程编号长度不能超过20");
		return;
	}
	if(!CheckIfIsLetter_Number(id)){
		alert("课程编号只能由字母、数字、'_'组成");
		return;
	}
	if(name==""){
		alert("课程名称不能为空");
		return;
	}
	if(name.length>20){
		alert("课程名称长度不能超过20");
		return;
	}
	if(!CheckIfChinaNumbLetter(name)){
		alert("课程名称只能由汉字、数字、字母组成");
		return;
	}
/* 	if(explain.length>500){
		alert("教材内容长度不能超过500字节");
		return;
	} */
	if(credit==""){
		alert("课程学分不能为空");
		return;
	}
	if(/* !CheckIfIsNumber(credit)&& */!isDecimal(credit)){
		alert("课程学分只能为正整数或者小数");
		return false;
	}
//	alert(credit);
	
	$(function() {
		var options = {
			type : "post",
			dataType : "json",
			success : function(result){
				alert(result.text);
				if("succ"==result.result)
				$('#CurseAddForm')[0].reset();
			}
		
		};
//		alert(credit);
		$('#CurseAddForm').ajaxSubmit(options);
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
		<form action="CurseAddAction" name="CurseAddForm" id="CurseAddForm">
			<table width="100%" class="table1">
				<tr>
					<th class="pagehead">添加课程</th>
				</tr>
				<tr>
					<td>
						<table border="0" cellpadding="0" cellspacing="0"
							style="width: 100%">
						</table>
					</td>
				</tr>
				<tr>
					<td width="100%">
						<table width="100%">
							<tr>
								<td>
									<fieldset style="height: 100%;">
										<legend style="font-size: 12px">添加课程</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 80%; font-size: 12px">
											<tr>
												<td nowrap align="right" width="100">学院专业:</td>
												<td nowrap width="30">
													<div id="SpecialField"></div>
												</td>

											</tr>
											<!-- <tr>
												<td nowrap align="right" width="100">所属课程:</td>
												<td nowrap><select name="SelectCurse" id="SelectCurse">
														<option value="">--请选择--</option>
												</select></td>
											</tr> -->
											<tr>
												<td nowrap align="right" width="100">课程编号:</td>
												<td nowrap><input name="CurseID"
													id="CurseID" class="text" type="text" /> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100">课程名称:</td>
												<td nowrap><input name="CurseName"
													id="CurseName" class="text" type="text" /> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100">课程学分:</td>
												<td nowrap><input name="CurseCredit"
													id="CurseCredit" class="text" type="text" /> <span
													class="red"> *</span></td>
												<td nowrap style="visibility:hidden"><input name="GetSpecial"
													id="GetSpecial" class="text" type="text" /></td>
											</tr>
											<tr>
											    <td nowrap align="right" height="30px" width="100">考核方式:</td>
											    <td nowrap width="30"><input type="radio" id="yes" checked name="radio" value="1">考试 <input
																		type="radio" id="no"  name="radio" value="0">考查</td>
											    
										        </tr>
											<tr>
												<td nowrap align="right" width="100">备注说明:</td>
												<td colspan="3"><textarea id="CurseRemarks"
														name="CurseRemarks" rows="5" cols="70"></textarea></td>
											</tr>
										</table>
									</fieldset>
								</td>
							</tr>
						</table>

					</td>
				</tr>

				<tr>
					<td align="center"><br> <input type="button" value="保存"
						class="button" onClick="CheckAndSubmit();" /> 
						<input type="reset" name="Submit" value="重置" class="button" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	<script type="text/javascript">
		getSchoolStructureList();
		loadMenu("SpecialField", SchoolStructure, GetCourseBySpecialfield,
				"College");
	</script>
</body>
</html>