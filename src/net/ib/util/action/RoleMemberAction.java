package net.ib.util.action;

import net.ib.util.service.RoleMemberService;

public class RoleMemberAction {
	private String TeacherDepartmentId;
	private String StudentDepartmentId;
	private String RoleListId;
	private String BulkMemberId;
	private String DeleteId;
	private RoleMemberService RoleMemberService;
	
	
	public String getDeleteId() {
		return DeleteId;
	}
	public void setDeleteId(String deleteId) {
		DeleteId = deleteId;
	}
	public String getTeacherDepartmentId() {
		return TeacherDepartmentId;
	}
	public void setTeacherDepartmentId(String teacherDepartmentId) {
		TeacherDepartmentId = teacherDepartmentId;
	}
	public String getStudentDepartmentId() {
		return StudentDepartmentId;
	}
	public void setStudentDepartmentId(String studentDepartmentId) {
		StudentDepartmentId = studentDepartmentId;
	}
	public String getRoleListId() {
		return RoleListId;
	}
	public void setRoleListId(String roleListId) {
		RoleListId = roleListId;
	}
	public String getBulkMemberId() {
		return BulkMemberId;
	}
	public void setBulkMemberId(String bulkMemberId) {
		BulkMemberId = bulkMemberId;
	}

	public RoleMemberService getRoleMemberService() {
		return RoleMemberService;
	}
	public void setRoleMemberService(RoleMemberService roleMemberService) {
		RoleMemberService = roleMemberService;
	}
	
	//��ӽ�ɫ��Ա
	public String AddRoleMember(){
		String DepartmentId;
		if(TeacherDepartmentId==null)
			DepartmentId=StudentDepartmentId;
		else
			DepartmentId=TeacherDepartmentId;
		RoleMemberService.addRoleMember(RoleListId, DepartmentId,BulkMemberId);
		return null;
	}
	//ɾ����ɫ��Ա
	public String DeleteRoleMember(){
		RoleMemberService.deleteRoleMember(DeleteId);
		return null;
	}
	
	//����ɾ����ɫ��Ա
	public String BulkDeleteRoleMember(){
		RoleMemberService.bulkDeleteRoleMember(DeleteId);
		return null;
	}
	

}
