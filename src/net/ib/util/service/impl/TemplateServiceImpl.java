
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TemplateGetAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-5 ����2:04:56
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-5 ����2:04:56
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
 * <p>������net.ib.util.service.Impl.TemplateServiceImpl </p>
 * <p>��������ȡ����ģ��</p>
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
	 * <p>���ƣ�GetXZTemplate</p>
	 * <p>˵������ȡѡ��������ģ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String GetXZTemplate(){
		InputStream in	=	service.getBlobInputStreamFromTable("sys_template", "id", "xuanze", "template");
		this.WriteIO(in);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�GetPDTemplate</p>
	 * <p>˵������ȡ�ж�������ģ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String GetPDTemplate(){
		InputStream in	=	service.getBlobInputStreamFromTable("sys_template", "id", "panduan", "template");
		this.WriteIO(in);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�GetJDTemplate</p>
	 * <p>˵������ȡ���������ģ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String GetJDTemplate(){
		InputStream in	=	service.getBlobInputStreamFromTable("sys_template", "id", "jianda", "template");
		this.WriteIO(in);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�GetTKTemplate</p>
	 * <p>˵������ȡ���������ģ��</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String GetTKTemplate(){
		InputStream in	=	service.getBlobInputStreamFromTable("sys_template", "id", "tiankong", "template");
		this.WriteIO(in);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�WriteIO</p>
	 * <p>˵������������io�����</p>
	 * <p>������@param in
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��Boolean ��������</p>
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
