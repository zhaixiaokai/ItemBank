
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	CurseService.java
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
 * <p>������net.ib.util.service.CurseService </p>
 * <p>�������γ̽ӿ�</p>
 * <p></p>
 */
public interface CurseService {

	/*public String addTeachMaterial(String teachingMaterialID,String teachingMaterialName,String teachingMaterialVersion,String teachingMaterialAuthor,String teachingMaterialDate,String teachingMaterialHouse,String teachingMaterialCode,String teachingMaterialRemarks) ;*/
	
	public	String	addCurse(String getSpecial,String curseId,String curseName,String curseCredit,String method,String curseRemarks);
	public	String	updateCurse(String oldCurseId,String curseName,String	curseID,String	curseCredit,String radio,String curseRemarks);
	public	String	deleteCurse(String deleteId);
	public	String	bulkDeleteCurse(String	deleteIds);
}
