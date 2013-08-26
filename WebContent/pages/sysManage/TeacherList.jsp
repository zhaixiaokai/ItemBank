<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.sql.*,javax.sql.rowset.serial.*"
	import="java.io.*" import="javax.servlet.*"
	import="javax.servlet.http.*" import="java.util.*"%>
<%@ page import="java.text.*" import="javax.servlet.ServletException"
	import="org.apache.commons.fileupload.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Tree Example</title>

<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />

<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css"
	href="../js/ext-4.0.7-gpl/resources/css/ext-all.css" />

<script type="text/javascript" src="../js/ext-4.0.7-gpl/bootstrap.js"></script>
<script type="text/javascript" src="progress-bar-pager.js"></script>
<script type="text/javascript"
	src="../js/ext-4.0.7-gpl/locale/ext-lang-zh_CN.js"></script>

</head>
<body>

	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/sysManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<div id="dd"></div>
	<script type="text/javascript">
Ext.define('Model', {
    extend: 'Ext.data.Model',
    idProperty: 'Model',
    fields: ['user_id','name','gender','id']        
});
var sm = Ext.create('Ext.selection.CheckboxModel');
var store = Ext.create('Ext.data.Store', {
		model: 'Model',
		pageSize:'10',
	proxy : {
		type:'ajax',							//获取方式ajax
		//url : "../Json/testGrid.json",		//获取json地址，可换为***.jsp
		url : "teacherManageAction",
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
							url :'../test/testDelet.jsp?action=delete',				//请求的服务器地址
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
						url :'../test/testEdit.jsp?action=edit',				//请求的服务器地址
						params : {id:rec.get("column1")}				//请求参数
					}
					Ext.Ajax.request(requestConfig);					//发送请求
			} 
		}] 

	};	

var grid=Ext.create('Ext.grid.Panel',{
	title:'教师列表',							//表格标题
	renderTo:'dd',							//表格渲染到id为d的元素
	bodyStyle:'width:400',					//表格宽度
	autoWidth:true,				
	store:store,	 						//加载数据
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
							url :'../test/testDelet.jsp?action=delete',				//请求的服务器地址
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
	         Ext.create('Ext.grid.RowNumberer',{text:'user_id',width:35}),
	         {header:"uname",dataIndex:'name',width:150},
	         {header:"bumen",dataIndex:'gender',width:150},
	         {header:"uid",dataIndex:'column3',width:150},
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
</script>

	

	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>
