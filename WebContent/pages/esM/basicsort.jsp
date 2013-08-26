<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*"%>
<%@	page import="net.ib.util.service.impl.*"
	import="net.sf.json.JSONArray" import="net.sf.json.JSONObject"%>

<%  DaoImpl di	=	new	DaoImpl();
	//从sys_ib_classification_tree中取数据
	List<Map> list	=	di.executeQuery("select classification_id,classification_name from sys_ib_classification_system");
	for (int i = 0; i < list.size(); i++) {
		String classification_id=(String) list.get(i).get("classification_id");
		String classification_name=(String) list.get(i).get("classification_name");
		Map sort= list.get(i);
		sort.put("pid", "jiedian1");
		sort.put("id", ""+classification_id+"");
		sort.put("text", ""+classification_name+"");
	}
	//将list<Map>转换为json串
	String id="";
	String name="";
	for (int i = 0; i < list.size(); i++) {
		id  = (String) list.get(i).get("classification_id");
		name=(String) list.get(i).get("classification_name");
		List<Map> sortlist	=	di.executeQuery("select id,text,pid,tree_id,node_path from sys_ib_classification_tree where tree_id='"+id+"' order by node_path asc");
		for (int j = 0; j < sortlist.size();j++) {
			Map sortelement= sortlist.get(j);
			if(sortlist.get(j).get("pid").equals("0")){
				Map ss=sortlist.get(j);
				ss.remove("pid");
				ss.put("pid", ""+id+"");
			}
			list.add(sortelement);
		}	
	}
	// List<Map> list11 = new ArrayList<Map>();
	Map<String, Object> rootnode = new HashMap<String, Object>();
	rootnode.put("pid", "0");
	rootnode.put("text", "分类体系列表");
	rootnode.put("id", "jiedian1");
	list.add(0,rootnode);

	/*Map<String, Object> map1 = new HashMap<String, Object>();
	map1.put("pid", "1");
	map1.put("text", "分类体系");
	map1.put("id", "fenleitixi");
	Map<String, Object> map2 = new HashMap<String, Object>();
	map2.put("pid", "1");
	map2.put("text", "基本分类体系");
	map2.put("id", "jibenfenleitixi");
	list11.add(rootnode);
	list11.add(map1);
	list11.add(map2);*/
	
	
	
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
    alert(zNodes);
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

