
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ValueOptionService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-25 ����2:31:22
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-25 ����2:31:22
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.ValueOptionService </p>
 * <p>����������ֵ�����Ľӿ�</p>
 * <p></p>
 */
public interface ValueOptionService {
	public	String	AddValueOption(String DataDicId,String	ValueName,String ValueValue,int SNO);
	public	String	UpdateValueOption(String OldId,String DataDicId,String	ValueName,String ValueValue,int SNO);
	public	String	DeleteValueOption(String ValueOptionId);
	public	String	BulkDeleteValueOption(String ValueOptionsIds);
}
