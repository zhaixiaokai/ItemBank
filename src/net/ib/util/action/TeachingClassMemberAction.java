
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TeachingClassMemberAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：wuzexi
 * | 创建日期：2012-12-10 下午4:43:15
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-10 下午4:43:15
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.TeachingClassMemberService;


  /**
 * <p>类名：net.ib.util.action.TeachingClassMemberAction </p>
 * <p>描述：对教学班级成员进行操作的Action</p>
 * <p></p>
 */
public class TeachingClassMemberAction {
	private	TeachingClassMemberService teachingClassMemberService;

	/*private	String	SelectCurse;
	private	String	ClassId;
	private	String	ClassName;
	private	String	TeacherId;
	private	String	ClassExplain;
	private	String	TeachingMaterialId;*/
	private	String	DeleteId;
//	private	String	OldClassId;
	private	String  AddUserId;
	private	String  TeachingClassId;
	private	String  CurseId;
	private	String  BulkAddIDs;

	public TeachingClassMemberService getTeachingClassMemberService() {
		return teachingClassMemberService;
	}

	public void setTeachingClassMemberService(
			TeachingClassMemberService teachingClassMemberService) {
		this.teachingClassMemberService = teachingClassMemberService;
	}
	
	public String getDeleteId() {
		return DeleteId;
	}

	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}
	public String getAddUserId() {
		return AddUserId;
	}

	public void setAddUserId(String addUserId) {
		AddUserId = addUserId;
	}

	public String getTeachingClassId() {
		return TeachingClassId;
	}

	public void setTeachingClassId(String teachingClassId) {
		TeachingClassId = teachingClassId;
	}
	
	public String getCurseId() {
		return CurseId;
	}

	public void setCurseId(String curseId) {
		CurseId = curseId;
	}
	
	public String getBulkAddIDs() {
		return BulkAddIDs;
	}

	public void setBulkAddIDs(String bulkAddIDs) {
		BulkAddIDs = bulkAddIDs;
	}

	/**
	 * 
	 * <p>名称：InsertTeachingClassMember</p>
	 * <p>说明：添加教学班级成员</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	InsertTeachingClassMember(){
		teachingClassMemberService.TeachingClassMemberAdd(AddUserId,TeachingClassId,CurseId);
		return null;
	}
	/**
	 * 
	 * <p>名称：DeleteTeachingClass</p>
	 * <p>说明：删除教学班级</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	DeleteTeachingClassMember(){
		teachingClassMemberService.TeachingClassMemberDelete(DeleteId);
		return null;
		
	}
	/**
	 * 
	 * <p>名称：ModifyTeachingClass</p>
	 * <p>说明：修改教学班级信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	/*public String	ModifyTeachingClass(){
		teachingClassServ.TeachingClassUpdate(OldClassId, ClassId, ClassName, TeacherId, TeachingMaterialId,ClassExplain);
		return null;
	}*/
	/**
	 * 
	 * <p>名称：BulkDeleteTeachingClassMember</p>
	 * <p>说明：批量删除</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	BulkDeleteTeachingClassMember(){
		teachingClassMemberService.TeachingClassMemberBulkDelete(DeleteId);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：BulkAddTeachingClassMember</p>
	 * <p>说明：批量添加</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String BulkAddTeachingClassMember(){
		teachingClassMemberService.TeachingClassMemberBulkAdd(BulkAddIDs,TeachingClassId,CurseId);
		return null;
	}
}
