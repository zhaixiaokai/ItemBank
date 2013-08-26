
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	PermitionServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-7-27 ����3:30:53
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-7-27 ����3:30:53
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
 * <p>������net.ib.util.service.impl.PermitionServiceImpl </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
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
	/* (��-Javadoc)
	 * <p>����: getFirstLevelPerm</p>
	 * <p>˵��: </p>
	 * @return
	 * @see net.ib.util.service.PermitionService#getFirstLevelPerm()
	 */
	@Override
	public Map<Object, Object> getFirstLevelPerm() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (��-Javadoc)
	 * <p>����: getSecondLevelPerm</p>
	 * <p>˵��: </p>
	 * @return
	 * @see net.ib.util.service.PermitionService#getSecondLevelPerm()
	 */
	@Override
	public Map<Object, Object> getSecondLevelPerm() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (��-Javadoc)
	 * <p>����: getThirdLevelPerm</p>
	 * <p>˵��: </p>
	 * @return
	 * @see net.ib.util.service.PermitionService#getThirdLevelPerm()
	 */
	@Override
	public Map<Object, Object> getThirdLevelPerm() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (��-Javadoc)
	 * <p>����: getAllLevelPerm</p>
	 * <p>˵��: </p>
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
		
		logger.debug("session�л�ȡ��[�û�ID]				��"+userId);
		logger.debug("session�л�ȡ��[�û�ѧ��/�̹���]		��"+schoolId);
		logger.debug("session�л�ȡ��[�û���]				��"+userName);
		logger.debug("session�л�ȡ��[�û�����]				��"+userOrg);
		logger.debug("session�л�ȡ��[�û�����]				��"+userType);
		logger.debug("session�л�ȡ��[��ɫ�б�]				��"+userRole);
		
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
	 * <p>���ƣ�getFunPermByUsrId</p>
	 * <p>˵����ͨ���û�id��ȡ�û�Ȩ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��Map<Object,Object> ��������</p>
	 * <p>@return</p>
	 */
	private Map<Object,Object>	getFunPermByUsrId(){
		if(null==this.userId||"null".equals(this.userId)||"".equals(this.userId)){
			return new HashMap<Object,Object>();
		}
		Map<Object,Object> permMap	=	new HashMap<Object,Object>();
		String sql	=	"select user_id,function_id from sys_perm_sys_user where user_id='"+this.userId+"'";
		logger.debug("[ִ��sql��]"+sql);
		List<Map> permList	=	dao.executeQuery(sql);
		for(Map<?, ?> tmp:permList){
			permMap.put(tmp.get("function_id"),"1");
		}
		return permMap;
	}
	/**
	 * 
	 * <p>���ƣ�getFucPermByOrg</p>
	 * <p>˵����ͨ���û����Ż�ȡ�û�����Ȩ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��Map<Object,Object> ��������</p>
	 * <p>@return</p>
	 */
	private Map<Object,Object>	getFucPermByOrg(){
		if(null==this.userOrg||"null".equals(this.userOrg)||"".equals(this.userOrg)){
			return new HashMap<Object,Object>();
		}
		Map<Object,Object> permMap	=	new HashMap<Object,Object>();
		String sql	=	"select department_id,function_id from sys_perm_sys_department where department_id='"+this.userOrg+"'";
		logger.debug("[ִ��sql��]"+sql);
		List<Map> permList	=	dao.executeQuery(sql);
		for(Map<?, ?> tmp:permList){
			permMap.put(tmp.get("function_id"),"1");
		}
		return permMap;
	}
	/**
	 * 
	 * <p>���ƣ�getFunPermByUsrType</p>
	 * <p>˵����ͨ���û����ͣ���ȡ�û�����Ȩ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��Map<Object,Object> ��������</p>
	 * <p>@return</p>
	 */
	private Map<Object,Object>	getFunPermByUsrType(){

		if(null==this.userType||"null".equals(this.userType)||"".equals(this.userType)){
			return new HashMap<Object,Object>();
		}		
		Map<Object,Object> permMap	=	new HashMap<Object,Object>();
		String sql	=	"select usertype_id,function_id from sys_perm_sys_usertype where usertype_id='"+this.userType+"'";
		logger.debug("[ִ��sql��]"+sql);
		List<Map> permList	=	dao.executeQuery(sql);
		for(Map<?, ?> tmp:permList){
			permMap.put(tmp.get("function_id"),"1");
		}
		return permMap;
	}
	/**
	 * 
	 * <p>���ƣ�getFunPermByUsrRole</p>
	 * <p>˵����ͨ���û���ɫ����ȡ�û���ɫȨ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��Map<Object,Object> ��������</p>
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
		logger.debug("[ִ��sql��]"+sql);
		List<Map> permList	=	dao.executeQuery(sql);
		for(Map<?, ?> tmp:permList){
			permMap.put(tmp.get("function_id"),"1");
		}
		return permMap;
	}
}
