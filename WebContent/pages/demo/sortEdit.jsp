<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css"/>
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css"/>
<style>
</style>
<script>
function certainPage(){
	self.parent.frames["mainFrame"].location="certainPage.jsp";
}
</script>
<title>Insert title here</title>
</head>
<body>
	<div id="act_top"><a href="#">试题库分类管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">修改分类体系</a></div>
    	<table id="divLine" height="4px" width="100%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
        		<td style="border:0"></td>
      		</tr>
    	</table>
    <div class="div_style2">
	<table>
		<tr>
			<td style="border:0;height:50px" >
	  			<table width="50%" align="left" style="font-size:12px">
	  				<tr >
						<td width="20" ><img src="image/refer.gif" width="20" height="18" /></td>
						<td width="150" >请输入分类体系关键字：<input type="text" id="keyword" name="keyword" size=10></td>
						<td width="10" ><input type=button id="button" name="button" value="查询" class="button1"></td>
					</tr>		
	  			</table>
	  		</td>
		</tr>
		<tr>
			<td style="border:0">
	  			<table cellpadding="7" cellspacing="1"   width="100%" class="tablelist" align=center>
	  				<tr class="tr1">
	  					<td>分类体系序号</td>
	  					<td>体系名称</td>
	  					<td>分类规则</td>
	  					<td>否否为默认分类体系</td>
	  					<td>修改分类体系</td>
	  				</tr>
	  				<tr>
	  					<td>1</td>
	  					<td>学科分类</td>
	  					<td><a href="#">按照学科专业课程分类</a></td>
	  					<td>是</td>
	  					<td><a href="#">修改</a></td>
	  				</tr>
	  				<tr>
	  					<td>2</td>
	  					<td>难度分类</td>
	  					<td><a href="#">按照题目难易程度分类</a></td>
	  					<td>否</td>
	  					<td><a href="#">修改</a></td>
	  				</tr><tr>
	  					<td>3</td>
	  					<td>知识点分类</td>
	  					<td><a href="#">按照知识点分类</a></td>
	  					<td>否</td>
	  					<td><a href="#">修改</a></td>
	  				</tr>
	  				<tr>
	  					<td>4</td>
	  					<td>按老师分类</td>
	  					<td><a href="#">按照老师分类</a></td>
	  					<td>否</td>
	  					<td><a href="#">修改</a></td>
	  				</tr>
	  				<tr>
	  					<td>5</td>
	  					<td>按近期考试分类</td>
	  					<td><a href="#">按照近期考试分类</a></td>
	  					<td>否</td>
	  					<td ><a href="#">修改</a></td>
	  				</tr><tr>
	  					<td>6</td>
	  					<td>按学期分类</td>
	  					<td><a href="#">按照学期分类</a></td>
	  					<td>否</td>
	  					<td><a href="#">修改</a></td>
	  				</tr>
	  			</table>
	  		</td>  
	  	</tr>
	  	<tr><td>
	  		<table>
	  			<tr><td style="font-size:12px">
						<a href="#" class="page">&nbsp;1&nbsp;</a>
						<a href="#" class="page">&nbsp;2&nbsp;</a>
						<a href="#" class="page">&nbsp;3&nbsp;</a>
						<a href="#" class="page">&nbsp;4&nbsp;</a>
						<a href="#" class="page">&nbsp;5&nbsp;</a>
						<a href="#"class="pageturn" style="font-size:12px" >&nbsp;下一页 ></a>
						&nbsp;&nbsp;共50页&nbsp;&nbsp;
						&nbsp;&nbsp;
						<a href="#" class="page">首页</a>
						&nbsp;&nbsp;
						<a href="#" class="page">末页</a>
						&nbsp;&nbsp;
						到第<input type="text" size="1">页
						&nbsp;&nbsp;
						<input type="button" value="确认" onclick="certainPage"  class="button1">
	  			</td></tr>
	  		</table>
	  	</td></tr>
	</table>
	</div>
</body>
</html>