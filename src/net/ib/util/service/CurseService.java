
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	CurseService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：wuzexi
 * | 创建日期：2012-12-6 下午5:10:58
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 下午5:10:58
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.CurseService </p>
 * <p>描述：课程接口</p>
 * <p></p>
 */
public interface CurseService {

	/*public String addTeachMaterial(String teachingMaterialID,String teachingMaterialName,String teachingMaterialVersion,String teachingMaterialAuthor,String teachingMaterialDate,String teachingMaterialHouse,String teachingMaterialCode,String teachingMaterialRemarks) ;*/
	
	public	String	addCurse(String getSpecial,String curseId,String curseName,String curseCredit,String method,String curseRemarks);
	public	String	updateCurse(String oldCurseId,String curseName,String	curseID,String	curseCredit,String radio,String curseRemarks);
	public	String	deleteCurse(String deleteId);
	public	String	bulkDeleteCurse(String	deleteIds);
}
