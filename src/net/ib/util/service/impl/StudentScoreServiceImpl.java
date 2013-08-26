
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	StudentScoreServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-19 上午11:12:17
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-19 上午11:12:17
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
import net.ib.util.service.Service;
import net.ib.util.service.StudentScoreService;


  /**
 * <p>类名：net.ib.util.service.impl.StudentScoreServiceImpl </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class StudentScoreServiceImpl implements StudentScoreService{
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
	
	//根据条件删除指定学生成绩
	@Override
	public String DeleteStudentScoreOnTeachingClass(String DeleteId,String selectedCurseId) {
		// TODO Auto-generated method stub
		String	Sql="delete from SYS_STUDENT_SCORE where curse_id='"+selectedCurseId+"' and student_id='"+DeleteId+"' ";
		logger.debug(Sql);
		String	result="";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if(1==dao.execute(Sql)){
			result="{\"result\":\"succ\",\"text\":\"删除学生成绩成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"删除学生成绩失败\"}";
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

	//根据条件修改指定学生成绩
	@Override
	public String UpdateStudentScoreOnTeachingClass(String SchoolID,
			String Score,String selectedCurseId, String oldSchoolID,String SelectedTeachingClassId) {
		// TODO Auto-generated method stub
		//查询新添加的学号是否存在
		String sql=" select sus.school_id from " +
				"(select * from SYS_CURSE_CLASS_MEMBER )sucm " +
				"inner join SYS_USER_STUDENT sus  on sucm.user_id= sus.user_id and sucm.curse_class_id='"+SelectedTeachingClassId+"'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map>	list	=	dao.executeQuery(sql);
		String result="";
		if(list.size()!=0){
			Map map=new HashMap();
			int i;
			for( i=0;i<list.size();i++){
				map=list.get(i);
				String Id=(String) map.get("school_id");
				if(Id.equals(SchoolID))
					break;
			}
			if(i>=list.size()){
				logger.info("修改学生成绩过程中该学号不存在！");
				result = "{\"result\":\"failedId\",\"text\":\"该学号号在系统中不存在，请重新输入\"}";
				try {
					PrintWriter pw = response.getWriter();
					pw.print(result);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				sql="update SYS_STUDENT_SCORE set student_id='"+SchoolID+"', score_total='"+Score+"' " +
						" where curse_id='"+selectedCurseId+"' and student_id='"+oldSchoolID+"' ";
				if(1==dao.execute(sql)){
					result="{\"result\":\"succ\",\"text\":\"修改学生成绩成功\"}";
				}
				else{
					result="{\"result\":\"failure\",\"text\":\"修改学生成绩失败\"}";
				}
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

	//根据指定的行政班级删除某个学生的成绩
	@Override
	public String DeleteStudentScoreOnClass(String DeleteId,
			String selectedCurseId) {
		// TODO Auto-generated method stub
		String	Sql="delete from SYS_STUDENT_SCORE where curse_id='"+selectedCurseId+"' and student_id='"+DeleteId+"' ";
		logger.debug(Sql);
		String	result="";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if(1==dao.execute(Sql)){
			result="{\"result\":\"succ\",\"text\":\"删除学生成绩成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"删除学生成绩失败\"}";
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
	//修改指定班级下的某个学生的成绩信息
	@Override
	public String UpdateStudentScoreOnClass(String SchoolID, String Score,
			String selectedCurseId, String oldSchoolID, String SelectedClassId) {
		// TODO Auto-generated method stub
		//查询新添加的学号是否存在
				String sql=" select sus.school_id from " +
						"(select * from SYS_DEPARTMENT_MEMBER )sdm " +
						"inner join SYS_USER_STUDENT sus  " +
						"on sdm.user_id= sus.user_id and sdm.department_id='"+SelectedClassId+"'";
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				List<Map>	list	=	dao.executeQuery(sql);
				String result="";
				if(list.size()!=0){
					Map map=new HashMap();
					int i;
					for( i=0;i<list.size();i++){
						map=list.get(i);
						String Id=(String) map.get("school_id");
						if(Id.equals(SchoolID))
							break;
					}
					if(i>=list.size()){
						logger.info("修改学生成绩过程中该学号不存在！");
						result = "{\"result\":\"failedId\",\"text\":\"该学号号在系统中不存在，请重新输入\"}";
						try {
							PrintWriter pw = response.getWriter();
							pw.print(result);
							pw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else{
						sql="update SYS_STUDENT_SCORE set student_id='"+SchoolID+"', score_total='"+Score+"' " +
								" where curse_id='"+selectedCurseId+"' and student_id='"+oldSchoolID+"' ";
						if(1==dao.execute(sql)){
							result="{\"result\":\"succ\",\"text\":\"修改学生成绩成功\"}";
						}
						else{
							result="{\"result\":\"failure\",\"text\":\"修改学生成绩失败\"}";
						}
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

}
