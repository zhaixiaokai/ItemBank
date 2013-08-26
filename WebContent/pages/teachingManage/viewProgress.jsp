<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../esM/css/table.css" />
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css" />
<link rel="stylesheet" type="text/css" href="../esM/css/menu.css">
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../esM/css/table.css" />
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
<script src="../js/TreeChangeStatue.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="../css/tree.css">
<script>
	function certainPage() {
		self.parent.frames["mainFrame"].location = "certainPage.jsp";
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="javascript:void(0)">教学管理</a>&nbsp;&gt;&gt;&nbsp;<a href="javascript:void(0)">教学进度管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">教学进度管理</a>	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div style="margin-left: 40px;">

		<table>
			<tr>
				<td style="border: 0; height: 50px">
					<table width="100%" align="left" style="font-size: 12px">
						<tr>
							<td><a href="addStru.jsp">添加子机构</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">添加前置机构</a> <strong>|</strong>&nbsp;<a href="javascript:void(0)">添加后置机构</a>
								<strong>|</strong>&nbsp;<a href="javascript:void(0)">修改机构</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">删除机构</a> <strong>|</strong>&nbsp;<a href="javascript:void(0)">剪切机构</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">前置粘帖</a> <strong>|</strong>&nbsp;<a href="javascript:void(0)">后置粘帖</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">粘帖为子机构</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>

	<div id="treebody" class="div_style2">

		<div class="TreeMenu">
			<ul id="ul">
					<li class="Opened" id="treeL1"><a href="javascript:void(0)"
					onclick="ChangeStatus(this);"><span><img
							class=s src="../source/ad.png" />第一学期</span></a>
					       <ul>
								<li id="treeL2"><a href="javascript:void(0)"
									onclick="ChangeStatus(this);" id="A1"><span><img
											class=s src="../source/ad.png" />期中</span></a>
								<ul>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点a</span></a></li>
			
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点b</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点c</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点d</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点e</span></a></li>
								</ul></li>
							</ul>
							<ul>
								<li id="treeL2"><a href="javascript:void(0)"
									onclick="ChangeStatus(this);" id="A1"><span><img
											class=s src="../source/ad.png" />期末</span></a>
								<ul>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点f</span></a></li>
			
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点g</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点h</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点i</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点j</span></a></li>
								</ul></li>
							</ul>
					<li class="Opened" id="treeL1"><a href="javascript:void(0)"
					onclick="ChangeStatus(this);"><span><img
							class=s src="../source/ad.png" />第二学期</span></a>
					       <ul>
								<li id="treeL2"><a href="javascript:void(0)"
									onclick="ChangeStatus(this);" id="A1"><span><img
											class=s src="../source/ad.png" />期中</span></a>
								<ul>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点a</span></a></li>
			
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点b</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点c</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点d</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点e</span></a></li>
								</ul></li>
							</ul>
							<ul>
								<li id="treeL2"><a href="javascript:void(0)"
									onclick="ChangeStatus(this);" id="A1"><span><img
											class=s src="../source/ad.png" />期末</span></a>
								<ul>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点f</span></a></li>
			
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点g</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点h</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点i</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点j</span></a></li>
								</ul></li>
							</ul>
					
					<li class="Opened" id="treeL1"><a href="javascript:void(0)"
					onclick="ChangeStatus(this);"><span><img
							class=s src="../source/ad.png" />第三学期</span></a>
					       <ul>
								<li id="treeL2"><a href="javascript:void(0)"
									onclick="ChangeStatus(this);" id="A1"><span><img
											class=s src="../source/ad.png" />期中</span></a>
								<ul>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点a</span></a></li>
			
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点b</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点c</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点d</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点e</span></a></li>
								</ul></li>
							</ul>
							<ul>
								<li id="treeL2"><a href="javascript:void(0)"
									onclick="ChangeStatus(this);" id="A1"><span><img
											class=s src="../source/ad.png" />期末</span></a>
								<ul>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点f</span></a></li>
			
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点g</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点h</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点i</span></a></li>
									<li id="treeL3"><a href="javascript:void(0)" id="A2"><span><img
												class=s src="../source/2.gif" />知识点j</span></a></li>
								</ul></li>
							</ul>
			</ul>
		</div>
	</div>
	<!-- <div id="bottom" class="div_style4"></div> -->

	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>