<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.sql.*,javax.sql.rowset.serial.*"
	import="java.io.*" import="javax.servlet.*"
	import="javax.servlet.http.*" import="java.util.*"%>
<%@ page import="java.text.*" import="javax.servlet.ServletException"
	import="org.apache.commons.fileupload.*"%>
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
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<!-- <script type="text/javascript" src="progress-bar-pager.js"></script> -->
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../../servJs/CreateMultiMenu.js" type="text/javascript"></script>

<style type="text/css">
#container {
	margin: 0 auto;
	width: 824px;
}

#act_divLine {
	margin-top: 5px;
	background: url(../source/divLine.gif);
	width: 784px;
	margin-left: 20px
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
<!-- YUI -->
<link rel="stylesheet" type="text/css"
	href="../js/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css"
	href="../js/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript"
	src="../js/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript"
	src="../js/yui/build/datasource/datasource-beta-min.js"></script>
<script type="text/javascript"
	src="../js/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../js/yui/build/json/json-min.js"></script>
<script type="text/javascript"
	src="../js/yui/build/history/history-min.js"></script>
<script type="text/javascript"
	src="../js/yui/build/element/element-beta-min.js"></script>
<script type="text/javascript"
	src="../js/yui/build/datatable/datatable-beta-min.js"></script>


<link rel="stylesheet" type="text/css"
	href="../js/yui/build/container/assets/skins/sam/container.css" />
<script type="text/javascript"
	src="../js/yui/build/container/container-min.js"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<script type="text/javascript" src="../js/jquery-1.5.2.min.js"></script>
<script type="text/javascript" src="../js/jquery.rotate.js"></script>
<script type="text/javascript" src="../js/jquery.form.js"></script>


<!-- YUI-END -->

