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
function CheckAndSubmit(){
	var	curse	=	document.getElementById("SelectCurse").value;
	var	id		=	document.getElementById("KnowledgePointId").value;
	var	name	=	document.getElementById("KnowledgePointName").value;
	var	explain	=	document.getElementById("KnowledgePointExplain").value;
	if(curse==""){
		alert("请选择课程");
		return;
	}
	if(id==""){
		alert("知识点编号不能为空");
		return;
	}
	if(id.length>20){
		alert("知识点编号长度不能超过20");
		return;
	}
	if(!CheckIfIsLetter_Number(id)){
		alert("知识点编号只能由字母、数字、'_'组成");
		return;
	}
	if(name==""){
		alert("知识点名称不能为空");
		return;
	}
	if(name.replace(/[^\x00-\xFF]/g,'**').length>20){
		alert("知识点名称长度不能超过20");
		return;
	}
	if(!CheckIfChinaNumbLetter(name)){
		alert("知识点名称只能由汉字、数字、字母组成");
		return;
	}
	if(explain.replace(/[^\x00-\xFF]/g,'**').length>300){
		alert("知识点内容长度不能超过300字节");
		return;
	}
	$(function() {
		var options = {
			type : "post",
			dataType : "json",
			success : function(result){
				alert(result.text);
				if(result.result=="succ"){
					$('#KnowledgePointAddForm')[0].reset();
				}
			}
		
		};
		$('#KnowledgePointAddForm').ajaxSubmit(options);
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
		<form action="KnowledgePointAddAction" name="KnowledgePointAddForm" id="KnowledgePointAddForm">
			<table width="100%" class="table1">
				<tr>
					<th class="pagehead">添加知识点</th>
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
										<legend style="font-size: 12px">添加知识点</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 80%; font-size: 12px">
											<tr>
												<td nowrap align="right" width="100">学院专业:</td>
												<td nowrap width="30">
													<div id="SpecialField"></div>
												</td>

											</tr>
											<tr>
												<td nowrap align="right" width="100">所属课程:</td>
												<td nowrap><select name="SelectCurse" id="SelectCurse">
														<option value="">--请选择--</option>
												</select></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100">知识点编号:</td>
												<td nowrap><input name="KnowledgePointId"
													id="KnowledgePointId" class="text" type="text" /> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100">知识点名称:</td>
												<td nowrap><input name="KnowledgePointName"
													id="KnowledgePointName" class="text" type="text" /> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100">备注说明:</td>
												<td colspan="3"><textarea id="KnowledgePointExplain"
														name="KnowledgePointExplain" rows="5" cols="70"></textarea></td>
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