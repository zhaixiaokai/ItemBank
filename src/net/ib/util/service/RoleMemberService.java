
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	RoleMemberService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2013-2-28 ����2:46:14
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-2-28 ����2:46:14
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.RoleMemberService </p>
 * <p>��������ӽ�ɫ��Ա��ɾ��ָ����ɫ����ĳ�Ա</p>
 * <p></p>
 */
public interface RoleMemberService {
	public String addRoleMember(String RoleListId , String DepartmentId,String BulkMemberId);
	public String deleteRoleMember(String DeleteId);
	public String bulkDeleteRoleMember(String DeleteIds);

}
