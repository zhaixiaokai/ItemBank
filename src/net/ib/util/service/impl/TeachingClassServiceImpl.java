/**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TeachingClassServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
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

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.TeachingClassService;

/**
 * <p>
 * ������net.ib.util.service.impl.TeachingClassServiceImpl
 * </p>
 * <p>
 * ������TODO(��һ�仰�������ļ���ʲô)
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
	 * (��-Javadoc) <p>����: TeachingClassAdd</p> <p>˵��: ��ӿ��ΰ༶</p>
	 * 
	 * @param courseId ��ѧ�γ�Id
	 * 
	 * @param className �༶����
	 * 
	 * @param classId �༶ID
	 * 
	 * @param TeacherId �༶�ڿν�ʦ�̹���
	 * 
	 * @param Explain
	 * 
	 * @param TeachingMaterialId �γ�ʹ�ý̲�ID
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
			
			//�жϸý�ʦ�Ƿ����
			
			String	GetTeacherSql	=	"Select * from sys_user_teacher where school_id='"+TeacherId+"'";
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
			result = "{\"result\":\"succ\",\"text\":\"��ӿ��ΰ༶�ɹ�\"}";
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
				// ����༶Id�ظ�
				if (classId.equals(ClassId)) {
					logger.info("��Ӱ༶�����а༶Id�ظ�");
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
				else if (className.equals(ClassName)) {
					logger.info("��Ӱ༶�����а༶�����ظ�");
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
	}

	
	 /* (��-Javadoc)
	 * <p>����: TeachingClassDelete</p>
	 * <p>˵��: ����IDɾ�����ΰ༶</p>
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
			result	=	"{\"result\":\"ɾ�����ΰ༶�ɹ�\"}";
		}
		else{
			result	=	"{\"result\":\"ɾ�����ΰ༶ʧ��\"}";
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
			result = "{\"result\":\"succ\",\"text\":\"�޸Ŀ��ΰ༶�ɹ�\"}";
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
	}

	
	 /* (��-Javadoc)
	 * <p>����: TeachingClassBulkDelete</p>
	 * <p>˵��: ����ɾ�����ΰ༶</p>
	 * @param DelIds ɾ����Id ���Id�ÿո�ֿ�
	 * @return
	 * @see net.ib.util.service.TeachingClassService#TeachingClassBulkDelete(java.lang.String)
	 */
	@Override
	public String TeachingClassBulkDelete(String DelIds) {
		// TODO Auto-generated method stub
		logger.debug(DelIds);
		//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
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
}
