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
<link href="../css/infoedit.css" rel="stylesheet" type="text/css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<style type="text/css">

#container{margin:0 auto;width:824px;}


</style>
<script>
function save(){
	 window.location.href="TMMmaterialList.jsp"; 
}
</script>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	
<table width="100%" class="CContent" >
	<tr>
		<th class="pagehead"  >修改教材信息</th>
	</tr>
	<tr><td width="100%">
		<table width="100%">
			<tr><td>
				<fieldset style="height:100%;">
				<legend style="font-size:12px">修改教材信息<span class="red">（标"*"为必填项）</span></legend>
				 <table width="100%" border="0" cellpadding="2" cellspacing="1" style="width:80%;font-size:12px">
			    <tr><td> </td></tr>
				       
					   <tr>
					    <td nowrap align="right" height="50px">教材归属信息:</td>
					    <td colspan="3">经济学&gt;&gt;专业一&gt;&gt;科目一</td>
					   </tr>
					  <tr>
					    <td nowrap align="right" height="60px">教材唯一编号:</td>
					    <td colspan="3"><input type="text" name="textfield" id="textfield" /><span class="red"> *</span></td>
				    </tr>
					    <tr>
					    <td nowrap align="right" height="50px">教材名称:</td>
					    <td colspan="3"><textarea name="textfield" id="textfield">大学英语听说教程</textarea><span class="red"> *</span></td>
					    </tr>
						<tr>
					    <td nowrap align="right" height="50px">教材版本:</td>
					    <td colspan="3"><input name="textfield" type="text" id="textfield" value="第三版" /><span class="red"> *</span></td>
					    </tr>
						<tr>
					    <td nowrap align="right" height="50px">教材作者:</td>
					    <td colspan="3"><input name="textfield" type="text" id="textfield" /></td>
					    </tr>
						<tr>
					    <td nowrap align="right" height="50px">教材出版日期:</td>
					    <td colspan="3"><input name="textfield" type="text" id="textfield" /></td>
					    </tr>
					     <tr>
					    <td nowrap align="right" height="50px">教材出版社:</td>
					    <td colspan="3"><textarea id="textarea" name="textarea" rows="4" cols="60">外语教学与研究出版社</textarea></td>
					    </tr>
					   
					    <tr>
					    <td nowrap align="right" height="100px">教材ISBN码:</td>
					    <td colspan="3"><textarea id="textarea" name="textarea" rows="2" cols="60">978-7-111-32501-7</textarea><span class="red"> *</span></td>
					    </tr>
						 <tr>
					    <td nowrap align="right" height="60px">上传教材封面照片:</td>
					    <td colspan="3"><form name="form1" enctype="multipart/form-data" method="post" action="">
					      <label>
					        <input type="file" name="file">
				          </label>
					      </form>
					    </td>
						 </tr>
					 
				  </table>
				</fieldset>
			</td></tr>
		</table>
	</td></tr>
				
	<tr><td align="center">
		<p>
		  <input type="button" name="Submit" value="保存" class="button" onClick="save();"/>　
		  <input type="button" name="reset" value="重置" class="button"/><!-- 
		  <input type="button" name="reset" value="返回" class="button" onclick="window.history.go(-1);"/> -->
		</p>
	</tr>	
</table>	

	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>