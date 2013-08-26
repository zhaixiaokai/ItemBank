
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	QuestionsAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-18 下午3:28:41
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-18 下午3:28:41
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import java.io.File;

import org.apache.log4j.Logger;

import net.ib.util.service.QuestionService;
import net.ib.util.service.impl.SortServiceImpl;


  /**
 * <p>类名：net.ib.util.action.QuestionsAction </p>
 * <p>描述：有关试题的Action</p>
 * <p></p>
 */
public class QuestionsAction {
	private static Logger logger = Logger.getLogger(SortServiceImpl.class);
    private File DocContent; //上传文件对象
    private String subjectid; //上传文件对象
    private String savePath; //上传文件对象
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
	private String DocContentFileName; //上传文件名
    private String DocContentContentType; //上传文件MIME类型
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
	 * <p>名称：uploadQuestionFile</p>
	 * <p>说明：上传试题文件</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	uploadQuestionFile(){
		questionService.uploadQuestionFile(this.DocContent,this.DocContentFileName,this.DocContentContentType,savePath);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：QuestionContentSave</p>
	 * <p>说明：TOD试题管理，试题内容修改，保存试题内容O(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
