<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="net.ib.util.service.*"%>
<%@ page import="net.ib.util.service.impl.*"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table style="margin: 5px">
	<tr>
		<td>
			<!-- 		<div id="CollapsiblePanel1" class="CollapsiblePanel">
				<div class="CollapsiblePanelTab" tabindex="0">试卷库分类体系管理</div>
				<div class="CollapsiblePanelContent">
					<ul id="MenuBar1" class="MenuBarVertical">
						<li class="menuli" style="width: 135px"><a
							href="../paperManage/addSort.jsp" target="mainFrame">添加分类体系</a></li>
						<li class="menuli" style="width: 135px"><a
							href="../paperManage/sortSystemManage.jsp" target="mainFrame">分类体系管理</a>
						</li>
						<li class="menuli" style="width: 135px"><a
							href="../paperManage/sortView.jsp" target="mainFrame">分类体系查看</a></li>
						<li class="menuli" style="width: 135px"><a
							href="../paperManage/sortManageSelect.jsp" target="mainFrame">分类管理</a></li>

					</ul>
				</div>
			</div> -->
<% 
	PermitionService ps	=	new		PermitionServiceImpl();
	Map<Object,Object> usrPerm	=	ps.getAllLevelPerm(request,response);
%>
<%if("1".equals(usrPerm.get("510"))){ %>
			<div id="CollapsiblePanel2" class="CollapsiblePanel">
				<div class="CollapsiblePanelTab" tabindex="1">试卷库管理</div>
				<div class="CollapsiblePanelContent">
					<ul id="MenuBar1" class="MenuBarVertical">
					<%if("1".equals(usrPerm.get("511"))){ %>
						<li class="menuli" style="width: 135px"><a href="../paperManage/addEsSelect.jsp" target="mainFrame">创建试卷库</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("512"))){ %>	
						<li class="menuli" style="width: 135px"><a href="../paperManage/esManageSelect.jsp" target="mainFrame">试卷库管理</a></li>
					<%} %>	
					<%if("1".equals(usrPerm.get("513"))){ %>
						<li class="menuli" style="width: 135px"><a href="../paperManage/esView.jsp" target="mainFrame">试卷库查看</a></li>
					<%} %>
					</ul>
				</div>
			</div>
<%} %>
<%if("1".equals(usrPerm.get("520"))){ %>
			<div id="CollapsiblePanel3" class="CollapsiblePanel">
				<div class="CollapsiblePanelTab" tabindex="0">试卷管理</div>
				<div class="CollapsiblePanelContent">
					<ul id="MenuBar1" class="MenuBarVertical">
					<%if("1".equals(usrPerm.get("521"))){ %>
						<li class="menuli" style="width: 135px"><a href="../paperManage/addPaper.jsp" target="mainFrame">添加试卷</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("522"))){ %>	
						<li class="menuli" style="width: 135px"><a href="../paperManage/paperManage.jsp" target="mainFrame">试卷管理</a></li>
					<%} %>
					</ul>
				</div>
			</div>
<%} %>
<%if("1".equals(usrPerm.get("530"))){ %>
			<div id="CollapsiblePanel4" class="CollapsiblePanel">
				<div class="CollapsiblePanelTab" tabindex="0">试卷库权限管理</div>
				<div class="CollapsiblePanelContent">
					<ul id="MenuBar1" class="MenuBarVertical">
					<%if("1".equals(usrPerm.get("531"))){ %>
						<li class="menuli" style="width: 135px"><a href="../paperManage/authority_role.jsp" target="mainFrame">按角色授权</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("532"))){ %>	
						<li class="menuli" style="width: 135px"><a href="../paperManage/authority_user.jsp" target="mainFrame">按用户授权</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("533"))){ %>
						<li class="menuli" style="width: 135px"><a href="../paperManage/authority_department.jsp" target="mainFrame">按机构授权</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("534"))){ %>	
						<li class="menuli" style="width: 135px"><a href="../paperManage/authority_membertype.jsp" target="mainFrame">按成员类型授权</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("535"))){ %>	
						<li class="menuli" style="width: 135px"><a href="../paperManage/authority_class.jsp" target="mainFrame">按班级授权</a></li>
					<%} %>
					</ul>
				</div>
			</div>
<%} %>			
		</td>
	</tr>
</table>
<script type="text/javascript">
	//var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			//"CollapsiblePanel1");
<%if("1".equals(usrPerm.get("510"))){ %>
	var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel2");

<%}%>
<%if("1".equals(usrPerm.get("520"))){ %>
	var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel3");

<%}%>
<%if("1".equals(usrPerm.get("530"))){ %>
	var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel4");
<%}%>
</script>