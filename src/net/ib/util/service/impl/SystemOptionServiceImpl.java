
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	SystemOptionServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-6 下午2:24:53
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 下午2:24:53
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.Service;
import net.ib.util.service.SystemOptionService;


  /**
 * <p>类名：net.ib.util.service.impl.SystemOptionServiceImpl </p>
 * <p>描述：从后台获取系统信息，包括系统配置信息</p>
 * <p></p>
 */
public class SystemOptionServiceImpl implements	SystemOptionService{
	private static Logger logger = Logger.getLogger(SystemOptionServiceImpl.class);
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		SystemOptionServiceImpl.logger = logger;
	}

	private	Dao	dao;
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
	 * <p>名称: GetDifficultyOptions</p>
	 * <p>说明: 获取难度选项</p>
	 * @return
	 * @see net.ib.util.service.SystemOptionService#GetDifficultyOptions()
	 */
	@Override
	public String GetDifficultyOptions() {
		// TODO Auto-generated method stub
		String	GetDifficultyOptionSql="select	* from SYS_DICTIONARY_ENTRIES_VALUE where DICTIONARY_ENTRIES_ID='difficulty' order by sno asc";
		List<Map>	list	=	dao.executeQuery(GetDifficultyOptionSql);
		String Json	=	service.DataListToJson(list);
		logger.debug(Json);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(Json);	
			logger.debug("$$$$$$$$$$$$$$$$$$$$"+Json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 /* (非-Javadoc)
	 * <p>名称: GetPaperUseOptions</p>
	 * <p>说明: 获取试卷用途的方法</p>
	 * @return
	 * @see net.ib.util.service.SystemOptionService#GetPaperUseSelect()
	 */
	@Override
	public String GetPaperUseOptions() {
		// TODO Auto-generated method stub
		String	GetDifficultyOptionSql="select	* from SYS_DICTIONARY_ENTRIES_VALUE where DICTIONARY_ENTRIES_ID='paperuse' order by sno asc";
		List<Map>	list	=	dao.executeQuery(GetDifficultyOptionSql);
		String Json	=	service.DataListToJson(list);
		logger.debug(Json);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(Json);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 /* (非-Javadoc)
	 * <p>名称: GetTeacherDepartmentOptions</p>
	 * <p>说明: </p>
	 * @return
	 * @see net.ib.util.service.SystemOptionService#GetTeacherDepartmentOptions()
	 */
	@Override
	public String GetTeacherDepartmentOptions() {
		// TODO Auto-generated method stub
		String	sql	=	"select * from sys_department_tree where tree_id='departmentTree' order by node_path asc";
		List<Map> list	=	dao.executeQuery(sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(StrList);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	 /* (非-Javadoc)
	 * <p>名称: GetStudentDepartmentOptions</p>
	 * <p>说明: </p>
	 * @return
	 * @see net.ib.util.service.SystemOptionService#GetStudentDepartmentOptions()
	 */
	@Override
	public String GetStudentDepartmentOptions() {
		// TODO Auto-generated method stub
		String	sql	=	"select * from sys_department_tree where tree_id='StudentDepTree' order by node_path asc";
		List<Map> list	=	dao.executeQuery(sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(StrList);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	 /* (非-Javadoc)
	 * <p>名称: GetRoleList</p>
	 * <p>说明: </p>
	 * @return
	 * @see net.ib.util.service.SystemOptionService#GetRoleList()
	 */
	@Override
	public String GetRoleList() {
		// TODO Auto-generated method stub
		String	Sql	=	"select * from SYS_ROLE order by role_id";
		List<Map>	list	=	dao.executeQuery(Sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(StrList);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 /* (非-Javadoc)
	 * <p>名称: GetSchoolStructureOptions</p>
	 * <p>说明: </p>
	 * @return
	 * @see net.ib.util.service.SystemOptionService#GetSchoolStructureOptions()
	 */
	@Override
	public String GetSchoolStructureOptions() {
		// TODO Auto-generated method stub
		String	sql	=	"select * from sys_ib_classification_tree where tree_id='jibenfenleitixi' order by node_path asc";
		List<Map> list	=	dao.executeQuery(sql);
		String	Json	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(Json);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	 /* (非-Javadoc)
	 * <p>名称: GetTeacherListByDepartmentIdOptions</p>
	 * <p>说明: </p>
	 * @param DepId
	 * @return
	 * @see net.ib.util.service.SystemOptionService#GetTeacherListByDepartmentIdOptions(java.lang.String)
	 */
	@Override
	public String GetTeacherListByDepartmentIdOptions(String DepId) {
		// TODO Auto-generated method stub
		String	sql	=	"select school_id,name from sys_user_teacher where user_id in ("
				+"select user_id from sys_department_member where department_id='"+DepId+"'"
				+") order by school_id asc";
		logger.debug(sql);
		List<Map> list	=	dao.executeQuery(sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(StrList);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		return null;
	}
	
	 /* (非-Javadoc)
	 * <p>名称: GetTeachingMaterialByCurseIdOptions</p>
	 * <p>说明: 通过课程Id选择该课程下所有教材的信息</p>
	 * @param CurseId
	 * @return
	 * @see net.ib.util.service.SystemOptionService#GetTeachingMaterialByCurseId(java.lang.String)
	 */
	@Override
	public String GetTeachingMaterialByCurseIdOptions(String CurseId) {
		// TODO Auto-generated method stub
		String	sql	=	"select teaching_material_id,name from" +
				" sys_teaching_material where curse_id='"+CurseId+"'";
		logger.debug(sql);
		
		List<Map>	list	=	dao.executeQuery(sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(StrList);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 /* (非-Javadoc)
	 * <p>名称: GetDataDicOptions</p>
	 * <p>说明: 获取字典项Option的服务程序</p>
	 * @return
	 * @see net.ib.util.service.SystemOptionService#GetDataDicOptions()
	 */
	@Override
	public String GetDataDicOptions() {
		// TODO Auto-generated method stub
		String	Sql	=	"select * from SYS_DICTIONARY_ENTRIES order by id";
		List<Map>	list	=	dao.executeQuery(Sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(StrList);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	 /* (非-Javadoc)
	 * <p>名称: GetQuesUseSelect</p>
	 * <p>说明: 获取试题用途的服务程序</p>
	 * @return
	 * @see net.ib.util.service.SystemOptionService#GetQuesUseSelect()
	 */
	@Override
	public String GetQuesUseSelect() {
		// TODO Auto-generated method stub
		String	GetDifficultyOptionSql="select	* from SYS_DICTIONARY_ENTRIES_VALUE where DICTIONARY_ENTRIES_ID='tixing' order by sno asc";
		List<Map>	list	=	dao.executeQuery(GetDifficultyOptionSql);
		String Json	=	service.DataListToJson(list);
		logger.debug(Json);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(Json);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/* (非-Javadoc)
	 * <p>名称: GetTeachingClassByCurseIdOptions</p>
	 * <p>说明: 通过课程Id选择该课程下所有开课班级的信息</p>
	 * @param CurseId
	 * @return
	 * @see net.ib.util.service.SystemOptionService#GetTeachingClassByCurseId(java.lang.String)
	 */
	@Override
	public String GetTeachingClassByCurseIdOptions(String CurseId) {
		// TODO Auto-generated method stub
		String	sql	=	"select curse_class_id,class_name from" +
				" sys_curse_class where curse_id='"+CurseId+"'";
		logger.debug(sql);
		
		List<Map>	list	=	dao.executeQuery(sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(StrList);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


}
