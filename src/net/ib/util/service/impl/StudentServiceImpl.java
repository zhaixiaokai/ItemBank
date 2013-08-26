/**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	StudentServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2012-12-17 ����2:34:54
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-17 ����2:34:54
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

/**
 * <p>������net.ib.util.service.impl.StudentServiceImpl </p>
 * <p>��������ʦ����ӿ�ʵ��</p>
 * <p></p>
 */
import net.ib.util.service.Service;
import net.ib.util.service.StudentService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.dao.DaoImpl;

public class StudentServiceImpl implements StudentService {

	private static Logger logger = Logger.getLogger(TeacherServiceImpl.class);
	Dao dao;
	Service service;

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

	// ���ѧ��

	@Override
	public String addStudent(String StudentDepartmentId, String StudentID,
			String StudentName, String Address, String TelPhone) {
		// TODO Auto-generated method stub
		String result;
		DaoImpl daoImpl = new DaoImpl();
		List<Map> list = (List<Map>) daoImpl
				.executeQuery("select * from sys_user_student where name='"
						+ StudentName + "' or school_id='" + StudentID + "'");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		// ������ظ���
		if (list.size() != 0) {
			String TempId = "";
			String TempName = "";
			Map map = new HashMap();
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				TempId = (String) map.get("school_id");
				TempName = (String) map.get("name");
				logger.debug(TempName);
				logger.debug(TempId);
				if (TempId.equals(StudentID)) {
					logger.info("���ѧ��������ѧ���ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"��ѧ�ŵ�ѧ���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (TempName.equals(StudentName)) {
					logger.info("���ѧ��������ѧ�������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"��ѧ���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			// ����ѧ��û�г��������е����ݱ��У���洢����
			// �����û�Ψһ��ʶ��
			UUID uuid = UUID.randomUUID();
			String UserId = uuid.toString();
			// System.out.println(UserId);
			// ���ý�ʦ��Ϣ�ӵ�sys_user_teacher��
			String sql_first = "insert into sys_user_student fields(user_id,name,"
					+ "school_id,address,telephone,username) "
					+ "VALUES('"
					+ UserId
					+ "','"
					+ StudentName
					+ "','"
					+ StudentID
					+ "','"
					+ Address
					+ "','" + TelPhone + "','"+StudentID+"')";
			// ���ý�ʦ��Ϣ��user_id�Լ����������ӵ�sys_department_member

			String sql_second = "insert into sys_department_member fields(department_id,user_id) VALUEs('"
					+ StudentDepartmentId + "','" + UserId + "')";
			if ((1 == dao.execute(sql_first)) && (1 == dao.execute(sql_second))) {
				result = "{\"result\":\"succ\",\"text\":\"���ѧ���ɹ�\"}";
				try {
					PrintWriter pw = response.getWriter();
					pw.print(result);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	// ɾ��ĳ��ѧ��
	@Override
	public String DeleteStudent(String StudentId) {
		// TODO Auto-generated method stub

		String Sql = "delete from SYS_USER_STUDENT where user_id='" + StudentId
				+ "'";
		logger.debug(Sql);
		String result = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if (1 == dao.execute(Sql)) {
			result = "{\"result\":\"succ\",\"text\":\"ɾ��ѧ���ɹ�\"}";
		} else {
			result = "{\"result\":\"failed\",\"text\":\"ɾ��ѧ��ʧ��\"}";
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// �޸�ѧ����Ϣ
	@Override
	public String UpdateStudent(String OldStudentId, String StudentID,
			String StudentName, String Address, String TelPhone) {
		// TODO Auto-generated method stub
		String sql = "select school_id,NAME from SYS_USER_STUDENT where (school_id='"
				+ StudentID
				+ "' or NAME='"
				+ StudentName
				+ "') and user_id!='"
				+ OldStudentId + "'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map> list = dao.executeQuery(sql);
		String result = "";
		if (list.size() == 0) {
			sql = "update SYS_USER_STUDENT set school_id='" + StudentID
					+ "',NAME='" + StudentName + "',ADDRESS='" + Address
					+ "',TELEPHONE='" + TelPhone + "'  where user_id='"
					+ OldStudentId + "'";
			System.out.print(sql);
			if (1 == dao.execute(sql)) {
				result = "{\"result\":\"succ\",\"text\":\"�޸�ѧ����Ϣ�ɹ�\"}";
			} else {
				result = "{\"result\":\"succ\",\"text\":\"�޸�ѧ����Ϣʧ��\"}";
			}
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String TempId = "";
			String TempName = "";
			Map map = new HashMap();
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				TempId = (String) map.get("school_id");
				TempName = (String) map.get("name");
				logger.debug(TempName);
				logger.debug(TempId);
				if (TempId.equals(StudentID)) {
					logger.info("�޸�ѧ����Ϣ������ѧ���ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"��ѧ���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (TempName.equals(StudentName)) {
					logger.info("�޸�ѧ����Ϣ������ѧ�����������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"��ѧ���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	@Override
	public String BulkDeleteStudent(String StudentIds) {
		// TODO Auto-generated method stub
		logger.debug(StudentIds);
		// �յ���DelIds��ʽΪ id1 id2 id3 id4 ....
		String[] array = StudentIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		String Sql = "delete from SYS_USER_STUDENT where";
		String result = "";
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				Sql += " or";
			}
			Sql += " user_id='" + array[i] + "'";
		}
		logger.debug(Sql);

		if (1 == dao.execute(Sql)) {
			result = "{\"result\":\"success\",\"text\":\"ɾ���ɹ�\"}";
		} else {
			result = "{\"result\":\"failed\",\"text\":\"ɾ��ʧ��\"}";
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
