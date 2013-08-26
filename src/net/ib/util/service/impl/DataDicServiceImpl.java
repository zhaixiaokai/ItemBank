
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	DataDicServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-25 上午10:11:28
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-25 上午10:11:28
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
 * <p>类名：net.ib.util.service.impl.DataDicServiceImpl </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
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
	/* (非-Javadoc)
	 * <p>名称: AddDataDataDic</p>
	 * <p>说明: 添加字典项</p>
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
		//如果有重复项
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
					logger.info("添加字典项过程中字典项唯一标识符重复");
					result = "{\"result\":\"failedId\",\"text\":\"字典项唯一标识符已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(DicName)){
					logger.info("添加数据字典项时名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"数据字典项名称已经存在，请重新输入\"}";
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
			Sql="insert into SYS_DICTIONARY_ENTRIES (ID,NAME,EXPLAIN) values " +
					"('"+DicId+"','"+DicName+"','"+DicExplain+"')";
			if(1==dao.execute(Sql)){
				result = "{\"result\":\"succ\",\"text\":\"添加字典项成功\"}";
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
	 * <p>名称: UpdateDataDic</p>
	 * <p>说明: 更新数据字典项</p>
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
				result="{\"result\":\"succ\",\"text\":\"修改数据字典项成功\"}";
			}
			else{
				result="{\"result\":\"succ\",\"text\":\"修改数据字典项失败\"}";
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
					logger.info("修改数据字典项过程中数据字典项唯一标识符重复");
					result = "{\"result\":\"failedId\",\"text\":\"数据字典项唯一标识符已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(DicName)){
					logger.info("修改数据字典项过程中数据字典项名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"数据字典项名称已经存在，请重新输入\"}";
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
	 * <p>名称: DeleteDataDic</p>
	 * <p>说明: 删除字典项</p>
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
			result="{\"result\":\"succ\",\"text\":\"删除数据字典项成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"删除数据字典项失败\"}";
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
	 * <p>名称: BulkDeleteDataDic</p>
	 * <p>说明: 批量删除字典项</p>
	 * @param DicIds
	 * @return
	 * @see net.ib.util.service.DataDicService#BulkDeleteDataDic(java.lang.String)
	 */
	@Override
	public String BulkDeleteDataDic(String DicIds) {
		// TODO Auto-generated method stub
		logger.debug(DicIds);
		//收到的DelIds格式为   id1 id2 id3 id4 ....
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
}
