<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>
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
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>

<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>

<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}
</style>
<script type="text/javascript">
function	CheckAndSubmit(){
	var ConfigOptionId=document.ConfigOptionForm.ConfigOptionId.value;
	var ConfigOptionName=document.ConfigOptionForm.ConfigOptionName.value;
	var ConfigOptionValue=document.ConfigOptionForm.ConfigOptionValue.value;
	//对唯一标识符进行合法性检查
	if(ConfigOptionId==""){
		alert("配置项唯一标识符不能为空");
		modifyForm.ClassName.focus();
		return false;
	}
	else{
		if(!CheckIfIsLetter_Number(ConfigOptionId)){
			alert("配置项唯一标识符必须由数字、字母、下划线组成");
			return false;
		}
		if(ConfigOptionId.length>20){
			alert("配置项的唯一标识符长度不能超过30");
			return false;
		}
	}
	//对配置项的名称进行合法性检查
	if(ConfigOptionName==""){
		alert("配置项名称不能为空");
		modifyForm.ClassId.focus();
		return false;
	}
	else{
		if(!CheckIfChinaNumbLetter(ConfigOptionId)){
			alert("配置项名称必须由数字、字母、汉字组成");
			return false;
		}
		if(ConfigOptionName.length>50){
			alert("配置项名称的长度不能超过50");
			return false;
		}
	}
	//对配置项的值进行合法性检查
	if(ConfigOptionValue==""){
		alert("配置项值不能为空");
		modifyForm.TeacherId.focus();
		return false;
	}
	else{
		if(!CheckIfChinaNumbLetter(ConfigOptionId)){
			alert("配置项名称必须由数字、字母、汉字组成");
			return false;
		}
		if(ConfigOptionValue.length>20){
			alert("配置项值长度不能超过20");
			return false;
		}
	}
	if(confirm("确认添加配置项？")){
		$(function() {
			var options = {
				type : "post",
				dataType : "json",
				success : ShowSuccess		
			};
			$('#ConfigOptionForm').ajaxSubmit(options);
			return false;
		});
	}
}
function ShowSuccess(responseText, statusText) {
	if(responseText!=null&&responseText!=""){
		alert(responseText.text);
		document.location.href="addConfig.jsp";
	}else{
	alert("添加配置项失败");
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
			<th class="pagehead">创建配置项</th>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<form id="ConfigOptionForm" name="ConfigOptionForm" action="ConfigOptionAddAction">
								<fieldset style="height: 100%;">
									<legend>创建配置项</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">
										<tr>
											<td></td>
										</tr>

										<tr>
											<td height="60px">唯一标识符:</td>
											<td><input type="text" name="ConfigOptionId"
												id="ConfigOptionId" /><span class="red"> *</span></td>
										</tr>
										<tr>
											<td height="50px">配置项名称:</td>
											<td colspan="3"><input type="text" name="ConfigOptionName"
												id="ConfigOptionName" /><span class="red"> *</span></td>
										</tr>
										<tr>
											<td height="50px">配置项值:</td>
											<td>
											<input type="text" name="ConfigOptionValue"
												id="ConfigOptionValue" /><span class="red"> *</span></td>
										</tr>

										<tr>
											<td nowrap align="left" height="100px">配置项说明:</td>
											<td colspan="3"><textarea id="ConfigOptionExplain" name="ConfigOptionExplain"
													rows="2" cols="70"></textarea></td>
										</tr>
										<tr>
											<td></td>
											<td align="center"><br> <input type="button"
												value="保存" class="button" onclick="CheckAndSubmit();" />
												<input type="reset" value="重置" class="button"/>
											</td>
										</tr>
									</table>
								</fieldset>
								<input type="hidden" name="ConfigOptionInfo" id="ConfigOptionInfo" value="1"/>
								<input type="hidden" name="ConfigOptionNum"	id="ConfigOptionNum" value="1">
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>