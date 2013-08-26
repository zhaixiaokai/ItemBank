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



//获取分类体系列表
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


//修改试题库获取试题库配置信息
var modifysortlistdata = null;
function modifygetsortList() {
	$.ajax({
		type : "post",
		dataType : "json",
		async : false,
		url : "ModifySortConfigAction",
		success : function(result) {
			result = eval(result);
			modifysortlistdata = result;
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


//试题库修改获取多级菜单中元素的值
function ModifyGetNode(o) {
	$("#modify_sortconfig_node").empty();
	$("#modify_sortconfig_node").append(o);
}
function ModifyFunctionFullfil() {
	//显示分类列表
	ModifyGetNode(this.innerHTML);
	new_config_leafid = this.id;
}




//获得分类体系名称及id
function getSort() {
	$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "SortSelectAction",
				success : function(result) {
					$("#SelectSort ").empty();
					$("#SelectSort").append(
							"<option value=\"\">--请选择--</option>");
					for ( var i = 0; i < result.data.length; i++) {
						$("#SelectSort")
								.append(
										"<option id=\""+result.data[i].classification_id+
					"\" value=\""+result.data[i].classification_id+"\">"
												+ result.data[i].classification_name
												+ "</option>");
					}
				},
				error : function() {
				}
			});

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




	function sortSelect() {
		self.parent.frames["mainFrame"].location = "esManage.jsp";
	}
	function check_all(obj, cName) {
		var checkboxs = document.getElementsByName(cName);
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = obj.checked;
		}
	}
</script>

<script>

//删除试题库,单条数据
function DeleteRow(id){
	  if(confirm("确定要删除数据吗？")){
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "ExamSystemDeleteAction?deleteid="+id,
				success : function(result) {
					document.location.href="esManageSelect.jsp";
				}
				});
	}  
}

//批量删除试题库
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
			url : "ExamSystemBulkDeleteAction?deleteid="+param,
			success : function(result) {
				alert(result.message);
				document.location.href="esManageSelect.jsp";
			}
		});
	}
}



