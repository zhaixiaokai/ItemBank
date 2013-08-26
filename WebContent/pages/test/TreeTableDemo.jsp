<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <title>Tree Table Example</title>
    <!-- 引入alertDialog -->
    <link href="../js/alertDialog/skin/chrome/chrome.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="../js/alertDialog/artDialog.js"></script>
	
	
    <link rel="stylesheet" type="text/css" href="../js/ext-4.0.7-gpl/resources/css/ext-all.css" />
    <script type="text/javascript" src="../js/ext-4.0.7-gpl/bootstrap.js"></script>
    <script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.rotate.js"></script>
    <script type="text/javascript">
    	var	currentNode=null;
    	var	store	=	null;
    	var	tree=null;
    	
    	function	AjaxGetTreeJson(){
    		var	TreeJson=$.ajax( {
    			type : "post",
    			dataType : "json",
    			async : false,
    			url : "TestTreeJson.jsp",
    			success : function(result) {
    			},
    			error:function(){
    			}
    		});
    	}
    	//验证所填写信息合法性，并且执行添加
    	function	getAddPreviousNodeInfo(){
    			artDialog({
    				title:'添加节点',
    				content:'<table>'+
    						'<tr><td>唯一标识符：</td><td><input type="text" id="id"/></td></tr>'+
    						'<tr><td>节点名称：</td><td><input type="text" id="text"/></td></tr>'+
    						'<tr><td>说明：</td><td><input type="text" id="explain"/></td></tr>'+
    						'</table>',
    				yesText:'添加',
    				noText:'取消'
    			},
    	        function(){
    				//点击确认
    				//添加当前填写的节点（唯一标识符、text、explain）
    				//newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf
    				if(document.getElementById("id").value==""||document.getElementById("id").value==null){
    					alert("添加失败：请填写唯一标识符！");
    					return;
    				}
    				if(document.getElementById("text").value==""||document.getElementById("text").value==null){
    					alert("添加失败：请填写节点名称！");
    					return;
    				}
    				else{
    					//alert("sth");
    					var	newId	=	document.getElementById("id").value;
    					var	newText	=	document.getElementById("text").value;
    					var	newPid	=	currentNode.get("pid");
    					var	newPath	=	currentNode.get("node_path");
    					var	newSNO	=	currentNode.get("sno");
    					var	newName	=	currentNode.get("classification_id");
    					var	newSeries	=	currentNode.get("node_series");
    					var	newExplain	=	document.getElementById("text").value;
    					var	newLeaf	=	"true";
    					AjaxNodeAdd(newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf);
    				}
         			var sameLevelNodes	=	currentNode.parentNode.childNodes;
        			//alert(sameLevelNodes.length);//获取同级节点个数
        			var	currentNodeSNO	=	currentNode.get("sno");
        			//alert(num_currentNodeSNO);
            		for(var i=currentNodeSNO;i<=sameLevelNodes.length;i++){
        				//对于同级节点修改SNO以及NodePath的值
         				var	currOperateNode	=	sameLevelNodes[i-1];
         				rightMoveCurrentNode(currOperateNode);
        			}
    	        },
    	        function(){
    	            alert('你点了取消');
    	        }
    			);
    	}
    	function	addNode(newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf){
    		
    	}
    	function	deleteNode(id){
    		
    	}
    	//节点信息的修改
    	function	AjaxNodeAdd(newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf){
    		$.ajax( {
    			type : "post",
    			dataType : "json",
    			async : false,
    			url : "updateNode.jsp?action=insert",
    			data:"id="+newId+"&pid="+newPid+"&sno="+newSNO+"&node_path="+newPath+"&classification_id="+newName
    					+"&node_series="+newSeries+"&node_explain="+newExplain+"&leaf="+newLeaf+"&text="+newText+"&table="+"SYS_IB_CLASSIFICATION_TREE",
    			success : function(result) {
    			},
    			error:function(){
    			}
    		});
    	}
    	//节点信息更新
    	function	AjaxNodeUpdate(newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf){
    		$.ajax( {
    			type : "post",
    			dataType : "json",
    			async : false,
    			url : "updateNode.jsp?action=update",
    			data:"id="+newId+"&pid="+newPid+"&sno="+newSNO+"&node_path="+newPath+"&classification_id="+newName
    					+"&node_series="+newSeries+"&node_explain="+newExplain+"&leaf="+newLeaf+"&text="+newText+"&table="+"SYS_IB_CLASSIFICATION_TREE",
    			success : function(result) {
    			},
    			error:function(){
    			}
    		});
    	}
    	//弹出填写添加节点信息窗口
     	function	addPreviousNode(){
    		if(currentNode==null){
    			alert("请选择节点");
    			return;
    		}
    		else{
    			getAddPreviousNodeInfo();
    		}
    	} 
