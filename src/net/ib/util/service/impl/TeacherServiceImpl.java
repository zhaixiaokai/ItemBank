/**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TeacherAddServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：添加教师接口实现
 * | 作者：HuangJu
 * | 创建日期：2012-12-6 下午9:05:04
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 下午9:05:04
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
 * 类名：net.ib.util.service.impl.TeacherAddServiceImpl
 * </p>
 * <p>
 * 描述：添加教师接口实现
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

	// 添加教师
	public String addTeacher(String TeacherDepartmentId, String TeacherID,
			String TeacherName, String Birthday, String Gender,
			String IdetiCard, String Email, String Phone, String Address) {
		String result = "";
		// 通过id、name查询是否存在与当前所要添加的项有重复的项
		String Sql = "select * from sys_user_teacher where name='"
				+ TeacherName + "' or school_id='" + TeacherID + "'";
		List<Map> list = dao.executeQuery(Sql);
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
				if (TempId.equals(TeacherID)) {
					logger.info("添加教师过程中教工号重复");
					result = "{\"result\":\"failedId\",\"text\":\"该教工号已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (TempName.equals(TeacherName)) {
					logger.info("添加教师过程中教师名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"该教师已经存在，请重新输入\"}";
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
		// 若此教师没有出现在已有的数据表中，则存储数据
		else {
			// 产生用户唯一标识符
			UUID uuid = UUID.randomUUID();
			String UserId = uuid.toString();
			// 将该教师信息加到sys_user_teacher中
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
				result = "{\"result\":\"succ\",\"text\":\"添加教师成功\"}";
				// 将该教师信息的user_id以及所属机构加到sys_department_member

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

	// 删除教师
	public String DeleteTeacher(String TeacherId) {

		// TODO Auto-generated method stub
		String Sql = "delete from SYS_USER_TEACHER where user_id='" + TeacherId
				+ "'";
		logger.debug(Sql);
		String result = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if (1 == dao.execute(Sql)) {
			result = "{\"result\":\"succ\",\"text\":\"删除教师成功\"}";
		} else {
			result = "{\"result\":\"failed\",\"text\":\"删除教师失败\"}";
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

	// 修改教师信息
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
		// 对于显示时候的空格进行处理
		if (Birthday.equals(" ")) {
			Birthday = "";
		}
		if (Gender.equals(" ")) {
			Gender = "";
		} else if (Gender.equals("男")) {
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
				result = "{\"result\":\"succ\",\"text\":\"修改教师信息成功\"}";
			} else {
				result = "{\"result\":\"succ\",\"text\":\"修改教师信息失败\"}";
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
					logger.info("修改教师信息过程中教工号重复");
					result = "{\"result\":\"failedId\",\"text\":\"该教工号已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (TempName.equals(TeacherName)) {
					logger.info("修改教师信息过程中教师名称名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"该教师已经存在，请重新输入\"}";
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

	// 批量删除教师
	public String BulkDeleteTeacher(String TeacherIds) {

		logger.debug(TeacherIds);
		// 收到的DelIds格式为 id1 id2 id3 id4 ....
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
