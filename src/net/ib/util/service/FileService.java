
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	FileService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-18 ����3:59:13
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-18 ����3:59:13
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.io.File;
import java.io.InputStream;


  /**
 * <p>������net.ib.util.service.FileService </p>
 * <p>�������ļ�service�����ļ��ϴ���ɾ���ȹ��ܽӿ�</p>
 * <p></p>
 */
public interface FileService {
	public	Boolean	FileUpload(String	savePath,File file,String	FileName,String	FileContentType);
	public	Boolean	FileUpload(String	savePath,String saveAsName,File file,String	FileName,String	FileContentType);
	public	InputStream FileUpload(File file,String	FileName,String FileContentType);
}
