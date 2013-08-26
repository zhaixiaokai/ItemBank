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
<%if("1".equals(usrPerm.get("310"))){ %>
<div id="CollapsiblePanel1" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="0">增加试题</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar1" class="MenuBarVertical">
	<%if("1".equals(usrPerm.get("311"))){ %>
      <li class="menuli" style="width: 135px"><a href="../examM/addQonlineSelect.jsp" target="mainFrame">在线增加试题</a> </li>
	<%} %>
	<%if("1".equals(usrPerm.get("312"))){ %>
      <li class="menuli" style="width: 135px"><a href="../examM/importPaperSelect.jsp" target="mainFrame">导入试题</a></li>
    <%} %>
    </ul>
  </div>
</div>   
<%} %>
<%if("1".equals(usrPerm.get("320"))){ %>
<div id="CollapsiblePanel2" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="1">查看试题</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar1" class="MenuBarVertical">
	<%if("1".equals(usrPerm.get("321"))){ %>
      <li class="menuli" style="width: 135px"><a href="../examM/qView1.jsp" target="mainFrame">查看试题</a></li>
    <%} %>
    </ul>
  </div>
</div>
<%} %>
<%if("1".equals(usrPerm.get("330"))){ %>
<div id="CollapsiblePanel3" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="1">试题管理</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar1" class="MenuBarVertical">
	<%if("1".equals(usrPerm.get("331"))){ %>
      <li class="menuli" style="width: 135px"><a href="../examM/qManage1.jsp" target="mainFrame">试题管理</a></li>
    <%} %>
	<%if("1".equals(usrPerm.get("332"))){ %>  
      <li class="menuli" style="width: 135px"><a href="../examM/qMove1.jsp" target="mainFrame">试题迁移</a></li>
    <%} %>
    </ul>
  </div>
</div>
<%} %>
<%if("1".equals(usrPerm.get("340"))){ %>
<div id="CollapsiblePanel4" class="CollapsiblePanel">
  <div class="CollapsiblePanelTab" tabindex="3">试题导出</div>
  <div class="CollapsiblePanelContent">
    <ul id="MenuBar1" class="MenuBarVertical">
    
	<%if("1".equals(usrPerm.get("341"))){ %>
      <li class="menuli" style="width: 135px"><a href="../examM/outputQ1.jsp" target="mainFrame">试题导出</a></li>
    <%} %>
    </ul>
  </div>
</div>
<%} %>

	</td>
  </tr>
</table>
<script type="text/javascript">
<%if("1".equals(usrPerm.get("310"))){ %>
var CollapsiblePanel1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel1");
<%} %>
<%if("1".equals(usrPerm.get("320"))){ %>
var CollapsiblePanel2 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel2");
<%} %>
<%if("1".equals(usrPerm.get("330"))){ %>
var CollapsiblePanel3 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel3");
<%} %>
<%if("1".equals(usrPerm.get("340"))){ %>
var CollapsiblePanel4 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel4");
<%} %>
</script>