
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	StudentService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2012-12-17 ����2:31:39
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-17 ����2:31:39
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.StudentService </p>
 * <p>������ѧ������ӿ�</p>
 * <p></p>
 */
public interface StudentService {

		public String addStudent(String StudentDepartmentId,String StudentID,String StudentName,String Address,String TelPhone);
		public String DeleteStudent(String StudentId);
		public String UpdateStudent(String OldStudentId,String StudentID,String StudentName,String Address,String TelPhone );
		public String BulkDeleteStudent(String StudentIds);
}
