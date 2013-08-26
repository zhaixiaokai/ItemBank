<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link rel="stylesheet" type="text/css" href="css/menu.css">
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

<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../../servJs/SortTreeOperate.js"
	charset="GBK"></script>


<link rel="stylesheet" type="text/css"
	href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css"
	href="../js/yui/2.6.0/build/treeview/assets/skins/sam/treeview.css" />
<script type="text/javascript"
	src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript"
	src="../js/yui/2.6.0/build/treeview/treeview-min.js"></script>
<script src="../js/yui/build/yahoo/yahoo-min.js"></script>
<script src="../js/yui/build/json/json-min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../js/yui/build/treeview/assets/treeview-menu.css" />
<link rel="stylesheet" type="text/css"
	href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript"
	src="../js/yui/build/container/container-min.js"></script>
	<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<style>
#expandcontractdiv {border:1px dotted #dedede; background-color:#EBE4F2; margin:0 0 .5em 0; padding:0.4em;}
#treeDiv1 { background: #fff; padding:1em; margin-top:1em; }
</style>
<style type="text/css">
.black_overlay { display: none; position: absolute; top: 0%; left: 0%; width: 100%; height: 100%;
	background-color: #EEEEEE; z-index: 1; -moz-opacity: 0.8; opacity: .80; filter: alpha(opacity = 80); }
</style>
<script>
	// 获得分类体系名称及id
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

	//分类管理生成树数据获取
	function SortManageDataSource() {
		var SortId = verifySort();
		if (SortId == null) {
			return;
		}
		var SortManageData = null;
		$.ajax({
					type : "post",
					dataType : "json",
					async : false,
					url : "SortManageDataSourceAction?TableName=sys_ib_classification_tree&TreeId="
							+ SortId,
					success : function(result) {
						SortManageData = result;
					},
					error : function() {
					}
				});
		return SortManageData;
	}

	//生成分类体系树
	function CreateSortTree() {
		var Data = SortManageDataSource();
		(function() {
			var tree; //will hold our TreeView instance
			function treeInit() {
				buildRandomTextNodeTree();
			}
			function buildRandomTextNodeTree() {
				tree = new YAHOO.widget.TreeView("sorttree", Data);
				tree.draw();
				tree.subscribe('clickEvent', function(oArgs) {
					treeNodeClick(oArgs.node);
				});
			}
			YAHOO.util.Event.onDOMReady(treeInit);

		})();
	}
	//显示分体体系树
	function showDiv() {
		var options = document.getElementById("SelectSort").options;
		var index = document.getElementById("SelectSort").selectedIndex;
		var selectedOption = options[index];
		if (selectedOption.id == '') {
			alert("请选择分类体系");
			return;
		}
		CreateSortTree();
		document.getElementById("sortdiv").style.visibility = "visible";
	}

	function treeNodeClick(o) {
		currentNode = o;
	}
	//清除屏幕锁定，并且隐藏修改框 
	function ClearFade() {
		document.getElementById("fade").style.display = "none";
		YAHOO.example.container.panel1.hide();
	}
	//确认修改
	function ConfirmModify() {
		var newExplain = document.getElementById("NewSortExplain").value;

		if (document.getElementById("NewSortText").value == ""
			|| document.getElementById("NewSortText").value == null) {
		alert("修改失败：请填写分类名称！");
		document.modifyForm.NewSortText.focus();
		return false;
	}
		
	if (!CheckIfChinaNumbLetter(document.getElementById("NewSortText").value)){
		alert("分类名称格式有误，请输入汉字，数学或者字母，20字符以内 ");
		document.modifyForm.NewSortText.focus();
		return false;
	}
	if(document.getElementById("NewSortText").value.length>20){
		alert("分类名称长度超出范围，长度应该在20字符以内，请重新输入");
		document.modifyForm.NewSortText.focus();
		return false;
	}if(newExplain.replace(/[^\x00-\xFF]/g,'**').length>300){
		alert("分类描述过长，请输入300字节以内说明。注：汉字一位是两字节，数字和字母一位是一字节 ");
		document.modifyForm.NewSortExplain.focus();
		return false;
	}else {
			if (confirm("确认修改？")) {
				var newText = document.getElementById("NewSortText").value;
				//alert(newExplain);
				$.ajax({
					type : "post",
					dataType : "json",
					async : false,
					url : "treeOperateAction.action?action=modify",
					data : "nodeid=" + currentNode.data.id + "&node_explain="
							+ newExplain + "&text=" + newText + "&table="
							+ "SYS_IB_CLASSIFICATION_TREE" + "&treeId="
							+ currentNode.data.tree_id,
					success : function(result) {
					},
					error : function() {
					}
				});
				ClearFade();
				alert("分类修改成功");
				CreateSortTree();
			}

		}
	}
	function ConfirmAddPre() {
		var newExplain = document.getElementById("NewSortExplain").value;

		if (document.getElementById("NewSortText").value == ""
				|| document.getElementById("NewSortText").value == null) {
			alert("添加失败：请填写分类名称！");
			document.modifyForm.NewSortText.focus();
			return false;
		}
			
		if (!CheckIfChinaNumbLetter(document.getElementById("NewSortText").value)){
			alert("分类名称格式有误，请输入汉字，数学或者字母，20字符以内 ");
			document.modifyForm.NewSortText.focus();
			return false;
		}
		if(document.getElementById("NewSortText").value.length>20){
			alert("分类名称长度超出范围，长度应该在20字符以内，请重新输入");
			document.modifyForm.NewSortText.focus();
			return false;
		} if(newExplain.replace(/[^\x00-\xFF]/g,'**').length>300){
			alert("分类描述过长，请输入300字节以内说明。注：汉字一位是两字节，数字和字母一位是一字节 ");
			document.modifyForm.NewSortExplain.focus();
			return false;
		}else {
			if(confirm("确认添加前置分类？")){
				
			var newText = document.getElementById("NewSortText").value;
			$.ajax({
				type : "post",
				dataType : "text",
				async : false,
				url : "treeOperateAction.action?action=addPre",
				data : "nodeid=" + currentNode.data.id + "&node_explain="
						+ newExplain + "&text=" + newText + "&table="
						+ "SYS_IB_CLASSIFICATION_TREE" + "&treeId="
						+ currentNode.data.tree_id,
				success : function(result) {
				},
				error : function() {
				}
			});
			ClearFade();
			alert("前置分类添加成功");
			CreateSortTree();
			}
		}
	}
	function ConfirmAddAfter() {
		var newExplain = document.getElementById("NewSortExplain").value;

		if (document.getElementById("NewSortText").value == ""
			|| document.getElementById("NewSortText").value == null) {
		alert("添加失败：请填写分类名称！");
		document.modifyForm.NewSortText.focus();
		return false;
	}
		
	if (!CheckIfChinaNumbLetter(document.getElementById("NewSortText").value)){
		alert("分类名称格式有误，请输入汉字，数学或者字母，20字符以内 ");
		document.modifyForm.NewSortText.focus();
		return false;
	}
	if(document.getElementById("NewSortText").value.length>20){
		alert("分类名称长度超出范围，长度应该在20字符以内，请重新输入");
		document.modifyForm.NewSortText.focus();
		return false;
	}  if(newExplain.replace(/[^\x00-\xFF]/g,'**').length>300){
		alert("分类描述过长，请输入300字节以内说明。注：汉字一位是两字节，数字和字母一位是一字节 ");
		document.modifyForm.NewSortExplain.focus();
		return false;
	}else {
		if(confirm("确认添加后置分类？")){
			
			var newText = document.getElementById("NewSortText").value;
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "treeOperateAction.action?action=addAfter",
				data : "nodeid=" + currentNode.data.id + "&node_explain="
						+ newExplain + "&text=" + newText + "&table="
						+ "SYS_IB_CLASSIFICATION_TREE" + "&treeId="
						+ currentNode.data.tree_id,
				success : function(result) {
				},
				error : function() {
				}
			});
			ClearFade();
			alert("后置分类添加成功");
			CreateSortTree();
		}
		}
	}
	function ConfirmAddChild() {
		var newExplain = document.getElementById("NewSortExplain").value;
		if (document.getElementById("NewSortText").value == ""
			|| document.getElementById("NewSortText").value == null) {
		alert("添加失败：请填写分类名称！");
		document.modifyForm.NewSortText.focus();
		return false;
	}
		
	if (!CheckIfChinaNumbLetter(document.getElementById("NewSortText").value)){
		alert("分类名称格式有误，请输入汉字，数学或者字母，20字符以内 ");
		document.modifyForm.NewSortText.focus();
		return false;
	}
	if(document.getElementById("NewSortText").value.length>20){
		alert("分类名称长度超出范围，长度应该在20字符以内，请重新输入");
		document.modifyForm.NewSortText.focus();
		return false;
	}if(newExplain.replace(/[^\x00-\xFF]/g,'**').length>300){
		alert("分类描述过长，请输入300字节以内说明。注：汉字一位是两字节，数字和字母一位是一字节 ");
		document.modifyForm.NewSortExplain.focus();
		return false;
	} else {
		if(confirm("确认添加子分类？")){
			var newText = document.getElementById("NewSortText").value;
			
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "treeOperateAction.action?action=addChild",
				data : "nodeid=" + currentNode.data.id + "&node_explain="
						+ newExplain + "&text=" + newText + "&table="
						+ "SYS_IB_CLASSIFICATION_TREE" + "&treeId="
						+ currentNode.data.tree_id,
				success : function(result) {
				},
				error : function() {
				}
			});
			ClearFade();
			alert("子分类添加成功");
			CreateSortTree();
		}
		}
	}
	//对数据进行验证，如果成功执行修改
	function getModifyNodeInfo() {
		//根据客户端浏览器高度设置半透明罩的高度 
		document.getElementById('fade').style.height = document.body.clientHeight
				+ "px";
		document.getElementById('fade').style.display = 'block';
		document.getElementById("PannelHeader").innerHTML = "修改分类信息";
		document.getElementById("ConfirmButton").onclick = ConfirmModify;
		YAHOO.example.container.panel1.show();
		document.getElementById("NewSortText").value = currentNode.data.label;
		if(currentNode.data.node_explain == "null"||currentNode.data.node_explain==""){
			document.getElementById("NewSortExplain").value="无";
		}else{
			document.getElementById("NewSortExplain").value=currentNode.data.node_explain.replace(/n1/g,"\n").replace(/n2/g,"n");
		}
	}
	//验证所填写信息合法性，并且执行添加前置节点
	function getAddPreviousNodeInfo() {
		var sortNodeId=currentNode.data.id;
		//判断该节点是否为根节点
		var ifRootNode=null;
		$.ajax({
				type : "post",
				dataType : "text",
				async : false,
				url : "sortTreeOperateActionIfRootIdAction?sortNodeId="+sortNodeId,
				success : function(result) {
					if(result=="1"){
						alert("该分类节点为根节点，不能添加前置分类，请重新选择分类节点");
						ifRootNode=result;
						return false;
					}
				},
				error : function() {
				}
			});
		 if(ifRootNode=="1"){
			return false;
		}
		//根据客户端浏览器高度设置半透明罩的高度 
		document.getElementById('fade').style.height = document.body.clientHeight
				+ "px";
		document.getElementById('fade').style.display = 'block';
		document.getElementById("PannelHeader").innerHTML = "添加前置分类";
		document.getElementById("ConfirmButton").onclick = ConfirmAddPre;
		YAHOO.example.container.panel1.show();
		document.getElementById("NewSortText").value = "";
		document.getElementById("NewSortExplain").value = "";

	}
	function getAddAfterNodeInfo() {
		var sortNodeId=currentNode.data.id;
		//判断该节点是否为根节点
		var ifRootNode=null;
		$.ajax({
				type : "post",
				dataType : "text",
				async : false,
				url : "sortTreeOperateActionIfRootIdAction?sortNodeId="+sortNodeId,
				success : function(result) {
					if(result=="1"){
						alert("该分类节点为根节点，不能添加后置分类，请重新选择分类节点");
						ifRootNode=result;
						return false;
					}
				},
				error : function() {
				}
			});
		 if(ifRootNode=="1"){
			return false;
		}
		//根据客户端浏览器高度设置半透明罩的高度 
		document.getElementById('fade').style.height = document.body.clientHeight
				+ "px";
		document.getElementById('fade').style.display = 'block';
		document.getElementById("PannelHeader").innerHTML = "添加后置分类";
		document.getElementById("ConfirmButton").onclick = ConfirmAddAfter;
		YAHOO.example.container.panel1.show();
		document.getElementById("NewSortText").value = "";
		document.getElementById("NewSortExplain").value = "";
	}
	function getAddChildNodeInfo() {
		//根据客户端浏览器高度设置半透明罩的高度 
		document.getElementById('fade').style.height = document.body.clientHeight
				+ "px";
		document.getElementById('fade').style.display = 'block';
		document.getElementById("PannelHeader").innerHTML = "添加子分类";
		document.getElementById("ConfirmButton").onclick = ConfirmAddChild;
		YAHOO.example.container.panel1.show();
		document.getElementById("NewSortText").value = "";
		document.getElementById("NewSortExplain").value = "";
	}
	
	//删除节点
   	function	deleteCurrentNode(){
   		if(currentNode==null){
   			alert("请选择分类");
   		}
   		else{
   			//判断该节点是否为根节点，若是根节点则不能删除
   			var sortNodeId=currentNode.data.id;
   			var ifRootNode=null;
   			$.ajax({
   					type : "post",
   					dataType : "text",
   					async : false,
   					url : "sortTreeOperateActionIfRootIdAction?sortNodeId="+sortNodeId,
   					success : function(result) {
   						if(result=="1"){
   							alert("该分类节点为根节点，不能删除，请重新选择分类节点");
   							ifRootNode=result;
   							return false;
   						}
   					},
   					error : function() {
   					}
   				});
   			 if(ifRootNode=="1"){
   				return false;
   			}
   			if(confirm("确定要删除数据吗？")){
   				//删除当前节点
   				if(currentNode.data.leaf=="true"){
       				deleteNode(currentNode);
   				}
   				else{
   					if(confirm("该操作将删除其目录下所有分类！")){
   						deleteNode(currentNode);
   					}
   				}
   				
   			}
   		}
   	}
	
	//剪切节点
   	function	cutCurrentNode(){
   		if(currentNode==null){
   			alert("请选择分类");
   			return;
   		}
   		else{
   			var sortNodeId=currentNode.data.id;
   			var ifRootNode=null;
   			$.ajax({
   					type : "post",
   					dataType : "text",
   					async : false,
   					url : "sortTreeOperateActionIfRootIdAction?sortNodeId="+sortNodeId,
   					success : function(result) {
   						if(result=="1"){
   							alert("该分类节点为根节点，不能被剪切，请重新选择分类节点");
   							ifRootNode=result;
   							return false;
   						}
   					},
   					error : function() {
   					}
   				});
   			 if(ifRootNode=="1"){
   				return false;
   			}
   			//获取源节点
       		SourceNode	=	currentNode;
   			alert("分类 "+SourceNode.data.label+" 被剪切");
   		}
   	}
	
  //前置粘贴
   	function	pasteAsPreviousNode(){
   		if(currentNode==null){
   			alert("请选择分类");
   			return;
   		}
   		else if(SourceNode==null){
   			alert("请先剪切节点再选择前置粘贴");
   			return;
   		}
   		else{
   			//获取目标节点
       		TargetNode 	=	currentNode;
   		}
   	//判断该节点是否为根节点，若是根节点则不能前置黏贴
			var sortNodeId=currentNode.data.id;
			var ifRootNode=null;
			$.ajax({
					type : "post",
					dataType : "text",
					async : false,
					url : "sortTreeOperateActionIfRootIdAction?sortNodeId="+sortNodeId,
					success : function(result) {
						if(result=="1"){
							alert("该分类节点为根节点，不能黏贴为前置节点，请重新选择分类节点");
							ifRootNode=result;
							return false;
						}
					},
					error : function() {
					}
				});
			 if(ifRootNode=="1"){
				return false;
			}
			 if(confirm("确认粘贴？")){
				 
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "treeOperateAction.action?action=pasteAsPrevious",
			data : "sourceId=" + SourceNode.data.id
					+ "&targetId=" + TargetNode.data.id  + "&table="
					+ "SYS_IB_CLASSIFICATION_TREE" + "&treeId="
					+ SourceNode.data.tree_id,
			success : function(result) {
			},
			error : function() {
			}
		});  
		alert("前向粘帖分类成功");
		   		CreateSortTree();
   		//粘贴完成后，清空源节点以及目标节点
   		SourceNode	=	null;
   		TargetNode	=	null;
			 }
			 
   	}
   	//后置粘贴
   	function	pasteAsAfterNode(){
   		if(currentNode==null){
   			alert("请选择分类");
   			return;
   		}
   		else if(SourceNode==null){
   			alert("请先剪切节点再选择后置粘贴");
   			return;
   		}
   		else{
   			//获取目标节点
       		TargetNode 	=	currentNode;
   		}
   	//判断该节点是否为根节点，若是根节点则不能后置黏贴
			var sortNodeId=currentNode.data.id;
			var ifRootNode=null;
			$.ajax({
					type : "post",
					dataType : "text",
					async : false,
					url : "sortTreeOperateActionIfRootIdAction?sortNodeId="+sortNodeId,
					success : function(result) {
						if(result=="1"){
							alert("该分类节点为根节点，不能黏贴为后置节点，请重新选择分类节点");
							ifRootNode=result;
							return false;
						}
					},
					error : function() {
					}
				});
			 if(ifRootNode=="1"){
				return false;
			}
			 if(confirm("确认粘贴？")){
				 
   		//粘贴为后置节点实现方法
  		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "treeOperateAction.action?action=pasteAsAfter",
			data : "sourceId=" + SourceNode.data.id
					+ "&targetId=" + TargetNode.data.id  + "&table="
					+ "SYS_IB_CLASSIFICATION_TREE" + "&treeId="
					+ SourceNode.data.tree_id,
			success : function(result) {
			},
			error : function() {			}
		}); 
  		alert("后向粘帖分类成功");
  		   		CreateSortTree();
   		//alert("ok");
   		//粘贴完成后，清空源节点以及目标节点
   		SourceNode	=	null;
   		TargetNode	=	null;    		
			 }
   	}
   	
   	
   	
  //粘贴为子机构
   	function	PasteAsChildNode(){
   		if(currentNode==null){
   			alert("请选择分类");
   			return;
   		}
   		else if(SourceNode==null){
   			alert("请先剪切分类在选择粘贴为子节点");
   			return;
   		}
   		else{
   			//获取目标节点
       		TargetNode 	=	currentNode;
   		}
   		if(confirm("确认粘贴?")){
   			
   			//粘贴为子节点实现方法
   			
   			$.ajax({
   				type : "post",
   				dataType : "json",
   				async : false,
   				url : "treeOperateAction.action?action=pasteAsChild",
   				data : "sourceId=" + SourceNode.data.id
   				+ "&targetId=" + TargetNode.data.id  + "&table="
   				+ "SYS_IB_CLASSIFICATION_TREE" + "&treeId="
   				+ SourceNode.data.tree_id,
   				success : function(result) {
   				},
   				error : function() {
   				}
   			});
   			alert("粘帖成功");
   			CreateSortTree();
   			//粘贴完成后，清空源节点以及目标节点
   			SourceNode = null;
   			TargetNode = null;
   		}
		}
