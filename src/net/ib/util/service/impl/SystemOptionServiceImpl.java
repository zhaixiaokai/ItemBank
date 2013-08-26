
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	SystemOptionServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-6 ����2:24:53
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-6 ����2:24:53
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
 * <p>������net.ib.util.service.impl.SystemOptionServiceImpl </p>
 * <p>�������Ӻ�̨��ȡϵͳ��Ϣ������ϵͳ������Ϣ</p>
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
	 /* (��-Javadoc)
	 * <p>����: GetDifficultyOptions</p>
	 * <p>˵��: ��ȡ�Ѷ�ѡ��</p>
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
	
	 /* (��-Javadoc)
	 * <p>����: GetPaperUseOptions</p>
	 * <p>˵��: ��ȡ�Ծ���;�ķ���</p>
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
	
	 /* (��-Javadoc)
	 * <p>����: GetTeacherDepartmentOptions</p>
	 * <p>˵��: </p>
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
	
	
	 /* (��-Javadoc)
	 * <p>����: GetStudentDepartmentOptions</p>
	 * <p>˵��: </p>
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
	 /* (��-Javadoc)
	 * <p>����: GetRoleList</p>
	 * <p>˵��: </p>
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
	
	 /* (��-Javadoc)
	 * <p>����: GetSchoolStructureOptions</p>
	 * <p>˵��: </p>
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
	
	 /* (��-Javadoc)
	 * <p>����: GetTeacherListByDepartmentIdOptions</p>
	 * <p>˵��: </p>
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
	
	 /* (��-Javadoc)
	 * <p>����: GetTeachingMaterialByCurseIdOptions</p>
	 * <p>˵��: ͨ���γ�Idѡ��ÿγ������н̲ĵ���Ϣ</p>
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
	
	 /* (��-Javadoc)
	 * <p>����: GetDataDicOptions</p>
	 * <p>˵��: ��ȡ�ֵ���Option�ķ������</p>
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
	 /* (��-Javadoc)
	 * <p>����: GetQuesUseSelect</p>
	 * <p>˵��: ��ȡ������;�ķ������</p>
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
	
	/* (��-Javadoc)
	 * <p>����: GetTeachingClassByCurseIdOptions</p>
	 * <p>˵��: ͨ���γ�Idѡ��ÿγ������п��ΰ༶����Ϣ</p>
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
