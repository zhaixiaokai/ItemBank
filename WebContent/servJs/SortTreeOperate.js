/*
 * author	:	HuangJu
 * date		:	20121122
 * Function	:	ʵ����ǰ̨�����Ĳ���
 * ˵��		:	����currentNode�ǵ�ǰ�����ڵ�����Ӧ�Ķ���
 * 				��ʹ��ʱ�ò��������ڵ��ĳ�ڵ�ʱ��������
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
   	
   	//���ǰ�ýڵ㣬������д��ӽڵ���Ϣ����
    	function	addPreviousNode(){
   		if(currentNode==null){
   			alert("��ѡ�����");
   			return;
   		}
   		else{
   			getAddPreviousNodeInfo();
   		}
   	} 
   	//��Ӻ��ýڵ㲢�ҵ������ýڵ���Ϣ��д�Ի���
   	function	addAfterNode(){
   		if(currentNode==null){
   			alert("��ѡ�����");
   			return;
   		}
   		else{
   			getAddAfterNodeInfo();
   		}
   	}
   	
   	//�޸ĵ�ǰ�ڵ�
   	function	modifyCurrentNode(){
   		//alert(currentNode);
   		if(currentNode==null){
   			alert("��ѡ�����");
   			return;
   		}
   		else{
   			getModifyNodeInfo();
   		}
   	}
   	
   	
   	
	//����ӻ���
	function addChildeNode() {
		if (currentNode == null) {
			alert("��ѡ�����");
			return;
		} else {
			//��ȡԴ�ڵ�
			getAddChildNodeInfo();
		}
	}