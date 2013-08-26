
<%-- <%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.bupt.itembank.*,java.util.*" %>
<%@ page import="com.bupt.itembank.dao.*" %>
<!-- 本页面用来执行从前台发来的sql语句，其中request.getParameter("sql")为前台传来的 -->
<%

	String	sql	=	request.getParameter("sql");
	String	backAdd	=	request.getParameter("backAdd");
	System.err.println(sql+" ! "+backAdd);
	DaoImpl di	=	new	DaoImpl();
	di.execute(sql);
	if(backAdd!=null)
		response.sendRedirect(backAdd);
	else
		System.err.println("backAdd is null");
	

%> --%>