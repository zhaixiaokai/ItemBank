
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	RoleMemberService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2013-2-28 下午2:46:14
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-2-28 下午2:46:14
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.RoleMemberService </p>
 * <p>描述：添加角色成员、删除指定角色下面的成员</p>
 * <p></p>
 */
public interface RoleMemberService {
	public String addRoleMember(String RoleListId , String DepartmentId,String BulkMemberId);
	public String deleteRoleMember(String DeleteId);
	public String bulkDeleteRoleMember(String DeleteIds);

}
