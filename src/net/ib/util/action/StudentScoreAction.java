/**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ResultAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2013-3-18 上午11:20:06
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-18 上午11:20:06
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import java.io.File;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

import javax.servlet.http.HttpServletResponse;

import net.ib.util.service.ReadExcel;
import net.ib.util.service.TestService;
import net.ib.util.service.impl.ReadExcelImpl;
import net.ib.util.service.impl.ServiceImpl;
import net.ib.util.service.StudentScoreService;
import net.ib.util.service.impl.StudentScoreServiceImpl;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import net.ib.util.service.ReadExcel;

/**
 * <p>
 * 类名：net.ib.util.action.ResultAction
 * </p>
 * <p>
 * 描述：成绩的导入、按条件查询
 * </p>
 * <p>
 * </p>
 */
public class StudentScoreAction {
	private String selectedCurseId;
	private String CurseTime;
	private static final long serialVersionUID = 1L;
	private File xlsFile; // 上传文件对象
	private String xlsFileFileName; // 上传文件名
	private String xlsFileContentType; // 上传文件MIME类型
	private String savePath;// 文件保存路径,相对于web应用程序的跟路径
	private ReadExcel readExcel;
	private String SchoolID;
	private String SchoolName;
	private String Score;
	private String oldSchoolID;
	private String DeleteId;
	private StudentScoreService StudentScoreService;
	private String SelectedTeachingClassId;// 开课班级的id
	private String SelectedClassId;// 行政班级的id

	public String getSelectedClassId() {
		return SelectedClassId;
	}

	public void setSelectedClassId(String selectedClassId) {
		SelectedClassId = selectedClassId;
	}

	public String getSelectedTeachingClassId() {
		return SelectedTeachingClassId;
	}

	public void setSelectedTeachingClassId(String selectedTeachingClassId) {
		SelectedTeachingClassId = selectedTeachingClassId;
	}

	public String getOldSchoolID() {
		return oldSchoolID;
	}

	public void setOldSchoolID(String oldSchoolID) {
		this.oldSchoolID = oldSchoolID;
	}

	public StudentScoreService getStudentScoreService() {
		return StudentScoreService;
	}

	public void setStudentScoreService(StudentScoreService studentScoreService) {
		StudentScoreService = studentScoreService;
	}

	public String getDeleteId() {
		return DeleteId;
	}

	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}

	public String getSchoolID() {
		return SchoolID;
	}

	public void setSchoolID(String schoolID) {
		SchoolID = schoolID;
	}

	public String getSchoolName() {
		return SchoolName;
	}

	public void setSchoolName(String schoolName) {
		SchoolName = schoolName;
	}

	public String getScore() {
		return Score;
	}

	public void setScore(String score) {
		Score = score;
	}

	public String getSelectedCurseId() {
		return selectedCurseId;
	}

	public void setSelectedCurseId(String selectedCurseId) {
		this.selectedCurseId = selectedCurseId;
	}

	public String getCurseTime() {
		return CurseTime;
	}

	public void setCurseTime(String curseTime) {
		CurseTime = curseTime;
	}

	public File getXlsFile() {
		return xlsFile;
	}

	public void setXlsFile(File xlsFile) {
		this.xlsFile = xlsFile;
	}

	public String getXlsFileFileName() {
		return xlsFileFileName;
	}

	public void setXlsFileFileName(String xlsFileFileName) {
		this.xlsFileFileName = xlsFileFileName;
	}

	public String getXlsFileContentType() {
		return xlsFileContentType;
	}

	public void setXlsFileContentType(String xlsFileContentType) {
		this.xlsFileContentType = xlsFileContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public ReadExcel getReadExcel() {
		return readExcel;
	}

	public void setReadExcel(ReadExcel readExcel) {
		this.readExcel = readExcel;
	}

	/*
	 * (非-Javadoc) <p>名称: StudentScoreUpload</p> <p>说明: 实现学生成绩上传解析和入库</p>
	 * 
	 * @return
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String StudentScoreUpload() throws Exception {
		// 获取保存上传文件的真实路
		String path = ServletActionContext.getServletContext().getRealPath(
				savePath);
		// System.out.print(path);

		File Dir = new File(path);
		// 如果路径不存在就创建它
		if (!Dir.exists())
			Dir.mkdir();
		// 为上传文件指定唯一文件名
		String UiqueFileName = "";
		// 获取文件上传时间，作为文件名的一部分
		Date Mydate = new Date();
		long NowTime = Mydate.getTime();
		// 判断文件是否有后缀名
		int index = xlsFileFileName.lastIndexOf(".");
		if (index != -1)
			UiqueFileName = NowTime + xlsFileFileName.substring(index);
		else
			UiqueFileName = Long.toString(NowTime);

		FileInputStream fis = new FileInputStream(xlsFile);
		FileOutputStream fos = new FileOutputStream(
				new File(Dir, UiqueFileName));

		byte[] buffer = new byte[400];
		int length = 0;
		while ((length = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, length);
		}

		fis.close();
		fos.close();
		List<Map> list = new ArrayList<Map>();
		String fileToBeRead = path + '\\' + UiqueFileName;

		// 解析Excel表中内容
		list = readExcel.ReadExcelList(fileToBeRead);
		readExcel.StudentScoreToDatabase(list, selectedCurseId, CurseTime);

		return null;
	}

	/*
	 * (非-Javadoc) <p>名称: deleteStudentScore</p> <p>说明: 实现删除某个学生的成绩</p>
	 * 
	 * @return
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String DeleteStudentScoreOnTeachingClass() {
		StudentScoreService.DeleteStudentScoreOnTeachingClass(DeleteId,
				selectedCurseId);
		return null;
	}

	/*
	 * (非-Javadoc) <p>名称: updateStudentScore</p> <p>说明: 实现修改某个学生的成绩</p>
	 * 
	 * @return
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String StudentScoreUpdateOnTeachingClass() {
		StudentScoreService.UpdateStudentScoreOnTeachingClass(SchoolID, Score,
				selectedCurseId, oldSchoolID, SelectedTeachingClassId);
		return null;
	}

	/*
	 * (非-Javadoc) <p>名称: deleteStudentScore</p> <p>说明: 根据所属行政班级实现删除某个学生的成绩</p>
	 * 
	 * @return
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String DeleteStudentScoreOnClass() {
		StudentScoreService.DeleteStudentScoreOnClass(DeleteId, selectedCurseId);
		return null;
	}

	/*
	 * (非-Javadoc) <p>名称: updateStudentScore</p> <p>说明: 实现修改某个学生的成绩</p>
	 * 
	 * @return
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String UpdateStudentScoreOnClass() {
		StudentScoreService.UpdateStudentScoreOnClass(SchoolID, Score,
				selectedCurseId, oldSchoolID, SelectedClassId);
		return null;
	}

}
