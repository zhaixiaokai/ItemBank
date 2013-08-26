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
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>

<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}

#act_divLine {
	margin-top: 5px;
	background: url(../source/divLine.gif);
	width: 784px;
	margin-left: 20px
}
.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: #EEEEEE;
	z-index: 1;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}
</style>
<!-- YUI -->
<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/build/datasource/datasource-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../js/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="../js/yui/build/history/history-min.js"></script>
<script type="text/javascript" src="../js/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/datatable/datatable-beta-min.js"></script>


<link rel="stylesheet" type="text/css" href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript" src="../js/yui/build/container/container-min.js"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>


<!-- YUI-END -->
</head>
<script type="text/javascript">
//选择的专业的id
var	SelectedSpecialField="";
//选择的课程的id
var	selectedCurseId="";
//选择的开课班级的id
var	SelectedClassId="";
//生成行政班级的多级菜单
function	getStudentDepartList(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "StudentDepartmentOptionsGetAction",
		success : function(result) {
			StudentDepartmentNodes=result.data;
		},
		error:function(){
		}
	});
}
//点击某一节点时触发的事件
function getStudentDepartmentId(){

	$("#std").empty();
	$("#std").append(this.innerHTML);
	document.getElementById("SelectedClassId").value=this.id;
 
}

//生成专业多级菜单
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
	var	SelectedSpecialField	=	o.id;//所选择的的专业的id
	if(SelectedSpecialField	==	null){
		alert("请选择专业");
		return;
	}
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
function	QureyResultOnClass(){
	selectedCurseId=document.getElementById("SelectCurse").value
	SelectedClassId=document.getElementById("SelectedClassId").value;
	
	if(selectedCurseId==""){
		alert("请选择所要查询课程！");
		return;
	}	
	if(SelectedClassId==""){
		alert("请选择需要查询的行政班级！");
		return;
	}
	CreateDataTableForStudentScore(selectedCurseId,SelectedClassId);
}

//根据查询条件生成学生成绩表格
function CreateDataTableForStudentScore(selectedCurseId,SelectedClassId){
	(function () {
		var timestamp = (new Date()).valueOf();  
	    var myDataSource = new YAHOO.util.DataSource("StudentScoreOnClassGetAction?selectedCurseId="+selectedCurseId+"&SelectedClassId="+SelectedClassId+"&timestamp="+timestamp+"&");  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
           resultsList: "records",
           fields: ["student_id","name","score_total","exam_time","rn"],
           metaFields: {
               totalRecords: "totalRecords",
               paginationRecordOffset : "startIndex",
               sortKey: "sort",
               sortDir: "dir"
           }  
	    };    
	      
	    // 自定义内容格式化方法   
	    YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
            var Id=oRecord.getData("student_id");
            var Name=oRecord.getData("name");
            var Score=oRecord.getData("score_total");
            var Exam_Time=oRecord.getData("exam_time");
    
            elCell.innerHTML="<a href='#' name='"+Id+"' onclick=\"DeleteRow('"+Id+"' ,'"+selectedCurseId+"')\">删除</a> "
            	+"| <a href='#' name='"+Id+"' onclick=\"ModifyRow('"+Id+"','"+Score+"')\">修改</a>";
        	//alert(elCell.innerHTML);
        }  
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('student_id')
              + "\" type=\"checkbox\"" + bChecked
              + " class=\"yui-dt-checkbox\" />";
             
        }
	    /** 
	     * 这里是要显示的列的定义，这里定义了多少个key,页面就会显示多少列 
	     * 要显示远程内容的那些列的key值与上文中fields里的内容相对应 
	     * sortable设置本列是否可以点击列头排序 
	     */  
	     var myColumnDefs = [  
		        	           	{key:"check",label:"",formatter:"checkbox"},  
		        	           	{key:"rn", label:"行号"},
		        	            {key:"student_id", label:"学号"},
		        	            {key:"name", label:"学生姓名"},	  
		        	            {key:"score_total", label:"成绩"},
		        	            {key:"exam_time",label:"考试时间"},
		        	            {key:'操作', lable:"",formatter:"Delete"}
		                    ];      
	    /** 
	     * 生成请求URL,每次翻页或排序时会自动发出请求 
	     */  
	     var generateRequest = function(oState, oSelf) {  
		        oState = oState || { pagination: null, sortedBy: null };  
		        var sort = (oState.sortedBy) ? oState.sortedBy.key : "null";  
		        var dir = (oState.sortedBy && oState.sortedBy.dir === YAHOO.widget.DataTable.CLASS_DESC) ? "desc" : "asc";  
		        var startIndex = (oState.pagination) ? oState.pagination.recordOffset : 0;  
		        var rowsPerPage = (oState.pagination) ? oState.pagination.rowsPerPage : 15;  
		        return  "&sort=" + sort +  
		                "&dir=" + dir +  
		                "&startIndex=" + startIndex +  
		                "&results=" + rowsPerPage;
		    };   
	    /** 
	     * 自定义的表格配置 
	     */  
	    var myConfigs = {  
	        generateRequest: generateRequest,  
	        initialRequest: generateRequest(), // 初始化表格   
	        dynamicData: true, // Enables dynamic server-driven data   
	        sortedBy : {key:"null", dir:YAHOO.widget.DataTable.CLASS_ASC},  
	        // 给表格添加翻页器   
	        paginator: new YAHOO.widget.Paginator({  
	            // 每页的数据条数   
	            rowsPerPage:15,  
	            // 翻页器本地化   
	            lastPageLinkLabel:"末页",  
	            firstPageLinkLabel:"首页",  
	            previousPageLinkLabel:"上一页",  
	            nextPageLinkLabel:"下一页",  
	            containers : ['dt-pag-nav'],
	            // 自定义翻页器内容   
	            template:"{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink} 跳转到{JumpToPageDropdown}页　每页显示{RowsPerPageDropdown}行",  
	            // 每页显示多少行的可选值   
	            rowsPerPageOptions : [ 1,15, 30, 45, 60]}),
	        paginationEventHandler:	YAHOO.widget.DataTable.handleDataSourcePagination
	    };  
	    // 新建一个表格，第一个参数是你页面上的div的id.表格会显示在那个div中。   
	    YAHOO.example.container.myDataTable = new YAHOO.widget.DataTable("serverintegration", myColumnDefs, myDataSource, myConfigs);  
	     
