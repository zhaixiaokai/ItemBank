
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	RoleMemberServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2013-2-28 下午2:51:53
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-2-28 下午2:51:53
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
 * <p>类名：net.ib.util.service.impl.RoleMemberServiceImpl </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
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
		//收到的DelIds格式为   id1 id2 id3 id4 ....
		String[] array=BulkMemberId.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		// 角色成员查重
		String QuerySql="select * from SYS_ROLE_MEMBER where (role_id='"+RoleListId+"') and ( ";
		for(int i=0;i<array.length;i++){
			if(i!=0){
				QuerySql+=" or ";
			}
			QuerySql+=" user_id = '"+array[i]+"' ";
		}
		QuerySql+=" ) ";
		List<Map> QueryList=dao.executeQuery(QuerySql);
		
		//根据前台选择的成员将符合条件的成员加入表中
		
		//Sql表示将符合条件的数据插入数据库的语句
		String	Sql	=	"insert into SYS_ROLE_MEMBER fields (role_id,user_id) ";
		String	result="";
		
		//假若有数据重复
		int flag = 0;// 表示不重复数据的个数
		int count=0;//表示重复数据的个数
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
		// 没有数据重复，则将数据直接插入表中
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
			result="{\"result\":\"success\",\"text\":\"添加的成员全部已经属于该角色\"}";
		}
		else if(1==dao.execute(Sql)){
			result="{\"result\":\"success\",\"text\":\"添加成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"添加失败\"}";
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
			result="{\"result\":\"succ\",\"text\":\"删除角色成员成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"删除角色成员失败\"}";
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
		//收到的DelIds格式为   id1 id2 id3 id4 ....
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
