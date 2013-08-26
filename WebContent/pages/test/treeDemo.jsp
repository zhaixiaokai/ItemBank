<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Tree Example</title>
    <!-- 引入alertDialog -->
    <link href="../js/alertDialog/skin/chrome/chrome.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/alertDialog/artDialog.js"></script>
	
	
    <link rel="stylesheet" type="text/css" href="../js/ext-4.0.7-gpl/resources/css/ext-all.css" />
    <script type="text/javascript" src="../js/ext-4.0.7-gpl/bootstrap.js"></script>
    <script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.rotate.js"></script>
    <script type="text/javascript">
    	var	currentNode=null;
    	var	store	=	null;
    	var	tree=null;
    	var	SourceNode=null;
    	var	TargetNode=null;
    	function	storeReload(){
    		var	re	=	$.ajax( {
    			type : "post",
    			dataType : "json",
    			async : false,
    			url : "TestTreeJson.jsp",
/*     			data:"pid="+newPid+"&sno="+newSNO+"&node_path="+newPath+"&tree_id="+newName
    					+"&node_series="+newSeries+"&node_explain="+newExplain+"&leaf="+newLeaf+"&text="+newText+"&table="+"SYS_IB_CLASSIFICATION_TREE", */
    			success : function(result) {
    				store	=	result;
    			},
    			error:function(){
    			}
    		});
    		return	re;
    	}
    	
    	//对数据进行验证，如果成功执行修改
    	function	getModifyNodeInfo(){
			artDialog({
				title:'修改节点',
				content:'<table>'+
						//'<tr><td>唯一标识符：</td><td><input type="text" id="modifyId"/></td></tr>'+
						'<tr><td>节点名称：</td><td><input type="text" id="modifyText"/></td></tr>'+
						'<tr><td>说明：</td><td><input type="text" id="modifyExplain"/></td></tr>'+
						'</table>',
				yesText:'修改',
				noText:'取消',
				lock: true
			},
	        function(){
				//点击确认
				//添加当前填写的节点（唯一标识符、text、explain）
				//newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf
/* 				if(document.getElementById("modifyId").value==""||document.getElementById("modifyId").value==null){
					alert("修改失败：请填写唯一标识符！");
					return;
				} */
				if(document.getElementById("modifyText").value==""||document.getElementById("modifyText").value==null){
					alert("修改失败：请填写节点名称！");
					return;
				}
				else{
					//alert("sth");
					//alert(currentNode);
 					var	newId	=	currentNode.get("id");;
					var	newText	=	document.getElementById("modifyText").value;
					var	newPid	=	currentNode.get("pid");
					var	newPath	=	currentNode.get("node_path");
					var	newSNO	=	currentNode.get("sno");
					var	newName	=	currentNode.get("tree_id");
					var	newSeries	=	currentNode.get("node_series");
					var	newExplain	=	document.getElementById("modifyExplain").value;
					var	newLeaf	=	"true";
					AjaxNodeUpdate(newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf); 
            		document.location.reload();
					
				}
	        },
	        function(){
	            alert('你点了取消');
	        }
			);
	}
    	//验证所填写信息合法性，并且执行添加
    	function	getAddPreviousNodeInfo(){
    			artDialog({
    				title:'添加节点',
    				content:'<table>'+
    						//'<tr><td>唯一标识符：</td><td><input type="text" id="id"/></td></tr>'+
    						'<tr><td>节点名称：</td><td><input type="text" id="text"/></td></tr>'+
    						'<tr><td>说明：</td><td><input type="text" id="explain"/></td></tr>'+
    						'</table>',
    				yesText:'添加',
    				noText:'取消',
    				lock: true
    			},
    	        function(){
    				//点击确认
    				//添加当前填写的节点（唯一标识符、text、explain）
    				//newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf
/*     				if(document.getElementById("id").value==""||document.getElementById("id").value==null){
    					alert("添加失败：请填写唯一标识符！");
    					return;
    				} */
    				if(document.getElementById("text").value==""||document.getElementById("text").value==null){
    					alert("添加失败：请填写节点名称！");
    					return;
    				}
    				else{
    					//alert("sth");
    					//alert(currentNode.get("pid"));
    					//var	newId	=	document.getElementById("id").value;
    					var	newText	=	document.getElementById("text").value;
    					var	newPid	=	currentNode.get("pid");
    					var	newPath	=	currentNode.get("node_path");
    					var	newSNO	=	currentNode.get("sno");
    					var	newName	=	currentNode.get("tree_id");
    					var	newSeries	=	currentNode.get("node_series");
    					var	newExplain	=	document.getElementById("explain").value;
    					var	newLeaf	=	"true";
    					AjaxNodeAdd(newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf);
             			var sameLevelNodes	=	currentNode.parentNode.childNodes;
            			//alert(sameLevelNodes.length);//获取同级节点个数
            			var	currentNodeSNO	=	currentNode.get("sno");
            			//alert(num_currentNodeSNO);
                		for(var i=currentNodeSNO;i<=sameLevelNodes.length;i++){
            				//对于同级节点修改SNO以及NodePath的值
             				var	currOperateNode	=	sameLevelNodes[i-1];
             				rightMoveCurrentNode(currOperateNode);
            			}
                		document.location.reload();
    				}

    	        },
    	        function(){
    	            alert('你点了取消');
    	        }
    			);
    	}
    	function	getAddAfterNodeInfo(){
			artDialog({
				title:'添加节点',
				content:'<table>'+
						//'<tr><td>唯一标识符：</td><td><input type="text" id="id"/></td></tr>'+
						'<tr><td>节点名称：</td><td><input type="text" id="text"/></td></tr>'+
						'<tr><td>说明：</td><td><input type="text" id="explain"/></td></tr>'+
						'</table>',
				yesText:'添加',
				noText:'取消',
				lock: true
			},
	        function(){
				//点击确认
				//添加当前填写的节点（唯一标识符、text、explain）
				//newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf
/*     				if(document.getElementById("id").value==""||document.getElementById("id").value==null){
					alert("添加失败：请填写唯一标识符！");
					return;
				} */
				if(document.getElementById("text").value==""||document.getElementById("text").value==null){
					alert("添加失败：请填写节点名称！");
					return;
				}
				else{
					//var	newId	=	document.getElementById("id").value;
					var	newText	=	document.getElementById("text").value;
					var	newPid	=	currentNode.get("pid");
					var	pathBuff	=	currentNode.get("node_path");
					alert("pathBuff:	"+pathBuff);
					var	newSeries	=	currentNode.get("node_series");
					alert("newSeries:	"+newSeries);
					var	subPath	=	pathBuff.substr(5*(newSeries-1),4);
					alert("subPath:	"+subPath);
					subPath=parseInt(subPath)+1;
					alert("Num subPath:	"+subPath);
					if(subPath<10){
						subPath="000"+subPath;
					}
					else if(subPath<100){
						subPath="00"+subPath;
					}
					else if(subPath<1000){
						subPath="0"+subPath;
					}
					else if(subPath<10000){
						subPath=subPath;
					}
					//alert("new str subpath："+subPath);
					//alert(pathBuff-1);
					alert(pathBuff.substring(0,0));
					alert("part1:	"+pathBuff.substring(0,5*(newSeries-1)));
					alert("part1:	"+subPath);
					alert("part1:	"+pathBuff.substr(5*(parseInt(newSeries))-1));
					var	newPath=pathBuff.substring(0,5*(newSeries-1))+subPath+pathBuff.substr(5*(parseInt(newSeries))-1);
					alert("newPath:	"+newPath);
					var	newSNO	=	parseInt(currentNode.get("sno"))+1;
					var	newName	=	currentNode.get("tree_id");
					var	newExplain	=	document.getElementById("explain").value;
					var	newLeaf	=	"true";
					AjaxNodeAdd(newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf);
	     			var sameLevelNodes	=	currentNode.parentNode.childNodes;
	    			//alert(sameLevelNodes.length);//获取同级节点个数
	    			var	currentNodeSNO	=	currentNode.get("sno");
	    			//alert(num_currentNodeSNO);
	        		for(var i=parseInt(currentNodeSNO)+1;i<=sameLevelNodes.length;i++){
	    				//对于同级节点修改SNO以及NodePath的值
	     				var	currOperateNode	=	sameLevelNodes[i-1];
	     				rightMoveCurrentNode(currOperateNode);
	    			}
	        		document.location.reload();
				}

	        },
	        function(){
	            alert('你点了取消');
	        }
			);
		}
    	function	getAddChildNodeInfo(){
			artDialog({
				title:'添加子节点',
				content:'<table>'+
						//'<tr><td>唯一标识符：</td><td><input type="text" id="id"/></td></tr>'+
						'<tr><td>节点名称：</td><td><input type="text" id="text"/></td></tr>'+
						'<tr><td>说明：</td><td><input type="text" id="explain"/></td></tr>'+
						'</table>',
				yesText:'添加',
				noText:'取消',
				lock: true
			},
	        function(){
				//点击确认
				//添加当前填写的节点（唯一标识符、text、explain）
				//newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf
/*     				if(document.getElementById("id").value==""||document.getElementById("id").value==null){
					alert("添加失败：请填写唯一标识符！");
					return;
				} */
				if(document.getElementById("text").value==""||document.getElementById("text").value==null){
					alert("添加失败：请填写节点名称！");
					return;
				}
				else{
					var	newText	=	document.getElementById("text").value;
					var	newPid	=	currentNode.get("id");
					var	pathTemp=	currentNode.get("node_path");
					var	children=	currentNode.childNodes;//该节点当前子节点
					var	newSNO	=	parseInt(children.length)+1;
					var	subPath	=	newSNO;
					if(subPath<10){
						subPath="000"+subPath;
					}
					else if(subPath<100){
						subPath="00"+subPath;
					}
					else if(subPath<1000){
						subPath="0"+subPath;
					}
					else if(subPath<10000){
						subPath=subPath;
					}
					var	newPath	=	pathTemp+"."+subPath;
					alert(newPath);
					var	newName	=	currentNode.get("tree_id");
					var	newSeries	=	parseInt(currentNode.get("node_series"))+1;
					var	newExplain	=	document.getElementById("explain").value;
					var	newLeaf	=	"true";
					AjaxNodeAdd(newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf);
				}
				alert("是否是叶子节点"+currentNode.get("leaf"));
				if(currentNode.get("leaf")==true){
					var	newId	=	currentNode.get("id");
					var	newText	=	currentNode.get("text");
					var	newPid	=	currentNode.get("pid");
					var	newPath	=	currentNode.get("node_path");
					var	newSNO	=	currentNode.get("sno");
					var	newName	=	currentNode.get("tree_id");
					var	newSeries	=	currentNode.get("node_series");
					var	newExplain	=	document.getElementById("explain").value;
					var	newLeaf	=	"false";
					AjaxNodeUpdate(newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf);
				}
	    		document.location.reload();
	        },
	        function(){
	            alert('你点了取消');
	        }
			);
		}	
    	function	deleteNode(node){
    		if(node.get("leaf")==true){				//如果删除的节点是叶子节点
        		var	delId	=	node.get("id");
        		AjaxNodeDelete(delId);
    		}
    		else{
    			var	children	=	node.childNodes;
    			var	delId	=	node.get("id");
    			AjaxNodeDelete(delId);
    			for(var	i=0;i<children.length;i++){
    				deleteNode(children[i]);
    			}
    		}
    		
    	}
    	function	pasteNodeUpdate(node,path,series){
    			//更新当前节点
    		alert("pasteNodeUpdate("+node.get("text")+","+path+","+series+")");
	   			var	newId=node.get("id");
   	   			var	newName=	node.get("tree_id");
   	   			var	newText	=	node.get("text");
   	   			var	newPid	=	node.get("pid");
   	   			var	newSNO	=	node.get("sno");
   	   			var	subPath	=	parseInt(newSNO);
   				if(subPath<10){
   					subPath="000"+subPath;
   				}
   				else if(subPath<100){
   					subPath="00"+subPath;
   				}
   				else if(subPath<1000){
   					subPath="0"+subPath;
   				}
   				else if(subPath<10000){
   					subPath=subPath;
   				}
   	   			var	newPath	=	path+"."+subPath;
   	   			alert("newPath:"+newPath);
   	   			var	newSeries	=	parseInt(series)+1;
   	   			alert("newSeries:"+newSeries);
   	   			var	newExplain	=	node.get("node_explain");
   	   			var	newLeaf	=	node.get("leaf");
   	   			AjaxNodeUpdate(newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf);
   			if(node.get("leaf")==true){

   	   			//alert(node.get("leaf")); 				
   			}
   			else if(node.get("leaf")==false){
   				var	children	=	node.childNodes;
    			for(var i=0;i<children.length;i++){
    				pasteNodeUpdate(children[i],newPath,newSeries);
    			}
    		}
    	}
    	//节点信息的修改
    	function	AjaxNodeAdd(newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf){
    		$.ajax( {
    			type : "post",
    			dataType : "json",
    			async : false,
    			url : "updateNode.jsp?action=insert",
    			data:"pid="+newPid+"&sno="+newSNO+"&node_path="+newPath+"&tree_id="+newName
    					+"&node_series="+newSeries+"&node_explain="+newExplain+"&leaf="+newLeaf+"&text="+newText+"&table="+"SYS_IB_CLASSIFICATION_TREE",
    			success : function(result) {
    			},
    			error:function(){
    			}
    		});
    	}
    	//节点信息更新
    	function	AjaxNodeUpdate(newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf){
    		$.ajax( {
    			type : "post",
    			dataType : "json",
    			async : false,
    			url : "updateNode.jsp?action=update",
    			data:"id="+newId+"&pid="+newPid+"&sno="+newSNO+"&node_path="+newPath+"&tree_id="+newName
    					+"&node_series="+newSeries+"&node_explain="+newExplain+"&leaf="+newLeaf+"&text="+newText+"&table="+"SYS_IB_CLASSIFICATION_TREE",
    			success : function(result) {
    			},
    			error:function(){
    			}
    		});
    	}
    	function	AjaxNodeDelete(newId){
    		$.ajax( {
    			type : "post",
    			dataType : "json",
    			async : false,
    			url : "updateNode.jsp?action=delete",
    			data:"id="+newId+"&table="+"SYS_IB_CLASSIFICATION_TREE",
    			success : function(result) {
    			},
    			error:function(){
    			}
    		});
    	}
    	//添加前置节点，弹出填写添加节点信息窗口
     	function	addPreviousNode(){
    		if(currentNode==null){
    			alert("请选择节点");
    			return;
    		}
    		else{
    			getAddPreviousNodeInfo();
    		}
    	} 
    	//添加后置节点并且弹出后置节点信息填写对话框
    	function	addAfterNode(){
    		if(currentNode==null){
    			alert("请选择节点");
    			return;
    		}
    		else{
    			getAddAfterNodeInfo();
    		}
    	}
    	//删除节点
    	function	deleteCurrentNode(){
    		if(currentNode==null){
    			alert("请选择节点");
    		}
    		else{
    			if(confirm("确定要删除数据吗？")){
    				//删除当前节点
    				if(currentNode.get("leaf")==true){
        				deleteNode(currentNode);
    				}
    				else{
    					if(confirm("当前节点不是最后以及节点，是否要删除其目录下所有节点？")){
    						deleteNode(currentNode);
    					}
    				}
    				//对当前节点后的节点进行操作
         			var sameLevelNodes	=	currentNode.parentNode.childNodes;
        			//alert(sameLevelNodes.length);//获取同级节点个数
        			var	currentNodeSNO	=	currentNode.get("sno");
        			//alert(num_currentNodeSNO);
            		for(var i=parseInt(currentNodeSNO)+1;i<=sameLevelNodes.length;i++){
        				//对于同级节点修改SNO以及NodePath的值
         				var	currOperateNode	=	sameLevelNodes[i-1];
         				leftMoveCurrentNode(currOperateNode);
        			}

            		document.location.reload();
    			}
    		}
    	}
    	//修改当前节点
    	function	modifyCurrentNode(){
    		//alert(currentNode);
    		if(currentNode==null){
    			alert("请选择节点");
    			return;
    		}
    		else{
    			getModifyNodeInfo();
    		}
    	}
    	//剪切节点
    	function	cutCurrentNode(){
    		if(currentNode==null){
    			alert("请选择节点");
    			return;
    		}
    		else{
    			//获取源节点
        		SourceNode	=	currentNode;
    			alert("节点 "+SourceNode.get("text")+" 被剪切");
    		}
    	}
    	//前置粘贴
    	function	pasteAsPreviousNode(){
    		if(currentNode==null){
    			alert("请选择节点");
    			return;
    		}
    		else if(SourceNode==null){
    			alert("没有源节点");
    			return;
    		}
    		else{
    			//获取目标节点
        		TargetNode 	=	currentNode;
    		}
    		//粘贴为前置节点实现方法
    		
			var	tempPid	=	TargetNode.get("pid");
			var	tempSeries	=	TargetNode.get("node_series");
			
			var	tempPath	=	TargetNode.get("node_path");
			var	tempSNO		=	TargetNode.get("sno");
			
			var	newId	=	SourceNode.get("id");;
			var	newText	=	SourceNode.get("text");
			var	newName	=	SourceNode.get("tree_id");
			var	newExplain	=	SourceNode.get("node_explain");
			var	newLeaf	=	SourceNode.get("leaf");
			//alert("function");
			if(SourceNode.get("leaf")==true){
				//移动当前节点
				alert("这是一个叶子节点");
				AjaxNodeUpdate(newId,newText,tempPid,tempSNO,tempPath,newName,tempSeries,newExplain,newLeaf);
				//右移
     			var sameLevelNodes	=	TargetNode.parentNode.childNodes;
    			//alert(sameLevelNodes.length);//获取同级节点个数
    			var	TargetNodeSNO	=	TargetNode.get("sno");
    			//alert(num_currentNodeSNO);
        		for(var i=parseInt(TargetNodeSNO);i<=sameLevelNodes.length;i++){
    				//对于同级节点修改SNO以及NodePath的值
     				var	currOperateNode	=	sameLevelNodes[i-1];
     				rightMoveCurrentNode(currOperateNode);
    			}
     			var SourceSameLevelNodes	=	SourceNode.parentNode.childNodes;
    			//alert(SourceSameLevelNodes.length);//获取同级节点个数
    			var	SourceNodeSNO	=	SourceNode.get("sno");
    			//alert(num_SourceNodeSNO);
        		for(var i=parseInt(SourceNodeSNO)+1;i<=SourceSameLevelNodes.length;i++){
    				//对于同级节点修改SNO以及NodePath的值
     				var	currOperateNode	=	SourceSameLevelNodes[i-1];
     				leftMoveCurrentNode(currOperateNode);
    			} 
			}
			else{
				if(confirm("当前移动的节点不是叶节点，确认移动其子节点？")){
					//移动当前节点以及子节点	
					alert("这是一个父节点");
					AjaxNodeUpdate(newId,newText,tempPid,tempSNO,tempPath,newName,tempSeries,newExplain,newLeaf);
					var children	=	SourceNode.childNodes;
					for(var	i=0;i<children.length;i++){
						pasteNodeUpdate(children[i],tempPath,tempSeries);
					}
            			var TargetSameLevelNodes	=	TargetNode.parentNode.childNodes;
        			//alert(TargetSameLevelNodes.length);//获取同级节点个数
        			var	TargetNodeSNO	=	TargetNode.get("sno");
        			//alert(num_currentNodeSNO);
            		for(var i=TargetNodeSNO;i<=TargetSameLevelNodes.length;i++){
        				//对于同级节点修改SNO以及NodePath的值
         				var	currOperateNode	=	TargetSameLevelNodes[i-1];
         				rightMoveCurrentNode(currOperateNode);
        			}
            		
         			var SourceSameLevelNodes	=	SourceNode.parentNode.childNodes;
        			//alert(SourceSameLevelNodes.length);//获取同级节点个数
        			var	SourceNodeSNO	=	SourceNode.get("sno");
        			//alert(num_currentNodeSNO);
            		for(var i=parseInt(SourceNodeSNO)+1;i<=SourceSameLevelNodes.length;i++){
        				//对于同级节点修改SNO以及NodePath的值
         				var	currOperateNode	=	SourceSameLevelNodes[i-1];
         				leftMoveCurrentNode(currOperateNode);
        			}
				}
			}
			//pasteNodeUpdate(SourceNode,tempPath,tempSeries,tempSNO,tempPid);
    		
    		
    		//粘贴完成后，清空源节点以及目标节点
    		SourceNode	=	null;
    		TargetNode	=	null;
    	}
    	//后置粘贴
    	function	pasteAsAfterNode(){
    		if(currentNode==null){
    			alert("请选择节点");
    			return;
    		}
    		else if(SourceNode==null){
    			alert("没有源节点");
    			return;
    		}
    		else{
    			//获取目标节点
        		TargetNode 	=	currentNode;
    		}
    		//粘贴为后置节点实现方法
    		
    		
    		//粘贴完成后，清空源节点以及目标节点
    		SourceNode	=	null;
    		TargetNode	=	null;    		
    	}
    	//粘贴为子机构
    	function	PasteAsChildNode(){
    		if(currentNode==null){
    			alert("请选择节点");
    			return;
    		}
    		else if(SourceNode==null){
    			alert("没有源节点");
    			return;
    		}
    		else{
    			//获取目标节点
        		TargetNode 	=	currentNode;
    		}
    		//粘贴为子节点实现方法
    		
    		
    		//粘贴完成后，清空源节点以及目标节点
    		SourceNode	=	null;
    		TargetNode	=	null;       		
    	}
    	//添加子机构
    	function 	addChildeNode(){
    		if(currentNode==null){
    			alert("请选择节点");
    			return;
    		}
    		else{
    			//获取源节点
        		getAddChildNodeInfo();
    		}
    	}
