<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<!-- 加上会有右侧滚动条痕迹~
<link rel="stylesheet" type="text/css" href="css/menu.css"> -->
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
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
//获取角色数据
function getroledata() {
	$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "GetRoleDataAction",
				success : function(result) {
					$("#selectrole ").empty();
					$("#selectrole").append(
							"<option value=\"\" id=\"selectnonerole\">--请选择--</option>");
					for ( var i = 0; i < result.data.length; i++) {
				
							$("#selectrole")
									.append(
											"<option id=\""+result.data[i].role_id+
					"\" value=\""+result.data[i].name+"\">"
													+ result.data[i].name
													+ "</option>");

						
					}
				},
				error : function() {
				}
			});

}


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

	
	//查询该角色下该成员对哪些试题库有权限
	var role=null;
	var member=null;
	function get_authorized_itembank(){
		var authorized_itembanklist=null;
		//获取角色
		 var roleoptions = document.getElementById("selectrole").options;
		var roleindex = document.getElementById("selectrole").selectedIndex;
		var roleselectedOption = roleoptions[roleindex];
		role = roleselectedOption.id;
		//判断是否选择角色
		if (role == '' || role == 'selectnonerole') {
			alert("请选择角色");
			return 1;
		}
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
		//查询该角色成员的权限
		$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetAuthorizedItembankByroleAction?role="+role+"&member="+member,
		success : function(result) {
			result = eval(result);
			authorized_itembanklist=result;
		},
		error:function(){
		}
	});
		return authorized_itembanklist;
	}
	
	/* //将有权限的试题库社设置为选中状态
	function SetAuthorized(authorized_itembank,itembanklistdata){
			for(var	a=0;a<authorized_itembank.length;a++){
				var authorized_es_id=authorized_itembank[a].itembank_id;//已授权的试题库
				
			}
	} */