/*     	function	rightMoveNode(node){
    		var	SNOBuf=currOperateNode.get("sno");
    		var	pathBuf	=	currOperateNode.get("node_path");
    		
    	} */
    	//向后移动本级节点以及子节点
    	function	rightMoveCurrentNode(node){
    		var	currentNodeLevel	=	node.get("node_series");
    		alert("当前节点级数为："+currentNodeLevel);
    		rightMoveCurrentNodeAndChildNode(node,currentNodeLevel);
    	}
    	//递归向后移动本级节点以及子节点			currentNodeLevel为增加节点的层次
    	//0001.0001				0001.0002
    	//0001.0001.0001		0001.0002.0001
    	//0001.0001.0002		0001.0002.0002
    	function	rightMoveCurrentNodeAndChildNode(node,currentNodeLevel){
    		alert("当前操作节点："+node.get("text"));
			var	nodePath	=	node.get("node_path");
			alert("当前操作节点路径为："+nodePath);
			var	subPath	=	nodePath.substr(5*(currentNodeLevel-1),4);
			alert("subPath:"+subPath);
			subPath=parseInt(subPath)+1;
			alert("new int subpath："+subPath);
			if(subPath<10){
				subPath="000"+subPath;
			}
			else if(subPath<100){
				subPath="00"+subPath;
			}
			else if(subPath<1000){
				subPath="0"+subPath;
			}
			else if(subPath<10000){
				subPath=subPath;
			}
			alert("new str subpath："+subPath);
			nodePath=nodePath.substring(0,5*(currentNodeLevel-1))+subPath+nodePath.substr(5*(parseInt(currentNodeLevel))-1);
			alert("part1:	"+nodePath.substring(5*(currentNodeLevel-2),5*(currentNodeLevel-1)));
			alert("part2:	"+subPath);
			alert("nodePath.substr("+5*(currentNodeLevel)+"):"+nodePath.substr(5*(currentNodeLevel)));
			alert("new nodePath"+nodePath);
			//获取当前节点等级，如果等级等于currentNodeLevel，则当前级数节点的序号自增
 			var	currentLevel=node.get("node_series");

			var	newSNO	=	node.get("sno");
			if(currentLevel==currentNodeLevel){
				alert("equals!!!		currentNodeLevel:	"+currentNodeLevel);
				currentLevel=parseInt(currentLevel)+1;
				alert(currentLevel);
				newSNO	=	parseInt(newSNO)+1;
				alert("newSNO:	"+newSNO);
			} 
			var	newId	=	node.get("id");
			var	newText	=	node.get("text");
			var	newPid	=	node.get("pid");
			var	newPath	=	nodePath;
			var	newName	=	node.get("classification_id");
			var	newSeries	=	node.get("node_series");
			var	newExplain	=	node.get("node_explain");
			var	newLeaf	=	node.get("leaf");
			AjaxNodeUpdate(newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf);
    		if(node.get("leaf")!=true){
    			var	children	=	node.childNodes;
    			
    			for(var	i=0;i<children.length;i++){
    				rightMoveCurrentNodeAndChildNode(children[i],currentNodeLevel);
    			}
    		}
    	}

    </script>
</head>
<body>
    <div id	="te1" style="width: 300px;height:500px;">
		<a href="javascript:void(0)" onclick="addPreviousNode()">添加前置机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)">添加后置机构</a><strong>|</strong>&nbsp;
		<a href="javascript:void(0)">修改机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)">删除机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)">剪切机构</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)">前置粘帖</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)">后置粘帖</a> <strong>|</strong>&nbsp;
		<a href="javascript:void(0)">粘帖为子机构</a><strong>|</strong>&nbsp;
		
		<a href="javascript:void(0)" onclick="test()">测试</a> 
	
	</div>

	<div id	="te2" style="width: 300px;height:500px;background-color: blue;position: absolute;left: 100px;top: 200px">
	
	
	</div>
	
	
	<script type="text/javascript">
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
			header : '管理',
			width : 85,
			xtype : 'actioncolumn',
 			items : [{
				icon : 'modify.png',
				handler : function(view, rowIndex, coIIndex, item, e) {
					var rec = view.getStore().getAt(rowIndex);
					//通过ajax向后台传参数
					var requestConfig = {
							url :'testEdit.jsp?action=edit',				//请求的服务器地址
							params : {id:rec.get("column1")}				//请求参数
						}
						Ext.Ajax.request(requestConfig);					//发送请求
				} 
			}] 

		};	
	Ext.onReady(function(){
		Ext.regModel('demo', {
	        extend: 'Ext.data.Model',
	        fields: ['text' ,'id' ,'pid','sno','node_path','teaching_material_id','node_series','node_explain']
	    });
	 	var store = Ext.create('Ext.data.TreeStore', {
	 		model: 'demo',
			proxy : {
				type:'ajax',
				//url : "../Json/TestTreeTable.json"
				url : "TestTreeTableJson.jsp"
			},
			autoLoad:true
		}); 
		// 树
		Ext.create('Ext.tree.Panel', {
			width : 400,
			height : 700,
			autoScroll : true,
			rootVisible : false,
			renderTo : 'te2',
			store: store, 
			fields:['text' ,'id' ,'pid','sno','node_path','teaching_material_id','node_series','node_explain'],
			columns:[{
				xtype:'treecolumn',
				text:'章节名称',
				dataIndex:'text',
				sortable:true
			},/*{
				text:'唯一编号',
				dataIndex:'id',
				sortable:true,
				flex:1
				
			},{
				text:'上级节点',
				dataIndex:'pid',
				sortable:true,
				flex:1
				
			},*/
			delCol,editCol
			]
		});	
	})
    
	
	</script>

   
</body>
</html>
