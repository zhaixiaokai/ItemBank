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
<% 
	PermitionService ps	=	new		PermitionServiceImpl();
	Map<Object,Object> usrPerm	=	ps.getAllLevelPerm(request,response);
%>		
<%if("1".equals(usrPerm.get("410"))){%>
			<div id="CollapsiblePanel1" class="CollapsiblePanel">
				<div class="CollapsiblePanelTab" tabindex="0">试题库分类体系管理</div>
				<div class="CollapsiblePanelContent">
					<ul id="MenuBar1" class="MenuBarVertical">
					<%if("1".equals(usrPerm.get("411"))){%>
						<li class="menuli" style="width: 135px"><a
							href="../esM/addSort.jsp" target="mainFrame">添加分类体系</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("412"))){%>
						<li class="menuli" style="width: 135px"><a
							href="../esM/sortSystemManage.jsp" target="mainFrame">分类体系管理</a>
						</li>
					<%} %>
					<%if("1".equals(usrPerm.get("413"))){%>
						<li class="menuli" style="width: 135px"><a
							href="../esM/sortView.jsp" target="mainFrame">分类体系查看</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("414"))){%>
						<li class="menuli" style="width: 135px"><a
							href="../esM/sortManageSelect.jsp" target="mainFrame">分类管理</a></li>
					<%} %>
					</ul>
				</div>
			</div>
<%} %>			
<%if("1".equals(usrPerm.get("420"))){%>
			<div id="CollapsiblePanel2" class="CollapsiblePanel">
				<div class="CollapsiblePanelTab" tabindex="1">试题库管理</div>
				<div class="CollapsiblePanelContent">
					<ul id="MenuBar2" class="MenuBarVertical">
					<%if("1".equals(usrPerm.get("421"))){%>
						<li class="menuli" style="width: 135px"><a
							href="../esM/addEsSelect.jsp" target="mainFrame">创建试题库</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("422"))){%>
						<li class="menuli" style="width: 135px"><a
							href="../esM/esManageSelect.jsp" target="mainFrame">试题库管理</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("423"))){%>
						<li class="menuli" style="width: 135px"><a
							href="../esM/esView.jsp" target="mainFrame">试题库查看</a></li>
					<%} %>
					</ul>
				</div>
			</div>
<%} %>			
<%if("1".equals(usrPerm.get("430"))){%>			
			<div id="CollapsiblePanel3" class="CollapsiblePanel">
				<div class="CollapsiblePanelTab" tabindex="0">试题库权限管理</div>
				<div class="CollapsiblePanelContent">
					<ul id="MenuBar3" class="MenuBarVertical">
					<%if("1".equals(usrPerm.get("431"))){%>
						<li class="menuli" style="width: 135px"><a
							href="../esM/authority_role.jsp" target="mainFrame">按角色授权</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("432"))){%>
						<li class="menuli" style="width: 135px"><a
							href="../esM/authority_user.jsp" target="mainFrame">按用户授权</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("433"))){%>
						<li class="menuli" style="width: 135px"><a
							href="../esM/authority_department.jsp" target="mainFrame">按机构授权</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("434"))){%>
						<li class="menuli" style="width: 135px"><a
							href="../esM/authority_membertype.jsp" target="mainFrame">按成员类型授权</a></li>
					<%} %>
					<%if("1".equals(usrPerm.get("435"))){%>
							<li class="menuli" style="width: 135px"><a
							href="../esM/authority_class.jsp" target="mainFrame">按班级授权</a></li>
					<%} %>
					</ul>
				</div>
			</div>
<%} %>
		</td>
	</tr>
</table>
<script type="text/javascript">
<%if("1".equals(usrPerm.get("410"))){%>	
	var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel1");
<%}%>
<%if("1".equals(usrPerm.get("420"))){%>	
	var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel2");
<%}%>
<%if("1".equals(usrPerm.get("430"))){%>	
	var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel(
			"CollapsiblePanel3");
<%}%>
</script>