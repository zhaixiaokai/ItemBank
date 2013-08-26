
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	SystemOptionService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-6 下午2:25:14
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 下午2:25:14
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.SystemOptionService </p>
 * <p>描述：获取系统选项</p>
 * <p></p>
 */
public interface SystemOptionService {
	/**
	 * 
	 * <p>名称：GetDifficultyOptions</p>
	 * <p>说明：获取难度选项</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	GetDifficultyOptions();
	public	String	GetPaperUseOptions();
	public	String	GetTeacherDepartmentOptions();
	public	String	GetSchoolStructureOptions();
	public	String	GetTeacherListByDepartmentIdOptions(String	DepId);
	public	String	GetTeachingMaterialByCurseIdOptions(String	CurseId);
	public	String	GetDataDicOptions();
	public	String	GetStudentDepartmentOptions();
	public	String	GetQuesUseSelect();
	public String GetRoleList();
	public	String	GetTeachingClassByCurseIdOptions(String	CurseId);

}
