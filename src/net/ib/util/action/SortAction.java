package net.ib.util.action;
import com.opensymphony.xwork2.ActionSupport;
import net.ib.util.service.SortService;
import org.apache.log4j.Logger;
public class SortAction extends ActionSupport{
	private String name;  //������ϵ��ʶ��
	private String SortName;  //������ϵID�������������ʾ������ϵ��ʱʹ��
	private String deleteid;//ɾ��������ϵid
	private String modifyid;//�޸ķ�����ϵid
	private String identifier;  //������ϵ��ʶ��
	private String discription; //������ϵ˵��
	private String newname; //������ϵ��ʶ��(�޸ķ�����ϵ)
	private String newidentifier; //������ϵ��ʶ��(�޸ķ�����ϵ)
	private String newdiscription; //������ϵ˵��(�޸ķ�����ϵ)
	private String message;    //���ݺϷ��Լ��action����
	private String TableName;    //����������������ݻ�ȡʱ����
	private String TreeId;    //��������з�����ϵID
	private SortService SortAction ; //����ʵ����
	private String sortNodeId ; //������ϵ�������жϽڵ��Ƿ�Ϊ���ڵ�
	public String getSortNodeId() {
		return sortNodeId;
	}
	public void setSortNodeId(String sortNodeId) {
		this.sortNodeId = sortNodeId;
	}
	public SortService getSortAction() {
		return SortAction;
	}
	public void setSortAction(SortService sortAction) {
		SortAction = sortAction;
	}


	private static Logger logger = Logger.getLogger(SortAction.class);
	public String getTableName() {
		return TableName;
	}
	public void setTableName(String tableName) {
		TableName = tableName;
	}
	public String getSortName() {
		return SortName;
	}
	public void setSortName(String sortName) {
		SortName = sortName;
	}
	
	public String getTreeId() {
		return TreeId;
	}
	public void setTreeId(String treeId) {
		TreeId = treeId;
	}
	public static Logger getLogger() {
		return logger;
	}
	

	public String getModifyid() {
		return modifyid;
	}
	public void setModifyid(String modifyid) {
		this.modifyid = modifyid;
	}
	public String getDeleteid() {
		return deleteid;
	}
	public void setDeleteid(String deleteid) {
		this.deleteid = deleteid;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
	public String getNewidentifier() {
		return newidentifier;
	}
	public void setNewidentifier(String newidentifier) {
		this.newidentifier = newidentifier;
	}
	public String getNewdiscription() {
		return newdiscription;
	}
	public void setNewdiscription(String newdiscription) {
		this.newdiscription = newdiscription;
		//ת��n��n2   \n��n1
		this.newdiscription=newdiscription.replaceAll("n", "n2");
		this.newdiscription=newdiscription.replaceAll("\n", "n1");
		logger.debug("newdiscription:"+this.newdiscription);
	}

	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
		//ת��n��n2   \n��n1
		this.discription=discription.replaceAll("n", "n2");
		this.discription=discription.replaceAll("\n", "n1");
		logger.debug("discription:"+this.discription);
	}
	
	/**
	 * 
	 * <p>���ƣ�addSort</p>
	 * <p>˵������ӷ�����ϵ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String SortAdd(){
		//����ʵ����
		/*SortService SortAddAction=new  SortServiceImpl();*/ //��ʽһ
		/*SortService SortAddAction=(SortService)BeanLoader.getBean("SortServiceImpl");*/   //��ʽ��
		logger.debug(discription);
		message=SortAction.SortAdd(name,identifier,discription);    //��ʽ��
		return "message";
	}
	/**
	 * 
	 * <p>���ƣ�SortDelete</p>
	 * <p>˵����ɾ��������ϵ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	
	public String SortDelete(){
		message=SortAction.SortDelete(deleteid);   
		return "message";
	}
	
	
	/**
	 * 
	 * <p>���ƣ�SortBulkDelete</p>
	 * <p>˵��������ɾ��������ϵ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	
	public String SortBulkDelete(){
		message=SortAction.SortBulkDelete(deleteid);  
		return "message";
	}
	
	
	
/**
 * 
 * <p>���ƣ�SortModify</p>
 * <p>˵�����޸ķ�����ϵ</p>
 * <p>������@return �趨�ļ�</p>
 * <p>����ֵ��String ��������</p>
 * <p>@return</p>
 */
public String SortModify(){
	message=SortAction.SortModify(modifyid,newname,newidentifier,newdiscription);  
	return "message";
}



/**
 * ��������з�����ϵѡ��
 */
public String SortSelect(){
	SortAction.SortSelect();  
	return null;
	//return message;
}


/**
 * ������ϵID��ȡ
 */
public String GetSortId(){
	message=SortAction.GetSortId(SortName);    
	return "message";
}


/**
 * ����������������ݻ�ȡ
 */

public String SortManageDataSource(){
	SortAction.SortManageDataSource(TableName,TreeId);   
	return null;
}

/**
 * 
 * <p>���ƣ�sortTreeOperateActionIfRootId</p>
 * <p>˵�����ж�ѡ�нڵ��Ƿ�Ϊ���ڵ�</p>
 * <p>������@return �趨�ļ�</p>
 * <p>����ֵ��String ��������</p>
 * <p>@return</p>
 */

public String sortTreeOperateActionIfRootId(){
	SortAction.sortTreeOperateActionIfRootId(sortNodeId);   
	return null;
}

}





