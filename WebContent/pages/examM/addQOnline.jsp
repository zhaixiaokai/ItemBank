
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
//获取试题模板
function getDoc(){
	document.getElementById("WebOffice1").LoadOriginalFile("/TemplateGetAction?type=<%=request.getParameter("SelectQuesType")%>","doc");
}
//提交、上传文件
function saveDoc(id,docType) {
	try{
		var webObj=document.getElementById("WebOffice1");
		var returnValue;
		webObj.HttpInit();			//初始化Http引擎
		// 添加相应的Post元素 
		webObj.HttpAddPostCurrFile("DocContent","");		// 上传文件
		returnValue = webObj.HttpPost("/QuestionUploadAction");	// 判断上传是否成功
		if("succeed" == returnValue){
			alert("文件上传成功");	
			webObj.Close();
		}else if("failed" == returnValue){
			alert("文件上传失败");
		}
	}catch(e){
		alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
	}
}



//获取知识点
	function getpointdata() {
	//alert(1);
	var subjectid='<%=request.getParameter("leafid")%>';
		$.ajax({
					type : "post",
					dataType : "json",
					async : false,
					url : "GetPointDataAction?leafid="+subjectid,
					success : function(result) {
						$("#selectpoint ").empty();
						$("#selectpoint").append(
								"<option value=\"\">--请选择--</option>");
						for ( var i = 0; i < result.data.length; i++) {
								$("#selectpoint")
										.append(
												"<option id=\""+result.data[i].knowledge_point_id+
						"\" value=\""+result.data[i].name+"\">"
														+ result.data[i].name
														+ "</option>");
						}
						//alert($("#selectpoint").html());
					},
					error : function() {
					}
				});

	}


//保存试题
function save(){
	var difficulty_coefficient = document.getElementById("difficulty_coefficient").value;
	var defaultpoint = document.getElementById("defaultpoint").value;
	var time = document.getElementById("time").value;
	var comment = document.getElementById("comment").value;
	var options = document.getElementById("selectpoint").options;
	var index = document.getElementById("selectpoint").selectedIndex;
	var selectedOption = options[index];
	var selectpoint = selectedOption.id; 
	if (difficulty_coefficient == "") {
		alert("难度系数为必填项，请重新输入 ");
		return;
	}else if (!isFigure(difficulty_coefficient)) {
		alert("难度系数为数字，请重新输入");
		return;
	} else if (difficulty_coefficient>=1||difficulty_coefficient<=0) {
		alert("难度系数为0到1之间的小数");
		return;
	}  else if (defaultpoint == "") {
		alert("默认分值为必填项，请重新输入 ");
		return;
	}  else if (!isFigure(defaultpoint)) {
		alert("默认分值为数字，请重新输入");
		return;
	} else if (time == "") {
		alert("时间为必填项，请重新输入 ");
		return;
	} else if (!isFigure(time)) {
		alert("时间为数字，请重新输入");
		return;
	} else {
		//判断试题格式是否正确
			var questiontype='<%=request.getParameter("SelectQuesType")%>';
			var target_itembank='<%=request.getParameter("radio")%>';
		/* $.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "QuestionCheckValidationAction?questiontype="+questiontype+"&target_itembank="+target_itembank,
			success : function(result) {
				if(result){
					alert(result);
				}
			},
			error : function() {
			}
		}); */
		
		//试题基本信息保存
	
	$(function() {
				var options = {
					type : "post",
					dataType : "text",
					url : "QuestionCheckValidationAction?questiontype="
							+ questiontype + "&target_itembank="
							+ target_itembank + "&difficulty_coefficient="
							+ difficulty_coefficient + "&defaultpoint="
							+ defaultpoint + "&time=" + time + "&ques_selectpoint="
							+ selectpoint + "&comment=" + comment,

					success : function(result) {
						if (result != null && result != "") {
							if (result == "添加成功") {
								alert(result);
								var form = document.getElementById("frmMain");
								form.reset();
							} else {
								alert(result);
							}
						} else {
							alert("修改失败");
						}
					}
				};
				$('#frmMain').ajaxSubmit(options);
				return false;
			});
		}
	}
</script>
<script type="text/javascript">
function	EditQues(){
	displayAndEditQues();
}
//对数据进行验证，如果成功执行修改
function	displayAndEditQues(){
    //根据客户端浏览器高度设置半透明罩的高度 
    document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
    document.getElementById('fade').style.display='block';
    document.getElementById("PannelHeader").innerHTML="编辑试题";
    document.getElementById("container").style.left=(document.body.clientWidth-1024)/2+15+"px";
    document.getElementById("container").style.top="0px"
    YAHOO.example.container.panel1.show();
}
//清除锁屏，隐藏试题编辑界面 
function ClearFade(){
	document.getElementById("fade").style.display="none";
	YAHOO.example.container.panel1.hide();
}
//完成编辑试题后的操作
function QuesComplete(){
	saveDoc();
	ClearFade();
}
//重置试题
function QuesReset(){
	getDoc();
}
</script>
</head>
<body class=" yui-skin-sam">
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperManageContent.jsp"></jsp:include>
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
				<th class="pagehead">在线添加试题</th>
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
									<legend style="font-size: 12px">添加试题</legend>
									<form id="frmMain" enctype="multipart/form-data" method="post">
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">
										<tr>
											<td nowrap align="right" >难度系数:</td>
											<td><input type="text" size="9" title="难度系数为0-1间的小数"
												class="text" id="difficulty_coefficient" name="difficulty_coefficient"><span
													class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right" height="10px">默认分值:</td>
											<td colspan="3"><input type="text"  size="9"
												class="text" id="defaultpoint" name="defaultpoint" title="请输入数字"><span
													class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right" height="10px">预计时间:</td>
											<td colspan="3"><input type="text" size="9"
												class="text" id="time" name="time" title="请输入数字，单位为秒">&nbsp;秒<span
													class="red"> *</span></td>
										</tr>

										<tr>
											<td nowrap align="right">知识点:</td>
											<td><select name="selectpoint" id="selectpoint" style="font-size: 12px">
											</select><span
													class="red"> *</span></td>
										</tr>
										<tr><td><input value="编写试题" type="button" onclick="EditQues();"/><span
													class="red"> *</span></td></tr>
										<tr>
											<td nowrap align="right" height="30px" >备注信息:</td>
											<td colspan="3"><textarea rows="4" cols="70" name="comment" id="comment"></textarea></td>
										</tr>
									</table>
									<table>
										<tr>
											<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请选择需要上传的试题文件：<input type="file" name="ques_attachment" id="ques_attachment"></td>
										</tr>
									</table>
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
		getpointdata();
		 YAHOO.namespace("example.container");
		function init() {
			YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", { width:"1000px", visible:false, constraintoviewport:true,close :false } );
			YAHOO.example.container.panel1.render();
		}
		YAHOO.util.Event.addListener(window, "load", init); 
		getDoc();
		
	</script>


	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>

