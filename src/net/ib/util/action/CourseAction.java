package net.ib.util.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.ib.util.dao.*;

public class CourseAction extends ActionSupport{

	private static final Log logger = LogFactory.getLog(CourseAction.class);
	
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
	public String DataToBase()
	{
		Dao daoImpl=new DaoImpl();
		
		//≈–∂œøŒ≥Ã±‡∫≈ «∑Ò÷ÿ∏¥
		List<Map> list_name = daoImpl
				.executeQuery("select * from sys_curse where curse_name='"+CurseName+"' ");
			if(list_name.size()>0)
			{
				HttpServletResponse response	=	ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				try {	
					PrintWriter pw = response.getWriter();	
					pw.print("{\"result\":\"øŒ≥Ã√˚≥∆÷ÿ∏¥\"}");	
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		//≈–∂œøŒ≥Ã√˚≥∆ «∑Ò÷ÿ∏¥
			else
			{
				List<Map> list_id = daoImpl
						.executeQuery("select * from sys_curse where curse_id='"+CurseID+"' ");
				if(list_id.size()>0)
				{
					HttpServletResponse response	=	ServletActionContext.getResponse();
					response.setCharacterEncoding("utf-8");
					try {	
						PrintWriter pw = response.getWriter();	
						pw.print("{\"result\":\"øŒ≥Ã±‡∫≈÷ÿ∏¥\"}");	
						pw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return null;
				}
				else
				{
					String sql="insert into sys_curse fields(curse_id,curse_name,curse_credit,assessment_method,special_field_id,remarks) VALUES('"+this.CurseID+"','"+this.CurseName+"','"+this.CurseCredit+"','"+this.radio+"','"+this.Specials+"','"+this.Remarks+"')";
					logger.debug(sql);
					daoImpl.execute(sql);
					return null;
				}
			}	
	}
	


	
	

}
