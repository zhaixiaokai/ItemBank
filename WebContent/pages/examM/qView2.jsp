<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/table.css">


<link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<link rel="stylesheet" type="text/css"
	href="../css/GeneralContainer.css">
<link rel="stylesheet" type="text/css" href="../css/css.css">
<link rel="stylesheet" type="text/css" href="../css/MainFrame.css">
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />
<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="../css/select.css">

<style>
#act_divLine {
	margin-top: 5px;
	background: url(../source/divLine.gif);
	width: 784px;
	margin-left: 20px;
}
</style>
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
	<jsp:include page="../functionList/paperManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="javascript:void(0)">试题管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">查看试题</a>&nbsp;&gt;&gt;&nbsp;查看试题
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div class="div_style2">
		<table width="700px" height=50px>
			<tr>
				<td width="20"><img src="image/refer.gif" width="20"
					height="18" /></td>
				<td width="">试题库：</td>
				<td>
					<!-- 引入外部文件，选择试题库 --> <jsp:include page="../document/selectEs.jsp"></jsp:include>
				</td>
				<td width="">知识点：</td>
				<td><input type="text" style="width: 50px"></td>
				<td><input type="button" size="1" value="选择"></td>
				<td width="">&nbsp;&nbsp;&nbsp;题型：</td>
				<td><select name="select" style="font-size: 12px">
						<option selected="selected">---请选择---</option>
						<option>选择题</option>
						<option>填空题</option>
						<option>判断题</option>
						<option>简答题</option>
				</select></td>
				<td width="">难度：</td>
				<td><select name="select" style="font-size: 12px">
						<option selected="selected">---请选择---</option>
						<option>简单</option>
						<option>中等</option>
						<option>困难</option>
				</select></td>
				<td width=""><input type=button id="button" value="查询"
					class="button"></td>
			</tr>
		</table>
		<table width="100%" class="tableList">
			<tr class="tr1">

				<td>试题序号</td>
				<!-- 
				<td>试题介绍</td> -->
				<td>试题内容</td>
				<td>知识点</td>
				<td>难度等级</td>
				<td>题型</td>
				<td>所属试题库</td>

			</tr>
			<tr>

				<td>17</td>
				<!-- 
				<td> 电子电路</td> -->
				<td><a href="javascript:void(0)"
					title="题目一，题干：“........”,答案：“.......”" class="detail">详细</a></td>
				<td>知识点b</td>
				<td>简单</td>
				<td>判断题</td>
				<td>电子电路</td>

			</tr>
			<tr>

				<td>18</td>
				<!-- 
				<td> 数字逻辑</td> -->
				<td><a href="javascript:void(0)"
					title="题目一，题干：“........”,答案：“.......”" class="detail">详细</a></td>
				<td>知识点a</td>
				<td>困难</td>
				<td>判断题</td>
				<td>数字逻辑</td>

			</tr>
			<tr>

				<td>19</td>
				<!-- 
				<td>离散数学 </td> -->
				<td><a href="javascript:void(0)"
					title="题目一，题干：“........”,答案：“.......”" class="detail">详细</a></td>
				<td>知识点b</td>
				<td>简单</td>
				<td>判断题</td>
				<td>离散数学</td>

			</tr>
			<tr>

				<td>20</td>
				<!-- 
				<td> 通信电子电路</td> -->
				<td><a href="javascript:void(0)"
					title="题目一，题干：“........”,答案：“.......”" class="detail">详细</a></td>
				<td>知识点a</td>
				<td>困难</td>
				<td>判断题</td>
				<td>通信电子电路</td>

			</tr>
			<tr>

				<td>21</td>
				<!-- 
				<td>英语 </td> -->
				<td><a href="javascript:void(0)"
					title="题目一，题干：“........”,答案：“.......”" class="detail">详细</a></td>
				<td>知识点b</td>
				<td>简单</td>
				<td>填空题</td>
				<td>英语</td>

			</tr>
			<tr>

				<td>22</td>
				<!-- 
				<td> 数学</td> -->
				<td><a href="javascript:void(0)"
					title="题目一，题干：“........”,答案：“.......”" class="detail">详细</a></td>
				<td>知识点a</td>
				<td>困难</td>
				<td>填空题</td>
				<td>数学</td>

			</tr>


		</table>
		<table>
			<tr>
				<td style="border: 0"><a href="qView1.jsp" class="page">首页</a>
					&nbsp;&nbsp; <a href="qView1.jsp" class="pageturn"
					style="font-size: 12px">&nbsp;< 上一页 </a> <a href="qView1.jsp"
					class="page">&nbsp;1&nbsp;</a> <a href="qView2.jsp"
					style="color: red; text-decoration: none;">&nbsp;2&nbsp;</a> <a
					href="qView3.jsp" class="page">&nbsp;3&nbsp;</a> <a
					href="javascript:void(0)" class="page">&nbsp;4&nbsp;</a> <a
					href="javascript:void(0)" class="page">&nbsp;5&nbsp;</a> <a
					href="qView3.jsp" class="pageturn" style="font-size: 12px">&nbsp;下一页
						></a> &nbsp;&nbsp; <a href="qView3.jsp" class="page">末页</a>
					&nbsp;&nbsp;共50页&nbsp;&nbsp; &nbsp;&nbsp; 到第<input type="text"
					size="1">页 &nbsp;&nbsp; <input type="button" value="确认"
					onclick="certainPage" class="button1"></td>
			</tr>
		</table>
	</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>