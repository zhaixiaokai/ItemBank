
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	SortService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：lijuan
 * | 创建日期：2012-12-4 上午10:43:54
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-4 上午10:43:54
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.SortService </p>
 * <p>描述：分类体系接口</p>
 * <p></p>
 */
public interface SortService {
	public String SortAdd(String name,String identifier,String discription) ;
	public String SortDelete(String id);
	public String SortBulkDelete(String id);
	public String SortModify(String id,String name,String identifier,String discription);
	public String SortManageDataSource(String TableName, String TreeId); 
	public String GetSortId(String SortName);
	public String SortSelect();
	public String sortTreeOperateActionIfRootId(String sortNodeId);
}
