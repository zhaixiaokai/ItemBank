
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ExamPaperDBServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-12 下午7:27:23
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-12 下午7:27:23
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.* ;
import net.sf.json.JSONArray;

  /**
 * <p>类名：net.ib.util.service.impl.ExamPaperDBServiceImpl </p>
 * <p>描述：试卷库服务程序实现</p>
 * <p></p>
 */

public class ExamPaperDBServiceImpl implements ExamPaperDBService{
	private static Logger logger = Logger.getLogger(ExamPaperDBServiceImpl.class);
	Service service;
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	private Dao dao;
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	/**
	 * 添加试卷库信息入库
	 */
	public String PaperAdd(String name, String discription,
			String identifier, String leafid, String config_leafid, String use) {
		String result = "";
		/*logger.debug("+++++++++++++++++++++++++++++++++++++++++++++==");
		logger.debug(name);
		logger.debug(identifier);
		logger.debug(leafid);
		logger.debug(config_leafid);*/
		
		// 判断分类体系名称是否重复
		List<Map> list = (List<Map>) dao
				.executeQuery("select * from sys_epdb_list where epdb_name='"
						+ name + "' or epdb_id='" + identifier + "'");
		for (int i = 0; i < list.size(); i++) {
			String epdb_name = (String) list.get(i).get("epdb_name");
			String epdb_id = (String) list.get(i).get("epdb_id");

			// 判断分类体系名称是否重复
			if (epdb_name.equals(name)) {
				result = "名称重复，请重新输入";
				return result;
			}
			// 判断分类体系标识符时候重复
			if (epdb_id.equals(identifier)) {
				result = "标识符重复，请重新输入";
				return result;
			}

		}
		dao.execute("insert into sys_epdb_list (epdb_id,epdb_name,usage,explain,curse_id,create_time) values('"
				+ identifier
				+ "','"
				+ name
				+ "','"
				+ use
				+ "','"
				+ discription
				+ "','" + leafid + "',sysdate)");
		dao.execute("insert into SYS_EPDB_CLASSIFICATION_NODE (id,node_id,epdb_id) values(sys_guid(),'"
				+ config_leafid + "','" + identifier + "')");
		result = "添加成功";
		return result;
	}
	
