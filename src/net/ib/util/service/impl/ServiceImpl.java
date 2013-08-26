
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-5 ����4:16:14
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-5 ����4:16:14
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.sql.BLOB;

import org.apache.log4j.Logger;

import net.ib.common.ConnPool;
import net.ib.util.dao.Dao;
import net.ib.util.dao.DaoImpl;
import net.ib.util.service.Service;


  /**
 * <p>������net.ib.util.service.impl.ServiceImpl </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class ServiceImpl  implements	Service{


	private static Logger logger = Logger.getLogger(ServiceImpl.class);
	 /* (��-Javadoc)
	 * <p>����: DataListToJson</p>
	 * <p>˵��:	��list���������ת��ΪJson�ַ���,json��recordCountΪ��ǰ���ݵ����� </p>
	 * @param list
	 * @return
	 * @see net.ib.util.service.Service#DataListToJson(java.util.List)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String DataListToJson(List<Map> list) {
		// TODO Auto-generated method stub
		
		String	returnStr="{\"recordCount\":";
		//Attention
		returnStr+=list.size();
		returnStr+=",\"data\":[";
		for(int	i=0;i<list.size();i++){
			returnStr+="{";
			Map	map	=	new	HashMap();
			map	=	list.get(i);
			Iterator<?> it = map.entrySet().iterator();
			for(int	j=0;j<map.size();j++){
				Map.Entry entry = (Map.Entry) it.next();
		        String paramName =(String) entry.getKey();
		        String	paramValue	=	(String)entry.getValue();
		        returnStr+="\"";
		        returnStr+=paramName;
		        returnStr+="\":\"";
		        returnStr+=paramValue;
		        if(j!=map.size()-1){
		        	returnStr+="\",";
		        }
		        else{
		        	returnStr	+=	"\"},";
		        }			
			}
		}
		if(list.size()!=0)
			returnStr = returnStr.substring(0, returnStr.length()-1);
		
		returnStr += "]}";
		return	returnStr;
	}

	
	 /* (��-Javadoc)
	 * <p>����: DataListToJson</p>
	 * <p>˵��: ��list���������ת��ΪJson�ַ���,json��recordCountΪ��ҳ��ʾʱ��������</p>
	 * @param list
	 * @param recordCount
	 * @return
	 * @see net.ib.util.service.Service#DataListToJson(java.util.List, int)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String DataListToJson(List<Map> list, int recordCount) {
		// TODO Auto-generated method stub
		String	returnStr="{\"recordCount\":";
		//Attention
		returnStr+=recordCount;
		returnStr+=",\"data\":[";
		for(int	i=0;i<list.size();i++){
			returnStr+="{";
			Map	map	=	new	HashMap();
			map	=	list.get(i);
			Iterator<?> it = map.entrySet().iterator();
			for(int	j=0;j<map.size();j++){
				Map.Entry entry = (Map.Entry) it.next();
		        String paramName =(String) entry.getKey();
		        String	paramValue	=	(String)entry.getValue();
		        returnStr+="\"";
		        returnStr+=paramName;
		        returnStr+="\":\"";
		        returnStr+=paramValue;
		        if(j!=map.size()-1){
		        	returnStr+="\",";
		        }
		        else{
		        	returnStr	+=	"\"},";
		        }			
			}
		}
		if(list.size()!=0)
			returnStr = returnStr.substring(0, returnStr.length()-1);
		
		returnStr += "]}";
		return	returnStr;
	}

	
	 /* (��-Javadoc)
	 * <p>����: DataListToTreeJson</p>
	 * <p>˵��: ʵ�ֶԻ�ȡ���������д���������ΪTreeJson���ַ���</p>
	 * @param list
	 * @return
	 * @see net.ib.util.service.Service#DataListToTreeJson(java.util.List)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String DataListToTreeJson(List<Map> list) {
		// TODO Auto-generated method stub
		String treeJson="[";
		String lastId="root";//��һ�ڵ��id
		String lastPid="";//��һ�ڵ��pid
		int level=0;//ָʾ����
		for(int i=0;i<list.size();i++){
			
			if((list.get(i).get("pid").equals(lastId)) && (!lastId.equals("root"))){//���ýڵ�����һ���ڵ���ӽڵ�ʱ
				treeJson+=",\"expanded\":\"true\""+",\"children\":[";
				level++;
			}else if((list.get(i).get("pid").equals(lastPid)) && (!lastId.equals("root"))){//���ýڵ�����һ�ڵ�ͬ��ʱ
				treeJson+="},";
			}else if(lastId.equals("root")){
				
			}else{//������һ��ʱ
				treeJson+="}]},";//,\"leaf\":\"true\"
				level--;
			}
			
			treeJson+="{";
			Iterator it = list.get(i).keySet().iterator();
			boolean	isFirst	=	true;
			while(it.hasNext()){
				String keytemp = (String) it.next();
				if(isFirst!=true){
					treeJson +=","+"\""+keytemp+"\":\""+list.get(i).get(keytemp)+"\"";//ѭ������ֶ�
				}
				else{
					treeJson +="\""+keytemp+"\":\""+list.get(i).get(keytemp)+"\"";//ѭ������ֶ�
					isFirst=false;
				}
				
			}
			
			lastId=(String) list.get(i).get("id");
			lastPid=(String) list.get(i).get("pid");	
		}
	
		treeJson+="}";
		for(int i=0;i<level;i++){
			treeJson+="]}";
		}
		treeJson+="]";
		logger.debug(treeJson);
		return treeJson;
	}

	
	 /* (��-Javadoc)
	 * <p>����: DataListToJsonForYuiDataTable</p>
	 * <p>˵��: ʵ�ֶԻ�ȡ���������д���������ΪTreeJson���ַ���,ʹ�ø÷���ʱ�����ݱ�����·����������</p>
	 * @param list
	 * @param TotalNum
	 * @param startIndex
	 * @param sort
	 * @param dir
	 * @return
	 * @see net.ib.util.service.Service#DataListToJsonForYuiDataTable(java.util.List, int, int, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String DataListToJsonForYuiDataTable(List<Map> list, int TotalNum,
			int startIndex, String sort, String dir) {
		// TODO Auto-generated method stub
		String	returnStr="{\"recordsReturned\":";
		//Attention
		returnStr+=list.size();
		returnStr+=",\"totalRecords\":"+TotalNum;
		returnStr+=",\"startIndex\":"+startIndex;
		returnStr+=",\"sort\":\""+sort+"\"";
		returnStr+=",\"dir\":\""+dir+"\"";
		returnStr+=",\"records\":[";
		for(int	i=0;i<list.size();i++){
			returnStr+="{";
			Map	map	=	new	HashMap();
			map	=	list.get(i);
			Iterator<?> it = map.entrySet().iterator();
			for(int	j=0;j<map.size();j++){
				Map.Entry entry = (Map.Entry) it.next();
		        String paramName =(String) entry.getKey();
		        String	paramValue	=	(String)entry.getValue();
		        returnStr+="\"";
		        returnStr+=paramName;
		        returnStr+="\":\"";
		        returnStr+=paramValue;
		        if(j!=map.size()-1){
		        	returnStr+="\",";
		        }
		        else{
		        	returnStr	+=	"\"},";
		        }			
			}
		}
		if(list.size()!=0)
			returnStr = returnStr.substring(0, returnStr.length()-1);
		
		returnStr += "]}";
		return	returnStr;
	}


	
	 /* (��-Javadoc)
	 * <p>����: DataListToTreeJson</p>
	 * <p>˵��: �������list<Map>ת��Ϊjson</p>
	 * @param list
	 * @param params��tablename�Ǳ���ģ��������ж�һ���ڵ��Ƿ���Ҷ�ӽڵ�ʱ��ѯʹ��
	 * @return
	 * @see net.ib.util.service.Service#DataListToTreeJson(java.util.List, java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String DataListToTreeJson(List<Map> list, Map<?, ?> params) {
		// TODO Auto-generated method stub
		String treeJson="[";
		String lastId="root";//��һ�ڵ��id
		String lastPid="";//��һ�ڵ��pid
		int level=0;//ָʾ����
		TypeChange	tc	=	new	TypeChange();
		for(int i=0;i<list.size();i++){
			
			if((list.get(i).get("pid").equals(lastId)) && (!lastId.equals("root"))){//���ýڵ�����һ���ڵ���ӽڵ�ʱ
				treeJson+=",\"expanded\":\"true\""+",\"children\":[";
				level++;
			}else if((list.get(i).get("pid").equals(lastPid)) && (!lastId.equals("root"))){//���ýڵ�����һ�ڵ�ͬ��ʱ
				treeJson+="},";
			}else if(lastId.equals("root")){
				
			}else{//������һ��ʱ
				int	currentLevel	=	tc.stringToInt((String)list.get(i).get("node_series"));
				treeJson+="}";//"]}";//,\"leaf\":\"true\"
				while(currentLevel<=level){
					treeJson+="]}";
					level--;
				}
				treeJson+=",";
			}
			
			treeJson+="{";
			Iterator it = list.get(i).keySet().iterator();
			boolean	isFirst	=	true;
			while(it.hasNext()){
				String keytemp = (String) it.next();
				if(isFirst!=true){
					treeJson +=","+"\""+keytemp+"\":\""+list.get(i).get(keytemp)+"\"";//ѭ������ֶ�
				}
				else{
					treeJson +="\""+keytemp+"\":\""+list.get(i).get(keytemp)+"\"";//ѭ������ֶΡ�
					isFirst=false;
				}
				
			}
			String	sqlGetIfLeaf	=	"select * from	"+params.get("tablename")+" where pid='"+list.get(i).get("id")+"'";
			Dao	di	=	new	DaoImpl();
			List<Map> childList =di.executeQuery(sqlGetIfLeaf);
			if(0==childList.size()){
				treeJson+=",\"leaf\":\"true\"";
			}
			else{
				treeJson+=",\"leaf\":\"false\"";
			}
			
			lastId=(String) list.get(i).get("id");
			lastPid=(String) list.get(i).get("pid");	
		}

		treeJson+="}";
		for(int i=0;i<level;i++){
			treeJson+="]}";
		}
		treeJson+="]";
		logger.debug(treeJson);
		return treeJson;
	}
	 /* (��-Javadoc)
	 * <p>����: exercuteInsertWithBlob</p>
	 * <p>˵��: ִ��blob������</p>
	 * @param TableName			����
	 * @param IdField			Id�ֶ���
	 * @param Id				Id
	 * @param FieldName			Blob�ֶ���
	 * @param InStream			Ҫ�����Blob��io��
	 * @return
	 * @see net.ib.util.dao.Dao#exercuteInsertWithBlob(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.InputStream)
	 */
	@Override
	public String exercuteInsertWithBlob(String TableName, String IdField,
			String Id, String FieldName, InputStream InStream) {
			Connection connection = null;
			Statement statement = null;
			OutputStream outStream =null;
			String	result="true";
			String	Sql	=	"select "+FieldName+" from "+TableName+" where "+IdField+"='"+Id+"' for update";
			try {
				connection = ConnPool.getConnection();
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(Sql);
				if (rs.next()) {
					logger.debug("come in");
					// �õ�java.sql.Blob����Ȼ��CastΪoracle.sql.BLOB
					oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob(1);
					// �����ݿ�������
					outStream = blob.getBinaryOutputStream();
					// ��������д�������
					byte[] b = new byte[blob.getBufferSize()];
					int len = 0;
					while ((len = InStream.read(b)) != -1) {
						outStream.write(b, 0, len);
					}
				}
				else{
					logger.debug("don't come in");
					result="nodata";
				}
				if(null!=InStream){
					InStream.close();  
				}
				if(null!=outStream){
					outStream.flush();   
					outStream.close();
				} 
				if(null!=connection){
					connection.commit();   
				}
				ConnPool.freeResource(rs, statement, connection);
			} catch (Exception e) {
				e.printStackTrace();
				result="error";
			}
			return result;
		/*	try {
				connection = ConnPool.getConnection();
				statement = connection.createStatement();
				//��ѯBlob, ���Blob��Cursor   
				ResultSet rs = statement.executeQuery(Sql);
				   BLOB blob = null;   
			         if(rs.next())   
			         {   
			             blob = (BLOB)rs.getBlob(1);   
			         }   
			         //ʹ���ֽ������������ļ�д�뵽blob��   
			       InputStream fin = InStream;   
			       byte[] temp = new byte[fin.available()];   
			         fin.read(temp);   
			         OutputStream out = blob.getBinaryOutputStream();   
			         out.write(temp);   
			         fin.close();   
			         out.close();   
			         //�����ݿ���д������   
			           String  sql = "update "+TableName+" set "+FieldName+" = ? where "+IdField+" = '"+Id+"'"; 
			           PreparedStatement ps = connection.prepareStatement(sql);  
			            ps.setBlob(1, blob);   
			            ps.setInt(2, 1);   
			            ps.executeUpdate();  
			            connection.commit(); 
		
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			
		// TODO Auto-generated method stub
	}
	
	 /* (��-Javadoc)
	 * <p>����: exercuteUpdateWithBlob</p>
	 * <p>˵��: ��Blobִ�и���</p>
	 * @param TableName			����
	 * @param IdField			Id�ֶ���
	 * @param Id				Id
	 * @param FieldName			Blob�ֶ���
	 * @param InStream			Ҫ�����Blob��io��
	 * @return					"true":�ɹ�"��error":�����쳣��"nodata":û�и�����
	 * @see net.ib.util.dao.Dao#exercuteUpdateWithBlob(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.io.InputStream)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public String exercuteUpdateWithBlob(String TableName, String IdField,
			String Id, String FieldName, InputStream InStream) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;
		OutputStream outStream =null;
		String	Sql	=	"select "+FieldName+" from "+TableName+" where "+IdField+"='"+Id+"' for update";
		//String	Sql	=	"select "+FieldName+" from "+TableName+" where "+IdField+"='103' for update";
		String	result="true";
		logger.debug(Sql);
		try {
			connection = ConnPool.getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(Sql);
			if (rs.next()) {
				logger.debug("come in");
				// �õ�java.sql.Blob����Ȼ��CastΪoracle.sql.BLOB
				oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob(1);
				// �����ݿ�������
				outStream = blob.getBinaryOutputStream();
				// ��������д�������
				byte[] b = new byte[blob.getBufferSize()];
				int len = 0;
				while ((len = InStream.read(b)) != -1) {
					outStream.write(b, 0, len);
				}
			}
			else{
				logger.debug("don't come in");
				result="nodata";
			}
			if(null!=InStream){
				InStream.close();  
			}
			if(null!=outStream){
				outStream.flush();   
				outStream.close();
			} 
			if(null!=connection){
				connection.commit();   
			}
			ConnPool.freeResource(rs, statement, connection);
		} catch (Exception e) {
			e.printStackTrace();
			result="error";
		}
		return result;
	}
	
	 /* (��-Javadoc)
	 * <p>����: getBlobInputStreamFromTable</p>
	 * <p>˵��: �ӱ��л�ȡBlob�����IO��</p>
	 * @param TableName			����
	 * @param IdField			Id�ֶ���
	 * @param Id				Id
	 * @param FieldName			Blob�ֶ���
	 * @return
	 * @see net.ib.util.dao.Dao#getBlobInputStreamFromTable(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public InputStream getBlobInputStreamFromTable(String TableName,
			String IdField, String Id, String FieldName) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;
		java.io.InputStream in =null;
		String	Sql	=	"select "+FieldName+" from "+TableName+" where "+IdField+"='"+Id+"'";
		try {
			connection = ConnPool.getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(Sql);
			if (rs.next()) {
				in= rs.getBinaryStream(FieldName);
			}
			ConnPool.freeResource(rs, statement, connection);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}
}
