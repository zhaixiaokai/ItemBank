
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	AuthorityService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2013-3-11 下午3:39:50
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-11 下午3:39:50
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.AuthorityService </p>
 * <p>描述：按角色、用户、机构、成员类型授权</p>
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
