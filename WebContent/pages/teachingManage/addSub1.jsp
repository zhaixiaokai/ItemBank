<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="net.ib.util.dao.*,java.util.*"%>
    <%@	page import="net.ib.util.service.impl.*"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link rel="stylesheet" type="text/css" href="../css/select.css">
<link href="../css/infoedit.css" rel="stylesheet" type="text/css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../js/formReset.js" type="text/javascript"></script>

<style type="text/css">
</style>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script src="../js/formReset.js" type="text/javascript"></script>
<!--<style type="text/css">

#container{margin:0 auto;width:824px;}

</style>
  -->
<script type="text/javascript">
function save()
{
	var ID =document.getElementById("CurseID1").value;
	var name=document.getElementById("CurseName1").value;
	var credit=document.getElementById("CurseCredit1").value;
	if(name=="")
	{
		alert("课程名称为必填项，请重新输入！！ ");
		return false;
	}
	else
	{
		if(ID=="")
		{
			alert("课程编号为必填项，请重新输入 ！！");
			return false;
		}
		else
		{
			if(credit=="")
			{
				alert("课程学分为必填项，请重新输入！！ ");
				return false;
			}
			else
			{
				var truthBeTold=window.confirm("确认添加新的课程？");
				if(truthBeTold)
				{
					//myForm1.submit();
					$(function() {
						var options = {
							type : "post",
							dataType : "json",
							beforeSubmit : showStart,
							success : showSuccess,
						};
						$('#myForm1').ajaxSubmit(options);
						return false;
					});
				}
			}
		}
	}
}

function showStart() {
	return true;
}
function showSuccess(responseText, statusText) {
	if(responseText!=null&&responseText!=""){
		alert(responseText.result);
	}else{
	alert("课程添加成功！！");
	document.location.href="addSub1.jsp";
	}
	

}

</script>
</head>

<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	
	<form id="myForm1" action="CourseAddAction">
	<table width="100%" class="CContent" >
	<tr>
		<th class="pagehead"  >添加课程</th>
	</tr>
	<tr><td width="100%">
		<table width="100%">  	
  			<tr><td>
				<fieldset style="height:100%;">
				<legend style="font-size:12px">添加课程</legend>
				 <table border="0" cellpadding="2" cellspacing="1" style="width:80%;font-size:12px">
			
				      <tr>
				    <td nowrap align="left">学院专业：
				    </td>
				    <td> 
				<jsp:include page="SchoolTree.jsp"></jsp:include>
				<input type="hidden" id="Specials1" name="Specials" value=""/>


			</td></tr>
				
					
					  <tr>
					    <td nowrap align="left" height="30px">课程编号:</td>
					    <td colspan="3"><input type="text" name="CurseID" id="CurseID1" /><span class="red"> *</span></td>
					    </tr>
					    <tr>
					    <td nowrap align="left" height="30px">课程名称:</td>
					    <td colspan="3"><input type="text" name="CurseName" id="CurseName1" /><span class="red"> *</span></td>
					    </tr>
					    <tr>
						<td nowrap align="left" height="30px">课程学分：</td>
						 <td colspan="3"><input type="text" name="CurseCredit" id="CurseCredit1" /><span class="red"> *</span></td>
						</tr>
						<tr>
					    <td nowrap align="left" height="30px" width="100">考核方式:</td>
					    <td nowrap width="30"><input type="radio" id="yes" checked name="radio" value="1">考试 <input
												type="radio" id="no"  name="radio" value="0">考查</td>
					    
				        </tr>
						
						<tr>
						<td nowrap align="left" height="30px">备注说明：</td>
						<td colspan="3"><textarea id="Remarks" name="Remarks" rows="2" cols="70"></textarea></td>
						</tr>
						<tr>
						<td></td>
						<td align="center">
							<br>
							<input type="button" value="保存" class="button" onclick="save();" />　
							<input type="reset" value="重置"/>
						</td></tr>	
					  </table>
				</fieldset>
			</td></tr>
		</table>
	</td></tr>				
	</table>
	</form>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>