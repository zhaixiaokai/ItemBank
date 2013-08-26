<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*"%>
<%@	page import="net.ib.util.service.impl.*"
	import="net.sf.json.JSONArray" import="net.sf.json.JSONObject"%>

<%  DaoImpl di	=	new	DaoImpl();
	List<Map> list	=	di.executeQuery("select * from sys_department_tree where tree_id='departmentTree' order by node_path asc");
	JSONArray zNodes = JSONArray.fromObject( list );
	   %>

<style>
ul {
	list-style: none;
}
</style>

<input type=hidden id="NodeName" name="NodeName" value="">
<script type="text/javascript">
function getMajor(o,name){
	document.getElementById("College").innerHTML=o;
	document.getElementById("NodeName").value=name;
}
    var zNodes=eval('<%=zNodes%>');
	// alert(zNodes[1].id);
	window.onload = loadMenu; 
	function loadMenu() {
		
		var menuStr = '';
		menuStr = menuFunction(zNodes, 0, menuStr, 0);
		if (menuStr != '') {
			menuStr = '<ul id="nav">' + menuStr + '</ul>';
			/* 	alert(1);
				document.write(menuStr); */
				// alert(menuStr);
			document.getElementById('menu').innerHTML = menuStr;
		}
	}
	//递归生成菜单
	var menuFunction = function(zNodes, num, menuStr, pid) {

		if (num == (zNodes.length)) {
			// alert(zNodes.length);
			return menuStr;
		} else {
			var codeStr = '';
			for ( var i = num; i < zNodes.length; i++) {
				//alert(codeStr);
				if (pid == zNodes[i].pid) {
					if (pid == 0) {     //判断是否为根节点
						codeStr += '<li ><a href="#" onclick="getMajor(this.innerHTML,\''+zNodes[i].text+'\')" id="College" >';
						codeStr += zNodes[i].text + ">>";
						codeStr += '</a>';
					} else {
						codeStr += '<li><a href="#" onclick="getMajor(this.innerHTML,\''+zNodes[i].text+'\')">';
						codeStr += zNodes[i].text + ">>";
						codeStr += '</a>';
					}
					// alert(zNodes[i].pid);

					if (IsChild(zNodes[i].id, zNodes)) {  //判断是否为叶子节点
						codeStr += '<ul>'
								+ menuFunction(zNodes, (i + 1), '',
										zNodes[i].id) + '</ul>';
					}
					/* 	if (zNodes[i].leaf =="false") {
							codeStr += '<ul >'
									+ menuFunction(zNodes, (i + 1), '',
											zNodes[i].id) + '</ul>';
						} else {
							break;
						} */
					codeStr += '</li>';
				}
			}
			return menuStr += codeStr;

		}
	};
	//验证是否存在子项
	var IsChild = function(id, zNodes) {
		var isPass = false;
		for ( var i = 0; i < zNodes.length; i++) {
			if (id == zNodes[i].pid && !isPass) {
				isPass = true;
				break;
			}
		}
		return isPass;
	}
</script>


<div id="menu"></div>

