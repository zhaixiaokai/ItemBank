
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ExamPaperDBAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-12 ����4:54:08
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-12 ����4:54:08
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import org.apache.log4j.Logger;

import net.ib.util.service.ExamPaperDBService;
import net.ib.util.service.impl.ExamPaperDBServiceImpl;


  /**
 * <p>������net.ib.util.action.ExamPaperDBAction </p>
 * <p>�������Ծ�����action</p>
 * <p></p>
 */
public class ExamPaperDBAction {

	// ����ʵ����(�˴�ֻ�Ƕ��壬û��ʵ�����������ʵ����ͨ��ExamPaperDBServiceImpl����)
	private ExamPaperDBService paperaction;

	private String leafid; // �Ծ�����������ϵ��Ҷ�ӽڵ�id
	private String config_leafid;// �Ծ���������Ҷ�ӽڵ�id
	private String EPName;// �Ծ������
	private String discription;// �Ծ��˵��
	private String use;// �Ծ����;
	private String EPIdentifier;// �Ծ���ʶ��
	private String role;// �Ծ����Ȩ����ɫ��Ȩ��ɫid
	private String member;// �Ծ����Ȩ��Ա����id
	private String authorized_epdb;// �Ծ����Ȩ������������Ȩ�Ծ��list
	private String majorid;//�Ծ����Ȩרҵid
	private String curseid;// �Ծ����Ȩ�γ�id����
	private String number;// �Ծ����Ȩ���û���Ȩ���̹���/ѧ��
	private String departmentid;// �Ծ����Ȩ��������Ȩ������id
	private String uppernode;// �Ծ����Ȩ��������Ȩ�������ϼ��ڵ�id
	private String teachingclass;// �Ծ����Ȩ����ѧ�༶��Ȩ����ѧ�༶id
	private String deleteid;// ɾ������id
	private String sortconfigid;// �Ծ���޸ķ�������id
	private String CurseFieldId;//�γ�Id
	private String courseid;//��Ȩ����ѡ��γ̵�id
	private String deletepaperid;
	
	private String message;// ������Ϣ

	private String modifyid;//��Ҫ�� �޸ĵ��Ծ���ʶ��
	private String newname;// �޸��Ծ������
	private String newdiscription;// �޸��Ծ��˵��
	private String newuse;// �޸��Ծ����;
	private String newidentifier;// ���Ծ���ʶ��
	private String newconfig_leafid;// �޸��Ծ���������Ҷ�ӽڵ�id
	
	
	private String modifyPaperId;// ��Ҫ���޸��Ծ������
	private String newPaperName;// ���Ծ�����
	private String newTotalScore;//���ܷ�
	private String newDuration;//�¿���ʱ��
	private String newdifficulty;//���Ѷ�ϵ��
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

		//ת��n��n2   \n��n1
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
	 * ����Ծ����Ϣ���
	 */
	public String PaperAdd() {
		message = paperaction.PaperAdd(EPName, discription,
				EPIdentifier, leafid, config_leafid, use);
		return "message1";
	}
	/**
	 * �Ծ�ⰴ��ɫ��Ȩ����ѯĳ��ɫ��Ա�������Ȩ��
	 */
	public String GetAuthorizedEPDBByrole() {
		paperaction.GetAuthorizedEPDBByrole(role, member);
		return null;
	}
	/**
	 * ͨ��רҵid��ȡ�������Ϣ����ѡרҵ
	 */
	public String GetEPDBBymajor() {
		message = paperaction.GetEPDBBymajor(majorid);
		return null;
	}
	/**
	 * ͨ���γ�id��ȡ�������Ϣ����ѡ�γ�
	 */
	public String GetEPDBBycurse(){
		message = paperaction.GetEPDBBycurse(curseid);
		return null;
	}
	
	
	/**
	 * �Ծ�ⰴ�û���Ȩ����ѯȨ��
	 */
	public String GetAuthorizedEPDBByuser(){
		message = paperaction.GetAuthorizedEPDBByuser(number, member);
		return null;
	}
	/**
	 * �Ծ�ⰴ�û���Ȩ����������
	 */
	public String SaveEPDBAuthorityConfigByuser(){
		message = paperaction.SaveEPDBAuthorityConfigByuser( authorized_epdb, number,member);
		return "message";
	}
	
