
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Tree Example</title>
    <link rel="stylesheet" type="text/css" href="../js/ext-4.0.7-gpl/resources/css/ext-all.css" />

    <script type="text/javascript" src="../js/ext-4.0.7-gpl/bootstrap.js"></script>
    <script type="text/javascript" src="progress-bar-pager.js"></script>
   	<script type="text/javascript" src="../js/ext-4.0.7-gpl/locale/ext-lang-zh_CN.js"></script> 
</head>
<body>
<div	id	=	"d" style="width:1100px;height:1100px;background-color: green">


</div>
<script type="text/javascript">

    Ext.define('Model', {
        extend: 'Ext.data.Model',
        idProperty: 'Model',
        fields: ['column1','column2','column3']        
    });
    var sm = Ext.create('Ext.selection.CheckboxModel');
	var store = Ext.create('Ext.data.Store', {
 		model: 'Model',
		proxy : {
			type:'ajax',						//获取方式ajax
			//url : "../Json/testGrid.json",		//获取json地址，可换为***.jsp
			url : "pagingServ.jsp",
			reader : {
				type : 'json',					//数据阅读器json
				totalProperty : 'recordCount',	//数据总数标识
				root : 'data'				//数据内容标识
			},
			actionMethods : {
				read : 'post'
			}
		}/* ,		
		autoLoad:true */
		
	});
	var grid=Ext.create('Ext.grid.Panel',{
		title:'title',							//表格标题
		renderTo:'d',							//表格渲染到id为d的元素
		bodyStyle:'width:500',					//表格宽度
		autoWidth:true,				
		store:store,	 						//加载数据
	    layout : 'fit',							
		columnLines : true,						//竖向分割线
		frame:false,							//解决title偏移
		selModel: sm,
		columns:[
		         Ext.create('Ext.grid.RowNumberer',{text:'行号',width:35}),
		         {header:"uname",dataIndex:'column1',width:150},
		         {header:"bumen",dataIndex:'column2',width:150},
		         {header:"uid",dataIndex:'column3',width:150}
		],
		bbar:[{									//分页工具
			xtype:'pagingtoolbar',
			store:store,
			pageSize:'10',
			displayInfo:true,
			displayMsg: '显示 {0}-{1}条记录，共 {2} 条',   
			emptyMsg: "没有记录"
		}]
	});
	store.loadPage(1);							//获取第一页内容 0-25
	
</script>
</body>
</html>
