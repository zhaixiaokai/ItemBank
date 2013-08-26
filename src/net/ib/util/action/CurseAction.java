/**
 * -------------------------------------------------------------------------------------
 * | 文件名：	CurseAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：wuzexi
 * | 创建日期：2012-12-6 上午9:27:39
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-6 上午9:27:39
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;
import com.opensymphony.xwork2.ActionSupport;

import net.ib.util.service.CurseService;

import org.apache.log4j.Logger;

import net.ib.util.service.impl.CurseServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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


  /**
 * <p>类名：net.ib.util.action.CurseAction </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class CurseAction extends ActionSupport {
	
/*	private static final Log logger = LogFactory.getLog(TeachingMaterialAction.class);*/
	private static Logger logger = Logger.getLogger(CurseAction.class);
	
	private	String	specialFieldId	= null;
	private String OldCurseId;
	private String CurseID;  //课程编号
	private String CurseName;  //课程名称
	private String CurseCredit; //课程学分
	private String radio;    //考核方式
	private String CurseRemarks;  //课程说明
	private	String SelectCurse;
	private	String	DeleteId;
	private	CurseService curseService;
	private String GetSpecial;    //获得的专业ID
	
	public String getGetSpecial() {
		return GetSpecial;
	}
	public void setGetSpecial(String getSpecial) {
		GetSpecial = getSpecial;
	}
	public String getSpecialFieldId() {
		return specialFieldId;
	}
	public void setSpecialFieldId(String specialFieldId) {
		this.specialFieldId = specialFieldId;
	}
	public String getOldCurseId() {
		return OldCurseId;
	}
	public void setOldCurseId(String oldCurseId) {
		OldCurseId = oldCurseId;
	}
	public String getCurseID() {
		return CurseID;
	}
	public void setCurseID(String curseID) {
		CurseID = curseID;
	}
	public String getCurseName() {
		return CurseName;
	}
	public void setCurseName(String curseName) {
		CurseName = curseName;
	}
	public String getCurseCredit() {
		return CurseCredit;
	}
	public void setCurseCredit(String curseCredit) {
		CurseCredit = curseCredit;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getCurseRemarks() {
		return CurseRemarks;
	}
	public void setCurseRemarks(String curseRemarks) {
		CurseRemarks = curseRemarks;
	}
	public String getSelectCurse() {
		return SelectCurse;
	}
	public void setSelectCurse(String selectCurse) {
		SelectCurse = selectCurse;
	}
	public String getDeleteId() {
		return DeleteId;
	}
	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}
	public CurseService getCurseService() {
		return curseService;
	}
	public void setCurseService(CurseService curseService) {
		this.curseService = curseService;
	}
	
	/**
	 * 
	 * <p>名称：addCurse</p>
	 * <p>说明：添加课程</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	addCurse(){
		//传过来的是GetSpecial、CurseID、CurseName、CurseCredit、radio对应的select或者input或者单选框中的value值
		logger.debug(GetSpecial);
		logger.debug(CurseID);
		logger.debug(CurseName);
		logger.debug(CurseCredit);
		logger.debug(radio);
		logger.debug(CurseRemarks);
		curseService.addCurse(GetSpecial, CurseID, CurseName, CurseCredit,radio,CurseRemarks);
		return null;
	}
	/**
	 * 
	 * <p>名称：deleteCurse</p>
	 * <p>说明：删除课程</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	deleteCurse(){
		curseService.deleteCurse(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>名称：updateCurse</p>
	 * <p>说明：修改课程信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	updateCurse(){
		/*logger.debug("__________________________________");
		logger.debug(OldCurseId);*/
		curseService.updateCurse(this.OldCurseId, this.CurseName, this.CurseID, this.CurseCredit,this.radio,this.CurseRemarks);
		return null;
	}
	/**
	 * 
	 * <p>名称：bulkDeleteCurse</p>
	 * <p>说明：批量删除课程信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	bulkDeleteCurse(){
		curseService.bulkDeleteCurse(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>名称：getCurseBySpecialFieldId</p>
	 * <p>说明：通过专业Id获取该专业下的所有课程信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String	getCurseBySpecialFieldId(){
		
		String sql	=	"select * from sys_curse where special_field_id='"+this.specialFieldId+"'";

		Dao	di	=	new	DaoImpl();
		List<Map> list	=	(List<Map>) di.executeQuery(sql);
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

}
	
	