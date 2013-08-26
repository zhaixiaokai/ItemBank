
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TeachingmaterialServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：wuzexi
 * | 创建日期：2012-12-6 下午8:22:24
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 下午8:22:24
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.TeachingmaterialService;
import net.ib.util.service.Service;


  /**
 * <p>类名：net.ib.util.service.impl.TeachingmaterialServiceImpl </p>
 * <p>描述：教材管理的服务实现类</p>
 * <p></p>
 */
public class TeachingmaterialServiceImpl implements TeachingmaterialService {

	private static Logger logger = Logger.getLogger(TeachingmaterialServiceImpl.class);
	private	Dao	dao;
	private	Service	service;
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	/* (非-Javadoc)
	 * <p>名称: addTeachingMaterial</p>
	 * <p>说明: </p>
	 * @param curseId
	 * @param teachingMaterialID
	 * @param teachingMaterialName
	 * @param teachingMaterialAuthor
	 * @return
	 * @see net.ib.util.service.TeachingmaterialService#addTeachingMaterial(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addTeachingMaterial(String curseId,String teachingMaterialID,String teachingMaterialName,String teachingMaterialVersion,String teachingMaterialAuthor,String teachingMaterialDate,String teachingMaterialHouse,String teachingMaterialCode) {
		// TODO Auto-generated method stub
		String result = "";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		
		String	CheckExistSql	=	"select * from SYS_CURSE where CURSE_ID='"+curseId+"'";
		List<Map>	CheckList	=	dao.executeQuery(CheckExistSql);
		if(CheckList.size()==0){
			logger.info("添加教材过程中未找到提交的课程");
			result = "{\"result\":\"failedId\",\"text\":\"该课程不存在！\"}";
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//通过id、name查询是否存在与当前所要添加的项有重复的项
		String	Sql	=	"select TEACHING_MATERIAL_ID,NAME,CURSE_ID from SYS_TEACHING_MATERIAL where TEACHING_MATERIAL_ID='"+teachingMaterialID
				+"' or NAME='"+teachingMaterialName+"'";
		List<Map>	list	=	dao.executeQuery(Sql);
		
		//如果有重复项
		if(list.size()!=0){
			String	TempId="";
			String	TempName="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				TempId	=	(String) map.get("teaching_material_id");
				TempName=	(String) map.get("name");
				logger.debug(TempName);
				logger.debug(TempId);
				if(TempId.equals(teachingMaterialID)){
					logger.info("添加教材过程中教材编号重复");
					result = "{\"result\":\"failedId\",\"text\":\"教材编号已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(TempName.equals(teachingMaterialName)){
					logger.info("添加教材过程中教材名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"教材名称已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//没有重复项
		else{
			Sql="insert into SYS_TEACHING_MATERIAL (TEACHING_MATERIAL_ID,NAME,CURSE_ID,VERSION,AUTHOR,PUBLICATION_DATE,PUBLISHING_HOUSE,ISBN_CODE) values " +
					"('"+teachingMaterialID+"','"+teachingMaterialName+"','"+curseId+
				    "','"+teachingMaterialVersion+"','"+teachingMaterialAuthor+"',to_date('"+teachingMaterialDate+"','yyyy-MM-dd')," +
				    "'"+teachingMaterialHouse+"','"+teachingMaterialCode+
					"')";
			
			String Sql2="insert into SYS_TEACHING_CHAPTER_TREE (TEXT,TREE_ID,SNO,PID,NODE_PATH,NODE_SERIES,NODE_EXPLAIN) values " +
					"('"+teachingMaterialName+"','"+teachingMaterialID+"','1','root','0001','1','')";
			
			if((1==dao.execute(Sql))&&(1==dao.execute(Sql2))){
				result = "{\"result\":\"succ\",\"text\":\"添加教材成功\"}";
				try {
					PrintWriter pw = response.getWriter();
					pw.print(result);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}
	@Override
	public String updateTeachingMaterial(String oldTeachingMaterialId,String teachingMaterialName,String	teachingMaterialID,String	teachingMaterialVersion,String	teachingMaterialAuthor,String teachingMaterialDate,String teachingMaterialHouse,String teachingMaterialCode)
	{
		// TODO Auto-generated method stub
		logger.debug(oldTeachingMaterialId);
		logger.debug(teachingMaterialName);
		logger.debug(teachingMaterialID);
		logger.debug(teachingMaterialVersion);
		logger.debug(teachingMaterialAuthor);
		logger.debug(teachingMaterialDate);
		logger.debug(teachingMaterialHouse);
		logger.debug(teachingMaterialCode);
		String sql = "select teaching_material_id,name from sys_teaching_material where (teaching_material_id='"
				+ teachingMaterialID + "' or name='" + teachingMaterialName + "') and teaching_material_id!='"+oldTeachingMaterialId+"'";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		List<Map>	list	=	dao.executeQuery(sql);
		String	result="";
		
		if (list.size() == 0) {
		
			//开始插入数据
			sql = "update sys_teaching_material set teaching_material_id='" + teachingMaterialID
					+ "',name='" + teachingMaterialName 
					+ "',version='" +teachingMaterialVersion
					+ "',author='"+ teachingMaterialAuthor
					+ "',publication_date=to_date('"+teachingMaterialDate+"','yyyy-MM-dd')"
					+ ",publishing_house='"+ teachingMaterialHouse
					+ "',isbn_code='"+ teachingMaterialCode
					+ "' where teaching_material_id='" + oldTeachingMaterialId + "'";

			logger.debug(sql);
			dao.execute(sql);
			result = "{\"result\":\"succ\",\"text\":\"修改教材成功\"}";
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		//有重复
		else{
			Map map = new HashMap();
			String ClassId = "";
			String ClassName = "";
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				ClassId = (String) map.get("teaching_material_id");
				ClassName = (String) map.get("name");
				// 如果班级Id重复
				if (teachingMaterialID.equals(ClassId)) {
					logger.info("修改教材过程中Id重复");
					result = "{\"result\":\"failedId\",\"text\":\"教材ID已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}
				// 班级
				else if (teachingMaterialName.equals(ClassName)) {
					logger.info("修改教材过程中名称重复");
					result = "{\"result\":\"failedName\",\"text\":\"教材名称已经存在，请重新输入\"}";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				} 

				else {
					logger.info("修改教材发生异常");
				}
			}			
		}
		return null;
	}
	@Override
	public String deleteTeachingMaterial(String deleteId) {
		// TODO Auto-generated method stub
		logger.debug(deleteId);
		
		String	sql	=	"delete from sys_teaching_material where TEACHING_MATERIAL_ID='"+deleteId+"'";
		logger.debug(sql);
		
		String	sql2	=	"delete from sys_teaching_chapter_tree where TREE_ID='"+deleteId+"'";
		logger.debug(sql2);
		
		String	result="";
		if((1==dao.execute(sql))&&(1==dao.execute(sql2))){
			result	=	"{\"result\":\"删除教材成功\"}";
		}
		else{
			result	=	"{\"result\":\"删除教材失败\"}";
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String bulkDeleteTeachingMaterial(String	deleteIds) {
		// TODO Auto-generated method stub
		logger.debug(deleteIds);
		//收到的DelIds格式为   id1 id2 id3 id4 ....
		String[] array=deleteIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		
		String	Sql	=	"delete from sys_teaching_material where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
			}
			Sql+=" TEACHING_MATERIAL_ID='"+array[i]+"'";
		}
		logger.debug(Sql);
		
		String	Sql2	=	"delete from sys_teaching_chapter_tree where";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql2+=" or";
			}
			Sql2+=" TREE_ID='"+array[i]+"'";
		}
		logger.debug(Sql2);
		
		if(1==dao.execute(Sql)&&1==dao.execute(Sql2)){
			result="{\"result\":\"success\",\"text\":\"批量删除教材成功\"}";
		}
		else{
			result="{\"result\":\"failed\",\"text\":\"批量删除教材失败\"}";
		}
		
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(result);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public	String getTeachMaterialByCourseId(String id){
		
		String	result	=	null;
		
		
		String	sql	=	"select * from sys_teaching_material where curse_id='"+id+"'";
		List<Map>	list	=	dao.executeQuery(sql);
		result=service.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(result);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}