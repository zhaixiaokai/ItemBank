
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	FileService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-18 下午3:59:13
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-18 下午3:59:13
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.io.File;
import java.io.InputStream;


  /**
 * <p>类名：net.ib.util.service.FileService </p>
 * <p>描述：文件service程序，文件上传，删除等功能接口</p>
 * <p></p>
 */
public interface FileService {
	public	Boolean	FileUpload(String	savePath,File file,String	FileName,String	FileContentType);
	public	Boolean	FileUpload(String	savePath,String saveAsName,File file,String	FileName,String	FileContentType);
	public	InputStream FileUpload(File file,String	FileName,String FileContentType);
}
