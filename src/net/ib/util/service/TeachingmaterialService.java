
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TeachingmaterialService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�wuzexi
 * | �������ڣ�2012-12-6 ����5:10:58
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-6 ����5:10:58
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.TeachingmaterialService </p>
 * <p>�������̲Ľӿ�</p>
 * <p></p>
 */
public interface TeachingmaterialService {

	/*public String addTeachMaterial(String teachingMaterialID,String teachingMaterialName,String teachingMaterialVersion,String teachingMaterialAuthor,String teachingMaterialDate,String teachingMaterialHouse,String teachingMaterialCode,String teachingMaterialRemarks) ;*/
	public	String	addTeachingMaterial(String curseId,String teachingMaterialID,String teachingMaterialName,String teachingMaterialVersion,String teachingMaterialAuthor,String teachingMaterialDate,String teachingMaterialHouse,String teachingMaterialCode);
	public	String	updateTeachingMaterial(String oldTeachingMaterialId,String teachingMaterialName,String	teachingMaterialID,String	teachingMaterialVersion,String	teachingMaterialAuthor,String teachingMaterialDate,String teachingMaterialHouse,String teachingMaterialCode);
	public	String	deleteTeachingMaterial(String deleteId);
	public	String	bulkDeleteTeachingMaterial(String	deleteIds);
	public	String getTeachMaterialByCourseId(String id);
}
