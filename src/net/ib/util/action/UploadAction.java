package net.ib.util.action;
/**
 * 
 */

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

import javax.servlet.http.HttpServletResponse;

import net.ib.util.service.ReadExcel;
import net.ib.util.service.TestService;
import net.ib.util.service.impl.ReadExcelImpl;
import net.ib.util.service.impl.ServiceImpl;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
 
public class UploadAction extends ActionSupport {

    
	private static final long serialVersionUID = 1L;
    private File xlsFile; //上传文件对象
    private String xlsFileFileName; //上传文件名
    private String xlsFileContentType; //上传文件MIME类型
    private String savePath;// 文件保存路径,相对于web应用程序的跟路径
    private ReadExcel readExcel;
    private String message;
   
  
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
	public String doUploadAction() throws Exception {
        // int m=file.size();
        // System.out.println(m);
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
        //System.out.println(fileToBeRead);
        list=readExcel.ReadExcelList(fileToBeRead);
        ServiceImpl	si	=	new	ServiceImpl();
    	String Json	=	si.DataListToJson(list);
    	
     this.message ="["+Json+"]";
  	/*HttpServletResponse response	=	ServletActionContext.getResponse();
    	
		response.setCharacterEncoding("utf-8");
		try {	
		
			PrintWriter pw = response.getWriter();	
			//pw.print(Json);	
			pw.print("{\"result\":\"名称重复，请重新输入\"}");
			pw.flush();
			pw.close();
		
		} catch (IOException e) {
			e.printStackTrace();
	
	}*/
    	//response.sendRedirect("TeacherList.jsp");
		return SUCCESS;
    }
    
}
	

