
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TreeDataService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-21 ����4:25:23
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-21 ����4:25:23
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.TreeDataService </p>
 * <p>������ϵͳ���漰�����ṹ����Jsonҵ��֧�Žӿ�</p>
 * <p></p>
 */
public interface TreeDataService {
	public	String	getTeacherDepTreeData(String TableName,String TreeId);
	public  String  getFunctionTreeData(String TableName, String TreeId);
	public	String  getChapterTreeData(String TableName,String TreeId);
	
}
