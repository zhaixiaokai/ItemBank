
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	RoleAction.java
 * | ������net.ib.util.action
 * | ������ϵͳ��ɫ����
 * | ���ߣ�HuangJu
 * | �������ڣ�2012-12-17 ����3:17:08
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-17 ����3:17:08
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.RoleService;
import com.opensymphony.xwork2.ActionSupport;

  /**
 * <p>������net.ib.util.action.RoleAction </p>
 * <p>������ϵͳ��ɫ����</p>
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
	 * <p>������net.ib.util.action.RoleAction </p>
	 * <p>���������ϵͳ��ɫ</p>
	 * <p></p>
	 */
	public String AddRole(){
		roleService.addRole(RoleName, RoleId, RoleExplain);
		return null;
	}
	
	 /**
	 * <p>������net.ib.util.action.RoleAction </p>
	 * <p>������ɾ����ɫ</p>
	 * <p></p>
	 */
	public String DeleteRole(){
		roleService.deleteRole(DeleteId);
		return null;
	}
	 /**
		 * <p>������net.ib.util.action.RoleAction </p>
		 * <p>�������޸Ľ�ɫ��Ϣ</p>
		 * <p></p>
		 */
	
	public String UpdateRole(){
		roleService.updateRole(OldRoleId, RoleId, RoleName, RoleExplain);
		return null;
	}
	 /**
	 * <p>������net.ib.util.action.RoleAction </p>
	 * <p>����������ɾ����ɫ</p>
	 * <p></p>
	 */
	public String BulkDeleteRole(){
		roleService.BulkDeleteRole(DeleteId);
		return null;
	}

}
