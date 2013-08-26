
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	KnowledgePointService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-29 下午2:53:42
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-29 下午2:53:42
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.KnowledgePointService </p>
 * <p>描述：定义知识点管理的接口</p>
 * <p></p>
 */
public interface KnowledgePointService {
	public	String	addKnowledgePoint(String curseId,String KnowledgePointId,String KnowledgePointName,String KnowledgePointExplain);
	public	String	updateKnowledgePoint(String	OldId,String KnowledgePointName,String	KnowledgePointId,String	KnowledgePointExplain);
	public	String	deleteKonwledgePoint(String	deleteId);
	public	String	bulkDeleteKnowledgePoint(String	deleteIds);
	public	String	getKnowledgePointListByChapterId(String	leafNodeIDs);
	public  String  getKnowledgePointListByTermId(String leafNodeIDs);
	public	String  getAllKnowledgePointListByCourseId(String myCourseId);
	public	String  getKnowledgeListForCheck(String myTeachingMaterialId);
	public	String  getKnowledgeListForCheckByCourseId(String myCourseId);
	public	String  SaveKnowledgePointConfigByChapter(String selected_point, String MyChapterId);
	public	String  SaveKnowledgePointConfigByTerm(String selected_point,String myTermId);
}
