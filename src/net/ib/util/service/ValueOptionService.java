
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ValueOptionService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-25 下午2:31:22
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-25 下午2:31:22
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.ValueOptionService </p>
 * <p>描述：定义值项管理的接口</p>
 * <p></p>
 */
public interface ValueOptionService {
	public	String	AddValueOption(String DataDicId,String	ValueName,String ValueValue,int SNO);
	public	String	UpdateValueOption(String OldId,String DataDicId,String	ValueName,String ValueValue,int SNO);
	public	String	DeleteValueOption(String ValueOptionId);
	public	String	BulkDeleteValueOption(String ValueOptionsIds);
}
