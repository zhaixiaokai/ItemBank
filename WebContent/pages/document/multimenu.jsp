<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*"%>
<%@	page import="net.ib.util.service.impl.*"
	import="net.sf.json.JSONArray" import="net.sf.json.JSONObject"%>

<%  DaoImpl di	=	new	DaoImpl();
	List<Map> list	=	di.executeQuery("select * from sys_ib_classification_tree where tree_id='fenleitixi' order by node_path asc");
	JSONArray zNodes = JSONArray.fromObject( list );
	//out.print(zNodes);
	   %>

<style>
ul {
	list-style: none;
}
</style>
<script type="text/javascript">
function getMajor(o){
	document.getElementById("College").innerHTML=o;
	
}
    var zNodes=eval('<%=zNodes%>');
	// alert(zNodes[1].id);
	window.onload = loadMenu; 
	function loadMenu() {
		/* var zNodes = [
		            { id: 1, pId: 0, name: "A" },
		            { id: 11, pId: 1, name: "A1" },
		            { id: 12, pId: 1, name: "A2" },
		            { id: 13, pId: 1, name: "A3" },
		            { id: 111, pId: 11, name: "A111" },
		            { id: 1112, pId: 111, name: "A1112" },
		            { id: 121, pId: 12, name: "A21" },
		            { id: 2, pId: 0, name: "B" },
		            { id: 21, pId: 2, name: "B1" },
		            { id: 3, pId: 0, name: "C" },
		            { id: 31, pId: 3, name: "C1" },
		            { id: 32, pId: 3, name: "C2" }
		            ];
		
		var bb= [
		        {"id":"00009","text":"学院","sno":"1","node_path":"0001","node_series":"1","pid":"0","leaf":"false","node_explain":"无","tree_id":"fenleitixi"},
		        {"id":"00010","text":"学院一","sno":"1","node_path":"0001.0001","node_series":"2","pid":"00009","leaf":"false","node_explain":null,"tree_id":"fenleitixi"},
		        {"id":"00013","text":"专业a","sno":"1","node_path":"0001.0001.0001","node_series":"3","pid":"00010","leaf":"true","node_explain":null,"tree_id":"fenleitixi"},
		        {"id":"00011","text":"学院二","sno":"2","node_path":"0001.0002","node_series":"2","pid":"00009","leaf":"false","node_explain":null,"tree_id":"fenleitixi"},
		        {"id":"00012","text":"专业aa","sno":"1","node_path":"0001.0002.0001","node_series":"3","pid":"00011","leaf":"true","node_explain":null,"tree_id":"fenleitixi"}] 
		 */
		var menuStr = '';
		menuStr = menuFunction(zNodes, 0, menuStr, 0);
		if (menuStr != '') {
			menuStr = '<ul id="nav">' + menuStr + '</ul>';
		/* 	alert(1);
			document.write(menuStr); */
		//	alert(menuStr);
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
					if (pid == 0) {
						codeStr += '<li ><a href="#" onclick="getMajor(this.innerHTML)" id="College">';
						codeStr += zNodes[i].text + ">>";
						codeStr += '</a>';
					} else {
						codeStr += '<li><a href="#" onclick="getMajor(this.innerHTML)">';
						codeStr += zNodes[i].text + ">>";
						codeStr += '</a>';
					}
					// alert(zNodes[i].pid);

					   if (IsChild(zNodes[i].id, zNodes)) {
					      codeStr += '<ul>' + menuFunction(zNodes, (i + 1), '', zNodes[i].id) + '</ul>';
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
	  var IsChild = function (id, zNodes) {
	      var isPass = false;
	      for (var i = 0; i < zNodes.length; i++) {
	          if (id == zNodes[i].pid && !isPass) {
	              isPass = true;
	              break;
	          }
	      }
	      return isPass;
	  } 
</script>


<div id="menu"></div>

