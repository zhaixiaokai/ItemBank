<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<% response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma"   content="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57 GMT">

<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link href="../css/infoedit.css" rel="stylesheet" type="text/css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link href="/css/multiMenu.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<!--   YUI 	 -->
   	<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/build/datasource/datasource-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../js/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="../js/yui/build/history/history-min.js"></script>
<script type="text/javascript" src="../js/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/datatable/datatable-beta-min.js"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>

<!-- ajaxform -->
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script src="../js/formReset.js" type="text/javascript"></script>


<link rel="stylesheet" type="text/css"
	href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript"
	src="../js/yui/build/container/container-min.js"></script>
	
<!--    YUI CSS配置	 -->
<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}
</style>

<!--begin custom header content for this example-->
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

<script type="text/javascript">
function ClearFade(){
	document.getElementById("fade").style.display="none";
	YAHOO.example.container.panel1.hide();
}


//修改分类，弹出对话框
function ModifyRow(id,name,defaultsort,discription){
    document.getElementById("modifyName").value=name;
    document.getElementById("modifyIdentifier").value=id;
    document.getElementById("modifyDiscription").value=discription.replace(/n1/g,"\n").replace(/n2/g,"n");//textarea换行处理
   /*  if(defaultsort=="是"){
		document.getElementById("RadioYes").checked=true;
		document.getElementById("RadioNo").checked=false;	
	}
	else {
		document.getElementById("RadioYes").checked=false;
		document.getElementById("RadioNo").checked=true;
	} */
    document.getElementById("modifyid").value=id;
    //根据客户端浏览器高度设置半透明罩的高度 
    document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
    document.getElementById('fade').style.display='block';
    YAHOO.example.container.panel1.show();
}



