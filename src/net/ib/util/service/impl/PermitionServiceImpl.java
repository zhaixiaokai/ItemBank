
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	PermitionServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-7-27 下午3:30:53
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-7-27 下午3:30:53
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.commons.collections.CollectionUtils;

import net.ib.config.SessionData;
import net.ib.util.dao.Dao;
import net.ib.util.dao.DaoImpl;
import net.ib.util.service.PermitionService;
import net.ib.util.service.Service;


  /**
 * <p>类名：net.ib.util.service.impl.PermitionServiceImpl </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class PermitionServiceImpl implements PermitionService {

	private static Logger logger = Logger.getLogger(PermitionServiceImpl.class);
	private String userName;
	private String userType;
	private String userOrg;
	private String schoolId;
	private String userId;
	private List<String> userRole;
	private Dao	dao	=	new	DaoImpl();
	private Service service = new ServiceImpl();
	/* (非-Javadoc)
	 * <p>名称: getFirstLevelPerm</p>
	 * <p>说明: </p>
	 * @return
	 * @see net.ib.util.service.PermitionService#getFirstLevelPerm()
	 */
	@Override
	public Map<Object, Object> getFirstLevelPerm() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非-Javadoc)
	 * <p>名称: getSecondLevelPerm</p>
	 * <p>说明: </p>
	 * @return
	 * @see net.ib.util.service.PermitionService#getSecondLevelPerm()
	 */
	@Override
	public Map<Object, Object> getSecondLevelPerm() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非-Javadoc)
	 * <p>名称: getThirdLevelPerm</p>
	 * <p>说明: </p>
	 * @return
	 * @see net.ib.util.service.PermitionService#getThirdLevelPerm()
	 */
	@Override
	public Map<Object, Object> getThirdLevelPerm() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非-Javadoc)
	 * <p>名称: getAllLevelPerm</p>
	 * <p>说明: </p>
	 * @return
	 * @see net.ib.util.service.PermitionService#getAllLevelPerm()
	 */
	@Override
	public Map<Object, Object> getAllLevelPerm(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		return getUserInfo(request);
	}

	@SuppressWarnings("unchecked")
	private Map<Object,Object> getUserInfo(HttpServletRequest request){
		HttpSession	session	=	request.getSession();
		logger.debug("session-------"+session);
		this.userId=(String) session.getAttribute(SessionData.SESSION_USER_ID);
		this.schoolId=(String)session.getAttribute(SessionData.SESSION_SCHOOL_ID);
		this.userName=(String) session.getAttribute(SessionData.SESSION_USERNAME);
		this.userOrg=(String) session.getAttribute(SessionData.SESSION_ORG);
		this.userType=(String) session.getAttribute(SessionData.SESSION_USERTYPE);
		this.userRole=(List<String>) session.getAttribute(SessionData.SESSION_ROLE);
		
		logger.debug("session中获取到[用户ID]				："+userId);
		logger.debug("session中获取到[用户学号/教工号]		："+schoolId);
		logger.debug("session中获取到[用户名]				："+userName);
		logger.debug("session中获取到[用户部门]				："+userOrg);
		logger.debug("session中获取到[用户类型]				："+userType);
		logger.debug("session中获取到[角色列表]				："+userRole);
		
		Map<Object,Object> userIdPerm	=	getFunPermByUsrId();
		Map<Object,Object> userOrgPerm	=	getFucPermByOrg();
		Map<Object,Object> usrTypePerm	=	getFunPermByUsrType();
		Map<Object,Object> usrRolePerm	=	getFunPermByUsrRole();
		
		Map<Object,Object> perMap	=	new	HashMap();
		
		Iterator<?> it0 = userIdPerm.entrySet().iterator();
		for(int	j=0;j<userIdPerm.size();j++){
			Map.Entry entry = (Map.Entry) it0.next();
			perMap.put(entry.getKey(),entry.getValue());
		}
		Iterator<?> it1 = userOrgPerm.entrySet().iterator();
		for(int	j=0;j<userOrgPerm.size();j++){
			Map.Entry entry = (Map.Entry) it1.next();
			perMap.put(entry.getKey(),entry.getValue());
		}
		Iterator<?> it2 = usrTypePerm.entrySet().iterator();
		for(int	j=0;j<usrTypePerm.size();j++){
			Map.Entry entry = (Map.Entry) it2.next();
			perMap.put(entry.getKey(),entry.getValue());
		}
		Iterator<?> it3 = usrRolePerm.entrySet().iterator();
		for(int	j=0;j<usrRolePerm.size();j++){
			Map.Entry entry = (Map.Entry) it3.next();
			perMap.put(entry.getKey(),entry.getValue());
		}
		
		logger.debug(perMap);
		
		return perMap;
	}
	/**
	 * 
	 * <p>名称：getFunPermByUsrId</p>
	 * <p>说明：通过用户id获取用户权限</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：Map<Object,Object> 返回类型</p>
	 * <p>@return</p>
	 */
	private Map<Object,Object>	getFunPermByUsrId(){
		if(null==this.userId||"null".equals(this.userId)||"".equals(this.userId)){
			return new HashMap<Object,Object>();
		}
		Map<Object,Object> permMap	=	new HashMap<Object,Object>();
		String sql	=	"select user_id,function_id from sys_perm_sys_user where user_id='"+this.userId+"'";
		logger.debug("[执行sql：]"+sql);
		List<Map> permList	=	dao.executeQuery(sql);
		for(Map<?, ?> tmp:permList){
			permMap.put(tmp.get("function_id"),"1");
		}
		return permMap;
	}
	/**
	 * 
	 * <p>名称：getFucPermByOrg</p>
	 * <p>说明：通过用户部门获取用户部门权限</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：Map<Object,Object> 返回类型</p>
	 * <p>@return</p>
	 */
	private Map<Object,Object>	getFucPermByOrg(){
		if(null==this.userOrg||"null".equals(this.userOrg)||"".equals(this.userOrg)){
			return new HashMap<Object,Object>();
		}
		Map<Object,Object> permMap	=	new HashMap<Object,Object>();
		String sql	=	"select department_id,function_id from sys_perm_sys_department where department_id='"+this.userOrg+"'";
		logger.debug("[执行sql：]"+sql);
		List<Map> permList	=	dao.executeQuery(sql);
		for(Map<?, ?> tmp:permList){
			permMap.put(tmp.get("function_id"),"1");
		}
		return permMap;
	}
	/**
	 * 
	 * <p>名称：getFunPermByUsrType</p>
	 * <p>说明：通过用户类型，获取用户类型权限</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：Map<Object,Object> 返回类型</p>
	 * <p>@return</p>
	 */
	private Map<Object,Object>	getFunPermByUsrType(){

		if(null==this.userType||"null".equals(this.userType)||"".equals(this.userType)){
			return new HashMap<Object,Object>();
		}		
		Map<Object,Object> permMap	=	new HashMap<Object,Object>();
		String sql	=	"select usertype_id,function_id from sys_perm_sys_usertype where usertype_id='"+this.userType+"'";
		logger.debug("[执行sql：]"+sql);
		List<Map> permList	=	dao.executeQuery(sql);
		for(Map<?, ?> tmp:permList){
			permMap.put(tmp.get("function_id"),"1");
		}
		return permMap;
	}
	/**
	 * 
	 * <p>名称：getFunPermByUsrRole</p>
	 * <p>说明：通过用户角色，获取用户角色权限</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：Map<Object,Object> 返回类型</p>
	 * <p>@return</p>
	 */
	private Map <Object,Object>	getFunPermByUsrRole(){

		if(null==this.userRole||"null".equals(this.userRole)||"".equals(this.userRole)||userRole.size()==0){
			return new HashMap<Object,Object>();
		}
		Map<Object,Object> permMap	=	new HashMap<Object,Object>();
		String roles="(";
		for(String role:this.userRole){
			roles+="'"+role+"',";
		}
		roles	=	roles.substring(0,roles.length()-1)+")";
		String sql	=	"select role_id,function_id from sys_perm_sys_role where role_id in"+roles;
		logger.debug("[执行sql：]"+sql);
		List<Map> permList	=	dao.executeQuery(sql);
		for(Map<?, ?> tmp:permList){
			permMap.put(tmp.get("function_id"),"1");
		}
		return permMap;
	}
}
