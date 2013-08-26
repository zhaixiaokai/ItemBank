/*
 * author	:	wuzexi
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
   							"SYS_PROGRESS_TEACHING"+"&treeId="+currentNode.data.tree_id,
   			success : function(result) {
   			},
   			error:function(){
   			}
   		});
		CreateTeacherDepartmentTree();
   	}
   	
   	//添加前置节点，弹出填写添加节点信息窗口
    	function	addPreviousNode(){
   		if(currentNode==null){
   			alert("请选择进度节点");
   			return;
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("根节点不允许添加前置节点");
   			return;
   		}
   		else{
   			getAddPreviousNodeInfo();
   		}
   	} 
   	//添加后置节点并且弹出后置节点信息填写对话框
   	function	addAfterNode(){
   		if(currentNode==null){
   			alert("请选择进度节点");
   			return;
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("根节点不允许添加后置节点");
   			return;
   		}
   		else{
   			getAddAfterNodeInfo();
   		}
   	}
   	//删除节点
   	function	deleteCurrentNode(){
   		if(currentNode==null){
   			alert("请选择进度节点");
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("根节点不允许删除");
   			return;
   		}
   		else{
   			if(confirm("确定要删除数据吗？")){
   				//删除当前节点
   				if(currentNode.data.leaf=="true"){
       				deleteNode(currentNode);
   				}
   				else{
   					if(confirm("该操作将删除其目录下所有进度节点！")){
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
   			alert("请选择进度节点");
   			return;
   		}
   		else{
   			getModifyNodeInfo();
   		}
   	}
   	//剪切节点
   	function	cutCurrentNode(){
   		if(currentNode==null){
   			alert("请选择进度节点");
   			return;
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("不容许剪切根节点");
   			return;
   		}
   		else{
   			//获取源节点
       		SourceNode	=	currentNode;
   			alert("进度节点 "+SourceNode.data.label+" 被剪切");
   		}
   	}
   	//前置粘贴
   	function	pasteAsPreviousNode(){
   		if(currentNode==null){
   			alert("请选择进度节点");
   			return;
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("根节点不允许前置粘贴");
   			return;
   		}
   		else if(SourceNode==null){
   			alert("没有上级进度节点");
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
			data : "sourceId=" + SourceNode.data.id
					+ "&targetId=" + TargetNode.data.id  + "&table="
					+ "SYS_PROGRESS_TEACHING" + "&treeId="
					+ SourceNode.data.tree_id,
			success : function(result) {
			},
			error : function() {
			}
		});  
		CreateTeacherDepartmentTree();
   		//粘贴完成后，清空源节点以及目标节点
   		SourceNode	=	null;
   		TargetNode	=	null;
   	}
   	//后置粘贴
   	function	pasteAsAfterNode(){
   		if(currentNode==null){
   			alert("请选择进度节点");
   			return;
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("根节点不允许后置粘贴");
   			return;
   		}
   		else if(SourceNode==null){
   			alert("没有上级进度节点");
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
			data : "sourceId=" + SourceNode.data.id
					+ "&targetId=" + TargetNode.data.id  + "&table="
					+ "SYS_PROGRESS_TEACHING" + "&treeId="
					+ SourceNode.data.tree_id,
			success : function(result) {
			},
			error : function() {
			}
		}); 
  		CreateTeacherDepartmentTree();
   		//alert("ok");
   		//粘贴完成后，清空源节点以及目标节点
   		SourceNode	=	null;
   		TargetNode	=	null;    		
   	}
   	//粘贴为子进度节点
   	function	PasteAsChildNode(){
   		if(currentNode==null){
   			alert("请选择进度节点");
   			return;
   		}
   		else if(SourceNode==null){
   			alert("没有上级进度节点");
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
				data : "sourceId=" + SourceNode.data.id
						+ "&targetId=" + TargetNode.data.id  + "&table="
						+ "SYS_PROGRESS_TEACHING" + "&treeId="
						+ SourceNode.data.tree_id,
				success : function(result) {
				},
				error : function() {
				}
			});
		CreateTeacherDepartmentTree();
		//粘贴完成后，清空源节点以及目标节点
		SourceNode = null;
		TargetNode = null;
		}
	//添加子进度节点
	function addChildeNode() {
		if (currentNode == null) {
			alert("请选择进度节点");
			return;
		} else {
			//获取源节点
			getAddChildNodeInfo();
		}
	}