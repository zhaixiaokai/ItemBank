
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ItembankGetServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-7 ����3:22:00
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-7 ����3:22:00
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
import net.ib.util.action.ItembankAction;
import net.ib.util.dao.Dao;
import net.ib.util.service.ItembankGetService;
import net.ib.util.service.Service;


  /**
 * <p>������net.ib.util.service.impl.ItembankGetServiceImpl </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class ItembankGetServiceImpl implements ItembankGetService{

	private static Logger logger = Logger.getLogger(ItembankGetServiceImpl.class);
	private	Dao	dao;
	private	Service	service;
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
	/* (��-Javadoc)
	 * <p>����: SelectItemBankByCourseAndUsage</p>
	 * <p>˵��: ͨ���γ̡��������;ѡ����������������</p>
	 * @param course	�γ�Id
	 * @param useage	��;value
	 * @return
	 * @see net.ib.util.service.ItembankGetService#SelectItemBankByCourseAndUsage(java.lang.String, java.lang.String)
	 */
	@Override
	public String SelectItemBankByCourseAndUsage(String course, String useage) {
		// TODO Auto-generated method stub
		logger.debug(course);
		logger.debug(useage);
		//��ѯһ���û����з���Ȩ�޵�����������SQL���
		HttpServletRequest request = ServletActionContext.getRequest();
		String sessionUser	=	request.getSession().getAttribute(SessionData.SESSION_SCHOOL_ID).toString();
		String sessionUserId=	request.getSession().getAttribute(SessionData.SESSION_USER_ID).toString();
		String sessionUserType	=	request.getSession().getAttribute(SessionData.SESSION_USERTYPE).toString();
		String str	=	"";
		if("1".equals(sessionUserType)) str="teacher";
		else	str	=	"student";
		String	SQLSelectItemBankByUserPerm="select * from sys_itembank_list where itembank_id in("
				  +"select itembank_id from sys_perm_itembank_user where user_id='"+sessionUser+"' UNION "
				  +"select itembank_id from sys_perm_itembank_role where role_id in ("
				  +"select role_id from sys_role_member where user_id='"+sessionUserId+"'"
				  +")union "
				  +"select itembank_id from sys_perm_itembank_class where class_id in("
				    +"select curse_class_id from sys_curse_class_member where user_id='"+sessionUserId+"'"
				  +")union "
				  +"select itembank_id from sys_perm_itembank_department where department_id in("
				    +"select department_id  from sys_department_member where user_id='"+sessionUserId+"'"
				  +")union "
				  +"select itembank_id from sys_perm_itembank_membertype where membertype in('"
				    +str
				  +"')"
				+")";
		logger.debug(SQLSelectItemBankByUserPerm);
		
		String	Sql	=	"select * from ("+SQLSelectItemBankByUserPerm+") where curse_id='"+course+"' and use='"+useage+"'";
		
		List<Map> list	=dao.executeQuery(Sql);
		
		String	Json	=service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(Json);	
			logger.debug("-------------------****-----------------"+Json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
