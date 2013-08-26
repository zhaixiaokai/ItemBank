<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>

<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/build/datasource/datasource-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../js/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="../js/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/datatable/datatable-beta-min.js"></script>
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<link rel="stylesheet" type="text/css" href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript"	src="../js/yui/build/container/container-min.js"></script>
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
	filter: alpha(opacity =   80);
}
</style>
<script type="text/javascript">
var	IbArray	=	new	Array();
var	QuesArray	=	new	Array();
function getDiff(){
	//加载难度选项
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetDifficultyOptionAction",
		success : function(result) {
			$("#SelectDifficulty ").empty();
			$("#SelectDifficulty").append("<option value=\"\">--请选择--</option>");
			var	difficultySelectInnerHTML="<option id=\"difficultyDefaultSelect\" value=\"\">--请选择--</option>";
			for(var	i=0;i<result.data.length;i++){
				$("#SelectDifficulty").append("<option id=\""+result.data[i].dictionary_entries_value
						+"\" value=\""+result.data[i].dictionary_entries_value+"\">"+result.data[i].name+"</option>");
			}
		},
		error:function(){
		}
	});
}
function getType(){
	//加载试题类型选项
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetQuesUseOptionAction",
		success : function(result) {
			$("#SelectQuesType").empty();
			$("#SelectQuesType").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectQuesType").append("<option id=\""+result.data[i].dictionary_entries_value+
						"\" value=\""+result.data[i].dictionary_entries_value+"\">"+
						result.data[i].name+"</option>");
			}
		},
		error:function(){
		}
	});		
}
function getKPList(){
	var	KPList	=	'<%=request.getParameter("selectedKP")%>';
	var	KPArray	=	KPList.split(",");
	//加载知识点信息
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "KPInfoGetByKPId",
		data:"KPIds="+KPList,
		success : function(result) {
			$("#selectKP").empty();
			$("#selectKP").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#selectKP").append("<option id=\""+result.data[i].knowledge_point_id+
						"\" value=\""+result.data[i].knowledge_point_id+"\">"+
						result.data[i].name+"</option>");
			}
		},
		error:function(){
		}
	});	
	return KPList;
}
function getIBList(){
	var	KPList	=	'<%=request.getParameter("selectedIB")%>';
	return KPList;
}

function	CreateDataTableForQues(itembank_id,kp_id){
/* 	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "QuesViewTableSourceAction?IBIds="+itembank_id,
		success : function(result) {
			tabledata=result;
		},
		error:function(){
		}
	});	 */	
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
	   var time=new Date();  
	    //var myDataSource = new YAHOO.util.DataSource(tabledata);  
	    var myDataSource = new YAHOO.util.DataSource("QuesGetByIBIds?IBIds="+itembank_id+"&KPIds="+kp_id+"&time="+time);  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
	        resultsList: "records",
	        fields: ['question_type','difficulty','knowledge_point_id','name','question_id','defaultpoint','time_use',"rn","ibid"],  
	        metaFields: {
	            totalRecords: "totalRecords",
	            paginationRecordOffset : "startIndex",
	            sortKey: "sort",
	            sortDir: "dir"
	        } 
	    };    
	       
	      
	    // 自定义内容格式化方法   
	    YAHOO.widget.DataTable.Formatter.QuesAdd = function(elCell, oRecord, oColumn, oData){
	    	 var id=oRecord.getData("question_id");
	    	 var ibid=oRecord.getData("ibid");
	  	   //     var name=oRecord.getData("itembank_name");
	  	    //    var use=oRecord.getData("use");
	  	    //    var explain=oRecord.getData("explain");
	              elCell.innerHTML="<a href='#' onclick=QuesView('"+ibid+"','"+id+"')>查看 </a>"+"|"+"<a href='#' onclick=add('"+ibid+"','"+id+"')> 添加</a>";
	    }  
	    
	    //checkbox
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('question_id')
              + "\" type=\"checkbox\"" + bChecked
              + " class=\"yui-dt-checkbox\" />";
        }
	    /** 
	     * 这里是要显示的列的定义，这里定义了多少个key,页面就会显示多少列 
	     * 要显示远程内容的那些列的key值与上文中fields里的内容相对应 
	     * sortable设置本列是否可以点击列头排序 
	     */  
	    var myColumnDefs = [  
	                        //{key:"check",label:"",formatter:"checkbox"},  
	                       	{key:"rn", label:"行号",resizeable:true},
	                     //   {key:"question_id", label:"试题内容",resizeable:true},
	                        //{key:"knowledge_point_id", label:"知识点"},
	                        {key:"difficulty", label:"难度"},
	                        {key:"question_type", label:"题型"},
	                        {key:"defaultpoint", label:"默认分值"},
	                        {key:"time_use", label:"答题 时长(秒)"},
	                        {key:"name", label:"知识点"},
	                   //     {key:"itembank", label:"试题库"},
	                        {key:'操作', lable:"",formatter:"QuesAdd"}
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
	            rowsPerPageOptions : [ 5,15, 30, 45, 60]}),
	        paginationEventHandler:	YAHOO.widget.DataTable.handleDataSourcePagination
	    };  
	    // 新建一个表格，第一个参数是你页面上的div的id.表格会显示在那个div中。   
	    YAHOO.example.container.myDataTable2 = new YAHOO.widget.DataTable("serverintegration", myColumnDefs, myDataSource, myConfigs);  
	     
	    return {   
	   　    ds: myDataSource,   
	        dt: myDataTable   
	    }; 
	})();
}

