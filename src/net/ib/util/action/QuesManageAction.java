package net.ib.util.action;
import java.io.File;

import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionSupport;

import net.ib.util.service.QuesManageService;

public class QuesManageAction extends ActionSupport {
	private String leafid;//�γ�id
	private String original_itembank;
	public String getOriginal_itembank() {
		return original_itembank;
	}

	public void setOriginal_itembank(String original_itembank) {
		this.original_itembank = original_itembank;
	}

	public String getQues_selectpoint() {
		return ques_selectpoint;
	}

	public void setQues_selectpoint(String ques_selectpoint) {
		this.ques_selectpoint = ques_selectpoint;
	}

	private String ques_selectpoint;//�γ�id
	private String questiontype;//��������
	private String quesKnowPointId;//��������
	public String getQuesKnowPointId() {
		return quesKnowPointId;
	}

	public void setQuesKnowPointId(String quesKnowPointId) {
		this.quesKnowPointId = quesKnowPointId;
	}

	private String target_itembank;//������ӵ��������
	private String difficulty_coefficient;//�����Ѷ�ϵ��
	private String defaultpoint;//����Ĭ�Ϸ�ֵ
	private String time;//����Ԥ�ƴ���ʱ��
	private String selectpoint;//����֪ʶ��
	private String comment;//���ⱸע��Ϣ
	private File ques_attachment; //�ϴ��ļ�����

	private File importQues; //�ϴ��ļ�����
	private String savePath; //�ϴ��ļ�����
	private String ques_attachmentFileName; //�ϴ��ļ�����
	private String ques_attachmentContentType; //�ϴ��ļ�����
	private String importQuesFileName; //�ϴ��ļ�����
	private String importQuesContentType; //�ϴ��ļ�����
	private String message; 
	private String quesFileName; 
	private String number; 
	private String SelectQuesType; 
	private String itembank_id; 
	private String questionid; 
	private String Quesselectpoint; 
	private String modifyid; 
	private String deleteid; 

	public String getDeleteid() {
		return deleteid;
	}

	public void setDeleteid(String deleteid) {
		this.deleteid = deleteid;
	}

	public String getModifyid() {
		return modifyid;
	}

	public void setModifyid(String modifyid) {
		this.modifyid = modifyid;
	}

	public String getQuesselectpoint() {
		return Quesselectpoint;
	}

