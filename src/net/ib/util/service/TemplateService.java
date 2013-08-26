
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TemplateService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-5 下午2:23:23
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-5 下午2:23:23
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.io.InputStream;


  /**
 * <p>类名：net.ib.util.service.TemplateService </p>
 * <p>描述：定义模板加载的Service接口函数</p>
 * <p></p>
 */
public interface TemplateService {
	public String GetXZTemplate();
	public String GetPDTemplate();
	public	String GetJDTemplate();
	public	String GetTKTemplate();
	Boolean	WriteIO(InputStream in);
}
