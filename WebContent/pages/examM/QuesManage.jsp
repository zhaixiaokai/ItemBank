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
	var subjectid='';
	var modify_question_id='';
function getpointdata(){
//由试题库id查找对应课程id
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

//创建试题表格
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
	    YAHOO.widget.DataTable.Formatter.QuesModify = function(elCell, oRecord, oColumn, oData){
	    	 var id=oRecord.getData("question_id");
	  	   var timeuse=oRecord.getData("time_use");
	  	        var defaultpoint=oRecord.getData("defaultpoint");
	  	        var difficulty=oRecord.getData("difficulty");
	  	        var knowledge_point=oRecord.getData("knowledge_point_id");
             elCell.innerHTML="<a href='#' onclick=QuesModifyBasicInfo('"+id+"','"+timeuse+"','"+defaultpoint+"','"+difficulty+"','"+knowledge_point+"')>基本信息 </a>|<a href='#' onclick=QuesModifyContent('"+id+"')> 试题内容 </a>";
	    }  
	    YAHOO.widget.DataTable.Formatter.QuesDelete = function(elCell, oRecord, oColumn, oData){
	    	 var id=oRecord.getData("question_id");
	  	   //     var name=oRecord.getData("itembank_name");
	  	    //    var use=oRecord.getData("use");
	  	    //    var explain=oRecord.getData("explain");
	              elCell.innerHTML="<a href='#' onclick=QuesDelete('"+id+"')>删除试题 </a>";
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
	                   //     {key:"check",label:"",formatter:"checkbox"},  
	                       	{key:"rn", label:"行号",resizeable:true},
	                     //   {key:"question_id", label:"试题内容",resizeable:true},
	                        {key:"knowledge_point_id", label:"知识点"},
	                        {key:"difficulty", label:"难度"},
	                        {key:"question_type", label:"题型"},
	                        {key:"defaultpoint", label:"默认分值(秒)"},
	                        {key:"time_use", label:"答题 时长"},
	                   //     {key:"itembank", label:"试题库"},
	                        {key:'修改试题', lable:"",formatter:"QuesModify"},
	                        {key:'删除试题', lable:"",formatter:"QuesDelete"}
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
	    YAHOO.widget.DataTable.Formatter.QuesModify = function(elCell, oRecord, oColumn, oData){
	    	 var id=oRecord.getData("question_id");
	  	   //     var name=oRecord.getData("itembank_name");
	  	    //    var use=oRecord.getData("use");
	  	    //    var explain=oRecord.getData("explain");
	              elCell.innerHTML="<a href='#' onclick=QuesModifyBasicInfo('"+id+"')>基本信息 </a>|<a href='#' onclick=QuesModifyContent('"+id+"')> 试题内容 </a>";
	    }  
	    YAHOO.widget.DataTable.Formatter.QuesDelete = function(elCell, oRecord, oColumn, oData){
	    	 var id=oRecord.getData("question_id");
	  	   //     var name=oRecord.getData("itembank_name");
	  	    //    var use=oRecord.getData("use");
	  	    //    var explain=oRecord.getData("explain");
	              elCell.innerHTML="<a href='#' onclick=QuesDelete('"+id+"')>删除试题 </a>";
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
	                        {key:'修改试题', lable:"",formatter:"QuesModify"},
	                        {key:'删除试题', lable:"",formatter:"QuesDelete"}
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

//修改试题基本信息
//弹框
function QuesModifyBasicInfo(id,timeuse,defaultpoint,difficulty,knowledge_point){
	document.getElementById("time").value=timeuse;
	  document.getElementById("defaultpoint").value=defaultpoint; 
	  document.getElementById("modifyid").value=id;
	  //取出难度系数值
	  var diff_coefficient=null;
	$.ajax( {
		type : "post",
		dataType : "text",
		async : false,
		url : "QuesManageGetDiffAction?questionid="+id+"&itembank_id="+itembank_id,
		success : function(result) {
			diff_coefficient=result;
		}
	});
	//难度字符串转数字
	diff_coefficient=parseFloat(diff_coefficient);
	 document.getElementById("difficulty_coefficient").value=diff_coefficient;
	 //获取知识点
	 $.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "GetPointDataAction?leafid="+subjectid,
			success : function(result) {
				$("#Quesselectpoint ").empty();
				$("#Quesselectpoint").append(
						"<option value=\"\" id=\"Quesselectnonepoint\">--请选择--</option>");
				for ( var i = 0; i < result.data.length; i++) {
			 	if (result.data[i].name==knowledge_point) {
						$("#Quesselectpoint")
								.append(
										"<option selected id=\""+result.data[i].knowledge_point_id+
				"\" value=\""+result.data[i].name+"\">"
												+ result.data[i].name
												+ "</option>");

					} else { 
						$("#Quesselectpoint")
								.append(
										"<option id=\""+result.data[i].knowledge_point_id+
				"\" value=\""+result.data[i].name+"\">"
												+ result.data[i].name
												+ "</option>");
					}
				
				}
			},
			error : function() {
			}
		});
	  //根据客户端浏览器高度设置半透明罩的高度 
	  document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
	  document.getElementById('fade').style.display='block';
	  YAHOO.example.container.panel1.show();
	return null;
}

function getDoc(id,itembank_id){
	document.getElementById("WebOffice1").LoadOriginalFile("/QuesManageGetQuesContentAction?questionid="+id+"&itembank_id="+itembank_id,"doc");
}


//修改试题内容
function QuesModifyContent(id){
	//获取试题内容
/* 	$.ajax( {
		type : "post",
		dataType : "text",
		async : false,
		url : "QuesManageGetQuesContentAction?questionid="+id+"&itembank_id="+itembank_id,
		success : function(result) {
			alert(1);
		}
	}); */
	modify_question_id=id;
	getDoc(id,itembank_id);
	  document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
	    document.getElementById('fade').style.display='block';
	    document.getElementById("PannelHeaderQues").innerHTML="修改试题内容";
	    document.getElementById("containerQues").style.left=(document.body.clientWidth-1024)/2+15+"px";
	    document.getElementById("containerQues").style.top="0px"
	    YAHOO.example.container.panel1Ques.show();
	return null;
}




//删除试题,单条数据
function QuesDelete(id){
	  if(confirm("确定要删除试题数据吗？")){
			$.ajax({
				type : "post",
				dataType : "text",
				async : false,
				url : "QuesManageDeleteQuesAction?questionid="+id+"&itembank_id="+itembank_id,
				success : function(result) {
					 if (result != null && result != "") {
							if (result == "删除成功") {
								alert(result);
								document.location.reload();
							}
						} else {
							alert("删除失败");
						}
					}
				});
	  }
 
	
	return null;
}

//批量删除试题
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
			dataType : "text",
			async : false,
			url : "QuesManageBulkDeleteQuesAction?deleteid="+param+"&itembank_id="+itembank_id,
			success : function(result) {
			
					 if (result != null && result != "") {
							if (result == "删除成功") {
								alert(result);
								document.location.reload();
							}
						} else {
							alert("删除失败");
						}
					}
			
		});
	}
}




