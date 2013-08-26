package net.ib.util.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.PaperService;
import net.ib.util.service.Service;

public class PaperServiceImpl implements PaperService {

	private static Logger logger = Logger.getLogger(PaperServiceImpl.class);
	private String OperateId=null;
	private Dao	dao;
	private Service service;
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	@Override
	public String addPaper(String id, String name, String score, String time,
			String diff, File paperCont,File paperAnswer, File paperAtta,String course,String epdbId,String comment,String type) {
		// TODO Auto-generated method stub
		logger.debug(id);
		logger.debug(course);
		logger.debug(epdbId);
		Boolean flag1=this.insertBasicInfo(id, name, score, time, diff,course,epdbId,comment);
		Boolean flag2=false;
		if(flag1){
			flag2=this.insertContentInfo(paperCont,paperAnswer, paperAtta,type);
		}
		logger.debug(flag1);logger.debug(flag2);
		if(flag1&&flag2){		
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			String result = "";
			result = "添加试卷成功";
			try {
				PrintWriter pw = response.getWriter();
				pw.print(result);
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
		}

		return null;
	}
	/**
	 * 
	 * <p>名称：insertBasicInfo</p>
	 * <p>说明：添加试卷基本信息</p>
	 * <p>参数：@param id
	 * <p>参数：@param name
	 * <p>参数：@param score
	 * <p>参数：@param time
	 * <p>参数：@param diff
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：boolean 返回类型</p>
	 */
	private boolean insertBasicInfo(String id, String name, String score,
			String time, String diff,String course,String epdbId,String comment) {
		this.OperateId=id;
		String	sql	=	"select exam_paper_id,paper_name from sys_exam_paper " +
				"where (exam_paper_id='"+id+"' or paper_name='"+name+"') " ;
		logger.debug(sql);
		List<Map>	list	=	dao.executeQuery(sql);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String	result="";
		TypeChange tc	=	new	TypeChange();
		//如果有重复项
		if(list.size()!=0){
			String	tempId="";
			String	tempName="";
			Map map = new HashMap();
			for(int	i=0;i<list.size();i++){
				map=list.get(i);
				tempId	=	(String) map.get("exam_paper_id");
				tempName=	(String) map.get("paper_name");
				if(tempId.equals(id)){
					logger.info("添加试题时试题标识符重复");
					result = "试卷唯一标识符已经存在，请重新填入";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else if(tempName.equals(name)){
					logger.info("添加试卷过程中试卷名称重复");
					result = "试卷名称已经存在，请重新输入";
					try {
						PrintWriter pw = response.getWriter();
						pw.print(result);
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		//没有重复项
		else{
			sql="insert into sys_exam_paper (EXAM_PAPER_ID,PAPER_NAME,TOTAL_SCORE,DIFFICULTY,EXAM_DURATION,CURSE_ID,EPDB_ID,EXPLAIN) values " +
					"('"+id+"','"+name+"','"+score+"','"+diff+"','"+time+"','"+course+"','"+epdbId+"','"+comment+"')";
			if(1==dao.execute(sql)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * <p>名称：insertContentInfo</p>
	 * <p>说明：添加试卷附件信息</p>
	 * <p>参数：@param paperCont
	 * <p>参数：@param paperAtta
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：boolean 返回类型</p>
	 * <p>@param paperCont
	 * <p>@param paperAtta
	 * <p>@return</p>
	 */
	private boolean insertContentInfo(File paperCont,File paperAnswer, File paperAtta,String type) {
		String 	id	=	this.OperateId;
		InputStream InStream=null;
		try {
			InStream = new FileInputStream(paperCont);
/*			File upload = new File("d:" + '\\' + "xiaokai.doc");
			InStream = new FileInputStream(upload);*/
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql="insert into SYS_EXAM_PAPER_CONTENT (EXAM_PAPER_ID,attach_type) values('"+id+"','"+type+"')";
		if(1==dao.execute(sql)){
		//if(true){
			if(null==paperCont){
				logger.error("试卷文件为空");
				return false;
			}//exercuteInsertWithBlob
			if(null==paperAnswer){
				logger.error("试卷答案为空");
				return false;
			}
			//插入试题文件
			if ("true".equals(service.exercuteInsertWithBlob(
					"SYS_EXAM_PAPER_CONTENT", "EXAM_PAPER_ID", id,
					"PAPER_CONTENT", InStream))) {
				try {
					InStream = new FileInputStream(paperAnswer);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//插入试题答案
				if("true".equals(service.exercuteInsertWithBlob(
					"SYS_EXAM_PAPER_CONTENT", "EXAM_PAPER_ID", id,
					"PAPER_ANSWER", InStream))){
					//如果附件不为空，插入试题附件
					if(paperAtta!=null){
						try {
							InStream = new FileInputStream(paperAtta);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if("true".equals(service.exercuteUpdateWithBlob("SYS_EXAM_PAPER_CONTENT", "EXAM_PAPER_ID", id, "PAPER_ATTACHMENT", InStream))){
							return true;
						}
					}
					//如果附件为空并且试卷内容已经添加完成，返回成功
					else{
						return true;
					}
				}
				
			}
			else{

				String delSql="delete from SYS_EXAM_PAPER_CONTENT where EXAM_PAPER_ID='"+id+"'";
				dao.execute(delSql);
				delSql	=	"delete from SYS_EXAM_PAPER where exam_paper_id='"+id+"'";
				dao.execute(delSql);
			}
		}
		else{

			String delSql="delete from SYS_EXAM_PAPER_CONTENT where EXAM_PAPER_ID='"+id+"'";
			dao.execute(delSql);
			delSql	=	"delete from SYS_EXAM_PAPER where exam_paper_id='"+id+"'";
			dao.execute(delSql);
		}
		return false;
	}

}
