
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	SortService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�lijuan
 * | �������ڣ�2012-12-4 ����10:43:54
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-4 ����10:43:54
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>������net.ib.util.service.SortService </p>
 * <p>������������ϵ�ӿ�</p>
 * <p></p>
 */
public interface SortService {
	public String SortAdd(String name,String identifier,String discription) ;
	public String SortDelete(String id);
	public String SortBulkDelete(String id);
	public String SortModify(String id,String name,String identifier,String discription);
	public String SortManageDataSource(String TableName, String TreeId); 
	public String GetSortId(String SortName);
	public String SortSelect();
	public String sortTreeOperateActionIfRootId(String sortNodeId);
}
