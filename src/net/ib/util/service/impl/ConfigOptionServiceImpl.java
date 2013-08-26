
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ConfigOptionServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-14 下午2:50:30
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-14 下午2:50:30
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
 * <p>类名：net.ib.util.service.impl.ConfigOptionServiceImpl </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
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

	
	 /* (非-Javadoc)
	 * <p>名称: AddConfigOptionService</p>
	 * <p>说明: 添加配置项</p>
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
		//通过id、name查询是否存在与当前所要添加的项有重复的项
		String	Sql	=	"select * from sys_configuration_items where CONFIGURATION_ITEMS_ID='"+ConfigOptionId
				+"' or NAME='"+ConfigOptionName+"'";
		List<Map>	list	=	dao.executeQuery(Sql);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		//如果有重复项
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
					logger.info("添加配置项过程中配置项唯一标识符重复");
					result = "{\"result\":\"failedId\",\"text\":\"配置项唯一标识符已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(ConfigOptionName)){
					logger.info("添加配置项过程中配置项名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"配置项名称已经存在，请重新输入\"}";
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
		//没有重复项
		else{
			Sql="insert into sys_configuration_items (CONFIGURATION_ITEMS_ID,NAME,VALUE,EXPLAIN) values " +
					"('"+ConfigOptionId+"','"+ConfigOptionName+"','"+ConfigOptionValue+"','"+ConfigOptionExplain+"')";
			if(1==dao.execute(Sql)){
				result = "{\"result\":\"succ\",\"text\":\"添加配置项成功\"}";
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

	
	 /* (非-Javadoc)
	 * <p>名称: AddConfigOptionService</p>
	 * <p>说明: 添加配置项</p>
	 * @param ConfigOptionId	配置项id
	 * @param ConfigOptionName	配置项名称
	 * @param ConfigOptionValue	配置项值
	 * @param ConfigOptionExplain	配置项说明
	 * @param ConfigOptionNum	配置项顺序
	 * @param ConfigOptionInfo	配置项信息
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
		//通过id、name查询是否存在与当前所要添加的项有重复的项
		String	Sql	=	"select * from sys_configuration_items where CONFIGURATION_ITEMS_ID='"+ConfigOptionId
				+"' or NAME='"+ConfigOptionName+"'";
		List<Map>	list	=	dao.executeQuery(Sql);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		//如果有重复项
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
					logger.info("添加配置项过程中配置项唯一标识符重复");
					result = "{\"result\":\"failedId\",\"text\":\"配置项唯一标识符已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(ConfigOptionName)){
					logger.info("添加配置项过程中配置项名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"配置项名称已经存在，请重新输入\"}";
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
		//没有重复项
		else{
			Sql="insert into sys_configuration_items (CONFIGURATION_ITEMS_ID,NAME,NUM,INFO,VALUE,EXPLAIN) values " +
					"('"+ConfigOptionId+"','"+ConfigOptionName+"','"+ConfigOptionNum+"','"+ConfigOptionInfo+"','"+ConfigOptionValue+"','"+ConfigOptionExplain+"')";
			if(1==dao.execute(Sql)){
				result = "{\"result\":\"succ\",\"text\":\"添加配置项成功\"}";
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

	
	 /* (非-Javadoc)
	 * <p>名称: DeleteConfigOptionService</p>
	 * <p>说明: 删除配置项</p>
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
			result="{\"result\":\"succ\",\"text\":\"删除配置项成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"删除配置项失败\"}";
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

	
	 /* (非-Javadoc)
	 * <p>名称: UpdateConfigOptionService</p>
	 * <p>说明: </p>
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
				result="{\"result\":\"succ\",\"text\":\"修改配置项成功\"}";
			}
			else{
				result="{\"result\":\"failed\",\"text\":\"修改配置项失败\"}";
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
					logger.info("修改配置项过程中配置项唯一标识符重复");
					result = "{\"result\":\"failedId\",\"text\":\"配置项唯一标识符已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(ConfigOptionName)){
					logger.info("修改配置项过程中配置项名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"配置项名称已经存在，请重新输入\"}";
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

	
	 /* (非-Javadoc)
	 * <p>名称: BulkDeleteConfigOption</p>
	 * <p>说明: </p>
	 * @param DeleteIds 批量删除的Id 多各Id用空格分开
	 * @return
	 * @see net.ib.util.service.ConfigOptionService#BulkDeleteConfigOption(java.lang.String)
	 */
	@Override
	public String BulkDeleteConfigOption(String DelIds) {
		// TODO Auto-generated method stub
		logger.debug(DelIds);
		//收到的DelIds格式为   id1 id2 id3 id4 ....
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
			result="{\"result\":\"success\",\"text\":\"删除成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"删除失败\"}";
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
