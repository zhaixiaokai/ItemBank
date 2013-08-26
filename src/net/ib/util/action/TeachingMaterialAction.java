
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TeachingMaterialAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：wuzexi
 * | 创建日期：2012-12-6 上午9:27:39
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 上午9:27:39
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
 * <p>类名：net.ib.util.action.TeachMaterialAction </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class TeachingMaterialAction extends ActionSupport {
	
/*	private static final Log logger = LogFactory.getLog(TeachingMaterialAction.class);*/
	private static Logger logger = Logger.getLogger(TeachingMaterialAction.class);
	
	private String OldTeachingMaterialId;
	private String TeachingMaterialID;  //教材编号
	private String TeachingMaterialName;  //教材名称
	private String TeachingMaterialVersion; //教材版本
	private String TeachingMaterialAuthor; //教材作者
	private String TeachingMaterialDate;    //教材出版日期
	private String TeachingMaterialHouse;    //教材出版社
	private String TeachingMaterialCode;    //教材ISBN码
	private String TeachingMaterialRemarks;  //教材封面
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
	 * <p>名称：addTeachingMaterial</p>
	 * <p>说明：添加教材</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>名称：deleteTeachingMaterial</p>
	 * <p>说明：删除教材</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	deleteTeachingMaterial(){
		teachingMaterialService.deleteTeachingMaterial(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>名称：updateTeachingMaterial</p>
	 * <p>说明：修改教材信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>名称：bulkDeleteTeachingMaterial</p>
	 * <p>说明：批量删除教材信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
