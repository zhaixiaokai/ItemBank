/* (非-Javadoc)
 * <p>名称: loadMenu</p>
 * <p>说明: 加载多级菜单</p>
 * @param target 加载多级菜单的目标div的id
 * @param zNodes 所要加载的多级菜单的内容json
 * @param func 点击叶子节点触发的事件
 * @param KeyId 根节点的Id
 * @return
 */

//只能选择叶子节点
function loadMenu(target, zNodes, func, KeyId) {
	var menuStr = '';
	menuStr = CreateMenuContent(zNodes, 0, menuStr, zNodes[0].pid, KeyId);
	if (menuStr != '') {
		menuStr = '<ul id="nav">' + menuStr + '</ul>';
		//alert(menuStr);
		document.getElementById(target).innerHTML = menuStr;
	}
	//只给叶子节点添加点击事件
	for ( var i = 0; i < zNodes.length; i++) {
		if (IsChild(zNodes[i].id, zNodes)) {
			continue;
		}
		document.getElementById(zNodes[i].id).onclick = func;
	}
	//alert(menuStr);
}

//节点均可选择
function loadMenu1(target, zNodes, func, KeyId) {
	var menuStr = '';
	menuStr = CreateMenuContent(zNodes, 0, menuStr, 0, KeyId);
	//alert(menuStr);
	if (menuStr != '') {
		menuStr = '<ul id="nav">' + menuStr + '</ul>';
		//alert(menuStr);
		document.getElementById(target).innerHTML = menuStr;
	}
	//只给叶子节点添加点击事件

	for ( var i = 0; i < zNodes.length; i++) {
		document.getElementById(zNodes[i].id).onclick = func;
		//	alert("here");
	}
}
/* (非-Javadoc)
 * <p>名称: CreateMenuContent</p>
 * <p>说明: 递归生成多级菜单</p>
 * @param zNodes 所要加载的多级菜单的内容json
 * @param num 当前加载到的节点在zNodes中的位置
 * @param menuStr 递归生成的innerHTML内容
 * @param pid 上级节点的id
 * @param KeyId 根节点的Id
 * @return
 */
var CreateMenuContent = function(zNodes, num, menuStr, pid, KeyId) {

	if (num == (zNodes.length)) {
		// alert(zNodes.length);
		return menuStr;
	} else {
		var codeStr = '';
		for ( var i = num; i < zNodes.length; i++) {
			if (pid == zNodes[i].pid) {
				if (pid == 0) { //判断是否为根节点
					codeStr += '<li><a href="javascript:void(0);" name="'
							+ "College" + '" id="' + KeyId + '">';
					codeStr += zNodes[i].text;
					codeStr += '</a>';
				} else {
					codeStr += '<li><a href="javascript:void(0);" id="'
							+ zNodes[i].id + '">';
					codeStr += zNodes[i].text;
					codeStr += '</a>';
				}

				if (IsChild(zNodes[i].id, zNodes)) { //判断是否为叶子节点
					codeStr += '<ul>'
							+ CreateMenuContent(zNodes, (i + 1), '',
									zNodes[i].id, KeyId) + '</ul>';
				}
				codeStr += '</li>';
			}
		}
		return menuStr += codeStr;

	}
};

/* (非-Javadoc)
 * <p>名称: IsChild</p>
 * <p>说明: 验证一个节点是否存在子项</p>
 * @param id 当前节点id
 * @param zNodes 所要加载的多级菜单的内容json
 * @return true表示存在子项，false表示不存在子项
 */
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



/*//递归获取某节点所有下级节点
function getLowerNode(sortid, zNodes){
	var LowerNode=[];
	for ( var i = 0; i < zNodes.length; i++) {
		if (sortid == zNodes[i].pid) {
			LowerNode[LowerNode.length] = zNodes[i];
		}
	}
	var pid=null;
	function getuppernode(departmentid){
		if(pid==0){
			return;
		}
		for(var i=0;i<departmentlist.length;i++){
			if(departmentlist[i].id==departmentid){
				pid=departmentlist[i].pid;
				//若已到根节点则无需添加
				if(pid==0){
					break;
				}
				UpperNode[UpperNode.length]=pid;
			}
		}
		getuppernode(pid);
	}

}*/