/* 	    return {   
	   　    ds: myDataSource,   
	        dt: myDataTable   
	    };  */
	})();
}
//删除一条记录
function DeleteRow(Id,selectedCurseId){
	
	//alert(id);
	if(confirm("确定要删除该记录？")){
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "StudentScoreDeleteOnClassAction",
			data:{
				"DeleteId":Id,
				"selectedCurseId":selectedCurseId,
				
			},
			success : function(result) {
				alert(result.text);
				//删除完成后重新载入数据表~
				QureyResultOnClass();
			}
		});
	}
}
//修改一条记录,将参数传入修改对话框
function ModifyRow(Id,Score){
    document.getElementById("SchoolID").value=Id;
    document.getElementById("Score").value=Score;
    document.getElementById("selectedCurseId").value=selectedCurseId;
    document.getElementById("SelectedClassId").value=SelectedClassId;
    document.getElementById("oldSchoolID").value=Id;
     
    //根据客户端浏览器高度设置半透明罩的高度 
    document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
    document.getElementById('fade').style.display='block';
    YAHOO.example.container.panel1.show();
}
//清除屏幕锁定，并且隐藏修改框 
function ClearFade(){
	document.getElementById("fade").style.display="none";
	YAHOO.example.container.panel1.hide();
}

//检查修改数据的合法性并且提交，显示处理结果
function	CheckValidation(){
	var schoolID =document.getElementById("SchoolID").value;
	var score=document.getElementById("Score").value;
	if(schoolID==""){
		alert("学号不能为空！");
		return;
	}
	if(score==""){
		alert("学生成绩不能为空！");
		return;
	}
	if(confirm("确认修改学生成绩信息？")){
		$(function() {
			var options = {
				type : "post",
				dataType : "json",
				success : function(result){
					alert(result.text);
				}
			
			};
			$('#modifyForm').ajaxSubmit(options);
			return false;
		});
		ClearFade();
		QureyResultOnClass();
	}
}
</script>

<body  class=" yui-skin-sam">
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a target="mainFrame" href="../functionList/appManage.jsp">应用管理</a>&nbsp;&gt;&gt;&nbsp;成绩管理<a
			href="selectSub.jsp">&nbsp;&gt;&gt;&nbsp;按教学班查询</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div id="act_content2">


		<table border="0" cellpadding="2" cellspacing="1"
			style="width: 80%; font-size: 12px">
			<tr>
				<td nowrap align="left">专业：</td>
				<td width="150px"><div id="SelectStudentDepartment"></div></td>
				<td width="35" style="border: 0">课程:</td>
				<td><select name="SelectCurse" id="SelectCurse">
						<option value="">--请选择--</option>
				</select></td>
				<td width="70" style="border: 0">行政班级:</td>			
				<td><div id="SelectDepartmentClass"></div></td>
				<td width="40"><input type="button"
					value="查询" onclick="QureyResultOnClass();"></td>
			</tr>

		</table>

		<!-- 修改的弹窗 -->
		<div id="container">
			<div id="panel1" style="z-index: 1999">
				<div class="hd" style="z-index: 2000">修改学生成绩信息</div>
				<div class="bd" style="z-index: 2000">
					<form name="modifyForm" action="StudentScoreUpdateOnClassAction"
						id="modifyForm">
						<input type="hidden" name="selectedCurseId" id="selectedCurseId" />
						<input type="hidden" name="SelectedClassId" id="SelectedClassId"/>
						<input type="hidden" name="oldSchoolID" id="oldSchoolID" />
						<table>
							<tr>
								<td width="150px">学号：</td>
								<td><input type="text" name="SchoolID" id="SchoolID" /></td>
							</tr>
							<tr>
								<td width="100px">成绩：</td>
								<td><input type="text" name="Score" id="Score" /></td>
							</tr>
						</table>
						<div align="center">
							<input type="button" value="修改" onclick="CheckValidation();" />
							<input type="button" value="取消" onclick="ClearFade();" />
						</div>
					</form>
				</div>
				<div class="ft"></div>
			</div>
		</div>
		<!-- 半透罩，用来锁定屏幕 -->
		<div id="fade" class="black_overlay"></div>
		<div id="serverintegration"></div>
		<div id="dt-pag-nav"></div>
	</div>

	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	<script type="text/javascript">
		//获取学院→专业多级菜单选项
		getSchoolStructureList();
		loadMenu("SelectStudentDepartment",SchoolStructure,GetCourseBySpecialfield,"College");
		//生成行政班级的多级菜单
		getStudentDepartList();
		loadMenu("SelectDepartmentClass",StudentDepartmentNodes,getStudentDepartmentId,"std");
		
	</script>
		<script>
		//初始化修改信息对话框
		YAHOO.namespace("example.container");
		function init() {
			YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", { width:"600px", visible:false, constraintoviewport:true,close :false } );
			YAHOO.example.container.panel1.render();
		}
		YAHOO.util.Event.addListener(window, "load", init);
	</script>
</body>
</html>