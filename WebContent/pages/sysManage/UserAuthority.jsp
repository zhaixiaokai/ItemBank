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
<script type="text/javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" src="../../servJs/CheckValidation.js"></script>
<script type="text/javascript" src="../../servJs/NestedTables.js"></script>
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

<script type="text/javascript">
	
	//从数据库中获取系统功能列表
	function GetFunctionTree() {
		var FunctionTreeData = null;
		$.ajax({
			type : "post",
			dataType : "json",
			async : false,
			url : "FunctionGetTreeDataAction",
			success : function(result) {
				//alert(result);
				FunctionTreeData = result;
			},
			error : function() {
			}
		});
		return FunctionTreeData;
	}

	var ChildrenJson; //用于存储截取的子json串
	var FunctionJson;//用于存储整个功能树的json串
	
	//点击某一checkbox触发的事件
	function CheckBoxSelectEvent() {		
		ChildrenJsonGet(FunctionJson,this.id);	
		ForwardNodeStatus(ChildrenJson,this);	
		BackNodeStatus(FunctionJson,this,this);
	}
	
	// 根据点击的checkbox 的id 获取其之后的json串，并存储在全局变量ChildrenJson中
	function ChildrenJsonGet( json , id ){
	 		for( var i = 0; i < json.length; i++){
				var CheckBoxId="CB_"+json[i].id;
				// 迭代过程中，一旦某一json串的id与点击的checkbox的id相同，返回其之后的所有json串
				if(CheckBoxId==id){				
					ChildrenJson = json[i].children;				
				}		
				else if(json[i].children!= undefined )
					ChildrenJsonGet(json[i].children,id);	
			} 	
	}
		
	// 对得到的子json串进行遍历，实现父节点状态改变以后，子节点状态也发生相应的变化
	function ForwardNodeStatus(json,o) {
	if (json == undefined) {
		} 
	else {
			for ( var i = 0; i < json.length; i++) {
				var CheckBoxId = "CB_" + json[i].id;
				var CheckBox = document.getElementById(CheckBoxId);
			 	if (json[i].leaf == "true") {
					CheckBox.checked = o.checked;
				} else {
					CheckBox.checked = o.checked;
					ForwardNodeStatus(json[i].children, CheckBox);
				} 
			}
		}
	}
	
	//点击某一子checkbox以后，其所属的父checkbox也相应的改变
	function BackNodeStatus(json,checkbox,o){
		var flag=0;// flag表示是否在遍历整个functionTree时候找到所点击的checkbox的id，若找到则为1，否则为0	
		for(var i=0;i<json.length;i++){
			var CheckBoxId="CB_"+json[i].id;
			var CheckBox = document.getElementById(CheckBoxId);
			
			if(CheckBoxId==o.id){
				flag=1;	
				}
			else{
				if(json[i].children!= undefined ){
					flag = BackNodeStatus(json[i].children,CheckBox,o);
					if(flag == 1){
						if(o.checked==false){//如果某一节点被取消
							var k;
							for(k=0;k<json[i].children.length;k++){
								//alert(k);
								//alert("i am jaon "+json[i].children.length);
								if(document.getElementById("CB_"+json[i].children[k].id).checked==true){
									break;
								}						
							}
							
							if(k==json[i].children.length){
								
								CheckBox.checked = false;
							}
						}
						else{//如果节点被选中
							CheckBox.checked = o.checked;
						}
					}				
				}		
			}
			  if(flag == 1){
				 break;  
			 } 
				
		}
		return flag;
	}
	
	//用于存储输入的学号和教工号对应的用户userid;
	var UserId="";
	//检查输入的学号和教工号的合法性，以及获取当前用户所拥有的权限;
	function CreateTree(){
		//将原来的生成的div内容进行清空
		$("#TableDiv").empty();
		$("#button").empty();
		//表格初始化函数的标志位进行清空
		createFlag=false;
		var MemberType=document.getElementById("MemberType").value;
		var UserNumber=document.getElementById("usernumber").value;
		//检查是否已经选择需要授权的成员类型和用户的学号和教工号
		if(MemberType==""){
			alert("请选择需要授权的成员类型！");
			return;
		}

		if (UserNumber == "") {
			alert("请输入需要授权的用户的学号/教工号！！！");
			return;
		}

		if (UserNumber.length > 20) {
			alert("教工号/学号 长度不能超过20！");
			return;
		}
		if (!CheckIfIsLetterNumber(UserNumber)) {
			alert("教工号/学号 必须为字母和数字组成！");
			return;
		}
		//检查输入的用户的学号和教工号是否合法，并根据用户的教工号和学号获取到用户的userid;
		
		$.ajax( {
			type : "post",
			dataType : "text",
			async : false,
			url : "CheckUserNumberAction",
			data:{"MemberType" : MemberType,
				"UserNumber":UserNumber,
				},
			success : function(result) {
				if(result=="该用户不存在！"){
					alert(result);
					return;
				}
				else{					
					UserId=result;				
				}			
			},
			error:function(){
			}
		});
		
		//alert(UserId);
		FunctionJson = GetFunctionTree();
		initTableContainer(FunctionJson,"TableDiv");
		$("#button").append("<input type=\"button\" value=\"提交\" class=\"button\" onclick=\"Save();\"> ");
		CreateMultiTableInterface(FunctionJson,"TableDiv",CheckBoxSelectEvent);
		
		
		//对于已经属于该用户的权限将其复选框勾选;
		var PresentAuthorityUser;
		$.ajax( {
			type : "post",
			dataType : "text",
			async : false,
			url : "PresentUserAuthorityGetAction",
			data:{
				"UserId":UserId,
				},
			success : function(result) {
				
				PresentAuthorityUser=result;//角色目前拥有的权限
				var PresentAuthorityUserIds=PresentAuthorityUser.split(",");
				var CheckBoxList = document.getElementsByName("CheckBoxes");
				for(var k=0;k < CheckBoxList.length; k++){
					TempId=CheckBoxList[k].id.substring(3);
					//alert(TempId);
					for(var i=0;i<PresentAuthorityUserIds.length;i++){
						
						//alert(PresentAuthorityRoleIds[i]);
						if(TempId==PresentAuthorityUserIds[i]){
							CheckBoxList[k].checked=true;
						}
					}
				}
				
				
			},
			error:function(){
			}
		});
	}
	function Save(){
		//获取页面上的所有checkbox对象
		var CheckBoxList = document.getElementsByName("CheckBoxes");
		//保存点击的checkbox的id
		var param = "";
		var flag = false;//点击的checkbox的个数
		var DeleteId = "";
		var DeleteCount = 0;
		
		for ( var i = 0; i < CheckBoxList.length; i++) {
			if (CheckBoxList[i].checked) {

				if (flag == false) {
					param += CheckBoxList[i].id.substring(3);
					flag = true;
				} else
					param += " " + CheckBoxList[i].id.substring(3);
			}
			else {
				if (DeleteCount == 0) {
					DeleteId += CheckBoxList[i].id.substring(3);
					DeleteCount++;
				} else
					DeleteId += " " + CheckBoxList[i].id.substring(3);
			}

		}
		if (confirm("确定添加？")) {
			$.ajax({
				type : "post",
				dataType : "json",
				url : "UserAuthorityAction",
				data : {
					"FunctionId" : param,
					"UserId":UserId,
					"DeleteId":DeleteId
				},
				success : function(result) {
					alert(result.text);
					document.location.href="UserAuthority.jsp";
				}
			});

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
			href="javascript:void(0)">权限管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">按用户授权</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div id="act_content2">
		<table height="50px" width="100%">
			<tr>
				<td width="180px">成员类型：<select id="MemberType"
					name="MemberType">
						<option value="">——请选择——</option>
						<option value="teacher">教师</option>
						<option value="student">学生</option>
				</select>
				</td>
				<td width="240px">教工号/学号：<input type="text" id="usernumber"
					name="usernumber">
				</td>
				<td><input type="button" class="button" value="确定"
					onclick="CreateTree();"></td>
			</tr>
		</table>

		<div id="TableDiv"></div>
		<div id="button" align="center"></div>
	</div>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>