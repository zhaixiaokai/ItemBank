
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	RoleAction.java
 * | 包名：net.ib.util.action
 * | 描述：系统角色管理
 * | 作者：HuangJu
 * | 创建日期：2012-12-17 下午3:17:08
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-17 下午3:17:08
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.RoleService;
import com.opensymphony.xwork2.ActionSupport;

  /**
 * <p>类名：net.ib.util.action.RoleAction </p>
 * <p>描述：系统角色管理</p>
 * <p></p>
 */
public class RoleAction extends ActionSupport{
	private String RoleName;
	private String RoleId;
	private String RoleExplain;
	private String DeleteId;
	private RoleService roleService;
	private String OldRoleId;
	
	
	
	public String getRoleName() {
		return RoleName;
	}
	public void setRoleName(String roleName) {
		RoleName = roleName;
	}
	public String getRoleId() {
		return RoleId;
	}
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	public String getRoleExplain() {
		return RoleExplain;
	}
	public void setRoleExplain(String roleExplain) {
		RoleExplain = roleExplain;
		RoleExplain=RoleExplain.replaceAll("n", "n2");
		RoleExplain=RoleExplain.replaceAll("\n", "n1");
	}
	public String getDeleteId() {
		return DeleteId;
	}
	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}
	public String getOldRoleId() {
		return OldRoleId;
	}
	public void setOldRoleId(String oldRoleId) {
		OldRoleId = oldRoleId;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	 /**
	 * <p>类名：net.ib.util.action.RoleAction </p>
	 * <p>描述：添加系统角色</p>
	 * <p></p>
	 */
	public String AddRole(){
		roleService.addRole(RoleName, RoleId, RoleExplain);
		return null;
	}
	
	 /**
	 * <p>类名：net.ib.util.action.RoleAction </p>
	 * <p>描述：删除角色</p>
	 * <p></p>
	 */
	public String DeleteRole(){
		roleService.deleteRole(DeleteId);
		return null;
	}
	 /**
		 * <p>类名：net.ib.util.action.RoleAction </p>
		 * <p>描述：修改角色信息</p>
		 * <p></p>
		 */
	
	public String UpdateRole(){
		roleService.updateRole(OldRoleId, RoleId, RoleName, RoleExplain);
		return null;
	}
	 /**
	 * <p>类名：net.ib.util.action.RoleAction </p>
	 * <p>描述：批量删除角色</p>
	 * <p></p>
	 */
	public String BulkDeleteRole(){
		roleService.BulkDeleteRole(DeleteId);
		return null;
	}

}
