<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>

<link href="../examM/css/style.css" rel="stylesheet" type="text/css" />
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
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<style type="text/css">
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
<script type="text/javascript">
var	SpecialFieldId=null;
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
function	GetCourseBySpecialfield(){
	getMajor(this.innerHTML);
	getCurse(this);
}
function getMajor(o){
	document.getElementById("College").innerHTML=o;
}
function getCurse(o){
	var	SelectedSpecialField	=	o.id;
	SpecialFieldId	=	SelectedSpecialField;
//	alert(SelectedSpecialField);
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
				$("#SelectCurse").append("<option id=\""+result.data[i].curse_id+"\" value=\""+result.data[i].curse_id+"\">"+
						result.data[i].curse_name+"</option>");
			}
		},
		error:function(){
		}
	});
	
}
function CheckValidationAndQueryCurse(){
	/* var	CurseId	= document.getElementById("SelectCurse").value;
	if(CurseId==""){
		alert("请选择课程");
		return;
	}
	SpecialFieldId	=	CurseId; */
	if(null==SpecialFieldId||""==SpecialFieldId){
		alert("请选择专业");
		return;
	}
	CreateCurseTableBySpecialFieldId(SpecialFieldId);
}

//修改一条记录,将参数传入修改对话框
function ModifyRow(Id,Name,Credit,Method,Explain){
    document.getElementById("OldCurseId").value=Id;
    //alert(document.getElementById("OldCurseId").value);
    document.getElementById("CurseName").value=Name;
    document.getElementById("CurseID").value=Id;
    var	CreditNew	=	Credit.replace(/n1/g,"\n");
    CreditNew=CreditNew.replace(/n2/g,"n");
    document.getElementById("CurseCredit").value=CreditNew;
    if(Method=="考试"){
		document.getElementById("RadioYes").checked=true;
		document.getElementById("RadioNo").checked=false;	
	}
	else {
		document.getElementById("RadioYes").checked=false;
		document.getElementById("RadioNo").checked=true;
	}
    document.getElementById("CurseRemarks").value=Explain.replace(/n1/g,"\n").replace(/n2/g,"n");
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
//删除一条记录
function DeleteRow(o){
	var	id=o.name;
	if(confirm("确定要删除该记录？")){
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "CurseDeleteAction?DeleteId="+id,
			success : function(result) {
				alert(result.result);
				//删除完成后重新载入数据表~
				if(SpecialFieldId==null||SpecialFieldId=="")
					getAllCourse();
				else
					CreateCurseTableBySpecialFieldId(SpecialFieldId);
			}
		});
	}
	else{
	}
}
//批量删除
function BulkDelete(){
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
	if(confirm("确定要删除？")){
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "CurseBulkDeleteAction",
			data:"DeleteId="+param,
			success : function(result) {
				alert(result.text);

				if(SpecialFieldId==null||SpecialFieldId=="")
					getAllCourse();
				else
					CreateCurseTableBySpecialFieldId(SpecialFieldId);
			 },
			error:function(){
			}
		});
	}
	
}
//检查数据合法新并且提交，显示处理结果
function	CheckValidation(){
	var	name	=	document.getElementById("CurseName").value;
	var	id	=	document.getElementById("CurseID").value;
	var	credit	=	document.getElementById("CurseCredit").value;
	if(name==""){
		alert("课程名称不能为空");
		modifyForm.CurseName.focus();
		return false;
	}
	if(!CheckIfChinaNumbLetter(name)){
		alert("课程名称只能由汉字、数字、字母构成");
		modifyForm.CurseName.focus();
		return false;
	}
	if(name.length>20){
		alert("课程名称长度不能超过20");
		return;
	}
	if(id==""){
		alert("课程编号不能为空");
		modifyForm.CurseID.focus();
		return false;
	}
	if(!CheckIfIsLetter_Number(id)){
		alert("课程编号只能由字母、数字、'_'组成");
		modifyForm.CurseID.focus();
		return false;
	}
	if(id.length>20){
		alert("课程编号长度不能超过20");
		return;
	}
	if(credit==""){
		alert("课程学分不能为空");
		modifyForm.CurseCredit.focus();
		return false;
	}
	if(!isDecimal(credit)){
		alert("课程学分只能为正整数或者小数");
		return false;
	}
 	$(function() {
		var options = {
			type : "post",
			dataType : "json",
			success : function(result){
				alert(result.text);
				if(result.result=="succ"){
					ClearFade();
					//重新载入数据

					if(SpecialFieldId==null||SpecialFieldId=="")
						getAllCourse();
					else
						CreateCurseTableBySpecialFieldId(SpecialFieldId);
				}
			},
		};
		$('#modifyForm').ajaxSubmit(options);
	});
}
function CreateCurseTableBySpecialFieldId(SpecialFieldId){
	(function () {
		var timestamp = (new Date()).valueOf();  
	    var myDataSource = new YAHOO.util.DataSource("CurseTableDataSelectByFieldIdAction?FieldId="+SpecialFieldId+"&timestamp="+timestamp+"&");  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
           resultsList: "records",
           fields: ["curse_name","curse_id","special_field_id","rn","curse_credit","assessment_method","remarks"],
           metaFields: {
               totalRecords: "totalRecords",
               paginationRecordOffset : "startIndex",
               sortKey: "sort",
               sortDir: "dir"
           }  
	    };    
	      
	    // 自定义内容格式化方法   
        YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
            var Id=oRecord.getData("curse_id");
            var Name=oRecord.getData("curse_name");
            var	Credit=oRecord.getData("curse_credit"); 
            var	Method=oRecord.getData("assessment_method"); 
            var	Explain=oRecord.getData("remarks"); 
            Explain=Explain.trim();
            elCell.innerHTML="<a href='#' name='"+Id+"' onclick='DeleteRow(this)'>删除</a> "
            	+"| <a href='#' name='"+Id+"' onclick=\"ModifyRow('"+Id+"','"+Name+"','"+Credit+"','"+Method+"','"+Explain+"')\">修改</a>";
        	//alert(elCell.innerHTML);
        }  
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('curse_id')
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
	        	            {key:"curse_id", label:"课程编号"},
	        	            {key:"curse_name", label:"课程名称"},
	        	            {key:"curse_credit", label:"课程学分"},
	        	            {key:"assessment_method", label:"考核方式"},
	        	            /* {key:"remarks", label:"课程说明"}, */
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
	            template:"{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}　每页显示{RowsPerPageDropdown}行",  
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

function getAllCourse(){
	(function () {
		var timestamp = (new Date()).valueOf();  
	    var myDataSource = new YAHOO.util.DataSource("CurseTableDataAll?timestamp="+timestamp+"&");  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
           resultsList: "records",
           fields: ["curse_name","curse_id","special_field_id","rn","curse_credit","assessment_method","remarks"],
           metaFields: {
               totalRecords: "totalRecords",
               paginationRecordOffset : "startIndex",
               sortKey: "sort",
               sortDir: "dir"
           }  
	    };    
	      
	    // 自定义内容格式化方法   
        YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
            var Id=oRecord.getData("curse_id");
            var Name=oRecord.getData("curse_name");
            var	Credit=oRecord.getData("curse_credit"); 
            var	Method=oRecord.getData("assessment_method"); 
            var	Explain=oRecord.getData("remarks"); 
            Explain=Explain.trim();
            elCell.innerHTML="<a href='#' name='"+Id+"' onclick='DeleteRow(this)'>删除</a> "
            	+"| <a href='#' name='"+Id+"' onclick=\"ModifyRow('"+Id+"','"+Name+"','"+Credit+"','"+Method+"','"+Explain+"')\">修改</a>";
        	//alert(elCell.innerHTML);
        }  
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('curse_id')
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
	        	            {key:"curse_id", label:"课程编号"},
	        	            {key:"curse_name", label:"课程名称"},
	        	            {key:"curse_credit", label:"课程学分"},
	        	            {key:"assessment_method", label:"考核方式"},
	        	            /* {key:"remarks", label:"课程说明"}, */
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
	            template:"{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink} 　每页显示{RowsPerPageDropdown}行",  
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
$(document).ready(function(){
	getAllCourse();
});
</script>

<!-- YUI -->
<link rel="stylesheet" type="text/css"
	href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css"
	href="../js/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript"
	src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript"
	src="../js/yui/build/datasource/datasource-beta-min.js"></script>
<script type="text/javascript"
	src="../js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../js/yui/build/json/json-min.js"></script>
<script type="text/javascript"
	src="../js/yui/build/history/history-min.js"></script>
<script type="text/javascript"
	src="../js/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript"
	src="../js/yui/build/datatable/datatable-beta-min.js"></script>


<link rel="stylesheet" type="text/css"
	href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript" src="../js/yui/build/container/container-min.js"></script>
<!-- YUI-END -->


</head>


<body class=" yui-skin-sam">
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a target="mainFrame" href="../functionList/teachingManage.jsp">教学管理</a>&nbsp;&gt;&gt;&nbsp;课程管理<a
			href="CourseM.jsp">&nbsp;&gt;&gt;&nbsp;课程管理</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	
	<div id="act_content2">
		<table style="font-size: 12px" width="100%" height=50px>
			<tr>
				<td width="20" style="border: 0"><img
					src="../examM/image/refer.gif" width="20" height="18" /></td>
				<td width="80" style="border: 0">学院专业:</td>
				<td width="90" style="border: 0"><div id="SpecialField"></div></td>
				<td width="80" style="border: 0;visibility:hidden">课程选择:</td>
				<td style="visibility:hidden"><select name="select" id="SelectCurse">
						<option value="">--请选择--</option>
				</select></td>

				<td width="40" align="right">
					<input type="button" class="button" value="查询" onclick="CheckValidationAndQueryCurse();">
				</td>
				<td width="40" align="right"><input type="button" onclick="BulkDelete();"
					value="批量刪除" class="button" /></td>
				<td></td>
			</tr>
		</table>

		<!-- 修改的弹窗 -->
		<div id="container">
			<div id="panel1" style="z-index: 1999">
				<div class="hd" style="z-index: 2000">修改课程信息</div>
				<div class="bd" style="z-index: 2000">
					<form name="modifyForm" action="CurseUpdateAction"
						id="modifyForm">
						<input type="hidden" name="OldCurseId" id="OldCurseId" />
						<table>
							<tr>
								<td width="300px">课程名称：</td>
								<td><input type="text" name="CurseName" id="CurseName" /></td>
							</tr>
							<tr>
								<td width="300px">课程编号：</td>
								<td><input type="text" name="CurseID" id="CurseID" /></td>
							</tr>
							<tr>
								<td width="300px">课程学分：</td>
								<!-- <td><textarea id="CurseCredit" name="CurseCredit"></textarea></td> -->
								<td><input type="text" name="CurseCredit" id="CurseCredit" /></td>
							</tr>
							<tr>
							    <td width="300px">考核方式: </td>
							    <td><input type="radio" id="RadioYes" checked name="radio" value="kaoshi">考试 
							        <input type="radio" id="RadioNo"  name="radio" value="kaocha">考查</td>
						        </tr>
							<tr>
								<td width="300px">备注说明: </td>
								<td><textarea id="CurseRemarks"
										name="CurseRemarks" rows="5" cols="70"></textarea></td>
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
		getSchoolStructureList();
		loadMenu("SpecialField", SchoolStructure, GetCourseBySpecialfield,
				"College");
	</script>
</body>

	<script>
		//初始化修改信息对话框
		YAHOO.namespace("example.container");
		function init() {
			YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", { width:"600px", visible:false, constraintoviewport:true,close :false } );
			YAHOO.example.container.panel1.render();
		}
		YAHOO.util.Event.addListener(window, "load", init);
	</script>
</html>