function ClearFade(){
	document.getElementById("fade").style.display="none";
	YAHOO.example.container.panel1.hide();
}
function ClearFadeQues(){
	document.getElementById("fade").style.display="none";
	YAHOO.example.container.panel1Ques.hide();
}

//修改弹出框保存
function Quesmodifysave(){
	var difficulty_coefficient = document.getElementById("difficulty_coefficient").value;
	var defaultpoint = document.getElementById("defaultpoint").value;
	var time = document.getElementById("time").value;
	var options = document.getElementById("Quesselectpoint").options;
	var index = document.getElementById("Quesselectpoint").selectedIndex;
	var selectedOption = options[index];
	var Quesselectpoint = selectedOption.id; 
	//alert(Quesselectpoint);
	if (difficulty_coefficient == "") {
		alert("难度系数为必填项，请重新输入 ");
		return;
	}else if (!isFigure(difficulty_coefficient)) {
		alert("难度系数为数字，请重新输入");
		return;
	} else if (difficulty_coefficient>=1||difficulty_coefficient<=0) {
		alert("难度系数为0到1之间的小数");
		return;
	}  else if (defaultpoint == "") {
		alert("默认分值为必填项，请重新输入 ");
		return;
	}  else if (!isFigure(defaultpoint)) {
		alert("默认分值为数字，请重新输入");
		return;
	} else if (time == "") {
		alert("时间为必填项，请重新输入 ");
		return;
	} else if (!isFigure(time)) {
		alert("时间为数字，请重新输入");
		return;
	}else if (Quesselectpoint==null||Quesselectpoint==''||Quesselectpoint=='Quesselectnonepoint') {
		alert("知识点是必选项，请选择知识点");
		return;
	}  else {
		//试题基本信息保存
	$(function() {
				var options = {
					type : "post",
					dataType : "text",
 					url : "ModifyQuesSaveAction?itembank_id="+itembank_id+"&quesKnowPointId="+Quesselectpoint,
					success : function(result) {
						 if (result != null && result != "") {
							if (result == "修改成功") {
								alert(result);
								document.location.reload();
							}
						} else {
							alert("添加失败");
						}
					}
				};
				$('#modifyForm').ajaxSubmit(options);
				return false;
			});
		}
	}
	
	
