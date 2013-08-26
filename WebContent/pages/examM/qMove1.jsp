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
var olduse=null;
var sortlistdata = null;
var new_config_leafid=null;
var refersortleaf=null;
function getsortList() {
	$.ajax({
		type : "post",
		dataType : "json",
		async : false,
		url : "SortConfigAction",
		success : function(result) {
			result = eval(result);
			sortlistdata = result;
		},
		error : function() {
		}
	});
}


//试题库查询获取多级菜单中元素的值
function getMajor(o) {
	$("#jiedian1").empty();
	$("#jiedian1").append(o);
}
function FunctionFullfil() {
	//显示分类列表
	getMajor(this.innerHTML);
	refersortleaf=this.id;//试题库查询分类id获取
}
//判断是否选择分类体系
function verifySort() {
	var options = document.getElementById("SelectSort").options;
	var index = document.getElementById("SelectSort").selectedIndex;
	var selectedOption = options[index];
	if (selectedOption.id == '') {
		alert("请选择分类体系");
		return null;
	}
	return selectedOption.id;
}

//获取分类体系ID
function getSortId() {
	var obj = document.getElementById("sortName"); //定位id
	var index = obj.selectedIndex; // 选中索引
	var text = obj.options[index].text; // 选中文本
	var SortName = obj.options[index].value; // 选中值
	var SortId;
	alert(SortName);
	$.ajax({
		type : "post",
		dataType : "json",
		async : false,
		url : "GetSortIdAction?SortName=" + SortName,
		success : function(result) {
			SortId = result.message;
		},
		error : function() {
		}
	});
	return SortId;
}


//获取用途数据
function getusedata() {
	$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "UseConfigAction",
				success : function(result) {
					$("#selectuse ").empty();
					$("#selectuse").append(
							"<option value=\"\">--请选择--</option>");
					for ( var i = 0; i < result.data.length; i++) {
						if (i == "0") {
							$("#selectuse")
									.append(
											"<option selected id=\""+result.data[i].dictionary_entries_value+
					"\" value=\""+result.data[i].dictionary_entries_value+"\">"
													+ result.data[i].name
													+ "</option>");

						} else {
							$("#selectuse")
									.append(
											"<option id=\""+result.data[i].dictionary_entries_value+
					"\" value=\""+result.data[i].dictionary_entries_value+"\">"
													+ result.data[i].name
													+ "</option>");

						}
					}
				},
				error : function() {
				}
			});

}

//查看试题
function QuesView(id){
	document.location.href="QuesMove.jsp?itembank_id="+id;
}

//显示表格
function	CreateDataTableForSort(){
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
	    var myDataSource = new YAHOO.util.DataSource("ExamSystemManageAction?time="+time);  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
	        resultsList: "records",
	        fields: ['itembank_name','itembank_id','explain','use','curse_id',"rn"],  
	        metaFields: {
	            totalRecords: "totalRecords",
	            paginationRecordOffset : "startIndex",
	            sortKey: "sort",
	            sortDir: "dir"
	        } 
	    };    
	       
	      
	    // 自定义内容格式化方法   
	    YAHOO.widget.DataTable.Formatter.QuesView = function(elCell, oRecord, oColumn, oData){
	        var id=oRecord.getData("itembank_id");
	        var name=oRecord.getData("itembank_name");
	        var use=oRecord.getData("use");
	        var explain=oRecord.getData("explain");
            elCell.innerHTML="<a href='#' onclick=QuesView('"+id+"')>查看试题 </a>";
	    }  
	    
	    //checkbox
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('itembank_id')
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
	                        {key:"itembank_name", label:"名称",resizeable:true},
	                        {key:"itembank_id", label:"标识符"},
	                        {key:"explain", label:"说明"},
	                        {key:"use", label:"用途"},
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



//试题库查询
function refer(){
	//试题库用途值获取
	   var options = document.getElementById("selectuse").options;
		var index = document.getElementById("selectuse").selectedIndex;
		var selectedOption = options[index];
		var referyuse = selectedOption.id;
		var leafnode=null;
		
		//判断是否选择分类
		if(refersortleaf==null){
			alert("请选择分类");
			return;
		}
		if(refersortleaf=="jiedian1"){
			alert("请选择分类");
			return;
		}
		//判断选择的是否为根节点
		
		var if_rootnode=null;
			$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "ReferIfRootnodeAction?refersortleaf="+refersortleaf,
			success : function(result) {
				if_rootnode=result.message;
			},
			error : function() {
			}
		}); 
		
		//判断是否为基本分类体系下节点，节本分类体系试题库是挂在课程下面
		var if_under_defaultsort=null;
	 	$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "ReferIfDefaultsortAction?refersortleaf="+refersortleaf,
			success : function(result) {
				if_under_defaultsort=result.message;
			},
			error : function() {
			}
		}); 
		//根据分类查找所有叶子节点
		leafnode=getleafnode(refersortleaf,sortlistdata,if_rootnode);//CreateMultiMenu中定义
		//生成查询后表格
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
		    var myDataSource = new YAHOO.util.DataSource("ReferDataSourceAction?refer_leafnode="+leafnode+"&referuse="+referyuse+"&if_under_defaultsort="+if_under_defaultsort);  
		    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
	        resultsList: "records",
	        fields: ['itembank_name','itembank_id','explain','use','curse_id',"rn"],  
	        metaFields: {
	            totalRecords: "totalRecords",
	            paginationRecordOffset : "startIndex",
	            sortKey: "sort",
	            sortDir: "dir"
	        } 
	    };    
	       
	      
	    // 自定义内容格式化方法   
	    YAHOO.widget.DataTable.Formatter.QuesView = function(elCell, oRecord, oColumn, oData){
	        var id=oRecord.getData("itembank_id");
	        var name=oRecord.getData("itembank_name");
	        var use=oRecord.getData("use");
	        var explain=oRecord.getData("explain");
            elCell.innerHTML="<a href='#' onclick=QuesView('"+id+"')>查看试题</a> ";
	    }  
	    
	    //checkbox
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('itembank_id')
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
	                        {key:"itembank_name", label:"名称",resizeable:true},
	                        {key:"itembank_id", label:"标识符"},
	                        {key:"explain", label:"说明"},
	                        {key:"use", label:"用途"},
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
<div style="margin-top: 10px">
	<table width="100%" class="table1">
				<tr>
					<th class="pagehead">选择试题库</th>
				</tr>
				<tr>
					<td>
	<div style="margin-left:45px">
		<table align="left" height=50px  >
			<tr>
				<td width="20"><img src="image/refer.gif" width="20"
					height="18" /></td>
				<td width="">分类选择：</td>
				<td nowrap width="40"><div id="sortconfig"></div></td>
				<td nowrap width="20">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;用途：</td>
				<td nowrap width="20"><select id="selectuse" name="selectuse"></select></td>
				<td><input type="button" value="查询" class="button" onclick="refer()"></td>
			</tr>
		</table>
		</div>
		
		</td>
		</tr>
		<tr><td>
		<div id="serverintegration" style="margin-left:45px"></div>
		<div id="dt-pag-nav"></div>
	</td></tr>
		</table>
</div>
	<script type="text/javascript">
	YAHOO.namespace("example.container");
	CreateDataTableForSort();
		//初始化修改信息对话框
	</script>
	
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
		<script>
		getsortList();
		loadMenu1("sortconfig", sortlistdata, FunctionFullfil, "jiedian1");
		getusedata();
	
	</script>
</body>
</html>