/**
 * -------------------------------------------------------------------------------------
 * | 文件名：	AuthorityServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2013-3-11 下午3:59:57
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-11 下午3:59:57
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
 * 类名：net.ib.util.service.impl.AuthorityServiceImpl
 * </p>
 * <p>
 * 描述：对指定的角色分配相应的权限
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

	// 按角色授权
	// 参数RoleId表示授权的角色的id，
	//参数FunctionIds表示所授权的功能的id
	//参数DeleteId表示页面上面未授权的id
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

		// 查询新添加的权限是否已经存在
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
		int flag=0;//表示添加数据是否有重复的状态变量，若果有重复则为1，否则为0
		// 如果有重复
		int count = 0;// 添加的功能数据中不重复数据的个数
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
		// 如果数据没有重复
		else {
			for (int i = 0; i < array.length; i++) {
				if (i != 0) {
					InsertSql += " union ";
				}
				InsertSql += " select '" + RoleId + "', '" + array[i]
						+ "' from dual ";
			}
		}
		
		//页面上面未点击的功能表示不给该角色分配这些功能，若这些数据在数据表中已经存在，则删除掉
		String DeleteSql="delete from SYS_PERM_SYS_ROLE where role_id='"+RoleId+"' and (";
		for(int i=0;i<DeleteArray.length;i++){
			if(i!=0){
				DeleteSql+=" or ";
			}
			DeleteSql+=" function_id='"+DeleteArray[i]+"' ";
		}
		DeleteSql+=" ) ";
		
		//数据有重复的情况下，在添加的数据中去除重复数据以后，将剩余的数据加入表中
		if (1==flag&&count!=0&&1 == dao.execute(InsertSql)) {
			result = "{\"result\":\"success\" , \"text\":\"权限分配成功\"}";
		}
		//数据没有重复
		else if(0==flag&&1==dao.execute(InsertSql)){
			result = "{\"result\":\"success\" , \"text\":\"权限分配成功\"}";
		}
		else {
			result = "{\"result\":\"success\", \"text\":\"权限分配失败\"}";
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

	
	
	// 按机构授权
	// 参数DepartmentId表示授权的机构的id
	//参数FunctionId表示授权的功能的id
	//参数DeleteId表示页面上面未授权的id
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

		// 查询新添加的权限是否已经存在
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
		int flag=0;//表示添加数据是否有重复的状态变量，若果有重复则为1，否则为0
		// 如果有重复
		int count = 0;// 添加的功能数据中不重复数据的个数
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
		// 如果数据没有重复
		else {
			for (int i = 0; i < array.length; i++) {
				if (i != 0) {
					InsertSql += " union ";
				}
				InsertSql += " select '" + DepartmentId + "', '" + array[i]
						+ "' from dual ";
			}
		}
		
		//页面上面未点击的功能表示不给该机构分配这些功能，若这些数据在数据表中已经存在，则删除掉
		String DeleteSql="delete from SYS_PERM_SYS_DEPARTMENT where department_id ='"+DepartmentId+"' and ( ";
		for(int i=0;i<DeleteArray.length;i++){
			if(i!=0){
				DeleteSql+=" or ";
			}
			DeleteSql+=" function_id='"+DeleteArray[i]+"' ";
		}
		DeleteSql+=" ) ";
		
		//数据有重复的情况下，在添加的数据中去除重复数据以后，将剩余的数据加入表中
		
		if (1==flag&& count!=0&&1 == dao.execute(InsertSql)) {
			result = "{\"result\":\"success\" , \"text\":\"权限分配成功\"}";
		}
		//数据没有重复
		else if(0==flag&&1==dao.execute(InsertSql)){
			result = "{\"result\":\"success\" , \"text\":\"权限分配成功\"}";
		}
		else {
			result = "{\"result\":\"success\", \"text\":\"权限分配失败\"}";
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

	// 按用户授权
	// 参数MemberType表示授权的机构的id
	// 参数UserNumber表示授权用户的教工号或者学号
	//参数FunctionId表示授权的功能的id
	//参数DeleteId表示页面上面未授权的id
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

			// 查询给该用户新添加的权限是否已经存在
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
			// 根据查询结果，将符合条件的功能添加给指定的用户
			String InsertSql = "insert into SYS_PERM_SYS_USER fields(user_id,function_id) ";		
			int flag=0;//表示数据是否有重复的状态变量，如果有重复则为1,否则为0
			// 如果有重复
			int count = 0;// 添加的功能数据中不重复数据的个数
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
			// 如果数据没有重复
			else {
				for (int i = 0; i < array.length; i++) {
					if (i != 0) {
						InsertSql += " union ";
					}
					InsertSql += " select '" + UserId + "', '" + array[i]
							+ "' from dual ";
				}
			}
			//页面上面未点击的功能表示不给该用户分配这些功能，若这些数据在数据表中已经存在，则删除掉
			String DeleteSql="delete from SYS_PERM_SYS_DEPARTMENT where department_id ='"+UserId+"' and ( ";
			for(int i=0;i<DeleteArray.length;i++){
				if(i!=0){
					DeleteSql+=" or ";
				}
				DeleteSql+=" function_id='"+DeleteArray[i]+"' ";
			}
			DeleteSql+=" ) ";
			
			//数据有重复的情况下，在添加的数据中去除重复数据以后，将剩余的数据加入表中
			if (1==flag&&count!=0 && 1 == dao.execute(InsertSql)) {
				result = "{\"result\":\"success\" , \"text\":\"按用户权限分配成功\"}";
			} 
			//数据没有重复
			else if(0==flag&& 1==dao.execute(InsertSql)){
				result = "{\"result\":\"success\" , \"text\":\"按用户权限分配成功\"}";
			}
			else {
				result = "{\"result\":\"fail\", \"text\":\"按用户权限分配失败\"}";
			}
			//将没有分配给用户的权限从数据表中删除
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
	
	

	//按用户类型授权
	//参数MemberType表示授权的机构的id
	//参数FunctionId表示授权的功能的id
	//参数DeleteId表示页面上面未授权的id
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

		// 查询给该该成员类型新添加的权限是否已经存在
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

		// 根据查询结果，将符合条件的功能添加给指定的成员类型
		String InsertSql = "insert into SYS_PERM_SYS_USERTYPE fields(usertype_id,function_id) ";
		// 如果成员类型的权限表中数据有重复
		int flag=0;//表示添加的数据是否有重复的状态，若没有重复则为0，若重复则为1
		int count = 0;// 添加数据中不与数据表中数据重复烦的个数
		
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
		// 如果数据没有重复
		else {
			for (int i = 0; i < array.length; i++) {
				if (i != 0) {
					InsertSql += " union ";
				}
				InsertSql += " select '" + UserType + "', '" + array[i]
						+ "' from dual ";
			}
		}

		
		//页面上面未点击的功能表示不给该成员类型分配这些功能，若这些数据在数据表中已经存在，则删除掉
		String DeleteSql="delete from SYS_PERM_SYS_USERTYPE where usertype_id ='"+UserType+"' and ( ";
		for(int i=0;i<DeleteArray.length;i++){
			if(i!=0){
				DeleteSql+=" or ";
			}
			DeleteSql+=" function_id='"+DeleteArray[i]+"' ";
		}
		DeleteSql+=" ) ";

		//如果添加的权限有重复，而且至少有一项权限是不存在于已有数据表中的
		if (1==flag&&count!=0&& 1 == dao.execute(InsertSql)) {
			result = "{\"result\":\"success\" , \"text\":\"按成员类型权限分配成功\"}";
		} 
		//数据没有重复
		else if (0==flag&& 1 == dao.execute(InsertSql)) {
			result = "{\"result\":\"success\" , \"text\":\"按成员类型权限分配成功\"}";
		}
		else {
			result = "{\"result\":\"fail\", \"text\":\"按成员类型权限分配失败\"}";
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

	
	//对输入的用户的学号或者教工号进行检查合法性
	// 参数MemberType表示授权的机构的id
	// 参数UserNumber表示授权用户的教工号或者学号
	@Override
	public String CheckUserNumber(String MemberType, String UserNumber) {
		// TODO Auto-generated method stub
		
		logger.debug(MemberType);
		logger.debug(UserNumber);
		String result="";
		// 查询用户是否存在，若存在则获取其userid
		String GetUserIdSql = "";
		if (MemberType.equals("teacher")) {
			GetUserIdSql = "select sut.user_id from SYS_USER_TEACHER sut where school_id='"
							+ UserNumber + "' ";
		} else if (MemberType.equals("student")) {
			GetUserIdSql = "select sus.user_id from SYS_USER_STUDENT sus where school_id='"
							+ UserNumber + "'";
		}
		List<Map> GetUserId = dao.executeQuery(GetUserIdSql);
		//输入的用户不存在于系统中
		if (GetUserId.size() == 0) {
			result = "该用户不存在！";
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
