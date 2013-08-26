
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	DataDicAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-25 上午10:04:04
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-25 上午10:04:04
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.DataDicService;


  /**
 * <p>类名：net.ib.util.action.DataDicAction </p>
 * <p>描述：字典项的Action</p>
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

		//转义n→n2   \n→n1
		DicExplain=DicExplain.replaceAll("n", "n2");
		DicExplain=DicExplain.replaceAll("\n", "n1");
	}
	/**
	 * 
	 * <p>名称：AddDataDic</p>
	 * <p>说明：添加字典项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	AddDataDic(){
		dataDicService.AddDataDataDic(DicId, DicName, DicExplain);
		return null;
	}
	/**
	 * 
	 * <p>名称：UpdataDataDic</p>
	 * <p>说明：更新字典项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	UpdateDataDic(){
		dataDicService.UpdateDataDic(OldDataDicId, DicId, DicName, DicExplain);
		return null;
	}
	/**
	 * 
	 * <p>名称：DeleteDataDic</p>
	 * <p>说明：删除字典项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	DeleteDataDic(){
		dataDicService.DeleteDataDic(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>名称：BulkDeleteDataDic</p>
	 * <p>说明：批量删除字典项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	BulkDeleteDataDic(){
		dataDicService.BulkDeleteDataDic(DeleteId);
		return null;
	}
}
