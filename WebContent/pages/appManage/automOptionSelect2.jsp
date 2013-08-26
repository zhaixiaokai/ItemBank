<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>自动组卷配置选择题</title>

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
<script type="text/javascript">
	function copyToList(from, to) {
		fromList = eval('document.forms[0].' + from);
		toList = eval('document.forms[0].' + to);
		if (toList.options.length > 0 && toList.options[0].value == 'temp') {
			toList.options.length = 0;
		}
		var sel = false;
		for (i = 0; i < fromList.options.length; i++) {
			var current = fromList.options[i];
			if (current.selected) {
				sel = true;
				if (current.value == 'temp') {
					alert('你不能选择这个项目!');
					return;
				}
				txt = current.text;
				val = current.value;
				toList.options[toList.length] = new Option(txt, val);
				fromList.options[i] = null;
				i--;
			}
		}
		if (!sel)
			alert('你还没有选择任何项目');
	}
	function allSelect() {
		List = document.forms[0].chosen;
		if (List.length && List.options[0].value == 'temp')
			return;
		for (i = 0; i < List.length; i++) {
			List.options[i].selected = true;
		}

	}
	/*
	 *
	 */
	function copyToListadd(from, to) {
		fromList = eval('document.forms[0].' + from);
		toList = eval('document.forms[0].' + to);
		if (toList.options.length > 0 && toList.options[0].value == 'temp') {
			toList.options.length = 0;
		}
		var sel = false;
		for (i = 0; i < fromList.options.length; i++) {
			var current = fromList.options[i];
			if (current.selected) {
				sel = true;
				if (current.value == 'temp') {
					alert('你不能选择这个项目!');
					return;
				}
				var number = form1.number.value;
				var difficulty = form1.difficulty.value;
				txt = "个数：" + number + " | 难度：" + difficulty + " |"
						+ current.text;
				val = current.value;
				toList.options[toList.length] = new Option(txt, val);
				fromList.options[i] = null;
				i--;
			}
		}
		if (!sel)
			alert('你还没有选择任何项目');
	}
	function allSelect() {
		List = document.forms[0].chosen;
		if (List.length && List.options[0].value == 'temp')
			return;
		for (i = 0; i < List.length; i++) {
			List.options[i].selected = true;
		}

	}
	/*
	 * 
	 */
	function copyToListdel(from, to) {
		fromList = eval('document.forms[0].' + from);
		toList = eval('document.forms[0].' + to);
		if (toList.options.length > 0 && toList.options[0].value == 'temp') {
			toList.options.length = 0;
		}
		var sel = false;
		for (i = 0; i < fromList.options.length; i++) {
			var current = fromList.options[i];
			if (current.selected) {
				sel = true;
				if (current.value == 'temp') {
					alert('你不能选择这个项目!');
					return;
				}
				var indexOfLastDiv = current.text.lastIndexOf("|");
				var length = current.text.length;
				txt = current.text.substr(indexOfLastDiv + 1, length
						- indexOfLastDiv);
				val = current.value;
				toList.options[toList.length] = new Option(txt, val);
				fromList.options[i] = null;
				i--;
			}
		}
		if (!sel)
			alert('你还没有选择任何项目');
	}
	function allSelect() {
		List = document.forms[0].chosen;
		if (List.length && List.options[0].value == 'temp')
			return;
		for (i = 0; i < List.length; i++) {
			List.options[i].selected = true;
		}

	}
	/* 
	 * 点击列表中的选项获取选项的值以及文字
	 */
	function over(id) {
		var select = document.getElementById(id);
		var vA = select.options[select.selectedIndex].value;
		var vB = select.options[select.selectedIndex].text;
		prompt();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<div id="act_top">
		<a href="。。/functionList/appManage.jsp">应用管理</a>&nbsp;&gt;&gt;&nbsp;组卷管理&nbsp;&gt;&gt;&nbsp;按章节自动组卷
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>

	<div id="act_content2">
		<form name="form1" method="post" action="">
			<table width="100%">
				<tr>
					<td>
						<table>
							<tr>
								<td>选择教学进度：</td>
							</tr>
							<tr>
								<td align="center"><select name="course1" size="10"
									multiple id="course1" style="width: 300px;">
										<!--  ondblclick="over(this.id);" -->
										<option value="sel1">间段1</option>
										<option value="sel2">间段2</option>
										<option value="sel3">间段3</option>
										<option value="sel4">间段4</option>
										<option value="sel5">间段5</option>
								</select></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td><a href="#" name="curseleft" id="curseleft"
									onClick="copyToList('course2','course1')"><<</a></td>
							</tr>
							<tr>
								<td><a href="#" name="curseright" id="curseright"
									onClick="copyToList('course1','course2')">&gt;&gt;</a></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td>已选择：</td>
							</tr>
							<tr>
								<td align="center" bgcolor="#FFFFFF"><select name="course2"
									size="10" multiple id="course2" style="width: 300px">
								</select></td>
							</tr>
						</table>
					</td>
				</tr>
		

				<tr>
					<td>
						<table>
							<tr>
								<td>选择知识点：</td>
							</tr>
							<tr>
								<td align="center"><select name="knowledge1" size="10"
									multiple id="knowledge1" style="width: 300px;">
										<option value="sel1">知识点1</option>
										<option value="sel2">知识点2</option>
										<option value="sel3">知识点3</option>
										<option value="sel4">知识点4</option>
										<option value="sel5">知识点5</option>
								</select></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td>个数:</td>
							</tr>
							<tr>
								<td><input name="number" type="text" size="1" value="1"></input>
								</td>
							</tr>
							<tr>
								<td>难度:</td>
							</tr>
							<tr>
								<td width="100px"><select name="difficulty">
										<option>简单</option>
										<option>中等</option>
										<option>困难</option>
								</select></td>
							</tr>
							<tr align="center">
								<td><a href="#" name="knowledgeleft" id="knowledgeleft" onClick="copyToListdel('knowledge2','knowledge1')"><<</a></td>
							</tr>
							<tr align="center">
								<td><a href="#" name="knowledgeright" id="knowledgeright" onClick="copyToListadd('knowledge1','knowledge2')">&gt;&gt;</a></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td>已选择：</td>
							</tr>
							<tr>
								<td align="center" bgcolor="#FFFFFF"><select
									name="knowledge2" size="10" multiple id="knowledge2"
									style="width: 300px">
								</select></td>
							</tr>
						</table>
					</td>
				</tr>

			</table>
			<table align="center" style="margin-top: 40px">
				<tr>
					<td><input type="button" value="保存" class="button" onclick="window.history.go(-1);"></input> <input
						type="button" value="重置" class="button"></input></td>
				</tr>
			</table>

		</form>
	</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>