//按条件查询时生成的表格
function reffer(itembank_id,kp_id,dif,type,kp){
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
	   var time=new Date();  
	    //var myDataSource = new YAHOO.util.DataSource(tabledata);  
	    var myDataSource = new YAHOO.util.DataSource("QuesMultiGetByDifTypKP?IBIds="+itembank_id+"&KPIds="+kp_id+"&dif="+dif+"&type="+type+"&kp="+kp+"&time="+time+"&");  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
	        resultsList: "records",
	        fields: ['question_type','difficulty','knowledge_point_id','name','question_id','defaultpoint','time_use',"rn","ibid"],  
	        metaFields: {
	            totalRecords: "totalRecords",
	            paginationRecordOffset : "startIndex",
	            sortKey: "sort",
	            sortDir: "dir"
	        } 
	    };    
	       
	      
	    // 自定义内容格式化方法   
	    YAHOO.widget.DataTable.Formatter.QuesRef = function(elCell, oRecord, oColumn, oData){
	    	 var id=oRecord.getData("question_id");
	    	 var ibid=oRecord.getData("ibid");
		  	   //     var name=oRecord.getData("itembank_name");
		  	    //    var use=oRecord.getData("use");
		  	    //    var explain=oRecord.getData("explain");
		              elCell.innerHTML="<a href='#' onclick=QuesView('"+ibid+"','"+id+"')>查看 </a>"+"|"+"<a href='#' onclick=add('"+ibid+"','"+id+"')> 添加</a>";
	    }  
	    
	    /** 
	     * 这里是要显示的列的定义，这里定义了多少个key,页面就会显示多少列 
	     * 要显示远程内容的那些列的key值与上文中fields里的内容相对应 
	     * sortable设置本列是否可以点击列头排序 
	     */  
	    var myColumnDefs = [  
	                        //{key:"check",label:"",formatter:"checkbox"},  
	                       	{key:"rn", label:"行号",resizeable:true},
	                     //   {key:"question_id", label:"试题内容",resizeable:true},
	                        //{key:"knowledge_point_id", label:"知识点"},
	                        {key:"difficulty", label:"难度"},
	                        {key:"question_type", label:"题型"},
	                        {key:"defaultpoint", label:"默认分值"},
	                        {key:"time_use", label:"答题 时长(秒)"},
	                        {key:"name", label:"知识点"},
	                   //     {key:"itembank", label:"试题库"},
	                        {key:'操作', lable:"",formatter:"QuesRef"}
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
	            rowsPerPageOptions : [ 5,15, 30, 45, 60]}),
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

