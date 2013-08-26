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
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<style type="text/css">

#container{margin:0 auto;width:824px;}
</style>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
<div id="act_top"><a href="../functionList/appManage.jsp">应用管理</a>&nbsp;&gt;&gt;&nbsp;课程教材管理&nbsp;&gt;&gt;&nbsp;<a target="mainFrame" href="ChapterManage.jsp">章节管理</a></div>
    <table id="act_divLine" height="4px" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
        	<td style="border:0"></td>
        </tr>
    </table>
    <div id="act_content2">
  <table class="tableList">
      <tr class="tr1">
        <td>教材编号</td>
        <td>教材名称</td>
        <td>教材版本</td>
        <td>教材出版社</td>
        <td>操作</td>
      </tr>
    <tr>
      <td>1</td>
      <td>大学英语听说教程</td>
      <td>第一版</td>
      <td>外语教学与研究出版社</td>
      <td><a href="ChapterManageTree.jsp">管理</a></td>
    </tr>
    <tr>
      <td>2</td>
      <td>大学英语写译教程</td>
      <td>第一版</td>
      <td>外语教学与研究出版社</td>
      <td><a href="ChapterManageTree.jsp">管理</a></td>
    </tr>
    <tr>
      <td>3</td>
      <td>大学英语听说教程</td>
      <td>第二版</td>
      <td>外语教学与研究出版社</td>
      <td><a href="ChapterManageTree.jsp">管理</a></td>
    </tr>
    <tr>
      <td>4</td>
      <td>大学英语写译教程</td>
      <td>第二版</td>
      <td>外语教学与研究出版社</td>
      <td><a href="ChapterManageTree.jsp">管理</a></td>
    </tr>
  </table>
  
  
</div>	
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>