
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	PaperService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-5-8 下午2:17:04
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-5-8 下午2:17:04
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.io.File;


  /**
 * <p>类名：net.ib.util.service.PaperService </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public interface PaperService {
	public String addPaper(String id,String name,String score,String time,String diff,File paperCont,File paperAnswer,File paperAtta,String course,String epdbId,String comment,String type);
}
