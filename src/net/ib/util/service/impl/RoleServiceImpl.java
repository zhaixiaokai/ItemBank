/**
 * -------------------------------------------------------------------------------------
 * | 文件名：	RoleServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2012-12-17 下午3:21:34
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-17 下午3:21:34
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
 * 类名：net.ib.util.service.impl.RoleServiceImpl
 * </p>
 * <p>
 * 描述：系统角色管理接口
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
	 * (非-Javadoc)
	 * <p>名称: addRole</p>
	 * <p>说明: </p>
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

				// 判断教师姓名是否重复
				if (role_name.equals(RoleName)) {
					result = "{\"result\":\"succ\",\"text\":\"角色名称重复，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				// 判断教工号是否重复
				if (role_id.equals(RoleID)) {
					result = "{\"result\":\"succ\",\"text\":\"角色唯一标识符重复，请重新输入\"}";
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
			// 若此教师没有出现在已有的数据表中，则存储数据
			String sql = "insert into sys_role fields(name,role_id,explain) VALUES('"
					+ RoleName + "','" + RoleID + "','" + RoleExplain + "')";
			if (1 == daoImpl.execute(sql)) {
				result = "{\"result\":\"succ\",\"text\":\"角色添加成功\"}";
			} else {
				result = "{\"result\":\"succ\",\"text\":\"角色添加失败\"}";
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
	 * (非-Javadoc)
	 * <p>名称: deleteRole</p>
	 * <p>说明: 删除角色</p>
	 * @param RoleID
	 * @return
	 * @see net.ib.util.service.RoleService#deleteRole(java.lang.String)
	 */

	@Override
	public String deleteRole(String RoleID) {
		// TODO Auto-generated method stub
		String	Sql="delete from SYS_ROLE where role_id='"+RoleID+"'";//从数据库中该角色删除
		String  Sql_RoleMember="delete from SYS_ROLE_MEMBER where role_id='"+RoleID+"'";//从角色成员表中将属于该角色的成员全部释放
		logger.debug(Sql);
		logger.debug(Sql_RoleMember);
		String	result="";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if((1==dao.execute(Sql))&&(1==dao.execute(Sql_RoleMember))){
			result="{\"result\":\"succ\",\"text\":\"删除角色成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"删除角色失败\"}";
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
	
	
	 /* (非-Javadoc)
		 * <p>名称: UpdateRole</p>
		 * <p>说明: 更新角色信息</p>
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
					result="{\"result\":\"succ\",\"text\":\"修改角色信息成功\"}";
				}
				else{
					result="{\"result\":\"succ\",\"text\":\"修改角色信息失败\"}";
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
						logger.info("修改角色信息过程中角色唯一标识符重复");
						result = "{\"result\":\"failedId\",\"text\":\"角色唯一标识符已经存在，请重新输入\"}";
						try {
							PrintWriter pw = response.getWriter();
							pw.print(result);
							pw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else if(TempName.equals(RoleName)){
						logger.info("修改角色信息过程中角色名称重复");
						result = "{\"result\":\"failedName\",\"text\":\"该角色名称已经存在，请重新输入\"}";
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
		 /* (非-Javadoc)
		 * <p>名称: UpdateRole</p>
		 * <p>说明: 批量删除角色信息</p>
		 * @return
		 * @see net.ib.util.service.RoleService#BulkDeleteRole(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
		 */
		public String BulkDeleteRole(String RoleIds) {
			// TODO Auto-generated method stub
			logger.debug(RoleIds);
			//收到的DelIds格式为   id1 id2 id3 id4 ....
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
				result="{\"result\":\"success\",\"text\":\"删除成功\"}";
			}
			else{
				result="{\"result\":\"failed\",\"text\":\"删除失败\"}";
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
