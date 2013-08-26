package net.ib.util.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import net.ib.util.service.ExamSystemManageService;

public class ExamSystemManageAction extends ActionSupport {
	private String leafid; // 试题库基本分类体系下叶子节点id
	private String config_leafid;// 试题库分类配置叶子节点id
	private String name;// 试题库名称
	private String discription;// 试题库说明
	private String use;// 试题库用途
	private String identifier;// 试题库标识符

	private String message;// 反馈信息
	private String deleteid;// 删除数据id
	private String sortconfigid;// 试题库修改分类配置id

	private String modifyid;// 修改试题库名称
	private String newname;// 修改试题库名称
	private String newdiscription;// 修改试题库说明
	private String newuse;// 修改试题库用途
	private String newidentifier;// 修改试题库标识符
	private String newconfig_leafid;// 修改试题库分类配置叶子节点id
	private String majorid;// 试题库授权按角色授权专业id
	private String curseid;// 试题库授权按角色授权课程id数组
	private String role;// 试题库授权按角色授权角色id
	private String member;// 试题库授权按角色授权成员类型id
	private String authorized_es;// 试题库授权按角色授权保存配置已授权试题库list
	private String number;// 试题库授权按用户授权，教工号/学号
	private String courseid;// 试题库授权按教学班级授权，课程id
	private String specialFieldId;// 试题库授权按教学班级授权，查询部分专业id
	private String teachingclass;// 试题库授权按教学班级授权，教学班级id
	private String membertypeid;// 试题库授权按机构授权，成员id
	private String departmentid;// 试题库授权按机构授权，机构id
	private String uppernode;// 试题库授权按机构授权，机构上级节点id
	private String refersortleaf;// 试题库查询，判断节点是否在基本分类体系下
	
	public String getRefersortleaf() {
		return refersortleaf;
	}

	public void setRefersortleaf(String refersortleaf) {
		this.refersortleaf = refersortleaf;
	}

	public String getUppernode() {
		return uppernode;
	}

