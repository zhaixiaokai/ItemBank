
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	RoleService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2012-12-17 ����3:19:03
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-17 ����3:19:03
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.RoleService </p>
 * <p>������ϵͳ��ɫ����ӿ�</p>
 * <p></p>
 */
public interface RoleService {
	
	public String addRole(String RoleName,String RoleID,String RoleExplain);
	public String deleteRole(String RoleID);
	public	String	updateRole(String OldRoleId,String RoleId,String RoleName,String RoleExplain);
	public	String	BulkDeleteRole(String	RoleIds);
	
}
