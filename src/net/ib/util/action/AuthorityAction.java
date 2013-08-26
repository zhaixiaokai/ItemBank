
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	AuthorityAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJU
 * | 创建日期：2013-3-11 下午3:33:35
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-11 下午3:33:35
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;
import net.ib.util.service.AuthorityService;

  /**
 * <p>类名：net.ib.util.action.AuthorityAction </p>
 * <p>描述：对指定的角色进行授权</p>
 * <p></p>
 */
public class AuthorityAction {
	private String FunctionId;// 点击的授权的功能的id
	private String RoleId;// 按角色授权中需要授权的角色id
	private String DepartmentId;//按机构授权中需要授权的机构的id
	private String MemberType;//按用户授权中需要授权的用户类型
	private String UserNumber;//按用户授权中需要授权的用户的教工号或者学号
	private String UserId;//用户的userid
	private String DeleteId;//表示页面上未授予该（用户、角色、机构）的权限
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
	//按角色授权
	 public String RoleAuthority(){
		 AuthorityService.RoleAuthority(RoleId, FunctionId, DeleteId);
		 return null;
	 }
	 //获取角色当前已有的角色
	 public String PresentRoleAuthorityGet(){
		 AuthorityService.PresentRoleAuthorityGet(RoleId);
		 return null;
	 }
	 
	 
	 // 按机构授权
	 public String DepartmentAuthority(){
		 AuthorityService.DepartmentAuthority(DepartmentId, FunctionId, DeleteId);
		 return null;
	 }
	 public String PresentDepartmentAuthorityGet(){
		 AuthorityService.PresentDepartmentAuthority(DepartmentId);
		 return null;
	 }
	 
	 // 按用户授权
	 public String UserAuthority(){
		 AuthorityService.UserAuthority(UserId, FunctionId, DeleteId);
		 return null;
	 }
	 //检查输入的学号和教工号是否合法
	 public String CheckUserNumber(){
		 AuthorityService.CheckUserNumber(MemberType, UserNumber);
		 return null;
	 }
	 //
	 public String PresentUserAuthorityGet(){
		 AuthorityService.PresentUserAuthority(UserId);
		 return null;
	 }
	 //按用户类型授权
	 public String UserMemberAuthority(){
		 AuthorityService.UserMemberAuthority(MemberType, FunctionId, DeleteId);
		 return null;
	 }
	 public String PresentUserTypeAuthorityGet(){
		 AuthorityService.PresentUserTypeAuthority(MemberType);
		 return null;
	 }

	
}
