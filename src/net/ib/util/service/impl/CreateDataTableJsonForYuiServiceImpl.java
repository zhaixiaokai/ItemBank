
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	CreateDataTableJsonForYui.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-6 上午10:29:03
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 上午10:29:03
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.CreateDataTableJsonForYuiService;
import net.ib.util.service.Service;


  /**
 * <p>类名：net.ib.util.service.impl.CreateDataTableJsonForYui </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class CreateDataTableJsonForYuiServiceImpl implements CreateDataTableJsonForYuiService {

	private static Logger logger = Logger.getLogger(CreateDataTableJsonForYuiServiceImpl.class);
	private	Dao	dao;
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
	private	Service	service;
	 /* (非-Javadoc)
	 * <p>名称: CreateDataTableJson</p>
	 * <p>说明: </p>
	 * @param dir
	 * @param results
	 * @param sort
	 * @param startIndex
	 * @param getCountSql 获取表格数据行数
	 * @param getDataSql 获取表格数据
	 * @return
	 * @see net.ib.util.service.CreateDataTableJsonForYui#CreateDataTableJson(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String CreateDataTableJson(String dir, String results, String sort,
			String startIndex, String getDataSql,String getCountSql) {
		// TODO Auto-generated method stub
		if(dir.indexOf("asc")!=-1){
			dir="asc";
		}
		else if(dir.indexOf("desc")!=-1||dir.indexOf("dasc")!=-1){
			dir="desc";
		}
		TypeChange	tc	=	new	TypeChange();
		int	IntStartIndex	=	tc.stringToInt(startIndex);
		int	IntResults	=	tc.stringToInt(results);
		// 获取数据项数目
		List<Map> list	=	dao.executeQuery("select count(*) cou from "+getCountSql); 
		String	DataCountStr	=	list.get(0).get("cou").toString();
		int	TotalNum	=	tc.stringToInt(DataCountStr);
		String	sql="select * from ("+getDataSql+" order by "+sort+" "+dir+") where rn<="+(IntStartIndex+IntResults)+" and rn>"+IntStartIndex;
		logger.debug(sql);
		//String	sql="select * from (select rownum rn,cu.* from test cu order by "+sort+" "+dir+") where rn<="+(IntStartIndex+IntResults)+" and rn>"+IntStartIndex;
		List<Map> PageList	=	dao.executeQuery(sql);
		
		logger.debug(PageList);
		
		String	Json	=	service.DataListToJsonForYuiDataTable(PageList, TotalNum, IntStartIndex, sort, dir);
		logger.debug(Json);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(Json);	
			logger.debug("%%%%%%%%%66665%%%%%%%%%"+Json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	 /* (非-Javadoc)
	 * <p>名称: CreateDataTableJson</p>
	 * <p>说明: </p>
	 * @param ConfigMap
	 * @param SqlMap
	 * @return
	 * @see net.ib.util.service.CreateDataTableJsonForYuiService#CreateDataTableJson(java.util.Map, java.util.Map)
	 */
	@Override
	public String CreateDataTableJson(Map ConfigMap, Map SqlMap) {
		// TODO Auto-generated method stub
		String dir	=	(String) ConfigMap.get("dir");
		String results	=(String) ConfigMap.get("results");
		String sort	=	(String) ConfigMap.get("sort");
		String startIndex	=	(String) ConfigMap.get("startIndex");
		String getDataSql	=	(String) SqlMap.get("getDataSql");
		String getCountSql	=	(String) SqlMap.get("getCountSql");
		return null;	
	}
	
	 /* (非-Javadoc)
	 * <p>名称: CreateDataTableJsonFullSql</p>
	 * <p>说明: 获取DataTable的Json并且返回给YUI-DataTable</p>
	 * @param dir
	 * @param results
	 * @param sort
	 * @param startIndex
	 * @param getDataSql
	 * @param getCountSql
	 * @return
	 * @see net.ib.util.service.CreateDataTableJsonForYuiService#CreateDataTableJsonFullSql(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String CreateDataTableJsonFullSql(String dir, String results,
			String sort, String startIndex, String getDataSql,
			String getCountSql) {
		// TODO Auto-generated method stub
		if(dir.indexOf("asc")!=-1){
			dir="asc";
		}
		else if(dir.indexOf("desc")!=-1||dir.indexOf("dasc")!=-1){
			dir="desc";
		}
		TypeChange	tc	=	new	TypeChange();
		int	IntStartIndex	=	tc.stringToInt(startIndex);
		// 获取数据项数目
		List<Map> list	=	dao.executeQuery("select count(*) cou from "+getCountSql); 
		String	DataCountStr	=	list.get(0).get("cou").toString();
		int	TotalNum	=	tc.stringToInt(DataCountStr);
		String	sql=getDataSql;
		logger.debug(sql);
		//String	sql="select * from (select rownum rn,cu.* from test cu order by "+sort+" "+dir+") where rn<="+(IntStartIndex+IntResults)+" and rn>"+IntStartIndex;
		List<Map> PageList	=	dao.executeQuery(sql);
		
		logger.debug(PageList);
		
		String	Json	=	service.DataListToJsonForYuiDataTable(PageList, TotalNum, IntStartIndex, sort, dir);
		logger.debug(Json);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(Json);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
