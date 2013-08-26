package net.ib.util.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import net.ib.util.service.ExamSystemManageService;

public class ExamSystemManageAction extends ActionSupport {
	private String leafid; // ��������������ϵ��Ҷ�ӽڵ�id
	private String config_leafid;// ������������Ҷ�ӽڵ�id
	private String name;// ���������
	private String discription;// �����˵��
	private String use;// �������;
	private String identifier;// ������ʶ��

	private String message;// ������Ϣ
	private String deleteid;// ɾ������id
	private String sortconfigid;// ������޸ķ�������id

	private String modifyid;// �޸����������
	private String newname;// �޸����������
	private String newdiscription;// �޸������˵��
	private String newuse;// �޸��������;
	private String newidentifier;// �޸�������ʶ��
	private String newconfig_leafid;// �޸�������������Ҷ�ӽڵ�id
	private String majorid;// �������Ȩ����ɫ��Ȩרҵid
	private String curseid;// �������Ȩ����ɫ��Ȩ�γ�id����
	private String role;// �������Ȩ����ɫ��Ȩ��ɫid
	private String member;// �������Ȩ����ɫ��Ȩ��Ա����id
	private String authorized_es;// �������Ȩ����ɫ��Ȩ������������Ȩ�����list
	private String number;// �������Ȩ���û���Ȩ���̹���/ѧ��
	private String courseid;// �������Ȩ����ѧ�༶��Ȩ���γ�id
	private String specialFieldId;// �������Ȩ����ѧ�༶��Ȩ����ѯ����רҵid
	private String teachingclass;// �������Ȩ����ѧ�༶��Ȩ����ѧ�༶id
	private String membertypeid;// �������Ȩ��������Ȩ����Աid
	private String departmentid;// �������Ȩ��������Ȩ������id
	private String uppernode;// �������Ȩ��������Ȩ�������ϼ��ڵ�id
	private String refersortleaf;// ������ѯ���жϽڵ��Ƿ��ڻ���������ϵ��
	
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
	private ExamSystemManageService ExamSystemManageAction; // ����ʵ����

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
	 * ���ƣ�sortConfig
	 * </p>
	 * <p>
	 * ˵������������������ϵ����
	 * </p>
	 * <p>
	 * ������@return �趨�ļ�
	 * </p>
	 * <p>
	 * ����ֵ��String ��������
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
	 * <p>���ƣ�addEsSortConfig</p>
	 * <p>˵�����������������ϵ�б��ȡ���˴�����������������ϵTODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String addEsSortConfig(){
		ExamSystemManageAction.addEsSortConfig();
		return null;
	}
	

	/**
	 * 
	 * <p>
	 * ���ƣ�UseConfig
	 * </p>
	 * <p>
	 * ˵��������������;����
	 * </p>
	 * <p>
	 * ������@return �趨�ļ�
	 * </p>
	 * <p>
	 * ����ֵ��String ��������
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
	 * ����������Ϣ���
	 */
	public String ExamSystemAdd() {
		message = ExamSystemManageAction.ExamSystemAdd(name, discription,
				identifier, leafid, config_leafid, use);
		return "message";
	}

	/**
	 * ɾ������⣬��������
	 */
	public String ExamSystemDelete() {
		message = ExamSystemManageAction.ExamSystemDelete(deleteid);
		return "message";
	}

	/**
	 * ����ɾ�������
	 */
	public String ExamSystemBulkDelete() {
		message = ExamSystemManageAction.ExamSystemBulkDelete(deleteid);
		return "message";
	}

	/**
	 * �޸�����⣬�����������Ϣ��ȡ
	 */
	public String ExamSystemSortConfig() {
		message = ExamSystemManageAction.ExamSystemSortConfig(sortconfigid);
		return "message";
	}

	/**
	 * �޸�����⣬��������� ������ϵ��Ϣ��ȡ
	 */
	public String ModifySortConfig() {
		message = ExamSystemManageAction.ModifySortConfig();
		return "message";
	}