<script>
	//获取需要添加成员的角色列表
	function RoleList() {
		$.ajax( {
			type : "post",
			dataType : "json",
			async : false,
			url : "GetRoleList",
			success : function(result) {
				//TeacherList	=	result.data;
				$("#SelectRole ").empty();
				$("#SelectRole").append("<option value=\"\">--请选择--</option>");
				for(var	i=0;i<result.data.length;i++){
					$("#SelectRole").append("<option value=\""+result.data[i].role_id+"\">"+
							result.data[i].name+"</option>");
				}
			},
			error:function(){
			}
		});
	}

	//生成教师机构多级菜单
	function getTeacherDepartmentId() {

		//$("#SchoolStructureId").value="";
		//alert(document.getElementById("TeacherDepartmentId").value);
		document.getElementById("TeacherDepartment").innerHTML = this.innerHTML;
		document.getElementById("TeacherDepartmentId").value = this.id;

	}

	var TeacherDepartmentNodes = null;
	function getTeacherDepartList() {
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "TeacherDepartmentOptionsGetAction",
			success : function(result) {
				TeacherDepartmentNodes = result.data;
			},
			error : function() {
			}
		});
	}

	//生成学生机构多级菜单
	function getStudentDepartmentId() {
		document.getElementById("StudentDepartmentId").value = this.id;
		//alert(document.getElementById("StudentDepartmentId").value);
	}

	var StudentDepartmentNodes = null;
	function getStudentDepartList() {
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "StudentDepartmentOptionsGetAction",
			success : function(result) {
				StudentDepartmentNodes = result.data;
			},
			error : function() {
			}
		});
	}

	// 根据成员类型选择不同的成员所属机构
	function loadList(obj) {
		if (obj.value == "teacher") {
			document.getElementById("td1").style.display = "block";
			document.getElementById("td2").style.display = "block";
			document.getElementById("td3").style.display = "none";
			document.getElementById("td4").style.display = "none";
		} else if (obj.value == "student") {

			document.getElementById("td1").style.display = "none";
			document.getElementById("td2").style.display = "none";
			document.getElementById("td3").style.display = "block";
			document.getElementById("td4").style.display = "block";
		}
	}

	//根据添加教师所属的机构查询出所有成员
	function QureyTeacherList() {
		//alert(document.getElementById("SelectCurse").value);
		if (document.getElementById("TeacherDepartmentId").value == "") {
			alert("请选择所要查询的教师机构");
			return;
		}
		SelectedSpecialFieldId = document.getElementById("TeacherDepartmentId").value;
		CreateDataTableForTeacher(SelectedSpecialFieldId);
	}
	function CreateDataTableForTeacher(SpecialFieldId) {
		(function() {
			var timestamp = (new Date()).valueOf();
			var myDataSource = new YAHOO.util.DataSource(
					"TeacherListDataGetAction?FieldId=" + SpecialFieldId
							+ "&timestamp=" + timestamp + "&");
			myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
			/** 
			 * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
			 */
			myDataSource.responseSchema = {
				resultsList : "records",
				fields : [ "user_id", "school_id", "name", "gender",
						"birthday", "id", "email", "address", "rn" ],
				metaFields : {
					totalRecords : "totalRecords",
					paginationRecordOffset : "startIndex",
					sortKey : "sort",
					sortDir : "dir"
				}
			};

			// 自定义内容格式化方法   
			YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord,
					oColumn, oData) {
				var Id = oRecord.getData("user_id");
				var Name = oRecord.getData("name");
				var SchoolId = oRecord.getData("school_id");
				var Gender = oRecord.getData("gender");
				var Birthday = oRecord.getData("birthday");
				var Identicard = oRecord.getData("id");
				var Email = oRecord.getData("email");
				var Address = oRecord.getData("address");
			}
			YAHOO.widget.DataTable.Formatter.checkbox = function(elCell,
					oRecord, oColumn, oData) {
				var bChecked = oData;
				bChecked = (bChecked) ? " checked=\"checked\"" : "";
				elCell.innerHTML = "<input name=\"CheckBoxs\" id = \""
						+ oRecord.getData('user_id') + "\" type=\"checkbox\""
						+ bChecked + " class=\"yui-dt-checkbox\" />";

			}
			/** 
			 * 这里是要显示的列的定义，这里定义了多少个key,页面就会显示多少列 
			 * 要显示远程内容的那些列的key值与上文中fields里的内容相对应 
			 * sortable设置本列是否可以点击列头排序 
			 */
			var myColumnDefs = [ {
				key : "check",
				label : "",
				formatter : "checkbox"
			}, {
				key : "rn",
				label : "行号"
			}, {
				key : "school_id",
				label : "教工号"
			}, {
				key : "name",
				label : "教师姓名"
			}, {
				key : "gender",
				label : "性别"
			}, {
				key : "birthday",
				label : "出生日期"
			}, {
				key : "email",
				label : "邮箱"
			}, {
				key : "address",
				label : "办公地址"
			},

			];
			/** 
			 * 生成请求URL,每次翻页或排序时会自动发出请求 
			 */
			var generateRequest = function(oState, oSelf) {
				oState = oState || {
					pagination : null,
					sortedBy : null
				};
				var sort = (oState.sortedBy) ? oState.sortedBy.key : "null";
				var dir = (oState.sortedBy && oState.sortedBy.dir === YAHOO.widget.DataTable.CLASS_DESC) ? "desc"
						: "asc";
				var startIndex = (oState.pagination) ? oState.pagination.recordOffset
						: 0;
				var rowsPerPage = (oState.pagination) ? oState.pagination.rowsPerPage
						: 15;
				return "&sort=" + sort + "&dir=" + dir + "&startIndex="
						+ startIndex + "&results=" + rowsPerPage;
			};
			/** 
			 * 自定义的表格配置 
			 */
			var myConfigs = {
				generateRequest : generateRequest,
				initialRequest : generateRequest(), // 初始化表格   
				dynamicData : true,
				sortedBy : {
					key : "null",
					dir : YAHOO.widget.DataTable.CLASS_ASC
				},
				// 给表格添加翻页器   
				paginator : new YAHOO.widget.Paginator(
						{
							// 每页的数据条数   
							rowsPerPage : 15,
							// 翻页器本地化   
							lastPageLinkLabel : "末页",
							firstPageLinkLabel : "首页",
							previousPageLinkLabel : "上一页",
							nextPageLinkLabel : "下一页",
							containers : [ 'dt-pag-nav' ],
							// 自定义翻页器内容   
							template : "{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink} 跳转到{JumpToPageDropdown}页　每页显示{RowsPerPageDropdown}行",
							// 每页显示多少行的可选值   
							rowsPerPageOptions : [ 1, 15, 30, 45, 60 ]
						}),
				paginationEventHandler : YAHOO.widget.DataTable.handleDataSourcePagination
			};
			// 新建一个表格，第一个参数是你页面上的div的id.表格会显示在那个div中。   
			YAHOO.example.container.myDataTable = new YAHOO.widget.DataTable(
					"serverintegration", myColumnDefs, myDataSource, myConfigs);

		})();
	}
	//根据学生的所属机构查询出其所有成员

	function QureyStudentList() {
		//alert(document.getElementById("SelectCurse").value);
		if (document.getElementById("StudentDepartmentId").value == "") {
			alert("请选择所要查询的学生机构");
			return;
		}
		SelectedSpecialFieldId = document.getElementById("StudentDepartmentId").value;
		CreateDataTableForStudent(SelectedSpecialFieldId);
	}
	function CreateDataTableForStudent(SpecialFieldId) {
		(function() {
			var timestamp = (new Date()).valueOf();
			var myDataSource = new YAHOO.util.DataSource(
					"StudentListDataGetAction?FieldId=" + SpecialFieldId
							+ "&timestamp=" + timestamp + "&");
			myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
			/** 
			 * 设置Fields以摘出页面上要用到的数据列，这里的列不一定全部会被显示在页面上。决定哪些列显示的语句在下面。 
			 */
			myDataSource.responseSchema = {
				resultsList : "records",
				fields : [ "user_id", "school_id", "name", "id", "address",
						"telephone", "rn" ],
				metaFields : {
					totalRecords : "totalRecords",
					paginationRecordOffset : "startIndex",
					sortKey : "sort",
					sortDir : "dir"
				}
			};

			// 自定义内容格式化方法   
			YAHOO.widget.DataTable.Formatter.Delete = function(elCell, oRecord,
					oColumn, oData) {
				var Id = oRecord.getData("user_id");
				var Name = oRecord.getData("name");

				var SchoolId = oRecord.getData("school_id");
				var Identicard = oRecord.getData("id");
				var Telephone = oRecord.getData("telephone");
				var Address = oRecord.getData("address");

			}
			YAHOO.widget.DataTable.Formatter.checkbox = function(elCell,
					oRecord, oColumn, oData) {
				var bChecked = oData;
				bChecked = (bChecked) ? " checked=\"checked\"" : "";
				elCell.innerHTML = "<input name=\"CheckBoxs\" id = \""
						+ oRecord.getData('user_id') + "\" type=\"checkbox\""
						+ bChecked + " class=\"yui-dt-checkbox\" />";

			}
			/** 
			 * 这里是要显示的列的定义，这里定义了多少个key,页面就会显示多少列 
			 * 要显示远程内容的那些列的key值与上文中fields里的内容相对应 
			 * sortable设置本列是否可以点击列头排序 
			 */
			var myColumnDefs = [ {
				key : "check",
				label : "",
				formatter : "checkbox"
			}, {
				key : "rn",
				label : "行号"
			}, {
				key : "school_id",
				label : "学号"
			}, {
				key : "name",
				label : "学生姓名"
			}, {
				key : "telephone",
				label : "联系电话"
			}, {
				key : "address",
				label : "联系地址"
			}, {
				key : '操作',
				lable : "",
				formatter : "Delete"
			} ];
			/** 
			 * 生成请求URL,每次翻页或排序时会自动发出请求 
			 */
			var generateRequest = function(oState, oSelf) {
				oState = oState || {
					pagination : null,
					sortedBy : null
				};
				var sort = (oState.sortedBy) ? oState.sortedBy.key : "null";
				var dir = (oState.sortedBy && oState.sortedBy.dir === YAHOO.widget.DataTable.CLASS_DESC) ? "desc"
						: "asc";
				var startIndex = (oState.pagination) ? oState.pagination.recordOffset
						: 0;
				var rowsPerPage = (oState.pagination) ? oState.pagination.rowsPerPage
						: 15;
				return "&sort=" + sort + "&dir=" + dir + "&startIndex="
						+ startIndex + "&results=" + rowsPerPage;
			};
			/** 
			 * 自定义的表格配置 
			 */
			var myConfigs = {
				generateRequest : generateRequest,
				initialRequest : generateRequest(), // 初始化表格   
				dynamicData : true, // Enables dynamic server-driven data   
				sortedBy : {
					key : "null",
					dir : YAHOO.widget.DataTable.CLASS_ASC
				},
				// 给表格添加翻页器   
				paginator : new YAHOO.widget.Paginator(
						{
							// 每页的数据条数   
							rowsPerPage : 15,
							// 翻页器本地化   
							lastPageLinkLabel : "末页",
							firstPageLinkLabel : "首页",
							previousPageLinkLabel : "上一页",
							nextPageLinkLabel : "下一页",
							containers : [ 'dt-pag-nav' ],
							// 自定义翻页器内容   
							template : "{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink} 跳转到{JumpToPageDropdown}页　每页显示{RowsPerPageDropdown}行",
							// 每页显示多少行的可选值   
							rowsPerPageOptions : [ 1, 15, 30, 45, 60 ]
						}),
				paginationEventHandler : YAHOO.widget.DataTable.handleDataSourcePagination
			};
			// 新建一个表格，第一个参数是你页面上的div的id.表格会显示在那个div中。   
			YAHOO.example.container.myDataTable = new YAHOO.widget.DataTable(
					"serverintegration", myColumnDefs, myDataSource, myConfigs);
		})();
	}

	function BulkAddMember() {
		var SelectRoleId = document.getElementById("SelectRole").value;
		//alert(SelectRoleId);
		if (SelectRoleId == "") {
			alert("请选择需要添加成员的角色");
			return;
		}
		document.getElementById("RoleListId").value = SelectRoleId;

		var CheckBoxsList = document.getElementsByName("CheckBoxs");

		var flag = false;
		var param = "";

		for ( var i = 0; i < CheckBoxsList.length; i++) {
			if (CheckBoxsList[i].checked) {
				if (flag == false) {
					//alert(CheckBoxsList[i].type);
					param += CheckBoxsList[i].id;
					flag = true;
				} else {

					param += " " + CheckBoxsList[i].id;
				}
			}
		}
		if (!flag) {
			alert("请至少选择一项需要添加的数据");
			return;
		}

		document.getElementById("BulkMemberId").value = param;

		if (confirm("确认添加新的角色成员")) {
			var options = {
				type : "post",
				dataType : "json",
				success : ShowSuccess,
			}
			$('#AddRoleMemberForm').ajaxSubmit(options);
			return false;
		}
	}

	function ShowSuccess(responseText, statusText) {
		if (responseText != null && responseText != "") {
			alert(responseText.text);
			document.location.href = "addRoleMember.jsp";
		} else {
			alert("添加成员失败");

		}
	}
