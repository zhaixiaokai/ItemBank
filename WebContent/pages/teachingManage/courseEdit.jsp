<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../esM/css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link rel="stylesheet" type="text/css" href="../esM/css/menu.css">
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../esM/css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
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
<script type="text/javascript">
function orelat(x){
   var o=document.getElementById(x);
   var li=o.getElementsByTagName('li');
   for(var i=0;i<li.length;i++){
         li[i].onmouseover=new Function("orelatf1(this)");
        li[i].onmouseout=new Function("orelatf2(this)");
   }
}
function orelatf1(o){
      var ul=o.getElementsByTagName('ul'); 
    if(o.className.indexOf('relat_li')>=0)o.style.backgroundColor='#84C1FF';//class值，主菜单划过背景色
    if(ul[0]){
    var class1=o.className;
    o.className=class1+' hovechild';
    ul[0].style.display='block';
    }
    
}
function orelatf2(o){
   var class2=o.className;
   if(o.className.indexOf('relat_li')>=0){o.style.backgroundColor="#84C1FF";}//class值，主菜单划过背景色
   if(class2.indexOf('relat_li')<0){//class值
      o.className='';
   }
   var ul=o.getElementsByTagName('ul'); if(ul[0]){ul[0].style.display='none';}
} 

</script>
</head>
<body >
<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
<div style="margin-top:10px">
<table width="100%" class="table1" >
	<tr >
		<th class="pagehead"  >修改课程信息</th>
	</tr>
	<tr>
		 <td>
			<table border="0" cellpadding="0" cellspacing="0" style="width:100%">
			</table>
		</td>
	</tr>	
	<tr><td width="100%">
		<table width="100%">
			<tr><td>
				<fieldset style="height:100%;">
				<legend style="font-size:12px">修改课程信息</legend>
				 <table border="0" cellpadding="2" cellspacing="1" style="width:80%;font-size:12px">
				    <tr>
					    <td nowrap align="right" width="100">学院专业:</td>
					     <td nowrap width="30"><!-- 引入外部文件，选择试题库 -->  <jsp:include page="../document/basicsortSelect.jsp"></jsp:include></td>
					    
				    </tr>
					<tr>
					    <td nowrap align="right" width="100">课程编号:</td>
					    <td nowrap><input name="text" class="text" style="width:150px" type="text" size="40" />
				        <span class="red"> *</span></td>
				    </tr>
					<tr>
					    <td nowrap align="right" width="100">课程名称:</td>
					    <td nowrap><input name="text" class="text" style="width:150px" type="text" size="40" />
				        <span class="red"> *</span></td>
				    </tr>
					<tr>
					    <td nowrap align="right" width="100">课程学分:</td>
					    <td nowrap><input name="text" class="text" style="width:150px" type="text" size="40" />
				        <span class="red"> *</span></td>
				    </tr>
				    <tr>
					    <td nowrap align="right" width="100">考核方式:</td>
					     <td nowrap width="30"><input type="radio" id="yes" checked name="radio">&nbsp;&nbsp;考试 <input
												type="radio" id="no"  name="radio">&nbsp;&nbsp;考查</td>
					    
				    </tr>
				    
					  <tr>
					    <td nowrap align="right" width="100">备注说明:</td>
					    <td colspan="3"><textarea id="textarea" name="textarea" rows="5" cols="70"></textarea></td>
					    </tr>
					    
					  </table>
				</fieldset>
			</td></tr>
		</table>
	</td></tr>
				
	<tr><td align="center">
		<br>
		<input type="button" name="Submit" value="保存" class="button" onClick="save();"/>　
		<input type="reset" name="Submit" value="重置" class="button" onClick="formReset( document.getElementById( 'myForm' ) );"/>
	</td></tr>	
</table>
</div>
<script type="text/javascript">
orelat('howl');
</script>
<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>