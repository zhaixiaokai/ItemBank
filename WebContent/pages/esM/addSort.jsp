<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*"%>
<%@	page import="net.ib.util.service.impl.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
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
<style type="text/css">
</style>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script src="../js/formReset.js" type="text/javascript"></script>
<script type="text/javascript">
	function save() {
		var name = document.getElementById("name").value;
		var identifier = document.getElementById("identifier").value;
		var discription = document.getElementById("discription").value;
		/* var defaultsort;
		var temp = document.getElementsByName("defaultsort");
		for( var i=0;i<temp.length;i++){
			if(temp[i].checked){
				defaultsort=temp[i].value;
			}
		} */
		if (name == "") {
			alert("分类体系名称为必填项，请重新输入 ");
			document.frmMain.name.focus();
		} else if( !CheckIfChinaNumbLetter(name)){
			alert("名称格式有误，请输入汉字，数学或者字母，20字符以内 ");
			document.frmMain.name.focus();
		} else if(name.length>20){
			alert("名称长度超出范围，长度应在20字符以内 ");
			document.frmMain.name.focus();
		}else if(identifier == ""){
				alert("分类体系标识符为必填项，请重新输入 ");
				document.frmMain.identifier.focus();
		} else if( !CheckIfIsLetter_Number(identifier)){
				alert("标识符格式有误，请输入数学或者字母或者下划线，20字符以内 ");
				document.frmMain.identifier.focus();
		}else if(identifier.length>20){
				alert("标示符长度超过范围，长度应在20字符以内 ");
				document.frmMain.identifier.focus();
		}else if (discription.replace(/[^\x00-\xFF]/g,'**').length>300) {
			alert("分类体系说明过长，请输入300字节以内说明。注：汉字一位是两字节，数字和字母一位是一字节 ");
			document.frmMain.discription.focus();
		}else {
				if (confirm("确认创建新的分类体系？")) {
					$(function() {
						var options = {
							type : "post",
							dataType : "json",
							beforeSubmit : showStart,
							success : showSuccess,
						};
						$('#frmMain').ajaxSubmit(options);
						return false;
					});
				}
			}
		}

	function showStart() {
		return true;
	}
	function showSuccess(responseText, statusText) {
		if(responseText.message!=null&&responseText.message!=""){
			if(responseText.message=="添加成功"){
				alert(responseText.message);
				document.location.href="addSort.jsp";
			}else{
				alert(responseText.message);
			}
		}else{
			alert("添加失败");
		}
	}
</script>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/examDbManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<div style="margin-top: 7px;">
		<form id="frmMain" action="SortAddAction" name="frmMain">
			<table width="100%" class="CContent">
				<tr>
					<th class="pagehead">添加分类体系</th>
				</tr>
				<tr>
					<td width="100%">
						<table width="100%">
							<tr>
								<td>
									<fieldset style="height: 100%;">
										<legend style="font-size: 12px">添加分类体系</legend>
										<table border="0" cellpadding="2" cellspacing="1"
											style="width: 80%; font-size: 12px">
											<tr>
												<td nowrap align="right" width="100">分类体系名称:</td>
												<td><input name="name" id="name"  class="text"
													style="width: 150px" type="text" size="40" title="请输入汉字，数学或者字母，20字符以内" />
													<span class="red" > *    </span></td>
											</tr>
											<tr>
												<td nowrap align="right" width="100">标识符:</td>
												<td><input name="identifier" id="identifier"
													class="text" style="width: 150px" type="text" size="40"
													 title="请输入字母，数字或者下划线,20字符以内"/> <span class="red"> *</span></td>
											</tr>
											
											<tr>
												<td nowrap align="right" height="120px">分类体系说明:</td>
												<td colspan="3"><textarea id="discription"
														name="discription" rows="5" cols="70"></textarea></td>
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
						class="button" onclick="save();" /> <input type="reset"
						name="Submit2" value="重置" class="button" /></td>
				</tr>
			</table>
		</form>

	</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	<iframe name="iframe" style="visibility: hidden"> </iframe>
</body>
</html>