</script>

</head>

<body class=" yui-skin-sam">
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/sysManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="../functionList/HomePage.html">首页</a>&nbsp;&gt;&gt;&nbsp;<a
			href="../functionList/sysManage.html">系统管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">角色管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">添加角色成员</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div id="act_content2">
		<form id="AddRoleMemberForm" name="AddRoleMemberForm"
			action="AddRoleMemberAction" method="post">
			<table width="100%" class="CContent">

				<tr>
					<td width="170px">所属角色列表： <select name="SelectRole"
						id="SelectRole">
							<option value="">--请选择--</option>
					</select> <input type="hidden" name="RoleListId" id="RoleListId" value="">
					</td>
					<td width="120px">成员类型： <select name="MemberType"
						id="MemberType" onchange="loadList(this)">
							<option value="teacher">教师</option>
							<option value="student">学生</option>
					</select>
					</td>
					<td id="td1" style="width: 80px">
						<div id="SelectTeacherDepartment"></div> <input type="hidden"
						name="TeacherDepartmentId" id="TeacherDepartmentId" value="">
					</td>
					<td id="td2" style="position: relative;"><input type="button"
						value="查询成员" class="button" onclick="QureyTeacherList();" /></td>
					<td id="td3" style="width: 80px; display: none"><div
							id="SelectStudentDepartment"></div> <input type="hidden"
						name="StudentDepartmentId" id="StudentDepartmentId" value="">
					</td>
					<td id="td4" style="position: relative; display: none"><input
						type="button" value="查询成员" class="button"
						onclick="QureyStudentList();" /></td>
					<td><input type="hidden" name="BulkMemberId"
						id="BulkMemberId" value=""> <input type="button"
						value="添加成员" class="button" onclick="BulkAddMember()" /></td>
				</tr>
			</table>
		</form>
		<!-- 半透罩，用来锁定屏幕 -->
		<div id="fade" class="black_overlay"></div>
		<div id="serverintegration"></div>
		<div id="dt-pag-nav"></div>
	</div>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>

	<script>
		RoleList();
		getTeacherDepartList();
		loadMenu("SelectTeacherDepartment", TeacherDepartmentNodes,
				getTeacherDepartmentId, "TeacherDepartment");
		getStudentDepartList();
		loadMenu("SelectStudentDepartment", StudentDepartmentNodes,
				getStudentDepartmentId, "StudentDepartment");
	</script>
	<script>
		//初始化修改信息对话框
		YAHOO.namespace("example.container");
		function init() {
			YAHOO.example.container.panel1 = new YAHOO.widget.Panel("panel1", {
				width : "600px",
				visible : false,
				constraintoviewport : true,
				close : false
			});
			YAHOO.example.container.panel1.render();
		}
		YAHOO.util.Event.addListener(window, "load", init);
	</script>
</body>
</html>