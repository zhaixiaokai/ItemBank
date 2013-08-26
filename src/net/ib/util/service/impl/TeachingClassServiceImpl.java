/**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TeachingClassServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-10 下午4:49:55
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-10 下午4:49:55
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.TeachingClassService;

/**
 * <p>
 * 类名：net.ib.util.service.impl.TeachingClassServiceImpl
 * </p>
 * <p>
 * 描述：TODO(用一句话描述该文件做什么)
 * </p>
 * <p>
 * </p>
 */
public class TeachingClassServiceImpl implements TeachingClassService {
	private static Logger logger = Logger
			.getLogger(TeachingClassServiceImpl.class);
	private Dao dao;

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	/*
	 * (非-Javadoc) <p>名称: TeachingClassAdd</p> <p>说明: 添加开课班级</p>
	 * 
	 * @param courseId 教学课程Id
	 * 
	 * @param className 班级名称
	 * 
	 * @param classId 班级ID
	 * 
	 * @param TeacherId 班级授课教师教工号
	 * 
	 * @param Explain
	 * 
	 * @param TeachingMaterialId 课程使用教材ID
	 * 
	 * @return
	 * 
	 * @see
	 * net.ib.util.service.TeachingClassService#TeachingClassAdd(java.lang.String
	 * , java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String TeachingClassAdd(String courseId, String className,
			String classId, String TeacherId, String Explain,
			String TeachingMaterialId) {
		// TODO Auto-generated method stub
		logger.debug(courseId);
		logger.debug(className);
		logger.debug(classId);
		logger.debug(TeacherId);
		logger.debug(Explain);
		logger.debug(TeachingMaterialId);
		String result = "";
		String sql = "select curse_class_id,class_name from sys_curse_class where curse_class_id='"
				+ classId + "' or class_name='" + className + "'";

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

		List<Map> list = dao.executeQuery(sql);
		if (list.size() == 0) {
			
			//判断该教师是否存在
			
			String	GetTeacherSql	=	"Select * from sys_user_teacher where school_id='"+TeacherId+"'";
			List<Map> TeacherList	=	dao.executeQuery(GetTeacherSql);
			if(TeacherList.size()==0){
				logger.info("添加教师时发现系统中没有该教师信息");
				result	=	"{\"result\":\"failed\",\"text\":\"没有该教师信息\"}";
				try {
					PrintWriter pw = response.getWriter();
					pw.print(result);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			//开始插入数据
			
			sql = "insert into sys_curse_class (curse_class_id,class_name,teacher_id,curse_id,explain,teaching_material_id) values ('"
					+ classId
					+ "','"
					+ className
					+ "','"
					+ TeacherId
					+ "','"
					+ courseId
					+ "','"
					+ Explain
					+ "','"
					+ TeachingMaterialId
					+ "')";
			logger.debug(sql);
			dao.execute(sql);
			result = "{\"result\":\"succ\",\"text\":\"添加开课班级成功\"}";
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			Map map = new HashMap();
			String ClassId = "";
			String ClassName = "";
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				ClassId = (String) map.get("curse_class_id");
				ClassName = (String) map.get("class_name");
				// 如果班级Id重复
				if (classId.equals(ClassId)) {
					logger.info("添加班级过程中班级Id重复");
					result = "{\"result\":\"failedId\",\"text\":\"班级ID已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}
				// 班级
				else if (className.equals(ClassName)) {
					logger.info("添加班级过程中班级名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"班级名称已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				} 

				else {
					logger.info("添加开课班级发生异常");
				}
			}
		}
		return null;
	}

	
	 /* (非-Javadoc)
	 * <p>名称: TeachingClassDelete</p>
	 * <p>说明: 根据ID删除开课班级</p>
	 * @param DelId
	 * @return
	 * @see net.ib.util.service.TeachingClassService#TeachingClassDelete(java.lang.String)
	 */
	@Override
	public String TeachingClassDelete(String DelId) {
		// TODO Auto-generated method stub
		logger.debug(DelId);
		String	sql	=	"delete from sys_curse_class where curse_class_id='"+DelId+"'";
		logger.debug(sql);
		String	result="";
		if(1==dao.execute(sql)){
			result	=	"{\"result\":\"删除开课班级成功\"}";
		}
		else{
			result	=	"{\"result\":\"删除开课班级失败\"}";
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

	
	 /* (非-Javadoc)
	 * <p>名称: TeachingClassUpdate</p>
	 * <p>说明: </p>
	 * @param OldClassId
	 * @param NewClassId
	 * @param NewClassName
	 * @param NewTeacherId
	 * @param NewTeachingMaterialId
	 * @param NewExplain
	 * @return
	 * @see net.ib.util.service.TeachingClassService#TeachingClassUpdate(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String TeachingClassUpdate(String OldClassId, String NewClassId,
			String NewClassName, String NewTeacherId,
			String NewTeachingMaterialId, String NewExplain) {
		// TODO Auto-generated method stub
		logger.debug(OldClassId);
		logger.debug(NewClassId);
		logger.debug(NewClassName);
		logger.debug(NewTeacherId);
		logger.debug(NewTeachingMaterialId);
		logger.debug(NewExplain);
		
		String sql = "select curse_class_id,class_name from sys_curse_class where (curse_class_id='"
				+ NewClassId + "' or class_name='" + NewClassName + "') and curse_class_id!='"+OldClassId+"'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map>	list	=	dao.executeQuery(sql);
		String	result="";
		if (list.size() == 0) {
			
			//判断该教师是否存在
			
			String	GetTeacherSql	=	"Select * from sys_user_teacher where school_id='"+NewTeacherId+"'";
			List<Map> TeacherList	=	dao.executeQuery(GetTeacherSql);
			if(TeacherList.size()==0){
				logger.info("添加教师时发现系统中没有该教师信息");
				result	=	"{\"result\":\"failed\",\"text\":\"没有该教师信息\"}";
				try {
					PrintWriter pw = response.getWriter();
					pw.print(result);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			//开始插入数据
			sql = "update sys_curse_class set curse_class_id='" + NewClassId
					+ "',class_name='" + NewClassName + "',teacher_id='"
					+ NewTeacherId + "',teaching_material_id='"
					+ NewTeachingMaterialId + "',explain='" + NewExplain
					+ "' where curse_class_id='" + OldClassId + "'";

			logger.debug(sql);
			dao.execute(sql);
			result = "{\"result\":\"succ\",\"text\":\"修改开课班级成功\"}";
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		//有重复
		else{
			Map map = new HashMap();
			String ClassId = "";
			String ClassName = "";
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				ClassId = (String) map.get("curse_class_id");
				ClassName = (String) map.get("class_name");
				// 如果班级Id重复
				if (NewClassId.equals(ClassId)) {
					logger.info("修改班级过程中班级Id重复");
					result = "{\"result\":\"failedId\",\"text\":\"班级ID已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}
				// 班级
				else if (NewClassName.equals(ClassName)) {
					logger.info("修改班级过程中班级名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"班级名称已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				} 

				else {
					logger.info("添加开课班级发生异常");
				}
			}			
		}
		return null;
	}

	
	 /* (非-Javadoc)
	 * <p>名称: TeachingClassBulkDelete</p>
	 * <p>说明: 批量删除开课班级</p>
	 * @param DelIds 删除的Id 多个Id用空格分开
	 * @return
	 * @see net.ib.util.service.TeachingClassService#TeachingClassBulkDelete(java.lang.String)
	 */
	@Override
	public String TeachingClassBulkDelete(String DelIds) {
		// TODO Auto-generated method stub
		logger.debug(DelIds);
		//收到的DelIds格式为   id1 id2 id3 id4 ....
		String[] array=DelIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		String	Sql	=	"delete from sys_curse_class where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" curse_class_id='"+array[i]+"'";
		}
		logger.debug(Sql);
		if(1==dao.execute(Sql)){
			result="{\"result\":\"success\",\"text\":\"删除成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"删除成功\"}";
		}
		
		HttpServletResponse response	=	ServletActionContext.getResponse();
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
