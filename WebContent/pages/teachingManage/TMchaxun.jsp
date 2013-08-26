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
<div id="act_top"><a href="../functionList/appManage.jsp">应用管理</a>&nbsp;&gt;&gt;&nbsp;课程教材管理&nbsp;&gt;&gt;&nbsp;<a target="mainFrame" href="TMInquiry.jsp">教材查询</a></div>
    <table id="act_divLine" height="4px" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
        	<td style="border:0"></td>
        </tr>
    </table>
<div id="act_content">
  <table class="tableList">
      <tr class="tr1">
        <td>章目录</td>
        <td>节目录</td>
        <td>知识点信息</td>
        <td>备注说明</td>
        </tr>
    <tr>
      <td rowspan="2">第一章</td>
      <td>第1节</td>
      <td>a.知识点a<br>
        b.知识点b<br>
        c.知识点c</td>
      <td>&nbsp;</td>
      </tr>
    <tr>
      <td>第2节</td>
      <td>a.知识点e<br>
        b.知识点f<br>
        c.知识点g</td>
      <td>&nbsp;</td>
      </tr>
    <tr>
      <td rowspan="3">第二章</td>
      <td>第1节</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>第2节</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
    <tr>
      <td>第3节</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
	  <tr>
      <td rowspan="2">第三章</td>
      <td>第1节</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
    <tr>
      <td>第2节</td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      </tr>
  </table>
  
  
</div>	

	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>