	public void setQuesselectpoint(String quesselectpoint) {
		Quesselectpoint = quesselectpoint;
	}

	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}

	public String getItembank_id() {
		return itembank_id;
	}

	public void setItembank_id(String itembank_id) {
		this.itembank_id = itembank_id;
	}

	public String getSelectQuesType() {
		return SelectQuesType;
	}

	public void setSelectQuesType(String selectQuesType) {
		SelectQuesType = selectQuesType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getQuesFileName() {
		return quesFileName;
	}

	public void setQuesFileName(String quesFileName) {
		this.quesFileName = quesFileName;
	}

	public File getImportQues() {
		return importQues;
	}

	public void setImportQues(File importQues) {
		this.importQues = importQues;
	}

	public String getImportQuesFileName() {
		return importQuesFileName;
	}

	public void setImportQuesFileName(String importQuesFileName) {
		this.importQuesFileName = importQuesFileName;
	}

	public String getImportQuesContentType() {
		return importQuesContentType;
	}

	public void setImportQuesContentType(String importQuesContentType) {
		this.importQuesContentType = importQuesContentType;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getQues_attachmentFileName() {
		return ques_attachmentFileName;
	}

	public void setQues_attachmentFileName(String ques_attachmentFileName) {
		this.ques_attachmentFileName = ques_attachmentFileName;
	}

	public String getQues_attachmentContentType() {
		return ques_attachmentContentType;
	}

	public void setQues_attachmentContentType(String ques_attachmentContentType) {
		this.ques_attachmentContentType = ques_attachmentContentType;
	}

	public File getQues_attachment() {
		return ques_attachment;
	}

	public void setQues_attachment(File ques_attachment) {
		this.ques_attachment = ques_attachment;
	}

	private static Logger logger = Logger.getLogger(ExamSystemManageAction.class);
	private QuesManageService QuesManageActionService; // ����ʵ����
	

	public String getDifficulty_coefficient() {
		return difficulty_coefficient;
	}

	public void setDifficulty_coefficient(String difficulty_coefficient) {
		this.difficulty_coefficient = difficulty_coefficient;
	}

	public String getDefaultpoint() {
		return defaultpoint;
	}

	public void setDefaultpoint(String defaultpoint) {
		this.defaultpoint = defaultpoint;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSelectpoint() {
		return selectpoint;
	}

	public void setSelectpoint(String selectpoint) {
		this.selectpoint = selectpoint;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	public String getQuestiontype() {
		return questiontype;
	}

	public void setQuestiontype(String questiontype) {
		this.questiontype = questiontype;
	}

	public String getTarget_itembank() {
		return target_itembank;
	}

	public void setTarget_itembank(String target_itembank) {
		this.target_itembank = target_itembank;
	}

	public QuesManageService getQuesManageActionService() {
		return QuesManageActionService;
	}

	public void setQuesManageActionService(QuesManageService quesManageActionService) {
		QuesManageActionService = quesManageActionService;
	}

	public String getLeafid() {
		return leafid;
	}

	public void setLeafid(String leafid) {
		this.leafid = leafid;
	}
	
	/**
	 * 
	 * <p>���ƣ�GetPointData</p>
	 * <p>˵������ȡ�γ���֪ʶ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String GetPointData(){
		QuesManageActionService.GetPointData(leafid);
		return null;
	}
	
	/**
	 * ���������������Ϸ�����֤
	 */
	public String QuestionCheckValidation(){
		logger.debug(questiontype+"   "+target_itembank+"   "+ques_attachmentFileName+"   "+ques_attachmentContentType+"  "+difficulty_coefficient+"   "+defaultpoint+"    "+time+"      "+comment+"    "+ques_selectpoint);
		QuesManageActionService.QuestionCheckValidation(questiontype,target_itembank,difficulty_coefficient,defaultpoint,time,comment,ques_selectpoint,ques_attachment,savePath,ques_attachmentFileName,ques_attachmentContentType);
		//logger.debug("%%%%%%%%%%^^^^^^^^^%%%%%%%%%%%%%"+message);
		return null;
		//return "message";
	}
	
	/**
	 * 
	 * <p>���ƣ�ImportQues</p>
	 * <p>˵��������������⣬���������Ϣ����</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String ImportQues(){
		QuesManageActionService.ImportQues(importQues,savePath,importQuesFileName,importQuesContentType,questiontype);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�QuesNumber</p>
	 * <p>˵�������������ȡ��������TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String QuesNumber(){
		QuesManageActionService.QuesNumber(quesFileName,savePath);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�ShowQuesContent</p>
	 * <p>˵������ʾ��������TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String ShowQuesContent(){
		QuesManageActionService.ShowQuesContent(quesFileName,savePath,number);
		return null;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�DeleteQuesHtml</p>
	 * <p>˵����ɾ������html�ĵ�TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String DeleteQuesHtml(){
		QuesManageActionService.DeleteQuesHtml(quesFileName,savePath);
		return null;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�SaveImportQues</p>
	 * <p>˵�����������⣬���Ᵽ��TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String SaveImportQues(){
		logger.debug(""+quesFileName+"  "+savePath+"  "+number+"  "+target_itembank+"  "+difficulty_coefficient+"  "+defaultpoint+"  "+time+"  "+ques_selectpoint+"  "+questiontype);
		QuesManageActionService.SaveImportQues(quesFileName,savePath,number,target_itembank,difficulty_coefficient,defaultpoint,time,ques_selectpoint,questiontype);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�GetQuesDifficultyOption</p>
	 * <p>˵�����鿴���⣬��ȡ�����Ѷȵȼ�option-TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String GetQuesDifficultyOption(){
		QuesManageActionService.GetQuesDifficultyOption();
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�GetCurseId</p>
	 * <p>˵����-�鿴���⣬�������id���ҿγ�id</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String GetCurseId(){
		logger.debug(itembank_id);
		QuesManageActionService.GetCurseId(itembank_id);
		return null;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�QuesContent</p>
	 * <p>˵�����鿴���⣬�鿴��������TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String QuesContent(){
		QuesManageActionService.QuesContent(questionid,itembank_id,savePath);
		return null;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�QuesManageGetDiff</p>
	 * <p>˵�������������ȡ�Ѷ�ϵ��TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String QuesManageGetDiff(){
		QuesManageActionService.QuesManageGetDiff(questionid,itembank_id);

		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�ModifyQuesSave</p>
	 * <p>˵��������������������Ϣ�޸ı���TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String ModifyQuesSave(){
		QuesManageActionService.ModifyQuesSave(difficulty_coefficient,defaultpoint,time,quesKnowPointId,itembank_id,modifyid);

		return null;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�QuesManageGetQuesContent</p>
	 * <p>˵��������������������޸ģ���ȡ��������-TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String QuesManageGetQuesContent(){
		QuesManageActionService.QuesManageGetQuesContent(itembank_id,questionid,savePath);

		return null;
	}
	
/**
 * 
 * <p>���ƣ�QuesManageDeleteQues</p>
 * <p>˵�����������ɾ������TODO(������һ�仰�����������������)</p>
 * <p>������@return �趨�ļ�</p>
 * <p>����ֵ��String ��������</p>
 * <p>@return</p>
 */
	public String QuesManageDeleteQues(){
		QuesManageActionService.QuesManageDeleteQues(questionid,itembank_id);

		return null;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�QuesManageBulkDeleteQues</p>
	 * <p>˵��������ɾ������TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String QuesManageBulkDeleteQues(){
		QuesManageActionService.QuesManageBulkDeleteQues(deleteid,itembank_id);
		return null;
	}
	
	
	
	/**
	 * 
	 * <p>���ƣ�QueswMoveSave</p>
	 * <p>˵��������Ǩ��TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String QueswMoveSave(){
		QuesManageActionService.QueswMoveSave(questionid,original_itembank,target_itembank);
		return null;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�QuesOutput</p>
	 * <p>˵�������⵼��TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String QuesOutput(){
		QuesManageActionService.QuesOutput(questionid,itembank_id,savePath);

		return null;
	}

}
