<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<!-- 
<link rel="stylesheet" type="text/css" href="css/menu.css"> -->
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
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
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>

<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>

<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/build/datasource/datasource-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../js/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="../js/yui/build/history/history-min.js"></script>
<script type="text/javascript" src="../js/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/datatable/datatable-beta-min.js"></script>
<script src="../js/formReset.js" type="text/javascript"></script>
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<link rel="stylesheet" type="text/css" href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript"	src="../js/yui/build/container/container-min.js"></script>
<style type="text/css">
/* custom styles for this example */
#dt-pag-nav {
	margin-bottom: 1ex;
	width: 790px
} /* custom pagination UI */
#dt-pag-nav a {
	color: #00c;
	text-decoration: underline;
}

#yui-history-iframe {
	position: absolute;
	top: 0;
	left: 0;
	width: 1px;
	height: 1px; /* avoid scrollbars */
	visibility: hidden;
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
	filter: alpha(opacity =         80);
}
</style>
<script type="text/javascript">
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

//获取课程列表
function getCurse(o){
	var	SelectedSpecialField	=	o.id;
	
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
						"\" value=\""+result.data[i].curse_id+"\">"+
						result.data[i].curse_name+"</option>");
			}
		},
		error:function(){
		}
	});
	
}
function getMajor(o){
	document.getElementById("College").innerHTML=o;
}
var major_id=null;
/**
 * 根据专业获取课程
 */
function	GetCourseBySpecialfield(){
	getMajor(this.innerHTML);
	getCurse(this);
	major_id=this.id;
}
/**
 * 根据课程获取试卷库
 */
 var SelectedCurseField =null;
 function getEPDB(){
 	var options = document.getElementById("SelectCurse").options;
	var index = document.getElementById("SelectCurse").selectedIndex;
	SelectedCurseField = options[index].id;
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "EPDBSelectByCurseIdAction",
		data:"CurseFieldId="+SelectedCurseField,
		success : function(result) {
			$("#SelectEPDB ").empty();
			$("#SelectEPDB").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectEPDB").append("<option id=\""+result.data[i].epdb_id+
						"\" value=\""+result.data[i].epdb_id+"\">"+
						result.data[i].epdb_name+"</option>");
			}
		},
		error:function(){
		}
	});
	
}
 

var SelectedEPDB=null;//已选择的试卷库的标识符
function showtable(){
		var options= document.getElementById("SelectEPDB").options;
		var index = document.getElementById("SelectEPDB").selectedIndex;
		SelectedEPDB = options[index].id;
		if(document.getElementById("College").innerHTML=="学院"||document.getElementById("College").innerHTML==""){
		alert("请选择专业");
		return;	
		}
	
		if(SelectedCurseField==null||SelectedCurseField=="selectnocurse"){
			alert("请选择课程");
			return;
		}
		
		if(SelectedEPDB==null||SelectedEPDB=="selectnoEPDB"||SelectedEPDB==""){
			alert("请选择试卷库");
			return;
		}
	(function () {
	var History = YAHOO.util.History,
     myPaginator,  // to hold the Paginator instance
     myDataSource, // to hold the DataSource instance
     myDataTable;  // to hold the DataTable instance
     var generateStateString = function (start,key,dir) {
         start = start || 0;
         key   = key || 'sno';
         dir   = dir || 'asc';
         return "results=15&startIndex="+start+"&sort="+key+"&dir="+dir;
     };
     //alert(SelectedEPDB);
   
    var myDataSource = new YAHOO.util.DataSource("ReferExamPaperAction?selectedEPDB="+SelectedEPDB+"&ts="+(new Date()).valueOf()+"&");  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
   // alert("zhixingwan");
/** 
 * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
 */  
myDataSource.responseSchema = {  
    resultsList: "records",
    fields: ['exam_paper_id','paper_name','total_score','exam_duration','difficulty',"rn"],  
    metaFields: {
        totalRecords: "totalRecords",
        paginationRecordOffset : "startIndex",
        sortKey: "sort",
        sortDir: "dir"
    } 
};    
   
  
// 自定义内容格式化方法   
YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
	var id=oRecord.getData("exam_paper_id");
    var name=oRecord.getData("paper_name");
    var totalscore=oRecord.getData("total_score");
    var examduration=oRecord.getData("exam_duration");
    var difficulty=oRecord.getData("difficulty");
    elCell.innerHTML="<a href='#' onclick=DeleteRow('"+id+"')>删除</a> | <a href='#' onclick=ModifyRow('"+id+"','"+name+"','"+totalscore+"','"+examduration+"','"+difficulty+"')>修改</a>";
}  

//checkbox
YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
    var bChecked = oData;
    bChecked = (bChecked) ? " checked=\"checked\"" : "";
    elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('exam_paper_id')
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
                   	{key:"rn", label:"行号",resizeable:true},
                    {key:"paper_name", label:"名称",resizeable:true},            
                    {key:"total_score", label:"总分"},
                    {key:"exam_duration", label:"考试时长"},
                    {key:"difficulty", label:"难度"},
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

return {   
    ds: myDataSource,   
    dt: myDataTable   
};  	
})();
}

