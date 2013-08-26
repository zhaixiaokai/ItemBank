<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
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

	</head>
	<body>
 	<%-- <jsp:include page="../frame/Frame1.jsp"></jsp:include> --%>


 
		<table class="treetable">
			<thead><tr><th>Row No</th><th>Description</th><th>Path to Row</th><th>Level</th><th>Status</th></tr></thead>
			<tbody id="treet1">
				<tr id="tr_01"><td>1</td><td>Child of 0</td><td>[0, 1]</td><td>1</td><td></td></tr>
				<tr id="tr_02"><td>2</td><td>Child of 0</td><td>[0, 2]&nbsp;</td><td>1</td><td></td></tr>
				<tr id="tr_03"><td>3</td><td>Child of 2</td><td>[0, 2, 3]&nbsp;</td><td>2</td><td></td></tr>
				<tr id="tr_04"><td>4</td><td>Child of 3</td><td>[0, 2, 3, 4]&nbsp;</td><td>3</td><td></td></tr>
				<tr id="tr_05"><td>5</td><td>Child of 4</td><td>[0, 2, 3, 4, 5]&nbsp;</td><td>4</td><td></td></tr>
				<tr id="tr_06"><td>6</td><td>Child of 4</td><td>[0, 2, 3, 4, 6]&nbsp;</td><td>4</td><td></td></tr>
				<tr id="tr_07"><td>7</td><td>Child of 6</td><td>[0, 2, 3, 4, 6, 7]&nbsp;</td><td>5</td><td></td></tr>
				<tr id="tr_08"><td>8</td><td>Child of 4</td><td>[0, 2, 3, 4, 8]&nbsp;</td><td>4</td><td></td></tr>
				<tr id="tr_09"><td>9</td><td>Child of 2</td><td>[0, 2, 9]&nbsp;</td><td>2</td><td></td></tr>
				<tr id="tr_10"><td>10</td><td>Child of 9</td><td>[0, 2, 9, 10]&nbsp;</td><td>3</td><td></td></tr>
				<tr id="tr_11"><td>11</td><td>Child of 10</td><td>[0, 2, 9, 10, 11]&nbsp;</td><td>4</td><td></td></tr>
				<tr id="tr_12"><td>12</td><td>Child of 0</td><td>[0, 12]&nbsp;</td><td>1</td><td></td></tr>
				<tr id="tr_13"><td>13</td><td>Child of 0</td><td>[0, 13]&nbsp;</td><td>1</td><td></td></tr>
				<tr id="tr_14"><td>14</td><td>Child of 13</td><td>[0, 13, 14]&nbsp;</td><td>2</td><td></td></tr>
				<tr id="tr_15"><td>15</td><td>Child of 0</td><td>[0, 15]&nbsp;</td><td>1</td><td></td></tr>
				<tr id="tr_16"><td>16</td><td>Child of 0</td><td>[0, 16]&nbsp;</td><td>1</td><td></td></tr>
				<tr id="tr_17"><td>17</td><td>Child of 0</td><td>[0, 17]&nbsp;</td><td>1</td><td></td></tr>

				<tr><td>18</td><td>Child of 17</td><td>[0, 17, 18]&nbsp;</td><td>2</td><td></td></tr>
				<tr><td>19</td><td>Child of 17</td><td>[0, 17, 19]&nbsp;</td><td>2</td><td></td></tr>
				<tr><td>20</td><td>Child of 0</td><td>[0, 20]&nbsp;</td><td>1</td><td></td></tr>
			</tbody>
		</table>
        
        		<!-- 右侧界面结束-->
	<%-- <jsp:include page="../frame/Frame3.jsp"></jsp:include> --%>


	</body>
	
</html>