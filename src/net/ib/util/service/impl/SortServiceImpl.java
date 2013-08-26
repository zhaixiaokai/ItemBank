/**
 * -------------------------------------------------------------------------------------
 * | 文件名：	SortServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：lijuan
 * | 创建日期：2012-12-4 上午10:54:04
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-4 上午10:54:04
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;
import java.io.IOException;
import net.ib.util.service.Service;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import net.ib.util.dao.Dao;
import net.ib.util.dao.DaoImpl;
import net.ib.util.service.SortService;

/**
 * <p>
 * 类名：net.ib.util.service.impl.SortServiceImpl
 * </p>
 * <p>
 * 描述：分类体系接口实现
 * </p>
 * <p>
 * </p>
 */
public class SortServiceImpl implements SortService {
	Service service;
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	private Dao di;



	public Dao getDi() {
		return di;
	}

	public void setDi(Dao di) {
		this.di = di;
	}

	private static Logger logger = Logger.getLogger(SortServiceImpl.class);

	/**
	 * 添加分类体系
	 */
	public String SortAdd(String name, String identifier, String discription) {
		String result;
		/* DaoImpl di = new DaoImpl(); */
		// Dao di = (Dao) BeanLoader.getBean("DaoImpl");
		// 判断分类体系名称是否重复
		List<Map> list = (List<Map>) di
				.executeQuery("select * from sys_ib_classification_system where classification_name='"
						+ name
						+ "' or classification_id='"
						+ identifier
						+ "'");
		for (int i = 0; i < list.size(); i++) {
			String classification_name = (String) list.get(i).get(
					"classification_name");
			String classification_id = (String) list.get(i).get(
					"classification_id");
			// 判断分类体系名称是否重复
			if (classification_name.equals(name)) {
				result = "名称重复，请重新输入";
				return result;
			}
			// 判断分类体系标识符时候重复
			if (classification_id.equals(identifier)) {
				result = "标识符重复，请重新输入";
				return result;
			}
		}
		//插入分类体系数据
		di.execute("insert into sys_ib_classification_system (classification_id,classification_name,classification_explain) values('"
					+ identifier + "','" + name + "','" + discription.trim()+ "')");
		//为分类体系添加根节点
		di.execute("insert into sys_ib_classification_tree (id,tree_id,text,pid,sno,node_path,node_series,node_explain) values(sys_guid(),'"+identifier+"','"+name+"','0',1,'0001',1,'无')");
		result = "添加成功";
		return result;
	}

