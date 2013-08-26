<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="net.ib.util.service.*"%>
<%@ page import="net.ib.util.service.impl.*"%>
<%
Service	service	=	new	ServiceImpl();
File file = new File("d:\\TestBlob.jpg");   
InputStream is=null;
try {
	is = new FileInputStream(file);
} catch (FileNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}   
service.exercuteUpdateWithBlob("testblob", "id", "C8CB90754D264357B13B847A402FB0AA", "testblob", is);
System.err.println("execute over!!!!!!");
%>