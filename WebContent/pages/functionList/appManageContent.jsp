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
		<TD>
<% 
	PermitionService ps	=	new		PermitionServiceImpl();
	
	Map<Object,Object> usrPerm	=	ps.getAllLevelPerm(request,response);
	if("1".equals(usrPerm.get("110"))){
%>
<div id="CollapsiblePanel2" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab">组卷管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar1" class="MenuBarVertical">
	<% if("1".equals(usrPerm.get("111"))){%>
      <li class="menuli" style="width: 135px"><a href="../appManage/automOnChapter.jsp" target="mainFrame">按章节自动组卷</a></li>
    <% }if("1".equals(usrPerm.get("112"))){%>  
      <li class="menuli" style="width: 135px"><a href="../appManage/automOnPro.jsp" target="mainFrame">按进度自动组卷</a></li>
    <% }if("1".equals(usrPerm.get("113"))){%>  
      <li class="menuli" style="width: 135px"><a href="../appManage/handOnChapter.jsp" target="mainFrame">按章节手动组卷</a></li>
    <% }if("1".equals(usrPerm.get("114"))){%>  
      <li class="menuli" style="width: 135px"><a href="../appManage/handOnPro.jsp" target="mainFrame">按进度手动组卷</a></li>
    <%} %>  
    </ul>
  </div>
</div>
<%} %>
<%if("1".equals(usrPerm.get("120"))) {%>
<div id="CollapsiblePanel3" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" >成绩管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar1" class="MenuBarVertical">
    <% if("1".equals(usrPerm.get("121"))){%>
      <li class="menuli" style="width: 135px"><a href="../appManage/addResult.jsp" target="mainFrame">成绩导入</a> </li>
    <% }if("1".equals(usrPerm.get("122"))){%>   
      <li class="menuli" style="width: 135px"><a href="../appManage/QueryResultOnTeachingClass.jsp" target="mainFrame">按教学班查询</a></li>
    <% }if("1".equals(usrPerm.get("123"))){%>   
      <li class="menuli" style="width: 135px"><a href="../appManage/QueryResultOnClass.jsp" target="mainFrame">按行政班级查询</a> </li>
	<% }if("1".equals(usrPerm.get("124"))){%>   
	  <li class="menuli" style="width: 135px"><a href="../appManage/QueryResultOnStudentSelf.jsp" target="mainFrame">学生自助查询</a> </li>
	<%} %> 
    </ul>
  </div>
</div>
<%} %>
<!-- <div id="CollapsiblePanel4" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab">在线答题</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar1" class="MenuBarVertical">
      
      <li class="menuli" style="width: 135px"><a href="../appManage/onlineExcise.jsp" target="mainFrame">在线练习</a></li>
      <li class="menuli" style="width: 135px"><a href="../appManage/onlineExam.jsp" target="mainFrame">在线考试</a></li>
  
    </ul>
  </div>
</div>		 -->

	
		</TD>
	</tr>
</table>



<script type="text/javascript">
<% if("1".equals(usrPerm.get("110"))){%>
var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel2");
<%}%>

<%if("1".equals(usrPerm.get("120"))) {%>
var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel3");
<%}%>
/* var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel4"); */
</script>