	public void setUppernode(String uppernode) {
		this.uppernode = uppernode;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	public String getMembertypeid() {
		return membertypeid;
	}

	public void setMembertypeid(String membertypeid) {
		this.membertypeid = membertypeid;
	}

	public String getTeachingclass() {
		return teachingclass;
	}

	public void setTeachingclass(String teachingclass) {
		this.teachingclass = teachingclass;
	}

	public String getSpecialFieldId() {
		return specialFieldId;
	}

	public void setSpecialFieldId(String specialFieldId) {
		this.specialFieldId = specialFieldId;
	}

	

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAuthorized_es() {
		return authorized_es;
	}

	public void setAuthorized_es(String authorized_es) {
		this.authorized_es = authorized_es;
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

	public String getCurseid() {
		return curseid;
	}

	public void setCurseid(String curseid) {
		this.curseid = curseid;
	}

	public String getMajorid() {
		return majorid;
	}

	public void setMajorid(String majorid) {
		this.majorid = majorid;
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
		this.newdiscription=newdiscription.replaceAll("n", "n2");
		this.newdiscription=newdiscription.replaceAll("\n", "n1");
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

	public String getSortconfigid() {
		return sortconfigid;
	}

	public void setSortconfigid(String sortconfigid) {
		this.sortconfigid = sortconfigid;
	}

	public String getDeleteid() {
		return deleteid;
	}

	public void setDeleteid(String deleteid) {
		this.deleteid = deleteid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getLeafid() {
		return leafid;
	}

	public void setLeafid(String leafid) {
		this.leafid = leafid;
	}

	public String getConfig_leafid() {
		return config_leafid;
	}

	public void setConfig_leafid(String config_leafid) {
		this.config_leafid = config_leafid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
		this.discription=discription.replaceAll("n", "n2");
		this.discription=discription.replaceAll("\n", "n1");
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	private static Logger logger = Logger
			.getLogger(ExamSystemManageAction.class);
	private ExamSystemManageService ExamSystemManageAction; // 定义实现类

	public ExamSystemManageService getExamSystemManageAction() {
		return ExamSystemManageAction;
	}

	public void setExamSystemManageAction(
			ExamSystemManageService examSystemManageAction) {
		ExamSystemManageAction = examSystemManageAction;
	}

	/**
	 * 
	 * <p>
	 * 名称：sortConfig
	 * </p>
	 * <p>
	 * 说明：创建试题库分类体系配置
	 * </p>
	 * <p>
	 * 参数：@return 设定文件
	 * </p>
	 * <p>
	 * 返回值：String 返回类型
	 * </p>
	 * <p>
	 * @return
	 * </p>
	 */
	public String SortConfig() {
		ExamSystemManageAction.SortConfig();
		return null;
	}
	
	/**
	 * 
	 * <p>名称：addEsSortConfig</p>
	 * <p>说明：添加试题库分类体系列表获取，此处不包含基本分类体系TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String addEsSortConfig(){
		ExamSystemManageAction.addEsSortConfig();
		return null;
	}
	

	/**
	 * 
	 * <p>
	 * 名称：UseConfig
	 * </p>
	 * <p>
	 * 说明：添加试题库用途配置
	 * </p>
	 * <p>
	 * 参数：@return 设定文件
	 * </p>
	 * <p>
	 * 返回值：String 返回类型
	 * </p>
	 * <p>
	 * @return
	 * </p>
	 */
	public String UseConfig() {
		ExamSystemManageAction.UseConfig();
		return null;
	}

	/**
	 * 添加试题库信息入库
	 */
	public String ExamSystemAdd() {
		message = ExamSystemManageAction.ExamSystemAdd(name, discription,
				identifier, leafid, config_leafid, use);
		return "message";
	}

	/**
	 * 删除试题库，单条数据
	 */
	public String ExamSystemDelete() {
		message = ExamSystemManageAction.ExamSystemDelete(deleteid);
		return "message";
	}

	/**
	 * 批量删除试题库
	 */
	public String ExamSystemBulkDelete() {
		message = ExamSystemManageAction.ExamSystemBulkDelete(deleteid);
		return "message";
	}

	/**
	 * 修改试题库，试题库配置信息获取
	 */
	public String ExamSystemSortConfig() {
		message = ExamSystemManageAction.ExamSystemSortConfig(sortconfigid);
		return "message";
	}

	/**
	 * 修改试题库，试题库配置 分类体系信息获取
	 */
	public String ModifySortConfig() {
		message = ExamSystemManageAction.ModifySortConfig();
		return "message";
	}

	/**
	 * 修改试题库，试题库信息保存
	 */
	public String ExamSystemModify() {
		message = ExamSystemManageAction.ExamSystemModify(modifyid, newname,
				newdiscription, newidentifier, newconfig_leafid, newuse);
		return "message";
	}

	/**
	 * 试题库授权按角色授权获取角色数据
	 */
	public String GetRoleData() {
		ExamSystemManageAction.GetRoleData();
		return null;
	}

	/**
	 * 试题库授权按角色授权获取试题库数据
	 */
	public String GetItembankBymajor() {
		ExamSystemManageAction.GetItembankBymajor(majorid);
		return null;
	}

	/**
	 * 
		根据专业选择课程
	 */
	public String GetCurseNameBymajor() {
		ExamSystemManageAction.GetCurseNameBymajor(curseid);
		return null;
	}

	/**
	 * 通过课程id获取课程name，已选课程
	 */
	public String GetCurseNameBycurse() {
		ExamSystemManageAction.GetCurseNameBycurse(curseid);
		return null;
	}

	/**
	 * 通过课程id获取试题库信息，已选课程
	 */
	public String GetItembankBycurse() {
		ExamSystemManageAction.GetItembankBycurse(curseid);
		return null;
	}

	/**
	 * 试题库按角色授权，查询某角色成员的试题库权限
	 */
	public String GetAuthorizedItembankByrole() {
		ExamSystemManageAction.GetAuthorizedItembankByrole(role, member);
		return null;
	}

	/**
	 * 试题库按角色授权，保存配置，未选课程
	 */
	public String SaveAuthorityConfigByroleSelectnonecourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigByroleSelectnonecourse(
				authorized_es, role, member,majorid);
		return "message";
	}
	
	/**
	 * 试题库按角色授权，保存配置，选择课程
	 */
	public String SaveAuthorityConfigByroleSelectcourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigByroleSelectcourse(
				authorized_es, role, member,courseid,majorid);
		return "message";
	}

	/**
	 * 试题库按用户授权，判断教工号或学号是否输入正确
	 */
	public String CheckNumber() {
		ExamSystemManageAction.CheckNumber(number, member);
		return null;
	}
	
	/**
	 * 试题库按用户授权，查询权限
	 */
	public String GetAuthorizedItembankByuser() {
		ExamSystemManageAction.GetAuthorizedItembankByuser(number, member);
		return null;
	}

	/**
	 * 试题库按用户授权，保存配置，未选课程
	 */
	public String SaveAuthorityConfigByuserSelectnonecourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigByuserSelectnonecourse(
				authorized_es, number, member,majorid);
		return "message";
	}
	
	/**
	 * 试题库按用户授权，保存配置，未选课程
	 */
	public String SaveAuthorityConfigByuserSelectcourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigByuserSelectcourse(authorized_es, number, member,courseid,majorid);
		return "message";
	}

	/**
	 * 试题库按成员类型授权，查询权限
	 */
	public String GetAuthorizedItembankBymembertype() {
		ExamSystemManageAction.GetAuthorizedItembankBymembertype(member);
		return null;
	}

	/**
	 * 试题库按成员类型授权，保存配置，选择课程
	 */
	public String SaveAuthorityConfigBymembertypeSelectnonecourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigBymembertypeSelectnonecourse(
				authorized_es, member,majorid);
		return "message";
	}
	/**
	 * 试题库按成员类型授权，保存配置，未选课程
	 */
	public String SaveAuthorityConfigBymembertypeSelectcourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigBymembertypeSelectcourse(
				authorized_es, member,courseid,majorid);
		return "message";
	}
	
	
	/**
	 * 试题库按教学班级授权，根据课程查询教学班级
	 */
	public String GetTeachingClass() {
		ExamSystemManageAction.GetTeachingClass(courseid);
		return null;
	}
	
	
	/**
	 * 试题库按教学班级授权，获得查询机构列表
	 */
	public String ReferSchoolStructureOptionsGet() {
		ExamSystemManageAction.ReferSchoolStructureOptionsGet();
		return null;
	}
	
	/**
	 * 试题库按教学班级授权，获得查询班级
	 */
	public String ReferCurseSelectBySpecialField() {
		ExamSystemManageAction.ReferCurseSelectBySpecialField(specialFieldId);
		return null;
	}
	
	/**
	 * 试题库按教学班级授权，获得查询班级
	 */
	public String GetAuthorizedItembankByteachingclass() {
		ExamSystemManageAction.GetAuthorizedItembankByteachingclass(teachingclass);
		return null;
	}
	
	/**
	 * 试题库按班级授权，保存配置,未选课程
	 */
	public String SaveAuthorityConfigByteachingclassSelectnoneclass() {
		message = ExamSystemManageAction.SaveAuthorityConfigByteachingclassSelectnoneclass(authorized_es, teachingclass,majorid);
		return "message";
	}
	
	/**
	 * 试题库按班级授权，保存配置,已选课程
	 */
	public String SaveAuthorityConfigByteachingclassSelectclass() {
		message = ExamSystemManageAction.SaveAuthorityConfigByteachingclassSelectclass(authorized_es, teachingclass,courseid,majorid);
		return "message";
	}
	
	/**
	 * 试题库按组织机构授权，获取机构信息
	 */
	public String GetSchoolStructure() {
	 ExamSystemManageAction.GetSchoolStructure(membertypeid);
	 return null;
	}
	
	
	/**
	 * 试题库按组织机构授权，查询权限
	 */
	public String GetAuthorizedItembankBydepartment() {
		ExamSystemManageAction.GetAuthorizedItembankBydepartment(member,departmentid,uppernode);
		return null;
	}
	
	
	/**
	 * 试题库按机构授权，保存配置,未选课程
	 */
	public String SaveAuthorityConfigBydepartmentSelectnonecourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigBydepartmentSelectnonecourse(authorized_es, member,departmentid,majorid);
		return "message";
	}
	
	/**
	 * 试题库按机构授权，保存配置,未选课程
	 */
	public String SaveAuthorityConfigBydepartmentSelectcourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigBydepartmentSelectcourse(authorized_es, member,departmentid,courseid,majorid);
		return "message";
	}
	
	/**
	 * 
	 * <p>名称：ReferIfDefaultsort</p>
	 * <p>说明：试题库查询，判断节点是否在分类体系下TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String ReferIfDefaultsort() {
		message=ExamSystemManageAction.ReferIfDefaultsort(refersortleaf);
		return "message";
	}
	
	/**
	 * 
	 * <p>名称：ReferIfRootnode</p>
	 * <p>说明：判断选择的是否为根节点TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String ReferIfRootnode() {
		message=ExamSystemManageAction.ReferIfRootnode(refersortleaf);
		return "message";
	}

}