function DeleteRow(id){
	if(confirm("确定要删除改试卷吗？")){
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "PaperDeleteAction?deletepaperid="+id,
			success : function(result){
				alert(result.message);
				//document.location.href="paperManage.jsp";
				showtable();ClearFade();
			}
		});
	}
}
	//批量删除试题库
	function bulkdelete(){
		var	CheckBoxsList=document.getElementsByName("CheckBoxs");
		var	flag=false;
		var	param="";
		for(var i=0;i<CheckBoxsList.length;i++){
			if(CheckBoxsList[i].checked){
				if(flag==false){
					param+=CheckBoxsList[i].id;
					flag=true;
				}
				else{
					param+=" "+CheckBoxsList[i].id;
				}
			}
		}
		if(!flag){
			alert("请至少选择一项需要删除的数据");
			return;
		}
		//alert(param);
		if(confirm("确定要删除？")){
			$.ajax( {
				type : "post",
				dataType : "json",
				async : false,
				url : "PaperBulkDeleteAction?deleteid="+param,
				success : function(result) {
					alert(result.message);
					//document.location.href="paperManage.jsp";
					showtable();ClearFade();
				}
			});
		}
	}
	

	//修改试题库，弹出对话框
	function ModifyRow(id, name, totalscore, examduration, difficulty) {
		document.getElementById("PaperName").value = name;
		document.getElementById("TotalScore").value = totalscore;
		document.getElementById("difficulty").value = difficulty;
		document.getElementById("ExamDuration").value = examduration;
		document.getElementById("modifyPaperId").value = id;
		//根据客户端浏览器高度设置半透明罩的高度 
		document.getElementById('fade').style.height = document.body.clientHeight
				+ "px";
		document.getElementById('fade').style.display = 'block';
		YAHOO.example.container.panel1.show();
	}

	//修改试卷信息，验证及提交
	function modifysort() {
		if (document.getElementById("PaperName").value == ""
				|| document.getElementById("PaperName").value == null) {
			alert("修改失败：试卷名称不能为空，请填写试题库名称！");
			return;
		}
		if (!CheckIfChinaNumbLetter(document.getElementById("PaperName").value)) {
			alert("修改失败：名称格式有误，请输入汉字，数学或者字母 ");
			return;
		}
		if (document.getElementById("PaperName").value.length > 20) {
			alert("试卷库名称长度不能超过20个字符");
			return;
		}
		if (document.getElementById("TotalScore").value == ""
				|| document.getElementById("TotalScore").value == null) {
			alert("修改失败：试卷总分不能为空，请填写总分！");
			return;
		}
		if (!CheckIfIsNumber(document.getElementById("TotalScore").value)) {
			alert("修改失败：总分格式有误，请输入正整数 ");
			return;
		}
		if (document.getElementById("ExamDuration").value == ""
				|| document.getElementById("ExamDuration").value == null) {
			alert("修改失败：考试时长不能为空，请填写考试时长！");
			return;
		}
		if (!CheckIfIsNumber(document.getElementById("ExamDuration").value)) {
			alert("修改失败：考试时长格式有误，请输入正整数 ");
			return;
		}

		if (document.getElementById("difficulty").value == ""
				|| document.getElementById("difficulty").value == null) {
			alert("修改失败：试卷难度系数不能为空，请填写难度系数！");
			return;
		}
		var difficulty_value = document.getElementById("difficulty").value;
		if (difficulty_value > 1 || difficulty_value < 0) {
			alert("修改失败：难度系数格式有误，请输入0-1之间的小数 ");
			return;
		}
		if (true) {
			$(function() {
				var options = {
					type : "post",
					dataType : "json",
					url : "PaperModifyAction",
					beforeSubmit : showStart,
					success : showSuccess,
				};
				$('#modifyForm').ajaxSubmit(options);
				return false;
			});
		}

		function showStart() {
			return true;
		}
		function showSuccess(responseText, statusText) {
			if (responseText.message != null && responseText.message != "") {
				if (responseText.message == "修改成功") {
					alert(responseText.message);
					//document.location.href="paperManage.jsp";
					showtable();ClearFade();
				} else {
					alert(responseText.message);
				}
			}
		}
	}

	//清除屏幕锁定，并且隐藏修改框 
	function ClearFade() {
		document.getElementById("fade").style.display = "none";
		YAHOO.example.container.panel1.hide();
	}
