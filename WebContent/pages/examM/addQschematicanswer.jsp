
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="../css/table.css">

<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />


<link href="css/multimenu.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />


<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}
</style>
<script type="text/javascript">
</script>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<div style="margin-top: 10px">
		<table width="100%" class="table1">
			<tr>
				<th class="pagehead">在线添加试题</th>
			</tr>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0"
						style="width: 100%">
						<tr>
							<td align="left" style="font-size: 12px">&nbsp;&nbsp;当前试题库：学科&nbsp;>&nbsp;法学&nbsp;>&nbsp;030102法律史</td>
							<td align="right"><input type="button" name="select"
								value="更改试题类型" class="button" onclick="window.history.go(-1);" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%">
						<tr>
							<td>
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">添加试题</legend>
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">
										<!-- 				 	<tr>
					    <td nowrap align="right" width="100">试题标题:</td>
					    <td ><input name="text" class="text" style="width:150px" type="text" size="40" />
				        <span class="red"> *</span></td>
				    </tr> -->
										<tr>
											<td nowrap align="right">难度等级:</td>
											<td><select name="select" style="font-size: 12px">
													<option selected="selected">--请选择--</option>
													<option>简单</option>
													<option>一般</option>
													<option>中等</option>
													<option>困难</option>
											</select></td>
										</tr>
										<tr>
											<td nowrap align="right">知识点:</td>
											<td><select name="select2" style="font-size: 12px">
													<option selected="selected">--请选择--</option>
													<option>知识点a</option>
													<option>知识点b</option>
													<option>知识点c</option>
													<option>知识点d</option>
													<option>知识点f</option>
											</select></td>
										</tr>
										<tr>
											<td align="right" nowrap>题型:</td>
											<td><select name="select2" style="font-size: 12px"
												disabled>
													<option>--请选择--</option>
													<option>选择题</option>
													<option>填空题</option>
													<option>判断题</option>
													<option selected="selected">简答题</option>
											</select></td>
										</tr>
										<tr>
											<td nowrap align="right" height="10px">默认分值:</td>
											<td colspan="3"><input type="text" id="score" size="9"
												class="text"></td>
										</tr>
										<tr>
											<td nowrap align="right" height="80px">题干内容:</td>
											<td colspan="3"><textarea id="textarea" name="textarea"
													rows="4" cols="70"></textarea></td>
										</tr>
										<tr>
											<td nowrap align="right" height="80px">试题答案:</td>
											<td colspan="3"><textarea id="textarea" name="textarea"
													rows="4" cols="70"></textarea></td>
										</tr>
										<tr>
											<td nowrap align="right" height="30px">备注信息:</td>
											<td colspan="3"><textarea id="textarea" name="textarea"
													rows="4" cols="70"></textarea></td>
										</tr>
									</table>
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td align="center"><br> <input type="button" name="Submit"
					value="保存" class="button" onclick="save();" /> <input type="reset"
					name="Submit2" value="重置" class="button" /></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
orelat('howl');
</script>


	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>

