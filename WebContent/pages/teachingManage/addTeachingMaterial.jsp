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
<script type="text/javascript" src="../js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>

<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
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
	var	name	=	document.getElementById("TeachingMaterialName").value;
	var	id	=	document.getElementById("TeachingMaterialID").value;
	var	version	=	document.getElementById("TeachingMaterialVersion").value;
	var	author	=	document.getElementById("TeachingMaterialAuthor").value;
	var	date	=	document.getElementById("TeachingMaterialDate").value;
	var	house	=	document.getElementById("TeachingMaterialHouse").value;
	var	code	=	document.getElementById("TeachingMaterialCode").value;
	
	
	if(name==""){
		alert("教材名称不能为空");
		modifyForm.TeachingMaterialName.focus();
		return false;
	}
	else
	{
		if(name.length>50){
			alert("教材名称的长度不能超过50");
			return false;
		}
		if(!CheckIfChinaNumbLetter(name)){
			alert("教材名称只能由汉字、数字、字母构成");
			modifyForm.TeachingMaterialName.focus();
			return false;
		}
    }
	
	if(id==""){
		alert("教材编号不能为空");
		modifyForm.TeachingMaterialID.focus();
		return false;
	}
	else
	{
		if(id.length>20){
			alert("教材编号长度不能超过20");
			return false;	
		}
		if(!CheckIfIsLetter_Number(id)){
			alert("教材编号只能由字母、数字、'_'组成");
			modifyForm.TeachingMaterialID.focus();
			return false;
		}
	}
	
	if(version==""){
		alert("教材版本不能为空");
		modifyForm.TeachingMaterialVersion.focus();
		return false;
	}
	else
	{
		if(version.length>25){
			alert("教材版本长度不能超过25");
			return false;	
		}
	}
	
	if(author.length>25){
		alert("教材作者名长度不能超过25");
		return false;	
	}
	
	if(house.length>50){
		alert("教材出版社名长度不能超过50");
		return false;	
	}
	
	if(code==""){
		alert("教材ISBN码为必填项，请重新输入 ");
		return false;
	}else{
		if(code.length>50){
			alert("教材ISBN码长度不能超过50");
			return false;
		}
		else if(!isISBN(code)){
			alert("教材ISBN码格式不正确！");
			return false;
		}
	}
	
	if(confirm("是否确认添加新的教材？"))
	{
			$(function() {
				var options = {
					type : "post",
					dataType : "json",
					success : function(result){
						if(result.text=="添加教材成功")
						{
							alert(result.text);
							document.location.href="addTeachingMaterial.jsp";
						}
						else
						{
							alert(result.text);	
						}
//						alert(result.text);
//						document.location.href="addTeachingMaterial.jsp";
					}
				
				};
				$('#TeachingMaterialAddForm').ajaxSubmit(options);
				return false;
			});
	}
}

// 自动选择日期插件
$(function() {
	$("#TeachingMaterialDate").datepicker({
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
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div style="margin-top: 10px">
		<form action="TeachingMaterialAddAction" name="TeachingMaterialAddForm" id="TeachingMaterialAddForm">
			<table width="100%" class="table1">
				<tr>
					<th class="pagehead">添加教材</th>
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
										<legend style="font-size: 12px">添加教材（标<span class="red"> *</span>为必填项！）</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 80%; font-size: 12px">
											<tr>
												<td nowrap align="right" width="100" height="30px">学院专业：</td>
												<td nowrap width="30">
													<div id="SpecialField"></div>
												</td>

											</tr>
											<tr>
												<td nowrap align="right" width="100" height="30px">所属课程：</td>
												<td nowrap><select name="SelectCurse" id="SelectCurse">
														<option value="">--请选择--</option>
												</select></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100" height="30px">教材编号：</td>
												<td nowrap><input name="TeachingMaterialID"
													id="TeachingMaterialID" class="text" type="text" /> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100" height="30px">教材名称：</td>
												<td nowrap><input name="TeachingMaterialName"
													id="TeachingMaterialName" class="text" type="text" /> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100" height="30px">教材版本：</td>
												<td nowrap><input name="TeachingMaterialVersion"
													id="TeachingMaterialVersion" class="text" type="text" /> <span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100" height="30px">教材作者：</td>
												<td nowrap><input name="TeachingMaterialAuthor"
													id="TeachingMaterialAuthor" class="text" type="text" /></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100" height="30px">出版日期：</td>
												<td nowrap><input name="TeachingMaterialDate"
													id="TeachingMaterialDate"  class="text" type="text" readOnly="true" /></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100" height="30px">出版单位：</td>
												<td nowrap><input name="TeachingMaterialHouse"
													id="TeachingMaterialHouse"  class="text" type="text" /></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100" height="30px">ISBN码：</td>
												<td nowrap><input name="TeachingMaterialCode"
													id="TeachingMaterialCode"  class="text" type="text" /><span class="red"> *</span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100" height="30px">教材封面:</td>
												<td colspan="3"><textarea id="TeachingMaterialRemarks"
														name="TeachingMaterialRemarks" rows="5" cols="70"></textarea></td>
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