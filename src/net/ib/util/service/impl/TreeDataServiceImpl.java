
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TreeDataServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-21 ����4:26:27
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-21 ����4:26:27
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.Service;
import net.ib.util.service.TreeDataService;


  /**
 * <p>������net.ib.util.service.impl.TreeDataServiceImpl </p>
 * <p>������ϵͳ���漰�����ṹ����Json��ҵ��֧�ŷ���</p>
 * <p></p>
 */
public class TreeDataServiceImpl implements TreeDataService {

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
	 * <p>����: getTeacherDepTreeData</p>
	 * <p>˵��: ��ȡ��ʦ�������ṹJson����</p>
	 * @param TableName
	 * @param TreeId
	 * @return
	 * @see net.ib.util.service.TreeDataService#getTeacherDepTreeData(java.lang.String, java.lang.String)
	 */
	@Override
	public String getTeacherDepTreeData(String TableName, String TreeId) {
		// TODO Auto-generated method stub
		String	Sql	=	"select id,tree_id,text label,pid,sno,node_path,node_series,node_explain from "+TableName+" where tree_id='"+TreeId+"' order by node_path asc";
		List<Map> list	=	new	ArrayList();
		list	=	dao.executeQuery(Sql);
		Map<String, String>	params	=	new	HashMap();
		params.put("tablename", TableName);
		String	Json	=	service.DataListToTreeJson(list, params);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(Json);
			System.out.print("$%%%%%%%%%%%%%%"+Json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/* (��-Javadoc)
	 * <p>����: getChapterTreeData</p>
	 * <p>˵��: ��ȡ�½����ṹJson����</p>
	 * @param TableName
	 * @param TreeId
	 * @return
	 * @see net.ib.util.service.TreeDataService#getTeacherDepTreeData(java.lang.String, java.lang.String)
	 */
	@Override
	public String getChapterTreeData(String TableName, String TreeId) {
		// TODO Auto-generated method stub
		String	Sql	=	"select id,tree_id,text label,pid,sno,node_path,node_series,node_explain from "+TableName+" where tree_id='"+TreeId+"' order by node_path asc";
		List<Map> list	=	new	ArrayList();
		list	=	dao.executeQuery(Sql);
		Map<String, String>	params	=	new	HashMap();
		params.put("tablename", TableName);
		String	Json	=	service.DataListToTreeJson(list, params);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(Json);
			System.out.print("$%%%%%%%%%%%%%%"+Json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String getFunctionTreeData(String TableName, String TreeId) {
		// TODO Auto-generated method stub
				String	Sql	=	"select id,tree_id,text label,pid,sno,node_path,node_series,node_explain from "+TableName+" where tree_id='"+TreeId+"' order by node_path asc";
				List<Map> list	=	new	ArrayList();
				list	=	dao.executeQuery(Sql);
				Map<String, String>	params	=	new	HashMap();
				params.put("tablename", TableName);
				String	Json	=	service.DataListToTreeJson(list, params);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				try {
					PrintWriter pw = response.getWriter();
					pw.print(Json);
					System.out.print("$%%%%%%%%%%%%%%"+Json);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
		
	}


}
