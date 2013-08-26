
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ItembankGetService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-7 下午3:20:01
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-7 下午3:20:01
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.ItembankGetService </p>
 * <p>描述：定义通过选择条件选择试题库功能的接口</p>
 * <p></p>
 */
public interface ItembankGetService {
	/**
	 * 
	 * <p>名称：SelectItemBankByCourseAndUsage</p>
	 * <p>说明：通过课程以及用途作为选择条件，选择符合条件的试题库</p>
	 * <p>参数：@param course
	 * <p>参数：@param useage
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@param course
	 * <p>@param useage
	 * <p>@return</p>
	 */
	public	String	SelectItemBankByCourseAndUsage(String	course,String	useage);
}
