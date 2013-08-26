<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
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
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>

<script src="../js/formReset.js" type="text/javascript"></script>
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	
</script>
<script type="text/javascript">
	//获取分类数据
	var sortlistdata = null;
	var config_leafid = null;
	function getsortList() {
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "SortConfigAction",
			success : function(result) {
				result = eval(result);
				sortlistdata = result;
			},
			error : function() {
			}
		});
	}

	//获取用途数据
	function getusedata() {
		$.ajax({
					type : "post",
					dataType : "json",
					async : false,
					url : "UseConfigAction",
					success : function(result) {
						$("#selectuse ").empty();
						$("#selectuse").append(
								"<option value=\"\">--请选择--</option>");
						for ( var i = 0; i < result.data.length; i++) {
							if (i == "0") {
								$("#selectuse")
										.append(
												"<option selected id=\""+result.data[i].dictionary_entries_value+
						"\" value=\""+result.data[i].dictionary_entries_value+"\">"
														+ result.data[i].name
														+ "</option>");

							} else {
								$("#selectuse")
										.append(
												"<option id=\""+result.data[i].dictionary_entries_value+
						"\" value=\""+result.data[i].dictionary_entries_value+"\">"
														+ result.data[i].name
														+ "</option>");

							}
						}
					},
					error : function() {
					}
				});

	}

	//获取多级菜单中元素的值
	function getMajor(o) {
		document.getElementById("sortelement").innerHTML = o;
	}
	function FunctionFullfil() {
		//显示分类列表
		getMajor(this.innerHTML);
		config_leafid = this.id;
	}

	//数据合理性验证
	function save() {
		var name = document.getElementById("name").value;
		var identifier = document.getElementById("identifier").value;
		var discription = document.getElementById("discription").value;
		//获取试题库用途中select的值
		var options = document.getElementById("selectuse").options;
		var index = document.getElementById("selectuse").selectedIndex;
		var selectedOption = options[index];
		var selectuse = selectedOption.id;
		if (name == "") {
			alert("试卷库名称为必填项，请重新输入 ");
			return;
		}
		if (!CheckIfChinaNumbLetter(name)) {
			alert("名称格式有误，请输入汉字，数字或者字母 ");
			return;
		}
		if(name.length>20){
			alert("名称长度不能超过20个字符");
			return;
		}
		if (identifier == "") {
			alert("试卷库标识符为必填项，请重新输入 ");
			return;
		}
		if (!CheckIfIsLetter_Number(identifier)) {
			alert("标识符格式有误，请输入数字或者字母或者下划线 ");
			return;
		}
		if(identifier.length>20){
			alert("标识符长度不能超过20个字符");
			return;
		}
		if(discription.length>300){
			alert("说明长度不能超过300字符");
			return;
		}
		if (confirm("确认创建新的试卷库")) {
			var leafid ="<%=request.getParameter("leafid")%>";
			$(function() {
				var options = {
					type : "post",
					dataType : "json",
					url : "PaperAddAction?leafid=" + leafid
							+ "&config_leafid=" + config_leafid + "&use="
							+ selectuse,
					beforeSubmit : showStart,
					success : showSuccess,
				};
				$('#frmMain').ajaxSubmit(options);
				return false;
			});
		}
	}
	function showStart() {
		return true;
	}
	function showSuccess(responseText, statusText) {
		
		if (responseText.message != null && responseText.message != "") {
			if (responseText.message == "添加成功") {
				alert(responseText.message);
				//重置表单
				var form = document.getElementById("frmMain");
				form.reset();
			} else {
				alert(responseText.message);
			}
		} else {
			alert("添加失败");
		}
	}
</script>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperDbManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div style="margin-top: 10px">
		<form id="frmMain">
		<table width="100%" class="table1">
			<tr>
				<th class="pagehead">创建试卷库</th>
			</tr>

			<tr>
				<td width="100%">
					<table width="100%">
						<tr>
							<td>
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">创建试卷库</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">
										<tr>
											<td nowrap align="right" width="100">试卷库名称:</td>
											<td nowrap><input id ="name" name="EPName" class="text"
												style="width: 150px" type="text" size="40" /> <span
												class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right" width="100">标识符:</td>
											<td nowrap><input id="identifier" name="EPIdentifier" class="text"
												style="width: 150px" type="text" size="40" /> <span
												class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right" width="100">分类配置:</td>
											<td nowrap width="30"><div id="sortconfig"	></div></td>

										</tr>
										<tr>
												<td nowrap align="right" width="100">试卷库用途:</td>
												<td nowrap width="30"><select id="selectuse"
													name="selectuse"></select></td>

											</tr>

										<tr>
											<td nowrap align="right" height="80px">试卷库说明:</td>
											<td colspan="3"><textarea id="discription" name="discription"
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
					<td align="center"><br> <input type="button" value="保存"
						class="button" onclick="save();" /> <input type="reset"
						name="Submit2" value="重置" class="button" /></td>
				</tr>
		</table>
		</form>
	</div>
	<script type="text/javascript">
		
	</script>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	<script>
		getsortList();
		loadMenu("sortconfig", sortlistdata, FunctionFullfil, "sortelement");
		getusedata();
	</script>
</body>
</html>