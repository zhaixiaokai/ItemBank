
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	PermitionService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-7-27 ����3:24:33
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-7-27 ����3:24:33
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


  /**
 * <p>������net.ib.util.service.PermitionService </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public interface PermitionService {
	
	public Map<Object,Object> getFirstLevelPerm();
	
	public Map<Object,Object> getSecondLevelPerm();
	
	public Map<Object,Object> getThirdLevelPerm();
	
	public Map<Object,Object> getAllLevelPerm(HttpServletRequest request,HttpServletResponse response);
	
}
