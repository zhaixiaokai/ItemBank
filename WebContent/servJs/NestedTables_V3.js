/*
 * Author	:	xiaoai
 * Date		:	20130119
 * Function	:	����Json���ɶ༶Ƕ�ױ��
 * 
 */
var	createFlag=false;
/**
 * 
 * <p>���ƣ�CreateTableByNode</p>
 * <p>˵����ͨ��node��Ϣ���ɱ��</p>
 * <p>������@para	node	��ǰ���ɱ���Ӧ��node</p>
 * <p>����ֵ��String ��������	����ƴ�Ӷ��ɵ��ַ���</p>
 * <p>@return</p>
 */
function CreateTableByNode(node){
	var	TableHTML="";
	//�������Ҷ�ӽڵ�������������ı��(1*2)
	//<table>
	//	<tr>
	//		<td></td>
	//		<td></td>
	//	</tr>
	//<table>
	if(node.leaf=="false"){
		TableHTML	=	"<table border=\"1px\">";
		TableHTML	+=	"<tr>";
		TableHTML	+=	"<td width='80px'>";
		TableHTML	+=	node.label;
		TableHTML	+=	"</td>";
		TableHTML	+=	"<td id=\"TD_"+node.id+"\">";
		TableHTML	+=	"</td>";
		TableHTML	+=	"</tr>";
		TableHTML	+=	"</table>";
	}
	//�����Ҷ�ӽڵ㣬������������ı��(1*1)
	//<table>
	//	<tr>
	//		<td></td>
	//	</tr>
	//<table>
	else{
		TableHTML	+="<table>";
		TableHTML	+=	"<tr>";
		TableHTML	+=	"<td width='80px'>";
		/*TableHTML	+=	"<input type=\"checkbox\" id=\"CB_"+node.id+"\" name=\"CheckBoxes\"/>";*/
		TableHTML	+=	node.label+"<br>";
		TableHTML	+=	"</td>";	
		TableHTML	+=	"<td width=\"200\" id=\"TD_"+node.id+"\">"+"";
		TableHTML	+=	"</td>";
		//TableHTML	+=	"<td width=\"80\">"+"<a href=\"#\" id=\"GL_"+node.id+"\">"+"����"+"</a>";
		//TableHTML	+=	"<td width=\"80\">"+"������<input id='IP_'"+node.id+" type='text' class='count' size='2'/>";
		TableHTML	+=	"</tr>";
		TableHTML	+=	"</table>";
	}
	return TableHTML;
}
/**
 * 
 * <p>���ƣ�DispTable</p>
 * <p>˵����Ϊָ��ID��Ԫ�ر�ǩ���innerHTML</p>
 * <p>������@para	obj	Ԫ��ID</p>
 * <p>������@para	str	Ҫ��ӵ�Ԫ���ڵ��ַ���
 * <p>@return</p>
 */
function DispTable(obj,str){
	$("#"+obj).html($("#"+obj).html()+str);
}

/**
 * 
 * <p>���ƣ�TraversalJsonAndCreateTable</p>
 * <p>˵�����ݹ����Json��������Ƕ�ױ��</p>
 * <p>������@para	json	</p>
 * <p>������@para	o		Ҫ��ӵ�Ԫ���ڵ��ַ���
 * <p>@return</p>
 */
function TraversalJsonAndCreateTable(json,o) {
	
	for ( var j = 0; j < json.length; j++) {
		if(!createFlag){
			DispTable(o+"_"+j,CreateTableByNode(json[j]));
			createFlag=true;
		}
		else{
			DispTable("TD_"+json[j].pid,CreateTableByNode(json[j]));
		}
		if (json[j].children != undefined) {
			TraversalJsonAndCreateTable(json[j].children);
		}
	}
}

function initTableContainer(Json,o){
	var	length	=	Json.length;
	var	InnerHTML	=	"<table>";
	for(var	i=0;i<length;i++){
		InnerHTML	+=	"<tr><td id=\""+(o+"_"+i)+"\"></td></tr>";
	}
	InnerHTML	+=	"</table>";
	DispTable(o,InnerHTML);//innerHtml�洢��ʼ����������
}
/**
 * 
 * <p>���ƣ�CreateMultiTableInterface</p>
 * <p>˵�����û����ýӿڣ���������Ƕ�ױ�񣬶�Ƕ�ױ���е�Ԫ�ذ��¼�</p>
 * <p>������@para	json ����Ƕ�ױ��Ҫʹ�õ�json</p>
 * <p>������@para	o	 ����Ƕ�ױ����Ⱦ��Ԫ��ID
 * <p>������@para	fun	 CheckBox����¼��󶨵�Function
 * <p>@return</p>
 */
function CreateMultiTableInterface(json,objId,fun){
	TraversalJsonAndCreateTable(json,objId);
	var	CheckBoxObjs	=	$("input[type='checkbox'][name='CheckBoxes']");
	for(var	i=0;i<CheckBoxObjs.length;i++){
		CheckBoxObjs[i].onclick=fun;
	}
}

	
	






