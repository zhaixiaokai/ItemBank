
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TemplateGetAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-5 下午2:04:56
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-5 下午2:04:56
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.Service;
import net.ib.util.service.TemplateService;


  /**
 * <p>类名：net.ib.util.service.Impl.TemplateServiceImpl </p>
 * <p>描述：获取试题模板</p>
 * <p></p>
 */
public class TemplateServiceImpl implements TemplateService{
	
	Dao	dao;
	Service service;
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	/**
	 * 
	 * <p>名称：GetXZTemplate</p>
	 * <p>说明：获取选择题试题模板</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String GetXZTemplate(){
		InputStream in	=	service.getBlobInputStreamFromTable("sys_template", "id", "xuanze", "template");
		this.WriteIO(in);
		return null;
	}
	/**
	 * 
	 * <p>名称：GetPDTemplate</p>
	 * <p>说明：获取判断题试题模板</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String GetPDTemplate(){
		InputStream in	=	service.getBlobInputStreamFromTable("sys_template", "id", "panduan", "template");
		this.WriteIO(in);
		return null;
	}
	/**
	 * 
	 * <p>名称：GetJDTemplate</p>
	 * <p>说明：获取简答题试题模板</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String GetJDTemplate(){
		InputStream in	=	service.getBlobInputStreamFromTable("sys_template", "id", "jianda", "template");
		this.WriteIO(in);
		return null;
	}
	/**
	 * 
	 * <p>名称：GetTKTemplate</p>
	 * <p>说明：获取填空题试题模板</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String GetTKTemplate(){
		InputStream in	=	service.getBlobInputStreamFromTable("sys_template", "id", "tiankong", "template");
		this.WriteIO(in);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：WriteIO</p>
	 * <p>说明：将参数的io流输出</p>
	 * <p>参数：@param in
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：Boolean 返回类型</p>
	 * <p>@param in
	 * <p>@return</p>
	 */
	public Boolean	WriteIO(InputStream in){
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
			return false;
		}
		return true;
	}
}
