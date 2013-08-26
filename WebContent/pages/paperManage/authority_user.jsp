<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" /><!-- 
<link rel="stylesheet" type="text/css" href="css/menu.css"> -->
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link rel="stylesheet" type="text/css" href="../css/select.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>

<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>
<script>
//获取组织机构数据，显示专业列表
var	SchoolStructure=null;
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

function getMajor(o){
	document.getElementById("College").innerHTML=o;
}
//获取专业id
var major_id=null;
function	GetCourseBySpecialfield(){
	getMajor(this.innerHTML);
	getCurse(this);
	major_id=this.id;
}

function getCurse(o){
	var	SelectedSpecialField	=	o.id;
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

//数组去重
	function unique(data) {
		data = data || [];
		var a = {};
		for ( var i = 0; i < data.length; i++) {
			var v = data[i];
			if (typeof (a[v]) == 'undefined') {
				a[v] = 1;
			}
		};
		data.length = 0;
		for ( var i in a) {
			data[data.length] = i;
		}
		return data;
	}
	
	//获取课程名称
	function	getcursename(curseid){
		var resultname=null;
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetCurseNameBymajorAction?curseid="+curseid,
		success : function(result) {
			result = eval(result);
			resultname=result;
		},
		error:function(){
		}
	});
		return resultname;
	
}

	
	//查询该成员对哪些试卷库有权限
	var member=null;
	var number=null;
	function get_authorized_epdb(){
		var authorized_epdblist=null;
		//获取成员类型
		var memberoptions = document.getElementById("selectmembertype").options;
		var memberindex = document.getElementById("selectmembertype").selectedIndex;
		var memberselectedOption = memberoptions[memberindex];
		 member = memberselectedOption.id;
		//判断是否选择角色
		if (member == '' || member == 'selectnonetype') {
			alert("请选择成员类型");
			return 1;
		}
		//获取教工号/学号
		number=document.getElementById("usernumber").value;
		//判断是否输入教工号/学号
			if (number ==null||number=='') {
			alert("请输入教工号/学号");
			return 1;
		}
		//判断教工号或者学号是否输入正确
		var checkuser;
		$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "CheckNumberAction?member="+member+"&number="+number,
		success : function(result) {
			result = eval(result);
			checkuser=result;
		},
		error:function(){
		}
	});
		if(checkuser.length<1){
			alert("无此用户，请重新输入教工号或学号");
			return 1;
		}
		
		//查询该角色成员的权限
		$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetAuthorizedEPDBByuserAction?member="+member+"&number="+number,
		success : function(result) {
			result = eval(result);
			authorized_epdblist=result;
		},
		error:function(){
		}
	});
		return authorized_epdblist;
	}
	
	//将有权限的试卷库设置为选中状态
	function SetAuthorized(){
		var authorized_epdb=get_authorized_epdb();
		for(var	a=0;a<authorized_epdb.length;a++){
			var authorized_epdb_id=authorized_epdb[a].epdb_id;//已授权的试卷库
			if(document.getElementById(authorized_epdb_id))
			document.getElementById(authorized_epdb_id).checked=true;    //将已授权的选上  
	}
	}
	//保存配置
	function SaveConfig(){
		//获取所有的Checkbox
		if (course_id != '' &&course_id != 'SelectNoneCourse') {
		var authorized_epdb=[];
		//var checkbox = document.getElementsByName('box[]');
		for(var	t=0;t<epdblistdata.length;t++){
			var epdb_id=epdblistdata[t].epdb_id;
			if(document.getElementById(epdb_id).checked){
				authorized_epdb[authorized_epdb.length]=epdb_id;
			}
		}
		//保存配置
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "SaveEPDBAuthorityConfigByuserSelectcourseAction?authorized_epdb="+authorized_epdb+"&number="+number+"&member="+member+"&courseid="+course_id+"&majorid="+major_id,
			success : function(responseText, statusText) {
				alert(responseText.message);
				document.location.href = "authority_user.jsp";
			},
			error:function(){
			}
		});
		}else{
			var authorized_epdb=[];
			//var checkbox = document.getElementsByName('box[]');
			for(var	t=0;t<epdblistdata.length;t++){
				var epdb_id=epdblistdata[t].epdb_id;
				if(document.getElementById(epdb_id).checked){
					authorized_epdb[authorized_epdb.length]=epdb_id;
				}
			}
			//保存配置
			$.ajax( {
				type : "post",
				dataType : "json",
				async : false,
				url : "SaveEPDBAuthorityConfigByuserSelectnonecourseAction?authorized_epdb="+authorized_epdb+"&number="+number+"&member="+member+"&majorid="+major_id,
				success : function(responseText, statusText) {
					alert(responseText.message);
					document.location.href = "authority_user.jsp";
				},
				error:function(){
				}
			});
		}
	}
	
	//按查询结果显示试题库
	var epdblistdata = null;
	var course_id=null;
	function showTable() {
		//获取该角色成员的权限
		var authorized_epdb=get_authorized_epdb();
		if(authorized_epdb==1){
			return;
		}
		//判断是否选择专业
		if (major_id == null) {
			alert("请选择专业");
			return;
		}
		//判断是否选择课程
		var options = document.getElementById("SelectCurse").options;
		var index = document.getElementById("SelectCurse").selectedIndex;
		var selectedOption = options[index];
		course_id=selectedOption.id;
		//var epdblistdata = null;
		//若没有选择课程，查找相应试卷库
		if (selectedOption.id == '' || selectedOption.id == 'SelectNoneCourse') {
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "GetEPDBBymajorAction?majorid=" + major_id,
				success : function(result) {
					result = eval(result);
					epdblistdata = result;
					//计算课程数，存储对应课程id
					var curse = [];
					var uniquecurse = [];
					var cursename=null;
					var tablecontent='';
					for ( var i = 0; i < epdblistdata.length; i++) {
						curse[curse.length] = epdblistdata[i].curse_id;
					}
					//去除课程数组中重复的元素
					uniquecurse = unique(curse);
					//获取课程名称
					cursename=getcursename(uniquecurse);
					//根据课程数生成多个表格
					$("#cursetable ").empty();
				 	for ( var i = 0; i < cursename.length; i++) {
						var curse_id = cursename[i].curse_id;
						var curse_name=cursename[i].curse_name;
						tablecontent+='<table class="tableList"><tr class="tr1"><td align=center>'+curse_name+'</td></tr><tr><td><table align=center><tr>';
						var s=0;//控制每行显示5个数据
							for(var	j=0;j<epdblistdata.length;j++){
								if(epdblistdata[j].curse_id==curse_id){
									var epdbname=epdblistdata[j].epdb_name;
									var epdbid=epdblistdata[j].epdb_id;
									tablecontent+='<td style="border: 0; width: 140px" ><input type="checkbox" id="'+epdbid+'" name="box[]">'+epdbname+'</td>';
									s=s+1;	
									if(s==5){
										tablecontent+='</tr><tr>';
										s=0;
									}
								}
							}
							tablecontent+='</tr></table></td></tr></table>';
					}
				$("#cursetable ").append(tablecontent);
				$("#cursetable ").append("<input type=\"button\" value=\"提交\" class=\"button\" onclick=\"SaveConfig();\"> ");
				//将有权限的试题库社设置为选中状态
				SetAuthorized();
				},
				error : function() {
				}
			});
		}else{
			//获取课程名称
			var subjectname=null;
			var tablecontent='';
			$.ajax( {
				type : "post",
				dataType : "json",
				async : false,
				url : "GetCurseNameBycurseAction?curseid="+selectedOption.id,
				success : function(result) {
					subjectname=result[0].curse_name;
				},
				error:function(){
				}
			});
		//获取试题库信息
			//var epdb=null;
			$.ajax( {
				type : "post",
				dataType : "json",
				async : false,
				url : "GetEPDBBycurseAction?curseid="+selectedOption.id,
				success : function(result) {
					result = eval(result);
					epdblistdata = result;
				},
				error:function(){
				}
			});
			
		//显示表格
			$("#cursetable ").empty();
			tablecontent+='<table class="tableList"><tr class="tr1"><td align=center>'+subjectname+'</td></tr><tr><td><table align=center><tr>';
			var s=0;//控制每行显示5个数据
			for(var	j=0;j<epdblistdata.length;j++){
					var epdbname=epdblistdata[j].epdb_name;
					var epdbid=epdblistdata[j].epdb_id;
					tablecontent+='<td style="border: 0; width: 140px" ><input type="checkbox" id="'+epdbid+'" name="box[]">'+epdbname+'</td>';
					s=s+1;	
					if(s==5){
						tablecontent+='</tr><tr>';
						s=0;
						}
					}
			tablecontent+='</tr></table></td></tr></table>';
			$("#cursetable ").append(tablecontent);
				$("#cursetable ").append("<input type=\"button\" value=\"提交\" class=\"button\" onclick=\"SaveConfig();\"> ");
			//将有权限的试题库社设置为选中状态
			SetAuthorized();
		}
	}
