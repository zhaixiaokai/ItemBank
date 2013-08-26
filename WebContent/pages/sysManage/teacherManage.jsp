<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.sql.*,javax.sql.rowset.serial.*"
	import="java.io.*" import="javax.servlet.*"
	import="javax.servlet.http.*" import="java.util.*"%>
<%@ page import="java.text.*" import="javax.servlet.ServletException"
	import="org.apache.commons.fileupload.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />

<title>Insert title here</title>

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
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>

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
							<!--jQuery DatePicker   -->
<link rel="stylesheet" href="../js/jquery-ui.css" />
<script type="text/javascript" src="../js/jquery-ui.js"></script> 
								<!--END  -->

<script type="text/javascript">
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
//点击某一节点时触发的事件
function getTeacherDepartmentId(){
	$("#TeacherDepartment").empty();
	$("#TeacherDepartment").append(this.innerHTML);
	//document.getElementById("TeacherDepartment").innerHTML=this.innerHTML;
	document.getElementById("TeacherDepartmentId").value=this.id;
	
 
}
function	QureyTeacherList(){
	//alert(document.getElementById("SelectCurse").value);
	if(document.getElementById("TeacherDepartmentId").value==""){
		alert("请选择所要查询的教师机构");
		return;
	}
	SelectedSpecialFieldId=document.getElementById("TeacherDepartmentId").value;
	CreateDataTableForTeacher(SelectedSpecialFieldId);
}
function CreateDataTableForTeacher(SpecialFieldId){
	(function () {
		var timestamp = (new Date()).valueOf();  
	    var myDataSource = new YAHOO.util.DataSource("TeacherListDataGetAction?FieldId="+SpecialFieldId+"&timestamp="+timestamp+"&");  
	    myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;  
	    /** 
	     * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
	     */  
	    myDataSource.responseSchema = {  
           resultsList: "records",
           fields: ["user_id","school_id","name","gender","birthday","id","telephone","email","address","rn"],
           metaFields: {
               totalRecords: "totalRecords",
               paginationRecordOffset : "startIndex",
               sortKey: "sort",
               sortDir: "dir"
           }  
	    };    
	      
	    // 自定义内容格式化方法   
	    YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord, oColumn, oData){
            var Id=oRecord.getData("user_id");
            var Name=oRecord.getData("name");
            var SchoolId=oRecord.getData("school_id");
            var Gender=oRecord.getData("gender");
            var Birthday=oRecord.getData("birthday");
            var Identicard=oRecord.getData("id");
            var Email=oRecord.getData("email");
            var Address=oRecord.getData("address");
            var Telephone=oRecord.getData("telephone");
    
            elCell.innerHTML="<a href='#' name='"+Id+"' onclick='DeleteRow(this)'>删除</a> "
            	+"| <a href='#' name='"+Id+"' onclick=\"ModifyRow('"+Id+"','"+SchoolId+"','"+Name+"','"+Gender+"','"+Birthday+"','"+Identicard+"','"+Email+"','"+Address+"','"+Telephone+"')\">修改</a>";
        	//alert(elCell.innerHTML);
        }  
        YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
            var bChecked = oData;
            bChecked = (bChecked) ? " checked=\"checked\"" : "";
            elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('user_id')
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
		        	            {key:"school_id", label:"教工号"},
		        	            {key:"name", label:"教师姓名"},
		        	            {key:"gender", label:"性别"},
		        	           /*  {key:"id",label:"身份证号码"}, */
		        	           /*  {key:"birthday", label:"出生日期"}, */
		        	            {key:"telephone",label:"电话号码"},
		        	            {key:"email", label:"邮箱"},
		        	            {key:"address", label:"办公地址"},
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
function DeleteRow(o){
	var	id=o.name;
	//alert(id);
	if(confirm("确定要删除该记录？")){
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "TeacherDeleteAction?DeleteId="+id,
			success : function(result) {
				alert(result.text);
				//删除完成后重新载入数据表~
				QureyTeacherList();
			}
		});
	}
	else{
	}
}
//修改一条记录,将参数传入修改对话框
function ModifyRow(UserId,SchoolId,Name,Gender,Birthday,Identicard,Email,Address,Telephone){
    document.getElementById("School_ID").value=SchoolId;
    document.getElementById("TeacherName").value=Name;
    document.getElementById("Gender").value=Gender;
    document.getElementById("Birthday").value=Birthday;
    document.getElementById("Identicard").value=Identicard;
    //alert(document.getElementById("Identicard").value);
    document.getElementById("Phone").value=Telephone;
    document.getElementById("Email").value=Email;
    document.getElementById("Address").value=Address.replace(/n1/g,"\n").replace(/n2/g,"n");
    document.getElementById("OldTeacherId").value=UserId;
    
    //根据客户端浏览器高度设置半透明罩的高度 
    document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
    document.getElementById('fade').style.display='block';
    YAHOO.example.container.panel1.show();
}
//清除屏幕锁定，并且隐藏修改框 
function ClearFade(){
	document.getElementById("fade").style.display="none";
	YAHOO.example.container.panel1.hide();
}
//批量删除
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
			url : "TeacherBulkDeleteAction",
			data:"DeleteId="+param,
			success : function(result) {
				alert(result.text);
				QureyTeacherList();
			 },
			error:function(){
			}
		});
	}
	
}


