<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link rel="stylesheet" type="text/css" href="../esM/css/menu.css">
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
<script src="../js/TreeChangeStatue.js"></script>
<link rel="stylesheet" type="text/css" href="../css/tree.css">
<link href="../esM/css/style.css" rel="stylesheet" type="text/css" />
<style>

</style>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/appManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->	
	
	<div id="act_top">
		<a href="../functionList/appManage.jsp">应用管理</a>&nbsp;&gt;&gt;&nbsp;课程教材管理&nbsp;&gt;&gt;&nbsp;<a target="mainFrame" href="ChapterManage.jsp">章节管理</a>&nbsp;</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>	


<div class="div_style3" style="margin-left: 40px;margin-right: 40px">
		<table>
			<tr>
				<td style="border:0;height:50px" >
	  				<table width="100%" align="left" style="font-size:12px">
	  					<tr >
							<td>功能选项：
								<a href="#">添加子节点</a>
								<strong>|</strong>&nbsp;<a href="#">添加前置节点</a>
								<strong>|</strong>&nbsp;<a href="#">添加后置节点</a>
								<strong>|</strong>&nbsp;<a href="#">修改节点</a>
								<strong>|</strong>&nbsp;<a href="#">删除节点</a>
								<strong>|</strong>&nbsp;<a href="#">剪切节点</a>
								<strong>|</strong>&nbsp;<a href="#">前置粘帖节点</a>
								<strong>|</strong>&nbsp;<a href="#">后置粘帖节点</a>
								<strong>|</strong>&nbsp;<a href="#">粘帖为子节点</a>
								</td>
						</tr>
					</table>
				</td>
			</tr>	
		</table>
		
<div id="treebody" class="div_style3">

		<div class="TreeMenu">
				<ul id="ul">
					<li class="Opened" id="treeL1"><a href="#"
						onclick="javascript:ChangeStatus(this);"><span><img
								class=s src="../source/ad.png" />Unit 1 A Technological
								Revolution in Education</span></a>
						<ul>
							<li id="treeL2"><a href="#" id="A1"><span><img
										class=s src="../source/2.gif" />knowledge point a</span></a></li>
							<li id="treeL2"><a href="#" id="A1"><span><img
										class=s src="../source/2.gif" />knowledge point b</span></a></li>
						</ul></li>
					<li class="Opened" id="treeL1"><a href="#"
						onclick="javascript:ChangeStatus(this);"><span><img
								class=s src="../source/ad.png" />Unit 2 Do We Really Want
								Eternal Life? </span></a>
						<ul>
							<li id="treeL2"><a href="#" id="A1"><span><img
										class=s src="../source/2.gif" />knowledge point c</span></a></li>
							<li id="treeL2"><a href="#" id="A1"><span><img
										class=s src="../source/2.gif" />knowledge point d</span></a></li>
						</ul></li>
					<li class="Opened" id="treeL1"><a href="#"
						onclick="javascript:ChangeStatus(this);"><span><img
								class=s src="../source/ad.png" /> Unit 3 An Introduction to Sales Promotion</span></a>
						<ul>
							<li id="treeL2"><a href="#" id="A1"><span><img
										class=s src="../source/2.gif" />knowledge point e</span></a></li>
							<li id="treeL2"><a href="#" id="A1"><span><img
										class=s src="../source/2.gif" />knowledge point f</span></a></li>
						</ul></li>						
				</ul>
			</div>
	</div>
	<!-- <div id="bottom" class="div_style4"></div> -->		
		
		
		
<!-- 		<div id="body">	
			<a id="expand_link" href="javascript:menu_expand();"><u><span id="expand_text">展开</span></u></a>
			<ul id="menu">
				<li class="L1"><a href="javascript:c(m01);" id="m01"><span><img src="../esM/images/ico/2.gif" align="middle"/> Unit 1 A Technological Revolution in Education </span></a></li>
					<ul id="m01d" style="display:none;" class="U1">
						<li class="L22"><a href="#" ><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point a</span></a></li>
						<li class="L22"><a href="#"><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point b</span></a></li>
						<li class="L22"><a href="#"><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point c</span></a></li>
					</ul>
				<li class="L1"><a href="javascript:c(m03);" id="m03"><span><img src="../esM/images/ico/2.gif" align="middle"/> Unit 2 Do We Really Want Eternal Life? </span></a></li>
					<ul id="m03d" style="display:none;" class="U1">
						<li class="L22"><a href="#" ><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point d</span></a></li>
						<li class="L22"><a href="#"><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point e</span></a></li>
						<li class="L22"><a href="#"><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point f</span></a></li>
					</ul>
				<li class="L1"><a href="javascript:c(m05);" id="m05"><span><img src="../esM/images/ico/2.gif" align="middle"/> Unit 3 An Introduction to Sales Promotion </span></a></li>
					<ul id="m05d" style="display:none;" class="U1">
						<li class="L22"><a href="#" ><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point g</span></a></li>
						<li class="L22"><a href="#"><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point h</span></a></li>
						<li class="L22"><a href="#"><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point i</span></a></li>
					</ul>
				<li class="L1"><a href="javascript:c(m09);" id="m09"><span><img src="../esM/images/ico/2.gif" align="middle"/> Unit 4 Work Still Has Value</span></a></li>
					<ul id="m09d" class="U1">
						<li class="L22"><a href="#" ><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point j</span></a></li>
						<li class="L22"><a href="#"><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point k</span></a></li>
						<li class="L22"><a href="#"><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point l</span></a></li>
					</ul>
				<li class="L1"><a href="javascript:c(m07);" id="m07"><span><img src="../esM/images/ico/2.gif" align="middle"/> Unit 5 Vancouver:A City Risen from the Forest </span></a></li>
					<ul id="m07d" class="U1">
						<li class="L22"><a href="#" ><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point m</span></a></li>
						<li class="L22"><a href="#"><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point n</span></a></li>
						<li class="L22"><a href="#"><span><img src="../esM/images/ico/2.gif" align="middle"/> knowledge point o</span></a></li>
					</ul>
			</ul>
		</div>
<div id="bottom"></div> -->
</div>
	
		<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	
</body>
</html>