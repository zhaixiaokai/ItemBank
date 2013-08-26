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
	List<Map> list	=	di.executeQuery("select count(*) from sys_ib_classification_system cu");// 获取数据表行数
	String	DataCountStr	=	list.get(0).get("count(*)").toString();//将行数转换为字符型
	int	DataCount	=	Integer.valueOf(DataCountStr);//将行数转换为int型
	String	sql="select classification_id,classification_name,classification_explain,(case if_default_classification when 0 then '否' when 1 then '是' end) if_default_classification from (select rownum rn,cu.* from sys_ib_classification_system cu order by rownum) where rn<="+(limitNum+startNum)+" and rn>"+startNum;
	List<Map> PageList	=	di.executeQuery(sql);
	ServiceImpl	serv	=	new	ServiceImpl();
	String	json	=	serv.DataListToJson(PageList, DataCount);
	out.print(json);
%>