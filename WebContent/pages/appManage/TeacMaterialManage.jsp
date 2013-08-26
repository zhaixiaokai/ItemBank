<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>纯CSS多级透明菜单</title>

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
		<a target="mainFrame" href="../functionList/appManage.jsp">应用管理</a>&nbsp;&gt;&gt;&nbsp;课程教材管理&nbsp;&gt;&gt;&nbsp;<a href="TeacMaterialManage.jsp">教材管理</a>	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>	
	
<div style="margin-left:25px;margin-top:10px">

<ul id="nav">
	<li><a href="#">学科列表&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
	<!--[if lte IE 6]><table><tr><td><![endif]-->
	<ul>
		<li><a href="#">经济学 &raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
		<!--[if lte IE 6]><table><tr><td><![endif]-->
		<ul>
			<li><a href="#">专业一&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
				<!--[if lte IE 6]><table><tr><td><![endif]-->
				<ul>
					<li><a href="TMMmaterialList.jsp">科目一</a></li>
					<li><a href="#">科目二</a></li>
					<li><a href="#">科目三</a></li>
					<li><a href="#">科目四</a></li>
					<li><a href="#">科目五</a></li>
					<li><a href="#">科目六</a></li>
					<li><a href="#">科目七</a></li>
					<li><a href="#">科目八</a></li>
				</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
			</li>
			<li><a href="#">专业二&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
				<!--[if lte IE 6]><table><tr><td><![endif]-->
				<ul>
					<li><a href="#">科目一</a></li>
					<li><a href="#">科目二</a></li>
					<li><a href="#">科目三</a></li>
					<li><a href="#">科目四</a></li>
					<li><a href="#">科目五</a></li>
					<li><a href="#">科目六</a></li>
					<li><a href="#">科目七</a></li>
					<li><a href="#">科目八</a></li>
				</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
			</li>
			<li><a href="#">专业三&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
				<!--[if lte IE 6]><table><tr><td><![endif]-->
				<ul>
					<li><a href="#">科目一</a></li>
					<li><a href="#">科目二</a></li>
					<li><a href="#">科目三</a></li>
					<li><a href="#">科目四</a></li>
					<li><a href="#">科目五</a></li>
					<li><a href="#">科目六</a></li>
					<li><a href="#">科目七</a></li>
					<li><a href="#">科目八</a></li>
				</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
			</li>
			<li><a href="#">专业四&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
				<!--[if lte IE 6]><table><tr><td><![endif]-->
				<ul>
					<li><a href="#">科目一</a></li>
					<li><a href="#">科目二</a></li>
					<li><a href="#">科目三</a></li>
					<li><a href="#">科目四</a></li>
					<li><a href="#">科目五</a></li>
					<li><a href="#">科目六</a></li>
					<li><a href="#">科目七</a></li>
					<li><a href="#">科目八</a></li>
				</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
			</li>
			<li><a href="#">专业五&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
				<!--[if lte IE 6]><table><tr><td><![endif]-->
				<ul>
					<li><a href="#">科目一</a></li>
					<li><a href="#">科目二</a></li>
					<li><a href="#">科目三</a></li>
					<li><a href="#">科目四</a></li>
					<li><a href="#">科目五</a></li>
					<li><a href="#">科目六</a></li>
					<li><a href="#">科目七</a></li>
					<li><a href="#">科目八</a></li>
				</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
			</li>
			<li><a href="#">专业六&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
				<!--[if lte IE 6]><table><tr><td><![endif]-->
				<ul>
					<li><a href="#">科目一</a></li>
					<li><a href="#">科目二</a></li>
					<li><a href="#">科目三</a></li>
					<li><a href="#">科目四</a></li>
					<li><a href="#">科目五</a></li>
					<li><a href="#">科目六</a></li>
					<li><a href="#">科目七</a></li>
					<li><a href="#">科目八</a></li>
				</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
			</li>
			<li><a href="#">专业七&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
				<!--[if lte IE 6]><table><tr><td><![endif]-->
				<ul>
					<li><a href="#">科目一</a></li>
					<li><a href="#">科目二</a></li>
					<li><a href="#">科目三</a></li>
					<li><a href="#">科目四</a></li>
					<li><a href="#">科目五</a></li>
					<li><a href="#">科目六</a></li>
					<li><a href="#">科目七</a></li>
					<li><a href="#">科目八</a></li>
				</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
			</li>
		</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
	</li>


	<li><a href="#">生物学 &raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
	<!--[if lte IE 6]><table><tr><td><![endif]-->
	<ul>
		<li><a href="#">专业一&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
				<li><a href="#">科目一</a></li>
				<li><a href="#">科目二</a></li>
				<li><a href="#">科目三</a></li>
				<li><a href="#">科目四</a></li>
				<li><a href="#">科目五</a></li>
				<li><a href="#">科目六</a></li>
				<li><a href="#">科目七</a></li>
				<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业二&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业三&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业四&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业五&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业六&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业七&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
	</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
	</li>
	
	<li><a href="#">医学 &raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
	<!--[if lte IE 6]><table><tr><td><![endif]-->
	<ul>
		<li><a href="#">专业一&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业二&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业三&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业四&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业五&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业六&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业七&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
	</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
	</li>
	
	
	
	<li><a href="#">计算机科学 &raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
	<!--[if lte IE 6]><table><tr><td><![endif]-->
	<ul>
		<li><a href="#">专业一&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业二&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业三&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业四&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业五&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业六&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
		<li><a href="#">专业七&raquo;<!--[if gte IE 7]><!--></a><!--<![endif]-->
			<!--[if lte IE 6]><table><tr><td><![endif]-->
			<ul>
			<li><a href="#">科目一</a></li>
			<li><a href="#">科目二</a></li>
			<li><a href="#">科目三</a></li>
			<li><a href="#">科目四</a></li>
			<li><a href="#">科目五</a></li>
			<li><a href="#">科目六</a></li>
			<li><a href="#">科目七</a></li>
			<li><a href="#">科目八</a></li>
			</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
		</li>
	</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
	</li>
	


</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
</li>
</ul><!--[if lte IE 6]></td></tr></table></a><![endif]-->
</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>