
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TeachingClassService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-10 ����4:46:16
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-10 ����4:46:16
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.TeachingClassService </p>
 * <p>��������ѧ�༶�����service</p>
 * <p></p>
 */
public interface TeachingClassService {
	public	String	TeachingClassAdd(String	courseId,String	className,String classId,String TeacherId,String Explain,String TeachingMaterialId);
	public	String	TeachingClassDelete(String DelId);
	public	String	TeachingClassUpdate(String OldClassId,String NewClassId,String NewClassName,String NewTeacherId,String NewTeachingMaterialId,String NewExplain);
	public	String	TeachingClassBulkDelete(String DelIds);
}
