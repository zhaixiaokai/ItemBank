package	net.ib.util.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.ib.util.service.TreeOperate;
import com.opensymphony.xwork2.ActionSupport;

public class TreeOperateAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	String	action	=	null;
	String	nodeid	=	null;

	String	node_explain	=	null;
	String	text	=	null;
	String	table	=	null;
	String	treeId	=	null;
	String	sourceId=	null;
	String	targetId=	null;
	TreeOperate	treeOperate;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	public String getNode_explain() {
		return node_explain;
	}
	public void setNode_explain(String node_explain) {
		this.node_explain = node_explain;
		//转义n→n2   \n→n1
		this.node_explain=this.node_explain.replaceAll("n", "n2");
		this.node_explain=this.node_explain.replaceAll("\n", "n1");
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getTreeId() {
		return treeId;
	}
	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public TreeOperate getTreeOperate() {
		return treeOperate;
	}
	public void setTreeOperate(TreeOperate treeOperate) {
		this.treeOperate = treeOperate;
	}
	public String execute(){
		//初始化操作方法对应的table以及treeId
		treeOperate.setTableName(table);
		treeOperate.setTreeName(treeId);
		
		if("addPre".equals(action)){
			if(treeOperate.addPreviousNode(this.nodeid,this.text,this.node_explain))
				this.write("前置添加成功");
			else{
				this.write("前置添加失败");
			}
			
		}
		else if("addAfter".equals(action)){
			if(treeOperate.addAfterNode(this.nodeid,this.text,this.node_explain))
				this.write("后置添加成功");
			else
				this.write("后置添加失败");
		}
		else if("modify".equals(action)){
			if(treeOperate.modifyNode(this.nodeid,this.text,this.node_explain))
				this.write("修改成功");
			else {
				this.write("修改失败");
			}
		}
		else if("delete".equals(action)){
			if(treeOperate.deleteNode(this.nodeid))
				this.write("删除成功");
			else {
				this.write("删除失败");
			}
		}
		else if("addChild".equals(action)){
			if(treeOperate.addChildNode(this.nodeid,this.text,this.node_explain))
				this.write("添加子节点成功");
			else {
				this.write("添加子节点失败");
			}
		}
		else if("pasteAsChild".equals(action)){
			if(treeOperate.pasteAsChildNode(sourceId,targetId))
				this.write("粘贴为子节点成功");
			else {
				this.write("粘贴为子节点失败");
			}
		}
		else if("pasteAsPrevious".equals(action)){
			if(treeOperate.pasteAsPreviousNode(sourceId,targetId))
				this.write("粘贴为前置节点成功");
			else {
				this.write("粘贴为前置节点失败");
			}
		}
		else if("pasteAsAfter".equals(action)){
			if(treeOperate.pasteAsAfterNode(sourceId,targetId))
				this.write("粘贴为后置节点成功");
			else {
				this.write("粘贴为后置节点失败");
			}
		}
		return null;
	}
	private void write(String result){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}