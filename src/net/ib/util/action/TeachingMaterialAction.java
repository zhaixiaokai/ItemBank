
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TeachingMaterialAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�wuzexi
 * | �������ڣ�2012-12-6 ����9:27:39
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-6 ����9:27:39
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;
import com.opensymphony.xwork2.ActionSupport;

import net.ib.util.service.TeachingmaterialService;

import org.apache.log4j.Logger;

import net.ib.util.service.impl.TeachingmaterialServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

  /**
 * <p>������net.ib.util.action.TeachMaterialAction </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class TeachingMaterialAction extends ActionSupport {
	
/*	private static final Log logger = LogFactory.getLog(TeachingMaterialAction.class);*/
	private static Logger logger = Logger.getLogger(TeachingMaterialAction.class);
	
	private String OldTeachingMaterialId;
	private String TeachingMaterialID;  //�̲ı��
	private String TeachingMaterialName;  //�̲�����
	private String TeachingMaterialVersion; //�̲İ汾
	private String TeachingMaterialAuthor; //�̲�����
	private String TeachingMaterialDate;    //�̲ĳ�������
	private String TeachingMaterialHouse;    //�̲ĳ�����
	private String TeachingMaterialCode;    //�̲�ISBN��
	private String TeachingMaterialRemarks;  //�̲ķ���
	private	String	SelectCurse;
	private	String	DeleteId;
	private	TeachingmaterialService teachingMaterialService;
	

	
	
	public String getOldTeachingMaterialId() {
		return OldTeachingMaterialId;
	}
	public void setOldTeachingMaterialId(String oldTeachingMaterialId) {
		OldTeachingMaterialId = oldTeachingMaterialId;
	}
	public String getTeachingMaterialID() {
		return TeachingMaterialID;
	}
	public void setTeachingMaterialID(String teachingMaterialID) {
		TeachingMaterialID = teachingMaterialID;
	}
	public String getTeachingMaterialName() {
		return TeachingMaterialName;
	}
	public void setTeachingMaterialName(String teachingMaterialName) {
		TeachingMaterialName = teachingMaterialName;
	}
	public String getTeachingMaterialVersion() {
		return TeachingMaterialVersion;
	}
	public void setTeachingMaterialVersion(String teachingMaterialVersion) {
		TeachingMaterialVersion = teachingMaterialVersion;
	}
	public String getTeachingMaterialAuthor() {
		return TeachingMaterialAuthor;
	}
	public void setTeachingMaterialAuthor(String teachingMaterialAuthor) {
		TeachingMaterialAuthor = teachingMaterialAuthor;
	}
	public String getTeachingMaterialDate() {
		return TeachingMaterialDate;
	}
	public void setTeachingMaterialDate(String teachingMaterialDate) {
		TeachingMaterialDate = teachingMaterialDate;
	}
	public String getTeachingMaterialHouse() {
		return TeachingMaterialHouse;
	}
	public void setTeachingMaterialHouse(String teachingMaterialHouse) {
		TeachingMaterialHouse = teachingMaterialHouse;
	}
	public String getTeachingMaterialCode() {
		return TeachingMaterialCode;
	}
	public void setTeachingMaterialCode(String teachingMaterialCode) {
		TeachingMaterialCode = teachingMaterialCode;
	}
	public String getTeachingMaterialRemarks() {
		return TeachingMaterialRemarks;
	}
	public void setTeachingMaterialRemarks(String teachingMaterialRemarks) {
		TeachingMaterialRemarks = teachingMaterialRemarks;
	}
	public String getSelectCurse() {
		return SelectCurse;
	}
	public void setSelectCurse(String selectCurse) {
		SelectCurse = selectCurse;
	}
	public String getDeleteId() {
		return DeleteId;
	}
	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}

	public TeachingmaterialService getTeachingMaterialService() {
		return teachingMaterialService;
	}
	public void setTeachingMaterialService(
			TeachingmaterialService teachingMaterialService) {
		this.teachingMaterialService = teachingMaterialService;
	}
	/**
	 * 
	 * <p>���ƣ�addTeachingMaterial</p>
	 * <p>˵������ӽ̲�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	addTeachingMaterial(){
		logger.debug(SelectCurse);
		logger.debug(TeachingMaterialID);
		logger.debug(TeachingMaterialName);
		logger.debug(TeachingMaterialAuthor);
		teachingMaterialService.addTeachingMaterial(SelectCurse, TeachingMaterialID, TeachingMaterialName, TeachingMaterialVersion,TeachingMaterialAuthor,
				                                    TeachingMaterialDate,TeachingMaterialHouse,TeachingMaterialCode);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�deleteTeachingMaterial</p>
	 * <p>˵����ɾ���̲�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	deleteTeachingMaterial(){
		teachingMaterialService.deleteTeachingMaterial(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�updateTeachingMaterial</p>
	 * <p>˵�����޸Ľ̲���Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	updateTeachingMaterial(){
		/*logger.debug("__________________________________");
		logger.debug(OldTeachingMaterialId);*/
		teachingMaterialService.updateTeachingMaterial(this.OldTeachingMaterialId, this.TeachingMaterialName, this.TeachingMaterialID, this.TeachingMaterialVersion,this.TeachingMaterialAuthor,
				                                      this.TeachingMaterialDate,this.TeachingMaterialHouse,this.TeachingMaterialCode);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�bulkDeleteTeachingMaterial</p>
	 * <p>˵��������ɾ���̲���Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	bulkDeleteTeachingMaterial(){
		teachingMaterialService.bulkDeleteTeachingMaterial(DeleteId);
		return null;
	}
	
	public	String	getTeachMaterialByCourseId(){
		this.teachingMaterialService.getTeachMaterialByCourseId(this.SelectCurse);
		return null;
		
	}
}
