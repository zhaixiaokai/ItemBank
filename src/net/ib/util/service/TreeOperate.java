package net.ib.util.service;
//���ǰ�ýڵ� |  ��Ӻ��ýڵ�|  �޸Ľڵ� |  ɾ���ڵ� |  ���нڵ� |  ǰ��ճ�� |  ����ճ�� |  ճ��Ϊ�ӽڵ�|  ����ӽڵ�
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
