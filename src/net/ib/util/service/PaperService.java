
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	PaperService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-5-8 ����2:17:04
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-5-8 ����2:17:04
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.io.File;


  /**
 * <p>������net.ib.util.service.PaperService </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public interface PaperService {
	public String addPaper(String id,String name,String score,String time,String diff,File paperCont,File paperAnswer,File paperAtta,String course,String epdbId,String comment,String type);
}
