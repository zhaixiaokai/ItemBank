
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	examPaperAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-5-8 ����2:13:55
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-5-8 ����2:13:55
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import java.io.File;

import net.ib.util.service.FileService;
import net.ib.util.service.PaperService;
import net.ib.util.service.impl.FileServiceImpl;
import net.ib.util.service.impl.PaperServiceImpl;


  /**
 * <p>������net.ib.util.action.examPaperAction </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class examPaperAction {
	private String paperId;
	private String paperName;
	private String paperTotalScore;
	private String difficulty_coefficient;
	private String paperTime;
	private String comment;
	private File paperFile;
	private String paperFileFileName; //�ϴ��ļ�����
	private String paperFileContentType; //�ϴ��ļ�����
	
	
	private	File paperAttachment;
	private String paperAttachmentFileName; //�ϴ��ļ�����
	private String paperAttachmentContentType; //�ϴ��ļ�����
	
	
	private	File paperAnswer;
	private String paperAnswerFileName; //�ϴ��ļ�����
	private String paperAnswerContentType; //�ϴ��ļ�����
	public File getPaperAnswer() {
		return paperAnswer;
	}
	public void setPaperAnswer(File paperAnswer) {
		this.paperAnswer = paperAnswer;
	}
	public String getPaperAnswerFileName() {
		return paperAnswerFileName;
	}
	public void setPaperAnswerFileName(String paperAnswerFileName) {
		this.paperAnswerFileName = paperAnswerFileName;
	}
	public String getPaperAnswerContentType() {
		return paperAnswerContentType;
	}
	public void setPaperAnswerContentType(String paperAnswerContentType) {
		this.paperAnswerContentType = paperAnswerContentType;
	}
	public String getPaperFileFileName() {
		return paperFileFileName;
	}
	public void setPaperFileFileName(String paperFileFileName) {
		this.paperFileFileName = paperFileFileName;
	}
	public String getPaperFileContentType() {
		return paperFileContentType;
	}
	public void setPaperFileContentType(String paperFileContentType) {
		this.paperFileContentType = paperFileContentType;
	}
	public String getPaperAttachmentFileName() {
		return paperAttachmentFileName;
	}
	public void setPaperAttachmentFileName(String paperAttachmentFileName) {
		this.paperAttachmentFileName = paperAttachmentFileName;
	}
	public String getPaperAttachmentContentType() {
		return paperAttachmentContentType;
	}
	public void setPaperAttachmentContentType(String paperAttachmentContentType) {
		this.paperAttachmentContentType = paperAttachmentContentType;
	}

	private String course;
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getEDBPID() {
		return EDBPID;
	}
	public void setEDBPID(String eDBPID) {
		EDBPID = eDBPID;
	}

	private String EDBPID;
	
	PaperService paperService;
	public PaperService getPaperService() {
		return paperService;
	}
	public void setPaperService(PaperService paperService) {
		this.paperService = paperService;
	}
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public String getPaperTotalScore() {
		return paperTotalScore;
	}
	public void setPaperTotalScore(String paperTotalScore) {
		this.paperTotalScore = paperTotalScore;
	}
	public String getDifficulty_coefficient() {
		return difficulty_coefficient;
	}
	public void setDifficulty_coefficient(String difficulty_coefficient) {
		this.difficulty_coefficient = difficulty_coefficient;
	}
	public String getPaperTime() {
		return paperTime;
	}
	public void setPaperTime(String paperTime) {
		this.paperTime = paperTime;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public File getPaperFile() {
		return paperFile;
	}
	public void setPaperFile(File paperFile) {
		this.paperFile = paperFile;
	}
	public File getPaperAttachment() {
		return paperAttachment;
	}
	public void setPaperAttachment(File paperAttachment) {
		this.paperAttachment = paperAttachment;
	}
	
	public String addPaper(){
		String type=null;
		if(null!=paperAttachmentFileName){
			type=paperAttachmentFileName.substring(paperAttachmentFileName.lastIndexOf("."));
		}
		paperService.addPaper(paperId, paperName, paperTotalScore, paperTime, difficulty_coefficient, paperFile,paperAnswer, paperAttachment,this.course,this.EDBPID,comment,type);
		return null;
	}
	
	private String PaperSave(){
		FileService	PS	=	new	FileServiceImpl();
		//PS.
		return null;
	}
}
