package net.ib.util.service;

public interface ExamSystemManageService {
	public String SortConfig() ;
	public String addEsSortConfig();
	public String UseConfig();
	public String ExamSystemAdd(String name,String discription,String identifier,String leafid,String config_leafid,String use);
	public String ExamSystemDelete(String id);
	public String ExamSystemBulkDelete(String id);
	public String ExamSystemSortConfig(String id);
	public String ModifySortConfig() ;
	public String ExamSystemModify(String id,String name,String discription,String identifier,String config_leafid,String use);  
	public String GetRoleData();  
	public String GetItembankBymajor(String majorid);  
	public String GetCurseNameBymajor(String curseid);  
	public String GetCurseNameBycurse(String curseid);  
	public String GetItembankBycurse(String curseid);  
	public String GetAuthorizedItembankByrole(String roleid,String memberid);  
	public String SaveAuthorityConfigByroleSelectnonecourse(String authorized_esid,String role,String member,String majorid);  
	public String SaveAuthorityConfigByroleSelectcourse(String authorized_esid,String role,String member,String courseid,String majorid);  
	public String CheckNumber(String number,String member);  
	public String GetAuthorizedItembankByuser(String number,String member);  
	public String SaveAuthorityConfigByuserSelectnonecourse(String authorized_esid,String number,String member,String majorid);  
	public String SaveAuthorityConfigByuserSelectcourse(String authorized_esid,String number,String member,String courseid,String majorid);  
	public String GetAuthorizedItembankBymembertype(String member);  
	public String SaveAuthorityConfigBymembertypeSelectnonecourse(String authorized_esid,String member,String majorid);  
	public String SaveAuthorityConfigBymembertypeSelectcourse(String authorized_esid,String member,String courseid,String majorid);  
	public String GetTeachingClass(String courseid);  
	public String ReferSchoolStructureOptionsGet();  
	public String ReferCurseSelectBySpecialField(String id);  
	public String GetAuthorizedItembankByteachingclass(String id);  
	public String SaveAuthorityConfigByteachingclassSelectnoneclass(String authorized_esid,String teachingclass,String majorid);  
	public String SaveAuthorityConfigByteachingclassSelectclass(String authorized_esid,String teachingclass,String courseid,String majorid);  
	public String GetSchoolStructure(String memberid);  
	public String GetAuthorizedItembankBydepartment(String memberid,String departmentid,String uppernode);  
	public String SaveAuthorityConfigBydepartmentSelectnonecourse(String authorized_es, String member,String departmentid,String majorid);  
	public String SaveAuthorityConfigBydepartmentSelectcourse(String authorized_es, String member,String departmentid,String courseid,String majorid);  
	public String ReferIfDefaultsort(String refernodeleaf);  
	public String ReferIfRootnode(String refernodeleaf);  
	
}
