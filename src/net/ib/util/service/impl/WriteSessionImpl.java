package net.ib.util.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.ib.config.SessionData;
import net.ib.util.dao.Dao;
import net.ib.util.dao.DaoImpl;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class WriteSessionImpl {
	private static Logger logger = Logger.getLogger(WriteSessionImpl.class);
	private Dao dao	=	new DaoImpl();

	public void writeSession(){

		HttpServletRequest	request	=	ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userName	=	(String) session.getAttribute(SessionData.SESSION_USERNAME);
		String userType	=	(String) session.getAttribute(SessionData.SESSION_USERTYPE);
		String sql="";
		if("1".equals(userType)){
			sql =	"select * from sys_user_"+"teacher"+" where username='"+userName+"'";
		}	
		else{
			sql =	"select * from sys_user_"+"student"+" where username='"+userName+"'";
		}
		
		@SuppressWarnings("rawtypes")
		List<Map>	list	=	dao.executeQuery(sql);
		logger.debug("-----"+list);
		String userId="";
		if(list.size()>0){
			userId	=	(String) list.get(0).get("user_id");
			session.setAttribute(SessionData.SESSION_USER_ID, list.get(0).get("user_id"));
			session.setAttribute(SessionData.SESSION_SCHOOL_ID, list.get(0).get("school_id"));
		}
		sql	=	"select * from SYS_DEPARTMENT_MEMBER where user_id='"+userId+"'";
		list	=	dao.executeQuery(sql);
		logger.debug("+++++++"+list);
		if(list.size()==0){}
		else{
			session.setAttribute(SessionData.SESSION_ORG, list.get(0).get("department_id"));
		}
		sql	=	"select * from sys_role_member where user_id='"+userId+"'";
		list	=	dao.executeQuery(sql);
		logger.debug("roles:"+list);
		List<Object>	roles	=	new	ArrayList<Object>();
		for(int i=0;i<list.size();i++){
			roles.add(list.get(i).get("role_id"));
		}
		session.setAttribute(SessionData.SESSION_ROLE, roles);
	}
	
	public void removeSession(){
		HttpServletRequest	request	=	ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute(SessionData.SESSION_ORG);
		session.removeAttribute(SessionData.SESSION_ROLE);
		session.removeAttribute(SessionData.SESSION_SCHOOL_ID);
		session.removeAttribute(SessionData.SESSION_USER_ID);
		session.removeAttribute(SessionData.SESSION_USERNAME);
		session.removeAttribute(SessionData.SESSION_USERTYPE);
	}
}
