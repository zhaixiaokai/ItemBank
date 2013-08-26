
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TreeDataAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-1-7 ����3:44:20
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-1-7 ����3:44:20
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.TreeDataService;


  /**
 * <p>������net.ib.util.action.TreeDataAction </p>
 * <p>��������ȡ�������ṹ������</p>
 * <p></p>
 */
public class TreeDataAction {
	
	private	TreeDataService treeDataService;
	public TreeDataService getTreeDataService() {
		return treeDataService;
	}
	public void setTreeDataService(TreeDataService treeDataService) {
		this.treeDataService = treeDataService;
	}
	
	private String MyTeachingMaterialId;
	public String getMyTeachingMaterialId() {
		return MyTeachingMaterialId;
	}
	public void setMyTeachingMaterialId(String myTeachingMaterialId) {
		MyTeachingMaterialId = myTeachingMaterialId;
	}
	
	private String MyCourseId;
	public String getMyCourseId() {
		return MyCourseId;
	}
	public void setMyCourseId(String myCourseId) {
		MyCourseId = myCourseId;
	}
	/**
	 * 
	 * <p>���ƣ�getTeacherDepartmentTreeData</p>
	 * <p>˵������ȡ��ʦ������������</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	getTeacherDepartmentTreeData(){
		treeDataService.getTeacherDepTreeData("SYS_DEPARTMENT_TREE", "departmentTree");
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�getFunctionTreeData</p>
	 * <p>˵������ȡ����������</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	getFunctionTreeData(){
		treeDataService.getFunctionTreeData("sys_function_list", "FunctionTree");
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�getStudentDepartmentTreeData</p>
	 * <p>˵������ȡѧ����֯������������</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	getStudentDepartmentTreeData(){
		treeDataService.getTeacherDepTreeData("SYS_DEPARTMENT_TREE", "StudentDepTree");
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�getFunctionChapterTreeData</p>
	 * <p>˵������ȡ�½�������</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	getFunctionChapterTreeData(){
		treeDataService.getChapterTreeData("sys_teaching_chapter_tree", MyTeachingMaterialId);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�getFunctionTeachingProgressTreeData</p>
	 * <p>˵������ȡ��ѧ����������</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String getFunctionTeachingProgressTreeData(){
		treeDataService.getTeacherDepTreeData("sys_progress_teaching", MyCourseId);
		return null;
	}
}
