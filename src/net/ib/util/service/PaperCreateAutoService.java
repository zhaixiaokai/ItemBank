
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	PaperCreateAutoService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-28 ����5:54:56
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-28 ����5:54:56
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.PaperCreateAutoService </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public interface PaperCreateAutoService {
	public String createPaper(String difficulty,String targetEPDB,String totalScore,String totalTime,String finCourseId,String useage,String IBList,String XZKPList,String XZKPCount,String PDKPList,String PDKPCount,String TKKPList,String TKKPCount,String JDKPList,String JDKPCount);
	public String autoPaperOnHand(String ibs,String ids);
}
