/**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	AuthorityServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2013-3-11 ����3:59:57
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-11 ����3:59:57
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import org.apache.commons.validator.Var;
import org.apache.log4j.Logger;

import net.ib.util.dao.Dao;
import net.ib.util.service.AuthorityService;
import net.ib.util.service.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.service.AuthorityService;

/**
 * <p>
 * ������net.ib.util.service.impl.AuthorityServiceImpl
 * </p>
 * <p>
 * ��������ָ���Ľ�ɫ������Ӧ��Ȩ��
 * </p>
 * <p>
 * </p>
 */
public class AuthorityServiceImpl implements AuthorityService {
	private static Logger logger = Logger.getLogger(TeacherServiceImpl.class);
	Dao dao;
	Service service;

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

	// ����ɫ��Ȩ
	// ����RoleId��ʾ��Ȩ�Ľ�ɫ��id��
	//����FunctionIds��ʾ����Ȩ�Ĺ��ܵ�id
	//����DeleteId��ʾҳ������δ��Ȩ��id
	@Override
	public String RoleAuthority(String RoleId, String FunctionIds,String DeleteId) {

		// TODO Auto-generated method stub
		logger.debug(FunctionIds);
		String[] array = FunctionIds.split(" ");
		logger.debug(array);
		logger.debug(RoleId);
		logger.debug(DeleteId);
		String[] DeleteArray = DeleteId.split(" ");
		logger.debug(array.length);
		String result = "";

		// ��ѯ����ӵ�Ȩ���Ƿ��Ѿ�����
		String QuerySql = "Select * from SYS_PERM_SYS_ROLE where role_id='"
				+ RoleId + "' and (";
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				QuerySql += " or ";
			}
			QuerySql += " function_id='" + array[i] + "' ";
		}
		QuerySql += " ) ";
		List<Map> QueryList = dao.executeQuery(QuerySql);
		String InsertSql = "insert into SYS_PERM_SYS_ROLE fields(role_id,function_id) ";		
		int flag=0;//��ʾ��������Ƿ����ظ���״̬�������������ظ���Ϊ1������Ϊ0
		// ������ظ�
		int count = 0;// ��ӵĹ��������в��ظ����ݵĸ���
		if (QueryList.size() != 0) {
			flag=1;
			for (int i = 0; i < array.length; i++) {
				Map QueryMap = new HashMap();
				int k;
				for (k = 0; k < QueryList.size(); k++) {
					QueryMap = QueryList.get(k);
					String Role_Id = (String) QueryMap.get("role_id");
					String Function_Id = (String) QueryMap.get("function_id");
					if (Role_Id.equals(RoleId) && Function_Id.equals(array[i]))
						break;
				}

				if (k >= QueryList.size()) {
					if (count != 0)
						InsertSql += " union ";

					InsertSql += " select '" + RoleId + "','" + array[i]
							+ "' from dual";
					count++;
				}
			}

		}
		// �������û���ظ�
		else {
			for (int i = 0; i < array.length; i++) {
				if (i != 0) {
					InsertSql += " union ";
				}
				InsertSql += " select '" + RoleId + "', '" + array[i]
						+ "' from dual ";
			}
		}
		
		//ҳ������δ����Ĺ��ܱ�ʾ�����ý�ɫ������Щ���ܣ�����Щ���������ݱ����Ѿ����ڣ���ɾ����
		String DeleteSql="delete from SYS_PERM_SYS_ROLE where role_id='"+RoleId+"' and (";
		for(int i=0;i<DeleteArray.length;i++){
			if(i!=0){
				DeleteSql+=" or ";
			}
			DeleteSql+=" function_id='"+DeleteArray[i]+"' ";
		}
		DeleteSql+=" ) ";
		
		//�������ظ�������£�����ӵ�������ȥ���ظ������Ժ󣬽�ʣ������ݼ������
		if (1==flag&&count!=0&&1 == dao.execute(InsertSql)) {
			result = "{\"result\":\"success\" , \"text\":\"Ȩ�޷���ɹ�\"}";
		}
		//����û���ظ�
		else if(0==flag&&1==dao.execute(InsertSql)){
			result = "{\"result\":\"success\" , \"text\":\"Ȩ�޷���ɹ�\"}";
		}
		else {
			result = "{\"result\":\"success\", \"text\":\"Ȩ�޷���ʧ��\"}";
		}
		dao.execute(DeleteSql);
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

	
	
	// ��������Ȩ
	// ����DepartmentId��ʾ��Ȩ�Ļ�����id
	//����FunctionId��ʾ��Ȩ�Ĺ��ܵ�id
	//����DeleteId��ʾҳ������δ��Ȩ��id
	@Override
	public String DepartmentAuthority(String DepartmentId, String FunctionIds, String DeleteId) {
		// TODO Auto-generated method stub

		logger.debug(FunctionIds);
		String[] array = FunctionIds.split(" ");
		logger.debug(array);
		logger.debug(DepartmentId);
		logger.debug(DeleteId);
		String[] DeleteArray = DeleteId.split(" ");
		logger.debug(array.length);
		String result = "";

		// ��ѯ����ӵ�Ȩ���Ƿ��Ѿ�����
		String QuerySql = "Select * from SYS_PERM_SYS_DEPARTMENT where department_id='"
				+ DepartmentId + "' and (";
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				QuerySql += " or ";
			}
			QuerySql += " function_id='" + array[i] + "' ";
		}
		QuerySql += " ) ";
		List<Map> QueryList = dao.executeQuery(QuerySql);
		String InsertSql = "insert into SYS_PERM_SYS_DEPARTMENT fields(department_id,function_id) ";	
		int flag=0;//��ʾ��������Ƿ����ظ���״̬�������������ظ���Ϊ1������Ϊ0
		// ������ظ�
		int count = 0;// ��ӵĹ��������в��ظ����ݵĸ���
		if (QueryList.size() != 0) {
			flag=1;
			for (int i = 0; i < array.length; i++) {
				Map QueryMap = new HashMap();
				int k;
				for (k = 0; k < QueryList.size(); k++) {
					QueryMap = QueryList.get(k);
					String Department_Id = (String) QueryMap
							.get("department_id");
					String Function_Id = (String) QueryMap.get("function_id");
					if (Department_Id.equals(DepartmentId)
							&& Function_Id.equals(array[i]))
						break;
				}

				if (k >= QueryList.size()) {
					if (count != 0)
						InsertSql += " union ";

					InsertSql += " select '" + DepartmentId + "','" + array[i]
							+ "' from dual";
					count++;
				}
			}
		}
		// �������û���ظ�
		else {
			for (int i = 0; i < array.length; i++) {
				if (i != 0) {
					InsertSql += " union ";
				}
				InsertSql += " select '" + DepartmentId + "', '" + array[i]
						+ "' from dual ";
			}
		}
		
		//ҳ������δ����Ĺ��ܱ�ʾ�����û���������Щ���ܣ�����Щ���������ݱ����Ѿ����ڣ���ɾ����
		String DeleteSql="delete from SYS_PERM_SYS_DEPARTMENT where department_id ='"+DepartmentId+"' and ( ";
		for(int i=0;i<DeleteArray.length;i++){
			if(i!=0){
				DeleteSql+=" or ";
			}
			DeleteSql+=" function_id='"+DeleteArray[i]+"' ";
		}
		DeleteSql+=" ) ";
		
		//�������ظ�������£�����ӵ�������ȥ���ظ������Ժ󣬽�ʣ������ݼ������
		
		if (1==flag&& count!=0&&1 == dao.execute(InsertSql)) {
			result = "{\"result\":\"success\" , \"text\":\"Ȩ�޷���ɹ�\"}";
		}
		//����û���ظ�
		else if(0==flag&&1==dao.execute(InsertSql)){
			result = "{\"result\":\"success\" , \"text\":\"Ȩ�޷���ɹ�\"}";
		}
		else {
			result = "{\"result\":\"success\", \"text\":\"Ȩ�޷���ʧ��\"}";
		}
		dao.execute(DeleteSql);
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

	// ���û���Ȩ
	// ����MemberType��ʾ��Ȩ�Ļ�����id
	// ����UserNumber��ʾ��Ȩ�û��Ľ̹��Ż���ѧ��
	//����FunctionId��ʾ��Ȩ�Ĺ��ܵ�id
	//����DeleteId��ʾҳ������δ��Ȩ��id
	@Override
	public String UserAuthority(String UserId,
			String FunctionIds,String DeleteId) {
		// TODO Auto-generated method stub
		logger.debug(FunctionIds);
		String[] array = FunctionIds.split(" ");
		logger.debug(array);
		logger.debug(UserId);
		logger.debug(DeleteId);
		String[] DeleteArray=DeleteId.split(" ");
		logger.debug(array.length);
		String result = "";

			// ��ѯ�����û�����ӵ�Ȩ���Ƿ��Ѿ�����
			String QuerySql = "select * from  SYS_PERM_SYS_USER where user_id='"
					+ UserId + "' and ( ";
			for (int i = 0; i < array.length; i++) {
				if (i != 0) {
					QuerySql += " or ";
				}
				QuerySql += " function_id='" + array[i] + "' ";
			}
			QuerySql += " ) ";
			List<Map> QueryList = dao.executeQuery(QuerySql);
			// ���ݲ�ѯ����������������Ĺ�����Ӹ�ָ�����û�
			String InsertSql = "insert into SYS_PERM_SYS_USER fields(user_id,function_id) ";		
			int flag=0;//��ʾ�����Ƿ����ظ���״̬������������ظ���Ϊ1,����Ϊ0
			// ������ظ�
			int count = 0;// ��ӵĹ��������в��ظ����ݵĸ���
			if (QueryList.size() != 0) {
				flag=1;
				for (int i = 0; i < array.length; i++) {
					Map QueryMap = new HashMap();
					int k;
					for (k = 0; k < QueryList.size(); k++) {
						QueryMap = QueryList.get(k);
						String User_Id = (String) QueryMap.get("user_id");
						String Function_Id = (String) QueryMap
								.get("function_id");
						if (User_Id.equals(UserId)
								&& Function_Id.equals(array[i]))
							break;
					}

					if (k >= QueryList.size()) {
						if (count != 0)
							InsertSql += " union ";

						InsertSql += " select '" + UserId + "','" + array[i]
								+ "' from dual";
						count++;

					}

				}

			}
			// �������û���ظ�
			else {
				for (int i = 0; i < array.length; i++) {
					if (i != 0) {
						InsertSql += " union ";
					}
					InsertSql += " select '" + UserId + "', '" + array[i]
							+ "' from dual ";
				}
			}
			//ҳ������δ����Ĺ��ܱ�ʾ�������û�������Щ���ܣ�����Щ���������ݱ����Ѿ����ڣ���ɾ����
			String DeleteSql="delete from SYS_PERM_SYS_DEPARTMENT where department_id ='"+UserId+"' and ( ";
			for(int i=0;i<DeleteArray.length;i++){
				if(i!=0){
					DeleteSql+=" or ";
				}
				DeleteSql+=" function_id='"+DeleteArray[i]+"' ";
			}
			DeleteSql+=" ) ";
			
			//�������ظ�������£�����ӵ�������ȥ���ظ������Ժ󣬽�ʣ������ݼ������
			if (1==flag&&count!=0 && 1 == dao.execute(InsertSql)) {
				result = "{\"result\":\"success\" , \"text\":\"���û�Ȩ�޷���ɹ�\"}";
			} 
			//����û���ظ�
			else if(0==flag&& 1==dao.execute(InsertSql)){
				result = "{\"result\":\"success\" , \"text\":\"���û�Ȩ�޷���ɹ�\"}";
			}
			else {
				result = "{\"result\":\"fail\", \"text\":\"���û�Ȩ�޷���ʧ��\"}";
			}
			//��û�з�����û���Ȩ�޴����ݱ���ɾ��
			dao.execute(DeleteSql);
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
	
	

	//���û�������Ȩ
	//����MemberType��ʾ��Ȩ�Ļ�����id
	//����FunctionId��ʾ��Ȩ�Ĺ��ܵ�id
	//����DeleteId��ʾҳ������δ��Ȩ��id
	@Override
	public String UserMemberAuthority(String MemberType, String FunctionIds,String DeleteId) {
		// TODO Auto-generated method stub
		logger.debug(FunctionIds);
		String[] array = FunctionIds.split(" ");
		logger.debug(array);
		logger.debug(MemberType);
		logger.debug(DeleteId);
		String[] DeleteArray=DeleteId.split(" ");
		logger.debug(array.length);
		String result = "";

		String UserType = "";
		if (MemberType.equals("teacher")) {
			UserType = "1";
		} else if (MemberType.equals("student")) {
			UserType = "2";
		}

		// ��ѯ���øó�Ա��������ӵ�Ȩ���Ƿ��Ѿ�����
		String QuerySql = "select * from  SYS_PERM_SYS_USERTYPE where usertype_id='"
				+ UserType + "' and ( ";
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				QuerySql += " or ";			
			}
			QuerySql += " function_id='" + array[i] + "' ";		
		}
		QuerySql += " ) ";
		List<Map> QueryList = dao.executeQuery(QuerySql);

		// ���ݲ�ѯ����������������Ĺ�����Ӹ�ָ���ĳ�Ա����
		String InsertSql = "insert into SYS_PERM_SYS_USERTYPE fields(usertype_id,function_id) ";
		// �����Ա���͵�Ȩ�ޱ����������ظ�
		int flag=0;//��ʾ��ӵ������Ƿ����ظ���״̬����û���ظ���Ϊ0�����ظ���Ϊ1
		int count = 0;// ��������в������ݱ��������ظ����ĸ���
		
		if (QueryList.size() != 0) {
			flag=1;
			for (int i = 0; i < array.length; i++) {
				Map QueryMap = new HashMap();
				int k;
				for (k = 0; k < QueryList.size(); k++) {
					QueryMap = QueryList.get(k);
					String User_Type = (String) QueryMap.get("usertype_id");
					String Function_Id = (String) QueryMap.get("function_id");
					if (User_Type.equals(UserType)
							&& Function_Id.equals(array[i]))
						break;
				}

				if (k >= QueryList.size()) {
					if (count != 0)
						InsertSql += " union ";

					InsertSql += " select '" + UserType + "','" + array[i]
							+ "' from dual";
					count++;

				}

			}

		}
		// �������û���ظ�
		else {
			for (int i = 0; i < array.length; i++) {
				if (i != 0) {
					InsertSql += " union ";
				}
				InsertSql += " select '" + UserType + "', '" + array[i]
						+ "' from dual ";
			}
		}

		
		//ҳ������δ����Ĺ��ܱ�ʾ�����ó�Ա���ͷ�����Щ���ܣ�����Щ���������ݱ����Ѿ����ڣ���ɾ����
		String DeleteSql="delete from SYS_PERM_SYS_USERTYPE where usertype_id ='"+UserType+"' and ( ";
		for(int i=0;i<DeleteArray.length;i++){
			if(i!=0){
				DeleteSql+=" or ";
			}
			DeleteSql+=" function_id='"+DeleteArray[i]+"' ";
		}
		DeleteSql+=" ) ";

		//�����ӵ�Ȩ�����ظ�������������һ��Ȩ���ǲ��������������ݱ��е�
		if (1==flag&&count!=0&& 1 == dao.execute(InsertSql)) {
			result = "{\"result\":\"success\" , \"text\":\"����Ա����Ȩ�޷���ɹ�\"}";
		} 
		//����û���ظ�
		else if (0==flag&& 1 == dao.execute(InsertSql)) {
			result = "{\"result\":\"success\" , \"text\":\"����Ա����Ȩ�޷���ɹ�\"}";
		}
		else {
			result = "{\"result\":\"fail\", \"text\":\"����Ա����Ȩ�޷���ʧ��\"}";
		}
		dao.execute(DeleteSql);
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
	public String PresentRoleAuthorityGet(String RoleId) {
		// TODO Auto-generated method stub
		logger.debug(RoleId);
		String Sql="select function_id from sys_perm_sys_role where role_id='"+RoleId+"'";
		List<Map> list	=	new	ArrayList();
		list	=	dao.executeQuery(Sql);
		Map map = new HashMap();
		String presentAuthorityId="";
		for(int i=0;i<list.size();i++){
			map=list.get(i);
			String TempId=(String) map.get("function_id");
			if(i!=0){
				presentAuthorityId+=",";
			}
			presentAuthorityId+=TempId;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(presentAuthorityId);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//��������û���ѧ�Ż��߽̹��Ž��м��Ϸ���
	// ����MemberType��ʾ��Ȩ�Ļ�����id
	// ����UserNumber��ʾ��Ȩ�û��Ľ̹��Ż���ѧ��
	@Override
	public String CheckUserNumber(String MemberType, String UserNumber) {
		// TODO Auto-generated method stub
		
		logger.debug(MemberType);
		logger.debug(UserNumber);
		String result="";
		// ��ѯ�û��Ƿ���ڣ����������ȡ��userid
		String GetUserIdSql = "";
		if (MemberType.equals("teacher")) {
			GetUserIdSql = "select sut.user_id from SYS_USER_TEACHER sut where school_id='"
							+ UserNumber + "' ";
		} else if (MemberType.equals("student")) {
			GetUserIdSql = "select sus.user_id from SYS_USER_STUDENT sus where school_id='"
							+ UserNumber + "'";
		}
		List<Map> GetUserId = dao.executeQuery(GetUserIdSql);
		//������û���������ϵͳ��
		if (GetUserId.size() == 0) {
			result = "���û������ڣ�";
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		else{
			Map UserIdMap = new HashMap();
			UserIdMap = GetUserId.get(0);
			String UserId = (String) UserIdMap.get("user_id");
			result=UserId;
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
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

	@Override
	public String PresentUserAuthority(String UserId) {
		// TODO Auto-generated method stub
				logger.debug(UserId);
				String Sql="select function_id from sys_perm_sys_user where user_id='"+UserId+"'";
				List<Map> list	=	new	ArrayList();
				list	=	dao.executeQuery(Sql);
				Map map = new HashMap();
				String presentAuthorityId="";
				for(int i=0;i<list.size();i++){
					map=list.get(i);
					String TempId=(String) map.get("function_id");
					if(i!=0){
						presentAuthorityId+=",";
					}
					presentAuthorityId+=TempId;
				}
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				try {
					PrintWriter pw = response.getWriter();
					pw.print(presentAuthorityId);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
	}

	@Override
	public String PresentDepartmentAuthority(String DepartmentId) {
		// TODO Auto-generated method stub
		logger.debug(DepartmentId);
		String Sql="select function_id from sys_perm_sys_department where department_id='"+DepartmentId+"'";
		List<Map> list	=	new	ArrayList();
		list	=	dao.executeQuery(Sql);
		Map map = new HashMap();
		String presentAuthorityId="";
		for(int i=0;i<list.size();i++){
			map=list.get(i);
			String TempId=(String) map.get("function_id");
			if(i!=0){
				presentAuthorityId+=",";
			}
			presentAuthorityId+=TempId;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(presentAuthorityId);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String PresentUserTypeAuthority(String MemberType) {
		// TODO Auto-generated method stub
		logger.debug(MemberType);
		
		String UserType = "";
		if (MemberType.equals("teacher")) {
			UserType = "1";
		} else if (MemberType.equals("student")) {
			UserType = "2";
		}
		String Sql="select function_id from sys_perm_sys_usertype where usertype_id='"+UserType+"'";
		List<Map> list	=	new	ArrayList();
		list	=	dao.executeQuery(Sql);
		Map map = new HashMap();
		String presentAuthorityId="";
		for(int i=0;i<list.size();i++){
			map=list.get(i);
			String TempId=(String) map.get("function_id");
			if(i!=0){
				presentAuthorityId+=",";
			}
			presentAuthorityId+=TempId;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(presentAuthorityId);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
