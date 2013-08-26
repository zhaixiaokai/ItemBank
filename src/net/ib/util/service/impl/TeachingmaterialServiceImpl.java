
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TeachingmaterialServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�wuzexi
 * | �������ڣ�2012-12-6 ����8:22:24
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-6 ����8:22:24
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
import net.ib.util.service.TeachingmaterialService;
import net.ib.util.service.Service;


  /**
 * <p>������net.ib.util.service.impl.TeachingmaterialServiceImpl </p>
 * <p>�������̲Ĺ���ķ���ʵ����</p>
 * <p></p>
 */
public class TeachingmaterialServiceImpl implements TeachingmaterialService {

	private static Logger logger = Logger.getLogger(TeachingmaterialServiceImpl.class);
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
	 * <p>����: addTeachingMaterial</p>
	 * <p>˵��: </p>
	 * @param curseId
	 * @param teachingMaterialID
	 * @param teachingMaterialName
	 * @param teachingMaterialAuthor
	 * @return
	 * @see net.ib.util.service.TeachingmaterialService#addTeachingMaterial(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addTeachingMaterial(String curseId,String teachingMaterialID,String teachingMaterialName,String teachingMaterialVersion,String teachingMaterialAuthor,String teachingMaterialDate,String teachingMaterialHouse,String teachingMaterialCode) {
		// TODO Auto-generated method stub
		String result = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		
		String	CheckExistSql	=	"select * from SYS_CURSE where CURSE_ID='"+curseId+"'";
		List<Map>	CheckList	=	dao.executeQuery(CheckExistSql);
		if(CheckList.size()==0){
			logger.info("��ӽ̲Ĺ�����δ�ҵ��ύ�Ŀγ�");
			result = "{\"result\":\"failedId\",\"text\":\"�ÿγ̲����ڣ�\"}";
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//ͨ��id��name��ѯ�Ƿ�����뵱ǰ��Ҫ��ӵ������ظ�����
		String	Sql	=	"select TEACHING_MATERIAL_ID,NAME,CURSE_ID from SYS_TEACHING_MATERIAL where TEACHING_MATERIAL_ID='"+teachingMaterialID
				+"' or NAME='"+teachingMaterialName+"'";
		List<Map>	list	=	dao.executeQuery(Sql);
		
		//������ظ���
		if(list.size()!=0){
			String	TempId="";
			String	TempName="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempId	=	(String) map.get("teaching_material_id");
				TempName=	(String) map.get("name");
				logger.debug(TempName);
				logger.debug(TempId);
				if(TempId.equals(teachingMaterialID)){
					logger.info("��ӽ̲Ĺ����н̲ı���ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"�̲ı���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(teachingMaterialName)){
					logger.info("��ӽ̲Ĺ����н̲������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�̲������Ѿ����ڣ�����������\"}";
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
		//û���ظ���
		else{
			Sql="insert into SYS_TEACHING_MATERIAL (TEACHING_MATERIAL_ID,NAME,CURSE_ID,VERSION,AUTHOR,PUBLICATION_DATE,PUBLISHING_HOUSE,ISBN_CODE) values " +
					"('"+teachingMaterialID+"','"+teachingMaterialName+"','"+curseId+
				    "','"+teachingMaterialVersion+"','"+teachingMaterialAuthor+"',to_date('"+teachingMaterialDate+"','yyyy-MM-dd')," +
				    "'"+teachingMaterialHouse+"','"+teachingMaterialCode+
					"')";
			
			String Sql2="insert into SYS_TEACHING_CHAPTER_TREE (TEXT,TREE_ID,SNO,PID,NODE_PATH,NODE_SERIES,NODE_EXPLAIN) values " +
					"('"+teachingMaterialName+"','"+teachingMaterialID+"','1','root','0001','1','')";
			
			if((1==dao.execute(Sql))&&(1==dao.execute(Sql2))){
				result = "{\"result\":\"succ\",\"text\":\"��ӽ̲ĳɹ�\"}";
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
	@Override
	public String updateTeachingMaterial(String oldTeachingMaterialId,String teachingMaterialName,String	teachingMaterialID,String	teachingMaterialVersion,String	teachingMaterialAuthor,String teachingMaterialDate,String teachingMaterialHouse,String teachingMaterialCode)
	{
		// TODO Auto-generated method stub
		logger.debug(oldTeachingMaterialId);
		logger.debug(teachingMaterialName);
		logger.debug(teachingMaterialID);
		logger.debug(teachingMaterialVersion);
		logger.debug(teachingMaterialAuthor);
		logger.debug(teachingMaterialDate);
		logger.debug(teachingMaterialHouse);
		logger.debug(teachingMaterialCode);
		String sql = "select teaching_material_id,name from sys_teaching_material where (teaching_material_id='"
				+ teachingMaterialID + "' or name='" + teachingMaterialName + "') and teaching_material_id!='"+oldTeachingMaterialId+"'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map>	list	=	dao.executeQuery(sql);
		String	result="";
		
		if (list.size() == 0) {
		
			//��ʼ��������
			sql = "update sys_teaching_material set teaching_material_id='" + teachingMaterialID
					+ "',name='" + teachingMaterialName 
					+ "',version='" +teachingMaterialVersion
					+ "',author='"+ teachingMaterialAuthor
					+ "',publication_date=to_date('"+teachingMaterialDate+"','yyyy-MM-dd')"
					+ ",publishing_house='"+ teachingMaterialHouse
					+ "',isbn_code='"+ teachingMaterialCode
					+ "' where teaching_material_id='" + oldTeachingMaterialId + "'";

			logger.debug(sql);
			dao.execute(sql);
			result = "{\"result\":\"succ\",\"text\":\"�޸Ľ̲ĳɹ�\"}";
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		//���ظ�
		else{
			Map map = new HashMap();
			String ClassId = "";
			String ClassName = "";
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				ClassId = (String) map.get("teaching_material_id");
				ClassName = (String) map.get("name");
				// ����༶Id�ظ�
				if (teachingMaterialID.equals(ClassId)) {
					logger.info("�޸Ľ̲Ĺ�����Id�ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"�̲�ID�Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}
				// �༶
				else if (teachingMaterialName.equals(ClassName)) {
					logger.info("�޸Ľ̲Ĺ����������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�̲������Ѿ����ڣ�����������\"}";
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
					logger.info("�޸Ľ̲ķ����쳣");
				}
			}			
		}
		return null;
	}
	@Override
	public String deleteTeachingMaterial(String deleteId) {
		// TODO Auto-generated method stub
		logger.debug(deleteId);
		
		String	sql	=	"delete from sys_teaching_material where TEACHING_MATERIAL_ID='"+deleteId+"'";
		logger.debug(sql);
		
		String	sql2	=	"delete from sys_teaching_chapter_tree where TREE_ID='"+deleteId+"'";
		logger.debug(sql2);
		
		String	result="";
		if((1==dao.execute(sql))&&(1==dao.execute(sql2))){
			result	=	"{\"result\":\"ɾ���̲ĳɹ�\"}";
		}
		else{
			result	=	"{\"result\":\"ɾ���̲�ʧ��\"}";
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
	@Override
	public String bulkDeleteTeachingMaterial(String	deleteIds) {
		// TODO Auto-generated method stub
		logger.debug(deleteIds);
		//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
		String[] array=deleteIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		
		String	Sql	=	"delete from sys_teaching_material where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" TEACHING_MATERIAL_ID='"+array[i]+"'";
		}
		logger.debug(Sql);
		
		String	Sql2	=	"delete from sys_teaching_chapter_tree where";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql2+=" or";
			}
			Sql2+=" TREE_ID='"+array[i]+"'";
		}
		logger.debug(Sql2);
		
		if(1==dao.execute(Sql)&&1==dao.execute(Sql2)){
			result="{\"result\":\"success\",\"text\":\"����ɾ���̲ĳɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"����ɾ���̲�ʧ��\"}";
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
	public	String getTeachMaterialByCourseId(String id){
		
		String	result	=	null;
		
		
		String	sql	=	"select * from sys_teaching_material where curse_id='"+id+"'";
		List<Map>	list	=	dao.executeQuery(sql);
		result=service.DataListToJson(list);
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