</script>
<title>Insert title here</title>
</head>
<body><jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperDbManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="#">试卷库管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">试卷库权限管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">按用户授权</a>
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
		<div class="div_style2">
		<fieldset>
		<legend>目标成员</legend>
			<table>
				<tr>
					<td nowrap>成员类型： <select id="selectmembertype"
						name="selectmembertype" style="font-size: 12px">
							<option selected="selected" id="selectnonetype">-请选择-</option>
							<option id="teacher">老师</option>
							<option id="student">学生</option>
					</select>
					</td>
					<td nowrap="nowrap">&nbsp;&nbsp;教工号/学号:</td>
					<td><input type="text" size=8 id="usernumber"
						name="usernumber"></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
		<legend>授权试卷库</legend>
		<table style="font-size: 12px" class="tableheight">
			<tr>

				<td width="20">&nbsp;<img
					src="image/refer.gif" width="20" height="18" /></td>
				<td  nowrap>专业：</td>
				<td id="SelectBasic"><div id="SpecialField"></div></td>
				<td nowrap>课程：</td>
				<td><select id="SelectCurse"
					style="font-size: 12px"><option selected="selected">--请选择--</option>
				</select></td>
				<td><input type="button" class="button" value="查询"
					onclick="showTable()"></td>
			</tr>
		</table>
		 <div>
			<div id="cursetable"align="center" style="margin-top: 10px;">
				
			</div>
		</div> 		
		</fieldset>


	</div>

	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	<script>
	getSchoolStructureList();
	loadMenu("SpecialField",SchoolStructure,GetCourseBySpecialfield,"College");
</script>
</body>
</html>