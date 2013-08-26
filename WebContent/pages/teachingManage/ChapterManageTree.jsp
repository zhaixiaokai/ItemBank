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
<!-- TreeTable -->


<script type="text/javascript" src="./Treetable_files/jquery.min.js"></script>
		<script type="text/javascript" src="./Treetable_files/jQTreeTable.js"></script><!--这个是现实表格的文件-->
		<link rel="stylesheet" type="text/css" href="./Treetable_files/jqtreetable.css" />
		    <script type="text/javascript" charset="utf-8">
		$(function(){
			// 这里要说明一下，虽然很简单
			// 比如现在有5行，其中第二行和第一行平级，第三行是第二行的下级行，第四行是第三行的下级
			// 第五行和第三行平级，也就是说是第二行的下级行，应该如下所示：
			//      1
			//      2
			//         3
			//            4
			//         5
			// 这样的话，map应该是这样的
			// 行号：1					 2				 3                 4				 5
			// map： 0    ,              0,              2,                3,                2
			// 说明：1行的上级的行为0    2行的上级也为0  3行的上级是第2行  4行的上级是第3行  第5行的上级是第2行
			// 这里有20行，原理是一样的
			//			1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
			 var map = [0,1,2,2,1,5,5,1,8,8, 1 ,11,11, 1,14,14, 1,17,17,1];
			//声明参数选项
			//1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
			//0 0 2 3 4 4 6 4 2 9  10 0  0  13 0  0  0  17 17 0
			var options = {openImg: "images/TreeTable/tv-collapsable.gif", shutImg: "images/TreeTable/tv-expandable.gif", leafImg: "images/TreeTable/tv-item.gif", lastOpenImg: "images/TreeTable/tv-collapsable-last.gif", lastShutImg: "images/TreeTable/tv-expandable-last.gif", lastLeafImg: "images/TreeTable/tv-item-last.gif", vertLineImg: "images/TreeTable/vertline.gif", blankImg: "images/TreeTable/blank.gif", collapse: false, column: 0, striped: true, highlight: true,  state:false};
			/* 对于options中的各个参数，copy原作者的解释，有权威，不翻译了
			openImg: Relative url of the image to use for an expanded parent node.
			shutImg: Relative url of the image to use for a collapsed parent node.
			leafImg: Relative url of the image to use for a child node.
			lastOpenImg: Relative url of the image to use for the last expanded parent node. Required if using lines. If not set this to the same as openImg.
			lastShutImg: Relative url of the image to use for the last collapsed parent node. Required if using lines. If not set this to the same as shutImg.
			lastLeafImg: Relative url of the image to use for the last child node. Required if using lines. If not set this to the same as leafImg.
			vertLineImg: Relative url of the image to use for the vertical lines. If not using lines, set this to the same as blankImg.
			blankImg: Relative url of the image to use for the filler that creates the indenting.
			collapse: An array of parent row numbers whose children are collapsed initially. Must be an array even if you only want one parent collapsed
			column: A zero based index of the column you want to show the treetable effect in, i.e. first column is 0.
			striped: Boolean indicating whether you want a striped effect. Set the colour in the css with an even class selector.
			highlight: Boolean indicating whether you want a highlighting effect when hovering over the rows. Set the colour in the css with an over class selector.
			state: Boolean indicating whether you want the collapsed state of each parent to be set in a cookie. Set this to false if there is likely to be a change in the relationshiop between the child nodes and their parents.
			*/
		   if(map!=null&&map.length>0)
			{
			  //根据参数显示树
			  // 注意treet1这个名字是 下文中 tbody 的名字
			  $("#treet1").jqTreeTable(map, options);
			}
        }
		);

    </script>
    
    <script type="text/javascript">
    function	openNewWindow(){
    	window.open ('page.html','newwindow','height=600,width=800,top=300,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
    }
    </script>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/teachingManageContent.jsp"></jsp:include>
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
	<div id="act_content2">
		<table>
			<tr>
				<td style="border: 0; height: 50px">
					<table width="100%" align="left" style="font-size: 12px">
						<tr>
							<td><a href="javascript:void(0)">添加子节点</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">添加前置节点</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">添加后置节点</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">修改节点</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">删除节点</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">剪切节点</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">前置粘帖</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">后置粘帖</a> <strong>|</strong>&nbsp;<a
								href="javascript:void(0)">粘帖为子节点</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>


	</div>



	<div id="treebody" class="div_style3">
		<table class="treetable" width="100%">
			
			<tbody id="treet1">
				<tr><td>大学英语（第三版）</td><td><a href="javascript:void(0);"></a></td><td></td><td>1</td><td></td></tr>
				<tr><td>Unit 1 A Technological Revolution in Education</td><td><a href="javascript:void(0);"></a></td><td>&nbsp;</td><td>1</td><td></td></tr>
				<tr><td>1.1	第一节</td><td><a href="javascript:void(0);" onclick="openNewWindow()">管理知识点</a></td><td>&nbsp;</td><td>2</td><td></td></tr>
				<tr><td>1.2	第二节</td><td><a href="javascript:void(0);">管理知识点</a></td><td>&nbsp;</td><td>3</td><td></td></tr>
				<tr><td>Unit 2 Do We Really Want Eternal Life?</td><td><a href="javascript:void(0);"></a></td><td>&nbsp;</td><td>4</td><td></td></tr>
				<tr><td>2.1	第一节</td><td><a href="javascript:void(0);">管理知识点</a></td><td>&nbsp;</td><td>4</td><td></td></tr>
				<tr><td>2.2	第二节</td><td><a href="javascript:void(0);">管理知识点</a></td><td>&nbsp;</td><td>5</td><td></td></tr>
				<tr><td>Unit 3 An Introduction to Sales Promotion</td><td><a href="javascript:void(0);"></a></td><td>&nbsp;</td><td>4</td><td></td></tr>
				<tr><td>3.1</td><td><a href="javascript:void(0);">管理知识点</a></td><td>&nbsp;</td><td>2</td><td></td></tr>
				<tr><td>3.2</td><td><a href="javascript:void(0);">管理知识点</a></td><td>&nbsp;</td><td>3</td><td></td></tr>
				<tr><td>第四章</td><td><a href="javascript:void(0);"></a></td><td>&nbsp;</td><td>4</td><td></td></tr>
				<tr><td>4.1</td><td><a href="javascript:void(0);">管理知识点</a></td><td>&nbsp;</td><td>1</td><td></td></tr>
				<tr><td>4.2</td><td><a href="javascript:void(0);">管理知识点</a></td><td>&nbsp;</td><td>1</td><td></td></tr>
				<tr><td>第五章</td><td><a href="javascript:void(0);"></a></td><td>&nbsp;</td><td>4</td><td></td></tr>
				<tr><td>5.1</td><td><a href="javascript:void(0);">管理知识点</a></td><td>&nbsp;</td><td>1</td><td></td></tr>
				<tr><td>5.2</td><td><a href="javascript:void(0);">管理知识点</a></td><td>&nbsp;</td><td>1</td><td></td></tr>
				<tr><td>第六章</td><td><a href="javascript:void(0);"></a></td><td>&nbsp;</td><td>1</td><td></td></tr>

				<tr><td>6.1</td><td><a href="javascript:void(0);">管理知识点</a></td><td>&nbsp;</td><td>2</td><td></td></tr>
				<tr><td>6.2</td><td><a href="javascript:void(0);">管理知识点</a></td><td>&nbsp;</td><td>2</td><td></td></tr>
				<tr><td>第七章</td><td><a href="javascript:void(0);"></a></td><td>&nbsp;</td><td>1</td><td></td></tr>
			</tbody>
		</table>
		
	</div>		
	<!-- <div id="bottom" class="div_style4"></div>	 -->	
		
		
		

	
		<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
	
</body>
</html>