</script>
<title>Insert title here</title>
</head>
<body class=" yui-skin-sam">
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperDbManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<div id="act_top">
		<a href="#">试卷库管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">试卷管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">试卷管理</a>
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>

	<div style="margin-left: 25px; margin-top: 10px">
		<table>
			<tr>
				<td>专业选择：</td>
				<td width="100px" id="SelectBasic"><div id="SpecialField"></div></td>
				<td>课程选择：</td>
				<td width="100px"><select id="SelectCurse" onchange="getEPDB()"><option
							selected="selected" id="selectnocurse"  >--请选择--</option>
				</select></td>
				<td>试卷库选择：</td>
				<td width="100px"><select id="SelectEPDB"
					style="font-size: 12px"><option selected="selected" id="selectnoEPDB">--请选择--</option>
				</select></td>
				<td width="30px"><input type="button" class="button" value="查询"
					onclick="showtable()"></td>
				<td align="right"><input type="button" class="button"
					value="批量删除" onclick="bulkdelete()"></td>
			</tr>
		</table>
	</div>

	<!-- 修改的弹窗 -->
	<table>
		<tr>
		<td>
		<div id="container">
			<div id="panel1" style="z-index: 1999">
				<div class="hd" style="z-index: 2000">修改试卷</div>
				<div class="bd" style="z-index: 2000">
					<form name="modifyForm"  id="modifyForm">
					<input type="hidden" id="modifyPaperId" name="modifyPaperId" />
					<table>
							<tr>
								<td width="150px" nowrap align="right">试卷名称：</td>
								<td><input type="text" id="PaperName" class="text"
									name="newPaperName"/></td>
							</tr>
							<tr>
								<td nowrap align="right" width="150px">总分：</td>
								<td nowrap width="30"><input id="TotalScore"
										name="newTotalScore"></input></td>

							</tr>
							<tr>
								<td nowrap align="right">考试时长：</td>
								<td><input id="ExamDuration" name="newDuration"></input></td>
							</tr>
							
							<tr>
								<td nowrap align="right" width="100">难度：</td>
								<td nowrap width="30"><input id="difficulty"
									name="newdifficulty"></input></td>
							</tr>

							<tr>
								<td nowrap align="right" height="30px" >备注信息:</td>
								<td colspan="3"><textarea rows="4" cols="70" name="comment" id="comment"></textarea></td>
							</tr>						

						</table>
							<div align="center">
				<input type="button" value="修改" onclick="modifysort(id);" />
				<input type="button" value="取消" onclick="ClearFade();" />
				</div>
					</form>
				</div>
				<div class="ft"></div>
			</div>
		</div>
		
		</td>
			</tr>
		</table>  


	<!-- 半透罩，用来锁定屏幕 -->
	<div id="fade" class="black_overlay"></div>
	<div id="serverintegration" style="margin-left: 45px"></div>
	<div id="dt-pag-nav"></div>
	<script>
		//初始化修改信息对话框
		YAHOO.namespace("example.container");
		function init() {
			YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", { width:"600px", visible:false, constraintoviewport:true,close :false } );
			YAHOO.example.container.panel1.render();
		}
		YAHOO.util.Event.addListener(window, "load", init);
		
	</script>

	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	<script>
	getSchoolStructureList();
	loadMenu("SpecialField",SchoolStructure,GetCourseBySpecialfield,"College");
</script>
</body>
</html>