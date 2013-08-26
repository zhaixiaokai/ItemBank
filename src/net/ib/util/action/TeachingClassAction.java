
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TeachingClassAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-10 下午4:43:15
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-10 下午4:43:15
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.TeachingClassService;


  /**
 * <p>类名：net.ib.util.action.TeachingClassAction </p>
 * <p>描述：对教学班级进行操作的Action</p>
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
		//转义n→n2   \n→n1
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
	 * <p>名称：GetTeacherListByDepartmentIdSelect</p>
	 * <p>说明：通过部门Id获取教师列表</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	InsertTeachingClass(){
		teachingClassServ.TeachingClassAdd(SelectCurse, ClassName, ClassId, TeacherId, ClassExplain,TeachingMaterialId);
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
	public	String	DeleteTeachingClass(){
		teachingClassServ.TeachingClassDelete(DeleteId);
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
	public String	ModifyTeachingClass(){
		teachingClassServ.TeachingClassUpdate(OldClassId, ClassId, ClassName, TeacherId, TeachingMaterialId,ClassExplain);
		return null;
	}
	/**
	 * 
	 * <p>名称：BulkDeleteTeachingClass</p>
	 * <p>说明：批量删除</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	BulkDeleteTeachingClass(){
		teachingClassServ.TeachingClassBulkDelete(DeleteId);
		return null;
	}
}
