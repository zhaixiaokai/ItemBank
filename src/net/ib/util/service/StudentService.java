
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	StudentService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2012-12-17 下午2:31:39
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-17 下午2:31:39
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.StudentService </p>
 * <p>描述：学生管理接口</p>
 * <p></p>
 */
public interface StudentService {

		public String addStudent(String StudentDepartmentId,String StudentID,String StudentName,String Address,String TelPhone);
		public String DeleteStudent(String StudentId);
		public String UpdateStudent(String OldStudentId,String StudentID,String StudentName,String Address,String TelPhone );
		public String BulkDeleteStudent(String StudentIds);
}
