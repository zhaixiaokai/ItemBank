<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
 	<head>
 		<script type="text/javascript" src="../js/jquery-1.8.0.js"></script>
		<script type="text/javascript" src="../js/jquery.rotate.js"></script>
		<script type="text/javascript" src="../js/jquery.form.js"></script>
 		<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
 		<script type="text/javascript" src="../../servJs/md5.js"></script>
 		<script type="text/javascript">
 			$(document).ready(function(){
 				$("#saveBtn").bind("click",function(){
 					if($("#opsw").val()==""){
 						$("#opswInfo").html("原始密码不能为空！");
 						return;
 					}else if($("#opsw").val().length>16||$("#opsw").val().length<6||!CheckIfIsLetterNumber($("#opsw").val())){
 						$("#opswInfo").html("请输入6-16位字母、数字");
 						return;
 					}else{
 						$("#opswInfo").html("");
 					}
 					if($("#npsw1").val()==""){
 						$("#npsw1Info").html("新密码不能为空！");
 						return;
 					}else if($("#npsw1").val().length>16||$("#npsw1").val().length<6||!CheckIfIsLetterNumber($("#npsw1").val())){
 						$("#npsw1Info").html("请输入6-16位字母、数字");
 						return;
 					}else{
 						$("#npsw1Info").html("");
 					}
 					
 					if($("#npsw2").val()!=$("#npsw1").val()){
 						$("#npsw2Info").html("两次密码输入不一致！");
 						return;
 					}else if($("#npsw2").val().length>16||$("#npsw2").val().length<6||!CheckIfIsLetterNumber($("#npsw2").val())){
 						$("#npsw1Info").html("请输入6-16位字母、数字");
 						return;
 					}else{
 						$("#npsw2Info").html("");
 					}
 					
 					$("#md5OldPsw").val(MD5($("#opsw").val()));
 					$("#md5Psw").val(MD5($("#npsw1").val()));
 					var options = {
 							type : "post",
 							dataType : "json",
 							success : function(result){
 								alert(result.desc);
 							}
 						};
 						$('#pswFrom').ajaxSubmit(options);
 				});
 			});
 		</script>
 		
 	</head>
 	<body>
 	<form action="updatePswAction" id="pswFrom" method="post">
 		<table>
 			<tr>
 				<td>原始密码：</td>
 				<td><input type="password" id="opsw" name="opsw" value=""></td>
 				<td><span style="color: red;" id="opswInfo"></span></td>
 			</tr>
 			<tr>
 				<td>新密码：</td>
 				<td><input type="password" id="npsw1" name="npsw1" value=""></td>
 				<td><span style="color: red;" id="npsw1Info"></span></td>
 			</tr>
 			<tr>
 				<td>确认新密码：</td>
 				<td><input type="password" id="npsw2" name="npsw2" value=""></td>
 				<td><span style="color: red;" id="npsw2Info"></span></td>
 			</tr>
 			<tr>
 				<td colspan="2" align="center">
 					<input type="button" id="saveBtn" value="保存">
 				</td>
 			</tr>
 		</table>
 	<input type="hidden" value="" id="md5Psw" name="md5Psw">
 	<input type="hidden" value="" id="md5OldPsw" name="md5OldPsw">
 	</form>
 	</body>
 </html>