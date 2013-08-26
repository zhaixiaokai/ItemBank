
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ExamPaperDBAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-12 下午4:54:08
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-12 下午4:54:08
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import org.apache.log4j.Logger;

import net.ib.util.service.ExamPaperDBService;
import net.ib.util.service.impl.ExamPaperDBServiceImpl;


  /**
 * <p>类名：net.ib.util.action.ExamPaperDBAction </p>
 * <p>描述：试卷库相关action</p>
 * <p></p>
 */
public class ExamPaperDBAction {

	// 定义实现类(此处只是定义，没有实例化，具体的实现是通过ExamPaperDBServiceImpl引用)
	private ExamPaperDBService paperaction;

	private String leafid; // 试卷库基本分类体系下叶子节点id
	private String config_leafid;// 试卷库分类配置叶子节点id
	private String EPName;// 试卷库名称
	private String discription;// 试卷库说明
	private String use;// 试卷库用途
	private String EPIdentifier;// 试卷库标识符
	private String role;// 试卷库授权按角色授权角色id
	private String member;// 试卷库授权成员类型id
	private String authorized_epdb;// 试卷库授权保存配置已授权试卷库list
	private String majorid;//试卷库授权专业id
	private String curseid;// 试卷库授权课程id数组
	private String number;// 试卷库授权按用户授权，教工号/学号
	private String departmentid;// 试卷库授权按机构授权，机构id
	private String uppernode;// 试卷库授权按机构授权，机构上级节点id
	private String teachingclass;// 试卷库授权按教学班级授权，教学班级id
	private String deleteid;// 删除数据id
	private String sortconfigid;// 试卷库修改分类配置id
	private String CurseFieldId;//课程Id
	private String courseid;//授权中已选择课程的id
	private String deletepaperid;
	
	private String message;// 反馈信息

	private String modifyid;//将要被 修改的试卷库标识符
	private String newname;// 修改试卷库名称
	private String newdiscription;// 修改试卷库说明
	private String newuse;// 修改试卷库用途
	private String newidentifier;// 新试卷库标识符
	private String newconfig_leafid;// 修改试卷库分类配置叶子节点id
	
	
	private String modifyPaperId;// 将要被修改试卷的名称
	private String newPaperName;// 新试卷名称
	private String newTotalScore;//新总分
	private String newDuration;//新考试时长
	private String newdifficulty;//新难度系数
	private String curseId;
	public String getCurseId() {
		return curseId;
	}

	public void setCurseId(String curseId) {
		this.curseId = curseId;
	}

	public String getIBusage() {
		return IBusage;
	}

	public void setIBusage(String iBusage) {
		IBusage = iBusage;
	}

	private String IBusage;
	private static Logger logger = Logger.getLogger(ExamPaperDBAction.class);
	
	public ExamPaperDBService getPaperaction() {
		return paperaction;
	}

	public void setPaperaction(ExamPaperDBService paperaction) {
		this.paperaction = paperaction;
	}

	

	public String getModifyPaperId() {
		return modifyPaperId;
	}

	public void setModifyPaperId(String modifyPaperId) {
		this.modifyPaperId = modifyPaperId;
	}

	public String getNewPaperName() {
		return newPaperName;
	}

	public void setNewPaperName(String newPaperName) {
		this.newPaperName = newPaperName;
	}

	public String getNewTotalScore() {
		return newTotalScore;
	}

	public void setNewTotalScore(String newTotalScore) {
		this.newTotalScore = newTotalScore;
	}

	public String getNewDuration() {
		return newDuration;
	}

	public void setNewDuration(String newDuration) {
		this.newDuration = newDuration;
	}

	public String getNewdifficulty() {
		return newdifficulty;
	}

	public void setNewdifficulty(String newdifficulty) {
		this.newdifficulty = newdifficulty;
	}

	public String getAuthorized_epdb() {
		return authorized_epdb;
	}

	public void setAuthorized_epdb(String authorized_epdb) {
		this.authorized_epdb = authorized_epdb;
	}

