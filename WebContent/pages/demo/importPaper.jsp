<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/multimenu.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css" href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />

<script>
function parsePaper(){
	self.parent.frames["mainFrame"].location="parsePaper.jsp";
}
function fclick(obj){
	  with(obj){
	    style.posTop=event.srcElement.offsetTop
	    var x=event.x-offsetWidth/2
	    if(x<event.srcElement.offsetLeft)x=event.srcElement.offsetLeft
	    if(x>event.srcElement.offsetLeft+event.srcElement.offsetWidth-offsetWidth)x=event.srcElement.offsetLeft+event.srcElement.offsetWidth-offsetWidth
	    style.posLeft=x
	  }
	}
</script>
<style>
</style>
<title>上传试题文档</title>
</head>
<body >
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<div id="act_top"><a href="#">试题管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">增加试题</a>&nbsp;&gt;&gt;&nbsp;<a href="#">导入试题</a></div>
    	<table id="divLine" height="4px" width="100%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
        		<td style="border:0"></td>
      		</tr>
    	</table>
    <div class="div_style2">
		<table  cellspacing="15" >
			<tr ><td>
				<form>
	  				&nbsp;&gt;&gt;&nbsp;请选择需要上传的试题文件：
					<span style="position: relative">
				        <span style="position:absolute; top:0; left:0; width:200px; filter:alpha(opacity=0); opacity:0.0; overflow:hidden">
				            <input type="file" name=fileSelect onchange="fake_upload.value=this.value" style="height:28px;width:300px;cursor:hand;">
				        </span>
				        <input type="text" name="fake_upload" style="width: 200px">
				   		<input type="button" name="select" value="选择" class="button" onclick = "fileSelect.click();"  />
  						<input type="button" name="Submit" value="上传" class="button" / onclick="parsePaper();">
  					</span>
  				</form>
			<tr><td>
		</table>	
	</div>
		<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>