//修改试题库，弹出对话框
function ModifyRow(id,name,use,explain){
		document.getElementById("modifyName").value=name;
	  document.getElementById("modifyIdentifier").value=id;
	  document.getElementById("modifyDiscription").value=explain.replace(/n1/g,"\n").replace(/n2/g,"n");
	  document.getElementById("modifyid").value=id;
	  olduse=use;
	//获得试题库分类体系配置值
	var sortconfigtext;
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "ExamSystemSortConfigAction?sortconfigid="+id,
		success : function(result) {
			sortconfigtext=result.message;
		}
			
	});
	if(sortconfigtext==""){
		sortconfigtext="分类体系列表";
	}
	
	//获取用途数据
	$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "UseConfigAction",
				success : function(result) {
					$("#modifyUse ").empty();
					$("#modifyUse").append(
					"<option value=\"\">--请选择--</option>");
					for ( var i = 0; i < result.data.length; i++) {
						//设置选中项
						if (result.data[i].name==use) {
							$("#modifyUse")
									.append(
											"<option selected id=\""+result.data[i].dictionary_entries_value+
					"\" value=\""+result.data[i].dictionary_entries_value+"\">"
													+ result.data[i].name
													+ "</option>");

						} else {
							$("#modifyUse")
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
	
	//获取分类配置数据
		modifygetsortList();
		loadMenu("modifySort", modifysortlistdata, ModifyFunctionFullfil, "modify_sortconfig_node");
		$(document).ready(function(){
			$("#modify_sortconfig_node").empty();
			 $("#modify_sortconfig_node").append(sortconfigtext);
		});
		
				
  
  //根据客户端浏览器高度设置半透明罩的高度 
  document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
  document.getElementById('fade').style.display='block';
  YAHOO.example.container.panel1.show();
}



//修改试题库信息，验证及提交
	function	modifysort(){
	//获取修改弹出框中修改后select的值
//		var options = document.getElementById("modifyUse").options;
	//	var index = document.getElementById("modifyUse").selectedIndex;
	//	var selectedOption = options[index];
	//	var modifyuse = selectedOption.id;
		//数据合理性验证
		if(document.getElementById("modifyName").value==""||document.getElementById("modifyName").value==null){
			alert("修改失败：试题库名称不能为空，请填写试题库名称！");
			document.modifyForm.newname.focus();
			return;
		}
		if( !CheckIfChinaNumbLetter(document.getElementById("modifyName").value)){
			alert("修改失败：名称格式有误，请输入汉字，数学或者字母，20字符以内 ");
			document.modifyForm.newname.focus();
			return;}
		if( document.getElementById("modifyName").value.length>20){
			alert("修改失败：名称长度超出范围，请输入20字符以内的名称 ");
			document.modifyForm.newname.focus();
			return;}
		if(document.getElementById("modifyIdentifier").value==""||document.getElementById("modifyIdentifier").value==null){
			alert("修改失败：试题库标识符不能为空，请填写试题库标识符！");
			document.modifyForm.newidentifier.focus();
			return;
			}
		 if( !CheckIfIsLetter_Number(document.getElementById("modifyIdentifier").value)){
			alert("修改失败：标识符格式有误，请输入数学或者字母或者下划线，20字符以内 ");
			document.modifyForm.newidentifier.focus();
			return;
		}
		 if( document.getElementById("modifyIdentifier").value.length>20){
			alert("修改失败：标识符长度超出范围，请输入20字符以内的标示符 ");
				document.modifyForm.newidentifier.focus();
				return;
			}
		 var discription=document.getElementById("modifyDiscription").value;
		 if(discription.replace(/[^\x00-\xFF]/g,'**').length>300){
			 alert("修改失败：试题库说明过长，请输入300字节以内说明。注：汉字一位是两字节，数字和字母一位是一字节 ");
			 return;
		 }
		if(confirm("确认修改？")){
			$(function() {
				var options = {
					type : "post",
					dataType : "json",
					//url : "ExamSystemModifyAction",
					url : "ExamSystemModifyAction?newconfig_leafid="+new_config_leafid,
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
</script>
<script>
//清除屏幕锁定，并且隐藏修改框 
function ClearFade(){
	document.getElementById("fade").style.display="none";
	YAHOO.example.container.panel1.hide();
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
	    YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
	        var id=oRecord.getData("itembank_id");
	        var name=oRecord.getData("itembank_name");
	        var use=oRecord.getData("use");
	        var explain=oRecord.getData("explain");
            elCell.innerHTML="<a href='#' onclick=DeleteRow('"+id+"')>删除</a> | <a href='#' onclick=ModifyRow('"+id+"','"+name+"','"+use+"','"+explain+"')>修改</a>";
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
	                        {key:"check",label:"",formatter:"checkbox"},  
	                       	{key:"rn", label:"行号",resizeable:true},
	                        {key:"itembank_name", label:"名称",resizeable:true},
	                        {key:"itembank_id", label:"标识符"},
	                        {key:"explain", label:"说明"},
	                        {key:"use", label:"用途"},
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
				//alert("是否是根节点   "+if_rootnode);
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
				//alert("是否在基本分类体系下  "+if_under_defaultsort);

			},
			error : function() {
			}
		}); 
		//根据分类查找所有叶子节点
		leafnode=getleafnode(refersortleaf,sortlistdata,if_rootnode);//CreateMultiMenu中定义
	//	alert("叶子节点列表为：   "+leafnode);
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
		    var myDataSource = new YAHOO.util.DataSource("ReferDataSourceAction?refer_leafnode="+leafnode+"&referuse="+referyuse+"&if_under_defaultsort="+if_under_defaultsort+"&time="+time);  
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
	    YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
	        var id=oRecord.getData("itembank_id");
	        var name=oRecord.getData("itembank_name");
	        var use=oRecord.getData("use");
	        var explain=oRecord.getData("explain");
            elCell.innerHTML="<a href='#' onclick=DeleteRow('"+id+"')>删除</a> | <a href='#' onclick=ModifyRow('"+id+"','"+name+"','"+use+"','"+explain+"')>修改</a>";
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
	                        {key:"check",label:"",formatter:"checkbox"},  
	                       	{key:"rn", label:"行号",resizeable:true},
	                        {key:"itembank_name", label:"名称",resizeable:true},
	                        {key:"itembank_id", label:"标识符"},
	                        {key:"explain", label:"说明"},
	                        {key:"use", label:"用途"},
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


function getleafnode(sortid, zNodes,if_rootnode) {
	//alert(zNodes[0].node_path);
	var treeid=null;
	var sort_node_path=null;
	if(if_rootnode=='0'){   //非根节点
		//获取tree_id
		for ( var i = 0; i < zNodes.length; i++) {
			if (sortid == zNodes[i].id) {
				treeid=zNodes[i].tree_id;
				sort_node_path=zNodes[i].node_path;
			}
		}
		//取得该树下下所有节点
		var treearray = new Array();
		for ( var i = 0; i < zNodes.length; i++) {
			if (treeid == zNodes[i].tree_id) {
				treearray[treearray.length] = zNodes[i];
			}
		}
		//alert("所有节点为：  "+treearray);
		//获取该节点所有下级节点
		var LowerNodeArray = new Array();
		for ( var i = 0; i < treearray.length; i++) {
			var node_path=treearray[i].node_path;
			if(node_path>=sort_node_path&&node_path.indexOf(sort_node_path)>=0){
				LowerNodeArray[LowerNodeArray.length]=treearray[i];
			}
			
		}
		//alert("所有下级节点为：  "+LowerNodeArray);
		//获取叶子节点
		var leafNodeArray=new Array();
		for ( var i = 0; i < LowerNodeArray.length; i++) {
			var nodeId=LowerNodeArray[i].id;
			var tempArray=new Array();
			for(var j=0;j<LowerNodeArray.length;j++){
				var nodePid=LowerNodeArray[j].pid;
				if(nodeId==nodePid){
					tempArray[tempArray.length]=LowerNodeArray[j];
				}
			}
			if(tempArray.length==0){
				leafNodeArray[leafNodeArray.length]=nodeId;
			}
			
		}
	
		//alert("叶子节点列表为：   "+leafNodeArray);
		return(leafNodeArray);
	}else{
		//取得该节点下所有节点
		var treearray = new Array();
		for ( var i = 0; i < zNodes.length; i++) {
			if (sortid == zNodes[i].tree_id) {
				treearray[treearray.length] = zNodes[i];
			}
		}
	//	alert("所有节点列表为：  "+treearray);
		//获取叶子节点
		var leafNodeArray=new Array();
		for ( var i = 0; i < treearray.length; i++) {
			var nodeId=treearray[i].id;
			var tempArray=new Array();
			for(var j=0;j<treearray.length;j++){
				var nodePid=treearray[j].pid;
				if(nodeId==nodePid){
					tempArray[tempArray.length]=treearray[j];
				}
			}
			if(tempArray.length==0){
				leafNodeArray[leafNodeArray.length]=nodeId;
			}
			
		}
		//alert("leafnode为："+leafNodeArray);
		return(leafNodeArray);
	}
}

</script>
<title>Insert title here</title>
<style>
</style>
</head>
<body class=" yui-skin-sam" >
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/examDbManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="#">试题库管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">试题库管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">试题库管理</a>
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>

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
				<td> <input type=button id="button" value="批量删除" class="button" onclick="bulkdelete()"></td>
			</tr>
		</table>
</div>
		<!-- 修改的弹窗 -->
		<table>
		<tr>
		<td>
		<div id="container">
			<div id="panel1" style="z-index: 1999">
				<div class="hd" style="z-index: 2000">修改试题库</div>
				<div class="bd" style="z-index: 2000">
					<form name="modifyForm"  id="modifyForm">
						<input type="hidden" id="modifyid" name="modifyid" />
						<table>
							<tr>
								<td width="150px" nowrap align="right">试题库名称：</td>
								<td nowrap><input type="text" id="modifyName" class="text"
									name="newname" /><span class="red"> *</span></td>
							</tr>
							<tr>
								<td width="150px" nowrap align="right">试题库标识符：</td>
								<td nowrap><input type="text" id="modifyIdentifier" class="text"
									name="newidentifier" /><span class="red"> *</span></td>
							</tr>
							<tr>
								<td nowrap align="right" width="150px">分类配置：</td>
								<td nowrap width="30"><div id="modifySort"
										name="newsort"></div></td>

							</tr>
							
							<tr>
								<td nowrap align="right" width="100">试题库用途：</td>
								<td nowrap width="30"><select id="modifyUse"
									name="newuse"></select></td>

							</tr>

						<tr>
								<td nowrap align="right">试题库说明：</td>
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
		
		</td>
			</tr>
		</table>
	
		
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
		}
		YAHOO.util.Event.addListener(window, "load", init);
	
	</script>
	
	<script type="text/javascript">
	CreateDataTableForSort();
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