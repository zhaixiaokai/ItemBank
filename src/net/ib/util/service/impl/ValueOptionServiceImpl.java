
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ValueOptionServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-25 ����2:39:15
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-25 ����2:39:15
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
import net.ib.util.service.Service;
import net.ib.util.service.ValueOptionService;


  /**
 * <p>������net.ib.util.service.impl.ValueOptionServiceImpl </p>
 * <p>����������ֵ������ʵ����</p>
 * <p></p>
 */
public class ValueOptionServiceImpl implements ValueOptionService {

	private static Logger logger = Logger.getLogger(ValueOptionServiceImpl.class);
	Dao	dao;
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

	/* (��-Javadoc)
	 * <p>����: AddValueOption</p>
	 * <p>˵��: ���ֵ��</p>
	 * @param DataDicId
	 * @param ValueName
	 * @param ValueValue
	 * @param SNO
	 * @return
	 * @see net.ib.util.service.ValueOptionService#AddValueOption(java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public String AddValueOption(String DataDicId, String ValueName,
			String ValueValue, int SNO) {
		// TODO Auto-generated method stub
		logger.debug(DataDicId);
		logger.debug(ValueName);
		logger.debug(ValueValue);
		logger.debug(SNO);
		String	sql	=	"select * from SYS_DICTIONARY_ENTRIES_VALUE " +
				"where (NAME='"+ValueName+"' or value='"+ValueValue+"'or sno='"+SNO+"') " +
				"and DICTIONARY_ENTRIES_ID='"+DataDicId+"'"; 
		//String	sql	=	"select * from SYS_DICTIONARY_ENTRIES_VALUE where DICTIONARY_ENTRIES_ID='"+DataDicId+"' order by 'SNO' asc";
		logger.debug(sql);
		List<Map>	list	=	dao.executeQuery(sql);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String	result="";
		TypeChange tc	=	new	TypeChange();
		//������ظ���
		if(list.size()!=0){
			String	TempValue="";
			String	TempName="";
			String	TempSNO="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempValue	=	(String) map.get("value");
				TempName=	(String) map.get("name");
				TempSNO	=	(String) map.get("sno");
				logger.debug(TempName);
				logger.debug(TempValue);
				logger.debug(TempSNO);
				if(TempValue.equals(ValueValue)){
					logger.info("���ֵ��ʱֵ��ֵ�ظ�");
					result = "{\"result\":\"failedValue\",\"text\":\"ֵ��ֵ�Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(ValueName)){
					logger.info("���ֵ�������ֵ�������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"ֵ�������Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempSNO.equals(tc.intToString(SNO))){
					logger.info("���ֵ�������˳����ظ�");
					result = "{\"result\":\"failedSNO\",\"text\":\"ֵ��˳����ظ�������������\"}";
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
			sql="insert into SYS_DICTIONARY_ENTRIES_VALUE (DICTIONARY_ENTRIES_ID,NAME,VALUE,SNO) values " +
					"('"+DataDicId+"','"+ValueName+"','"+ValueValue+"','"+SNO+"')";
			if(1==dao.execute(sql)){
				result = "{\"result\":\"succ\",\"text\":\"���ֵ��ɹ�\"}";
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
	 * <p>����: UpdateValueOption</p>
	 * <p>˵��: ����ֵ��</p>
	 * @param OldId			�޸ĵ����ݵ�id
	 * @param DataDicId		�ֵ�������
	 * @param ValueName		ֵ������
	 * @param ValueValue	ֵ��ֵ
	 * @param SNO			ֵ��˳���
	 * @return
	 * @see net.ib.util.service.ValueOptionService#UpdateValueOption(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public String UpdateValueOption(String OldEntriesSNO, String DataDicId,
			String ValueName, String ValueValue, int SNO) {
		// TODO Auto-generated method stub
		logger.debug(OldEntriesSNO);
		logger.debug(DataDicId);
		logger.debug(ValueName);
		logger.debug(ValueValue);
		logger.debug(SNO);
		TypeChange	tc	=	new	TypeChange();
		String sql = "select * from SYS_DICTIONARY_ENTRIES_VALUE where (NAME='"
				+ ValueName + "' or value='" + ValueValue + "' or SNO='"+SNO + "') " +
						"and DICTIONARY_ENTRIES_VALUE!='"+OldEntriesSNO+"' and DICTIONARY_ENTRIES_ID='"+DataDicId+"'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map>	list	=	dao.executeQuery(sql);
		String	result="";
		if (list.size() == 0) {
			sql = "update SYS_DICTIONARY_ENTRIES_VALUE set NAME='" + ValueName
					+ "',value='" + ValueValue + "',SNO='" + SNO
					+ "' where DICTIONARY_ENTRIES_VALUE='" + OldEntriesSNO + "'";
			if(1==dao.execute(sql)){
				result="{\"result\":\"succ\",\"text\":\"�޸�ֵ��ɹ�\"}";
			}
			else{
				result="{\"result\":\"failed\",\"text\":\"�޸�ֵ��ʧ��\"}";
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
			String	TempValue="";
			String	TempName="";
			String	TempSNO="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempValue	=	(String) map.get("value");
				TempName=	(String) map.get("name");
				TempSNO	=	(String) map.get("sno");
				logger.debug(TempName);
				logger.debug(TempValue);
				logger.debug(TempSNO);
				if(TempName.equals(ValueName)){
					logger.info("�޸�ֵ�������ֵ�������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"ֵ�������Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempValue.equals(ValueValue)){
					logger.info("�޸�ֵ�������ֵ��ֵ�ظ�");
					result = "{\"result\":\"failedValue\",\"text\":\"ֵ��ֵ�Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempSNO.equals(tc.intToString(SNO))){
					logger.info("�޸�ֵ�������ֵ��˳����ظ�");
					result = "{\"result\":\"failedSNO\",\"text\":\"ֵ��˳����Ѿ����ڣ�����������\"}";
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
	 * <p>����: DeleteValueOption</p>
	 * <p>˵��: ɾ��ֵ��</p>
	 * @param ValueOptionId
	 * @return
	 * @see net.ib.util.service.ValueOptionService#DeleteValueOption(java.lang.String)
	 */
	@Override
	public String DeleteValueOption(String ValueOptionId) {
		// TODO Auto-generated method stub
		String	Sql	=	"delete from SYS_DICTIONARY_ENTRIES_VALUE where dictionary_entries_value='"+ValueOptionId+"'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String	result="";
		if(1==dao.execute(Sql)){
			result = "{\"result\":\"succ\",\"text\":\"ɾ��ֵ��ɹ�\"}";
		}
		else{
			result = "{\"result\":\"failed\",\"text\":\"ɾ��ֵ��ʧ��\"}";
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
	 * <p>����: BulkDeleteValueOption</p>
	 * <p>˵��: ����ɾ��ֵ��</p>
	 * @param ValueOptionsIds
	 * @return
	 * @see net.ib.util.service.ValueOptionService#BulkDeleteValueOption(java.lang.String)
	 */
	@Override
	public String BulkDeleteValueOption(String ValueOptionsIds) {
		// TODO Auto-generated method stub
		logger.debug(ValueOptionsIds);
		//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
		String[] array=ValueOptionsIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		String	Sql	=	"delete from SYS_DICTIONARY_ENTRIES_VALUE where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" DICTIONARY_ENTRIES_VALUE='"+array[i]+"'";
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

}
