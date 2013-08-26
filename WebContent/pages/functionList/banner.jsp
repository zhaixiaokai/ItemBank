<%@page import="net.ib.config.SessionData"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="net.ib.util.service.*"%>
<%@ page import="net.ib.util.service.impl.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="../../servJs/basicService.js"></script>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="banner">
      <tr height="117px">
        <td>
        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          		<tr>
            		<td width="450px"></td>
            		<td width="280px">&nbsp;</td>
            		<td width="38%">
            		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	              		<tr>
	                		<td align="center" width="223px"></td>
	                		<td style="text-align: center; font-size: 12px;font-family: 楷体;">
	                		<%if(session.getAttribute(SessionData.SESSION_USERNAME)!=null) {%>
	                		<a target="mainFrame" href="../functionList/HomePage.jsp" onclick="logout()">退出登录</a>
	                		<%} %>
	                		</td>
	              		</tr>
		              	<tr>
		                	<td>&nbsp;</td>
		                	<td style="text-align: center; font-size: 12px;font-family: 楷体;"><a href="#" onclick="setHomepage();">设为主页</a></td>
		              	</tr>
		              	<tr>
		                	<td>&nbsp;</td>
		                	<td style="text-align: center; font-size: 12px;font-family: 楷体;"><a target="mainFrame" href="#" onclick="addToFav();">收藏本站</a></td>

		              	</tr>
		              	<tr>
		                	<td>&nbsp;</td>
		                	<td style="text-align: center; font-size: 12px;font-family: 楷体;"><span class="zitiLittle" id="localtime">
                            <script type="text/javascript">
								
								tick();
							</script>
                            
                            </span></td>
		              	</tr>
            		</table>
        			</td>
        		</tr>
        	</table>
        </td>
      </tr>
      <tr height="20px">
        <td>
        <%
        	PermitionService ps	=	new		PermitionServiceImpl();/* 
        	session.setAttribute("usrId", "CC57DF4711D3437A832F75D0B08FC694");*/
        	//session.setAttribute("org", "FE579607C86D449BA4D0371195360D53");
        	//session.setAttribute("usrType", "1");  
        	//List roleList	=	new	ArrayList();
        	//roleList.add("admin");
        	//session.setAttribute("role", roleList); 
        	
        	Map<Object,Object> usrPerm	=	ps.getAllLevelPerm(request,response);
        	
        %>
	        <table background="../source/bg_2.gif" width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr height="50px">
	            <td id="Menu_0" width="100px" align="right"><a href="../functionList/HomePage.jsp" style="text-decoration:none; font-size:14px;color:#fff;font-weight: bold;">系统首页</a></td>
	            <% 
	            	if("1".equals(usrPerm.get("100"))){
	            %>
	            <td id="Menu_1" width="100px" align="right"><a href="../functionList/appManage.jsp" target="mainFrame"  style="text-decoration:none; font-size:14px;color:#fff;font-weight: bold;">应用管理</a></td>
	            <%
	            	}
	            	if("1".equals(usrPerm.get("200"))){
	            %>
	            <td id="Menu_2" width="100px" align="right"><a href="../functionList/teachingManage.jsp" target="mainFrame"  style="text-decoration:none; font-size:14px;color:#fff;font-weight: bold;">教学管理</a></td>
	            <%
	            	}
	            	if("1".equals(usrPerm.get("300"))){
	            %>
	            <td id="Menu_3" width="100px" align="right"><a href="../functionList/paperManage.jsp" target="mainFrame"  style="text-decoration:none; font-size:14px;color:#fff;font-weight: bold;">试题管理</a></td>
	            <%
	            	}
	            	if("1".equals(usrPerm.get("400"))){
	            %>
	            <td id="Menu_4" width="100px" align="right"><a href="../functionList/examDbManage.jsp" target="mainFrame"  style="text-decoration:none; font-size:14px;color:#fff;font-weight: bold;">题库管理</a></td>
	            <%
	            	}
	            	if("1".equals(usrPerm.get("500"))){
	            %>
	            <td id="Menu_5" width="100px" align="right"><a href="../functionList/paperDbManage.jsp" target="mainFrame"  style="text-decoration:none; font-size:14px;color:#fff;font-weight: bold;">卷库管理</a></td>
	            <%
	            	}
	            	if("1".equals(usrPerm.get("600"))){
	            %>
	            <td id="Menu_6" width="100px" align="right"><a href="../functionList/sysManage.jsp" target="mainFrame"  style="text-decoration:none; font-size:14px;color:#fff;font-weight: bold;">系统管理</a></td>
	            <%
	            	}
	            %>
	            <td>&nbsp;</td>
	          </tr>
	        </table>
        </td>
      </tr>
    </table>