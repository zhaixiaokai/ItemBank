
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TeachingClassMemberService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�wuzexi
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
 * <p>������net.ib.util.service.TeachingClassMemberService </p>
 * <p>���������ΰ༶��Ա�����service</p>
 * <p></p>
 */
public interface TeachingClassMemberService {
	public	String	TeachingClassMemberAdd(String addUserId,String teachingClassId,String curseId);
	public	String	TeachingClassMemberDelete(String DelId);
//	public	String	TeachingClassUpdate(String OldClassId,String NewClassId,String NewClassName,String NewTeacherId,String NewTeachingMaterialId,String NewExplain);
	public	String	TeachingClassMemberBulkDelete(String DelIds);
	public	String  TeachingClassMemberBulkAdd(String bulkAddIDs,String teachingClassId,String curseId);
}
