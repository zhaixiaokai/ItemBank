package	net.ib.util.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import freemarker.core.ReturnInstruction.Return;

import net.ib.util.dao.DaoImpl;
import net.ib.util.service.TreeOperate;
/*
 * author	:	xiaokai
 * date		:	20121120
 * function :	�����ڵ���в���
 * explain	:	ʹ�ø÷������ȵ���setTableName��setTreeName�����������õ�ǰ�����������ڵı��Լ�����tree_id
 * 
*/
public	class	TreeOperateImpl implements TreeOperate	{
	String	TableName	=	null;
	String	TreeName	=	null;
	private static Logger logger = Logger.getLogger(TreeOperateImpl.class);
	@Override
	public void setTableName(String TableName) {
		// TODO Auto-generated method stub
		this.TableName	=	TableName;
	}
	@Override
	public void setTreeName(String TreeName) {
		// TODO Auto-generated method stub
		this.TreeName	=	TreeName;
	}
	@SuppressWarnings("unchecked")
	@Override
/*
 * author	:	xiaokai
 * function	:	���ǰ�ýڵ�
 * Parameter:	oldNodeId-----------------------��ǰ�����ڵ�Id������IDΪ�ò����Ľڵ�ǰ�����µĽڵ�
 * 				newNodeText---------------------��ǰ��Ҫ��ӵĽڵ�����
 * 				newNodeExplain------------------��ǰ��Ҫ��ӵĽڵ㱸ע
*/	
	public boolean addPreviousNode(String oldNodeId, String newNodeText,
			String newNodeExplain) {
		// TODO Auto-generated method stub
		//��ȡ��ǰĿ��ڵ����Ϣ
		String	getOldNodeSql="select * from "+this.TableName+" where id='"+oldNodeId+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		///oldNodeInfo�����洢��ǰ�����ڵ��ԭʼ��Ϣ
		List<Map> oldNodeInfo =di.executeQuery(getOldNodeSql);
		logger.debug(oldNodeInfo);
		String currentNodePath	=	(String) oldNodeInfo.get(0).get("node_path");
		String	currentNodeSeries	=	(String) oldNodeInfo.get(0).get("node_series");
		String	currentNodeSNO	=	(String) oldNodeInfo.get(0).get("sno");
		String	currentNodeTreeId	=	(String) oldNodeInfo.get(0).get("tree_id");
		String	currentNodePid	=	(String) oldNodeInfo.get(0).get("pid");
		TypeChange	tc	=	new	TypeChange();
		
		if(currentNodePid.equals("0")||currentNodePid.equals("root")){
			return false;
		}
		else{
		int	currentLevel	=	tc.stringToInt(currentNodeSeries);//��ǰ�ڵ㼶��
		int	currentSNO	=	tc.stringToInt(currentNodeSNO);
		
		
		//��ȡ��ǰ�ڵ�ͬ���ڵ��Լ�����ͬ���ڵ������ڵ�
		String	searchStr	=	currentNodePath.substring(0, currentNodePath.length()-4);
		String	sql	=	"select * from "+this.TableName+" where tree_id='"+
						this.TreeName+"'"+" and node_path like '"+searchStr+"%'  order by node_path asc";
		@SuppressWarnings("rawtypes")
		//allNodeInfo�����洢��ǰ�ڵ������ͬ���ڵ��Լ�����ͬ���ڵ������ڵ�
		List<Map> allNodeInfo	=	di.executeQuery(sql);
		logger.debug(allNodeInfo);
		boolean	flag	=	false;			//��ʾ�ҵ���ʼ�����ڵ��λ��
		for(int i=0;i<allNodeInfo.size();i++){
			//��ǰ�����Ľڵ��ID��Ŀ��ڵ��id��ͬ
			if(allNodeInfo.get(i).get("id").equals(oldNodeId)){
				flag	=	true;
			}
			if(flag){
				//��ӽڵ�ĵȼ��뵱ǰ�ڵ�ļ�����ͬ���ҵ�ǰ�ڵ�sno���ڵ�����ӽڵ�Ŀ��ڵ��sno
				if(currentNodeSeries.equals((String)allNodeInfo.get(i).get("node_series"))	&&	
						currentSNO<=tc.stringToInt((String)allNodeInfo.get(i).get("sno"))){
					allNodeInfo.get(i).put("sno", tc.stringToInt((String)allNodeInfo.get(i).get("sno"))+1);
				}
				String	nodePath	=	(String) allNodeInfo.get(i).get("node_path");
				String	subPath	=	nodePath.substring((currentLevel-1)*5,(currentLevel)*5-1);
				int	numSubPath	=	tc.stringToInt(subPath)+1;
				String	newPath	=	"";
				//��Ŀ��·��������λ
				if(numSubPath<10){
					subPath	=	"000"+tc.intToString(numSubPath);
				}
				else if(numSubPath<100){
					subPath	=	"00"+tc.intToString(numSubPath);
				}
				else if(numSubPath<1000){
					subPath	=	"0"+tc.intToString(numSubPath);
				}
				else if(numSubPath<10000){
					subPath	=	tc.intToString(numSubPath);
				}
				newPath	=	nodePath.substring(0,5*(currentLevel-1))+subPath+nodePath.substring(5*currentLevel-1);
				logger.debug(newPath);
				allNodeInfo.get(i).put("node_path", newPath);
				String	updateSql	=	"update	"+this.TableName+" set node_path='"+allNodeInfo.get(i).get("node_path")+
						"',sno='"+allNodeInfo.get(i).get("sno")+"' where id='"+allNodeInfo.get(i).get("id")+"'";
				logger.debug(updateSql);
				if(0==di.execute(updateSql))
					return false;
			}
		}
		
		//���һ����¼
		String insertSQL = "insert	into "
				+ TableName
				+ " (TREE_ID, TEXT, PID, SNO, NODE_PATH, NODE_SERIES,node_explain) values ('"
				+ currentNodeTreeId + "', '" + newNodeText
				+ "', '" + currentNodePid + "', '" + currentNodeSNO + "', '" + currentNodePath
				+ "', '" + currentNodeSeries + "', '" + newNodeExplain + "')";
		
		if(0==di.execute(insertSQL))
			return false;
		
		logger.debug(insertSQL);
		return true;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
/*
 * author	:	xiaokai
 * function	:	��Ӻ��ýڵ�
 * Parameter��	oldNodeId-----------------------��ǰ�����ڵ�Id����Ҫ�ڽڵ�IdΪ�ò����Ľڵ����ӽڵ�
 * 				newNodeText---------------------��Ҫ��ӽڵ������
 * 				newNodeExplain------------------��Ҫ��ӽڵ�ı�ע����
*/
	public boolean addAfterNode(String oldNodeId, String newNodeText,
			String newNodeExplain) {
		// TODO Auto-generated method stub
		//��ȡ��ǰĿ��ڵ����Ϣ
		String	getOldNodeSql="select * from "+this.TableName+" where id='"+oldNodeId+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		//oldNodeInfo�����洢��ǰ�����ڵ��ԭʼ��Ϣ
		List<Map> oldNodeInfo =di.executeQuery(getOldNodeSql);
		logger.debug(oldNodeInfo);
		String currentNodePath	=	(String) oldNodeInfo.get(0).get("node_path");
		String	currentNodeSeries	=	(String) oldNodeInfo.get(0).get("node_series");
		String	currentNodeSNO	=	(String) oldNodeInfo.get(0).get("sno");
		String	currentNodeTreeId	=	(String) oldNodeInfo.get(0).get("tree_id");
		String	currentNodePid	=	(String) oldNodeInfo.get(0).get("pid");
		TypeChange	tc	=	new	TypeChange();
		//�Ը��ڵ�����⴦��
		if(currentNodePid.equals("0")||currentNodePid.equals("root")){
		
				return false;
			
		}
		else{
		int	currentLevel	=	tc.stringToInt(currentNodeSeries);//��ǰ�ڵ㼶��
		int	currentSNO	=	tc.stringToInt(currentNodeSNO);
		
		//��ȡ��ǰ�ڵ�ͬ���ڵ��Լ�����ͬ���ڵ������ڵ�
		String	searchStr	=	currentNodePath.substring(0, currentNodePath.length()-4);
		String	sql	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+"'"+
						" and node_path like '"+searchStr+"%'  order by node_path asc";
		@SuppressWarnings("rawtypes")
		//allNodeInfo�����洢��ǰ�ڵ������ͬ���ڵ��Լ�ͬ���ڵ����������ڵ�
		List<Map> allNodeInfo	=	di.executeQuery(sql);
		logger.debug(allNodeInfo);
		boolean	getCurrentOperateFlag	=	false;
		boolean	flag	=	false;	
		
		for(int i=0;i<allNodeInfo.size();i++){
			if(allNodeInfo.get(i).get("id").equals(oldNodeId)){
				//��ʾ�ҵ���ʼ�����ڵ��λ��
				getCurrentOperateFlag	=	true;			
				continue;
			}
			if(getCurrentOperateFlag&&flag==false){
				//�ҵ���ǰ�ڵ��ͬ���ڵ��У��ýڵ����һ��ͬ���ڵ�
				if(allNodeInfo.get(i).get("node_series").equals(currentNodeSeries)){
					flag	=	true;
				}
			}
			if(flag){
				//��ӽڵ�ĵȼ��뵱ǰ�ڵ�ļ�����ͬ���ҵ�ǰ�ڵ�sno���ڵ�����ӽڵ�Ŀ��ڵ��sno
				if(currentNodeSeries.equals((String)allNodeInfo.get(i).get("node_series"))	&&	
						currentSNO<tc.stringToInt((String)allNodeInfo.get(i).get("sno"))){
					allNodeInfo.get(i).put("sno", tc.stringToInt((String)allNodeInfo.get(i).get("sno"))+1);
					logger.debug(allNodeInfo);
				}
				String	nodePath	=	(String) allNodeInfo.get(i).get("node_path");
				String	subPath	=	nodePath.substring((currentLevel-1)*5,(currentLevel)*5-1);
				int	numSubPath	=	tc.stringToInt(subPath)+1;
				String	newPath	=	"";
				if(numSubPath<10){
					subPath	=	"000"+tc.intToString(numSubPath);
				}
				else if(numSubPath<100){
					subPath	=	"00"+tc.intToString(numSubPath);
				}
				else if(numSubPath<1000){
					subPath	=	"0"+tc.intToString(numSubPath);
				}
				else if(numSubPath<10000){
					subPath	=	tc.intToString(numSubPath);
				}
				newPath	=	nodePath.substring(0,5*(currentLevel-1))+subPath+nodePath.substring(5*currentLevel-1);
				logger.debug(newPath);
				allNodeInfo.get(i).put("node_path", newPath);
				String	updateSql	=	"update	"+this.TableName+" set node_path='"+allNodeInfo.get(i).get("node_path")+
						"',sno='"+allNodeInfo.get(i).get("sno")+"' where id='"+allNodeInfo.get(i).get("id")+"'";
				logger.debug(updateSql);
				di.execute(updateSql);
			}
			
		}
		String	subPath	=	currentNodePath.substring(currentNodePath.length()-4);
		int	newSNO	=	tc.stringToInt(subPath)+1;
		if(newSNO<10){
			subPath	=	"000"+tc.intToString(newSNO);
		}
		else if(newSNO<100){
			subPath	=	"00"+tc.intToString(newSNO);
		}
		else if(newSNO<1000){
			subPath	=	"0"+tc.intToString(newSNO);
		}
		else if(newSNO<10000){
			subPath	=	tc.intToString(newSNO);
		}
		String	newNodePath	=	currentNodePath.substring(0,currentNodePath.length()-4)+subPath;
		//���һ����¼
		String insertSQL = "insert	into "
				+ TableName
				+ " (TREE_ID, TEXT, PID, SNO, NODE_PATH, NODE_SERIES,node_explain) values ('"
				+ currentNodeTreeId + "', '" + newNodeText
				+ "', '" + currentNodePid + "', '" +( tc.stringToInt(currentNodeSNO)+1) + "', '" + newNodePath
				+ "', '" + currentNodeSeries + "', '"+ newNodeExplain + "')";
		
		if(0==di.execute(insertSQL))
			return false;
		logger.debug(insertSQL);
		return true;
		}
	}
		
	
	@Override
/*
 * author	:	xiaokai
 * function	:	Ϊ��ǰ�ڵ�����ӽڵ�
 * Parameter��	oldNodeId-----------------------��ǰ�����ڵ�Id����Ҫ�ڽڵ�IdΪ�ò����Ľڵ����ӽڵ�
 * 				newNodeText---------------------��Ҫ��ӽڵ������
 * 				newNodeExplain------------------��Ҫ��ӽڵ�ı�ע����
*/
	public boolean addChildNode(String oldNodeId, String newNodeText,
			String newNodeExplain) {
		// TODO Auto-generated method stub
		//��ȡ��ǰĿ��ڵ����Ϣ
		String	getOldNodeSql="select * from "+this.TableName+" where id='"+oldNodeId+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		//oldNodeInfo�����洢Ŀ��ڵ��ԭʼ��Ϣ
		List<Map> oldNodeInfo =di.executeQuery(getOldNodeSql);
		logger.debug(oldNodeInfo);
		String currentNodePath	=	(String) oldNodeInfo.get(0).get("node_path");
		String	currentNodeSeries	=	(String) oldNodeInfo.get(0).get("node_series");
		String	currentNodeTreeId	=	(String) oldNodeInfo.get(0).get("tree_id");
		String	currentNodePid	=	(String) oldNodeInfo.get(0).get("id");			//����ӽڵ��pidΪ��ǰ�ڵ��id
		TypeChange	tc	=	new	TypeChange();
		
		//��ȡ��ǰ�ӽڵ�
		String	sql	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+"'"+
						" and node_path like '"+currentNodePath+".%'  order by node_path asc";
		logger.debug(sql);
		
		@SuppressWarnings("rawtypes")
		//allNodeInfo�����洢��ǰ�ڵ��ӽڵ����Ϣ
		List<Map> allNodeInfo	=	di.executeQuery(sql);
		
		int	childNodesCount	=	allNodeInfo.size()+1;
		String	subPath	=	"";
		if(childNodesCount<10){
			subPath	=	"000"+tc.intToString(childNodesCount);
		}
		else if(childNodesCount<100){
			subPath	=	"00"+tc.intToString(childNodesCount);
		}
		else if(childNodesCount<1000){
			subPath	=	"0"+tc.intToString(childNodesCount);
		}
		else if(childNodesCount<10000){
			subPath	=	tc.intToString(childNodesCount);
		}
		String	newNodePath	=	currentNodePath+"."+subPath;
		String insertSQL = "insert	into "
				+ TableName
				+ " (TREE_ID, TEXT, PID, SNO, NODE_PATH, NODE_SERIES,node_explain) values ('"
				+ currentNodeTreeId + "', '" + newNodeText
				+ "', '" + currentNodePid + "', '" + childNodesCount + "', '" + newNodePath
				+ "', '" + (tc.stringToInt(currentNodeSeries)+1) + "', '" + newNodeExplain + "')";
		logger.debug(insertSQL);
		
		if(0==di.execute(insertSQL))
			return false;
		
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
/*
 * author	:	xiaokai
 * function	:	ɾ���ڵ�
 * Parameter:	delNodeIdΪ��ǰ������Ҫɾ���Ľڵ�Id	
*/	
	public boolean deleteNode(String delNodeID) {
		// TODO Auto-generated method stub
		//��ȡ��ǰĿ��ڵ����Ϣ
		String	getOldNodeSql="select * from "+this.TableName+" where id='"+delNodeID+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		List<Map> oldNodeInfo =di.executeQuery(getOldNodeSql);
		logger.debug(oldNodeInfo);
		String currentNodePath	=	(String) oldNodeInfo.get(0).get("node_path");
		String	strCurrentLevel	=	(String) oldNodeInfo.get(0).get("node_series");
		String	currentNodePid	=	(String) oldNodeInfo.get(0).get("pid");
		TypeChange	tc	=	new	TypeChange();
		//���ڸ��ڵ�������⴦��
		if(currentNodePid.equals("0")||currentNodePid.equals("root")){
			return false;
		}
		else{
		int	currentLevel	=	tc.stringToInt(strCurrentLevel);
		String	searchStr	=	currentNodePath.substring(0, currentNodePath.length()-4);
		String	sql	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+"'"
						+" and node_path like '"+searchStr+"%'  order by node_path asc";
		@SuppressWarnings("rawtypes")
		List<Map> allNodeInfo	=	di.executeQuery(sql);
		//boolean	findTargetFlag	=	false;
		logger.debug(allNodeInfo);
		boolean	startDeleteFlag	=	false;//��ʾ�Ƿ��ҵ�ɾ����ʼλ
		boolean	endDeleteFlag	=	false;	//��ʾ�Ƿ��ҵ�ɾ������λ
		for(int	i=0;i<allNodeInfo.size();i++){
			if(!startDeleteFlag){
				if(delNodeID.equals(allNodeInfo.get(i).get("id"))){
					startDeleteFlag	=	true;
					String	deleteNodeSql	=	"delete from "+this.TableName+" where id='"+allNodeInfo.get(i).get("id")+"'";
					logger.debug(deleteNodeSql);
					if(0==di.execute(deleteNodeSql))
						return false;
				}
			}
			else{
				if(strCurrentLevel.equals(allNodeInfo.get(i).get("node_series"))){
					endDeleteFlag	=	true;
				}
				if(!endDeleteFlag){//���ɾ��λ���ҵ�����ʼɾ�������ж��Ƿ����ǰ�ڵ㼶����ͬ�������ֹͬͣɾ��
					String	deleteNodeSql	=	"delete from "+this.TableName+" where id='"+allNodeInfo.get(i).get("id")+"'";
					
					logger.debug(deleteNodeSql);
					if(0==di.execute(deleteNodeSql))
						return false;
				}
				if(endDeleteFlag){
					String	nodePath	=	(String) allNodeInfo.get(i).get("node_path");
					String	subPath	=	nodePath.substring((currentLevel-1)*5,(currentLevel)*5-1);
					int	numSubPath	=	tc.stringToInt(subPath)-1;
					if(numSubPath<10){
						subPath	=	"000"+tc.intToString(numSubPath);
					}
					else if(numSubPath<100){
						subPath	=	"00"+tc.intToString(numSubPath);
					}
					else if(numSubPath<1000){
						subPath	=	"0"+tc.intToString(numSubPath);
					}
					else if(numSubPath<10000){
						subPath	=	tc.intToString(numSubPath);
					}
					String	newPath	=	nodePath.substring(0,5*(currentLevel-1))+subPath+nodePath.substring(5*currentLevel-1);
					allNodeInfo.get(i).put("node_path", newPath);
					numSubPath	=	tc.stringToInt(newPath.substring(newPath.length()-4));
					allNodeInfo.get(i).put("sno",numSubPath);
					String	sqlUpdate	=	"update "+this.TableName+" set sno='"+allNodeInfo.get(i).get("sno")+
							"',node_path='"+allNodeInfo.get(i).get("node_path")+"' where id='"+allNodeInfo.get(i).get("id")+"'";
					logger.debug(sqlUpdate);
					if(0==di.execute(sqlUpdate))
						return false;
				}
			}
		}
		return true;
		}
	}
	@Override
/*
 * author	:	xiaokai
 * function	:	�޸Ľڵ���Ϣ
 * Parameter:	oldNodeId---------------------��ǰ��Ҫ�޸ĵĽڵ�id
 * 				newNodeText-------------------�޸ĺ���²���
 * 				newNodeExplain----------------�޸ĺ���½ڵ�˵��
*/	
	public boolean modifyNode(String oldNodeId, String newNodeText,
			String newNodeExplain) {
		// TODO Auto-generated method stub
			String updateSql = "UPDATE " + TableName + " SET "+"text='" + newNodeText + "',"+"node_explain='" + newNodeExplain + "'"
					+ " WHERE id = '" + oldNodeId + "'";
					logger.debug(updateSql);
					DaoImpl di	=	new DaoImpl();
					if(0==di.execute(updateSql))
						return false;
					return true;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
/*
 * author	:	xiaokai
 * function	:	ճ��Ϊǰ�ýڵ�
 * Parameter:	sourceNodeId-------------------Դ�ڵ�ID
 * 				TargetNodeId-------------------Ŀ��ڵ�id	
*/	
	public boolean pasteAsPreviousNode(String sourceNodeId, String TargetNodeId) {
		// TODO Auto-generated method stub
		String	getNodeSql="select * from "+this.TableName+" where id='"+sourceNodeId+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		//sourceNodeInfo�����洢Դ�ڵ����Ϣ
		List<Map> sourceNodeInfo =di.executeQuery(getNodeSql);
		TypeChange	tc	=	new	TypeChange();
		String	str_sourceNodePath	=	(String) sourceNodeInfo.get(0).get("node_path");
		String	str_sourceNodeLevel	=	(String) sourceNodeInfo.get(0).get("node_series");
		String	currentNodePid	=	(String) sourceNodeInfo.get(0).get("pid");
		//���ڸ��ڵ�������⴦��
		if(currentNodePid.equals("0")||currentNodePid.equals("root")){
			return false;
		}
		else{
		int		num_sourceNodeLevel	=	tc.stringToInt(str_sourceNodeLevel);
	
		
		//��ȡԴ�ڵ��ӽڵ�			SourceNodeChildren
		String	sqlGetNodeAndChildren	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+
				"' and node_path like '"+str_sourceNodePath+".%'";
		@SuppressWarnings("rawtypes")
		//SourceNodeChildren�����洢Դ�ڵ���ӽڵ���Ϣ
		List<Map> SourceNodeChildren =di.executeQuery(sqlGetNodeAndChildren);		//Դ�ڵ��ӽڵ�	
		//�ڸ��½ڵ�֮ǰ��Դ�ڵ�֮��ͬ���ڵ��Լ����ӽڵ�λ�õ�����ǰ��
		//��ȡͬ���ڵ㼰��ͬ���ڵ������ڵ�
		String	sqlGetSameLevelNodes	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+
				"' and node_path like '"+str_sourceNodePath.substring(0,str_sourceNodePath.length()-4)+"%' order by node_path asc";
		logger.debug(sqlGetSameLevelNodes);
		@SuppressWarnings("rawtypes")
		//SameLevelAndChildNodes�����洢��ǰ�ڵ���ӽڵ��Լ����ڵ�ͬ���ڵ������ڵ�
		List<Map> SameLevelAndChildNodes	=	di.executeQuery(sqlGetSameLevelNodes);
		//��ʾ�Ƿ��ҵ�Դ�ڵ��Լ�Դ�ڵ���ӽڵ�Ŀ�ʼλ
		boolean	startFlag	=	false;
		boolean	endFlag		=	false;
		for(int	i=0;i<SameLevelAndChildNodes.size();i++){
			//���ҵ�Դ�ڵ�ǰ�Լ��ҵ�Դ�ڵ�ʱ
			if(!startFlag){		
				//��ǰ������Դ�ڵ�
				if(sourceNodeId.equals(SameLevelAndChildNodes.get(i).get("id"))){
					startFlag	=	true;
				}
			}
			else{
				//��ǰ�ڵ���Դ�ڵ�֮��ĵ�һ��ͬ���ڵ�
				if(str_sourceNodeLevel.equals(SameLevelAndChildNodes.get(i).get("node_series"))){
					endFlag	=	true;
				}
				//�ҵ�Դ�ڵ���ӽڵ�
				if(!endFlag){
				}
				//Դ�ڵ���ͬ���ڵ��Լ�Դ�ڵ���ͬ���ڵ���ӽڵ���ǰ�ƶ�
				if(endFlag){
					String	nodePath	=	(String) SameLevelAndChildNodes.get(i).get("node_path");
					String	SameLevelSubPath	=	nodePath.substring((num_sourceNodeLevel-1)*5,(num_sourceNodeLevel)*5-1);
					int	numSubPath	=	tc.stringToInt(SameLevelSubPath)-1;
					if(numSubPath<10){
						SameLevelSubPath	=	"000"+tc.intToString(numSubPath);
					}
					else if(numSubPath<100){
						SameLevelSubPath	=	"00"+tc.intToString(numSubPath);
					}
					else if(numSubPath<1000){
						SameLevelSubPath	=	"0"+tc.intToString(numSubPath);
					}
					else if(numSubPath<10000){
						SameLevelSubPath	=	tc.intToString(numSubPath);
					}
					String	newPath	=	nodePath.substring(0,5*(num_sourceNodeLevel-1))+
							SameLevelSubPath+nodePath.substring(5*num_sourceNodeLevel-1);
					SameLevelAndChildNodes.get(i).put("node_path", newPath);
					numSubPath	=	tc.stringToInt(newPath.substring(newPath.length()-4));
					SameLevelAndChildNodes.get(i).put("sno",numSubPath);
					String	sqlUpdate	=	"update "+this.TableName+" set sno='"+SameLevelAndChildNodes.get(i).get("sno")+
											"',node_path='"+SameLevelAndChildNodes.get(i).get("node_path")+
											"' where id='"+SameLevelAndChildNodes.get(i).get("id")+"'";
					logger.debug(sqlUpdate);
					if(0==di.execute(sqlUpdate))
						return false;
				}
			}
			
		}	
		//���в�����ϣ��Ѿ���Ŀ��ڵ�֮���ͬ���ڵ��Լ�Ŀ��ڵ�֮����ӽڵ���ǰ�ƶ�
		//��ȡĿ��ڵ�
		getNodeSql="select * from "+this.TableName+" where id='"+TargetNodeId+"'";
		@SuppressWarnings("rawtypes")
		List<Map> targetNodeInfo =di.executeQuery(getNodeSql);
		
		String	str_targetNodePath	=	(String) targetNodeInfo.get(0).get("node_path");
		String	str_targetNodeSNO	=	(String) targetNodeInfo.get(0).get("sno");
		String	str_targetNodePid	=	(String) targetNodeInfo.get(0).get("pid");
		String	str_targetNodeLevel	=	(String) targetNodeInfo.get(0).get("node_series");
		int		num_targetNodeLevel				=	tc.stringToInt(str_targetNodeLevel);	
		
		//��ȡĿ��ڵ��ͬ���ڵ��Լ�����ڵ�
		String	sqlGetTargetSameLevelNodes	=	"select * from "+this.TableName+" where tree_id='"+
												this.TreeName+"' and node_path like '"+
												str_targetNodePath.substring(0,str_targetNodePath.length()-4)+
												"%' order by node_path asc";
		logger.debug(sqlGetTargetSameLevelNodes);
		@SuppressWarnings("rawtypes")
		List<Map> TargetSameLevelAndChildNodes	=	di.executeQuery(sqlGetTargetSameLevelNodes);
		boolean targetFindFlag	=	false;
		for(int	i=0;i<TargetSameLevelAndChildNodes.size();i++){
			if(!targetFindFlag){
				if(TargetNodeId.equals((String)TargetSameLevelAndChildNodes.get(i).get("id"))){
					targetFindFlag	=	true;
				}
				else{
					continue;
				}
			}
			if(targetFindFlag){
				//��Ŀ��ڵ㿪ʼ����ͬ���ڵ��Լ�����ڵ������
				String	currentPath	=	(String) TargetSameLevelAndChildNodes.get(i).get("node_path");
				String	targetSubString	=	currentPath.substring((num_targetNodeLevel-1)*5,(num_targetNodeLevel)*5-1);
				int	numSubPath	=	tc.stringToInt(targetSubString)+1;
				if(numSubPath<10){
					targetSubString	=	"000"+tc.intToString(numSubPath);
				}
				else if(numSubPath<100){
					targetSubString	=	"00"+tc.intToString(numSubPath);
				}
				else if(numSubPath<1000){
					targetSubString	=	"0"+tc.intToString(numSubPath);
				}
				else if(numSubPath<10000){
					targetSubString	=	tc.intToString(numSubPath);
				}
				String	newPath	=	currentPath.substring(0,5*(num_targetNodeLevel-1))+
						targetSubString+currentPath.substring(5*num_targetNodeLevel-1);
				TargetSameLevelAndChildNodes.get(i).put("node_path", newPath);
				numSubPath	=	tc.stringToInt(newPath.substring(newPath.length()-4));
				TargetSameLevelAndChildNodes.get(i).put("sno",numSubPath);
				String	sqlUpdate	=	"update "+this.TableName+" set sno='"+TargetSameLevelAndChildNodes.get(i).get("sno")+
						"',node_path='"+TargetSameLevelAndChildNodes.get(i).get("node_path")+
						"' where id='"+TargetSameLevelAndChildNodes.get(i).get("id")+"'";
				logger.debug(sqlUpdate);
				if(0==di.execute(sqlUpdate))
					return false;
			}
		}
		
		//����Դ�ڵ�ΪĿ��ڵ��ǰ�ýڵ�
		//Դ�ڵ��·�����º�ΪĿ��ڵ�ԭ�ȵ�·��
		String	SourceAsPreviousNodePath	=	str_targetNodePath;
		//Դ�ڵ�SNO���º�ΪĿ��ڵ�ԭ�ȵ�SNO
		String	SourceAsPreviousNodeSNO		=	str_targetNodeSNO;
		String	sqlUpdateSourceAsAfterNode	=	"update	"+this.TableName+" set pid='"+str_targetNodePid+
				"',sno='"+SourceAsPreviousNodeSNO+"',node_path='"+SourceAsPreviousNodePath+
				"',node_series='"+num_targetNodeLevel+"' where id='"+sourceNodeId+"'";
		logger.debug(sqlUpdateSourceAsAfterNode);
		if(0==di.execute(sqlUpdateSourceAsAfterNode))
			return false;
		//����Դ�ڵ�����ڵ��·���Լ�����
		for(int	i=0;i<SourceNodeChildren.size();i++){
			int childAsTargetChildNodeSeries	=	tc.stringToInt((String)SourceNodeChildren.get(i).get("node_series"))-
					num_sourceNodeLevel+num_targetNodeLevel;
			String	childAsTargetChildNodePath	=	SourceAsPreviousNodePath+"."+
					((String)SourceNodeChildren.get(i).get("node_path")).substring(5*num_sourceNodeLevel);
			
			String	sqlUpdateSourceChildAsAfterChildNode	=	"update	"+this.TableName+" set node_series='"+
			childAsTargetChildNodeSeries+"',node_path='"+childAsTargetChildNodePath+"' where id='"+SourceNodeChildren.get(i).get("id")+"'";
			logger.debug(sqlUpdateSourceChildAsAfterChildNode);
			if(0==di.execute(sqlUpdateSourceChildAsAfterChildNode))
				return false;
		}
		return true;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	/*
	 * author	:	xiaokai
	 * function	:	ճ��Ϊ���ýڵ�
	 * Parameter:	sourceNodeId-------------------Դ�ڵ�ID
	 * 				TargetNodeId-------------------Ŀ��ڵ�id	
	*/	
	public boolean pasteAsAfterNode(String sourceNodeId, String TargetNodeId) {
		// TODO Auto-generated method stub
		String	getNodeSql="select * from "+this.TableName+" where id='"+sourceNodeId+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		List<Map> sourceNodeInfo =di.executeQuery(getNodeSql);
		TypeChange	tc	=	new	TypeChange();
		String	str_sourceNodePath	=	(String) sourceNodeInfo.get(0).get("node_path");
		String	str_sourceNodeLevel	=	(String) sourceNodeInfo.get(0).get("node_series");
		String	currentNodePid	=	(String) sourceNodeInfo.get(0).get("pid");
		//���ڸ��ڵ�������⴦��
		if(currentNodePid.equals("0")||currentNodePid.equals("root")){
			return false;
		}
		else{
		int		num_sourceNodeLevel	=	tc.stringToInt(str_sourceNodeLevel);
	
		
		//��ȡԴ�ڵ��ӽڵ�			SourceNodeChildren
		String	sqlGetNodeAndChildren	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+
				"' and node_path like '"+str_sourceNodePath+".%'";
		@SuppressWarnings("rawtypes")
		List<Map> SourceNodeChildren =di.executeQuery(sqlGetNodeAndChildren);		//Դ�ڵ��ӽڵ�	
		//�ڸ��½ڵ�֮ǰ��Դ�ڵ�֮��ͬ���ڵ��Լ����ӽڵ�λ�õ�����ǰ��
		//��ȡͬ���ڵ㼰��ͬ���ڵ������ڵ�
		String	sqlGetSameLevelNodes	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+
				"' and node_path like '"+str_sourceNodePath.substring(0,str_sourceNodePath.length()-4)+"%' order by node_path asc";
		logger.debug(sqlGetSameLevelNodes);
		@SuppressWarnings("rawtypes")
		List<Map> SameLevelAndChildNodes	=	di.executeQuery(sqlGetSameLevelNodes);
		//��ʾ�Ƿ��ҵ�Դ�ڵ��Լ�Դ�ڵ���ӽڵ�Ŀ�ʼλ
		boolean	startFlag	=	false;
		boolean	endFlag		=	false;
		for(int	i=0;i<SameLevelAndChildNodes.size();i++){
			//���ҵ�Դ�ڵ�ǰ�Լ��ҵ�Դ�ڵ�ʱ
			if(!startFlag){		
				if(sourceNodeId.equals(SameLevelAndChildNodes.get(i).get("id"))){
					//��ǰ������Դ�ڵ�
					startFlag	=	true;
				}
			}
			else{
				if(str_sourceNodeLevel.equals(SameLevelAndChildNodes.get(i).get("node_series"))){
					//��ǰ�ڵ���Դ�ڵ�֮��ĵ�һ��ͬ���ڵ�
					endFlag	=	true;
				}
				if(!endFlag){
					//�ҵ�Դ�ڵ���ӽڵ�
				}
				if(endFlag){
					//Դ�ڵ���ͬ���ڵ��Լ�Դ�ڵ���ͬ���ڵ���ӽڵ�
					String	nodePath	=	(String) SameLevelAndChildNodes.get(i).get("node_path");
					String	SameLevelSubPath	=	nodePath.substring((num_sourceNodeLevel-1)*5,(num_sourceNodeLevel)*5-1);
					int	numSubPath	=	tc.stringToInt(SameLevelSubPath)-1;
					if(numSubPath<10){
						SameLevelSubPath	=	"000"+tc.intToString(numSubPath);
					}
					else if(numSubPath<100){
						SameLevelSubPath	=	"00"+tc.intToString(numSubPath);
					}
					else if(numSubPath<1000){
						SameLevelSubPath	=	"0"+tc.intToString(numSubPath);
					}
					else if(numSubPath<10000){
						SameLevelSubPath	=	tc.intToString(numSubPath);
					}
					String	newPath	=	nodePath.substring(0,5*(num_sourceNodeLevel-1))+SameLevelSubPath+
							nodePath.substring(5*num_sourceNodeLevel-1);
					SameLevelAndChildNodes.get(i).put("node_path", newPath);
					numSubPath	=	tc.stringToInt(newPath.substring(newPath.length()-4));
					SameLevelAndChildNodes.get(i).put("sno",numSubPath);
					String	sqlUpdate	=	"update "+this.TableName+" set sno='"+SameLevelAndChildNodes.get(i).get("sno")+
							"',node_path='"+SameLevelAndChildNodes.get(i).get("node_path")+"' where id='"+
							SameLevelAndChildNodes.get(i).get("id")+"'";
					logger.debug(sqlUpdate);
					if(0==di.execute(sqlUpdate))
						return false;
				}
			}
			
		}	
		//���в�����ϣ��Ѿ���Ŀ��ڵ�֮���ͬ���ڵ��Լ�Ŀ��ڵ�֮����ӽڵ���ǰ�ƶ�
		//��ȡĿ��ڵ�
		getNodeSql="select * from "+this.TableName+" where id='"+TargetNodeId+"'";
		@SuppressWarnings("rawtypes")
		List<Map> targetNodeInfo =di.executeQuery(getNodeSql);
		
		String	str_targetNodePath	=	(String) targetNodeInfo.get(0).get("node_path");
		String	str_targetNodeSNO	=	(String) targetNodeInfo.get(0).get("sno");
		String	str_targetNodePid	=	(String) targetNodeInfo.get(0).get("pid");
		String	str_targetNodeLevel	=	(String) targetNodeInfo.get(0).get("node_series");
		int		num_targetNodeSNO	=	tc.stringToInt(str_targetNodeSNO);
		int		num_targetNodeLevel				=	tc.stringToInt(str_targetNodeLevel);	
		
		//��ȡĿ��ڵ��ͬ���ڵ��Լ�����ڵ�
		String	sqlGetTargetSameLevelNodes	=	"select * from "+this.TableName+" where tree_id='"+
												this.TreeName+"' and node_path like '"+
												str_targetNodePath.substring(0,str_targetNodePath.length()-4)+
												"%' order by node_path asc";
		logger.debug(sqlGetTargetSameLevelNodes);
		@SuppressWarnings("rawtypes")
		List<Map> TargetSameLevelAndChildNodes	=	di.executeQuery(sqlGetTargetSameLevelNodes);
		boolean	targetStart	=	false;
		boolean	targetEnd	=	false;
		for(int	i=0;i<TargetSameLevelAndChildNodes.size();i++){
			if(!targetStart){		//���ҵ�Դ�ڵ�ǰ�Լ��ҵ�Դ�ڵ�ʱ
				if(TargetNodeId.equals(TargetSameLevelAndChildNodes.get(i).get("id"))){
					//��ǰ������Դ�ڵ�
					targetStart	=	true;
				}
			}
			else{
				if(str_targetNodeLevel.equals(TargetSameLevelAndChildNodes.get(i).get("node_series"))){
					//��ǰ�ڵ���Ŀ��ڵ�֮��ĵ�һ��ͬ���ڵ�
					targetEnd	=	true;
				}
				if(!targetEnd){
					//�ҵ�Ŀ��ڵ���ӽڵ�
				}
				if(targetEnd){
					//Ŀ��ڵ���ͬ���ڵ��Լ�Դ�ڵ���ͬ���ڵ���ӽڵ�
					//��Ŀ��ڵ����ӽڵ��Լ���ڵ����
					String	currentPath	=	(String) TargetSameLevelAndChildNodes.get(i).get("node_path");
					String	targetSubString	=	currentPath.substring((num_targetNodeLevel-1)*5,(num_targetNodeLevel)*5-1);
					int	numSubPath	=	tc.stringToInt(targetSubString)+1;
					if(numSubPath<10){
						targetSubString	=	"000"+tc.intToString(numSubPath);
					}
					else if(numSubPath<100){
						targetSubString	=	"00"+tc.intToString(numSubPath);
					}
					else if(numSubPath<1000){
						targetSubString	=	"0"+tc.intToString(numSubPath);
					}
					else if(numSubPath<10000){
						targetSubString	=	tc.intToString(numSubPath);
					}
					String	newPath	=	currentPath.substring(0,5*(num_targetNodeLevel-1))+
							targetSubString+currentPath.substring(5*num_targetNodeLevel-1);
					TargetSameLevelAndChildNodes.get(i).put("node_path", newPath);
					numSubPath	=	tc.stringToInt(newPath.substring(newPath.length()-4));
					TargetSameLevelAndChildNodes.get(i).put("sno",numSubPath);
					String	sqlUpdate	=	"update "+this.TableName+" set sno='"+TargetSameLevelAndChildNodes.get(i).get("sno")+
							"',node_path='"+TargetSameLevelAndChildNodes.get(i).get("node_path")+"' where id='"+
							TargetSameLevelAndChildNodes.get(i).get("id")+"'";
					logger.debug(sqlUpdate);
					if(0==di.execute(sqlUpdate))
						return false;
				}
			}
		}
		//����Դ�ڵ�ΪĿ��ڵ�ĺ��ýڵ�
		String	SourceAsAfterNodePath	=	str_targetNodePath.substring(0,str_targetNodePath.length()-4);
		int	numSubPath	=	(num_targetNodeSNO+1);
		String	subPath	=	"";
		if(numSubPath<10){
			subPath	=	"000"+tc.intToString(numSubPath);
		}
		else if(numSubPath<100){
			subPath	=	"00"+tc.intToString(numSubPath);
		}
		else if(numSubPath<1000){
			subPath	=	"0"+tc.intToString(numSubPath);
		}
		else if(numSubPath<10000){
			subPath	=	tc.intToString(numSubPath);
		}
		SourceAsAfterNodePath+=subPath;
		String	sqlUpdateSourceAsAfterNode	=	"update	"+this.TableName+" set pid='"+str_targetNodePid+"',sno='"+
												numSubPath+"',node_path='"+SourceAsAfterNodePath+"',node_series='"+
												num_targetNodeLevel+"' where id='"+sourceNodeId+"'";
		logger.debug(sqlUpdateSourceAsAfterNode);
		if(0==di.execute(sqlUpdateSourceAsAfterNode))
			return false;
		//����Դ�ڵ���ӽڵ��·���Լ��ڵ㼶��
		for(int	i=0;i<SourceNodeChildren.size();i++){
			int childAsTargetChildNodeSeries	=	tc.stringToInt((String)SourceNodeChildren.get(i).get("node_series"))-
					num_sourceNodeLevel+num_targetNodeLevel;
			String	childAsTargetChildNodePath	=	SourceAsAfterNodePath+"."+
					((String)SourceNodeChildren.get(i).get("node_path")).substring(5*num_sourceNodeLevel);
			
			String	sqlUpdateSourceChildAsAfterChildNode	=	"update	"+this.TableName+
					" set node_series='"+childAsTargetChildNodeSeries+"',node_path='"+
					childAsTargetChildNodePath+"' where id='"+SourceNodeChildren.get(i).get("id")+"'";
			logger.debug(sqlUpdateSourceChildAsAfterChildNode);
			if(0==di.execute(sqlUpdateSourceChildAsAfterChildNode))
				return false;
			
		}
		return true;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	/*
	 * author	:	xiaokai
	 * function	:	ճ��Ϊ�ӽڵ�
	 * Parameter:	sourceNodeId-------------------Դ�ڵ�ID
	 * 				TargetNodeId-------------------Ŀ��ڵ�id	
	*/	
	public boolean pasteAsChildNode(String sourceNodeId, String TargetNodeId) {
		// TODO Auto-generated method stub
		String	getNodeSql="select * from "+this.TableName+" where id='"+sourceNodeId+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		List<Map> sourceNodeInfo =di.executeQuery(getNodeSql);

		TypeChange	tc	=	new	TypeChange();
		String	str_sourceNodePath	=	(String) sourceNodeInfo.get(0).get("node_path");
		String	str_sourceNodeLevel	=	(String) sourceNodeInfo.get(0).get("node_series");
		int		num_sourceNodeLevel	=	tc.stringToInt(str_sourceNodeLevel);


		//��ȡԴ�ڵ�����ڵ�			SourceNodeChildren
		String	sqlGetNodeAndChildren	=	"select * from "+this.TableName+" where tree_id='"+
											this.TreeName+"' and node_path like '"+str_sourceNodePath+".%'";
		@SuppressWarnings("rawtypes")
		List<Map> SourceNodeChildren =di.executeQuery(sqlGetNodeAndChildren);		//Դ�ڵ�����ڵ�	
		//�ڸ��½ڵ�֮ǰ��Դ�ڵ�֮��ͬ���ڵ��Լ����ӽڵ�λ�õ�����ǰ��
		//��ȡͬ���ڵ㼰��ͬ���ڵ������ڵ�
		String	sqlGetSameLevelNodes	=	"select * from "+this.TableName+" where tree_id='"+
											this.TreeName+"' and node_path like '"+
											str_sourceNodePath.substring(0,str_sourceNodePath.length()-4)+
											"%' order by node_path asc";
		logger.debug(sqlGetSameLevelNodes);
		@SuppressWarnings("rawtypes")
		List<Map> SameLevelAndChildNodes	=	di.executeQuery(sqlGetSameLevelNodes);
		//��ʾ�Ƿ��ҵ�Դ�ڵ��Լ�Դ�ڵ���ӽڵ�Ŀ�ʼλ
		boolean	startFlag	=	false;
		boolean	endFlag		=	false;
		for(int	i=0;i<SameLevelAndChildNodes.size();i++){
			
			if(!startFlag){		
				//���ҵ�Դ�ڵ�ǰ�Լ��ҵ�Դ�ڵ�ʱ
				if(sourceNodeId.equals(SameLevelAndChildNodes.get(i).get("id"))){
					//��ǰ������Դ�ڵ�
					startFlag	=	true;
				}
			}
			else{
				if(str_sourceNodeLevel.equals(SameLevelAndChildNodes.get(i).get("node_series"))){
					//��ǰ�ڵ���Դ�ڵ�֮��ĵ�һ��ͬ���ڵ�
					endFlag	=	true;
				}
				if(!endFlag){
					//�ҵ�Դ�ڵ���ӽڵ�
				}
				if(endFlag){
					//Դ�ڵ���ͬ���ڵ��Լ�Դ�ڵ���ͬ���ڵ���ӽڵ�
					String	nodePath	=	(String) SameLevelAndChildNodes.get(i).get("node_path");
					String	SameLevelSubPath	=	nodePath.substring((num_sourceNodeLevel-1)*5,(num_sourceNodeLevel)*5-1);
					int	numSubPath	=	tc.stringToInt(SameLevelSubPath)-1;
					if(numSubPath<10){
						SameLevelSubPath	=	"000"+tc.intToString(numSubPath);
					}
					else if(numSubPath<100){
						SameLevelSubPath	=	"00"+tc.intToString(numSubPath);
					}
					else if(numSubPath<1000){
						SameLevelSubPath	=	"0"+tc.intToString(numSubPath);
					}
					else if(numSubPath<10000){
						SameLevelSubPath	=	tc.intToString(numSubPath);
					}
					String	newPath	=	nodePath.substring(0,5*(num_sourceNodeLevel-1))+SameLevelSubPath+
							nodePath.substring(5*num_sourceNodeLevel-1);
					SameLevelAndChildNodes.get(i).put("node_path", newPath);
					numSubPath	=	tc.stringToInt(newPath.substring(newPath.length()-4));
					SameLevelAndChildNodes.get(i).put("sno",numSubPath);
					String	sqlUpdate	=	"update "+this.TableName+" set sno='"+
											SameLevelAndChildNodes.get(i).get("sno")+"',node_path='"+
											SameLevelAndChildNodes.get(i).get("node_path")+"' where id='"+
											SameLevelAndChildNodes.get(i).get("id")+"'";
					logger.debug(sqlUpdate);
					if(0==di.execute(sqlUpdate))
						return false;
				}
			}
		}	
		//���в�����ϣ��Ѿ���Ŀ��ڵ�֮���ͬ���ڵ��Լ�Ŀ��ڵ�֮����ӽڵ���ǰ�ƶ�
		
		getNodeSql="select * from "+this.TableName+" where id='"+TargetNodeId+"'";
		@SuppressWarnings("rawtypes")
		List<Map> targetNodeInfo =di.executeQuery(getNodeSql);
		
		String	str_targetNodePath	=	(String) targetNodeInfo.get(0).get("node_path");
		String	str_targetNodeLevel	=	(String) targetNodeInfo.get(0).get("node_series");
		int		num_targetNodeLevel				=	tc.stringToInt(str_targetNodeLevel);
		
		//��ȡĿ��ڵ��ӽڵ�
		String	sqlGetTargetNodeChildren	=	"select * from "+this.TableName+" where tree_id='"
												+this.TreeName+"' and node_path like '"
												+str_targetNodePath+".%' and node_series='"
												+(num_targetNodeLevel+1)+"'";
		@SuppressWarnings("rawtypes")
		List<Map> TargetNodeChildren =di.executeQuery(sqlGetTargetNodeChildren);
		//Դ�ڵ���Ϊ�ӽڵ�ʱ��Sno
		int	sourceAsChildSNO	=	TargetNodeChildren.size()+1;			
		String	subPath	="";
		if(sourceAsChildSNO<10){
			subPath	=	"000"+tc.intToString(sourceAsChildSNO);
		}
		else if(sourceAsChildSNO<100){
			subPath	=	"00"+tc.intToString(sourceAsChildSNO);
		}
		else if(sourceAsChildSNO<1000){
			subPath	=	"0"+tc.intToString(sourceAsChildSNO);
		}
		else if(sourceAsChildSNO<10000){
			subPath	=	tc.intToString(sourceAsChildSNO);
		}
		//Դ�ڵ���Ϊ�ӽڵ�ʱ��path
		String	sourceAsChildPath	=	str_targetNodePath+"."+subPath;		
		String	sourceAsChildPid	=	TargetNodeId;
		String	sourceAsChildSeries	=	tc.intToString(num_targetNodeLevel+1);
		//����Դ�ڵ�ΪĿ��ڵ���ӽڵ�
		String	updateSourceNode	=	"update	"+this.TableName+" set pid='"+sourceAsChildPid
										+"',node_path='"+sourceAsChildPath+"',sno='"+sourceAsChildSNO
										+"',node_series='"+sourceAsChildSeries+"' where id='"+sourceNodeId+"'";
		logger.debug(updateSourceNode);
		di.execute(updateSourceNode);
		for(int	i=0;i<SourceNodeChildren.size();i++){
			//Դ�ڵ���ӽڵ���¼�����path
			String	sourceChildNodePath	=	(String) SourceNodeChildren.get(i).get("node_path");
			String	sourceChildNodeSubPath	=	sourceChildNodePath.substring(num_sourceNodeLevel*5-1);
			String	str_sourceChildNodeLevel	=	(String) SourceNodeChildren.get(i).get("node_series");
			int		num_sourceChildNodeLevel	=	tc.stringToInt(str_sourceChildNodeLevel);
			//Դ�ڵ���ӽڵ��Լ�������ڵ�Ľڵ㼶��
			int		sourceChildAsChildNodeLevel	=	num_sourceChildNodeLevel-num_sourceNodeLevel+1+num_targetNodeLevel;
			String	updateSourceChildNode	=	"update "+this.TableName+" set node_path='"
												+(sourceAsChildPath+sourceChildNodeSubPath)+"',node_series='"
												+sourceChildAsChildNodeLevel+"' where id='"+SourceNodeChildren.get(i).get("id")+"'";
			logger.debug(updateSourceChildNode);
			if(0==di.execute(updateSourceChildNode))
				return false;					
			
		}
		return true;
	}
}