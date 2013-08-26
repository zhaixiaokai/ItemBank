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
<link rel="stylesheet" type="text/css" href="../css/PicAlt.css">
<script src="../js/jquery.min.js" type="text/javascript"></script> 
<script src="../js/PicAlt.js" type="text/javascript"></script>
<script src="../../servJs/md5.js" type="text/javascript"></script>  
<script src="../../servJs/login.js" type="text/javascript" charset="GBK"></script>  
    <script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<style type="text/css">
#container{margin:0 auto;width:824px;}
.button{
	border:1px solid #7373B9;
	padding:2px 2px 0px 2px;margin:4px;border-width:1px 3px 1px 3px;
	font-size: 12px;color:#ffffff;
	background:url(../source/bg_3.gif);}

</style>
<script type="text/javascript">

</script>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/login.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<jsp:include page="../functionList/home.jsp"></jsp:include>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>