</script>
<title>Insert title here</title>
</head>
<body class=" yui-skin-sam">
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
	<div style="margin-left: 25px; margin-top: 10px">
		<table>
			<tr>
				<td>选择分类体系：</td>
				<td><select name="select" style="font-size: 12px"
					id="SelectSort">
						<option selected="selected" value="">------请选择------</option>
				</select></td>
				<td><input type="button" value="确认" class="button"
					onclick="showDiv();"></td>
			</tr>
		</table>
		<div id="sortdiv" style="visibility: hidden">
			<div>
				<table>
					<tr>
						<td style="border: 0; height: 50px">
							<table width="100%" align="left" style="font-size: 12px">
								<tr>
									<td><a href="javascript:void(0)"
										onclick="addPreviousNode()">添加前置分类</a> <strong>|</strong> <a
										href="javascript:void(0)" onclick="addAfterNode()">添加后置分类</a><strong>|</strong>
										<a href="javascript:void(0)" onclick="modifyCurrentNode()">修改分类</a>
										<strong>|</strong> <a href="javascript:void(0)"
										onclick="deleteCurrentNode()">删除分类</a> <strong>|</strong> <a
										href="javascript:void(0)" onclick="cutCurrentNode()">剪切分类</a>
										<strong>|</strong> <a href="javascript:void(0)"
										onclick="pasteAsPreviousNode()">前置粘帖</a> <strong>|</strong> <a
										href="javascript:void(0)" onclick="pasteAsAfterNode()">后置粘帖</a>
										<strong>|</strong> <a href="javascript:void(0)"
										onclick="PasteAsChildNode()">粘帖为子分类</a><strong>|</strong> <a
										href="javascript:void(0)" onclick="addChildeNode()">添加子分类</a></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>

			<div id="container">
				<div id="panel1" style="z-index: 1999">
					<div id="PannelHeader" class="hd" style="z-index: 2000"></div>
					<div class="bd" style="z-index: 2000">
						<form name="modifyForm" id="modifyForm">
							<table>
								<tr>
									<td width="100px">分类名称：</td>
									<td><input type="text" name="NewSortText" id="NewSortText" /><span class="red" > *    </span></td>
								</tr>
								<tr>
									<td width="100px">分类描述：</td>
									<td><textarea cols="60" rows="6" name="NewSortExplain"
											id="NewSortExplain"></textarea></td>
								</tr>
							</table>
							<div align="center">
								<input id="ConfirmButton" type="button" value="确定""> <input
									type="button" value="取消" onclick="ClearFade();" />
							</div>
						</form>
					</div>
					<div class="ft"></div>
				</div>
			</div>
			<!-- 半透罩，用来锁定屏幕 -->
			<div id="fade" class="black_overlay"></div>
			<div id="sorttree"
				style="margin-left: 25px; margin-top: 10px; margin-right: 20px">
			</div>
			<script type="text/javascript">
				//初始化修改信息对话框
				YAHOO.namespace("example.container");
				function init() {
					YAHOO.example.container.panel1 = new YAHOO.widget.Panel(
							"panel1", {
								width : "600px",
								visible : false,
								constraintoviewport : true,
								close : false
							});
					YAHOO.example.container.panel1.render();
				}
				YAHOO.util.Event.addListener(window, "load", init);
			</script>


		</div>
	</div>


	<script language="JavaScript">
		
	</script>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>

	<script>
		getSort();
	</script>
</body>
</html>