	/**
	 * �޸�����⣬�������Ϣ����
	 */
	public String ExamSystemModify() {
		message = ExamSystemManageAction.ExamSystemModify(modifyid, newname,
				newdiscription, newidentifier, newconfig_leafid, newuse);
		return "message";
	}

	/**
	 * �������Ȩ����ɫ��Ȩ��ȡ��ɫ����
	 */
	public String GetRoleData() {
		ExamSystemManageAction.GetRoleData();
		return null;
	}

	/**
	 * �������Ȩ����ɫ��Ȩ��ȡ���������
	 */
	public String GetItembankBymajor() {
		ExamSystemManageAction.GetItembankBymajor(majorid);
		return null;
	}

	/**
	 * 
		����רҵѡ��γ�
	 */
	public String GetCurseNameBymajor() {
		ExamSystemManageAction.GetCurseNameBymajor(curseid);
		return null;
	}

	/**
	 * ͨ���γ�id��ȡ�γ�name����ѡ�γ�
	 */
	public String GetCurseNameBycurse() {
		ExamSystemManageAction.GetCurseNameBycurse(curseid);
		return null;
	}

	/**
	 * ͨ���γ�id��ȡ�������Ϣ����ѡ�γ�
	 */
	public String GetItembankBycurse() {
		ExamSystemManageAction.GetItembankBycurse(curseid);
		return null;
	}

	/**
	 * ����ⰴ��ɫ��Ȩ����ѯĳ��ɫ��Ա�������Ȩ��
	 */
	public String GetAuthorizedItembankByrole() {
		ExamSystemManageAction.GetAuthorizedItembankByrole(role, member);
		return null;
	}

