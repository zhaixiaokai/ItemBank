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
<script>
var	TeachingMaterialId='<%=request.getParameter("SelectTM")%>';  
// var	TeachingMaterialId="jiaocai020000102-1";
var	FunctionJson=null;

var	flag=false;
var	param="";

var	XZIDarr	=	new	Array();
var	XZCount	=	new	Array();
var	TKIDarr	=	new	Array();
var	TKCount	=	new	Array();
var	PDIDarr	=	new	Array();
var	PDCount	=	new	Array();
var	JDIDarr	=	new	Array();
var	JDCount	=	new	Array();
function NextStep(){
	self.parent.frames["mainFrame"].location="Preview1_autom.jsp";
}
//清除屏幕锁定，并且隐藏修改框 
function ClearFade(){
	document.getElementById("fade").style.display="none";
	YAHOO.example.container.panel1.hide();
}
function ShowPannel(){
    //根据客户端浏览器高度设置半透明罩的高度 
    document.getElementById('fade').style.height=document.body.clientHeight+"px" ;
    document.getElementById('fade').style.display='block';
    YAHOO.example.container.panel1.show();
}

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
		url : "FunctionGetChapterTreeDataAction?MyTeachingMaterialId="+TeachingMaterialId,
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
		url : "KnowledgeListGetByChapterIdAction",
		data:"LeafNodeIDs="+param,
		success : function(result) {
			GetKnowledgeList=result.data;
			for(var i=0;i<result.data.length;i++){
				$("#TD_"+result.data[i].chapter_id).empty();
			} 
			for(var	i=0;i<result.data.length;i++){
					document.getElementById("TD_"+result.data[i].chapter_id).innerHTML+="<br>"
					+result.data[i].knowledgepointname+"&nbsp;&nbsp;&nbsp;个数：<input id='IP_"+result.data[i].knowledge_point_id
					+"' type='text' class='count' value='0' size='2'/>";
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

	$('#BD_content').html("");
	
	Render("BD_content");
}
</script>
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../../servJs/NestedTables_V3.js" charset="GBK"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<script type="text/javascript" src="../../servJs/ZuJuanFun.js" charset="GBK"></script>
<!-- YUI -->
<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../js/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="../js/yui/build/element/element-beta-min.js"></script>


<link rel="stylesheet" type="text/css" href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript" src="../js/yui/build/container/container-min.js"></script>
<style type="text/css">
.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: #EEEEEE;
	z-index: 1;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
}
</style>
<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		//初始化修改信息对话框
		YAHOO.namespace("example.container");
		function init() {
			YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", { width:"800px", visible:false, constraintoviewport:true,close :false } );
			YAHOO.example.container.panel1.render();
		}
		YAHOO.util.Event.addListener(window, "load", init);
		$("#cancleButton").bind('click',function(){
			if(confirm("取消后对应试题类型中的配置将丢失，确认取消？"))
			ClearFade();
		});				
		reloadTable();
		KnowledgeList();

		$(".config").bind('click',function(){
			var	id	=	$(this).attr("id");
			var	title	=	$(this).html();
			
			if(id=="configS"){//配置选择题
				$('.hd').html(title);
				$('#BD_content')[0].reset();
				//为已经配置过的数据初始化 
				if(XZIDarr.length!=0){
					for(var	i=0;i<XZIDarr.length;i++){
						$('#'+XZIDarr[i]).attr('value',XZCount[i]);
					}
				}
				ShowPannel();
				$("#saveButton").unbind("click");
				$('#saveButton').bind('click',function(){
					saveConfig("xuanze");
				});
			}else if(id=="configT"){//配置填空题
				$('.hd').html(title);
				$('#BD_content')[0].reset();
				//为已经配置过的数据初始化 
				if(TKIDarr.length!=0){
					for(var	i=0;i<TKIDarr.length;i++){
						$('#'+TKIDarr[i]).attr('value',TKCount[i]);
					}
				}
				ShowPannel();
				$("#saveButton").unbind("click");
				$('#saveButton').bind('click',function(){
					saveConfig("tiankong");
				});
			}else if(id=="configP"){//判断题
				$('.hd').html(title);
				$('#BD_content')[0].reset();
				//为已经配置过的数据初始化 
				if(PDIDarr.length!=0){
					for(var	i=0;i<PDIDarr.length;i++){
						$('#'+PDIDarr[i]).attr('value',PDCount[i]);
					}
				}
				ShowPannel();
				$("#saveButton").unbind("click");
				$('#saveButton').bind('click',function(){
					saveConfig("panduan");
				});
			}else if(id=="configJ"){//简答题
				$('.hd').html(title);
				$('#BD_content')[0].reset();
				//为已经配置过的数据初始化 
				if(JDIDarr.length!=0){
					for(var	i=0;i<JDIDarr.length;i++){
						$('#'+JDIDarr[i]).attr('value',JDCount[i]);
					}
				}
				ShowPannel();
				$("#saveButton").unbind("click");
				$('#saveButton').bind('click',function(){
					saveConfig("jianda");
				});
			}
		});
		
		$("#zujuan").bind('click',function(){
			if(XZCount.length==0||PDCount.length==0||JDCount.length==0||TKCount.length==0){
				//if(!confirm("有题型未进行配置，确认组卷？"))
				if(XZCount.length==0){
					alert("选择题未配置，若不要求有选择题将个数配置为0即可");
					return;
				}
				else if(PDCount.length==0){
					alert("判断题未配置，若不要求有判断题将个数配置为0即可");
					return;
				}
				else if(JDCount.length==0){
					alert("简答题未配置，若不要求有简答题将个数配置为0即可");
					return;
				}
				else if(TKCount.length==0){
					alert("填空题未配置，若不要求有填空题将个数配置为0即可");
					return;
				}
			}
			//alert("组卷时间可能比较长，请稍后...");
			
			$("#myForm")[0].reset();
			$("#XZKPList").val(XZIDarr);
			$("#XZKPCount").val(XZCount);
			$("#PDKPList").val(PDIDarr);
			$("#PDKPCount").val(PDCount);
			$("#TKKPList").val(TKIDarr);
			$("#TKKPCount").val(TKCount);
			$("#JDKPList").val(JDIDarr);
			$("#JDKPCount").val(JDCount);
			

			//接收从前页传来的参数
			$("#difficulty").val('<%=request.getParameter("SelectDifficulty")%>');
			$("#targetEPDB").val('<%=request.getParameter("SelectEPDB")%>'); 
			$("#totalScore").val('<%=request.getParameter("totalScore")%>');
			$("#totalTime").val('<%=request.getParameter("totalTime")%>');
			$("#finCourseId").val('<%=request.getParameter("SelectCurse")%>');
			$("#useage").val('<%=request.getParameter("SelectIBUseage")%>');
			$("#IBList").val('<%=request.getParameter("selectedIB")%>');
			
			$(function() {
				var options = {
					type : "post",
					dataType : "json",
					success : function(result) {
						alert(result.text);
						if(result.result!="succ")
							return;
						var FilePath="../../"+result.dir+"/paperDoc.doc";
						window.open(FilePath,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no'); 
					},
					error:function(){
						alert("未知错误 ");
					}
				};
				$('#myForm').ajaxSubmit(options);
				return false;
			});
			
		});
		
		
	}); 
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
			<th class="pagehead">自动组卷</th>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td><div id="container">
								<div id="panel1" style="z-index: 1999">
									<div class="hd" style="z-index: 2000">查看教材信息</div>
									<div class="bd" style="z-index: 2000">
										<div><form id="BD_content"></form></div>		
										<div id="BD_button" align="center">
											<input type="button" value="保存" class="button" id="saveButton">
											<input type="button" value="取消" class="button" id="cancleButton">
										</div>						
									</div>
									<div class="ft"></div>
								</div>
							</div> <!-- 半透罩，用来锁定屏幕 -->
							<div id="fade" class="black_overlay"></div>
							<fieldset style="height: 100%;">
								<legend>配置题型</legend>

								<table width="100%" height="80px">
									<tr>
										<td><a href="#" class="config" id="configS">配置选择题</a></td>
										<td><a href="#" class="config" id="configT">配置填空题</a></td>
										<td><a href="#" class="config" id="configP">配置判断题</a></td>
										<td><a href="#" class="config" id="configJ">配置简答题</a></td>
									</tr>

								</table>
								<table align="center">
									<tr>
										<td>
											<p>
												<br> <input type="button" value="组卷" class="button" id="zujuan"/>
											</p>
										</td>
									</tr>
								</table>


							</fieldset></td>
					</tr>
				</table>
				<form id="myForm" action="PaperCreateAutoAction">
				<input type="hidden" id="difficulty" name="difficulty">
				<input type="hidden" id="targetEPDB" name="targetEPDB">
				<input type="hidden" id="totalScore" name="totalScore">
				<input type="hidden" id="totalTime" name="totalTime">
				<input type="hidden" id="finCourseId" name="finCourseId">
				<input type="hidden" id="useage" name="useage">
				<input type="hidden" id="IBList" name="IBList">
				
				
				<input type="hidden"  id="XZKPList" name="XZKPList">
				<input type="hidden"  id="XZKPCount" name="XZKPCount">
				<input type="hidden"  id="PDKPList" name="PDKPList">
				<input type="hidden"  id="PDKPCount" name="PDKPCount">
				<input type="hidden"  id="TKKPList" name="TKKPList">
				<input type="hidden"  id="TKKPCount" name="TKKPCount">
				<input type="hidden"  id="JDKPList" name="JDKPList">
				<input type="hidden"  id="JDKPCount" name="JDKPCount">
				</form>
			</td>
		</tr>


	</table>




	<!-- 右侧界面完 -->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>
