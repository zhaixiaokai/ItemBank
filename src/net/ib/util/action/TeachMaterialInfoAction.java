package	net.ib.util.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.dao.DaoImpl;
import net.ib.util.service.Service;
import net.ib.util.service.impl.ServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

public	class	TeachMaterialInfoAction extends ActionSupport{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	teachingMaterial=null;
	private String	materialChapter	=null;
	private String	chapterKnowledge	=null;
	public String getTeachingMaterial() {
		return teachingMaterial;
	}
	public void setTeachingMaterial(String teachingMaterial) {
		this.teachingMaterial = teachingMaterial;
	}
	
	public String getMaterialChapter() {
		return materialChapter;
	}
	public void setMaterialChapter(String materialChapter) {
		this.materialChapter = materialChapter;
	}
	public String getChapterKnowledge() {
		return chapterKnowledge;
	}
	public void setChapterKnowledge(String chapterKnowledge) {
		this.chapterKnowledge = chapterKnowledge;
	}

	Dao dao	=	new	DaoImpl();
	Service si	=	new	ServiceImpl();
	/*
	 * author	:	xiaokai
	 * function	:	根据教材获取章节信息
	 * date		:	20121130
	*/
	public String getChapterByMaterial(){
		String	sql	=	"select * from sys_teaching_chapter_tree where tree_id='"+this.teachingMaterial+"' order by node_path asc";
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Map> list	=	(List<Map>) dao.executeQuery(sql);
		//String Json	=	si.DataListToJson(list);
		String	Json	=	si.DataListToTreeJson(list);
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
	/*
	 * author	:	xiaokai
	 * function	:	根据章节获取知识点信息
	 * date		:	20121130
	*/
	public String	getKnowledgeByChapter(){
		String	sql	=	"select name,knowledge_point_id from sys_knowledge_point where knowledge_point_id in" +
				"(select Knowledge_point_id from sys_teaching_chapter_knowledge where chapter_id='"+this.materialChapter+"')";
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Map> list	=	(List<Map>) dao.executeQuery(sql);
		System.err.println(list);
		String	Json	=	si.DataListToJson(list);
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
}