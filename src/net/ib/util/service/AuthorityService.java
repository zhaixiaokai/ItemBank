
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	AuthorityService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2013-3-11 ����3:39:50
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-11 ����3:39:50
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.AuthorityService </p>
 * <p>����������ɫ���û�����������Ա������Ȩ</p>
 * <p></p>
 */
public interface AuthorityService {
	public String RoleAuthority( String RoleId, String FunctionIds,String DeleteId);
	public String PresentRoleAuthorityGet(String RoleId);
	public String DepartmentAuthority(String DepartmentId ,String FunctionIds,String DeleteId);
	public String PresentDepartmentAuthority(String DepartmentId);
	public String UserAuthority(String UserId, String FunctionIds ,String DeleteId);
	public String CheckUserNumber(String MemberType , String UserNumber);
	public String PresentUserAuthority(String UserId);
	public String UserMemberAuthority(String MemberType, String FunctionIds, String DeleteId);
	public String PresentUserTypeAuthority(String MemberType);
}