	/**
	 * 删除分类体系
	 * 
	 */
	public String SortDelete(String id) {
		String getSql = "delete from sys_ib_classification_system	where	classification_id='"
				+ id + "'";
		String result = "";
		if (getSql != null) {
			if (di.execute(getSql) != 1) {
				//删除分类体系根节点
			
				result = "删除失败!";
			} else {
				di.execute("delete from sys_ib_classification_tree	where	tree_id='"+ id + "'");
				result = "删除成功";
			}
		}
		return result;
	}
	
	
	/**
	 * 批量删除分类体系
	 */
	public String SortBulkDelete(String id) {
		// TODO Auto-generated method stub
		//收到的DelIds格式为   id1 id2 id3 id4 ....
		String[] array=id.split(" ");
		String	Sql	=	"delete from sys_ib_classification_system where";
		String rootnodeSql="delete from sys_ib_classification_tree where";
		String	result="";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				Sql+=" or";
				rootnodeSql+=" or";
			}
			Sql+=" classification_id='"+array[i]+"'";
			rootnodeSql+=" tree_id='"+array[i]+"'";
		}
		if (Sql != null) {
			if (di.execute(Sql) != 1) {
				
				result = "删除失败!";
			} else {
				di.execute(rootnodeSql);
				result = "删除成功";
			}
		}
		return result;
	}
	
	

	/**
	 * 分类体系修改
	 */

	public String SortModify(String id, String name, String identifier,
			String discription) {
		String result = "";
		String oldname = "";
		/*String olddefaultsort = "";
		String is_exist_defaultsort_id = "";*/
		discription=discription.trim();
		/*if (defaultsort.trim().toUpperCase().equals("YES")) {
			defaultsort = "1";
		} else {
			defaultsort = "0";
		}
		// 从数据库中取出设置为默认分类体系的数据
		List<Map> listdefaultsort = (List<Map>) di
				.executeQuery("select * from sys_ib_classification_system where if_default_classification=1 ");
		if (listdefaultsort.size() > 0) {
			is_exist_defaultsort_id = (String) listdefaultsort.get(0).get(
					"classification_id");
		} else {
			is_exist_defaultsort_id = "notexist";
		}*/
		// 从数据库中取出修改前的数据
		List<Map> list = (List<Map>) di
				.executeQuery("select * from sys_ib_classification_system where classification_id='"
						+ id + "' ");
		for (int i = 0; i < list.size(); i++) {
			oldname = (String) list.get(i).get("classification_name");
		/*	olddefaultsort = (String) list.get(i).get(
					"if_default_classification");*/
		}
		// 若标识符和标题均已修改
		if (!oldname.equals(name) && !id.equals(identifier)) {
			// 判断新的名称是否和数据库中的名称重复
			List<Map> listname = (List<Map>) di
					.executeQuery("select * from sys_ib_classification_system where classification_name='"
							+ name + "' ");
			// 若新的分类体系名称有重复
			if (listname.size() > 0) {
				result = "名称重复，请重新输入";
				return result;
			}
			// 判断新的标识符是否和数据库中的名称重复
			List<Map> listidentifier = (List<Map>) di
					.executeQuery("select * from sys_ib_classification_system where classification_name='"
							+ name + "' ");
			// 若新的分类体系标识符有重复
			if (listidentifier.size() > 0) {
				result = "标识符重复，请重新输入";
				return result;
			}
			//修改分类体系数据
			di.execute("update sys_ib_classification_system set classification_name='"
					+ name
					+ "',classification_id='"
					+ identifier
					+ "',classification_explain='"
					+ discription
					+ "' 	where	classification_id='" + id + "'");
			//修改节点数据
			di.execute("update sys_ib_classification_tree set text='"+name+"',tree_id='"+identifier+"'where	tree_id='"+id+"' and pid='0'");
			
			/*// 若是否为默认分类体系未修改
			if (olddefaultsort.equals(defaultsort)) {
				String Sql = "update sys_ib_classification_system set classification_name='"
						+ name
						+ "',classification_id='"
						+ identifier
						+ "',classification_explain='"
						+ discription
						+ "' 	where	classification_id='" + id + "'";
				di.execute(Sql);
			} else {
				// 若默认分类体系由否修改为是
				if (defaultsort.equals("1")) {
					// 若数据表中没有设置为默认分类体系的数据
					if (is_exist_defaultsort_id.equals("notexist")) {
						// 更新数据
						String Sql = "update sys_ib_classification_system set classification_name='"
								+ name
								+ "',classification_id='"
								+ identifier
								+ "',if_default_classification="
								+ defaultsort
								+ ",classification_explain='"
								+ discription
								+ "' 	where	classification_id='" + id + "'";
						di.execute(Sql);
					} else {
						// 若有
						// 更新数据
						di.executeQuery("update sys_ib_classification_system set classification_name='"
								+ name
								+ "',classification_id='"
								+ identifier
								+ "',if_default_classification="
								+ defaultsort
								+ ",classification_explain='"
								+ discription
								+ "' 	where	classification_id='" + id + "'");
						// 将原先的默认分类体系设置为否
						di.execute("update sys_ib_classification_system set if_default_classification=0 where classification_id='"
								+ is_exist_defaultsort_id + "'");
					}
				}else{
					String Sql = "update sys_ib_classification_system set classification_name='"
							+ name
							+ "',classification_id='"
							+ identifier
							+ "',if_default_classification="
							+ defaultsort
							+ ",classification_explain='"
							+ discription
							+ "' 	where	classification_id='" + id + "'";
					di.execute(Sql);
				}
			}*/
		}
		// 若标识符未修改，分类体系名称已修改
		if (!oldname.equals(name) && id.equals(identifier)) {
			List<Map> listname = (List<Map>) di
					.executeQuery("select * from sys_ib_classification_system where classification_name='"
							+ name + "' ");
			// 若新的分类体系名称有重复
			if (listname.size() > 0) {
				result = "名称重复，请重新输入";
				return result;
			} else {
				di.execute("update sys_ib_classification_system set classification_name='"+name+"',classification_explain='"+discription+"' where classification_id='"+id+"'");
				di.execute("update sys_ib_classification_tree set text='"+name+"' where tree_id='"+id+"' and pid='0'");
				
				// 若未重复
				/*// 若是否为默认分类体系未修改
				if (olddefaultsort.equals(defaultsort)) {
					String Sql = "update sys_ib_classification_system set classification_name='"
							+ name
							+ "',classification_explain='"
							+ discription
							+ "' 	where	classification_id='" + id + "'";
					di.execute(Sql);
				} else {
                    //若是否设置为默认分类体系修改为“是”
					if (defaultsort.equals("1")) {
						// 若数据表中没有设置为默认分类体系的数据
						if (is_exist_defaultsort_id.equals("notexist")) {
							// 更新数据
							String Sql = "update sys_ib_classification_system set classification_name='"
									+ name
									+ "',if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'";
							di.execute(Sql);
						}else {
							// 若有
							// 更新数据
							di.executeQuery("update sys_ib_classification_system set classification_name='"
									+ name
									+ "',if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'");
							// 将原先的默认分类体系设置为否
							di.execute("update sys_ib_classification_system set if_default_classification=0 where classification_id='"
									+ is_exist_defaultsort_id + "'");
						}
					}else{
						String Sql = "update sys_ib_classification_system set classification_name='"
								+ name
								+ "',if_default_classification="
								+ defaultsort
								+ ",classification_explain='"
								+ discription
								+ "' 	where	classification_id='" + id + "'";
						di.execute(Sql);
					}
				}*/
			}
		}
		
		
		// 若名称未修改，分类体系标识符已修改
		if (oldname.equals(name) && !id.equals(identifier)) {
			List<Map> listidentifier = (List<Map>) di
					.executeQuery("select * from sys_ib_classification_system where classification_id='"
							+ identifier + "' ");
			// 若新的分类体系名称有重复
			if (listidentifier.size() > 0) {
				result = "标识符重复，请重新输入";
				return result;
			} else {
				di.execute("update sys_ib_classification_system set classification_id='"
						+ identifier
						+ "',classification_explain='"
						+ discription
						+ "' where	classification_id='"+id+"'");
				di.execute("update sys_ib_classification_tree set tree_id='"
						+ identifier
						+ "' where	tree_id='"+id+"' and pid='0'");
				// 若未重复
				/*// 若是否为默认分类体系未修改
				if (olddefaultsort.equals(defaultsort)) {
					String Sql = "update sys_ib_classification_system set classification_id='"
							+ identifier
							+ "',classification_explain='"
							+ discription
							+ "' 	where	classification_id='" + id + "'";
					di.execute(Sql);
				} else {
					//若是否设置为默认分类体系修改为“是”
					if (defaultsort.equals("1")) {
						// 若数据表中没有设置为默认分类体系的数据
						if (is_exist_defaultsort_id.equals("notexist")) {
							// 更新数据
							String Sql = "update sys_ib_classification_system set classification_id='"
									+ identifier
									+ "',if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'";
							di.execute(Sql);
						}else {
							// 若有
							// 更新数据
							di.executeQuery("update sys_ib_classification_system set classification_id='"
									+ identifier
									+ "',if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'");
							// 将原先的默认分类体系设置为否
							di.execute("update sys_ib_classification_system set if_default_classification=0 where classification_id='"
									+ is_exist_defaultsort_id + "'");
						}
					}else{
						String Sql = "update sys_ib_classification_system set classification_id='"
								+ identifier
								+ "',if_default_classification="
								+ defaultsort
								+ ",classification_explain='"
								+ discription
								+ "' 	where	classification_id='" + id + "'";
						di.execute(Sql);
					}
				}*/
			}
		}
		// 若名称和标识符均未修改
		if (oldname.equals(name) && id.equals(identifier)) {
			/*	// 若是否为默认分类体系未修改
				if (olddefaultsort.equals(defaultsort)) {
					String Sql = "update sys_ib_classification_system set classification_explain='"
							+ discription
							+ "' 	where	classification_id='" + id + "'";
					di.execute(Sql);
				} else {
					//若是否设置为默认分类体系修改为“是”
					if (defaultsort.equals("1")) {
						// 若数据表中没有设置为默认分类体系的数据
						if (is_exist_defaultsort_id.equals("notexist")) {
							// 更新数据
							String Sql = "update sys_ib_classification_system set if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'";
							di.execute(Sql);
						}else {
							// 若有
							// 更新数据
							di.executeQuery("update sys_ib_classification_system set if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'");
							// 将原先的默认分类体系设置为否
							di.execute("update sys_ib_classification_system set if_default_classification=0 where classification_id='"
									+ is_exist_defaultsort_id + "'");
						}
					}else{
						String Sql = "update sys_ib_classification_system set if_default_classification="
								+ defaultsort
								+ ",classification_explain='"
								+ discription
								+ "' 	where	classification_id='" + id + "'";
						di.execute(Sql);
					}
				}*/
			di.execute("update sys_ib_classification_system set classification_explain='"
					+ discription
					+ "' where	classification_id='" + id + "'");
		}
			result = "修改成功";
			return result;
		}
	
	
	/**
	 * 分类管理中分类体系ID获取
	 */
	public String GetSortId(String SortName){
		List<Map> list = (List<Map>) di
				.executeQuery("select * from sys_ib_classification_system where classification_name='"
						+ SortName + "' ");
		String SortId = (String) list.get(0).get("classification_id");
		return SortId;
	}
	
	
	/**
	 * 分类体系选择
	 */
	public String SortSelect(){
		DaoImpl di = new DaoImpl();
		List<Map> list = (List<Map>) di
		.executeQuery("select * from sys_ib_classification_system");
		Service	si	=	new	ServiceImpl();
		String Json =	si.DataListToJson(list);
		HttpServletResponse response	=	ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {	
			PrintWriter pw = response.getWriter();	
			pw.print(Json);	
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 分类管理生成树数据获取
	 */
	public String SortManageDataSource(String TableName, String TreeId){
		String	Sql	=	"select id,tree_id,text label,pid,sno,node_path,node_series,node_explain from "+TableName+" where tree_id='"+TreeId+"' order by node_path asc";
		List<Map> list	=	new	ArrayList();
		list=di.executeQuery(Sql);
		Map<String, String>	params	=	new	HashMap();
		params.put("tablename", TableName);
		String	Json	=	service.DataListToTreeJson(list, params);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw=response.getWriter();
			pw.print(Json);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 
	 * <p>名称：sortTreeOperateActionIfRootId</p>
	 * <p>说明：分类体系树管理判断节点是否为根节点TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String sortTreeOperateActionIfRootId(String sortNodeId){
		String result="0";
		List<Map> list=di.executeQuery("select * from sys_ib_classification_tree where id='"+sortNodeId+"'");
		String pid=(String) list.get(0).get("pid");
		if(pid.equals("0")){
			result="1";
		}
		print(result);
		return null;
	}
	
	
	/**
	 * 
	 * <p>名称：print</p>
	 * <p>说明：向前台返回结果TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@param string
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@param string
	 * <p>@return</p>
	 */
	public String print(String string){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(string);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
	
