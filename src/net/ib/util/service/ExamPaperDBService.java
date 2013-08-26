
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ExamPaperDBService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-12 下午7:19:17
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-12 下午7:19:17
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.ExamPaperDBService </p>
 * <p>描述：试卷库服务程序，定义创建、删除等功能接口</p>
 * <p></p>
 */
public interface ExamPaperDBService {
	
	public String PaperAdd(String name, String discription,String identifier,String  leafid, String config_leafid, String use);
	public String GetAuthorizedEPDBByrole(String roleid,String memberid);
	public String GetEPDBBymajor(String majorid);
	public String GetEPDBBycurse(String curseid);
	public String GetAuthorizedEPDBByuser(String number,String member);
	public String SaveEPDBAuthorityConfigByuser(String authorized_epdb,String number,String member);
	public String GetAuthorizedEPDBBydepartment(String memberid,String departmentid,String uppernode);
	public String SaveEPDBAuthorityConfigBydepartment(String authorized_epdb, String member,String departmentid);
	public String GetAuthorizedEPDBBymembertype(String member);
	public String SaveEPDBAuthorityConfigBymembertype(String authorized_epdbid,String member);
	public String GetAuthorizedEPDBByteachingclass(String id);
	public String SaveEPDBAuthorityConfigByteachingclass(String authorized_epdbid,String teachingclass);
	public String EPDBSortConfig(String id);
	public String EPDBBulkDelete(String id);
	public String EPDBDelete(String id);
	public String EPDBModify(String id, String name, String discription,
			String identifier, String config_leafid, String use);
	public String EPDBSelectByCurseId(String CurseFieldId);
	public String SaveEPDBAuthorityConfigByroleSelectnonecourse(
			String authorized_epdb, String role, String member,String majorid);
	public String SaveEPDBAuthorityConfigByroleSelectcourse(
			String authorized_epdb,String role,String member,String courseid,String majorid);
	public String SaveEPDBAuthorityConfigByuserSelectnonecourse(
			String authorized_epdb,String number, String member,String majorid);
	public String SaveEPDBAuthorityConfigByuserSelectcourse(
			String authorized_epdb,String number,String member,String courseid,String majorid);
	public String SaveEPDBAuthorityConfigBydepartmentSelectnonecourse(
			String authorized_epdb,String department, String member,String majorid);
	public String SaveEPDBAuthorityConfigBydepartmentSelectcourse(
			String authorized_epdb,String department,String member,String courseid,String majorid);
	public String SaveEPDBAuthorityConfigBymembertypeSelectnonecourse(String authorized_epdb, String member,String majorid);
	public String SaveEPDBAuthorityConfigBymembertypeSelectcourse(String authorized_epdb,String member,String courseid,String majorid);
	public String SaveEPDBAuthorityConfigByteachingclassSelectnonecourse(String authorized_epdb, String teachingclass,String majorid);
	public String SaveEPDBAuthorityConfigByteachingclassSelectcourse(String authorized_epdb,String teachingclass,String courseid,String majorid);
	public String PaperDelete(String id);
	public String PaperBulkDelete(String id);
	public String PaperModify(
			String paper_id,String newname,String newTotalScore,String newDuration,String newdifficulty);
	
	public String SelectEDBPByCourseAndUsage(String courseId,String usage);
}
