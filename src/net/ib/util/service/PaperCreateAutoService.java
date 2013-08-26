
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	PaperCreateAutoService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-28 下午5:54:56
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-28 下午5:54:56
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.PaperCreateAutoService </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public interface PaperCreateAutoService {
	public String createPaper(String difficulty,String targetEPDB,String totalScore,String totalTime,String finCourseId,String useage,String IBList,String XZKPList,String XZKPCount,String PDKPList,String PDKPCount,String TKKPList,String TKKPCount,String JDKPList,String JDKPCount);
	public String autoPaperOnHand(String ibs,String ids);
}
