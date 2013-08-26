<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*"%>
<%@	page import="net.ib.util.service.impl.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
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
<style type="text/css">
</style>
    <script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.rotate.js"></script>
    <script type="text/javascript" src="../js/jquery.form1.js"></script>
<script>
function testSubmit(){
	alert(1);
	  var options = {
		   //   	target:     '#testFrame',
		        url:      "testjsp.jsp",
		        type:      "post",
		   //   dataType:  "json",
		        beforeSubmit:  showRequest, 
		        success:       showResponse
		    }; 
		 
		    $('#myForm').submit(function() {
		    	alert(2);
		        $(this).ajaxSubmit(options); 
		        return false; 
		    }); 
		    }; 
		 
		// pre-submit callback 
		function showRequest(formData, jqForm, options) { 
			alert(4);
		    var queryString = $.param(formData); 
		 
		    alert('About to submit: \n\n' + queryString); 
		    return true; 
		} 
		 
		function showResponse(responseText, statusText)  {
		    alert('status: ' + statusText + '\n\nresponseText: \n' + responseText + 
		        '\n\nThe output div should have already been updated with the responseText.'); 
}
</script>
</head>
<body>
	
	 <form id="myForm" method="post" >
<input type="text" id="name" name="inputname1"><br>
	 <input type="submit" value="submit" name="name1" onclick="testSubmit();">
	</form>
	

	
	
</body>
</html>