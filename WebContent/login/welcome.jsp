<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.ib.common.ConnPool,java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<font color="red" size=10> Welcome!!! </font>
	<%-- <table cellpadding=0 cellspacing=0 border=1 bordercolordark=#FFFFFF bordercolorlight=#000000>
			<tr style="background-color:#f0f0f0">
				<td style="font-size:12px;font-weight:bold">名称</td>
				<td style="font-size:12px;font-weight:bold">等级</td>
				<td style="font-size:12px;font-weight:bold">类型</td>
			</tr>
		<%
		Connection conn=null;
		String sql="select * from Test order by tpath";
		try{
			conn=ConnPool.getConnection();
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){%>
				<tr>
					<td style="font-size:12px;"><%=rs.getString("name")%></td>
					<td style="font-size:12px;"><%=rs.getString("tpath")%></td>
					<td style="font-size:12px;"><%=rs.getString("classic")%></td>
				</tr>
			<%}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(null!=conn){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		%>
		</table> --%>
</body>
</html>