<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.sql.*,javax.sql.rowset.serial.*"
	import="java.io.*" import="javax.servlet.*"
	import="javax.servlet.http.*" import="java.util.*"%>
<%@ page import="java.text.*" import="javax.servlet.ServletException"
	import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page
	import="org.apache.commons.fileupload.servlet.ServletFileUpload"
	import="org.apache.commons.fileupload.util.Streams"%>
<%@ page import="org.apache.poi.poifs.filesystem.POIFSFileSystem"
	import="org.apache.poi.xssf.usermodel.XSSFWorkbook"
	import="org.apache.poi.xssf.usermodel.XSSFSheet"%>
<%@ page import="org.apache.poi.xssf.usermodel.XSSFRow"
	import="org.apache.poi.xssf.usermodel.XSSFCell"%>
<%@ page import="net.ib.util.service.impl.*"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

</head>
<body>



	<%
	/*
	Function：上传文件到服务器工作目录下
	*/
	String id = "";
		File tmpDir = null;
		File saveDir = null;
		String savePath = "/pages";
		String dsavePath = request.getRealPath(savePath);
		boolean result = true;
		saveDir = new File(dsavePath);
		if (!saveDir.isDirectory())
			saveDir.mkdir();
		
		//	String name = "";
		//	String discription = "";
		//	String dep = "";
		//	String author = "";
			String tem_suffix = "";
			String temname = "";
			String tem_realname = "";
			String temUniqueName = "";
		//	InputStream dbIn = null;
			//Connection con = JdbcConnection.getConnection();
			
	//		Statement myStatement = con.createStatement();
			if (ServletFileUpload.isMultipartContent(request)) {
				DiskFileItemFactory dff = new DiskFileItemFactory(); //创建工厂
				dff.setRepository(tmpDir); //设置目录
				dff.setSizeThreshold(1024000); //文件大小门限
				ServletFileUpload upload = new ServletFileUpload(dff);//创建一个文件上传对象
				upload.setFileSizeMax(5000000);//设置文件最大尺寸
				upload.setSizeMax(10000000);//设置缓冲区大小
				List items = upload.parseRequest(request); //获得所有文件
				Iterator iter = items.iterator();
				while (iter.hasNext()) { //依次处理每一个文件
					FileItem fis = (FileItem) iter.next();
					if (!fis.isFormField() && fis.getName().length() > 0) {    //如果不是form表单
						String fileName = fis.getName(); //获得文件名及其路径
						System.out.print(fis.getName());
						tem_realname = fis.getName().substring(fis.getName().lastIndexOf("\\"),
								fis.getName().lastIndexOf(".")); //获取文件信息
						tem_suffix = fis.getName().substring(
								fis.getName().lastIndexOf("."));
						temUniqueName = tem_realname;
						
						InputStream in = new BufferedInputStream(     //保存文件
								fis.getInputStream()); 
						
						BufferedOutputStream out1 = new BufferedOutputStream(     //在工程路径下保存一份文件
								new FileOutputStream(
										new File(saveDir + "\\"+ temUniqueName
												+ tem_suffix)));
						Streams.copy(in, out1, true);
						
					//	dbIn = new FileInputStream(saveDir + "\\"+ temUniqueName+ tem_suffix);
					}
				}
			}
			String fileToBeRead=saveDir + "\\"+ temUniqueName+ tem_suffix;
			// System.out.print(fileToBeRead);
			fileToBeRead = fileToBeRead.replace("\\", "\\\\");
			 System.out.print(fileToBeRead);
		response.sendRedirect("teacherUploadView.jsp?fileToBeRead="+fileToBeRead);
	%>





</body>
</html>