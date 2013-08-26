
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TreeDataService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-21 下午4:25:23
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-21 下午4:25:23
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.TreeDataService </p>
 * <p>描述：系统中涉及到树结构数据Json业务支撑接口</p>
 * <p></p>
 */
public interface TreeDataService {
	public	String	getTeacherDepTreeData(String TableName,String TreeId);
	public  String  getFunctionTreeData(String TableName, String TreeId);
	public	String  getChapterTreeData(String TableName,String TreeId);
	
}
