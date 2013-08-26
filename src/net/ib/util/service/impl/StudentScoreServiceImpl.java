
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	StudentScoreServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-19 ����11:12:17
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-19 ����11:12:17
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
 * <p>������net.ib.util.service.impl.StudentScoreServiceImpl </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
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
	
	//��������ɾ��ָ��ѧ���ɼ�
	@Override
	public String DeleteStudentScoreOnTeachingClass(String DeleteId,String selectedCurseId) {
		// TODO Auto-generated method stub
		String	Sql="delete from SYS_STUDENT_SCORE where curse_id='"+selectedCurseId+"' and student_id='"+DeleteId+"' ";
		logger.debug(Sql);
		String	result="";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if(1==dao.execute(Sql)){
			result="{\"result\":\"succ\",\"text\":\"ɾ��ѧ���ɼ��ɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"ɾ��ѧ���ɼ�ʧ��\"}";
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

	//���������޸�ָ��ѧ���ɼ�
	@Override
	public String UpdateStudentScoreOnTeachingClass(String SchoolID,
			String Score,String selectedCurseId, String oldSchoolID,String SelectedTeachingClassId) {
		// TODO Auto-generated method stub
		//��ѯ����ӵ�ѧ���Ƿ����
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
				logger.info("�޸�ѧ���ɼ������и�ѧ�Ų����ڣ�");
				result = "{\"result\":\"failedId\",\"text\":\"��ѧ�ź���ϵͳ�в����ڣ�����������\"}";
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
					result="{\"result\":\"succ\",\"text\":\"�޸�ѧ���ɼ��ɹ�\"}";
				}
				else{
					result="{\"result\":\"failure\",\"text\":\"�޸�ѧ���ɼ�ʧ��\"}";
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

	//����ָ���������༶ɾ��ĳ��ѧ���ĳɼ�
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
			result="{\"result\":\"succ\",\"text\":\"ɾ��ѧ���ɼ��ɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"ɾ��ѧ���ɼ�ʧ��\"}";
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
	//�޸�ָ���༶�µ�ĳ��ѧ���ĳɼ���Ϣ
	@Override
	public String UpdateStudentScoreOnClass(String SchoolID, String Score,
			String selectedCurseId, String oldSchoolID, String SelectedClassId) {
		// TODO Auto-generated method stub
		//��ѯ����ӵ�ѧ���Ƿ����
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
						logger.info("�޸�ѧ���ɼ������и�ѧ�Ų����ڣ�");
						result = "{\"result\":\"failedId\",\"text\":\"��ѧ�ź���ϵͳ�в����ڣ�����������\"}";
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
							result="{\"result\":\"succ\",\"text\":\"�޸�ѧ���ɼ��ɹ�\"}";
						}
						else{
							result="{\"result\":\"failure\",\"text\":\"�޸�ѧ���ɼ�ʧ��\"}";
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
