<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css" href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link rel="stylesheet" type="text/css" href="../css/select.css">
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
	filter: alpha(opacity =   80);
}

</style>
<script>
//获取知识点
var itembank_id='<%=request.getParameter("itembank_id")%>';
function getpointdata(){
//由试题库id查找对应课程id
	var subjectid='';
	$.ajax({
		type : "post",
		dataType : "text",
		async : false,
		url : "GetCurseIdAction?itembank_id="+itembank_id,
		success : function(result) {
			subjectid=result;
		},
		error:function(){
		}
	});		
	//由课程id查找知识点
	$.ajax({
		type : "post",
		dataType : "json",
		async : false,
		url : "GetPointDataAction?leafid="+subjectid,
		success : function(result) {
			$("#selectpoint ").empty();
			$("#selectpoint").append(
					"<option value=\"\" >--请选择--</option>");
			for ( var i = 0; i < result.data.length; i++) {
				
					$("#selectpoint")
							.append(
									"<option id=\""+result.data[i].knowledge_point_id+
			"\" value=\""+result.data[i].name+"\">"
											+ result.data[i].name
											+ "</option>");
				
			}
		},
		error : function() {
		}
	});
}

//获取题型
function getQuesType(){
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "GetQuesUseOptionAction",
			success : function(result) {
				$("#SelectQuesType").empty();
				$("#SelectQuesType").append("<option value=\"\" >--请选择--</option>");
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


//获取试题难度
function getQuesDifficulty(){
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "GetQuesDifficultyOptionAction",
			success : function(result) {
				$("#SelectQuesDifficulty").empty();
				$("#SelectQuesDifficulty").append("<option value=\"\" >--请选择--</option>");
				for(var	i=0;i<result.data.length;i++){
					$("#SelectQuesDifficulty").append("<option id=\""+result.data[i].dictionary_entries_value+
							"\" value=\""+result.data[i].dictionary_entries_value+"\">"+
							result.data[i].name+"</option>");
				}
			},
			error:function(){
			}
		});		
	
}