	/**
	 * 试卷库按角色授权获取某角色成员的试卷库权限	
	 */
		public String GetAuthorizedEPDBByrole(String roleid,String memberid){
			List<Map> authorized_epdb	=	dao.executeQuery("select role_id,epdb_id,membertype_id from sys_perm_epdb_role where role_id='"+roleid+"' and membertype_id='"+memberid+"'");
			JSONArray authorized_epdblist = JSONArray.fromObject(authorized_epdb);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try {
				PrintWriter pw = response.getWriter();
				pw.print(authorized_epdblist);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}
	
		/**
		 * 试卷库按角色授权获取试卷库数，未选课程
		 */
		public String GetEPDBBymajor(String majorid){
			List<Map> subjectlist	=	dao.executeQuery("select * from sys_curse where special_field_id='"+majorid+"'");
			String curse_sql="";
			for (int j = 0; j < subjectlist.size(); j++) {
				//课程sql拼接
				String subjectid=(String) subjectlist.get(j).get("curse_id");
					if(curse_sql!=""){
						curse_sql=curse_sql+" or ";
					}
					curse_sql+="curse_id="+"'"+subjectid+"'";
				}
			//查找试卷库
			String getDataSql = "select rownum rn,cu.epdb_id,cu.epdb_name,cu.curse_id from  "
					+"sys_epdb_list cu where "+curse_sql+"";
			List<Map> epdblist	=	dao.executeQuery(getDataSql);
			JSONArray epdblistdata = JSONArray.fromObject(epdblist);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try {
				PrintWriter pw = response.getWriter();
				pw.print(epdblistdata);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * 试卷库按角色授权获取试卷库数据，已选课程
		 */
		public String GetEPDBBycurse(String curseid){
			//查找试卷库
			String getDataSql = "select cu.epdb_id,cu.epdb_name,cu.curse_id from  "
					+"sys_epdb_list cu where curse_id='"+curseid+"'";
			List<Map> epdblist	=	dao.executeQuery(getDataSql);
			JSONArray epdblistdata = JSONArray.fromObject(epdblist);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try {
				PrintWriter pw = response.getWriter();
				pw.print(epdblistdata);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * 试卷库按角色授权，保存配置
		 */
		public String SaveEPDBAuthorityConfigByroleSelectcourse(String authorized_epdb,String role,String member,String courseid,String majorid){
			String result=null;
			//查找配置中在该课程下的试题库
			ArrayList epdblist=new ArrayList();
			List<Map> AllList	=	dao.executeQuery("select * from sys_perm_epdb_role");
			for(int j=0;j<AllList.size();j++){
				String epdbid=(String) AllList.get(j).get("epdb_id");
				List<Map> list	=	dao.executeQuery("select * from sys_epdb_list where epdb_id='"+epdbid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					epdblist.add(epdbid);
				}
			}
			//删除该课程下表中原先配置
			for(int t=0;t<epdblist.size();t++){
				dao.execute("delete from sys_perm_epdb_role where role_id='"+role+"' and membertype_id='"+member+"' and epdb_id='"+epdblist.get(t)+"'");
			}
	//添加配置数据
	//和判断是否授权的试题库只有一个
	//若不是
	if(authorized_epdb.indexOf(",")!=-1){
		String[] eslist =authorized_epdb.split(",");
		//添加配置数据
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				dao.execute("insert into sys_perm_epdb_role (id,epdb_id,role_id,membertype_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+role+"','"+member+"','"+majorid+"')");
			}
		}
	}else{
		dao.execute("insert into sys_perm_epdb_role (id,epdb_id,role_id,membertype_id,major_id) values(sys_guid(),'"+authorized_epdb+"','"+role+"','"+member+"','"+majorid+"')");

	}
			result="配置成功";
			return result;
		}
		/*
		 *
		 * <p>名称: SaveEPDBAuthorityConfigByroleSelectnonecourse</p>
		 * <p>说明: 试题库按角色授权，保存配置，未选课程</p>
		 * @param authorized_epdb
		 * @param role
		 * @param member
		 * @return
		 * @see net.ib.util.service.ExamPaperDBService#SaveEPDBAuthorityConfigByroleSelectnonecourse(java.lang.String, java.lang.String, java.lang.String)
		 */
		
		public String SaveEPDBAuthorityConfigByroleSelectnonecourse(String authorized_epdb, String role, String member,String majorid){
			String result=null;
			//和判断是否授权的试题库只有一个
			//若不是
			if(authorized_epdb.indexOf(",")!=-1){
				String[] eslist=authorized_epdb.split(",");
				//删除表中原先配置
				dao.execute("delete from sys_perm_epdb_role where role_id='"+role+"' and membertype_id='"+member+"' and major_id='"+majorid+"'");
				//添加配置数据
				//判断是否有选择试题库,为空时长度为1
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_role (id,epdb_id,role_id,membertype_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+role+"','"+member+"','"+majorid+"')");
					}
				}
				
			}else{
				dao.execute("delete from sys_perm_epdb_role where role_id='"+role+"' and membertype_id='"+member+"' and major_id='"+majorid+"'");
				dao.execute("insert into sys_perm_epdb_role (id,epdb_id,role_id,membertype_id,major_id) values(sys_guid(),'"+authorized_epdb+"','"+role+"','"+member+"','"+majorid+"')");
			}
			result="配置成功";
			return result;
		}	
		/**
		 * <p>名称：SaveEPDBAuthorityConfigByuserSelectcourse</p>
		 * <p>说明：TODO 试卷库按用户授权获取试卷库数据，已选课程)
		 * <p>参数：@param authorized_epdb
		 * <p>参数：@param number
		 * <p>参数：@param member
		 * <p>参数：@param courseid
		 * <p>参数：@return 设定文件</p>
		 * <p>返回值：String 返回类型</p>
		 * <p>@param authorized_epdb
		 * <p>@param number
		 * <p>@param member
		 * <p>@param courseid
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigByuserSelectcourse(String authorized_epdb,String number,String member,String courseid,String majorid){
			String result=null;
			//查找配置中在该课程下的试题库
			ArrayList epdblist=new ArrayList();
			List<Map> AllList	=	dao.executeQuery("select * from sys_perm_epdb_user");
			for(int j=0;j<AllList.size();j++){
				String epdbid=(String) AllList.get(j).get("epdb_id");
				List<Map> list	=	dao.executeQuery("select * from sys_epdb_list where epdb_id='"+epdbid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					epdblist.add(epdbid);
				}
			}
			//删除该课程下表中原先配置
			for(int t=0;t<epdblist.size();t++){
				dao.execute("delete from sys_perm_epdb_user where user_id='"+number+"' and membertype='"+member+"' and epdb_id='"+epdblist.get(t)+"'");
			}
	//添加配置数据
	//和判断是否授权的试题库只有一个
	//若不是
	if(authorized_epdb.indexOf(",")!=-1){
		String[] eslist =authorized_epdb.split(",");
		//添加配置数据
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				dao.execute("insert into sys_perm_epdb_user (id,epdb_id,user_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"','"+majorid+"')");
			}
		}
	}else{
		dao.execute("insert into sys_perm_epdb_user(id,epdb_id,user_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+number+"','"+member+"','"+majorid+"')");

	}
			result="配置成功";
			return result;
		}
		
		/**
		 * 
		 * <p>名称：SaveEPDBAuthorityConfigBydepartmentSelectcourse</p>
		 * <p>说明：TODO(试卷库按机构授权获取试卷库数据，已选课程)</p>
		 * <p>参数：@param authorized_epdb
		 * <p>参数：@param department
		 * <p>参数：@param member
		 * <p>参数：@param courseid
		 * <p>参数：@return 设定文件</p>
		 * <p>返回值：String 返回类型</p>
		 * <p>@param authorized_epdb
		 * <p>@param department
		 * <p>@param member
		 * <p>@param courseid
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigBydepartmentSelectcourse(String authorized_epdb,String department,String member,String courseid,String majorid){
			String result=null;
			//查找配置中在该课程下的试题库
			ArrayList epdblist=new ArrayList();
			List<Map> AllList	=	dao.executeQuery("select * from sys_perm_epdb_department");
			for(int j=0;j<AllList.size();j++){
				String epdbid=(String) AllList.get(j).get("epdb_id");
				List<Map> list	=	dao.executeQuery("select * from sys_epdb_list where epdb_id='"+epdbid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					epdblist.add(epdbid);
				}
			}
			//删除该课程下表中原先配置
			for(int t=0;t<epdblist.size();t++){
				dao.execute("delete from sys_perm_epdb_department where department_id='"+department+"' and membertype='"+member+"' and epdb_id='"+epdblist.get(t)+"'");
			}
	//添加配置数据
	//和判断是否授权的试题库只有一个
	//若不是
	if(authorized_epdb.indexOf(",")!=-1){
		String[] eslist =authorized_epdb.split(",");
		//添加配置数据
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				dao.execute("insert into sys_perm_epdb_department (id,epdb_id,department_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+department+"','"+member+"','"+majorid+"')");
			}
		}
	}else{
		dao.execute("insert into sys_perm_epdb_department(id,epdb_id,department_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+department+"','"+member+"','"+majorid+"')");

	}
			result="配置成功";
			return result;
}
		
		/**
		 * 
		 * <p>名称：SaveEPDBAuthorityConfigBymembertypeSelectcourse</p>
		 * <p>说明：TODO(试卷库按成员类型授权获取试卷库数据，已选课程)</p>
		 * <p>参数：@param authorized_epdb
		 * <p>参数：@param member
		 * <p>参数：@param courseid
		 * <p>参数：@return 设定文件</p>
		 * <p>返回值：String 返回类型</p>
		 * <p>@param authorized_epdb
		 * <p>@param member
		 * <p>@param courseid
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigBymembertypeSelectcourse(String authorized_epdb,String member,String courseid,String majorid){
			String result=null;
			//查找配置中在该课程下的试题库
			ArrayList epdblist=new ArrayList();
			List<Map> AllList	=	dao.executeQuery("select * from sys_perm_epdb_membertype");
			for(int j=0;j<AllList.size();j++){
				String epdbid=(String) AllList.get(j).get("epdb_id");
				List<Map> list	=	dao.executeQuery("select * from sys_epdb_list where epdb_id='"+epdbid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					epdblist.add(epdbid);
				}
			}
			//删除该课程下表中原先配置
			for(int t=0;t<epdblist.size();t++){
				dao.execute("delete from sys_perm_epdb_membertype where membertype='"+member+"' and epdb_id='"+epdblist.get(t)+"'");
			}
	//添加配置数据
	//和判断是否授权的试题库只有一个
	//若不是
	if(authorized_epdb.indexOf(",")!=-1){
		String[] eslist =authorized_epdb.split(",");
		//添加配置数据
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				dao.execute("insert into sys_perm_epdb_membertype (id,epdb_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+member+"','"+majorid+"')");
			}
		}
	}else{
		dao.execute("insert into sys_perm_epdb_membertype(id,epdb_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+member+"','"+majorid+"')");

	}
			result="配置成功";
			return result;
		}
		/**
		 * 
		 * <p>名称：SaveEPDBAuthorityConfigByteachingclassSelectcourse</p>
		 * <p>说明：TODO(试卷库按班级授权获取试卷库数据，已选课程)</p>
		 * <p>参数：@param authorized_epdb
		 * <p>参数：@param teachingclass
		 * <p>参数：@param courseid
		 * <p>参数：@return 设定文件</p>
		 * <p>返回值：String 返回类型</p>
		 * <p>@param authorized_epdb
		 * <p>@param teachingclass
		 * <p>@param courseid
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigByteachingclassSelectcourse(String authorized_epdb,String teachingclass,String courseid,String majorid){
			String result=null;
			//查找配置中在该课程下的试题库
			ArrayList epdblist=new ArrayList();
			List<Map> AllList	=	dao.executeQuery("select * from sys_perm_epdb_class");
			for(int j=0;j<AllList.size();j++){
				String epdbid=(String) AllList.get(j).get("epdb_id");
				List<Map> list	=	dao.executeQuery("select * from sys_epdb_list where epdb_id='"+epdbid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					epdblist.add(epdbid);
				}
			}
			//删除该课程下表中原先配置
			for(int t=0;t<epdblist.size();t++){
				dao.execute("delete from sys_perm_epdb_class where class_id='"+teachingclass+"' and epdb_id='"+epdblist.get(t)+"'");
			}
	//添加配置数据
	//和判断是否授权的试题库只有一个
	//若不是
	if(authorized_epdb.indexOf(",")!=-1){
		String[] eslist =authorized_epdb.split(",");
		//添加配置数据
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				dao.execute("insert into sys_perm_epdb_class (id,epdb_id,class_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+teachingclass+"','"+majorid+"')");
			}
		}
	}else{
		dao.execute("insert into sys_perm_epdb_class(id,epdb_id,class_id,major_id) values(sys_guid(),'"+authorized_epdb+"','"+teachingclass+"','"+majorid+"')");

	}
			result="配置成功";
			return result;
		}
		
		/*
		 *
		 * <p>名称: SaveEPDBAuthorityConfigByuserSelectnonecourse</p>
		 * <p>说明: 试题库按用户授权，保存配置，未选课程</p>
		 * @param authorized_epdb
		 * @param role
		 * @param member
		 * @return
		 * @see net.ib.util.service.ExamPaperDBService#SaveEPDBAuthorityConfigByuserSelectnonecourse(java.lang.String, java.lang.String, java.lang.String)
		 */
		
		public String SaveEPDBAuthorityConfigByuserSelectnonecourse(String authorized_epdb,String number, String member,String majorid){
			String result=null;
			//和判断是否授权的试题库只有一个
			//若不是
			if(authorized_epdb.indexOf(",")!=-1){
				String[] eslist=authorized_epdb.split(",");
				//删除表中原先配置
				dao.execute("delete from sys_perm_epdb_user where user_id='"+number+"' and membertype='"+member+"' and major_id='"+majorid+"'");
				//添加配置数据
				//判断是否有选择试题库,为空时长度为1
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_user (id,epdb_id,user_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"','"+majorid+"')");
					}
				}
				
			}else{
				dao.execute("delete from sys_perm_epdb_user where user_id='"+number+"' and membertype='"+member+"' and major_id='"+majorid+"'");
				dao.execute("insert into sys_perm_epdb_user (id,epdb_id,user_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+number+"','"+member+"','"+majorid+"')");
			}
			result="配置成功";
			return result;
		}	
		/**
		 * 
		 * <p>名称：SaveEPDBAuthorityConfigBydepartmentSelectnonecourse</p>
		 * <p>说明：TODO(试题库按j机构授权，保存配置，已选课程)</p>
		 * <p>参数：@param authorized_epdb
		 * <p>参数：@param department
		 * <p>参数：@param member
		 * <p>参数：@return 设定文件</p>
		 * <p>返回值：String 返回类型</p>
		 * <p>@param authorized_epdb
		 * <p>@param department
		 * <p>@param member
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigBydepartmentSelectnonecourse(String authorized_epdb,String department, String member,String majorid){
			String result=null;
			//和判断是否授权的试题库只有一个
			//若不是
			if(authorized_epdb.indexOf(",")!=-1){
				String[] eslist=authorized_epdb.split(",");
				//删除表中原先配置
				dao.execute("delete from sys_perm_epdb_department where department_id='"+department+"' and membertype='"+member+"' and major_id='"+majorid+"'");
				//添加配置数据
				//判断是否有选择试题库,为空时长度为1
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_department (id,epdb_id,department_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+department+"','"+member+"','"+majorid+"')");
					}
				}
				
			}else{
				dao.execute("delete from sys_perm_epdb_department where department_id='"+department+"' and membertype='"+member+"' and major_id='"+majorid+"'");
				dao.execute("insert into sys_perm_epdb_department (id,epdb_id,department_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+department+"','"+member+"','"+majorid+"')");
			}
			result="配置成功";
			return result;
		}	
		
		/**
		 * 
		 * <p>名称：SaveEPDBAuthorityConfigBymembertypeSelectnonecourse</p>
		 * <p>说明：TODO(试题库按成员类型授权，保存配置，未选课程)</p>
		 * <p>参数：@param authorized_epdb
		 * <p>参数：@param member
		 * <p>参数：@return 设定文件</p>
		 * <p>返回值：String 返回类型</p>
		 * <p>@param authorized_epdb
		 * <p>@param member
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigBymembertypeSelectnonecourse(String authorized_epdb, String member,String majorid){
			String result=null;
			//和判断是否授权的试题库只有一个
			//若不是
			if(authorized_epdb.indexOf(",")!=-1){
				String[] eslist=authorized_epdb.split(",");
				//删除表中原先配置
				dao.execute("delete from sys_perm_epdb_membertype where membertype='"+member+"' and major_id='"+majorid+"'");
				//添加配置数据
				//判断是否有选择试题库,为空时长度为1
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_membertype (id,epdb_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+member+"','"+majorid+"')");
					}
				}
				
			}else{
				dao.execute("delete from sys_perm_epdb_membertype where membertype='"+member+"' and major_id='"+majorid+"'");
				dao.execute("insert into sys_perm_epdb_membertype (id,epdb_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+member+"','"+majorid+"')");
			}
			result="配置成功";
			return result;
		}	
		/**
		 * 
		 * <p>名称：SaveEPDBAuthorityConfigByteachingclassSelectnonecourse</p>
		 * <p>说明：TODO(试题库按班级授权，保存配置，未选课程)</p>
		 * <p>参数：@param authorized_epdb
		 * <p>参数：@param teachingclass
		 * <p>参数：@return 设定文件</p>
		 * <p>返回值：String 返回类型</p>
		 * <p>@param authorized_epdb
		 * <p>@param teachingclass
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigByteachingclassSelectnonecourse(String authorized_epdb, String teachingclass,String majorid){
			String result=null;
			//和判断是否授权的试题库只有一个
			//若不是
			if(authorized_epdb.indexOf(",")!=-1){
				String[] eslist=authorized_epdb.split(",");
				//删除表中原先配置
				dao.execute("delete from sys_perm_epdb_class where membertype='"+teachingclass+"' and major_id='"+majorid+"'");
				//添加配置数据
				//判断是否有选择试题库,为空时长度为1
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_class (id,epdb_id,class_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+teachingclass+"','"+majorid+"')");
					}
				}
				
			}else{
				dao.execute("delete from sys_perm_epdb_class where class_id='"+teachingclass+"' and major_id='"+majorid+"'");
				dao.execute("insert into sys_perm_epdb_class (id,epdb_id,class_id,major_id) values(sys_guid(),'"+authorized_epdb+"','"+teachingclass+"','"+majorid+"')");
			}
			result="配置成功";
			return result;
		}	
		
		/**
		 * 试卷库按用户授权获取某用户的试卷库权限	
		 */
			public String GetAuthorizedEPDBByuser(String number,String member){
				List<Map> authorized_epdb	=	dao.executeQuery("select user_id,epdb_id,membertype from sys_perm_epdb_user where user_id='"+number+"' and membertype='"+member+"'");
				JSONArray authorized_epdblist = JSONArray.fromObject(authorized_epdb);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				try {
					PrintWriter pw = response.getWriter();
					pw.print(authorized_epdblist);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			}
			/**
			 * 试卷库按用户授权，保存配置
			 */
			public String SaveEPDBAuthorityConfigByuser(String authorized_epdb,String number,String member){
				String result=null;
				String[] eslist=authorized_epdb.split(",");
				//删除表中原先配置
				dao.execute("delete from sys_perm_epdb_user where user_id='"+number+"' and membertype='"+member+"'");
				//添加配置数据
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_user (id,epdb_id,user_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"')");
				}
				}
				result="配置成功";
				return result;
			}
			
			
			/**
			 * 试卷库按组织机构授权，查询权限
			 */
			public String GetAuthorizedEPDBBydepartment(String memberid,String departmentid,String uppernode){
				String[] uppernodelist=uppernode.split(",");
				//sql拼接
				String uppernodeSql="";
				for(int t=0;t<uppernodelist.length;t++){
					String uppernodeid=uppernodelist[t];
					if(uppernodeSql!=""){
						uppernodeSql=uppernodeSql+" or ";
					}
					uppernodeSql+="department_id="+"'"+uppernodeid+"'";
				}
				//权限信息
				List<Map> authorized_epdb	=	dao.executeQuery("select epdb_id,department_id,membertype from sys_perm_epdb_department where membertype='"+memberid+"' and "+uppernodeSql+" or department_id='"+departmentid+"'");
				JSONArray authorized_epdblist = JSONArray.fromObject(authorized_epdb);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				try {
					PrintWriter pw = response.getWriter();
					pw.print(authorized_epdblist);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			}
			
			/**
			 * 试卷库按机构授权，保存配置
			 */
			public String SaveEPDBAuthorityConfigBydepartment(String authorized_epdb, String member,String departmentid){
				String result=null;
				String[] eslist=authorized_epdb.split(",");
				//删除表中原先配置
				dao.execute("delete from sys_perm_epdb_department where department_id='"+departmentid+"' and membertype='"+member+"'");
				//添加配置数据
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						dao.execute("insert into sys_perm_epdb_department (id,epdb_id,department_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+departmentid+"','"+member+"')");
					}
				}
			
				result="配置成功";
				return result;
			}
			
			
			
