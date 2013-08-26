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
 * function :	对树节点进行操作
 * explain	:	使用该方法是先调用setTableName与setTreeName两个方法设置当前操作的树所在的表以及树的tree_id
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
 * function	:	添加前置节点
 * Parameter:	oldNodeId-----------------------当前操作节点Id，即在ID为该参数的节点前加入新的节点
 * 				newNodeText---------------------当前所要添加的节点名称
 * 				newNodeExplain------------------当前所要添加的节点备注
*/	
	public boolean addPreviousNode(String oldNodeId, String newNodeText,
			String newNodeExplain) {
		// TODO Auto-generated method stub
		//获取当前目标节点的信息
		String	getOldNodeSql="select * from "+this.TableName+" where id='"+oldNodeId+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		///oldNodeInfo用来存储当前操作节点的原始信息
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
		int	currentLevel	=	tc.stringToInt(currentNodeSeries);//当前节点级数
		int	currentSNO	=	tc.stringToInt(currentNodeSNO);
		
		
		//获取当前节点同级节点以及所有同级节点的子孙节点
		String	searchStr	=	currentNodePath.substring(0, currentNodePath.length()-4);
		String	sql	=	"select * from "+this.TableName+" where tree_id='"+
						this.TreeName+"'"+" and node_path like '"+searchStr+"%'  order by node_path asc";
		@SuppressWarnings("rawtypes")
		//allNodeInfo用来存储当前节点的所有同级节点以及所有同级节点的子孙节点
		List<Map> allNodeInfo	=	di.executeQuery(sql);
		logger.debug(allNodeInfo);
		boolean	flag	=	false;			//表示找到开始操作节点的位置
		for(int i=0;i<allNodeInfo.size();i++){
			//当前遍历的节点的ID与目标节点的id相同
			if(allNodeInfo.get(i).get("id").equals(oldNodeId)){
				flag	=	true;
			}
			if(flag){
				//添加节点的等级与当前节点的级数相同并且当前节点sno大于等于添加节点目标节点的sno
				if(currentNodeSeries.equals((String)allNodeInfo.get(i).get("node_series"))	&&	
						currentSNO<=tc.stringToInt((String)allNodeInfo.get(i).get("sno"))){
					allNodeInfo.get(i).put("sno", tc.stringToInt((String)allNodeInfo.get(i).get("sno"))+1);
				}
				String	nodePath	=	(String) allNodeInfo.get(i).get("node_path");
				String	subPath	=	nodePath.substring((currentLevel-1)*5,(currentLevel)*5-1);
				int	numSubPath	=	tc.stringToInt(subPath)+1;
				String	newPath	=	"";
				//将目标路径补足四位
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
		
		//添加一条记录
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
 * function	:	添加后置节点
 * Parameter：	oldNodeId-----------------------当前操作节点Id，即要在节点Id为该参数的节点后添加节点
 * 				newNodeText---------------------所要添加节点的名称
 * 				newNodeExplain------------------所要添加节点的备注内容
*/
	public boolean addAfterNode(String oldNodeId, String newNodeText,
			String newNodeExplain) {
		// TODO Auto-generated method stub
		//获取当前目标节点的信息
		String	getOldNodeSql="select * from "+this.TableName+" where id='"+oldNodeId+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		//oldNodeInfo用来存储当前操作节点的原始信息
		List<Map> oldNodeInfo =di.executeQuery(getOldNodeSql);
		logger.debug(oldNodeInfo);
		String currentNodePath	=	(String) oldNodeInfo.get(0).get("node_path");
		String	currentNodeSeries	=	(String) oldNodeInfo.get(0).get("node_series");
		String	currentNodeSNO	=	(String) oldNodeInfo.get(0).get("sno");
		String	currentNodeTreeId	=	(String) oldNodeInfo.get(0).get("tree_id");
		String	currentNodePid	=	(String) oldNodeInfo.get(0).get("pid");
		TypeChange	tc	=	new	TypeChange();
		//对根节点的特殊处理
		if(currentNodePid.equals("0")||currentNodePid.equals("root")){
		
				return false;
			
		}
		else{
		int	currentLevel	=	tc.stringToInt(currentNodeSeries);//当前节点级数
		int	currentSNO	=	tc.stringToInt(currentNodeSNO);
		
		//获取当前节点同级节点以及所有同级节点的子孙节点
		String	searchStr	=	currentNodePath.substring(0, currentNodePath.length()-4);
		String	sql	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+"'"+
						" and node_path like '"+searchStr+"%'  order by node_path asc";
		@SuppressWarnings("rawtypes")
		//allNodeInfo用来存储当前节点的所有同级节点以及同级节点的所有子孙节点
		List<Map> allNodeInfo	=	di.executeQuery(sql);
		logger.debug(allNodeInfo);
		boolean	getCurrentOperateFlag	=	false;
		boolean	flag	=	false;	
		
		for(int i=0;i<allNodeInfo.size();i++){
			if(allNodeInfo.get(i).get("id").equals(oldNodeId)){
				//表示找到开始操作节点的位置
				getCurrentOperateFlag	=	true;			
				continue;
			}
			if(getCurrentOperateFlag&&flag==false){
				//找到当前节点的同级节点中，该节点的下一个同级节点
				if(allNodeInfo.get(i).get("node_series").equals(currentNodeSeries)){
					flag	=	true;
				}
			}
			if(flag){
				//添加节点的等级与当前节点的级数相同并且当前节点sno大于等于添加节点目标节点的sno
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
		//添加一条记录
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
 * function	:	为当前节点添加子节点
 * Parameter：	oldNodeId-----------------------当前操作节点Id，即要在节点Id为该参数的节点后添加节点
 * 				newNodeText---------------------所要添加节点的名称
 * 				newNodeExplain------------------所要添加节点的备注内容
*/
	public boolean addChildNode(String oldNodeId, String newNodeText,
			String newNodeExplain) {
		// TODO Auto-generated method stub
		//获取当前目标节点的信息
		String	getOldNodeSql="select * from "+this.TableName+" where id='"+oldNodeId+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		//oldNodeInfo用来存储目标节点的原始信息
		List<Map> oldNodeInfo =di.executeQuery(getOldNodeSql);
		logger.debug(oldNodeInfo);
		String currentNodePath	=	(String) oldNodeInfo.get(0).get("node_path");
		String	currentNodeSeries	=	(String) oldNodeInfo.get(0).get("node_series");
		String	currentNodeTreeId	=	(String) oldNodeInfo.get(0).get("tree_id");
		String	currentNodePid	=	(String) oldNodeInfo.get(0).get("id");			//添加子节点的pid为当前节点的id
		TypeChange	tc	=	new	TypeChange();
		
		//获取当前子节点
		String	sql	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+"'"+
						" and node_path like '"+currentNodePath+".%'  order by node_path asc";
		logger.debug(sql);
		
		@SuppressWarnings("rawtypes")
		//allNodeInfo用来存储当前节点子节点的信息
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
 * function	:	删除节点
 * Parameter:	delNodeId为当前操作需要删除的节点Id	
*/	
	public boolean deleteNode(String delNodeID) {
		// TODO Auto-generated method stub
		//获取当前目标节点的信息
		String	getOldNodeSql="select * from "+this.TableName+" where id='"+delNodeID+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		List<Map> oldNodeInfo =di.executeQuery(getOldNodeSql);
		logger.debug(oldNodeInfo);
		String currentNodePath	=	(String) oldNodeInfo.get(0).get("node_path");
		String	strCurrentLevel	=	(String) oldNodeInfo.get(0).get("node_series");
		String	currentNodePid	=	(String) oldNodeInfo.get(0).get("pid");
		TypeChange	tc	=	new	TypeChange();
		//对于根节点进行特殊处理
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
		boolean	startDeleteFlag	=	false;//表示是否找到删除开始位
		boolean	endDeleteFlag	=	false;	//表示是否找到删除截至位
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
				if(!endDeleteFlag){//如果删除位置找到，开始删除并且判断是否跟当前节点级数相同，如果相同停止删除
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
 * function	:	修改节点信息
 * Parameter:	oldNodeId---------------------当前需要修改的节点id
 * 				newNodeText-------------------修改后的新参数
 * 				newNodeExplain----------------修改后的新节点说明
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
 * function	:	粘贴为前置节点
 * Parameter:	sourceNodeId-------------------源节点ID
 * 				TargetNodeId-------------------目标节点id	
*/	
	public boolean pasteAsPreviousNode(String sourceNodeId, String TargetNodeId) {
		// TODO Auto-generated method stub
		String	getNodeSql="select * from "+this.TableName+" where id='"+sourceNodeId+"'";
		DaoImpl di	=	new DaoImpl();
		@SuppressWarnings("rawtypes")
		//sourceNodeInfo用来存储源节点的信息
		List<Map> sourceNodeInfo =di.executeQuery(getNodeSql);
		TypeChange	tc	=	new	TypeChange();
		String	str_sourceNodePath	=	(String) sourceNodeInfo.get(0).get("node_path");
		String	str_sourceNodeLevel	=	(String) sourceNodeInfo.get(0).get("node_series");
		String	currentNodePid	=	(String) sourceNodeInfo.get(0).get("pid");
		//对于根节点进行特殊处理
		if(currentNodePid.equals("0")||currentNodePid.equals("root")){
			return false;
		}
		else{
		int		num_sourceNodeLevel	=	tc.stringToInt(str_sourceNodeLevel);
	
		
		//获取源节点子节点			SourceNodeChildren
		String	sqlGetNodeAndChildren	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+
				"' and node_path like '"+str_sourceNodePath+".%'";
		@SuppressWarnings("rawtypes")
		//SourceNodeChildren用来存储源节点的子节点信息
		List<Map> SourceNodeChildren =di.executeQuery(sqlGetNodeAndChildren);		//源节点子节点	
		//在更新节点之前将源节点之后同级节点以及其子节点位置调整，前移
		//获取同级节点及其同级节点的子孙节点
		String	sqlGetSameLevelNodes	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+
				"' and node_path like '"+str_sourceNodePath.substring(0,str_sourceNodePath.length()-4)+"%' order by node_path asc";
		logger.debug(sqlGetSameLevelNodes);
		@SuppressWarnings("rawtypes")
		//SameLevelAndChildNodes用来存储当前节点的子节点以及本节点同级节点的子孙节点
		List<Map> SameLevelAndChildNodes	=	di.executeQuery(sqlGetSameLevelNodes);
		//表示是否找到源节点以及源节点的子节点的开始位
		boolean	startFlag	=	false;
		boolean	endFlag		=	false;
		for(int	i=0;i<SameLevelAndChildNodes.size();i++){
			//在找到源节点前以及找到源节点时
			if(!startFlag){		
				//当前数据是源节点
				if(sourceNodeId.equals(SameLevelAndChildNodes.get(i).get("id"))){
					startFlag	=	true;
				}
			}
			else{
				//当前节点是源节点之后的第一个同级节点
				if(str_sourceNodeLevel.equals(SameLevelAndChildNodes.get(i).get("node_series"))){
					endFlag	=	true;
				}
				//找到源节点的子节点
				if(!endFlag){
				}
				//源节点后的同级节点以及源节点后的同级节点的子节点向前移动
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
		//剪切操作完毕，已经将目标节点之后的同级节点以及目标节点之后的子节点向前移动
		//获取目标节点
		getNodeSql="select * from "+this.TableName+" where id='"+TargetNodeId+"'";
		@SuppressWarnings("rawtypes")
		List<Map> targetNodeInfo =di.executeQuery(getNodeSql);
		
		String	str_targetNodePath	=	(String) targetNodeInfo.get(0).get("node_path");
		String	str_targetNodeSNO	=	(String) targetNodeInfo.get(0).get("sno");
		String	str_targetNodePid	=	(String) targetNodeInfo.get(0).get("pid");
		String	str_targetNodeLevel	=	(String) targetNodeInfo.get(0).get("node_series");
		int		num_targetNodeLevel				=	tc.stringToInt(str_targetNodeLevel);	
		
		//获取目标节点的同级节点以及子孙节点
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
				//从目标节点开始，其同级节点以及子孙节点均后移
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
		
		//更新源节点为目标节点的前置节点
		//源节点的路径更新后为目标节点原先的路径
		String	SourceAsPreviousNodePath	=	str_targetNodePath;
		//源节点SNO更新后为目标节点原先的SNO
		String	SourceAsPreviousNodeSNO		=	str_targetNodeSNO;
		String	sqlUpdateSourceAsAfterNode	=	"update	"+this.TableName+" set pid='"+str_targetNodePid+
				"',sno='"+SourceAsPreviousNodeSNO+"',node_path='"+SourceAsPreviousNodePath+
				"',node_series='"+num_targetNodeLevel+"' where id='"+sourceNodeId+"'";
		logger.debug(sqlUpdateSourceAsAfterNode);
		if(0==di.execute(sqlUpdateSourceAsAfterNode))
			return false;
		//更新源节点子孙节点的路径以及级数
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
	 * function	:	粘贴为后置节点
	 * Parameter:	sourceNodeId-------------------源节点ID
	 * 				TargetNodeId-------------------目标节点id	
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
		//对于根节点进行特殊处理
		if(currentNodePid.equals("0")||currentNodePid.equals("root")){
			return false;
		}
		else{
		int		num_sourceNodeLevel	=	tc.stringToInt(str_sourceNodeLevel);
	
		
		//获取源节点子节点			SourceNodeChildren
		String	sqlGetNodeAndChildren	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+
				"' and node_path like '"+str_sourceNodePath+".%'";
		@SuppressWarnings("rawtypes")
		List<Map> SourceNodeChildren =di.executeQuery(sqlGetNodeAndChildren);		//源节点子节点	
		//在更新节点之前将源节点之后同级节点以及其子节点位置调整，前移
		//获取同级节点及其同级节点的子孙节点
		String	sqlGetSameLevelNodes	=	"select * from "+this.TableName+" where tree_id='"+this.TreeName+
				"' and node_path like '"+str_sourceNodePath.substring(0,str_sourceNodePath.length()-4)+"%' order by node_path asc";
		logger.debug(sqlGetSameLevelNodes);
		@SuppressWarnings("rawtypes")
		List<Map> SameLevelAndChildNodes	=	di.executeQuery(sqlGetSameLevelNodes);
		//表示是否找到源节点以及源节点的子节点的开始位
		boolean	startFlag	=	false;
		boolean	endFlag		=	false;
		for(int	i=0;i<SameLevelAndChildNodes.size();i++){
			//在找到源节点前以及找到源节点时
			if(!startFlag){		
				if(sourceNodeId.equals(SameLevelAndChildNodes.get(i).get("id"))){
					//当前数据是源节点
					startFlag	=	true;
				}
			}
			else{
				if(str_sourceNodeLevel.equals(SameLevelAndChildNodes.get(i).get("node_series"))){
					//当前节点是源节点之后的第一个同级节点
					endFlag	=	true;
				}
				if(!endFlag){
					//找到源节点的子节点
				}
				if(endFlag){
					//源节点后的同级节点以及源节点后的同级节点的子节点
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
		//剪切操作完毕，已经将目标节点之后的同级节点以及目标节点之后的子节点向前移动
		//获取目标节点
		getNodeSql="select * from "+this.TableName+" where id='"+TargetNodeId+"'";
		@SuppressWarnings("rawtypes")
		List<Map> targetNodeInfo =di.executeQuery(getNodeSql);
		
		String	str_targetNodePath	=	(String) targetNodeInfo.get(0).get("node_path");
		String	str_targetNodeSNO	=	(String) targetNodeInfo.get(0).get("sno");
		String	str_targetNodePid	=	(String) targetNodeInfo.get(0).get("pid");
		String	str_targetNodeLevel	=	(String) targetNodeInfo.get(0).get("node_series");
		int		num_targetNodeSNO	=	tc.stringToInt(str_targetNodeSNO);
		int		num_targetNodeLevel				=	tc.stringToInt(str_targetNodeLevel);	
		
		//获取目标节点的同级节点以及子孙节点
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
			if(!targetStart){		//在找到源节点前以及找到源节点时
				if(TargetNodeId.equals(TargetSameLevelAndChildNodes.get(i).get("id"))){
					//当前数据是源节点
					targetStart	=	true;
				}
			}
			else{
				if(str_targetNodeLevel.equals(TargetSameLevelAndChildNodes.get(i).get("node_series"))){
					//当前节点是目标节点之后的第一个同级节点
					targetEnd	=	true;
				}
				if(!targetEnd){
					//找到目标节点的子节点
				}
				if(targetEnd){
					//目标节点后的同级节点以及源节点后的同级节点的子节点
					//将目标节点后的子节点以及孙节点后遗
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
		//更新源节点为目标节点的后置节点
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
		//更新源节点的子节点的路径以及节点级数
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
	 * function	:	粘贴为子节点
	 * Parameter:	sourceNodeId-------------------源节点ID
	 * 				TargetNodeId-------------------目标节点id	
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


		//获取源节点子孙节点			SourceNodeChildren
		String	sqlGetNodeAndChildren	=	"select * from "+this.TableName+" where tree_id='"+
											this.TreeName+"' and node_path like '"+str_sourceNodePath+".%'";
		@SuppressWarnings("rawtypes")
		List<Map> SourceNodeChildren =di.executeQuery(sqlGetNodeAndChildren);		//源节点子孙节点	
		//在更新节点之前将源节点之后同级节点以及其子节点位置调整，前移
		//获取同级节点及其同级节点的子孙节点
		String	sqlGetSameLevelNodes	=	"select * from "+this.TableName+" where tree_id='"+
											this.TreeName+"' and node_path like '"+
											str_sourceNodePath.substring(0,str_sourceNodePath.length()-4)+
											"%' order by node_path asc";
		logger.debug(sqlGetSameLevelNodes);
		@SuppressWarnings("rawtypes")
		List<Map> SameLevelAndChildNodes	=	di.executeQuery(sqlGetSameLevelNodes);
		//表示是否找到源节点以及源节点的子节点的开始位
		boolean	startFlag	=	false;
		boolean	endFlag		=	false;
		for(int	i=0;i<SameLevelAndChildNodes.size();i++){
			
			if(!startFlag){		
				//在找到源节点前以及找到源节点时
				if(sourceNodeId.equals(SameLevelAndChildNodes.get(i).get("id"))){
					//当前数据是源节点
					startFlag	=	true;
				}
			}
			else{
				if(str_sourceNodeLevel.equals(SameLevelAndChildNodes.get(i).get("node_series"))){
					//当前节点是源节点之后的第一个同级节点
					endFlag	=	true;
				}
				if(!endFlag){
					//找到源节点的子节点
				}
				if(endFlag){
					//源节点后的同级节点以及源节点后的同级节点的子节点
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
		//剪切操作完毕，已经将目标节点之后的同级节点以及目标节点之后的子节点向前移动
		
		getNodeSql="select * from "+this.TableName+" where id='"+TargetNodeId+"'";
		@SuppressWarnings("rawtypes")
		List<Map> targetNodeInfo =di.executeQuery(getNodeSql);
		
		String	str_targetNodePath	=	(String) targetNodeInfo.get(0).get("node_path");
		String	str_targetNodeLevel	=	(String) targetNodeInfo.get(0).get("node_series");
		int		num_targetNodeLevel				=	tc.stringToInt(str_targetNodeLevel);
		
		//获取目标节点子节点
		String	sqlGetTargetNodeChildren	=	"select * from "+this.TableName+" where tree_id='"
												+this.TreeName+"' and node_path like '"
												+str_targetNodePath+".%' and node_series='"
												+(num_targetNodeLevel+1)+"'";
		@SuppressWarnings("rawtypes")
		List<Map> TargetNodeChildren =di.executeQuery(sqlGetTargetNodeChildren);
		//源节点作为子节点时的Sno
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
		//源节点作为子节点时的path
		String	sourceAsChildPath	=	str_targetNodePath+"."+subPath;		
		String	sourceAsChildPid	=	TargetNodeId;
		String	sourceAsChildSeries	=	tc.intToString(num_targetNodeLevel+1);
		//更新源节点为目标节点的子节点
		String	updateSourceNode	=	"update	"+this.TableName+" set pid='"+sourceAsChildPid
										+"',node_path='"+sourceAsChildPath+"',sno='"+sourceAsChildSNO
										+"',node_series='"+sourceAsChildSeries+"' where id='"+sourceNodeId+"'";
		logger.debug(updateSourceNode);
		di.execute(updateSourceNode);
		for(int	i=0;i<SourceNodeChildren.size();i++){
			//源节点的子节点更新级数、path
			String	sourceChildNodePath	=	(String) SourceNodeChildren.get(i).get("node_path");
			String	sourceChildNodeSubPath	=	sourceChildNodePath.substring(num_sourceNodeLevel*5-1);
			String	str_sourceChildNodeLevel	=	(String) SourceNodeChildren.get(i).get("node_series");
			int		num_sourceChildNodeLevel	=	tc.stringToInt(str_sourceChildNodeLevel);
			//源节点的子节点以及各级孙节点的节点级数
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