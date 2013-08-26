<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*,net.ib.util.service.impl.*" %>
<%
	DaoImpl di	=	new	DaoImpl();
	String	limit	=	request.getParameter("limit");
	String	start	=request.getParameter("start");
	System.err.println(start+"	"+limit);
	int	startNum	=	Integer.valueOf(start);
	int	limitNum	=	Integer.valueOf(limit);
	List<Map> list	=	di.executeQuery("select count(*) from test cu");// 获取数据项数目 
	String	DataCountStr	=	list.get(0).get("count(*)").toString();
	int	DataCount	=	Integer.valueOf(DataCountStr);
	//System.out.println("DataCount:	"+DataCount);
	
	String	sql="select * from (select rownum rn,cu.* from test cu order by rownum) where rn<="+(limitNum+startNum)+" and rn>"+startNum;
	List<Map> PageList	=	di.executeQuery(sql);
	
	
	
	ServiceImpl	serv	=	new	ServiceImpl();
	String	json	=	serv.DataListToJson(PageList, DataCount);
	System.out.println(json);
	out.print(json);
%>