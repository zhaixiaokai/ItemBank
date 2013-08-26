
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	KnowledgePointService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-29 ����2:53:42
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-29 ����2:53:42
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.KnowledgePointService </p>
 * <p>����������֪ʶ�����Ľӿ�</p>
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
