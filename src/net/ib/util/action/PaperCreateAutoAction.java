
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	PaperCreateAuto.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-28 ����5:42:54
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-28 ����5:42:54
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.PaperCreateAutoService;


  /**
 * <p>������net.ib.util.action.PaperCreateAuto </p>
 * <p>��������ȡ�������</p>
 * <p></p>
 */
public class PaperCreateAutoAction {

	String difficulty;//�Ѷ�String difficulty,String targetEPDB,String totalScore,String totalTime,String finCourseId,String useage,String IBList,String XZKPList,String XZKPCount,String PDKPList,String PDKPCount
	String targetEPDB;//Ŀ���Ծ��String TKKPList,String TKKPCount,String JDKPList,String JDKPCount
	String totalScore;//�ܷ�
	String totalTime;//��ʱ��
	String finCourseId;//�γ�id
	String useage;//��;
	String IBList;//������б���","�ָͬ�������id
	String XZKPList;//ѡ��֪ʶ���б�
	String XZKPCount;//ѡ��֪ʶ�����
	String PDKPList;//�ж�֪ʶ���б�
	String PDKPCount;//�ж�֪ʶ�����
	String TKKPList;//���֪ʶ���б�
	String TKKPCount;//���֪ʶ�����
	String JDKPList;//���֪ʶ���б�
	String JDKPCount;//���֪ʶ�����
	PaperCreateAutoService paperCreateAutoService;
	String	IBS;//�����id
	String   IDS;//����id
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
	 * <p>���ƣ�autoPaper</p>
	 * <p>˵������������Ϣ�Զ����</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	autoPaper(){
		paperCreateAutoService.createPaper(difficulty,targetEPDB,totalScore,totalTime,finCourseId,useage,IBList,XZKPList,XZKPCount,PDKPList,PDKPCount,TKKPList,TKKPCount,JDKPList,JDKPCount);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�autoPaperOnHand</p>
	 * <p>˵�����ֶ����</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String autoPaperOnHand(){
		paperCreateAutoService.autoPaperOnHand(this.IBS, this.IDS);
		return null;
	}
}
