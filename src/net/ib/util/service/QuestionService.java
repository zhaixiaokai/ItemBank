
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	QuestionService.java
 * | ������net.ib.util.service
 * | ������������������ܵķ������ӿ�
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-18 ����3:32:20
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-18 ����3:32:20
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.io.File;


  /**
 * <p>������net.ib.util.service.QuestionService </p>
 * <p>����������service�ӿ�</p>
 * <p></p>
 */
public interface  QuestionService {
	public	String	uploadQuestionFile(File DocContent,String  DocContentFileName,String DocContentContentType,String path);
	public	String	QuestionContentSave(File DocContent,String  DocContentFileName,String DocContentContentType,String path,String itembank_id,String questionid);
	//public	String	GetPointData(String subjectid);
}
