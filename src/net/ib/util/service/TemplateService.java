
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TemplateService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-5 ����2:23:23
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-5 ����2:23:23
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.io.InputStream;


  /**
 * <p>������net.ib.util.service.TemplateService </p>
 * <p>����������ģ����ص�Service�ӿں���</p>
 * <p></p>
 */
public interface TemplateService {
	public String GetXZTemplate();
	public String GetPDTemplate();
	public	String GetJDTemplate();
	public	String GetTKTemplate();
	Boolean	WriteIO(InputStream in);
}
