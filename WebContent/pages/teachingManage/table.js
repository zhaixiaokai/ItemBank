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
			type:'ajax',							//获取方式ajax
			//url : "../Json/testGrid.json",		//获取json地址，可换为***.jsp
			url : "pagingServ.jsp",
			reader : {
				type : 'json',						//数据阅读器json
				totalProperty : 'recordCount',		//数据总数标识
				root : 'data'						//数据内容标识
			},
			actionMethods : {
				read : 'post'
			}
		}
		
	});
	var delCol = {
			header : '删除',
			width : 85,
			xtype : 'actioncolumn',
 			items : [{
				icon : 'modify.png',
				handler : function(view, rowIndex, coIIndex, item, e) {
					if(confirm("确定要删除数据吗？")){
						var	id	=view.getStore().getAt(rowIndex).get("id");
						//通过ajax向后台传参数
						var requestConfig = {
								url :'testDelet.jsp?action=delete',				//请求的服务器地址
								params : {id:id},								//请求参数
								callback : function(options,success,response){//回调函数
									var msg = ["服务器返回值：",response.responseText];
									var	str	=	response.responseText;
									alert(str);
								}
							}
							Ext.Ajax.request(requestConfig);					//发送请求
							//alert("end");
					}
				} 
			}] 
		};	
	
	var editCol = {
			header : '编辑',
			width : 85,
			xtype : 'actioncolumn',
 			items : [{
				icon : 'modify.png',
				handler : function(view, rowIndex, coIIndex, item, e) {
					var rec = view.getStore().getAt(rowIndex);
					//通过ajax向后台传参数
					var requestConfig = {
							url :'testEdit.jsp?action=edit',				//请求的服务器地址
							params : {id:rec.get("curse_id")}				//请求参数
						}
						Ext.Ajax.request(requestConfig);					//发送请求
				} 
			}] 

		};	
	
	var grid=Ext.create('Ext.grid.Panel',{
		title:'title',							//表格标题
		renderTo:'tableid',							//表格渲染到id为d的元素
		//bodyStyle:'width:770',					//表格宽度
		autoWidth:true,				
		store:store,	 						//加载数据
		fields:['curse_id' ,'curse_name' ,'special_field_id','curse_credit','assessment_method'],
	    layout : 'fit',							
		columnLines : true,						//竖向分割线
		frame:false,							//框架
		selModel: sm,
		tbar: [{								//批量删除功能
            text: '批量删除',
            handler : function(){
            	var record = grid.getSelectionModel().getSelection();//获取选中checkbox的个数
            	if(record.length == 0){ 
            		alert("请选择需要删除的行！");
            	}
            	else{
            		if(confirm("确定要删除数据吗？")){
                		var ids = ""; 
                		for(var i = 0; i < record.length; i++){ 
                			ids += record[i].get("id") 
                			if(i<record.length-1){ 
                				ids = ids + "'or id='"; 
                			}
                		}
                		//alert(ids);
                		var requestConfig = {
    							url :'testDelet.jsp?action=delete',				//请求的服务器地址
    							params : {id:ids},								//请求参数
    							callback : function(options,success,response){//回调函数
    								var msg = ["服务器返回值：",response.responseText];
    								var	str	=	response.responseText;
    								alert(str);
    							}
    						}
    					Ext.Ajax.request(requestConfig);
            		}
					//发送请求
            	}

            }
        }],
		columns:[
		         Ext.create('Ext.grid.RowNumberer',{text:'列表序号',width:50}),
		         {header:"课程编号",dataIndex:'curse_id',width:110},
		         {header:"课程名称",dataIndex:'curse_name',width:110},
		         {header:"所属专业编号",dataIndex:'special_field_id',width:110},
		         {header:"课程学分",dataIndex:'curse_credit',width:110},
		         {header:"考核方式",dataIndex:'assessment_method',width:110},
		         delCol,editCol
		],
		bbar:[{									//分页工具
			xtype:'pagingtoolbar',
			store:store,
			displayInfo:true,
			displayMsg: '显示 {0}-{1}条记录，共 {2} 条',   
			emptyMsg: "没有记录"
		}]
	});
	store.loadPage(1);							//获取第一页内容 0-25
	