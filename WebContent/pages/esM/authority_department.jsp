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
//获取机构信息
	var	departmentlist=null;
function	getSchoolStructureList(memberid){
	if (memberid == '' || memberid == 'selectnonetype'||memberid==null) {
		alert("请选择成员类型");
		return;
	} 
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetSchoolStructureAction?membertypeid="+memberid,
		success : function(result) {
			departmentlist=result.data;
		},
		error:function(){
		}
	});
	//加载机构
	loadMenu("departmentlist",departmentlist,GetDepartment,"department");
}

function getelement(o){
	document.getElementById("department").innerHTML=o;
}
//获取机构id
var department_id=null;
function	GetDepartment(){
	getelement(this.innerHTML);
	department_id=this.id;
}


//获取专业
var	majorlist=null;
function	getMajorList(){
	$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "SchoolStructureOptionsGetAction",
		success : function(result) {
			majorlist=result.data;
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

//获取某专业下课程
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
	
	
	//递归获取该机构所有上级节点
	var UpperNode=[];
	var pid=null;
	function getuppernode(departmentid){
		if(pid==0){
			return;
		}
		for(var i=0;i<departmentlist.length;i++){
			if(departmentlist[i].id==departmentid){
				pid=departmentlist[i].pid;
				//若已到根节点则无需添加
				if(pid==0){
					break;
				}
				UpperNode[UpperNode.length]=pid;
			}
		}
		getuppernode(pid);
	}

	
	//查询该组织机构对哪些试题库有权限
	var member=null;
	function get_authorized_itembank(){
		//获取成员类型信息
		var options = document.getElementById("selectmembertype").options;
		var index = document.getElementById("selectmembertype").selectedIndex;
		var selectedOption = options[index];
		member=selectedOption.id;
		//判断是否选择成员
		if (member == '' || member == 'selectnonetype'||member==null) {
			alert("请选择成员类型");
			return 1;
		}
		//判断是否选择机构
		if (department_id == ''||department_id==null) {
			alert("请选择机构");
			return 1;
		}
		//获取所有上级节点
		getuppernode(department_id);
		//查询该角色成员的权限
		$.ajax( {
		type : "post",
		dataType : "json",
		async : false,
		url : "GetAuthorizedItembankBydepartmentAction?member="+member+"&departmentid="+department_id+"&uppernode="+UpperNode,
		success : function(result) {
			result = eval(result);
			authorized_itembanklist=result;
		},
		error:function(){
		}
	});
		return authorized_itembanklist;
	}
	
/* 	//将有权限的试题库社设置为选中状态
	function SetAuthorized(authorized_itembank,itembanklistdata){
		var m = document.getElementsByName('box[]');//所有chechbox
		for(var	t=0;t<itembanklistdata.length;t++){
			var es_id=itembanklistdata[t].itembank_id;//符合专业课程查询条件的试题库
			for(var	a=0;a<authorized_itembank.length;a++){
				var authorized_es_id=authorized_itembank[a];//已授权的试题库
				 if(authorized_es_id== es_id ) {
					m[t].checked = true;    //将已授权的选上  
		    	 }    
			}
			
		}
	}

	
	//保存配置
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
			url : "SaveAuthorityConfigBydepartmentSelectcourseAction?authorized_es="+authorized_es+"&departmentid="+department_id+"&member="+member+"&courseid="+course_id+"&majorid="+major_id,
			success : function(responseText, statusText) {
				alert(responseText.message);
				document.location.href = "authority_department.jsp";
			},
			error:function(){
			}
		});
		
	}else{
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
		url : "SaveAuthorityConfigBydepartmentSelectnonecourseAction?authorized_es="+authorized_es+"&departmentid="+department_id+"&member="+member+"&majorid="+major_id,
		success : function(responseText, statusText) {
			alert(responseText.message);
			document.location.href = "authority_department.jsp";
		},
		error:function(){
		}
	});
	}
	/* 	//获取所有的Checkbox
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
		url : "SaveAuthorityConfigBydepartmentAction?authorized_es="+authorized_es+"&departmentid="+department_id+"&member="+member,
		success : function(responseText, statusText) {
			alert(responseText.message);
			document.location.href = "authority_department.jsp";
		},
		error:function(){
		}
	}); */
	
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
			href="#">按机构授权</a>
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div class="div_style2">
	<fieldset>
		<legend>目标组织机构选择</legend>
		<table style="font-size: 12px" class="tableheight">
			<tr>
				<td nowrap>成员： <select id="selectmembertype" name="selectmembertype" style="font-size: 12px">
						<option selected="selected" id="selectnonetype" onclick="getSchoolStructureList('selectnonetype')" >-请选择-</option>
						<option id="teacher" onclick="getSchoolStructureList('teacher')" >老师</option>
						<option id="student" onclick="getSchoolStructureList('student')">学生</option>
				</select>
				<td nowrap>组织机构：</td>
				<td id="SelectBasic"><div id="departmentlist"></div></td>
			
			</tr>
		</table>
	</fieldset>
	<fieldset>
		<legend>授权试题库</legend>
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
	getMajorList();
	loadMenu("SpecialField",majorlist,GetCourseBySpecialfield,"College");
	</script>
</body>
</html>