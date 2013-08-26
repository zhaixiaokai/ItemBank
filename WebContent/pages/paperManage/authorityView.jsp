<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
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
<style>
#act_divLine{margin-top:5px; background:url(../source/divLine.gif);width:784px; margin-left:20px;}
</style>
<script>
function certainPage(){
	self.parent.frames["mainFrame"].location="certainPage.jsp";
}
</script>
<title>Insert title here</title>
</head>
<body><jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperDbManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
    <div id="act_top"><a href="#">试题管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">权限查看</a>
    <table id="divLine" height="4px" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td style="border:0"></td>
      </tr>
    </table>
	<div class="div_style2">
		<table  align="left" style="font-size:12px" width="700px" height=50px>
			<tr >
				<td width="20" style="border:0"><img src="image/refer.gif" width="20" height="18" /></td>
				<td width="50" style="border:0">查询条件：</td>
				<td width="100" style="border:0">姓名：<input type="text" id="subject" size=10></td>
				<td width="100" style="border:0">学号：<input type="text" id="major" size=10></td>
				<td width="100" style="border:0">教工号：<input type="text" id="course" size=10></td>
				<td width="20" style="border:0"><input type=button id="button" name="button" value="查询" style="background:url(image/trbg.gif)"></td>
			</tr>		
		</table>
    <table width="100%" class="tableList" align="center" style="font-size:12px">
		<tr>
			<td style="border:0">
	  			<table class="table1" width="100%">
	  				<tr class="tr1">
	  					<td>角色序号</td>
	  					<td>角色姓名</td>
	  					<td>角色资料</td>
	  					<td>角色权限查看</td>
	  				</tr>
	  				<tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr>
	  				<tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr><tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr><tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr><tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr><tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr><tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr><tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr><tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr><tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr><tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr><tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr><tr>
	  					<td> 1</td>
	  					<td> 角色a</td>
	  					<td>  <a href="#">资料详细</a></td>
	  					<td> <a href="#">权限查看</a></td>
	  				</tr>
	  			</table>
	  		</td>  
	  	</tr>
	  	<tr ><td style="border:0">
	  		<table>
	  			<tr><td style="border:0" >
						<a href="qView1.jsp" class="page">&nbsp;1&nbsp;</a>
						<a href="qView2.jsp" class="page">&nbsp;2&nbsp;</a>
						<a href="qView3.jsp" class="page">&nbsp;3&nbsp;</a>
						<a href="#" class="page">&nbsp;4&nbsp;</a>
						<a href="#" class="page">&nbsp;5&nbsp;</a>
						<a href="qView2.jsp"class="pageturn" style="font-size:12px" >&nbsp;下一页 ></a>
						&nbsp;&nbsp;共50页&nbsp;&nbsp;
						&nbsp;&nbsp;
						<a href="qView1.jsp" class="page">首页</a>
						&nbsp;&nbsp;
						<a href="qView3.jsp" class="page">末页</a>
						&nbsp;&nbsp;
						到第<input type="text" size="1">页
						&nbsp;&nbsp;
						<input type="button" value="确认" onclick="certainPage" style="font-size:12px;">
	  			</td></tr>
	  		</table>
	  	</td></tr>
	</table>
    </div>
    <!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>