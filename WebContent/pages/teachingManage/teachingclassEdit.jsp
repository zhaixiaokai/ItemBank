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
html,body{font-size:12px;margin:0px;height:100%;}
.mesWindow{border:#666 1px solid;background:#fff;}
.mesWindowTop{border-bottom:#eee 1px solid;margin-left:4px;padding:3px;font-weight:bold;text-align:left;font-size:12px;}
.mesWindowContent{margin:4px;font-size:12px;}
.mesWindow .close{height:15px;width:28px;border:none;cursor:pointer;text-decoration:underline;background:#fff}
</style>
<script type="text/javascript">

function func1(){
	
/* 	var xLeft=document.getElementById("findTeacher").getBoundingClientRect().left;
	var yTop=document.getElementById("findTeacher").getBoundingClientRect().top;
	var xRight=document.getElementById("findTeacher").getBoundingClientRect().right;
	var yBottom=document.getElementById("findTeacher").getBoundingClientRect().bottom;
	var	xCenter=(xLeft+(xRight-xLeft)/2);
	var yCenter=(yTop+(yBottom-yTop)/2);
	alert(xCenter+" "+yCenter);
	document.getElementById("click").style.left=(xCenter-335)+"px";
	document.getElementById("click").style.top=(yCenter-150)+"px"; */
	document.getElementById("click").style.display="block";
}

</script>
</head>
<body>
<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
<div style="margin-top:10px">
<table width="100%" class="table1" >
	<tr >
		<th class="pagehead"  >修改开课班级信息</th>
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
				<legend style="font-size:12px">修改开课班级信息</legend>
				 <table border="0" cellpadding="2" cellspacing="1" style="width:100%;font-size:12px">
					<tr>
					    <td align="right" width="60px">学院专业:</td>
					     <td width="30"><!-- 引入外部文件，选择试题库 -->  <jsp:include page="../document/basicsortSelect.jsp"></jsp:include></td>
					    
				    </tr>
					
					<tr>
					    <td align="right" width="60px">所开课程:</td>
					    <td nowrap><form name="form1" method="post" action="">
					      <label>
					        <select name="select">
					          <option selected>--选择所开课程--</option>
					          <option>信号与系统</option>
					          <option>通信原理</option>
					          <option>数据库应用技术</option>
					          <option>通信电子电路</option>
					          <option>电子电路基础</option>
					          </select>
					        </label>
					      </form>
					    </td>
					</tr>
					<tr>
					    <td align="right" width="60px">班级编号:</td>
					    <td nowrap><input name="text" class="text" style="width:150px" type="text" size="40" />
				        <span class="red"> *</span></td>
				    </tr>
					<tr>
					    <td align="right" width="60px">班级名称:</td>
					    <td nowrap><input name="text" class="text" style="width:150px" type="text" size="40" />
				        <span class="red"> *</span></td>
				    </tr>
				    <tr>
					    <td align="right" width="60px">授课教师:</td>
					    <td nowrap><input id="fillblank" name="text" class="text" style="width:150px" type="text" size="40" />
				        <span class="red"> *</span>&nbsp;&nbsp;&nbsp;<!--<a id="findTeacher" href="javascript:void(0);" onClick="func1();" ">查询教师</a>-->
						<td><!-- 引入外部文件，选择试题库 -->  <jsp:include page="ResearchCenter1.jsp"></jsp:include></td>
						
				    </tr>
				    <tr><td></td>
				    <td>
				    <div id="click" style="width:500px;height:20px;display: none;">
						<!--  -->
						<jsp:include page="ResearchCenter1.jsp"></jsp:include>
					</div>
				    
				    </td></tr>
					  <tr>
					    <td align="right" width="60px">备注说明:</td>
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