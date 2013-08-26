
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

<jsp:include page="../frame/Frame1.jsp"></jsp:include>
<!-- 左侧界面 -->
<jsp:include page="../functionList/paperManageContent.jsp"></jsp:include>
<jsp:include page="../frame/Frame2.jsp"></jsp:include>
<!-- 右侧界面 -->

<div class="div_style2">
	<table width="100%" class="table1">
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<fieldset style="height: 100%;">
								<legend style="font-size: 12px">修改试题</legend>
								<table border="0" cellpadding="2" cellspacing="1"
									style="width: 80%; font-size: 12px">
									<!-- 				 	<tr>
					    <td nowrap align="right" width="100">试题标题:</td>
					    <td ><input name="text" class="text" style="width:150px" type="text" size="40" />
				        <span class="red"> *</span></td>
				    </tr> -->
									<tr>
										<td nowrap align="right">难度系数:</td>
										<td><input type="text" size="9" title="难度系数为0-1间的小数"
											class="text" value="0.33"></td>
									</tr>


									<tr>
										<td nowrap align="right" height="10px">默认分值:</td>
										<td colspan="3"><input type="text" id="score" size="9"
											class="text" value="5"></td>
									</tr>
									<tr>
										<td nowrap align="right" height="10px">预计时间:</td>
										<td colspan="3"><input type="text" id="score" size="9"
											class="text" value="30">&nbsp;秒</td>
									</tr>

									<tr>
										<td nowrap align="right">知识点:</td>
										<td><select name="select2" style="font-size: 12px">
												<option>--请选择--</option>
												<option selected="selected">知识点a</option>
												<option>知识点b</option>
												<option>知识点c</option>
												<option>知识点d</option>
												<option>知识点f</option>
										</select></td>
									</tr>
									<tr>
										<td nowrap align="right" height="80px">题干内容:</td>
										<td colspan="3"><textarea id="textarea" name="textarea"
												rows="4" cols="70">	"十进制计数器74LS160的功能表2-1所示,试分析图2-2所示电路的逻辑功能。(12分)

(1) 异步复位（2分），同步预置（2分）

(2) 计数到0110时输出低电平（2分），

持续时间为时钟高电平时长（2分）

(3) M=6（2分）
													"</textarea></td>
									</tr>
									<tr>
										<td nowrap align="right" height="80px">试题选项:</td>
										<td colspan="3"><textarea id="textarea" name="textarea"
												rows="4" cols="70">"A  0  B    1    c   5   d  7 "</textarea></td>
									</tr>
									<tr>
										<td nowrap align="right" height="80px">试题答案:</td>
										<td colspan="3"><textarea id="textarea" name="textarea"
												rows="4" cols="70">B</textarea></td>
									</tr>

									<tr>
										<td nowrap align="right" height="30px">备注信息:</td>
										<td colspan="3"><textarea id="textarea" name="textarea"
												rows="4" cols="70">无</textarea>
								</table>
							</fieldset>
						</td>
					</tr>
				</table>
			</td>
		</tr>

		<tr>
			<td align="center"><br> <input type="button" name="Submit"
				value="保存" class="button" /> <input type="button" name="Submit"
				value="返回" class="button" onclick="window.history.go(-1);" /></td>
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

