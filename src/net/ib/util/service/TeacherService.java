
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TeacherService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2012-12-6 ����5:33:36
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-6 ����5:33:36
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

public interface TeacherService {
	  /**
	 * <p>������net.ib.util.service.TeacherService </p>
	 * <p>��������ӽ�ʦ��ɾ����ʦ���޸����н�ʦ��Ϣ</p>
	 * <p>���ߣ�HuangJu</p>
	 */
	public String addTeacher(String TeacherDepartmentId, String TeacherID, String TeacherName,
			String Birthday,String Gender, String IdetiCard,String Email,String Phone,String Address);
	
	public String DeleteTeacher(String TeacherId);
	
	public String UpdateTeacher(String OldTeacherId,String TeacherID,String TeacherName,String Birthday,String Gender,
			String IdetiCard,String Email,String Phone,String Address );
	public String BulkDeleteTeacher(String TeacherIds);

}
