<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
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
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>

<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}
</style>

<style type="text/css">
body {
	margin:0;
	padding:0;
}
</style>
<!--begin custom header content for this example-->
<style type="text/css">
/* custom styles for this example */
.yui-skin-sam .yui-dt-liner { white-space:nowrap; } 
</style>
<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/build/dragdrop/dragdrop-min.js"></script>
<script type="text/javascript" src="../js/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/datasource/datasource-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/datatable/datatable-beta-min.js"></script>
<script type="text/javascript" src="../../servJs/NestedTables_V3.js" charset="GBK"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>



<script type="text/javascript">
var	CourseId="";
var	param="";
var	SchoolStructure=null;
var	flag=false;
var	FunctionJson=null;
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
function	CreateTable(){
	//获取课程选择框中选中的值的id
	var	options	=	document.getElementById("SelectCurse").options;
	var	index	=	document.getElementById("SelectCurse").selectedIndex;
	var	selectedOption	=	options[index];
	var	course	=	selectedOption.id;
	
	//获取用途选择框中选中值的id
	options	=	document.getElementById("SelectPaperUseage").options;
	index	=	document.getElementById("SelectPaperUseage").selectedIndex;
	selectedOption	=	options[index];
	var	useage	=	selectedOption.id;
	YAHOO.example.Data = {
			data:[]
	}
	//加载查询到的试题库信息表格
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "ItemBankGetByCourseAndUseage",
		data:"curseId="+course+"&IBusage="+useage,
		success : function(result) {
			YAHOO.example.Data=result;
		},
		error:function(){
		}
	});
    //checkbox
    YAHOO.widget.DataTable.Formatter.checkbox = function(elCell, oRecord, oColumn, oData){
        var bChecked = oData;
        bChecked = (bChecked) ? " checked=\"checked\"" : "";
        elCell.innerHTML = "<input name=\"CheckBoxs\" id = \"" + oRecord.getData('itembank_id')
          + "\" type=\"checkbox\"" + bChecked
          + " class=\"yui-dt-checkbox\" />";
    }

    YAHOO.example.Basic = new function() {
        var myColumnDefs = [
            {key:"check",label:"",formatter:"checkbox"},  
            {key:"itembank_id",label:"试题库编号", sortable:true,width:200},
            {key:"itembank_name",label:"试题库名称",sortable:true,width:200},
            {key:"curse_id",label:"课程编号", sortable:true,width:200}

        ];

        this.myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.data);
        this.myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        this.myDataSource.responseSchema = {
            fields: ["itembank_id","itembank_name","curse_id"]
        };

        this.myDataTable = new YAHOO.widget.DataTable("basic",
                myColumnDefs, this.myDataSource);
    };

}
</script>
<script>

function	IBQurey(){
	var	options	=	document.getElementById("SelectCurse").options;
	var	index	=	document.getElementById("SelectCurse").selectedIndex;
	var	selectedOption	=	options[index];
	if(selectedOption.id==''||selectedOption.id=='SelectNoneCourse'){
		alert("请选择课程");
		return;
	}
	options	=	document.getElementById("SelectPaperUseage").options;
	index	=	document.getElementById("SelectPaperUseage").selectedIndex;
	selectedOption	=	options[index];
	if(selectedOption.id==''||selectedOption.id=='none'){
		alert("请选择试题用途");
		return;
	}
	CreateTable();

}
//通过课程选择试卷库 并且生成下拉列表 
function getEPDB(){
 	var options = document.getElementById("SelectCurse").options;
	var index = document.getElementById("SelectCurse").selectedIndex;
	SelectedCurseField = options[index].id;
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "EPDBSelectByCurseIdAction",
		data:"CurseFieldId="+SelectedCurseField,
		success : function(result) {
			$("#SelectEPDB ").empty();
			$("#SelectEPDB").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectEPDB").append("<option id=\""+result.data[i].epdb_id+
						"\" value=\""+result.data[i].epdb_id+"\">"+
						result.data[i].epdb_name+"</option>");
			}
		},
		error:function(){
		}
	});
	
}

