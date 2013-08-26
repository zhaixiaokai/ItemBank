/**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	RoleServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2012-12-17 ����3:21:34
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-17 ����3:21:34
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import net.ib.util.service.RoleService;
import net.ib.util.service.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.dao.DaoImpl;

/**
 * <p>
 * ������net.ib.util.service.impl.RoleServiceImpl
 * </p>
 * <p>
 * ������ϵͳ��ɫ����ӿ�
 * </p>
 * <p>
 * </p>
 */
public class RoleServiceImpl implements RoleService {
	private static Logger logger = Logger.getLogger(DataDicServiceImpl.class);
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
	
	/*
	 * (��-Javadoc)
	 * <p>����: addRole</p>
	 * <p>˵��: </p>
	 * @param RoleName
	 * @param RoleID
	 * @param RoleExplain
	 * @return
	 * @see net.ib.util.service.RoleService#addRole(java.lang.String, java.lang.String, java.lang.String)
	 */
	
	public String addRole(String RoleName, String RoleID, String RoleExplain) {
		String result;
		DaoImpl daoImpl = new DaoImpl();
		List<Map> list = (List<Map>) daoImpl
				.executeQuery("select * from sys_role where name='" + RoleName
						+ "' or role_id='" + RoleID + "'");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				String role_name = (String) list.get(i).get("name");
				String role_id = (String) list.get(i).get("role_id");

				// �жϽ�ʦ�����Ƿ��ظ�
				if (role_name.equals(RoleName)) {
					result = "{\"result\":\"succ\",\"text\":\"��ɫ�����ظ�������������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// �жϽ̹����Ƿ��ظ�
				if (role_id.equals(RoleID)) {
					result = "{\"result\":\"succ\",\"text\":\"��ɫΨһ��ʶ���ظ�������������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			// ���˽�ʦû�г��������е����ݱ��У���洢����
			String sql = "insert into sys_role fields(name,role_id,explain) VALUES('"
					+ RoleName + "','" + RoleID + "','" + RoleExplain + "')";
			if (1 == daoImpl.execute(sql)) {
				result = "{\"result\":\"succ\",\"text\":\"��ɫ��ӳɹ�\"}";
			} else {
				result = "{\"result\":\"succ\",\"text\":\"��ɫ���ʧ��\"}";
			}
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
	
	/*
	 * (��-Javadoc)
	 * <p>����: deleteRole</p>
	 * <p>˵��: ɾ����ɫ</p>
	 * @param RoleID
	 * @return
	 * @see net.ib.util.service.RoleService#deleteRole(java.lang.String)
	 */

	@Override
	public String deleteRole(String RoleID) {
		// TODO Auto-generated method stub
		String	Sql="delete from SYS_ROLE where role_id='"+RoleID+"'";//�����ݿ��иý�ɫɾ��
		String  Sql_RoleMember="delete from SYS_ROLE_MEMBER where role_id='"+RoleID+"'";//�ӽ�ɫ��Ա���н����ڸý�ɫ�ĳ�Աȫ���ͷ�
		logger.debug(Sql);
		logger.debug(Sql_RoleMember);
		String	result="";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if((1==dao.execute(Sql))&&(1==dao.execute(Sql_RoleMember))){
			result="{\"result\":\"succ\",\"text\":\"ɾ����ɫ�ɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"ɾ����ɫʧ��\"}";
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
	
	
	 /* (��-Javadoc)
		 * <p>����: UpdateRole</p>
		 * <p>˵��: ���½�ɫ��Ϣ</p>
		 * @param OldRoleId
		 * @param RoleId
		 * @param RoleName
		 * @param RoleExplain
		 * @return
		 * @see net.ib.util.service.RoleService#UpdateRole(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
		 */
		@Override
		public String updateRole(String OldRoleId, String RoleId, String RoleName,
				String RoleExplain) {
			// TODO Auto-generated method stub
			String sql = "select role_id,NAME from SYS_ROLE where (role_id='"
					+ RoleId + "' or NAME='" + RoleName + "') and role_id!='"+OldRoleId+"'";
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			List<Map>	list	=	dao.executeQuery(sql);
			String	result="";
			if (list.size() == 0) {
				sql = "update SYS_ROLE set role_id='" + RoleId
						+ "',NAME='" + RoleName + "',EXPLAIN='"
						+ RoleExplain
						+ "' where role_id='" + OldRoleId + "'";
				if(1==dao.execute(sql)){
					result="{\"result\":\"succ\",\"text\":\"�޸Ľ�ɫ��Ϣ�ɹ�\"}";
				}
				else{
					result="{\"result\":\"succ\",\"text\":\"�޸Ľ�ɫ��Ϣʧ��\"}";
				}
				try {
					PrintWriter pw = response.getWriter();
					pw.print(result);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
				String	TempId="";
				String	TempName="";
				Map map = new HashMap();
				for(int	i=0;i<list.size();i++){
					map=list.get(i);
					TempId	=	(String) map.get("role_id");
					TempName=	(String) map.get("name");
					logger.debug(TempName);
					logger.debug(TempId);
					if(TempId.equals(RoleId)){
						logger.info("�޸Ľ�ɫ��Ϣ�����н�ɫΨһ��ʶ���ظ�");
						result = "{\"result\":\"failedId\",\"text\":\"��ɫΨһ��ʶ���Ѿ����ڣ�����������\"}";
						try {
							PrintWriter pw = response.getWriter();
							pw.print(result);
							pw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else if(TempName.equals(RoleName)){
						logger.info("�޸Ľ�ɫ��Ϣ�����н�ɫ�����ظ�");
						result = "{\"result\":\"failedName\",\"text\":\"�ý�ɫ�����Ѿ����ڣ�����������\"}";
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
			
			return null;
		}
		 /* (��-Javadoc)
		 * <p>����: UpdateRole</p>
		 * <p>˵��: ����ɾ����ɫ��Ϣ</p>
		 * @return
		 * @see net.ib.util.service.RoleService#BulkDeleteRole(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
		 */
		public String BulkDeleteRole(String RoleIds) {
			// TODO Auto-generated method stub
			logger.debug(RoleIds);
			//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
			String[] array=RoleIds.split(" ");
			logger.debug(array);
			logger.debug(array.length);
			String	Sql	=	"delete from SYS_ROLE where";
			String Sql_RoleMember	=	"delete from SYS_ROLE_MEMBER where ";
			String	result="";
			for(int	i=0;i<array.length;i++){
				if(i!=0){
					Sql+=" or";
					Sql_RoleMember+=" or ";
				}
				Sql+=" role_id='"+array[i]+"'";
				Sql_RoleMember+=" role_id= '"+array[i]+"'";
			}
			logger.debug(Sql);
			if((1==dao.execute(Sql))&&(1==dao.execute(Sql_RoleMember))){
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
