/**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	StudentScoreService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJU
 * | �������ڣ�2013-3-19 ����11:10:39
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-19 ����11:10:39
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

/**
 * <p>
 * ������net.ib.util.service.StudentScoreService
 * </p>
 * <p>
 * ������ѧ���ɼ��Ĺ���
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
