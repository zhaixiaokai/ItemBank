package net.ib.util.service.impl;

import net.ib.common.ConnPool;
import net.ib.config.WordBeanProperty;
import net.ib.util.service.QuesManageService;
import net.ib.util.dao.Dao;
import net.ib.util.service.Service;
import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import oracle.sql.BLOB;
import oracle.sql.Datum;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import net.ib.util.service.impl.JacobWordBeanServiceImpl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.fileupload.*;
import java.util.UUID;
import com.jacob.com.Dispatch;

public class QuesManageServiceImpl implements QuesManageService {
	private static Logger logger = Logger.getLogger(SortServiceImpl.class);
	private Dao di;
	private Service service;



	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Dao getDi() {
		return di;
	}

	public void setDi(Dao di) {
		this.di = di;
	}
	
/**
 * �����������֪ʶ���ȡ
 */
	public String GetPointData(String leafid) {
		List<Map> list = di
				.executeQuery("select * from sys_knowledge_point where curse_id='"
						+ leafid + "'");
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
	}

/**
 * ���������������Ϸ�����֤
 */
	 
	public String QuestionCheckValidation(String questiontype,
			String target_itembank, String difficulty_coefficient,
			String defaultpoint, String time, String comment,
			String selectpoint, File ques_attachment, String savePath,
			String ques_attachmentFileName, String ques_attachmentContentType) {
		logger.debug(selectpoint);
		String path = ServletActionContext.getServletContext().getRealPath(savePath); //�ļ�����·��
		String result = "";
		// ������������Ϣ���
		JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
		UUID newdocuuid = UUID.randomUUID();
		String newdocname = newdocuuid.toString();
		wordBean.CreateDoc(newdocname,path);// ����һ�����ļ�
		wordBean.OpenFile(path+'\\' + newdocname + ".doc");
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
		wordBean.copyTableFromAnotherDoc(path+'\\'+"xiaokai.doc", 1);
		wordBean.moveEnd();
		wordBean.InsertText("------END------");
		wordBean.findTextAndCopy("------END------");
		wordBean.moveStart();
		wordBean.replaceAllText("------END------", " ");
		UUID savedocuuid = UUID.randomUUID();
		String savedocname = savedocuuid.toString();
		wordBean.saveFileAs(path+'\\' + savedocname + ".doc");// *******�����һ����񣬱�����棬��Ȼ����ǰ̨�Ƿ񱣴�word�����޷�ִ��ɾ������
		// ��������������Ƿ���ȷ
		int rowCount = wordBean.gettableRow();// �������
		int colCount = wordBean.gettableColumn();// �������
		int cols1 = wordBean.getCols(1);// ��һ������
		int cols2 = wordBean.getCols(2);// �ڶ�������
		if (rowCount != 2 || colCount != 4 || cols1 != 2 || cols2 != 4) {
			result = "���������Ϣ����ʽ��������ģ������±༭";
			print(result);
			return null;
		}
		String cell12 = wordBean.getTxtFromCell(1, 1, 2);// ʱ��
		String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";// ��֤ʱ���ʽ�Ƿ���ȷ
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(cell12);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			result = "ʱ���ʽ����ȷ";
			print(result);
			return null;
		}
		String cell24 = wordBean.getTxtFromCell(1, 2, 4);// �������߹���֤��
		List<Map> list = di
				.executeQuery("select * from sys_user_teacher where school_id='"
						+ cell24 + "'");
		if (list.size() < 1) {
			result = "�������߹���֤���������󣬲��޴��ˣ�����������";
			print(result);
			return null;
		}
		wordBean.closeWord();
		File file = new File(path+'\\' + newdocname + ".doc");
		file.delete();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File file2 = new File( path+'\\' + savedocname + ".doc");
		file2.delete();
		
