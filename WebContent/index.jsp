<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	if(null!=session.getAttribute("session_data")){
		session.removeAttribute("session_data");
	}

	response.sendRedirect("login/HomePage.jsp");
%>