/**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TeachingClassMemberServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�wuzexi
 * | �������ڣ�2012-12-10 ����4:49:55
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-10 ����4:49:55
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
 * ������net.ib.util.service.impl.TeachingClassMemberServiceImpl
 * </p>
 * <p>
 * ������TODO(��һ�仰�������ļ���ʲô)
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


	/* (��-Javadoc)
	 * <p>����: TeachingClassMemberAdd</p>
	 * <p>˵��: </p>
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
		//ͨ��curse_class_id��ѯ�Ƿ�����뵱ǰ��Ҫ��ӵ������ظ�����
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
		//�����ѧ��֮ǰ�Ѿ�����ӽ�ĳЩ���ΰ༶
		if(list.size()!=0){
			String	TempId="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempId	=	(String) map.get("existedcurseid");
				logger.debug(TempId);
				if(TempId.equals(curseId)){
					logger.info("��ӿ��ΰ༶��Ա�����пγ̵�Id���ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"��ѧ���Ѿ�ѡ���ÿγ̣�������ѡ��\"}";
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
			//��ӿ��ΰ༶��Ա�����пγ̵�Idû���ظ�
			//�����û�Ψһ��ʶ��
			 UUID uuid = UUID.randomUUID(); 
			 String Id=uuid.toString();
			//����ѧ����Ϣ�ӵ�sys_curse_class_member��
			 String sql_first="insert into sys_curse_class_member fields(id,curse_class_id,user_id) VALUES('"+Id+"','"+teachingClassId+"','"+addUserId+"')";
			
			if(1==dao.execute(sql_first)){
				result = "{\"result\":\"succ\",\"text\":\"���ѧ����Ϣ�����ΰ༶�ɹ�\"}";
	
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
		//�����ѧ��֮ǰ��δ����ӽ��κο��ΰ༶
		else{
			//�����û�Ψһ��ʶ��
			 UUID uuid = UUID.randomUUID(); 
			 String Id=uuid.toString();
			//����ѧ����Ϣ�ӵ�sys_curse_class_member��
			 String sql_first="insert into sys_curse_class_member fields(id,curse_class_id,user_id) VALUES('"+Id+"','"+teachingClassId+"','"+addUserId+"')";
			
			if(1==dao.execute(sql_first)){
				result = "{\"result\":\"succ\",\"text\":\"���ѧ����Ϣ�����ΰ༶�ɹ�\"}";
	
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

	
	 /* (��-Javadoc)
	 * <p>����: TeachingClassDelete</p>
	 * <p>˵��: ����IDɾ����ѧ�༶</p>
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
			result	=	"{\"result\":\"ɾ�����ΰ༶��Ա�ɹ�\"}";
		}
		else{
			result	=	"{\"result\":\"ɾ�����ΰ༶��Աʧ��\"}";
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

	
	 /* (��-Javadoc)
	 * <p>����: TeachingClassUpdate</p>
	 * <p>˵��: </p>
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
			
			//�жϸý�ʦ�Ƿ����
			
			String	GetTeacherSql	=	"Select * from sys_user_teacher where school_id='"+NewTeacherId+"'";
			List<Map> TeacherList	=	dao.executeQuery(GetTeacherSql);
			if(TeacherList.size()==0){
				logger.info("��ӽ�ʦʱ����ϵͳ��û�иý�ʦ��Ϣ");
				result	=	"{\"result\":\"failed\",\"text\":\"û�иý�ʦ��Ϣ\"}";
				try {
					PrintWriter pw = response.getWriter();
					pw.print(result);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			//��ʼ��������
			sql = "update sys_curse_class set curse_class_id='" + NewClassId
					+ "',class_name='" + NewClassName + "',teacher_id='"
					+ NewTeacherId + "',teaching_material_id='"
					+ NewTeachingMaterialId + "',explain='" + NewExplain
					+ "' where curse_class_id='" + OldClassId + "'";

			logger.debug(sql);
			dao.execute(sql);
			result = "{\"result\":\"succ\",\"text\":\"�޸Ľ�ѧ�༶�ɹ�\"}";
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
				ClassId = (String) map.get("curse_class_id");
				ClassName = (String) map.get("class_name");
				// ����༶Id�ظ�
				if (NewClassId.equals(ClassId)) {
					logger.info("�޸İ༶�����а༶Id�ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"�༶ID�Ѿ����ڣ�����������\"}";
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
				else if (NewClassName.equals(ClassName)) {
					logger.info("�޸İ༶�����а༶�����ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�༶�����Ѿ����ڣ�����������\"}";
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
					logger.info("��ӿ��ΰ༶�����쳣");
				}
			}			
		}
		return null;
	}*/

	
	 /* (��-Javadoc)
	 * <p>����: TeachingClassMemberBulkDelete</p>
	 * <p>˵��: ����ɾ�����ΰ༶��Ա</p>
	 * @param DelIds ɾ����Id ���Id�ÿո�ֿ�
	 * @return
	 * @see net.ib.util.service.TeachingClassMemberService#TeachingClassMemberBulkDelete(java.lang.String)
	 */
	@Override
	public String TeachingClassMemberBulkDelete(String DelIds) {
		// TODO Auto-generated method stub
		logger.debug(DelIds);
		//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
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
			result="{\"result\":\"success\",\"text\":\"����ɾ���ɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"����ɾ��ʧ��\"}";
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
	
	
	 /* (��-Javadoc)
		 * <p>����: TeachingClassMemberBulkAdd</p>
		 * <p>˵��: ������ӿ��ΰ༶��Ա</p>
		 * @param bulkAddIDs ��ӵĳ�Ա��Id ���Id�ÿո�ֿ�
		 * @return
		 * @see net.ib.util.service.TeachingClassMemberService#TeachingClassMemberBulkAdd(java.lang.String)
		 */
		@Override
		public String TeachingClassMemberBulkAdd(String bulkAddIDs,String teachingClassId,String curseId) {
			// TODO Auto-generated method stub
			logger.debug(bulkAddIDs);
			//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
			String[] array=bulkAddIDs.split(" ");
			logger.debug(array);
			logger.debug(array.length);
			
			String result = "";
			//ͨ��curse_class_id��ѯ�Ƿ�����뵱ǰ��Ҫ��ӵ������ظ�����
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
			//�����ѧ��֮ǰ�Ѿ�����ӽ�ĳЩ���ΰ༶
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
						logger.info("��ӿ��ΰ༶��Ա�����пγ̵�Id���ظ�");
						result = "{\"result\":\"failedId\",\"text\":\"ѧ�� "+TempName+" �Ѿ�ѡ���ÿγ̣�������ѡ��ѧ��\"}";
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
				//��ӿ��ΰ༶��Ա�����������û���ѡ�γ̵�Id��û�к͵�ǰ�ظ�
				//����ѧ����Ϣ�ӵ�sys_curse_class_member��
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
					result = "{\"result\":\"succ\",\"text\":\"�������ѧ����Ϣ�����ΰ༶�ɹ�\"}";
		
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
			//���Ҫ��ӵ�ѧ��֮ǰ��δ����ӽ��κο��ΰ༶
			else
			{
				//����ѧ����Ϣ�ӵ�sys_curse_class_member��
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
					result = "{\"result\":\"succ\",\"text\":\"�������ѧ����Ϣ�����ΰ༶�ɹ�\"}";
		
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
