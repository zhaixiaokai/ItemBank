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
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../../servJs/NestedTables_V2.js" charset="GBK"></script>
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
<script type="text/javascript">

/* //获取角色列表
function	RoleList(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetRoleList",
		success : function(result) {
			TeacherList	=	result.data;
			$("#SelectRoleList ").empty();
			$("#SelectRoleList").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectRoleList").append("<option value=\""+result.data[i].role_id+"\">"+
						result.data[i].name+"</option>");
			}
		},
		error:function(){
		}
	});
} */


//点击某一节点时触发的事件
/* function getStudentDepartmentId(){

	document.getElementById("StudentDepartment").innerHTML=this.innerHTML;
	document.getElementById("StudentDepartmentId").value=this.id;
 
} */
</script>
<script type="text/javascript">
    
    var CourseId = "";
    CourseId ="<%= request.getParameter("SelectedCourseId")%>";
<%--     var TeachingMaterialId = "";
    TeachingMaterialId ="<%= request.getParameter("SelectedTeachingMaterialId")%>"; --%>
	
	//从数据库中获取章节列表
	function GetFunctionTree() {
		var FunctionTreeData = null;
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "FunctionGetTeachingProgressTreeDataAction?MyCourseId="+CourseId,
			success : function(result) {
				//alert(result);
				FunctionTreeData = result;
			},
			error : function() {
			}
		});
		return FunctionTreeData;
	}

	var ChildrenJson; //用于存储截取的子json串
	var	GetKnowledgeList=null;
	var	GetAllKnowledgeList=null;
	var	TermId=null;
	var	flag=false;
	var	param="";
	
	//点击某一checkbox触发的事件
	function CheckBoxSelectEvent() {

		
		ChildrenJsonGet(FunctionJson,this.id);
		
		ForwardNodeStatus(ChildrenJson,this);
		
		BackNodeStatus(FunctionJson,this,this);
	}
	
    //获取叶子节点的Id
	function GetLeafNodes(json)
	{
		for(var i=0;i<json.length;i++)
		{
			if(json[i].children==undefined)
			{
				if(flag==false){
					param+=json[i].id;
					flag=true;
				}
				else{
					param+=" "+json[i].id;
				}
				//$("#GL_"+json[i].id).bind('click',ModifyRow(json[i].id));
				document.getElementById("GL_"+json[i].id).onclick=function(){
					ModifyRow(this.id);
				};
			}
			else
			{
				GetLeafNodes(json[i].children);
			}
		}	
	}

    //获取知识点列表1
	function	KnowledgeList(){
		GetLeafNodes(FunctionJson);
		
//		alert(param);
		
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "KnowledgeListGetByTermIdAction",
			data:"LeafNodeIDs="+param,
			success : function(result) {
				GetKnowledgeList=result.data;
				/* $("#SelectKnowledgePoint ").empty();
				$("#SelectKnowledgePoint").append("<option value=\"\">--请选择--</option>"); */
				for(var i=0;i<result.data.length;i++){
					$("#TD_"+result.data[i].progress_teaching_node_id).empty();
				} 
				for(var	i=0;i<result.data.length;i++){
/* 					if(i==0)
						document.getElementById("TD_"+result.data[i].chapter_id).innerHTML+=result.data[i].knowledgepointname;
					else */
						document.getElementById("TD_"+result.data[i].progress_teaching_node_id).innerHTML+="<br>"+result.data[i].knowledgepointname;
				}
			},
			error:function(){
			}
		});
	}
    
    
	//获取知识点列表2：按课程编号获取该课程下所有知识点信息
	function	AllKnowledgeList(){
		
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "AllKnowledgeListGetByCourseIdAction",
			data:"MyCourseId="+CourseId,
			success : function(result) {
				GetAllKnowledgeList=result.data;
				var tablecontent='';
				
				/* for(var i=0;i<result.data.length;i++){
					$("#TD_"+result.data[i].chapter_id).empty();
				} 
				for(var	i=0;i<result.data.length;i++){
						document.getElementById("TD_"+result.data[i].chapter_id).innerHTML+="<br>"+result.data[i].knowledgepointname;
				} */
				$("#knowledgetable ").empty();
				tablecontent+='<table><tr><td align=center>'+CourseId+'</td></tr><tr><td><table align=center><tr>';
				var s=0;//控制每行显示3个数据
				for(var	j=0;j<result.data.length;j++){
						var knowledgename=result.data[j].name;
						var knowledgeid=result.data[j].knowledge_point_id;
						tablecontent+='<td style="border: 0; width: 100px" ><input type="checkbox" id="'+knowledgeid+'" name="box[]">'+knowledgename+'</td>';
						s=s+1;	
						if(s==3){
							tablecontent+='</tr><tr>';
							s=0;
							}
						}
				tablecontent+='</tr></table></td></tr></table>';
				$("#knowledgetable ").append(tablecontent);
			},
			error:function(){
			}
		});
	}
	
	// 根据点击的checkbox 的id 获取其之后的json串，并存储在全局变量ChildrenJson中
	function ChildrenJsonGet( json , id ){
	

	 		for( var i = 0; i < json.length; i++){
				var CheckBoxId="CB_"+json[i].id;
				// 迭代过程中，一旦某一json串的id与点击的checkbox的id相同，返回其之后的所有json串
				if(CheckBoxId==id){
					
					ChildrenJson = json[i].children;
					
				}
			
				else if(json[i].children!= undefined )
					ChildrenJsonGet(json[i].children,id);	
			} 	
	}
	
	
	// 对得到的子json串进行遍历，实现父节点状态改变以后，子节点状态也发生相应的变化
	function ForwardNodeStatus(json,o) {
		

	if (json == undefined) {

		} 

	else {
			for ( var i = 0; i < json.length; i++) {
				var CheckBoxId = "CB_" + json[i].id;

				var CheckBox = document.getElementById(CheckBoxId);
			 	if (json[i].leaf == "true") {

					CheckBox.checked = o.checked;
				} else {

					CheckBox.checked = o.checked;

					ForwardNodeStatus(json[i].children, CheckBox);
				} 
		

			}
		}
	}
	
	//点击某一子checkbox以后，其所属的父checkbox也相应的改变
	function BackNodeStatus(json,checkbox,o){
		var flag=0;// flag表示是否在遍历整个functionTree时候找到所点击的checkbox的id，若找到则为1，否则为0
		
		for(var i=0;i<json.length;i++){
			var CheckBoxId="CB_"+json[i].id;
			var CheckBox = document.getElementById(CheckBoxId);
			
			if(CheckBoxId==o.id){
				flag=1;
				
				}
			else{
				if(json[i].children!= undefined ){
					flag = BackNodeStatus(json[i].children,CheckBox,o);
					if(flag == 1)
						CheckBox.checked = o.checked;
						
						
				}
				
			}
			  if(flag == 1){
				 break;  
			 } 
				
		}
		return flag;
	}
	
	
	//弹出修改对话框
	function ModifyRow(leafId){
//		alert(leafId);
		TermId=leafId.substring(3);
//		alert(TermId);
	    
	    //根据客户端浏览器高度设置半透明罩的高度 
 	    document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
	    document.getElementById('fade').style.display='block';
	    YAHOO.example.container.panel1.show(); 
	    
	    $.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "AllKnowledgeListGetByCourseIdAction",
			data:"MyCourseId="+CourseId,
			success : function(result) {
				GetAllKnowledgeList=result.data;
				var tablecontent='';
			
				$("#knowledgetable ").empty();
				tablecontent+='<table><tr><td align=center>'+CourseId+'</td></tr><tr><td><table align=center><tr>';
				var s=0;//控制每行显示5个数据
				for(var	j=0;j<result.data.length;j++){
						var knowledgename=result.data[j].name;
						var knowledgeid=result.data[j].knowledge_point_id;
						tablecontent+='<td style="border: 0; width: 100px" ><input type="checkbox" id="'+knowledgeid+'" name="box[]">'+knowledgename+'</td>';
						s=s+1;	
						if(s==5){
							tablecontent+='</tr><tr>';
							s=0;
							}
						}
				tablecontent+='</tr></table></td></tr></table>';
				$("#knowledgetable ").append(tablecontent);
				
			},
			error:function(){
			}
		});
	    
	    //获得该课程下已管理的知识点的信息
	    $.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "KnowledgeListForCheckGetByCourseIdAction",
			data:"MyCourseId="+CourseId,
			success : function(result) {
				
				var m = document.getElementsByName('box[]');//所有chechbox
				for(var	t=0;t<result.data.length;t++)
				{
					if(result.data[t].progress_teaching_node_id==TermId) {
						document.getElementById(result.data[t].knowledge_point_id).checked = true;    //将已包含的知识点的选上  
			    	}
					else{
						document.getElementById(result.data[t].knowledge_point_id).disabled="disabled";  //将本章节未包含、但其他章节已包含的知识点的前的选择框置灰色  
					}
				} 
				
			},
			error:function(){
			}
		});
	    
	    
	}
	
	function SaveModify(){
		//获取所有的Checkbox
		var selected_point=[];
		var checkbox = document.getElementsByName('box[]');
		for(var	t=0;t<GetAllKnowledgeList.length;t++){
			var kp_id=GetAllKnowledgeList[t].knowledge_point_id;
			if(checkbox[t].checked){
				selected_point[selected_point.length]=kp_id;
			}
		}
//		alert(selected_point);
//		alert(TermId);
	    //保存修改
	    if(confirm("确定要保存修改？")){
			$.ajax( {
				type : "post",
				dataType : "json",
				async : false,
				url : "SaveKnowledgePointConfigByTermAction?selected_point="+selected_point+"&MyTermId="+TermId,
				success : function(result) {
					alert(result.result);
					
					/* FunctionJson = GetFunctionTree();
					initTableContainer(FunctionJson,"TableDiv");
					CreateMultiTableInterface(FunctionJson,"TableDiv",CheckBoxSelectEvent); */
					ClearFade();
					KnowledgeList();
				},
				error:function(){
				}
			}); 
	    }
	    else{
		}
	}
	
	//清除屏幕锁定，并且隐藏修改框 
	function ClearFade(){
		document.getElementById("fade").style.display="none";
		YAHOO.example.container.panel1.hide();
	}
	
	
	function Save(){
		var RoleListId=document.getElementById("SelectRoleList").value;
		if(RoleListId==""){
			alert("请选择需要授权的角色！");
			return;
		}
		//获取页面上的所有checkbox对象
		var CheckBoxList=document.getElementsByName("CheckBoxes");
		//保存点击的checkbox的id
		var param="";
		var flag=false;//点击的checkbox的个数
		for(var i=0;i<CheckBoxList.length;i++){
			if(CheckBoxList[i].checked){
				
				if(flag==false){
					param+=CheckBoxList[i].id.substring(3);	
					flag=true;
				}
				else
					param+=" "+CheckBoxList[i].id.substring(3);				
			}
			
		}
		if(confirm("确定添加？")){
			$.ajax({
				type:"post",
				dataType:"json",
				url:"RoleAuthorityAction",
				data:{"FunctionId":param,"RoleId":RoleListId},
				success:function(result){
					alert(result.text);
					document.location.href="RoleAuthority.jsp";
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
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="../functionList/teachingManage.jsp">教学管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">教学进度管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="TeachingProgressM.jsp">教学进度管理</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<table height="30px" width="100%">
		<!-- <tr>
			<td>所属角色：</td>
			<td><select name="SelectRoleList" id="SelectRoleList">
					<option value="">--请选择--</option>
			</select><span class="red"> *</span></td>

		</tr> -->
		<!-- <tr>
		<td width="70" style="border: 0">知识点信息:</td>
				<td><select name="SelectKnowledgePoint" id="SelectKnowledgePoint"
					onchange="#">
						<option value="">--请选择--</option>
				</select></td>
		</tr> -->

	</table>
	<!-- 修改的弹窗 -->
		<div id="container">
			<div id="panel1" style="z-index: 1999">
				<div class="hd" style="z-index: 2000">修改信息</div>
				<div class="bd" style="z-index: 2000">
					<form name="modifyForm" action="UpdateAction"
						id="modifyForm">
						<input type="hidden" name="OldId" id="OldId" />
						<!-- <table>
							<tr>
								<td width="100px">教材名称：</td>
								<td><input type="text" name="TeachingMaterialName" id="TeachingMaterialName" /></td>
							</tr>
							<tr>
								<td width="100px">教材编号：</td>
								<td><input type="text" name="TeachingMaterialID" id="TeachingMaterialID" /></td>
							</tr>
						</table> -->
						<div id="knowledgetable" align="center" style="margin-top: 10px;">
				
			            </div>
						<div align="center">
							<input type="button" value="修改" onclick="SaveModify();" /> 
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
	<div id="TableDiv"></div>
	<table align="center">
	<tr>
	<!-- <td ><input type="button" class="button" value="保存" onclick="Save();"></td> -->
	</tr>
	</table>
	<!-- <input type="checkbox" disabled="disabled"/> -->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	
	<script type="text/javascript">
	//RoleList();
	var FunctionJson = GetFunctionTree();
	initTableContainer(FunctionJson,"TableDiv");
	CreateMultiTableInterface(FunctionJson,"TableDiv",CheckBoxSelectEvent);
	KnowledgeList();
	
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