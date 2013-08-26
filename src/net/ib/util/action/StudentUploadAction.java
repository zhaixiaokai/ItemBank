
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	StudentUploadAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：HuangJu
 * | 创建日期：2013-3-8 上午10:37:35
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-8 上午10:37:35
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
 * <p>类名：net.ib.util.action.StudentUploadAction </p>
 * <p>描述：学生Excel表的上传和解析和入库</p>
 * <p></p>
 */
public class StudentUploadAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
    private File xlsFile; //上传文件对象
    private String xlsFileFileName; //上传文件名
    private String xlsFileContentType; //上传文件MIME类型
    private String savePath;// 文件保存路径,相对于web应用程序的跟路径
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
		//获取保存上传文件的真实路
		String path=ServletActionContext.getServletContext().getRealPath(savePath);
		//System.out.print(path);

		File Dir=new File(path);
		// 如果路径不存在就创建它
		if(!Dir.exists())
			Dir.mkdir();
		// 为上传文件指定唯一文件名
		String UiqueFileName="";
		// 获取文件上传时间，作为文件名的一部分
		Date Mydate=new Date();
		long NowTime=Mydate.getTime();
		// 判断文件是否有后缀名
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
        
        //解析Excel表中内容
        list=readExcel.ReadExcelList(fileToBeRead);
        //完成数据入库
       readExcel.StudentExcelToDatabase(list, StudentDepartmentId);
    
		return null;
    }

}
