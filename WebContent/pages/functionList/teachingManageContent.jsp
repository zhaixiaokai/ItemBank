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
<% if("1".equals(usrPerm.get("210"))){%>
<div id="CollapsiblePanel1" class="CollapsiblePanel" style="padding: 0px">
  <div class="CollapsiblePanelTab">课程管理</div>
  <div class="CollapsiblePanelContent">   
    <ul id="MenuBar1" class="MenuBarVertical">
	<% if("1".equals(usrPerm.get("211"))){%>
      <li class="menuli" style="width: 135px"><a href="../teachingManage/addCourse.jsp" target="mainFrame">添加课程</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("212"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/CourseInquiry.jsp" target="mainFrame">课程查询</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("213"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/CourseM.jsp" target="mainFrame">课程管理</a></li>
    <%} %>
    
    </ul>
  </div>
</div>
<%} %>

<% if("1".equals(usrPerm.get("220"))){%>
<div id="CollapsiblePanel2" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab">教材管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar2" class="MenuBarVertical">
	<% if("1".equals(usrPerm.get("221"))){%>
      <li class="menuli" style="width: 135px"><a href="../teachingManage/addTeachingMaterial.jsp" target="mainFrame">添加教材</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("222"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/TeachingMaterialM.jsp" target="mainFrame">教材管理</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("223"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/ChapterM.jsp" target="mainFrame">章节管理</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("224"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/ChapterStructureM.jsp" target="mainFrame">章节结构管理</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("225"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/TeachingMaterialInquiry.jsp" target="mainFrame">教材查询</a></li>
	<%} %>

    </ul>
  </div>
</div>
<%} %>
<% if("1".equals(usrPerm.get("230"))){%>
<div id="CollapsiblePanel3" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" >教学进度管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar3" class="MenuBarVertical">
    <% if("1".equals(usrPerm.get("231"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/TeachingProgressM.jsp" target="mainFrame">教学进度管理</a> </li>
    <%} %>
    <% if("1".equals(usrPerm.get("232"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/TeachingProgressStructureM.jsp" target="mainFrame">教学进度结构管理</a> </li>
    <%} %>
      
    </ul>
  </div>
</div>
<%} %>
<% if("1".equals(usrPerm.get("240"))){%>
<div id="CollapsiblePanel5" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab">开课班级管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar5" class="MenuBarVertical">
    <% if("1".equals(usrPerm.get("241"))){%>
      <li class="menuli" style="width: 135px"><a href="../teachingManage/addTeachingclass.jsp" target="mainFrame">添加开课班级</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("242"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/teachingclassManage.jsp" target="mainFrame">管理开课班级</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("243"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/teachingclassInquiry.jsp" target="mainFrame">查询开课班级</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("244"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/addTeachingClassMember.jsp" target="mainFrame">开课班级成员添加</a></li>
<!--       <li class="menuli" style="width: 135px"><a href="../teachingManage/importClassMember.jsp" target="mainFrame">开课班级成员导入</a></li> -->
    <%} %>
    <% if("1".equals(usrPerm.get("245"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/TeachingClassMemberM.jsp" target="mainFrame">开课班级成员管理</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("246"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/TeachingClassMemberInquiry.jsp" target="mainFrame">开课班级成员查询</a></li>
    <%} %>
      
    </ul>
  </div>
</div>
<%} %>
<% if("1".equals(usrPerm.get("250"))){%>
<div id="CollapsiblePanel6" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab">知识点管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar6" class="MenuBarVertical">
    <% if("1".equals(usrPerm.get("251"))){%>
      <li class="menuli" style="width: 135px"><a href="../teachingManage/addKnowledgePoint.jsp" target="mainFrame">添加知识点</a> </li>
    <%} %>
    <% if("1".equals(usrPerm.get("252"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/knowledgePointManage.jsp" target="mainFrame">管理知识点</a></li>
    <%} %>
    <% if("1".equals(usrPerm.get("253"))){%>  
      <li class="menuli" style="width: 135px"><a href="../teachingManage/knowledgePointInquiry.jsp" target="mainFrame">查看知识点</a></li>
    <%} %>
    </ul>
  </div>
</div>
<%} %>
<% if("1".equals(usrPerm.get("260"))){%>
<div id="CollapsiblePanel7" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab">授课管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar7" class="MenuBarVertical">
    <% if("1".equals(usrPerm.get("261"))){%>
      <li class="menuli" style="width: 135px"><a href="../teachingManage/TeachingCourseInquiry.jsp" target="mainFrame">查看教学课程</a></li>
    <%} %>
    </ul>
  </div>
</div>

<%} %>
	</td>
  </tr>
</table>


<script type="text/javascript">
<% if("1".equals(usrPerm.get("210"))){%>
var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel1");
<%} %>
<% if("1".equals(usrPerm.get("220"))){%>
var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel2");
<%} %>
<% if("1".equals(usrPerm.get("230"))){%>
var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel3");
<%} %>
<% if("1".equals(usrPerm.get("240"))){%>
var CollapsiblePanel5 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel5");
<%} %>
<% if("1".equals(usrPerm.get("250"))){%>
var CollapsiblePanel6 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel6");
<%} %>
<% if("1".equals(usrPerm.get("260"))){%>
var CollapsiblePanel7 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel7");
<%} %>
</script>