/*     	function	rightMoveNode(node){
    		var	SNOBuf=currOperateNode.get("sno");
    		var	pathBuf	=	currOperateNode.get("node_path");
    		
    	} */
    	//向后移动本级节点以及子节点
    	function	rightMoveCurrentNode(node){
    		var	currentNodeLevel	=	node.get("node_series");
    		//alert("当前节点级数为："+currentNodeLevel);
    		rightMoveCurrentNodeAndChildNode(node,currentNodeLevel);
    	}
    	function	leftMoveCurrentNode(node){
    		var	currentNodeLevel	=	node.get("node_series");
    		//alert("当前节点级数为："+currentNodeLevel);
    		leftMoveCurrentNodeAndChildNode(node,currentNodeLevel);
    	}
    	//递归向后移动本级节点以及子节点			currentNodeLevel为增加节点的层次
    	//0001.0001				0001.0002
    	//0001.0001.0001		0001.0002.0001
    	//0001.0001.0002		0001.0002.0002
    	function	rightMoveCurrentNodeAndChildNode(node,currentNodeLevel){
    		//alert("当前操作节点："+node.get("text"));
			var	nodePath	=	node.get("node_path");
			//alert("当前操作节点路径为："+nodePath);
			var	subPath	=	nodePath.substr(5*(currentNodeLevel-1),4);
			//alert("subPath:"+subPath);
			subPath=parseInt(subPath)+1;
			//alert("new int subpath："+subPath);
			if(subPath<10){
				subPath="000"+subPath;
			}
			else if(subPath<100){
				subPath="00"+subPath;
			}
			else if(subPath<1000){
				subPath="0"+subPath;
			}
			else if(subPath<10000){
				subPath=subPath;
			}
			//alert("new str subpath："+subPath);
			nodePath=nodePath.substring(0,5*(currentNodeLevel-1))+subPath+nodePath.substr(5*(parseInt(currentNodeLevel))-1);
			//alert("part1:	"+nodePath.substring(5*(currentNodeLevel-2),5*(currentNodeLevel-1)));
			//alert("part2:	"+subPath);
			//alert("nodePath.substr("+5*(currentNodeLevel)+"):"+nodePath.substr(5*(currentNodeLevel)));
			//alert("new nodePath"+nodePath);
			//获取当前节点等级，如果等级等于currentNodeLevel，则当前级数节点的序号自增
 			var	currentLevel=node.get("node_series");

			var	newSNO	=	node.get("sno");
			if(currentLevel==currentNodeLevel){
				//alert("equals!!!		currentNodeLevel:	"+currentNodeLevel);
				currentLevel=parseInt(currentLevel)+1;
				//alert(currentLevel);
				newSNO	=	parseInt(newSNO)+1;
				//alert("newSNO:	"+newSNO);
			} 
			var	newId	=	node.get("id");
			var	newText	=	node.get("text");
			var	newPid	=	node.get("pid");
			var	newPath	=	nodePath;
			var	newName	=	node.get("tree_id");
			var	newSeries	=	node.get("node_series");
			var	newExplain	=	node.get("node_explain");
			var	newLeaf	=	node.get("leaf");
			AjaxNodeUpdate(newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf);
    		if(node.get("leaf")!=true){
    			var	children	=	node.childNodes;
    			
    			for(var	i=0;i<children.length;i++){
    				rightMoveCurrentNodeAndChildNode(children[i],currentNodeLevel);
    			}
    		}
    	}
    	function	leftMoveCurrentNodeAndChildNode(node,currentNodeLevel){
    		//alert("当前操作节点："+node.get("text"));
			var	nodePath	=	node.get("node_path");
			//alert("当前操作节点路径为："+nodePath);
			var	subPath	=	nodePath.substr(5*(currentNodeLevel-1),4);
			//alert("subPath:"+subPath);
			subPath=parseInt(subPath)-1;
			//alert("new int subpath："+subPath);
			if(subPath<10){
				subPath="000"+subPath;
			}
			else if(subPath<100){
				subPath="00"+subPath;
			}
			else if(subPath<1000){
				subPath="0"+subPath;
			}
			else if(subPath<10000){
				subPath=subPath;
			}
			//alert("new str subpath："+subPath);
			nodePath=nodePath.substring(0,5*(currentNodeLevel-1))+subPath+nodePath.substr(5*(parseInt(currentNodeLevel))-1);
			//alert("part1:	"+nodePath.substring(5*(currentNodeLevel-2),5*(currentNodeLevel-1)));
			//alert("part2:	"+subPath);
			//alert("nodePath.substr("+5*(currentNodeLevel)+"):"+nodePath.substr(5*(currentNodeLevel)));
			//alert("new nodePath"+nodePath);
			//获取当前节点等级，如果等级等于currentNodeLevel，则当前级数节点的序号自增
 			var	currentLevel=node.get("node_series");

			var	newSNO	=	node.get("sno");
			if(currentLevel==currentNodeLevel){
				//alert("equals!!!		currentNodeLevel:	"+currentNodeLevel);
				currentLevel=parseInt(currentLevel)+1;
				//alert("currentLevel:	"+currentLevel);
				newSNO	=	parseInt(newSNO)-1;
				//alert("newSNO:	"+newSNO);
			} 
			var	newId	=	node.get("id");
			var	newText	=	node.get("text");
			var	newPid	=	node.get("pid");
			var	newPath	=	nodePath;
			var	newName	=	node.get("tree_id");
			var	newSeries	=	node.get("node_series");
			var	newExplain	=	node.get("node_explain");
			var	newLeaf	=	node.get("leaf");
			AjaxNodeUpdate(newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf);
    		if(node.get("leaf")!=true){
    			var	children	=	node.childNodes;
    			
    			for(var	i=0;i<children.length;i++){
    				leftMoveCurrentNodeAndChildNode(children[i],currentNodeLevel);
    			}
    		}
    	}
    	

    </script>
