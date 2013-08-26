<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="net.ib.util.dao.*,java.util.*" %>
<%
	String	TableName	=	request.getParameter("table");
	String	id	=	request.getParameter("id");
	String	pid	=	request.getParameter("pid");
	String	sno	=	request.getParameter("sno");
	String	node_path	=	request.getParameter("node_path");
	String	tree_id	=	request.getParameter("tree_id");
	String	node_series	=	request.getParameter("node_series");
	String	node_explain	=	request.getParameter("node_explain");
	String	leaf	=	request.getParameter("leaf");
	String	text	=	request.getParameter("text");

	String	action	=	request.getParameter("action").toLowerCase();
	System.err.println(request.getParameter("text"));
	DaoImpl di	=	new	DaoImpl();
	if(action.equals("update")){
		String updateSql = "UPDATE " + TableName + " SET id ='" + id
		+ "' ," + "pid='" + pid + "' ," + "sno='" + sno + "' ,"
		+ "node_path='" + node_path + "' ," + "tree_id='"
		+ tree_id + "' ," + "node_series='" + node_series
		+ "' ," + "node_explain='" + node_explain + "' ,"
		+ "leaf='" + leaf + "' ," + "text='" + text + "' "
		+ " WHERE id = '" + id + "'";
		System.err.println(updateSql);
		di.execute(updateSql);
	}
	//(ID, tree_id, TEXT, PID, SNO, NODE_PATH, NODE_SERIES, LEAF) VALUES ('sd', 'sdf', 'sdf', '21', '1', 'sdf', '1', 'sdtre')
	else if (action.equals("insert")) {
		String insertSQL = "insert	into "
				+ TableName
				+ " (tree_id, TEXT, PID, SNO, NODE_PATH, NODE_SERIES, LEAF,node_explain) values ('"
				+ tree_id + "', '" + text
				+ "', '" + pid + "', '" + sno + "', '" + node_path
				+ "', '" + node_series + "', '" + leaf+"', '" + node_explain + "')";
		System.err.println(insertSQL);
		di.execute(insertSQL);
	}
	else if(action.equals("delete")){
		String	deleteSQL="delete from "+TableName+" where id='"+id+"'";
		System.err.println(deleteSQL);
		di.execute(deleteSQL);
	}
%>