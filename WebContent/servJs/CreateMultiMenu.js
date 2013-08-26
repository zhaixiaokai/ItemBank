/* (��-Javadoc)
 * <p>����: loadMenu</p>
 * <p>˵��: ���ض༶�˵�</p>
 * @param target ���ض༶�˵���Ŀ��div��id
 * @param zNodes ��Ҫ���صĶ༶�˵�������json
 * @param func ���Ҷ�ӽڵ㴥�����¼�
 * @param KeyId ���ڵ��Id
 * @return
 */

//ֻ��ѡ��Ҷ�ӽڵ�
function loadMenu(target, zNodes, func, KeyId) {
	var menuStr = '';
	menuStr = CreateMenuContent(zNodes, 0, menuStr, zNodes[0].pid, KeyId);
	if (menuStr != '') {
		menuStr = '<ul id="nav">' + menuStr + '</ul>';
		//alert(menuStr);
		document.getElementById(target).innerHTML = menuStr;
	}
	//ֻ��Ҷ�ӽڵ���ӵ���¼�
	for ( var i = 0; i < zNodes.length; i++) {
		if (IsChild(zNodes[i].id, zNodes)) {
			continue;
		}
		document.getElementById(zNodes[i].id).onclick = func;
	}
	//alert(menuStr);
}

//�ڵ����ѡ��
function loadMenu1(target, zNodes, func, KeyId) {
	var menuStr = '';
	menuStr = CreateMenuContent(zNodes, 0, menuStr, 0, KeyId);
	//alert(menuStr);
	if (menuStr != '') {
		menuStr = '<ul id="nav">' + menuStr + '</ul>';
		//alert(menuStr);
		document.getElementById(target).innerHTML = menuStr;
	}
	//ֻ��Ҷ�ӽڵ���ӵ���¼�

	for ( var i = 0; i < zNodes.length; i++) {
		document.getElementById(zNodes[i].id).onclick = func;
		//	alert("here");
	}
}
/* (��-Javadoc)
 * <p>����: CreateMenuContent</p>
 * <p>˵��: �ݹ����ɶ༶�˵�</p>
 * @param zNodes ��Ҫ���صĶ༶�˵�������json
 * @param num ��ǰ���ص��Ľڵ���zNodes�е�λ��
 * @param menuStr �ݹ����ɵ�innerHTML����
 * @param pid �ϼ��ڵ��id
 * @param KeyId ���ڵ��Id
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
				if (pid == 0) { //�ж��Ƿ�Ϊ���ڵ�
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

				if (IsChild(zNodes[i].id, zNodes)) { //�ж��Ƿ�ΪҶ�ӽڵ�
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

/* (��-Javadoc)
 * <p>����: IsChild</p>
 * <p>˵��: ��֤һ���ڵ��Ƿ��������</p>
 * @param id ��ǰ�ڵ�id
 * @param zNodes ��Ҫ���صĶ༶�˵�������json
 * @return true��ʾ�������false��ʾ����������
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



/*//�ݹ��ȡĳ�ڵ������¼��ڵ�
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
				//���ѵ����ڵ����������
				if(pid==0){
					break;
				}
				UpperNode[UpperNode.length]=pid;
			}
		}
		getuppernode(pid);
	}

}*/

