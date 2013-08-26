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
 * <p>������net.ib.util.dao.DaoImpl </p>
 * <p>������ʵ�ֻ����Ķ����ݿ��������</p>
 */
public class DaoImpl implements Dao {
	//
	private static Logger logger = Logger.getLogger(DaoImpl.class);
	public int execute(String sql){

		Connection connection = null;
		Statement statement = null;
		ResultSet	rs	=	null;
		
		int flag = 1;

		logger.debug("ִ�еġ�SQL:		"+sql);
			
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
			//rsmt��ִ��sql��䷵������
			while(resultset.next()){
				//ѭ�����list���ݣ����������Ϊlist���һ����¼����¼����Ϊmap
				logger.debug("����һ���µ�����");
					Map map = new HashMap();
					//ѭ������ֵ��д��map���Ա��ڽ�map��ӵ�list��
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
