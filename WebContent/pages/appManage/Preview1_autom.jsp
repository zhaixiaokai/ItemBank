<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css" href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link href="../css/infoedit.css" rel="stylesheet" type="text/css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link href="/css/multiMenu.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../js/checkAll.js" type="text/javascript"></script>

<style type="text/css">

#container{margin:0 auto;width:824px;}
#act_divLine{margin-top:5px; background:url(../source/divLine.gif);width:784px; margin-left:20px}

</style>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top"><a href="../../functionList/HomePage.html">应用管理&gt;&gt;</a><a href="../../functionList/sysManage.html">组卷管理</a>&nbsp;&gt;&gt;&nbsp;按章节自动组卷&gt;&gt;&nbsp;试卷预览</div>
    <table id="act_divLine" height="4px" width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td style="border:0"></td>
      </tr>
    </table>
<div id="act_content2">

<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tableList">

  <tr align="center" class="tr1">
    <td width="50px"><input type="checkbox" name="checkAll" id="checkbox" onClick="check_all(this,'checkbox')"/> 全选</td>
    <td>试题序号</td>
    <td>试题编号</td>
    <td width="200px">试题信息</td>
    <td>试题答案</td>
    <td>试题录入时间</td>
     <td>出题人</td>
     <td>试题分值</td>
     <td>所属知识点</td>
      <td>操作</td>
  </tr>
  <tr align="center">
    <td height="30"><form id="form1" name="form1" method="post" action="">
      <input type="checkbox" name="checkbox" id="checkbox" />
      <label for="checkbox"></label>
    </form></td>
    <td>1</td>
    <td>080001</td>
    <td><br>
        <p>解释数据模型的概念，为什么要将数据模型分成两个层次？</p>
      <br></td>
    <td><a href="#">预览</a></td>
 		<td>2007-12</td>
		<td>xx</td>
		<td>2</td>
    
    <td>知识点a</td>
     <td><a href="DataEdit.jsp">删除</a></td>
  </tr>

  <tr align="center">
    <td><input type="checkbox" name="checkbox" id="checkbox2" /></td>
    <td>2</td>
    <td>080002</td>
    <td><br>
        <p>概念层数据模型和组织层数据模型分别是针对什么进行抽象的？</p>
      <br></td>
    <td><a href="#">预览</a></td>
   <td>2007-12</td>
							<td>xx</td>
							<td>2</td>
    <td>知识点b</td>
  <td>删除</td>
  </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox" id="checkbox3" /></td>
    <td>3</td>
    <td>080003</td>
    <td><br>
        <p>实体之间的联系有哪几种？</p>
      <br></td>
    <td><a href="#">预览</a></td>
  <td>2007-12</td>
							<td>xx</td>
							<td>2</td>
    <td>知识点c</td>
    <td><a href="DataEdit.jsp">删除</a></td>
  </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox" id="checkbox5" /></td>
    <td>4</td>
    <td>080004</td>
    <td><br>
        <p>数据库管理系统提供的两级映像是什么？</p>
      <br></td>
    <td><a href="#">预览</a></td>
   <td>2007-12</td>
							<td>xx</td>
							<td>2</td>
    <td>知识点d</td>
    <td><a href="DataEdit.jsp">删除</a></td>
  </tr>
  <tr align="center">
    <td><input type="checkbox" name="checkbox" id="checkbox4" /></td>
    <td>5</td>
    <td>080005</td>
    <td><br>
        <p>数据库系统包含哪三级模式？</p>
      <br></td>
    <td><a href="#">预览</a></td>
    <td>2007-12</td>
							<td>xx</td>
							<td>2</td>
    <td>知识点e</td>
    <td><a href="DataEdit.jsp">删除</a></td>
  </tr>
</table>
    <p>&nbsp;</p>
</div>
<div style="text-align:center">
  <input type="button" name="Submit" value="保存" class="button"/>
  
  </div> 
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>