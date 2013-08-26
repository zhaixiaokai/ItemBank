<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css" href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link rel="stylesheet" type="text/css" href="../css/select.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet" type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script>
	function showTable() {
		if(!QueryAndCreateItembankList()){
			return;
		}
		document.getElementById("div").style.visibility = "visible";
		document.getElementById("SelectType").style.visibility = "visible";
	}
	function addQ() {
		var radio = $('input[name="radio"]').filter(':checked');
		if(!radio.length){
			alert("请选择目标试题库");
			return ;
		}
		if(""==$("#SelectQuesType").val()){
			alert("请选择题型");
			return;
		}

		//获取课程id
		var	options	=	document.getElementById("SelectCurse").options;
		var	index	=	document.getElementById("SelectCurse").selectedIndex;
		var	selectedOption	=	options[index];
		var leafid=selectedOption.id;
		$("#leafid").val(leafid);
		$("#IBSelectForm").submit();
	
		//self.parent.frames["mainFrame"].location="addQOnline.jsp";
	}
</script>
<script type="text/javascript">
var	SchoolStructure=null;
var	UseageJson=null;
function	getSchoolStructureList(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "SchoolStructureOptionsGetAction",
		success : function(result) {
			SchoolStructure=result.data;
		},
		error:function(){
		}
	});
}

function getCurse(o){
	var	SelectedSpecialField	=	o.id;
	if(SelectedSpecialField	==	null){
		alert("请选择专业");
		return;
	}
	var	innerHTML	= 	document.getElementById("SelectCurse").innerHTML;
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "CurseSelectBySpecialFieldAction",
		data:"specialFieldId="+SelectedSpecialField,
		success : function(result) {
			$("#SelectCurse ").empty();
			$("#SelectCurse").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectCurse").append("<option id=\""+result.data[i].curse_id+
						"\" value=\""+result.data[i].curse_id+"\">"+
						result.data[i].curse_name+"</option>");
			}
		},
		error:function(){
		}
	});
	
}
function getMajor(o){
	document.getElementById("College").innerHTML=o;
}
function	GetCourseBySpecialfield(){
	getMajor(this.innerHTML);
	getCurse(this);
}
//根据课程ID与用途ID查询符合条件的试题库列表
function GetItemBankByCurseId(curseId,useageId){
	
	//加载查询到的试题库信息表格
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "ItemBankGetByCourseAndUseage",
		data:"curseId="+curseId+"&IBusage="+useageId,
		success : function(result) {
			//YAHOO.example.Data=result;
			//alert(result.data);
			if(result==""){
			}
			var dataJson=result.data;
			var	innerStr="";
			innerStr+='<table width="100%">';
			innerStr+='<tr>';
			for(var	i=0;i<dataJson.length;i++){
				//alert(dataJson[i].itembank_name);
				innerStr+='<td style="border: 0px"><input type="radio" value="'+dataJson[i].itembank_id+'" name="radio">'+
				dataJson[i].itembank_name+'</td>';
				if((i+1)%5==0){
					innerStr+='</tr><tr>';
				}
			}
			innerStr+='</tr>'
			innerStr+='</table>';
			$("#TD_"+useageId).empty();
			if(dataJson.length==0){
				$("#TD_"+useageId).append("(无)");
			}
			$("#TD_"+useageId).append(innerStr);
		},
		error:function(){
		}
	});
}
function CheckValidity(){
	if(""==$("#SelectCurse").val()){
		alert("请选择课程");
		return false;
	}
	return true;
}
function QueryAndCreateItembankList(){
	if(CheckValidity()){
		for(var i=0,j=UseageJson.length;i<j;i++){
			GetItemBankByCurseId($("#SelectCurse").val(),UseageJson[i].dictionary_entries_value);
		}
		
		return true;
	}
	else{
		return false;
	}
}
</script>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<div id="act_top">
		<a href="#">试题管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">增加试题</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">在线增加试题</a>
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>

	<div style="margin-left: 25px; margin-top: 10px">
		<form action="addQOnline.jsp" id="IBSelectForm">
		<input type="hidden" value="" name="leafid" id="leafid">
		<table>
			<tr>
				<td>学院专业：</td>
				<td><div id="SpecialField"></div></td>
				
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程：</td>
				<td>
					<select id="SelectCurse" style="font-size: 12px">
						<option selected="selected" value="">--请选择--</option>
					</select>
				</td>
				<td><input type="button" class="button" value="查询"
					onclick="showTable()"></td>
			</tr>
		</table>
		<div id="div" style="visibility: hidden">
		</div>
		<div id="SelectType" style="visibility: hidden">
			<table>
				<tr>
					<td>题型选择：</td>
					<td><select name="SelectQuesType" id="SelectQuesType" style="font-size: 12px">
								<option selected="selected" value="">--请选择--</option>
						</select></td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<td align="center"><input type="button" value="确定"
						class="button" onclick="addQ();"></td>

				</tr>
			</table>

		</div>
		</form>
	</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	<script>
		getSchoolStructureList();
		loadMenu("SpecialField", SchoolStructure, GetCourseBySpecialfield,"College");
		//加载用途选项,生成显示分块框架 每个显示试题库列表的表格中<td>的id为"TD_"+字典项值 ，在查询后根据此ID填充对应框架中的内容
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "GetPaperUseOptionAction",
			success : function(result) {
				UseageJson=result.data;
				//alert(UseageJson);
				$("#div ").empty();
				for(var i=0,j=result.data.length;i<j;i++){
					//生成表格 
					$("#div").append('<table width="100%" border="0" cellspacing="0" cellpadding="0"class="tableList">'+
							'<tr align="center"><td class="tr1">试题库列表('+result.data[i].name+')</td></tr>'+
							'<tr align="center"><td id="TD_'+result.data[i].dictionary_entries_value+'">(无)</td></tr></table>')
				}
			},
			error:function(){
			}
		});
		//加载试题类型选项
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "GetQuesUseOptionAction",
			success : function(result) {
				$("#SelectQuesType").empty();
				$("#SelectQuesType").append("<option value=\"\">--请选择--</option>");
				for(var	i=0;i<result.data.length;i++){
					$("#SelectQuesType").append("<option id=\""+result.data[i].dictionary_entries_value+
							"\" value=\""+result.data[i].dictionary_entries_value+"\">"+
							result.data[i].name+"</option>");
				}
			},
			error:function(){
			}
		});		
		
	</script>
</body>
</html>