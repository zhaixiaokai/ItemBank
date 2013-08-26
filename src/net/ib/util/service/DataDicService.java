
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	DataDicService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-25 上午10:10:15
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-25 上午10:10:15
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.DataDicService </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public interface DataDicService {
	public	String	AddDataDataDic(String DicId,String	DicName,String	DicExplain);
	public	String	UpdateDataDic(String OldDicId,String DicId,String DicName,String DicExplain);
	public	String	DeleteDataDic(String	DicId);
	public	String	BulkDeleteDataDic(String	DicIds);
}
