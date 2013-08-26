
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TestUploadInDbAction.java
 * | 包名：test
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-20 下午1:40:44
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-20 下午1:40:44
 * |------------------------------------------------------------------------------------ 
 */
package test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.service.FileService;
import net.ib.util.service.impl.TeachingClassServiceImpl;


  /**
 * <p>类名：test.TestUploadInDbAction </p>
 * <p>描述：将前台发来的数据直接入库</p>
 * <p></p>
 */
public class TestUploadInDbAction {
	private static Logger logger = Logger
			.getLogger(TestUploadInDbAction.class);
	private	File	DocContent;
    private String DocContentFileName; //上传文件名
    private String DocContentContentType; //上传文件MIME类型

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
		//InputStream is	=	fs.FileUpload(DocContent, DocContentFileName, DocContentContentType);
		logger.debug(this.DocContentFileName);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print("succeed");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
