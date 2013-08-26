
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TeachingClassMemberAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�wuzexi
 * | �������ڣ�2012-12-10 ����4:43:15
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-10 ����4:43:15
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.TeachingClassMemberService;


  /**
 * <p>������net.ib.util.action.TeachingClassMemberAction </p>
 * <p>�������Խ�ѧ�༶��Ա���в�����Action</p>
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
	 * <p>���ƣ�InsertTeachingClassMember</p>
	 * <p>˵������ӽ�ѧ�༶��Ա</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	InsertTeachingClassMember(){
		teachingClassMemberService.TeachingClassMemberAdd(AddUserId,TeachingClassId,CurseId);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�DeleteTeachingClass</p>
	 * <p>˵����ɾ����ѧ�༶</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	DeleteTeachingClassMember(){
		teachingClassMemberService.TeachingClassMemberDelete(DeleteId);
		return null;
		
	}
	/**
	 * 
	 * <p>���ƣ�ModifyTeachingClass</p>
	 * <p>˵�����޸Ľ�ѧ�༶��Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	/*public String	ModifyTeachingClass(){
		teachingClassServ.TeachingClassUpdate(OldClassId, ClassId, ClassName, TeacherId, TeachingMaterialId,ClassExplain);
		return null;
	}*/
	/**
	 * 
	 * <p>���ƣ�BulkDeleteTeachingClassMember</p>
	 * <p>˵��������ɾ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	BulkDeleteTeachingClassMember(){
		teachingClassMemberService.TeachingClassMemberBulkDelete(DeleteId);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�BulkAddTeachingClassMember</p>
	 * <p>˵�����������</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String BulkAddTeachingClassMember(){
		teachingClassMemberService.TeachingClassMemberBulkAdd(BulkAddIDs,TeachingClassId,CurseId);
		return null;
	}
}
