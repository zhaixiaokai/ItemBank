package net.ib.util.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

import net.ib.util.dao.*;

public class addSubAction extends ActionSupport{

	private static final Log logger = LogFactory.getLog(addSubAction.class);
	
	private String CurseID;
	private String CurseName;
	private String CurseCredit;
	private String radio;
	private String Remarks;

	public String getCurseID() {
		return CurseID;
	}
	public void setCurseID(String curseID) {
		this.CurseID = curseID;
	}
	public String getCurseName() {
		return CurseName;
	}
	public void setCurseName(String curseName) {
		this.CurseName = curseName;
	}
	public String getCurseCredit() {
		return CurseCredit;
	}
	public void setCurseCredit(String curseCredit) {
		this.CurseCredit = curseCredit;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		this.Remarks = remarks;
	}
	public String DataToBase(){
		String sql="insert into sys_curse fields(curse_id,curse_name,curse_credit,assessment_method) VALUES('"+this.CurseID+"','"+this.CurseName+"','"+this.CurseCredit+"','"+this.radio+"')";
		logger.debug(sql);
		DaoImpl daoImpl=new DaoImpl();
		if(daoImpl.execute(sql)==1){
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	


	
	

}
