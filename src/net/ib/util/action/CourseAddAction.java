package net.ib.util.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

import net.ib.util.dao.*;

public class CourseAddAction extends ActionSupport{

	private static final Log logger = LogFactory.getLog(CourseAddAction.class);
	
	private String CurseID;
	private String CurseName;
	private String CurseCredit;
	private String radio;
	private String Remarks;
	private String Specials;
	private String message;

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
	public String getSpecials() {
		return Specials;
	}
	public void setSpecials(String specials) {
		this.Specials = specials;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String DataToBase(){
		DaoImpl daoImpl=new DaoImpl();
		
		//判断课程编号是否重复
	/*			List<Map> list_name = daoImpl
						.executeQuery("select * from sys_curse where curse_name='"+CurseName+"' ");
				if(list_name.size()>0){
					this.message="名称重复，请重新输入";
					return "message";
				}
		//判断课程名称是否重复
				List<Map> list_identifier = daoImpl
						.executeQuery("select * from sys_curse where curse_id='"+CurseID+"' ");
				if(list_identifier.size()>0){
					this.message="标识符重复，请重新输入";
					return "message";
				}
		*/
		String sql="insert into sys_curse fields(curse_id,curse_name,curse_credit,assessment_method,special_field_id) VALUES('"+this.CurseID+"','"+this.CurseName+"','"+this.CurseCredit+"','"+this.radio+"','"+this.Specials+"')";
		logger.debug(sql);
		
		if(daoImpl.execute(sql)==1){
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	


	
	

}
