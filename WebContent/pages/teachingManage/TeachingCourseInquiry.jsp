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
var	param="";

var MyDepartmentId=null

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


//生成教师机构多级菜单
var	TeacherDepartmentNodes=null;
function	getTeacherDepartList(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "TeacherDepartmentOptionsGetAction",
		success : function(result) {
			TeacherDepartmentNodes=result.data;
		},
		error:function(){
		}
	});
}
function	GetTeacherByTeacherDepartment(){
	$("#TeacherDepartment").html($(this).html());
//	alert(this.id);
	getTeacherList(this);
}
function getTeacherList(o){
	var	SelectedDepartmentId	=	o.id;
	MyDepartmentId   =    SelectedDepartmentId;
//	alert(MyDepartmentId);
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "TeacherSelectByDepartmentId",
		data:"DepartmentId="+SelectedDepartmentId,
		success : function(result) {
			$("#SelectTeacher ").empty();
			$("#SelectTeacher").append("<option value=\"\">--请选择--</option>");
			TeacherList	=	result.data;
			for(var	i=0;i<result.data.length;i++){
				$("#SelectTeacher").append("<option value=\""
						+result.data[i].school_id+"\">"+result.data[i].name+"  "
						+result.data[i].school_id+"</option>");
			}
			if(result.data.length==0)
			{
				param="";
			}
			else
			{
				for(var	i=0;i<result.data.length;i++){
					if(i==0){
						param+=result.data[i].school_id;
					}
					else{
						param+=" "+result.data[i].school_id;
					}
				}
			}
		},
		error:function(){
		}
	});
}
function EndSelectTeacher(e){
	document.getElementById("TeacherId").value=e.value;
}