		// ����������ݱ��
		// �ж�����
		// ����ѡ����
		UUID docuuid = UUID.randomUUID();
		String docname = docuuid.toString();
		if (questiontype.equals("xuanze")) {
			JacobWordBeanServiceImpl wordBean1 = new JacobWordBeanServiceImpl();
			UUID contentdocuuid = UUID.randomUUID();
			String newcontentname = contentdocuuid.toString();
			wordBean1.CreateDoc(newcontentname,path);// ����һ�����ļ�
			wordBean1.OpenFile(path+'\\' + newcontentname + ".doc");
			wordBean1.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean1.copyTableFromAnotherDoc(path+'\\'+"xiaokai.doc", 2);
			wordBean1.moveEnd();
			wordBean1.InsertText("------END------");
			wordBean1.findTextAndCopy("------END------");
			wordBean1.moveStart();
			wordBean1.replaceAllText("------END------", " ");
			wordBean1.saveFileAs(path+'\\' + docname + ".doc");
			int Table2rowCount = wordBean1.gettableRow();// �������
			int Table2colCount = wordBean1.gettableColumn();// �������
			int Table2cols1 = wordBean1.getCols(1);// ��һ������
			int Table2cols2 = wordBean1.getCols(2);// �ڶ�������
			int Table2cols3 = wordBean1.getCols(3);// ����������
			int Table2cols4 = wordBean1.getCols(4);// ����������
			if (Table2rowCount != 4 || Table2colCount != 2 || Table2cols1 != 1
					|| Table2cols2 != 2 || Table2cols3 != 2||Table2cols4!=2) {
				result = "�������ݱ���ʽ��������ģ������±༭";
				print(result);
				return null;
			}
			wordBean1.closeWord();
			// ɾ���ĵ�
			logger.debug("  " + newdocname + "  "
					+ savedocname + "  " + newcontentname
					+ "  " + docname);
			File file1 = new File(path+'\\' + newcontentname + ".doc");
			file1.delete();
		} else {
			JacobWordBeanServiceImpl wordBean1 = new JacobWordBeanServiceImpl();
			UUID contentdocuuid = UUID.randomUUID();
			String newcontentname = contentdocuuid.toString();
			wordBean1.CreateDoc(newcontentname,path);// ����һ�����ļ�
			wordBean1.OpenFile(path+'\\' + newcontentname + ".doc");
			wordBean1.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean1.copyTableFromAnotherDoc(path+'\\'+"xiaokai.doc", 2);
			wordBean1.moveEnd();
			wordBean1.InsertText("------END------");
			wordBean1.findTextAndCopy("------END------");
			wordBean1.moveStart();
			wordBean1.replaceAllText("------END------", " ");
			wordBean1.saveFileAs(path+'\\' + docname + ".doc");
			int Table2rowCount = wordBean1.gettableRow();// �������
			int Table2colCount = wordBean1.gettableColumn();// �������
			int Table2cols1 = wordBean1.getCols(1);// ��һ������
			int Table2cols2 = wordBean1.getCols(2);// �ڶ�������
			int Table2cols3 = wordBean1.getCols(3);// ����������
			if (Table2rowCount != 3 || Table2colCount != 2 || Table2cols1 != 1
					|| Table2cols2 != 2 || Table2cols3 != 2) {
				result = "�������ݱ���ʽ��������ģ������±༭";
				print(result);
				return null;
			}
			wordBean1.closeWord();
			// ɾ���ĵ�
			File file1 = new File(path+'\\' + newcontentname + ".doc");
			file1.delete();
		}
		// ����༭��Ա��Ϣ��ʱ��,�������ͱ���
		UUID questionuuid = UUID.randomUUID();
		String questionid = questionuuid.toString();
		di.execute("insert into QT_BASICFIELD_"
				+ target_itembank
				+ " fields(question_id,EDITOR_ID,TIME,QUESTION_TYPE,DIFFICULTY,defaultpoint,time_use,explain,knowledge_point_id) values('"
				+ questionid + "','" + cell24 + "'," + "to_date('" + cell12
				+ "','yyyy-MM-dd')" + ",'" + questiontype + "',"
				+ difficulty_coefficient + "," + defaultpoint + "," + time
				+ ",'" + comment + "','" + selectpoint + "')");
		// �������ݱ���
		di.execute("insert into QT_BLOBFIELD_" + target_itembank
				+ "   values('" + questionid + "',empty_blob(),empty_blob())");// ���������
		String TableName = "QT_BLOBFIELD_" + target_itembank + "";
		String IdField = "question_id";
		String FieldName = "questioncontent";
		File file4 = new File(path+'\\'+ docname + ".doc");
		InputStream in = null;
		// ��ȡ�����ļ���
		try {
			in = new FileInputStream(file4);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		service.exercuteInsertWithBlob(TableName, IdField, questionid,
				FieldName, in);
		// ɾ�������ĵ�
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		file4.delete();

		// ���⸽������
		if (ques_attachmentFileName != null) {
			File Dir = new File(path);
			if (!Dir.exists())
				Dir.mkdir();
			UUID uploadfile = UUID.randomUUID();
			String uploadfilename = uploadfile.toString();
			int index = ques_attachmentFileName.lastIndexOf(".");
			String UiqueFileName = "";
			UiqueFileName = uploadfilename
					+ ques_attachmentFileName.substring(index);// ��������
			FileInputStream fis;
			try {
				fis = new FileInputStream(ques_attachment);
				FileOutputStream fos;
				fos = new FileOutputStream(new File(Dir, UiqueFileName));
				byte[] buffer = new byte[400];
				int length = 0;
				while ((length = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, length);
				}
				fis.close();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// �����⸽��д�����ݿ�
			File upload = new File(path + '\\' + UiqueFileName);
			InputStream upfile = null;
			try {
				upfile = new FileInputStream(upload);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			service.exercuteInsertWithBlob(TableName, IdField, questionid,
					"uploadfile", upfile);
			
			//ɾ�����⸽��
			File fileupfile = new File(path + '\\' + UiqueFileName);
			fileupfile.delete();
		}
		result = "��ӳɹ�";
		print(result);

		return null;
	}

	
	/**
	 * �������������ĵ�����
	 */
	
	public String ImportQues(File importQues,String savePath,String importQuesFileName,String importQuesContentType,String quesType){
		String path=ServletActionContext.getServletContext().getRealPath(savePath);		//��ȡ�����ϴ��ļ�����ʵ·
		File Dir=new File(path);
		if(!Dir.exists())
			Dir.mkdir();
		UUID uploadfile = UUID.randomUUID(); 
		String uploadfilename=uploadfile.toString();
		logger.debug(importQuesFileName);
	    int index=importQuesFileName.lastIndexOf(".");
		String UiqueFileName="";
        UiqueFileName=uploadfilename+importQuesFileName.substring(index);//��������
        FileInputStream fis;
		try {
			fis = new FileInputStream(importQues);
			FileOutputStream fos;
			fos = new FileOutputStream(new File(Dir,UiqueFileName));
			  byte[] buffer = new byte[400];
	            int length = 0 ;
	            while((length = fis.read(buffer)) > 0){
	                fos.write(buffer, 0, length);
	            }
	            fis.close();
	            fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		//��������ļ��Ƿ����Ҫ��
		String validationResult=QuesFileValidation(path, UiqueFileName, quesType);
		if(validationResult!=null){
			String resultString="�ļ���ʽ����";
			print(resultString);
		}
		print(UiqueFileName);
		return null;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�print</p>
	 * <p>˵������ǰ̨���ؽ��TODO(������һ�仰�����������������)</p>
	 * <p>������@param string
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@param string
	 * <p>@return</p>
	 */
	public String print(String string){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(string);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�QuesNumber</p>
	 * <p>˵�������������ȡ��������TODO(������һ�仰�����������������)</p>
	 * <p>������@param string
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@param string
	 * <p>@return</p>
	 */
	public String QuesNumber(String quesFileName,String savePath){
		String path=ServletActionContext.getServletContext().getRealPath(savePath);		//��ȡ�����ϴ��ļ�����ʵ·
		JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
		wordBean.OpenFile(path + '\\' + quesFileName);
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE); 
		int Number =wordBean.CountTable();
		wordBean.closeWord();
		String tableNmuber=Integer.toString(Number);
		logger.debug(tableNmuber);
		print(tableNmuber);
		return null;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�ShowQuesContent</p>
	 * <p>˵������ʾ��������TODO(������һ�仰�����������������)</p>
	 * <p>������@param string
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@param string
	 * <p>@return</p>
	 */
	public String ShowQuesContent(String quesFileName,String savePath,String number){
		String path=ServletActionContext.getServletContext().getRealPath(savePath);		
		int quesNumber=Integer.parseInt(number)+1;
		JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
		//wordBean.OpenFile(path + '\\' + quesFileName);
		UUID newdocuuid = UUID.randomUUID();
		String newdocname = newdocuuid.toString();
		wordBean.CreateDoc(newdocname,path);// ����һ�����ļ�
		wordBean.OpenFile(path + '\\' + newdocname + ".doc");
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
		wordBean.copyTableFromAnotherDoc(path + '\\' + quesFileName, quesNumber);
		UUID savedocuuid = UUID.randomUUID();
		String savedocname = savedocuuid.toString();
		wordBean.moveEnd();
		wordBean.InsertText("------END------");
		wordBean.findTextAndCopy("------END------");
		wordBean.moveStart();
		wordBean.replaceAllText("------END------", " ");
		wordBean.saveFileAs(path + '\\' + savedocname + ".doc");
		wordBean.wordToHtml(path + '\\' + savedocname + ".doc", path + '\\' + savedocname + ".html");
		wordBean.closeWord();
		File file = new File(path + '\\' + newdocname + ".doc");
		file.delete();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logger.debug(newdocname+"   "+savedocname);
		File savefile = new File(path + '\\' + savedocname + ".doc");
		savefile.delete();
		print(savedocname);
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
	public String DeleteQuesHtml(String quesFileName,String savePath){
		String path=ServletActionContext.getServletContext().getRealPath(savePath);	
		File file = new File(path + '\\' + quesFileName + ".html");
		file.delete();
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
	public String SaveImportQues(String quesFileName,String savePath,String number,String target_itembank,String difficulty_coefficient,String defaultpoint,String time,String selectpoint,String SelectQuesType){
		String path=ServletActionContext.getServletContext().getRealPath(savePath);	
		//��ȡ����༭��Ա��Ϣ
		JacobWordBeanServiceImpl wordBean1 = new JacobWordBeanServiceImpl();
		UUID editorDocId = UUID.randomUUID();
		String editorDocName = editorDocId.toString();
		wordBean1.CreateDoc(editorDocName,path);// ����һ�����ļ�
		wordBean1.OpenFile(path+'\\' + editorDocName + ".doc");
		wordBean1.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
		wordBean1.copyTableFromAnotherDoc(path + '\\' + quesFileName,1);
		UUID saveEditorDocId = UUID.randomUUID();
		String saveEditorDocName = saveEditorDocId.toString();
		wordBean1.moveEnd();
		wordBean1.InsertText("------END------");
		wordBean1.findTextAndCopy("------END------");
		wordBean1.moveStart();
		wordBean1.replaceAllText("------END------", " ");
		wordBean1.saveFileAs(path+'\\' + saveEditorDocName + ".doc");// *******�����һ����񣬱�����棬��Ȼ����ǰ̨�Ƿ񱣴�word�����޷�ִ��ɾ������
		// ��ȡ����༭ʱ��͹���֤��
		String editTime = wordBean1.getTxtFromCell(1, 1, 2);// ʱ��
		String eidtorId = wordBean1.getTxtFromCell(1, 2, 4);// �������߹���֤��
		wordBean1.closeWord();
		File fileEditorDoc = new File(path+'\\' + editorDocName + ".doc");
		fileEditorDoc.delete();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File saveEditorDoc = new File( path+'\\' + saveEditorDocName + ".doc");
		saveEditorDoc.delete();
		
		//��ȡ��������
		String result=null;
		int quesNumber=Integer.parseInt(number)+1;
		JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
		UUID newdocuuid = UUID.randomUUID();
		String newdocname = newdocuuid.toString();
		wordBean.CreateDoc(newdocname,path);// ����һ�����ļ�
		wordBean.OpenFile(path + '\\' + newdocname + ".doc");
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
		wordBean.copyTableFromAnotherDoc(path + '\\' + quesFileName, quesNumber);
		UUID savedocuuid = UUID.randomUUID();
		String savedocname = savedocuuid.toString();
		wordBean.moveEnd();
		wordBean.InsertText("------END------");
		wordBean.findTextAndCopy("------END------");
		wordBean.moveStart();
		wordBean.replaceAllText("------END------", " ");
		wordBean.saveFileAs(path + '\\' + savedocname + ".doc");
		wordBean.closeWord();
		File file = new File(path + '\\' + newdocname + ".doc");
		file.delete();
		//��������
		UUID questionuuid = UUID.randomUUID();
		String questionid = questionuuid.toString();
		di.execute("insert into QT_BASICFIELD_"
				+ target_itembank
				+ " fields(question_id,EDITOR_ID,TIME,QUESTION_TYPE,DIFFICULTY,defaultpoint,time_use,explain,knowledge_point_id) values('"
				+ questionid + "','"+eidtorId+"',to_date('" + editTime
				+ "','yyyy-MM-dd') ,'" + SelectQuesType + "',"
				+ difficulty_coefficient + "," + defaultpoint + "," + time
				+ ",'','" + selectpoint + "')");
		logger.debug("insert into QT_BASICFIELD_"
				+ target_itembank
				+ " fields(question_id,EDITOR_ID,TIME,QUESTION_TYPE,DIFFICULTY,defaultpoint,time_use,explain,knowledge_point_id) values('"
				+ questionid + "','"+eidtorId+"'," + "to_date('" + editTime
				+ "','yyyy-MM-dd')" + ",'" + SelectQuesType + "',"
				+ difficulty_coefficient + "," + defaultpoint + "," + time
				+ ",'','" + selectpoint + "')");
		// �������ݱ���
		di.execute("insert into QT_BLOBFIELD_" + target_itembank
				+ "   values('" + questionid + "',empty_blob(),empty_blob())");// ���������
		String TableName = "QT_BLOBFIELD_" + target_itembank + "";
		String IdField = "question_id";
		String FieldName = "questioncontent";
		File file4 = new File(path+'\\'+ savedocname + ".doc");
		InputStream in = null;
		// ��ȡ�����ļ���
		try {
			in = new FileInputStream(file4);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		service.exercuteInsertWithBlob(TableName, IdField, questionid,
				FieldName, in);
		file4.delete();
		result = "����ɹ�";
		print(result);
		return null;
	}
	
	

	/**
	 * 
	 * <p>���ƣ�GetQuesDifficultyOption</p>
	 * <p>˵�����鿴���⣬��ȡ�����Ѷȵȼ�option</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String GetQuesDifficultyOption(){
		List<Map> list = di
				.executeQuery("select * from sys_dictionary_entries_value where dictionary_entries_id='difficulty' order by sno asc");
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
	}
	
	/**
	 * 
	 * <p>���ƣ�GetCurseId</p>
	 * <p>˵����-�鿴���⣬�������id���ҿγ�id</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String GetCurseId(String itembank_id){
		logger.debug(itembank_id);
		List<Map> listcurse = (List<Map>) di.executeQuery("select * from sys_itembank_list where itembank_id='"+ itembank_id + "' ");
		String curse_id=(String) listcurse.get(0).get("curse_id");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			logger.debug(curse_id);
			PrintWriter pw = response.getWriter();
			pw.print(curse_id);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public String QuesContent(String questionid,String itembank_id,String savePath){
		String TableName="QT_BLOBFIELD_"+itembank_id+"";
		InputStream in=service.getBlobInputStreamFromTable(TableName,"question_id",questionid,"questioncontent");
		String path=ServletActionContext.getServletContext().getRealPath(savePath);	
		UUID questioncontentuuid = UUID.randomUUID();
		String QuesContentDoc = questioncontentuuid.toString();
		File f=new File(path+'\\'+ QuesContentDoc + ".doc");
		//��io��ת�����ļ�
		  try {
			  OutputStream out=new FileOutputStream(f);
			  byte buf[]=new byte[1024];
			  int len;
			  while((len=in.read(buf))>0)
			  out.write(buf,0,len);
			  out.close();
			  in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  //��wordת��Ϊhtml�ļ�
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.OpenFile(path + '\\' + QuesContentDoc + ".doc");
			wordBean.SetVisible(WordBeanProperty.WORD_VISABLE); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			wordBean.wordToHtml(path + '\\' + QuesContentDoc + ".doc", path + '\\' + QuesContentDoc + ".html");
			wordBean.closeWord();
			File file = new File(path + '\\' + QuesContentDoc + ".doc");
			file.delete();
			print(QuesContentDoc);
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
	public String QuesManageGetDiff(String questionid,String itembank_id){
		List<Map> Queslist = (List<Map>) di.executeQuery("select * from QT_BASICFIELD_"+itembank_id+" where question_id='"+ questionid + "' ");
		String diff_coefficient=(String) Queslist.get(0).get("difficulty");
		diff_coefficient="0"+diff_coefficient+"";
		logger.debug(diff_coefficient);
		print(diff_coefficient);
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
	public String ModifyQuesSave(String difficulty_coefficient,String defaultpoint,String time,String Quesselectpoint,String itembank_id,String modifyid){
		String result="";
		di.executeQuery("update QT_BASICFIELD_"+itembank_id+" set defaultpoint='"+defaultpoint+"',time_use='"+time+"',difficulty='"+difficulty_coefficient+"',knowledge_point_id='"+Quesselectpoint+"' where question_id='"+ modifyid + "' ");
		result="�޸ĳɹ�";
		print(result);
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
	public String QuesManageGetQuesContent(String itembank_id,String questionid,String savePath){
		String TableName="QT_BLOBFIELD_"+itembank_id+"";
		InputStream in=service.getBlobInputStreamFromTable(TableName,"question_id",questionid,"questioncontent");
		this.WriteIO(in);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�WriteIO</p>
	 * <p>˵������������io�����</p>
	 * <p>������@param in
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��Boolean ��������</p>
	 * <p>@param in
	 * <p>@return</p>
	 */
	public Boolean	WriteIO(InputStream in){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			java.io.OutputStream outStream = response.getOutputStream();
			byte[] buf = new byte[1024];
			int bytes = 0;
			while ((bytes = in.read(buf)) != -1)
			outStream.write(buf, 0, bytes);
			in.close();
			outStream.close();
		} catch (Throwable e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	/**
	 * 
	 * <p>���ƣ�QuesManageDeleteQues</p>
	 * <p>˵�����������ɾ������TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String QuesManageDeleteQues(String questionid,String itembank_id){
		String result="";
		di.executeQuery("delete from QT_BASICFIELD_"+itembank_id+"  where question_id='"+ questionid + "' ");
		di.executeQuery("delete from QT_BLOBFIELD_"+itembank_id+"  where question_id='"+ questionid + "' ");
		result="ɾ���ɹ�";
		print(result);
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
	public String QuesManageBulkDeleteQues(String deleteid,String itembank_id){
		String[] array = deleteid.split(" ");
		//������Ϣɾ��
		String Sql = "delete from QT_BASICFIELD_"+itembank_id+" where";
		String result = "";
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				Sql += " or";
			}
			Sql += " question_id='" + array[i] + "'";
		}
		//blob����ɾ��
		String SqlBlob = "delete from QT_BLOBFIELD_"+itembank_id+" where";
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				SqlBlob += " or";
			}
			SqlBlob += " question_id='" + array[i] + "'";
		}
		di.executeQuery(Sql);
		di.executeQuery(SqlBlob);
		result="ɾ���ɹ�";
		print(result);
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
	public String QueswMoveSave(String questionid,String original_itembank,String target_itembank){
		String BasicSql="insert into QT_BASICFIELD_"+target_itembank+" SELECT * FROM QT_BASICFIELD_"+original_itembank+" WHERE QUESTION_ID='"+questionid+"'";
		String BlobSql="insert into QT_BLOBFIELD_"+target_itembank+" SELECT * FROM QT_BLOBFIELD_"+original_itembank+" WHERE QUESTION_ID='"+questionid+"'";
		di.executeQuery(BasicSql);
		di.executeQuery(BlobSql);
		di.executeQuery("delete from QT_BASICFIELD_"+original_itembank+"  where question_id='"+ questionid + "' ");
		di.executeQuery("delete from QT_BLOBFIELD_"+original_itembank+"  where question_id='"+ questionid + "' ");
		String result="Ǩ�Ƴɹ�";
		print(result);
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
	public String QuesOutput(String questionid,String itembank_id,String savePath){
		String TableName="QT_BLOBFIELD_"+itembank_id+"";
		InputStream in=service.getBlobInputStreamFromTable(TableName,"question_id",questionid,"questioncontent");
		String path=ServletActionContext.getServletContext().getRealPath(savePath);	
		UUID questioncontentuuid = UUID.randomUUID();
		String QuesContentDoc = questioncontentuuid.toString();
		File f=new File(path+'\\'+ QuesContentDoc + ".doc");
		//��io��ת�����ļ�
		  try {
			  OutputStream out=new FileOutputStream(f);
			  byte buf[]=new byte[1024];
			  int len;
			  while((len=in.read(buf))>0)
			  out.write(buf,0,len);
			  out.close();
			  in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
			print(QuesContentDoc);
		
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�QuesFileValidation</p>
	 * <p>˵���������ļ���֤TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String QuesFileValidation(String path,String fileName,String questiontype){
	//String path = ServletActionContext.getServletContext().getRealPath(savePath); //�ļ�����·��
	//	logger.debug("*********************7777**********"+path+'\\' + fileName);
		String result = "";
		JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
		//wordBean.OpenFile(path+'\\' + fileName);
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
	
		
		//������������Ϣ���
		UUID basicInfoTableId = UUID.randomUUID();
		String basicInfoTableName = basicInfoTableId.toString();
		wordBean.CreateDoc(basicInfoTableName,path);
		wordBean.OpenFile(path + '\\' + basicInfoTableName + ".doc");
		wordBean.copyTableFromAnotherDoc(path+'\\' + fileName, 1);
		UUID saveBasicInfoTableId = UUID.randomUUID();
		String saveBasicInfoTableName = saveBasicInfoTableId.toString();
		wordBean.moveEnd();
		wordBean.InsertText("------END------");
		wordBean.findTextAndCopy("------END------");
		wordBean.moveStart();
		wordBean.replaceAllText("------END------", " ");
		wordBean.saveFileAs(path+'\\' + saveBasicInfoTableName + ".doc");// *******�����һ����񣬱�����棬��Ȼ����ǰ̨�Ƿ񱣴�word�����޷�ִ��ɾ������
		int rowCount = wordBean.gettableRow();// �������
		int colCount = wordBean.gettableColumn();// �������
		int cols1 = wordBean.getCols(1);// ��һ������
		int cols2 = wordBean.getCols(2);// �ڶ�������
		if (rowCount != 2 || colCount != 4 || cols1 != 2 || cols2 != 4) {
			result = "�����ļ���ʽ��������������ģ���޸ĺ������ϴ������ļ�";
			return result;
		}
		String editTime = wordBean.getTxtFromCell(1, 1, 2);// ʱ��
		String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";// ��֤ʱ���ʽ�Ƿ���ȷ
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(editTime);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			result = "�����ļ���ʽ��������������ģ���޸ĺ������ϴ������ļ�";
			return result;
		}
		String quesEditorId = wordBean.getTxtFromCell(1, 2, 4);// �������߹���֤��
		List<Map> list = di
				.executeQuery("select * from sys_user_teacher where school_id='"
						+ quesEditorId + "'");
		if (list.size() < 1) {
			result = "�����ļ���ʽ��������������ģ���޸ĺ������ϴ������ļ�";
			wordBean.closeWord();
			return result;
		}
		wordBean.closeWord();
		File file = new File(path+'\\' + basicInfoTableName + ".doc");
		file.delete();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File file2 = new File( path+'\\' + saveBasicInfoTableName + ".doc");
		file2.delete();
		
		//����������Ŀ
		JacobWordBeanServiceImpl wordBean2 = new JacobWordBeanServiceImpl();
		wordBean2.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
		wordBean2.OpenFile(path+'\\' + fileName);
		int Number =wordBean2.CountTable();
		wordBean2.closeWord();

		//����������ݱ���Ƿ���ȷ
		for(int i=2;i<=Number;i++){
			JacobWordBeanServiceImpl wordBean1 = new JacobWordBeanServiceImpl();
			wordBean1.SetVisible(true); // �Ƿ�ǰ̨��word ���򣬻��ߺ�̨����
			UUID newdocuuid = UUID.randomUUID();
			String newdocname = newdocuuid.toString();
			wordBean1.CreateDoc(newdocname,path);
			wordBean1.OpenFile(path+'\\' + newdocname);
			wordBean1.copyTableFromAnotherDoc(path+'\\' + fileName, i);
			UUID savedocuuid = UUID.randomUUID();
			String savedocname = savedocuuid.toString();
			wordBean1.moveEnd();
			wordBean1.InsertText("------END------");
			wordBean1.findTextAndCopy("------END------");
			wordBean1.moveStart();
			wordBean1.replaceAllText("------END------", " ");
			wordBean1.saveFileAs(path+'\\' + savedocname + ".doc");// *******�����һ����񣬱�����棬��Ȼ����ǰ̨�Ƿ񱣴�word�����޷�ִ��ɾ������
			// ����ѡ����
			UUID docuuid = UUID.randomUUID();
			String docname = docuuid.toString();
			if (questiontype.equals("xuanze")) {
				int Table2rowCount = wordBean1.gettableRow();// �������
				int Table2colCount = wordBean1.gettableColumn();// �������
				int Table2cols1 = wordBean1.getCols(1);// ��һ������
				int Table2cols2 = wordBean1.getCols(2);// �ڶ�������
				int Table2cols3 = wordBean1.getCols(3);// ����������
				int Table2cols4 = wordBean1.getCols(4);// ����������
				if (Table2rowCount != 4 || Table2colCount != 2 || Table2cols1 != 1
						|| Table2cols2 != 2 || Table2cols3 != 2||Table2cols4!=2) {
					result = "�����ļ���ʽ��������������ģ���޸ĺ������ϴ������ļ�";
					wordBean1.closeWord();
					return result;
				}
				wordBean1.closeWord();
				// ɾ���ĵ�
				File filenewdocname = new File(path+'\\' + newdocname + ".doc");
				filenewdocname.delete();
				File filesavedocname = new File(path+'\\' + savedocname + ".doc");
				filesavedocname.delete();
			} else {
				int Table2rowCount = wordBean1.gettableRow();// �������
				int Table2colCount = wordBean1.gettableColumn();// �������
				int Table2cols1 = wordBean1.getCols(1);// ��һ������
				int Table2cols2 = wordBean1.getCols(2);// �ڶ�������
				int Table2cols3 = wordBean1.getCols(3);// ����������
				if (Table2rowCount != 3 || Table2colCount != 2 || Table2cols1 != 1
						|| Table2cols2 != 2 || Table2cols3 != 2) {
					result = "�����ļ���ʽ��������������ģ���޸ĺ������ϴ������ļ�";
					wordBean1.closeWord();
					return result;
				}
				wordBean1.closeWord();
				// ɾ���ĵ�
				File filenewdocname = new File(path+'\\' + newdocname + ".doc");
				filenewdocname.delete();
				File filesavedocname = new File(path+'\\' + savedocname + ".doc");
				filesavedocname.delete();
			}
			
		}
		return null;
	}
	
	
}

