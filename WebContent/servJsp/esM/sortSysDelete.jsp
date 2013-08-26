<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*" %>
<%
//获取需要删除的数据的id
	String	id	=	request.getParameter("id");
	DaoImpl di	=	new	DaoImpl();
	//删除数据
	String	getSql	=	"delete from sys_ib_classification_system	where	classification_id='"+id+"'";
	System.err.println("$$$$$$$$$$$$$$$$$"+getSql);
	if(getSql!=null){
		if(di.execute(getSql)!=1){
			out.write("删除失败!");
		}
		else{
			out.write("删除成功！");
		}
	}
%>