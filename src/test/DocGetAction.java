
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	DocGetAction.java
 * | ������test
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-21 ����9:22:47
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-21 ����9:22:47
 * |------------------------------------------------------------------------------------ 
 */
package test;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.Service;


  /**
 * <p>������test.DocGetAction </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class DocGetAction {
	Service	service;
	String	id="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public	String	getDocFromDb(){
		InputStream in	=	service.getBlobInputStreamFromTable("testblob", "id", "C8CB90754D264357B13B847A402FB0AA", "testblob");
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
		}
		return	null;
	}
}
