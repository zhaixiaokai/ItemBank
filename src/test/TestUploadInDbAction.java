
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TestUploadInDbAction.java
 * | ������test
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-20 ����1:40:44
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-20 ����1:40:44
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
 * <p>������test.TestUploadInDbAction </p>
 * <p>��������ǰ̨����������ֱ�����</p>
 * <p></p>
 */
public class TestUploadInDbAction {
	private static Logger logger = Logger
			.getLogger(TestUploadInDbAction.class);
	private	File	DocContent;
    private String DocContentFileName; //�ϴ��ļ���
    private String DocContentContentType; //�ϴ��ļ�MIME����

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