function QuesView(id){
	var htmlName=null;
	$.ajax( {
		type : "post",
		dataType : "text",
		async : false,
		url : "QuesContentAction?questionid="+id+"&itembank_id="+itembank_id,
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

//创建试题表格
//显示表格
//function	CreateDataTableForQues(){
	/* alert(1);
	alert(itembank_id);
	var tabledata=null;
	//获取表格数据
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "QuesViewTableSourceAction?itembank_id="+itembank_id,
			success : function(result) {
				tabledata=result;
			},
			error:function(){
			}
		});		
	
alert(tabledata); */
//显示表格
var tabledata=null;
function	CreateDataTableForQues(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "QuesViewTableSourceAction?itembank_id="+itembank_id,
		success : function(result) {
			tabledata=result;
		},
		error:function(){
		}
	});		
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
	    var myDataSource = new YAHOO.util.DataSource(tabledata);  
	//    var myDataSource = new YAHOO.util.DataSource("QuesViewTableSourceAction?itembank_id="+itembank_id);  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
	        resultsList: "records",
	        fields: ['question_type','difficulty','knowledge_point_id','question_id','defaultpoint','time_use',"rn"],  
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
	  	   //     var name=oRecord.getData("itembank_name");
	  	    //    var use=oRecord.getData("use");
	  	    //    var explain=oRecord.getData("explain");
	              elCell.innerHTML="<a href='#' onclick=QuesView('"+id+"')>查看试题 </a>";
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
	                     //   {key:"check",label:"",formatter:"checkbox"},  
	                       	{key:"rn", label:"行号",resizeable:true},
	                     //   {key:"question_id", label:"试题内容",resizeable:true},
	                        {key:"knowledge_point_id", label:"知识点"},
	                        {key:"difficulty", label:"难度"},
	                        {key:"question_type", label:"题型"},
	                        {key:"defaultpoint", label:"默认分值(秒)"},
	                        {key:"time_use", label:"答题 时长"},
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




//查询试题
function refer(){
	//知识点
	   var options = document.getElementById("selectpoint").options;
		var index = document.getElementById("selectpoint").selectedIndex;
		var selectedOption = options[index];
		var selectpointid = selectedOption.id;
		
		//判断是否选择知识点
	/* 	if(selectpointid==null||selectpointid=="selectnonepoint"||selectpointid==''){
			alert("请选择知识点");
			return;
		} */
		//题型
	   var typeoptions = document.getElementById("SelectQuesType").options;
		var typeindex = document.getElementById("SelectQuesType").selectedIndex;
		var typeselectedOption = typeoptions[typeindex];
		var selecttypeid = typeselectedOption.id;
		
		/* if(selecttypeid==null||selecttypeid=="selectnonetype"||selecttypeid==''){
			alert("请选择题型");
			return;
		} */
		//难度等级
	   var difficultyoptions = document.getElementById("SelectQuesDifficulty").options;
		var difficultyindex = document.getElementById("SelectQuesDifficulty").selectedIndex;
		var difficultyselectedOption = difficultyoptions[difficultyindex];
		var selectdifficultyid = difficultyselectedOption.id;
		//alert(selectdifficultyid);
		//alert(selecttypeid);
		//alert(selectpointid);
	//	alert(selectdifficultyid);
		
		/* if(selectdifficultyid==null||selectdifficultyid=="selectnonedifficulty"||selectdifficultyid==''){
			alert("请选择难度等级");
			return;
		} */
		//生成查询后表格
		//若查询条件均未选择
		if(selectdifficultyid==""&&selecttypeid==""&&selectpointid==""){
			CreateDataTableForQues();
			return;
		}
		//若选择了条件
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
	//    var myDataSource = new YAHOO.util.DataSource(tabledata);  
    var myDataSource = new YAHOO.util.DataSource("ReferResult_QuesViewTableSourceAction?itembank_id="+itembank_id+"&selectpointid="+selectpointid+"&selecttypeid="+selecttypeid+"&selectdifficultyid="+selectdifficultyid);  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
	        resultsList: "records",
	        fields: ['question_type','difficulty','knowledge_point_id','question_id','defaultpoint','time_use',"rn"],  
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
	  	   //     var name=oRecord.getData("itembank_name");
	  	    //    var use=oRecord.getData("use");
	  	    //    var explain=oRecord.getData("explain");
	              elCell.innerHTML="<a href='#' onclick=QuesView('"+id+"')>查看试题 </a>";
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
	                       // {key:"check",label:"",formatter:"checkbox"},  
	                       	{key:"rn", label:"行号",resizeable:true},
	                     //   {key:"question_id", label:"试题内容",resizeable:true},
	                        {key:"knowledge_point_id", label:"知识点"},
	                        {key:"difficulty", label:"难度"},
	                        {key:"question_type", label:"题型"},
	                        {key:"defaultpoint", label:"默认分值(秒)"},
	                        {key:"time_use", label:"答题 时长"},
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

</script>
<title>Insert title here</title>
</head>
<body class=" yui-skin-sam" >
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="javascript:void(0)">试题管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">查看试题</a>&nbsp;&gt;&gt;&nbsp;查看试题
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div class="div_style2">
		<table height=50px>
			<tr>
				<td width="20"><img src="image/refer.gif" width="20"
					height="18" /></td>
				<td nowrap align="right">知识点:</td><td><select name="selectpoint" id="selectpoint" style="font-size: 12px"></select></td>
				<td width="">&nbsp;&nbsp;&nbsp;题型：</td>
				<td><select name="SelectQuesType" id="SelectQuesType" style="font-size: 12px"><option selected="selected" value="">--请选择--</option></select></td>
				<td width="">难度：</td>
				<td><select name="SelectQuesDifficulty" id="SelectQuesDifficulty" style="font-size: 12px"><option selected="selected" value="">--请选择--</option></select></td>
				<td width=""><input type=button id="button" value="查询" onclick="refer();" class="button"></td>
			</tr>
		</table>
			<div>
		<div id="serverintegration" style="margin-left:45px"></div>
		<div id="dt-pag-nav"></div>
</div>
	</div>
	<script type="text/javascript">
		//初始化修改信息对话框
	</script>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	<script>
	getpointdata();
	getQuesType();
	getQuesDifficulty();
	YAHOO.namespace("example.container");
	CreateDataTableForQues();
	</script>
</body>
</html>