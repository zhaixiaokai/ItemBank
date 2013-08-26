
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	StudentUploadAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�HuangJu
 * | �������ڣ�2013-3-8 ����10:37:35
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-8 ����10:37:35
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

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

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
  /**
 * <p>������net.ib.util.action.StudentUploadAction </p>
 * <p>������ѧ��Excel����ϴ��ͽ��������</p>
 * <p></p>
 */
public class StudentUploadAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
    private File xlsFile; //�ϴ��ļ�����
    private String xlsFileFileName; //�ϴ��ļ���
    private String xlsFileContentType; //�ϴ��ļ�MIME����
    private String savePath;// �ļ�����·��,�����webӦ�ó���ĸ�·��
    private ReadExcel readExcel;
    private String message;
    private String StudentDepartmentId;
   
  
	
	public String getStudentDepartmentId() {
		return StudentDepartmentId;
	}
	public void setStudentDepartmentId(String studentDepartmentId) {
		StudentDepartmentId = studentDepartmentId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ReadExcel getReadExcel() {
		return readExcel;
	}
	public void setReadExcel(ReadExcel readExcel) {
		this.readExcel = readExcel;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
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
	
	public String StudentUpload() throws Exception {
        // int m=file.size();
		//��ȡ�����ϴ��ļ�����ʵ·
		String path=ServletActionContext.getServletContext().getRealPath(savePath);
		//System.out.print(path);

		File Dir=new File(path);
		// ���·�������ھʹ�����
		if(!Dir.exists())
			Dir.mkdir();
		// Ϊ�ϴ��ļ�ָ��Ψһ�ļ���
		String UiqueFileName="";
		// ��ȡ�ļ��ϴ�ʱ�䣬��Ϊ�ļ�����һ����
		Date Mydate=new Date();
		long NowTime=Mydate.getTime();
		// �ж��ļ��Ƿ��к�׺��
        int index=xlsFileFileName.lastIndexOf(".");
        if(index!=-1)
        	UiqueFileName=NowTime+xlsFileFileName.substring(index);
        else
        	UiqueFileName=Long.toString(NowTime);
        
        FileInputStream fis=new FileInputStream(xlsFile);
        FileOutputStream fos=new FileOutputStream(new File(Dir,UiqueFileName));
   
            byte[] buffer = new byte[400];
            int length = 0 ;
            while((length = fis.read(buffer)) > 0){
                fos.write(buffer, 0, length);
            }
            
            fis.close();
            fos.close();
        List<Map> list =new ArrayList<Map>();
        String fileToBeRead=path+'\\'+UiqueFileName;
        
        //����Excel��������
        list=readExcel.ReadExcelList(fileToBeRead);
        //����������
       readExcel.StudentExcelToDatabase(list, StudentDepartmentId);
    
		return null;
    }

}
