<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*"%>
<%@	page import="net.ib.util.service.impl.*"
	import="net.sf.json.JSONArray" import="net.sf.json.JSONObject"%>

<%  DaoImpl di	=	new	DaoImpl();
	//从sys_ib_classification_tree中取数据
	List<Map> list	=	di.executeQuery("select * from sys_ib_classification_tree where tree_id='fenleitixi' order by node_path asc");
	//将list<Map>转换为json串
	JSONArray zNodes = JSONArray.fromObject( list );
	   %>

<style>
ul {
	list-style: none;
}
</style>
<script type="text/javascript">
//替换页面元素
function getMajor(o){
	document.getElementById("College").innerHTML=o;
	
}
    var zNodes=eval('<%=zNodes%>');
	window.onload = loadMenu; 
	function loadMenu() {
		var menuStr = '';  //div中html内容
		menuStr = menuFunction(zNodes, 0, menuStr, 0);//递归函数结果返回给menuStr
		if (menuStr != '') {
			menuStr = '<ul id="nav">' + menuStr + '</ul>';
			document.getElementById('menu').innerHTML = menuStr;
		}
	}
	//递归生成菜单
	var menuFunction = function(zNodes, num, menuStr, pid) {
		//递归调出条件
		if (num == (zNodes.length)) {
			return menuStr;
		} else {
			var codeStr = '';
			for ( var i = num; i < zNodes.length; i++) {
				if (pid == zNodes[i].pid) {
					//判断是否为根节点
					if (pid == 0) {     
						codeStr += '<li ><a href="#" onclick="getMajor(this.innerHTML)" id="College">';
						codeStr += zNodes[i].text + ">>";
						codeStr += '</a>';
					} else {
						codeStr += '<li><a href="#" onclick="getMajor(this.innerHTML)">';
						codeStr += zNodes[i].text + ">>";
						codeStr += '</a>';
					}
					//判断是否为叶子节点
					if (IsChild(zNodes[i].id, zNodes)) {  
						codeStr += '<ul>'
								+ menuFunction(zNodes, (i + 1), '',
										zNodes[i].id) + '</ul>';
					}
					codeStr += '</li>';
				}
			}
			return menuStr += codeStr;
		}
	};
	//判断是否为叶子节点
	var IsChild = function(id, zNodes) {
		var isPass = false;
		for ( var i = 0; i < zNodes.length; i++) {
			//寻找是否有以其id作为上级节点pid
			if (id == zNodes[i].pid && !isPass) {
				isPass = true;
				break;
			}
		}
		return isPass;
	}
</script>
<div id="menu"></div>

