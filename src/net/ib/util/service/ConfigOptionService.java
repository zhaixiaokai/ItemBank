
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ConfigOptionService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-14 ����2:49:25
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-14 ����2:49:25
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.ConfigOptionService </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public interface ConfigOptionService {
	/**
	 * 
	 * <p>���ƣ�AddConfigOptionService</p>
	 * <p>˵������������������������Ϣ��������˳��ţ�</p>
	 * <p>������@param ConfigOptionId
	 * <p>������@param ConfigOptionName
	 * <p>������@param ConfigOptionValue
	 * <p>������@param ConfigOptionExplain
	 * <p>������@return �趨�ļ�</p>
	 */
	public	String	AddConfigOptionService(String ConfigOptionId,String ConfigOptionName,String ConfigOptionValue,String ConfigOptionExplain);
	/**
	 * 
	 * <p>���ƣ�AddConfigOptionService</p>
	 * <p>˵�������������(����������Ϣ�����������)</p>
	 * <p>������@param ConfigOptionId
	 * <p>������@param ConfigOptionName
	 * <p>������@param ConfigOptionValue
	 * <p>������@param ConfigOptionExplain
	 * <p>������@param ConfigOptionNum
	 * <p>������@param ConfigOptionInfo
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 */
	public	String	AddConfigOptionService(String ConfigOptionId,String ConfigOptionName,String ConfigOptionValue,String ConfigOptionExplain,String	ConfigOptionNum,String ConfigOptionInfo);
	/**
	 * 
	 * <p>���ƣ�DeleteConfigOptionService</p>
	 * <p>˵����ɾ��������</p>
	 * <p>������@param DelId
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@param DelId
	 * <p>@return</p>
	 */
	public	String	DeleteConfigOptionService(String	DelId);
	/**
	 * 
	 * <p>���ƣ�UpdateConfigOptionService</p>
	 * <p>˵��������һ���������¼</p>
	 * <p>������@param OldConfigOptionId
	 * <p>������@param ConfigOptionId
	 * <p>������@param ConfigOptionName
	 * <p>������@param ConfigOptionValue
	 * <p>������@param ConfigOptionExplain
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 */
	public	String	UpdateConfigOptionService(String OldConfigOptionId,String ConfigOptionId,String ConfigOptionName,String ConfigOptionValue,String ConfigOptionExplain);
	public	String	BulkDeleteConfigOption(String DeleteIds);
	public	String	getKPInfoByKPId(String ids);
}
