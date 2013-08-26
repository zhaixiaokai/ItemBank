/*
 * author	:	xiaokai
 * date		:	20121122
 * Function	:	实现在前台对树的操作
 * 说明		:	参数currentNode是当前操作节点所对应的对象，
 * 				在使用时该参数必须在点击某节点时进行设置
*/
   	var	currentNode=null;   	
   	var	store	=	null;
   	var	tree=null;
   	var	SourceNode=null;
   	var	TargetNode=null;
   	
   	
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

			if(document.getElementById("modifyText").value==""||document.getElementById("modifyText").value==null){
				alert("修改失败：请填写节点名称！");
				return;
			}
			else{
				var	newText		=	document.getElementById("modifyText").value;
				var	newExplain	=	document.getElementById("modifyExplain").value;
				//alert(newExplain);
	    		$.ajax( {
	    			type : "post",
	    			dataType : "json",
	    			async : false,
	    			url : "treeOperateAction.action?action=modify",
	    			data:"nodeid="+currentNode.get("id")+"&node_explain="+newExplain+"&text="+newText+"&table="+"SYS_IB_CLASSIFICATION_TREE"+"&treeId="+currentNode.get("tree_id"),
	    			success : function(result) {
	    			},
	    			error:function(){
	    			}
	    		});
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
						'<tr><td>节点名称：</td><td><input type="text" id="AddPreText"/></td></tr>'+
						'<tr><td>说明：</td><td><input type="text" id="AddPreExplain"/></td></tr>'+
						'</table>',
				yesText:'添加',
				noText:'取消',
				lock: true
			},
	        function(){
				//点击确认
				//添加当前填写的节点（唯一标识符、text、explain）
				if(document.getElementById("AddPreText").value==""||document.getElementById("AddPreText").value==null){
					alert("添加失败：请填写节点名称！");
					return;
				}
				else{
					var	newText		=	document.getElementById("AddPreText").value;
					var	newExplain	=	document.getElementById("AddPreExplain").value;
					//alert(newExplain);
		    		$.ajax( {
		    			type : "post",
		    			dataType : "text",
		    			async : false,
		    			url : "treeOperateAction.action?action=addPre",
		    			data:"nodeid="+currentNode.get("id")+"&node_explain="+newExplain+"&text="+newText+"&table="+"SYS_IB_CLASSIFICATION_TREE"+"&treeId="+currentNode.get("tree_id"),
		    			success : function(result) {
		    				document.location.reload();
		    			},
		    			error:function(){
		    			}
		    		});
		    		//document.location.reload();
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
					'<tr><td>节点名称：</td><td><input type="text" id="addAfterText"/></td></tr>'+
					'<tr><td>说明：</td><td><input type="text" id="addAfterExplain"/></td></tr>'+
					'</table>',
			yesText:'添加',
			noText:'取消',
			lock: true
		},
        function(){
			//点击确认
			//添加当前填写的节点（唯一标识符、text、explain）
			if(document.getElementById("addAfterText").value==""||document.getElementById("addAfterText").value==null){
				alert("添加失败：请填写节点名称！");
				return;
			}
			else{
				var	newText		=	document.getElementById("addAfterText").value;
				var	newExplain	=	document.getElementById("addAfterExplain").value;
				//alert(newExplain);
	    		$.ajax( {
	    			type : "post",
	    			dataType : "json",
	    			async : false,
	    			url : "treeOperateAction.action?action=addAfter",
	    			data:"nodeid="+currentNode.get("id")+"&node_explain="+newExplain+"&text="+newText+"&table="+"SYS_IB_CLASSIFICATION_TREE"+"&treeId="+currentNode.get("tree_id"),
	    			success : function(result) {
	    			},
	    			error:function(){
	    			}
	    		});
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
					'<tr><td>节点名称：</td><td><input type="text" id="addChildText"/></td></tr>'+
					'<tr><td>说明：</td><td><input type="text" id="addChildExplain"/></td></tr>'+
					'</table>',
			yesText:'添加',
			noText:'取消',
			lock: true
		},
        function(){
			//点击确认
			//添加当前填写的节点（唯一标识符、text、explain）
			if(document.getElementById("addChildText").value==""||document.getElementById("addChildText").value==null){
				alert("添加失败：请填写节点名称！");
				return;
			}
			else{
				var	newText		=	document.getElementById("addChildText").value;
				var	newExplain	=	document.getElementById("addChildExplain").value;
				//alert(newExplain);
	    		$.ajax( {
	    			type : "post",
	    			dataType : "json",
	    			async : false,
	    			url : "treeOperateAction.action?action=addChild",
	    			data:"nodeid="+currentNode.get("id")+"&node_explain="+newExplain+"&text="+newText+"&table="+"SYS_IB_CLASSIFICATION_TREE"+"&treeId="+currentNode.get("tree_id"),
	    			success : function(result) {
	    			},
	    			error:function(){
	    			}
	    		});
	    		document.location.reload();
			}

        },
        function(){
            alert('你点了取消');
        }
		);
	}
   	function	deleteNode(){
   		$.ajax( {
   			type : "post",
   			dataType : "json",
   			async : false,
   			url : "treeOperateAction.action?action=delete",
   			data:"nodeid="+currentNode.get("id")+"&table="+
   							"SYS_IB_CLASSIFICATION_TREE"+"&treeId="+currentNode.get("tree_id"),
   			success : function(result) {
   			},
   			error:function(){
   			}
   		});
   		document.location.reload();
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
   					if(confirm("该操作将删除其目录下所有节点！")){
   						deleteNode(currentNode);
   					}
   				}
   				
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
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "treeOperateAction.action?action=pasteAsPrevious",
			data : "sourceId=" + SourceNode.get("id")
					+ "&targetId=" + TargetNode.get("id")  + "&table="
					+ "SYS_IB_CLASSIFICATION_TREE" + "&treeId="
					+ SourceNode.get("tree_id"),
			success : function(result) {
			},
			error : function() {
			}
		});  
		document.location.reload();
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
  		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "treeOperateAction.action?action=pasteAsAfter",
			data : "sourceId=" + SourceNode.get("id")
					+ "&targetId=" + TargetNode.get("id")  + "&table="
					+ "SYS_IB_CLASSIFICATION_TREE" + "&treeId="
					+ SourceNode.get("tree_id"),
			success : function(result) {
			},
			error : function() {
			}
		}); 
  		document.location.reload();
   		//alert("ok");
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

		$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "treeOperateAction.action?action=pasteAsChild",
				data : "sourceId=" + SourceNode.get("id")
						+ "&targetId=" + TargetNode.get("id")  + "&table="
						+ "SYS_IB_CLASSIFICATION_TREE" + "&treeId="
						+ SourceNode.get("tree_id"),
				success : function(result) {
				},
				error : function() {
				}
			});
		document.location.reload();
		//粘贴完成后，清空源节点以及目标节点
		SourceNode = null;
		TargetNode = null;
		}
	//添加子机构
	function addChildeNode() {
		if (currentNode == null) {
			alert("请选择节点");
			return;
		} else {
			//获取源节点
			getAddChildNodeInfo();
		}
	}