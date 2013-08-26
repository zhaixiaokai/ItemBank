
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ConfigOptionService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-14 下午2:49:25
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-14 下午2:49:25
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.ConfigOptionService </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public interface ConfigOptionService {
	/**
	 * 
	 * <p>名称：AddConfigOptionService</p>
	 * <p>说明：添加配置项（不带配置项信息与配置项顺序号）</p>
	 * <p>参数：@param ConfigOptionId
	 * <p>参数：@param ConfigOptionName
	 * <p>参数：@param ConfigOptionValue
	 * <p>参数：@param ConfigOptionExplain
	 * <p>参数：@return 设定文件</p>
	 */
	public	String	AddConfigOptionService(String ConfigOptionId,String ConfigOptionName,String ConfigOptionValue,String ConfigOptionExplain);
	/**
	 * 
	 * <p>名称：AddConfigOptionService</p>
	 * <p>说明：添加配置项(带配置项信息与配置项序号)</p>
	 * <p>参数：@param ConfigOptionId
	 * <p>参数：@param ConfigOptionName
	 * <p>参数：@param ConfigOptionValue
	 * <p>参数：@param ConfigOptionExplain
	 * <p>参数：@param ConfigOptionNum
	 * <p>参数：@param ConfigOptionInfo
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 */
	public	String	AddConfigOptionService(String ConfigOptionId,String ConfigOptionName,String ConfigOptionValue,String ConfigOptionExplain,String	ConfigOptionNum,String ConfigOptionInfo);
	/**
	 * 
	 * <p>名称：DeleteConfigOptionService</p>
	 * <p>说明：删除配置项</p>
	 * <p>参数：@param DelId
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@param DelId
	 * <p>@return</p>
	 */
	public	String	DeleteConfigOptionService(String	DelId);
	/**
	 * 
	 * <p>名称：UpdateConfigOptionService</p>
	 * <p>说明：更新一条配置项记录</p>
	 * <p>参数：@param OldConfigOptionId
	 * <p>参数：@param ConfigOptionId
	 * <p>参数：@param ConfigOptionName
	 * <p>参数：@param ConfigOptionValue
	 * <p>参数：@param ConfigOptionExplain
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 */
	public	String	UpdateConfigOptionService(String OldConfigOptionId,String ConfigOptionId,String ConfigOptionName,String ConfigOptionValue,String ConfigOptionExplain);
	public	String	BulkDeleteConfigOption(String DeleteIds);
	public	String	getKPInfoByKPId(String ids);
}
