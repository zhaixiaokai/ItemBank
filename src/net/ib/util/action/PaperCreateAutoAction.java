
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	PaperCreateAuto.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-28 下午5:42:54
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-28 下午5:42:54
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.PaperCreateAutoService;


  /**
 * <p>类名：net.ib.util.action.PaperCreateAuto </p>
 * <p>描述：获取组卷配置</p>
 * <p></p>
 */
public class PaperCreateAutoAction {

	String difficulty;//难度String difficulty,String targetEPDB,String totalScore,String totalTime,String finCourseId,String useage,String IBList,String XZKPList,String XZKPCount,String PDKPList,String PDKPCount
	String targetEPDB;//目标试卷库String TKKPList,String TKKPCount,String JDKPList,String JDKPCount
	String totalScore;//总分
	String totalTime;//总时间
	String finCourseId;//课程id
	String useage;//用途
	String IBList;//试题库列表，用","分割不同的试题库id
	String XZKPList;//选择知识点列表
	String XZKPCount;//选择知识点个数
	String PDKPList;//判断知识点列表
	String PDKPCount;//判断知识点个数
	String TKKPList;//填空知识点列表
	String TKKPCount;//填空知识点个数
	String JDKPList;//简答知识点列表
	String JDKPCount;//简答知识点个数
	PaperCreateAutoService paperCreateAutoService;
	String	IBS;//试题库id
	String   IDS;//试题id
	public String getIBS() {
		return IBS;
	}
	public void setIBS(String iBS) {
		IBS = iBS;
	}
	public String getIDS() {
		return IDS;
	}
	public void setIDS(String iDS) {
		IDS = iDS;
	}
	public PaperCreateAutoService getPaperCreateAutoService() {
		return paperCreateAutoService;
	}
	public void setPaperCreateAutoService(
			PaperCreateAutoService paperCreateAutoService) {
		this.paperCreateAutoService = paperCreateAutoService;
	}
	public String getPDKPCount() {
		return PDKPCount;
	}
	public void setPDKPCount(String pDKPCount) {
		PDKPCount = pDKPCount;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getTargetEPDB() {
		return targetEPDB;
	}
	public void setTargetEPDB(String targetEPDB) {
		this.targetEPDB = targetEPDB;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getFinCourseId() {
		return finCourseId;
	}
	public void setFinCourseId(String finCourseId) {
		this.finCourseId = finCourseId;
	}
	public String getUseage() {
		return useage;
	}
	public void setUseage(String useage) {
		this.useage = useage;
	}
	public String getIBList() {
		return IBList;
	}
	public void setIBList(String iBList) {
		IBList = iBList;
	}
	public String getXZKPList() {
		return XZKPList;
	}
	public void setXZKPList(String xZKPList) {
		XZKPList = xZKPList;
	}
	public String getXZKPCount() {
		return XZKPCount;
	}
	public void setXZKPCount(String xZKPCount) {
		XZKPCount = xZKPCount;
	}
	public String getPDKPList() {
		return PDKPList;
	}
	public void setPDKPList(String pDKPList) {
		PDKPList = pDKPList;
	}
	public String getTKKPList() {
		return TKKPList;
	}
	public void setTKKPList(String tKKPList) {
		TKKPList = tKKPList;
	}
	public String getTKKPCount() {
		return TKKPCount;
	}
	public void setTKKPCount(String tKKPCount) {
		TKKPCount = tKKPCount;
	}
	public String getJDKPList() {
		return JDKPList;
	}
	public void setJDKPList(String jDKPList) {
		JDKPList = jDKPList;
	}
	public String getJDKPCount() {
		return JDKPCount;
	}
	public void setJDKPCount(String jDKPCount) {
		JDKPCount = jDKPCount;
	}
	/**
	 * 
	 * <p>名称：autoPaper</p>
	 * <p>说明：按配置信息自动组卷</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	autoPaper(){
		paperCreateAutoService.createPaper(difficulty,targetEPDB,totalScore,totalTime,finCourseId,useage,IBList,XZKPList,XZKPCount,PDKPList,PDKPCount,TKKPList,TKKPCount,JDKPList,JDKPCount);
		return null;
	}
	/**
	 * 
	 * <p>名称：autoPaperOnHand</p>
	 * <p>说明：手动组卷</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String autoPaperOnHand(){
		paperCreateAutoService.autoPaperOnHand(this.IBS, this.IDS);
		return null;
	}
}
