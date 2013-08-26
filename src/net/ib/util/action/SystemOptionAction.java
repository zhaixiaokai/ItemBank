
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	SystemOptionsAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-6 下午2:20:47
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 下午2:20:47
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.SystemOptionService;


  /**
 * <p>类名：net.ib.util.action.SystemOptionsAction </p>
 * <p>描述：系统向的Action，包括从数据字典获取难度信息等</p>
 * <p></p>
 */
public class SystemOptionAction {
	
	private	SystemOptionService	SysOptionServcie;
	private	String	DepartmentId= null;
	private	String	CurseId;
	public String getCurseId() {
		return CurseId;
	}
	public void setCurseId(String curseId) {
		CurseId = curseId;
	}
	public String getDepartmentId() {
		return DepartmentId;
	}
	public void setDepartmentId(String departmentId) {
		DepartmentId = departmentId;
	}
	public SystemOptionService getSysOptionServcie() {
		return SysOptionServcie;
	}
	public void setSysOptionServcie(SystemOptionService sysOptionServcie) {
		SysOptionServcie = sysOptionServcie;
	}
	/**
	 * 
	 * <p>名称：GetDifficultySelect</p>
	 * <p>说明：获取难度选择的select内容</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	GetDifficultySelect(){
		SysOptionServcie.GetDifficultyOptions();
		return	null;
	}
	/**
	 * 
	 * <p>名称：GetPaperUseSelect</p>
	 * <p>说明：获取试题用途的select内容</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	GetPaperUseSelect(){
		SysOptionServcie.GetPaperUseOptions();
		return null;
	}
	/**
	 * 
	 * <p>名称：GetTeacherDepartmentSelect</p>
	 * <p>说明：获取教师机构选项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	GetTeacherDepartmentSelect(){
		SysOptionServcie.GetTeacherDepartmentOptions();
		return	null;
	}
	/**
	 * 
	 * <p>名称：GetTeacherDepartmentSelect</p>
	 * <p>说明：获取教师机构选项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	GetStudentDepartmentSelect(){
		SysOptionServcie.GetStudentDepartmentOptions();
		return	null;
	}

	/**
	 * 
	 * <p>名称：GetSchoolStructureSelect</p>
	 * <p>说明：获取学院专业结构的选项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */

	public	String	GetSchoolStructureSelect(){
		SysOptionServcie.GetSchoolStructureOptions();
		return null;
	}
	/**
	 * 
	 * <p>名称：GetTeacherListByDepartmentIdSelect</p>
	 * <p>说明：通过部门Id选择该部门下包括的教师信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	GetTeacherListByDepartmentIdSelect(){
		SysOptionServcie.GetTeacherListByDepartmentIdOptions(this.DepartmentId);
		return	null;
	}
	/**
	 * 
	 * <p>名称：GetTeachingMaterialByCurseIdSelect</p>
	 * <p>说明：通过课程id选择该课程所使用的所有教材列表</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	GetTeachingMaterialByCurseIdSelect(){
		SysOptionServcie.GetTeachingMaterialByCurseIdOptions(CurseId);
		return null;
	}
	/**
	 * 
	 * <p>名称：GetDataDicSelect</p>
	 * <p>说明：获取字典项选择中的选项值</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	GetDataDicSelect(){
		SysOptionServcie.GetDataDicOptions();
		return null;
	}
	/**
	 * 
	 * <p>名称：GetRoleSelect</p>
	 * <p>说明：获取字典项选择中的选项值</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	GetRoleListSelect(){
		SysOptionServcie.GetRoleList();
		return null;
	}
	/**
	 * 
	 * <p>名称：GetQuesUseSelect</p>
	 * <p>说明：获取试题类型选项值</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	GetQuesUseSelect(){
		SysOptionServcie.GetQuesUseSelect();
		return null;
	}
	
	/**
	 * 
	 * <p>名称：GetTeachingClassByCurseIdSelect</p>
	 * <p>说明：通过课程id选择该课程所使用的所有教材列表</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	GetTeachingClassByCurseIdSelect(){
		SysOptionServcie.GetTeachingClassByCurseIdOptions(CurseId);
		return null;
	}
}
