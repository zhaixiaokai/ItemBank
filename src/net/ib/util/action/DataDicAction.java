
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	DataDicAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-25 ����10:04:04
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-25 ����10:04:04
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.DataDicService;


  /**
 * <p>������net.ib.util.action.DataDicAction </p>
 * <p>�������ֵ����Action</p>
 * <p></p>
 */
public class DataDicAction {
	private	String	DicId;
	private	String	DicName;
	private	String	DicExplain;
	private	String	OldDataDicId;
	private	String	DeleteId;
	public String getDeleteId() {
		return DeleteId;
	}
	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}
	private	DataDicService	dataDicService;
	public String getOldDataDicId() {
		return OldDataDicId;
	}
	public void setOldDataDicId(String oldDataDicId) {
		OldDataDicId = oldDataDicId;
	}
	public DataDicService getDataDicService() {
		return dataDicService;
	}
	public void setDataDicService(DataDicService dataDicService) {
		this.dataDicService = dataDicService;
	}
	public String getDicId() {
		return DicId;
	}
	public void setDicId(String dicId) {
		DicId = dicId;
	}
	public String getDicName() {
		return DicName;
	}
	public void setDicName(String dicName) {
		DicName = dicName;
	}
	public String getDicExplain() {
		return DicExplain;
	}
	public void setDicExplain(String dicExplain) {
		DicExplain = dicExplain;

		//ת��n��n2   \n��n1
		DicExplain=DicExplain.replaceAll("n", "n2");
		DicExplain=DicExplain.replaceAll("\n", "n1");
	}
	/**
	 * 
	 * <p>���ƣ�AddDataDic</p>
	 * <p>˵��������ֵ���</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	AddDataDic(){
		dataDicService.AddDataDataDic(DicId, DicName, DicExplain);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�UpdataDataDic</p>
	 * <p>˵���������ֵ���</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	UpdateDataDic(){
		dataDicService.UpdateDataDic(OldDataDicId, DicId, DicName, DicExplain);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�DeleteDataDic</p>
	 * <p>˵����ɾ���ֵ���</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	DeleteDataDic(){
		dataDicService.DeleteDataDic(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�BulkDeleteDataDic</p>
	 * <p>˵��������ɾ���ֵ���</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	BulkDeleteDataDic(){
		dataDicService.BulkDeleteDataDic(DeleteId);
		return null;
	}
}
