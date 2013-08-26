/**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	CurseAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�wuzexi
 * | �������ڣ�2012-12-6 ����9:27:39
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-6 ����9:27:39
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
 * <p>������net.ib.util.action.CurseAction </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class CurseAction extends ActionSupport {
	
/*	private static final Log logger = LogFactory.getLog(TeachingMaterialAction.class);*/
	private static Logger logger = Logger.getLogger(CurseAction.class);
	
	private	String	specialFieldId	= null;
	private String OldCurseId;
	private String CurseID;  //�γ̱��
	private String CurseName;  //�γ�����
	private String CurseCredit; //�γ�ѧ��
	private String radio;    //���˷�ʽ
	private String CurseRemarks;  //�γ�˵��
	private	String SelectCurse;
	private	String	DeleteId;
	private	CurseService curseService;
	private String GetSpecial;    //��õ�רҵID
	
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
	 * <p>���ƣ�addCurse</p>
	 * <p>˵������ӿγ�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	addCurse(){
		//����������GetSpecial��CurseID��CurseName��CurseCredit��radio��Ӧ��select����input���ߵ�ѡ���е�valueֵ
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
	 * <p>���ƣ�deleteCurse</p>
	 * <p>˵����ɾ���γ�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	deleteCurse(){
		curseService.deleteCurse(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�updateCurse</p>
	 * <p>˵�����޸Ŀγ���Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
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
	 * <p>���ƣ�bulkDeleteCurse</p>
	 * <p>˵��������ɾ���γ���Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	bulkDeleteCurse(){
		curseService.bulkDeleteCurse(DeleteId);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�getCurseBySpecialFieldId</p>
	 * <p>˵����ͨ��רҵId��ȡ��רҵ�µ����пγ���Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
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
	
	