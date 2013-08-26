
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	DocGetAction.java
 * | 包名：test
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-21 上午9:22:47
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-21 上午9:22:47
 * |------------------------------------------------------------------------------------ 
 */
package test;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.Service;


  /**
 * <p>类名：test.DocGetAction </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
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
