/**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ReadExcelImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2012-12-3 ����4:09:37
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-3 ����4:09:37
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
 * ������net.ib.util.service.impl.ReadExcelImpl
 * </p>
 * <p>
 * �����������ϴ���Excel�ļ�
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

	
	// ���ϴ��ĵ�excel����н��������ս������Ϊlist��ʽ��excel����һ����¼Ϊlist�е�ĳ����¼
	@Override
	public List<Map> ReadExcelList(String fileUrl) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Map> list = new ArrayList<Map>();
		InputStream in = new FileInputStream(fileUrl);
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		// XSSFSheet sheet = workbook.getSheet("Sheet1");

		int sheetNumber = workbook.getNumberOfSheets();// �@ȡexcel��sheet��Ŀ

		// ����Excel����������
		for (int k = 0; k < sheetNumber; k++) {

			 // ����sheet
			XSSFSheet sheet = workbook.getSheetAt(k);
			// ��ȡÿһsheet������������
			int rowFirstNumber = sheet.getFirstRowNum();
			int rowLastNumber = sheet.getLastRowNum();
			// �ӵڶ��п�ʼΪ����
			for (int i = rowFirstNumber ; i <= rowLastNumber; i++) {

				XSSFRow row = sheet.getRow(i);
				// ��ȡÿһrow����ʼ��Ԫ�����ͽ�����Ԫ��
				int columFirstNumber = row.getFirstCellNum();
				int columLastNumber = row.getLastCellNum();
				// ��ȡÿһ��Ԫ������,map��keyΪExcel�ĵ�һ�б���
				Map<String, String> map = new HashMap<String, String>();
				for (int j = columFirstNumber; j < columLastNumber; j++) {

					XSSFCell cell = row.getCell(j);
					// �@ȡ���}�Ѓ���
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
					// ��Ԫ����������
					int CellDateType = cell.getCellType();
					String cellValue="";// ���ݸ�����
					//String TitleCellValue = TitleCell.getStringCellValue();// ���}�Ѓ���

					// ��Excel��Ԫ���е�����ȫ��ת��ΪString��ʽ���ݣ�Ȼ���ٴ�ΪList<Map>��ʽ

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
	
	
	
	//��ɽ�ʦ��Ϣ�������
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
		
		// ��excel���в�ѯ�Ƿ��������ݱ����ظ������ݵ�sql
		String QuerySql = "select * from SYS_USER_TEACHER where ";
		
		// ����
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
		
		// ���������������ݲ����ʦ�û����ݱ���
		String InsertSql = "insert into SYS_USER_TEACHER fields(user_id,name,gender,school_id,email,address,telephone)";
		// ����������������������󶨣������ϴ���������ӵ�ָ���Ļ�������
		String InsertDepartmentSql=" insert into SYS_DEPARTMENT_MEMBER fields (department_id,user_id)";
		// ������ظ�
		if (QueryList.size() != 0) {
			Map map1 =new HashMap();
			int flag=0;//��ʾ���ظ����ݵĸ���
			for(int i=1;i<list.size();i++){
				map1=list.get(i);
				Name = (String) map1.get("name");
				Gender = (String) map1.get("gender");
				School_Id = (String) map1.get("schoolid");
				Email = (String) map1.get("email");
				Address = (String) map1.get("address");
				Telephone = (String) map1.get("telephone");
				//���û��Ա����ת����->1,Ů->0
				int GenderInt;
				if(Gender=="Ů")GenderInt=0;
				else GenderInt=1;
				//�����û�Ψһ��ʶ��
				UUID uuid = UUID.randomUUID(); 
				String UserId=uuid.toString();
				
				//�޳�Excel���������ݿ�����ظ������ݣ�����δ�ظ���������ӵ����ݿ���
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
		// ���ݱ���û���ظ�
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
				if(Gender=="Ů")GenderInt=0;
				else GenderInt=1;
				//�����û�Ψһ��ʶ��
				UUID uuid = UUID.randomUUID(); 
				String UserId=uuid.toString();
				
				// ��Excel����������ӵ����ݿ���
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
		// ��������
		if(QueryList.size()==(list.size()-1)){
			result = "{\"result\":\"success\",\"text\":\"����ȫ���ظ�\"}";
		}
		else if ((1 == dao.execute(InsertSql))&&(1 == dao.execute(InsertDepartmentSql))) {
			result = "{\"result\":\"success\",\"text\":\"��ӳɹ�\"}";
		} 
		else {
			result = "{\"result\":\"failed\",\"text\":\"���ʧ��\"}";
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


// ���ѧ����Ϣ�������
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
		
		// ��excel���в�ѯ�Ƿ��������ݱ����ظ������ݵ�sql
		String QuerySql = "select * from SYS_USER_STUDENT where ";
		
		// ����
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
		
		// ���������������ݲ����ʦ�û����ݱ���
		String InsertSql = "insert into SYS_USER_STUDENT fields(user_id,name,school_id,address,telephone)";
		// ����������������������󶨣������ϴ���������ӵ�ָ���Ļ�������
		String InsertDepartmentSql=" insert into SYS_DEPARTMENT_MEMBER fields (department_id,user_id)";
		// ������ظ�
		if (QueryList.size() != 0) {
			Map map1 =new HashMap();
			int flag=0;//��ʾ���ظ����ݵĸ���
			for(int i=1;i<list.size();i++){
				map1=list.get(i);
				Name = (String) map1.get("name");
				School_Id = (String) map1.get("school_id");
				Address = (String) map1.get("address");
				Telephone = (String) map1.get("telephone");
				//�����û�Ψһ��ʶ��
				UUID uuid = UUID.randomUUID(); 
				String UserId=uuid.toString();
				
				//�޳�Excel���������ݿ�����ظ������ݣ�����δ�ظ���������ӵ����ݿ���
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
		// ���ݱ���û���ظ�
		else {
			Map map1 =new HashMap();
			for (int i = 1; i < list.size(); i++) {
				map1=list.get(i);
				Name = (String) map1.get("name");
				School_Id = (String) map1.get("school_id");
				Address = (String) map1.get("address");
				Telephone = (String) map1.get("telephone");

				//�����û�Ψһ��ʶ��
				UUID uuid = UUID.randomUUID(); 
				String UserId=uuid.toString();
				
				// ��Excel����������ӵ����ݿ���
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
		// ������д�����ݿ�
		if(QueryList.size()==(list.size()-1)){
			result = "{\"result\":\"success\",\"text\":\"����ȫ���ظ�\"}";
		}
		else if ((1 == dao.execute(InsertSql))&&(1 == dao.execute(InsertDepartmentSql))) {
			result = "{\"result\":\"success\",\"text\":\"��ӳɹ�\"}";
		} 
		else {
			result = "{\"result\":\"failed\",\"text\":\"���ʧ��\"}";
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

	
	//����ϴ���ѧ���ɼ������
	@Override
	public String StudentScoreToDatabase(List<Map> list, String CurseId,
			String CurseTime) {
		// TODO Auto-generated method stub
		String result = "";

		
		//�����ϴ���ѧ����ѧ�Ų�ѯ��Ӧ��USER_ID
		String QueryStudentId=" select sus.user_id, sus.school_id from SYS_USER_STUDENT sus where ";
		// ��ѯ�ϴ���excel�����Ƿ��������ݱ����ظ�������
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
		
		//ȡ���ϴ���excel���в�������ϵͳ�е��û�
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
				WrongStudentList+=SchoolId+",";//��ʾ�ϴ���excel���в�������ϵͳ�е�ѧ���б�

		}
		
		// ���������������ݲ������ݱ���
		String InsertSql = "insert into SYS_STUDENT_SCORE fields(STUDENT_ID,CURSE_ID,EXAM_TIME,SCORE_TOTAL)";
		// ������ظ�
		int flag_repeat=0;//��ʾ���ظ����ݵĸ���
		
		if (QueryList.size() != 0) {
			Map map1 =new HashMap();
			
			for (int i = 1; i < list.size(); i++) {
				map1 = list.get(i);
				String Id = (String) map1.get("SchoolId");
				String StudentScore = (String) map1.get("Score");

				// �޳�Excel���������ݿ�����ظ������ݣ�����û���ظ���������ӵ����ݿ���
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
		// ���ݱ���û���ظ�
		
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
		// ��������
		if (flag_repeat!=0 && 1== dao.execute(InsertSql)) {
			result = "{\"result\":\"success\",\"text\":\"��ӳɹ�\"}";
		} 
		else if(0==flag_repeat){
			result = "{\"result\":\"success\",\"text\":\"����ȫ���ظ�\"}";
			//result="����ȫ���ظ�";
		}
		else {
			result = "{\"result\":\"failed\",\"text\":\"���ʧ��\"}";
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
