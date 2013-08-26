
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TeachingmaterialService.java
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
 * <p>类名：net.ib.util.service.TeachingmaterialService </p>
 * <p>描述：教材接口</p>
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
