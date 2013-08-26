
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	UserPswServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-8-20 上午11:58:42
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-8-20 上午11:58:42
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.config.SessionData;
import net.ib.util.dao.Dao;
import net.ib.util.service.Service;
import net.ib.util.service.UserPswService;


  /**
 * <p>类名：net.ib.util.service.impl.UserPswServiceImpl </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class UserPswServiceImpl implements UserPswService {

	private static Logger logger = Logger.getLogger(UserPswServiceImpl.class);
	private Dao	dao;
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	/* (非-Javadoc)
	 * <p>名称: updateUserPsw</p>
	 * <p>说明: </p>
	 * @param opsw
	 * @param md5Psw
	 * @return
	 * @see net.ib.util.service.UserPswService#updateUserPsw(java.lang.String, java.lang.String)
	 */
	@Override
	public String updateUserPsw(String md5OldPsw, String md5Psw) {
		// TODO Auto-generated method stub
		logger.debug(md5OldPsw);
		logger.debug(md5Psw);
		HttpServletRequest request	=	ServletActionContext.getRequest();
		
		int	userType	=	Integer.valueOf(request.getSession().getAttribute(SessionData.SESSION_USERTYPE).toString()) ;
		String userT	=	"";
		if(userType==1){
			userT	=	"teacher";
		}else{
			userT	=	"student";
		}
		String userId	=	request.getSession().getAttribute(SessionData.SESSION_USER_ID).toString();
		
		String selSql	=	"select * from sys_user_"+userT+" where user_id='"+userId+"'";
		List<Map> list 	=	dao.executeQuery(selSql);
		logger.debug(list);
		if(!md5OldPsw.equals(list.get(0).get("password"))){
			//密码不正确
			
			this.writeResponse("{\"result\":\"error\",\"desc\":\"密码错误\"}");
			return "";
		}
		String sql="update sys_user_"+userT+" set password='"+md5Psw+"' where user_id='"+userId+"'";
		
		dao.execute(sql);
		this.writeResponse("{\"result\":\"success\",\"desc\":\"修改成功\"}");
		return null;
	}
	
	private void writeResponse(String string){
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(string);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
