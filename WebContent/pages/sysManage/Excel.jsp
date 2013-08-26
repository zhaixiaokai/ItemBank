<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,javax.sql.rowset.serial.*"
	import="java.io.*" import="javax.servlet.*"
	import="javax.servlet.http.*" import="java.util.*"%>
<%@ page import="java.text.*" import="javax.servlet.ServletException"
	import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.poi.poifs.filesystem.POIFSFileSystem"
	import="org.apache.poi.xssf.usermodel.XSSFWorkbook"
	import="org.apache.poi.xssf.usermodel.XSSFSheet"%>
<%@ page import="org.apache.poi.xssf.usermodel.XSSFRow"
	import="org.apache.poi.xssf.usermodel.XSSFCell"%>
<%@ page import="net.ib.util.service.impl.*"%>

<%
	/*
Function:Excel文件解析
author: HuangJu

*/
String fileToBeRead=request.getParameter("dataUrl");// 解析文件的路徑
// System.out.println(fileToBeRead);
//存放解析的数据
ArrayList<Map> list =new ArrayList<Map>();

InputStream in=new FileInputStream(fileToBeRead);

XSSFWorkbook workbook = new XSSFWorkbook(in); 

// XSSFSheet sheet = workbook.getSheet("Sheet1");

int sheetNumber=workbook.getNumberOfSheets();// 獲取excel的sheet數目

// 遍历Excel的所有数据
for(int k=0;k<sheetNumber;k++){

	XSSFSheet sheet = workbook.getSheetAt(k); // 创建sheet
	//获取每一sheet的行数和列数
	int rowFirstNumber=sheet.getFirstRowNum(); 

	int rowLastNumber=sheet.getLastRowNum();
			
	// 从第二行开始为数据
	for(int i=rowFirstNumber+1;i<=rowLastNumber;i++){								
		
		XSSFRow row = sheet.getRow(i);
		 //获取每一row的起始单元数、和结束单元数
		int columFirstNumber=row.getFirstCellNum();
		int columLastNumber=row.getLastCellNum();
		// 存取每一单元格数据,map中key为Excel的第一行标题
		Map<String,String> map=new HashMap<String,String>();
		
		for(int j=columFirstNumber;j<columLastNumber;j++){
	
	XSSFCell cell = row.getCell(j);
	// 獲取標題行內容
	XSSFRow TitleRow=sheet.getRow(rowFirstNumber);
	XSSFCell TitleCell=TitleRow.getCell(j);
	//单元格数据类型
	int CellDateType = cell.getCellType();
	
	String cellValue;//数据格内容
	String TitleCellValue=TitleCell.getStringCellValue();//標題行內容
	
	//将Excel单元格中的数据全部转换为String格式数据，然后再存为List<Map>形式

		if(CellDateType==XSSFCell.CELL_TYPE_NUMERIC){
	cellValue=String.valueOf(cell.getNumericCellValue());
	map.put(TitleCellValue, cellValue);
	}
	
		else{
		cellValue=cell.getStringCellValue();
		map.put(TitleCellValue, cellValue);
		
	}


		}
		list.add(map);
	}
	
	
}

		// ListMap数据转换为Json格式
	ServiceImpl	si	=	new	ServiceImpl();
	String Json	=	si.DataListToJson(list);
	out.print(Json);
%>