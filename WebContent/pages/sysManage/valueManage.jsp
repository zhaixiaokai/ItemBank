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
<link href="../css/infoedit.css" rel="stylesheet" type="text/css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script> 

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

<script type="text/javascript">

function	GetDataDicList(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetDataDicOption",
		success : function(result) {
			TeacherList	=	result.data;
			$("#SelectDataDic ").empty();
			$("#SelectDataDic").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectDataDic").append("<option value=\""+result.data[i].id+"\">"+
						result.data[i].name+"</option>");
			}
		},
		error:function(){
		}
	});
}
function ValueList(){
	
	if(document.getElementById("SelectDataDic").value==""){
		alert("请选择所要查询的字典项");
		return;
	}
	SelectedSpecialFieldId=document.getElementById("SelectDataDic").value;
	//alert(SelectedSpecialFieldId);
	CreateDataTableForValue(SelectedSpecialFieldId);
}
function CreateDataTableForValue(SpecialFieldId){
	(function () {
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
		var timestamp = (new Date()).valueOf();  
	    var myDataSource = new YAHOO.util.DataSource("ValueOptionTableDataGetAction?FieldId="+SpecialFieldId+"&timestamp="+timestamp+"&");  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
           resultsList: "records",
           fields: ["dictionary_entries_value","dictionary_entries_id","name","value","sno","rn"],
           metaFields: {
               totalRecords: "totalRecords",
               paginationRecordOffset : "startIndex",
               sortKey: "sort",
               sortDir: "dir"
           }  
	    };    
	      
	    // 自定义内容格式化方法   
	    YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
            var Name=oRecord.getData("name");
            var Value=oRecord.getData("value");
            var Sno=oRecord.getData("sno");
            var Dictionary_Entries_Value=oRecord.getData("dictionary_entries_value");
    
            elCell.innerHTML="<a href='#' name='"+Dictionary_Entries_Value+"' onclick='DeleteRow(this)'>删除</a> "
            	+"| <a href='#' name='"+Dictionary_Entries_Value+"' onclick=\"ModifyRow('"+Name+"','"+Value+"','"+Sno+"','"+Dictionary_Entries_Value+"')\">修改</a>";
        	//alert(elCell.innerHTML);
        }  
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('dictionary_entries_value')
              + "\" type=\"checkbox\"" + bChecked
              + " class=\"yui-dt-checkbox\" />";
              //alert(oRecord.getData('dictionary_entries_value'));
        }
	    /** 
	     * 这里是要显示的列的定义，这里定义了多少个key,页面就会显示多少列 
	     * 要显示远程内容的那些列的key值与上文中fields里的内容相对应 
	     * sortable设置本列是否可以点击列头排序 
	     */  
	     var myColumnDefs = [  
		        	           	{key:"check",label:"",formatter:"checkbox"},  
		        	           	{key:"rn", label:"行号"},
		        	            {key:"name", label:"值项名称"},
		        	            {key:"value", label:"值项值"},
		        	            {key:"sno", label:"值项顺序号"},		        	        
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

	//删除一条记录
	function DeleteRow(o) {
		var id = o.name;
		//alert(id);
		if (confirm("确定要删除该记录？")) {
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "DeleteValueOptionAction?DeleteId=" + id,
				success : function(result) {
					alert(result.text);
					//删除完成后重新载入数据表~
					ValueList();
				}
			});
		} else {
		}
	}
	//修改一条记录,将参数传入修改对话框
	function ModifyRow(ValueName, ValueValue, ValueSNO, Dictionary_Entries_Value) {
		document.getElementById("DicValueOptionName").value = ValueName;
		document.getElementById("DicValueOptionValue").value = ValueValue;
		document.getElementById("DicValueOptionSNO").value = ValueSNO;
		document.getElementById("OldEntriesSNO").value = Dictionary_Entries_Value;

		//根据客户端浏览器高度设置半透明罩的高度 
		document.getElementById('fade').style.height = document.body.clientHeight
				+ "px";
		document.getElementById('fade').style.display = 'block';
		YAHOO.example.container.panel1.show();
	}
	//清除屏幕锁定，并且隐藏修改框 
	function ClearFade() {
		document.getElementById("fade").style.display = "none";
		YAHOO.example.container.panel1.hide();
	}
	
	
	  //批量删除
	function BulkDelete() {
		var CheckBoxsList = document.getElementsByName("CheckBoxs");
		var flag = false;
		var param = "";

		for ( var i = 0; i < CheckBoxsList.length; i++) {
			if (CheckBoxsList[i].checked) {
				if (flag == false) {
					param += CheckBoxsList[i].id;
					flag = true;
				} else {
					param += " " + CheckBoxsList[i].id;
				}
			}
		}
		if (!flag) {
			alert("请至少选择一项需要删除的数据");
			return;
		}
		if (confirm("确定要删除？")) {
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "BulkDeleteValueOptionAction",
				data : "DeleteId=" + param,
				success : function(result) {
					alert(result.text);
					ValueList();
				},
				error : function() {
				}
			});
		}

	}

	function	CheckValidation(){
		
		var	ValueName	=	document.getElementById("DicValueOptionName").value;
		var	ValueValue	=	document.getElementById("DicValueOptionValue").value;
		var	ValueSNO	=	document.getElementById("DicValueOptionSNO").value;
		
		if(ValueName==""){
			alert("值项名称不能为空");
			document.ValueAddForm.DicValueOptionName.focus();
			return;
		}
		if(ValueName.length>20){
			alert("值项名称长度不能超过50个字符");
			document.ValueAddForm.DicValueOptionName.focus();
			return;
		}
		if(!CheckIfChinaNumbLetter(ValueName)){
			alert("值项名称只能由 汉字、字母、数字、'_'组成");
			document.ValueAddForm.DicValueOptionName.focus();
			return;
		}
		if(ValueValue==""){
			alert("值项值不能为空");
			document.ValueAddForm.DicValueOptionValue.focus();
			return;
		}
		if(ValueValue.length>20){
			alert("值项值长度不能超过50个字符");
			document.ValueAddForm.DicValueOptionValue.focus();
			return;
		}
		if(!CheckIfIsLetterNumber(ValueValue)){
			alert("值项值只能由 字母、数字 组成");
			document.ValueAddForm.DicValueOptionValue.focus();
			return;
		}
		if(ValueSNO==""){
			alert("值项序号不能为空");
			document.ValueAddForm.DicValueOptionSNO.focus();
			return;
		}
		if(!CheckIfIsNumber(ValueSNO)){
			alert("值项序号只能是正整数");
			document.ValueAddForm.DicValueOptionSNO.focus();
			return;
		}
		if(ValueSNO.length>30){
			alert("值项值序号长度不能超过30");
		}
		
		if(confirm("确认修改该值项？")){
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
			ValueList();
		}
	}
