/**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ReadExcelImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2012-12-3 下午4:09:37
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-3 下午4:09:37
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import javassist.bytecode.Descriptor.Iterator;

import net.ib.util.dao.Dao;
import net.ib.util.service.ReadExcel;
import net.ib.util.service.Service;

import org.apache.log4j.Logger;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.struts2.ServletActionContext;

/**
 * <p>
 * 类名：net.ib.util.service.impl.ReadExcelImpl
 * </p>
 * <p>
 * 描述：解析上传的Excel文件
 * </p>
 * <p>
 * </p>
 */
public class ReadExcelImpl implements ReadExcel {
	private static Logger logger = Logger.getLogger(TeacherServiceImpl.class);
	Dao	dao;
	Service	service;
	
	
	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	
	// 对上传的的excel表进行解析，最终解析结果为list形式，excel表中一条记录为list中的某条记录
	@Override
	public List<Map> ReadExcelList(String fileUrl) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Map> list = new ArrayList<Map>();
		InputStream in = new FileInputStream(fileUrl);
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		// XSSFSheet sheet = workbook.getSheet("Sheet1");

		int sheetNumber = workbook.getNumberOfSheets();// 獲取excel的sheet數目

		// 遍历Excel的所有数据
		for (int k = 0; k < sheetNumber; k++) {

			 // 创建sheet
			XSSFSheet sheet = workbook.getSheetAt(k);
			// 获取每一sheet的行数和列数
			int rowFirstNumber = sheet.getFirstRowNum();
			int rowLastNumber = sheet.getLastRowNum();
			// 从第二行开始为数据
			for (int i = rowFirstNumber ; i <= rowLastNumber; i++) {

				XSSFRow row = sheet.getRow(i);
				// 获取每一row的起始单元数、和结束单元数
				int columFirstNumber = row.getFirstCellNum();
				int columLastNumber = row.getLastCellNum();
				// 存取每一单元格数据,map中key为Excel的第一行标题
				Map<String, String> map = new HashMap<String, String>();
				for (int j = columFirstNumber; j < columLastNumber; j++) {

					XSSFCell cell = row.getCell(j);
					// 獲取標題行內容
					XSSFRow TitleRow = sheet.getRow(rowFirstNumber);
					XSSFCell TitleCell = TitleRow.getCell(j);
					int TitleDataType = TitleCell.getCellType();
					String TitleCellValue="";
					if (TitleDataType == XSSFCell.CELL_TYPE_NUMERIC) {
						TitleCellValue = String.valueOf(TitleCell
								.getNumericCellValue());
					}
					else if(TitleDataType==XSSFCell.CELL_TYPE_STRING){
						TitleCellValue = TitleCell.getStringCellValue();
					}
					// 单元格数据类型
					int CellDateType = cell.getCellType();
					String cellValue="";// 数据格内容
					//String TitleCellValue = TitleCell.getStringCellValue();// 標題行內容

					// 将Excel单元格中的数据全部转换为String格式数据，然后再存为List<Map>形式

					if (CellDateType == XSSFCell.CELL_TYPE_NUMERIC) {
						cellValue = String.valueOf(cell.getNumericCellValue());
						map.put(TitleCellValue, cellValue);
					}
					else {
						cellValue = cell.getStringCellValue();
						map.put(TitleCellValue, cellValue);
					}
				
					
			

				}
				list.add(map);
			}

		}
		return list;
	}
	
	
	
	//完成教师信息数据入库
	@Override
	public String TeacherExcelToDatabase(List<Map> list,String TeacherDepartmentId) {
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		String Name = " ";
		String Gender="";
		String School_Id = " ";
		String Email = " ";
		String Address = " ";
		String Telephone = " ";
		String result = "";
		
		// 在excel表中查询是否有与数据表中重复的数据的sql
		String QuerySql = "select * from SYS_USER_TEACHER where ";
		
		// 查重
		//System.out.println(list.size());
		for (int i =1; i <list.size(); i++) {
			map = list.get(i);
			School_Id = (String) map.get("schoolid");
			if (i != 1) {
				QuerySql += "or";
			}
			QuerySql += " school_id='" + School_Id + "'";
		}
		List<Map> QueryList = dao.executeQuery(QuerySql);
		
		// 将符合条件的数据插入教师用户数据表中
		String InsertSql = "insert into SYS_USER_TEACHER fields(user_id,name,gender,school_id,email,address,telephone)";
		// 将插入的数据与所属机构绑定，即将上传的数据添加到指定的机构下面
		String InsertDepartmentSql=" insert into SYS_DEPARTMENT_MEMBER fields (department_id,user_id)";
		// 如果有重复
		if (QueryList.size() != 0) {
			Map map1 =new HashMap();
			int flag=0;//表示不重复数据的个数
			for(int i=1;i<list.size();i++){
				map1=list.get(i);
				Name = (String) map1.get("name");
				Gender = (String) map1.get("gender");
				School_Id = (String) map1.get("schoolid");
				Email = (String) map1.get("email");
				Address = (String) map1.get("address");
				Telephone = (String) map1.get("telephone");
				//对用户性别进行转换男->1,女->0
				int GenderInt;
				if(Gender=="女")GenderInt=0;
				else GenderInt=1;
				//产生用户唯一标识符
				UUID uuid = UUID.randomUUID(); 
				String UserId=uuid.toString();
				
				//剔除Excel表中与数据库表中重复的数据，并将未重复的数据添加到数据库中
				Map map2=new HashMap();
				String RepeatSchoolId="";
				int k;
				for(k=0;k<QueryList.size();k++){
					map2=QueryList.get(k);
					RepeatSchoolId=(String) map2.get("school_id");
					
					if(School_Id.equals(RepeatSchoolId))
						break;
				}
				if(k>=QueryList.size()){
					if(flag!=0){
						InsertSql+=" union ";
						InsertDepartmentSql+=" union ";
					}	
					InsertSql += " select '"+UserId+"', '" + Name + "' ," + GenderInt + ", '"
							+ School_Id + "', '" + Email + "', '" + Address
							+ "', '" + Telephone + "' from dual";
					InsertDepartmentSql+=" select '"+TeacherDepartmentId+"', '"+UserId+"' from dual ";
					flag++;
					 
					
				}
			}

		}
		// 数据表中没有重复
		else {
			Map map1 =new HashMap();
			for (int i = 1; i < list.size(); i++) {
				map1=list.get(i);
				Name = (String) map1.get("name");
				Gender = (String) map1.get("gender");
				School_Id = (String) map1.get("schoolid");
				Email = (String) map1.get("email");
				Address = (String) map1.get("address");
				Telephone = (String) map1.get("telephone");
				int GenderInt;
				if(Gender=="女")GenderInt=0;
				else GenderInt=1;
				//产生用户唯一标识符
				UUID uuid = UUID.randomUUID(); 
				String UserId=uuid.toString();
				
				// 将Excel表中数据添加到数据库中
				if (i != 1) {
					InsertSql += " union ";
					InsertDepartmentSql+=" union ";
				}
				InsertSql += " select '"+UserId+"','" + Name + "' ," + GenderInt + ", '"
						+ School_Id + "', '" + Email + "', '" + Address
						+ "', '" + Telephone + "' from dual";
				InsertDepartmentSql+=" select '"+TeacherDepartmentId+"', '"+UserId+"' from dual ";

			}

		}
		// 插入数据
		if(QueryList.size()==(list.size()-1)){
			result = "{\"result\":\"success\",\"text\":\"数据全部重复\"}";
		}
		else if ((1 == dao.execute(InsertSql))&&(1 == dao.execute(InsertDepartmentSql))) {
			result = "{\"result\":\"success\",\"text\":\"添加成功\"}";
		} 
		else {
			result = "{\"result\":\"failed\",\"text\":\"添加失败\"}";
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
			
		
		
	
	}


// 完成学生信息数据入库
	@Override
	public String StudentExcelToDatabase(List<Map> list,
			String StudentDepartmentId) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		String Name = "";
		String School_Id = "";
		String Address = "";
		String Telephone = "";
		String result = "";
		
		// 在excel表中查询是否有与数据表中重复的数据的sql
		String QuerySql = "select * from SYS_USER_STUDENT where ";
		
		// 查重
		//System.out.println(list.size());
		for (int i =1; i <list.size(); i++) {
			map = list.get(i);
			School_Id = (String) map.get("school_id");
			if (i != 1) {
				QuerySql += "or";
			}
			QuerySql += " school_id='" + School_Id + "'";
		}
		List<Map> QueryList = dao.executeQuery(QuerySql);
		
		// 将符合条件的数据插入教师用户数据表中
		String InsertSql = "insert into SYS_USER_STUDENT fields(user_id,name,school_id,address,telephone)";
		// 将插入的数据与所属机构绑定，即将上传的数据添加到指定的机构下面
		String InsertDepartmentSql=" insert into SYS_DEPARTMENT_MEMBER fields (department_id,user_id)";
		// 如果有重复
		if (QueryList.size() != 0) {
			Map map1 =new HashMap();
			int flag=0;//表示不重复数据的个数
			for(int i=1;i<list.size();i++){
				map1=list.get(i);
				Name = (String) map1.get("name");
				School_Id = (String) map1.get("school_id");
				Address = (String) map1.get("address");
				Telephone = (String) map1.get("telephone");
				//产生用户唯一标识符
				UUID uuid = UUID.randomUUID(); 
				String UserId=uuid.toString();
				
				//剔除Excel表中与数据库表中重复的数据，并将未重复的数据添加到数据库中
				Map map2=new HashMap();
				String RepeatSchoolId="";
				int k;
				for(k=0;k<QueryList.size();k++){
					map2=QueryList.get(k);
					RepeatSchoolId=(String) map2.get("school_id");
					
					if(School_Id.equals(RepeatSchoolId))
						break;
				}
				if(k>=QueryList.size()){
					if(flag!=0){
						InsertSql+=" union ";
						InsertDepartmentSql+=" union ";
					}	
					InsertSql += " select '"+UserId+"', '" + Name + "', '"
							+ School_Id + "', '" + Address
							+ "', '" + Telephone + "' from dual";
					InsertDepartmentSql+=" select '"+StudentDepartmentId+"', '"+UserId+"' from dual ";
					flag++;
					 
					
				}
			}

		}
		// 数据表中没有重复
		else {
			Map map1 =new HashMap();
			for (int i = 1; i < list.size(); i++) {
				map1=list.get(i);
				Name = (String) map1.get("name");
				School_Id = (String) map1.get("school_id");
				Address = (String) map1.get("address");
				Telephone = (String) map1.get("telephone");

				//产生用户唯一标识符
				UUID uuid = UUID.randomUUID(); 
				String UserId=uuid.toString();
				
				// 将Excel表中数据添加到数据库中
				if (i != 1) {
					InsertSql += " union ";
					InsertDepartmentSql+=" union ";
				}
				InsertSql += " select '"+UserId+"','" + Name + "' , '"
						+ School_Id + "',  '" + Address
						+ "', '" + Telephone + "' from dual";
				InsertDepartmentSql+=" select '"+StudentDepartmentId+"', '"+UserId+"' from dual ";

			}

		}
		// 将数据写入数据库
		if(QueryList.size()==(list.size()-1)){
			result = "{\"result\":\"success\",\"text\":\"数据全部重复\"}";
		}
		else if ((1 == dao.execute(InsertSql))&&(1 == dao.execute(InsertDepartmentSql))) {
			result = "{\"result\":\"success\",\"text\":\"添加成功\"}";
		} 
		else {
			result = "{\"result\":\"failed\",\"text\":\"添加失败\"}";
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	
	//完成上传的学生成绩的入库
	@Override
	public String StudentScoreToDatabase(List<Map> list, String CurseId,
			String CurseTime) {
		// TODO Auto-generated method stub
		String result = "";

		
		//根据上传的学生的学号查询相应的USER_ID
		String QueryStudentId=" select sus.user_id, sus.school_id from SYS_USER_STUDENT sus where ";
		// 查询上传的excel表中是否有与数据表中重复的数据
		String QuerySql = "select * from SYS_STUDENT_SCORE where ( CURSE_ID='"+CurseId+"' ) and (EXAM_TIME='"+CurseTime+"') and ( ";	
		Map map = new HashMap();
		
		for (int i =1; i <list.size(); i++) {
			map = list.get(i);
			String SchoolId = (String) map.get("SchoolId");
			if (i != 1) {
				QuerySql += "or";
				QueryStudentId+=" or ";
			}
			QuerySql += " STUDENT_ID='" + SchoolId + "' ";
			QueryStudentId+=" SCHOOL_ID='"+SchoolId+"'";
		}
		QuerySql +=" ) ";
		List<Map> QueryList = dao.executeQuery(QuerySql);
		List<Map> StudentIdList=dao.executeQuery(QueryStudentId);
		
		//取出上传的excel表中不存在于系统中的用户
		String WrongStudentList="";
		Map Excelmap=new HashMap();
		Map Studentmap=new HashMap();
		
		
		for (int k = 1; k < list.size(); k++) {
			Excelmap = list.get(k);
			String SchoolId = (String) Excelmap.get("SchoolId");
			int i;
			for (i = 0; i < StudentIdList.size(); i++) {
				Studentmap = StudentIdList.get(i);
				String StudentId = (String) Studentmap.get("school_id");
				if (SchoolId.equals(StudentId)) 
					break;
			}
			if(i>=StudentIdList.size())
				WrongStudentList+=SchoolId+",";//表示上传的excel表中不存在于系统中的学生列表

		}
		
		// 将符合条件的数据插入数据表中
		String InsertSql = "insert into SYS_STUDENT_SCORE fields(STUDENT_ID,CURSE_ID,EXAM_TIME,SCORE_TOTAL)";
		// 如果有重复
		int flag_repeat=0;//表示不重复数据的个数
		
		if (QueryList.size() != 0) {
			Map map1 =new HashMap();
			
			for (int i = 1; i < list.size(); i++) {
				map1 = list.get(i);
				String Id = (String) map1.get("SchoolId");
				String StudentScore = (String) map1.get("Score");

				// 剔除Excel表中与数据库表中重复的数据，并将没有重复的数据添加到数据库中
				Map map2 = new HashMap();
				String RepeatId = "";
				int k;
				for (k = 0; k < QueryList.size(); k++) {
					map2 = QueryList.get(k);
					RepeatId = (String) map2.get("student_id");

					if (Id.equals(RepeatId))
						break;
				}
				if(k>=QueryList.size()){
					Map map3=new HashMap();
					for(int m=0;m<StudentIdList.size();m++){
						map3=StudentIdList.get(m);
						String StudentId=(String) map3.get("school_id");
						if(StudentId.equals(Id)){
							if(flag_repeat!=0){
								InsertSql+=" union ";
							}
							InsertSql+=" select '"+StudentId+"', '"+CurseId+"', '"+CurseTime+"', '"+StudentScore+"' from dual";
							flag_repeat++;
							break;
						}
					}
				}

			}

		}
		// 数据表中没有重复
		
		else {
			Map map1 =new HashMap();
			for (int i = 1; i < list.size(); i++) {
				map1=list.get(i);
				String Id = (String) map1.get("SchoolId");
				String StudentScore= (String) map1.get("Score");
				int k;
				Map map2=new HashMap();
				for (k = 0; k< StudentIdList.size(); k++) {
					map2 = StudentIdList.get(k);
					String StudentId = (String) map2.get("school_id");
					if (StudentId.equals(Id)) {
						if (flag_repeat != 0) 
							InsertSql += " union ";
						InsertSql += " select '"+Id+"', '"+CurseId+"', '"+CurseTime+"', '"+StudentScore+"' from dual ";
						flag_repeat++;
						break;
			
					}
						
				}
			

			}
			

		}
		// 插入数据
		if (flag_repeat!=0 && 1== dao.execute(InsertSql)) {
			result = "{\"result\":\"success\",\"text\":\"添加成功\"}";
		} 
		else if(0==flag_repeat){
			result = "{\"result\":\"success\",\"text\":\"数据全部重复\"}";
			//result="数据全部重复";
		}
		else {
			result = "{\"result\":\"failed\",\"text\":\"添加失败\"}";
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
