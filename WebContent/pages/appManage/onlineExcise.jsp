<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>

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


</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<table width="100%" class="CContent">
		<tr>
			<th class="pagehead">学生自主组卷</th>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<fieldset style="height: 100%;">
								<legend style="font-size: 12px">参数设置</legend>
								<table border="0" cellpadding="0" cellspacing="0"
									style="width: 100%; font-size: 12px">
							
									
									<tr>
										<td  height="50px" >学院专业:</td>
										<td colspan="3"><jsp:include page="../document/basicsortSelect.jsp"></jsp:include></td>
										<td>课程:</td>
										<td colspan="3"><jsp:include page="../document/subjectSelect.jsp"></jsp:include></td>
										
									</tr>
									<tr>
										<td  height="50px">试卷难度:</td>
										<td colspan="3"><form id="form1" name="form1"
												method="post" action="">
												<label> <select name="select2">
														<option selected="selected">--请选择--</option>
														<option>容易</option>
														<option>中等</option>
														<option>困难</option>
												</select>
												</label>
											</form></td>
										<td height="50px">考试时长:</td>
										<td colspan="3"><input name="textfield" type="text"
											id="textfield" /> （分钟）</td>
											<td colspan="3"></td>
										
									</tr>

									</table>
									<table width="100%">
									<tr>
										<td nowrap align="left" height="70px">考试章节:</td>
										<td height="70px">
										<table border="0" width="100%" class="tableListleft">
										<tr class="tr1"><td align="left" height="30px" width="50px"><input type="checkbox" name="checkbox" id="checkbox" />全选</td><td style="text-align: center"> 章节列表</td></tr>
										<tr><td style="text-align: center"><input type="checkbox" name="checkbox" id="checkbox" /></td><td> 第一章、算法分析</td></tr>
										<tr><td style="text-align: center"><input type="checkbox" name="checkbox" id="checkbox" /></td><td> 第二章、表、栈、队列</td></tr>
										<tr><td style="text-align: center"><input type="checkbox" name="checkbox" id="checkbox" /></td><td> 第三章、树</td></tr>
										<tr><td style="text-align: center"><input type="checkbox" name="checkbox" id="checkbox" /></td><td> 第四章、散列</td></tr>
										<tr><td style="text-align: center"><input type="checkbox" name="checkbox" id="checkbox" /></td><td> 第五章、排序</td></tr>
										</table>
										</td>
									</tr>
									
									<tr>
										<td nowrap align="left" height="70px">题型分布:</td>
										<td>
										<table border="0" width="100%">
										<tr><td>选择题<input
												name="textfield222" type="text" size="5" />（个）
												判断题<input name="textfield22"
												type="text" size="5" />（个）
												填空题<input name="textfield23"
												type="text" size="5" />（个）
												简答题<input name="textfield24"
												type="text" size="5" />（个）</td></tr>
										</table>
										
											</td>
									</tr>

								
									
								</table>
							</fieldset>
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<tr>
			<td align="center">
				
					<input type="button" name="Submit" value="保存"
						class="button" onclick="save();" /> <input type="button"
						name="Submit2" value="返回" class="button"
						onClick="window.history.go(-1);" /> &nbsp;
				
				
			</td>
		</tr>
	</table>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>

</body>
</html>