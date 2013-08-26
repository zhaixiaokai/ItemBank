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
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
	<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}
</style>

<!-- <script>
function ShowSuccess(responseText, statusText) {
	if(responseText!=null&&responseText!=""){
		alert(responseText.message);
	}else{
	alert("成功添加角色");
	document.location.href="addRole.jsp";
	}
	
}
</script> -->
<script type="text/javascript">
function	save(){
	var	RoleName	= document.AddRoleForm.RoleName.value;
	var	RoleId	= document.AddRoleForm.RoleId.value;
	var	RoleExplain=document.AddRoleForm.RoleExplain.value;
	if(RoleId==""){
		alert("请填写角色唯一标识符");
		return;
	}
	if(RoleId.length>50){
		alert("唯一标识符长度不能超过50");
		return;
	}
	if(!CheckIfIsLetter_Number(RoleId)){
		alert("唯一标识符只能由字母、数字、'_'组成");
		return;
	}
	if(RoleName==""){
		alert("请填写角色名称");
		return;
	}
	if(RoleName.length>20){
		alert("角色名称长度不能超过20");
		return;
	}
	if(!CheckIfChinaNumbLetter(RoleName)){
		alert("角色名称只能由字母、数字、汉字、'_'组成");
		return;
	}
	if(RoleExplain.length>500){
		alert("角色说明的文字长度不能超过500");
	}
	if(confirm("确认添加新的角色")){
		var options={
			type:"post",
			dataType:"json",
			success:ShowSuccess		
			}
		$('#AddRoleForm').ajaxSubmit(options);
		return false;
		}
		
	
}

function ShowSuccess(responseText, statusText) {
	if(responseText!=null&&responseText!=""){
		alert(responseText.text);
		document.location.href="addRole.jsp";
	}else{
	alert("添加角色失败");
	
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
				<th class="pagehead">添加新角色</th>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%">
						<tr>
							<td>
							<form id="AddRoleForm" name="AddRoleForm" action="RoleAddAction">
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">添加角色</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">

										<tr>
											<td></td>
										</tr>

										<tr>
											<td nowrap align="left" height="30px">角色名称:</td>
											<td colspan="3"><input type="text" name="RoleName"
												id="RoleName" /><span class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="left" height="30px">角色唯一标识符:</td>
											<td colspan="3"><input type="text" name="RoleId"
												id="RoleId" /><span class="red"> *</span></td>
										</tr>

										<tr>
											<td nowrap align="left" height="100px">角色说明:</td>
											<td colspan="3"><textarea id="RoleExplain"
													name="RoleExplain" rows="4" cols="70"></textarea></td>
										</tr>
										<tr>
											<td></td>
											<td align="center"><br> <input type="button"
												name="Submit" value="保存" class="button" onclick="save();" />
												<input type="reset" value="重置" /></td>
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
</body>
</html>