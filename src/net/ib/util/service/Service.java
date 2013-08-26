package net.ib.util.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface Service {
	public String DataListToJson(List<Map> list);
	public	String	DataListToJson(List<Map> list,int	recordCount);
	public	String	DataListToTreeJson(List<Map> list);
	public String DataListToTreeJson(List<Map> list,Map<?, ?> params);
	public	String	DataListToJsonForYuiDataTable(List<Map> list,int	TotalNum,int startIndex,String	sort,String dir);
	/**
	 * 
	 * <p>名称：exercuteInsertWithBlob</p>
	 * <p>说明：带blob数据行插入</p>
	 * <p>参数：@param TableName		表名
	 * <p>参数：@param IdField		Id字段名
	 * <p>参数：@param Id				Id
	 * <p>参数：@param FieldName		Blob字段名
	 * <p>参数：@param InStream		插入的Blob的IO流
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 */
	public	String	exercuteInsertWithBlob(String TableName,String	IdField,String Id,String FieldName,InputStream InStream);
	/**
	 * 
	 * <p>名称：exercuteUpdateWithBlob</p>
	 * <p>说明：更新某表格中指定数据项中的blob字段内容</p>
	 * <p>参数：@param TableName		表名
	 * <p>参数：@param IdField		Id字段名
	 * <p>参数：@param Id				Id
	 * <p>参数：@param FieldName		Blob字段名
	 * <p>参数：@param InStream		插入的Blob的IO流
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 */
	public	String	exercuteUpdateWithBlob(String TableName,String	IdField,String Id,String FieldName,InputStream InStream);
	/**
	 * 
	 * <p>名称：getBlobInputStreamFromTable</p>
	 * <p>说明：从数据表中某一条数据中的某个字段读取blob，并且返回IO流</p>
	 * <p>参数：@param TableName		表名
	 * <p>参数：@param IdField		Id字段名
	 * <p>参数：@param Id				Id
	 * <p>参数：@param FieldName		Blob字段名
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：InputStream 返回类型</p>
	 */
	public	InputStream	getBlobInputStreamFromTable(String	TableName,String IdField,String Id,String FieldName);
}
