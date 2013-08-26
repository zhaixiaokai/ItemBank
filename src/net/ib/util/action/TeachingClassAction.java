
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TeachingClassAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-10 ����4:43:15
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-10 ����4:43:15
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.TeachingClassService;


  /**
 * <p>������net.ib.util.action.TeachingClassAction </p>
 * <p>�������Խ�ѧ�༶���в�����Action</p>
 * <p></p>
 */
public class TeachingClassAction {
	private	TeachingClassService teachingClassServ;
	private	String	SelectCurse;
	private	String	ClassId;
	private	String	ClassName;
	private	String	TeacherId;
	private	String	ClassExplain;
	private	String	TeachingMaterialId;
	private	String	DeleteId;
	private	String	OldClassId;
	public String getOldClassId() {
		return OldClassId;
	}

	public void setOldClassId(String oldClassId) {
		OldClassId = oldClassId;
	}

	public String getDeleteId() {
		return DeleteId;
	}

	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}

	public String getTeachingMaterialId() {
		return TeachingMaterialId;
	}

	public void setTeachingMaterialId(String teachingMaterialId) {
		TeachingMaterialId = teachingMaterialId;
	}

	public String getSelectCurse() {
		return SelectCurse;
	}

	public void setSelectCurse(String selectCurse) {
		SelectCurse = selectCurse;
	}

	public String getClassId() {
		return ClassId;
	}

	public void setClassId(String classId) {
		ClassId = classId;
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public String getTeacherId() {
		return TeacherId;
	}

	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}

	public String getClassExplain() {
		return ClassExplain;
	}

	public void setClassExplain(String classExplain) {
		ClassExplain = classExplain;
		//ת��n��n2   \n��n1
		ClassExplain=ClassExplain.replaceAll("n", "n2");
		ClassExplain=ClassExplain.replaceAll("\n", "n1");
	}

	public TeachingClassService getTeachingClassServ() {
		return teachingClassServ;
	}

	public void setTeachingClassServ(TeachingClassService teachingClassServ) {
		this.teachingClassServ = teachingClassServ;
	}
	/**
	 * 
	 * <p>���ƣ�GetTeacherListByDepartmentIdSelect</p>
	 * <p>˵����ͨ������Id��ȡ��ʦ�б�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	InsertTeachingClass(){
		teachingClassServ.TeachingClassAdd(SelectCurse, ClassName, ClassId, TeacherId, ClassExplain,TeachingMaterialId);
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
	public	String	DeleteTeachingClass(){
		teachingClassServ.TeachingClassDelete(DeleteId);
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
	public String	ModifyTeachingClass(){
		teachingClassServ.TeachingClassUpdate(OldClassId, ClassId, ClassName, TeacherId, TeachingMaterialId,ClassExplain);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�BulkDeleteTeachingClass</p>
	 * <p>˵��������ɾ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	BulkDeleteTeachingClass(){
		teachingClassServ.TeachingClassBulkDelete(DeleteId);
		return null;
	}
}
