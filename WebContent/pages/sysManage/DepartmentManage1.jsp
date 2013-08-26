<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../esM/css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link rel="stylesheet" type="text/css" href="../esM/css/menu.css">
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../esM/css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
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
<script src="../js/TreeChangeStatue.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="../css/tree.css">

<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../../servJs/TeacherTreeOperate.js"
	charset="GBK"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/2.6.0/build/treeview/assets/skins/sam/treeview.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/2.6.0/build/treeview/treeview-min.js"></script>
<script src="../js/yui/build/yahoo/yahoo-min.js"></script>
<script src="../js/yui/build/json/json-min.js"></script>
<link rel="stylesheet" type="text/css" href="../js/yui/build/treeview/assets/treeview-menu.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript" src="../js/yui/build/container/container-min.js"></script>
<style>
#expandcontractdiv {border:1px dotted #dedede; background-color:#EBE4F2; margin:0 0 .5em 0; padding:0.4em;}
#treeDiv1 { background: #fff; padding:1em; margin-top:1em; }
</style>
<style type="text/css">
.black_overlay { display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%;
	background-color: #EEEEEE; z-index: 1; -moz-opacity: 0.8; opacity: .80; filter: alpha(opacity = 80); }
</style>
<script type="text/javascript">
	function GetTeacherDepTree() {
		var TeacherDepTreeData = null;
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "TeacherDepartmentGetTreeDataAction",
			data : "TableName=sys_department_tree&TreeId=departmentTree",
			success : function(result) {
				//alert(result);
				TeacherDepTreeData = result;
			},
			error : function() {
			}
		});
		return TeacherDepTreeData;
	}
	function CreateTeacherDepartmentTree() {
		var Data = GetTeacherDepTree();
		(function() {
			var tree; //will hold our TreeView instance

			function treeInit() {
				buildRandomTextNodeTree();
			}
			function buildRandomTextNodeTree() {
				tree = new YAHOO.widget.TreeView("treeDiv1", Data);
				tree.draw();
				tree.subscribe('clickEvent', function(oArgs) {
					treeNodeClick(oArgs.node);
				});
			}
			YAHOO.util.Event.onDOMReady(treeInit);

		})();
	}
	function treeNodeClick(o) {
		currentNode = o;
	}
	//清除屏幕锁定，并且隐藏修改框 
	function ClearFade(){
		document.getElementById("fade").style.display="none";
		YAHOO.example.container.panel1.hide();
	}
	//确认修改
	function ConfirmModify(){
		var	newText		=	document.getElementById("NewTeacherDepartmentText").value;
		var	newExplain	=	document.getElementById("NewTeacherDepartmentExplain").value;
		if(newText==""||newText==null){
			alert("修改失败：请填写机构名称！");
			document.modifyForm.NewFunctionText.focus();
		}
		else if(newText.length>20){
			alert("修改失败，机构名称的长度不能超过20");
			document.modifyForm.NewFunctionText.focus();		
		}
		else if(!CheckIfChinaNumbLetter(newText)){
			alert("修改失败，机构名称只能由汉字、数字、字母组成");
			document.modifyForm.NewFunctionText.focus();
		}
		else if(newExplain.length>500){
			alert("修改失败，机构节点说明的长度不能超过500");
			document.modifyForm.NewFunctionText.focus();
		}
		else{
			if(confirm("确认修改？")){
				//alert(newExplain);
	    		$.ajax( {
	    			type : "post",
	    			dataType : "text",
	    			async : false,
	    			url : "treeOperateAction.action?action=modify",
	    			data:"nodeid="+currentNode.data.id+"&node_explain="+newExplain+"&text="+newText+"&table="+"SYS_DEPARTMENT_TREE"+"&treeId="+currentNode.data.tree_id,
	    			success : function(result) {
	    				alert(result);
	    			},
	    			error:function(){
	    			}
	    		});
	    		ClearFade();
	    		CreateTeacherDepartmentTree();
			}
			
		}
	}
	function 	ConfirmAddPre(){
		var	newText		=	document.getElementById("NewTeacherDepartmentText").value;
		var	newExplain	=	document.getElementById("NewTeacherDepartmentExplain").value;
		if(newText==""||newText==null){
			alert("修改失败：请填写机构名称！");
			document.modifyForm.NewFunctionText.focus();
		}
		else if(newText.length>20){
			alert("修改失败，机构名称的长度不能超过20");
			document.modifyForm.NewFunctionText.focus();		
		}
		else if(!CheckIfChinaNumbLetter(newText)){
			alert("修改失败，机构名称只能由汉字、数字、字母组成");
			document.modifyForm.NewFunctionText.focus();
		}
		else if(newExplain.length>500){
			alert("修改失败，机构节点说明的长度不能超过500");
			document.modifyForm.NewFunctionText.focus();
		}
		else{
			//alert(newExplain);
    		$.ajax( {
    			type : "post",
    			dataType : "text",
    			async : false,
    			url : "treeOperateAction.action?action=addPre",
    			data:"nodeid="+currentNode.data.id+"&node_explain="+newExplain+"&text="+newText+"&table="+"SYS_DEPARTMENT_TREE"+"&treeId="+currentNode.data.tree_id,
    			success : function(result) {
    				alert(result);
    			},
    			error:function(){
    			}
    		});
    		ClearFade();
    		CreateTeacherDepartmentTree();
    		//alert("前置添加成功！");
		}
	}
	function ConfirmAddAfter(){
		var	newText		=	document.getElementById("NewTeacherDepartmentText").value;
		var	newExplain	=	document.getElementById("NewTeacherDepartmentExplain").value;
		if(newText==""||newText==null){
			alert("修改失败：请填写机构名称！");
			document.modifyForm.NewFunctionText.focus();
		}
		else if(newText.length>20){
			alert("修改失败，机构名称的长度不能超过20");
			document.modifyForm.NewFunctionText.focus();		
		}
		else if(!CheckIfChinaNumbLetter(newText)){
			alert("修改失败，机构名称只能由汉字、数字、字母组成");
			document.modifyForm.NewFunctionText.focus();
		}
		else if(newExplain.length>500){
			alert("修改失败，机构节点说明的长度不能超过500");
			document.modifyForm.NewFunctionText.focus();
		}
		else{
			//alert(newExplain);
    		$.ajax( {
    			type : "post",
    			dataType : "text",
    			async : false,
    			url : "treeOperateAction.action?action=addAfter",
    			data:"nodeid="+currentNode.data.id+"&node_explain="+newExplain+"&text="+newText+"&table="+"SYS_DEPARTMENT_TREE"+"&treeId="+currentNode.data.tree_id,
    			success : function(result) {
    				alert(result);
    			},
    			error:function(){
    			}
    		});
    		ClearFade();
    		CreateTeacherDepartmentTree();
		}
	}
	function ConfirmAddChild(){
		var	newText		=	document.getElementById("NewTeacherDepartmentText").value;
		var	newExplain	=	document.getElementById("NewTeacherDepartmentExplain").value;
		if(newText==""||newText==null){
			alert("修改失败：请填写机构名称！");
			document.modifyForm.NewFunctionText.focus();
		}
		else if(newText.length>20){
			alert("修改失败，机构名称的长度不能超过20");
			document.modifyForm.NewFunctionText.focus();		
		}
		else if(!CheckIfChinaNumbLetter(newText)){
			alert("修改失败，机构名称只能由汉字、数字、字母组成");
			document.modifyForm.NewFunctionText.focus();
		}
		else if(newExplain.length>500){
			alert("修改失败，机构节点说明的长度不能超过500");
			document.modifyForm.NewFunctionText.focus();
		}
		else{
    		$.ajax( {
    			type : "post",
    			dataType : "text",
    			async : false,
    			url : "treeOperateAction.action?action=addChild",
    			data:"nodeid="+currentNode.data.id+"&node_explain="+newExplain+"&text="+newText+"&table="+"SYS_DEPARTMENT_TREE"+"&treeId="+currentNode.data.tree_id,
    			success : function(result) {
    				alert(result);
    			},
    			error:function(){
    			}
    		});
    		ClearFade();
    		CreateTeacherDepartmentTree();
		}
	}
   	//对数据进行验证，如果成功执行修改
   	function	getModifyNodeInfo(){
   	    //根据客户端浏览器高度设置半透明罩的高度 
   	    document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
   	    document.getElementById('fade').style.display='block';
   	    document.getElementById("PannelHeader").innerHTML="修改教师机构信息";
   	 	document.getElementById("ConfirmButton").onclick=ConfirmModify;
   	    YAHOO.example.container.panel1.show();
   	    document.getElementById("NewTeacherDepartmentText").value=currentNode.data.label;
   	    document.getElementById("NewTeacherDepartmentExplain").value=(currentNode.data.node_explain=="null")? "无":currentNode.data.node_explain;
	}
   	//验证所填写信息合法性，并且执行添加前置节点
   	function	getAddPreviousNodeInfo(){
   	    //根据客户端浏览器高度设置半透明罩的高度 
   	    document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
   	    document.getElementById('fade').style.display='block';
   	    document.getElementById("PannelHeader").innerHTML="添加前置教师机构";
   	 	document.getElementById("ConfirmButton").onclick=ConfirmAddPre;
   	    YAHOO.example.container.panel1.show();
   	    document.getElementById("NewTeacherDepartmentText").value="";
   	    document.getElementById("NewTeacherDepartmentExplain").value="";
	
   	}
   	function	getAddAfterNodeInfo(){
   	    //根据客户端浏览器高度设置半透明罩的高度 
   	    document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
   	    document.getElementById('fade').style.display='block';
   	    document.getElementById("PannelHeader").innerHTML="添加后置教师机构";
   	 	document.getElementById("ConfirmButton").onclick=ConfirmAddAfter;
   	    YAHOO.example.container.panel1.show();
   	    document.getElementById("NewTeacherDepartmentText").value="";
   	    document.getElementById("NewTeacherDepartmentExplain").value="";   		
	}
   	function	getAddChildNodeInfo(){
   	    //根据客户端浏览器高度设置半透明罩的高度 
   	    document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
   	    document.getElementById('fade').style.display='block';
   	    document.getElementById("PannelHeader").innerHTML="添加子教师机构";
   	 	document.getElementById("ConfirmButton").onclick=ConfirmAddChild;
   	    YAHOO.example.container.panel1.show();
   	    document.getElementById("NewTeacherDepartmentText").value="";
   	    document.getElementById("NewTeacherDepartmentExplain").value="";
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
		<a href="javascript:void(0)">系统管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">组织机构管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">教师组织机构管理</a>
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>

	<div id="act_content2">
		<div id="te1" style="width: 100%; height: 80px;">
			<a href="javascript:void(0)" onclick="addPreviousNode()">添加前置机构</a> <strong>|</strong>
			<a href="javascript:void(0)" onclick="addAfterNode()">添加后置机构</a><strong>|</strong>
			<a href="javascript:void(0)" onclick="modifyCurrentNode()">修改机构</a> <strong>|</strong>
			<a href="javascript:void(0)" onclick="deleteCurrentNode()">删除机构</a> <strong>|</strong>
			<a href="javascript:void(0)" onclick="cutCurrentNode()">剪切机构</a> <strong>|</strong>
			<a href="javascript:void(0)" onclick="pasteAsPreviousNode()">前置粘帖</a>
			<strong>|</strong> <a href="javascript:void(0)"
				onclick="pasteAsAfterNode()">后置粘帖</a> <strong>|</strong> <a
				href="javascript:void(0)" onclick="PasteAsChildNode()">粘帖为子机构</a><strong>|</strong>
			<a href="javascript:void(0)" onclick="addChildeNode()">添加子机构</a>

		</div>
		<div id="container">
			<div id="panel1" style="z-index: 1999">
				<div id="PannelHeader" class="hd" style="z-index: 2000"></div>
				<div class="bd" style="z-index: 2000">
					<form name="modifyForm" action="TeacherDepartmentModifyAction"
						id="modifyForm">
						<table>
							<tr>
								<td width="100px">机构名称：</td>
								<td><input type="text" name="NewTeacherDepartmentText" id="NewTeacherDepartmentText" /></td>
							</tr>
							<tr>
								<td width="100px">机构描述：</td>
								<td>
									<textarea cols="60" rows="6" name="NewTeacherDepartmentExplain" id="NewTeacherDepartmentExplain"></textarea>
								</td>
							</tr>
						</table>
						<div align="center">
							<input id="ConfirmButton" type="button" value="确定" ">
							<input type="button" value="取消" onclick="ClearFade();" />
						</div>
					</form>
				</div>
				<div class="ft"></div>
			</div>
		</div>
		<!-- 半透罩，用来锁定屏幕 -->
		<div id="fade" class="black_overlay"></div>		
		<div id="treeDiv1"></div>
		<script type="text/javascript">
		//初始化修改信息对话框
		YAHOO.namespace("example.container");
		function init() {
			YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", { width:"600px", visible:false, constraintoviewport:true,close :false } );
			YAHOO.example.container.panel1.render();
		}
		YAHOO.util.Event.addListener(window, "load", init);
			CreateTeacherDepartmentTree();
		</script>
	</div>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>