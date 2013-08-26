<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="net.ib.util.service.*"%>
<%@ page import="net.ib.util.service.impl.*"%>
<%
System.err.println("fuck");
Service	service	=	new	ServiceImpl();
InputStream is=service.getBlobInputStreamFromTable("testblob","id","C8CB90754D264357B13B847A402FB0AA","testblob");

try {
	java.io.OutputStream outStream = response.getOutputStream();
	byte[] buf = new byte[1024];
	int bytes = 0;
	while ((bytes = is.read(buf)) != -1)
	outStream.write(buf, 0, bytes);
	is.close();
	outStream.close();
} catch (Throwable e) {
	out.println(e.toString());
}
System.err.println("execute over!!!!!!");
%>