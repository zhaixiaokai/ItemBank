
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ValueOptionServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-25 下午2:39:15
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-25 下午2:39:15
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
 * <p>类名：net.ib.util.service.impl.ValueOptionServiceImpl </p>
 * <p>描述：定义值项管理的实现类</p>
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

	/* (非-Javadoc)
	 * <p>名称: AddValueOption</p>
	 * <p>说明: 添加值项</p>
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
		//如果有重复项
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
					logger.info("添加值项时值项值重复");
					result = "{\"result\":\"failedValue\",\"text\":\"值项值已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(ValueName)){
					logger.info("添加值项过程中值项名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"值项名称已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempSNO.equals(tc.intToString(SNO))){
					logger.info("添加值项过程中顺序号重复");
					result = "{\"result\":\"failedSNO\",\"text\":\"值项顺序号重复，请重新输入\"}";
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
			sql="insert into SYS_DICTIONARY_ENTRIES_VALUE (DICTIONARY_ENTRIES_ID,NAME,VALUE,SNO) values " +
					"('"+DataDicId+"','"+ValueName+"','"+ValueValue+"','"+SNO+"')";
			if(1==dao.execute(sql)){
				result = "{\"result\":\"succ\",\"text\":\"添加值项成功\"}";
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
	 * <p>名称: UpdateValueOption</p>
	 * <p>说明: 更新值项</p>
	 * @param OldId			修改的数据的id
	 * @param DataDicId		字典项名称
	 * @param ValueName		值项名称
	 * @param ValueValue	值项值
	 * @param SNO			值项顺序号
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
				result="{\"result\":\"succ\",\"text\":\"修改值项成功\"}";
			}
			else{
				result="{\"result\":\"failed\",\"text\":\"修改值项失败\"}";
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
					logger.info("修改值项过程中值项名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"值项名称已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempValue.equals(ValueValue)){
					logger.info("修改值项过程中值项值重复");
					result = "{\"result\":\"failedValue\",\"text\":\"值项值已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempSNO.equals(tc.intToString(SNO))){
					logger.info("修改值项过程中值项顺序号重复");
					result = "{\"result\":\"failedSNO\",\"text\":\"值项顺序号已经存在，请重新输入\"}";
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
	 * <p>名称: DeleteValueOption</p>
	 * <p>说明: 删除值项</p>
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
			result = "{\"result\":\"succ\",\"text\":\"删除值项成功\"}";
		}
		else{
			result = "{\"result\":\"failed\",\"text\":\"删除值项失败\"}";
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
	 * <p>名称: BulkDeleteValueOption</p>
	 * <p>说明: 批量删除值项</p>
	 * @param ValueOptionsIds
	 * @return
	 * @see net.ib.util.service.ValueOptionService#BulkDeleteValueOption(java.lang.String)
	 */
	@Override
	public String BulkDeleteValueOption(String ValueOptionsIds) {
		// TODO Auto-generated method stub
		logger.debug(ValueOptionsIds);
		//收到的DelIds格式为   id1 id2 id3 id4 ....
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
