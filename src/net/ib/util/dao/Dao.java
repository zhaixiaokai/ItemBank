package net.ib.util.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface Dao{
	/**
	 * 
	 * <p>���ƣ�execute</p>
	 * <p>˵����ִ��ָ��sql���</p>
	 * <p>������@param sql
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��int ��������</p>
	 * <p>@param sql
	 * <p>@return</p>
	 */
	public int execute(String sql);
	/**
	 * 
	 * <p>���ƣ�executeQuery</p>
	 * <p>˵����ִ��ָ����ѯ��sql��䣬���ؽ����</p>
	 * <p>������@param sql
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��List<Map> ��������</p>
	 * <p>@param sql
	 * <p>@return</p>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> executeQuery(String sql);

}