function QuesComplete(){
	saveDoc();
	ClearFadeQues();
}
//重置试题
function QuesReset(){
	getDoc(modify_question_id,itembank_id);
}


function saveDoc(id,docType) {
	try{
		var webObj=document.getElementById("WebOffice1");
		var returnValue;
		webObj.HttpInit();			//初始化Http引擎
		// 添加相应的Post元素 
		webObj.HttpAddPostCurrFile("DocContent","");		// 上传文件
		returnValue = webObj.HttpPost("/QuestionContentSaveAction?questionid="+modify_question_id+"&itembank_id="+itembank_id);	// 判断上传是否成功
		if("succeed" == returnValue){
			alert("修改成功");	
			webObj.Close();
		}else if("failed" == returnValue){
			alert("修改失败");
		}
	}catch(e){
		alert("异常\r\nError:"+e+"\r\nError Code:"+e.number+"\r\nError Des:"+e.description);
	}
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
			href="javascript:void(0)">试题管理</a>&nbsp;&gt;&gt;&nbsp;试题管理
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
					<td> <input type=button id="button" value="批量删除" class="button" onclick="bulkdelete()"></td>
			</tr>
		</table>
			<!-- 修改基本信息的弹窗 -->
		<table>
		<tr>
		<td>
		<div id="container">
			<div id="panel1" style="z-index: 1999">
				<div class="hd" style="z-index: 2000">修改试题基本信息</div>
				<div class="bd" style="z-index: 2000">
					<form name="modifyForm"  id="modifyForm">
						<input type="hidden" id="modifyid" name="modifyid" />
						<table>
						<tr>
											<td nowrap align="right" >难度系数:</td>
											<td><input type="text" size="9" title="难度系数为0-1间的小数"
												class="text" id="difficulty_coefficient" name="difficulty_coefficient"><span
													class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right" height="10px">默认分值:</td>
											<td colspan="3"><input type="text"  size="9"
												class="text" id="defaultpoint" name="defaultpoint" title="请输入数字"><span
													class="red"> *</span></td>
										</tr>
										<tr>
											<td nowrap align="right" height="10px">预计时间:</td>
											<td colspan="3"><input type="text" size="9"
												class="text" id="time" name="time" title="请输入数字，单位为秒">&nbsp;秒<span
													class="red"> *</span></td>
										</tr>

										<tr>
											<td nowrap align="right">知识点:</td>
											<td><select name="Quesselectpoint" id="Quesselectpoint" style="font-size: 12px">
											</select><span
													class="red"> *</span></td>
										</tr>

						</table>
							<div align="center">
				<input type="button" value="修改" onclick="Quesmodifysave();" />
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
		
		
		
		
		
		<!-- 修改试题内容的弹窗 -->
		<div id="containerQues" style="position: absolute;">
		<div id="panel1Ques" style="z-index: 1999">
			<div id="PannelHeaderQues" class="hd" style="z-index: 2000"></div>
			<div class="bd" style="z-index: 2000">
				<div id="QuesContent"></div>
				<script src="../js/webOffice/LoadWebOffice.js"></script>
				<table align="center">
					<tr>
						<td><input type="button" class="button" value="完成" onclick="QuesComplete()"/></td>
						<td><input type="button" class="button" value="重置" onclick="QuesReset()"/></td>
					</tr>
				</table>
			</div>
			<div class="ft"></div>
		</div>
	</div>	
		
	
	
	
	
	
		
		<!-- 半透罩，用来锁定屏幕 -->
		<div id="fade" class="black_overlay"></div>
		<div id="serverintegration" style="margin-left:45px"></div>
		<div id="dt-pag-nav"></div>


	<script>
		//初始化修改信息对话框
		YAHOO.namespace("example.container");
		function init() {
			YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", { width:"600px", visible:false, constraintoviewport:true,close :false } );
			YAHOO.example.container.panel1.render();
			YAHOO.example.container.panel1Ques = new YAHOO.widget.Panel("panel1Ques", { width:"1000px", visible:false, constraintoviewport:true,close :false } );
			YAHOO.example.container.panel1Ques.render();
		}
		YAHOO.util.Event.addListener(window, "load", init);
	
	</script>
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
	//YAHOO.namespace("example.container");
	CreateDataTableForQues();
	</script>
</body>
</html>