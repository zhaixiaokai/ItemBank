
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ItembankGetService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-7 ����3:20:01
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-7 ����3:20:01
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.ItembankGetService </p>
 * <p>����������ͨ��ѡ������ѡ������⹦�ܵĽӿ�</p>
 * <p></p>
 */
public interface ItembankGetService {
	/**
	 * 
	 * <p>���ƣ�SelectItemBankByCourseAndUsage</p>
	 * <p>˵����ͨ���γ��Լ���;��Ϊѡ��������ѡ����������������</p>
	 * <p>������@param course
	 * <p>������@param useage
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@param course
	 * <p>@param useage
	 * <p>@return</p>
	 */
	public	String	SelectItemBankByCourseAndUsage(String	course,String	useage);
}
