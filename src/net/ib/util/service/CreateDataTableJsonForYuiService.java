
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	CreateDataTableJsonForYui.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-6 ����10:30:02
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-6 ����10:30:02
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.util.Map;


  /**
 * <p>������net.ib.util.service.CreateDataTableJsonForYui </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public interface CreateDataTableJsonForYuiService {
	public	String	CreateDataTableJson(String	dir,String	results,String	sort,String	startIndex,String	getDataSql,String getCountSql);
	public	String	CreateDataTableJson(Map ConfigMap,Map SqlMap);
	public	String	CreateDataTableJsonFullSql(String	dir,String	results,String	sort,String	startIndex,String	getDataSql,String getCountSql);
};
