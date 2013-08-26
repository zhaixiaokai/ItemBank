<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Server-side Pagination and Sorting, with Browser History Manager</title>

<style type="text/css">
/*margin and padding on body element
  can introduce errors in determining
  element position and are not recommended;
  we turn them off as a foundation for YUI
  CSS treatments. */
body {
	margin:0;
	padding:0;
}
</style>

<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/build/datasource/datasource-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../js/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="../js/yui/build/history/history-min.js"></script>
<script type="text/javascript" src="../js/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/datatable/datatable-beta-min.js"></script>


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
<script>
function NextStep(){
	self.parent.frames["mainFrame"].location="Preview1_autom.jsp";
}
</script>
<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}
</style>

<!--begin custom header content for this example-->
<style type="text/css">
/* custom styles for this example */
#dt-pag-nav { margin-bottom:1ex; width: 900px} /* custom pagination UI */
#dt-pag-nav a {
    color: #00c;
    text-decoration: underline;
}
#yui-history-iframe {
  position:absolute;
  top:0; left:0;
  width:1px; height:1px; /* avoid scrollbars */
  visibility:hidden;
}
</style>

<!--end custom header content for this example-->

</head>

<body class=" yui-skin-sam">

<!--BEGIN SOURCE CODE FOR EXAMPLE =============================== -->

<input id="yui-history-field" type="hidden">
<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

