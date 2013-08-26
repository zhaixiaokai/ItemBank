
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TeacherService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2012-12-6 下午5:33:36
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 下午5:33:36
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

public interface TeacherService {
	  /**
	 * <p>类名：net.ib.util.service.TeacherService </p>
	 * <p>描述：添加教师、删除教师、修改已有教师信息</p>
	 * <p>作者：HuangJu</p>
	 */
	public String addTeacher(String TeacherDepartmentId, String TeacherID, String TeacherName,
			String Birthday,String Gender, String IdetiCard,String Email,String Phone,String Address);
	
	public String DeleteTeacher(String TeacherId);
	
	public String UpdateTeacher(String OldTeacherId,String TeacherID,String TeacherName,String Birthday,String Gender,
			String IdetiCard,String Email,String Phone,String Address );
	public String BulkDeleteTeacher(String TeacherIds);

}
