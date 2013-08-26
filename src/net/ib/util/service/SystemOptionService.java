
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	SystemOptionService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-6 ����2:25:14
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-6 ����2:25:14
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.SystemOptionService </p>
 * <p>��������ȡϵͳѡ��</p>
 * <p></p>
 */
public interface SystemOptionService {
	/**
	 * 
	 * <p>���ƣ�GetDifficultyOptions</p>
	 * <p>˵������ȡ�Ѷ�ѡ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
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
