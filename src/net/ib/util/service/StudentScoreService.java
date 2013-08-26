/**
 * -------------------------------------------------------------------------------------
 * | 文件名：	StudentScoreService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJU
 * | 创建日期：2013-3-19 上午11:10:39
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-19 上午11:10:39
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

/**
 * <p>
 * 类名：net.ib.util.service.StudentScoreService
 * </p>
 * <p>
 * 描述：学生成绩的管理
 * </p>
 * <p>
 * </p>
 */
public interface StudentScoreService {
	public String DeleteStudentScoreOnTeachingClass(String DeleteId,
			String selectedCurseId);

	public String UpdateStudentScoreOnTeachingClass(String SchoolID,
			String Score, String selectedCurseId, String oldSchoolID,
			String SelectedTeachingClassId);

	public String DeleteStudentScoreOnClass(String DeleteId,
			String selectedCurseId);

	public String UpdateStudentScoreOnClass(String SchoolID,
			String Score, String selectedCurseId, String oldSchoolID,
			String SelectedClassId);
}
