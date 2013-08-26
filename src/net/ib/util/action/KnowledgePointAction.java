
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	KnowledgePointAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-29 ����2:48:38
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-29 ����2:48:38
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.KnowledgePointService;

import org.apache.log4j.Logger;


  /**
 * <p>������net.ib.util.action.KnowledgePointAction </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class KnowledgePointAction {
	private static Logger logger = Logger.getLogger(KnowledgePointAction.class);
	private	String  message;
	private	String	SelectCurse;
	private	String	KnowledgePointId;
	private	String	KnowledgePointName;
	private	String	KnowledgePointExplain;
	private	String	OldKnowledgePointId;
	private	String	KnowledgeContent;
	private	String	DeleteId;
	private	String  LeafNodeIDs;
	private	String  MyCourseId;
	private	String  selected_point;
	private	String  MyChapterId;
	private	String  MyTermId;
	private	String  MyTeachingMaterialId;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDeleteId() {
		return DeleteId;
	}
	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}
	public String getKnowledgeContent() {
		return KnowledgeContent;
	}
	public void setKnowledgeContent(String knowledgeContent) {
		KnowledgeContent = knowledgeContent;
		//ת��n��n2   \n��n1
		KnowledgeContent=KnowledgeContent.replaceAll("n", "n2");
		KnowledgeContent=KnowledgeContent.replaceAll("\n", "n1");
	}
	public String getOldKnowledgePointId() {
		return OldKnowledgePointId;
	}
	public void setOldKnowledgePointId(String oldKnowledgePointId) {
		OldKnowledgePointId = oldKnowledgePointId;
	}
	private	KnowledgePointService knowledgePointService;
	public KnowledgePointService getKnowledgePointService() {
		return knowledgePointService;
	}
	public void setKnowledgePointService(KnowledgePointService knowledgePointService) {
		this.knowledgePointService = knowledgePointService;
	}
	public String getSelectCurse() {
		return SelectCurse;
	}
	public void setSelectCurse(String selectCurse) {
		SelectCurse = selectCurse;
	}
	public String getKnowledgePointId() {
		return KnowledgePointId;
	}
	public void setKnowledgePointId(String knowledgePointId) {
		KnowledgePointId = knowledgePointId;
	}
	public String getKnowledgePointName() {
		return KnowledgePointName;
	}
	public void setKnowledgePointName(String knowledgePointName) {
		KnowledgePointName = knowledgePointName;
	}
	public String getKnowledgePointExplain() {
		return KnowledgePointExplain;
	}
	public void setKnowledgePointExplain(String knowledgePointExplain) {
		KnowledgePointExplain = knowledgePointExplain;
		//ת��n��n2   \n��n1
		KnowledgePointExplain=KnowledgePointExplain.replaceAll("n", "n2");
		KnowledgePointExplain=KnowledgePointExplain.replaceAll("\n", "n1");
	}
	
	public String getLeafNodeIDs() {
		return LeafNodeIDs;
	}
	public void setLeafNodeIDs(String leafNodeIDs) {
		LeafNodeIDs = leafNodeIDs;
	}
	
	public String getMyCourseId() {
		return MyCourseId;
	}
	public void setMyCourseId(String myCourseId) {
		MyCourseId = myCourseId;
	}
	
	public String getSelected_point() {
		return selected_point;
	}
	public void setSelected_point(String selected_point) {
		this.selected_point = selected_point;
	}
	public String getMyChapterId() {
		return MyChapterId;
	}
	public void setMyChapterId(String myChapterId) {
		MyChapterId = myChapterId;
	}
	
	public String getMyTermId() {
		return MyTermId;
	}
	public void setMyTermId(String myTermId) {
		MyTermId = myTermId;
	}
	
	public String getMyTeachingMaterialId() {
		return MyTeachingMaterialId;
	}
	public void setMyTeachingMaterialId(String myTeachingMaterialId) {
		MyTeachingMaterialId = myTeachingMaterialId;
	}
	/**
	 * 
	 * <p>���ƣ�addKnowledgePoint</p>
	 * <p>˵�������֪ʶ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	addKnowledgePoint(){
		logger.debug(SelectCurse);
		logger.debug(KnowledgePointId);
		logger.debug(KnowledgePointName);
		logger.debug(KnowledgePointExplain);
		knowledgePointService.addKnowledgePoint(SelectCurse, KnowledgePointId, KnowledgePointName, KnowledgePointExplain);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�deleteKnowledgePoint</p>
	 * <p>˵����ɾ��֪ʶ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	deleteKnowledgePoint(){
		knowledgePointService.deleteKonwledgePoint(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�updateKnowledgePoint</p>
	 * <p>˵�����޸�֪ʶ����Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	updateKnowledgePoint(){
		knowledgePointService.updateKnowledgePoint(this.OldKnowledgePointId, this.KnowledgePointName, this.KnowledgePointId, this.KnowledgeContent);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�bulkDeleteKnowledgePoint</p>
	 * <p>˵��������ɾ��֪ʶ����Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	bulkDeleteKnowledgePoint(){
		knowledgePointService.bulkDeleteKnowledgePoint(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�getKnowledgePointList</p>
	 * <p>˵������ȡ�½���֪ʶ����Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String getKnowledgePointList(){
		knowledgePointService.getKnowledgePointListByChapterId(LeafNodeIDs);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�getKnowledgePointListByTermId</p>
	 * <p>˵������ȡѧ����֪ʶ����Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String getKnowledgePointListByTermId(){
		knowledgePointService.getKnowledgePointListByTermId(LeafNodeIDs);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�getAllKnowledgePointList</p>
	 * <p>˵������ȡ�½���֪ʶ����Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String getAllKnowledgePointList(){
		knowledgePointService.getAllKnowledgePointListByCourseId(MyCourseId);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�getKnowledgeListForCheckByTeachingMaterialId</p>
	 * <p>˵������ȡ���̲��´���ѡ��֪ʶ����Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String getKnowledgeListForCheckByTeachingMaterialId(){
		knowledgePointService.getKnowledgeListForCheck(MyTeachingMaterialId);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�getKnowledgeListForCheckByCourseId</p>
	 * <p>˵������ȡ���γ��´���ѡ��֪ʶ����Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String getKnowledgeListForCheckByCourseId(){
		knowledgePointService.getKnowledgeListForCheckByCourseId(MyCourseId);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�SaveKnowledgePointConfigByChapter</p>
	 * <p>˵��������֪ʶ��������Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String SaveKnowledgePointConfigByChapter(){
		knowledgePointService.SaveKnowledgePointConfigByChapter(selected_point, MyChapterId);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�SaveKnowledgePointConfigByTerm</p>
	 * <p>˵��������֪ʶ��������Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String SaveKnowledgePointConfigByTerm(){
		knowledgePointService.SaveKnowledgePointConfigByTerm(selected_point, MyTermId);
		return null;
	}
}
