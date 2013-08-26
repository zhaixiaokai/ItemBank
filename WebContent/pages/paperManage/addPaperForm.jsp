
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="../css/table.css">

<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />
<link href="css/multimenu.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<!-- YUI -->
<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/2.6.0/build/treeview/assets/skins/sam/treeview.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/2.6.0/build/treeview/treeview-min.js"></script>
<script src="../js/yui/build/yahoo/yahoo-min.js"></script>
<script src="../js/yui/build/json/json-min.js"></script>
<link rel="stylesheet" type="text/css" href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../js/yui/build/container/container-min.js"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<script src="../js/formReset.js" type="text/javascript"></script>
<style type="text/css">
#container {
	margin: 0 auto;
	width: 1024px;
}
</style>
<style type="text/css">
.black_overlay { display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%;
	background-color: #EEEEEE; z-index: 1; -moz-opacity: 0.8; opacity: .80; filter: alpha(opacity = 80); }
</style>
<script type="text/javascript">
function save(){
	var id=$("#paperId").val();
	var name=$("#paperName").val();
	var totalScore=$("#paperTotalScore").val();
	var diff=$("#difficulty_coefficient").val();
	var paperTime=$("#paperTime").val();
	var explain=$("#comment").val();
	var paperFile=$("#paperFile").val();
	var paperAtt=$("#paperAttachment").val();
	var paperAns=$("#paperAnswer").val();
	if(""==id||null==id){
		alert("试卷唯一标识符不能为空");
		return;
	}
	if(!CheckIfIsLetter_Number(id)){
		alert("试卷唯一标识符只能由字母、数字、下划线组成");
		return;
	}
	if(id.length>20){
		alert("唯一标识符长度不能超过20");
		return;
	}
	if(""==name||null==name){
		alert("试卷名称不能为空");
		return;
	}
	if(!CheckIfChinaNumbLetter(name)){
		alert("试卷名称只能由汉字、字母、数字组成");
		return;
	}
	if(name.length>20){
		alert("试卷名称长度不能超过20个字符");
		return;
	}
	if(""==totalScore||null==totalScore){
		alert("试卷总分值不能为空");
		return;
	}
	if(!CheckIfIsNumber(totalScore)){
		alert("试卷总分值必须为数字");
		return;
	}
	if(""==diff||null==diff){
		alert("难度系数不能为空");
		return;
	}
	if(!isFigure(diff)){
		alert("难度系数必须为数字");
		return;
	}
	if(diff>=1||diff<=0){
		alert("难度系数必须在0-1之间");
		return;
	}
	if(""==paperTime||null==paperTime){
		alert("考试时长 不能为空");
		return;
	}
	if(!isFigure(paperTime)){
		alert("考试时常必须为数字");return;
	}
	if(explain.length>300){
		alert("试卷说明最多300个字符");
		return;
	}
	if(""==paperFile||null==paperFile){
		alert(" 请选择需要上传的试卷文件");
		return;
	}
	var objtype=paperFile.substring(paperFile.lastIndexOf(".")).toLowerCase();
	if(".doc"!=objtype&&".docx"!=objtype){
		alert("上传文件必须为.doc或者.docx文件");
		return;
	}
	if(""==paperAns||null==paperAns){
		alert(" 请选择需要上传的试卷文件");
		return;
	}
	objtype=paperAns.substring(paperFile.lastIndexOf(".")).toLowerCase();
	if(".doc"!=objtype&&".docx"!=objtype){
		alert("上传文件必须为.doc或者.docx文件");
		return;
	}
	$(function() {
		var options = {
			type : "post",
			dataType : "text",
			url : "ExamPaperAddAction",
			success:function(result){
				alert((result));
			}
		};
		$('#frmMain').ajaxSubmit(options);
		return false;
	});
	
}
$(document).ready(function(){
	document.getElementById("course").value='<%=request.getParameter("leafid")%>';
	document.getElementById("EPDBID").value='<%=request.getParameter("radio")%>';	
});
</script>

</head>
<body class=" yui-skin-sam">
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperDbManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="container" style="position: absolute;">
		<div id="panel1" style="z-index: 1999">
			<div id="PannelHeader" class="hd" style="z-index: 2000"></div>
			<div class="bd" style="z-index: 2000">
				<script src="../js/webOffice/LoadWebOffice.js"></script>
				<table align="center">
					<tr>
						<td><input type="button" class="button" value="完成" onclick="QuesComplete()"/></td>
						<td><input type="button" class="button" value="重置" onclick="QuesReset()"/></td>
					</tr>
				</table>
			</div>
			<div class="ft"></div>
		</div>
	</div>	
	<!-- 半透罩，用来锁定屏幕 -->
	<div id="fade" class="black_overlay"></div>
	<div style="margin-top: 10px">
		<table width="100%" class="table1">
			<tr>
				<th class="pagehead">添加试卷</th>
			</tr>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0"
						style="width: 100%">
					
					</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%">
						<tr>
							<td>
								<fieldset style="height: 100%;">
									<legend style="font-size: 12px">添加试卷</legend>
									<form id="frmMain" enctype="multipart/form-data" method="post" action="">
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">
										<tr>
											<td nowrap align="right" >试卷唯一标识符</td>
											<td><input type="text" size="20" 
												class="text" id="paperId" name="paperId"><span
													class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right" >试卷名称</td>
											<td><input type="text" size="20" 
												class="text" id="paperName" name="paperName"><span
													class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right" >试卷总分值</td>
											<td><input type="text" size="20" 
												class="text" id="paperTotalScore" name="paperTotalScore"><span
													class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right" >难度系数</td>
											<td><input type="text" size="20" title="难度系数为0-1间的小数"
												class="text" id="difficulty_coefficient" name="difficulty_coefficient"><span
													class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right" height="10px">考试时长</td>
											<td colspan="3"><input type="text"  size="20"
												class="text" id="paperTime" name="paperTime">（分钟）<span
													class="red"> *</span></td>
										</tr>

										<tr>
											<td nowrap align="right" height="30px" >备注信息:</td>
											<td colspan="3"><textarea rows="4" cols="70" name="comment" id="comment"></textarea></td>
										</tr>
									</table>
										<table>
											<tr>
												<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择需要上传的试卷文件：</td><td><input
													type="file" name="paperFile" id="paperFile"><span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择需要上传的试卷答案附件：</td><td><input
													type="file" name="paperAnswer" id="paperAnswer"><span
													class="red"> *</span></td>
											</tr>
											<tr>
												<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择需要上传的试卷附件：</td><td><input
													type="file" name="paperAttachment" id="paperAttachment"></td>
											</tr>
										</table>
										<input id="course" name="course" type="hidden"/>
									<input id="EPDBID" name="EDBPID" type="hidden"/>
									</form>
								</fieldset>
							</td>
						</tr>
					</table>
				</td>
			</tr>


			<tr>
				<td align="center"><br> <input type="button" name="Submit"
					value="保存" class="button" onclick="save();" /> </td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		//初始化修改信息对话框
		//getpointdata();
		 YAHOO.namespace("example.container");
		function init() {
			YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", { width:"1000px", visible:false, constraintoviewport:true,close :false } );
			YAHOO.example.container.panel1.render();
		}
		YAHOO.util.Event.addListener(window, "load", init); 
	</script>


	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>

