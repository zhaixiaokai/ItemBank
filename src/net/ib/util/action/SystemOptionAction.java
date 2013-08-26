
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	SystemOptionsAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-6 ����2:20:47
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-6 ����2:20:47
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.SystemOptionService;


  /**
 * <p>������net.ib.util.action.SystemOptionsAction </p>
 * <p>������ϵͳ���Action�������������ֵ��ȡ�Ѷ���Ϣ��</p>
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
	 * <p>���ƣ�GetDifficultySelect</p>
	 * <p>˵������ȡ�Ѷ�ѡ���select����</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	GetDifficultySelect(){
		SysOptionServcie.GetDifficultyOptions();
		return	null;
	}
	/**
	 * 
	 * <p>���ƣ�GetPaperUseSelect</p>
	 * <p>˵������ȡ������;��select����</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	GetPaperUseSelect(){
		SysOptionServcie.GetPaperUseOptions();
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�GetTeacherDepartmentSelect</p>
	 * <p>˵������ȡ��ʦ����ѡ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	GetTeacherDepartmentSelect(){
		SysOptionServcie.GetTeacherDepartmentOptions();
		return	null;
	}
	/**
	 * 
	 * <p>���ƣ�GetTeacherDepartmentSelect</p>
	 * <p>˵������ȡ��ʦ����ѡ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	GetStudentDepartmentSelect(){
		SysOptionServcie.GetStudentDepartmentOptions();
		return	null;
	}

	/**
	 * 
	 * <p>���ƣ�GetSchoolStructureSelect</p>
	 * <p>˵������ȡѧԺרҵ�ṹ��ѡ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */

	public	String	GetSchoolStructureSelect(){
		SysOptionServcie.GetSchoolStructureOptions();
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�GetTeacherListByDepartmentIdSelect</p>
	 * <p>˵����ͨ������Idѡ��ò����°����Ľ�ʦ��Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	GetTeacherListByDepartmentIdSelect(){
		SysOptionServcie.GetTeacherListByDepartmentIdOptions(this.DepartmentId);
		return	null;
	}
	/**
	 * 
	 * <p>���ƣ�GetTeachingMaterialByCurseIdSelect</p>
	 * <p>˵����ͨ���γ�idѡ��ÿγ���ʹ�õ����н̲��б�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	GetTeachingMaterialByCurseIdSelect(){
		SysOptionServcie.GetTeachingMaterialByCurseIdOptions(CurseId);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�GetDataDicSelect</p>
	 * <p>˵������ȡ�ֵ���ѡ���е�ѡ��ֵ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	GetDataDicSelect(){
		SysOptionServcie.GetDataDicOptions();
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�GetRoleSelect</p>
	 * <p>˵������ȡ�ֵ���ѡ���е�ѡ��ֵ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	GetRoleListSelect(){
		SysOptionServcie.GetRoleList();
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�GetQuesUseSelect</p>
	 * <p>˵������ȡ��������ѡ��ֵ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	GetQuesUseSelect(){
		SysOptionServcie.GetQuesUseSelect();
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�GetTeachingClassByCurseIdSelect</p>
	 * <p>˵����ͨ���γ�idѡ��ÿγ���ʹ�õ����н̲��б�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	GetTeachingClassByCurseIdSelect(){
		SysOptionServcie.GetTeachingClassByCurseIdOptions(CurseId);
		return null;
	}
}