//������޸���������ѯ��ȡ����Ҷ�ӽڵ�
/*function getleafnode(sortid, zNodes,if_rootnode,if_under_defaultsort) {
	var treeid=null;
	var sort_node_path=null;
	if(if_under_defaultsort=='1'){
		if(if_rootnode=='1'){
			//ȡ�øýڵ������нڵ�
			var treearray = new Array();
			for ( var i = 0; i < zNodes.length; i++) {
				if (sortid == zNodes[i].tree_id) {
					treearray[treearray.length] = zNodes[i];
				}
			}
			//��ȡҶ�ӽڵ�nodepath
			var nodepath_length=''; 
			for ( var i = 0; i < zNodes.length; i++) {
				if(nodepath_length<zNodes[i].node_path){
					nodepath_length=zNodes[i].node_path;
				}
			}
			//��������Ҷ�ӽڵ�
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
			//ȡ�ø����������нڵ�
			var treearray = new Array();
			for ( var i = 0; i < zNodes.length; i++) {
				if (zNodes[i].tree_id=="jibenfenleitixi") {
					treearray[treearray.length] = zNodes[i];
				}
			}
			//��ȡ�ýڵ������¼��ڵ�
			var LowerNodeArray = new Array();
			for ( var i = 0; i < treearray.length; i++) {
				var node_path=treearray[i].node_path;
				if(node_path>=sort_node_path&&node_path.indexOf(sort_node_path)>=0){
					LowerNodeArray[LowerNodeArray.length]=treearray[i];
				}
				
			}
			//��ȡҶ�ӽڵ�nodepath
			var nodepath_length=''; 
			for ( var i = 0; i < LowerNodeArray.length; i++) {
				if(nodepath_length<LowerNodeArray[i].node_path){
					nodepath_length=LowerNodeArray[i].node_path;
				}
			}
			//alert(nodepath_length.length);
			//��������Ҷ�ӽڵ�
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
	if(if_rootnode=='0'){   //�Ǹ��ڵ�
		//��ȡtree_id
		for ( var i = 0; i < zNodes.length; i++) {
			if (sortid == zNodes[i].id) {
				treeid=zNodes[i].tree_id;
				sort_node_path=zNodes[i].node_path;
			}
		}
		//ȡ�ø����������нڵ�
		var treearray = new Array();
		for ( var i = 0; i < zNodes.length; i++) {
			if (treeid == zNodes[i].tree_id) {
				treearray[treearray.length] = zNodes[i];
			}
		}
		//��ȡ�ýڵ������¼��ڵ�
		var LowerNodeArray = new Array();
		for ( var i = 0; i < treearray.length; i++) {
			var node_path=treearray[i].node_path;
			if(node_path>=sort_node_path&&node_path.indexOf(sort_node_path)>=0){
				LowerNodeArray[LowerNodeArray.length]=treearray[i];
			}
			
		}
		//��ȡҶ�ӽڵ�nodepath
		var nodepath_length=''; 
		for ( var i = 0; i < LowerNodeArray.length; i++) {
			if(nodepath_length<LowerNodeArray[i].node_path){
				nodepath_length=LowerNodeArray[i].node_path;
			}
		}
		//alert(nodepath_length.length);
		//��������Ҷ�ӽڵ�
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
		//ȡ�øýڵ������нڵ�
		var treearray = new Array();
		for ( var i = 0; i < zNodes.length; i++) {
			if (sortid == zNodes[i].tree_id) {
				treearray[treearray.length] = zNodes[i];
			}
		}
		//��ȡҶ�ӽڵ�nodepath
		var nodepath_length=''; 
		for ( var i = 0; i < zNodes.length; i++) {
			if(nodepath_length<zNodes[i].node_path){
				nodepath_length=zNodes[i].node_path;
			}
		}
		//��������Ҷ�ӽڵ�
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
	//��ȡtreeid
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
	//ȡ�øýڵ������нڵ�
	var treearray = new Array();
	for ( var i = 0; i < zNodes.length; i++) {
		if (treeid == zNodes[i].tree_id) {
			treearray[treearray.length] = zNodes[i];
		}
	}
	//��ȡҶ�ӽڵ�nodepath
	var nodepath_length=''; 
	for ( var i = 0; i < zNodes.length; i++) {
		if(nodepath_length<zNodes[i].node_path){
			nodepath_length=zNodes[i].node_path;
		}
	}
	//alert(nodepath_length.length);
	//��������Ҷ�ӽڵ�
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
	if(if_rootnode=='0'){   //�Ǹ��ڵ�
		//��ȡtree_id
		for ( var i = 0; i < zNodes.length; i++) {
			if (sortid == zNodes[i].id) {
				treeid=zNodes[i].tree_id;
				sort_node_path=zNodes[i].node_path;
			}
		}
		//ȡ�ø����������нڵ�
		var treearray = new Array();
		for ( var i = 0; i < zNodes.length; i++) {
			if (treeid == zNodes[i].tree_id) {
				treearray[treearray.length] = zNodes[i];
			}
		}
		//alert("���нڵ�Ϊ��  "+treearray);
		//��ȡ�ýڵ������¼��ڵ�
		var LowerNodeArray = new Array();
		for ( var i = 0; i < treearray.length; i++) {
			var node_path=treearray[i].node_path;
			if(node_path>=sort_node_path&&node_path.indexOf(sort_node_path)>=0){
				LowerNodeArray[LowerNodeArray.length]=treearray[i];
			}
			
		}
		//alert("�����¼��ڵ�Ϊ��  "+LowerNodeArray);
		//��ȡҶ�ӽڵ�
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
	
		//alert("Ҷ�ӽڵ��б�Ϊ��   "+leafNodeArray);
		return(leafNodeArray);
	}else{
		//ȡ�øýڵ������нڵ�
		var treearray = new Array();
		for ( var i = 0; i < zNodes.length; i++) {
			if (sortid == zNodes[i].tree_id) {
				treearray[treearray.length] = zNodes[i];
			}
		}
	//	alert("���нڵ��б�Ϊ��  "+treearray);
		//��ȡҶ�ӽڵ�
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
		//alert("leafnodeΪ��"+leafNodeArray);
		return(leafNodeArray);
	}
}