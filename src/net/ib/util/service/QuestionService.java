
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	QuestionService.java
 * | 包名：net.ib.util.service
 * | 描述：定义试题管理功能的服务程序接口
 * | 作者：xiaokai
 * | 创建日期：2012-12-18 下午3:32:20
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-18 下午3:32:20
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.io.File;


  /**
 * <p>类名：net.ib.util.service.QuestionService </p>
 * <p>描述：试题service接口</p>
 * <p></p>
 */
public interface  QuestionService {
	public	String	uploadQuestionFile(File DocContent,String  DocContentFileName,String DocContentContentType,String path);
	public	String	QuestionContentSave(File DocContent,String  DocContentFileName,String DocContentContentType,String path,String itembank_id,String questionid);
	//public	String	GetPointData(String subjectid);
}
