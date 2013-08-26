/**
 * -------------------------------------------------------------------------------------
 * | 文件名：	StudentServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2012-12-17 下午2:34:54
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-17 下午2:34:54
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

/**
 * <p>类名：net.ib.util.service.impl.StudentServiceImpl </p>
 * <p>描述：教师管理接口实现</p>
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

	// 添加学生

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
		// 如果有重复项
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
					logger.info("添加学生过程中学号重复");
					result = "{\"result\":\"failedId\",\"text\":\"该学号的学生已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (TempName.equals(StudentName)) {
					logger.info("添加学生过程中学生名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"该学生已经存在，请重新输入\"}";
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
			// 若此学生没有出现在已有的数据表中，则存储数据
			// 产生用户唯一标识符
			UUID uuid = UUID.randomUUID();
			String UserId = uuid.toString();
			// System.out.println(UserId);
			// 将该教师信息加到sys_user_teacher中
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
			// 将该教师信息的user_id以及所属机构加到sys_department_member

			String sql_second = "insert into sys_department_member fields(department_id,user_id) VALUEs('"
					+ StudentDepartmentId + "','" + UserId + "')";
			if ((1 == dao.execute(sql_first)) && (1 == dao.execute(sql_second))) {
				result = "{\"result\":\"succ\",\"text\":\"添加学生成功\"}";
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

	// 删除某个学生
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
			result = "{\"result\":\"succ\",\"text\":\"删除学生成功\"}";
		} else {
			result = "{\"result\":\"failed\",\"text\":\"删除学生失败\"}";
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

	// 修改学生信息
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
				result = "{\"result\":\"succ\",\"text\":\"修改学生信息成功\"}";
			} else {
				result = "{\"result\":\"succ\",\"text\":\"修改学生信息失败\"}";
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
					logger.info("修改学生信息过程中学号重复");
					result = "{\"result\":\"failedId\",\"text\":\"该学号已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (TempName.equals(StudentName)) {
					logger.info("修改学生信息过程中学生名称名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"该学生已经存在，请重新输入\"}";
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
		// 收到的DelIds格式为 id1 id2 id3 id4 ....
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
			result = "{\"result\":\"success\",\"text\":\"删除成功\"}";
		} else {
			result = "{\"result\":\"failed\",\"text\":\"删除失败\"}";
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
