<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css">

<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/select.css">
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<style>
#act_divLine {
	margin-top: 5px;
	background: url(../source/divLine.gif);
	width: 784px;
	margin-left: 20px;
}
</style>
<script>
	function certainPage() {
		self.parent.frames["mainFrame"].location = "certainPage.jsp";
	}
	function check_all(obj, cName) {
		var checkboxs = document.getElementsByName(cName);
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = obj.checked;
		}
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="javascript:void(0)">试题管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">查看试题</a>&nbsp;&gt;&gt;&nbsp;查看试题
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div class="div_style2">
		<table width="700px" height=50px>
			<tr>
				<td width="20"><img src="image/refer.gif" width="20"
					height="18" /></td>
				<td width="" nowrap>试题库：</td>
				<td>
					<!-- 引入外部文件，选择试题库 --> <jsp:include page="../document/selectEs.jsp"></jsp:include>
				</td>
				<td width="" nowrap>知识点：</td>
				<%-- <td>
					<!-- 引入外部文件，选择试题库 --> <jsp:include page="../frame/selectPoint.jsp"></jsp:include></td> --%>
				<td><input type="text" style="width: 50px"></td>
				<td><input type="button" size="1" value="选择"></td>
				<td width="" nowrap>题型：</td>
				<td><select name="select" style="font-size: 12px">
						<option selected="selected">---请选择---</option>
						<option>选择题</option>
						<option>填空题</option>
						<option>判断题</option>
						<option>简答题</option>
				</select></td>
				<td width="" nowrap>难度：</td>
				<td><select name="select" style="font-size: 12px">
						<option selected="selected">---请选择---</option>
						<option>简单</option>
						<option>中等</option>
						<option>困难</option>
				</select></td>
				<td width=""><input type=button id="button" value="查询"
					class="button"></td>
				<td width=""><input type=button id="button" value="批量导出"
					class="button"></td>
			</tr>
		</table>
		<table width="100%" class="tableList">
			<tr>
				<td style="border: 0">
					<table width="100%" align="center">
						<tr class="tr1">
							<td><input type="checkbox" id="chks"
								onclick="check_all(this,'chks')">全选</td>
							<td>试题序号</td>

							<td>试题内容</td>
							<td>知识点</td>
							<td>难度等级</td>
							<td>题型</td>
							<td>所属试题库</td>
							<td>导出试题</td>
						</tr>
						<tr>
							<td><input type="checkbox" name=chks></td>
							<td>33</td>

							<td><a href="javascript:void(0)" class="detail"
								title="题目一，题干：“........”,答案：“.......”">详细</a></td>
							<td>知识点b</td>
							<td>简单</td>
							<td>判断题</td>
							<td>电子电路</td>
							<td><a href="javascript:void(0)" class="detail">导出</a></td>
						</tr>
						<tr>
							<td><input type="checkbox" name=chks></td>
							<td>34</td>

							<td><a href="javascript:void(0)" class="detail"
								title="题目一，题干：“........”,答案：“.......”">详细</a></td>
							<td>知识点b</td>
							<td>简单</td>
							<td>判断题</td>
							<td>数学</td>
							<td><a href="javascript:void(0)" class="detail">导出</a></td>
						</tr>
						<tr>
							<td><input type="checkbox" name=chks></td>
							<td>35</td>

							<td><a href="javascript:void(0)" class="detail"
								title="题目一，题干：“........”,答案：“.......”">详细</a></td>
							<td>知识点b</td>
							<td>简单</td>
							<td>判断题</td>
							<td>语文</td>
							<td><a href="javascript:void(0)" class="detail">导出</a></td>
						</tr>
						<tr>
							<td><input type="checkbox" name=chks></td>
							<td>36</td>

							<td><a href="javascript:void(0)" class="detail"
								title="题目一，题干：“........”,答案：“.......”">详细</a></td>
							<td>知识点b</td>
							<td>简单</td>
							<td>判断题</td>
							<td>英语</td>
							<td><a href="javascript:void(0)" class="detail">导出</a></td>
						</tr>
						<tr>
							<td><input type="checkbox" name=chks></td>
							<td>37</td>

							<td><a href="javascript:void(0)" class="detail"
								title="题目一，题干：“........”,答案：“.......”">详细</a></td>
							<td>知识点b</td>
							<td>简单</td>
							<td>判断题</td>
							<td>英语</td>
							<td><a href="javascript:void(0)" class="detail">导出</a></td>
						</tr>
						<tr>
							<td><input type="checkbox" name=chks></td>
							<td>38</td>

							<td><a href="javascript:void(0)" class="detail"
								title="题目一，题干：“........”,答案：“.......”">详细</a></td>
							<td>知识点b</td>
							<td>简单</td>
							<td>判断题</td>
							<td>数学</td>
							<td><a href="javascript:void(0)" class="detail">导出</a></td>
						</tr>


					</table>

				</td>
			</tr>
			<tr>
				<td style="border: 0;">
					<table>
						<tr>
							<td style="font-size: 12px; border: 0;"><a
								href="outputQ1.jsp" class="page">首页</a> &nbsp;&nbsp; <a
								href="outputQ2.jsp" class="pageturn" style="font-size: 12px">&nbsp;<
									上一页</a> <a href="outputQ1.jsp" class="page">&nbsp;1&nbsp;</a> <a
								href="outputQ2.jsp" class="page">&nbsp;2&nbsp;</a> <a
								href="outputQ3.jsp" style="color: red; text-decoration: none;">&nbsp;3&nbsp;</a>
								<a href="javascript:void(0)" class="page">&nbsp;4&nbsp;</a> <a
								href="javascript:void(0)" class="page">&nbsp;5&nbsp;</a> <a
								href="javascript:void(0)" class="pageturn"
								style="font-size: 12px">&nbsp;下一页 ></a> &nbsp;&nbsp; <a
								href="outputQ3.jsp" class="page">末页</a>
								&nbsp;&nbsp;共50页&nbsp;&nbsp; &nbsp;&nbsp; 到第<input type="text"
								size="1" style="">页 &nbsp;&nbsp; <input type="button"
								value="确认" onclick="certainPage" class="button1"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>