$(document).ready(function(){
	getSchoolStructureList();
	loadMenu("SpecialField",SchoolStructure,GetCourseBySpecialfield,"College");
/* 	//加载难度选项
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetDifficultyOptionAction",
		success : function(result) {
			$("#SelectDifficulty ").empty();
			$("#SelectDifficulty").append("<option value=\"\">--请选择--</option>");
			var	difficultySelectInnerHTML="<option id=\"difficultyDefaultSelect\" value=\"none\">--请选择--</option>";
			for(var	i=0;i<result.data.length;i++){
				$("#SelectDifficulty").append("<option id=\""+result.data[i].dictionary_entries_value
						+"\" value=\""+result.data[i].value+"\">"+result.data[i].name+"</option>");
			}
		},
		error:function(){
		}
	}); */
	//$("#divContainer").hide();
	//加载试卷用途选项
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetPaperUseOptionAction",
		success : function(result) {
			$("#SelectPaperUseage ").empty();
			$("#SelectPaperUseage").append("<option value=\"\">--请选择--</option>");
			for(var	i=0;i<result.data.length;i++){
				$("#SelectPaperUseage").append("<option id=\""+result.data[i].dictionary_entries_value
						+"\" value=\""+result.data[i].value+"\">"+result.data[i].name+"</option>");
			}
		},
		error:function(){
		}
	});
	$("#SelectCurse").change(function(){
		//根据课程id选择试卷库
		createFlag=false;
		FunctionJson=null;
		$("#divContainer").empty();
		getEPDB();
		CourseId	=	$(this).val();
		if(reloadTable()){
			KnowledgeList();
		}
		
		$("#kpDiv").slideDown();
		//getTM(); 
	});
	$("#resetForm").bind('click',function(){
		$("#myForm")[0].reset();
	});
	$("#nextStep").bind('click',function(){		
		var SelectedIBList=	new	Array();//选中的试题库id
		var SelectedKPList=	new	Array();//选中的知识点
		var	CheckBoxsList=document.getElementsByName("CheckBoxs");
		//$("#myForm").submit();
		var	flag=false;
		for(var i=0;i<CheckBoxsList.length;i++){
			if(CheckBoxsList[i].checked){
				if(flag==false){
					flag=true;
				}
				SelectedIBList.push(CheckBoxsList[i].id);
			}
		}
		var	kpCheckbox	=	document.getElementsByName("kpCheckbox");
		var	count	=	0;
		for(var	i=0;i<kpCheckbox.length;i++){
			if(kpCheckbox[i].checked){
				count=SelectedKPList.push(kpCheckbox[i].id);
			}
		}
		
		if(!flag){
			alert("请至少选择一个试题库供组卷");
			return;
		}
			
 		if($("#SelectDifficulty").val()==""){
			alert("请填写难度");
			return;
		}
 		if (!isFigure($("#SelectDifficulty").val())) {
 			alert("难度系数为数字，请重新输入");
 			return;
 		}
 		if ($("#SelectDifficulty").val()>=1||$("#SelectDifficulty").val()<=0) {
 			alert("难度系数为0到1之间的小数");
 			return;
 		}
		if($("#SelectEPDB").val()==""){
			alert("请选择目标试卷库");
			return;
		}
		if($("#totalScore").val()==""){
			alert("请填写试卷总分");
			return;
		}
		if($("#totalTime").val()==""){
			alert("请填写试卷总时间");
			return;
		}
		if(!CheckIfIsInteger($("#totalScore").val())){
			alert("试卷总分必须为整数");
			return;
		}
		if(!CheckIfIsInteger($("#totalTime").val())){
			alert("试卷总时间必须为整数");
			return;
		}
		if(count==0){
			alert("请选择知识点");
			return;
		}
		$("#selectedKP").val(SelectedKPList);
		$("#selectedIB").val(SelectedIBList);
		$("#myForm")[0].submit();
		
	});
});

