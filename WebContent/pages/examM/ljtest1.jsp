<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script>
function showframe(){
var ss='/exam/system/aa/bb/cc';
var bb='/exam';
var cc='/am';
alert(ss.indexOf(bb));
alert(ss.indexOf(cc));


}
</script>
</head>
<body>
<p>ABCD</p>
<div id="ques_content" style="width:300px;height:400px;display:none">
<iframe id="myframe" src=""> </iframe>
</div>
<input type=button value="submit" onclick="showframe();">

<script>

</script>

</body>
</html>