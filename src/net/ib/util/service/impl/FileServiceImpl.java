
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	FileServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-18 ����4:00:20
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-18 ����4:00:20
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
 * <p>������net.ib.util.service.impl.FileServiceImpl </p>
 * <p>�������ļ�service�����ļ��ϴ���ɾ���ȹ���ʵ��</p>
 * <p></p>
 */
public class FileServiceImpl implements FileService {
	private static Logger logger = Logger
			.getLogger(FileServiceImpl.class);
	
	 /* (��-Javadoc)
	 * <p>����: FileUpload</p>
	 * <p>˵��: �ļ��ϴ�</p>
	 * @param savePath 				�ļ��ϴ�����·��
	 * @param file					��Ҫ�ϴ����ļ�
	 * @param FileName				�ϴ��ļ����ļ���
	 * @param FileContentType		�ϴ��ļ��ļ�����
	 * @return						true:�ϴ��ɹ���false:�ϴ�ʧ��
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
		// ���·�������ھʹ�����
		if (!Dir.exists()) {
			Dir.mkdir();
		}
		String UiqueFileName = "";
		// ��ȡ�ļ��ϴ�ʱ�䣬��Ϊ�ļ�����һ����
		Date Mydate = new Date();
		long NowTime = Mydate.getTime();
		logger.debug("fileFileName is :" + FileName);
		// �ж��ļ��Ƿ��к�׺��
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
			logger.error("�ļ��ϴ�ʱ���Ҳ���ָ���ϴ��ļ���");
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
			logger.error("�ļ��ϴ�ʱ���Ҳ���ָ��Ŀ���ļ���");
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
			logger.error("IO����");
			return false;
		}

		try {
			fis.close();
			fos.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("�ر�IO������");
			return	false;
		}
		return true;
	}

	
	 /* (��-Javadoc)
	 * <p>����: FileUpload</p>
	 * <p>˵��: ָ��������·���ϴ��ļ�</p>
	 * @param savePath			�ϴ��ļ�����·��
	 * @param saveAsName		�ϴ��ļ����Ϊ����
	 * @param file				�ϴ��ļ�
	 * @param FileName			�ϴ��ļ�����
	 * @param FileContentType	�ϴ��ļ�����
	 * @return					true:�ϴ��ɹ���false���ϴ�ʧ��
	 * @see net.ib.util.service.FileService#FileUpload(java.lang.String, java.lang.String, java.io.File, java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean FileUpload(String savePath, String saveAsName, File file,
			String FileName, String FileContentType) {
		// TODO Auto-generated method stub
		String	path=savePath;
		File Dir = new File(path);
		// ���·�������ھʹ�����
		if (!Dir.exists()) {
			Dir.mkdir();
		}
		logger.debug("fileFileName is :" + FileName);
		// �ж��ļ��Ƿ��к�׺��
		int index = FileName.lastIndexOf(".");
		if (index != -1) {
			saveAsName+=FileName.substring(index);
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			logger.error("�ļ��ϴ�ʱ���Ҳ���ָ���ϴ��ļ���");
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
			logger.error("�ļ��ϴ�ʱ���Ҳ���ָ��Ŀ���ļ���");
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
			logger.error("IO����");
			return false;
		}

		try {
			fis.close();
			fos.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("�ر�IO������");
			return	false;
		}
		return true;
	}


	
	 /* (��-Javadoc)
	 * <p>����: FileUpload</p>
	 * <p>˵��: ���ϴ����ļ�ת��ΪInputStream</p>
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
			logger.error("�ļ��ϴ�ʱ���Ҳ���ָ���ϴ��ļ���");
			return null;
		}
		return fis;
	}

}
