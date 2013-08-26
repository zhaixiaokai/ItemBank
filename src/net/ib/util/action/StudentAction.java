/*
 author:huangJu
 function:添加学生Action
 Date：2012-11-27
 */
package net.ib.util.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionSupport;

import net.ib.util.dao.*;
import net.ib.util.service.StudentService;

public class StudentAction extends ActionSupport{
	private static final Log logger = LogFactory.getLog(TeacherAction.class);
	
	private String StudentID;
	private String StudentName;
	private String Address;
	private String TelPhone;
	private String StudentDepartmentId;
	private String OldStudentId;
	private String DeleteId;
	private StudentService studentService;



	public String getTelPhone() {
		return TelPhone;
	}

	public void setTelPhone(String telPhone) {
		TelPhone = telPhone;
	}

	public String getDeleteId() {
		return DeleteId;
	}

	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}

	public String getOldStudentId() {
		return OldStudentId;
	}

	public void setOldStudentId(String oldStudentId) {
		OldStudentId = oldStudentId;
	}

	public String getStudentDepartmentId() {
		return StudentDepartmentId;
	}

	public void setStudentDepartmentId(String studentDepartmentId) {
		StudentDepartmentId = studentDepartmentId;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public String getStudentID() {
		return this.StudentID;
	}

	public void setStudentID(String studentID) {
		this.StudentID = studentID;
	}

	public String getStudentName() {
		return this.StudentName;
	}

	public void setStudentName(String studentName) {
		this.StudentName = studentName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
		//转义n→n2   \n→n1
		Address=Address.replaceAll("n", "n2");
		Address=Address.replaceAll("\n", "n1");
	}
	public static Log getLogger() {
		return logger;
	}


	//添加学生
	public String AddStudent(){
		/*
		 * (非-Javadoc)
		 * <p>名称: addTeacher</p>
		 * <p>说明: 实现添加学生Action</p>
		 * @return
		 * @see com.opensymphony.xwork2.ActionSupport#execute()
		 */
		studentService.addStudent(StudentDepartmentId,StudentID, StudentName, Address,TelPhone);
		return null;	
	}
	//删除学生
	public String DeleteStudent(){
		studentService.DeleteStudent(DeleteId);
		return null;
	}
	//修改学生信息
	public String UpdateStudent(){
		studentService.UpdateStudent(OldStudentId, StudentID, StudentName, Address,TelPhone);
		return null;
	}
	//批量删除学生
	public String BulkDeleteStudent(){
		
		studentService.BulkDeleteStudent(DeleteId);
		return null;
	}


	
	

}
