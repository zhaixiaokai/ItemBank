<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../../servJs/NestedTables.js"></script>
<script type="text/javascript">
	
	//从数据库中获取系统功能列表
	function GetFunctionTree() {
		var FunctionTreeData = null;
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "FunctionGetTreeDataAction",
			success : function(result) {
				//alert(result);
				FunctionTreeData = result;
			},
			error : function() {
			}
		});
		return FunctionTreeData;
	}

	var ChildrenJson; //用于存储截取的子json串
	
	//点击某一checkbox触发的事件
	function CheckBoxSelectEvent() {

		
		ChildrenJsonGet(FunctionJson,this.id);
		
		ForwardNodeStatus(ChildrenJson,this);
		
		BackNodeStatus(FunctionJson,this,this);
	}
	
	// 根据点击的checkbox 的id 获取其之后的json串，并存储在全局变量ChildrenJson中
	function ChildrenJsonGet( json , id ){
	

	 		for( var i = 0; i < json.length; i++){
				var CheckBoxId="CB_"+json[i].id;
				// 迭代过程中，一旦某一json串的id与点击的checkbox的id相同，返回其之后的所有json串
				if(CheckBoxId==id){
					
					ChildrenJson = json[i].children;
					
				}
			
				else if(json[i].children!= undefined )
					ChildrenJsonGet(json[i].children,id);	
			} 	
	}
	
	
	// 对得到的子json串进行遍历，实现父节点状态改变以后，子节点状态也发生相应的变化
	function ForwardNodeStatus(json,o) {
		

	if (json == undefined) {

		} 

	else {
			for ( var i = 0; i < json.length; i++) {
				var CheckBoxId = "CB_" + json[i].id;

				var CheckBox = document.getElementById(CheckBoxId);
			 	if (json[i].leaf == "true") {

					CheckBox.checked = o.checked;
				} else {

					CheckBox.checked = o.checked;

					ForwardNodeStatus(json[i].children, CheckBox);
				} 
		

			}
		}
	}
	
	//点击某一子checkbox以后，其所属的父checkbox也相应的改变
	function BackNodeStatus(json,checkbox,o){
		var flag=0;// flag表示是否在遍历整个functionTree时候找到所点击的checkbox的id，若找到则为1，否则为0
		
		for(var i=0;i<json.length;i++){
			var CheckBoxId="CB_"+json[i].id;
			var CheckBox = document.getElementById(CheckBoxId);
			
			if(CheckBoxId==o.id){
				flag=1;
				
				}
			else{
				if(json[i].children!= undefined ){
					flag = BackNodeStatus(json[i].children,CheckBox,o);
					if(flag == 1)
						CheckBox.checked = o.checked;
						
						
				}
				
			}
			  if(flag == 1){
				 break;  
			 } 
				
		}
		return flag;
	}
</script>
</head>
<body>
	<div id="TableDiv"></div>
</body>
<script type="text/javascript">
	var FunctionJson = GetFunctionTree();
	//var Str=CreateTableByNode(FunctionJson[0]);
	//DispTable("TableDiv",Str); 
	
	initTableContainer(FunctionJson,"TableDiv");
	CreateMultiTableInterface(FunctionJson,"TableDiv",CheckBoxSelectEvent);
	alert($("#TableDiv").html());
</script>
</html>