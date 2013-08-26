<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*,java.io.*"%>
<html>
<head>
<script type="text/javascript" >
function saveDoc(id,docType) {
	try{
		var webObj=document.getElementById("WebOffice1");
		var returnValue;
		webObj.HttpInit();			//初始化Http引擎
		// 添加相应的Post元素 
		webObj.HttpAddPostCurrFile("DocContent","");		// 上传文件
		returnValue = webObj.HttpPost("/XiaokaiAction");	// 判断上传是否成功
		if("succeed" == returnValue){
			alert("文件上传成功");	
		}else if("failed" == returnValue){
			alert("文件上传失败");
		}
	}catch(e){
		alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
	}
}
</script>

</head>
<body>
	<table style="width: 100%">
		<tr>
			<td>
				<input type="button" value="上传" onclick="saveDoc();"/>
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