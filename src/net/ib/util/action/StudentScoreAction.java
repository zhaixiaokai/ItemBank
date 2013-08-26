/**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ResultAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2013-3-18 ����11:20:06
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-18 ����11:20:06
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
 * ������net.ib.util.action.ResultAction
 * </p>
 * <p>
 * �������ɼ��ĵ��롢��������ѯ
 * </p>
 * <p>
 * </p>
 */
public class StudentScoreAction {
	private String selectedCurseId;
	private String CurseTime;
	private static final long serialVersionUID = 1L;
	private File xlsFile; // �ϴ��ļ�����
	private String xlsFileFileName; // �ϴ��ļ���
	private String xlsFileContentType; // �ϴ��ļ�MIME����
	private String savePath;// �ļ�����·��,�����webӦ�ó���ĸ�·��
	private ReadExcel readExcel;
	private String SchoolID;
	private String SchoolName;
	private String Score;
	private String oldSchoolID;
	private String DeleteId;
	private StudentScoreService StudentScoreService;
	private String SelectedTeachingClassId;// ���ΰ༶��id
	private String SelectedClassId;// �����༶��id

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
	 * (��-Javadoc) <p>����: StudentScoreUpload</p> <p>˵��: ʵ��ѧ���ɼ��ϴ����������</p>
	 * 
	 * @return
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String StudentScoreUpload() throws Exception {
		// ��ȡ�����ϴ��ļ�����ʵ·
		String path = ServletActionContext.getServletContext().getRealPath(
				savePath);
		// System.out.print(path);

		File Dir = new File(path);
		// ���·�������ھʹ�����
		if (!Dir.exists())
			Dir.mkdir();
		// Ϊ�ϴ��ļ�ָ��Ψһ�ļ���
		String UiqueFileName = "";
		// ��ȡ�ļ��ϴ�ʱ�䣬��Ϊ�ļ�����һ����
		Date Mydate = new Date();
		long NowTime = Mydate.getTime();
		// �ж��ļ��Ƿ��к�׺��
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

		// ����Excel��������
		list = readExcel.ReadExcelList(fileToBeRead);
		readExcel.StudentScoreToDatabase(list, selectedCurseId, CurseTime);

		return null;
	}

	/*
	 * (��-Javadoc) <p>����: deleteStudentScore</p> <p>˵��: ʵ��ɾ��ĳ��ѧ���ĳɼ�</p>
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
	 * (��-Javadoc) <p>����: updateStudentScore</p> <p>˵��: ʵ���޸�ĳ��ѧ���ĳɼ�</p>
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
	 * (��-Javadoc) <p>����: deleteStudentScore</p> <p>˵��: �������������༶ʵ��ɾ��ĳ��ѧ���ĳɼ�</p>
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
	 * (��-Javadoc) <p>����: updateStudentScore</p> <p>˵��: ʵ���޸�ĳ��ѧ���ĳɼ�</p>
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
