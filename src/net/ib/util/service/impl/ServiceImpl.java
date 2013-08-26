
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-5 下午4:16:14
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-5 下午4:16:14
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
 * <p>类名：net.ib.util.service.impl.ServiceImpl </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class ServiceImpl  implements	Service{


	private static Logger logger = Logger.getLogger(ServiceImpl.class);
	 /* (非-Javadoc)
	 * <p>名称: DataListToJson</p>
	 * <p>说明:	将list传入的数据转换为Json字符串,json的recordCount为当前数据的总数 </p>
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

	
	 /* (非-Javadoc)
	 * <p>名称: DataListToJson</p>
	 * <p>说明: 将list传入的数据转换为Json字符串,json的recordCount为分页显示时数据总数</p>
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

	
	 /* (非-Javadoc)
	 * <p>名称: DataListToTreeJson</p>
	 * <p>说明: 实现对获取到的树进行处理，处理结果为TreeJson的字符串</p>
	 * @param list
	 * @return
	 * @see net.ib.util.service.Service#DataListToTreeJson(java.util.List)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String DataListToTreeJson(List<Map> list) {
		// TODO Auto-generated method stub
		String treeJson="[";
		String lastId="root";//上一节点的id
		String lastPid="";//上一节点的pid
		int level=0;//指示层数
		for(int i=0;i<list.size();i++){
			
			if((list.get(i).get("pid").equals(lastId)) && (!lastId.equals("root"))){//当该节点是上一个节点的子节点时
				treeJson+=",\"expanded\":\"true\""+",\"children\":[";
				level++;
			}else if((list.get(i).get("pid").equals(lastPid)) && (!lastId.equals("root"))){//当该节点与上一节点同级时
				treeJson+="},";
			}else if(lastId.equals("root")){
				
			}else{//当回退一层时
				treeJson+="}]},";//,\"leaf\":\"true\"
				level--;
			}
			
			treeJson+="{";
			Iterator it = list.get(i).keySet().iterator();
			boolean	isFirst	=	true;
			while(it.hasNext()){
				String keytemp = (String) it.next();
				if(isFirst!=true){
					treeJson +=","+"\""+keytemp+"\":\""+list.get(i).get(keytemp)+"\"";//循环添加字段
				}
				else{
					treeJson +="\""+keytemp+"\":\""+list.get(i).get(keytemp)+"\"";//循环添加字段
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

	
	 /* (非-Javadoc)
	 * <p>名称: DataListToJsonForYuiDataTable</p>
	 * <p>说明: 实现对获取到的树进行处理，处理结果为TreeJson的字符串,使用该方法时，数据必须以路径进行排序</p>
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


	
	 /* (非-Javadoc)
	 * <p>名称: DataListToTreeJson</p>
	 * <p>说明: 将传入的list<Map>转换为json</p>
	 * @param list
	 * @param params中tablename是必须的，用来在判断一个节点是否是叶子节点时查询使用
	 * @return
	 * @see net.ib.util.service.Service#DataListToTreeJson(java.util.List, java.util.Map)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String DataListToTreeJson(List<Map> list, Map<?, ?> params) {
		// TODO Auto-generated method stub
		String treeJson="[";
		String lastId="root";//上一节点的id
		String lastPid="";//上一节点的pid
		int level=0;//指示层数
		TypeChange	tc	=	new	TypeChange();
		for(int i=0;i<list.size();i++){
			
			if((list.get(i).get("pid").equals(lastId)) && (!lastId.equals("root"))){//当该节点是上一个节点的子节点时
				treeJson+=",\"expanded\":\"true\""+",\"children\":[";
				level++;
			}else if((list.get(i).get("pid").equals(lastPid)) && (!lastId.equals("root"))){//当该节点与上一节点同级时
				treeJson+="},";
			}else if(lastId.equals("root")){
				
			}else{//当回退一层时
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
					treeJson +=","+"\""+keytemp+"\":\""+list.get(i).get(keytemp)+"\"";//循环添加字段
				}
				else{
					treeJson +="\""+keytemp+"\":\""+list.get(i).get(keytemp)+"\"";//循环添加字段、
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
	 /* (非-Javadoc)
	 * <p>名称: exercuteInsertWithBlob</p>
	 * <p>说明: 执行blob插入行</p>
	 * @param TableName			表名
	 * @param IdField			Id字段名
	 * @param Id				Id
	 * @param FieldName			Blob字段名
	 * @param InStream			要插入的Blob的io流
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
					// 得到java.sql.Blob对象，然后Cast为oracle.sql.BLOB
					oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob(1);
					// 到数据库的输出流
					outStream = blob.getBinaryOutputStream();
					// 将输入流写到输出流
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
				//查询Blob, 获得Blob的Cursor   
				ResultSet rs = statement.executeQuery(Sql);
				   BLOB blob = null;   
			         if(rs.next())   
			         {   
			             blob = (BLOB)rs.getBlob(1);   
			         }   
			         //使用字节流将待入库的文件写入到blob中   
			       InputStream fin = InStream;   
			       byte[] temp = new byte[fin.available()];   
			         fin.read(temp);   
			         OutputStream out = blob.getBinaryOutputStream();   
			         out.write(temp);   
			         fin.close();   
			         out.close();   
			         //向数据库中写入数据   
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
	
	 /* (非-Javadoc)
	 * <p>名称: exercuteUpdateWithBlob</p>
	 * <p>说明: 对Blob执行更新</p>
	 * @param TableName			表名
	 * @param IdField			Id字段名
	 * @param Id				Id
	 * @param FieldName			Blob字段名
	 * @param InStream			要插入的Blob的io流
	 * @return					"true":成功"；error":出现异常；"nodata":没有该数据
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
				// 得到java.sql.Blob对象，然后Cast为oracle.sql.BLOB
				oracle.sql.BLOB blob = (oracle.sql.BLOB) rs.getBlob(1);
				// 到数据库的输出流
				outStream = blob.getBinaryOutputStream();
				// 将输入流写到输出流
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
	
	 /* (非-Javadoc)
	 * <p>名称: getBlobInputStreamFromTable</p>
	 * <p>说明: 从表中获取Blob对象的IO流</p>
	 * @param TableName			表名
	 * @param IdField			Id字段名
	 * @param Id				Id
	 * @param FieldName			Blob字段名
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
