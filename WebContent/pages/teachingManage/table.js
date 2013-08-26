    Ext.define('Model', {
        extend: 'Ext.data.Model',
        idProperty: 'Model',
        fields: ['curse_id','curse_name','special_field_id','curse_credit','assessment_method','id']        
    });
    var sm = Ext.create('Ext.selection.CheckboxModel');
	var store = Ext.create('Ext.data.Store', {
 		model: 'Model',
 		pageSize:'10',
		proxy : {
			type:'ajax',							//��ȡ��ʽajax
			//url : "../Json/testGrid.json",		//��ȡjson��ַ���ɻ�Ϊ***.jsp
			url : "pagingServ.jsp",
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
						var	id	=view.getStore().getAt(rowIndex).get("id");
						//ͨ��ajax���̨������
						var requestConfig = {
								url :'testDelet.jsp?action=delete',				//����ķ�������ַ
								params : {id:id},								//�������
								callback : function(options,success,response){//�ص�����
									var msg = ["����������ֵ��",response.responseText];
									var	str	=	response.responseText;
									alert(str);
								}
							}
							Ext.Ajax.request(requestConfig);					//��������
							//alert("end");
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
							url :'testEdit.jsp?action=edit',				//����ķ�������ַ
							params : {id:rec.get("curse_id")}				//�������
						}
						Ext.Ajax.request(requestConfig);					//��������
				} 
			}] 

		};	
	
	var grid=Ext.create('Ext.grid.Panel',{
		title:'title',							//������
		renderTo:'tableid',							//�����Ⱦ��idΪd��Ԫ��
		//bodyStyle:'width:770',					//�����
		autoWidth:true,				
		store:store,	 						//��������
		fields:['curse_id' ,'curse_name' ,'special_field_id','curse_credit','assessment_method'],
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
                			ids += record[i].get("id") 
                			if(i<record.length-1){ 
                				ids = ids + "'or id='"; 
                			}
                		}
                		//alert(ids);
                		var requestConfig = {
    							url :'testDelet.jsp?action=delete',				//����ķ�������ַ
    							params : {id:ids},								//�������
    							callback : function(options,success,response){//�ص�����
    								var msg = ["����������ֵ��",response.responseText];
    								var	str	=	response.responseText;
    								alert(str);
    							}
    						}
    					Ext.Ajax.request(requestConfig);
            		}
					//��������
            	}

            }
        }],
		columns:[
		         Ext.create('Ext.grid.RowNumberer',{text:'�б����',width:50}),
		         {header:"�γ̱��",dataIndex:'curse_id',width:110},
		         {header:"�γ�����",dataIndex:'curse_name',width:110},
		         {header:"����רҵ���",dataIndex:'special_field_id',width:110},
		         {header:"�γ�ѧ��",dataIndex:'curse_credit',width:110},
		         {header:"���˷�ʽ",dataIndex:'assessment_method',width:110},
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
	