//试题库修改中试题库查询获取所有叶子节点
/*function getleafnode(sortid, zNodes,if_rootnode,if_under_defaultsort) {
	var treeid=null;
	var sort_node_path=null;
	if(if_under_defaultsort=='1'){
		if(if_rootnode=='1'){
			//取得该节点下所有节点
			var treearray = new Array();
			for ( var i = 0; i < zNodes.length; i++) {
				if (sortid == zNodes[i].tree_id) {
					treearray[treearray.length] = zNodes[i];
				}
			}
			//获取叶子节点nodepath
			var nodepath_length=''; 
			for ( var i = 0; i < zNodes.length; i++) {
				if(nodepath_length<zNodes[i].node_path){
					nodepath_length=zNodes[i].node_path;
				}
			}
			//查找所有叶子节点
			var leafnode = new Array();
			for ( var i = 0; i < treearray.length; i++) {
				var nodepath='';
				nodepath=treearray[i].node_path;
				if(nodepath_length.length==nodepath.length){
					leafnode[leafnode.length]=treearray[i].id;
				}
			}
			return(leafnode);
			
		}else{
			//取得该树下下所有节点
			var treearray = new Array();
			for ( var i = 0; i < zNodes.length; i++) {
				if (zNodes[i].tree_id=="jibenfenleitixi") {
					treearray[treearray.length] = zNodes[i];
				}
			}
			//获取该节点所有下级节点
			var LowerNodeArray = new Array();
			for ( var i = 0; i < treearray.length; i++) {
				var node_path=treearray[i].node_path;
				if(node_path>=sort_node_path&&node_path.indexOf(sort_node_path)>=0){
					LowerNodeArray[LowerNodeArray.length]=treearray[i];
				}
				
			}
			//获取叶子节点nodepath
			var nodepath_length=''; 
			for ( var i = 0; i < LowerNodeArray.length; i++) {
				if(nodepath_length<LowerNodeArray[i].node_path){
					nodepath_length=LowerNodeArray[i].node_path;
				}
			}
			//alert(nodepath_length.length);
			//查找所有叶子节点
			var leafnode = new Array();
			for ( var i = 0; i < LowerNodeArray.length; i++) {
				var nodepath='';
				nodepath=LowerNodeArray[i].node_path;
				if(nodepath_length.length==nodepath.length){
					leafnode[leafnode.length]=LowerNodeArray[i].id;
				}
			}
			return(leafnode);
		}
	}
	if(if_rootnode=='0'){   //非根节点
		//获取tree_id
		for ( var i = 0; i < zNodes.length; i++) {
			if (sortid == zNodes[i].id) {
				treeid=zNodes[i].tree_id;
				sort_node_path=zNodes[i].node_path;
			}
		}
		//取得该树下下所有节点
		var treearray = new Array();
		for ( var i = 0; i < zNodes.length; i++) {
			if (treeid == zNodes[i].tree_id) {
				treearray[treearray.length] = zNodes[i];
			}
		}
		//获取该节点所有下级节点
		var LowerNodeArray = new Array();
		for ( var i = 0; i < treearray.length; i++) {
			var node_path=treearray[i].node_path;
			if(node_path>=sort_node_path&&node_path.indexOf(sort_node_path)>=0){
				LowerNodeArray[LowerNodeArray.length]=treearray[i];
			}
			
		}
		//获取叶子节点nodepath
		var nodepath_length=''; 
		for ( var i = 0; i < LowerNodeArray.length; i++) {
			if(nodepath_length<LowerNodeArray[i].node_path){
				nodepath_length=LowerNodeArray[i].node_path;
			}
		}
		//alert(nodepath_length.length);
		//查找所有叶子节点
		var leafnode = new Array();
		for ( var i = 0; i < LowerNodeArray.length; i++) {
			var nodepath='';
			nodepath=LowerNodeArray[i].node_path;
			if(nodepath_length.length==nodepath.length){
				leafnode[leafnode.length]=LowerNodeArray[i].id;
			}
		}
		return(leafnode);
	}else{
		//取得该节点下所有节点
		var treearray = new Array();
		for ( var i = 0; i < zNodes.length; i++) {
			if (sortid == zNodes[i].tree_id) {
				treearray[treearray.length] = zNodes[i];
			}
		}
		//获取叶子节点nodepath
		var nodepath_length=''; 
		for ( var i = 0; i < zNodes.length; i++) {
			if(nodepath_length<zNodes[i].node_path){
				nodepath_length=zNodes[i].node_path;
			}
		}
		//查找所有叶子节点
		var leafnode = new Array();
		for ( var i = 0; i < treearray.length; i++) {
			var nodepath='';
			nodepath=treearray[i].node_path;
			if(nodepath_length.length==nodepath.length){
				leafnode[leafnode.length]=treearray[i].id;
			}
		}
		return(leafnode);
	}
}*/



