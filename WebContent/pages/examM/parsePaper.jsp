<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/multimenu.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<link href="css/table.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/select.css">
<script src="../SpryAssets/SpryCollapsiblePanel.js" type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../js/formReset.js" type="text/javascript"></script>
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>

<link rel="stylesheet" type="text/css" href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../js/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../js/yui/build/datasource/datasource-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../js/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="../js/yui/build/history/history-min.js"></script>
<script type="text/javascript" src="../js/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript" src="../js/yui/build/datatable/datatable-beta-min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript"
	src="../js/yui/build/container/container-min.js"></script>
	<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<title>无标题文档</title>
<style type="text/css">
/* custom styles for this example */
#dt-pag-nav {
	margin-bottom: 1ex;
	width: 790px
} /* custom pagination UI */
#dt-pag-nav a {
	color: #00c;
	text-decoration: underline;
}

#yui-history-iframe {
	position: absolute;
	top: 0;
	left: 0;
	width: 1px;
	height: 1px; /* avoid scrollbars */
	visibility: hidden;
}

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
	filter: alpha(opacity =   80);
}
</style>
<script type="text/javascript">
	var quesFile='<%=request.getParameter("quesFileName")%>';
	var target_itembank='<%=request.getParameter("target_itembank")%>';
	var quesType='<%=request.getParameter("quesType")%>';
	var htmlName=null;
	function showQues(number){
		$.ajax( {
			type : "post",
			dataType : "text",
			async : false,
			url : "ShowQuesContentAction?number="+number+"&quesFileName="+quesFile,
			success : function(result) {
				htmlName=result;
			},
			error:function(){
			}
		});
		var currentPath=parent.window.frames["mainFrame"].location.href;
		//字符串截取
		var tempString=currentPath;
		var string="/";
		var position=0;
		while( (  pos = tempString.indexOf( "/" ) ) != -1 ){
			tempString = tempString.substr( pos + string.length, tempString.length );
			position+=pos+string.length;
		}
		//var a=ss.indexOf('/');
		var FilePath=currentPath.substr(0,position);
		FilePath+=htmlName+'.html';
		window.open(FilePath,'newwindow','top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no, status=no'); 
		//$("#quesAnchor"+number+"").attr({href:FilePath});
		/* //删除html文件
			$.ajax( {
			type : "post",
			dataType : "text",
			async : false,
			url : "DeleteQuesHtmlAction?quesFileName="+htmlName,
			success : function(result) {
			},
			error:function(){
			}
		}); */
	
	return null;
	}
	
	
	//获取知识点
	function getpointdata() {
	var curseid='<%=request.getParameter("curseid")%>';
		$.ajax({
					type : "post",
					dataType : "json",
					async : false,
					url : "GetPointDataAction?leafid="+curseid,
					success : function(result) {
						$("[name=selectpoint]").empty(); 
						$("[name=selectpoint]").append(
								"<option value=\"selectnonepoint\" id=\"selectnonepoint\">--请选择--</option>");
						for ( var i = 0; i < result.data.length; i++) {
							if (i == "0") {
								$("[name=selectpoint]")
										.append(
												"<option selected id=\""+result.data[i].knowledge_point_id+
						"\" value=\""+result.data[i].name+"\">"
														+ result.data[i].name
														+ "</option>");

							} else {
								$("[name=selectpoint]")
										.append(
												"<option id=\""+result.data[i].knowledge_point_id+
						"\" value=\""+result.data[i].name+"\">"
														+ result.data[i].name
														+ "</option>");
							}
						}
					},
					error : function() {
					}
				});

	}
	
	
	//加载试题题型
	function getQuesType(){
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "GetQuesUseOptionAction",
			success : function(result) {
				$("[name=SelectQuesType]").empty();
				$("[name=SelectQuesType]").append("<option value=\"selectnonetype\" id=\"selectnonetype\>--请选择--</option>");
				for(var	i=0;i<result.data.length;i++){
					$("[name=SelectQuesType]").append("<option id=\""+result.data[i].dictionary_entries_value+
							"\" value=\""+result.data[i].dictionary_entries_value+"\">"+
							result.data[i].name+"</option>");
				}
			},
			error:function(){
			}
		});		
	}
	
	
	//导入试题
	function importQues(number){
		//数据合理性检查
	var difficulty_coefficient = document.getElementById(""+number+"difficulty_coefficient").value;
	var defaultpoint = document.getElementById(""+number+"defaultpoint").value;
	var time = document.getElementById(""+number+"time").value;
	//获取试题库用途中select的值
	 var options = document.getElementById(""+number+"selectpoint").options;
	var index = document.getElementById(""+number+"selectpoint").selectedIndex;
	var selectedOption = options[index];
	var selectpoint = selectedOption.id; 
	/* 
	 var typeoptions = document.getElementById(""+number+"SelectQuesType").options;
	var typeindex = document.getElementById(""+number+"SelectQuesType").selectedIndex;
	var typeselectedOption = typeoptions[typeindex];
	var selecttype = typeselectedOption.id;   */
	if (difficulty_coefficient == "") {
		alert("难度系数为必填项，请重新输入 ");
		return;
	}else if (!isFigure(difficulty_coefficient)) {
		alert("难度系数为数字，请重新输入");
		return;
	} else if (difficulty_coefficient>=1||difficulty_coefficient<=0) {
		alert("难度系数为0到1之间的小数");
		return;
	}  else if (defaultpoint == "") {
		alert("默认分值为必填项，请重新输入 ");
		return;
	}  else if (!isFigure(defaultpoint)) {
		alert("默认分值为数字，请重新输入");
		return;
	} else if (time == "") {
		alert("时间为必填项，请重新输入 ");
		return;
	} else if (!isFigure(time)) {
		alert("时间为数字，请重新输入");
		return;
	}else if (selectpoint==null||selectpoint==""||selectpoint=="selectnonepoint") {
		alert("请选择知识点");
		return;
	}  else {
	//试题基本信息保存
	$(function() {
				var options = {
					type : "post",
					dataType : "text",
					url : "SaveImportQuesAction?target_itembank="+ target_itembank+"&quesFileName="+quesFile+"&number="+number+"&ques_selectpoint="+selectpoint+"&questiontype="+quesType,
					success : function(result) {
						if (result != null && result != "") {
							if (result == "导入成功") {
								alert(result);
								var form = document.getElementById(""+number+"frmMain");
								form.reset();
							}
						}else {
							alert("导入失败");
						}
					}
				};
			//	var formname=
				$('#'+number+'frmMain').ajaxSubmit(options);
				return false;
			});
		}
		
	}

