
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ConfigOptionAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-14 ����2:24:58
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-14 ����2:24:58
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.ConfigOptionService;
import org.apache.log4j.Logger;


  /**
 * <p>������net.ib.util.action.ConfigOptionAction </p>
 * <p>��������������ص�Action</p>
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
		//ת��n��n2   \n��n1
		ConfigOptionValue=ConfigOptionValue.replaceAll("n", "n2");
		ConfigOptionValue=ConfigOptionValue.replaceAll("\n", "n1");
	}
	public String getConfigOptionExplain() {
		return ConfigOptionExplain;
	}
	public void setConfigOptionExplain(String configOptionExplain) {
		ConfigOptionExplain = configOptionExplain;
		//ת��n��n2   \n��n1
		ConfigOptionExplain=ConfigOptionExplain.replaceAll("n", "n2");
		ConfigOptionExplain=ConfigOptionExplain.replaceAll("\n", "n1");
	}
	/**
	 * 
	 * <p>���ƣ�AddConfigOption</p>
	 * <p>˵�������������</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	AddConfigOption(){
		//û����������Ϣ��������˳���
		COS.AddConfigOptionService(ConfigOptionId, ConfigOptionName, ConfigOptionValue, ConfigOptionExplain);
		//����������Ϣ��������˳���
		//COS.AddConfigOptionService(ConfigOptionId, ConfigOptionName, ConfigOptionValue, ConfigOptionExplain,ConfigOptionNum,ConfigOptionInfo);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�DeleteConfigOption</p>
	 * <p>˵����ɾ��������</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
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
