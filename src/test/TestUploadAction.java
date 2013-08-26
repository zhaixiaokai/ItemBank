
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TestUploadAction.java
 * | 包名：test
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-18 下午4:29:58
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-18 下午4:29:58
 * |------------------------------------------------------------------------------------ 
 */
package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.FileService;
import net.ib.util.service.Service;


  /**
 * <p>类名：test.TestUploadAction </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class TestUploadAction {
	private	String	id;
	private	String	DocTitle;
	private	String	DocID;
	private	String	DocType;
	private	File	DocContent;
    private String DocContentFileName; //上传文件名
    private String DocContentContentType; //上传文件MIME类型
    private	FileService	fs;
    private	Service	service;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDocTitle() {
		return DocTitle;
	}
	public void setDocTitle(String docTitle) {
		DocTitle = docTitle;
	}
	public String getDocID() {
		return DocID;
	}
	public void setDocID(String docID) {
		DocID = docID;
	}
	public String getDocType() {
		return DocType;
	}
	public void setDocType(String docType) {
		DocType = docType;
	}
	public File getDocContent() {
		return DocContent;
	}
	public void setDocContent(File docContent) {
		DocContent = docContent;
	}
	public String getDocContentFileName() {
		return DocContentFileName;
	}
	public void setDocContentFileName(String docContentFileName) {
		DocContentFileName = docContentFileName;
	}
	public String getDocContentContentType() {
		return DocContentContentType;
	}
	public void setDocContentContentType(String docContentContentType) {
		DocContentContentType = docContentContentType;
	}
	
	public	String	Function(){
		Boolean	flag	=	fs.FileUpload("c:\\","test", DocContent, DocContentFileName, DocContentContentType);
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
	public	String	Func(){
		InputStream is=null;
		try {
			is = new FileInputStream(this.DocContent);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(is!=null){
			String result=service.exercuteUpdateWithBlob("testblob", "id", "C8CB90754D264357B13B847A402FB0AA", "testblob", is);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			if(result.equals("true")){
				try {
					PrintWriter pw = response.getWriter();
					pw.print("succeed");
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(result.equals("nodata")){
				try {
					PrintWriter pw = response.getWriter();
					pw.print("nodata");
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					PrintWriter pw = response.getWriter();
					pw.print("failed");
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			
		
		return null;
	}
}