//加载项
function getKnowledgeList(){
	
}
//从数据库中获取章节列表
function GetFunctionTree() {
	var FunctionTreeData = null;
	$.ajax({
		type : "post",
		dataType : "json",
		async : false,
		url : "FunctionGetTeachingProgressTreeDataAction?MyCourseId="+CourseId,
		success : function(result) {
			FunctionTreeData = result;
		},
		error : function() {
		}
	});
	return FunctionTreeData;
}
function Render(div){
	FunctionJson = GetFunctionTree();
	if(FunctionJson==null){
		alert("该进度未配置进度结构，请练习相关人员解决！");
		return false;
	}
	initTableContainer(FunctionJson,div);
	CreateMultiTableInterface(FunctionJson,div,CheckBoxSelectEvent);
}
function CheckBoxSelectEvent(){
	
}
//获取知识点列表1
function	KnowledgeList(){
	GetLeafNodes(FunctionJson);
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "KnowledgeListGetByTermIdAction",
		data:"LeafNodeIDs="+param,
		success : function(result) {
			GetKnowledgeList=result.data;
			for(var i=0;i<result.data.length;i++){
				$("#TD_"+result.data[i].progress_teaching_node_id).empty();
			} 
			for(var	i=0;i<result.data.length;i++){
					document.getElementById("TD_"+result.data[i].progress_teaching_node_id).innerHTML+="<br>"+"<input name=\"kpCheckbox\" id='IP_"+result.data[i].knowledge_point_id
					+"' type='checkbox' />&nbsp;&nbsp;&nbsp;"
					+result.data[i].knowledgepointname;
			}
		},
		error:function(){
		}
	});
}
//获取叶子节点的Id
function GetLeafNodes(json)
{
	for(var i=0;i<json.length;i++)
	{
		if(json[i].children==undefined)
		{
			if(flag==false){
				param+=json[i].id;
				flag=true;
			}
			else{
				param+=" "+json[i].id;
			}
		}
		else
		{
			GetLeafNodes(json[i].children);
		}
	}	
}
function	reloadTable(){

	$('#divContainer').html("");
	
	if(Render("divContainer")==false){
		return false;
	}
	return true;
}
</script>
</head>
<body class=" yui-skin-sam"> 

	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->

	<table width="100%" class="CContent">
		<tr>
			<th class="pagehead">手动组卷</th>
		</tr>
		<tr>
			<td width="100%">
			<form id="myForm" action="handOnChapter1.jsp">
				<table width="100%">
				
					<tr>
						<td>
							<fieldset style="height: 100%;">
									<legend>选择目标试题库</legend>
									<table>
										<tr>
											<td width="100px">学院专业:</td>

											<td width="100px" id="SelectBasic"><div
													id="SpecialField"></div></td>

											<td width="50px">课程:</td>
											<td width="100px"><select id="SelectCurse"
												name="SelectCurse" style="font-size: 12px"><option
														value="" selected="selected">--请选择--</option>
											</select></td>
											<td>试题用途:</td>
											<td><select name="SelectIBUseage" id="SelectPaperUseage"></select></td>
											<td><input type="button" class="button" value="查询"
												onclick="IBQurey()" /></td>

										</tr>
									</table>
									<div id="basic" style="width: 600px"></div>
								</fieldset>
								<fieldset style="height: 100%;">
									<legend>配置试卷基本信息</legend>

									<table>

										<tr>
											<td>试卷难度:</td>
											<td>
												<!-- 											<select name="SelectDifficulty" id="SelectDifficulty">
											</select> --> <input type="text" id="SelectDifficulty"
												name="SelectDifficulty">
											</td>

											<td width="70px">目标试卷库:</td>
											<td width="100px"><select id="SelectEPDB"
												name="SelectEPDB"><option value=""
														selected="selected">--请选择--</option>
											</select></td>
											<td></td>
										</tr>
										<tr height="50px">
											<td>试卷总分:</td>
											<td><input name="totalScore" type="text" id="totalScore" />(分)</td>
											<td>考试时长:</td>
											<td><input name="totalTime" type="text" id="totalTime" />（分钟）</td>

										</tr>
									</table>


								</fieldset>
								
								<fieldset id="kpDiv" style="height: 100%;display: none;" >
								<legend>选择覆盖知识点</legend>
								<div id="divContainer">
								
								</div>
								</fieldset>
							</td>
					</tr>
					
				</table>
				<input type="hidden" id="selectedIB" name="selectedIB"/> 
				<input type="hidden" id="selectedKP" name="selectedKP"/> 
				</form>
			</td>
		</tr>

		<tr>
			<td align="center">
				<p>
					<input id="resetForm" type="button" value="重置" class="button"/> &nbsp;
					<input id="nextStep" type="button" value="下一步" class="button" />
				</p>
			</td>
		</tr>
	</table>

	<!-- 右侧界面完 -->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>



<!--END SOURCE CODE FOR EXAMPLE =============================== -->		
</body>
</html>