/* 	function SetAuthorized(authorized_itembank,itembanklistdata){
		var m = document.getElementsByName('box[]');//所有chechbox
		for(var	t=0;t<itembanklistdata.length;t++){
			var es_id=itembanklistdata[t].itembank_id;//符合专业课程查询条件的试题库
			for(var	a=0;a<authorized_itembank.length;a++){
				var authorized_es_id=authorized_itembank[a].itembank_id;//已授权的试题库
				 if(authorized_es_id== es_id ) {
					m[t].checked = true;    //将已授权的选上  
		    	 }    
			}
			
		}
	} */
	
	/* //保存配置
	function SaveConfig(){
		//获取所有的Checkbox
		var authorized_es=[];
		var checkbox = document.getElementsByName('box[]');
		for(var	t=0;t<itembanklistdata.length;t++){
			var es_id=itembanklistdata[t].itembank_id;
			if(checkbox[t].checked){
				authorized_es[authorized_es.length]=es_id;
			}
		} */
	//将有权限的试题库设置为选中状态
	function SetAuthorized(){
		var authorized_itembank=get_authorized_itembank();
		for(var	a=0;a<authorized_itembank.length;a++){
			var authorized_itembank_id=authorized_itembank[a].itembank_id;//已授权的试题库
			if(document.getElementById(authorized_itembank_id))//防止结果为空
			document.getElementById(authorized_itembank_id).checked=true;    //将已授权的选上  
	}
	}
	
	
	//按查询结果显示试题库
	var course_id=null;
	var itembanklistdata = null;
	function showTable() {
	var options = document.getElementById("SelectCurse").options;
	var index = document.getElementById("SelectCurse").selectedIndex;
	var selectedOption = options[index];
	course_id=selectedOption.id;
		//获取该角色成员的权限
		var authorized_itembank=get_authorized_itembank();
	
		if(authorized_itembank==1){
			return;
		};
		//判断是否选择专业
		if (major_id == null) {
			alert("请选择专业");
			return;
		}
		//判断是否选择课程
/* 		var options = document.getElementById("SelectCurse").options;
		var index = document.getElementById("SelectCurse").selectedIndex;
		var selectedOption = options[index]; */
		//var itembanklistdata = null;
		//若没有选择课程，查找相应试题库
		if (selectedOption.id == '' || selectedOption.id == 'SelectNoneCourse') {
			$.ajax({
				type : "post",
				dataType : "json",
				async : false,
				url : "GetItembankBymajorAction?majorid=" + major_id,
				success : function(result) {
					result = eval(result);
					itembanklistdata = result;
					//计算课程数，存储对应课程id
					var curse = [];
					var uniquecurse = [];
					var cursename=null;
					var tablecontent='';
					for ( var i = 0; i < itembanklistdata.length; i++) {
						curse[curse.length] = itembanklistdata[i].curse_id;
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
							for(var	j=0;j<itembanklistdata.length;j++){
								if(itembanklistdata[j].curse_id==curse_id){
									var itembankname=itembanklistdata[j].itembank_name;
									var itembankid=itembanklistdata[j].itembank_id;
									tablecontent+='<td style="border: 0; width: 140px" ><input type="checkbox" id="'+itembankid+'" name="box[]">'+itembankname+'</td>';
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
			//var itembank=null;
			$.ajax( {
				type : "post",
				dataType : "json",
				async : false,
				url : "GetItembankBycurseAction?curseid="+selectedOption.id,
				success : function(result) {
					result = eval(result);
					itembanklistdata = result;
				},
				error:function(){
				}
			});
			
		//显示表格
			$("#cursetable ").empty();
			tablecontent+='<table class="tableList"><tr class="tr1"><td align=center>'+subjectname+'</td></tr><tr><td><table align=center><tr>';
			var s=0;//控制每行显示5个数据
			for(var	j=0;j<itembanklistdata.length;j++){
					var itembankname=itembanklistdata[j].itembank_name;
					var itembankid=itembanklistdata[j].itembank_id;
					tablecontent+='<td style="border: 0; width: 140px" ><input type="checkbox" id="'+itembankid+'" name="box[]">'+itembankname+'</td>';
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
	
	
	
	//保存配置
	function SaveConfig(){
		//判断是否选择课程
		//若选择课程
		if (course_id != '' &&course_id != 'SelectNoneCourse') {
		//获取所有的Checkbox
		var authorized_es=[];
		var checkbox = document.getElementsByName('box[]');
		for(var	t=0;t<itembanklistdata.length;t++){
			var itembank_id=itembanklistdata[t].itembank_id;
			if(document.getElementById(itembank_id).checked){
				authorized_es[authorized_es.length]=itembank_id;
			}
		}
		//保存配置
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "SaveAuthorityConfigByroleSelectcourseAction?authorized_es="+authorized_es+"&role="+role+"&member="+member+"&courseid="+course_id+"&majorid="+major_id,
			success : function(responseText, statusText) {
				alert(responseText.message);
				document.location.href = "authority_role.jsp";
			},
			error:function(){
			}
		});
		
	}else{
		//获取所有的Checkbox
		var authorized_es=[];
		var checkbox = document.getElementsByName('box[]');
		for(var	t=0;t<itembanklistdata.length;t++){
			var itembank_id=itembanklistdata[t].itembank_id;
			if(document.getElementById(itembank_id).checked){
				authorized_es[authorized_es.length]=itembank_id;
			}
		}
		//保存配置
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "SaveAuthorityConfigByroleSelectnonecourseAction?authorized_es="+authorized_es+"&role="+role+"&member="+member+"&majorid="+major_id,
			success : function(responseText, statusText) {
				alert(responseText.message);
				document.location.href = "authority_role.jsp";
			},
			error:function(){
			}
		});
	}
}
</script>
<title>Insert title here</title>
</head>
<body><jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/examDbManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="#">试题库管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">试题库权限管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">按角色授权</a>
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div id="act_content2">
	<fieldset>
	<legend>目标角色选择</legend>
		<table style="font-size: 12px" class="tableheight">
			<tr>
				<td nowrap>角色： <select id="selectrole" name="selectrole"
					style="font-size: 12px">
				</select>
				</td>
				<td nowrap>成员类型： <select id="selectmembertype"
					name="selectmembertype" style="font-size: 12px">
						<option selected="selected" id="selectnonetype">-请选择-</option>
						<option id="teacher">老师</option>
						<option id="student">学生</option>
				</select>
				</td>
			</tr>
		</table>
	
	</fieldset>
	<fieldset>
		<legend>授权试题库</legend>
		<table style="font-size: 12px" class="tableheight">
			<tr>
				
				<td width="20">&nbsp;<img src="image/refer.gif" width="20"
					height="18" /></td>
				<td nowrap>专业：</td>
				<td id="SelectBasic"><div id="SpecialField"></div></td>
				<td nowrap>课程：</td>
				<td><select id="SelectCurse" style="font-size: 12px"><option
							selected="selected">--请选择--</option>
				</select></td>
				<td><input type="button" class="button" value="查询"
					onclick="showTable()"></td>
			</tr>
		</table>
	
		<div>
			<div id="cursetable" align="center" style="margin-top: 10px;">

			</div>
		</div>
	</fieldset>
		

	</div>
<!-- 	<div id="act_content2">
		<table style="font-size: 12px" class="tableheight">
			<tr>
				<td nowrap>角色： <select id="selectrole" name="selectrole"
					style="font-size: 12px">
				</select>
				</td>
				<td nowrap>成员类型： <select id="selectmembertype"
					name="selectmembertype" style="font-size: 12px">
						<option selected="selected" id="selectnonetype">-请选择-</option>
						<option id="teacher">老师</option>
						<option id="student">学生</option>
				</select>
				</td>
				<td width="20">&nbsp;<img src="image/refer.gif" width="20"
					height="18" /></td>
				<td nowrap>专业：</td>
				<td id="SelectBasic"><div id="SpecialField"></div></td>
				<td nowrap>课程：</td>
				<td><select id="SelectCurse" style="font-size: 12px"><option
							selected="selected">--请选择--</option>
				</select></td>
				<td><input type="button" class="button" value="查询"
					onclick="showTable()"></td>
			</tr>
		</table>
		<div>
			<div id="cursetable" align="center" style="margin-top: 10px;">

			</div>
		</div>

	</div> -->

	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	<script>
	getroledata();
	getSchoolStructureList();
	loadMenu("SpecialField",SchoolStructure,GetCourseBySpecialfield,"College");
</script>

</body>
</html>