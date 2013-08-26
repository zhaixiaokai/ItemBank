<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*" %>
<%
/* 	String	col1	=	request.getParameter("col1");
	String	col2	=	request.getParameter("col2");
	String	col3	=	request.getParameter("col3"); */
	String	action	=	request.getParameter("action");
	String	id	=	request.getParameter("id");
	System.err.println(action);
	
	DaoImpl di	=	new	DaoImpl();
	String	getSql	=	"delete from test	where	id='"+id+"'";
	System.err.println(getSql);
	if(getSql!=null){
		if(di.execute(getSql)!=1){
			out.write("删除失败!");
		}
		else{
			out.write("删除成功！");
		}
	}
%>