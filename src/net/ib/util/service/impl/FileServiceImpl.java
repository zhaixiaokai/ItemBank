
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	FileServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-18 下午4:00:20
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-18 下午4:00:20
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.service.FileService;


  /**
 * <p>类名：net.ib.util.service.impl.FileServiceImpl </p>
 * <p>描述：文件service程序，文件上传，删除等功能实现</p>
 * <p></p>
 */
public class FileServiceImpl implements FileService {
	private static Logger logger = Logger
			.getLogger(FileServiceImpl.class);
	
	 /* (非-Javadoc)
	 * <p>名称: FileUpload</p>
	 * <p>说明: 文件上传</p>
	 * @param savePath 				文件上传保存路径
	 * @param file					需要上传的文件
	 * @param FileName				上传文件的文件名
	 * @param FileContentType		上传文件文件类型
	 * @return						true:上传成功，false:上传失败
	 * @see net.ib.util.service.FileService#FileUpload(java.lang.String, java.io.File, java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean FileUpload(String savePath, File file, String FileName,
			String FileContentType) {
		// TODO Auto-generated method stub
/*		String path = ServletActionContext.getServletContext().getRealPath(
				savePath);*/
		String	path=savePath;
		File Dir = new File(path);
		// 如果路径不存在就创建它
		if (!Dir.exists()) {
			Dir.mkdir();
		}
		String UiqueFileName = "";
		// 获取文件上传时间，作为文件名的一部分
		Date Mydate = new Date();
		long NowTime = Mydate.getTime();
		logger.debug("fileFileName is :" + FileName);
		// 判断文件是否有后缀名
		int index = FileName.lastIndexOf(".");
		if (index != -1) {
			UiqueFileName = NowTime + FileName.substring(index);
		} else {
			UiqueFileName = Long.toString(NowTime);
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.error("文件上传时：找不到指定上传文件！");
			return false;
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(Dir, UiqueFileName));
			logger.debug("Dir:	" + Dir);
			logger.debug("FileName:	"+UiqueFileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("文件上传时：找不到指定目标文件！");
			return	false;
		}

		byte[] buffer = new byte[400];
		int length = 0;
		try {
			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("IO错误！");
			return false;
		}

		try {
			fis.close();
			fos.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("关闭IO流错误！");
			return	false;
		}
		return true;
	}

	
	 /* (非-Javadoc)
	 * <p>名称: FileUpload</p>
	 * <p>说明: 指定名称与路径上传文件</p>
	 * @param savePath			上传文件保存路径
	 * @param saveAsName		上传文件另存为名称
	 * @param file				上传文件
	 * @param FileName			上传文件名称
	 * @param FileContentType	上传文件类型
	 * @return					true:上传成功，false：上传失败
	 * @see net.ib.util.service.FileService#FileUpload(java.lang.String, java.lang.String, java.io.File, java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean FileUpload(String savePath, String saveAsName, File file,
			String FileName, String FileContentType) {
		// TODO Auto-generated method stub
		String	path=savePath;
		File Dir = new File(path);
		// 如果路径不存在就创建它
		if (!Dir.exists()) {
			Dir.mkdir();
		}
		logger.debug("fileFileName is :" + FileName);
		// 判断文件是否有后缀名
		int index = FileName.lastIndexOf(".");
		if (index != -1) {
			saveAsName+=FileName.substring(index);
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.error("文件上传时：找不到指定上传文件！");
			return false;
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(Dir, saveAsName));
			logger.debug("Dir:	" + Dir);
			logger.debug("SaveAsFileName:	"+saveAsName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("文件上传时：找不到指定目标文件！");
			return	false;
		}

		byte[] buffer = new byte[400];
		int length = 0;
		try {
			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("IO错误！");
			return false;
		}

		try {
			fis.close();
			fos.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("关闭IO流错误！");
			return	false;
		}
		return true;
	}


	
	 /* (非-Javadoc)
	 * <p>名称: FileUpload</p>
	 * <p>说明: 将上传的文件转换为InputStream</p>
	 * @param file
	 * @param FileName
	 * @param FileContentType
	 * @return
	 * @see net.ib.util.service.FileService#FileUpload(java.io.File, java.lang.String, java.lang.String)
	 */
	@Override
	public InputStream FileUpload(File file, String FileName,
			String FileContentType) {
		// TODO Auto-generated method stub
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.error("文件上传时：找不到指定上传文件！");
			return null;
		}
		return fis;
	}

}
