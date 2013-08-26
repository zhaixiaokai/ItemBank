
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	CreateDataTableJsonForYui.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-6 上午10:30:02
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 上午10:30:02
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.util.Map;


  /**
 * <p>类名：net.ib.util.service.CreateDataTableJsonForYui </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public interface CreateDataTableJsonForYuiService {
	public	String	CreateDataTableJson(String	dir,String	results,String	sort,String	startIndex,String	getDataSql,String getCountSql);
	public	String	CreateDataTableJson(Map ConfigMap,Map SqlMap);
	public	String	CreateDataTableJsonFullSql(String	dir,String	results,String	sort,String	startIndex,String	getDataSql,String getCountSql);
};