	public String getDeletepaperid() {
		return deletepaperid;
	}

	public void setDeletepaperid(String deletepaperid) {
		this.deletepaperid = deletepaperid;
	}

	public String getLeafid() {
		return leafid;
	}

	public void setLeafid(String leafid) {
		this.leafid = leafid;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getConfig_leafid() {
		return config_leafid;
	}

	public void setConfig_leafid(String config_leafid) {
		this.config_leafid = config_leafid;
	}

	public String getEPName() {
		return EPName;
	}

	public void setEPName(String ePName) {
		EPName = ePName;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		
		this.discription = discription;

		//转义n→n2   \n→n1
		this.discription=this.discription.replaceAll("n", "n2");
		this.discription=this.discription.replaceAll("\n", "n1");
		//this.discription = discription;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getEPIdentifier() {
		return EPIdentifier;
	}

	public void setEPIdentifier(String ePIdentifier) {
		EPIdentifier = ePIdentifier;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}
	

	public String getMajorid() {
		return majorid;
	}

	public void setMajorid(String majorid) {
		this.majorid = majorid;
	}
	public String getCurseid() {
		return curseid;
	}

	public void setCurseid(String curseid) {
		this.curseid = curseid;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getUppernode() {
		return uppernode;
	}

	public void setUppernode(String uppernode) {
		this.uppernode = uppernode;
	}

	public String getTeachingclass() {
		return teachingclass;
	}

	public void setTeachingclass(String teachingclass) {
		this.teachingclass = teachingclass;
	}
	
	

	public String getDeleteid() {
		return deleteid;
	}

	public void setDeleteid(String deleteid) {
		this.deleteid = deleteid;
	}

	public String getSortconfigid() {
		return sortconfigid;
	}

	public void setSortconfigid(String sortconfigid) {
		this.sortconfigid = sortconfigid;
	}

	public String getModifyid() {
		return modifyid;
	}

	public void setModifyid(String modifyid) {
		this.modifyid = modifyid;
	}

	public String getNewname() {
		return newname;
	}

	public void setNewname(String newname) {
		this.newname = newname;
	}

	public String getNewdiscription() {
		return newdiscription;
	}

	public void setNewdiscription(String newdiscription) {
		this.newdiscription = newdiscription;
	}

	public String getNewuse() {
		return newuse;
	}

	public void setNewuse(String newuse) {
		this.newuse = newuse;
	}

	public String getNewidentifier() {
		return newidentifier;
	}

	public void setNewidentifier(String newidentifier) {
		this.newidentifier = newidentifier;
	}

	public String getNewconfig_leafid() {
		return newconfig_leafid;
	}

	public void setNewconfig_leafid(String newconfig_leafid) {
		this.newconfig_leafid = newconfig_leafid;
	}

	public String getCurseFieldId() {
		return CurseFieldId;
	}

	public void setCurseFieldId(String curseFieldId) {
		this.CurseFieldId = curseFieldId;
	}

	/**
	 * 添加试卷库信息入库
	 */
	public String PaperAdd() {
		message = paperaction.PaperAdd(EPName, discription,
				EPIdentifier, leafid, config_leafid, use);
		return "message1";
	}
	/**
	 * 试卷库按角色授权，查询某角色成员的试题库权限
	 */
	public String GetAuthorizedEPDBByrole() {
		paperaction.GetAuthorizedEPDBByrole(role, member);
		return null;
	}
	/**
	 * 通过专业id获取试题库信息，已选专业
	 */
	public String GetEPDBBymajor() {
		message = paperaction.GetEPDBBymajor(majorid);
		return null;
	}
	/**
	 * 通过课程id获取试题库信息，已选课程
	 */
	public String GetEPDBBycurse(){
		message = paperaction.GetEPDBBycurse(curseid);
		return null;
	}
	
	
	/**
	 * 试卷库按用户授权，查询权限
	 */
	public String GetAuthorizedEPDBByuser(){
		message = paperaction.GetAuthorizedEPDBByuser(number, member);
		return null;
	}
	/**
	 * 试卷库按用户授权，保存配置
	 */
	public String SaveEPDBAuthorityConfigByuser(){
		message = paperaction.SaveEPDBAuthorityConfigByuser( authorized_epdb, number,member);
		return "message";
	}
	
	/**
	 * 试卷库按组织机构授权，查询权限
	 */
	public String GetAuthorizedEPDBBydepartment() {
		paperaction.GetAuthorizedEPDBBydepartment(member,departmentid,uppernode);
		return null;
	}
	/**
	 * 试卷库按机构授权，保存配置
	 */
	public String SaveEPDBAuthorityConfigBydepartment() {
		message = paperaction.SaveEPDBAuthorityConfigBydepartment(authorized_epdb, member,departmentid);
		return "message";
	}
	
	
	/**
	 * 试卷库按组织机构授权，查询权限
	 */
	public String GetAuthorizedEPDBBymembertype() {
		paperaction.GetAuthorizedEPDBBymembertype(member);
		return null;
	}
	/**
	 * 试卷库按机构授权，保存配置
	 */
	public String SaveEPDBAuthorityConfigBymembertype() {
		message = paperaction.SaveEPDBAuthorityConfigBymembertype(authorized_epdb, member);
		return "message";
	}
	
	
	
	/**
	 * 试卷库按教学班级授权，查询权限
	 */
	public String GetAuthorizedEPDBByteachingclass() {
		paperaction.GetAuthorizedEPDBByteachingclass(teachingclass);
		return null;
	}
	/**
	 * 试卷库按教学班级授权，保存配置
	 */
	public String SaveEPDBAuthorityConfigByteachingclass() {
		message = paperaction.SaveEPDBAuthorityConfigByteachingclass(authorized_epdb, teachingclass);
		return "message";
	}
	
	
	
	/**
	 * 删除试卷库，单条数据
	 */
	public String EPDBDelete() {
		message = paperaction.EPDBDelete(deleteid);
		return "message";
	}

	/**
	 * 批量删除试卷库
	 */
	public String EPDBBulkDelete() {
		message = paperaction.EPDBBulkDelete(deleteid);
		return "message";
	}

	/**
	 * 修改试卷库，试卷库配置信息获取
	 */
	public String EPDBSortConfig() {
		message = paperaction.EPDBSortConfig(sortconfigid);
		return "message";
	}
	
	/**
	 * 
	 * <p>名称：EPDBModify</p>
	 * <p>说明：TODO(修改试卷库，试卷库信息保存)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String EPDBModify(){
		message = paperaction.EPDBModify(modifyid, newname,
				newdiscription, newidentifier, newconfig_leafid, newuse);
		return "message";	
	}
	
	/**
	 * 
	 * <p>名称：EPDBSelectByCurseId</p>
	 * <p>说明：TODO(根据课程选择对应试卷库)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String EPDBSelectByCurseId(){
		//logger.debug(CurseFieldId+"******************************************************");
		message = paperaction.EPDBSelectByCurseId(CurseFieldId);
		return "message";	
	}
	/**
	 * 试题库按角色授权，保存配置，未选课程
	 */
	public String SaveEPDBAuthorityConfigByroleSelectnonecourse() {
		message = paperaction.SaveEPDBAuthorityConfigByroleSelectnonecourse(
				authorized_epdb, role, member,majorid);
		return "message";
	}
	/**
	 * 
	 * <p>名称：SaveEPDBAuthorityConfigByroleSelectcourse</p>
	 * <p>说明：TODO(试题库授权按角色授权,选择课程,保存配置)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigByroleSelectcourse() {
		message = paperaction.SaveEPDBAuthorityConfigByroleSelectcourse(
				authorized_epdb, role, member,courseid,majorid);
		return "message";
	}
	/**
	 * 试卷库按用户授权，保存配置，未选课程
	 */
	public String SaveEPDBAuthorityConfigByuserSelectnonecourse() {
		message = paperaction.SaveEPDBAuthorityConfigByuserSelectnonecourse(
				authorized_epdb, number, member,majorid);
		return "message";
	}
	/**
	 * 
	 * <p>名称：SaveEPDBAuthorityConfigByuserSelectcourse</p>
	 * <p>说明：TODO(试题库授权按用户授权,选择课程,保存配置)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigByuserSelectcourse() {
		message = paperaction.SaveEPDBAuthorityConfigByuserSelectcourse(
				authorized_epdb, number, member,courseid,majorid);
		return "message";
	}
	/**
	 * 
	 * <p>名称：SaveEPDBAuthorityConfigBydepartmentSelectnonecourse</p>
	 * <p>说明：TODO(试卷库按机构授权，保存配置，未选课程)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigBydepartmentSelectnonecourse(){
		message = paperaction.SaveEPDBAuthorityConfigBydepartmentSelectnonecourse(
			authorized_epdb, departmentid, member,majorid);
		return "message";
		}
	/**
	 * 
	 * <p>名称：SaveEPDBAuthorityConfigBydepartmentSelectcourse</p>
	 * <p>说明：TODO(试卷库按机构授权，保存配置，已选课程)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigBydepartmentSelectcourse(){
		message = paperaction.SaveEPDBAuthorityConfigBydepartmentSelectcourse(
			authorized_epdb, departmentid, member,courseid,majorid);
		return "message";
		}
	/**
	 * 
	 * <p>名称：SaveEPDBAuthorityConfigBymembertypeSelectnonecourse</p>
	 * <p>说明：TODO(试卷库按成员类型授权，保存配置，未选课程)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigBymembertypeSelectnonecourse(){
		message = paperaction.SaveEPDBAuthorityConfigBymembertypeSelectnonecourse(
			authorized_epdb,  member,majorid);
		return "message";
		}
	/**
	 * 
	 * <p>名称：SaveEPDBAuthorityConfigBymembertypeSelectcourse</p>
	 * <p>说明：TODO((试卷库按成员类型授权，保存配置，已选课程)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigBymembertypeSelectcourse(){
		message = paperaction.SaveEPDBAuthorityConfigBymembertypeSelectcourse(
			authorized_epdb,  member,courseid,majorid);
		return "message";
		}
	/**
	 * 
	 * <p>名称：SaveEPDBAuthorityConfigByteachingclassSelectcourse</p>
	 * <p>说明：TODO(试卷库按班级授权，保存配置，已选课程)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigByteachingclassSelectcourse(){
		message = paperaction.SaveEPDBAuthorityConfigByteachingclassSelectcourse(
			authorized_epdb,teachingclass,courseid,majorid );
		return "message";
		}
	/**
	 * 
	 * <p>名称：SaveEPDBAuthorityConfigByteachingclassSelectnonecourse</p>
	 * <p>说明：TODO(试卷库按班级授权，保存配置，未选课程)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigByteachingclassSelectnonecourse(){
		message = paperaction.SaveEPDBAuthorityConfigByteachingclassSelectnonecourse(
			authorized_epdb,teachingclass,majorid );
		return "message";
		}
	/**
	 * 
	 * <p>名称：PaperDelete</p>
	 * <p>说明：TODO(删除单个试卷)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	
	public String PaperDelete(){
		message = paperaction.PaperDelete(deletepaperid);
		return "message";
	}
	/**
	 * 
	 * <p>名称：PaperBulkDelete</p>
	 * <p>说明：TODO(批量删除试卷)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String PaperBulkDelete(){
		message = paperaction.PaperBulkDelete(deleteid);
		return "message";
	}
	/**
	 * 
	 * <p>名称：PaperModify</p>
	 * <p>说明：TODO(修改试卷信息)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String PaperModify(){
		message = paperaction.PaperModify(modifyPaperId,newPaperName,newTotalScore,newDuration,newdifficulty);
		return "message";
	}
	
	public String SelectEDBPByCourseAndUsage(){
		message=paperaction.SelectEDBPByCourseAndUsage(this.curseId, this.IBusage);
		return null;
	}
}
