package net.ib.util.service.impl;

import net.ib.util.service.ExamSystemManageService;
import net.ib.util.service.Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import net.ib.util.dao.Dao;
import net.sf.json.JSONArray;

public class ExamSystemManageServiceImpl implements ExamSystemManageService {
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
	 * 试题库管理添加试题库分类配置
	 */
	public String SortConfig() {
		// 取分类体系
		List<Map> list = di
				.executeQuery("select classification_id,classification_name from sys_ib_classification_system");
		String id = "";
		String name = "";
		for (int i = 0; i < list.size(); i++) {
			id = (String) list.get(i).get("classification_id");
			name = (String) list.get(i).get("classification_name");
			// 给每个分类体系加上pid等键值对
			Map sort = list.get(i);
			sort.put("pid", "jiedian1");
			sort.put("id", "" + id + "");
			sort.put("text", "" + name + "");
		}
		int listlength = list.size();// 控制循环次数，将大的循环次数控制为3次
		for (int i = 0; i < listlength; i++) {
			String classification_id = (String) list.get(i).get(
					"classification_id");
			String classification_name = (String) list.get(i).get(
					"classification_name");
			// 取出每个分类体系下子分类树
			List<Map> sortlist = di
					.executeQuery("select id,text,pid,tree_id,node_path from sys_ib_classification_tree where tree_id='"
							+ classification_id + "' order by node_path asc");
			for (int j = 0; j < sortlist.size(); j++) {
				Map sortelement = sortlist.get(j);
				// 修改pid值为零的节点作为分类体系的子节点
				if (sortlist.get(j).get("pid").equals("0")) {
					Map ss = sortlist.get(j);
					ss.remove("pid");
					ss.put("pid", "" + classification_id + "");
				}
				list.add(sortelement);

			}
		}
		// 给list添加根节点
		Map<String, Object> rootnode = new HashMap<String, Object>();
		rootnode.put("pid", "0");
		rootnode.put("text", "分类体系列表");
		rootnode.put("id", "jiedian1");
		list.add(0, rootnode);
		// 将list<map>转换为Json串
		JSONArray zNodes = JSONArray.fromObject(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(zNodes);
			logger.debug(zNodes);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * <p>名称：addEsSortConfig</p>
	 * <p>说明：添加试题库时分类体系获取，此处不能包含基本分类体系TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String addEsSortConfig(){
		// 取分类体系
				List<Map> list = di
						.executeQuery("select classification_id,classification_name from sys_ib_classification_system where classification_id!='jibenfenleitixi'");
				String id = "";
				String name = "";
				for (int i = 0; i < list.size(); i++) {
					id = (String) list.get(i).get("classification_id");
					name = (String) list.get(i).get("classification_name");
					// 给每个分类体系加上pid等键值对
					Map sort = list.get(i);
					sort.put("pid", "jiedian1");
					sort.put("id", "" + id + "");
					sort.put("text", "" + name + "");
				}
				int listlength = list.size();// 控制循环次数，将大的循环次数控制为3次
				for (int i = 0; i < listlength; i++) {
					String classification_id = (String) list.get(i).get(
							"classification_id");
					String classification_name = (String) list.get(i).get(
							"classification_name");
					// 取出每个分类体系下子分类树
					List<Map> sortlist = di
							.executeQuery("select id,text,pid,tree_id,node_path from sys_ib_classification_tree where tree_id='"
									+ classification_id + "' order by node_path asc");
					for (int j = 0; j < sortlist.size(); j++) {
						Map sortelement = sortlist.get(j);
						// 修改pid值为零的节点作为分类体系的子节点
						if (sortlist.get(j).get("pid").equals("0")) {
							Map ss = sortlist.get(j);
							ss.remove("pid");
							ss.put("pid", "" + classification_id + "");
						}
						list.add(sortelement);

					}
				}
				// 给list添加根节点
				Map<String, Object> rootnode = new HashMap<String, Object>();
				rootnode.put("pid", "0");
				rootnode.put("text", "分类体系列表");
				rootnode.put("id", "jiedian1");
				list.add(0, rootnode);
				// 将list<map>转换为Json串
				JSONArray zNodes = JSONArray.fromObject(list);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				try {
					PrintWriter pw = response.getWriter();
					pw.print(zNodes);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		return null;
	}

	/**
	 * 添加试题库试题库用途配置用途数据获取
	 */
	public String UseConfig() {
		List<Map> list = di
				.executeQuery("select * from sys_dictionary_entries_value where dictionary_entries_id='paperuse' order by sno asc");
		Service si = new ServiceImpl();
		String Json = si.DataListToJson(list);
		HttpServletResponse response = ServletActionContext.getResponse();
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
	 * 添加试题库信息入库
	 */
	public String ExamSystemAdd(String name, String discription,
			String identifier, String leafid, String config_leafid, String use) {
		String result = "";
		// 判断分类体系名称是否重复
		List<Map> list = (List<Map>) di
				.executeQuery("select * from sys_itembank_list where itembank_name='"
						+ name + "' or itembank_id='" + identifier + "'");
		for (int i = 0; i < list.size(); i++) {
			String itembank_name = (String) list.get(i).get("itembank_name");
			String itembank_id = (String) list.get(i).get("itembank_id");

			// 判断分类体系名称是否重复
			if (itembank_name.equals(name)) {
				result = "名称重复，请重新输入";
				return result;
			}
			// 判断分类体系标识符时候重复
			if (itembank_id.equals(identifier)) {
				result = "标识符重复，请重新输入";
				return result;
			}

		}
		di.execute("insert into sys_itembank_list (itembank_id,itembank_name,use,explain,curse_id,create_time) values('"
				+ identifier
				+ "','"
				+ name
				+ "','"
				+ use
				+ "','"
				+ discription
				+ "','" + leafid + "',sysdate)");
		di.execute("insert into sys_ib_classification_node (id,node_id,itembank_id) values(sys_guid(),'"
				+ config_leafid + "','" + identifier + "')");
		//创建对应试题基本信息表
		di.execute("create table QT_BasicField_"+identifier+" (QUESTION_ID VARCHAR(50) primary key,question_type varchar(50),difficulty float(1),defaultpoint number(38),time_use number(38),knowledge_point_id varchar(50),editor_id varchar(50),update_time Date,time Date,explain varchar(50) default('无'),statue number(38) default(1))");
		//创建对应试题内容和附件信息表
		di.execute("create table QT_BlobField_"+identifier+"(QUESTION_ID VARCHAR(50) primary key,questioncontent blob not null,uploadfile blob not null)");
		result = "添加成功";
		return result;
	}

	/**
	 * 删除试题库，单条数据
	 */
	public String ExamSystemDelete(String id) {
		String getSql = "delete from sys_itembank_list where itembank_id='"
				+ id + "'";
		String result = "";
		if (getSql != null) {
			if (di.execute(getSql) != 1) {
				
				result = "删除失败!";
			} else {
				//删除对应试题表格
				di.execute("drop table QT_BasicField_"+id+"");
				di.execute("drop table QT_BlobField_"+id+"");
				//删除试题库在其他分类体系下的配置信息
				di.execute("delete from sys_ib_classification_node where itembank_id='"
				+ id + "'");
				result = "删除成功";
			}
		}
		return result;
	}

	/**
	 * 批量删除试题库
	 */
	public String ExamSystemBulkDelete(String id) {
		String[] array = id.split(" ");
		String Sql = "delete from sys_itembank_list where";
		String sortConfigSql="delete from sys_ib_classification_node where";
		String result = "";
		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				Sql += " or";
				sortConfigSql += " or";
			}
			Sql += " itembank_id='" + array[i] + "'";
			sortConfigSql += " itembank_id='" + array[i] + "'";
		}
		if (Sql != null) {
			if (di.execute(Sql) != 1) {
				result = "删除失败!";
			} else {
				//删除对应试题表格
				for(int j=0;j<array.length;j++){
					String itembankId=array[j];
					di.execute("drop table QT_BasicField_"+itembankId+"");
					di.execute("drop table QT_BlobField_"+itembankId+"");
				}
				//删除在其他分类体系下的对应分类配置信息
				di.execute(sortConfigSql);
				result = "删除成功";
			}
		}
		return result;
	}

	/**
	 * 修改分类体系试题库分类配置信息获取
	 */
	public String ExamSystemSortConfig(String id) {
		String sortconfigid = "";
		String sortconfigtext = "";
		List<Map> list_configid = (List<Map>) di
				.executeQuery("select * from sys_ib_classification_node where itembank_id='"
						+ id + "'");
		for (int i = 0; i < list_configid.size(); i++) {
			sortconfigid = (String) list_configid.get(i).get("node_id");
		}
		List<Map> list_nodetext = (List<Map>) di
				.executeQuery("select text from sys_ib_classification_tree where id='"
						+ sortconfigid + "'");
		for (int i = 0; i < list_nodetext.size(); i++) {
			sortconfigtext = (String) list_nodetext.get(i).get("text");
		}
		return sortconfigtext;
	}

	/**
	 * 修改试题库试题库分类配置分类列表信息获取
	 */
	public String ModifySortConfig() {
		// 取分类体系
		List<Map> list = di
				.executeQuery("select classification_id,classification_name from sys_ib_classification_system where classification_id!='jibenfenleitixi'");
		String id = "";
		String name = "";
		for (int i = 0; i < list.size(); i++) {
			id = (String) list.get(i).get("classification_id");
			name = (String) list.get(i).get("classification_name");
			// 给每个分类体系加上pid等键值对
			Map sort = list.get(i);
			sort.put("pid", "modify_sortconfig_node");
			sort.put("id", "modify" + id + "");
			sort.put("text", "" + name + "");
		}
		int listlength = list.size();// 控制循环次数，将大的循环次数控制为3次
		for (int i = 0; i < listlength; i++) {
			String classification_id = (String) list.get(i).get(
					"classification_id");
			String classification_name = (String) list.get(i).get(
					"classification_name");
			// 取出每个分类体系下子分类树
			List<Map> sortlist = di
					.executeQuery("select id,text,pid,tree_id,node_path from sys_ib_classification_tree where tree_id='"
							+ classification_id + "' order by node_path asc");
			for (int j = 0; j < sortlist.size(); j++) {
				Map sortelement = sortlist.get(j);
				// 给每个id前面增加字段，和试题库查询中分类列表中的id区分
				String original_id;
				original_id = (String) sortlist.get(j).get("id");
				sortelement.remove("id");
				sortelement.put("id", "modify" + original_id + "");
				// 更改对应子节点的pid
				// 修改pid值为零的节点作为分类体系的子节点
				if (sortlist.get(j).get("pid").equals("0")) {
					Map ss = sortlist.get(j);
					ss.remove("pid");
					ss.put("pid", "" + classification_id + "");
				}
				list.add(sortelement);
			}
			// 更改分类体系id，加上modify字段
			for (int s = 0; s < list.size(); s++) {
				if (list.get(s).get("pid").equals(classification_id)) {
					Map sortelement = list.get(s);
					sortelement.remove("pid");
					sortelement.put("pid", "modify" + classification_id + "");
				}
			}

		}

		// 更改节点pid
		for (int t = 0; t < list.size(); t++) {
			String modifyid;
			String originalid;
			Map sortelement = list.get(t);
			modifyid = (String) list.get(t).get("id");
			originalid = modifyid.substring(6, modifyid.length());
			for (int a = 0; a < list.size(); a++) {
				Map listelement = list.get(a);
				if (list.get(a).get("pid").equals(originalid)) {
					listelement.remove("pid");
					listelement.put("pid", "" + modifyid + "");
				}
			}
		}

		// 给list添加根节点
		Map<String, Object> rootnode = new HashMap<String, Object>();
		rootnode.put("pid", "0");
		rootnode.put("text", "分类体系列表");
		rootnode.put("id", "modify_sortconfig_node");
		list.add(0, rootnode);
		// 将list<map>转换为Json串
		JSONArray zNodes = JSONArray.fromObject(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(zNodes);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	/**
	 * 修改试题库，信息入库
	 */
	public String ExamSystemModify(String id, String name, String discription,
			String identifier, String config_leafid, String use) {
		String result=""; // 前台反馈结果
		// 修改前标识符为id的值
		String oldname = "";// 修改前name
		String olduse="";// 修改前用途
		String oldexplain="";// 修改前说明
		String oldconfig_leafid="";// 修改前分类配置id
		//去除分类配置id前的modify
		if(!"null".equals(config_leafid)){
			config_leafid = config_leafid.substring(6, config_leafid.length());
		}
		// 从数据库中取出修改前的数据
		List<Map> list = (List<Map>) di
				.executeQuery("select * from sys_itembank_list where itembank_id='"
						+ id + "' ");
		for (int i = 0; i < list.size(); i++) {
			oldname = (String) list.get(i).get("itembank_name");
			olduse = (String) list.get(i).get("use");
			oldexplain = (String) list.get(i).get("explain");
		}
		List<Map> list_sortnode = (List<Map>) di
				.executeQuery("select node_id from sys_ib_classification_node where itembank_id='"
						+ id + "' ");
		for (int i = 0; i < list_sortnode.size(); i++) {
			oldconfig_leafid = (String) list_sortnode.get(i).get("node_id");
		}

		// 若标识符和标题均已修改
		if (!oldname.equals(name) && !id.equals(identifier)) {
			// 判断新的名称是否和数据库中的名称重复
			List<Map> listname = (List<Map>) di
					.executeQuery("select * from sys_itembank_list where itembank_name='"
							+ name + "' ");
			// 若名称有重复
			if (listname.size() > 0) {
				result = "名称重复，请重新输入";
				return result;
			}
			// 判断新的标识符是否和数据库中重复
			List<Map> listidentifier = (List<Map>) di
					.executeQuery("select * from sys_itembank_list where itembank_id='"
							+ identifier + "' ");
			// 若新的标识符有重复
			if (listidentifier.size() > 0) {
				result = "标识符重复，请重新输入";
				return result;
			}
			//判断试题库分类体系节点是否触发
			if("null".equals(config_leafid)){
				String Sql = "update sys_itembank_list set itembank_name='"
						+ name + "',itembank_id='" + identifier + "',explain='"
						+ discription + "',use='" + use
						+ "' 	where	itembank_id='" + id + "'";
				di.execute(Sql);
				di.execute("update sys_ib_classification_node set itembank_id='"
						+ identifier
						+ "' where	itembank_id='" + id + "'");
			}else{
				// 判断试题库分类体系设置是否更改，若未改变
				if (oldconfig_leafid.equals(config_leafid)) {
					String Sql = "update sys_itembank_list set itembank_name='"
							+ name + "',itembank_id='" + identifier + "',explain='"
							+ discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					//修改分类配置中的试题库id
					di.execute("update sys_ib_classification_node set itembank_id='"
							+ identifier
							+ "' where	itembank_id='" + id + "'");
				} else {
					// 试题库分类体系设置已修改
					String Sql = "update sys_itembank_list set itembank_name='"
							+ name + "',itembank_id='" + identifier + "',explain='"
							+ discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					// 修改试题库分类体系设置项
					String Sql1 = "update sys_ib_classification_node set itembank_id='"
							+ identifier
							+ "',node_id='"
							+ config_leafid
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql1);
					

				}
			}
			//修改存储试题的表名
			di.execute("ALTER  TABLE QT_BASICFIELD_"+id+" RENAME TO QT_bASICFIELD_"+identifier+"");
			di.execute("ALTER  TABLE QT_BLOBFIELD_"+id+" RENAME TO QT_BLOBFIELD_"+identifier+"");
			
			
		}

		// 若标识符未修改，名称已修改
		if (!oldname.equals(name) && id.equals(identifier)) {
			List<Map> listname = (List<Map>) di
					.executeQuery("select * from sys_itembank_list where itembank_name='"
							+ name + "' ");
			// 若新名称有重复
			if (listname.size() > 0) {
				result = "名称重复，请重新输入";
				return result;
			}
			//判断试题库分类体系节点是否触发
			if("null".equals(config_leafid)){
				String Sql = "update sys_itembank_list set itembank_name='"
						+ name + "',explain='" + discription + "',use='" + use
						+ "' 	where	itembank_id='" + id + "'";
				di.execute(Sql);
			}else{
				// 判断试题库分类体系设置是否更改，若未改变
				if (oldconfig_leafid.equals(config_leafid)) {
					String Sql = "update sys_itembank_list set itembank_name='"
							+ name + "',explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
				} else {
					// 试题库分类体系设置已修改
					String Sql = "update sys_itembank_list set itembank_name='"
							+ name + "',explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					// 修改试题库分类体系设置项
					String Sql1 = "update sys_ib_classification_node set node_id='"
							+ config_leafid + "' 	where	itembank_id='" + id + "'";
					di.execute(Sql1);
				}
			}
		}

		// 若名称未修改，标识符已修改
		if (oldname.equals(name) && !id.equals(identifier)) {
			List<Map> listidentifier = (List<Map>) di
					.executeQuery("select * from sys_itembank_list where itembank_id='"
							+ identifier + "' ");
			// 若新的分类体系名称有重复
			if (listidentifier.size() > 0) {
				result = "标识符重复，请重新输入";
				return result;
			}
			//判断试题库分类体系节点是否触发
			if("null".equals(config_leafid)){
				String Sql = "update sys_itembank_list set itembank_id='"
						+ identifier + "',explain='" + discription + "',use='" + use
						+ "' 	where	itembank_id='" + id + "'";
				di.execute(Sql);
				di.execute("update sys_ib_classification_node set itembank_id='"
						+ identifier
						+ "' where	itembank_id='" + id + "'");
			}else{
				// 判断试题库分类体系设置是否更改，若未改变
				if (oldconfig_leafid.equals(config_leafid)) {
					String Sql = "update sys_itembank_list set itembank_id='"
							+ identifier + "',explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					//修改分类配置中的试题库id
					di.execute("update sys_ib_classification_node set itembank_id='"
							+ identifier
							+ "' where	itembank_id='" + id + "'");
				} else {
					// 试题库分类体系设置已修改
					String Sql = "update sys_itembank_list set itembank_id='"
							+ identifier + "',explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					// 修改试题库分类体系设置项
					String Sql1 = "update sys_ib_classification_node set itembank_id='"
							+ identifier + "',node_id='"
							+ config_leafid + "' 	where	itembank_id='" + id + "'";
					di.execute(Sql1);
				}
				
			}
			di.execute("ALTER  TABLE QT_BASICFIELD_"+id+" RENAME TO QT_bASICFIELD_"+identifier+"");
			di.execute("ALTER  TABLE QT_BLOBFIELD_"+id+" RENAME TO QT_BLOBFIELD_"+identifier+"");

		}

		// 若名称和标识符均未修改
		if (oldname.equals(name) && id.equals(identifier)) {
			//判断试题库分类体系节点是否触发
			if("null".equals(config_leafid)){
				String Sql = "update sys_itembank_list set explain='" + discription + "',use='" + use
						+ "' 	where	itembank_id='" + id + "'";
				di.execute(Sql);
			}else{
				// 判断试题库分类体系设置是否更改，若未改变
				if (oldconfig_leafid.equals(config_leafid)) {
					String Sql = "update sys_itembank_list set explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
				} else {
					// 试题库分类体系设置已修改
					String Sql = "update sys_itembank_list set explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					// 修改试题库分类体系设置项
					String Sql1 = "update sys_ib_classification_node set node_id='"
							+ config_leafid + "' 	where	itembank_id='" + id + "'";
					di.execute(Sql1);
				}
			}
		
		
		}
		result = "修改成功";
		return result;
	}
	
	/**
	 * 
	//试题库授权按角色授权获取角色数据
	 */
	public String GetRoleData(){
		List<Map> list = di
				.executeQuery("select * from sys_role");
		Service si = new ServiceImpl();
		String Json = si.DataListToJson(list);
		HttpServletResponse response = ServletActionContext.getResponse();
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
	 * 试题库按角色授权获取试题库数，未选课程
	 */
	public String GetItembankBymajor(String majorid){
		List<Map> subjectlist	=	di.executeQuery("select * from sys_curse where special_field_id='"+majorid+"'");
		String curse_sql="";
		for (int j = 0; j < subjectlist.size(); j++) {
			//课程sql拼接
			String subjectid=(String) subjectlist.get(j).get("curse_id");
				if(curse_sql!=""){
					curse_sql=curse_sql+" or ";
				}
				curse_sql+="curse_id="+"'"+subjectid+"'";
			}
		//查找试题库
		String getDataSql = "select rownum rn,cu.itembank_id,cu.itembank_name,cu.curse_id from  "
				+"sys_itembank_list cu where "+curse_sql+"";
		List<Map> itembanklist	=	di.executeQuery(getDataSql);
		JSONArray itembanklistdata = JSONArray.fromObject(itembanklist);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(itembanklistdata);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取课程name，仅选专业未选课程时
	 */
	public String GetCurseNameBymajor(String curseid){
		String[] curselist=curseid.split(",");
		String curse_sql="";
		for(int i=0;i<curselist.length;i++){
			String curse=curselist[i];
			if(curse_sql!=""){
				curse_sql=curse_sql+" or ";
			}
			curse_sql+="curse_id="+"'"+curse+"'";
		}
		List<Map> subjectlist	=	di.executeQuery("select curse_name,curse_id,special_field_id from sys_curse where "+curse_sql+"");
		JSONArray subjectlistdata = JSONArray.fromObject(subjectlist);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(subjectlistdata);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 专业课程均选择时通过课程id获得课程name
	 */
	public String GetCurseNameBycurse(String curseid){
		logger.debug(curseid);
		List<Map> subjectlist	=	di.executeQuery("select curse_name,curse_id,special_field_id from sys_curse where curse_id='"+curseid+"'");
		logger.debug(subjectlist);
		JSONArray subjectlistdata = JSONArray.fromObject(subjectlist);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			logger.debug(subjectlistdata);
			pw.print(subjectlistdata);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 试题库按角色授权获取试题库数据，已选课程
	 */
	public String GetItembankBycurse(String curseid){
		//查找试题库
		String getDataSql = "select cu.itembank_id,cu.itembank_name,cu.curse_id from  "
				+"sys_itembank_list cu where curse_id='"+curseid+"'";
		List<Map> itembanklist	=	di.executeQuery(getDataSql);
		JSONArray itembanklistdata = JSONArray.fromObject(itembanklist);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(itembanklistdata);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

/**
 * 试题库按角色授权获取某角色成员的试题库权限	
 */
	public String GetAuthorizedItembankByrole(String roleid,String memberid){
		List<Map> authorized_itembank	=	di.executeQuery("select role_id,itembank_id,membertype_id from sys_perm_itembank_role where role_id='"+roleid+"' and membertype_id='"+memberid+"'");
		JSONArray authorized_itembanklist = JSONArray.fromObject(authorized_itembank);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(authorized_itembanklist);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 试题库按角色授权，保存配置，未选课程
	 */
	public String SaveAuthorityConfigByroleSelectnonecourse(String authorized_esid,String role,String member,String majorid){
		String result=null;
		//判断授权的试题库只有一个
		//若不是
		if(authorized_esid.indexOf(",")!=-1){
			String[] eslist=authorized_esid.split(",");
			//删除表中原先配置
			di.execute("delete from sys_perm_itembank_role where role_id='"+role+"' and membertype_id='"+member+"' and major_id='"+majorid+"'");
			//添加配置数据
			//判断是否有选择试题库,为空时长度为1
			if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				di.execute("insert into sys_perm_itembank_role (id,itembank_id,role_id,membertype_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+role+"','"+member+"','"+majorid+"')");
				}
			}
			
		}else{
			di.execute("delete from sys_perm_itembank_role where role_id='"+role+"' and membertype_id='"+member+"' and major_id='"+majorid+"'");
			di.execute("insert into sys_perm_itembank_role (id,itembank_id,role_id,membertype_id,major_id) values(sys_guid(),'"+authorized_esid+"','"+role+"','"+member+"','"+majorid+"')");

		}
		/*String[] eslist = null;
		//和判断是否授权的试题库只有一个
		if(authorized_esid.indexOf(",")!=-1){
			eslist=authorized_esid.split(",");
		}else{
		}
		//删除表中原先配置
		di.execute("delete from sys_perm_itembank_role where role_id='"+role+"' and membertype_id='"+member+"'");
		//添加配置数据
		//判断是否有选择试题库,为空时长度为1
		if(eslist.length!=1){
		for(int i=0;i<eslist.length;i++){
			di.execute("insert into sys_perm_itembank_role (id,itembank_id,role_id,membertype_id) values(sys_guid(),'"+eslist[i]+"','"+role+"','"+member+"')");
		}
		}*/
		result="配置成功";
		return result;
	}
	/**
	 * 试题库按角色授权，保存配置，选择课程
	 */
	public String SaveAuthorityConfigByroleSelectcourse(String authorized_esid,String role,String member,String courseid,String majorid){
		String result=null;
		//查找配置中在该课程下的试题库
				ArrayList banklist=new ArrayList();
				List<Map> AllList	=	di.executeQuery("select * from sys_perm_itembank_role");
				for(int j=0;j<AllList.size();j++){
					String esid=(String) AllList.get(j).get("itembank_id");
					List<Map> list	=	di.executeQuery("select * from sys_itembank_list where itembank_id='"+esid+"' and curse_id='"+courseid+"'");
					if(list.size()>0){
						banklist.add(esid);
					}
				}
				//删除该课程下表中原先配置
				for(int t=0;t<banklist.size();t++){
					di.execute("delete from sys_perm_itembank_role where role_id='"+role+"' and membertype_id='"+member+"' and itembank_id='"+banklist.get(t)+"'");
				}
		//添加配置数据
		//和判断是否授权的试题库只有一个
		//若不是
		if(authorized_esid.indexOf(",")!=-1){
			String[] eslist=authorized_esid.split(",");
			//添加配置数据
			if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_role (id,itembank_id,role_id,membertype_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+role+"','"+member+"','"+majorid+"')");
				}
			}
		}else{
			di.execute("insert into sys_perm_itembank_role (id,itembank_id,role_id,membertype_id,major_id) values(sys_guid(),'"+authorized_esid+"','"+role+"','"+member+"','"+majorid+"')");

		}
		/*String[] eslist=authorized_esid.split(",");
		//查找配置中在该课程下的试题库
		ArrayList banklist=new ArrayList();
		List<Map> AllList	=	di.executeQuery("select * from sys_perm_itembank_role");
		for(int j=0;j<AllList.size();j++){
			String esid=(String) AllList.get(j).get("itembank_id");
			List<Map> list	=	di.executeQuery("select * from sys_itembank_list where itembank_id='"+esid+"' and curse_id='"+courseid+"'");
			if(list.size()>0){
				banklist.add(esid);
			}
		}
		//删除该课程下表中原先配置
		for(int t=0;t<banklist.size();t++){
			di.execute("delete from sys_perm_itembank_role where role_id='"+role+"' and membertype_id='"+member+"' and itembank_id='"+banklist.get(t)+"'");
		}
		//添加配置数据
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				di.execute("insert into sys_perm_itembank_role (id,itembank_id,role_id,membertype_id) values(sys_guid(),'"+eslist[i]+"','"+role+"','"+member+"')");
			}
		}*/
		result="配置成功";
		return result;
	}
	
	
	/**
	 * 试题库按用户授权，判断教工号或学号是否输入正确
	 */
	public String CheckNumber(String number, String member) {
		JSONArray authorized_itembanklist = null;
		if (member.equals("teacher")) {
			List<Map> authorized_itembank = di
					.executeQuery("select school_id from sys_user_teacher where school_id='"
							+ number + "'");
			authorized_itembanklist = JSONArray.fromObject(authorized_itembank);
		}
		if (member.equals("student")) {
			List<Map> authorized_itembank = di
					.executeQuery("select school_id from sys_user_student where school_id='"
							+ number + "'");
			authorized_itembanklist = JSONArray.fromObject(authorized_itembank);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(authorized_itembanklist);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	
	/**
	 * 试题库按用户授权获取某用户的试题库权限	
	 */
		public String GetAuthorizedItembankByuser(String number,String member){
			List<Map> authorized_itembank	=	di.executeQuery("select user_id,itembank_id,membertype from sys_perm_itembank_user where user_id='"+number+"' and membertype='"+member+"'");
			JSONArray authorized_itembanklist = JSONArray.fromObject(authorized_itembank);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try {
				PrintWriter pw = response.getWriter();
				pw.print(authorized_itembanklist);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		/**
		 * 试题库按用户授权，保存配置,未选课程
		 */
		public String SaveAuthorityConfigByuserSelectnonecourse(String authorized_esid,String number,String member,String majorid){
			String result=null;
			//删除表中原先配置
			di.execute("delete from sys_perm_itembank_user where user_id='"+number+"' and membertype='"+member+"' and major_id='"+majorid+"'");
			if(authorized_esid.indexOf(",")!=-1){
				String[] eslist=authorized_esid.split(",");
				//添加配置数据
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						di.execute("insert into sys_perm_itembank_user (id,itembank_id,user_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"','"+majorid+"')");
					}
				}
				}else{
					di.execute("insert into sys_perm_itembank_user (id,itembank_id,user_id,membertype,major_id) values(sys_guid(),'"+authorized_esid+"','"+number+"','"+member+"','"+majorid+"')");

				}
			result="配置成功";
			return result;
		}
		
		/**
		 * 试题库按用户授权，保存配置,选择课程
		 */
		public String SaveAuthorityConfigByuserSelectcourse(String authorized_esid,String number,String member,String courseid,String majorid){
			String result=null;
			//查找配置中在该课程下的试题库
			ArrayList banklist=new ArrayList();
			List<Map> AllList	=	di.executeQuery("select * from sys_perm_itembank_user");
			for(int j=0;j<AllList.size();j++){
				String esid=(String) AllList.get(j).get("itembank_id");
				List<Map> list	=	di.executeQuery("select * from sys_itembank_list where itembank_id='"+esid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					banklist.add(esid);
				}
			}
			//删除该课程下表中原先配置
			for(int t=0;t<banklist.size();t++){
				di.execute("delete from sys_perm_itembank_user where user_id='"+number+"' and membertype='"+member+"' and itembank_id='"+banklist.get(t)+"'");
			}
			if(authorized_esid.indexOf(",")!=-1){
				String[] eslist=authorized_esid.split(",");
				//添加配置数据
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						di.execute("insert into sys_perm_itembank_user (id,itembank_id,user_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"','"+majorid+"')");
					}
				}
			}else{
				di.execute("insert into sys_perm_itembank_user (id,itembank_id,user_id,membertype,major_id) values(sys_guid(),'"+authorized_esid+"','"+number+"','"+member+"','"+majorid+"')");

			}
			
			result="配置成功";
			return result;
			
		/*	String result=null;
			String[] eslist=authorized_esid.split(",");
			//删除表中原先配置
			di.execute("delete from sys_perm_itembank_user where user_id='"+number+"' and membertype='"+member+"'");
			//添加配置数据
			if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_user (id,itembank_id,user_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"')");
				}
			}
			result="配置成功";
			return result;*/
		}
		
		/**
		 * 试题库按成员类型授权，查询权限
		 */
		public String GetAuthorizedItembankBymembertype(String member){
				List<Map> authorized_itembank	=	di.executeQuery("select itembank_id,membertype from sys_perm_itembank_membertype where membertype='"+member+"'");
				JSONArray authorized_itembanklist = JSONArray.fromObject(authorized_itembank);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				try {
					PrintWriter pw = response.getWriter();
					pw.print(authorized_itembanklist);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			}
		
		
		/**
		 * 试题库按成员类型授权，保存配置，未选课程
		 */
		public String SaveAuthorityConfigBymembertypeSelectnonecourse(String authorized_esid,String member,String majorid){
			String result=null;
			//删除表中原先配置
			di.execute("delete from sys_perm_itembank_membertype where membertype='"+member+"' and major_id='"+majorid+"'");
			if(authorized_esid.indexOf(",")!=-1){
				String[] eslist=authorized_esid.split(",");
				//添加配置数据
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						di.execute("insert into sys_perm_itembank_membertype (id,itembank_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+member+"','"+majorid+"')");
					}
				}
				
			}else{
				di.execute("insert into sys_perm_itembank_membertype (id,itembank_id,membertype,major_id) values(sys_guid(),'"+authorized_esid+"','"+member+"','"+majorid+"')");

			}
			result="配置成功";
			return result;
		}
		
		/**
		 * 试题库按成员类型授权，保存配置，已选课程
		 */
		public String SaveAuthorityConfigBymembertypeSelectcourse(String authorized_esid,String member,String courseid,String majorid){
			String result=null;
			//查找配置中在该课程下的试题库
			ArrayList banklist=new ArrayList();
			List<Map> AllList	=	di.executeQuery("select * from sys_perm_itembank_membertype");
			for(int j=0;j<AllList.size();j++){
				String esid=(String) AllList.get(j).get("itembank_id");
				List<Map> list	=	di.executeQuery("select * from sys_itembank_list where itembank_id='"+esid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					banklist.add(esid);
				}
			}
			//删除该课程下表中原先配置
			for(int t=0;t<banklist.size();t++){
				di.execute("delete from sys_perm_itembank_membertype where membertype='"+member+"' and itembank_id='"+banklist.get(t)+"'");
			}
			if(authorized_esid.indexOf(",")!=-1){
				
				String[] eslist=authorized_esid.split(",");
				//添加配置数据
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						di.execute("insert into sys_perm_itembank_membertype (id,itembank_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+member+"','"+majorid+"')");
					}
				}
			}else{
				di.execute("insert into sys_perm_itembank_membertype (id,itembank_id,membertype,major_id) values(sys_guid(),'"+authorized_esid+"','"+member+"','"+majorid+"')");

			}
			/*String result=null;
			String[] eslist=authorized_esid.split(",");
			//删除表中原先配置
			di.execute("delete from sys_perm_itembank_membertype where membertype='"+member+"'");
			//添加配置数据
			if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_membertype (id,itembank_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+member+"')");
				}
			}*/
			result="配置成功";
			return result;
		}
		
		
		/**
		 * 试题库按教学班级授权，根据课程查询教学班级
		 */
		
		public String GetTeachingClass(String courseid){
			List<Map> teachingclass	=	di.executeQuery("select curse_class_id,curse_id,class_name from sys_curse_class where curse_id='"+courseid+"'");
			/*Service si = new ServiceImpl();
			String Json = si.DataListToJson(teachingclass);*/  //另一种形式json
			JSONArray Json = JSONArray.fromObject(teachingclass);
			HttpServletResponse response = ServletActionContext.getResponse();
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
		 * 试题库按教学班级授权，获得查询机构列表
		 */
		public String ReferSchoolStructureOptionsGet(){
			String	sql	=	"select * from sys_ib_classification_tree where tree_id='jibenfenleitixi' order by node_path asc";
			List<Map> list	=	di.executeQuery(sql);
			// 给每个id前面增加字段refer，和选择教学班级处区分
			for (int j = 0; j < list.size(); j++) {
				Map sortelement = list.get(j);
				String original_id;
				original_id = (String) list.get(j).get("id");
				sortelement.remove("id");
				sortelement.put("id", "refer" + original_id + "");
				}
			// 更改节点pid
			for (int t = 0; t < list.size(); t++) {
				String modifyid;
				String originalid;
				Map sortelement = list.get(t);
				modifyid = (String) list.get(t).get("id");
				originalid = modifyid.substring(5, modifyid.length());
				for (int a = 0; a < list.size(); a++) {
					Map listelement = list.get(a);
					if (list.get(a).get("pid").equals(originalid)) {
						listelement.remove("pid");
						listelement.put("pid", "" + modifyid + "");
					}
				}
			}
			
			String	Json	=	service.DataListToJson(list);
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
		 * 
		 * <p>名称：ReferCurseSelectBySpecialField</p>
		 * <p>说明：按教学班级授权，查询部分通过专业Id获取该专业下的所有课程信息</p>
		 * <p>参数：@return 设定文件</p>
		 * <p>返回值：String 返回类型</p>
		 * <p>@return</p>
		 */
		public String	ReferCurseSelectBySpecialField(String id){
			id = id.substring(5, id.length());
			String sql	=	"select * from sys_curse where special_field_id='"+id+"'";
			List<Map> list	=	(List<Map>) di.executeQuery(sql);
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
		 * 试题库按班级类型授权，查询权限
		 */
		public String GetAuthorizedItembankByteachingclass(String id){
				List<Map> authorized_itembank	=	di.executeQuery("select itembank_id,class_id from sys_perm_itembank_class where class_id='"+id+"'");
				JSONArray authorized_itembanklist = JSONArray.fromObject(authorized_itembank);
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				try {
					PrintWriter pw = response.getWriter();
					pw.print(authorized_itembanklist);
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				return null;
			}
		
		
		//试题库按班级授权，保存配置，未选课程
		
		public String SaveAuthorityConfigByteachingclassSelectnoneclass(String authorized_esid,String teachingclass,String majorid){
			logger.debug(majorid);
			String result=null;
			//删除表中原先配置
			di.execute("delete from sys_perm_itembank_class where class_id='"+teachingclass+"' and major_id='"+majorid+"'");
			if(authorized_esid.indexOf(",")!=-1){
				String[] eslist=authorized_esid.split(",");
				//添加配置数据
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_class (id,itembank_id,class_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+teachingclass+"','"+majorid+"')");
				}
				}
			}else{
				di.execute("insert into sys_perm_itembank_class (id,itembank_id,class_id,major_id) values(sys_guid(),'"+authorized_esid+"','"+teachingclass+"','"+majorid+"')");

			}
		
			result="配置成功";
			return result;
		}
		
		//试题库按班级授权，保存配置,已选课程
		
		public String SaveAuthorityConfigByteachingclassSelectclass(String authorized_esid,String teachingclass,String courseid,String majorid){
			logger.debug(courseid);
			String result=null;
			//查找配置中在该课程下的试题库
			ArrayList banklist=new ArrayList();
			List<Map> AllList	=	di.executeQuery("select * from sys_perm_itembank_class");
			logger.debug(AllList);
			for(int j=0;j<AllList.size();j++){
				String esid=(String) AllList.get(j).get("itembank_id");
				List<Map> list	=	di.executeQuery("select * from sys_itembank_list where itembank_id='"+esid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					banklist.add(esid);
				}
			}
			//删除该课程下表中原先配置
			for(int t=0;t<banklist.size();t++){
				di.execute("delete from sys_perm_itembank_class where  class_id='"+teachingclass+"' and itembank_id='"+banklist.get(t)+"'");
			}
			if(authorized_esid.indexOf(",")!=-1){
				String[] eslist=authorized_esid.split(",");
				//添加配置数据
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						di.execute("insert into sys_perm_itembank_class (id,itembank_id,class_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+teachingclass+"','"+majorid+"')");
					}
				}
			}else{
				di.execute("insert into sys_perm_itembank_class (id,itembank_id,class_id,major_id) values(sys_guid(),'"+authorized_esid+"','"+teachingclass+"','"+majorid+"')");

			}
			
			/*String result=null;
			String[] eslist=authorized_esid.split(",");
			//删除表中原先配置
			di.execute("delete from sys_perm_itembank_class where class_id='"+teachingclass+"'");
			//添加配置数据
			if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_class (id,itembank_id,class_id) values(sys_guid(),'"+eslist[i]+"','"+teachingclass+"')");
				}
			}*/
			result="配置成功";
			return result;
		}
		
		
		/**
		 * 试题库按组织机构授权，获取机构信息
		 */
		public String GetSchoolStructure(String memberid){
			String treeid="";
			if(memberid.equals("teacher")){
				treeid="departmentTree";
			}
			if(memberid.equals("student")){
				treeid="StudentDepTree";
			}
			String	sql	=	"select * from sys_department_tree  where tree_id='"+treeid+"'order by node_path asc";
			List<Map> list	=	di.executeQuery(sql);
			String	Json	=	service.DataListToJson(list);
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
		 * 试题库按组织机构授权，查询权限
		 */
		public String GetAuthorizedItembankBydepartment(String memberid,String departmentid,String uppernode){
			String[] uppernodelist=uppernode.split(",");
			//sql拼接
			String uppernodeSql="";
			for(int t=0;t<uppernodelist.length;t++){
				String uppernodeid=uppernodelist[t];
				if(uppernodeSql!=""){
					uppernodeSql=uppernodeSql+" or ";
				}
				uppernodeSql+="department_id="+"'"+uppernodeid+"'";
			}
			//权限信息
			List<Map> authorized_itembank	=	di.executeQuery("select itembank_id,department_id,membertype from sys_perm_itembank_department where membertype='"+memberid+"' and "+uppernodeSql+" or department_id='"+departmentid+"'");
			JSONArray authorized_itembanklist = JSONArray.fromObject(authorized_itembank);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try {
				PrintWriter pw = response.getWriter();
				pw.print(authorized_itembanklist);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		/**
		 * 试题库按机构授权，保存配置，未选课程
		 */
		public String SaveAuthorityConfigBydepartmentSelectnonecourse(String authorized_es, String member,String departmentid,String majorid){
			String result=null;
			//删除表中原先配置
			di.execute("delete from sys_perm_itembank_department where department_id='"+departmentid+"' and membertype='"+member+"' and major_id='"+majorid+"'");
			if(authorized_es.indexOf(",")!=-1){
				
				String[] eslist=authorized_es.split(",");
				//添加配置数据
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						di.execute("insert into sys_perm_itembank_department (id,itembank_id,department_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+departmentid+"','"+member+"','"+majorid+"')");
					}
				}
			}else{
				di.execute("insert into sys_perm_itembank_department (id,itembank_id,department_id,membertype,major_id) values(sys_guid(),'"+authorized_es+"','"+departmentid+"','"+member+"','"+majorid+"')");

			}
			result="配置成功";
			return result;
		}
		
		/**
		 * 试题库按机构授权，保存配置，选择课程
		 */
		public String SaveAuthorityConfigBydepartmentSelectcourse(String authorized_es, String member,String departmentid,String courseid,String majorid){
			String result=null;
			//查找配置中在该课程下的试题库
			ArrayList banklist=new ArrayList();
			List<Map> AllList	=	di.executeQuery("select * from sys_perm_itembank_department");
			for(int j=0;j<AllList.size();j++){
				String esid=(String) AllList.get(j).get("itembank_id");
				List<Map> list	=	di.executeQuery("select * from sys_itembank_list where itembank_id='"+esid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					banklist.add(esid);
				}
			}
			//删除该课程下表中原先配置
			for(int t=0;t<banklist.size();t++){
				di.execute("delete from sys_perm_itembank_department where department_id='"+departmentid+"' and membertype='"+member+"' and itembank_id='"+banklist.get(t)+"'");
			}
			List<Map> test	=	di.executeQuery("select * from sys_perm_itembank_department");
			if(authorized_es.indexOf(",")!=-1){
				
				String[] eslist=authorized_es.split(",");
				//添加配置数据
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						di.execute("insert into sys_perm_itembank_department (id,itembank_id,department_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+departmentid+"','"+member+"','"+majorid+"')");
					}
				}
			}else{
				di.execute("insert into sys_perm_itembank_department (id,itembank_id,department_id,membertype,major_id) values(sys_guid(),'"+authorized_es+"','"+departmentid+"','"+member+"','"+majorid+"')");

			}
			/*String result=null;
			String[] eslist=authorized_es.split(",");
			//删除表中原先配置
			di.execute("delete from sys_perm_itembank_department where department_id='"+departmentid+"' and membertype='"+member+"'");
			//添加配置数据
			if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_department (id,itembank_id,department_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+departmentid+"','"+member+"')");
				}
			}*/
			
			result="配置成功";
			return result;
		}
		
		/**
		 * 试题库查询，判断节点是否在分类体系下
		 */
		public String ReferIfDefaultsort(String refernodeleaf){
			//判断是否为跟节点
			String result="0";
			List<Map> list	=	di.executeQuery("select * from sys_ib_classification_system where classification_id='"+refernodeleaf+"'");
			if(list.size()>0){
				String treeid=(String) list.get(0).get("classification_id");
				if(treeid.equals("jibenfenleitixi")){

					result="1";
				}	
			}else{

				List<Map> listnode	=	di.executeQuery("select * from sys_ib_classification_tree where id='"+refernodeleaf+"'");
				String treeid=(String) listnode.get(0).get("tree_id");
				if(treeid.equals("jibenfenleitixi")){

					result="1";
				}	
				
			}
		//	logger.debug("************^***************"+list);
			return result;
		}
		/**
		 * 试题库查询判断选择的是否为根节点
		 */
		public String ReferIfRootnode(String refernodeleaf){
			//判断是否为跟节点
			String result="0";
			List<Map> list	=	di.executeQuery("select * from sys_ib_classification_system where classification_id='"+refernodeleaf+"'");
			if(list.size()>0){
					result="1";
			}
		//	logger.debug("************^***************"+list);
			return result;
		}
		
		
		
}

