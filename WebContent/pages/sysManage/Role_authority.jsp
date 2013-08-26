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
<link href="/css/multiMenu.css" rel="stylesheet" type="text/css" />
<link href="../SpryAssets/SpryCollapsiblePanel.css" rel="stylesheet"
	type="text/css" />
<link href="../SpryAssets/SpryMenuBarVertical.css" rel="stylesheet"
	type="text/css" />

<script src="../SpryAssets/SpryCollapsiblePanel.js"
	type="text/javascript"></script>
<script src="../SpryAssets/SpryMenuBar.js" type="text/javascript"></script>
<script src="../sysManage/checkAll.js" type="text/javascript"></script>

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
</style>
</head>
<body>
	<jsp:include page="../frame/Frame1.jsp"></jsp:include>
	<!-- 左侧界面 -->
	<jsp:include page="../functionList/sysManageContent.jsp"></jsp:include>
	<jsp:include page="../frame/Frame2.jsp"></jsp:include>
	<!-- 右侧界面 -->
	<div id="act_top">
		<a href="../../functionList/HomePage.html">首页</a>&nbsp;&gt;&gt;&nbsp;<a
			href="../../functionList/sysManage.html">系统管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">权限管理</a>&nbsp;&gt;&gt;&nbsp;<a
			href="javascript:void(0)">按角色授权</a>
	</div>
	<table id="act_divLine" height="4px" width="100%" border="0"
		cellspacing="0" cellpadding="0">
		<tr>
			<td style="border: 0"></td>
		</tr>
	</table>
	<div id="act_content2">
		<table height="50px">
			<tr>
				<td><label for="授权角色列表">角色列表：</label></td>
				<td><select name="授权角色列表" id="授权角色列表">
						<option value="试题管理员">试题管理员</option>
						<option value="试题库管理员">试题库管理员</option>
						<option value="试题编制人员">试题编制人员</option>
						<option value="系统管理员">系统管理员</option>
						<option value="题库使用人员">题库使用人员</option>
				</select></td>
			</tr>
		</table>


		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableList">
			<tr>
				<td align="center" colspan="7" class="tr1">系统管理</td>
			</tr>
			<tr align="center">
				<td>组织机构管理</td>
				<td>用户管理</td>
				<td>角色管理</td>
				<td>功能管理</td>
				<td>权限管理</td>
				<td>数据字典管理</td>
				<td>配置管理</td>
			</tr>
			<tr class="tr3">
				<td>

					<p>
						<input type="checkbox" name="创建新部门" id="创建新部门" />创建新部门
					</p>
					<p>
						<input type="checkbox" name="创建新部门2" id="创建新部门2" />部门管理
					</p>
				</td>

				<td>
					<p>
						<input type="checkbox" name="创建新部门3" id="创建新部门3" />教师管理
					</p>
					<p>
						<input type="checkbox" name="创建新部门4" id="创建新部门4" />学生管理
					</p>
				</td>

				<td>
					<p>
						<input type="checkbox" name="创建新部门5" id="创建新部门5" />创建新角色
					</p>
					<p>
						<input type="checkbox" name="创建新部门6" id="创建新部门6" />角色管理
					</p>
					<p>
						<input type="checkbox" name="创建新部门7" id="创建新部门7" />角色成员管理
					</p>
				</td>

				<td>
					<p>
						<input type="checkbox" name="创建新部门8" id="创建新部门8" /> 创建新功能
					</p>
					<p>
						<input type="checkbox" name="创建新部门9" id="创建新部门9" />系统功能管理
					</p>
				</td>

				<td>
					<p>
						<input type="checkbox" name="创建新部门15" id="创建新部门15" />角色授权
					</p>
					<p>
						<input type="checkbox" name="创建新部门16" id="创建新部门16" />用户授权
					</p>
				</td>

				<td>
					<p>
						<input type="checkbox" name="创建新部门10" id="创建新部门10" />创建字典项
					</p>
					<p>
						<input type="checkbox" name="创建新部门11" id="创建新部门11" />字典项管理
					</p>
					<p>
						<input type="checkbox" name="创建新部门12" id="创建新部门12" />值项管理
					</p>
				</td>

				<td>
					<p>
						<input type="checkbox" name="创建新部门13" id="创建新部门13" />创建新配置项
					</p>
					<p>
						<input type="checkbox" name="创建新部门14" id="创建新部门14" />配置项管理
					</p>
				</td>
			</tr>
		</table>

		<p>&nbsp;</p>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableList">
			<tr align="center">
				<td align="center" colspan="4" class="tr1">试题库管理</td>
			</tr>
			<tr align="center">
				<td>试题库分类管理</td>
				<td>试题库管理</td>
				<td>试题库管理授权</td>
				<td>试题库访问授权</td>
			</tr>
			<tr class="tr3">

				<td>
					<p>
						<input type="checkbox" name="创建新部门17" id="创建新部门19" />查看分类体系
					</p>
					<p>
						<input type="checkbox" name="创建新部门17" id="创建新部门20" />创建分类体系
					</p>
					<p>
						<input type="checkbox" name="创建新部门18" id="创建新部门17" />分类体系 管理
					</p>
					<p>
						<input type="checkbox" name="创建新部门19" id="创建新部门18" /> 分类管理
					</p>
				</td>
				<td align="left" valign="top"><p>
						<input type="checkbox" name="创建新部门17" id="创建新部门21" /> 查看试题库
					</p>
					<p>
						<input type="checkbox" name="创建新部门17" id="创建新部门22" />创建试题库
					</p>
					<p>
						<input type="checkbox" name="创建新部门17" id="创建新部门23" /> 修改试题库
					</p>
					<p>
						<input type="checkbox" name="创建新部门20" id="创建新部门28" /> 查询试题库
					</p></td>

				<td>
					<p>
						<input type="checkbox" name="创建新部门17" id="创建新部门24" /> 增加试题库管理员
					</p>
					<p>
						<input type="checkbox" name="创建新部门17" id="创建新部门25" />查看试题库管理员
					</p>
				</td>

				<td>
					<p>
						<input type="checkbox" name="创建新部门17" id="创建新部门26" />访问授权管理
					</p>
					<p>
						<input type="checkbox" name="创建新部门17" id="创建新部门27" />数据授权管理
					</p>
				</td>
			</tr>
		</table>

		<p>&nbsp;</p>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableList">
			<tr>
				<td align="center" colspan="4" class="tr1">试题管理</td>
			</tr>
			<tr align="center">
				<td>增加试题</td>
				<td>查看试题</td>
				<td>修改试题</td>
				<td>试题导出</td>
			</tr>
			<tr class="tr3">
				<td>
					<p>
						<input type="checkbox" name="创建新部门21" id="创建新部门29" />在线增加试题
					</p>
					<p>
						<input type="checkbox" name="创建新部门21" id="创建新部门30" />导入试题
					</p>
				</td>
				<td>
					<p>
						<input type="checkbox" name="创建新部门21" id="创建新部门31" />查看试题
					</p>
					<p>
						<input type="checkbox" name="创建新部门21" id="创建新部门32" />删除试题
					</p>
				</td>

				<td>
					<p>
						<input type="checkbox" name="创建新部门21" id="创建新部门33" />在线修改试题
					</p>
					<p>
						<input type="checkbox" name="创建新部门21" id="创建新部门35" />离线修改试题
					</p>
				</td>
				<td>
					<p>
						<input type="checkbox" name="创建新部门21" id="创建新部门36" /> 试题导出
					</p>
				</td>
			</tr>
		</table>

		<p>&nbsp;</p>

		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="tableList">
			<tr>
				<td align="center" colspan="4" class="tr1">应用管理</td>
			</tr>
			<tr align="center">
				<td>课程教材管理</td>
				<td>试题库管理</td>
				<td>试题库管理授权</td>
				<td>试题库访问授权</td>
			</tr>
			<tr class="tr3">
				<td>
					<p>
						<input type="checkbox" name="创建新部门22" id="创建新部门34" />教材管理
					</p>
					<p>
						<input type="checkbox" name="创建新部门22" id="创建新部门37" /> 章节管理
					</p>
					<p>
						<input type="checkbox" name="创建新部门23" id="创建新部门52" />教材查询
					</p>
				</td>
				<td>
					<p>
						<input type="checkbox" name="创建新部门22" id="创建新部门38" />查看试题库
					</p>
					<p>
						<input type="checkbox" name="创建新部门22" id="创建新部门39" />创建试题库
					</p>
					<p>
						<input type="checkbox" name="创建新部门24" id="创建新部门53" />修改试题库
					</p>
				</td>
				<td><p>
						<input type="checkbox" name="创建新部门22" id="创建新部门41" />查看试题库管理员
					</p>
					<p>
						<input type="checkbox" name="创建新部门22" id="创建新部门42" />增加试题库管理员
					</p></td>
				<td><p>
						<input type="checkbox" name="创建新部门22" id="创建新部门43" />访问授权管理
					</p>
					<p>
						<input type="checkbox" name="创建新部门22" id="创建新部门44" />数据授权管理
					</p></td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<div style="text-align: center">
			<input type="button" name="Submit" value="保存" class="button"
				onclick="save();" />

		</div>
	</div>
	<jsp:include page="../frame/Frame3.jsp"></jsp:include>
</body>
</html>