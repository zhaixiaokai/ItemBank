<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>New</title>
<link href="../../css/MainFrame.css" rel="stylesheet" type="text/css" />
<!--<link rel="stylesheet" type="text/css" href="../../css/MainFrame.css">-->
<link rel="stylesheet" type="text/css" href="../../css/MainFrame.css">
<link rel="stylesheet" type="text/css" href="../../css/table.css">
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
</style>
</head>

<body>
	<div id="act_top">
		<a href="../../functionList/HomePage.jsp">首页</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">最新添加试题</a>&nbsp;&gt;&gt;&nbsp;正文
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td></td>
		</tr>
	</table>
	<div id="act_content_height">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="21px"><img src="../../examM/image/refer.gif"
					width="20" height="18" /></td>
				<td width="60px">课程名称:</td>
				<td><select name="sel">
						<option selected="selected">--请选择--</option>
						<option>高等数学（上）</option>
						<option>高等数学（下）</option>
						<option>大学物理（上）</option>
						<option>大学物理（下）</option>
						<option>线性代数</option>
						<option>通信原理</option>
						<option>微机原理与接口技术</option>
				</select> <input name="confirm" type="button" value="搜索" /></td>
				<td width="100px"><input type="button" value="批量下载"
					class="button" /></td>
		</table>

		<table class="tableListNoWidth">
			<tr class="tr1">
				<td><input name="checkAll" type="checkbox" value="" /></td>
				<td>序号</td>
				<td>试卷名称</td>
				<td>科目</td>
				<td>时间</td>
				<td>操作</td>
			</tr>
			<tr>
				<td><input name="check1" type="checkbox" value="" /></td>
				<td>1</td>
				<td>期末考试练习</td>
				<td>大学物理（上）</td>
				<td>2012-10-10</td>
				<td><a href="#">下载</a></td>
			</tr>
			<tr>
				<td><input name="check2" type="checkbox" value="" /></td>
				<td>2</td>
				<td>期末考试练习</td>
				<td>线性代数</td>
				<td>2012-10-10</td>
				<td><a href="#">下载</a></td>
			</tr>
			<tr>
				<td><input name="check3" type="checkbox" value="" /></td>
				<td>3</td>
				<td>期末考试练习</td>
				<td>大学物理（下）</td>
				<td>2012-10-10</td>
				<td><a href="#">下载</a></td>
			</tr>
			<tr>
				<td><input name="check4" type="checkbox" value="" /></td>
				<td>4</td>
				<td>期末考试练习</td>
				<td>随机过程</td>
				<td>2012-10-10</td>
				<td><a href="#">下载</a></td>
			</tr>
			<tr>
				<td><input name="check5" type="checkbox" value="" /></td>
				<td>5</td>
				<td>期末考试练习</td>
				<td>离散数学</td>
				<td>2012-10-10</td>
				<td><a href="#">下载</a></td>
			</tr>
			<tr>
				<td><input name="check6" type="checkbox" value="" /></td>
				<td>6</td>
				<td>期末考试练习</td>
				<td>现代交换原理</td>
				<td>2012-10-10</td>
				<td><a href="#">下载</a></td>
			</tr>
			<tr>
				<td><input name="check7" type="checkbox" value="" /></td>
				<td>7</td>
				<td>期末考试练习</td>
				<td>通信原理</td>
				<td>2012-10-10</td>
				<td><a href="#">下载</a></td>
			</tr>
			<tr>
				<td><input name="check8" type="checkbox" value="" /></td>
				<td>8</td>
				<td>期末考试练习</td>
				<td>现代数字通信</td>
				<td>2012-10-10</td>
				<td><a href="#">下载</a></td>
			</tr>
			<tr>
				<td><input name="check9" type="checkbox" value="" /></td>
				<td>9</td>
				<td>期末考试练习</td>
				<td>微机原理与接口技术</td>
				<td>2012-10-10</td>
				<td><a href="#">下载</a></td>
			</tr>
		</table>

		<table width="800px">
			<tr>
				<td><a href="#" class="page">&nbsp;1&nbsp;</a> <a href="#"
					class="page">&nbsp;2&nbsp;</a> <a href="#" class="page">&nbsp;3&nbsp;</a>
					<a href="#" class="page">&nbsp;4&nbsp;</a> <a href="#" class="page">&nbsp;5&nbsp;</a>
					<a href="#" class="pageturn">&nbsp;下一页 ></a>
					&nbsp;&nbsp;共50页&nbsp;&nbsp; &nbsp;&nbsp; <a href="#" class="page">首页</a>
					&nbsp;&nbsp; <a href="#" class="page">末页</a> &nbsp;&nbsp; 到第<input
					type="text" size="1">页 &nbsp;&nbsp; <input type="button"
					value="确认" onclick="certainPage"></td>
			</tr>
		</table>

	</div>

	<div class="bottom">版权所有©北京邮电大学 地址:北京市西土城路10号 邮编:100876 京ICP备
		05064445号 京公网安备110402430070</div>
</body>
</html>
