
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ExamPaperDBServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-12 ����7:27:23
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-12 ����7:27:23
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
 * <p>������net.ib.util.service.impl.ExamPaperDBServiceImpl </p>
 * <p>�������Ծ��������ʵ��</p>
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
	 * ����Ծ����Ϣ���
	 */
	public String PaperAdd(String name, String discription,
			String identifier, String leafid, String config_leafid, String use) {
		String result = "";
		/*logger.debug("+++++++++++++++++++++++++++++++++++++++++++++==");
		logger.debug(name);
		logger.debug(identifier);
		logger.debug(leafid);
		logger.debug(config_leafid);*/
		
		// �жϷ�����ϵ�����Ƿ��ظ�
		List<Map> list = (List<Map>) dao
				.executeQuery("select * from sys_epdb_list where epdb_name='"
						+ name + "' or epdb_id='" + identifier + "'");
		for (int i = 0; i < list.size(); i++) {
			String epdb_name = (String) list.get(i).get("epdb_name");
			String epdb_id = (String) list.get(i).get("epdb_id");

			// �жϷ�����ϵ�����Ƿ��ظ�
			if (epdb_name.equals(name)) {
				result = "�����ظ�������������";
				return result;
			}
			// �жϷ�����ϵ��ʶ��ʱ���ظ�
			if (epdb_id.equals(identifier)) {
				result = "��ʶ���ظ�������������";
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
		result = "��ӳɹ�";
		return result;
	}
	
	/**
	 * �Ծ�ⰴ��ɫ��Ȩ��ȡĳ��ɫ��Ա���Ծ��Ȩ��	
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
		 * �Ծ�ⰴ��ɫ��Ȩ��ȡ�Ծ������δѡ�γ�
		 */
		public String GetEPDBBymajor(String majorid){
			List<Map> subjectlist	=	dao.executeQuery("select * from sys_curse where special_field_id='"+majorid+"'");
			String curse_sql="";
			for (int j = 0; j < subjectlist.size(); j++) {
				//�γ�sqlƴ��
				String subjectid=(String) subjectlist.get(j).get("curse_id");
					if(curse_sql!=""){
						curse_sql=curse_sql+" or ";
					}
					curse_sql+="curse_id="+"'"+subjectid+"'";
				}
			//�����Ծ��
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
		 * �Ծ�ⰴ��ɫ��Ȩ��ȡ�Ծ�����ݣ���ѡ�γ�
		 */
		public String GetEPDBBycurse(String curseid){
			//�����Ծ��
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
		 * �Ծ�ⰴ��ɫ��Ȩ����������
		 */
		public String SaveEPDBAuthorityConfigByroleSelectcourse(String authorized_epdb,String role,String member,String courseid,String majorid){
			String result=null;
			//�����������ڸÿγ��µ������
			ArrayList epdblist=new ArrayList();
			List<Map> AllList	=	dao.executeQuery("select * from sys_perm_epdb_role");
			for(int j=0;j<AllList.size();j++){
				String epdbid=(String) AllList.get(j).get("epdb_id");
				List<Map> list	=	dao.executeQuery("select * from sys_epdb_list where epdb_id='"+epdbid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					epdblist.add(epdbid);
				}
			}
			//ɾ���ÿγ��±���ԭ������
			for(int t=0;t<epdblist.size();t++){
				dao.execute("delete from sys_perm_epdb_role where role_id='"+role+"' and membertype_id='"+member+"' and epdb_id='"+epdblist.get(t)+"'");
			}
	//�����������
	//���ж��Ƿ���Ȩ�������ֻ��һ��
	//������
	if(authorized_epdb.indexOf(",")!=-1){
		String[] eslist =authorized_epdb.split(",");
		//�����������
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				dao.execute("insert into sys_perm_epdb_role (id,epdb_id,role_id,membertype_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+role+"','"+member+"','"+majorid+"')");
			}
		}
	}else{
		dao.execute("insert into sys_perm_epdb_role (id,epdb_id,role_id,membertype_id,major_id) values(sys_guid(),'"+authorized_epdb+"','"+role+"','"+member+"','"+majorid+"')");

	}
			result="���óɹ�";
			return result;
		}
		/*
		 *
		 * <p>����: SaveEPDBAuthorityConfigByroleSelectnonecourse</p>
		 * <p>˵��: ����ⰴ��ɫ��Ȩ���������ã�δѡ�γ�</p>
		 * @param authorized_epdb
		 * @param role
		 * @param member
		 * @return
		 * @see net.ib.util.service.ExamPaperDBService#SaveEPDBAuthorityConfigByroleSelectnonecourse(java.lang.String, java.lang.String, java.lang.String)
		 */
		
		public String SaveEPDBAuthorityConfigByroleSelectnonecourse(String authorized_epdb, String role, String member,String majorid){
			String result=null;
			//���ж��Ƿ���Ȩ�������ֻ��һ��
			//������
			if(authorized_epdb.indexOf(",")!=-1){
				String[] eslist=authorized_epdb.split(",");
				//ɾ������ԭ������
				dao.execute("delete from sys_perm_epdb_role where role_id='"+role+"' and membertype_id='"+member+"' and major_id='"+majorid+"'");
				//�����������
				//�ж��Ƿ���ѡ�������,Ϊ��ʱ����Ϊ1
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_role (id,epdb_id,role_id,membertype_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+role+"','"+member+"','"+majorid+"')");
					}
				}
				
			}else{
				dao.execute("delete from sys_perm_epdb_role where role_id='"+role+"' and membertype_id='"+member+"' and major_id='"+majorid+"'");
				dao.execute("insert into sys_perm_epdb_role (id,epdb_id,role_id,membertype_id,major_id) values(sys_guid(),'"+authorized_epdb+"','"+role+"','"+member+"','"+majorid+"')");
			}
			result="���óɹ�";
			return result;
		}	
		/**
		 * <p>���ƣ�SaveEPDBAuthorityConfigByuserSelectcourse</p>
		 * <p>˵����TODO �Ծ�ⰴ�û���Ȩ��ȡ�Ծ�����ݣ���ѡ�γ�)
		 * <p>������@param authorized_epdb
		 * <p>������@param number
		 * <p>������@param member
		 * <p>������@param courseid
		 * <p>������@return �趨�ļ�</p>
		 * <p>����ֵ��String ��������</p>
		 * <p>@param authorized_epdb
		 * <p>@param number
		 * <p>@param member
		 * <p>@param courseid
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigByuserSelectcourse(String authorized_epdb,String number,String member,String courseid,String majorid){
			String result=null;
			//�����������ڸÿγ��µ������
			ArrayList epdblist=new ArrayList();
			List<Map> AllList	=	dao.executeQuery("select * from sys_perm_epdb_user");
			for(int j=0;j<AllList.size();j++){
				String epdbid=(String) AllList.get(j).get("epdb_id");
				List<Map> list	=	dao.executeQuery("select * from sys_epdb_list where epdb_id='"+epdbid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					epdblist.add(epdbid);
				}
			}
			//ɾ���ÿγ��±���ԭ������
			for(int t=0;t<epdblist.size();t++){
				dao.execute("delete from sys_perm_epdb_user where user_id='"+number+"' and membertype='"+member+"' and epdb_id='"+epdblist.get(t)+"'");
			}
	//�����������
	//���ж��Ƿ���Ȩ�������ֻ��һ��
	//������
	if(authorized_epdb.indexOf(",")!=-1){
		String[] eslist =authorized_epdb.split(",");
		//�����������
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				dao.execute("insert into sys_perm_epdb_user (id,epdb_id,user_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"','"+majorid+"')");
			}
		}
	}else{
		dao.execute("insert into sys_perm_epdb_user(id,epdb_id,user_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+number+"','"+member+"','"+majorid+"')");

	}
			result="���óɹ�";
			return result;
		}
		
		/**
		 * 
		 * <p>���ƣ�SaveEPDBAuthorityConfigBydepartmentSelectcourse</p>
		 * <p>˵����TODO(�Ծ�ⰴ������Ȩ��ȡ�Ծ�����ݣ���ѡ�γ�)</p>
		 * <p>������@param authorized_epdb
		 * <p>������@param department
		 * <p>������@param member
		 * <p>������@param courseid
		 * <p>������@return �趨�ļ�</p>
		 * <p>����ֵ��String ��������</p>
		 * <p>@param authorized_epdb
		 * <p>@param department
		 * <p>@param member
		 * <p>@param courseid
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigBydepartmentSelectcourse(String authorized_epdb,String department,String member,String courseid,String majorid){
			String result=null;
			//�����������ڸÿγ��µ������
			ArrayList epdblist=new ArrayList();
			List<Map> AllList	=	dao.executeQuery("select * from sys_perm_epdb_department");
			for(int j=0;j<AllList.size();j++){
				String epdbid=(String) AllList.get(j).get("epdb_id");
				List<Map> list	=	dao.executeQuery("select * from sys_epdb_list where epdb_id='"+epdbid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					epdblist.add(epdbid);
				}
			}
			//ɾ���ÿγ��±���ԭ������
			for(int t=0;t<epdblist.size();t++){
				dao.execute("delete from sys_perm_epdb_department where department_id='"+department+"' and membertype='"+member+"' and epdb_id='"+epdblist.get(t)+"'");
			}
	//�����������
	//���ж��Ƿ���Ȩ�������ֻ��һ��
	//������
	if(authorized_epdb.indexOf(",")!=-1){
		String[] eslist =authorized_epdb.split(",");
		//�����������
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				dao.execute("insert into sys_perm_epdb_department (id,epdb_id,department_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+department+"','"+member+"','"+majorid+"')");
			}
		}
	}else{
		dao.execute("insert into sys_perm_epdb_department(id,epdb_id,department_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+department+"','"+member+"','"+majorid+"')");

	}
			result="���óɹ�";
			return result;
}
		
		/**
		 * 
		 * <p>���ƣ�SaveEPDBAuthorityConfigBymembertypeSelectcourse</p>
		 * <p>˵����TODO(�Ծ�ⰴ��Ա������Ȩ��ȡ�Ծ�����ݣ���ѡ�γ�)</p>
		 * <p>������@param authorized_epdb
		 * <p>������@param member
		 * <p>������@param courseid
		 * <p>������@return �趨�ļ�</p>
		 * <p>����ֵ��String ��������</p>
		 * <p>@param authorized_epdb
		 * <p>@param member
		 * <p>@param courseid
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigBymembertypeSelectcourse(String authorized_epdb,String member,String courseid,String majorid){
			String result=null;
			//�����������ڸÿγ��µ������
			ArrayList epdblist=new ArrayList();
			List<Map> AllList	=	dao.executeQuery("select * from sys_perm_epdb_membertype");
			for(int j=0;j<AllList.size();j++){
				String epdbid=(String) AllList.get(j).get("epdb_id");
				List<Map> list	=	dao.executeQuery("select * from sys_epdb_list where epdb_id='"+epdbid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					epdblist.add(epdbid);
				}
			}
			//ɾ���ÿγ��±���ԭ������
			for(int t=0;t<epdblist.size();t++){
				dao.execute("delete from sys_perm_epdb_membertype where membertype='"+member+"' and epdb_id='"+epdblist.get(t)+"'");
			}
	//�����������
	//���ж��Ƿ���Ȩ�������ֻ��һ��
	//������
	if(authorized_epdb.indexOf(",")!=-1){
		String[] eslist =authorized_epdb.split(",");
		//�����������
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				dao.execute("insert into sys_perm_epdb_membertype (id,epdb_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+member+"','"+majorid+"')");
			}
		}
	}else{
		dao.execute("insert into sys_perm_epdb_membertype(id,epdb_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+member+"','"+majorid+"')");

	}
			result="���óɹ�";
			return result;
		}
		/**
		 * 
		 * <p>���ƣ�SaveEPDBAuthorityConfigByteachingclassSelectcourse</p>
		 * <p>˵����TODO(�Ծ�ⰴ�༶��Ȩ��ȡ�Ծ�����ݣ���ѡ�γ�)</p>
		 * <p>������@param authorized_epdb
		 * <p>������@param teachingclass
		 * <p>������@param courseid
		 * <p>������@return �趨�ļ�</p>
		 * <p>����ֵ��String ��������</p>
		 * <p>@param authorized_epdb
		 * <p>@param teachingclass
		 * <p>@param courseid
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigByteachingclassSelectcourse(String authorized_epdb,String teachingclass,String courseid,String majorid){
			String result=null;
			//�����������ڸÿγ��µ������
			ArrayList epdblist=new ArrayList();
			List<Map> AllList	=	dao.executeQuery("select * from sys_perm_epdb_class");
			for(int j=0;j<AllList.size();j++){
				String epdbid=(String) AllList.get(j).get("epdb_id");
				List<Map> list	=	dao.executeQuery("select * from sys_epdb_list where epdb_id='"+epdbid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					epdblist.add(epdbid);
				}
			}
			//ɾ���ÿγ��±���ԭ������
			for(int t=0;t<epdblist.size();t++){
				dao.execute("delete from sys_perm_epdb_class where class_id='"+teachingclass+"' and epdb_id='"+epdblist.get(t)+"'");
			}
	//�����������
	//���ж��Ƿ���Ȩ�������ֻ��һ��
	//������
	if(authorized_epdb.indexOf(",")!=-1){
		String[] eslist =authorized_epdb.split(",");
		//�����������
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				dao.execute("insert into sys_perm_epdb_class (id,epdb_id,class_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+teachingclass+"','"+majorid+"')");
			}
		}
	}else{
		dao.execute("insert into sys_perm_epdb_class(id,epdb_id,class_id,major_id) values(sys_guid(),'"+authorized_epdb+"','"+teachingclass+"','"+majorid+"')");

	}
			result="���óɹ�";
			return result;
		}
		
		/*
		 *
		 * <p>����: SaveEPDBAuthorityConfigByuserSelectnonecourse</p>
		 * <p>˵��: ����ⰴ�û���Ȩ���������ã�δѡ�γ�</p>
		 * @param authorized_epdb
		 * @param role
		 * @param member
		 * @return
		 * @see net.ib.util.service.ExamPaperDBService#SaveEPDBAuthorityConfigByuserSelectnonecourse(java.lang.String, java.lang.String, java.lang.String)
		 */
		
		public String SaveEPDBAuthorityConfigByuserSelectnonecourse(String authorized_epdb,String number, String member,String majorid){
			String result=null;
			//���ж��Ƿ���Ȩ�������ֻ��һ��
			//������
			if(authorized_epdb.indexOf(",")!=-1){
				String[] eslist=authorized_epdb.split(",");
				//ɾ������ԭ������
				dao.execute("delete from sys_perm_epdb_user where user_id='"+number+"' and membertype='"+member+"' and major_id='"+majorid+"'");
				//�����������
				//�ж��Ƿ���ѡ�������,Ϊ��ʱ����Ϊ1
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_user (id,epdb_id,user_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"','"+majorid+"')");
					}
				}
				
			}else{
				dao.execute("delete from sys_perm_epdb_user where user_id='"+number+"' and membertype='"+member+"' and major_id='"+majorid+"'");
				dao.execute("insert into sys_perm_epdb_user (id,epdb_id,user_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+number+"','"+member+"','"+majorid+"')");
			}
			result="���óɹ�";
			return result;
		}	
		/**
		 * 
		 * <p>���ƣ�SaveEPDBAuthorityConfigBydepartmentSelectnonecourse</p>
		 * <p>˵����TODO(����ⰴj������Ȩ���������ã���ѡ�γ�)</p>
		 * <p>������@param authorized_epdb
		 * <p>������@param department
		 * <p>������@param member
		 * <p>������@return �趨�ļ�</p>
		 * <p>����ֵ��String ��������</p>
		 * <p>@param authorized_epdb
		 * <p>@param department
		 * <p>@param member
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigBydepartmentSelectnonecourse(String authorized_epdb,String department, String member,String majorid){
			String result=null;
			//���ж��Ƿ���Ȩ�������ֻ��һ��
			//������
			if(authorized_epdb.indexOf(",")!=-1){
				String[] eslist=authorized_epdb.split(",");
				//ɾ������ԭ������
				dao.execute("delete from sys_perm_epdb_department where department_id='"+department+"' and membertype='"+member+"' and major_id='"+majorid+"'");
				//�����������
				//�ж��Ƿ���ѡ�������,Ϊ��ʱ����Ϊ1
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_department (id,epdb_id,department_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+department+"','"+member+"','"+majorid+"')");
					}
				}
				
			}else{
				dao.execute("delete from sys_perm_epdb_department where department_id='"+department+"' and membertype='"+member+"' and major_id='"+majorid+"'");
				dao.execute("insert into sys_perm_epdb_department (id,epdb_id,department_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+department+"','"+member+"','"+majorid+"')");
			}
			result="���óɹ�";
			return result;
		}	
		
		/**
		 * 
		 * <p>���ƣ�SaveEPDBAuthorityConfigBymembertypeSelectnonecourse</p>
		 * <p>˵����TODO(����ⰴ��Ա������Ȩ���������ã�δѡ�γ�)</p>
		 * <p>������@param authorized_epdb
		 * <p>������@param member
		 * <p>������@return �趨�ļ�</p>
		 * <p>����ֵ��String ��������</p>
		 * <p>@param authorized_epdb
		 * <p>@param member
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigBymembertypeSelectnonecourse(String authorized_epdb, String member,String majorid){
			String result=null;
			//���ж��Ƿ���Ȩ�������ֻ��һ��
			//������
			if(authorized_epdb.indexOf(",")!=-1){
				String[] eslist=authorized_epdb.split(",");
				//ɾ������ԭ������
				dao.execute("delete from sys_perm_epdb_membertype where membertype='"+member+"' and major_id='"+majorid+"'");
				//�����������
				//�ж��Ƿ���ѡ�������,Ϊ��ʱ����Ϊ1
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_membertype (id,epdb_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+member+"','"+majorid+"')");
					}
				}
				
			}else{
				dao.execute("delete from sys_perm_epdb_membertype where membertype='"+member+"' and major_id='"+majorid+"'");
				dao.execute("insert into sys_perm_epdb_membertype (id,epdb_id,membertype,major_id) values(sys_guid(),'"+authorized_epdb+"','"+member+"','"+majorid+"')");
			}
			result="���óɹ�";
			return result;
		}	
		/**
		 * 
		 * <p>���ƣ�SaveEPDBAuthorityConfigByteachingclassSelectnonecourse</p>
		 * <p>˵����TODO(����ⰴ�༶��Ȩ���������ã�δѡ�γ�)</p>
		 * <p>������@param authorized_epdb
		 * <p>������@param teachingclass
		 * <p>������@return �趨�ļ�</p>
		 * <p>����ֵ��String ��������</p>
		 * <p>@param authorized_epdb
		 * <p>@param teachingclass
		 * <p>@return</p>
		 */
		public String SaveEPDBAuthorityConfigByteachingclassSelectnonecourse(String authorized_epdb, String teachingclass,String majorid){
			String result=null;
			//���ж��Ƿ���Ȩ�������ֻ��һ��
			//������
			if(authorized_epdb.indexOf(",")!=-1){
				String[] eslist=authorized_epdb.split(",");
				//ɾ������ԭ������
				dao.execute("delete from sys_perm_epdb_class where membertype='"+teachingclass+"' and major_id='"+majorid+"'");
				//�����������
				//�ж��Ƿ���ѡ�������,Ϊ��ʱ����Ϊ1
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_class (id,epdb_id,class_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+teachingclass+"','"+majorid+"')");
					}
				}
				
			}else{
				dao.execute("delete from sys_perm_epdb_class where class_id='"+teachingclass+"' and major_id='"+majorid+"'");
				dao.execute("insert into sys_perm_epdb_class (id,epdb_id,class_id,major_id) values(sys_guid(),'"+authorized_epdb+"','"+teachingclass+"','"+majorid+"')");
			}
			result="���óɹ�";
			return result;
		}	
		
		/**
		 * �Ծ�ⰴ�û���Ȩ��ȡĳ�û����Ծ��Ȩ��	
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
			 * �Ծ�ⰴ�û���Ȩ����������
			 */
			public String SaveEPDBAuthorityConfigByuser(String authorized_epdb,String number,String member){
				String result=null;
				String[] eslist=authorized_epdb.split(",");
				//ɾ������ԭ������
				dao.execute("delete from sys_perm_epdb_user where user_id='"+number+"' and membertype='"+member+"'");
				//�����������
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_user (id,epdb_id,user_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"')");
				}
				}
				result="���óɹ�";
				return result;
			}
			
			
			/**
			 * �Ծ�ⰴ��֯������Ȩ����ѯȨ��
			 */
			public String GetAuthorizedEPDBBydepartment(String memberid,String departmentid,String uppernode){
				String[] uppernodelist=uppernode.split(",");
				//sqlƴ��
				String uppernodeSql="";
				for(int t=0;t<uppernodelist.length;t++){
					String uppernodeid=uppernodelist[t];
					if(uppernodeSql!=""){
						uppernodeSql=uppernodeSql+" or ";
					}
					uppernodeSql+="department_id="+"'"+uppernodeid+"'";
				}
				//Ȩ����Ϣ
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
			 * �Ծ�ⰴ������Ȩ����������
			 */
			public String SaveEPDBAuthorityConfigBydepartment(String authorized_epdb, String member,String departmentid){
				String result=null;
				String[] eslist=authorized_epdb.split(",");
				//ɾ������ԭ������
				dao.execute("delete from sys_perm_epdb_department where department_id='"+departmentid+"' and membertype='"+member+"'");
				//�����������
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						dao.execute("insert into sys_perm_epdb_department (id,epdb_id,department_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+departmentid+"','"+member+"')");
					}
				}
			
				result="���óɹ�";
				return result;
			}
			
			
			
			/**
			 * �Ծ�ⰴ��Ա������Ȩ����ѯȨ��
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
			 * �Ծ�ⰴ��Ա������Ȩ����������
			 */
			public String SaveEPDBAuthorityConfigBymembertype(String authorized_epdbid,String member){
				String result=null;
				String[] eslist=authorized_epdbid.split(",");
				//ɾ������ԭ������
				dao.execute("delete from sys_perm_epdb_membertype where membertype='"+member+"'");
				//�����������
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_membertype (id,epdb_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+member+"')");
				}
				}
				result="���óɹ�";
				return result;
			}
			
			
			/**
			 * �Ծ�ⰴ�༶������Ȩ����ѯȨ��
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
			 * (��-Javadoc)
			 * <p>����: SaveEPDBAuthorityConfigByteachingclass</p>
			 * <p>˵��: �Ծ�ⰴ�༶��Ȩ����������</p>
			 * @param authorized_epdbid
			 * @param teachingclass
			 * @return result
			 * @see net.ib.util.service.ExamPaperDBService#SaveEPDBAuthorityConfigByteachingclass(java.lang.String, java.lang.String)
			 */
			
			public String SaveEPDBAuthorityConfigByteachingclass(String authorized_epdbid,String teachingclass){
				String result=null;
				String[] eslist=authorized_epdbid.split(",");
				//ɾ������ԭ������
				dao.execute("delete from sys_perm_epdb_class where class_id='"+teachingclass+"'");
				//�����������
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					dao.execute("insert into sys_perm_epdb_class (id,epdb_id,class_id) values(sys_guid(),'"+eslist[i]+"','"+teachingclass+"')");
				}
				}
				result="���óɹ�";
				return result;
			}
			/**
			 * 
			 * <p>���ƣ�EPDBDelete</p>
			 * <p>˵���� ɾ���Ծ�⣬��������</p>
			 * <p>������@param id
			 * <p>������@return �趨�ļ�</p>
			 * <p>����ֵ��String ��������</p>
			 * <p>@param id
			 * <p>@return result</p>
			 */
			
			
			public String EPDBDelete(String id) {
				String getSql = "delete from sys_epdb_list where epdb_id='"
						+ id + "'";
				String result = "";
				if (getSql != null) {
					if (dao.execute(getSql) != 1) {
						result = "ɾ��ʧ��!";
					} else {
						result = "ɾ���ɹ�";
					}
				}
				return result;
			}
			
		/*
		 *
		 * <p>����: PaperDelete</p>
		 * <p>˵��:ɾ�������Ծ� </p>
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
						result = "ɾ���ɹ�!";
					} else {
						result = "ɾ��ʧ�ܣ�";
					}
				}
				return result;
			}
			
			/**
			 * 
			 * <p>���ƣ�EPDBBulkDelete</p>
			 * <p>˵��������ɾ���Ծ��</p>
			 * <p>������@param id
			 * <p>������@return �趨�ļ�</p>
			 * <p>����ֵ��String ��������</p>
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
						result = "ɾ��ʧ��!";
					} else {
						result = "ɾ���ɹ�";
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
						result = "ɾ���ɹ�!";
					} else {
						result = "ɾ��ʧ�ܣ�";
					}
				}
				return result;
			}
			/**
			 * 
			 * <p>���ƣ�EPDBSortConfig</p>
			 * <p>˵�����޸ķ�����ϵ�Ծ�����������Ϣ��ȡ</p>
			 * <p>������@param id
			 * <p>������@return �趨�ļ�</p>
			 * <p>����ֵ��String ��������</p>
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
			 * <p>���ƣ�EPDBModify</p>
			 * <p>˵����TODO( �޸��Ծ�⣬��Ϣ���)</p>
			 * <p>������@param id
			 * <p>������@param name
			 * <p>������@param discription
			 * <p>������@param identifier
			 * <p>������@param config_leafid
			 * <p>������@param use
			 * <p>������@return result</p>
			 * <p>����ֵ��String ��������</p>
			 */
			public String EPDBModify(String id, String name, String discription,
					String identifier, String config_leafid, String use) {
				String result=""; // ǰ̨�������
				// �޸�ǰ��ʶ��Ϊid��ֵ
				String oldname = "";// �޸�ǰname
				String olduse="";// �޸�ǰ��;
				String oldexplain="";// �޸�ǰ˵��
				String oldconfig_leafid="";// �޸�ǰ��������id
				//ȥ����������idǰ��modify
				if(!"null".equals(config_leafid)){
					config_leafid = config_leafid.substring(6, config_leafid.length());
				}
				// �����ݿ���ȡ���޸�ǰ������
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

				// ����ʶ���ͱ�������޸�
				if (!oldname.equals(name) && !id.equals(identifier)) {
					// �ж��µ������Ƿ�����ݿ��е������ظ�
					List<Map> listname = (List<Map>) dao
							.executeQuery("select * from sys_epdb_list where epdb_name='"
									+ name + "' ");
					// ���������ظ�
					if (listname.size() > 0) {
						result = "�����ظ�������������";
						return result;
					}
					// �ж��µı�ʶ���Ƿ�����ݿ����ظ�
					List<Map> listidentifier = (List<Map>) dao
							.executeQuery("select * from sys_epdb_list where epdb_id='"
									+ identifier + "' ");
					// ���µı�ʶ�����ظ�
					if (listidentifier.size() > 0) {
						result = "��ʶ���ظ�������������";
						return result;
					}
					//�ж��Ծ�������ϵ�ڵ��Ƿ񴥷�
					if("null".equals(config_leafid)){
						String Sql = "update sys_epdb_list set epdb_name='"
								+ name + "',epdb_id='" + identifier + "',explain='"
								+ discription + "',usage='" + use
								+ "' 	where	epdb_id='" + id + "'";
						dao.execute(Sql);
					}else{
						// �ж��Ծ�������ϵ�����Ƿ���ģ���δ�ı�
						if (oldconfig_leafid.equals(config_leafid)) {
							String Sql = "update sys_epdb_list set epdb_name='"
									+ name + "',epdb_id='" + identifier + "',explain='"
									+ discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
						} else {
							// �Ծ�������ϵ�������޸�
							String Sql = "update sys_epdb_list set epdb_name='"
									+ name + "',epdb_id='" + identifier + "',explain='"
									+ discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
							// �޸��Ծ�������ϵ������
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

				// ����ʶ��δ�޸ģ��������޸�
				if (!oldname.equals(name) && id.equals(identifier)) {
					List<Map> listname = (List<Map>) dao
							.executeQuery("select * from sys_epdb_list where epdb_name='"
									+ name + "' ");
					// �����������ظ�
					if (listname.size() > 0) {
						result = "�����ظ�������������";
						return result;
					}
					//�ж��Ծ�������ϵ�ڵ��Ƿ񴥷�
					if("null".equals(config_leafid)){
						String Sql = "update sys_epdb_list set epdb_name='"
								+ name + "',explain='" + discription + "',usage='" + use
								+ "' 	where	epdb_id='" + id + "'";
						dao.execute(Sql);
					}else{
						// �ж��Ծ�������ϵ�����Ƿ���ģ���δ�ı�
						if (oldconfig_leafid.equals(config_leafid)) {
							String Sql = "update sys_epdb_list set epdb_name='"
									+ name + "',explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
						} else {
							// �Ծ�������ϵ�������޸�
							String Sql = "update sys_epdb_list set epdb_name='"
									+ name + "',explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
							// �޸��Ծ�������ϵ������
							String Sql1 = "update sys_ib_classification_node set node_id='"
									+ config_leafid + "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql1);
						}
					}
				}

				// ������δ�޸ģ���ʶ�����޸�
				if (oldname.equals(name) && !id.equals(identifier)) {
					List<Map> listidentifier = (List<Map>) dao
							.executeQuery("select * from sys_epdb_list where epdb_id='"
									+ identifier + "' ");
					// ���µķ�����ϵ�������ظ�
					if (listidentifier.size() > 0) {
						result = "��ʶ���ظ�������������";
						return result;
					}
					//�ж��Ծ�������ϵ�ڵ��Ƿ񴥷�
					if("null".equals(config_leafid)){
						String Sql = "update sys_epdb_list set epdb_id='"
								+ identifier + "',explain='" + discription + "',usage='" + use
								+ "' 	where	epdb_id='" + id + "'";
						dao.execute(Sql);
					}else{
						// �ж��Ծ�������ϵ�����Ƿ���ģ���δ�ı�
						if (oldconfig_leafid.equals(config_leafid)) {
							String Sql = "update sys_epdb_list set epdb_id='"
									+ identifier + "',explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
						} else {
							// �Ծ�������ϵ�������޸�
							String Sql = "update sys_epdb_list set epdb_id='"
									+ identifier + "',explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
							// �޸��Ծ�������ϵ������
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

				// �����ƺͱ�ʶ����δ�޸�
				if (oldname.equals(name) && id.equals(identifier)) {
					//�ж��Ծ�������ϵ�ڵ��Ƿ񴥷�
					if("null".equals(config_leafid)){
						String Sql = "update sys_epdb_list set explain='" + discription + "',usage='" + use
								+ "' 	where	epdb_id='" + id + "'";
						dao.execute(Sql);
					}else{
						// �ж��Ծ�������ϵ�����Ƿ���ģ���δ�ı�
						if (oldconfig_leafid.equals(config_leafid)) {
							String Sql = "update sys_epdb_list set explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
						} else {
							// �Ծ�������ϵ�������޸�
							String Sql = "update sys_epdb_list set explain='" + discription + "',usage='" + use
									+ "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql);
							// �޸��Ծ�������ϵ������
							String Sql1 = "update sys_ib_classification_node set node_id='"
									+ config_leafid + "' 	where	epdb_id='" + id + "'";
							dao.execute(Sql1);
						}
					}
				
				
				}
				result = "�޸ĳɹ�";
				return result;
			}
			
		/*
		 * (��-Javadoc)
		 * <p>����: EPDBSelectByCurseId</p>
		 * <p>˵��: ���ݿγ̻�ȡ�Ծ����Ϣ</p>
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
		 * <p>����: PaperModify</p>
		 * <p>˵��: �޸��Ծ���Ϣ</p>
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
				result="�޸ĳɹ�" ;
				
			}
			else{
				List<Map> name_list=dao.executeQuery("Select paper_name from sys_exam_paper where paper_name='"+newname+"'");
				if(name_list.size()>0){
					result="�����ظ�������������";
					
				}
				else{
					dao.execute("update sys_exam_paper set total_score='" + newTotalScore + "',exam_duration='" + newDuration
							+"',difficulty='"+newdifficulty+"',paper_name='" +newname+"' 	where	exam_paper_id='" +paper_id + "'");
				result="�޸ĳɹ�" ;
				
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
				//��ѯһ���û����з���Ȩ�޵�����������SQL���
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
