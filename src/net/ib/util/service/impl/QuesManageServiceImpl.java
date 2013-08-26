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
 * 在线添加试题知识点获取
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
 * 在线添加试题试题合法性验证
 */
	 
	public String QuestionCheckValidation(String questiontype,
			String target_itembank, String difficulty_coefficient,
			String defaultpoint, String time, String comment,
			String selectpoint, File ques_attachment, String savePath,
			String ques_attachmentFileName, String ques_attachmentContentType) {
		logger.debug(selectpoint);
		String path = ServletActionContext.getServletContext().getRealPath(savePath); //文件保存路径
		String result = "";
		// 检查试题基本信息表格
		JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
		UUID newdocuuid = UUID.randomUUID();
		String newdocname = newdocuuid.toString();
		wordBean.CreateDoc(newdocname,path);// 创建一个新文件
		wordBean.OpenFile(path+'\\' + newdocname + ".doc");
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE); // 是否前台打开word 程序，或者后台运行
		wordBean.copyTableFromAnotherDoc(path+'\\'+"xiaokai.doc", 1);
		wordBean.moveEnd();
		wordBean.InsertText("------END------");
		wordBean.findTextAndCopy("------END------");
		wordBean.moveStart();
		wordBean.replaceAllText("------END------", " ");
		UUID savedocuuid = UUID.randomUUID();
		String savedocname = savedocuuid.toString();
		wordBean.saveFileAs(path+'\\' + savedocname + ".doc");// *******保存第一个表格，必须另存，不然会在前台是否保存word，则无法执行删除工作
		// 检查表格行数列数是否正确
		int rowCount = wordBean.gettableRow();// 表格行数
		int colCount = wordBean.gettableColumn();// 表格列数
		int cols1 = wordBean.getCols(1);// 第一行列数
		int cols2 = wordBean.getCols(2);// 第二行列数
		if (rowCount != 2 || colCount != 4 || cols1 != 2 || cols2 != 4) {
			result = "试题基本信息表格格式不允许更改，请重新编辑";
			print(result);
			return null;
		}
		String cell12 = wordBean.getTxtFromCell(1, 1, 2);// 时间
		String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";// 验证时间格式是否正确
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(cell12);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			result = "时间格式不正确";
			print(result);
			return null;
		}
		String cell24 = wordBean.getTxtFromCell(1, 2, 4);// 试题作者工作证号
		List<Map> list = di
				.executeQuery("select * from sys_user_teacher where school_id='"
						+ cell24 + "'");
		if (list.size() < 1) {
			result = "试题作者工作证号输入有误，查无此人，请重新输入";
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
		
		// 检查试题内容表格
		// 判断题型
		// 若是选择题
		UUID docuuid = UUID.randomUUID();
		String docname = docuuid.toString();
		if (questiontype.equals("xuanze")) {
			JacobWordBeanServiceImpl wordBean1 = new JacobWordBeanServiceImpl();
			UUID contentdocuuid = UUID.randomUUID();
			String newcontentname = contentdocuuid.toString();
			wordBean1.CreateDoc(newcontentname,path);// 创建一个新文件
			wordBean1.OpenFile(path+'\\' + newcontentname + ".doc");
			wordBean1.SetVisible(true); // 是否前台打开word 程序，或者后台运行
			wordBean1.copyTableFromAnotherDoc(path+'\\'+"xiaokai.doc", 2);
			wordBean1.moveEnd();
			wordBean1.InsertText("------END------");
			wordBean1.findTextAndCopy("------END------");
			wordBean1.moveStart();
			wordBean1.replaceAllText("------END------", " ");
			wordBean1.saveFileAs(path+'\\' + docname + ".doc");
			int Table2rowCount = wordBean1.gettableRow();// 表格行数
			int Table2colCount = wordBean1.gettableColumn();// 表格列数
			int Table2cols1 = wordBean1.getCols(1);// 第一行列数
			int Table2cols2 = wordBean1.getCols(2);// 第二行列数
			int Table2cols3 = wordBean1.getCols(3);// 第三行列数
			int Table2cols4 = wordBean1.getCols(4);// 第四行列数
			if (Table2rowCount != 4 || Table2colCount != 2 || Table2cols1 != 1
					|| Table2cols2 != 2 || Table2cols3 != 2||Table2cols4!=2) {
				result = "试题内容表格格式不允许更改，请重新编辑";
				print(result);
				return null;
			}
			wordBean1.closeWord();
			// 删除文档
			logger.debug("  " + newdocname + "  "
					+ savedocname + "  " + newcontentname
					+ "  " + docname);
			File file1 = new File(path+'\\' + newcontentname + ".doc");
			file1.delete();
		} else {
			JacobWordBeanServiceImpl wordBean1 = new JacobWordBeanServiceImpl();
			UUID contentdocuuid = UUID.randomUUID();
			String newcontentname = contentdocuuid.toString();
			wordBean1.CreateDoc(newcontentname,path);// 创建一个新文件
			wordBean1.OpenFile(path+'\\' + newcontentname + ".doc");
			wordBean1.SetVisible(true); // 是否前台打开word 程序，或者后台运行
			wordBean1.copyTableFromAnotherDoc(path+'\\'+"xiaokai.doc", 2);
			wordBean1.moveEnd();
			wordBean1.InsertText("------END------");
			wordBean1.findTextAndCopy("------END------");
			wordBean1.moveStart();
			wordBean1.replaceAllText("------END------", " ");
			wordBean1.saveFileAs(path+'\\' + docname + ".doc");
			int Table2rowCount = wordBean1.gettableRow();// 表格行数
			int Table2colCount = wordBean1.gettableColumn();// 表格列数
			int Table2cols1 = wordBean1.getCols(1);// 第一行列数
			int Table2cols2 = wordBean1.getCols(2);// 第二行列数
			int Table2cols3 = wordBean1.getCols(3);// 第三行列数
			if (Table2rowCount != 3 || Table2colCount != 2 || Table2cols1 != 1
					|| Table2cols2 != 2 || Table2cols3 != 2) {
				result = "试题内容表格格式不允许更改，请重新编辑";
				print(result);
				return null;
			}
			wordBean1.closeWord();
			// 删除文档
			File file1 = new File(path+'\\' + newcontentname + ".doc");
			file1.delete();
		}
		// 试题编辑人员信息，时间,试题类型保存
		UUID questionuuid = UUID.randomUUID();
		String questionid = questionuuid.toString();
		di.execute("insert into QT_BASICFIELD_"
				+ target_itembank
				+ " fields(question_id,EDITOR_ID,TIME,QUESTION_TYPE,DIFFICULTY,defaultpoint,time_use,explain,knowledge_point_id) values('"
				+ questionid + "','" + cell24 + "'," + "to_date('" + cell12
				+ "','yyyy-MM-dd')" + ",'" + questiontype + "',"
				+ difficulty_coefficient + "," + defaultpoint + "," + time
				+ ",'" + comment + "','" + selectpoint + "')");
		// 试题内容保存
		di.execute("insert into QT_BLOBFIELD_" + target_itembank
				+ "   values('" + questionid + "',empty_blob(),empty_blob())");// 插入空数据
		String TableName = "QT_BLOBFIELD_" + target_itembank + "";
		String IdField = "question_id";
		String FieldName = "questioncontent";
		File file4 = new File(path+'\\'+ docname + ".doc");
		InputStream in = null;
		// 获取试题文件流
		try {
			in = new FileInputStream(file4);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		service.exercuteInsertWithBlob(TableName, IdField, questionid,
				FieldName, in);
		// 删除试题文档
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		file4.delete();

		// 试题附件保存
		if (ques_attachmentFileName != null) {
			File Dir = new File(path);
			if (!Dir.exists())
				Dir.mkdir();
			UUID uploadfile = UUID.randomUUID();
			String uploadfilename = uploadfile.toString();
			int index = ques_attachmentFileName.lastIndexOf(".");
			String UiqueFileName = "";
			UiqueFileName = uploadfilename
					+ ques_attachmentFileName.substring(index);// 附件名称
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
			// 将试题附件写入数据库
			File upload = new File(path + '\\' + UiqueFileName);
			InputStream upfile = null;
			try {
				upfile = new FileInputStream(upload);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			service.exercuteInsertWithBlob(TableName, IdField, questionid,
					"uploadfile", upfile);
			
			//删除试题附件
			File fileupfile = new File(path + '\\' + UiqueFileName);
			fileupfile.delete();
		}
		result = "添加成功";
		print(result);

		return null;
	}

	
	/**
	 * 导入试题试题文档保存
	 */
	
	public String ImportQues(File importQues,String savePath,String importQuesFileName,String importQuesContentType,String quesType){
		String path=ServletActionContext.getServletContext().getRealPath(savePath);		//获取保存上传文件的真实路
		File Dir=new File(path);
		if(!Dir.exists())
			Dir.mkdir();
		UUID uploadfile = UUID.randomUUID(); 
		String uploadfilename=uploadfile.toString();
		logger.debug(importQuesFileName);
	    int index=importQuesFileName.lastIndexOf(".");
		String UiqueFileName="";
        UiqueFileName=uploadfilename+importQuesFileName.substring(index);//附件名称
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
		
		//检查试题文件是否符合要求
		String validationResult=QuesFileValidation(path, UiqueFileName, quesType);
		if(validationResult!=null){
			String resultString="文件格式有误";
			print(resultString);
		}
		print(UiqueFileName);
		return null;
	}
	
	
	/**
	 * 
	 * <p>名称：print</p>
	 * <p>说明：向前台返回结果TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@param string
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>名称：QuesNumber</p>
	 * <p>说明：解析试题获取试题数量TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@param string
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@param string
	 * <p>@return</p>
	 */
	public String QuesNumber(String quesFileName,String savePath){
		String path=ServletActionContext.getServletContext().getRealPath(savePath);		//获取保存上传文件的真实路
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
	 * <p>名称：ShowQuesContent</p>
	 * <p>说明：显示试题内容TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@param string
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
		wordBean.CreateDoc(newdocname,path);// 创建一个新文件
		wordBean.OpenFile(path + '\\' + newdocname + ".doc");
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE); // 是否前台打开word 程序，或者后台运行
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
	 * <p>名称：DeleteQuesHtml</p>
	 * <p>说明：删除试题html文档TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>名称：SaveImportQues</p>
	 * <p>说明：导入试题，试题保存TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SaveImportQues(String quesFileName,String savePath,String number,String target_itembank,String difficulty_coefficient,String defaultpoint,String time,String selectpoint,String SelectQuesType){
		String path=ServletActionContext.getServletContext().getRealPath(savePath);	
		//获取试题编辑人员信息
		JacobWordBeanServiceImpl wordBean1 = new JacobWordBeanServiceImpl();
		UUID editorDocId = UUID.randomUUID();
		String editorDocName = editorDocId.toString();
		wordBean1.CreateDoc(editorDocName,path);// 创建一个新文件
		wordBean1.OpenFile(path+'\\' + editorDocName + ".doc");
		wordBean1.SetVisible(true); // 是否前台打开word 程序，或者后台运行
		wordBean1.copyTableFromAnotherDoc(path + '\\' + quesFileName,1);
		UUID saveEditorDocId = UUID.randomUUID();
		String saveEditorDocName = saveEditorDocId.toString();
		wordBean1.moveEnd();
		wordBean1.InsertText("------END------");
		wordBean1.findTextAndCopy("------END------");
		wordBean1.moveStart();
		wordBean1.replaceAllText("------END------", " ");
		wordBean1.saveFileAs(path+'\\' + saveEditorDocName + ".doc");// *******保存第一个表格，必须另存，不然会在前台是否保存word，则无法执行删除工作
		// 获取试题编辑时间和工作证号
		String editTime = wordBean1.getTxtFromCell(1, 1, 2);// 时间
		String eidtorId = wordBean1.getTxtFromCell(1, 2, 4);// 试题作者工作证号
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
		
		//获取试题内容
		String result=null;
		int quesNumber=Integer.parseInt(number)+1;
		JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
		UUID newdocuuid = UUID.randomUUID();
		String newdocname = newdocuuid.toString();
		wordBean.CreateDoc(newdocname,path);// 创建一个新文件
		wordBean.OpenFile(path + '\\' + newdocname + ".doc");
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE); // 是否前台打开word 程序，或者后台运行
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
		//保存试题
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
		// 试题内容保存
		di.execute("insert into QT_BLOBFIELD_" + target_itembank
				+ "   values('" + questionid + "',empty_blob(),empty_blob())");// 插入空数据
		String TableName = "QT_BLOBFIELD_" + target_itembank + "";
		String IdField = "question_id";
		String FieldName = "questioncontent";
		File file4 = new File(path+'\\'+ savedocname + ".doc");
		InputStream in = null;
		// 获取试题文件流
		try {
			in = new FileInputStream(file4);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		service.exercuteInsertWithBlob(TableName, IdField, questionid,
				FieldName, in);
		file4.delete();
		result = "导入成功";
		print(result);
		return null;
	}
	
	

	/**
	 * 
	 * <p>名称：GetQuesDifficultyOption</p>
	 * <p>说明：查看试题，获取试题难度等级option</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>名称：GetCurseId</p>
	 * <p>说明：-查看试题，由试题库id查找课程id</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>名称：QuesContent</p>
	 * <p>说明：查看试题，查看试题内容TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String QuesContent(String questionid,String itembank_id,String savePath){
		String TableName="QT_BLOBFIELD_"+itembank_id+"";
		InputStream in=service.getBlobInputStreamFromTable(TableName,"question_id",questionid,"questioncontent");
		String path=ServletActionContext.getServletContext().getRealPath(savePath);	
		UUID questioncontentuuid = UUID.randomUUID();
		String QuesContentDoc = questioncontentuuid.toString();
		File f=new File(path+'\\'+ QuesContentDoc + ".doc");
		//将io流转换成文件
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
		  //将word转换为html文件
			JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
			wordBean.OpenFile(path + '\\' + QuesContentDoc + ".doc");
			wordBean.SetVisible(WordBeanProperty.WORD_VISABLE); // 是否前台打开word 程序，或者后台运行
			wordBean.wordToHtml(path + '\\' + QuesContentDoc + ".doc", path + '\\' + QuesContentDoc + ".html");
			wordBean.closeWord();
			File file = new File(path + '\\' + QuesContentDoc + ".doc");
			file.delete();
			print(QuesContentDoc);
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
	 * <p>名称：ModifyQuesSave</p>
	 * <p>说明：试题管理，试题基本信息修改保存TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String ModifyQuesSave(String difficulty_coefficient,String defaultpoint,String time,String Quesselectpoint,String itembank_id,String modifyid){
		String result="";
		di.executeQuery("update QT_BASICFIELD_"+itembank_id+" set defaultpoint='"+defaultpoint+"',time_use='"+time+"',difficulty='"+difficulty_coefficient+"',knowledge_point_id='"+Quesselectpoint+"' where question_id='"+ modifyid + "' ");
		result="修改成功";
		print(result);
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
	public String QuesManageGetQuesContent(String itembank_id,String questionid,String savePath){
		String TableName="QT_BLOBFIELD_"+itembank_id+"";
		InputStream in=service.getBlobInputStreamFromTable(TableName,"question_id",questionid,"questioncontent");
		this.WriteIO(in);
		return null;
	}
	/**
	 * 
	 * <p>名称：WriteIO</p>
	 * <p>说明：将参数的io流输出</p>
	 * <p>参数：@param in
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：Boolean 返回类型</p>
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
	 * <p>名称：QuesManageDeleteQues</p>
	 * <p>说明：试题管理，删除试题TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String QuesManageDeleteQues(String questionid,String itembank_id){
		String result="";
		di.executeQuery("delete from QT_BASICFIELD_"+itembank_id+"  where question_id='"+ questionid + "' ");
		di.executeQuery("delete from QT_BLOBFIELD_"+itembank_id+"  where question_id='"+ questionid + "' ");
		result="删除成功";
		print(result);
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
	public String QuesManageBulkDeleteQues(String deleteid,String itembank_id){
		String[] array = deleteid.split(" ");
		//基本信息删除
		String Sql = "delete from QT_BASICFIELD_"+itembank_id+" where";
		String result = "";
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				Sql += " or";
			}
			Sql += " question_id='" + array[i] + "'";
		}
		//blob数据删除
		String SqlBlob = "delete from QT_BLOBFIELD_"+itembank_id+" where";
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				SqlBlob += " or";
			}
			SqlBlob += " question_id='" + array[i] + "'";
		}
		di.executeQuery(Sql);
		di.executeQuery(SqlBlob);
		result="删除成功";
		print(result);
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
	public String QueswMoveSave(String questionid,String original_itembank,String target_itembank){
		String BasicSql="insert into QT_BASICFIELD_"+target_itembank+" SELECT * FROM QT_BASICFIELD_"+original_itembank+" WHERE QUESTION_ID='"+questionid+"'";
		String BlobSql="insert into QT_BLOBFIELD_"+target_itembank+" SELECT * FROM QT_BLOBFIELD_"+original_itembank+" WHERE QUESTION_ID='"+questionid+"'";
		di.executeQuery(BasicSql);
		di.executeQuery(BlobSql);
		di.executeQuery("delete from QT_BASICFIELD_"+original_itembank+"  where question_id='"+ questionid + "' ");
		di.executeQuery("delete from QT_BLOBFIELD_"+original_itembank+"  where question_id='"+ questionid + "' ");
		String result="迁移成功";
		print(result);
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
	public String QuesOutput(String questionid,String itembank_id,String savePath){
		String TableName="QT_BLOBFIELD_"+itembank_id+"";
		InputStream in=service.getBlobInputStreamFromTable(TableName,"question_id",questionid,"questioncontent");
		String path=ServletActionContext.getServletContext().getRealPath(savePath);	
		UUID questioncontentuuid = UUID.randomUUID();
		String QuesContentDoc = questioncontentuuid.toString();
		File f=new File(path+'\\'+ QuesContentDoc + ".doc");
		//将io流转换成文件
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
	 * <p>名称：QuesFileValidation</p>
	 * <p>说明：试题文件验证TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String QuesFileValidation(String path,String fileName,String questiontype){
	//String path = ServletActionContext.getServletContext().getRealPath(savePath); //文件保存路径
	//	logger.debug("*********************7777**********"+path+'\\' + fileName);
		String result = "";
		JacobWordBeanServiceImpl wordBean = new JacobWordBeanServiceImpl();
		//wordBean.OpenFile(path+'\\' + fileName);
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE); // 是否前台打开word 程序，或者后台运行
	
		
		//检查试题基本信息表格
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
		wordBean.saveFileAs(path+'\\' + saveBasicInfoTableName + ".doc");// *******保存第一个表格，必须另存，不然会在前台是否保存word，则无法执行删除工作
		int rowCount = wordBean.gettableRow();// 表格行数
		int colCount = wordBean.gettableColumn();// 表格列数
		int cols1 = wordBean.getCols(1);// 第一行列数
		int cols2 = wordBean.getCols(2);// 第二行列数
		if (rowCount != 2 || colCount != 4 || cols1 != 2 || cols2 != 4) {
			result = "试题文件格式有误，请依照试题模板修改后重新上传试题文件";
			return result;
		}
		String editTime = wordBean.getTxtFromCell(1, 1, 2);// 时间
		String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";// 验证时间格式是否正确
		Pattern p = Pattern.compile(eL);
		Matcher m = p.matcher(editTime);
		boolean dateFlag = m.matches();
		if (!dateFlag) {
			result = "试题文件格式有误，请依照试题模板修改后重新上传试题文件";
			return result;
		}
		String quesEditorId = wordBean.getTxtFromCell(1, 2, 4);// 试题作者工作证号
		List<Map> list = di
				.executeQuery("select * from sys_user_teacher where school_id='"
						+ quesEditorId + "'");
		if (list.size() < 1) {
			result = "试题文件格式有误，请依照试题模板修改后重新上传试题文件";
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
		
		//计算试题数目
		JacobWordBeanServiceImpl wordBean2 = new JacobWordBeanServiceImpl();
		wordBean2.SetVisible(true); // 是否前台打开word 程序，或者后台运行
		wordBean2.OpenFile(path+'\\' + fileName);
		int Number =wordBean2.CountTable();
		wordBean2.closeWord();

		//检查试题内容表格是否正确
		for(int i=2;i<=Number;i++){
			JacobWordBeanServiceImpl wordBean1 = new JacobWordBeanServiceImpl();
			wordBean1.SetVisible(true); // 是否前台打开word 程序，或者后台运行
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
			wordBean1.saveFileAs(path+'\\' + savedocname + ".doc");// *******保存第一个表格，必须另存，不然会在前台是否保存word，则无法执行删除工作
			// 若是选择题
			UUID docuuid = UUID.randomUUID();
			String docname = docuuid.toString();
			if (questiontype.equals("xuanze")) {
				int Table2rowCount = wordBean1.gettableRow();// 表格行数
				int Table2colCount = wordBean1.gettableColumn();// 表格列数
				int Table2cols1 = wordBean1.getCols(1);// 第一行列数
				int Table2cols2 = wordBean1.getCols(2);// 第二行列数
				int Table2cols3 = wordBean1.getCols(3);// 第三行列数
				int Table2cols4 = wordBean1.getCols(4);// 第四行列数
				if (Table2rowCount != 4 || Table2colCount != 2 || Table2cols1 != 1
						|| Table2cols2 != 2 || Table2cols3 != 2||Table2cols4!=2) {
					result = "试题文件格式有误，请依照试题模板修改后重新上传试题文件";
					wordBean1.closeWord();
					return result;
				}
				wordBean1.closeWord();
				// 删除文档
				File filenewdocname = new File(path+'\\' + newdocname + ".doc");
				filenewdocname.delete();
				File filesavedocname = new File(path+'\\' + savedocname + ".doc");
				filesavedocname.delete();
			} else {
				int Table2rowCount = wordBean1.gettableRow();// 表格行数
				int Table2colCount = wordBean1.gettableColumn();// 表格列数
				int Table2cols1 = wordBean1.getCols(1);// 第一行列数
				int Table2cols2 = wordBean1.getCols(2);// 第二行列数
				int Table2cols3 = wordBean1.getCols(3);// 第三行列数
				if (Table2rowCount != 3 || Table2colCount != 2 || Table2cols1 != 1
						|| Table2cols2 != 2 || Table2cols3 != 2) {
					result = "试题文件格式有误，请依照试题模板修改后重新上传试题文件";
					wordBean1.closeWord();
					return result;
				}
				wordBean1.closeWord();
				// 删除文档
				File filenewdocname = new File(path+'\\' + newdocname + ".doc");
				filenewdocname.delete();
				File filesavedocname = new File(path+'\\' + savedocname + ".doc");
				filesavedocname.delete();
			}
			
		}
		return null;
	}
	
	
}

