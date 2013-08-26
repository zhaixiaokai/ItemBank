/*
 * author	:	xiaokai
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
   	
   	
   	//�����ݽ�����֤������ɹ�ִ���޸�
   	function	getModifyNodeInfo(){
		artDialog({
			title:'�޸Ľڵ�',
			content:'<table>'+
					//'<tr><td>Ψһ��ʶ����</td><td><input type="text" id="modifyId"/></td></tr>'+
					'<tr><td>�ڵ����ƣ�</td><td><input type="text" id="modifyText"/></td></tr>'+
					'<tr><td>˵����</td><td><input type="text" id="modifyExplain"/></td></tr>'+
					'</table>',
			yesText:'�޸�',
			noText:'ȡ��',
			lock: true
		},
        function(){
			//���ȷ��
			//��ӵ�ǰ��д�Ľڵ㣨Ψһ��ʶ����text��explain��

			if(document.getElementById("modifyText").value==""||document.getElementById("modifyText").value==null){
				alert("�޸�ʧ�ܣ�����д�ڵ����ƣ�");
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
            alert('�����ȡ��');
        }
		);
	}
   	//��֤����д��Ϣ�Ϸ��ԣ�����ִ�����
   	function	getAddPreviousNodeInfo(){
			artDialog({
				title:'��ӽڵ�',
				content:'<table>'+
						//'<tr><td>Ψһ��ʶ����</td><td><input type="text" id="id"/></td></tr>'+
						'<tr><td>�ڵ����ƣ�</td><td><input type="text" id="AddPreText"/></td></tr>'+
						'<tr><td>˵����</td><td><input type="text" id="AddPreExplain"/></td></tr>'+
						'</table>',
				yesText:'���',
				noText:'ȡ��',
				lock: true
			},
	        function(){
				//���ȷ��
				//��ӵ�ǰ��д�Ľڵ㣨Ψһ��ʶ����text��explain��
				if(document.getElementById("AddPreText").value==""||document.getElementById("AddPreText").value==null){
					alert("���ʧ�ܣ�����д�ڵ����ƣ�");
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
	            alert('�����ȡ��');
	        }
			);
   	}
   	function	getAddAfterNodeInfo(){
		artDialog({
			title:'��ӽڵ�',
			content:'<table>'+
					//'<tr><td>Ψһ��ʶ����</td><td><input type="text" id="id"/></td></tr>'+
					'<tr><td>�ڵ����ƣ�</td><td><input type="text" id="addAfterText"/></td></tr>'+
					'<tr><td>˵����</td><td><input type="text" id="addAfterExplain"/></td></tr>'+
					'</table>',
			yesText:'���',
			noText:'ȡ��',
			lock: true
		},
        function(){
			//���ȷ��
			//��ӵ�ǰ��д�Ľڵ㣨Ψһ��ʶ����text��explain��
			if(document.getElementById("addAfterText").value==""||document.getElementById("addAfterText").value==null){
				alert("���ʧ�ܣ�����д�ڵ����ƣ�");
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
            alert('�����ȡ��');
        }
		);
	}
   	function	getAddChildNodeInfo(){
		artDialog({
			title:'����ӽڵ�',
			content:'<table>'+
					//'<tr><td>Ψһ��ʶ����</td><td><input type="text" id="id"/></td></tr>'+
					'<tr><td>�ڵ����ƣ�</td><td><input type="text" id="addChildText"/></td></tr>'+
					'<tr><td>˵����</td><td><input type="text" id="addChildExplain"/></td></tr>'+
					'</table>',
			yesText:'���',
			noText:'ȡ��',
			lock: true
		},
        function(){
			//���ȷ��
			//��ӵ�ǰ��д�Ľڵ㣨Ψһ��ʶ����text��explain��
			if(document.getElementById("addChildText").value==""||document.getElementById("addChildText").value==null){
				alert("���ʧ�ܣ�����д�ڵ����ƣ�");
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
            alert('�����ȡ��');
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
   	
   	//���ǰ�ýڵ㣬������д��ӽڵ���Ϣ����
    	function	addPreviousNode(){
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
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
   		else{
   			getAddAfterNodeInfo();
   		}
   	}
   	//ɾ���ڵ�
   	function	deleteCurrentNode(){
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
   		}
   		else{
   			if(confirm("ȷ��Ҫɾ��������")){
   				//ɾ����ǰ�ڵ�
   				if(currentNode.get("leaf")==true){
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
   		else{
   			//��ȡԴ�ڵ�
       		SourceNode	=	currentNode;
   			alert("�ڵ� "+SourceNode.get("text")+" ������");
   		}
   	}
   	//ǰ��ճ��
   	function	pasteAsPreviousNode(){
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
   			return;
   		}
   		else if(SourceNode==null){
   			alert("û��Դ�ڵ�");
   			return;
   		}
   		else{
   			//��ȡĿ��ڵ�
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
   		else if(SourceNode==null){
   			alert("û��Դ�ڵ�");
   			return;
   		}
   		else{
   			//��ȡĿ��ڵ�
       		TargetNode 	=	currentNode;
   		}
   		//ճ��Ϊ���ýڵ�ʵ�ַ���
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
   		//ճ����ɺ����Դ�ڵ��Լ�Ŀ��ڵ�
   		SourceNode	=	null;
   		TargetNode	=	null;    		
   	}
   	//ճ��Ϊ�ӻ���
   	function	PasteAsChildNode(){
   		if(currentNode==null){
   			alert("��ѡ��ڵ�");
   			return;
   		}
   		else if(SourceNode==null){
   			alert("û��Դ�ڵ�");
   			return;
   		}
   		else{
   			//��ȡĿ��ڵ�
       		TargetNode 	=	currentNode;
   		}
   		//ճ��Ϊ�ӽڵ�ʵ�ַ���

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
		//ճ����ɺ����Դ�ڵ��Լ�Ŀ��ڵ�
		SourceNode = null;
		TargetNode = null;
		}
	//����ӻ���
	function addChildeNode() {
		if (currentNode == null) {
			alert("��ѡ��ڵ�");
			return;
		} else {
			//��ȡԴ�ڵ�
			getAddChildNodeInfo();
		}
	}