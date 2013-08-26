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
	 * �����������������������
	 */
	public String SortConfig() {
		// ȡ������ϵ
		List<Map> list = di
				.executeQuery("select classification_id,classification_name from sys_ib_classification_system");
		String id = "";
		String name = "";
		for (int i = 0; i < list.size(); i++) {
			id = (String) list.get(i).get("classification_id");
			name = (String) list.get(i).get("classification_name");
			// ��ÿ��������ϵ����pid�ȼ�ֵ��
			Map sort = list.get(i);
			sort.put("pid", "jiedian1");
			sort.put("id", "" + id + "");
			sort.put("text", "" + name + "");
		}
		int listlength = list.size();// ����ѭ�������������ѭ����������Ϊ3��
		for (int i = 0; i < listlength; i++) {
			String classification_id = (String) list.get(i).get(
					"classification_id");
			String classification_name = (String) list.get(i).get(
					"classification_name");
			// ȡ��ÿ��������ϵ���ӷ�����
			List<Map> sortlist = di
					.executeQuery("select id,text,pid,tree_id,node_path from sys_ib_classification_tree where tree_id='"
							+ classification_id + "' order by node_path asc");
			for (int j = 0; j < sortlist.size(); j++) {
				Map sortelement = sortlist.get(j);
				// �޸�pidֵΪ��Ľڵ���Ϊ������ϵ���ӽڵ�
				if (sortlist.get(j).get("pid").equals("0")) {
					Map ss = sortlist.get(j);
					ss.remove("pid");
					ss.put("pid", "" + classification_id + "");
				}
				list.add(sortelement);

			}
		}
		// ��list��Ӹ��ڵ�
		Map<String, Object> rootnode = new HashMap<String, Object>();
		rootnode.put("pid", "0");
		rootnode.put("text", "������ϵ�б�");
		rootnode.put("id", "jiedian1");
		list.add(0, rootnode);
		// ��list<map>ת��ΪJson��
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
	 * <p>���ƣ�addEsSortConfig</p>
	 * <p>˵������������ʱ������ϵ��ȡ���˴����ܰ�������������ϵTODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String addEsSortConfig(){
		// ȡ������ϵ
				List<Map> list = di
						.executeQuery("select classification_id,classification_name from sys_ib_classification_system where classification_id!='jibenfenleitixi'");
				String id = "";
				String name = "";
				for (int i = 0; i < list.size(); i++) {
					id = (String) list.get(i).get("classification_id");
					name = (String) list.get(i).get("classification_name");
					// ��ÿ��������ϵ����pid�ȼ�ֵ��
					Map sort = list.get(i);
					sort.put("pid", "jiedian1");
					sort.put("id", "" + id + "");
					sort.put("text", "" + name + "");
				}
				int listlength = list.size();// ����ѭ�������������ѭ����������Ϊ3��
				for (int i = 0; i < listlength; i++) {
					String classification_id = (String) list.get(i).get(
							"classification_id");
					String classification_name = (String) list.get(i).get(
							"classification_name");
					// ȡ��ÿ��������ϵ���ӷ�����
					List<Map> sortlist = di
							.executeQuery("select id,text,pid,tree_id,node_path from sys_ib_classification_tree where tree_id='"
									+ classification_id + "' order by node_path asc");
					for (int j = 0; j < sortlist.size(); j++) {
						Map sortelement = sortlist.get(j);
						// �޸�pidֵΪ��Ľڵ���Ϊ������ϵ���ӽڵ�
						if (sortlist.get(j).get("pid").equals("0")) {
							Map ss = sortlist.get(j);
							ss.remove("pid");
							ss.put("pid", "" + classification_id + "");
						}
						list.add(sortelement);

					}
				}
				// ��list��Ӹ��ڵ�
				Map<String, Object> rootnode = new HashMap<String, Object>();
				rootnode.put("pid", "0");
				rootnode.put("text", "������ϵ�б�");
				rootnode.put("id", "jiedian1");
				list.add(0, rootnode);
				// ��list<map>ת��ΪJson��
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
	 * ���������������;������;���ݻ�ȡ
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
	 * ����������Ϣ���
	 */
	public String ExamSystemAdd(String name, String discription,
			String identifier, String leafid, String config_leafid, String use) {
		String result = "";
		// �жϷ�����ϵ�����Ƿ��ظ�
		List<Map> list = (List<Map>) di
				.executeQuery("select * from sys_itembank_list where itembank_name='"
						+ name + "' or itembank_id='" + identifier + "'");
		for (int i = 0; i < list.size(); i++) {
			String itembank_name = (String) list.get(i).get("itembank_name");
			String itembank_id = (String) list.get(i).get("itembank_id");

			// �жϷ�����ϵ�����Ƿ��ظ�
			if (itembank_name.equals(name)) {
				result = "�����ظ�������������";
				return result;
			}
			// �жϷ�����ϵ��ʶ��ʱ���ظ�
			if (itembank_id.equals(identifier)) {
				result = "��ʶ���ظ�������������";
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
		//������Ӧ���������Ϣ��
		di.execute("create table QT_BasicField_"+identifier+" (QUESTION_ID VARCHAR(50) primary key,question_type varchar(50),difficulty float(1),defaultpoint number(38),time_use number(38),knowledge_point_id varchar(50),editor_id varchar(50),update_time Date,time Date,explain varchar(50) default('��'),statue number(38) default(1))");
		//������Ӧ�������ݺ͸�����Ϣ��
		di.execute("create table QT_BlobField_"+identifier+"(QUESTION_ID VARCHAR(50) primary key,questioncontent blob not null,uploadfile blob not null)");
		result = "��ӳɹ�";
		return result;
	}

	/**
	 * ɾ������⣬��������
	 */
	public String ExamSystemDelete(String id) {
		String getSql = "delete from sys_itembank_list where itembank_id='"
				+ id + "'";
		String result = "";
		if (getSql != null) {
			if (di.execute(getSql) != 1) {
				
				result = "ɾ��ʧ��!";
			} else {
				//ɾ����Ӧ������
				di.execute("drop table QT_BasicField_"+id+"");
				di.execute("drop table QT_BlobField_"+id+"");
				//ɾ�������������������ϵ�µ�������Ϣ
				di.execute("delete from sys_ib_classification_node where itembank_id='"
				+ id + "'");
				result = "ɾ���ɹ�";
			}
		}
		return result;
	}

	/**
	 * ����ɾ�������
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
				result = "ɾ��ʧ��!";
			} else {
				//ɾ����Ӧ������
				for(int j=0;j<array.length;j++){
					String itembankId=array[j];
					di.execute("drop table QT_BasicField_"+itembankId+"");
					di.execute("drop table QT_BlobField_"+itembankId+"");
				}
				//ɾ��������������ϵ�µĶ�Ӧ����������Ϣ
				di.execute(sortConfigSql);
				result = "ɾ���ɹ�";
			}
		}
		return result;
	}

	/**
	 * �޸ķ�����ϵ��������������Ϣ��ȡ
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
	 * �޸�����������������÷����б���Ϣ��ȡ
	 */
	public String ModifySortConfig() {
		// ȡ������ϵ
		List<Map> list = di
				.executeQuery("select classification_id,classification_name from sys_ib_classification_system where classification_id!='jibenfenleitixi'");
		String id = "";
		String name = "";
		for (int i = 0; i < list.size(); i++) {
			id = (String) list.get(i).get("classification_id");
			name = (String) list.get(i).get("classification_name");
			// ��ÿ��������ϵ����pid�ȼ�ֵ��
			Map sort = list.get(i);
			sort.put("pid", "modify_sortconfig_node");
			sort.put("id", "modify" + id + "");
			sort.put("text", "" + name + "");
		}
		int listlength = list.size();// ����ѭ�������������ѭ����������Ϊ3��
		for (int i = 0; i < listlength; i++) {
			String classification_id = (String) list.get(i).get(
					"classification_id");
			String classification_name = (String) list.get(i).get(
					"classification_name");
			// ȡ��ÿ��������ϵ���ӷ�����
			List<Map> sortlist = di
					.executeQuery("select id,text,pid,tree_id,node_path from sys_ib_classification_tree where tree_id='"
							+ classification_id + "' order by node_path asc");
			for (int j = 0; j < sortlist.size(); j++) {
				Map sortelement = sortlist.get(j);
				// ��ÿ��idǰ�������ֶΣ���������ѯ�з����б��е�id����
				String original_id;
				original_id = (String) sortlist.get(j).get("id");
				sortelement.remove("id");
				sortelement.put("id", "modify" + original_id + "");
				// ���Ķ�Ӧ�ӽڵ��pid
				// �޸�pidֵΪ��Ľڵ���Ϊ������ϵ���ӽڵ�
				if (sortlist.get(j).get("pid").equals("0")) {
					Map ss = sortlist.get(j);
					ss.remove("pid");
					ss.put("pid", "" + classification_id + "");
				}
				list.add(sortelement);
			}
			// ���ķ�����ϵid������modify�ֶ�
			for (int s = 0; s < list.size(); s++) {
				if (list.get(s).get("pid").equals(classification_id)) {
					Map sortelement = list.get(s);
					sortelement.remove("pid");
					sortelement.put("pid", "modify" + classification_id + "");
				}
			}

		}

		// ���Ľڵ�pid
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

		// ��list��Ӹ��ڵ�
		Map<String, Object> rootnode = new HashMap<String, Object>();
		rootnode.put("pid", "0");
		rootnode.put("text", "������ϵ�б�");
		rootnode.put("id", "modify_sortconfig_node");
		list.add(0, rootnode);
		// ��list<map>ת��ΪJson��
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
	 * �޸�����⣬��Ϣ���
	 */
	public String ExamSystemModify(String id, String name, String discription,
			String identifier, String config_leafid, String use) {
		String result=""; // ǰ̨�������
		// �޸�ǰ��ʶ��Ϊid��ֵ
		String oldname = "";// �޸�ǰname
		String olduse="";// �޸�ǰ��;
		String oldexplain="";// �޸�ǰ˵��
		String oldconfig_leafid="";// �޸�ǰ��������id
		//ȥ����������idǰ��modify
		if(!"null".equals(config_leafid)){
			config_leafid = config_leafid.substring(6, config_leafid.length());
		}
		// �����ݿ���ȡ���޸�ǰ������
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

		// ����ʶ���ͱ�������޸�
		if (!oldname.equals(name) && !id.equals(identifier)) {
			// �ж��µ������Ƿ�����ݿ��е������ظ�
			List<Map> listname = (List<Map>) di
					.executeQuery("select * from sys_itembank_list where itembank_name='"
							+ name + "' ");
			// ���������ظ�
			if (listname.size() > 0) {
				result = "�����ظ�������������";
				return result;
			}
			// �ж��µı�ʶ���Ƿ�����ݿ����ظ�
			List<Map> listidentifier = (List<Map>) di
					.executeQuery("select * from sys_itembank_list where itembank_id='"
							+ identifier + "' ");
			// ���µı�ʶ�����ظ�
			if (listidentifier.size() > 0) {
				result = "��ʶ���ظ�������������";
				return result;
			}
			//�ж�����������ϵ�ڵ��Ƿ񴥷�
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
				// �ж�����������ϵ�����Ƿ���ģ���δ�ı�
				if (oldconfig_leafid.equals(config_leafid)) {
					String Sql = "update sys_itembank_list set itembank_name='"
							+ name + "',itembank_id='" + identifier + "',explain='"
							+ discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					//�޸ķ��������е������id
					di.execute("update sys_ib_classification_node set itembank_id='"
							+ identifier
							+ "' where	itembank_id='" + id + "'");
				} else {
					// ����������ϵ�������޸�
					String Sql = "update sys_itembank_list set itembank_name='"
							+ name + "',itembank_id='" + identifier + "',explain='"
							+ discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					// �޸�����������ϵ������
					String Sql1 = "update sys_ib_classification_node set itembank_id='"
							+ identifier
							+ "',node_id='"
							+ config_leafid
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql1);
					

				}
			}
			//�޸Ĵ洢����ı���
			di.execute("ALTER  TABLE QT_BASICFIELD_"+id+" RENAME TO QT_bASICFIELD_"+identifier+"");
			di.execute("ALTER  TABLE QT_BLOBFIELD_"+id+" RENAME TO QT_BLOBFIELD_"+identifier+"");
			
			
		}

		// ����ʶ��δ�޸ģ��������޸�
		if (!oldname.equals(name) && id.equals(identifier)) {
			List<Map> listname = (List<Map>) di
					.executeQuery("select * from sys_itembank_list where itembank_name='"
							+ name + "' ");
			// �����������ظ�
			if (listname.size() > 0) {
				result = "�����ظ�������������";
				return result;
			}
			//�ж�����������ϵ�ڵ��Ƿ񴥷�
			if("null".equals(config_leafid)){
				String Sql = "update sys_itembank_list set itembank_name='"
						+ name + "',explain='" + discription + "',use='" + use
						+ "' 	where	itembank_id='" + id + "'";
				di.execute(Sql);
			}else{
				// �ж�����������ϵ�����Ƿ���ģ���δ�ı�
				if (oldconfig_leafid.equals(config_leafid)) {
					String Sql = "update sys_itembank_list set itembank_name='"
							+ name + "',explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
				} else {
					// ����������ϵ�������޸�
					String Sql = "update sys_itembank_list set itembank_name='"
							+ name + "',explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					// �޸�����������ϵ������
					String Sql1 = "update sys_ib_classification_node set node_id='"
							+ config_leafid + "' 	where	itembank_id='" + id + "'";
					di.execute(Sql1);
				}
			}
		}

		// ������δ�޸ģ���ʶ�����޸�
		if (oldname.equals(name) && !id.equals(identifier)) {
			List<Map> listidentifier = (List<Map>) di
					.executeQuery("select * from sys_itembank_list where itembank_id='"
							+ identifier + "' ");
			// ���µķ�����ϵ�������ظ�
			if (listidentifier.size() > 0) {
				result = "��ʶ���ظ�������������";
				return result;
			}
			//�ж�����������ϵ�ڵ��Ƿ񴥷�
			if("null".equals(config_leafid)){
				String Sql = "update sys_itembank_list set itembank_id='"
						+ identifier + "',explain='" + discription + "',use='" + use
						+ "' 	where	itembank_id='" + id + "'";
				di.execute(Sql);
				di.execute("update sys_ib_classification_node set itembank_id='"
						+ identifier
						+ "' where	itembank_id='" + id + "'");
			}else{
				// �ж�����������ϵ�����Ƿ���ģ���δ�ı�
				if (oldconfig_leafid.equals(config_leafid)) {
					String Sql = "update sys_itembank_list set itembank_id='"
							+ identifier + "',explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					//�޸ķ��������е������id
					di.execute("update sys_ib_classification_node set itembank_id='"
							+ identifier
							+ "' where	itembank_id='" + id + "'");
				} else {
					// ����������ϵ�������޸�
					String Sql = "update sys_itembank_list set itembank_id='"
							+ identifier + "',explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					// �޸�����������ϵ������
					String Sql1 = "update sys_ib_classification_node set itembank_id='"
							+ identifier + "',node_id='"
							+ config_leafid + "' 	where	itembank_id='" + id + "'";
					di.execute(Sql1);
				}
				
			}
			di.execute("ALTER  TABLE QT_BASICFIELD_"+id+" RENAME TO QT_bASICFIELD_"+identifier+"");
			di.execute("ALTER  TABLE QT_BLOBFIELD_"+id+" RENAME TO QT_BLOBFIELD_"+identifier+"");

		}

		// �����ƺͱ�ʶ����δ�޸�
		if (oldname.equals(name) && id.equals(identifier)) {
			//�ж�����������ϵ�ڵ��Ƿ񴥷�
			if("null".equals(config_leafid)){
				String Sql = "update sys_itembank_list set explain='" + discription + "',use='" + use
						+ "' 	where	itembank_id='" + id + "'";
				di.execute(Sql);
			}else{
				// �ж�����������ϵ�����Ƿ���ģ���δ�ı�
				if (oldconfig_leafid.equals(config_leafid)) {
					String Sql = "update sys_itembank_list set explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
				} else {
					// ����������ϵ�������޸�
					String Sql = "update sys_itembank_list set explain='" + discription + "',use='" + use
							+ "' 	where	itembank_id='" + id + "'";
					di.execute(Sql);
					// �޸�����������ϵ������
					String Sql1 = "update sys_ib_classification_node set node_id='"
							+ config_leafid + "' 	where	itembank_id='" + id + "'";
					di.execute(Sql1);
				}
			}
		
		
		}
		result = "�޸ĳɹ�";
		return result;
	}
	
	/**
	 * 
	//�������Ȩ����ɫ��Ȩ��ȡ��ɫ����
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
	 * ����ⰴ��ɫ��Ȩ��ȡ���������δѡ�γ�
	 */
	public String GetItembankBymajor(String majorid){
		List<Map> subjectlist	=	di.executeQuery("select * from sys_curse where special_field_id='"+majorid+"'");
		String curse_sql="";
		for (int j = 0; j < subjectlist.size(); j++) {
			//�γ�sqlƴ��
			String subjectid=(String) subjectlist.get(j).get("curse_id");
				if(curse_sql!=""){
					curse_sql=curse_sql+" or ";
				}
				curse_sql+="curse_id="+"'"+subjectid+"'";
			}
		//���������
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
	 * ��ȡ�γ�name����ѡרҵδѡ�γ�ʱ
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
	 * רҵ�γ̾�ѡ��ʱͨ���γ�id��ÿγ�name
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
	 * ����ⰴ��ɫ��Ȩ��ȡ��������ݣ���ѡ�γ�
	 */
	public String GetItembankBycurse(String curseid){
		//���������
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
 * ����ⰴ��ɫ��Ȩ��ȡĳ��ɫ��Ա�������Ȩ��	
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
	 * ����ⰴ��ɫ��Ȩ���������ã�δѡ�γ�
	 */
	public String SaveAuthorityConfigByroleSelectnonecourse(String authorized_esid,String role,String member,String majorid){
		String result=null;
		//�ж���Ȩ�������ֻ��һ��
		//������
		if(authorized_esid.indexOf(",")!=-1){
			String[] eslist=authorized_esid.split(",");
			//ɾ������ԭ������
			di.execute("delete from sys_perm_itembank_role where role_id='"+role+"' and membertype_id='"+member+"' and major_id='"+majorid+"'");
			//�����������
			//�ж��Ƿ���ѡ�������,Ϊ��ʱ����Ϊ1
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
		//���ж��Ƿ���Ȩ�������ֻ��һ��
		if(authorized_esid.indexOf(",")!=-1){
			eslist=authorized_esid.split(",");
		}else{
		}
		//ɾ������ԭ������
		di.execute("delete from sys_perm_itembank_role where role_id='"+role+"' and membertype_id='"+member+"'");
		//�����������
		//�ж��Ƿ���ѡ�������,Ϊ��ʱ����Ϊ1
		if(eslist.length!=1){
		for(int i=0;i<eslist.length;i++){
			di.execute("insert into sys_perm_itembank_role (id,itembank_id,role_id,membertype_id) values(sys_guid(),'"+eslist[i]+"','"+role+"','"+member+"')");
		}
		}*/
		result="���óɹ�";
		return result;
	}
	/**
	 * ����ⰴ��ɫ��Ȩ���������ã�ѡ��γ�
	 */
	public String SaveAuthorityConfigByroleSelectcourse(String authorized_esid,String role,String member,String courseid,String majorid){
		String result=null;
		//�����������ڸÿγ��µ������
				ArrayList banklist=new ArrayList();
				List<Map> AllList	=	di.executeQuery("select * from sys_perm_itembank_role");
				for(int j=0;j<AllList.size();j++){
					String esid=(String) AllList.get(j).get("itembank_id");
					List<Map> list	=	di.executeQuery("select * from sys_itembank_list where itembank_id='"+esid+"' and curse_id='"+courseid+"'");
					if(list.size()>0){
						banklist.add(esid);
					}
				}
				//ɾ���ÿγ��±���ԭ������
				for(int t=0;t<banklist.size();t++){
					di.execute("delete from sys_perm_itembank_role where role_id='"+role+"' and membertype_id='"+member+"' and itembank_id='"+banklist.get(t)+"'");
				}
		//�����������
		//���ж��Ƿ���Ȩ�������ֻ��һ��
		//������
		if(authorized_esid.indexOf(",")!=-1){
			String[] eslist=authorized_esid.split(",");
			//�����������
			if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_role (id,itembank_id,role_id,membertype_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+role+"','"+member+"','"+majorid+"')");
				}
			}
		}else{
			di.execute("insert into sys_perm_itembank_role (id,itembank_id,role_id,membertype_id,major_id) values(sys_guid(),'"+authorized_esid+"','"+role+"','"+member+"','"+majorid+"')");

		}
		/*String[] eslist=authorized_esid.split(",");
		//�����������ڸÿγ��µ������
		ArrayList banklist=new ArrayList();
		List<Map> AllList	=	di.executeQuery("select * from sys_perm_itembank_role");
		for(int j=0;j<AllList.size();j++){
			String esid=(String) AllList.get(j).get("itembank_id");
			List<Map> list	=	di.executeQuery("select * from sys_itembank_list where itembank_id='"+esid+"' and curse_id='"+courseid+"'");
			if(list.size()>0){
				banklist.add(esid);
			}
		}
		//ɾ���ÿγ��±���ԭ������
		for(int t=0;t<banklist.size();t++){
			di.execute("delete from sys_perm_itembank_role where role_id='"+role+"' and membertype_id='"+member+"' and itembank_id='"+banklist.get(t)+"'");
		}
		//�����������
		if(eslist.length!=1){
			for(int i=0;i<eslist.length;i++){
				di.execute("insert into sys_perm_itembank_role (id,itembank_id,role_id,membertype_id) values(sys_guid(),'"+eslist[i]+"','"+role+"','"+member+"')");
			}
		}*/
		result="���óɹ�";
		return result;
	}
	
	
	/**
	 * ����ⰴ�û���Ȩ���жϽ̹��Ż�ѧ���Ƿ�������ȷ
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
	 * ����ⰴ�û���Ȩ��ȡĳ�û��������Ȩ��	
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
		 * ����ⰴ�û���Ȩ����������,δѡ�γ�
		 */
		public String SaveAuthorityConfigByuserSelectnonecourse(String authorized_esid,String number,String member,String majorid){
			String result=null;
			//ɾ������ԭ������
			di.execute("delete from sys_perm_itembank_user where user_id='"+number+"' and membertype='"+member+"' and major_id='"+majorid+"'");
			if(authorized_esid.indexOf(",")!=-1){
				String[] eslist=authorized_esid.split(",");
				//�����������
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						di.execute("insert into sys_perm_itembank_user (id,itembank_id,user_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"','"+majorid+"')");
					}
				}
				}else{
					di.execute("insert into sys_perm_itembank_user (id,itembank_id,user_id,membertype,major_id) values(sys_guid(),'"+authorized_esid+"','"+number+"','"+member+"','"+majorid+"')");

				}
			result="���óɹ�";
			return result;
		}
		
		/**
		 * ����ⰴ�û���Ȩ����������,ѡ��γ�
		 */
		public String SaveAuthorityConfigByuserSelectcourse(String authorized_esid,String number,String member,String courseid,String majorid){
			String result=null;
			//�����������ڸÿγ��µ������
			ArrayList banklist=new ArrayList();
			List<Map> AllList	=	di.executeQuery("select * from sys_perm_itembank_user");
			for(int j=0;j<AllList.size();j++){
				String esid=(String) AllList.get(j).get("itembank_id");
				List<Map> list	=	di.executeQuery("select * from sys_itembank_list where itembank_id='"+esid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					banklist.add(esid);
				}
			}
			//ɾ���ÿγ��±���ԭ������
			for(int t=0;t<banklist.size();t++){
				di.execute("delete from sys_perm_itembank_user where user_id='"+number+"' and membertype='"+member+"' and itembank_id='"+banklist.get(t)+"'");
			}
			if(authorized_esid.indexOf(",")!=-1){
				String[] eslist=authorized_esid.split(",");
				//�����������
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						di.execute("insert into sys_perm_itembank_user (id,itembank_id,user_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"','"+majorid+"')");
					}
				}
			}else{
				di.execute("insert into sys_perm_itembank_user (id,itembank_id,user_id,membertype,major_id) values(sys_guid(),'"+authorized_esid+"','"+number+"','"+member+"','"+majorid+"')");

			}
			
			result="���óɹ�";
			return result;
			
		/*	String result=null;
			String[] eslist=authorized_esid.split(",");
			//ɾ������ԭ������
			di.execute("delete from sys_perm_itembank_user where user_id='"+number+"' and membertype='"+member+"'");
			//�����������
			if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_user (id,itembank_id,user_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+number+"','"+member+"')");
				}
			}
			result="���óɹ�";
			return result;*/
		}
		
		/**
		 * ����ⰴ��Ա������Ȩ����ѯȨ��
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
		 * ����ⰴ��Ա������Ȩ���������ã�δѡ�γ�
		 */
		public String SaveAuthorityConfigBymembertypeSelectnonecourse(String authorized_esid,String member,String majorid){
			String result=null;
			//ɾ������ԭ������
			di.execute("delete from sys_perm_itembank_membertype where membertype='"+member+"' and major_id='"+majorid+"'");
			if(authorized_esid.indexOf(",")!=-1){
				String[] eslist=authorized_esid.split(",");
				//�����������
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						di.execute("insert into sys_perm_itembank_membertype (id,itembank_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+member+"','"+majorid+"')");
					}
				}
				
			}else{
				di.execute("insert into sys_perm_itembank_membertype (id,itembank_id,membertype,major_id) values(sys_guid(),'"+authorized_esid+"','"+member+"','"+majorid+"')");

			}
			result="���óɹ�";
			return result;
		}
		
		/**
		 * ����ⰴ��Ա������Ȩ���������ã���ѡ�γ�
		 */
		public String SaveAuthorityConfigBymembertypeSelectcourse(String authorized_esid,String member,String courseid,String majorid){
			String result=null;
			//�����������ڸÿγ��µ������
			ArrayList banklist=new ArrayList();
			List<Map> AllList	=	di.executeQuery("select * from sys_perm_itembank_membertype");
			for(int j=0;j<AllList.size();j++){
				String esid=(String) AllList.get(j).get("itembank_id");
				List<Map> list	=	di.executeQuery("select * from sys_itembank_list where itembank_id='"+esid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					banklist.add(esid);
				}
			}
			//ɾ���ÿγ��±���ԭ������
			for(int t=0;t<banklist.size();t++){
				di.execute("delete from sys_perm_itembank_membertype where membertype='"+member+"' and itembank_id='"+banklist.get(t)+"'");
			}
			if(authorized_esid.indexOf(",")!=-1){
				
				String[] eslist=authorized_esid.split(",");
				//�����������
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
			//ɾ������ԭ������
			di.execute("delete from sys_perm_itembank_membertype where membertype='"+member+"'");
			//�����������
			if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_membertype (id,itembank_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+member+"')");
				}
			}*/
			result="���óɹ�";
			return result;
		}
		
		
		/**
		 * ����ⰴ��ѧ�༶��Ȩ�����ݿγ̲�ѯ��ѧ�༶
		 */
		
		public String GetTeachingClass(String courseid){
			List<Map> teachingclass	=	di.executeQuery("select curse_class_id,curse_id,class_name from sys_curse_class where curse_id='"+courseid+"'");
			/*Service si = new ServiceImpl();
			String Json = si.DataListToJson(teachingclass);*/  //��һ����ʽjson
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
		 * ����ⰴ��ѧ�༶��Ȩ����ò�ѯ�����б�
		 */
		public String ReferSchoolStructureOptionsGet(){
			String	sql	=	"select * from sys_ib_classification_tree where tree_id='jibenfenleitixi' order by node_path asc";
			List<Map> list	=	di.executeQuery(sql);
			// ��ÿ��idǰ�������ֶ�refer����ѡ���ѧ�༶������
			for (int j = 0; j < list.size(); j++) {
				Map sortelement = list.get(j);
				String original_id;
				original_id = (String) list.get(j).get("id");
				sortelement.remove("id");
				sortelement.put("id", "refer" + original_id + "");
				}
			// ���Ľڵ�pid
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
		 * <p>���ƣ�ReferCurseSelectBySpecialField</p>
		 * <p>˵��������ѧ�༶��Ȩ����ѯ����ͨ��רҵId��ȡ��רҵ�µ����пγ���Ϣ</p>
		 * <p>������@return �趨�ļ�</p>
		 * <p>����ֵ��String ��������</p>
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
		 * ����ⰴ�༶������Ȩ����ѯȨ��
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
		
		
		//����ⰴ�༶��Ȩ���������ã�δѡ�γ�
		
		public String SaveAuthorityConfigByteachingclassSelectnoneclass(String authorized_esid,String teachingclass,String majorid){
			logger.debug(majorid);
			String result=null;
			//ɾ������ԭ������
			di.execute("delete from sys_perm_itembank_class where class_id='"+teachingclass+"' and major_id='"+majorid+"'");
			if(authorized_esid.indexOf(",")!=-1){
				String[] eslist=authorized_esid.split(",");
				//�����������
				if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_class (id,itembank_id,class_id,major_id) values(sys_guid(),'"+eslist[i]+"','"+teachingclass+"','"+majorid+"')");
				}
				}
			}else{
				di.execute("insert into sys_perm_itembank_class (id,itembank_id,class_id,major_id) values(sys_guid(),'"+authorized_esid+"','"+teachingclass+"','"+majorid+"')");

			}
		
			result="���óɹ�";
			return result;
		}
		
		//����ⰴ�༶��Ȩ����������,��ѡ�γ�
		
		public String SaveAuthorityConfigByteachingclassSelectclass(String authorized_esid,String teachingclass,String courseid,String majorid){
			logger.debug(courseid);
			String result=null;
			//�����������ڸÿγ��µ������
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
			//ɾ���ÿγ��±���ԭ������
			for(int t=0;t<banklist.size();t++){
				di.execute("delete from sys_perm_itembank_class where  class_id='"+teachingclass+"' and itembank_id='"+banklist.get(t)+"'");
			}
			if(authorized_esid.indexOf(",")!=-1){
				String[] eslist=authorized_esid.split(",");
				//�����������
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
			//ɾ������ԭ������
			di.execute("delete from sys_perm_itembank_class where class_id='"+teachingclass+"'");
			//�����������
			if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_class (id,itembank_id,class_id) values(sys_guid(),'"+eslist[i]+"','"+teachingclass+"')");
				}
			}*/
			result="���óɹ�";
			return result;
		}
		
		
		/**
		 * ����ⰴ��֯������Ȩ����ȡ������Ϣ
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
		 * ����ⰴ��֯������Ȩ����ѯȨ��
		 */
		public String GetAuthorizedItembankBydepartment(String memberid,String departmentid,String uppernode){
			String[] uppernodelist=uppernode.split(",");
			//sqlƴ��
			String uppernodeSql="";
			for(int t=0;t<uppernodelist.length;t++){
				String uppernodeid=uppernodelist[t];
				if(uppernodeSql!=""){
					uppernodeSql=uppernodeSql+" or ";
				}
				uppernodeSql+="department_id="+"'"+uppernodeid+"'";
			}
			//Ȩ����Ϣ
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
		 * ����ⰴ������Ȩ���������ã�δѡ�γ�
		 */
		public String SaveAuthorityConfigBydepartmentSelectnonecourse(String authorized_es, String member,String departmentid,String majorid){
			String result=null;
			//ɾ������ԭ������
			di.execute("delete from sys_perm_itembank_department where department_id='"+departmentid+"' and membertype='"+member+"' and major_id='"+majorid+"'");
			if(authorized_es.indexOf(",")!=-1){
				
				String[] eslist=authorized_es.split(",");
				//�����������
				if(eslist.length!=1){
					for(int i=0;i<eslist.length;i++){
						di.execute("insert into sys_perm_itembank_department (id,itembank_id,department_id,membertype,major_id) values(sys_guid(),'"+eslist[i]+"','"+departmentid+"','"+member+"','"+majorid+"')");
					}
				}
			}else{
				di.execute("insert into sys_perm_itembank_department (id,itembank_id,department_id,membertype,major_id) values(sys_guid(),'"+authorized_es+"','"+departmentid+"','"+member+"','"+majorid+"')");

			}
			result="���óɹ�";
			return result;
		}
		
		/**
		 * ����ⰴ������Ȩ���������ã�ѡ��γ�
		 */
		public String SaveAuthorityConfigBydepartmentSelectcourse(String authorized_es, String member,String departmentid,String courseid,String majorid){
			String result=null;
			//�����������ڸÿγ��µ������
			ArrayList banklist=new ArrayList();
			List<Map> AllList	=	di.executeQuery("select * from sys_perm_itembank_department");
			for(int j=0;j<AllList.size();j++){
				String esid=(String) AllList.get(j).get("itembank_id");
				List<Map> list	=	di.executeQuery("select * from sys_itembank_list where itembank_id='"+esid+"' and curse_id='"+courseid+"'");
				if(list.size()>0){
					banklist.add(esid);
				}
			}
			//ɾ���ÿγ��±���ԭ������
			for(int t=0;t<banklist.size();t++){
				di.execute("delete from sys_perm_itembank_department where department_id='"+departmentid+"' and membertype='"+member+"' and itembank_id='"+banklist.get(t)+"'");
			}
			List<Map> test	=	di.executeQuery("select * from sys_perm_itembank_department");
			if(authorized_es.indexOf(",")!=-1){
				
				String[] eslist=authorized_es.split(",");
				//�����������
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
			//ɾ������ԭ������
			di.execute("delete from sys_perm_itembank_department where department_id='"+departmentid+"' and membertype='"+member+"'");
			//�����������
			if(eslist.length!=1){
				for(int i=0;i<eslist.length;i++){
					di.execute("insert into sys_perm_itembank_department (id,itembank_id,department_id,membertype) values(sys_guid(),'"+eslist[i]+"','"+departmentid+"','"+member+"')");
				}
			}*/
			
			result="���óɹ�";
			return result;
		}
		
		/**
		 * ������ѯ���жϽڵ��Ƿ��ڷ�����ϵ��
		 */
		public String ReferIfDefaultsort(String refernodeleaf){
			//�ж��Ƿ�Ϊ���ڵ�
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
		 * ������ѯ�ж�ѡ����Ƿ�Ϊ���ڵ�
		 */
		public String ReferIfRootnode(String refernodeleaf){
			//�ж��Ƿ�Ϊ���ڵ�
			String result="0";
			List<Map> list	=	di.executeQuery("select * from sys_ib_classification_system where classification_id='"+refernodeleaf+"'");
			if(list.size()>0){
					result="1";
			}
		//	logger.debug("************^***************"+list);
			return result;
		}
		
		
		
}