	/**
	 * �Ծ�ⰴ��֯������Ȩ����ѯȨ��
	 */
	public String GetAuthorizedEPDBBydepartment() {
		paperaction.GetAuthorizedEPDBBydepartment(member,departmentid,uppernode);
		return null;
	}
	/**
	 * �Ծ�ⰴ������Ȩ����������
	 */
	public String SaveEPDBAuthorityConfigBydepartment() {
		message = paperaction.SaveEPDBAuthorityConfigBydepartment(authorized_epdb, member,departmentid);
		return "message";
	}
	
	
	/**
	 * �Ծ�ⰴ��֯������Ȩ����ѯȨ��
	 */
	public String GetAuthorizedEPDBBymembertype() {
		paperaction.GetAuthorizedEPDBBymembertype(member);
		return null;
	}
	/**
	 * �Ծ�ⰴ������Ȩ����������
	 */
	public String SaveEPDBAuthorityConfigBymembertype() {
		message = paperaction.SaveEPDBAuthorityConfigBymembertype(authorized_epdb, member);
		return "message";
	}
	
	
	
	/**
	 * �Ծ�ⰴ��ѧ�༶��Ȩ����ѯȨ��
	 */
	public String GetAuthorizedEPDBByteachingclass() {
		paperaction.GetAuthorizedEPDBByteachingclass(teachingclass);
		return null;
	}
	/**
	 * �Ծ�ⰴ��ѧ�༶��Ȩ����������
	 */
	public String SaveEPDBAuthorityConfigByteachingclass() {
		message = paperaction.SaveEPDBAuthorityConfigByteachingclass(authorized_epdb, teachingclass);
		return "message";
	}
	
	
	
	/**
	 * ɾ���Ծ�⣬��������
	 */
	public String EPDBDelete() {
		message = paperaction.EPDBDelete(deleteid);
		return "message";
	}

	/**
	 * ����ɾ���Ծ��
	 */
	public String EPDBBulkDelete() {
		message = paperaction.EPDBBulkDelete(deleteid);
		return "message";
	}

	/**
	 * �޸��Ծ�⣬�Ծ��������Ϣ��ȡ
	 */
	public String EPDBSortConfig() {
		message = paperaction.EPDBSortConfig(sortconfigid);
		return "message";
	}
	