function QuesView(ibid,id){
	var htmlName=null;
	$.ajax( {
		type : "post",
		dataType : "text",
		async : false,
		url : "QuesContentAction2?questionid="+id+"&itembank_id="+ibid,
		success : function(result) {
			htmlName=result;
		},
		error:function(){
		}
	});
	var currentPath=parent.window.frames["mainFrame"].location.href;
	//字符串截取
	var tempString=currentPath;
	var string="/";
	var position=0;
	while( (  pos = tempString.indexOf( "/" ) ) != -1 ){
		tempString = tempString.substr( pos + string.length, tempString.length );
		position+=pos+string.length;
	}
	//var a=ss.indexOf('/');
	var FilePath=currentPath.substr(0,position);
	FilePath+=htmlName+'.html';
	window.open(FilePath,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no'); 
}
$(document).ready(function (){
	YAHOO.namespace("example.container");
	getDiff();
	getType();
	var kpList=getKPList();
	var	iblist=getIBList();		
	function init() {
		YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", { width:"600px", visible:false, constraintoviewport:true,close :false } );
		YAHOO.example.container.panel1.render();
	}
	YAHOO.util.Event.addListener(window, "load", init);
	CreateDataTableForQues(iblist,kpList);
	$("#query").bind('click',function(){
 		if($("#SelectDifficulty").val()==""&&$("#selectKP").val()==""&&$("#SelectQuesType").val()==""){
			alert("请至少选一项筛选条件");
			return;
		}
		reffer(iblist,kpList,$("#SelectDifficulty").val(),$("#SelectQuesType").val(),$("#selectKP").val());
		
	});
	$("#AddedQues").bind('click',function(){
		getSelectedQuesList();
		DispContainer();
	});
	$("#confirm").bind('click',function(){
		ClearFade();
	});
	$("#zujuan").bind('click',function(){
		SubmitZujuan();
	});
});
function add(ibid,id){
	for(var i=0;i<QuesArray.length;i++){
		if(QuesArray[i]==id){
			alert("已经添加过！");
			return;
		}
	}
	IbArray.push(ibid);
	QuesArray.push(id);
	alert("添加成功");
}
function del(ibid,id){
	for(var i=0;i<QuesArray.length;i++){
		if(QuesArray[i]==id){
			QuesArray.splice(i,1);
			IbArray.splice(i,1);
			alert("删除成功");
		}
	}
	getSelectedQuesList();
}
//清除屏幕锁定，并且隐藏修改框 
function ClearFade(){
	document.getElementById("fade").style.display="none";
	YAHOO.example.container.panel1.hide();
}
function DispContainer(){
	  //根据客户端浏览器高度设置半透明罩的高度 
	  document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
	  document.getElementById('fade').style.display='block';
	  YAHOO.example.container.panel1.show();
}
function SubmitZujuan(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "AutoPaperOnHandAction?IDS="+QuesArray+"&IBS="+IbArray,
		success : function(result) {
			alert(result.text);
			if(result.result=="fail") return;
			var FilePath="../../"+result.dir+"/paperDoc.doc";
			window.open(FilePath,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no'); 
		},
		error:function(){
			alert("未知错误 ");
		}
	});
}
function getSelectedQuesList(){
		//url : "KPInfoGetByKPId&KPIds="+QuesArray+"&IBIds"+IbArray,
(function () {
		var History = YAHOO.util.History,
	     myPaginator,  // to hold the Paginator instance
	     myDataSource, // to hold the DataSource instance
	     myDataTable1;  // to hold the DataTable instance
	     var generateStateString = function (start,key,dir) {
	         start = start || 0;
	         key   = key || 'sno';
	         dir   = dir || 'asc';
	         return "results=15&startIndex="+start+"&sort="+key+"&dir="+dir;
	     };
	   var time=new Date();  
	    //var myDataSource = new YAHOO.util.DataSource(tabledata);  
	    var myDataSource = new YAHOO.util.DataSource("AddedQuesGetByIBIds?KPIds="+QuesArray+"&IBIds="+IbArray+"&time="+time);  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
	        resultsList: "records",
	        fields: ['question_type','difficulty','knowledge_point_id','name','question_id','defaultpoint','time_use',"rn","ibid"],  
	        metaFields: {
	            totalRecords: "totalRecords",
	            paginationRecordOffset : "startIndex",
	            sortKey: "sort",
	            sortDir: "dir"
	        } 
	    };    
	       
	      
	    // 自定义内容格式化方法   
	    YAHOO.widget.DataTable.Formatter.QuesView = function(elCell, oRecord, oColumn, oData){
	    	 var id=oRecord.getData("question_id");
	    	 var ibid=oRecord.getData("ibid");
		  	   //     var name=oRecord.getData("itembank_name");
		  	    //    var use=oRecord.getData("use");
		  	    //    var explain=oRecord.getData("explain");
		              elCell.innerHTML="<a href='#' onclick=QuesView('"+ibid+"','"+id+"')>查看 </a>"+"|"+"<a href='#' onclick=del('"+ibid+"','"+id+"')> 删除</a>";
	    }  
	    
	    //checkbox
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('question_id')
              + "\" type=\"checkbox\"" + bChecked
              + " class=\"yui-dt-checkbox\" />";
        }
	    /** 
	     * 这里是要显示的列的定义，这里定义了多少个key,页面就会显示多少列 
	     * 要显示远程内容的那些列的key值与上文中fields里的内容相对应 
	     * sortable设置本列是否可以点击列头排序 
	     */  
	    var myColumnDefs = [  
	                        //{key:"check",label:"",formatter:"checkbox"},  
	                       	{key:"rn", label:"行号",resizeable:true},
	                     //   {key:"question_id", label:"试题内容",resizeable:true},
	                        //{key:"knowledge_point_id", label:"知识点"},
	                        {key:"difficulty", label:"难度"},
	                        {key:"question_type", label:"题型"},
	                        {key:"defaultpoint", label:"默认分值"},
	                        {key:"time_use", label:"答题 时长(秒)"},
	                        {key:"name", label:"知识点"},
	                   //     {key:"itembank", label:"试题库"},
	                        {key:'操作', lable:"",formatter:"QuesView"}
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
	            containers : ['dt-pag-nav1'],
	            // 自定义翻页器内容   
	            template:"{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink} 跳转到{JumpToPageDropdown}页　每页显示{RowsPerPageDropdown}行",  
	            // 每页显示多少行的可选值   
	            rowsPerPageOptions : [ 5,15, 30, 45, 60]}),
	        paginationEventHandler:	YAHOO.widget.DataTable.handleDataSourcePagination
	    };  
	    // 新建一个表格，第一个参数是你页面上的div的id.表格会显示在那个div中。   
	    YAHOO.example.container.myDataTable1 = new YAHOO.widget.DataTable("serverintegration1", myColumnDefs, myDataSource, myConfigs);  
	     
	    return {   
	   　    ds: myDataSource,   
	        dt: myDataTable1   
	    }; 
	})();
}
</script>
</head>
<body class=" yui-skin-sam">
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a target="mainFrame" href="../functionList/appManage.jsp">应用管理</a>&nbsp;&gt;&gt;&nbsp;组卷管理<a
			href="selectBase.jsp">&nbsp;&gt;&gt;&nbsp;按章节手动组卷</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table><br>
	<div id="act_content2">
		<form action="" id="myForm">
		<table align="left" width="100%" height=50px style="float:left">
			<tr>
				<td width="20" style="border: 0"><img src="image/refer.gif"
					width="20" height="18" /></td>

				<td width="40" style="border: 0" align="right">难度：</td>
				<td width="60px" align="left">
						<select name="SelectDifficulty" id="SelectDifficulty">
							<option value="">--请选择--</option>
						</select>
				</td>
				<td width="40px" align="right">题型：</td>
				<td width="60px" align="left">
						<select name="SelectQuesType" id="SelectQuesType">
							<option value="">--请选择--</option>
						</select>
				</td>
				<td width="60px" align="right">知识点：</td>
				<td width="100px" align="left"><select id="selectKP"><option value="">--请选择--</option></select></td>
				<td width="30px" align="left"><input type="button" value="查询" id="query" 
					class="button" /></td>
				<td></td>
				<td align="right"><input type="button" value="已添加试题" class="button" id="AddedQues" /></td>
			</tr>
		</table>
		</form>
		<table width="100%" style="float: left;clear: left" >
					<tr ><td>
					<!-- 半透罩，用来锁定屏幕 -->
					<div id="fade" class="black_overlay"></div>
					<div id="container">
						<div id="panel1" style="z-index: 1999">
							<div class="hd" style="z-index: 2000">查看已选试题</div>
							<div class="bd" style="z-index: 2000">
								<div id="serverintegration1" style="margin-left: 45px"></div>
								<div id="dt-pag-nav1"></div>
								<div align="center"><input type="button" class="button" value="返回" id="confirm"><input type="button" class="button" value="确认" id="zujuan"></div>
							</div>
							<div class="ft"></div>
						</div>
					</div>
					<div id="serverintegration" style="margin-left: 45px"></div>
					<div id="dt-pag-nav"></div>
				</td></tr>
		</table>
		
	</div>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>