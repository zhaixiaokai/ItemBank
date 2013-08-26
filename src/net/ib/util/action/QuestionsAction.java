
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	QuestionsAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-18 ����3:28:41
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-18 ����3:28:41
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import java.io.File;

import org.apache.log4j.Logger;

import net.ib.util.service.QuestionService;
import net.ib.util.service.impl.SortServiceImpl;


  /**
 * <p>������net.ib.util.action.QuestionsAction </p>
 * <p>�������й������Action</p>
 * <p></p>
 */
public class QuestionsAction {
	private static Logger logger = Logger.getLogger(SortServiceImpl.class);
    private File DocContent; //�ϴ��ļ�����
    private String subjectid; //�ϴ��ļ�����
    private String savePath; //�ϴ��ļ�����
    public String getItembank_id() {
		return itembank_id;
	}
	public void setItembank_id(String itembank_id) {
		this.itembank_id = itembank_id;
	}
	public String getQuestionid() {
		return questionid;
	}
	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}
	private String itembank_id; 
    private String questionid; 
    public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	private String DocContentFileName; //�ϴ��ļ���
    private String DocContentContentType; //�ϴ��ļ�MIME����
    private	QuestionService	questionService;
    
	public QuestionService getQuestionService() {
		return questionService;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	public File getDocContent() {
		return DocContent;
	}
	public void setDocContent(File docContent) {
		DocContent = docContent;
	}
	public String getDocContentFileName() {
		return DocContentFileName;
	}
	public void setDocContentFileName(String docContentFileName) {
		DocContentFileName = docContentFileName;
	}
	public String getDocContentContentType() {
		return DocContentContentType;
	}
	public void setDocContentContentType(String docContentContentType) {
		DocContentContentType = docContentContentType;
	}
	/**
	 * 
	 * <p>���ƣ�uploadQuestionFile</p>
	 * <p>˵�����ϴ������ļ�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	uploadQuestionFile(){
		questionService.uploadQuestionFile(this.DocContent,this.DocContentFileName,this.DocContentContentType,savePath);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�QuestionContentSave</p>
	 * <p>˵����TOD����������������޸ģ�������������O(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String QuestionContentSave(){
		questionService.QuestionContentSave(this.DocContent,this.DocContentFileName,this.DocContentContentType,savePath,itembank_id,questionid);
		return null;
	}
	public	String	AddQuestion(){
		return null;
	}
	public	String	UpdateQuestion(){
		return null;
	}
	public	String	DeleteQuestion(){
		return null;
	}
	public	String	SelectQuestion(){
		return null;
	}
	/*public	String	GetPointData(){
		logger.debug("*************&*********"+subjectid);
		questionService.GetPointData(subjectid);
		return null;
	}*/
}
