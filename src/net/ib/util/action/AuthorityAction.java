
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	AuthorityAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJU
 * | �������ڣ�2013-3-11 ����3:33:35
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-11 ����3:33:35
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;
import net.ib.util.service.AuthorityService;

  /**
 * <p>������net.ib.util.action.AuthorityAction </p>
 * <p>��������ָ���Ľ�ɫ������Ȩ</p>
 * <p></p>
 */
public class AuthorityAction {
	private String FunctionId;// �������Ȩ�Ĺ��ܵ�id
	private String RoleId;// ����ɫ��Ȩ����Ҫ��Ȩ�Ľ�ɫid
	private String DepartmentId;//��������Ȩ����Ҫ��Ȩ�Ļ�����id
	private String MemberType;//���û���Ȩ����Ҫ��Ȩ���û�����
	private String UserNumber;//���û���Ȩ����Ҫ��Ȩ���û��Ľ̹��Ż���ѧ��
	private String UserId;//�û���userid
	private String DeleteId;//��ʾҳ����δ����ã��û�����ɫ����������Ȩ��
	private AuthorityService AuthorityService;
	
	
	
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getDeleteId() {
		return DeleteId;
	}
	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}
	public String getMemberType() {
		return MemberType;
	}
	public void setMemberType(String memberType) {
		MemberType = memberType;
	}
	public String getUserNumber() {
		return UserNumber;
	}
	public void setUserNumber(String userNumber) {
		UserNumber = userNumber;
	}
	public String getDepartmentId() {
		return DepartmentId;
	}
	public void setDepartmentId(String departmentId) {
		DepartmentId = departmentId;
	}
	public String getFunctionId() {
		return FunctionId;
	}
	public void setFunctionId(String functionId) {
		FunctionId = functionId;
	}
	public String getRoleId() {
		return RoleId;
	}
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	public AuthorityService getAuthorityService() {
		return AuthorityService;
	}
	public void setAuthorityService(AuthorityService authorityService) {
		AuthorityService = authorityService;
	}
	//����ɫ��Ȩ
	 public String RoleAuthority(){
		 AuthorityService.RoleAuthority(RoleId, FunctionId, DeleteId);
		 return null;
	 }
	 //��ȡ��ɫ��ǰ���еĽ�ɫ
	 public String PresentRoleAuthorityGet(){
		 AuthorityService.PresentRoleAuthorityGet(RoleId);
		 return null;
	 }
	 
	 
	 // ��������Ȩ
	 public String DepartmentAuthority(){
		 AuthorityService.DepartmentAuthority(DepartmentId, FunctionId, DeleteId);
		 return null;
	 }
	 public String PresentDepartmentAuthorityGet(){
		 AuthorityService.PresentDepartmentAuthority(DepartmentId);
		 return null;
	 }
	 
	 // ���û���Ȩ
	 public String UserAuthority(){
		 AuthorityService.UserAuthority(UserId, FunctionId, DeleteId);
		 return null;
	 }
	 //��������ѧ�źͽ̹����Ƿ�Ϸ�
	 public String CheckUserNumber(){
		 AuthorityService.CheckUserNumber(MemberType, UserNumber);
		 return null;
	 }
	 //
	 public String PresentUserAuthorityGet(){
		 AuthorityService.PresentUserAuthority(UserId);
		 return null;
	 }
	 //���û�������Ȩ
	 public String UserMemberAuthority(){
		 AuthorityService.UserMemberAuthority(MemberType, FunctionId, DeleteId);
		 return null;
	 }
	 public String PresentUserTypeAuthorityGet(){
		 AuthorityService.PresentUserTypeAuthority(MemberType);
		 return null;
	 }

	
}