	/**
	 * 
	 * <p>���ƣ�EPDBModify</p>
	 * <p>˵����TODO(�޸��Ծ�⣬�Ծ����Ϣ����)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String EPDBModify(){
		message = paperaction.EPDBModify(modifyid, newname,
				newdiscription, newidentifier, newconfig_leafid, newuse);
		return "message";	
	}
	
	/**
	 * 
	 * <p>���ƣ�EPDBSelectByCurseId</p>
	 * <p>˵����TODO(���ݿγ�ѡ���Ӧ�Ծ��)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String EPDBSelectByCurseId(){
		//logger.debug(CurseFieldId+"******************************************************");
		message = paperaction.EPDBSelectByCurseId(CurseFieldId);
		return "message";	
	}
	/**
	 * ����ⰴ��ɫ��Ȩ���������ã�δѡ�γ�
	 */
	public String SaveEPDBAuthorityConfigByroleSelectnonecourse() {
		message = paperaction.SaveEPDBAuthorityConfigByroleSelectnonecourse(
				authorized_epdb, role, member,majorid);
		return "message";
	}
	/**
	 * 
	 * <p>���ƣ�SaveEPDBAuthorityConfigByroleSelectcourse</p>
	 * <p>˵����TODO(�������Ȩ����ɫ��Ȩ,ѡ��γ�,��������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigByroleSelectcourse() {
		message = paperaction.SaveEPDBAuthorityConfigByroleSelectcourse(
				authorized_epdb, role, member,courseid,majorid);
		return "message";
	}
	/**
	 * �Ծ�ⰴ�û���Ȩ���������ã�δѡ�γ�
	 */
	public String SaveEPDBAuthorityConfigByuserSelectnonecourse() {
		message = paperaction.SaveEPDBAuthorityConfigByuserSelectnonecourse(
				authorized_epdb, number, member,majorid);
		return "message";
	}
	/**
	 * 
	 * <p>���ƣ�SaveEPDBAuthorityConfigByuserSelectcourse</p>
	 * <p>˵����TODO(�������Ȩ���û���Ȩ,ѡ��γ�,��������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigByuserSelectcourse() {
		message = paperaction.SaveEPDBAuthorityConfigByuserSelectcourse(
				authorized_epdb, number, member,courseid,majorid);
		return "message";
	}
	/**
	 * 
	 * <p>���ƣ�SaveEPDBAuthorityConfigBydepartmentSelectnonecourse</p>
	 * <p>˵����TODO(�Ծ�ⰴ������Ȩ���������ã�δѡ�γ�)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigBydepartmentSelectnonecourse(){
		message = paperaction.SaveEPDBAuthorityConfigBydepartmentSelectnonecourse(
			authorized_epdb, departmentid, member,majorid);
		return "message";
		}
	/**
	 * 
	 * <p>���ƣ�SaveEPDBAuthorityConfigBydepartmentSelectcourse</p>
	 * <p>˵����TODO(�Ծ�ⰴ������Ȩ���������ã���ѡ�γ�)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigBydepartmentSelectcourse(){
		message = paperaction.SaveEPDBAuthorityConfigBydepartmentSelectcourse(
			authorized_epdb, departmentid, member,courseid,majorid);
		return "message";
		}
	/**
	 * 
	 * <p>���ƣ�SaveEPDBAuthorityConfigBymembertypeSelectnonecourse</p>
	 * <p>˵����TODO(�Ծ�ⰴ��Ա������Ȩ���������ã�δѡ�γ�)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigBymembertypeSelectnonecourse(){
		message = paperaction.SaveEPDBAuthorityConfigBymembertypeSelectnonecourse(
			authorized_epdb,  member,majorid);
		return "message";
		}
	/**
	 * 
	 * <p>���ƣ�SaveEPDBAuthorityConfigBymembertypeSelectcourse</p>
	 * <p>˵����TODO((�Ծ�ⰴ��Ա������Ȩ���������ã���ѡ�γ�)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigBymembertypeSelectcourse(){
		message = paperaction.SaveEPDBAuthorityConfigBymembertypeSelectcourse(
			authorized_epdb,  member,courseid,majorid);
		return "message";
		}
	/**
	 * 
	 * <p>���ƣ�SaveEPDBAuthorityConfigByteachingclassSelectcourse</p>
	 * <p>˵����TODO(�Ծ�ⰴ�༶��Ȩ���������ã���ѡ�γ�)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigByteachingclassSelectcourse(){
		message = paperaction.SaveEPDBAuthorityConfigByteachingclassSelectcourse(
			authorized_epdb,teachingclass,courseid,majorid );
		return "message";
		}
	/**
	 * 
	 * <p>���ƣ�SaveEPDBAuthorityConfigByteachingclassSelectnonecourse</p>
	 * <p>˵����TODO(�Ծ�ⰴ�༶��Ȩ���������ã�δѡ�γ�)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String SaveEPDBAuthorityConfigByteachingclassSelectnonecourse(){
		message = paperaction.SaveEPDBAuthorityConfigByteachingclassSelectnonecourse(
			authorized_epdb,teachingclass,majorid );
		return "message";
		}
	/**
	 * 
	 * <p>���ƣ�PaperDelete</p>
	 * <p>˵����TODO(ɾ�������Ծ�)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	
	public String PaperDelete(){
		message = paperaction.PaperDelete(deletepaperid);
		return "message";
	}
	/**
	 * 
	 * <p>���ƣ�PaperBulkDelete</p>
	 * <p>˵����TODO(����ɾ���Ծ�)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String PaperBulkDelete(){
		message = paperaction.PaperBulkDelete(deleteid);
		return "message";
	}
	/**
	 * 
	 * <p>���ƣ�PaperModify</p>
	 * <p>˵����TODO(�޸��Ծ���Ϣ)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
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
