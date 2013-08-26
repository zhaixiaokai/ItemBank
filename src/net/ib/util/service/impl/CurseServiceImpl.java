
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	CurseServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�wuzexi
 * | �������ڣ�2012-12-6 ����8:22:24
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-6 ����8:22:24
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
import net.ib.util.service.CurseService;
import net.ib.util.service.Service;


  /**
 * <p>������net.ib.util.service.impl.CurseServiceImpl </p>
 * <p>�������γ̹���ķ���ʵ����</p>
 * <p></p>
 */
public class CurseServiceImpl implements CurseService {

	private static Logger logger = Logger.getLogger(CurseServiceImpl.class);
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
	 * <p>����: addCurse</p>
	 * <p>˵��: </p>
	 * @param getSpecial
	 * @param curseId
	 * @param curseName
	 * @param curseCredit
	 * @return
	 * @see net.ib.util.service.CurseService#addCurse(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addCurse(String getSpecial,String curseId,String curseName,String curseCredit,String method,String curseRemarks) {
		// TODO Auto-generated method stub
		String result = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		
		String	CheckExistSql	=	"select * from SYS_IB_CLASSIFICATION_TREE where ID='"+getSpecial+"'";
		List<Map>	CheckList	=	dao.executeQuery(CheckExistSql);
		if(CheckList.size()==0){
			logger.info("��ӿγ̹�����δ�ҵ��ύ��רҵ");
			result = "{\"result\":\"failedId\",\"text\":\"��רҵ������\"}";
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//ͨ��id��name��ѯ�Ƿ�����뵱ǰ��Ҫ��ӵ������ظ�����
		String	Sql	=	"select CURSE_ID,CURSE_NAME,SPECIAL_FIELD_ID from SYS_CURSE where CURSE_ID='"+curseId
				+"' or CURSE_NAME='"+curseName+"'";
		List<Map>	list	=	dao.executeQuery(Sql);
		
		//������ظ���
		if(list.size()!=0){
			String	TempId="";
			String	TempName="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempId	=	(String) map.get("curse_id");
				TempName=	(String) map.get("curse_name");
				logger.debug(TempName);
				logger.debug(TempId);
				if(TempId.equals(curseId)){
					logger.info("��ӿγ̹����пγ�Ψһ��ʶ���ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"�γ̱���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(curseName)){
					logger.info("��ӿγ̹����пγ������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�γ������Ѿ����ڣ�����������\"}";
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
			Sql="insert into SYS_CURSE (CURSE_ID,CURSE_NAME,SPECIAL_FIELD_ID,CURSE_CREDIT,ASSESSMENT_METHOD,REMARKS) values " +
					"('"+curseId+"','"+curseName+"','"+getSpecial+"','"+curseCredit+"','"+method+"','"+curseRemarks+"')";
			if(1==dao.execute(Sql)){
				result = "{\"result\":\"succ\",\"text\":\"��ӿγ̳ɹ�\"}";
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
	@Override
	public String updateCurse(String oldCurseId,String curseName,String	curseID,String	curseCredit,String radio,String curseRemarks)
	{
		// TODO Auto-generated method stub
		logger.debug(oldCurseId);
		logger.debug(curseName);
		logger.debug(curseID);
		logger.debug(curseCredit);
		logger.debug(radio);
		logger.debug(curseRemarks);
		curseRemarks=curseRemarks.trim();
		if (radio.trim().toUpperCase().equals("KAOSHI")) {
			radio = "1";
		} else {
			radio = "0";
		}
		String sql = "select curse_id,curse_name from sys_curse where (curse_id='"
				+ curseID + "' or curse_name='" + curseName + "') and curse_id!='"+oldCurseId+"'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map>	list	=	dao.executeQuery(sql);
		String	result="";
		
		if (list.size() == 0) {
		
			//��ʼ��������
			sql = "update sys_curse set curse_id='" + curseID
					+ "',curse_name='" + curseName + "',curse_credit='"
					+ curseCredit+ "',assessment_method='" + radio
					+ "',remarks='" + curseRemarks
					+ "' where curse_id='" + oldCurseId + "'";

			logger.debug(sql);
			dao.execute(sql);
			result = "{\"result\":\"succ\",\"text\":\"�޸Ŀγ̳ɹ�\"}";
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		//���ظ�
		else{
			Map map = new HashMap();
			String ClassId = "";
			String ClassName = "";
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				ClassId = (String) map.get("curse_id");
				ClassName = (String) map.get("curse_name");
				// ����γ�Id�ظ�
				if (curseID.equals(ClassId)) {
					logger.info("�޸Ŀγ̹�����Id�ظ�");
					result = "{\"result\":\"failedId\",\"text\":\"�γ̱���Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}
				// �༶
				else if (curseName.equals(ClassName)) {
					logger.info("�޸Ŀγ̹����������ظ�");
					result = "{\"result\":\"failedName\",\"text\":\"�γ������Ѿ����ڣ�����������\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				} 

				else {
					logger.info("�޸Ŀγ̷����쳣");
				}
			}			
		}
		return null;
	}
	@Override
	public String deleteCurse(String deleteId) {
		// TODO Auto-generated method stub
		logger.debug(deleteId);
		String	sql	=	"delete from sys_curse where CURSE_ID='"+deleteId+"'";
		logger.debug(sql);
		String	result="";
		if(1==dao.execute(sql)){
			result	=	"{\"result\":\"ɾ���γ̳ɹ�\"}";
		}
		else{
			result	=	"{\"result\":\"ɾ���γ�ʧ��\"}";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
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
	public String bulkDeleteCurse(String	deleteIds) {
		// TODO Auto-generated method stub
		logger.debug(deleteIds);
		//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
		String[] array=deleteIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		String	Sql	=	"delete from sys_curse where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" curse_id='"+array[i]+"'";
		}
		logger.debug(Sql);
		if(1==dao.execute(Sql)){
			result="{\"result\":\"success\",\"text\":\"ɾ���ɹ�\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"ɾ���ɹ�\"}";
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