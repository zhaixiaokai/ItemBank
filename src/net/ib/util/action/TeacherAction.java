/*
 author:huangJu
 function:��ӽ�ʦAction
 
 */
package net.ib.util.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import net.ib.util.dao.*;
import net.ib.util.service.TeacherService;
import net.ib.util.service.impl.TeacherServiceImpl;

public class TeacherAction extends ActionSupport{
	private static  Logger logger = Logger.getLogger(TeacherAction.class);
	
	private String TeacherID;
	private String TeacherName;
	private String Birthday;
	private String Gender;
	private String Identicard;
	private String Email;
	private String Phone;
	private String Address;
	private String message;
	private TeacherService teacherService;
	private String DeleteId;
	private String OldTeacherId;
	private String School_ID;
	private String TeacherDepartmentId;//ָ����ӽ�ʦ�Ļ����ڵ�
	
	




	
	
	public String getIdenticard() {
		return Identicard;
	}
	public void setIdenticard(String identicard) {
		Identicard = identicard;
	}
	public String getTeacherDepartmentId() {
		return TeacherDepartmentId;
	}
	public void setTeacherDepartmentId(String teacherDepartmentId) {
		TeacherDepartmentId = teacherDepartmentId;
	}
	public String getSchool_ID() {
		return School_ID;
	}
	public void setSchool_ID(String school_ID) {
		School_ID = school_ID;
	}
	public String getOldTeacherId() {
		return OldTeacherId;
	}
	public void setOldTeacherId(String oldTeacherId) {
		OldTeacherId = oldTeacherId;
	}
	public String getDeleteId() {
		return DeleteId;
	}
	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}
	public TeacherService getTeacherService() {
		return teacherService;
	}
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTeacherID() {
		return TeacherID;
	}
	public void setTeacherID(String teacherID) {
		this.TeacherID = teacherID;
	}
	public String getTeacherName() {
		return TeacherName;
	}
	public void setTeacherName(String teacherName) {
		this.TeacherName = teacherName;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		this.Birthday = birthday;
	}
	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		this.Gender = gender;
	}

	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		this.Phone = phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		this.Address = address;
		//ת��n��n2   \n��n1
		Address=Address.replaceAll("n", "n2");
		Address=Address.replaceAll("\n", "n1");
	}
	
	/*
	 * (��-Javadoc)
	 * <p>����: addTeacher</p>
	 * <p>˵��: ʵ����ӽ�ʦAction</p>
	 * @return
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String AddTeacher(){
		
		logger.debug(this.Gender+"---------------------");
		if(this.Gender==null){
			this.Gender="";
		}
		logger.debug(".........."+this.Gender);
		message=teacherService.addTeacher(TeacherDepartmentId,TeacherID, TeacherName, Birthday,
				Gender, Identicard, Email, Phone, Address);
		return null;
	}
	/*
	 * (��-Javadoc)
	 * <p>����: deleteTeacher</p>
	 * <p>˵��: ʵ��ɾ����ʦ����</p>
	 * @return
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String DeleteTeacher(){
	   	teacherService.DeleteTeacher(DeleteId);
	   	return null;
	}
	/*
	 * (��-Javadoc)
	 * <p>����: updateTeacher</p>
	 * <p>˵��: ʵ���޸Ľ�ʦ��Ϣ����</p>
	 * @return
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String UpdateTeacher(){
		teacherService.UpdateTeacher(OldTeacherId, School_ID, TeacherName, Birthday, Gender, Identicard, Email, Phone, Address);
		return null;
	}
	/*
	 * (��-Javadoc)
	 * <p>����: BulkDeleteTeacher</p>
	 * <p>˵��: ʵ������ɾ����ʦ����</p>
	 * @return
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String BulkDeleteTeacher(){
		
		teacherService.BulkDeleteTeacher(DeleteId);
		return null;
	}
}

	


