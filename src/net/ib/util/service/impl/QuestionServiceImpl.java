
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	QuestionServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-18 ����3:39:44
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-18 ����3:39:44
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.FileService;
import net.ib.util.service.QuestionService;
import net.ib.util.service.Service;

  /**
 * <p>������net.ib.util.service.impl.QuestionServiceImpl </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class QuestionServiceImpl implements QuestionService {
	private static Logger logger = Logger.getLogger(SortServiceImpl.class);

    private	FileService	fs;
    private Service service;
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public FileService getFs() {
		return fs;
	}
	public void setFs(FileService fs) {
		this.fs = fs;
	}
	/* (��-Javadoc)
	 * <p>����: uploadQuestionFile</p>
	 * <p>˵��: </p>
	 * @return
	 * @see net.ib.util.service.QuestionService#uploadQuestionFile()
	 */
	@Override
	public String uploadQuestionFile(File DocContent,String  DocContentFileName,String DocContentContentType,String path) {
		// TODO Auto-generated method stub
		String savepath = ServletActionContext.getServletContext().getRealPath(path); //�ļ�����·��
		Boolean	flag	=	fs.FileUpload(savepath+'\\',"xiaokai", DocContent, DocContentFileName, DocContentContentType);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if(flag){
			try {
				PrintWriter pw = response.getWriter();
				pw.print("succeed");
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				PrintWriter pw = response.getWriter();
				pw.print("failed");
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//�������������������
	public String QuestionContentSave(File DocContent,String  DocContentFileName,String DocContentContentType,String path,String itembank_id,String questionid) {
		// TODO Auto-generated method stub
		String savepath = ServletActionContext.getServletContext().getRealPath(path); //�ļ�����·��
		UUID newdocuuid = UUID.randomUUID();
		String newdocname = newdocuuid.toString();
		Boolean	flag	=	fs.FileUpload(savepath+'\\',newdocname, DocContent, DocContentFileName, DocContentContentType);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		if(flag){
			try {
				PrintWriter pw = response.getWriter();
				pw.print("succeed");
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			try {
				PrintWriter pw = response.getWriter();
				pw.print("failed");
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//�ĵ����浽�����
		String TableName = "QT_BLOBFIELD_" + itembank_id + "";
		String IdField = "question_id";
		String FieldName = "questioncontent";
		File file4 = new File(savepath+'\\'+ newdocname + ".doc");
		InputStream in = null;
		// ��ȡ�����ļ���
		try {
			in = new FileInputStream(file4);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		service.exercuteInsertWithBlob(TableName, IdField, questionid,
				FieldName, in);
		return null;
	}
	
	/*//��ȡ֪ʶ��
	public String GetPointData(String subjectid){
		String sql="select * from sys_knowledge_point where curse_id='"+subjectid+"'";
		logger.debug("&&&&&&&&**&&&&&&&&"+sql);
		List<Map> list = dao.executeQuery("select * from sys_knowledge_point where curse_id='"+subjectid+"'");
		Service si = new ServiceImpl();
		String Json = si.DataListToJson(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(Json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/

}
