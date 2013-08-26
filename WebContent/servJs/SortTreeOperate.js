/*
 * author	:	HuangJu
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
   	function	deleteNode(){
   		$.ajax( {
   			type : "post",
   			dataType : "json",
   			async : false,
   			url : "treeOperateAction.action?action=delete",
   			data:"nodeid="+currentNode.data.id+"&table="+
   							"SYS_IB_CLASSIFICATION_TREE"+"&treeId="+currentNode.data.tree_id,
   			success : function(result) {
   			},
   			error:function(){
   			}
   		});
   		CreateSortTree();
   	}
   	
   	//添加前置节点，弹出填写添加节点信息窗口
    	function	addPreviousNode(){
   		if(currentNode==null){
   			alert("请选择分类");
   			return;
   		}
   		else{
   			getAddPreviousNodeInfo();
   		}
   	} 
   	//添加后置节点并且弹出后置节点信息填写对话框
   	function	addAfterNode(){
   		if(currentNode==null){
   			alert("请选择分类");
   			return;
   		}
   		else{
   			getAddAfterNodeInfo();
   		}
   	}
   	
   	//修改当前节点
   	function	modifyCurrentNode(){
   		//alert(currentNode);
   		if(currentNode==null){
   			alert("请选择分类");
   			return;
   		}
   		else{
   			getModifyNodeInfo();
   		}
   	}
   	
   	
   	
	//添加子机构
	function addChildeNode() {
		if (currentNode == null) {
			alert("请选择分类");
			return;
		} else {
			//获取源节点
			getAddChildNodeInfo();
		}
	}