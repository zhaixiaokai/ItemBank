    Ext.define('Model', {
        extend: 'Ext.data.Model',
        idProperty: 'Model',
        fields: ['classification_id','classification_name','if_default_classification','classification_explain','statue']   
    });
    var sm = Ext.create('Ext.selection.CheckboxModel');
	var store = Ext.create('Ext.data.Store', {
 		model: 'Model',
 		pageSize:'10',
		proxy : {
			type:'ajax',							//��ȡ��ʽajax
			//url : "../Json/testGrid.json",		//��ȡjson��ַ���ɻ�Ϊ***.jsp
			url : "sortSystemManage_s.jsp",
			reader : {
				type : 'json',						//�����Ķ���json
				totalProperty : 'recordCount',		//����������ʶ
				root : 'data'						//�������ݱ�ʶ
			},
			actionMethods : {
				read : 'post'
			}
		}
		
	});
	var delCol = {
			header : 'ɾ��',
			width : 85,
			xtype : 'actioncolumn',
 			items : [{
				icon : 'modify.png',
				handler : function(view, rowIndex, coIIndex, item, e) {
					if(confirm("ȷ��Ҫɾ��������")){
						var	id	=view.getStore().getAt(rowIndex).get("classification_id");
						//ͨ��ajax���̨������
						var requestConfig = {
								url :'SortDeleteAction',				//����ķ�������ַ
								params : {id:id},								//�������
								callback : function(options,success,response){//�ص�����
									var msg = ["����������ֵ��",response.responseText];
									var	str	=	response.responseText;
									alert(str);
									var grid=Ext.getCmp("sortgrid");  //��ȡgrid id
									grid.getStore().load({      //ҳ��ˢ��
										params : {
											start : 0
										}
									});

								}
							}
							Ext.Ajax.request(requestConfig);					//��������
					}
				} 
			}] 
		};	
	
	var editCol = {
			header : '�༭',
			width : 85,
			xtype : 'actioncolumn',
 			items : [{
				icon : 'modify.png',
				handler : function(view, rowIndex, coIIndex, item, e) {
					var rec = view.getStore().getAt(rowIndex);
					//ͨ��ajax���̨������
					var requestConfig = {
							url :'../../servJsp/esM/sortSysEdit.jsp',				//����ķ�������ַ
							params : {id:rec.get("classification_id")}				//�������
						}
						Ext.Ajax.request(requestConfig);					//��������
					
					
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
				} 
			}] 

		};	
	
	var grid=Ext.create('Ext.grid.Panel',{
		id:'sortgrid',
		title:'������ϵ',							//������
		renderTo:'act_content2',							//�����Ⱦ��idΪd��Ԫ��
		bodyStyle:'width:500',					//�����
		autoWidth:true,				
		store:store,	 						//��������
	    layout : 'fit',							
		columnLines : true,						//����ָ���
		frame:false,							//���
		selModel: sm,
		tbar: [{								//����ɾ������
            text: '����ɾ��',
            handler : function(){
            	var record = grid.getSelectionModel().getSelection();//��ȡѡ��checkbox�ĸ���
            	if(record.length == 0){ 
            		alert("��ѡ����Ҫɾ�����У�");
            	}
            	else{
            		if(confirm("ȷ��Ҫɾ��������")){
                		var ids = ""; 
                		for(var i = 0; i < record.length; i++){ 
                			ids += record[i].get("classification_id") 
                			if(i<record.length-1){ 
                				ids = ids + "'or classification_id='"; 
                			}
                		}
                		//alert(ids);
                		var requestConfig = {
    							url :'../../servJsp/esM/sortSysDelete.jsp',				//����ķ�������ַ
    							params : {id:ids},								//�������
    							callback : function(options,success,response){//�ص�����
    								var msg = ["����������ֵ��",response.responseText];
    								var	str	=	response.responseText;
    								alert(str);
    								var grid=Ext.getCmp("sortgrid");
									grid.getStore().load({
										params : {
											start : 0
										}
									});
    								
    							}
    						}
    					Ext.Ajax.request(requestConfig);
            		}
					//��������
            	}

            }
        }],
		columns:[
		         Ext.create('Ext.grid.RowNumberer',{text:'�к�',width:30}),
		         {header:"������ϵ����",dataIndex:'classification_name',width:100},
		         {header:"������ϵ��ʶ��",dataIndex:'classification_id',width:100},
		         {header:"������ϵ˵��",dataIndex:'classification_explain',width:100},
		         {header:"�Ƿ�ΪĬ�Ϸ�����ϵ",dataIndex:'if_default_classification',width:120},
		         delCol,editCol
		],
		bbar:[{									//��ҳ����
			xtype:'pagingtoolbar',
			store:store,
			displayInfo:true,
			displayMsg: '��ʾ {0}-{1}����¼���� {2} ��',   
			emptyMsg: "û�м�¼"
		}]
	});
	store.loadPage(1);							//��ȡ��һҳ���� 0-25
	