
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TableDataAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-5 ����2:33:28
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-5 ����2:33:28
 * |  2 lijuan
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils.Null;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.dao.Dao;
import net.ib.util.service.CreateDataTableJsonForYuiService;
import net.ib.util.service.Service;
import net.ib.util.service.impl.TypeChange;


  /**
 * <p>������test.YuiTableServAction </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class TableDataAction {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(TableDataAction.class);
	private	String	results;
	private	String	startIndex;
	private	String	sort;
	private	String	dir;
	private	String	FieldId;
	private String MemberType;//��Ա����
	private	String  TeachingClassId;
	private	String  TeacherId;
	private	String  TeacherIds;
	private String  selectedCurseId;//�ɼ���ѯ�еĿγ�id
	private String  SelectedTeachingClassId;//�ɼ���ѯ�еĿ��ΰ༶id
	private String SchoolId;//�ɼ���ѯ��ѧ��ѧ��
	private String SelectedClassId;//�ɼ���ѯ�����������༶��id
	private String itembank_id;
	private String selectpointid;
	private String selectedEPDB;//ѡ�е��Ծ�⣬���ݴ��Ծ�������Ӧ���Ծ�
	private	String IBIds;
	private	String	KPIds;
	private	String	kp;
	private	String 	dif;
	private	String	type;
	private String 	specialFieldId;
	public String getSpecialFieldId() {
		return specialFieldId;
	}
	public void setSpecialFieldId(String specialFieldId) {
		this.specialFieldId = specialFieldId;
	}
	public String getKp() {
		return kp;
	}
	public void setKp(String kp) {
		this.kp = kp;
	}
	public String getDif() {
		return dif;
	}
	public void setDif(String dif) {
		this.dif = dif;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKPIds() {
		return KPIds;
	}
	public void setKPIds(String kPIds) {
		KPIds = kPIds;
	}
	public String getIBIds() {
		return IBIds;
	}
	public void setIBIds(String iBIds) {
		IBIds = iBIds;
	}
	public String getSelectedEPDB() {
		return selectedEPDB;
	}
	public void setSelectedEPDB(String selectedEPDB) {
		this.selectedEPDB = selectedEPDB;
	}
	public String getSelectpointid() {
		return selectpointid;
	}
	public void setSelectpointid(String selectpointid) {
		this.selectpointid = selectpointid;
	}
	public String getSelecttypeid() {
		return selecttypeid;
	}
	public void setSelecttypeid(String selecttypeid) {
		this.selecttypeid = selecttypeid;
	}
	public String getSelectdifficultyid() {
		return selectdifficultyid;
	}
	public void setSelectdifficultyid(String selectdifficultyid) {
		this.selectdifficultyid = selectdifficultyid;
	}

	private String selecttypeid;
	private String selectdifficultyid;

	public String getItembank_id() {
		return itembank_id;
	}
	public void setItembank_id(String itembank_id) {
		this.itembank_id = itembank_id;
	}
	public String getSelectedClassId() {
		return SelectedClassId;
	}
	public void setSelectedClassId(String selectedClassId) {
		SelectedClassId = selectedClassId;
	}
	public String getSchoolId() {
		return SchoolId;
	}
	public void setSchoolId(String schoolId) {
		SchoolId = schoolId;
	}
	public String getSelectedCurseId() {
		return selectedCurseId;
	}
	public void setSelectedCurseId(String selectedCurseId) {
		this.selectedCurseId = selectedCurseId;
	}
	public String getSelectedTeachingClassId() {
		return SelectedTeachingClassId;
	}
	public void setSelectedTeachingClassId(String selectedTeachingClassId) {
		SelectedTeachingClassId = selectedTeachingClassId;
	}

	private	String	DataDicId;
	private	String	referuse;//������ѯ�������;
	private	String	if_under_defaultsort;
	public String getIf_under_defaultsort() {
		return if_under_defaultsort;
	}
	public void setIf_under_defaultsort(String if_under_defaultsort) {
		this.if_under_defaultsort = if_under_defaultsort;
	}
	public String getReferuse() {
		return referuse;
	}
	public void setReferuse(String referuse) {
		this.referuse = referuse;
	}
	public String getRefer_leafnode() {
		return refer_leafnode;
	}
	public void setRefer_leafnode(String refer_leafnode) {
		this.refer_leafnode = refer_leafnode;
	}

	private	String	refer_leafnode;//������ѯ������������Ҷ�ӽڵ�
	
	public String getDataDicId() {
		return DataDicId;
	}
	public void setDataDicId(String dataDicId) {
		DataDicId = dataDicId;
	}
	public String getFieldId() {
		return FieldId;
	}
	public void setFieldId(String fieldId) {
		FieldId = fieldId;
	}

	
	public String getTeachingClassId() {
		return TeachingClassId;
	}
	public void setTeachingClassId(String teachingClassId) {
		TeachingClassId = teachingClassId;
	}
	
	public String getTeacherId() {
		return TeacherId;
	}
	public void setTeacherId(String teacherId) {
		TeacherId = teacherId;
	}
	
	public String getTeacherIds() {
		return TeacherIds;
	}
	public void setTeacherIds(String teacherIds) {
		TeacherIds = teacherIds;
	}


	public String getMemberType() {
		return MemberType;
	}
	public void setMemberType(String memberType) {
		MemberType = memberType;
	}

	private	Dao	dao;
	private	Service	service;
	private	CreateDataTableJsonForYuiService CDTJFYS;
	public CreateDataTableJsonForYuiService getCDTJFYS() {
		return CDTJFYS;
	}
	public void setCDTJFYS(CreateDataTableJsonForYuiService cDTJFYS) {
		CDTJFYS = cDTJFYS;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public Dao getDao() {
		logger.debug("��ʼ��dao  GET");
		return dao;
	}
	public void setDao(Dao dao) {
		logger.debug("��ʼ��dao  SET");
		this.dao = dao;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}

	public String CreateDataTableJson() {
		String getDataSql = "select rownum rn,cu.* from test cu";// �кű�������Ϊrn
		String getCountSql = "test cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�SortSystemManage</p>
	 * <p>˵����������ϵ���������ݻ�ȡ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String SortSystemManage() {
		String getDataSql = "select rownum rn,cu.classification_id,"
				+ "cu.classification_name,"
				+"(case when classification_explain is null then '��' else classification_explain end) classification_explain,"
				+ "(case if_default_classification when 0 then '��' when"
				+ " 1 then '��' end) if_default_classification from  "
				+ "sys_ib_classification_system cu";// �кű�������Ϊrn
		String getCountSql = "sys_ib_classification_system cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	
	/**
	 * ��������������
	 */
	public String ExamSystemManage() {
		//������е�paperuse�ȸ���Ϊ�����ֵ��ж�Ӧ��������
		String useconfig="";
		List<Map> usedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='paperuse'");
		for (int i = 0; i < usedata.size(); i++) {
			String entries_value=(String) usedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) usedata.get(i).get("name");
		    useconfig+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		
		String getDataSql = "select rownum rn,cu.itembank_id,cu.itembank_name,"
				+"(case when explain is null then '��' else explain end) explain,"
				+"(case use "+useconfig+" end) use from  "
				+"sys_itembank_list cu";// �кű�������Ϊrn
		String getCountSql = "sys_itembank_list cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	/**
	 * ���ݲ�ѯ������ȡ�����������
	 */
	
	public String ReferDataSource() {
		//���ַ���ת��Ϊ����
		ArrayList<String> leafnode=new ArrayList<String>();
		if(refer_leafnode.indexOf(",")!=-1){
			String[] tempArray=refer_leafnode.split(",");
			for(int i=0;i<tempArray.length;i++){
				leafnode.add(tempArray[i]);
			}
		}else{
			leafnode.add(refer_leafnode);
		}

	//�������ֵ���ȡ����Ӧֵ��
		String useconfig="";
		List<Map> usedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='paperuse'");
		for (int i = 0; i < usedata.size(); i++) {
			String entries_value=(String) usedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) usedata.get(i).get("name");
		    useconfig+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		//���ڵ�ǻ���������ϵ�½ڵ�
		if(if_under_defaultsort.equals("0")){
			//����Ҷ�ӽڵ�id��sys_ib_classification_node�в��Ҷ�Ӧ�����
			ArrayList<String> itembank=new ArrayList<String>();
			for(int i=0;i<leafnode.size();i++){
				String node=(String) leafnode.get(i);
				List<Map> eslist=dao.executeQuery("select itembank_id from sys_ib_classification_node where node_id='"+node+"'");
				for (int j = 0; j< eslist.size(); j++) {
					String es_id=(String) eslist.get(j).get("itembank_id");
					itembank.add(es_id);
				}
			}
			//sqlƴ��,�����id����
			//�ж��Ƿ��������
			String getDataSql="";
			String getCountSql="";
			if(itembank.size()<1){
			getDataSql = "select rownum rn,cu.itembank_id,cu.itembank_name,"
						+"(case when explain is null then '��' else explain end) explain,"
						+"(case use "+useconfig+" end) use from  "
						+"sys_itembank_list cu where use='"+referuse+"' AND itembank_id=''";// �кű�������Ϊrn
				 getCountSql = "sys_itembank_list cu where use='"+referuse+"' AND itembank_id=''";
			}else{
				String es_sql="";
				for (int j = 0; j< itembank.size(); j++) {
					if(es_sql!=""){
						es_sql=es_sql+" or ";
					}
					String es_id=itembank.get(j);
					es_sql+="itembank_id="+"'"+es_id+"'";
				}
				es_sql="("+es_sql+")";
				//��sys_itembank_list�в�������
			getDataSql = "select rownum rn,cu.itembank_id,cu.itembank_name,"
						+"(case when explain is null then '��' else explain end) explain,"
						+"(case use "+useconfig+" end) use from  "
						+"sys_itembank_list cu where use='"+referuse+"' AND "+es_sql+"";// �кű�������Ϊrn
			//logger.debug("%%%%%%%%%%%%&&%%%%%%%%%%"+getDataSql);
				getCountSql = "sys_itembank_list cu where use='"+referuse+"' AND "+es_sql+"";
			}
	
			CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
					getCountSql);
			
		}else{
			//����Ҷ�ӽڵ�id�������пγ�
			ArrayList<String> subject=new ArrayList<String>();
			for(int i=0;i<leafnode.size();i++){
				String node=(String) leafnode.get(i);
				List<Map> subjectlist	=	dao.executeQuery("select curse_id from sys_curse where special_field_id='"+node+"'");
				for (int j = 0; j< subjectlist.size(); j++) {
					String subject_id=(String) subjectlist.get(j).get("curse_id");
					subject.add(subject_id);
				}
			}
		//	���ݿγ�id��itembank_list�в������������
			//�жϿγ������Ƿ�Ϊ��
			String getDataSql="";
			String getCountSql="";
			logger.debug(subject.size());
			if(subject.size()<1){
			 getDataSql = "select rownum rn,cu.itembank_id,cu.itembank_name,"
						+"(case when explain is null then '��' else explain end) explain,"
						+"(case use "+useconfig+" end) use from  "
						+"sys_itembank_list cu where use='"+referuse+"' AND curse_id=''";// �кű�������Ϊrn
				 getCountSql = "sys_itembank_list cu where use='"+referuse+"' AND curse_id=''";
					logger.debug(getDataSql);

			}else{
				String curse_sql="";
				for(int j=0;j<subject.size();j++){
					String subjectid=subject.get(j);
					if(curse_sql!=""){
						curse_sql=curse_sql+" or ";
					}
					curse_sql+="curse_id="+"'"+subjectid+"'";
				}
				curse_sql="("+curse_sql+")";
				logger.debug(curse_sql);

				 getDataSql = "select rownum rn,cu.itembank_id,cu.itembank_name,"
						+"(case when explain is null then '��' else explain end) explain,"
						+"(case use "+useconfig+" end) use from  "
						+"sys_itembank_list cu where use='"+referuse+"' AND "+curse_sql+"";// �кű�������Ϊrn
				getCountSql = "sys_itembank_list cu where use='"+referuse+"' AND "+curse_sql+"";

			}
	
			CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
					getCountSql);
		}
			 
			
		
		
		return null;
	}
	/**
	 * ���ݲ�ѯ������ȡ�Ծ��������
	 */
	
	public String ReferEPDB() {
		
		//logger.debug(referuse+"++++++++++++++++++++++++++++++++++");
		//���ַ���ת��Ϊ����
		String[] leafnode=refer_leafnode.split(",");
		//�������ֵ���ȡ����Ӧֵ��
		String useconfig="";
		List<Map> usedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='paperuse'");
		for (int i = 0; i < usedata.size(); i++) {
			String entries_value=(String) usedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) usedata.get(i).get("name");
		    useconfig+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		//���ڵ�ǻ���������ϵ�½ڵ�
				if(if_under_defaultsort.equals("0")){
					//����Ҷ�ӽڵ�id��sys_epdb_classification_node�в��Ҷ�Ӧ�����
					ArrayList<String> epdb=new ArrayList<String>();
					for(int i=0;i<leafnode.length;i++){
						String node=leafnode[i];
						List<Map> eslist	=	dao.executeQuery("select epdb_id from sys_epdb_classification_node where node_id='"+node+"'");
						for (int j = 0; j< eslist.size(); j++) {
							String es_id=(String) eslist.get(j).get("epdb_id");
							epdb.add(es_id);
						}
					}
					//sqlƴ��,�����id����
					//�ж��Ƿ��������
					String getDataSql="";
					String getCountSql="";
					
				
					if(epdb.size()<1){
						
					getDataSql = "select rownum rn,cu.epdb_id,cu.epdb_name,"
								+"(case when explain is null then '��' else explain end) explain,"
								+"(case uagse "+useconfig+" end) usage from  "
								+"sys_epdb_list cu where usage='"+referuse+"' AND epdb_id=''";// �кű�������Ϊrn
						 getCountSql = "sys_epdb_list cu where usage='"+referuse+"' AND epdb_id=''";
					}else{
						String es_sql="";
						for (int j = 0; j< epdb.size(); j++) {
							if(es_sql!=""){
								es_sql=es_sql+" or ";
							}
							String es_id=epdb.get(j);
							es_sql+="epdb_id="+"'"+es_id+"'";
						}
						es_sql="("+es_sql+")";
						//��sys_epdb_list�в�������
					getDataSql = "select rownum rn,cu.epdb_id,cu.epdb_name,"
								+"(case when explain is null then '��' else explain end) explain,"
								+"(case usage "+useconfig+" end) usage from  "
								+"sys_epdb_list cu where usage='"+referuse+"' AND "+es_sql+"";// �кű�������Ϊrn
					//logger.debug("%%%%%%%%%%%%&&%%%%%%%%%%"+getDataSql);
						getCountSql = "sys_epdb_list cu where usage='"+referuse+"' AND "+es_sql+"";
					}
			
					CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
							getCountSql);
					
				}else{
					//����Ҷ�ӽڵ�id�������пγ�
					ArrayList<String> subject=new ArrayList<String>();
					for(int i=0;i<leafnode.length;i++){
						String node=leafnode[i];
						List<Map> subjectlist	=	dao.executeQuery("select curse_id from sys_curse where special_field_id='"+node+"'");
						for (int j = 0; j< subjectlist.size(); j++) {
							String subject_id=(String) subjectlist.get(j).get("curse_id");
							subject.add(subject_id);
						}
					}
				//	���ݿγ�id��epdbk_list�в������������
					//�жϿγ������Ƿ�Ϊ��
					String getDataSql="";
					String getCountSql="";
					logger.debug(subject.size());
					if(subject.size()<1){
					 getDataSql = "select rownum rn,cu.epdb_id,cu.epdb_name,"
								+"(case when explain is null then '��' else explain end) explain,"
								+"(case usage "+useconfig+" end) usage from  "
								+"sys_epdb_list cu where usage='"+referuse+"' AND curse_id=''";// �кű�������Ϊrn
						 getCountSql = "sys_epdb_list cu where usage='"+referuse+"' AND curse_id=''";
							logger.debug(getDataSql);

					}else{
						String curse_sql="";
						for(int j=0;j<subject.size();j++){
							String subjectid=subject.get(j);
							if(curse_sql!=""){
								curse_sql=curse_sql+" or ";
							}
							curse_sql+="curse_id="+"'"+subjectid+"'";
						}
						curse_sql="("+curse_sql+")";
						logger.debug(curse_sql);

						 getDataSql = "select rownum rn,cu.epdb_id,cu.epdb_name,"
								+"(case when explain is null then '��' else explain end) explain,"
								+"(case usage "+useconfig+" end) usage from  "
								+"sys_epdb_list cu where usage='"+referuse+"' AND "+curse_sql+"";// �кű�������Ϊrn
						getCountSql = "sys_epdb_list cu where usage='"+referuse+"' AND "+curse_sql+"";

					}
			
					CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
							getCountSql);
				}
				return null;
	}
		
	/**
	 * 
	 * <p>���ƣ�QuesViewTableSource</p>
	 * <p>˵�����鿴���⣬������ݻ�ȡTODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	//xiaokaiPoint
	public String QuesViewTableSource(){
		logger.debug(itembank_id);
		//��������ת��
		String quesTypeSql="";
		List<Map> quesTypedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='tixing'");
		for (int i = 0; i < quesTypedata.size(); i++) {
			String entries_value=(String) quesTypedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) quesTypedata.get(i).get("name");
			quesTypeSql+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		//�Ѷ�����ת��
		List<Map> quesDifficultyTypeData	=	dao.executeQuery("select dictionary_entries_value,dictionary_entries_id,name,value from sys_dictionary_entries_value where dictionary_entries_id='difficulty'");
		for (int i = 0; i < quesDifficultyTypeData.size(); i++) {
			Map<String, String> TypeElement=quesDifficultyTypeData.get(i);
			String type_entries=(String) quesDifficultyTypeData.get(i).get("dictionary_entries_value");
			List<Map> quesDifficultyValueData	=	dao.executeQuery("select dictionary_entries_value,name,value from sys_dictionary_entries_value where dictionary_entries_value like '%"+type_entries+"%' and dictionary_entries_id='difficulty_value' ");
			for(int j=0;j<quesDifficultyValueData.size();j++){
				String type_value_entries=(String) quesDifficultyValueData.get(j).get("dictionary_entries_value");
				String type_value=(String) quesDifficultyValueData.get(j).get("value");
				if(type_value_entries.equals(""+type_entries+"_T")){
					TypeElement.put("value_T", type_value);
				}
				if(type_value_entries.equals(""+type_entries+"_B")){
					TypeElement.put("value_B", type_value);
				}
			}
		}
		
		//֪ʶ��ת��
		String quesPointSql="";
		List<Map> courseidlist	=	dao.executeQuery("select curse_id,itembank_id from sys_itembank_list where itembank_id='"+itembank_id+"'");
		String courseid=(String) courseidlist.get(0).get("curse_id");
		List<Map> quesPointdata	=	dao.executeQuery("select knowledge_point_id,name,curse_id from sys_knowledge_point where curse_id='"+courseid+"'");
		for (int i = 0; i < quesPointdata.size(); i++) {
			String point_name=(String) quesPointdata.get(i).get("name");
			String point_id=(String) quesPointdata.get(i).get("knowledge_point_id");
			quesPointSql+=" when '"+point_id+"' then '"+point_name+"'";
			}
		
		String quesDifficultySql="";
		for (int i = 0; i < quesDifficultyTypeData.size(); i++) {
			String dictionary_entries_value=(String) quesDifficultyTypeData.get(i).get("dictionary_entries_value");
			String name=(String) quesDifficultyTypeData.get(i).get("name");
			String value_B=(String) quesDifficultyTypeData.get(i).get("value_B");
			java.math.BigDecimal int_value_B = new java.math.BigDecimal(value_B) ;
			String value_T=(String) quesDifficultyTypeData.get(i).get("value_T");
			java.math.BigDecimal int_value_T = new java.math.BigDecimal(value_T) ;
			quesDifficultySql+=" when difficulty<"+int_value_T+" and difficulty>="+int_value_B+" then '"+name+"'";
			}
			String getDataSql = "select rownum rn,cu.question_id,"
				+ "cu.defaultpoint,"
				+ "cu.time_use,"
			//	+ "cu.knowledge_point_id,"
				+ " (case knowledge_point_id "+quesPointSql+" end) knowledge_point_id,"
				+ "cu.editor_id,"
				+ "cu.UPDATE_TIME,"
				+ "cu.TIME,"
				+ "cu.EXPLAIN,"
				+ "(case "+quesDifficultySql+" end) DIFFICULTY, "
				+ "(case QUESTION_TYPE "+quesTypeSql+" end) QUESTION_TYPE from  "
				+ "QT_BASICFIELD_"+itembank_id+" cu where cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";// �кű�������Ϊrn
		String getCountSql = "QT_BASICFIELD_"+itembank_id+" cu where cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		//select * from qt_basicfield_lijuantest1 qt where qt.knowledge_point_id in(select kp.knowledge_point_id from sys_knowledge_point kp)

		//	List<Map> quesDifficultyValueData	=	dao.executeQuery("select dictionary_entries_value,name,value from sys_dictionary_entries_value where dictionary_entries_id='difficulty_value'");
		//type_value_entries=type_value_entries.substring(0, type_value_entries.length()-2);
		
	/*	for (int i = 0; i < quesTypedata.size(); i++) {
			String entries_value=(String) quesDifficultyTypeData.get(i).get("dictionary_entries_value");
			String entries_name=(String) quesDifficultyTypeData.get(i).get("name");
			quesDifficulty+=" when '"+entries_value+"' then '"+entries_name+"'";
		}*/
		
		return null;
	}
	
	
	
	/**
	 * 
	 * <p>���ƣ�ReferResult_QuesViewTableSource</p>
	 * <p>˵�����鿴���⣬���ݲ�ѯ������ʾ���TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String ReferResult_QuesViewTableSource(){
		logger.debug("**********&&&**********"+itembank_id+selectdifficultyid+selecttypeid+selectpointid);
		//��������ת��
		String quesTypeSql="";
		
			List<Map> quesTypedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='tixing'");
			for (int i = 0; i < quesTypedata.size(); i++) {
				String entries_value=(String) quesTypedata.get(i).get("dictionary_entries_value");
				String entries_name=(String) quesTypedata.get(i).get("name");
				quesTypeSql+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
			
		
		
		//�Ѷ�����ת��
		String quesDifficultySql="";
		List<Map> quesDifficultyTypeData	=	dao.executeQuery("select dictionary_entries_value,dictionary_entries_id,name,value from sys_dictionary_entries_value where dictionary_entries_id='difficulty'");
		
			for (int i = 0; i < quesDifficultyTypeData.size(); i++) {
				Map<String, String> TypeElement=quesDifficultyTypeData.get(i);
				String type_entries=(String) quesDifficultyTypeData.get(i).get("dictionary_entries_value");
				List<Map> quesDifficultyValueData	=	dao.executeQuery("select dictionary_entries_value,name,value from sys_dictionary_entries_value where dictionary_entries_value like '%"+type_entries+"%' and dictionary_entries_id='difficulty_value' ");
				for(int j=0;j<quesDifficultyValueData.size();j++){
					String type_value_entries=(String) quesDifficultyValueData.get(j).get("dictionary_entries_value");
					String type_value=(String) quesDifficultyValueData.get(j).get("value");
					if(type_value_entries.equals(""+type_entries+"_T")){
						TypeElement.put("value_T", type_value);
					}
					if(type_value_entries.equals(""+type_entries+"_B")){
						TypeElement.put("value_B", type_value);
					}
				}
			}
			logger.debug(""+quesDifficultyTypeData);
			for (int i = 0; i < quesDifficultyTypeData.size(); i++) {
				String dictionary_entries_value=(String) quesDifficultyTypeData.get(i).get("dictionary_entries_value");
				String name=(String) quesDifficultyTypeData.get(i).get("name");
				String value_B=(String) quesDifficultyTypeData.get(i).get("value_B");
				java.math.BigDecimal int_value_B = new java.math.BigDecimal(value_B) ;
				String value_T=(String) quesDifficultyTypeData.get(i).get("value_T");
				java.math.BigDecimal int_value_T = new java.math.BigDecimal(value_T) ;
				quesDifficultySql+=" when difficulty<"+int_value_T+" and difficulty>="+int_value_B+" then '"+name+"'";
			}
		
		
	
		
		
		//֪ʶ��ת��
		String quesPointSql="";
	
			List<Map> courseidlist	=	dao.executeQuery("select curse_id,itembank_id from sys_itembank_list where itembank_id='"+itembank_id+"'");
			String courseid=(String) courseidlist.get(0).get("curse_id");
			List<Map> quesPointdata	=	dao.executeQuery("select knowledge_point_id,name,curse_id from sys_knowledge_point where curse_id='"+courseid+"'");
			for (int i = 0; i < quesPointdata.size(); i++) {
				String point_name=(String) quesPointdata.get(i).get("name");
				String point_id=(String) quesPointdata.get(i).get("knowledge_point_id");
				quesPointSql+=" when '"+point_id+"' then '"+point_name+"'";
			}
		
		
		
		//�����Ѷ�idȡ���Ѷȷ�Χ
			String dif_value_T="";
			String dif_value_B="";
			if(!selectdifficultyid.equals("")){
				List<Map> Data	=	dao.executeQuery("select dictionary_entries_value,name,value from sys_dictionary_entries_value where dictionary_entries_value like '%"+selectdifficultyid+"%' and dictionary_entries_id='difficulty_value' ");
				
				for(int j=0;j<Data.size();j++){
					String type_value_entries=(String) Data.get(j).get("dictionary_entries_value");
					String type_value=(String) Data.get(j).get("value");
					if(type_value_entries.equals(""+selectdifficultyid+"_T")){
						dif_value_T=type_value;
					}
					if(type_value_entries.equals(""+selectdifficultyid+"_B")){
						dif_value_B=type_value;
					}
				}
				
			}
			if(dif_value_T.equals("")){
				dif_value_T="0.3";
				dif_value_B="0.4";
			}
				
			java.math.BigDecimal int_dif_value_T= new java.math.BigDecimal(dif_value_T) ;
			java.math.BigDecimal int_dif_value_B= new java.math.BigDecimal(dif_value_B) ;
			logger.debug(""+int_dif_value_T+int_dif_value_B);
		
		
		String confineSql="";
		
		//��ֻѡ��������
		if(selectpointid.equals("")&&selectdifficultyid.equals("")){
			confineSql="where QUESTION_TYPE='"+selecttypeid+"'";
		
		}
		//��ֻѡ����֪ʶ��
		if(selecttypeid.equals("")&&selectdifficultyid.equals("")){
			logger.debug("");
			confineSql="where knowledge_point_id='"+selectpointid+"' and cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";
		
		}
		//��ֻѡ�����Ѷ�
		if(selecttypeid.equals("")&&selectpointid.equals("")){
			logger.debug("");
			confineSql="where DIFFICULTY>="+int_dif_value_B+" and DIFFICULTY<"+int_dif_value_T+"";	
		}
		//��ѡ�������ͺ��Ѷ�
		if(!selecttypeid.equals("")&&!selectdifficultyid.equals("")&&selectpointid.equals("")){
			logger.debug("");

			confineSql="where QUESTION_TYPE='"+selecttypeid+"' and (DIFFICULTY>="+int_dif_value_B+" and DIFFICULTY<"+int_dif_value_T+")";	
		}
		//��ѡ�������ͺ�֪ʶ��
		if(!selecttypeid.equals("")&&selectdifficultyid.equals("")&&!selectpointid.equals("")){
			logger.debug("");

			confineSql="where knowledge_point_id='"+selectpointid+"' and QUESTION_TYPE='"+selecttypeid+"' and cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";	
		}
		//��ѡ����֪ʶ����Ѷ�
		if(selecttypeid.equals("")&&!selectdifficultyid.equals("")&&!selectpointid.equals("")){
			logger.debug("");

			confineSql="where knowledge_point_id='"+selectpointid+"' and  (DIFFICULTY>="+int_dif_value_B+" and DIFFICULTY<"+int_dif_value_T+") and cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";	
		}
		//��3����ѡ����
		if(!selecttypeid.equals("")&&!selectdifficultyid.equals("")&&!selectpointid.equals("")){
			logger.debug("");

			confineSql="where knowledge_point_id='"+selectpointid+"' and QUESTION_TYPE='"+selecttypeid+"' and (DIFFICULTY>="+int_dif_value_B+" and DIFFICULTY<"+int_dif_value_T+") and cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";	
		}
		
			String getDataSql = "select rownum rn,cu.question_id,"
				+ "cu.defaultpoint,"
				+ "cu.time_use,"
			//	+ "cu.knowledge_point_id,"
				+ " (case knowledge_point_id "+quesPointSql+" end) knowledge_point_id,"
				+ "cu.editor_id,"
				+ "cu.UPDATE_TIME,"
				+ "cu.TIME,"
				+ "cu.EXPLAIN,"
				+ "(case "+quesDifficultySql+" end) DIFFICULTY, "
				+ "(case QUESTION_TYPE "+quesTypeSql+" end) QUESTION_TYPE from  "
				+ "QT_BASICFIELD_"+itembank_id+" cu "+confineSql+"";// �кű�������Ϊrn
		String getCountSql = "QT_BASICFIELD_"+itembank_id+" cu "+confineSql+"";
/*		String getDataSql = "select rownum rn,cu.question_id,"
				+ "cu.defaultpoint,"
				+ "cu.time_use,"
				//	+ "cu.knowledge_point_id,"
				+ " (case knowledge_point_id "+quesPointSql+" end) knowledge_point_id,"
				+ "cu.editor_id,"
				+ "cu.UPDATE_TIME,"
				+ "cu.TIME,"
				+ "cu.EXPLAIN,"
				+ "(case "+quesDifficultySql+" end) DIFFICULTY, "
				+ "(case QUESTION_TYPE "+quesTypeSql+" end) QUESTION_TYPE from  "
				+ "QT_BASICFIELD_"+itembank_id+" cu where knowledge_point_id='"+selectpointid+"' and QUESTION_TYPE='"+selecttypeid+"' and (DIFFICULTY>="+int_dif_value_B+" and DIFFICULTY<"+int_dif_value_T+") and cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";// �кű�������Ϊrn
		String getCountSql = "QT_BASICFIELD_"+itembank_id+" cu where knowledge_point_id='"+selectpointid+"' and QUESTION_TYPE='"+selecttypeid+"' and (DIFFICULTY>="+int_dif_value_B+" and DIFFICULTY<"+int_dif_value_T+") and cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";
*/		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,getCountSql);
		return null;
	}
	
	
	/**
	 * �Ծ�����������
	 */
	public String EPDBManage() {
		//������е�paperuse�ȸ���Ϊ�����ֵ��ж�Ӧ��������
		String useconfig="";
		List<Map> usedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='paperuse'");
		for (int  i = 0; i < usedata.size(); i++) {
			String entries_value=(String) usedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) usedata.get(i).get("name");
		    useconfig+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		
		String getDataSql = "select rownum rn,cu.epdb_id,cu.epdb_name,"
				+"(case when explain is null then '��' else explain end) explain,"
				+"(case usage "+useconfig+" end) usage from  "
				+"sys_epdb_list cu";// �кű�������Ϊrn
		String getCountSql = "sys_epdb_list cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�ReferExamPaper</p>
	 * <p>˵����TODO(�����Ծ������Ծ�)</p>
	 * <p>������@param SelectedEPDB
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@param SelectedEPDB
	 * <p>@return</p>
	 */
	public String ReferExamPaper(){
					//logger.debug(selectedEPDB+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
					String getDataSql = "select rownum rn,cu.exam_paper_id,cu.paper_name,decode(substr(difficulty,1,1),'.','0'||difficulty,difficulty) difficulty,cu.exam_duration,cu.total_score from  sys_exam_paper cu where epdb_id='"+selectedEPDB+"'";// �кű�������Ϊrn
					String getCountSql = "sys_exam_paper cu where epdb_id='"+selectedEPDB+"'";
					CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
							getCountSql);
					return null;
				}
	
	/**
	 * 
	 * <p>���ƣ�GetTeachingClassTableDataByFieldId</p>
	 * <p>˵����ͨ���γ�Id��ȡ�ÿγ������н�ѧ�༶��Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String GetTeachingClassTableDataByFieldId() {
		if (dir.indexOf("asc") != -1) {
			dir = "asc";
		}
		if (dir.indexOf("desc") != -1 || dir.indexOf("dasc") != -1) {
			dir = "desc";
		}
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		String getDataSql = "select AllClass.rn,AllClass.curse_class_id,AllClass.teacher_id,AllClass.teaching_material_id,AllClass.class_name,(case when(AllClass.explain is null) then '��' else AllClass.explain end) explain," +
				"(case when (ut.name is null) then '��' else ut.name end) TeacherName,tm.name TMName from "
				+ "(select rownum rn,cu.* from sys_curse_class cu where curse_id='"
				+ FieldId
				+ "' order by "
				+ sort
				+ " "
				+ dir
				+ ") AllClass "
				+ "left join sys_user_teacher UT on AllClass.teacher_id=ut.school_id "
				+ "left join sys_teaching_material tm on AllClass.teaching_material_id=tm.teaching_material_id "
				+ "where rn<="
				+ (IntStartIndex + IntResults)
				+ " and rn>"
				+ IntStartIndex + " order by rn asc";
		String getCountSql = "sys_curse_class where curse_id='" + FieldId + "'";
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex,
				getDataSql, getCountSql);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�GetConfigOptionTableData</p>
	 * <p>˵������ȡ����������ı������ʾ����Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String GetConfigOptionTableData() {
		String getDataSql = "select rownum rn,cu.* from SYS_CONFIGURATION_ITEMS cu";
		String getCountSql = "SYS_CONFIGURATION_ITEMS";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	/**
	 * <p>���ߣ�HuangJu</p>
	 * <p>���ƣ�RoleDataGet</p>
	 * <p>˵����ͨ����ɫId��ȡ��ɫ�б�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	
	public String RoleDataGet() {
		String getDataSql = "select rownum rn,cu.ROLE_ID,cu.NAME," +
				"(case when (EXPLAIN is null) then ' ' else EXPLAIN end) as EXPLAIN" +
				" from SYS_ROLE cu";
		String getCountSql = "SYS_ROLE cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	/**

	 * <p>author��HuangJu</p>
	 * <p>���ƣ�TeacherListDataGet</p>
	 * <p>˵������ȡ��ʦ��������Table�е�ֵ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String TeacherListDataGet() {
		// ��ȡҪ��ѯ������
/*		String getDataSql = "select rownum rn,AllTeacher.* from sys_user_teacher AllTeacher " +
				"where user_id in (select cu.user_id from sys_department_member cu where department_id='"
				+ FieldId+"')";
		*/
		String getDataSql="select rownum rn,cu.USER_ID,cu.SCHOOL_ID,cu.NAME," +
				"(case when (GENDER = 1) then '��' when (GENDER = 0) then 'Ů' else ' ' end) as GENDER, " +
				"(case when (to_char(BIRTHDAY,'yyyy-MM-dd') is null) then ' '  when (to_char(BIRTHDAY,'yyyy-MM-dd') like 'null') then ' '  else  to_char(BIRTHDAY,'yyyy-MM-dd') end) as BIRTHDAY," +
				"(case when (ID is null) then ' '  when (ID like 'null') then ' '  else ID end) as ID, "+
				"(case when (EMAIL is null) then ' ' when (EMAIL like 'null') then ' ' else EMAIL end) as EMAIL," +
				"(case when (ADDRESS is null) then ' ' when (ADDRESS like 'null') then ' ' else ADDRESS end) as ADDRESS ," +
				"(case when (TELEPHONE is null) then ' ' when (TELEPHONE like 'null') then ' ' else TELEPHONE end) as TELEPHONE  "+
				"from SYS_USER_TEACHER cu " +
				"where user_id in (select dm.user_id from sys_department_member dm where department_id='" + FieldId + "')";
		//System.out.println(getDataSql);		
		//// ��ȡҪ��ѯ�����ݵĸ���
		String getCountSql = "sys_department_member where department_id='" + FieldId + "'";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	/**
	 * <p>author��HuangJu</p>
	 * <p>���ƣ�StudentListDataGet</p>
	 * <p>˵������ȡ��ʦ��������Table�е�ֵ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String StudentListDataGet() {
		// ��ȡҪ��ѯ������
		String getDataSql = "select rownum rn,cu.USER_ID,cu.SCHOOL_ID,cu.NAME," +
				"(case when (ADDRESS is null) then ' ' when (ADDRESS like 'null') then ' ' else ADDRESS end) as ADDRESS," +
				"(case when (TELEPHONE is null) then ' ' when (TELEPHONE like 'null') then ' ' else TELEPHONE end) as TELEPHONE " +
				"from sys_user_student cu " +
				"where user_id in (select dm.user_id from sys_department_member dm where department_id='"
				+ FieldId+"')";
				
				
		//// ��ȡҪ��ѯ�����ݵĸ���
		String getCountSql = "sys_department_member where department_id='" + FieldId + "'";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	/**

	 * <p>���ߣ�HuangJu</p>
	 * <p>���ƣ�DataDicDataGet</p>
	 * <p>˵������ȡָ����ɫ�µĳ�Ա�б�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String RoleMemberGet() {
		String getDataSql="";
		//System.out.println(MemberType);
		if(MemberType.equals("teacher")){
			getDataSql= "select rownum rn,sut.user_id user_id, sut.name name , sut.school_id school_id, " +
					"(case when (sut.user_type = '1') then '��ʦ' end ) as user_type " +
					"from (select rm.* from SYS_ROLE_MEMBER RM where rm.role_id='"+FieldId+"') srm  inner join SYS_USER_TEACHER sut" +
					" on sut.user_id=srm.user_id ";
		}
		else if(MemberType.equals("student")){
			getDataSql="select rownum rn,sus.user_id user_id ,sus.name name, sus.school_id school_id," +
					" (case when (sus.user_type ='2') then 'ѧ��' end ) as user_type " +
					" from (select rm.* from SYS_ROLE_MEMBER RM where rm.role_id='"+FieldId+"') srm " +
							"inner join SYS_USER_STUDENT sus" +
					" on sus.user_id=srm.user_id ";
			
		}
		
		String getCountSql = "SYS_ROLE_MEMBER srm where srm.role_id='"+FieldId+"'";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}

	/**

	 * <p>���ߣ�HuangJu</p>
	 * <p>���ƣ�DataDicDataGet</p>
	 * <p>˵������ȡ�ֵ����������Table�е�ֵ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String DataDicDataGet() {
		String getDataSql = "select rownum rn,cu.* from SYS_DICTIONARY_ENTRIES cu";
		String getCountSql = "SYS_DICTIONARY_ENTRIES cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	/**
	 * <p>���ߣ�HuangJu</p>
	 * <p>���ƣ�GetValueOptionByDicId</p>
	 * <p>˵����ͨ�������ֵ�id��ȡ���ֵ�����ֵ�����Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String GetValueOptionByDicId() {
		
		String getDataSql = "select rownum rn,cu.* from SYS_DICTIONARY_ENTRIES_VALUE cu where DICTIONARY_ENTRIES_ID='"+FieldId+"'";
				String getCountSql = "SYS_DICTIONARY_ENTRIES_VALUE where DICTIONARY_ENTRIES_ID='"
				+ FieldId + "'";
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex,
				getDataSql, getCountSql);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�getKnowledgePointByCurseId</p>
	 * <p>˵����ͨ���γ�idѡ��ÿγ���֪ʶ����Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	getKnowledgePointByCurseId(){
		logger.debug(this.FieldId);
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		String	getDataSql	=	"select rn,knowledge_point_id,name,curse_id,(case when (knowledge_point_content is null)then '��' else knowledge_point_content end)knowledge_point_content from ("+"select rownum rn,cu.* from sys_knowledge_point cu"
				+ " where curse_id='"
				+ this.FieldId
				+ "'"
				+ " ) "
				+ "where rn<="
				+ (IntStartIndex + IntResults) + " and rn>" + IntStartIndex;
		String	getCountSql	=	"sys_knowledge_point cu where curse_id='"
				+ this.FieldId
				+ "'";
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�getTeachingMateialByCurseId</p>
	 * <p>˵����ͨ���γ�idѡ��ÿγ��½̲���Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String getTeachingMateialByCurseId(){
		logger.debug(this.FieldId);
		/*TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		String	getDataSql	=	"select * from ("+"select rownum rn,cu.* from sys_teaching_material cu"
				+ " where curse_id='"
				+ this.FieldId
				+ "'"
				+ " ) "
				+ "where rn<="
				+ (IntStartIndex + IntResults) + " and rn>" + IntStartIndex;
		String	getCountSql	=	"sys_teaching_material cu";
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex, getDataSql,
				getCountSql);*/
		String getDataSql = "select rownum rn,cu.teaching_material_id,"
				+ "cu.name,"
				+ "cu.version,"
				+ "cu.author,"
				+"(case when (to_char(publication_date,'yyyy-MM-dd') is null) then '��' else to_char(publication_date,'yyyy-MM-dd') end) as publication_date,"
				+"(case when publishing_house is null then '��' else publishing_house end) as publishing_house,"
				+"(case when isbn_code is null then '��' else isbn_code end) as isbn_code from "
				+ "sys_teaching_material cu"
				+ " where curse_id='"
				+ this.FieldId
				+ "'";// �кű�������Ϊrn
		String	getCountSql	=	"sys_teaching_material cu where curse_id='"+ this.FieldId+ "'";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�getCurseByFieldId</p>
	 * <p>˵����ͨ��רҵidѡ���רҵ�¿γ���Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	getCurseByFieldId(){
		logger.debug(this.FieldId);
		/*TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);*/
		/*String	getDataSql	=	"select * from ("+"select rownum rn,cu.* from sys_curse cu"
				+ " where special_field_id='"
				+ this.FieldId
				+ "'"
				+ " ) "
				+ "where rn<="
				+ (IntStartIndex + IntResults) + " and rn>" + IntStartIndex;*/
		String getDataSql = "select rownum rn,cu.curse_id,"
				+ "cu.curse_name,"
				+ "cu.curse_credit,"
				+"(case when remarks is null then '��' else remarks end) remarks,"
				+ "(case assessment_method when 0 then '����' when"
				+ " 1 then '����' end) assessment_method from  "
				+ "sys_curse cu"
				+ " where special_field_id='"
				+ this.FieldId
				+ "'";// �кű�������Ϊrn
		String	getCountSql	=	"sys_curse cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�GetAdminiClassTableDataByFieldId</p>
	 * <p>˵����ͨ���γ�Id��ȡ�ÿγ������н�ѧ�༶��Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String GetAdminiClassTableDataByFieldId() {
		if (dir.indexOf("asc") != -1) {
			dir = "asc";
		}
		if (dir.indexOf("desc") != -1 || dir.indexOf("dasc") != -1) {
			dir = "desc";
		}
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		String getDataSql = "select AllClass.*,ut.name TeacherName,tm.name TMName from "
				+ "(select rownum rn,cu.* from sys_curse_class cu where curse_id='"
				+ FieldId
				+ "' order by "
				+ sort
				+ " "
				+ dir
				+ ") AllClass "
				+ "inner join sys_user_teacher UT on AllClass.teacher_id=ut.school_id "
				+ "inner join sys_teaching_material tm on AllClass.teaching_material_id=tm.teaching_material_id "
				+ "where rn<="
				+ (IntStartIndex + IntResults)
				+ " and rn>"
				+ IntStartIndex + " order by rn asc";
		String getCountSql = "sys_curse_class where curse_id='" + FieldId + "'";
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex,
				getDataSql, getCountSql);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�GetTeachingClassMemberTableDataByTeachingClassId</p>
	 * <p>˵����ͨ�����ΰ༶Id��ȡ�ÿ��ΰ༶�����г�Ա��Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String GetTeachingClassMemberTableDataByTeachingClassId() {
		if (dir.indexOf("asc") != -1) {
			dir = "asc";
		}
		if (dir.indexOf("desc") != -1 || dir.indexOf("dasc") != -1) {
			dir = "desc";
		}
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		String getDataSql = "select AllClass.*,sus.name StudentName,sus.school_id StudentSchoolId from "
				+ "(select rownum rn,cu.* from sys_curse_class_member cu where curse_class_id='"
				+ TeachingClassId
				+ "' and cu.user_id in (select user_id from sys_user_student) order by "
				+ sort
				+ " "
				+ dir
				+ ") AllClass "
				+ "left join sys_user_student sus on AllClass.user_id=sus.user_id "
				+ "where rn<="
				+ (IntStartIndex + IntResults)
				+ " and rn>"
				+ IntStartIndex + " order by rn asc";
		String getCountSql = "sys_curse_class_member where curse_class_id='" + TeachingClassId + "' and sys_curse_class_member.user_id in (select user_id from sys_user_student)";
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex,
				getDataSql, getCountSql);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�getTeachingCourseByTeacherId</p>
	 * <p>˵����ͨ����ʦId��ȡ�ý�ʦ�����н�ѧ�γ̺Ͱ༶��Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String getTeachingCourseByTeacherId() {
		if (dir.indexOf("asc") != -1) {
			dir = "asc";
		}
		if (dir.indexOf("desc") != -1 || dir.indexOf("dasc") != -1) {
			dir = "desc";
		}
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		String getDataSql = "select AllClass.*,ut.name TeacherName,dt.text TeacherCenter,sc.curse_name CurseName,(select count(*) from sys_curse_class_member where curse_class_id=AllClass.curse_class_id)MemberCount from "
				+ "(select rownum rn,cu.* from sys_curse_class cu where teacher_id='"
				+ TeacherId
				+ "' order by "
				+ sort
				+ " "
				+ dir
				+ ") AllClass "
				+ "inner join sys_user_teacher ut on AllClass.teacher_id=ut.school_id "
				+ "inner join sys_department_member dm on ut.user_id=dm.user_id "
				+ "inner join sys_department_tree dt on dm.department_id=dt.id "
				+ "inner join sys_curse sc on AllClass.curse_id=sc.curse_id "
				+ "where rn<="
				+ (IntStartIndex + IntResults)
				+ " and rn>"
				+ IntStartIndex + " order by rn asc";
		String getCountSql = "sys_curse_class where teacher_id='" + TeacherId + "'";
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex,
				getDataSql, getCountSql);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�getTeachingCourseByTeacherIds</p>
	 * <p>˵����ͨ����ʦ������ȡ�û��������н�ʦ�Ľ�ѧ�γ̺Ͱ༶��Ϣ</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String getTeachingCourseByTeacherIds() {
		logger.debug(TeacherIds);
		//�յ���TeacherIds��ʽΪ   TeacherId1 TeacherId2 TeacherId3 TeacherId4 ....
		String[] array=TeacherIds.split(" ");
		logger.debug(array);
		logger.debug(array.length);
		
		if (dir.indexOf("asc") != -1) {
			dir = "asc";
		}
		if (dir.indexOf("desc") != -1 || dir.indexOf("dasc") != -1) {
			dir = "desc";
		}
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		String getDataSql = "select AllClass.*,ut.name TeacherName,dt.text TeacherCenter,sc.curse_name CurseName,(select count(*) from sys_curse_class_member where curse_class_id=AllClass.curse_class_id)MemberCount from "
				+ "(select rownum rn,cu.* from sys_curse_class cu where";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				getDataSql+=" or";
			}
			getDataSql+=" teacher_id='"+array[i]+"'";
		}
		
		String getDataSql_second = " order by "
				+ sort
				+ " "
				+ dir
				+ ") AllClass "
				+ "inner join sys_user_teacher ut on AllClass.teacher_id=ut.school_id "
				+ "inner join sys_department_member dm on ut.user_id=dm.user_id "
				+ "inner join sys_department_tree dt on dm.department_id=dt.id "
				+ "inner join sys_curse sc on AllClass.curse_id=sc.curse_id "
				+ "where rn<="
				+ (IntStartIndex + IntResults)
				+ " and rn>"
				+ IntStartIndex + " order by rn asc";
		
		getDataSql+=getDataSql_second;
		
		String getCountSql = "sys_curse_class where";
		for(int	i=0;i<array.length;i++){
			if(i!=0){
				getCountSql+=" or";
			}
			getCountSql+=" teacher_id='"+array[i]+"'";
		}
		
		
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex,
				getDataSql, getCountSql);
		return null;
	}
	/**

	 * <p>���ߣ�HuangJu</p>
	 * <p>���ƣ�StudentScoreOnTeachingClassGet</p>
	 * <p>˵����ѯָ����ѧ�༶�µ�ѧ���ĳɼ�<p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String StudentScoreOnTeachingClassGet() {
		String getDataSql = "";
		// System.out.println(MemberType);
	
		getDataSql =  "select rownum rn, sss.student_id,sss.score_total, sss.exam_time ,b.name " +
				" from sys_student_score sss " +
				" inner join ( select sus.school_id, sus.name from (select * from sys_curse_class_member where curse_class_id='"+SelectedTeachingClassId+"')a " +
				"inner join sys_user_student sus on a.user_id=sus.user_id) b " +
				" on b.school_id=sss.student_id and sss.curse_id='"+selectedCurseId+"' ";

		String getCountSql = " sys_student_score " +
				"where student_id in (" +
				" select sus.school_id  from " +
				" (select * from sys_curse_class_member where curse_class_id='"+SelectedTeachingClassId+"')a " +
						"inner join sys_user_student sus on a.user_id=sus.user_id) " +
						"and curse_id='"+selectedCurseId+"' ";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	/**

	 * <p>���ߣ�HuangJu</p>
	 * <p>���ƣ�StudentScoreOnStudentSelf</p>
	 * <p>˵����ѧ��������ѯ�ɼ�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String StudentScoreOnStudentSelf(){
		String getDataSql = "";
		// System.out.println(MemberType);
	
		getDataSql = " select rownum rn, sc.curse_name, sus.name , a.student_id , a.score_total ,a.exam_time from " +
				"(select * from sys_student_score sss where sss.student_id='"+SchoolId+"') a " +
				"inner join sys_curse sc on  sc.curse_id=a.curse_id " +
				"inner join sys_user_student sus on a.student_id=sus.school_id";

		String getCountSql = " sys_student_score where student_id ='"+SchoolId+"' ";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	/**

	 * <p>���ߣ�HuangJu</p>
	 * <p>���ƣ�StudentScoreOnClass</p>
	 * <p>˵�����������༶��ѯѧ���ɼ�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String StudentScoreOnClass(){
		String getDataSql = "";
		// System.out.println(MemberType);
	
		getDataSql =  "select rownum rn, sss.student_id,sss.score_total, sss.exam_time ,b.name " +
				" from SYS_STUDENT_SCORE sss " +
				" inner join ( select sus.school_id, sus.name from " +
				"(select * from SYS_DEPARTMENT_MEMBER where department_id='"+SelectedClassId+"')a " +
				"inner join SYS_USER_STUDENT sus on a.user_id=sus.user_id) b " +
				" on b.school_id=sss.student_id and sss.curse_id='"+selectedCurseId+"' ";

		String getCountSql = " sys_student_score " +
				"where student_id in (" +
				" select sus.school_id  from " +
				" (select * from sys_department_member where department_id='"+SelectedClassId+"')a " +
						"inner join sys_user_student sus on a.user_id=sus.user_id) " +
						"and curse_id='"+selectedCurseId+"' ";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}

	public	String	getQuesByIds(){
		
		logger.debug(IBIds);
		String[] ids=	IBIds.split(",");
		logger.debug("0000000000000000000000000000000000"+ids.length);
		String[] kps=KPIds.split(",");
		String	getDataSql	=	"";
		String	getCountSql="";
		String	temp	=	"";
		boolean	flag	=	false;
		//��������ת��
		String quesTypeSql="";
		List<Map> quesTypedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='tixing'");
		for (int i = 0; i < quesTypedata.size(); i++) {
			String entries_value=(String) quesTypedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) quesTypedata.get(i).get("name");
			quesTypeSql+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		
		for(int	i=0;i<ids.length;i++){
			//����ǵ�һ��
			if(!flag){
				getDataSql+="select QUESTION_ID,(case QUESTION_TYPE "+quesTypeSql+" end) QUESTION_TYPE, decode(substr(difficulty,1,1),'.','0'||difficulty,difficulty) difficulty,TIME_USE,DEFAULTPOINT,KNOWLEDGE_POINT_ID,'"+ids[i]+"' as IBID from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				temp+="select QUESTION_ID,QUESTION_TYPE,DIFFICULTY,DEFAULTPOINT,TIME_USE,KNOWLEDGE_POINT_ID from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				for(int	j=0;j<kps.length;j++){
					temp+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
					getDataSql+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
				}
				flag=true;
			}
			//���ǵ�һ�������
			else{
				getDataSql+="union select QUESTION_ID,(case QUESTION_TYPE "+quesTypeSql+" end) QUESTION_TYPE,decode(substr(difficulty,1,1),'.','0'||difficulty,difficulty) difficulty,TIME_USE,DEFAULTPOINT,KNOWLEDGE_POINT_ID,'"+ids[i]+"' as IBID from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				temp+=" union select QUESTION_ID,QUESTION_TYPE,DIFFICULTY,DEFAULTPOINT,TIME_USE,KNOWLEDGE_POINT_ID from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				for(int	j=0;j<kps.length;j++){
					getDataSql+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
					temp+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
				}
			}
		}
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		getDataSql="select * from(select rownum rn,st.* from ("+getDataSql+") st )t inner join sys_knowledge_point on t.knowledge_point_id=sys_knowledge_point.knowledge_point_id where rn<="+(IntStartIndex + IntResults)+" and rn>"+IntStartIndex+" order by rn asc";
		logger.debug(getDataSql);
		
		getCountSql="("+temp+")";
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	public String multiGetQuesByDifTypKP(){
		logger.debug("===========multiGetQuesByDifTypKP===========");
		logger.debug(this.type);
		logger.debug(this.dif);
		logger.debug(this.kp);
		String[] ids=	IBIds.split(",");
		String[] kps=KPIds.split(",");
		String	getDataSql	=	"";
		String	getCountSql="";
		String	temp	=	"";
		boolean	flag	=	false;
		
		List<Map> quesDifficultyTypeData = dao
				.executeQuery("select dictionary_entries_value,dictionary_entries_id,name,value from sys_dictionary_entries_value where dictionary_entries_id='difficulty'");
		for (int i = 0; i < quesDifficultyTypeData.size(); i++) {
			Map<String, String> TypeElement = quesDifficultyTypeData.get(i);
			String type_entries = (String) quesDifficultyTypeData.get(i).get(
					"dictionary_entries_value");
			List<Map> quesDifficultyValueData = dao
					.executeQuery("select dictionary_entries_value,name,value from sys_dictionary_entries_value where dictionary_entries_value like '%"
							+ type_entries
							+ "%' and dictionary_entries_id='difficulty_value' ");
			for (int j = 0; j < quesDifficultyValueData.size(); j++) {
				String type_value_entries = (String) quesDifficultyValueData
						.get(j).get("dictionary_entries_value");
				String type_value = (String) quesDifficultyValueData.get(j)
						.get("value");
				if (type_value_entries.equals("" + type_entries + "_T")) {
					TypeElement.put("value_T", type_value);
				}
				if (type_value_entries.equals("" + type_entries + "_B")) {
					TypeElement.put("value_B", type_value);
				}
			}
		}
			String quesDifficultySql="";
			logger.debug("_________________________________________");
			logger.debug(quesDifficultyTypeData);
			for (int i = 0; i < quesDifficultyTypeData.size(); i++) {
				String dictionary_entries_value = (String) quesDifficultyTypeData
						.get(i).get("dictionary_entries_value");
				String name = (String) quesDifficultyTypeData.get(i).get("name");
				String value_B = (String) quesDifficultyTypeData.get(i).get(
						"value_B");
				java.math.BigDecimal int_value_B = new java.math.BigDecimal(value_B);
				String value_T = (String) quesDifficultyTypeData.get(i).get(
						"value_T");
				java.math.BigDecimal int_value_T = new java.math.BigDecimal(value_T);
				if (dictionary_entries_value.equals(this.dif)) {
					quesDifficultySql = "  difficulty<" + int_value_T
							+ " and difficulty>=" + int_value_B;
				}
		}
			logger.debug("+++++++++++++++++++++++++++++");
			logger.debug(quesDifficultySql);
		//��������ת��
		String quesTypeSql="";
		List<Map> quesTypedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='tixing'");
		for (int i = 0; i < quesTypedata.size(); i++) {
			String entries_value=(String) quesTypedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) quesTypedata.get(i).get("name");
			quesTypeSql+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		
		for(int	i=0;i<ids.length;i++){
			//����ǵ�һ��
			if(!flag){
				getDataSql+="select QUESTION_ID,QUESTION_TYPE qt,(case QUESTION_TYPE "+quesTypeSql+" end) QUESTION_TYPE,decode(substr(difficulty,1,1),'.','0'||difficulty,difficulty) difficulty,DEFAULTPOINT,KNOWLEDGE_POINT_ID,TIME_USE,'"+ids[i]+"' as IBID from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				temp+="select * from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				for(int	j=0;j<kps.length;j++){
					temp+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
					getDataSql+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
				}
				flag=true;
			}
			//���ǵ�һ�������
			else{
				getDataSql+="union select QUESTION_ID,QUESTION_TYPE qt,(case QUESTION_TYPE "+quesTypeSql+" end) QUESTION_TYPE,decode(substr(difficulty,1,1),'.','0'||difficulty,difficulty) difficulty,DEFAULTPOINT,KNOWLEDGE_POINT_ID,TIME_USE,'"+ids[i]+"' as IBID from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				temp+=" union select * from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				for(int	j=0;j<kps.length;j++){
					getDataSql+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
					temp+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
				}
			}
		}
		getDataSql="select * from ("+getDataSql+") st inner join (select knowledge_point_id kp,name from sys_knowledge_point) m on st.knowledge_point_id=m.kp";
		//getDataSql="select * from("+getDataSql+") where qt='"+this.type+"' and KNOWLEDGE_POINT_ID='"+this.kp+"' and "+quesDifficultySql;
		boolean startFlag	=	false;
		if(!"".equals(this.type)){
			getDataSql="select rownum rn,ques.* from("+getDataSql+")ques where qt='"+this.type+"' ";
			startFlag=true;
		}
		else{
			getDataSql="select rownum rn,ques.* from("+getDataSql+")ques where ";
		}
		if(!"".equals(this.kp)){
			if(startFlag){
				getDataSql+="and KNOWLEDGE_POINT_ID='"+this.kp+"' ";
			}
			else{
				getDataSql+="KNOWLEDGE_POINT_ID='"+this.kp+"' ";
				startFlag=true;
			}
		}
		if(!"".equals(this.dif)){
			if(startFlag){
				getDataSql+="and "+quesDifficultySql;
			}
			else{
				getDataSql+=quesDifficultySql;
			}
		}
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		getCountSql="("+getDataSql+")";
		getDataSql	=	"select * from ("+getDataSql+") where rn>"+startIndex+" and rn<="+(IntStartIndex+IntResults)+" order by rn asc";
		logger.debug(getDataSql);
		
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�getAddedQuesByIds</p>
	 * <p>˵����TODO(������һ�仰�����������������)</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public	String	getAddedQuesByIds(){

		logger.debug(IBIds);
		logger.debug(KPIds);
		if("".equals(IBIds)||null==IBIds||"".equals(KPIds)||null==KPIds){
			logger.info("û��ѡ��������������");
			HttpServletResponse response	=	ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");
			try {	
				PrintWriter pw = response.getWriter();	
				pw.print("{\"recordsReturned\":0,\"totalRecords\":0,\"startIndex\":0,\"sort\":\"null\",\"dir\":\"asc\",\"records\":[]}");	
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		//��������ת��
		String quesTypeSql="";
		List<Map> quesTypedata = dao
				.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='tixing'");
		for (int i = 0; i < quesTypedata.size(); i++) {
			String entries_value = (String) quesTypedata.get(i).get(
					"dictionary_entries_value");
			String entries_name = (String) quesTypedata.get(i).get("name");
			quesTypeSql += " when '" + entries_value + "' then '"
					+ entries_name + "'";
		}
		logger.debug("ѡ����һЩ���⣬���潫��ʼ������Щ������Ϣ");
		String[] ids=	IBIds.split(",");
		String[] kps=KPIds.split(",");
		String	getDataSql	=	"";
		String	getCountSql="";
		logger.debug("+++++++++++getAddedQuesByIds++++++++++");
		boolean flag	=	false;
		for(int i=0;i<ids.length;i++){
			if(!flag){
				getDataSql	=	"select QUESTION_ID,QUESTION_TYPE,DIFFICULTY,DEFAULTPOINT,KNOWLEDGE_POINT_ID,TIME_USE,'"+ids[i]+"' as IBID from QT_BASICFIELD_"+ids[i]+" where question_id='"+kps[i]+"'";
				flag	=	true;
			}else{
				getDataSql	+=	" union select QUESTION_ID,QUESTION_TYPE,DIFFICULTY,DEFAULTPOINT,KNOWLEDGE_POINT_ID,TIME_USE,'"+ids[i]+"' as IBID from QT_BASICFIELD_"+ids[i]+" where question_id='"+kps[i]+"'";
			}
		}
		
		
		getDataSql	=	"select rownum rn,QUESTION_ID,QUESTION_TYPE qt,(case QUESTION_TYPE "+quesTypeSql+" end) QUESTION_TYPE,decode(substr(difficulty,1,1),'.','0'||difficulty,difficulty) difficulty,DEFAULTPOINT,KNOWLEDGE_POINT_ID,IBID,TIME_USE from ("+getDataSql+")";
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		getCountSql="("+getDataSql+")";
		getDataSql	=	"select * from ("+getDataSql+") st inner join (select knowledge_point_id kp,name from sys_knowledge_point) m on st.knowledge_point_id=m.kp where rn>"+IntStartIndex+" and rn<="+(IntStartIndex+IntResults)+" order by rn asc";
		
		logger.debug(getDataSql);
		
		
		
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex, getDataSql, 
				getCountSql);
		return null;
	}
	
	/**
	 * 
	 * <p>���ƣ�getCurseBySpecialFieldId</p>
	 * <p>˵�����γ̲�ѯ��ͨ��רҵID��ѯ�γ��б�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String getCurseBySpecialFieldId() {
		// "select * from sys_curse where special_field_id='"+this.specialFieldId+"'";
		if (dir.indexOf("asc") != -1) {
			dir = "asc";
		}
		if (dir.indexOf("desc") != -1 || dir.indexOf("dasc") != -1) {
			dir = "desc";
		}
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		String getDataSql = "select rn,curse_id,curse_name,special_field_id,curse_credit,(case assessment_method when 0 then '����' when 1 then '����' end) assessment_method,(case when remarks is null then '��' else remarks end) remarks from(select rownum rn,cu.* from sys_curse cu where special_field_id='"
				+ this.FieldId
				+ "' order by "
				+ sort
				+ " "
				+ dir
				+ ") where rn<="
				+ (IntStartIndex + IntResults)
				+ " and rn>"
				+ IntStartIndex + " order by rn asc";
		String getCountSql = "sys_curse where special_field_id='" + FieldId
				+ "'";
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex,
				getDataSql, getCountSql);
		return null;
	}
	/**
	 * 
	 * <p>���ƣ�getAllCourse</p>
	 * <p>˵������ȡ���пγ�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��String ��������</p>
	 * <p>@return</p>
	 */
	public String getAllCourse(){
		if (dir.indexOf("asc") != -1) {
			dir = "asc";
		}
		if (dir.indexOf("desc") != -1 || dir.indexOf("dasc") != -1) {
			dir = "desc";
		}
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		String getDataSql = "select rn,curse_id,curse_name,special_field_id,curse_credit,(case assessment_method when 0 then '����' when 1 then '����' end) assessment_method,(case when remarks is null then '��' else remarks end) remarks from(select rownum rn,cu.* from sys_curse cu "
				+ " order by "
				+ sort
				+ " "
				+ dir
				+ ") where rn<="
				+ (IntStartIndex + IntResults)
				+ " and rn>"
				+ IntStartIndex + " order by rn asc";
		String getCountSql = "sys_curse";
		CDTJFYS.CreateDataTableJsonFullSql(dir, results, sort, startIndex,
				getDataSql, getCountSql);
		return null;
	}
	
}