//检查修改数据的合法性并且提交，显示处理结果
function	CheckValidation(){
	var schoolID =document.getElementById("School_ID").value;
	var name=document.getElementById("TeacherName").value;
	var birthday=document.getElementById("Birthday").value;
	var gender=document.getElementById("Gender").value;
	var id=document.getElementById("Identicard").value;
	//alert(document.getElementById("Gender").value+","+document.getElementById("Identicard").value);
	var email=document.getElementById("Email").value;
	var phone=document.getElementById("Phone").value;
	//教工号和教师姓名是必填项，需检查非空，在满足非空的情况下验证格式的合法性
	
	if(name==""){
		alert("教师姓名为必填项，请重新输入 ");
		return;
	}else{
		if(name.length>10){
			alert("教师姓名的长度不能超过10");
			return;
		}
		if(!isZh(name)){
			if(!CheckIfIsLetter(name)){
				alert("教师姓名必须由汉字、字母组成");
				return;
				}
			
		}
	}
	if(schoolID==""){
		alert("教工号为必填项，请重新输入 ");
		return false;
	}else{
		if(schoolID.length>20){
			alert("教工号长度不能超过20");
			return;	
		}
		if(!CheckIfIsNumber(schoolID)){
			alert("教工号必须为正整数");
			return;
		}
	}
	// 其他信息在非空的前提下检查格式的合法性，若为空则不检查
	//身份证等信息为可选项，在非空的情况下判断其格式问题
	if(id!=""&&id!=" "){
		if(!isIDno(id)){
			alert("非法身份证号码");
			return;
		}
		
	}
	
	if(phone!=""&&phone!=" "){
		if(!isTel(phone)){
			if(!checkMobile(phone)){
				alert("请填写正确格式的电话号码");
				return;
			}
			
		}
	}
	
	if(email!=""&&email!=" "){
		if(!isEmail(email)){
			alert("请填写正确格式的电子邮箱");
			return;
		}
	}		
	
	
	
	if (confirm("确认修改教师信息？")) {
			$(function() {
				var options = {
					type : "post",
					dataType : "json",
					success : function(result) {
						alert(result.text);
						if (result.result == "succ") {
							ClearFade();
							//重新载入数据
							QureyTeacherList();
						}
					}

				};
				$('#modifyForm').ajaxSubmit(options);

			});

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
			href="javascript:void(0)">用户管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">教师管理</a>
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
					<td width="60" style="border: 0">所属机构:</td>
					<td width="80px"><div id="SelectTeacherDepartment"></div>
					<input type="hidden" name="TeacherDepartmentId" id="TeacherDepartmentId" value=""></td>
					<td width="40"><input class="button" type="button"
					value="查询" onclick="QureyTeacherList();"></td>
					<td align="right"><input type="button" value="批量删除" class="button" onclick="BulkDelete();"/></td>
				</tr>
			</table>
	
		<!-- 修改的弹窗 -->
		<div id="container">
			<div id="panel1" style="z-index: 1999">
				<div class="hd" style="z-index: 2000">修改教师信息</div>
				<div class="bd" style="z-index: 2000">
					<form name="modifyForm" action="TeacherUpdateAction"
						id="modifyForm">
						<input type="hidden" name="OldTeacherId" id="OldTeacherId" />
						<table>
							<tr>
								<td width="150px">教工号：</td>
								<td><input type="text" name="School_ID" id="School_ID" /></td>
							</tr>
							<tr>
								<td width="150px">教师姓名：</td>
								<td><input type="text" name="TeacherName" id="TeacherName" /></td>
							</tr>
							<tr>
								<td width="100px">性别：</td>
								<td><input type="text" name="Gender" id="Gender" /></td>
							</tr>
							<tr>
								<td width="100px">出生日期：</td>
								<td><input type="text" name="Birthday" id="Birthday" /></td>
							</tr>
							<tr>
								<td width="100px">邮箱：</td>
								<td><input type="text" name="Email" id="Email" /></td>
							</tr>
							<tr>
								<td width="100px">身份证号码：</td>
								<td><input type="text" name="Identicard" id="Identicard" /></td>
							</tr>
							<tr>
								<td width="100px">电话：</td>
								<td><input type="text" name="Phone" id="Phone" /></td>
							</tr>
							<tr>
								<td width="100px">办公地址：</td>
								<td><textarea id="Address" name="Address"></textarea></td>
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
	
	<script type="text/javascript">
		
		//加载教师机构多级菜单
		getTeacherDepartList();
		loadMenu("SelectTeacherDepartment",TeacherDepartmentNodes,getTeacherDepartmentId,"TeacherDepartment");
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
	
</body>
</html>