//修改分类信息，验证及提交
	function	modifysort(){
		var discription=document.getElementById("modifyDiscription").value;
		if(document.getElementById("modifyName").value==""||document.getElementById("modifyName").value==null){
			alert("修改失败：分类体系名称不能为空，请填写分类体系名称！");
			document.modifyForm.newname.focus();
			return;
		}
		if( !CheckIfChinaNumbLetter(document.getElementById("modifyName").value)){
			alert("修改失败：名称格式有误，请输入汉字，数学或者字母，20字符以内 ");
			document.modifyForm.newname.focus();
			return;}
		if( document.getElementById("modifyName").value.length>20){
			alert("修改失败：名称长度超出范围，20字符以内 的名称");
			document.modifyForm.newname.focus();
			return;}
		if(document.getElementById("modifyIdentifier").value==""||document.getElementById("modifyIdentifier").value==null){
				alert("修改失败：分类体系标识符不能为空，请填写分类体系标识符！");
				document.modifyForm.newidentifier.focus();
				return;
			}
		 if( !CheckIfIsLetter_Number(document.getElementById("modifyIdentifier").value)){
				alert("修改失败：标识符格式有误，请输入数学或者字母或者下划线，20字符以内 ");
				document.modifyForm.newidentifier.focus();
				return;
		}
		 if( document.getElementById("modifyIdentifier").value.length>20){
				alert("修改失败：标示符长度超出范围，20字符以内 的名称");
				document.modifyForm.newidentifier.focus();
				return;}
		 if(discription.replace(/[^\x00-\xFF]/g,'**').length>300){
			 alert("修改失败：分类体系说明过长，请输入300字节以内说明。注：汉字一位是两字节，数字和字母一位是一字节 ");
			 return;
		 }
		 if (confirm("确认修改？")) {
			$(function() {
				var options = {
					type : "post",
					dataType : "json",
					url : "SortModifyAction",
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
			if(responseText.message!=null&&responseText.message!=""){
				if(responseText.message=="修改成功"){
					alert(responseText.message);
					document.location.reload();
				}else{
					alert(responseText.message);
				}
			}
		}
	}
	
	//删除分类体系,单条数据
	function deletesort(id){
		  if(confirm("确定要删除数据吗？")){
				$.ajax({
					type : "post",
					dataType : "json",
					async : false,
					url : "SortDeleteAction?deleteid="+id,
					success : function(result) {
						alert(result.message);
						document.location.href="sortSystemManage.jsp";
					}
					});
		}  
	}
	
	
	
	//批量删除数据
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
		if(confirm("确定要删除？")){
			$.ajax( {
				type : "post",
				dataType : "json",
				async : false,
				url : "SortBulkDeleteAction?deleteid="+param,
				success : function(result) {
					alert(result.message);
					document.location.href="sortSystemManage.jsp";
				}
			});
		}
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
	   var time =new Date();
	    var myDataSource = new YAHOO.util.DataSource("SortSystemManageAction?time="+time);  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
	        resultsList: "records",
	        fields: ['classification_id','classification_name','if_default_classification','classification_explain','statue',"rn"],  
	        metaFields: {
	            totalRecords: "totalRecords",
	            paginationRecordOffset : "startIndex",
	            sortKey: "sort",
	            sortDir: "dir"
	        } 
	    };    
	      
	    // 自定义内容格式化方法   
	    YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
	        var id=oRecord.getData("classification_id");
	        var name=oRecord.getData("classification_name");
	        var defaultsort=oRecord.getData("if_default_classification");
	        var discription=oRecord.getData("classification_explain");
	        discription=discription.trim();
            elCell.innerHTML="<a href='#' onclick=deletesort('"+id+"')>删除</a> | <a href='#' onclick=ModifyRow('"+id+"','"+name+"','"+defaultsort+"','"+discription+"')>修改</a>";
	    }  
	    
	    
	    //checkbox
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('classification_id')
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
	                        {key:"classification_name", label:"名称",resizeable:true},
	                        {key:"classification_id", label:"标识符"},
	                        {key:"if_default_classification", label:"默认分类体系"},//sortable:true
	                     //   {key:"classification_explain", label:"说明"},//sortable:true
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
</script>

<title>Insert title here</title>
</head>
<body class=" yui-skin-sam" >
<input id="yui-history-field" type="hidden">
<jsp:include page="../frame/Frame1.jsp"></jsp:include>
<!-- 左侧界面 -->
<jsp:include page="../functionList/examDbManageContent.jsp"></jsp:include>
<jsp:include page="../frame/Frame2.jsp"></jsp:include>
<!-- 右侧界面 -->
<div id="act_top">
	<a href="#">试题库管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">试题库分类体系管理</a>&nbsp;&gt;&gt;&nbsp;<a
		href="#">分类体系管理</a>
</div>
<table id="divLine" height="4px" width="100%" border="0"
	cellspacing="0" cellpadding="0">
	<tr>
		<td style="border: 0"></td>
	</tr>
</table>
<div style="margin-left: 600px"><input type=button id="button" value="批量删除" class="button" onclick="bulkdelete()"></div>
<div id="act_content2">
<!-- 修改的弹窗 -->
<div id="container">
	<div id="panel1" style="z-index: 1999">
		<div class="hd" style="z-index: 2000">修改分类体系</div>
		<div class="bd" style="z-index: 2000">
			<form name="modifyForm" action="SortModifyAction" id="modifyForm">
				<input type="hidden" id="modifyid" name="modifyid" />
				<table>
					<tr>
						<td width="150px">分类体系名称：</td>
						<td><input type="text" id="modifyName" class="text"
							name="newname" /><span class="red" > *    </span></td>
					</tr>
					<tr>
						<td width="150px">分类体系标识符：</td>
						<td><input type="text" id="modifyIdentifier" class="text"
							name="newidentifier" /><span class="red" > *    </span></td>
					</tr>
					
					<tr>
						<td>分类体系说明</td>
						<td><textarea id="modifyDiscription" name="newdiscription"></textarea></td>
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
<!-- 半透罩，用来锁定屏幕 -->
<div id="fade" class="black_overlay"></div>
<div id="serverintegration" ></div>
<div id="dt-pag-nav"></div>
</div>
<script>
	//初始化修改信息对话框
	YAHOO.namespace("example.container");
	function init() {
		YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", { width:"600px", visible:false, constraintoviewport:true,close :false } );
		YAHOO.example.container.panel1.render();
	}
	YAHOO.util.Event.addListener(window, "load", init);
</script>
<script>
CreateDataTableForSort();
</script>
<!-- 右侧界面结束-->
<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>