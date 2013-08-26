package net.ib.util.action;
import java.io.File;

import org.apache.log4j.Logger;
import com.opensymphony.xwork2.ActionSupport;

import net.ib.util.service.QuesManageService;

public class QuesManageAction extends ActionSupport {
	private String leafid;//课程id
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

	private String ques_selectpoint;//课程id
	private String questiontype;//试题类型
	private String quesKnowPointId;//试题类型
	public String getQuesKnowPointId() {
		return quesKnowPointId;
	}

	public void setQuesKnowPointId(String quesKnowPointId) {
		this.quesKnowPointId = quesKnowPointId;
	}

	private String target_itembank;//试题添加到的试题库
	private String difficulty_coefficient;//试题难度系数
	private String defaultpoint;//试题默认分值
	private String time;//试题预计答题时间
	private String selectpoint;//试题知识点
	private String comment;//试题备注信息
	private File ques_attachment; //上传文件对象

	private File importQues; //上传文件对象
	private String savePath; //上传文件对象
	private String ques_attachmentFileName; //上传文件对象
	private String ques_attachmentContentType; //上传文件对象
	private String importQuesFileName; //上传文件对象
	private String importQuesContentType; //上传文件对象
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
	private QuesManageService QuesManageActionService; // 定义实现类
	

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
	 * <p>名称：GetPointData</p>
	 * <p>说明：获取课程下知识点</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String GetPointData(){
		QuesManageActionService.GetPointData(leafid);
		return null;
	}
	
	/**
	 * 在线添加试题试题合法性验证
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
	 * <p>名称：ImportQues</p>
	 * <p>说明：在线添加试题，试题基本信息保存</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String ImportQues(){
		QuesManageActionService.ImportQues(importQues,savePath,importQuesFileName,importQuesContentType,questiontype);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：QuesNumber</p>
	 * <p>说明：解析试题获取试题数量TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String QuesNumber(){
		QuesManageActionService.QuesNumber(quesFileName,savePath);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：ShowQuesContent</p>
	 * <p>说明：显示试题内容TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String ShowQuesContent(){
		QuesManageActionService.ShowQuesContent(quesFileName,savePath,number);
		return null;
	}
	
	
	/**
	 * 
	 * <p>名称：DeleteQuesHtml</p>
	 * <p>说明：删除试题html文档TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String DeleteQuesHtml(){
		QuesManageActionService.DeleteQuesHtml(quesFileName,savePath);
		return null;
	}
	
	
	/**
	 * 
	 * <p>名称：SaveImportQues</p>
	 * <p>说明：导入试题，试题保存TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SaveImportQues(){
		logger.debug(""+quesFileName+"  "+savePath+"  "+number+"  "+target_itembank+"  "+difficulty_coefficient+"  "+defaultpoint+"  "+time+"  "+ques_selectpoint+"  "+questiontype);
		QuesManageActionService.SaveImportQues(quesFileName,savePath,number,target_itembank,difficulty_coefficient,defaultpoint,time,ques_selectpoint,questiontype);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：GetQuesDifficultyOption</p>
	 * <p>说明：查看试题，获取试题难度等级option-TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String GetQuesDifficultyOption(){
		QuesManageActionService.GetQuesDifficultyOption();
		return null;
	}
	
	/**
	 * 
	 * <p>名称：GetCurseId</p>
	 * <p>说明：-查看试题，由试题库id查找课程id</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String GetCurseId(){
		logger.debug(itembank_id);
		QuesManageActionService.GetCurseId(itembank_id);
		return null;
	}
	
	
	/**
	 * 
	 * <p>名称：QuesContent</p>
	 * <p>说明：查看试题，查看试题内容TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String QuesContent(){
		QuesManageActionService.QuesContent(questionid,itembank_id,savePath);
		return null;
	}
	
	
	/**
	 * 
	 * <p>名称：QuesManageGetDiff</p>
	 * <p>说明：试题管理，获取难度系数TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String QuesManageGetDiff(){
		QuesManageActionService.QuesManageGetDiff(questionid,itembank_id);

		return null;
	}
	
	/**
	 * 
	 * <p>名称：ModifyQuesSave</p>
	 * <p>说明：试题管理，试题基本信息修改保存TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String ModifyQuesSave(){
		QuesManageActionService.ModifyQuesSave(difficulty_coefficient,defaultpoint,time,quesKnowPointId,itembank_id,modifyid);

		return null;
	}
	
	
	/**
	 * 
	 * <p>名称：QuesManageGetQuesContent</p>
	 * <p>说明：试题管理，试题内容修改，获取试题内容-TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String QuesManageGetQuesContent(){
		QuesManageActionService.QuesManageGetQuesContent(itembank_id,questionid,savePath);

		return null;
	}
	
/**
 * 
 * <p>名称：QuesManageDeleteQues</p>
 * <p>说明：试题管理，删除试题TODO(这里用一句话描述这个方法的作用)</p>
 * <p>参数：@return 设定文件</p>
 * <p>返回值：String 返回类型</p>
 * <p>@return</p>
 */
	public String QuesManageDeleteQues(){
		QuesManageActionService.QuesManageDeleteQues(questionid,itembank_id);

		return null;
	}
	
	
	/**
	 * 
	 * <p>名称：QuesManageBulkDeleteQues</p>
	 * <p>说明：批量删除试题TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String QuesManageBulkDeleteQues(){
		QuesManageActionService.QuesManageBulkDeleteQues(deleteid,itembank_id);
		return null;
	}
	
	
	
	/**
	 * 
	 * <p>名称：QueswMoveSave</p>
	 * <p>说明：试题迁移TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String QueswMoveSave(){
		QuesManageActionService.QueswMoveSave(questionid,original_itembank,target_itembank);
		return null;
	}
	
	
	/**
	 * 
	 * <p>名称：QuesOutput</p>
	 * <p>说明：试题导出TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String QuesOutput(){
		QuesManageActionService.QuesOutput(questionid,itembank_id,savePath);

		return null;
	}

}
