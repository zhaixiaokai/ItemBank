
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	DataDicServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-25 ����10:11:28
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-25 ����10:11:28
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

import net.ib.util.action.ConfigOptionAction;
import net.ib.util.dao.Dao;
import net.ib.util.service.DataDicService;
import net.ib.util.service.Service;


  /**
 * <p>������net.ib.util.service.impl.DataDicServiceImpl </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class DataDicServiceImpl implements DataDicService {
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
	/* (��-Javadoc)
	 * <p>����: AddDataDataDic</p>
	 * <p>˵��: ����ֵ���</p>
	 * @param DicId
	 * @param DicName
	 * @param DicExplain
	 * @return
	 * @see net.ib.util.service.DataDicService#AddDataDataDic(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String AddDataDataDic(String DicId, String DicName, String DicExplain) {
		// TODO Auto-generated method stub
		String	Sql	=	"select * from SYS_DICTIONARY_ENTRIES where ID='"+DicId+"' or name='"+DicName+"'";
		List<Map>	list	=	dao.executeQuery(Sql);
		String	result="";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		//������ظ���
		if(list.size()!=0){
			String	TempId="";
			String	TempName="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempId	=	(String) map.get("id");
				TempName=	(String) map.get("name");
				logger.debug(TempName);
				logger.debug(TempId);
				if(TempId.equals(DicId)){
					logger.info("����ֵ���������ֵ���Ψһ��ʶ���ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"�ֵ���Ψһ��ʶ���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(DicName)){
					logger.info("��������ֵ���ʱ�����ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�����ֵ��������Ѿ����ڣ�����������\"}";
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
			Sql="insert into SYS_DICTIONARY_ENTRIES (ID,NAME,EXPLAIN) values " +
					"('"+DicId+"','"+DicName+"','"+DicExplain+"')";
			if(1==dao.execute(Sql)){
				result = "{\"result\":\"succ\",\"text\":\"����ֵ���ɹ�\"}";
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
	 * <p>����: UpdateDataDic</p>
	 * <p>˵��: ���������ֵ���</p>
	 * @param OldDicId
	 * @param DicId
	 * @param DicName
	 * @param DicExplain
	 * @return
	 * @see net.ib.util.service.DataDicService#UpdateDataDic(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String UpdateDataDic(String OldDicId, String DicId, String DicName,
			String DicExplain) {
		// TODO Auto-generated method stub
		String sql = "select id,NAME from SYS_DICTIONARY_ENTRIES where (id='"
				+ DicId + "' or NAME='" + DicName + "') and id!='"+OldDicId+"'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map>	list	=	dao.executeQuery(sql);
		String	result="";
		if (list.size() == 0) {
			sql = "update SYS_DICTIONARY_ENTRIES set id='" + DicId
					+ "',NAME='" + DicName + "',EXPLAIN='"
					+ DicExplain
					+ "' where id='" + OldDicId + "'";
			if(1==dao.execute(sql)){
				result="{\"result\":\"succ\",\"text\":\"�޸������ֵ���ɹ�\"}";
			}
			else{
				result="{\"result\":\"succ\",\"text\":\"�޸������ֵ���ʧ��\"}";
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
				TempId	=	(String) map.get("id");
				TempName=	(String) map.get("name");
				logger.debug(TempName);
				logger.debug(TempId);
				if(TempId.equals(DicId)){
					logger.info("�޸������ֵ�������������ֵ���Ψһ��ʶ���ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"�����ֵ���Ψһ��ʶ���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(DicName)){
					logger.info("�޸������ֵ�������������ֵ��������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�����ֵ��������Ѿ����ڣ�����������\"}";
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
	 * <p>����: DeleteDataDic</p>
	 * <p>˵��: ɾ���ֵ���</p>
	 * @param DicId
	 * @return
	 * @see net.ib.util.service.DataDicService#DeleteDataDic(java.lang.String)
	 */
	@Override
	public String DeleteDataDic(String DicId) {
		// TODO Auto-generated method stub
		String	Sql="delete from SYS_DICTIONARY_ENTRIES where id='"+DicId+"'";
		logger.debug(Sql);
		String	result="";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if(1==dao.execute(Sql)){
			result="{\"result\":\"succ\",\"text\":\"ɾ�������ֵ���ɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"ɾ�������ֵ���ʧ��\"}";
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
	 * <p>����: BulkDeleteDataDic</p>
	 * <p>˵��: ����ɾ���ֵ���</p>
	 * @param DicIds
	 * @return
	 * @see net.ib.util.service.DataDicService#BulkDeleteDataDic(java.lang.String)
	 */
	@Override
	public String BulkDeleteDataDic(String DicIds) {
		// TODO Auto-generated method stub
		logger.debug(DicIds);
		//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
		String[] array=DicIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		String	Sql	=	"delete from SYS_DICTIONARY_ENTRIES where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" id='"+array[i]+"'";
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
