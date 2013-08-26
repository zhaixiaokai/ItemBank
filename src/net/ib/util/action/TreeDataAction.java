
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TreeDataAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-1-7 下午3:44:20
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-1-7 下午3:44:20
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.TreeDataService;


  /**
 * <p>类名：net.ib.util.action.TreeDataAction </p>
 * <p>描述：获取各种树结构的数据</p>
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
	 * <p>名称：getTeacherDepartmentTreeData</p>
	 * <p>说明：获取教师机构树形数据</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	getTeacherDepartmentTreeData(){
		treeDataService.getTeacherDepTreeData("SYS_DEPARTMENT_TREE", "departmentTree");
		return null;
	}
	/**
	 * 
	 * <p>名称：getFunctionTreeData</p>
	 * <p>说明：获取功能树数据</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	getFunctionTreeData(){
		treeDataService.getFunctionTreeData("sys_function_list", "FunctionTree");
		return null;
	}
	/**
	 * 
	 * <p>名称：getStudentDepartmentTreeData</p>
	 * <p>说明：获取学生组织机构树形数据</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	getStudentDepartmentTreeData(){
		treeDataService.getTeacherDepTreeData("SYS_DEPARTMENT_TREE", "StudentDepTree");
		return null;
	}
	
	/**
	 * 
	 * <p>名称：getFunctionChapterTreeData</p>
	 * <p>说明：获取章节树数据</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	getFunctionChapterTreeData(){
		treeDataService.getChapterTreeData("sys_teaching_chapter_tree", MyTeachingMaterialId);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：getFunctionTeachingProgressTreeData</p>
	 * <p>说明：获取教学进度树数据</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String getFunctionTeachingProgressTreeData(){
		treeDataService.getTeacherDepTreeData("sys_progress_teaching", MyCourseId);
		return null;
	}
}