</script>
</head>
<body class=" yui-skin-sam" >
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="#">试题库分类管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">导入试题</a>
	</div>

	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td></td>
		</tr>
	</table>
	<div class="div_style3">
		<div id="quesTable"align="center" style="margin-top: 10px;">
			</div>
		<script>
	var quesFileName='<%=request.getParameter("quesFileName")%>';
	var tablenumber=null;
	//计算试题数量
		$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "QuesNumberAction?quesFileName="+quesFileName,
		success : function(result) {
			tablenumber=result;//注意试题数量是tablenumber减1，第一个表格是试题编辑人员信息
		},
		error:function(){
		}
	});
	tablenumber=parseInt(tablenumber);
	var tablecontent='';
	//var insideTable='<table class="tablestyle1" align="center"><tr style="font-size: 16px;solid"><td style="border: 0px" nowrap>难度系数：</td><td style="border: 0px"><input type="text"style="width: 50px"></td><td style="border: 0px">(0-1)</td></tr><tr><td style="border: 0px" nowrap>答题时间：</td><td style="border: 0px"><input type="text"style="width: 50px"></td><td style="border: 0px">s</td></tr><tr><td style="border: 0px">知识点：</td><td style="border: 0px"><select name="select2"><option selected="selected">--请选择--</option><option>知识点a</option><option>知识点b</option><option>知识点c</option><option>知识点d</option><option>知识点f</option></select></td></tr><tr><td style="border: 0px">题目类型：</td><td style="border: 0px"><select><option selected="selected">--请选择--</option><option>选择题</option><option>填空题</option><option>简答题</option></select></td></tr></table>';
