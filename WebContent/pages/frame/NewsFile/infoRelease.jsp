<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../../css/MainFrame.css" rel="stylesheet" type="text/css" />
<link href="../../css/tableStyle.css" rel="stylesheet" type="text/css" />
<style>
#act_divLine {
	margin-top: 5px;
	background: url(../../source/divLine.gif);
	width: 984px;
	margin-left: 20px
}

#piliang {
	margin-right: 40px;
	text-align: left
}

#Attachment {
	background: #FFF;
}
</style>
</head>

<body>

	<div id="act_top">
		<a href="../../functionList/HomePage.jsp">首页</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">web信息发布</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td></td>
		</tr>
	</table>
	<div id="act_content_height">
		<fieldset>
			<legend>信息发布</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-left: 20px; margin-right: 20px">
				<tr height="30px">
					<td width="60px">发布版面：</td>
					<td><select name="Forums">
							<option selected="selected">--请选择--</option>
							<option>本站公告</option>
							<option>资料下载</option>
							<option>产品信息</option>
							<option>图片新闻</option>
							<option>最新动态</option>
					</select></td>
				</tr>
				<tr height="30px">
					<td width="60px">标题：</td>
					<td><input name="title" type="text" /></td>
				</tr>
				<tr height="30px">
					<td width="60px">新闻题目：</td>
					<td><input name="title" type="text" /></td>
				</tr>
				<tr height="30px">
					<td width="60px">内容：</td>
					<td><textarea name="content" cols="80%" rows="10"></textarea>
					</td>
				</tr>

				<tr height="30px">
					<td width="60px">附件：</td>
					<td><input name="Attachment" type="file" id="Attachment" /></td>
				</tr>
			</table>
		</fieldset>

		<div align="center" style="margin-top: 100px;">
			<input type="button" name="Submit" value="保存" class="button"
				onclick="save();" /> <input type="button" name="Submit2" value="返回"
				class="button" onclick="window.history.go(-1);" />
		</div>
	</div>
	<div class="bottom">版权所有©北京邮电大学 地址:北京市西土城路10号 邮编:100876 京ICP备
		05064445号 京公网安备110402430070</div>
</body>
</html>
