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
<link href="../css/infoedit.css" rel="stylesheet" type="text/css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../js/formReset.js" type="text/javascript"></script>

<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}
</style>

<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript">
function	GetDataDicList(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetDataDicOption",
		success : function(result) {
			TeacherList	=	result.data;
			$("#SelectDataDic ").empty();
			$("#SelectDataDic").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectDataDic").append("<option value=\""+result.data[i].id+"\">"+
						result.data[i].name+"</option>");
			}
		},
		error:function(){
		}
	});
}
function	CheckAndSubmit(){
	var	DataDic	=	document.getElementById("SelectDataDic").value;
	//alert(DataDic);
	var	ValueName	=	document.getElementById("DicValueOptionName").value;
	var	ValueValue	=	document.getElementById("DicValueOptionValue").value;
	var	ValueSNO	=	document.getElementById("DicValueOptionSNO").value;
	
	if(DataDic==""){
		alert("请选择所属字典项");
		document.ValueAddForm.SelectDataDic.focus();
		return ;
	}
	if(ValueName==""){
		alert("值项名称不能为空");
		document.ValueAddForm.DicValueOptionName.focus();
		return;
	}
	if(ValueName.length>20){
		alert("值项名称长度不能超过50个字符");
		document.ValueAddForm.DicValueOptionName.focus();
		return;
	}
	if(!CheckIfChinaNumbLetter(ValueName)){
		alert("值项名称只能由 汉字、字母、数字、'_'组成");
		document.ValueAddForm.DicValueOptionName.focus();
		return;
	}
	if(ValueValue==""){
		alert("值项值不能为空");
		document.ValueAddForm.DicValueOptionValue.focus();
		return;
	}
	if(ValueValue.length>20){
		alert("值项值长度不能超过50个字符");
		document.ValueAddForm.DicValueOptionValue.focus();
		return;
	}
	if(!CheckIfIsLetterNumber(ValueValue)){
		alert("值项值只能由 字母、数字 组成");
		document.ValueAddForm.DicValueOptionValue.focus();
		return;
	}
	if(ValueSNO==""){
		alert("值项序号不能为空");
		document.ValueAddForm.DicValueOptionSNO.focus();
		return;
	}
	if(!CheckIfIsNumber(ValueSNO)){
		alert("值项序号只能是正整数");
		document.ValueAddForm.DicValueOptionSNO.focus();
		return;
	}
	if(ValueSNO.length>30){
		alert("值项值序号长度不能超过30");
	}
	if(confirm("确认添加新的值项？")){
	$(function() {
		var options = {
			type : "post",
			dataType : "json",
			success : function(result){
				alert(result.text);
				document.location.href="addValue.jsp";
			}
		
		};
		$('#ValueAddForm').ajaxSubmit(options);
		return false;
	});
	}
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
			<th class="pagehead">创建值项</th>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<form id="ValueAddForm" name="ValueAddForm" action="ValueOptionAddAction">
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">创建值项</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">
										<tr>
											<td>所属字典项：</td>
											<td><select name="SelectDataDic" id="SelectDataDic">
												<option value="">--请选择--</option>
											</select><span class="red"> *</span></td>
										</tr>
										<tr>
										<tr>
											<td nowrap align="left" height="50px">值项名称:</td>
											<td><input type="text" name="DicValueOptionName" id="DicValueOptionName" /><span
												class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="left" height="50px">值项值:</td>
											<td><input type="text" name="DicValueOptionValue" id="DicValueOptionValue" /><span
												class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="left" height="50px">值项顺序号:</td>
											<td><input type="text" name="DicValueOptionSNO" id="DicValueOptionSNO" /><span
												class="red"> *</span></td>
										</tr>
										<tr>
											<td></td>
											<td align="center"><br> 
												<input type="button" value="保存" class="button" onclick="CheckAndSubmit()" />
												<input type="reset" value="重置" class="button"  />
											</td>
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
	GetDataDicList();
	</script>
</body>
</html>