//	var insideTable='<form id="'+i+'frmMain"><table class="tablestyle1" align="center">';
	//insideTable+='<tr><td style="border: 0px" nowrap>难度系数：</td><td style="border: 0px"><input type="text" size="9" title="难度系数为0-1间的小数" class="text" id="difficulty_coefficient" name="difficulty_coefficient"><span class="red"> *</span></td></tr>';
	//insideTable+='	<tr><td nowrap align="right" height="10px" style="border: 0px" nowrap>默认分值:</td><td colspan="3" style="border: 0px"><input type="text"  size="9" class="text" id="defaultpoint" name="defaultpoint" title="请输入数字"><span class="red"> *</span></td></tr>';
//	insideTable+='	<tr><td nowrap align="right" height="10px" style="border: 0px" nowrap>预计时间:</td><td colspan="3" style="border: 0px"><input type="text" size="9" class="text" id="time" name="time" title="请输入数字，单位为秒">&nbsp;秒<span	class="red"> *</span></td></tr>';
//	insideTable+='	<tr><td nowrap align="right" style="border: 0px" nowrap>知识点:</td><td style="border: 0px"><select name="selectpoint" id="selectpoint" style="font-size: 12px"></select><span class="red"> *</span></td></tr>';
//	insideTable+='	<tr><td style="border: 0px" nowrap>题型选择：</td><td style="border: 0px"><select name="SelectQuesType" id="SelectQuesType" style="font-size: 12px"><option selected="selected" value="">--请选择--</option></select></td></tr>';
//	insideTable+='</table>';
	//alert(insideTable);
	$("#quesTable ").empty();
	tablecontent+='<table border="0" width="100%" align="center" class="tableList"><tr class="tr1" style="font-size: 18px;solid"><td></td><td>序号</td><td>试题信息</td><td>试题配置</td><td>导入</td></tr>';
 	for ( var i = 1; i < tablenumber; i++) {
 		tablecontent+='<tr><td><input type="checkbox"></td><td>'+i+'</td><td><a href="javacript:void(0)" onclick="showQues('+i+')" name="quesAnchor'+i+'" id="quesAnchor'+i+'" target="_blank">点击查看试题详细信息</a></td>';
 		tablecontent+='<td><form id="'+i+'frmMain"><table class="tablestyle1" align="center">';
 		tablecontent+='<tr><td style="border: 0px" nowrap>难度系数：</td><td style="border: 0px"><input type="text" size="9" title="难度系数为0-1间的小数" class="text" id="'+i+'difficulty_coefficient" name="difficulty_coefficient"><span class="red"> *</span></td></tr>';
 		tablecontent+='<tr><td nowrap align="right" height="10px" style="border: 0px" nowrap>默认分值:</td><td colspan="3" style="border: 0px"><input type="text"  size="9" class="text" id="'+i+'defaultpoint" name="defaultpoint" title="请输入数字"><span class="red"> *</span></td></tr>';
 		tablecontent+='<tr><td nowrap align="right" height="10px" style="border: 0px" nowrap>预计时间:</td><td colspan="3" style="border: 0px"><input type="text" size="9" class="text" id="'+i+'time" name="time" title="请输入数字，单位为秒">&nbsp;秒<span	class="red"> *</span></td></tr>';
 		tablecontent+='<tr><td nowrap align="right" style="border: 0px" nowrap>知识点:</td><td style="border: 0px"><select name="selectpoint" id="'+i+'selectpoint" style="font-size: 12px"></select><span class="red"> *</span></td></tr>';
 		tablecontent+='</table></form></td>';
 		tablecontent+='<td><a href="#" onclick="importQues('+i+')">导入</a></td></tr>';
			}
	tablecontent+='</table>';
	$("#quesTable ").append(tablecontent);
	getpointdata();
	getQuesType();
	</script>

	
	</div>
	
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>

</body>
</html>