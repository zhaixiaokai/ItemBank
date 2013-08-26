/*
 * Author	:	xiaoai
 * Date		:	20130119
 * Function	:	根据Json生成多级嵌套表格
 * 
 */
var	createFlag=false;
/**
 * 
 * <p>名称：CreateTableByNode</p>
 * <p>说明：通过node信息生成表格</p>
 * <p>参数：@para	node	当前生成表格对应的node</p>
 * <p>返回值：String 返回类型	返回拼接而成的字符串</p>
 * <p>@return</p>
 */
function CreateTableByNode(node){
	var	TableHTML="";
	//如果不是叶子节点生成类似下面的表格(1*2)
	//<table>
	//	<tr>
	//		<td></td>
	//		<td></td>
	//	</tr>
	//<table>
	if(node.leaf=="false"){
		TableHTML	=	"<table border=\"1px\">";
		TableHTML	+=	"<tr>";
		TableHTML	+=	"<td width='150px'>";
		TableHTML	+=	"<input type=\"checkbox\" id=\"CB_"+node.id+"\" name=\"CheckBoxes\"/>";
		TableHTML	+=	node.label;
		TableHTML	+=	"</td>";
		TableHTML	+=	"<td id=\"TD_"+node.id+"\">";
		TableHTML	+=	"</td>";
		TableHTML	+=	"</tr>";
		TableHTML	+=	"</table>";
	}
	//如果是叶子节点，生成类似下面的表格(1*1)
	//<table>
	//	<tr>
	//		<td></td>
	//	</tr>
	//<table>
	else{
		TableHTML	+="<table>";
		TableHTML	+=	"<tr>";
		TableHTML	+=	"<td width='150px'>";
		TableHTML	+=	"<input type=\"checkbox\" id=\"CB_"+node.id+"\" name=\"CheckBoxes\"/>";
		TableHTML	+=	node.label+"<br>";
		TableHTML	+=	"</td>";		
		TableHTML	+=	"</tr>";
		TableHTML	+=	"</table>";
	}
	return TableHTML;
}
/**
 * 
 * <p>名称：DispTable</p>
 * <p>说明：为指定ID的元素标签添加innerHTML</p>
 * <p>参数：@para	obj	元素ID</p>
 * <p>参数：@para	str	要添加到元素内的字符串
 * <p>@return</p>
 */
function DispTable(obj,str){
	$("#"+obj).html($("#"+obj).html()+str);
}

/**
 * 
 * <p>名称：TraversalJsonAndCreateTable</p>
 * <p>说明：递归遍历Json并且生成嵌套表格</p>
 * <p>参数：@para	json	</p>
 * <p>参数：@para	o		要添加到元素内的字符串
 * <p>@return</p>
 */
function TraversalJsonAndCreateTable(json,o) {
	for ( var j = 0; j < json.length; j++) {
		if(!createFlag){
			//alert(o+"_"+j);
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
	//alert(InnerHTML);
	DispTable(o,InnerHTML);//innerHtml存储初始化表格的内容
}
/**
 * 
 * <p>名称：CreateMultiTableInterface</p>
 * <p>说明：用户调用接口，包括生成嵌套表格，对嵌套表格中的元素绑定事件</p>
 * <p>参数：@para	json 生成嵌套表格要使用的json</p>
 * <p>参数：@para	o	 生成嵌套表格渲染的元素ID
 * <p>参数：@para	fun	 CheckBox点击事件绑定的Function
 * <p>@return</p>
 */
function CreateMultiTableInterface(json,objId,fun){
	TraversalJsonAndCreateTable(json,objId);
	var	CheckBoxObjs	=	$("input[type='checkbox'][name='CheckBoxes']");
	for(var	i=0;i<CheckBoxObjs.length;i++){
		CheckBoxObjs[i].onclick=fun;
	}
}
//点击某一个checkbox触发的事件,点击父节点以后相应的子节点全部选中
/*function CheckBoxSelectEvent(Json){
	alert(Json);*/
	/*var i=0;
	for(i=0;i<Json.length;i++){
		var CheckBoxId="CB_"+Json[i].id;
		var CheckBoxObj=$("#this.id");
		
		if(CheckBoxId==this.id){
			if(Json[i].leaf=="false"){
				CheckBoxObj.checked=true;
				return
			}
			else{
				while(Json[i].children!=undefined){
					var ChildrenObjId="CB_"+Json[i].children.id;
					var ChildrenObj=$("#ChildrenObjId");
					ChildrenObj.checked=CheckBoxObj.checked;
					i++;
				}
			}
			break
		
		}
		
	}*/
	
	






