package net.ib.util.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import net.ib.common.ConnPool;

/**
 * <p>类名：net.ib.util.dao.DaoImpl </p>
 * <p>描述：实现基本的对数据库操作功能</p>
 */
public class DaoImpl implements Dao {
	//
	private static Logger logger = Logger.getLogger(DaoImpl.class);
	public int execute(String sql){

		Connection connection = null;
		Statement statement = null;
		ResultSet	rs	=	null;
		
		int flag = 1;

		logger.debug("执行的SQL:		"+sql);
			
		try{
			connection=ConnPool.getConnection();
			statement = connection.createStatement();
			statement.execute(sql);
		}
		catch (Exception e) {
			//	throw new RuntimeException(e);
				e.printStackTrace();
				logger.debug("Excute	failed!!!");
				flag = 0;
			}
		finally {
			ConnPool.freeResource(rs, statement, connection);
		}
		
		return flag;
		
	}
	//
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> executeQuery(String sql){
		logger.debug("the sql executing is:"+sql);
		ArrayList<Map> list =new ArrayList<Map>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		try {
			connection = ConnPool.getConnection();
			statement = connection.createStatement();
			resultset = statement.executeQuery(sql);
			ResultSetMetaData rsmt = resultset.getMetaData();
			//rsmt是执行sql语句返回类型
			while(resultset.next()){
				//循环组合list内容，如果有数据为list添加一条记录，记录内容为map
				logger.debug("这是一条新的数据");
					Map map = new HashMap();
					//循环将键值对写入map，以便于讲map添加到list中
					for(int i=0;i<rsmt.getColumnCount();i++){
						map.put(rsmt.getColumnName(i+1).toLowerCase(), resultset.getString(rsmt.getColumnName(i+1)));
						logger.debug(rsmt.getColumnName(i+1)+" : "+resultset.getString(rsmt.getColumnName(i+1)));
					}
					list.add(map);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnPool.freeResource(resultset, statement, connection);
		}
		
		return list;
		
	}
	

}
