
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ValueOptionAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-25 ����2:27:38
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-25 ����2:27:38
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.ValueOptionService;


  /**
 * <p>������net.ib.util.action.ValueOptionAction </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class ValueOptionAction {

	private	String	SelectDataDic;
	private	String	DicValueOptionName;
	private	String	DicValueOptionValue;
	private	int	DicValueOptionSNO;
	private	String	DeleteId;
	private	String	OldEntriesSNO;
	
	public String getOldEntriesSNO() {
		return OldEntriesSNO;
	}
	public void setOldEntriesSNO(String oldEntriesSNO) {
		OldEntriesSNO = oldEntriesSNO;
	}
	public String getDeleteId() {
		return DeleteId;
	}
	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}
	private	ValueOptionService valueOptionService;
	public ValueOptionService getValueOptionService() {
		return valueOptionService;
	}
	public void setValueOptionService(ValueOptionService valueOptionService) {
		this.valueOptionService = valueOptionService;
	}
	public String getSelectDataDic() {
		return SelectDataDic;
	}
	public void setSelectDataDic(String selectDataDic) {
		SelectDataDic = selectDataDic;
	}
	public String getDicValueOptionName() {
		return DicValueOptionName;
	}
	public void setDicValueOptionName(String dicValueOptionName) {
		DicValueOptionName = dicValueOptionName;
	}
	public String getDicValueOptionValue() {
		return DicValueOptionValue;
	}
	public void setDicValueOptionValue(String dicValueOptionValue) {
		DicValueOptionValue = dicValueOptionValue;
	}
	public int getDicValueOptionSNO() {
		return DicValueOptionSNO;
	}
	public void setDicValueOptionSNO(int dicValueOptionSNO) {
		DicValueOptionSNO = dicValueOptionSNO;
	}
	/**
	 * 
	 * <p>���ƣ�AddValueOption</p>
	 * <p>˵�������ֵ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	AddValueOption(){
		valueOptionService.AddValueOption(SelectDataDic, DicValueOptionName, DicValueOptionValue, DicValueOptionSNO);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�UpdateValueOption</p>
	 * <p>˵�����޸�ֵ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	UpdateValueOption(){
		valueOptionService.UpdateValueOption(OldEntriesSNO,SelectDataDic, DicValueOptionName, DicValueOptionValue, DicValueOptionSNO);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�DeleteValueOption</p>
	 * <p>˵����ɾ��ֵ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	DeleteValueOption(){
		valueOptionService.DeleteValueOption(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�BulkDeleteValueOption</p>
	 * <p>˵��������ɾ��ֵ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	BulkDeleteValueOption(){
		valueOptionService.BulkDeleteValueOption(DeleteId);
		return null;
	}
}