<div id="serverintegration"></div>
<div id="dt-pag-nav"></div>
<script type="text/javascript">
function	fun(o){
	alert(o.id);
}
(function () {
    var History = YAHOO.util.History,
        myPaginator,  // to hold the Paginator instance
        myDataSource, // to hold the DataSource instance
        myDataTable;  // to hold the DataTable instance

    // function to generate a query string for the DataSource.  Also used
    // as the state indicator for the History Manager
    var generateStateString = function (start,key,dir) {
        start = start || 0;
        key   = key || 'id';
        dir   = dir || 'asc';
        return "results=15&startIndex="+start+"&sort="+key+"&dir="+dir;
    };

    // function to extract the key values from the state string
    var parseStateString = function (state) {
        return {
            results    : 15,
            startIndex : /\bstartIndex=(\d+)/.test(state) ? parseInt(RegExp.$1) : 0,
            sort       : /\bsort=(\w+)/.test(state)       ? RegExp.$1 : 'id',
            dir        : /\bdir=(\w+)/.test(state)        ? RegExp.$1 : 'asc'
        };
    };

    // function used to intercept pagination requests
    var handlePagination = function (state,datatable) {
        var sortedBy  = datatable.get('sortedBy');

        var newState = generateStateString(
                            state.recordOffset,
                            sortedBy.key,
                            sortedBy.dir);
		alert(newState);
        History.navigate("myDataTable",newState);
        alert("fuck");
    };

    // function used to intercept sorting requests
    var handleSorting = function (oColumn) {
        // Which direction
        var sDir = "asc";

        // Already sorted?
        if(oColumn.key === this.get("sortedBy").key) {
            sDir = (this.get("sortedBy").dir === "asc") ?
                    "desc" : "asc";
        }

        var newBookmark = generateStateString(0, oColumn.key, sDir);

        YAHOO.util.History.navigate("myDataTable", newBookmark);
    };

    var handleHistoryNavigation = function (state) {
        // Create a payload to pass through the DataSource request to the
        // handler
        var parsed = parseStateString(state);

        // Use the DataTable's baked in server-side pagination handler
        myDataSource.sendRequest(state,{
                success  : myDataTable.onDataReturnSetRows,
                failure  : myDataTable.onDataReturnSetRows,
                scope    : myDataTable
        });
    };

    var initialState = History.getBookmarkedState('myDataTable') ||
                       generateStateString(0,'id','asc');

    History.register('myDataTable',initialState, handleHistoryNavigation);

    History.onReady(function() {
        // Pull the state from the History Manager, or default from the
        // initial state.  Parse the state string into an object literal.
        var initialRequest = History.getCurrentState('myDataTable') ||
                             initialState,
            state          = parseStateString(initialRequest);

        // Create the DataSource
        myDataSource = new YAHOO.util.DataSource("GetTableDataAction?");
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
        myDataSource.responseSchema = {
            resultsList: "records",
            fields: ["id","name","date","price","rn"],
            metaFields: {
                totalRecords: "totalRecords",
                paginationRecordOffset : "startIndex",
                sortKey: "sort",
                sortDir: "dir"
            }
        };

        // Column definitions
        var myColumnDefs = [
			//{key:"Select", label:"<input type='checkbox' id='SelectAll'>全选",  formatter:"checkbox"}, 
			//{key:'', formatter:YAHOO.widget.DataTable.formatCheckbox},
           // {key:"id", label:"ID", sortable:true,resizeable:true},
           	{key:"check",label:"",formatter:"checkbox"},  
           	{key:"rn", label:"行号",resizeable:true},
            {key:"name", label:"姓名",resizeable:true},
            {key:"id", label:"ID",resortable:true,sortable:true},
            {key:"price", label:"Price",width:300},//sortable:true
            {key:'操作', lable:"",formatter:"Delete"}
        ];

         YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
             var id=oRecord.getData("id");
             elCell.innerHTML='<a href="#" mce_href="#">删除</a> | <a href="#" mce_href="#">修改</a>';;
         } 
        // Create the DataTable configuration and Paginator using the state
        // information we pulled from the History Manager
        myPaginator = new YAHOO.widget.Paginator({
            rowsPerPage : state.results,
            recordOffset : state.startIndex,
            lastPageLinkLabel:"末页",  
            firstPageLinkLabel:"首页",  
            previousPageLinkLabel:"上一页",  
            nextPageLinkLabel:"下一页",  
            containers : ['dt-pag-nav'],
            template : "{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink} 跳转到{JumpToPageDropdown}页　每页显示{RowsPerPageDropdown}行",  
            pageReportTemplate : "Showing items {startIndex} - {endIndex} of {totalRecords}"
        });

        var myConfig = {
            paginator : myPaginator,
            paginationEventHandler : handlePagination,
            // generateRequest : generateStateString, // moot
            sortedBy : {
                key : state.sort,
                dir : state.dir
            },
            initialRequest : initialRequest
        };

        // Instantiate DataTable
        myDataTable = new YAHOO.widget.DataTable(
            "serverintegration", // The dom element to contain the DataTable
            myColumnDefs,        // What columns will display
            myDataSource,        // The DataSource for our records
            myConfig             // Other configurations
        );
     // 为表格中的checkbox绑定事件，记录被选择的行内容的id。   
        myDataTable.subscribe("checkboxClickEvent", function(oArgs){   
            var elCheckbox = oArgs.target;   
            var oRecord = this.getRecord(elCheckbox);  
            var id = oRecord.getData('rn');  
            if(elCheckbox.checked){  
                alert("你选择了第" + id + "行");  
            }else{  
                alert("你取消选择了第" + id + "行");  
            }  
        }); 
        // Listen to header link clicks to sort the column
//        myDataTable.subscribe('theadCellClickEvent', myDataTable.onEventSortColumn);
        //选中行事件
        //myDataTable.subscribe("rowClickEvent",myDataTable.onEventSelectRow);
        
        
     // Enables cell-block selection		通过shift选择时只选中相应列
     //   myDataTable.set("selectionMode", "cellblock");
     //   myDataTable.subscribe("cellClickEvent", myDataTable.onEventSelectCell);
         
        // Enables cell-range selection			通过shift选择时会将不同行的数据全选中
     //   myDataTable.set("selectionMode", "cellrange");
     //   myDataTable.subscribe("cellClickEvent", myDataTable.onEventSelectCell);
         
        // Enables cell-block selection (modifier keys are disabled)		不支持多选
     //   myDataTable.set("selectionMode", "singlecell");
     //   myDataTable.subscribe("cellClickEvent", myDataTable.onEventSelectCell); 
        		

        // Override the DataTable's sortColumn method with our intercept handler
/*         myDataTable.sortColumn = handleSorting;
         myDataTable.on('theadCellClickEvent', function (oArgs) {
        	alert("aaa");
    		var target = oArgs.target;
    		alert(target);
    			column = this.getColumn(target);
    			alert(column[0]);
    			//actualTarget = Event.getTarget(oArgs.event);
    			//alert(actualTarget);
    			check = true;
    			alert(check);
    			alert(column.key);
    		if (column.key == 'Select') {
    			alert("hel");
    			this.forAllRecords(function (r) {
    				r.setData('Select',check);
    			});
    			this.render();
    		}
    	});  */
        // Add the example objects to the YAHOO.example namespace for inspection
        YAHOO.example.ServerIntegration = {
            myPaginator  : myPaginator,
            myDataSource : myDataSource,
            myDataTable  : myDataTable
        };
    });

	
    YAHOO.util.History.initialize("yui-history-field", "yui-history-iframe");
})();
</script>

<!--END SOURCE CODE FOR EXAMPLE =============================== -->


<!-- 右侧界面完 -->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>