/*function getleafnode(sortid, zNodes,if_rootnode) {
	//获取treeid
	if(if_rootnode=='0'){
		
	}
	var treeid=null;
	var tree_nodepath=null;
	for ( var i = 0; i < zNodes.length; i++) {
		if (sortid == zNodes[i].id) {
			treeid=zNodes[i].tree_id;
			tree_nodepath=zNodes[i].node_path;
		}
	}
	//取得该节点下所有节点
	var treearray = new Array();
	for ( var i = 0; i < zNodes.length; i++) {
		if (treeid == zNodes[i].tree_id) {
			treearray[treearray.length] = zNodes[i];
		}
	}
	//获取叶子节点nodepath
	var nodepath_length=''; 
	for ( var i = 0; i < zNodes.length; i++) {
		if(nodepath_length<zNodes[i].node_path){
			nodepath_length=zNodes[i].node_path;
		}
	}
	//alert(nodepath_length.length);
	//查找所有叶子节点
	var leafnode = new Array();
	for ( var i = 0; i < treearray.length; i++) {
		var nodepath='';
		nodepath=treearray[i].node_path;
		//	var aa=treearray[i].node_path;
//		alert(aa);
		if(nodepath_length.length==nodepath.length){
			leafnode[leafnode.length]=treearray[i].id;
		}
	}
	return(leafnode);
}


*/


function getleafnode(sortid, zNodes,if_rootnode) {
	//alert(zNodes[0].node_path);
	var treeid=null;
	var sort_node_path=null;
	if(if_rootnode=='0'){   //非根节点
		//获取tree_id
		for ( var i = 0; i < zNodes.length; i++) {
			if (sortid == zNodes[i].id) {
				treeid=zNodes[i].tree_id;
				sort_node_path=zNodes[i].node_path;
			}
		}
		//取得该树下下所有节点
		var treearray = new Array();
		for ( var i = 0; i < zNodes.length; i++) {
			if (treeid == zNodes[i].tree_id) {
				treearray[treearray.length] = zNodes[i];
			}
		}
		//alert("所有节点为：  "+treearray);
		//获取该节点所有下级节点
		var LowerNodeArray = new Array();
		for ( var i = 0; i < treearray.length; i++) {
			var node_path=treearray[i].node_path;
			if(node_path>=sort_node_path&&node_path.indexOf(sort_node_path)>=0){
				LowerNodeArray[LowerNodeArray.length]=treearray[i];
			}
			
		}
		//alert("所有下级节点为：  "+LowerNodeArray);
		//获取叶子节点
		var leafNodeArray=new Array();
		for ( var i = 0; i < LowerNodeArray.length; i++) {
			var nodeId=LowerNodeArray[i].id;
			var tempArray=new Array();
			for(var j=0;j<LowerNodeArray.length;j++){
				var nodePid=LowerNodeArray[j].pid;
				if(nodeId==nodePid){
					tempArray[tempArray.length]=LowerNodeArray[j];
				}
			}
			if(tempArray.length==0){
				leafNodeArray[leafNodeArray.length]=nodeId;
			}
			
		}
	
		//alert("叶子节点列表为：   "+leafNodeArray);
		return(leafNodeArray);
	}else{
		//取得该节点下所有节点
		var treearray = new Array();
		for ( var i = 0; i < zNodes.length; i++) {
			if (sortid == zNodes[i].tree_id) {
				treearray[treearray.length] = zNodes[i];
			}
		}
	//	alert("所有节点列表为：  "+treearray);
		//获取叶子节点
		var leafNodeArray=new Array();
		for ( var i = 0; i < treearray.length; i++) {
			var nodeId=treearray[i].id;
			var tempArray=new Array();
			for(var j=0;j<treearray.length;j++){
				var nodePid=treearray[j].pid;
				if(nodeId==nodePid){
					tempArray[tempArray.length]=treearray[j];
				}
			}
			if(tempArray.length==0){
				leafNodeArray[leafNodeArray.length]=nodeId;
			}
			
		}
		//alert("leafnode为："+leafNodeArray);
		return(leafNodeArray);
	}
}