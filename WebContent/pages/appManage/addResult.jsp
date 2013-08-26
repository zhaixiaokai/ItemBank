<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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

<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../../servJs/CreateMultiMenu.js"></script>
<link rel="stylesheet" href="../js/jquery-ui.css" />
<script type="text/javascript" src="../js/jquery-ui.js"></script> 
	
							
<script type="text/javascript">
// 自动选择日期插件
$(function() {
	$("#CurseTime").datepicker({
		dateFormat: "yy-mm-dd",
		autoSize: true,
		changeMonth : true,
		changeYear : true,
		dayNamesMin: [ "日", "一", "二", "三", "四", "五", "六" ],
		firstDay: 1,
		monthNamesShort: [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" ],
		showMonthAfterYear: true,		//在“年”后显示“月”
		showOtherMonths: true,
		yearRange:"1900:2100",
		prevText:"上月",
		nextText:"下月"
	});
});
//选择的专业的id
var	SelectedSpecialField="";
//选择的课程的id
var	selectedCurseId="";
//选择的开课班级的id
var	SelectedTeachingClassId="";

//生成学院专业多级菜单
var	SchoolStructure=null;
function	getSchoolStructureList(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "SchoolStructureOptionsGetAction",
		success : function(result) {
			SchoolStructure=result.data;
		},
		error:function(){
		}
	});
}
//根据选择的专业查询出相应的课程
function getCurse(o){
	SelectedSpecialField	=	o.id;//所选择的的专业的id
	/* if(SelectedSpecialField	==	null){
		alert("请选择专业");
		return;
	} */
	var	innerHTML	= 	document.getElementById("SelectCurse").innerHTML;
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "CurseSelectBySpecialFieldAction",
		data:"specialFieldId="+SelectedSpecialField,
		success : function(result) {
			$("#SelectCurse ").empty();
			$("#SelectCurse").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectCurse").append("<option id=\""+result.data[i].curse_id+
						"\" value=\""+result.data[i].curse_id+"\">"+result.data[i].curse_name+"</option>");
			}
		},
		error:function(){
		}
	});
	
}
function getMajor(o){
	document.getElementById("College").innerHTML=o;
}
//点击学院专业多级菜单的某一节点之后触发的事件
function	GetCourseBySpecialfield(){
	getMajor(this.innerHTML);
	getCurse(this);
}
// 根据选择的专业、课程查询相应的开课班级
function GetTeachingClassOptions(){
	selectedCurseId	=	document.getElementById("SelectCurse").value;
	
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "TeachingClassGetByCurseId",
		data:"CurseId="+selectedCurseId,
		success : function(result) {
			$("#SelectTeachingClass ").empty();
			$("#SelectTeachingClass").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectTeachingClass").append("<option id=\""
						+result.data[i].curse_class_id+"\" value=\""
						+result.data[i].curse_class_id+"\">"
						+result.data[i].class_name+"</option>");
			}
		 },
		error:function(){
		}
	});
}
function uploadFile() {
	SelectedTeachingClassId=document.getElementById("SelectTeachingClass").value;
	//alert(Id);
	//选择的专业的id
 	if(SelectedSpecialField==""){
 		alert("请选择要导入成绩的学院专业");
 		return;
 	} 		
	//选择的课程的id
	if(selectedCurseId==""){
		alert("请选择导入成绩的课程");
		return;
	}
	//选择的开课班级的id
	if(SelectedTeachingClassId==""){
		alert("请选择要导入成绩的开课班级");
		return;
	}
	if(document.getElementById("xlsFile").value==""){
		alert("请选择上传文件");
		return;
	}
	var options = {
		type : "post",
		dataType : "text",
		async : false,
		url : "StudentScoreAction",
		data:
		{
			"selectedCurseId":selectedCurseId
		},
		success : function(responseText){
			alert(responseText.text);
			document.location.href="addResult.jsp";
		}
	};

	$('#StudentScoreForm').ajaxSubmit(options);
	return false;
}
</script>



<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}
</style>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<table width="100%" class="CContent">
		<tr>
			<th class="pagehead">成绩导入</th>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<fieldset style="height: 100%;">
								<legend style="font-size: 12px">成绩导入</legend>
								<form id="StudentScoreForm" enctype="multipart/form-data"
									method="post">
									<table border="0" cellpadding="2" cellspacing="1"
										style="width: 80%; font-size: 12px">
										<tr>
											<td nowrap align="left">学院专业：</td>
											<td width="150px"><div id="SelectStudentDepartment"></div>
											</td>
										</tr>
										<tr>

											<td width="35" style="border: 0">课程:</td>
											<td><select name="SelectCurse" id="SelectCurse"
												onchange="GetTeachingClassOptions();">
													<option value="">--请选择--</option>
											</select></td>
										</tr>
										<tr>
										<td>开课时间：</td>
										<td><input type="text" name="CurseTime"
												id="CurseTime" /></td>
										</tr>
										<tr>
											<td width="70" style="border: 0">开课班级:</td>
											<td><select name="SelectTeachingClass"
												id="SelectTeachingClass">
													<option value="">--请选择--</option>
											</select></td>
										</tr>
										<tr>
											<td>&nbsp;&gt;&gt;&nbsp;成绩导入：</td>
											<td><input type="file" name="xlsFile" id="xlsFile"> </td>
											<td><input type="button" name="upload" value="上传"
												onclick="uploadFile();"></td>
										</tr>
							
									</table>
								</form>
							</fieldset>

						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	

	<!-- 右侧界面完 -->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	
	<script type="text/javascript">
		//获取学院→专业多级菜单选项
		getSchoolStructureList();
		loadMenu("SelectStudentDepartment",SchoolStructure,GetCourseBySpecialfield,"College");
		
	</script>
</body>
</html>