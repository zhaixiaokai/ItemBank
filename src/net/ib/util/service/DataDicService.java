
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	DataDicService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-25 ����10:10:15
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-25 ����10:10:15
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.DataDicService </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public interface DataDicService {
	public	String	AddDataDataDic(String DicId,String	DicName,String	DicExplain);
	public	String	UpdateDataDic(String OldDicId,String DicId,String DicName,String DicExplain);
	public	String	DeleteDataDic(String	DicId);
	public	String	BulkDeleteDataDic(String	DicIds);
}
