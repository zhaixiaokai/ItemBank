
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	RoleMemberServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2013-2-28 ����2:51:53
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-2-28 ����2:51:53
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
import net.ib.util.service.RoleMemberService;
import net.ib.util.service.Service;


  /**
 * <p>������net.ib.util.service.impl.RoleMemberServiceImpl </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class RoleMemberServiceImpl implements RoleMemberService {
	
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

	@Override
	public String addRoleMember(String RoleListId, String DepartmentId,String BulkMemberId) {
		// TODO Auto-generated method stub
		
		
		logger.debug(BulkMemberId);
		//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
		String[] array=BulkMemberId.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		// ��ɫ��Ա����
		String QuerySql="select * from SYS_ROLE_MEMBER where (role_id='"+RoleListId+"') and ( ";
		for(int i=0;i<array.length;i++){
			if(i!=0){
				QuerySql+=" or ";
			}
			QuerySql+=" user_id = '"+array[i]+"' ";
		}
		QuerySql+=" ) ";
		List<Map> QueryList=dao.executeQuery(QuerySql);
		
		//����ǰ̨ѡ��ĳ�Ա�����������ĳ�Ա�������
		
		//Sql��ʾ���������������ݲ������ݿ�����
		String	Sql	=	"insert into SYS_ROLE_MEMBER fields (role_id,user_id) ";
		String	result="";
		
		//�����������ظ�
		int flag = 0;// ��ʾ���ظ����ݵĸ���
		int count=0;//��ʾ�ظ����ݵĸ���
		if (QueryList.size() != 0) {
			
			for (int i = 0; i < array.length; i++) {
				Map QueryMap = new HashMap();
				int k;
				for (k = 0; k < QueryList.size(); k++) {
					QueryMap = QueryList.get(k);
					if (QueryMap.get("user_id").equals(array[i]))
						break;
				}
				if (k >= QueryList.size()) {
					if(flag!=0){
						Sql+=" union ";
					}
					Sql += " select '" + RoleListId + "', '" + array[i]
							+ "' from dual ";
					flag++;
				}
				else {
					count++;
				}
			}
		}
		// û�������ظ���������ֱ�Ӳ������
		else {
			for (int i = 0; i < array.length; i++) {
				if (i != 0) {
					Sql += " union ";
				}
				Sql += " select '" + RoleListId + "', '" + array[i]
						+ "' from dual ";
			}

		}
		
		logger.debug(Sql);
		if(count==(array.length)){
			result="{\"result\":\"success\",\"text\":\"��ӵĳ�Աȫ���Ѿ����ڸý�ɫ\"}";
		}
		else if(1==dao.execute(Sql)){
			result="{\"result\":\"success\",\"text\":\"��ӳɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"���ʧ��\"}";
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
	public String deleteRoleMember(String DeleteId) {
		// TODO Auto-generated method stub
		String	Sql="delete from SYS_ROLE_MEMBER where user_id='"+DeleteId+"'";
		logger.debug(Sql);
		String	result="";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if(1==dao.execute(Sql)){
			result="{\"result\":\"succ\",\"text\":\"ɾ����ɫ��Ա�ɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"ɾ����ɫ��Աʧ��\"}";
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

	@Override
	public String bulkDeleteRoleMember(String DeleteIds) {
		// TODO Auto-generated method stub
		logger.debug(DeleteIds);
		//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
		String[] array=DeleteIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		String	Sql	=	"delete from SYS_ROLE_MEMBER where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" user_id='"+array[i]+"'";
		}
		logger.debug(Sql);
	
		if(1==dao.execute(Sql)){
			result="{\"result\":\"success\",\"text\":\"ɾ���ɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"ɾ��ʧ��\"}";
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
