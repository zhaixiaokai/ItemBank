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
<script>
	function sortSelect() {
		self.parent.frames["mainFrame"].location = "esManage.jsp";
	}
	function check_all(obj, cName) {
		var checkboxs = document.getElementsByName(cName);
		for ( var i = 0; i < checkboxs.length; i++) {
			checkboxs[i].checked = obj.checked;
		}
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/paperDbManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="#">试卷库管理</a>&nbsp;&gt;&gt;&nbsp;<a href="#">试卷库分类体系管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="#">分类体系管理</a>
	</div>
	<table id="divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
     <div id="act_content2">
	<table width="100%" class="tableList">
		<tr>
			<td style="border: 0">
				<table width="100%" align="center">
					<tr class="tr1">
						<td><input type="checkbox" id="chks"
							onclick="check_all(this,'chks')">全选</td>
						<td>序号</td>
						<td>名称</td>
						<td>标识符</td>
						<td>分类体系说明</td>
						<td>是否为默认分类体系</td>
						<td>编辑</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="chks"></td>
						<td>1</td>
						<td>分类体系a</td>
						<td>aaaaa</td>
						<td><a href="javascript:void(0)" title="该分类体系。。。。"
							class="detail">说明</a></td>
						<td>否</td>


						<td><a href="javascript:void(0)" class="detail">修改</a>&nbsp;/&nbsp;<a
							href="javascript:void(0)">删除</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="chks"></td>
						<td>2</td>
						<td>分类体系a</td>
						<td>aaaaa</td>
						<td><a href="javascript:void(0)" title="该分类体系。。。。"
							class="detail">说明</a></td>
						<td>否</td>


						<td><a href="javascript:void(0)" class="detail">修改</a>&nbsp;/&nbsp;<a
							href="javascript:void(0)">删除</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="chks"></td>
						<td>3</td>
						<td>分类体系a</td>
						<td>aaaaa</td>
						<td><a href="javascript:void(0)" title="该分类体系。。。。"
							class="detail">说明</a></td>
						<td>否</td>


						<td><a href="javascript:void(0)" class="detail">修改</a>&nbsp;/&nbsp;<a
							href="javascript:void(0)">删除</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="chks"></td>
						<td>4</td>
						<td>分类体系a</td>
						<td>aaaaa</td>
						<td><a href="javascript:void(0)" title="该分类体系。。。。"
							class="detail">说明</a></td>
						<td>否</td>


						<td><a href="javascript:void(0)" class="detail">修改</a>&nbsp;/&nbsp;<a
							href="javascript:void(0)">删除</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="chks"></td>
						<td>5</td>
						<td>分类体系a</td>
						<td>aaaaa</td>
						<td><a href="javascript:void(0)" title="该分类体系。。。。"
							class="detail">说明</a></td>
						<td>否</td>


						<td><a href="javascript:void(0)" class="detail">修改</a>&nbsp;/&nbsp;<a
							href="javascript:void(0)">删除</a></td>
					</tr>
					<tr>
						<td><input type="checkbox" name="chks"></td>
						<td>6</td>
						<td>分类体系a</td>
						<td>aaaaa</td>
						<td><a href="javascript:void(0)" title="该分类体系。。。。"
							class="detail">说明</a></td>
						<td>否</td>


						<td><a href="javascript:void(0)" class="detail">修改</a>&nbsp;/&nbsp;<a
							href="javascript:void(0)">删除</a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="border: 0">
				<table>
					<tr>
						<td style="font-size: 12px; border: 0"><a href="qManage1.jsp"
							style="color: red; text-decoration: none;">&nbsp;1&nbsp;</a> <a
							href="qManage2.jsp" class="page">&nbsp;2&nbsp;</a> <a
							href="qManage3.jsp" class="page">&nbsp;3&nbsp;</a> <a
							href="javascript:void(0)" class="page">&nbsp;4&nbsp;</a> <a
							href="javascript:void(0)" class="page">&nbsp;5&nbsp;</a> <a
							href="qManage2.jsp" class="pageturn" style="font-size: 12px">&nbsp;下一页
								&gt;</a> &nbsp;&nbsp; <a href="qManage3.jsp" class="page">末页</a>
							&nbsp;&nbsp;共50页&nbsp;&nbsp; &nbsp;&nbsp; 到第<input type="text"
							size="1">页 &nbsp;&nbsp; <input type="button" value="确认"
							onclick="certainPage" class="button1"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</div>
	<!-- 右侧界面结束-->
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>