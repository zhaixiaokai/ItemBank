
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ConfigOptionAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-14 下午2:24:58
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-14 下午2:24:58
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.ConfigOptionService;
import org.apache.log4j.Logger;


  /**
 * <p>类名：net.ib.util.action.ConfigOptionAction </p>
 * <p>描述：配置项相关的Action</p>
 * <p></p>
 */
public class ConfigOptionAction {
	private static Logger logger = Logger.getLogger(ConfigOptionAction.class);
	private String	ConfigOptionId="";
	private	String	ConfigOptionName="";
	private	String	ConfigOptionValue="";
	private	String	ConfigOptionExplain="";
	private	String	ConfigOptionInfo="";
	private	String	ConfigOptionNum="";
	private	String	DeleteId="";
	private String 	OldConfigOptionId="";
	private String	KPIds;
	public String getKPIds() {
		return KPIds;
	}
	public void setKPIds(String kPIds) {
		KPIds = kPIds;
	}
	public String getOldConfigOptionId() {
		return OldConfigOptionId;
	}
	public void setOldConfigOptionId(String oldConfigOptionId) {
		OldConfigOptionId = oldConfigOptionId;
	}
	public String getDeleteId() {
		return DeleteId;
	}
	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}
	public String getConfigOptionInfo() {
		return ConfigOptionInfo;
	}
	public void setConfigOptionInfo(String configOptionInfo) {
		ConfigOptionInfo = configOptionInfo;
	}
	public String getConfigOptionNum() {
		return ConfigOptionNum;
	}
	public void setConfigOptionNum(String configOptionNum) {
		ConfigOptionNum = configOptionNum;
	}

	private	ConfigOptionService	COS;
	public ConfigOptionService getCOS() {
		return COS;
	}
	public void setCOS(ConfigOptionService cOS) {
		COS = cOS;
	}
	public String getConfigOptionId() {
		return ConfigOptionId;
	}
	public void setConfigOptionId(String configOptionId) {
		ConfigOptionId = configOptionId;
	}
	public String getConfigOptionName() {
		return ConfigOptionName;
	}
	public void setConfigOptionName(String configOptionName) {
		ConfigOptionName = configOptionName;
	}
	public String getConfigOptionValue() {
		return ConfigOptionValue;
	}
	public void setConfigOptionValue(String configOptionValue) {
		ConfigOptionValue = configOptionValue;
		//转义n→n2   \n→n1
		ConfigOptionValue=ConfigOptionValue.replaceAll("n", "n2");
		ConfigOptionValue=ConfigOptionValue.replaceAll("\n", "n1");
	}
	public String getConfigOptionExplain() {
		return ConfigOptionExplain;
	}
	public void setConfigOptionExplain(String configOptionExplain) {
		ConfigOptionExplain = configOptionExplain;
		//转义n→n2   \n→n1
		ConfigOptionExplain=ConfigOptionExplain.replaceAll("n", "n2");
		ConfigOptionExplain=ConfigOptionExplain.replaceAll("\n", "n1");
	}
	/**
	 * 
	 * <p>名称：AddConfigOption</p>
	 * <p>说明：添加配置项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	AddConfigOption(){
		//没有配置项信息与配置项顺序号
		COS.AddConfigOptionService(ConfigOptionId, ConfigOptionName, ConfigOptionValue, ConfigOptionExplain);
		//有配置项信息与配置项顺序号
		//COS.AddConfigOptionService(ConfigOptionId, ConfigOptionName, ConfigOptionValue, ConfigOptionExplain,ConfigOptionNum,ConfigOptionInfo);
		return null;
	}
	/**
	 * 
	 * <p>名称：DeleteConfigOption</p>
	 * <p>说明：删除配置项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	DeleteConfigOption(){
		COS.DeleteConfigOptionService(DeleteId);
		return null;
	}
	public	String	UpdateConfigOption(){
		logger.debug(ConfigOptionExplain);
		COS.UpdateConfigOptionService(OldConfigOptionId, ConfigOptionId, ConfigOptionName, ConfigOptionValue, ConfigOptionExplain);
		return null;
	}
	public	String	BulkDeleteConfigOption(){
		COS.BulkDeleteConfigOption(DeleteId);
		return null;
	}
	public	String	getKPInfoByKPId(){
		COS.getKPInfoByKPId(KPIds);
		return null;
	}
}
