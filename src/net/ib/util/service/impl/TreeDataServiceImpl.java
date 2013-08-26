
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TreeDataServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-21 下午4:26:27
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-21 下午4:26:27
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
 * <p>类名：net.ib.util.service.impl.TreeDataServiceImpl </p>
 * <p>描述：系统中涉及到树结构数据Json的业务支撑方法</p>
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
	
	 /* (非-Javadoc)
	 * <p>名称: getTeacherDepTreeData</p>
	 * <p>说明: 获取教师机构树结构Json数据</p>
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
	
	/* (非-Javadoc)
	 * <p>名称: getChapterTreeData</p>
	 * <p>说明: 获取章节树结构Json数据</p>
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
