<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">   
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

<script src="../js/checkAll.js" type="text/javascript"></script>
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>

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
var	SelectedSpecialFieldId="";
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
				$("#SelectCurse").append("<option id=\""+result.data[i].curse_id+
						"\" value=\""+result.data[i].curse_id+"\">"+result.data[i].curse_name+"</option>");
			}
		},
		error:function(){
		}
	});
	
}
function getMajor(o){
	document.getElementById("College").innerHTML=o;
}
function	GetCourseBySpecialfield(){
	getMajor(this.innerHTML);
	getCurse(this);
}
function	QureyTeachingClass(){
	//alert(document.getElementById("SelectCurse").value);
	if(document.getElementById("SelectCurse").value==""){
		alert("请选择课程");
		return;
	}
	SelectedSpecialFieldId=document.getElementById("SelectCurse").value;
	CreateTeachingClassTableByCurseId(SelectedSpecialFieldId);
}
function CreateTeachingClassTableByCurseId(SpecialFieldId){
	(function () {
		var timestamp = (new Date()).valueOf();  
	    var myDataSource = new YAHOO.util.DataSource("TeachingClassTableDataSelectByFieldIdAction?FieldId="+SpecialFieldId+"&timestamp="+timestamp+"&");  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
           resultsList: "records",
           fields: ["curse_class_id","class_name","curse_id","teacher_id","rn","teaching_material_id","teachername","tmname","explain"],
           metaFields: {
               totalRecords: "totalRecords",
               paginationRecordOffset : "startIndex",
               sortKey: "sort",
               sortDir: "dir"
           }  
	    };    
	      
	    // 自定义内容格式化方法   
        YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
            var Id=oRecord.getData("curse_class_id");
            var Name=oRecord.getData("class_name");
            var TId=oRecord.getData("teacher_id");
            var TMId=oRecord.getData("teaching_material_id");
            var	Explain=oRecord.getData("explain"); 
            elCell.innerHTML="<a href='#' name='"+Id+"' onclick='DeleteRow(this)'>删除</a> "
            	+"| <a href='#' name='"+Id+"' onclick=\"ModifyRow('"+Id+"','"+Name+"','"+TId+"','"+TMId+"','"+Explain+"')\">修改</a>";
        	//alert(elCell.innerHTML);
        }  
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('curse_class_id')
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
	        	            {key:"curse_class_id", label:"班级编号"},
	        	            {key:"class_name", label:"班级名称"},
	        	            {key:"teachername", label:"教师姓名"},
	        	            {key:"tmname", label:"教材名称"},
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
	     
/* 	    return {   
	   　    ds: myDataSource,   
	        dt: myDataTable   
	    };  */
	})();
}
//删除一条记录
function DeleteRow(o){
	var	id=o.name;
	if(confirm("确定要删除该记录？")){
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "TeachingClassDeleteAction?DeleteId="+id,
			success : function(result) {
				alert(result.result);
				//删除完成后重新载入数据表~
				CreateTeachingClassTableByCurseId(SelectedSpecialFieldId);
			}
		});
	}
	else{
	}
}
//修改一条记录,将参数传入修改对话框
function ModifyRow(Id,Name,TeacherId,TeachingMaterialId,Explain){
    document.getElementById("ClassId").value=Id;
    document.getElementById("ClassName").value=Name;
    document.getElementById("TeacherId").value=TeacherId;
    document.getElementById("SelectTeachingMaterial").value=TeachingMaterialId;
    document.getElementById("OldClassId").value=Id;
    var	ExplainNew	=	Explain.replace(/n1/g,"\n");
    ExplainNew=ExplainNew.replace(/n2/g,"n");
    document.getElementById("ClassExplain").value=ExplainNew;
    //根据客户端浏览器高度设置半透明罩的高度 
    document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
    document.getElementById('fade').style.display='block';
    YAHOO.example.container.panel1.show();
}
//检查数据合法新并且提交，显示处理结果
function	CheckValidation(){
	
	

	if(document.getElementById("ClassName").value==""){
		alert("请填写班级名称");
		return;
	}
	if(!CheckIfChinaNumbLetter(document.getElementById("ClassName").value)){
		alert("班级名称只能由汉字、字母数字组成");
		return;
	}

	if(document.getElementById("ClassName").value.length>20){
		alert("班级名称长度最多20个字符");
		return;
	}
	if(document.getElementById("ClassId").value==""){
		alert("请填写班级编号");
		return;
	}
	if(!CheckIfIsLetter_Number(document.getElementById("ClassId").value)){
		alert("班级编号只能由字母、数字、'_'组成");
		return;
	}
	if(document.getElementById("ClassId").value.length>20){
		alert("班级编号长度最多20个字符");
		return;
	}
	if(document.getElementById("TeacherId").value==""){
		alert("请填写教师工作证号码");
		return;
	}
	if(!CheckIfIsLetterNumber(document.getElementById("TeacherId").value)){
		alert("教师工作证号码只能由字母、数字组成");
		return;
	}
	if(document.getElementById("TeacherId").value.length>20){
		alert("教师工作证号长度最多20个字符");
		return;
	}
	if(document.getElementById("SelectTeachingMaterial").value==""){
		alert("请选择班级使用的教材");
		return;
	}
	if($('#ClassExplain').val().replace(/[^\x00-\xFF]/g,'**').length>300){
		alert("班级说明最多300个字符");
		return;
	}
	
	
 	$(function() {
		var options = {
			type : "post",
			dataType : "json",
			success : function(result){
				alert(result.text);
				if(result.result=="succ"){
					ClearFade();
					//重新载入数据
					CreateTeachingClassTableByCurseId(SelectedSpecialFieldId);
				}
			},
		};
		$('#modifyForm').ajaxSubmit(options);
	});
}
//清除屏幕锁定，并且隐藏修改框 
function ClearFade(){
	document.getElementById("fade").style.display="none";
	YAHOO.example.container.panel1.hide();
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
	getTeacherList(this);
}
function getTeacherList(o){
	var	SelectedDepartmentId	=	o.id;
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
		},
		error:function(){
		}
	});
}
function EndSelectTeacher(e){
	document.getElementById("TeacherId").value=e.value;
}
function GetTeachingMaterialOptions(){
	var	selectedCurseId	=	document.getElementById("SelectCurse").value;
	
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "TeachingMaterialGetByCurseId",
		data:"CurseId="+selectedCurseId,
		success : function(result) {
			$("#SelectTeachingMaterial ").empty();
			$("#SelectTeachingMaterial").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectTeachingMaterial").append("<option id=\""
						+result.data[i].teaching_material_id+"\" value=\""
						+result.data[i].teaching_material_id+"\">"
						+result.data[i].name+"</option>");
			}
		 },
		error:function(){
		}
	});
}
function BulkDelete(){
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
			url : "TeachingClassBulkDeleteAction",
			data:"DeleteId="+param,
			success : function(result) {
				alert(result.text);
				CreateTeachingClassTableByCurseId(SelectedSpecialFieldId);
			 },
			error:function(){
			}
		});
	}
	
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
	<input id="yui-history-field" type="hidden">
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a target="mainFrame" href="../functionList/teachingManage.jsp">教学管理</a>&nbsp;&gt;&gt;&nbsp;开课班级管理<a
			href="teachingclassManage.jsp">&nbsp;&gt;&gt;&nbsp;管理开课班级</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div id="act_content2">
		<table style="font-size: 12px" width="100%" height=50px>
			<tr>
				<td width="20" style="border: 0"><img
					src="../examM/image/refer.gif" width="20" height="18" /></td>
				<td width="70" style="border: 0">学院专业:</td>
				<td width="150px"><div id="SpecialField"></div></td>
				<td width="35" style="border: 0">课程:</td>
				<td><select name="SelectCurse" id="SelectCurse"
					onchange="GetTeachingMaterialOptions();">
						<option value="">--请选择--</option>
				</select></td>

				<td width="40" align="right"><input type="button"
					class="button" value="查询" onclick="QureyTeachingClass();"></td>
				<td width="40" align="right"><input type="button" name="Submit"
					value="批量刪除" class="button"
					onclick="BulkDelete();" /></td>
			</tr>
		</table>
		<!-- 修改的弹窗 -->
		<div id="container">
			<div id="panel1" style="z-index: 1999">
				<div class="hd" style="z-index: 2000">修改教学班级信息</div>
				<div class="bd" style="z-index: 2000">
					<form name="modifyForm" action="TeachingClassModifyAction"
						id="modifyForm">
						<input type="hidden" name="OldClassId" id="OldClassId" />
						<table>
							<tr>
								<td width="100px">教学班级名称：</td>
								<td><input type="text" name="ClassName" id="ClassName" /></td>
							</tr>
							<tr style="display: none;">
								<td width="100px">教学班级编号：</td>
								<td><input type="text" name="ClassId" id="ClassId" /></td>
							</tr>
							<tr>
								<td width="100px">教师教工号：</td>
								<td><input type="text" name="TeacherId" id="TeacherId" /></td>
								<td width="150px"><div id="SelectTeacherDepartment"></div></td>
								<td width="100px"><select id="SelectTeacher"
									onchange="EndSelectTeacher(this);">
										<option value="">--请选择--</option>
								</select></td>
							</tr>
							<tr>
								<td width="100px">授课教材：</td>
								<td><select name="TeachingMaterialId"
									id="SelectTeachingMaterial"><option value="">--请选择--</option></select></td>
							</tr>
							<tr>
								<td width="100px">教学班级说明：</td>
								<td><textarea id="ClassExplain" name="ClassExplain"></textarea></td>
							</tr>
						</table>
						<div align="center">
							<input type="button" value="修改" onclick="CheckValidation();" /> <input
								type="button" value="取消" onclick="ClearFade();" />
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

</body>

	<script type="text/javascript">
		//获取学院→专业多级菜单选项
		getSchoolStructureList();
		loadMenu("SpecialField",SchoolStructure,GetCourseBySpecialfield,"College");
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
</html>
