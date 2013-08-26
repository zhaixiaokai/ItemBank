/**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TeachingClassMemberServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：wuzexi
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
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.Service;
import net.ib.util.service.TeachingClassMemberService;

/**
 * <p>
 * 类名：net.ib.util.service.impl.TeachingClassMemberServiceImpl
 * </p>
 * <p>
 * 描述：TODO(用一句话描述该文件做什么)
 * </p>
 * <p>
 * </p>
 */
public class TeachingClassMemberServiceImpl implements TeachingClassMemberService {
	private static Logger logger = Logger
			.getLogger(TeachingClassMemberServiceImpl.class);
	private Dao dao;
	private	Service	service;
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


	/* (非-Javadoc)
	 * <p>名称: TeachingClassMemberAdd</p>
	 * <p>说明: </p>
	 * @param addUserId
	 * @param teachingClassId
	 * @param curseId
	 * @return
	 * @see net.ib.util.service.CurseService#addCurse(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String TeachingClassMemberAdd(String addUserId,String teachingClassId,String curseId)
	{
		String result = "";
		//通过curse_class_id查询是否存在与当前所要添加的项有重复的项
		String	Sql	=	"select AllClass.*,scc.curse_id ExistedCurseId from "
				+ "(select rownum rn,cu.* from sys_curse_class_member cu where user_id='"
				+ addUserId
				+ "'"
				+ ") AllClass "
				+ "inner join sys_curse_class scc on AllClass.curse_class_id=scc.curse_class_id ";
		List<Map>	list	=	dao.executeQuery(Sql);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		logger.debug(addUserId);
		logger.debug(teachingClassId);
		logger.debug(curseId);
		//如果此学生之前已经被添加进某些开课班级
		if(list.size()!=0){
			String	TempId="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempId	=	(String) map.get("existedcurseid");
				logger.debug(TempId);
				if(TempId.equals(curseId)){
					logger.info("添加开课班级成员过程中课程的Id有重复");
					result = "{\"result\":\"failedId\",\"text\":\"此学生已经选过该课程，请重新选择\"}";
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
			//添加开课班级成员过程中课程的Id没有重复
			//产生用户唯一标识符
			 UUID uuid = UUID.randomUUID(); 
			 String Id=uuid.toString();
			//将该学生信息加到sys_curse_class_member中
			 String sql_first="insert into sys_curse_class_member fields(id,curse_class_id,user_id) VALUES('"+Id+"','"+teachingClassId+"','"+addUserId+"')";
			
			if(1==dao.execute(sql_first)){
				result = "{\"result\":\"succ\",\"text\":\"添加学生信息到开课班级成功\"}";
	
				try {
					PrintWriter pw = response.getWriter();
					pw.print(result);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		//如果此学生之前尚未被添加进任何开课班级
		else{
			//产生用户唯一标识符
			 UUID uuid = UUID.randomUUID(); 
			 String Id=uuid.toString();
			//将该学生信息加到sys_curse_class_member中
			 String sql_first="insert into sys_curse_class_member fields(id,curse_class_id,user_id) VALUES('"+Id+"','"+teachingClassId+"','"+addUserId+"')";
			
			if(1==dao.execute(sql_first)){
				result = "{\"result\":\"succ\",\"text\":\"添加学生信息到开课班级成功\"}";
	
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

	
	 /* (非-Javadoc)
	 * <p>名称: TeachingClassDelete</p>
	 * <p>说明: 根据ID删除教学班级</p>
	 * @param DelId
	 * @return
	 * @see net.ib.util.service.TeachingClassService#TeachingClassDelete(java.lang.String)
	 */
	@Override
	public String TeachingClassMemberDelete(String DelId) {
		// TODO Auto-generated method stub
		logger.debug(DelId);
		String	sql	=	"delete from sys_curse_class_member where user_id='"+DelId+"'";
		logger.debug(sql);
		String	result="";
		if(1==dao.execute(sql)){
			result	=	"{\"result\":\"删除开课班级成员成功\"}";
		}
		else{
			result	=	"{\"result\":\"删除开课班级成员失败\"}";
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
	/*@Override
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
			result = "{\"result\":\"succ\",\"text\":\"修改教学班级成功\"}";
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
	}*/

	
	 /* (非-Javadoc)
	 * <p>名称: TeachingClassMemberBulkDelete</p>
	 * <p>说明: 批量删除开课班级成员</p>
	 * @param DelIds 删除的Id 多个Id用空格分开
	 * @return
	 * @see net.ib.util.service.TeachingClassMemberService#TeachingClassMemberBulkDelete(java.lang.String)
	 */
	@Override
	public String TeachingClassMemberBulkDelete(String DelIds) {
		// TODO Auto-generated method stub
		logger.debug(DelIds);
		//收到的DelIds格式为   id1 id2 id3 id4 ....
		String[] array=DelIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		String	Sql	=	"delete from sys_curse_class_member where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" user_id='"+array[i]+"'";
		}
		logger.debug(Sql);
		if(1==dao.execute(Sql)){
			result="{\"result\":\"success\",\"text\":\"批量删除成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"批量删除失败\"}";
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
	
	
	 /* (非-Javadoc)
		 * <p>名称: TeachingClassMemberBulkAdd</p>
		 * <p>说明: 批量添加开课班级成员</p>
		 * @param bulkAddIDs 添加的成员的Id 多个Id用空格分开
		 * @return
		 * @see net.ib.util.service.TeachingClassMemberService#TeachingClassMemberBulkAdd(java.lang.String)
		 */
		@Override
		public String TeachingClassMemberBulkAdd(String bulkAddIDs,String teachingClassId,String curseId) {
			// TODO Auto-generated method stub
			logger.debug(bulkAddIDs);
			//收到的DelIds格式为   id1 id2 id3 id4 ....
			String[] array=bulkAddIDs.split(" ");
			logger.debug(array);
			logger.debug(array.length);
			
			String result = "";
			//通过curse_class_id查询是否存在与当前所要添加的项有重复的项
			String	Sql	=	"select AllClass.*,scc.curse_id ExistedCurseId,sus.name ExistedName from "
					+ "(select rownum rn,cu.* from sys_curse_class_member cu where";
			for(int	i=0;i<array.length;i++){
				if(i!=0){
					Sql+=" or";
				}
				Sql+=" user_id='"+array[i]+"'";
			}
			String Sql_second = ") AllClass "
					          + "inner join sys_curse_class scc on AllClass.curse_class_id=scc.curse_class_id "
			                  + "inner join sys_user_student sus on AllClass.user_id=sus.user_id ";
			Sql = Sql+Sql_second;
			logger.debug(Sql);
			
			List<Map>	list	=	dao.executeQuery(Sql);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			logger.debug(teachingClassId);
			logger.debug(curseId);
			//如果有学生之前已经被添加进某些开课班级
			if(list.size()!=0)
			{
				String	TempId="";
				String	TempName="";
				Map map = new HashMap();
				for(int	i=0;i<list.size();i++)
				{
					map=list.get(i);
					TempId	=	(String) map.get("existedcurseid");
					TempName	=	(String) map.get("existedname");
					logger.debug(TempId);
					logger.debug(TempName);
					if(TempId.equals(curseId))
					{
						logger.info("添加开课班级成员过程中课程的Id有重复");
						result = "{\"result\":\"failedId\",\"text\":\"学生 "+TempName+" 已经选过该课程，请重新选择学生\"}";
						try 
						{
							PrintWriter pw = response.getWriter();
							pw.print(result);
							pw.close();
						} catch (IOException e) 
						{
							e.printStackTrace();
						}
						return null;
					}
				}
				//添加开课班级成员过程中所有用户已选课程的Id都没有和当前重复
				//将该学生信息加到sys_curse_class_member中
				 String Sql_third="insert into sys_curse_class_member(curse_class_id,user_id)";
				 for(int i=0;i<array.length;i++){
						if(i!=0){
							Sql_third+=" union all";
						}
						Sql_third+=" select '"+teachingClassId+"','"+array[i]+"' from Dual";
				 }
				
				 logger.debug(Sql_third);
				 if(1==dao.execute(Sql_third))
				 {
					result = "{\"result\":\"succ\",\"text\":\"批量添加学生信息到开课班级成功\"}";
		
					try 
					{
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) 
					{
						e.printStackTrace();
					}
				 }
				 return null;
			}
			//如果要添加的学生之前尚未被添加进任何开课班级
			else
			{
				//将该学生信息加到sys_curse_class_member中
				 String Sql_third="insert into sys_curse_class_member(curse_class_id,user_id)";
				 for(int i=0;i<array.length;i++){
						if(i!=0){
							Sql_third+=" union all";
						}
						Sql_third+=" select '"+teachingClassId+"','"+array[i]+"' from Dual";
				 }
				
				 logger.debug(Sql_third);
				 if(1==dao.execute(Sql_third))
				 {
					result = "{\"result\":\"succ\",\"text\":\"批量添加学生信息到开课班级成功\"}";
		
					try 
					{
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) 
					{
						e.printStackTrace();
					}
				 }
				 return null;
			}	
		}
}
