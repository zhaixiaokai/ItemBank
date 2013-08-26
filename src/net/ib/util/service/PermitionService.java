
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	PermitionService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-7-27 下午3:24:33
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-7-27 下午3:24:33
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


  /**
 * <p>类名：net.ib.util.service.PermitionService </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public interface PermitionService {
	
	public Map<Object,Object> getFirstLevelPerm();
	
	public Map<Object,Object> getSecondLevelPerm();
	
	public Map<Object,Object> getThirdLevelPerm();
	
	public Map<Object,Object> getAllLevelPerm(HttpServletRequest request,HttpServletResponse response);
	
}
