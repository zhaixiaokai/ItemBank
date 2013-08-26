<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/webOffice/main.js"></script>
<script type="text/javascript">
function getDoc(){
	document.getElementById("WebOffice1").LoadOriginalFile("/DocGetAction?id=C8CB90754D264357B13B847A402FB0AA","doc");
}
</script>
</head>
<body>
	<table style="width: 100%">
		<tr>
			<td>
				<input type="button" value="下载" onclick="getDoc();"/>
			</td>
		</tr>
		<tr>
			<td>
				<script src="../js/webOffice/LoadWebOffice.js"></script>
			</td>
		</tr>
	</table>
</body>
</html>