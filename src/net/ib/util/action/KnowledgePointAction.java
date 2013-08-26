
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	KnowledgePointAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-29 下午2:48:38
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-29 下午2:48:38
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.KnowledgePointService;

import org.apache.log4j.Logger;


  /**
 * <p>类名：net.ib.util.action.KnowledgePointAction </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
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
		//转义n→n2   \n→n1
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
		//转义n→n2   \n→n1
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
	 * <p>名称：addKnowledgePoint</p>
	 * <p>说明：添加知识点</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>名称：deleteKnowledgePoint</p>
	 * <p>说明：删除知识点</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	deleteKnowledgePoint(){
		knowledgePointService.deleteKonwledgePoint(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>名称：updateKnowledgePoint</p>
	 * <p>说明：修改知识点信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	updateKnowledgePoint(){
		knowledgePointService.updateKnowledgePoint(this.OldKnowledgePointId, this.KnowledgePointName, this.KnowledgePointId, this.KnowledgeContent);
		return null;
	}
	/**
	 * 
	 * <p>名称：bulkDeleteKnowledgePoint</p>
	 * <p>说明：批量删除知识点信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	bulkDeleteKnowledgePoint(){
		knowledgePointService.bulkDeleteKnowledgePoint(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>名称：getKnowledgePointList</p>
	 * <p>说明：获取章节下知识点信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String getKnowledgePointList(){
		knowledgePointService.getKnowledgePointListByChapterId(LeafNodeIDs);
		return null;
	}
	/**
	 * 
	 * <p>名称：getKnowledgePointListByTermId</p>
	 * <p>说明：获取学期下知识点信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String getKnowledgePointListByTermId(){
		knowledgePointService.getKnowledgePointListByTermId(LeafNodeIDs);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：getAllKnowledgePointList</p>
	 * <p>说明：获取章节下知识点信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String getAllKnowledgePointList(){
		knowledgePointService.getAllKnowledgePointListByCourseId(MyCourseId);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：getKnowledgeListForCheckByTeachingMaterialId</p>
	 * <p>说明：获取本教材下待勾选的知识点信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String getKnowledgeListForCheckByTeachingMaterialId(){
		knowledgePointService.getKnowledgeListForCheck(MyTeachingMaterialId);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：getKnowledgeListForCheckByCourseId</p>
	 * <p>说明：获取本课程下待勾选的知识点信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String getKnowledgeListForCheckByCourseId(){
		knowledgePointService.getKnowledgeListForCheckByCourseId(MyCourseId);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：SaveKnowledgePointConfigByChapter</p>
	 * <p>说明：保存知识点配置信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String SaveKnowledgePointConfigByChapter(){
		knowledgePointService.SaveKnowledgePointConfigByChapter(selected_point, MyChapterId);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：SaveKnowledgePointConfigByTerm</p>
	 * <p>说明：保存知识点配置信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String SaveKnowledgePointConfigByTerm(){
		knowledgePointService.SaveKnowledgePointConfigByTerm(selected_point, MyTermId);
		return null;
	}
}
