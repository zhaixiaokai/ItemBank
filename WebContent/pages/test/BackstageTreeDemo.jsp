<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Tree Example</title>
    <!-- 引入alertDialog -->
    <link href="../js/alertDialog/skin/chrome/chrome.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/alertDialog/artDialog.js"></script>
	
	
    <link rel="stylesheet" type="text/css" href="../js/ext-4.0.7-gpl/resources/css/ext-all.css" />
    <script type="text/javascript" src="../js/ext-4.0.7-gpl/bootstrap.js"></script>
    <script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.rotate.js"></script>
    <script type="text/javascript" src="TreeOperate.js" charset="GBK"></script> 
<script type="text/javascript">

</script>
</head>
<body>
	<div id	="te1" style="width: 300px;height:500px;">
		<a href="javascript:void(0)" onclick="addPreviousNode()">添加前置机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="addAfterNode()">添加后置机构</a><strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="modifyCurrentNode()">修改机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="deleteCurrentNode()">删除机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="cutCurrentNode()">剪切机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="pasteAsPreviousNode()">前置粘帖</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="pasteAsAfterNode()">后置粘帖</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="PasteAsChildNode()">粘帖为子机构</a><strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="addChildeNode()">添加子机构</a><strong>|</strong>&nbsp;
		<a href="javascript:void(0)" onclick="pasteAsPreviousNode()">测试</a> 
	
	</div>
	<div id	="te2" style="width: 300px;height:500px;background-color: blue;position: absolute;left: 100px;top: 200px">
	
	
	</div>
	
	
	<script type="text/javascript">
	
  	Ext.regModel('demo', {
        extend: 'Ext.data.Model',
        fields: ['text' ,'id' ,'pid','sno','node_path','tree_id','node_series','node_explain' ]//这里不知道为什么不能添加leaf
    }); 
	//下面是通过ajax去后台获取数据
	store = Ext.create('Ext.data.TreeStore', {
		model: 'demo',
		proxy : {
			type:"ajax",
			//url : "../Json/TestTree.json"
			url : "TestTreeJson.jsp"
			//url : "../Json/test.json"
		},
		root : {
			expanded : true,
			text : "根"
		}
	});
	// 生成树
	tree	=	Ext.create('Ext.tree.Panel', {
		width : 400,
		height : 700,
		store : store,
		autoScroll : true,
		rootVisible : true,
		renderTo : 'te2'
	});
 	tree.on({
        //目录树单击事件
        'itemclick' : function(view, rcd, item, idx, event, eOpts) {
        	//alert("id:"+rcd.get("id")+"\n"+"pid:"+rcd.get("pid")+"\n"+"leaf:"+rcd.get("leaf")+"\n"+"sno:"+rcd.get("sno")+"\n"+"text:"+rcd.get("text")+"\n"+"node_path:"+rcd.get("node_path")+"\n"+"tree_id:"+rcd.get("tree_id")+"\n"+"node_series:"+rcd.get("node_series")+"\n"+"node_explain:"+rcd.get("node_explain"));
        	currentNode=rcd;
        	//alert("clic");
        },  
        //双击事件
        'itemdblclick' : function(view, rcd, item, idx, event, eOpts) {
            ///var dirid = rcd.get('id'); //节点id
           // var dirtype = rcd.raw.dirtype; //自定义数据
    	},
    	//右击事件
        'itemcontextmenu' : function(view, rcd, item, idx, event, eOpts) {
        	alert("rightClicked");
            //event.preventDefault();
            //this.showTreeItemMenu(rcd, event); //自定义处理函数
        },
        scope : this
    });
	</script>

   
</body>
</html>
