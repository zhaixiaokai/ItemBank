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
function	CheckAndSave(){
	var	DicName	= document.AddDataDicForm.DicName.value;
	var	DicId	= document.AddDataDicForm.DicId.value;
	var	DicExplain=document.AddDataDicForm.DicExplain.value;
	if(DicId==""){
		alert("请填写唯一标识符");
		return;
	}
	if(DicId.length>50){
		alert("唯一标识符长度不能超过30");
		return;
	}
	if(!CheckIfIsLetter_Number(DicId)){
		alert("唯一标识符只能由字母、数字、'_'组成");
		return;
	}
	if(DicName==""){
		alert("请填写字典项名称");
		return;
	}
	if(DicName.length>20){
		alert("字典项名称长度不能超过20");
		return;
	}
	if(!CheckIfChinaNumbLetter(DicName)){
		alert("字典项名称只能由字母、数字、汉字、'_'组成");
		return;
	}
	if(DicExplain.length>20){
		alert("字典项说明文字长度不能超过20");
		return;
	}
	if(confirm("确认添加字典项？")){
		$(function() {
			var options = {
				type : "post",
				dataType : "json",
				success : function(result){
					alert(result.text);
					document.location.href="addDataDic.jsp";
				}
			
			};
			$('#AddDataDicForm').ajaxSubmit(options);
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
			<th class="pagehead">创建字典项</th>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<form id="AddDataDicForm" name="AddDataDicForm" action="DataDicAddAction">
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">创建字典项</legend>
									<table style="width: 80%;">

										<tr>
											<td nowrap align="left" height="40px">唯一标识符:</td>
											<td><input type="text" name="DicId" id="DicId" /><span
												class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="left" height="40px">字典项名称:</td>
											<td><input type="text" name="DicName" id="DicName" /><span
												class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="left" height="40px">字典项说明:</td>
											<td><textarea id="DicExplain" name="DicExplain" rows="2"
													cols="70"></textarea></td>
										</tr>
										<tr>
											<td></td>
											<td align="center"><br> <input type="button" value="保存" class="button" onclick="CheckAndSave();" />
												<input type="reset" name="FormReset" value="重置" class="button" />
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
</body>
</html>