			/**
			 * 试卷库按成员类型授权，查询权限
			 */
			public String GetAuthorizedEPDBBymembertype(String member){
					List<Map> authorized_epdb	=	dao.executeQuery("select epdb_id,membertype from sys_perm_epdb_membertype where membertype='"+member+"'");
					JSONArray authorized_epdblist = JSONArray.fromObject(authorized_epdb);
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setCharacterEncoding("utf-8");
					try {
						PrintWriter pw = response.getWriter();
						pw.print(authorized_epdblist);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return null;
				}
			
			
			/**
			 * 试卷库按成员类型授权，保存配置
			 */
			public String SaveEPDBAuthorityConfigBymembertype(String authorized_epdbid,String member){
				String result=null;
				String[] eslist=authorized_epdbid.split(",");
				//删除表中原先配置
				dao.execute("delete from sys_perm_epdb_membertype where membertype='"+member+"'");
				//添加配置数据
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_membertype (id,epdb_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+member+"')");
				}
				}
				result="配置成功";
				return result;
			}
			
			
			/**
			 * 试卷库按班级类型授权，查询权限
			 */
		
			public String GetAuthorizedEPDBByteachingclass(String id){
					List<Map> authorized_epdb	=	dao.executeQuery("select epdb_id,class_id from sys_perm_epdb_class where class_id='"+id+"'");
					JSONArray authorized_epdblist = JSONArray.fromObject(authorized_epdb);
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setCharacterEncoding("utf-8");
					try {
						PrintWriter pw = response.getWriter();
						pw.print(authorized_epdblist);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return null;
				}
			
			
			/*
			 * (非-Javadoc)
			 * <p>名称: SaveEPDBAuthorityConfigByteachingclass</p>
			 * <p>说明: 试卷库按班级授权，保存配置</p>
			 * @param authorized_epdbid
			 * @param teachingclass
			 * @return result
			 * @see net.ib.util.service.ExamPaperDBService#SaveEPDBAuthorityConfigByteachingclass(java.lang.String, java.lang.String)
			 */
			
			public String SaveEPDBAuthorityConfigByteachingclass(String authorized_epdbid,String teachingclass){
				String result=null;
				String[] eslist=authorized_epdbid.split(",");
				//删除表中原先配置
				dao.execute("delete from sys_perm_epdb_class where class_id='"+teachingclass+"'");
				//添加配置数据
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_class (id,epdb_id,class_id) values(sys_guid(),'"+eslist[i]+"','"+teachingclass+"')");
				}
				}
				result="配置成功";
				return result;
			}
			/**
			 * 
			 * <p>名称：EPDBDelete</p>
			 * <p>说明： 删除试卷库，单条数据</p>
			 * <p>参数：@param id
			 * <p>参数：@return 设定文件</p>
			 * <p>返回值：String 返回类型</p>
			 * <p>@param id
			 * <p>@return result</p>
			 */
			
			
			public String EPDBDelete(String id) {
				String getSql = "delete from sys_epdb_list where epdb_id='"
						+ id + "'";
				String result = "";
				if (getSql != null) {
					if (dao.execute(getSql) != 1) {
						result = "删除失败!";
					} else {
						result = "删除成功";
					}
				}
				return result;
			}
			
		/*
		 *
		 * <p>名称: PaperDelete</p>
		 * <p>说明:删除单个试卷 </p>
		 * @param id
		 * @return
		 * @see net.ib.util.service.ExamPaperDBService#PaperDelete(java.lang.String)
		 */
			public String PaperDelete(String id) {
				String getSql = "delete from sys_exam_paper where exam_paper_id='"+ id + "'";	
				String sql="delete from sys_exam_paper_content where exam_paper_id='"+ id + "'";
				String result = "";
				if (getSql != null) {
					if (dao.execute(getSql) == 1&&(dao.execute(sql) == 1)){
						result = "删除成功!";
					} else {
						result = "删除失败！";
					}
				}
				return result;
			}
			
			/**
			 * 
			 * <p>名称：EPDBBulkDelete</p>
			 * <p>说明：批量删除试卷库</p>
			 * <p>参数：@param id
			 * <p>参数：@return 设定文件</p>
			 * <p>返回值：String 返回类型</p>
			 * <p>@param id
			 * <p>@return result</p>
			 */
			
			
			public String EPDBBulkDelete(String id) {
				String[] array = id.split(" ");
				String Sql = "delete from sys_epdb_list where";
				String result = "";
				for (int i = 0; i < array.length; i++) {
					if (i != 0) {
						Sql += " or";
					}
					Sql += " epdb_id='" + array[i] + "'";
				}
				if (Sql != null) {
					if (dao.execute(Sql) != 1) {
						result = "删除失败!";
					} else {
						result = "删除成功";
					}
				}
				return result;
			}
			
			public String PaperBulkDelete(String id) {
				String[] array = id.split(" ");
				String Sql1 = "delete from sys_exam_paper where";
				String Sql2 ="delete from sys_exam_paper_content where";
				String result = "";
				for (int i = 0; i < array.length; i++) {
					if (i != 0) {
						Sql1 += " or";
						Sql2+=" or";
					}
					Sql1 += " exam_paper_id='" + array[i] + "'";
					Sql2 += " exam_paper_id='" + array[i] + "'";
				}
				if (Sql1 != null) {
					if ((dao.execute(Sql1) == 1&&dao.execute(Sql2) == 1)) {
						result = "删除成功!";
					} else {
						result = "删除失败！";
					}
				}
				return result;
			}
			/**
			 * 
			 * <p>名称：EPDBSortConfig</p>
			 * <p>说明：修改分类体系试卷库分类配置信息获取</p>
			 * <p>参数：@param id
			 * <p>参数：@return 设定文件</p>
			 * <p>返回值：String 返回类型</p>
			 * <p>@param id
			 * <p>@return sortconfigtext</p>
			 */
			public String EPDBSortConfig(String id) {
				String sortconfigid = "";
				String sortconfigtext = "";
				List<Map> list_configid = (List<Map>) dao
						.executeQuery("select * from sys_ib_classification_node where itembank_id='"
								+ id + "'");
				for (int i = 0; i < list_configid.size(); i++) {
					sortconfigid = (String) list_configid.get(i).get("node_id");
				}
				List<Map> list_nodetext = (List<Map>) dao
						.executeQuery("select text from sys_ib_classification_tree where id='"
								+ sortconfigid + "'");
				for (int i = 0; i < list_nodetext.size(); i++) {
					sortconfigtext = (String) list_nodetext.get(i).get("text");
				}
				return sortconfigtext;
			}
			
			
			/**
			 * 
			 * <p>名称：EPDBModify</p>
			 * <p>说明：TODO( 修改试卷库，信息入库)</p>
			 * <p>参数：@param id
			 * <p>参数：@param name
			 * <p>参数：@param discription
			 * <p>参数：@param identifier
			 * <p>参数：@param config_leafid
			 * <p>参数：@param use
			 * <p>参数：@return result</p>
			 * <p>返回值：String 返回类型</p>
			 */
			public String EPDBModify(String id, String name, String discription,
					String identifier, String config_leafid, String use) {
				String result=""; // 前台反馈结果
				// 修改前标识符为id的值
				String oldname = "";// 修改前name
				String olduse="";// 修改前用途
				String oldexplain="";// 修改前说明
				String oldconfig_leafid="";// 修改前分类配置id
				//去除分类配置id前的modify
				if(!"null".equals(config_leafid)){
					config_leafid = config_leafid.substring(6, config_leafid.length());
				}
				// 从数据库中取出修改前的数据
				List<Map> list = (List<Map>) dao
						.executeQuery("select * from sys_epdb_list where epdb_id='"
								+ id + "' ");
				for (int i = 0; i < list.size(); i++) {
					oldname = (String) list.get(i).get("epdb_name");
					olduse = (String) list.get(i).get("usage");
					oldexplain = (String) list.get(i).get("explain");
				}
				List<Map> list_sortnode = (List<Map>) dao
						.executeQuery("select node_id from sys_ib_classification_node where itembank_id='"
								+ id + "' ");
				for (int i = 0; i < list_sortnode.size(); i++) {
					oldconfig_leafid = (String) list_sortnode.get(i).get("node_id");
				}

				// 若标识符和标题均已修改
				if (!oldname.equals(name) && !id.equals(identifier)) {
					// 判断新的名称是否和数据库中的名称重复
					List<Map> listname = (List<Map>) dao
							.executeQuery("select * from sys_epdb_list where epdb_name='"
									+ name + "' ");
					// 若名称有重复
					if (listname.size() > 0) {
						result = "名称重复，请重新输入";
						return result;
					}
					// 判断新的标识符是否和数据库中重复
					List<Map> listidentifier = (List<Map>) dao
							.executeQuery("select * from sys_epdb_list where epdb_id='"
									+ identifier + "' ");
					// 若新的标识符有重复
					if (listidentifier.size() > 0) {
						result = "标识符重复，请重新输入";
						return result;
					}
					//判断试卷库分类体系节点是否触发
					if("null".equals(config_leafid)){
						String Sql = "update sys_epdb_list set epdb_name='"
								+ name + "',epdb_id='" + identifier + "',explain='"
								+ discription + "',usage='" + use
								+ "' 	where	epdb_id='" + id + "'";
						dao.execute(Sql);
					}else{
						// 判断试卷库分类体系设置是否更改，若未改变
						if (oldconfig_leafid.equals(config_leafid)) {
							String Sql = "update sys_epdb_list set epdb_name='"
									+ name + "',epdb_id='" + identifier + "',explain='"
									+ discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
						} else {
							// 试卷库分类体系设置已修改
							String Sql = "update sys_epdb_list set epdb_name='"
									+ name + "',epdb_id='" + identifier + "',explain='"
									+ discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
							// 修改试卷库分类体系设置项
							String Sql1 = "update sys_ib_classification_node set epdb_id='"
									+ identifier
									+ "',node_id='"
									+ config_leafid
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql1);
							

						}
					}
					/*dao.execute("ALTER  TABLE QT_BASICFIELD_"+id+" RENAME TO QT_BASICFIELD_"+identifier+"");
					dao.execute("ALTER  TABLE QT_BLOBFIELD_"+id+" RENAME TO QT_BLOBFIELD_"+identifier+"");*/
					
				}

				// 若标识符未修改，名称已修改
				if (!oldname.equals(name) && id.equals(identifier)) {
					List<Map> listname = (List<Map>) dao
							.executeQuery("select * from sys_epdb_list where epdb_name='"
									+ name + "' ");
					// 若新名称有重复
					if (listname.size() > 0) {
						result = "名称重复，请重新输入";
						return result;
					}
					//判断试卷库分类体系节点是否触发
					if("null".equals(config_leafid)){
						String Sql = "update sys_epdb_list set epdb_name='"
								+ name + "',explain='" + discription + "',usage='" + use
								+ "' 	where	epdb_id='" + id + "'";
						dao.execute(Sql);
					}else{
						// 判断试卷库分类体系设置是否更改，若未改变
						if (oldconfig_leafid.equals(config_leafid)) {
							String Sql = "update sys_epdb_list set epdb_name='"
									+ name + "',explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
						} else {
							// 试卷库分类体系设置已修改
							String Sql = "update sys_epdb_list set epdb_name='"
									+ name + "',explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
							// 修改试卷库分类体系设置项
							String Sql1 = "update sys_ib_classification_node set node_id='"
									+ config_leafid + "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql1);
						}
					}
				}

				// 若名称未修改，标识符已修改
				if (oldname.equals(name) && !id.equals(identifier)) {
					List<Map> listidentifier = (List<Map>) dao
							.executeQuery("select * from sys_epdb_list where epdb_id='"
									+ identifier + "' ");
					// 若新的分类体系名称有重复
					if (listidentifier.size() > 0) {
						result = "标识符重复，请重新输入";
						return result;
					}
					//判断试卷库分类体系节点是否触发
					if("null".equals(config_leafid)){
						String Sql = "update sys_epdb_list set epdb_id='"
								+ identifier + "',explain='" + discription + "',usage='" + use
								+ "' 	where	epdb_id='" + id + "'";
						dao.execute(Sql);
					}else{
						// 判断试卷库分类体系设置是否更改，若未改变
						if (oldconfig_leafid.equals(config_leafid)) {
							String Sql = "update sys_epdb_list set epdb_id='"
									+ identifier + "',explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
						} else {
							// 试卷库分类体系设置已修改
							String Sql = "update sys_epdb_list set epdb_id='"
									+ identifier + "',explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
							// 修改试卷库分类体系设置项
							String Sql1 = "update sys_ib_classification_node set epdb_id='"
									+ identifier + "',node_id='"
									+ config_leafid + "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql1);
						}
						
					}
					/*dao.execute("ALTER  TABLE QT_BASICFIELD_"+id+" RENAME TO QT_BASICFIELD_"+identifier+"");
					dao.execute("ALTER  TABLE QT_BLOBFIELD_"+id+" RENAME TO QT_BLOBFIELD_"+identifier+"");
*/
				}

				// 若名称和标识符均未修改
				if (oldname.equals(name) && id.equals(identifier)) {
					//判断试卷库分类体系节点是否触发
					if("null".equals(config_leafid)){
						String Sql = "update sys_epdb_list set explain='" + discription + "',usage='" + use
								+ "' 	where	epdb_id='" + id + "'";
						dao.execute(Sql);
					}else{
						// 判断试卷库分类体系设置是否更改，若未改变
						if (oldconfig_leafid.equals(config_leafid)) {
							String Sql = "update sys_epdb_list set explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
						} else {
							// 试卷库分类体系设置已修改
							String Sql = "update sys_epdb_list set explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
							// 修改试卷库分类体系设置项
							String Sql1 = "update sys_ib_classification_node set node_id='"
									+ config_leafid + "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql1);
						}
					}
				
				
				}
				result = "修改成功";
				return result;
			}
			
		/*
		 * (非-Javadoc)
		 * <p>名称: EPDBSelectByCurseId</p>
		 * <p>说明: 根据课程获取试卷库信息</p>
		 * @param CurseFieldId
		 * @return
		 * @see net.ib.util.service.ExamPaperDBService#EPDBSelectByCurseId(java.lang.String)
		 */
			public String	EPDBSelectByCurseId(String CurseFieldId){
				//logger.debug(CurseFieldId+"******************************************************");
				String sql	=	"select * from sys_epdb_list  where curse_id='"+CurseFieldId+"'";
				List<Map> list	=	(List<Map>) dao.executeQuery(sql);
				
				String Json =	service.DataListToJson(list);
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

		/*
		 * 
		 * <p>名称: PaperModify</p>
		 * <p>说明: 修改试卷信息</p>
		 * @param paper_id
		 * @param newname
		 * @param newTotalScore
		 * @param newDuration
		 * @param newdifficulty
		 * @return
		 * @see net.ib.util.service.ExamPaperDBService#PaperModify(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
		 */
			public String PaperModify(String paper_id,String newname,String newTotalScore,String newDuration,String newdifficulty){
			String result="";
			String oldname="";
			List<Map> list=dao.executeQuery("Select paper_name from sys_exam_paper where exam_paper_id='"+paper_id+"'");
			oldname=(String)list.get(0).get("paper_name");
			if(oldname.equals(newname)){
				dao.execute("update sys_exam_paper set total_score='" + newTotalScore + "',exam_duration='" + newDuration
							+"',difficulty='"+newdifficulty+ "' 	where	exam_paper_id='" +paper_id + "'");
				result="修改成功" ;
				
			}
			else{
				List<Map> name_list=dao.executeQuery("Select paper_name from sys_exam_paper where paper_name='"+newname+"'");
				if(name_list.size()>0){
					result="名字重复，请重新输入";
					
				}
				else{
					dao.execute("update sys_exam_paper set total_score='" + newTotalScore + "',exam_duration='" + newDuration
							+"',difficulty='"+newdifficulty+"',paper_name='" +newname+"' 	where	exam_paper_id='" +paper_id + "'");
				result="修改成功" ;
				
				}
			}
			return result;
		}
			@Override
			public String SelectEDBPByCourseAndUsage(String courseId,
					String usage) {
				// TODO Auto-generated method stub
				

				// TODO Auto-generated method stub
				logger.debug(courseId);
				logger.debug(usage);
				//查询一个用户所有访问权限的所有试题库的SQL语句
				String	SQLSelectItemBankByUserPerm="select * from sys_epdb_list where CURSE_ID='"+courseId+"' and usage='"+usage+"'";
				logger.debug(SQLSelectItemBankByUserPerm);
				
				//String	Sql	=	"select * from ("+SQLSelectItemBankByUserPerm+") where curse_id='"+courseId+"' and use='"+usage+"'";
				
				List<Map> list	=dao.executeQuery(SQLSelectItemBankByUserPerm);
				
				String	Json	=service.DataListToJson(list);
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
				return Json;
			}
}
