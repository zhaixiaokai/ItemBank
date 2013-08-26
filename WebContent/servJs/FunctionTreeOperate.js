/*
 * Author	��	xiaokai
 * Function	:	ʵ�ֶԹ���������
 * Date		:	20130114
 * 
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
   							"SYS_FUNCTION_LIST"+"&treeId="+currentNode.data.tree_id,
   			success : function(result) {
   				alert(result.text);
   			},
   			error:function(){
   			}
   		});
		CreateFunctionTree();
   	}
   	
   	//���ǰ�ýڵ㣬������д��ӽڵ���Ϣ����
    	function	addPreviousNode(){
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
   			return;
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("���ڵ㲻�������ǰ�ýڵ�");
   			return;
   		}
   		else{
   			getAddPreviousNodeInfo();
   		}
   	} 
   	//��Ӻ��ýڵ㲢�ҵ������ýڵ���Ϣ��д�Ի���
   	function	addAfterNode(){
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
   			return;
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("���ڵ㲻������Ӻ��ýڵ�");
   			return;
   		}
   		else{
   			getAddAfterNodeInfo();
   		}
   	}
   	//ɾ���ڵ�
   	function	deleteCurrentNode(){
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("���ڵ㲻����ɾ��");
   			return;
   		}
   		else{
   			if(confirm("ȷ��Ҫɾ��������")){
   				//ɾ����ǰ�ڵ�
   				if(currentNode.data.leaf=="true"){
       				deleteNode(currentNode);
   				}
   				else{
   					if(confirm("�ò�����ɾ����Ŀ¼�����нڵ㣡")){
   						deleteNode(currentNode);
   					}
   				}
   				
   			}
   		}
   	}
   	//�޸ĵ�ǰ�ڵ�
   	function	modifyCurrentNode(){
   		//alert(currentNode);
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
   			return;
   		}
   		else{
   			getModifyNodeInfo();
   		}
   	}
   	//���нڵ�
   	function	cutCurrentNode(){
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
   			return;
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("���ڵ㲻�������");
   			return;
   		}
   		else{
   			//��ȡԴ�ڵ�
       		SourceNode	=	currentNode;
   			alert("�ڵ� "+SourceNode.data.label+" ������");
   		}
   	}
   	//ǰ��ճ��
   	function	pasteAsPreviousNode(){
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
   			return;
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("���ڵ㲻����ǰ��ճ��");
   			return;
   		}
   		else if(SourceNode==null){
   			alert("û���ϼ��ڵ�");
   			return;
   		}
   		else{
   			//��ȡĿ��ڵ�
       		TargetNode 	=	currentNode;
   		}
		$.ajax({
			type : "post",
			dataType : "text",
			async : false,
			url : "treeOperateAction.action?action=pasteAsPrevious",
			data : "sourceId=" + SourceNode.data.id
					+ "&targetId=" + TargetNode.data.id  + "&table="
					+ "SYS_FUNCTION_LIST" + "&treeId="
					+ SourceNode.data.tree_id,
			success : function(result) {
				alert(result);
			},
			error : function() {
			}
		});  
		CreateFunctionTree();
   		//ճ����ɺ����Դ�ڵ��Լ�Ŀ��ڵ�
   		SourceNode	=	null;
   		TargetNode	=	null;
   	}
   	//����ճ��
   	function	pasteAsAfterNode(){
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
   			return;
   		}
   		else if(currentNode.data.pid=="0"||currentNode.data.pid=="root"){
   			alert("���ڵ㲻�������ճ��");
   			return;
   		}
   		else if(SourceNode==null){
   			alert("û���ϼ��ڵ�");
   			return;
   		}
   		else{
   			//��ȡĿ��ڵ�
       		TargetNode 	=	currentNode;
   		}
   		//ճ��Ϊ���ýڵ�ʵ�ַ���
  		$.ajax({
			type : "post",
			dataType : "text",
			async : false,
			url : "treeOperateAction.action?action=pasteAsAfter",
			data : "sourceId=" + SourceNode.data.id
					+ "&targetId=" + TargetNode.data.id  + "&table="
					+ "SYS_FUNCTION_LIST" + "&treeId="
					+ SourceNode.data.tree_id,
			success : function(result) {
				alert(result);
			},
			error : function() {
			}
		}); 
  		CreateFunctionTree();
   		//alert("ok");
   		//ճ����ɺ����Դ�ڵ��Լ�Ŀ��ڵ�
   		SourceNode	=	null;
   		TargetNode	=	null;    		
   	}
   	//ճ��Ϊ�ӽڵ�
   	function	PasteAsChildNode(){
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
   			return;
   		}
   		else if(SourceNode==null){
   			alert("û���ϼ��ڵ�");
   			return;
   		}
   		else{
   			//��ȡĿ��ڵ�
       		TargetNode 	=	currentNode;
   		}
   		//ճ��Ϊ�ӽڵ�ʵ�ַ���

		$.ajax({
				type : "post",
				dataType : "text",
				async : false,
				url : "treeOperateAction.action?action=pasteAsChild",
				data : "sourceId=" + SourceNode.data.id
						+ "&targetId=" + TargetNode.data.id  + "&table="
						+ "SYS_FUNCTION_LIST" + "&treeId="
						+ SourceNode.data.tree_id,
				success : function(result) {
					alert(result);
				},
				error : function() {
				}
			});
		CreateFunctionTree();
		//ճ����ɺ����Դ�ڵ��Լ�Ŀ��ڵ�
		SourceNode = null;
		TargetNode = null;
		}
	//����ӽڵ�
	function addChildeNode() {
		if (currentNode == null) {
			alert("��ѡ��ڵ�");
			return;
		} else {
			//��ȡԴ�ڵ�
			getAddChildNodeInfo();
		}
	}