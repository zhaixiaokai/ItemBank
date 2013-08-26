package net.ib.util.service;
//添加前置节点 |  添加后置节点|  修改节点 |  删除节点 |  剪切节点 |  前置粘帖 |  后置粘帖 |  粘帖为子节点|  添加子节点
//newId,newText,newPid,newSNO,newPath,newName,newSeries,newExplain,newLeaf
public interface TreeOperate {
	public	boolean	addPreviousNode(String	oldNodeId,String	newNodeText,String	newNodeExplain);
	public	boolean	addAfterNode(String	oldNodeId,String	newNodeText,String	newNodeExplain);
	public	boolean	addChildNode(String	oldNodeId,String	newNodeText,String	newNodeExplain);
	public	boolean	deleteNode(String	delNodeID);
	public	boolean	modifyNode(String oldNodeId,String	newNodeText,String newNodeExplain);
	public	boolean	pasteAsPreviousNode(String sourceNodeId,String TargetNodeId);
	public	boolean	pasteAsAfterNode(String	sourceNodeId,String TargetNodeId);
	public	boolean	pasteAsChildNode(String	sourceNodeId,String TargetNodeId);
	public	void	setTableName(String	TableName);
	public	void	setTreeName(String	TreeName);
}