</script>

</head>
<body class=" yui-skin-sam">
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/sysManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="../functionList/HomePage.html">首页</a>&nbsp;&gt;&gt;&nbsp;<a
			href="../functionList/sysManage.html">系统管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">数据字典管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">值项管理</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div id="act_content2">


		<table height="50px" width="100%">
			<tr>
				<td width="160">所属字典项:
				<select name="SelectDataDic" id="SelectDataDic">
						<option value="">--请选择--</option>
				</select><span class="red"> *</span></td>
				<td width="40"><input type="button" class="button" value="查询"
					onclick="ValueList();"></td>
				<td align="right"><input type="button" value="批量删除"
					class="button" onclick="BulkDelete();" /></td>
			</tr>
		</table>

		<!-- 修改的弹窗 -->
		<div id="container">
			<div id="panel1" style="z-index: 1999">
				<div class="hd" style="z-index: 2000">修改教师信息</div>
				<div class="bd" style="z-index: 2000">
					<form name="modifyForm" action="UpdateValueOptionAction"
						id="modifyForm">
						<input type="hidden" name="OldEntriesSNO" id="OldEntriesSNO" />
						<table>
							<tr>
								<td nowrap align="left" height="50px">值项名称:</td>
								<td><input type="text" name="DicValueOptionName"
									id="DicValueOptionName" /><span class="red"> *</span></td>
							</tr>
							<tr>
								<td nowrap align="left" height="50px">值项值:</td>
								<td><input type="text" name="DicValueOptionValue"
									id="DicValueOptionValue" /><span class="red"> *</span></td>
							</tr>
							<tr>
								<td nowrap align="left" height="50px">值项顺序号:</td>
								<td><input type="text" name="DicValueOptionSNO"
									id="DicValueOptionSNO" /><span class="red"> *</span></td>
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
	GetDataDicList();
	</script>
	
	<script>
		//初始化修改信息对话框
		YAHOO.namespace("example.container");
		function init() {
			YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", {
				width : "600px",
				visible : false,
				constraintoviewport : true,
				close : false
			});
			YAHOO.example.container.panel1.render();
		}
		YAHOO.util.Event.addListener(window, "load", init);
	</script>
	

</body>
</html>