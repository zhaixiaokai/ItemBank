package net.ib.util.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface Dao{
	/**
	 * 
	 * <p>名称：execute</p>
	 * <p>说明：执行指定sql语句</p>
	 * <p>参数：@param sql
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：int 返回类型</p>
	 * <p>@param sql
	 * <p>@return</p>
	 */
	public int execute(String sql);
	/**
	 * 
	 * <p>名称：executeQuery</p>
	 * <p>说明：执行指定查询的sql语句，返回结果集</p>
	 * <p>参数：@param sql
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：List<Map> 返回类型</p>
	 * <p>@param sql
	 * <p>@return</p>
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> executeQuery(String sql);

}