function CheckValidationAndQueryTeachingCourse(){
	var	TeacherId	= document.getElementById("TeacherId").value;
	if((TeacherId=="")&&(MyDepartmentId==null)){
		alert("请选择教师所在机构，或者选择/填入教师教工号");
		return;
	}
	if(TeacherId!="")
	{
		SpecialFieldId	=	TeacherId;
		CreateTeachingCourseTableByTeacherId(SpecialFieldId);
	}
	else
	{
		CreateTeachingCourseTableBySerialsTeacherIds(param);
	}
}
//修改一条记录,将参数传入修改对话框
function ModifyRow(Id,Name,Version,Author,Date,House,Code){
    document.getElementById("OldTeachingMaterialId").value=Id;
    //alert(document.getElementById("OldTeachingMaterialId").value);
    document.getElementById("TeachingMaterialName").value=Name;
    document.getElementById("TeachingMaterialID").value=Id;
    document.getElementById("TeachingMaterialVersion").value=Version;
    
    var	AuthorNew	=	Author.replace(/n1/g,"\n");
    AuthorNew=AuthorNew.replace(/n2/g,"n");
    document.getElementById("TeachingMaterialAuthor").value=AuthorNew;
    
    document.getElementById("TeachingMaterialDate").value=Date;
    
    var	HouseNew	=	House.replace(/n1/g,"\n");
    HouseNew=HouseNew.replace(/n2/g,"n");
    document.getElementById("TeachingMaterialHouse").value=HouseNew;
    
    var	CodeNew	=	Code.replace(/n1/g,"\n");
    CodeNew=CodeNew.replace(/n2/g,"n");
    document.getElementById("TeachingMaterialCode").value=CodeNew;
    
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
function CreateTeachingCourseTableByTeacherId(SpecialFieldId){
	(function () {
		var timestamp = (new Date()).valueOf();  
	    var myDataSource = new YAHOO.util.DataSource("TeachingCourseTableDataSelectByTeacherIdAction?TeacherId="+SpecialFieldId+"&timestamp="+timestamp+"&");  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
           resultsList: "records",
           fields: ["teacher_id","teachername","teachercenter","rn","curse_id","cursename","curse_class_id","class_name","membercount"],
           metaFields: {
               totalRecords: "totalRecords",
               paginationRecordOffset : "startIndex",
               sortKey: "sort",
               sortDir: "dir"
           }  
	    };    
	      
	    // 自定义内容格式化方法   
        YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
        	var Id=oRecord.getData("teaching_material_id");
            var Name=oRecord.getData("name");
            var	Version=oRecord.getData("version"); 
            var	Author=oRecord.getData("author"); 
            var Date=oRecord.getData("publication_date"); 
            var House=oRecord.getData("publishing_house"); 
            var Code=oRecord.getData("isbn_code"); 
            elCell.innerHTML="<a href='#' name='"+Id+"' onclick=\"ModifyRow('"+Id+"','"+Name+"','"+Version+"','"+Author+"','"+Date+"','"+House+"','"+Code+"')\">详细</a>";
        	//alert(elCell.innerHTML);
        }  
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('teaching_material_id')
              + "\" type=\"checkbox\"" + bChecked
              + " class=\"yui-dt-checkbox\" />";
        }
	    /** 
	     * 这里是要显示的列的定义，这里定义了多少个key,页面就会显示多少列 
	     * 要显示远程内容的那些列的key值与上文中fields里的内容相对应 
	     * sortable设置本列是否可以点击列头排序 
	     */  
	    var myColumnDefs = [  
//	        	           	{key:"check",label:"",formatter:"checkbox"},  
	        	           	{key:"rn", label:"行号"},
	        	            {key:"teacher_id", label:"教师编号"},
	        	            {key:"teachername", label:"教师姓名"},
	        	            {key:"teachercenter", label:"教研中心"},
	        	            {key:"cursename", label:"教授课程"},
	        	            {key:"class_name", label:"授课班级"},
	        	            {key:"membercount", label:"班级人数"},
	        	            //{key:'操作', lable:"",formatter:"Delete"}
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

function CreateTeachingCourseTableBySerialsTeacherIds(param){
	(function () {
		var timestamp = (new Date()).valueOf();  
	    var myDataSource = new YAHOO.util.DataSource("TeachingCourseTableDataSelectBySerialsTeacherIdsAction?TeacherIds="+param+"&timestamp="+timestamp+"&");  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
           resultsList: "records",
           fields: ["teacher_id","teachername","teachercenter","rn","curse_id","cursename","curse_class_id","class_name","membercount"],
           metaFields: {
               totalRecords: "totalRecords",
               paginationRecordOffset : "startIndex",
               sortKey: "sort",
               sortDir: "dir"
           }  
	    };    
	      
	    // 自定义内容格式化方法   
        YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
        	var Id=oRecord.getData("teaching_material_id");
            var Name=oRecord.getData("name");
            var	Version=oRecord.getData("version"); 
            var	Author=oRecord.getData("author"); 
            var Date=oRecord.getData("publication_date"); 
            var House=oRecord.getData("publishing_house"); 
            var Code=oRecord.getData("isbn_code"); 
            elCell.innerHTML="<a href='#' name='"+Id+"' onclick=\"ModifyRow('"+Id+"','"+Name+"','"+Version+"','"+Author+"','"+Date+"','"+House+"','"+Code+"')\">详细</a>";
        	//alert(elCell.innerHTML);
        }  
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('teaching_material_id')
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
	        	            {key:"teacher_id", label:"教师编号"},
	        	            {key:"teachername", label:"教师姓名"},
	        	            {key:"teachercenter", label:"教研中心"},
	        	            {key:"cursename", label:"教授课程"},
	        	            {key:"class_name", label:"授课班级"},
	        	            {key:"membercount", label:"班级人数"},
	        	            //{key:'操作', lable:"",formatter:"Delete"}
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
		<a target="mainFrame" href="../functionList/teachingManage.jsp">教学管理</a>&nbsp;&gt;&gt;&nbsp;授课管理<a href="TeachingCourseInquiry.jsp">&nbsp;&gt;&gt;&nbsp;查看教学课程</a></div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div id="act_content2">
		<table style="font-size: 12px" width="100%" height=50px>
			<tr>
				<td width="20" style="border: 0"><img src="../examM/image/refer.gif"
					width="20" height="18" /></td>
					<!-- <td width="80" style="border: 0">学院专业：</td>
					<td width="90" style="border: 0"><div id="SpecialField"></div></td> -->
					<td width="80" style="border: 0">教师机构：</td>
					<td width="90" style="border: 0"><div id="SelectTeacherDepartment"></div></td>
					
					<!-- <td width="80" style="border: 0">课程选择：</td>
					<td><select name="select" id="SelectCurse">
                      <option value="">--请选择--</option>
                    </select></td> -->
                    <td width="80" style="border: 0">教师选择：</td>
					<td><select name="select" id="SelectTeacher" onchange="EndSelectTeacher(this);">
                      <option value="">--请选择--</option>
                    </select></td>
                    
                    <td width="80">教师教工号：</td>
					<td><input type="text" name="TeacherId" id="TeacherId" /></td>
                    
				    <td   width="40" align="right">
				  	<input type="button" class="button" value="查询" onclick="CheckValidationAndQueryTeachingCourse();">
				    </td>
				 
			</tr>
		</table>
		<!-- 修改的弹窗 -->
		<div id="container">
			<div id="panel1" style="z-index: 1999">
				<div class="hd" style="z-index: 2000">查看教材信息</div>
				<div class="bd" style="z-index: 2000">
					<form name="modifyForm" action="TeachingMaterialUpdateAction"
						id="modifyForm">
						<input type="hidden" name="OldTeachingMaterialId" id="OldTeachingMaterialId" />
						<table>
							<tr>
								<td width="100px">教材名称：</td>
								<td><input type="text" name="TeachingMaterialName" id="TeachingMaterialName" /></td>
							</tr>
							<tr>
								<td width="100px">教材编号：</td>
								<td><input type="text" name="TeachingMaterialID" id="TeachingMaterialID" /></td>
							</tr>
							<tr>
								<td width="100px">教材版本：</td>
								<td><input type="text" name="TeachingMaterialVersion" id="TeachingMaterialVersion" /></td>
							</tr>
							<tr>
								<td width="100px">教材作者：</td>
								<!-- <td><textarea id="TeachingMaterialAuthor" name="TeachingMaterialAuthor"></textarea></td> -->
								<td><input type="text" name="TeachingMaterialAuthor" id="TeachingMaterialAuthor" /></td>
							</tr>
							<tr>
								<td width="100px">出版日期：</td>
								<td><input type="text" name="TeachingMaterialDate" id="TeachingMaterialDate" /></td>
							</tr>
							<tr>
								<td width="100px">出版单位：</td>
								<td><input type="text" name="TeachingMaterialHouse" id="TeachingMaterialHouse" /></td>
							</tr>
							<tr>
								<td width="100px">ISBN码：</td>
								<td><input type="text" name="TeachingMaterialCode" id="TeachingMaterialCode" /></td>
							</tr>
						</table>
						<div align="center">
							<input type="button" value="确定" onclick="ClearFade();" />
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
			/* getSchoolStructureList();
			loadMenu("SpecialField", SchoolStructure, GetCourseBySpecialfield,
					"College"); */
			
			//加载教师机构多级菜单
			getTeacherDepartList();
			loadMenu("SelectTeacherDepartment",TeacherDepartmentNodes,GetTeacherByTeacherDepartment,"TeacherDepartment");
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