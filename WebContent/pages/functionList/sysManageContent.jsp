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
<% if("1".equals(usrPerm.get("610"))){%>    
<div id="CollapsiblePanel1" class="CollapsiblePanel" style="padding: 0px">
  <div class="CollapsiblePanelTab">组织机构管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar1" class="MenuBarVertical">
    <% if("1".equals(usrPerm.get("611"))){%>      
      <li class="menuli" style="width: 135px"><a href="../sysManage/DepartmentManage1.jsp" target="mainFrame">教师组织机构管理</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("612"))){%>      
      <li class="menuli" style="width: 135px"><a href="../sysManage/DepartmentManage2.jsp" target="mainFrame">学生组织机构管理</a></li>
    <%} %>
    
    </ul>
  </div>
</div>
<%} %>
<% if("1".equals(usrPerm.get("620"))){%>
<div id="CollapsiblePanel2" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab">用户管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar2" class="MenuBarVertical">
	<% if("1".equals(usrPerm.get("621"))){%>
      <li class="menuli" style="width: 135px"><a href="../sysManage/addTeacher.jsp" target="mainFrame">添加教师</a></li>
	<%} %>
    <% if("1".equals(usrPerm.get("622"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/BulkAddTeacher.jsp" target="mainFrame">教师信息导入</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("623"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/teacherManage.jsp" target="mainFrame">教师管理</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("624"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/addStudent.jsp" target="mainFrame">添加学生</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("625"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/BulkAddStudent.jsp" target="mainFrame">学生信息导入</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("626"))){%> 
      <li class="menuli" style="width: 135px"><a href="../sysManage/studentManage.jsp" target="mainFrame">学生管理</a></li>
	<%} %>
    
    </ul>
  </div>
</div>
<%} %>
<% if("1".equals(usrPerm.get("630"))){%>
<div id="CollapsiblePanel3" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" >角色管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar3" class="MenuBarVertical">
	<% if("1".equals(usrPerm.get("631"))){%>
      <li class="menuli" style="width: 135px"><a href="../sysManage/addRole.jsp" target="mainFrame">添加新角色</a> </li>
    <%} %>
    <% if("1".equals(usrPerm.get("632"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/RoleManage.jsp" target="mainFrame">角色管理</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("633"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/addRoleMember.jsp" target="mainFrame">添加角色成员</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("634"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/RoleMemberManage.jsp" target="mainFrame">角色成员管理</a></li>
    <%} %>
    
    </ul>
  </div>
</div>
<%} %>
<% if("1".equals(usrPerm.get("640"))){%>
<div id="CollapsiblePanel4" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab">功能管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar4" class="MenuBarVertical">
    <% if("1".equals(usrPerm.get("641"))){%>
      <li class="menuli" style="width: 135px"><a href="../sysManage/functionM.jsp" target="mainFrame">功能管理</a></li>
	<%} %>
    </ul>
  </div>
</div>
<%} %>
<% if("1".equals(usrPerm.get("650"))){%>
<div id="CollapsiblePanel5" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab">权限管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar5" class="MenuBarVertical">
    <% if("1".equals(usrPerm.get("651"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/RoleAuthority.jsp" target="mainFrame">按角色授权</a> </li>
    <%} %>
    <% if("1".equals(usrPerm.get("652"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/UserAuthority.jsp" target="mainFrame">按用户授权</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("653"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/DepartmentAuthority.jsp" target="mainFrame">按机构授权</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("654"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/UserMemberAuthority.jsp" target="mainFrame">按成员类型授权</a></li>
    <%} %>
    
    </ul>
  </div>
</div>
<%} %>
<% if("1".equals(usrPerm.get("660"))){%>
<div id="CollapsiblePanel6" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab">数据字典管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar6" class="MenuBarVertical">
    <% if("1".equals(usrPerm.get("661"))){%>
      <li class="menuli" style="width: 135px"><a href="../sysManage/addDataDic.jsp" target="mainFrame">添加字典项</a> </li>
    <%} %>
    <% if("1".equals(usrPerm.get("662"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/DataM.jsp" target="mainFrame">字典项管理</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("663"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/addValue.jsp" target="mainFrame">添加值项</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("664"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/valueManage.jsp" target="mainFrame">值项管理</a></li>
    <%} %>
    
    </ul>
  </div>
</div>
<%} %>
<% if("1".equals(usrPerm.get("670"))){%>
<div id="CollapsiblePanel7" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" >配置管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar7" class="MenuBarVertical">
    <% if("1".equals(usrPerm.get("671"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/addConfig.jsp" target="mainFrame">添加配置项</a> </li>
    <%} %>
    <% if("1".equals(usrPerm.get("672"))){%>  
      <li class="menuli" style="width: 135px"><a href="../sysManage/configManage.jsp" target="mainFrame">配置项管理</a></li>
    <%} %>
    
    </ul>
  </div>
</div>
<%} %>
	</td>
  </tr>
</table>


<script type="text/javascript">
<% if("1".equals(usrPerm.get("610"))){%>
var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel1");
<%} %>
<% if("1".equals(usrPerm.get("620"))){%>
var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel2");
<%} %>
<% if("1".equals(usrPerm.get("630"))){%>
var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel3");
<%} %>
<% if("1".equals(usrPerm.get("640"))){%>
var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel4");
<%} %>
<% if("1".equals(usrPerm.get("650"))){%>
var CollapsiblePanel5 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel5");
<%} %>
<% if("1".equals(usrPerm.get("660"))){%>
var CollapsiblePanel6 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel6");
<%} %>
<% if("1".equals(usrPerm.get("670"))){%>
var CollapsiblePanel7 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel7");
<%} %>
</script>