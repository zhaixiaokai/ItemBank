
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ValueOptionAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-25 下午2:27:38
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-25 下午2:27:38
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.ValueOptionService;


  /**
 * <p>类名：net.ib.util.action.ValueOptionAction </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
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
	 * <p>名称：AddValueOption</p>
	 * <p>说明：添加值项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	AddValueOption(){
		valueOptionService.AddValueOption(SelectDataDic, DicValueOptionName, DicValueOptionValue, DicValueOptionSNO);
		return null;
	}
	/**
	 * 
	 * <p>名称：UpdateValueOption</p>
	 * <p>说明：修改值项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	UpdateValueOption(){
		valueOptionService.UpdateValueOption(OldEntriesSNO,SelectDataDic, DicValueOptionName, DicValueOptionValue, DicValueOptionSNO);
		return null;
	}
	/**
	 * 
	 * <p>名称：DeleteValueOption</p>
	 * <p>说明：删除值项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	DeleteValueOption(){
		valueOptionService.DeleteValueOption(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>名称：BulkDeleteValueOption</p>
	 * <p>说明：批量删除值项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	BulkDeleteValueOption(){
		valueOptionService.BulkDeleteValueOption(DeleteId);
		return null;
	}
}
