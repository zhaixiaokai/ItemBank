<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*,net.ib.util.service.*,net.ib.util.service.impl.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>自动组卷配置选择题</title>
<%
	String	_curse	=	request.getParameter("curse");
	if(_curse==null)	_curse	=	"testCurse";
	String	sql	=	"select * from sys_teaching_material where curse_id='"+_curse+"'";
	
	Dao	dao	=	new	DaoImpl();
	List<?> list	=	dao.executeQuery(sql);
	System.err.println(list);
	Service	si	=	new ServiceImpl();
	String	json	=	si.DataListToJson((List<Map>)list);
	//System.err.println(json);
%>
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link rel="stylesheet" type="text/css" href="../css/select.css">
<link href="../css/infoedit.css" rel="stylesheet" type="text/css" />
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />

<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
    <script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript">
	
	var	chapter1InnerHTML	=	"";
	var	knowledge1InnerHTML	=	"";

	function copyToList(from, to) {
		fromList = eval('document.forms[0].' + from);
		toList = eval('document.forms[0].' + to);
		if (toList.options.length > 0 && toList.options[0].value == 'temp') {
			toList.options.length = 0;
		}
		var sel = false;
		for (i = 0; i < fromList.options.length; i++) {
			var current = fromList.options[i];
			if (current.selected) {
				sel = true;
				if (current.value == 'temp') {
					alert('你不能选择这个项目!');
					return;
				}
				txt = current.text;
				val = current.value;
				toList.options[toList.length] = new Option(txt, val);
				fromList.options[i] = null;
				i--;
			}
		}
		if (!sel)
			alert('你还没有选择任何项目');
	}
	function allSelect() {
		List = document.forms[0].chosen;
		if (List.length && List.options[0].value == 'temp')
			return;
		for (i = 0; i < List.length; i++) {
			List.options[i].selected = true;
		}

	}
	/*
	 *
	 */
	function copyToListadd(from, to) {
		fromList = eval('document.forms[0].' + from);
		toList = eval('document.forms[0].' + to);
		if (toList.options.length > 0 && toList.options[0].value == 'temp') {
			toList.options.length = 0;
		}
		var sel = false;
		for (i = 0; i < fromList.options.length; i++) {
			var current = fromList.options[i];
			if (current.selected) {
				sel = true;
				if (current.value == 'temp') {
					alert('你不能选择这个项目!');
					return;
				}
				var number = form1.number.value;
				var difficulty = form1.difficulty.value;
				txt = "个数：" + number + " | 难度：" + difficulty + " |"
						+ current.text;
				val = current.value;
				toList.options[toList.length] = new Option(txt, val);
				fromList.options[i] = null;
				i--;
			}
		}
		if (!sel)
			alert('你还没有选择任何项目');
	}
	function allSelect() {
		List = document.forms[0].chosen;
		if (List.length && List.options[0].value == 'temp')
			return;
		for (i = 0; i < List.length; i++) {
			List.options[i].selected = true;
		}

	}
	/*
	 * 
	 */
	function copyToListdel(from, to) {
		fromList = eval('document.forms[0].' + from);
		toList = eval('document.forms[0].' + to);
		if (toList.options.length > 0 && toList.options[0].value == 'temp') {
			toList.options.length = 0;
		}
		var sel = false;
		for (i = 0; i < fromList.options.length; i++) {
			var current = fromList.options[i];
			if (current.selected) {
				sel = true;
				if (current.value == 'temp') {
					alert('你不能选择这个项目!');
					return;
				}
				var indexOfLastDiv = current.text.lastIndexOf("|");
				var length = current.text.length;
				txt = current.text.substr(indexOfLastDiv + 1, length
						- indexOfLastDiv);
				val = current.value;
				toList.options[toList.length] = new Option(txt, val);
				fromList.options[i] = null;
				i--;
			}
		}
		if (!sel)
			alert('你还没有选择任何项目');
	}
	function allSelect() {
		List = document.forms[0].chosen;
		if (List.length && List.options[0].value == 'temp')
			return;
		for (i = 0; i < List.length; i++) {
			List.options[i].selected = true;
		}

	}
	/* 
	 * 点击列表中的选项获取选项的值以及文字
	 */
	function over(id) {
		var select = document.getElementById(id);
		var vA = select.options[select.selectedIndex].value;
		var vB = select.options[select.selectedIndex].text;
		prompt();
	}
	
	//通过教材异步加载章节信息，章节信息只显示最后一级的章节目录
	function getChapter(){
		var	MaterialList	=	document.getElementById("course2").options;
		if(MaterialList.length==0){
			document.getElementById("chapter1").innerHTML="";
		}
		chapter1InnerHTML	=	"";
		for(var	i=0;i<MaterialList.length;i++){
    		var	re	=	$.ajax( {
    			type : "post",
    			dataType : "json",
    			async : false,
    			url : "ChapterSelectByMaterialAction?teachingMaterial="+MaterialList[i].value,
				success : function(result) {
					for(var	i=0;i<result.length;i++){
						getChapterLeaf(result[i]);
					}
    			},
    			error:function(){
    			}
    		});
		}
		document.getElementById("chapter1").innerHTML=chapter1InnerHTML;
	}
	//递归获取章节的最后一级并且拼接成为select的innerHTML内容
	function getChapterLeaf(obj){
		if(obj.children!=null){
			for(var i=0;i<obj.children.length;i++){
				getChapterLeaf(obj.children[i]);
			}
		}
		else{
			chapter1InnerHTML+="<option value=\""+obj.id+"\">"+obj.text+"</option>";
		}
	}
	//通过chapter2中包含的章节，在知识点左侧栏目中显示这些章节包含的知识点列表
	function getKnowledge(){
		var	chapterList	=	document.getElementById("chapter2").options;	
		if(chapterList.length==0){
			document.getElementById("knowledge1").innerHTML="";
		}
		knowledge1InnerHTML="";
		for(var	i=0;i<chapterList.length;i++){
    		var	re	=	$.ajax( {
    			type : "post",
    			dataType : "json",
    			async : false,
    			url : "KnowledgeSelectByCurseAction?materialChapter="+chapterList[i].value,
				success : function(result) {
					for(var	i=0;i<result.data.length;i++){
						knowledge1InnerHTML+="<option value=\""+result.data[i].id+"\">"+result.data[i].name+"</option>"
					}
    			},
    			error:function(){
    			}
    		});
		}
		document.getElementById("knowledge1").innerHTML	=	knowledge1InnerHTML;
	}
	//清除id为o的标签内部所有innerHTML
	function	cleanSelect(o){
		document.getElementById(o).innerHTML	=	"";
	}
	//点击“>>”或者“<<”时触发的事件
	function	TeachingMaterialLeft(){
		copyToList('course2','course1');
 		cleanSelect("chapter1");
 		cleanSelect("chapter2");
		cleanSelect("knowledge1");
		cleanSelect("knowledge2");
		getChapter();
	}
	function	TeachingMaterialRight(){
		copyToList('course1','course2');
		getChapter();
	}
	
	function	ChapterLeft(){
		copyToList('chapter2','chapter1');
		cleanSelect("knowledge1");
		cleanSelect("knowledge2");
		getKnowledge();
	}
	function	ChapterRight(){
		copyToList('chapter1','chapter2');
		getKnowledge();
	}
	function	knowledgeLeft(){
		copyToListdel('knowledge2','knowledge1');
	}
	function	knowledgeRight(){
		copyToListadd('knowledge1','knowledge2');
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<div id="act_top">
		<a href="../functionList/appManage.jsp">应用管理</a>&nbsp;&gt;&gt;&nbsp;组卷管理&nbsp;&gt;&gt;&nbsp;按章节自动组卷
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>

	<div id="act_content2">
		<form name="form1" method="post" action="">
			<table align="center">
				<tr>
					<td>
						<table>
							<tr>
								<td>选择教材：</td>
							</tr>
							<tr>
								<td align="center"><select name="course1" size="10"
									multiple id="course1" style="width: 140px;">
									
								</select></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td><a href="javascript:void(0)" name="curseleft" id="curseleft"
									onClick="TeachingMaterialLeft();"><<</a></td>
							</tr>
							<tr>
								<td><a href="javascript:void(0)" name="curseright" id="curseright"
									onClick="TeachingMaterialRight();">&gt;&gt;</a></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td>已选择：</td>
							</tr>
							<tr>
								<td align="center" bgcolor="#FFFFFF"><select name="course2"
									size="10" multiple id="course2" style="width: 140px">
								</select></td>
							</tr>
						</table>
					</td>


					<td>
						<table style="margin-left: 40px">
							<tr>
								<td>选择章节：</td>
							</tr>
							<tr>
								<td align="center"><select name="chapter1" size="10"
									multiple id="chapter1" style="width: 140px;">
								</select></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td>
								<a href="javascript:void(0)" name="chapterleft" id="chapterleft" onClick="ChapterLeft()"><<</a></td>
							</tr>
							<tr>
								<td><a href="javascript:void(0)" name="chapterright" id="chapterright" onClick="ChapterRight()">&gt;&gt;</a></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td>已选择：</td>
							</tr>
							<tr>
								<td align="center" bgcolor="#FFFFFF"><select
									name="chapter2" size="10" multiple id="chapter2"
									style="width: 140px">
								</select></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table width="100%">

				<tr>
					<td>
						<table>
							<tr>
								<td>选择知识点：</td>
							</tr>
							<tr>
								<td align="center"><select name="knowledge1" size="10"
									multiple id="knowledge1" style="width: 300px;">
								</select></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td>题数:</td>
							</tr>
							<tr>
								<td><input name="number" type="text" size="1" value="1"></input>
								</td>
							</tr>
							<tr>
								<td>难度:</td>
							</tr>
							<tr>
								<td width="100px"><select name="difficulty">
										<option>简单</option>
										<option>中等</option>
										<option>困难</option>
								</select></td>
							</tr>
							<tr align="center">
								<td><a href="javascript:void(0)" name="knowledgeleft" id="knowledgeleft" onClick="knowledgeLeft();"><<</a></td>
							</tr>
							<tr align="center">
								<td><a href="javascript:void(0)" name="knowledgeright" id="knowledgeright" onClick="knowledgeRight();">&gt;&gt;</a></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td>已选择：</td>
							</tr>
							<tr>
								<td align="center" bgcolor="#FFFFFF"><select
									name="knowledge2" size="10" multiple id="knowledge2"
									style="width: 330px">
								</select></td>
							</tr>
						</table>
					</td>
				</tr>

			</table>
			<table align="center" style="margin-top: 40px">
				<tr>
					<td><input type="button" value="保存" class="button" onclick="window.history.go(-1);"></input> <input
						type="button" value="重置" class="button" onclick="window.history.go(0);"></input></td>
				</tr>
			</table>

		</form>
	</div>


</body>

	<script type="text/javascript">
		var	json	=	<%=json%>;
		var	InnerHtml="";
		for(var	i=0;i<json.data.length;i++){
			//document.getElementById("course1_"+i).innerHTML=json.data[0].author;
			InnerHtml	+=	"<option value=\""+json.data[i].teaching_material_id+"\">"+json.data[i].name+"</option>";
		}
		document.getElementById("course1").innerHTML=InnerHtml;
	</script>
</html>