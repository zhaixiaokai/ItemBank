<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="net.ib.util.dao.*,java.util.*"%>
    <%@	page import="net.ib.util.service.impl.*"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加教材</title>

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
	var ID =document.getElementById("TeachingMaterialID1").value;
	var name=document.getElementById("TeachingMaterialName1").value;
	
	if(name=="")
	{
		alert("教材名称为必填项，请重新输入！！ ");
		return false;
	}
	else
	{
		if(ID=="")
		{
			alert("教材编号为必填项，请重新输入 ！！");
			return false;
		}
		else
		{
			
				var truthBeTold=window.confirm("确认添加新的教材？");
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

function showStart() {
	return true;
}
function showSuccess(responseText, statusText) {
	if(responseText!=null&&responseText!=""){
		alert(responseText.result);
	}else{
	alert("教材添加成功！！");
	document.location.href="TMMadding1.jsp";
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
	
	<form id="myForm1" action="TeachmaterialAddAction">
	<table width="100%" class="CContent" >
	<tr>
		<th class="pagehead"  >添加教材</th>
	</tr>
	<tr><td width="100%">
		<table width="100%">  	
  			<tr><td>
				<fieldset style="height:100%;">
				<legend style="font-size:12px">添加教材</legend>
				 <table border="0" cellpadding="2" cellspacing="1" style="width:80%;font-size:12px">
			
				      <tr>
				    <td nowrap align="right">学院专业：
				    </td>
				    <td> 
				<jsp:include page="SchoolTree.jsp"></jsp:include>
				<input type="hidden" id="Specials1" name="Specials" value=""/>


			</td></tr>
				<tr>
					    <td nowrap align="right" width="100" height="50px">所属课程:</td>
					    <td nowrap><select name="select3">
                          <option selected>--选择所开课程--</option>
                          <option>信号与系统</option>
                          <option>通信原理</option>
                          <option>数据库应用技术</option>
                          <option>通信电子电路</option>
                          <option>电子电路基础</option>
                        </select></td>
					</tr>
					
					  <tr>
					    <td nowrap align="right" height="50px">教材编号:</td>
					    <td colspan="3"><input type="text" name="TeachingMaterialID" id="TeachingMaterialID1" /><span class="red"> *</span></td>
					    </tr>
					    <tr>
					    <td nowrap align="right" height="50px">教材名称:</td>
					    <td colspan="3"><input type="text" name="TeachingMaterialName" id="TeachingMaterialName1" /><span class="red"> *</span></td>
					    </tr>
					    <tr>
						<td nowrap align="right" height="50px">教材版本：</td>
						 <td colspan="3"><input type="text" name="TeachingMaterialVersion" id="TeachingMaterialVersion1" /><span class="red"> *</span></td>
						</tr>
						<tr>
					    <td nowrap align="right" height="50px">教材作者:</td>
					    <td colspan="3"><input type="text" name="TeachingMaterialAuthor" id="TeachingMaterialAuthor1" class="text" style="width:150px" size="40" /></td>
				       </tr>
				       <tr>
					    <td nowrap align="right" height="50px">教材出版日期:</td>
					    <td nowrap><input name="TeachingMaterialDate" id="TeachingMaterialDate1" class="text" style="width:150px" type="text" size="40" /></td>
				    </tr>
					<tr>
					    <td nowrap align="right" width="100" height="50px">教材出版社:</td>
					    <td colspan="3"><textarea name="TeachingMaterialHouse" id="TeachingMaterialHouse1" rows="4" cols="60">外语教学与研究出版社</textarea></td>
					    </tr>
					   
					    <tr>
					    <td nowrap align="right" width="100" height="50px">教材ISBN码:</td>
					    <td colspan="3"><textarea name="TeachingMaterialCode" id="TeachingMaterialCode1" rows="2" cols="60">978-7-111-32501-7</textarea><span class="red"> *</span></td>
					    </tr>
						<tr>
					    <td nowrap align="right" width="100" height="50px">上传教材封面照片:</td>
					    <td colspan="3"><form name="form1" enctype="multipart/form-data" method="post" action="">
					      <label>
					        <input type="file" name="file">
				          </label>
					      </form>
					    </td>
						 </tr>
						
						<tr>
						<td nowrap align="right" height="50px">备注说明：</td>
						<td colspan="3"><textarea id="TeachingMaterialRemarks" name="TeachingMaterialRemarks1" rows="2" cols="70"></textarea></td>
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