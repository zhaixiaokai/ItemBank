
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ConfigOptionServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-14 ����2:50:30
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-14 ����2:50:30
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
import net.ib.util.service.ConfigOptionService;
import net.ib.util.service.Service;


  /**
 * <p>������net.ib.util.service.impl.ConfigOptionServiceImpl </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class ConfigOptionServiceImpl implements ConfigOptionService{
	private static Logger logger = Logger.getLogger(ConfigOptionServiceImpl.class);
	private Dao	dao;
	private	Service	service;
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	
	 /* (��-Javadoc)
	 * <p>����: AddConfigOptionService</p>
	 * <p>˵��: ���������</p>
	 * @param ConfigOptionId
	 * @param ConfigOptionName
	 * @param ConfigOptionValue
	 * @param ConfigOptionExplain
	 * @return
	 * @see net.ib.util.service.ConfigOptionService#AddConfigOptionService(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String AddConfigOptionService(String ConfigOptionId,
			String ConfigOptionName, String ConfigOptionValue,
			String ConfigOptionExplain) {
		// TODO Auto-generated method stub
		String result = "";
		//ͨ��id��name��ѯ�Ƿ�����뵱ǰ��Ҫ��ӵ������ظ�����
		String	Sql	=	"select * from sys_configuration_items where CONFIGURATION_ITEMS_ID='"+ConfigOptionId
				+"' or NAME='"+ConfigOptionName+"'";
		List<Map>	list	=	dao.executeQuery(Sql);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		//������ظ���
		if(list.size()!=0){
			String	TempId="";
			String	TempName="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempId	=	(String) map.get("configuration_items_id");
				TempName=	(String) map.get("name");
				logger.debug(TempName);
				logger.debug(TempId);
				if(TempId.equals(ConfigOptionId)){
					logger.info("��������������������Ψһ��ʶ���ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"������Ψһ��ʶ���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(ConfigOptionName)){
					logger.info("�������������������������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�����������Ѿ����ڣ�����������\"}";
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
		//û���ظ���
		else{
			Sql="insert into sys_configuration_items (CONFIGURATION_ITEMS_ID,NAME,VALUE,EXPLAIN) values " +
					"('"+ConfigOptionId+"','"+ConfigOptionName+"','"+ConfigOptionValue+"','"+ConfigOptionExplain+"')";
			if(1==dao.execute(Sql)){
				result = "{\"result\":\"succ\",\"text\":\"���������ɹ�\"}";
				try {
					PrintWriter pw = response.getWriter();
					pw.print(result);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	
	 /* (��-Javadoc)
	 * <p>����: AddConfigOptionService</p>
	 * <p>˵��: ���������</p>
	 * @param ConfigOptionId	������id
	 * @param ConfigOptionName	����������
	 * @param ConfigOptionValue	������ֵ
	 * @param ConfigOptionExplain	������˵��
	 * @param ConfigOptionNum	������˳��
	 * @param ConfigOptionInfo	��������Ϣ
	 * @return
	 * @see net.ib.util.service.ConfigOptionService#AddConfigOptionService(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String AddConfigOptionService(String ConfigOptionId,
			String ConfigOptionName, String ConfigOptionValue,
			String ConfigOptionExplain, String ConfigOptionNum,
			String ConfigOptionInfo) {
		// TODO Auto-generated method stub
		String result = "";
		//ͨ��id��name��ѯ�Ƿ�����뵱ǰ��Ҫ��ӵ������ظ�����
		String	Sql	=	"select * from sys_configuration_items where CONFIGURATION_ITEMS_ID='"+ConfigOptionId
				+"' or NAME='"+ConfigOptionName+"'";
		List<Map>	list	=	dao.executeQuery(Sql);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		//������ظ���
		if(list.size()!=0){
			String	TempId="";
			String	TempName="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempId	=	(String) map.get("configuration_items_id");
				TempName=	(String) map.get("name");
				logger.debug(TempName);
				logger.debug(TempId);
				if(TempId.equals(ConfigOptionId)){
					logger.info("��������������������Ψһ��ʶ���ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"������Ψһ��ʶ���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(ConfigOptionName)){
					logger.info("�������������������������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�����������Ѿ����ڣ�����������\"}";
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
		//û���ظ���
		else{
			Sql="insert into sys_configuration_items (CONFIGURATION_ITEMS_ID,NAME,NUM,INFO,VALUE,EXPLAIN) values " +
					"('"+ConfigOptionId+"','"+ConfigOptionName+"','"+ConfigOptionNum+"','"+ConfigOptionInfo+"','"+ConfigOptionValue+"','"+ConfigOptionExplain+"')";
			if(1==dao.execute(Sql)){
				result = "{\"result\":\"succ\",\"text\":\"���������ɹ�\"}";
				try {
					PrintWriter pw = response.getWriter();
					pw.print(result);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
		return null;
	}

	
	 /* (��-Javadoc)
	 * <p>����: DeleteConfigOptionService</p>
	 * <p>˵��: ɾ��������</p>
	 * @param DelId
	 * @return
	 * @see net.ib.util.service.ConfigOptionService#DeleteConfigOptionService(java.lang.String)
	 */
	@Override
	public String DeleteConfigOptionService(String DelId) {
		// TODO Auto-generated method stub
		String	Sql="delete from sys_configuration_items where CONFIGURATION_ITEMS_ID='"+DelId+"'";
		logger.debug(Sql);
		String	result="";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if(1==dao.execute(Sql)){
			result="{\"result\":\"succ\",\"text\":\"ɾ��������ɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"ɾ��������ʧ��\"}";
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

	
	 /* (��-Javadoc)
	 * <p>����: UpdateConfigOptionService</p>
	 * <p>˵��: </p>
	 * @param OldConfigOptionId
	 * @param ConfigOptionId
	 * @param ConfigOptionName
	 * @param ConfigOptionValue
	 * @param ConfigOptionExplain
	 * @return
	 * @see net.ib.util.service.ConfigOptionService#UpdateConfigOptionService(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String UpdateConfigOptionService(String OldConfigOptionId,
			String ConfigOptionId, String ConfigOptionName,
			String ConfigOptionValue, String ConfigOptionExplain) {
		// TODO Auto-generated method stub
		String sql = "select CONFIGURATION_ITEMS_ID,NAME from sys_configuration_items where (CONFIGURATION_ITEMS_ID='"
				+ ConfigOptionId + "' or NAME='" + ConfigOptionName + "') and CONFIGURATION_ITEMS_ID!='"+OldConfigOptionId+"'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map>	list	=	dao.executeQuery(sql);
		String	result="";
		if (list.size() == 0) {
			sql = "update sys_configuration_items set CONFIGURATION_ITEMS_ID='" + ConfigOptionId
					+ "',NAME='" + ConfigOptionName + "',VALUE='"
					+ ConfigOptionValue + "',EXPLAIN='"
					+ ConfigOptionExplain
					+ "' where CONFIGURATION_ITEMS_ID='" + OldConfigOptionId + "'";
			if(1==dao.execute(sql)){
				result="{\"result\":\"succ\",\"text\":\"�޸�������ɹ�\"}";
			}
			else{
				result="{\"result\":\"failed\",\"text\":\"�޸�������ʧ��\"}";
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
				TempId	=	(String) map.get("configuration_items_id");
				TempName=	(String) map.get("name");
				logger.debug(TempName);
				logger.debug(TempId);
				if(TempId.equals(ConfigOptionId)){
					logger.info("�޸������������������Ψһ��ʶ���ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"������Ψһ��ʶ���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(ConfigOptionName)){
					logger.info("�޸�����������������������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�����������Ѿ����ڣ�����������\"}";
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

	
	 /* (��-Javadoc)
	 * <p>����: BulkDeleteConfigOption</p>
	 * <p>˵��: </p>
	 * @param DeleteIds ����ɾ����Id ���Id�ÿո�ֿ�
	 * @return
	 * @see net.ib.util.service.ConfigOptionService#BulkDeleteConfigOption(java.lang.String)
	 */
	@Override
	public String BulkDeleteConfigOption(String DelIds) {
		// TODO Auto-generated method stub
		logger.debug(DelIds);
		//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
		String[] array=DelIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		String	Sql	=	"delete from sys_configuration_items where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" CONFIGURATION_ITEMS_ID='"+array[i]+"'";
		}
		logger.debug(Sql);
		if(1==dao.execute(Sql)){
			result="{\"result\":\"success\",\"text\":\"ɾ���ɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"ɾ��ʧ��\"}";
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
	public String getKPInfoByKPId(String ids) {
		// TODO Auto-generated method stub
		String[] idList=ids.split(",");
		String	sql	=	"select KNOWLEDGE_POINT_ID,NAME from SYS_KNOWLEDGE_POINT WHERE KNOWLEDGE_POINT_ID=''";
		for(int i=0;i<idList.length;i++){
			sql+=" or KNOWLEDGE_POINT_ID='"+idList[i].substring(3)+"'";
		}
		logger.debug(sql);
		List<Map> list	=	 dao.executeQuery(sql);
		String	result	=	service.DataListToJson(list);
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
