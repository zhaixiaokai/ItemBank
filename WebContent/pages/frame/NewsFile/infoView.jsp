<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="../../css/MainFrame.css">
<link rel="stylesheet" type="text/css" href="../../css/table.css">
<style>
#act_divLine {
	margin-top: 5px;
	background: url(../../source/divLine.gif);
	width: 984px;
	margin-left: 20px
}
</style>
</head>

<body>
	<div id="act_top">
		<a href="../../functionList/HomePage.jsp">首页</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">web信息查看</a>
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
				<td width="60px">所属版面:</td>
				<td><select name="sel">
						<option selected="selected">--请选择--</option>
						<option>本站公告</option>
						<option>资料下载</option>
						<option>最新动态</option>
						<option>图片新闻</option>
				</select></td>
				<td width="100px"><input type="button" value="批量删除"
					class="button" /></td>
		</table>
		<table class="tableListNoWidth" width="100%" border="0"
			cellspacing="0" cellpadding="0">
			<tr class="tr1">
				<td><input name="checkAll" type="checkbox"></td>
				<td>编号</td>
				<td>信息名称</td>
				<td>所属版面</td>
				<td>发布时间</td>
				<td>信息内容</td>
				<td>操作</td>
			</tr>
			<tr>
				<td><input name="checkAll" type="checkbox"></td>
				<td>1</td>
				<td>北京邮电大学党委书记</td>
				<td>最新动态</td>
				<td>2009-02-21</td>
				<td>9月29日下午，王亚杰书...</td>
				<td><span><a href="#">删除&nbsp;</a>/<a href="#">&nbsp;修改</a></span></td>
			</tr>
			<tr>
				<td><input name="checkAll" type="checkbox"></td>
				<td>2</td>
				<td>北京邮电大学学术委员</td>
				<td>最新动态</td>
				<td>2009-02-21</td>
				<td>王亚杰书记仔细询问了各科研团...</td>
				<td><span><a href="#">删除&nbsp;</a>/<a href="#">&nbsp;修改</a></span></td>
			</tr>
			<tr>
				<td><input name="checkAll" type="checkbox"></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>2009-02-21</td>
				<td>&nbsp;</td>
				<td><span><a href="#">删除&nbsp;</a>/<a href="#">&nbsp;修改</a></span></td>
			</tr>
			<tr>
				<td><input name="checkAll" type="checkbox"></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><span><a href="#">删除&nbsp;</a>/<a href="#">&nbsp;修改</a></span></td>
			</tr>
			<tr>
				<td><input name="checkAll" type="checkbox"></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><span><a href="#">删除&nbsp;</a>/<a href="#">&nbsp;修改</a></span></td>
			</tr>
			<tr>
				<td><input name="checkAll" type="checkbox"></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><span><a href="#">删除&nbsp;</a>/<a href="#">&nbsp;修改</a></span></td>
			</tr>
			<tr>
				<td><input name="checkAll" type="checkbox"></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><span><a href="#">删除&nbsp;</a>/<a href="#">&nbsp;修改</a></span></td>
			</tr>
			<tr>
				<td><input name="checkAll" type="checkbox"></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><span><a href="#">删除&nbsp;</a>/<a href="#">&nbsp;修改</a></span></td>
			</tr>
			<tr>
				<td><input name="checkAll" type="checkbox"></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><span><a href="#">删除&nbsp;</a>/<a href="#">&nbsp;修改</a></span></td>
			</tr>
			<tr>
				<td><input name="checkAll" type="checkbox"></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td><span><a href="#">删除&nbsp;</a>/<a href="#">&nbsp;修改</a></span></td>
			</tr>
		</table>
		<table>
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
