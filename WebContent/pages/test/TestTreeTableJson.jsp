<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*" %>
<%@	page import="net.ib.util.service.impl.*" %>

<%
	//response.setContentType("text/html;charset=utf-8");
	DaoImpl di	=	new	DaoImpl();
	List<Map> list	=	di.executeQuery("select * from sys_teaching_chapter_tree where tree_id='jiaocaiyi' order by node_path asc");// 获取数据项数目 
	ServiceImpl	si	=	new	ServiceImpl();
	System.out.println(list);
	String	json	=	si.DataListToTreeJson(list);
	System.err.println(json);
	out.write(json);
%>