	/**
	 * ����ⰴ��ɫ��Ȩ���������ã�δѡ�γ�
	 */
	public String SaveAuthorityConfigByroleSelectnonecourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigByroleSelectnonecourse(
				authorized_es, role, member,majorid);
		return "message";
	}
	
	/**
	 * ����ⰴ��ɫ��Ȩ���������ã�ѡ��γ�
	 */
	public String SaveAuthorityConfigByroleSelectcourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigByroleSelectcourse(
				authorized_es, role, member,courseid,majorid);
		return "message";
	}

	/**
	 * ����ⰴ�û���Ȩ���жϽ̹��Ż�ѧ���Ƿ�������ȷ
	 */
	public String CheckNumber() {
		ExamSystemManageAction.CheckNumber(number, member);
		return null;
	}
	
	/**
	 * ����ⰴ�û���Ȩ����ѯȨ��
	 */
	public String GetAuthorizedItembankByuser() {
		ExamSystemManageAction.GetAuthorizedItembankByuser(number, member);
		return null;
	}

	/**
	 * ����ⰴ�û���Ȩ���������ã�δѡ�γ�
	 */
	public String SaveAuthorityConfigByuserSelectnonecourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigByuserSelectnonecourse(
				authorized_es, number, member,majorid);
		return "message";
	}
	
	/**
	 * ����ⰴ�û���Ȩ���������ã�δѡ�γ�
	 */
	public String SaveAuthorityConfigByuserSelectcourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigByuserSelectcourse(authorized_es, number, member,courseid,majorid);
		return "message";
	}

	/**
	 * ����ⰴ��Ա������Ȩ����ѯȨ��
	 */
	public String GetAuthorizedItembankBymembertype() {
		ExamSystemManageAction.GetAuthorizedItembankBymembertype(member);
		return null;
	}

	/**
	 * ����ⰴ��Ա������Ȩ���������ã�ѡ��γ�
	 */
	public String SaveAuthorityConfigBymembertypeSelectnonecourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigBymembertypeSelectnonecourse(
				authorized_es, member,majorid);
		return "message";
	}
	/**
	 * ����ⰴ��Ա������Ȩ���������ã�δѡ�γ�
	 */
	public String SaveAuthorityConfigBymembertypeSelectcourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigBymembertypeSelectcourse(
				authorized_es, member,courseid,majorid);
		return "message";
	}
	
	
	/**
	 * ����ⰴ��ѧ�༶��Ȩ�����ݿγ̲�ѯ��ѧ�༶
	 */
	public String GetTeachingClass() {
		ExamSystemManageAction.GetTeachingClass(courseid);
		return null;
	}
	
	
	/**
	 * ����ⰴ��ѧ�༶��Ȩ����ò�ѯ�����б�
	 */
	public String ReferSchoolStructureOptionsGet() {
		ExamSystemManageAction.ReferSchoolStructureOptionsGet();
		return null;
	}
	
	/**
	 * ����ⰴ��ѧ�༶��Ȩ����ò�ѯ�༶
	 */
	public String ReferCurseSelectBySpecialField() {
		ExamSystemManageAction.ReferCurseSelectBySpecialField(specialFieldId);
		return null;
	}
	
	/**
	 * ����ⰴ��ѧ�༶��Ȩ����ò�ѯ�༶
	 */
	public String GetAuthorizedItembankByteachingclass() {
		ExamSystemManageAction.GetAuthorizedItembankByteachingclass(teachingclass);
		return null;
	}
	
	/**
	 * ����ⰴ�༶��Ȩ����������,δѡ�γ�
	 */
	public String SaveAuthorityConfigByteachingclassSelectnoneclass() {
		message = ExamSystemManageAction.SaveAuthorityConfigByteachingclassSelectnoneclass(authorized_es, teachingclass,majorid);
		return "message";
	}
	
	/**
	 * ����ⰴ�༶��Ȩ����������,��ѡ�γ�
	 */
	public String SaveAuthorityConfigByteachingclassSelectclass() {
		message = ExamSystemManageAction.SaveAuthorityConfigByteachingclassSelectclass(authorized_es, teachingclass,courseid,majorid);
		return "message";
	}
	
	/**
	 * ����ⰴ��֯������Ȩ����ȡ������Ϣ
	 */
	public String GetSchoolStructure() {
	 ExamSystemManageAction.GetSchoolStructure(membertypeid);
	 return null;
	}
	
	
	/**
	 * ����ⰴ��֯������Ȩ����ѯȨ��
	 */
	public String GetAuthorizedItembankBydepartment() {
		ExamSystemManageAction.GetAuthorizedItembankBydepartment(member,departmentid,uppernode);
		return null;
	}
	
	
	/**
	 * ����ⰴ������Ȩ����������,δѡ�γ�
	 */
	public String SaveAuthorityConfigBydepartmentSelectnonecourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigBydepartmentSelectnonecourse(authorized_es, member,departmentid,majorid);
		return "message";
	}
	
	/**
	 * ����ⰴ������Ȩ����������,δѡ�γ�
	 */
	public String SaveAuthorityConfigBydepartmentSelectcourse() {
		message = ExamSystemManageAction.SaveAuthorityConfigBydepartmentSelectcourse(authorized_es, member,departmentid,courseid,majorid);
		return "message";
	}
	
	/**
	 * 
	 * <p>���ƣ�ReferIfDefaultsort</p>
	 * <p>˵����������ѯ���жϽڵ��Ƿ��ڷ�����ϵ��TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String ReferIfDefaultsort() {
		message=ExamSystemManageAction.ReferIfDefaultsort(refersortleaf);
		return "message";
	}
	
	/**
	 * 
	 * <p>���ƣ�ReferIfRootnode</p>
	 * <p>˵�����ж�ѡ����Ƿ�Ϊ���ڵ�TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String ReferIfRootnode() {
		message=ExamSystemManageAction.ReferIfRootnode(refersortleaf);
		return "message";
	}

}
