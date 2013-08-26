package net.ib.util.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.config.SessionData;
import net.ib.util.dao.DaoImpl;
import net.ib.util.service.TestService;
/*
 * author：xiaokai
 * Function：实现登录验证
 * Description：从前台传送用户填写的用户名和密码以及用户类型以参数的形式传入函数，进行验证验证通过返回true否则返回false
*/
public class TestServiceImp implements TestService {

	private static Logger logger = Logger.getLogger(TestServiceImp.class);
	private DataSource dataSource ;
	private	DaoImpl	dao	=	new	DaoImpl();
	private WriteSessionImpl	wsi	=	new	WriteSessionImpl();
	public boolean ValidPassword(String name, String pwd,String	usertype,String	check) {
		// TODO Auto-generated method stub		
		HttpServletRequest	request	=	ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		System.err.println("session"+session.getAttribute("_CheckCode"));
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if(!session.getAttribute("_CheckCode").equals(check)){
			try {	
				PrintWriter pw = response.getWriter();	
				pw.print("{\"result\":\"checkErr\",\"text\":\"验证码错误\"}");	
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return  false;
		}
		if(usertype.equals("teacher")){
			String	sql	=	"select password from sys_user_teacher	where	username='"+name+"'";
			@SuppressWarnings("rawtypes")
			List<Map> ls	=	dao.executeQuery(sql);
			logger.debug(ls);
			@SuppressWarnings("rawtypes")
			Map<?, ?>	map	=	new	HashMap();
			if(ls.size()!=0){
				map	=	ls.get(0);
				Iterator<?> it = map.entrySet().iterator();
				@SuppressWarnings("rawtypes")
				Map.Entry entry = (Map.Entry) it.next();
			    String	paramValue	=	(String)entry.getValue();
				if(pwd.equals(paramValue)){
					try {	
						PrintWriter pw = response.getWriter();	
						pw.print("{\"result\":\"loginSuccess\",\"text\":\"登录成功\"}");
						pw.close();
						session.setAttribute(SessionData.SESSION_USERNAME, name);
						session.setAttribute(SessionData.SESSION_USERTYPE, "1");
						wsi.writeSession();
					} catch (IOException e) {
						e.printStackTrace();
					}
					logger.info("password is valid");
					return	true;
				}	
				else{
					try {	
						PrintWriter pw = response.getWriter();	
						pw.print("{\"result\":\"pswErr\",\"text\":\"密码错误\"}");	
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return  false;
				}
			}
			else{
				logger.info("没有该用户");
				try {	
					PrintWriter pw = response.getWriter();	
					pw.print("{\"result\":\"noUser\",\"text\":\"没有该用户\"}");	
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return  false;
			}

		}
		else if(usertype.equals("student")){
			String	sql	=	"select password	from	sys_user_student	where	username='"+name+"'";
			@SuppressWarnings("rawtypes")
			List<Map> ls	=	dao.executeQuery(sql);
			logger.debug(ls);
			@SuppressWarnings("rawtypes")
			Map<?, ?>	map	=	new	HashMap();
			if(ls.size()!=0){
				map	=	ls.get(0);
				Iterator<?> it = map.entrySet().iterator();
				@SuppressWarnings("rawtypes")
				Map.Entry entry = (Map.Entry) it.next();
			    String	paramValue	=	(String)entry.getValue();
				if(pwd.equals(paramValue)){
					logger.info("password is valid");
					try {	
						PrintWriter pw = response.getWriter();	
						pw.print("{\"result\":\"loginSuccess\",\"text\":\"登陆成功\"}");	
						pw.close();
						session.setAttribute(SessionData.SESSION_USERNAME, name);
						session.setAttribute(SessionData.SESSION_USERTYPE, "2");
						wsi.writeSession();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return	true;
				}		
				else{
					try {	
						PrintWriter pw = response.getWriter();	
						pw.print("{\"result\":\"pswErr\",\"text\":\"密码错误\"}");	
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return  false;
				}
			}
			else{
				logger.info("没有该用户");
				
				try {	
					PrintWriter pw = response.getWriter();	
					pw.print("{\"result\":\"noUser\",\"text\":\"没有该用户\"}");	
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return  false;
			}		
		}
		else{
			logger.info("没有此类型的用户！");
			try {	
				PrintWriter pw = response.getWriter();	
				pw.print("{\"result\":\"typeErr\",\"text\":\"请选择用户类型\"}");	
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return  false;
		}
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