</head>
<body>
	<div id	="te1" style="width: 300px;height:500px;">
		<a href="javascript:void(0)" onclick="addPreviousNode()">添加前置机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="addAfterNode()">添加后置机构</a><strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="modifyCurrentNode()">修改机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="deleteCurrentNode()">删除机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="cutCurrentNode()">剪切机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)">前置粘帖</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)">后置粘帖</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)">粘帖为子机构</a><strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="addChildeNode()">添加子机构</a><strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="pasteAsPreviousNode()">测试</a> 
	
	</div>
	<div id	="te2" style="width: 300px;height:500px;background-color: blue;position: absolute;left: 100px;top: 200px">
	
	
	</div>
	
	
	<script type="text/javascript">
	
  	Ext.regModel('demo', {
        extend: 'Ext.data.Model',
        fields: ['text' ,'id' ,'pid','sno','node_path','tree_id','node_series','node_explain' ]//这里不知道为什么不能添加leaf
    }); 
	//下面是通过ajax去后台获取数据
	store = Ext.create('Ext.data.TreeStore', {
		model: 'demo',
		proxy : {
			type:"ajax",
			//url : "../Json/TestTree.json"
			url : "TestTreeJson.jsp"
			//url : "../Json/test.json"
			//url : "../../test/test.jsp"
		},
		root : {
			expanded : true,
			text : "根"
		}
	});
	// 生成树
	tree	=	Ext.create('Ext.tree.Panel', {
		width : 400,
		height : 700,
		store : store,
		autoScroll : true,
		rootVisible : true,
		renderTo : 'te2'
	});
 	tree.on({
        //目录树单击事件
        'itemclick' : function(view, rcd, item, idx, event, eOpts) {
        	//alert("id:"+rcd.get("id")+"\n"+"pid:"+rcd.get("pid")+"\n"+"leaf:"+rcd.get("leaf")+"\n"+"sno:"+rcd.get("sno")+"\n"+"text:"+rcd.get("text")+"\n"+"node_path:"+rcd.get("node_path")+"\n"+"tree_id:"+rcd.get("tree_id")+"\n"+"node_series:"+rcd.get("node_series")+"\n"+"node_explain:"+rcd.get("node_explain"));
        	currentNode=rcd;
        	//alert("clic");
        },  
        //双击事件
        'itemdblclick' : function(view, rcd, item, idx, event, eOpts) {
            ///var dirid = rcd.get('id'); //节点id
           // var dirtype = rcd.raw.dirtype; //自定义数据
    	},
    	//右击事件
        'itemcontextmenu' : function(view, rcd, item, idx, event, eOpts) {
        	alert("rightClicked");
            //event.preventDefault();
            //this.showTreeItemMenu(rcd, event); //自定义处理函数
        },
        scope : this
    });
	</script>

   
</body>
</html>
