
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	KnowledgePointServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-29 ����2:56:10
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-29 ����2:56:10
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
import net.ib.util.service.KnowledgePointService;
import net.ib.util.service.Service;


  /**
 * <p>������net.ib.util.service.impl.KnowledgePointServiceImpl </p>
 * <p>������֪ʶ�����ķ���ʵ����</p>
 * <p></p>
 */
public class KnowledgePointServiceImpl implements KnowledgePointService {

	private static Logger logger = Logger.getLogger(KnowledgePointServiceImpl.class);
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
	 * <p>����: addKnowledgePoint</p>
	 * <p>˵��: </p>
	 * @param curseId
	 * @param KnowledgePointId
	 * @param KnowledgePointName
	 * @param KnowledgePointExplain
	 * @return
	 * @see net.ib.util.service.KnowledgePointService#addKnowledgePoint(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addKnowledgePoint(String curseId, String KnowledgePointId,
			String KnowledgePointName, String KnowledgePointExplain) {
		// TODO Auto-generated method stub
		String result = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		
		String	CheckExistSql	=	"select * from SYS_CURSE where CURSE_ID='"+curseId+"'";
		List<Map>	CheckList	=	dao.executeQuery(CheckExistSql);
		if(CheckList.size()==0){
			logger.info("���֪ʶ�������δ�ҵ��ύ�Ŀγ�");
			result = "{\"result\":\"failedId\",\"text\":\"�ÿγ̲�����\"}";
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
		String	Sql	=	"select KNOWLEDGE_POINT_ID,NAME,CURSE_ID from SYS_KNOWLEDGE_POINT where KNOWLEDGE_POINT_ID='"+KnowledgePointId
				+"' or NAME='"+KnowledgePointName+"'";
		List<Map>	list	=	dao.executeQuery(Sql);
		
		//������ظ���
		if(list.size()!=0){
			String	TempId="";
			String	TempName="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempId	=	(String) map.get("knowledge_point_id");//knowledge_point_id
				TempName=	(String) map.get("name");
				logger.debug(TempName);
				logger.debug(TempId);
				if(TempId.equals(KnowledgePointId)){
					logger.info("���֪ʶ�������֪ʶ�����ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"֪ʶ�����Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(KnowledgePointName)){
					logger.info("���֪ʶ�������֪ʶ�������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"֪ʶ�������Ѿ����ڣ�����������\"}";
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
			Sql="insert into SYS_KNOWLEDGE_POINT (KNOWLEDGE_POINT_ID,NAME,CURSE_ID,KNOWLEDGE_POINT_CONTENT) values " +
					"('"+KnowledgePointId+"','"+KnowledgePointName+"','"+curseId+"','"+KnowledgePointExplain+"')";
			if(1==dao.execute(Sql)){
				result = "{\"result\":\"succ\",\"text\":\"���֪ʶ��ɹ�\"}";
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
	public String updateKnowledgePoint(String OldId, String KnowledgePointName,
			String KnowledgePointId, String KnowledgePointExplain) {
		// TODO Auto-generated method stub
		logger.debug(OldId);
		logger.debug(KnowledgePointName);
		logger.debug(KnowledgePointId);
		logger.debug(KnowledgePointExplain);
		String sql = "select knowledge_point_id,name from sys_knowledge_point where (knowledge_point_id='"
				+ KnowledgePointId + "' or name='" + KnowledgePointName + "') and knowledge_point_id!='"+OldId+"'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map>	list	=	dao.executeQuery(sql);
		String	result="";
		
		if (list.size() == 0) {
		
			//��ʼ��������
			sql = "update sys_knowledge_point set knowledge_point_id='" + KnowledgePointId
					+ "',name='" + KnowledgePointName + "',knowledge_point_content='"
					+ KnowledgePointExplain
					+ "' where knowledge_point_id='" + OldId + "'";

			logger.debug(sql);
			dao.execute(sql);
			result = "{\"result\":\"succ\",\"text\":\"�޸�֪ʶ��ɹ�\"}";
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
				ClassId = (String) map.get("knowledge_point_id");
				ClassName = (String) map.get("name");
				// ����༶Id�ظ�
				if (KnowledgePointId.equals(ClassId)) {
					logger.info("�޸�֪ʶ������а༶Id�ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"֪ʶ��ID�Ѿ����ڣ�����������\"}";
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
				else if (KnowledgePointName.equals(ClassName)) {
					logger.info("�޸�֪ʶ������а༶�����ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"֪ʶ�������Ѿ����ڣ�����������\"}";
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
					logger.info("�޸�֪ʶ�㷢���쳣");
				}
			}			
		}
		return null;
	}
	@Override
	public String deleteKonwledgePoint(String deleteId) {
		// TODO Auto-generated method stub
		logger.debug(deleteId);
		String	sql	=	"delete from sys_knowledge_point where knowledge_point_id='"+deleteId+"'";
		logger.debug(sql);
		String	result="";
		if(1==dao.execute(sql)){
			result	=	"{\"result\":\"ɾ��֪ʶ��ɹ�\"}";
		}
		else{
			result	=	"{\"result\":\"ɾ��֪ʶ��ʧ��\"}";
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
	public String bulkDeleteKnowledgePoint(String deleteIds) {
		// TODO Auto-generated method stub
		logger.debug(deleteIds);
		//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
		String[] array=deleteIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		String	Sql	=	"delete from sys_knowledge_point where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" knowledge_point_id='"+array[i]+"'";
		}
		logger.debug(Sql);
		if(1==dao.execute(Sql)){
			result="{\"result\":\"success\",\"text\":\"ɾ���ɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"ɾ���ɹ�\"}";
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
	
	@Override
	public	String	getKnowledgePointListByChapterId(String	leafNodeIDs)
	{
		// TODO Auto-generated method stub
		logger.debug(leafNodeIDs);
		//�յ���leafNodeIDs��ʽΪ   id1 id2 id3 id4 ....
		String[] array=leafNodeIDs.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		
		String result = "";
		
		String	Sql	=	"select AllClass.*,skp.name KnowledgePointName from "
				+ "(select rownum rn,stck.* from sys_teaching_chapter_knowledge stck where";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" chapter_id='"+array[i]+"'";
		}
		String Sql_second = ") AllClass "
				          + "inner join sys_knowledge_point skp on AllClass.knowledge_point_id=skp.knowledge_point_id ";
		Sql = Sql+Sql_second;
		logger.debug(Sql);
		
		List<Map>	list	=	dao.executeQuery(Sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(StrList);
			System.out.print("$%%%%%%%%%%%%%%"+StrList);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	@Override
	public  String  getKnowledgePointListByTermId(String leafNodeIDs)
	{
		// TODO Auto-generated method stub
		logger.debug(leafNodeIDs);
		//�յ���leafNodeIDs��ʽΪ   id1 id2 id3 id4 ....
		String[] array=leafNodeIDs.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		
		String result = "";
		
		String	Sql	=	"select AllClass.*,skp.name KnowledgePointName from "
				+ "(select rownum rn,sptk.* from sys_progr_teaching_knowledge sptk where";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" progress_teaching_node_id='"+array[i]+"'";
		}
		String Sql_second = ") AllClass "
				          + "inner join sys_knowledge_point skp on AllClass.knowledge_point_id=skp.knowledge_point_id ";
		Sql = Sql+Sql_second;
		logger.debug(Sql);
		
		List<Map>	list	=	dao.executeQuery(Sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(StrList);
			System.out.print("$%%%%%%%%%%%%%%"+StrList);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public	String  getAllKnowledgePointListByCourseId(String myCourseId)
	{
		logger.debug(myCourseId);
		
		String result = "";
		
		String	Sql	= "select rownum rn,skp.* from sys_knowledge_point skp where curse_id='"
				+ myCourseId
				+ "'";
		logger.debug(Sql);
		
		List<Map>	list	=	dao.executeQuery(Sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(StrList);
			System.out.print("$%%%%%%%%%%%%%%"+StrList);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public	String  getKnowledgeListForCheck(String myTeachingMaterialId)
	{
		logger.debug(myTeachingMaterialId);
		
		String result = "";
		
		String	Sql	=	"select * from"
				+ "(select AllClass.*,tct.tree_id TeachingMaterialId from "
				+ "(select rownum rn,stck.* from sys_teaching_chapter_knowledge stck"
				+ ") AllClass "
				+ "inner join sys_teaching_chapter_tree tct on AllClass.chapter_id=tct.id) "
				+ "where TeachingMaterialId='"
				+ myTeachingMaterialId
				+ "'";
		logger.debug(Sql);
		
		List<Map>	list	=	dao.executeQuery(Sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(StrList);
			System.out.print("$%%%%%%%%%%%%%%"+StrList);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public	String  getKnowledgeListForCheckByCourseId(String myCourseId)
	{
        logger.debug(myCourseId);
		
		String result = "";
		
		String	Sql	=	"select * from"
				+ "(select AllClass.*,spt.tree_id CourseId from "
				+ "(select rownum rn,ptk.* from sys_progr_teaching_knowledge ptk"
				+ ") AllClass "
				+ "inner join sys_progress_teaching spt on AllClass.progress_teaching_node_id=spt.id) "
				+ "where CourseId='"
				+ myCourseId
				+ "'";
		logger.debug(Sql);
		
		List<Map>	list	=	dao.executeQuery(Sql);
		String	StrList	=	service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(StrList);
			System.out.print("$%%%%%%%%%%%%%%"+StrList);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public	String  SaveKnowledgePointConfigByChapter(String selected_point, String MyChapterId){
		String result="";
		String[] kplist=selected_point.split(",");
		//ɾ������ԭ������
		String	Sql	= "delete from sys_teaching_chapter_knowledge where chapter_id='"+MyChapterId+"'";
		logger.debug(Sql);
		dao.execute(Sql);
		//�����������
        for(int i=0;i<kplist.length;i++){
        	dao.execute("insert into sys_teaching_chapter_knowledge (id,chapter_id,knowledge_point_id) values(sys_guid(),'"+MyChapterId+"','"+kplist[i]+"')");
		}
       
        result	=	"{\"result\":\"����֪ʶ��ɹ�\"}";
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
	public	String  SaveKnowledgePointConfigByTerm(String selected_point,String myTermId){
		String result="";
		String[] kplist=selected_point.split(",");
		//ɾ������ԭ������
		String	Sql	= "delete from sys_progr_teaching_knowledge where progress_teaching_node_id='"+myTermId+"'";
		logger.debug(Sql);
		dao.execute(Sql);
		//�����������
		if(kplist.length!=0){
	        for(int i=0;i<kplist.length;i++){
	        	dao.execute("insert into sys_progr_teaching_knowledge (id,progress_teaching_node_id,knowledge_point_id) values(sys_guid(),'"+myTermId+"','"+kplist[i]+"')");
			}
		}
       
        result	=	"{\"result\":\"����֪ʶ��ɹ�\"}";
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
