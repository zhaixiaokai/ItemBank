<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/build/datasource/datasource-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../js/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="../js/yui/build/history/history-min.js"></script>
<script type="text/javascript" src="../js/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/datatable/datatable-beta-min.js"></script>
<style type="text/css">

</style>
</head>
<body class=" yui-skin-sam">
<div id="serverintegration"></div>
<div id="dt-pag-nav"></div>

<script type="text/javascript">
YAHOO.example.DynamicData = function() {  
    /** 
     * 设置远程数据源，远程获取json数据。本例中，json的数据内容形如： 
     * {"startIndex":0,"totalRecords":10,"result":[{"id":"1","updatetime":"2011-05-04","status":"1","address":"海珠区","name":"客村二店","brand":"七天连锁酒店","activity":"2"},{...},{...}]} 
     *　翻页时，服务器端返回的startIndex和result的内容随情况变化 
     */   
     var History = YAHOO.util.History,
     myPaginator,  // to hold the Paginator instance
     myDataSource, // to hold the DataSource instance
     myDataTable;  // to hold the DataTable instance
     var generateStateString = function (start,key,dir) {
         start = start || 0;
         key   = key || 'id';
         dir   = dir || 'asc';
         return "results=15&startIndex="+start+"&sort="+key+"&dir="+dir;
     };
     
    var myDataSource = new YAHOO.util.DataSource("GetTableDataAction?");  
    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
    /** 
     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
     */  
    myDataSource.responseSchema = {  
        resultsList: "records",  
        fields: ["id","name","date","price","rn"],  
        metaFields: {   
                totalRecords: "totalRecords",   
                startIndex: "startIndex"   
            }   
    };    
      
    // 自定义内容格式化方法   
    var formatoperation = function(elLiner, oRecord, oColumn, oData) {   
         elLiner.innerHTML = "<a href='#' onclick=''>删除</a>  <a href='#'>审核</a>";  
    }  
    /** 
     * 这里是要显示的列的定义，这里定义了多少个key,页面就会显示多少列 
     * 要显示远程内容的那些列的key值与上文中fields里的内容相对应 
     * sortable设置本列是否可以点击列头排序 
     */  
    var myColumnDefs = [  
                       	{key:"check",label:"",formatter:"checkbox"},  
                       	{key:"rn", label:"行号",resizeable:true},
                        {key:"name", label:"姓名",resizeable:true},
                        {key:"id", label:"ID",resortable:true},
                        {key:"price", label:"Price",width:100},//sortable:true
                        {key:"operation",label:"操作",formatter:formatoperation}  
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
            rowsPerPageOptions : [ 1, 3, 5, 10]}),
        paginationEventHandler:	YAHOO.widget.DataTable.handleDataSourcePagination
    };  
    // 新建一个表格，第一个参数是你页面上的div的id.表格会显示在那个div中。   
    var myDataTable = new YAHOO.widget.DataTable("serverintegration", myColumnDefs, myDataSource, myConfigs);  
      
    // 更新表格参数，以记录当前显示到第几行   
     myDataTable.doBeforeLoadData = function(oRequest, oResponse, oPayload) {  
        return oPayload;  
    };  
      
    // 为表格中的checkbox绑定事件，记录被选择的行内容的id。   
    myDataTable.subscribe("checkboxClickEvent", function(oArgs){   
        var elCheckbox = oArgs.target;   
        var oRecord = this.getRecord(elCheckbox);  
        var id = oRecord.getData('id');  
        if(elCheckbox.checked){  
            alert("你选择了第" + id + "行");  
        }else{  
            alert("你取消选择了第" + id + "行");  
        }  
    });  
      
    // 官方的例子里有这一句，我也没删，目前来看也没什么用   
    return {   
   　    ds: myDataSource,   
        dt: myDataTable   
    };       
}();  

</script>
</body>