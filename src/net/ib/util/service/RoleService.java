
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	RoleService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2012-12-17 下午3:19:03
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-17 下午3:19:03
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.RoleService </p>
 * <p>描述：系统角色管理接口</p>
 * <p></p>
 */
public interface RoleService {
	
	public String addRole(String RoleName,String RoleID,String RoleExplain);
	public String deleteRole(String RoleID);
	public	String	updateRole(String OldRoleId,String RoleId,String RoleName,String RoleExplain);
	public	String	BulkDeleteRole(String	RoleIds);
	
}
