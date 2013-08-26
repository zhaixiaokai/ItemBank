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
<script>
	function selectdepart(){
		self.parent.frames["mainFrame"].location="authorityManage.jsp";
	}
</script>
<title>Insert title here</title>
</head>
<body><jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperDbManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top"><a href="#">试卷库权限管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">权限管理</a></div>
    	<table id="divLine" height="4px" width="100%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
        		<td style="border:0"></td>
      		</tr>
    	</table>
    <div class="div_style2">
    	<table style="font-size:12px" class="tableheight">
    		<tr>
    			<td>组织机构：
    				<select name="select" style="font-size: 12px">
						<option selected="selected">&nbsp;&nbsp;&nbsp;----请选择----&nbsp;&nbsp;&nbsp;</option>
						<option onclick="basicsort();">信通院</option>
						<option>电子院</option>
						<option>国际学院</option>
						<option>理学院</option>
						<option>人文学院</option></select>
    			</td>
    			<td>角色：
    				<select name="select" style="font-size: 12px">
						<option selected="selected">&nbsp;&nbsp;&nbsp;----请选择----&nbsp;&nbsp;&nbsp;</option>
						<option onclick="basicsort();">系统管理员</option>
						<option>试题编制人员</option>
						<option>题库使用人员</option>
						<option>试卷库管理员</option>
						<option>试题管理员</option></select>
    			</td>
    			<td>分类体系：
    				<select name="select" style="font-size: 12px">
						<option selected="selected">&nbsp;&nbsp;&nbsp;----请选择----&nbsp;&nbsp;&nbsp;</option>
						<option onclick="basicsort();">基本分类体系</option>
						<option>分类体系1</option>
						<option>分类体系2</option>
						<option>分类体系3</option>
						<option>分类体系4</option></select>
    			</td>
    			<td><input type="button" value="确认" class="button" onclick="selectdepart();"></td>
    		</tr>
    	</table>
    	
    	
    </div>
    
<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>