package net.ib.util.service;
import java.io.File;

public interface QuesManageService {
	public String GetPointData(String leafid);
	public String QuestionCheckValidation(String questiontype,String target_itembank,String difficulty_coefficient,String defaultpoint,String time,String comment,String selectpoint,File ques_attachment,String savePath,String ques_attachmentFileName,String ques_attachmentContentType);
	public String ImportQues(File importQues,String savePath,String importQuesFileName,String importQuesContentType,String quesType);
	public String QuesNumber(String quesFileName,String savePath);
	public String ShowQuesContent(String quesFileName,String savePath,String number);
	public String DeleteQuesHtml(String quesFileName,String savePath);
	public String SaveImportQues(String quesFileName,String savePath,String number,String target_itembank,String difficulty_coefficient,String defaultpoint,String time,String selectpoint,String SelectQuesType);
	public String GetQuesDifficultyOption();
	public String GetCurseId(String itembank_id);
	public String QuesContent(String questionid,String itembank_id,String savePath);
	public String QuesManageGetDiff(String questionid,String itembank_id);
	public String ModifyQuesSave(String difficulty_coefficient,String defaultpoint,String time,String Quesselectpoint,String itembank_id,String modifyid);
	public String QuesManageGetQuesContent(String itembank_id,String questionid,String savePath);
	public String QuesManageDeleteQues(String questionid,String itembank_id);
	public String QuesManageBulkDeleteQues(String deleteid,String itembank_id);
	public String QueswMoveSave(String questionid,String original_itembank,String target_itembank);
	public String QuesOutput(String questionid,String itembank_id,String savePath);
}
