/**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TeacherAddServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ��������ӽ�ʦ�ӿ�ʵ��
 * | ���ߣ�HuangJu
 * | �������ڣ�2012-12-6 ����9:05:04
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-6 ����9:05:04
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import net.ib.util.service.Service;
import net.ib.util.service.TeacherService;

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

/**
 * <p>
 * ������net.ib.util.service.impl.TeacherAddServiceImpl
 * </p>
 * <p>
 * ��������ӽ�ʦ�ӿ�ʵ��
 * </p>
 * <p>
 * </p>
 */
public class TeacherServiceImpl implements TeacherService {
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

	// ��ӽ�ʦ
	public String addTeacher(String TeacherDepartmentId, String TeacherID,
			String TeacherName, String Birthday, String Gender,
			String IdetiCard, String Email, String Phone, String Address) {
		String result = "";
		// ͨ��id��name��ѯ�Ƿ�����뵱ǰ��Ҫ��ӵ������ظ�����
		String Sql = "select * from sys_user_teacher where name='"
				+ TeacherName + "' or school_id='" + TeacherID + "'";
		List<Map> list = dao.executeQuery(Sql);
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
				if (TempId.equals(TeacherID)) {
					logger.info("��ӽ�ʦ�����н̹����ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"�ý̹����Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (TempName.equals(TeacherName)) {
					logger.info("��ӽ�ʦ�����н�ʦ�����ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�ý�ʦ�Ѿ����ڣ�����������\"}";
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
		// ���˽�ʦû�г��������е����ݱ��У���洢����
		else {
			// �����û�Ψһ��ʶ��
			UUID uuid = UUID.randomUUID();
			String UserId = uuid.toString();
			// ���ý�ʦ��Ϣ�ӵ�sys_user_teacher��
			String sql_first = "insert into sys_user_teacher fields(user_id,name,gender,birthday,"
					+ "id,school_id,email,address,telephone,username) "
					+ "VALUES('"
					+ UserId
					+ "','"
					+ TeacherName
					+ "','"
					+ Gender
					+ "',to_date('"
					+ Birthday
					+ "','yyyy-MM-dd'),"
					+ "'"
					+ IdetiCard
					+ "','"
					+ TeacherID
					+ "','"
					+ Email
					+ "','"
					+ Address + "','" + Phone + "',"+TeacherID+")";

			if (1 == dao.execute(sql_first)) {
				result = "{\"result\":\"succ\",\"text\":\"��ӽ�ʦ�ɹ�\"}";
				// ���ý�ʦ��Ϣ��user_id�Լ����������ӵ�sys_department_member

				String sql_second = "insert into sys_department_member fields(department_id,user_id) VALUEs('"
						+ TeacherDepartmentId + "','" + UserId + "')";
				dao.execute(sql_second);
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

	// ɾ����ʦ
	public String DeleteTeacher(String TeacherId) {

		// TODO Auto-generated method stub
		String Sql = "delete from SYS_USER_TEACHER where user_id='" + TeacherId
				+ "'";
		logger.debug(Sql);
		String result = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if (1 == dao.execute(Sql)) {
			result = "{\"result\":\"succ\",\"text\":\"ɾ����ʦ�ɹ�\"}";
		} else {
			result = "{\"result\":\"failed\",\"text\":\"ɾ����ʦʧ��\"}";
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

	// �޸Ľ�ʦ��Ϣ
	public String UpdateTeacher(String OldTeacherId, String TeacherID,
			String TeacherName, String Birthday, String Gender,
			String IdetiCard, String Email, String Phone, String Address) {
		String sql = "select school_id,NAME from SYS_USER_TEACHER where (school_id='"
				+ TeacherID
				+ "' or NAME='"
				+ TeacherName
				+ "') and user_id!='"
				+ OldTeacherId + "'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map> list = dao.executeQuery(sql);
		// ������ʾʱ��Ŀո���д���
		if (Birthday.equals(" ")) {
			Birthday = "";
		}
		if (Gender.equals(" ")) {
			Gender = "";
		} else if (Gender.equals("��")) {
			Gender = "1";
		} else {
			Gender = "0";
		}
		if (IdetiCard.equals(" ")) {
			IdetiCard = "";
		}
		if (Email.equals(" ")) {
			Email = "";
		}
		if (Phone.equals(" ")) {
			Phone = "";
		}
		if (Address.equals(" ")) {
			Address = "";
		}

		String result = "";
		if (list.size() == 0) {
			sql = "update SYS_USER_TEACHER set school_id='" + TeacherID
					+ "',NAME='" + TeacherName + "',GENDER='" + Gender
					+ "',EMAIL='" + Email + "',ADDRESS='" + Address
					+ "',TELEPHONE='" + Phone + "',ID='" + IdetiCard
					+ "',BIRTHDAY=to_date('" + Birthday
					+ "','yyyy-MM-dd') where user_id='" + OldTeacherId + "'";
			if (1 == dao.execute(sql)) {
				result = "{\"result\":\"succ\",\"text\":\"�޸Ľ�ʦ��Ϣ�ɹ�\"}";
			} else {
				result = "{\"result\":\"succ\",\"text\":\"�޸Ľ�ʦ��Ϣʧ��\"}";
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
				if (TempId.equals(TeacherID)) {
					logger.info("�޸Ľ�ʦ��Ϣ�����н̹����ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"�ý̹����Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (TempName.equals(TeacherName)) {
					logger.info("�޸Ľ�ʦ��Ϣ�����н�ʦ���������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�ý�ʦ�Ѿ����ڣ�����������\"}";
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

	// ����ɾ����ʦ
	public String BulkDeleteTeacher(String TeacherIds) {

		logger.debug(TeacherIds);
		// �յ���DelIds��ʽΪ id1 id2 id3 id4 ....
		String[] array = TeacherIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		String Sql = "delete from SYS_USER_TEACHER where";
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
