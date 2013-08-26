/**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	SortServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�lijuan
 * | �������ڣ�2012-12-4 ����10:54:04
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-4 ����10:54:04
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
 * ������net.ib.util.service.impl.SortServiceImpl
 * </p>
 * <p>
 * ������������ϵ�ӿ�ʵ��
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
	 * ��ӷ�����ϵ
	 */
	public String SortAdd(String name, String identifier, String discription) {
		String result;
		/* DaoImpl di = new DaoImpl(); */
		// Dao di = (Dao) BeanLoader.getBean("DaoImpl");
		// �жϷ�����ϵ�����Ƿ��ظ�
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
			// �жϷ�����ϵ�����Ƿ��ظ�
			if (classification_name.equals(name)) {
				result = "�����ظ�������������";
				return result;
			}
			// �жϷ�����ϵ��ʶ��ʱ���ظ�
			if (classification_id.equals(identifier)) {
				result = "��ʶ���ظ�������������";
				return result;
			}
		}
		//���������ϵ����
		di.execute("insert into sys_ib_classification_system (classification_id,classification_name,classification_explain) values('"
					+ identifier + "','" + name + "','" + discription.trim()+ "')");
		//Ϊ������ϵ��Ӹ��ڵ�
		di.execute("insert into sys_ib_classification_tree (id,tree_id,text,pid,sno,node_path,node_series,node_explain) values(sys_guid(),'"+identifier+"','"+name+"','0',1,'0001',1,'��')");
		result = "��ӳɹ�";
		return result;
	}

	/**
	 * ɾ��������ϵ
	 * 
	 */
	public String SortDelete(String id) {
		String getSql = "delete from sys_ib_classification_system	where	classification_id='"
				+ id + "'";
		String result = "";
		if (getSql != null) {
			if (di.execute(getSql) != 1) {
				//ɾ��������ϵ���ڵ�
			
				result = "ɾ��ʧ��!";
			} else {
				di.execute("delete from sys_ib_classification_tree	where	tree_id='"+ id + "'");
				result = "ɾ���ɹ�";
			}
		}
		return result;
	}
	
	
	/**
	 * ����ɾ��������ϵ
	 */
	public String SortBulkDelete(String id) {
		// TODO Auto-generated method stub
		//�յ���DelIds��ʽΪ   id1 id2 id3 id4 ....
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
				
				result = "ɾ��ʧ��!";
			} else {
				di.execute(rootnodeSql);
				result = "ɾ���ɹ�";
			}
		}
		return result;
	}
	
	

	/**
	 * ������ϵ�޸�
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
		// �����ݿ���ȡ������ΪĬ�Ϸ�����ϵ������
		List<Map> listdefaultsort = (List<Map>) di
				.executeQuery("select * from sys_ib_classification_system where if_default_classification=1 ");
		if (listdefaultsort.size() > 0) {
			is_exist_defaultsort_id = (String) listdefaultsort.get(0).get(
					"classification_id");
		} else {
			is_exist_defaultsort_id = "notexist";
		}*/
		// �����ݿ���ȡ���޸�ǰ������
		List<Map> list = (List<Map>) di
				.executeQuery("select * from sys_ib_classification_system where classification_id='"
						+ id + "' ");
		for (int i = 0; i < list.size(); i++) {
			oldname = (String) list.get(i).get("classification_name");
		/*	olddefaultsort = (String) list.get(i).get(
					"if_default_classification");*/
		}
		// ����ʶ���ͱ�������޸�
		if (!oldname.equals(name) && !id.equals(identifier)) {
			// �ж��µ������Ƿ�����ݿ��е������ظ�
			List<Map> listname = (List<Map>) di
					.executeQuery("select * from sys_ib_classification_system where classification_name='"
							+ name + "' ");
			// ���µķ�����ϵ�������ظ�
			if (listname.size() > 0) {
				result = "�����ظ�������������";
				return result;
			}
			// �ж��µı�ʶ���Ƿ�����ݿ��е������ظ�
			List<Map> listidentifier = (List<Map>) di
					.executeQuery("select * from sys_ib_classification_system where classification_name='"
							+ name + "' ");
			// ���µķ�����ϵ��ʶ�����ظ�
			if (listidentifier.size() > 0) {
				result = "��ʶ���ظ�������������";
				return result;
			}
			//�޸ķ�����ϵ����
			di.execute("update sys_ib_classification_system set classification_name='"
					+ name
					+ "',classification_id='"
					+ identifier
					+ "',classification_explain='"
					+ discription
					+ "' 	where	classification_id='" + id + "'");
			//�޸Ľڵ�����
			di.execute("update sys_ib_classification_tree set text='"+name+"',tree_id='"+identifier+"'where	tree_id='"+id+"' and pid='0'");
			
			/*// ���Ƿ�ΪĬ�Ϸ�����ϵδ�޸�
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
				// ��Ĭ�Ϸ�����ϵ�ɷ��޸�Ϊ��
				if (defaultsort.equals("1")) {
					// �����ݱ���û������ΪĬ�Ϸ�����ϵ������
					if (is_exist_defaultsort_id.equals("notexist")) {
						// ��������
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
						// ����
						// ��������
						di.executeQuery("update sys_ib_classification_system set classification_name='"
								+ name
								+ "',classification_id='"
								+ identifier
								+ "',if_default_classification="
								+ defaultsort
								+ ",classification_explain='"
								+ discription
								+ "' 	where	classification_id='" + id + "'");
						// ��ԭ�ȵ�Ĭ�Ϸ�����ϵ����Ϊ��
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
		// ����ʶ��δ�޸ģ�������ϵ�������޸�
		if (!oldname.equals(name) && id.equals(identifier)) {
			List<Map> listname = (List<Map>) di
					.executeQuery("select * from sys_ib_classification_system where classification_name='"
							+ name + "' ");
			// ���µķ�����ϵ�������ظ�
			if (listname.size() > 0) {
				result = "�����ظ�������������";
				return result;
			} else {
				di.execute("update sys_ib_classification_system set classification_name='"+name+"',classification_explain='"+discription+"' where classification_id='"+id+"'");
				di.execute("update sys_ib_classification_tree set text='"+name+"' where tree_id='"+id+"' and pid='0'");
				
				// ��δ�ظ�
				/*// ���Ƿ�ΪĬ�Ϸ�����ϵδ�޸�
				if (olddefaultsort.equals(defaultsort)) {
					String Sql = "update sys_ib_classification_system set classification_name='"
							+ name
							+ "',classification_explain='"
							+ discription
							+ "' 	where	classification_id='" + id + "'";
					di.execute(Sql);
				} else {
                    //���Ƿ�����ΪĬ�Ϸ�����ϵ�޸�Ϊ���ǡ�
					if (defaultsort.equals("1")) {
						// �����ݱ���û������ΪĬ�Ϸ�����ϵ������
						if (is_exist_defaultsort_id.equals("notexist")) {
							// ��������
							String Sql = "update sys_ib_classification_system set classification_name='"
									+ name
									+ "',if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'";
							di.execute(Sql);
						}else {
							// ����
							// ��������
							di.executeQuery("update sys_ib_classification_system set classification_name='"
									+ name
									+ "',if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'");
							// ��ԭ�ȵ�Ĭ�Ϸ�����ϵ����Ϊ��
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
		
		
		// ������δ�޸ģ�������ϵ��ʶ�����޸�
		if (oldname.equals(name) && !id.equals(identifier)) {
			List<Map> listidentifier = (List<Map>) di
					.executeQuery("select * from sys_ib_classification_system where classification_id='"
							+ identifier + "' ");
			// ���µķ�����ϵ�������ظ�
			if (listidentifier.size() > 0) {
				result = "��ʶ���ظ�������������";
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
				// ��δ�ظ�
				/*// ���Ƿ�ΪĬ�Ϸ�����ϵδ�޸�
				if (olddefaultsort.equals(defaultsort)) {
					String Sql = "update sys_ib_classification_system set classification_id='"
							+ identifier
							+ "',classification_explain='"
							+ discription
							+ "' 	where	classification_id='" + id + "'";
					di.execute(Sql);
				} else {
					//���Ƿ�����ΪĬ�Ϸ�����ϵ�޸�Ϊ���ǡ�
					if (defaultsort.equals("1")) {
						// �����ݱ���û������ΪĬ�Ϸ�����ϵ������
						if (is_exist_defaultsort_id.equals("notexist")) {
							// ��������
							String Sql = "update sys_ib_classification_system set classification_id='"
									+ identifier
									+ "',if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'";
							di.execute(Sql);
						}else {
							// ����
							// ��������
							di.executeQuery("update sys_ib_classification_system set classification_id='"
									+ identifier
									+ "',if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'");
							// ��ԭ�ȵ�Ĭ�Ϸ�����ϵ����Ϊ��
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
		// �����ƺͱ�ʶ����δ�޸�
		if (oldname.equals(name) && id.equals(identifier)) {
			/*	// ���Ƿ�ΪĬ�Ϸ�����ϵδ�޸�
				if (olddefaultsort.equals(defaultsort)) {
					String Sql = "update sys_ib_classification_system set classification_explain='"
							+ discription
							+ "' 	where	classification_id='" + id + "'";
					di.execute(Sql);
				} else {
					//���Ƿ�����ΪĬ�Ϸ�����ϵ�޸�Ϊ���ǡ�
					if (defaultsort.equals("1")) {
						// �����ݱ���û������ΪĬ�Ϸ�����ϵ������
						if (is_exist_defaultsort_id.equals("notexist")) {
							// ��������
							String Sql = "update sys_ib_classification_system set if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'";
							di.execute(Sql);
						}else {
							// ����
							// ��������
							di.executeQuery("update sys_ib_classification_system set if_default_classification="
									+ defaultsort
									+ ",classification_explain='"
									+ discription
									+ "' 	where	classification_id='" + id + "'");
							// ��ԭ�ȵ�Ĭ�Ϸ�����ϵ����Ϊ��
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
			result = "�޸ĳɹ�";
			return result;
		}
	
	
	/**
	 * ��������з�����ϵID��ȡ
	 */
	public String GetSortId(String SortName){
		List<Map> list = (List<Map>) di
				.executeQuery("select * from sys_ib_classification_system where classification_name='"
						+ SortName + "' ");
		String SortId = (String) list.get(0).get("classification_id");
		return SortId;
	}
	
	
	/**
	 * ������ϵѡ��
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
	 * ����������������ݻ�ȡ
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
	 * <p>���ƣ�sortTreeOperateActionIfRootId</p>
	 * <p>˵����������ϵ�������жϽڵ��Ƿ�Ϊ���ڵ�TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
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
	 * <p>���ƣ�print</p>
	 * <p>˵������ǰ̨���ؽ��TODO(������һ�仰�����������������)</p>
	 * <p>������@param string
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
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
	
