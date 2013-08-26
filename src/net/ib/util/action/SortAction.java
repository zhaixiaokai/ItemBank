package net.ib.util.action;
import com.opensymphony.xwork2.ActionSupport;
import net.ib.util.service.SortService;
import org.apache.log4j.Logger;
public class SortAction extends ActionSupport{
	private String name;  //分类体系标识符
	private String SortName;  //分类体系ID，分类管理中显示分类体系树时使用
	private String deleteid;//删除分类体系id
	private String modifyid;//修改分类体系id
	private String identifier;  //分类体系标识符
	private String discription; //分类体系说明
	private String newname; //分类体系标识符(修改分类体系)
	private String newidentifier; //分类体系标识符(修改分类体系)
	private String newdiscription; //分类体系说明(修改分类体系)
	private String message;    //数据合法性检查action传参
	private String TableName;    //分类管理生成树数据获取时表名
	private String TreeId;    //分类管理中分类体系ID
	private SortService SortAction ; //定义实现类
	private String sortNodeId ; //分类体系树管理判断节点是否为根节点
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
		//转义n→n2   \n→n1
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
		//转义n→n2   \n→n1
		this.discription=discription.replaceAll("n", "n2");
		this.discription=discription.replaceAll("\n", "n1");
		logger.debug("discription:"+this.discription);
	}
	
	/**
	 * 
	 * <p>名称：addSort</p>
	 * <p>说明：添加分类体系</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SortAdd(){
		//调用实现类
		/*SortService SortAddAction=new  SortServiceImpl();*/ //方式一
		/*SortService SortAddAction=(SortService)BeanLoader.getBean("SortServiceImpl");*/   //方式二
		logger.debug(discription);
		message=SortAction.SortAdd(name,identifier,discription);    //方式三
		return "message";
	}
	/**
	 * 
	 * <p>名称：SortDelete</p>
	 * <p>说明：删除分类体系</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	
	public String SortDelete(){
		message=SortAction.SortDelete(deleteid);   
		return "message";
	}
	
	
	/**
	 * 
	 * <p>名称：SortBulkDelete</p>
	 * <p>说明：批量删除分类体系</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	
	public String SortBulkDelete(){
		message=SortAction.SortBulkDelete(deleteid);  
		return "message";
	}
	
	
	
/**
 * 
 * <p>名称：SortModify</p>
 * <p>说明：修改分类体系</p>
 * <p>参数：@return 设定文件</p>
 * <p>返回值：String 返回类型</p>
 * <p>@return</p>
 */
public String SortModify(){
	message=SortAction.SortModify(modifyid,newname,newidentifier,newdiscription);  
	return "message";
}



/**
 * 分类管理中分类体系选择
 */
public String SortSelect(){
	SortAction.SortSelect();  
	return null;
	//return message;
}


/**
 * 分类体系ID获取
 */
public String GetSortId(){
	message=SortAction.GetSortId(SortName);    
	return "message";
}


/**
 * 分类管理生成树数据获取
 */

public String SortManageDataSource(){
	SortAction.SortManageDataSource(TableName,TreeId);   
	return null;
}

/**
 * 
 * <p>名称：sortTreeOperateActionIfRootId</p>
 * <p>说明：判断选中节点是否为根节点</p>
 * <p>参数：@return 设定文件</p>
 * <p>返回值：String 返回类型</p>
 * <p>@return</p>
 */

public String sortTreeOperateActionIfRootId(){
	SortAction.sortTreeOperateActionIfRootId(sortNodeId);   
	return null;
}

}





