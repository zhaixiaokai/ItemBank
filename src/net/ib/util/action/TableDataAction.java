
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TableDataAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-5 下午2:33:28
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-5 下午2:33:28
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
 * <p>类名：test.YuiTableServAction </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
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
	private String MemberType;//成员类型
	private	String  TeachingClassId;
	private	String  TeacherId;
	private	String  TeacherIds;
	private String  selectedCurseId;//成绩查询中的课程id
	private String  SelectedTeachingClassId;//成绩查询中的开课班级id
	private String SchoolId;//成绩查询中学生学号
	private String SelectedClassId;//成绩查询过程中行政班级的id
	private String itembank_id;
	private String selectpointid;
	private String selectedEPDB;//选中的试卷库，根据此试卷库查找相应的试卷
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
	private	String	referuse;//试题库查询试题库用途
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

	private	String	refer_leafnode;//试题库查询分类配置所有叶子节点
	
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
		logger.debug("初始化dao  GET");
		return dao;
	}
	public void setDao(Dao dao) {
		logger.debug("初始化dao  SET");
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
		String getDataSql = "select rownum rn,cu.* from test cu";// 行号必须命名为rn
		String getCountSql = "test cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	/**
	 * 
	 * <p>名称：SortSystemManage</p>
	 * <p>说明：分类体系管理表格数据获取</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String SortSystemManage() {
		String getDataSql = "select rownum rn,cu.classification_id,"
				+ "cu.classification_name,"
				+"(case when classification_explain is null then '无' else classification_explain end) classification_explain,"
				+ "(case if_default_classification when 0 then '否' when"
				+ " 1 then '是' end) if_default_classification from  "
				+ "sys_ib_classification_system cu";// 行号必须命名为rn
		String getCountSql = "sys_ib_classification_system cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	
	/**
	 * 试题库管理表格数据
	 */
	public String ExamSystemManage() {
		//将表格中的paperuse等更改为数据字典中对应的配置项
		String useconfig="";
		List<Map> usedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='paperuse'");
		for (int i = 0; i < usedata.size(); i++) {
			String entries_value=(String) usedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) usedata.get(i).get("name");
		    useconfig+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		
		String getDataSql = "select rownum rn,cu.itembank_id,cu.itembank_name,"
				+"(case when explain is null then '无' else explain end) explain,"
				+"(case use "+useconfig+" end) use from  "
				+"sys_itembank_list cu";// 行号必须命名为rn
		String getCountSql = "sys_itembank_list cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	/**
	 * 根据查询条件获取试题库表格数据
	 */
	
	public String ReferDataSource() {
		//将字符串转换为数组
		ArrayList<String> leafnode=new ArrayList<String>();
		if(refer_leafnode.indexOf(",")!=-1){
			String[] tempArray=refer_leafnode.split(",");
			for(int i=0;i<tempArray.length;i++){
				leafnode.add(tempArray[i]);
			}
		}else{
			leafnode.add(refer_leafnode);
		}

	//从数据字典中取出对应值项
		String useconfig="";
		List<Map> usedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='paperuse'");
		for (int i = 0; i < usedata.size(); i++) {
			String entries_value=(String) usedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) usedata.get(i).get("name");
		    useconfig+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		//若节点非基本分类体系下节点
		if(if_under_defaultsort.equals("0")){
			//根据叶子节点id从sys_ib_classification_node中查找对应试题库
			ArrayList<String> itembank=new ArrayList<String>();
			for(int i=0;i<leafnode.size();i++){
				String node=(String) leafnode.get(i);
				List<Map> eslist=dao.executeQuery("select itembank_id from sys_ib_classification_node where node_id='"+node+"'");
				for (int j = 0; j< eslist.size(); j++) {
					String es_id=(String) eslist.get(j).get("itembank_id");
					itembank.add(es_id);
				}
			}
			//sql拼接,试题库id条件
			//判断是否有试题库
			String getDataSql="";
			String getCountSql="";
			if(itembank.size()<1){
			getDataSql = "select rownum rn,cu.itembank_id,cu.itembank_name,"
						+"(case when explain is null then '无' else explain end) explain,"
						+"(case use "+useconfig+" end) use from  "
						+"sys_itembank_list cu where use='"+referuse+"' AND itembank_id=''";// 行号必须命名为rn
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
				//从sys_itembank_list中查找数据
			getDataSql = "select rownum rn,cu.itembank_id,cu.itembank_name,"
						+"(case when explain is null then '无' else explain end) explain,"
						+"(case use "+useconfig+" end) use from  "
						+"sys_itembank_list cu where use='"+referuse+"' AND "+es_sql+"";// 行号必须命名为rn
			//logger.debug("%%%%%%%%%%%%&&%%%%%%%%%%"+getDataSql);
				getCountSql = "sys_itembank_list cu where use='"+referuse+"' AND "+es_sql+"";
			}
	
			CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
					getCountSql);
			
		}else{
			//根据叶子节点id查找所有课程
			ArrayList<String> subject=new ArrayList<String>();
			for(int i=0;i<leafnode.size();i++){
				String node=(String) leafnode.get(i);
				List<Map> subjectlist	=	dao.executeQuery("select curse_id from sys_curse where special_field_id='"+node+"'");
				for (int j = 0; j< subjectlist.size(); j++) {
					String subject_id=(String) subjectlist.get(j).get("curse_id");
					subject.add(subject_id);
				}
			}
		//	根据课程id从itembank_list中查找所有试题库
			//判断课程数组是否为空
			String getDataSql="";
			String getCountSql="";
			logger.debug(subject.size());
			if(subject.size()<1){
			 getDataSql = "select rownum rn,cu.itembank_id,cu.itembank_name,"
						+"(case when explain is null then '无' else explain end) explain,"
						+"(case use "+useconfig+" end) use from  "
						+"sys_itembank_list cu where use='"+referuse+"' AND curse_id=''";// 行号必须命名为rn
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
						+"(case when explain is null then '无' else explain end) explain,"
						+"(case use "+useconfig+" end) use from  "
						+"sys_itembank_list cu where use='"+referuse+"' AND "+curse_sql+"";// 行号必须命名为rn
				getCountSql = "sys_itembank_list cu where use='"+referuse+"' AND "+curse_sql+"";

			}
	
			CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
					getCountSql);
		}
			 
			
		
		
		return null;
	}
	/**
	 * 根据查询条件获取试卷库表格数据
	 */
	
	public String ReferEPDB() {
		
		//logger.debug(referuse+"++++++++++++++++++++++++++++++++++");
		//将字符串转换为数组
		String[] leafnode=refer_leafnode.split(",");
		//从数据字典中取出对应值项
		String useconfig="";
		List<Map> usedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='paperuse'");
		for (int i = 0; i < usedata.size(); i++) {
			String entries_value=(String) usedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) usedata.get(i).get("name");
		    useconfig+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		//若节点非基本分类体系下节点
				if(if_under_defaultsort.equals("0")){
					//根据叶子节点id从sys_epdb_classification_node中查找对应试题库
					ArrayList<String> epdb=new ArrayList<String>();
					for(int i=0;i<leafnode.length;i++){
						String node=leafnode[i];
						List<Map> eslist	=	dao.executeQuery("select epdb_id from sys_epdb_classification_node where node_id='"+node+"'");
						for (int j = 0; j< eslist.size(); j++) {
							String es_id=(String) eslist.get(j).get("epdb_id");
							epdb.add(es_id);
						}
					}
					//sql拼接,试题库id条件
					//判断是否有试题库
					String getDataSql="";
					String getCountSql="";
					
				
					if(epdb.size()<1){
						
					getDataSql = "select rownum rn,cu.epdb_id,cu.epdb_name,"
								+"(case when explain is null then '无' else explain end) explain,"
								+"(case uagse "+useconfig+" end) usage from  "
								+"sys_epdb_list cu where usage='"+referuse+"' AND epdb_id=''";// 行号必须命名为rn
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
						//从sys_epdb_list中查找数据
					getDataSql = "select rownum rn,cu.epdb_id,cu.epdb_name,"
								+"(case when explain is null then '无' else explain end) explain,"
								+"(case usage "+useconfig+" end) usage from  "
								+"sys_epdb_list cu where usage='"+referuse+"' AND "+es_sql+"";// 行号必须命名为rn
					//logger.debug("%%%%%%%%%%%%&&%%%%%%%%%%"+getDataSql);
						getCountSql = "sys_epdb_list cu where usage='"+referuse+"' AND "+es_sql+"";
					}
			
					CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
							getCountSql);
					
				}else{
					//根据叶子节点id查找所有课程
					ArrayList<String> subject=new ArrayList<String>();
					for(int i=0;i<leafnode.length;i++){
						String node=leafnode[i];
						List<Map> subjectlist	=	dao.executeQuery("select curse_id from sys_curse where special_field_id='"+node+"'");
						for (int j = 0; j< subjectlist.size(); j++) {
							String subject_id=(String) subjectlist.get(j).get("curse_id");
							subject.add(subject_id);
						}
					}
				//	根据课程id从epdbk_list中查找所有试题库
					//判断课程数组是否为空
					String getDataSql="";
					String getCountSql="";
					logger.debug(subject.size());
					if(subject.size()<1){
					 getDataSql = "select rownum rn,cu.epdb_id,cu.epdb_name,"
								+"(case when explain is null then '无' else explain end) explain,"
								+"(case usage "+useconfig+" end) usage from  "
								+"sys_epdb_list cu where usage='"+referuse+"' AND curse_id=''";// 行号必须命名为rn
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
								+"(case when explain is null then '无' else explain end) explain,"
								+"(case usage "+useconfig+" end) usage from  "
								+"sys_epdb_list cu where usage='"+referuse+"' AND "+curse_sql+"";// 行号必须命名为rn
						getCountSql = "sys_epdb_list cu where usage='"+referuse+"' AND "+curse_sql+"";

					}
			
					CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
							getCountSql);
				}
				return null;
	}
		
	/**
	 * 
	 * <p>名称：QuesViewTableSource</p>
	 * <p>说明：查看试题，表格数据获取TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	//xiaokaiPoint
	public String QuesViewTableSource(){
		logger.debug(itembank_id);
		//题型数据转换
		String quesTypeSql="";
		List<Map> quesTypedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='tixing'");
		for (int i = 0; i < quesTypedata.size(); i++) {
			String entries_value=(String) quesTypedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) quesTypedata.get(i).get("name");
			quesTypeSql+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		//难度数据转化
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
		
		//知识点转换
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
				+ "QT_BASICFIELD_"+itembank_id+" cu where cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";// 行号必须命名为rn
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
	 * <p>名称：ReferResult_QuesViewTableSource</p>
	 * <p>说明：查看试题，根据查询条件显示表格TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String ReferResult_QuesViewTableSource(){
		logger.debug("**********&&&**********"+itembank_id+selectdifficultyid+selecttypeid+selectpointid);
		//题型数据转换
		String quesTypeSql="";
		
			List<Map> quesTypedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='tixing'");
			for (int i = 0; i < quesTypedata.size(); i++) {
				String entries_value=(String) quesTypedata.get(i).get("dictionary_entries_value");
				String entries_name=(String) quesTypedata.get(i).get("name");
				quesTypeSql+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
			
		
		
		//难度数据转化
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
		
		
	
		
		
		//知识点转换
		String quesPointSql="";
	
			List<Map> courseidlist	=	dao.executeQuery("select curse_id,itembank_id from sys_itembank_list where itembank_id='"+itembank_id+"'");
			String courseid=(String) courseidlist.get(0).get("curse_id");
			List<Map> quesPointdata	=	dao.executeQuery("select knowledge_point_id,name,curse_id from sys_knowledge_point where curse_id='"+courseid+"'");
			for (int i = 0; i < quesPointdata.size(); i++) {
				String point_name=(String) quesPointdata.get(i).get("name");
				String point_id=(String) quesPointdata.get(i).get("knowledge_point_id");
				quesPointSql+=" when '"+point_id+"' then '"+point_name+"'";
			}
		
		
		
		//根据难度id取得难度范围
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
		
		//若只选择了题型
		if(selectpointid.equals("")&&selectdifficultyid.equals("")){
			confineSql="where QUESTION_TYPE='"+selecttypeid+"'";
		
		}
		//若只选择了知识点
		if(selecttypeid.equals("")&&selectdifficultyid.equals("")){
			logger.debug("");
			confineSql="where knowledge_point_id='"+selectpointid+"' and cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";
		
		}
		//若只选择了难度
		if(selecttypeid.equals("")&&selectpointid.equals("")){
			logger.debug("");
			confineSql="where DIFFICULTY>="+int_dif_value_B+" and DIFFICULTY<"+int_dif_value_T+"";	
		}
		//若选择了题型和难度
		if(!selecttypeid.equals("")&&!selectdifficultyid.equals("")&&selectpointid.equals("")){
			logger.debug("");

			confineSql="where QUESTION_TYPE='"+selecttypeid+"' and (DIFFICULTY>="+int_dif_value_B+" and DIFFICULTY<"+int_dif_value_T+")";	
		}
		//若选择了题型和知识点
		if(!selecttypeid.equals("")&&selectdifficultyid.equals("")&&!selectpointid.equals("")){
			logger.debug("");

			confineSql="where knowledge_point_id='"+selectpointid+"' and QUESTION_TYPE='"+selecttypeid+"' and cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";	
		}
		//若选择了知识点和难度
		if(selecttypeid.equals("")&&!selectdifficultyid.equals("")&&!selectpointid.equals("")){
			logger.debug("");

			confineSql="where knowledge_point_id='"+selectpointid+"' and  (DIFFICULTY>="+int_dif_value_B+" and DIFFICULTY<"+int_dif_value_T+") and cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";	
		}
		//若3个都选择了
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
				+ "QT_BASICFIELD_"+itembank_id+" cu "+confineSql+"";// 行号必须命名为rn
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
				+ "QT_BASICFIELD_"+itembank_id+" cu where knowledge_point_id='"+selectpointid+"' and QUESTION_TYPE='"+selecttypeid+"' and (DIFFICULTY>="+int_dif_value_B+" and DIFFICULTY<"+int_dif_value_T+") and cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";// 行号必须命名为rn
		String getCountSql = "QT_BASICFIELD_"+itembank_id+" cu where knowledge_point_id='"+selectpointid+"' and QUESTION_TYPE='"+selecttypeid+"' and (DIFFICULTY>="+int_dif_value_B+" and DIFFICULTY<"+int_dif_value_T+") and cu.knowledge_point_id in (select kp.knowledge_point_id from sys_knowledge_point kp)";
*/		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,getCountSql);
		return null;
	}
	
	
	/**
	 * 试卷库管理表格数据
	 */
	public String EPDBManage() {
		//将表格中的paperuse等更改为数据字典中对应的配置项
		String useconfig="";
		List<Map> usedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='paperuse'");
		for (int  i = 0; i < usedata.size(); i++) {
			String entries_value=(String) usedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) usedata.get(i).get("name");
		    useconfig+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		
		String getDataSql = "select rownum rn,cu.epdb_id,cu.epdb_name,"
				+"(case when explain is null then '无' else explain end) explain,"
				+"(case usage "+useconfig+" end) usage from  "
				+"sys_epdb_list cu";// 行号必须命名为rn
		String getCountSql = "sys_epdb_list cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	
	/**
	 * 
	 * <p>名称：ReferExamPaper</p>
	 * <p>说明：TODO(根据试卷库查找试卷)</p>
	 * <p>参数：@param SelectedEPDB
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@param SelectedEPDB
	 * <p>@return</p>
	 */
	public String ReferExamPaper(){
					//logger.debug(selectedEPDB+"&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
					String getDataSql = "select rownum rn,cu.exam_paper_id,cu.paper_name,decode(substr(difficulty,1,1),'.','0'||difficulty,difficulty) difficulty,cu.exam_duration,cu.total_score from  sys_exam_paper cu where epdb_id='"+selectedEPDB+"'";// 行号必须命名为rn
					String getCountSql = "sys_exam_paper cu where epdb_id='"+selectedEPDB+"'";
					CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
							getCountSql);
					return null;
				}
	
	/**
	 * 
	 * <p>名称：GetTeachingClassTableDataByFieldId</p>
	 * <p>说明：通过课程Id获取该课程下所有教学班级信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
		String getDataSql = "select AllClass.rn,AllClass.curse_class_id,AllClass.teacher_id,AllClass.teaching_material_id,AllClass.class_name,(case when(AllClass.explain is null) then '无' else AllClass.explain end) explain," +
				"(case when (ut.name is null) then '无' else ut.name end) TeacherName,tm.name TMName from "
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
	 * <p>名称：GetConfigOptionTableData</p>
	 * <p>说明：获取所有配置项的表格中显示的信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>作者：HuangJu</p>
	 * <p>名称：RoleDataGet</p>
	 * <p>说明：通过角色Id获取角色列表</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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

	 * <p>author：HuangJu</p>
	 * <p>名称：TeacherListDataGet</p>
	 * <p>说明：获取教师管理功能中Table中的值</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String TeacherListDataGet() {
		// 获取要查询的数据
/*		String getDataSql = "select rownum rn,AllTeacher.* from sys_user_teacher AllTeacher " +
				"where user_id in (select cu.user_id from sys_department_member cu where department_id='"
				+ FieldId+"')";
		*/
		String getDataSql="select rownum rn,cu.USER_ID,cu.SCHOOL_ID,cu.NAME," +
				"(case when (GENDER = 1) then '男' when (GENDER = 0) then '女' else ' ' end) as GENDER, " +
				"(case when (to_char(BIRTHDAY,'yyyy-MM-dd') is null) then ' '  when (to_char(BIRTHDAY,'yyyy-MM-dd') like 'null') then ' '  else  to_char(BIRTHDAY,'yyyy-MM-dd') end) as BIRTHDAY," +
				"(case when (ID is null) then ' '  when (ID like 'null') then ' '  else ID end) as ID, "+
				"(case when (EMAIL is null) then ' ' when (EMAIL like 'null') then ' ' else EMAIL end) as EMAIL," +
				"(case when (ADDRESS is null) then ' ' when (ADDRESS like 'null') then ' ' else ADDRESS end) as ADDRESS ," +
				"(case when (TELEPHONE is null) then ' ' when (TELEPHONE like 'null') then ' ' else TELEPHONE end) as TELEPHONE  "+
				"from SYS_USER_TEACHER cu " +
				"where user_id in (select dm.user_id from sys_department_member dm where department_id='" + FieldId + "')";
		//System.out.println(getDataSql);		
		//// 获取要查询的数据的个数
		String getCountSql = "sys_department_member where department_id='" + FieldId + "'";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	/**
	 * <p>author：HuangJu</p>
	 * <p>名称：StudentListDataGet</p>
	 * <p>说明：获取教师管理功能中Table中的值</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String StudentListDataGet() {
		// 获取要查询的数据
		String getDataSql = "select rownum rn,cu.USER_ID,cu.SCHOOL_ID,cu.NAME," +
				"(case when (ADDRESS is null) then ' ' when (ADDRESS like 'null') then ' ' else ADDRESS end) as ADDRESS," +
				"(case when (TELEPHONE is null) then ' ' when (TELEPHONE like 'null') then ' ' else TELEPHONE end) as TELEPHONE " +
				"from sys_user_student cu " +
				"where user_id in (select dm.user_id from sys_department_member dm where department_id='"
				+ FieldId+"')";
				
				
		//// 获取要查询的数据的个数
		String getCountSql = "sys_department_member where department_id='" + FieldId + "'";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	/**

	 * <p>作者：HuangJu</p>
	 * <p>名称：DataDicDataGet</p>
	 * <p>说明：获取指定角色下的成员列表</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String RoleMemberGet() {
		String getDataSql="";
		//System.out.println(MemberType);
		if(MemberType.equals("teacher")){
			getDataSql= "select rownum rn,sut.user_id user_id, sut.name name , sut.school_id school_id, " +
					"(case when (sut.user_type = '1') then '教师' end ) as user_type " +
					"from (select rm.* from SYS_ROLE_MEMBER RM where rm.role_id='"+FieldId+"') srm  inner join SYS_USER_TEACHER sut" +
					" on sut.user_id=srm.user_id ";
		}
		else if(MemberType.equals("student")){
			getDataSql="select rownum rn,sus.user_id user_id ,sus.name name, sus.school_id school_id," +
					" (case when (sus.user_type ='2') then '学生' end ) as user_type " +
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

	 * <p>作者：HuangJu</p>
	 * <p>名称：DataDicDataGet</p>
	 * <p>说明：获取字典项管理功能中Table中的值</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>作者：HuangJu</p>
	 * <p>名称：GetValueOptionByDicId</p>
	 * <p>说明：通过数据字典id获取该字典项中值项的信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>名称：getKnowledgePointByCurseId</p>
	 * <p>说明：通过课程id选择该课程下知识点信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	getKnowledgePointByCurseId(){
		logger.debug(this.FieldId);
		TypeChange tc = new TypeChange();
		int IntStartIndex = tc.stringToInt(startIndex);
		int IntResults = tc.stringToInt(results);
		String	getDataSql	=	"select rn,knowledge_point_id,name,curse_id,(case when (knowledge_point_content is null)then '无' else knowledge_point_content end)knowledge_point_content from ("+"select rownum rn,cu.* from sys_knowledge_point cu"
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
	 * <p>名称：getTeachingMateialByCurseId</p>
	 * <p>说明：通过课程id选择该课程下教材信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
				+"(case when (to_char(publication_date,'yyyy-MM-dd') is null) then '无' else to_char(publication_date,'yyyy-MM-dd') end) as publication_date,"
				+"(case when publishing_house is null then '无' else publishing_house end) as publishing_house,"
				+"(case when isbn_code is null then '无' else isbn_code end) as isbn_code from "
				+ "sys_teaching_material cu"
				+ " where curse_id='"
				+ this.FieldId
				+ "'";// 行号必须命名为rn
		String	getCountSql	=	"sys_teaching_material cu where curse_id='"+ this.FieldId+ "'";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：getCurseByFieldId</p>
	 * <p>说明：通过专业id选择该专业下课程信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
				+"(case when remarks is null then '无' else remarks end) remarks,"
				+ "(case assessment_method when 0 then '考查' when"
				+ " 1 then '考试' end) assessment_method from  "
				+ "sys_curse cu"
				+ " where special_field_id='"
				+ this.FieldId
				+ "'";// 行号必须命名为rn
		String	getCountSql	=	"sys_curse cu";
		CDTJFYS.CreateDataTableJson(dir, results, sort, startIndex, getDataSql,
				getCountSql);
		return null;
	}
	
	/**
	 * 
	 * <p>名称：GetAdminiClassTableDataByFieldId</p>
	 * <p>说明：通过课程Id获取该课程下所有教学班级信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>名称：GetTeachingClassMemberTableDataByTeachingClassId</p>
	 * <p>说明：通过开课班级Id获取该开课班级下所有成员信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>名称：getTeachingCourseByTeacherId</p>
	 * <p>说明：通过教师Id获取该教师的所有教学课程和班级信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
	 * <p>名称：getTeachingCourseByTeacherIds</p>
	 * <p>说明：通过教师机构获取该机构下所有教师的教学课程和班级信息</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public String getTeachingCourseByTeacherIds() {
		logger.debug(TeacherIds);
		//收到的TeacherIds格式为   TeacherId1 TeacherId2 TeacherId3 TeacherId4 ....
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

	 * <p>作者：HuangJu</p>
	 * <p>名称：StudentScoreOnTeachingClassGet</p>
	 * <p>说明查询指定教学班级下的学生的成绩<p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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

	 * <p>作者：HuangJu</p>
	 * <p>名称：StudentScoreOnStudentSelf</p>
	 * <p>说明：学生自助查询成绩</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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

	 * <p>作者：HuangJu</p>
	 * <p>名称：StudentScoreOnClass</p>
	 * <p>说明：按行政班级查询学生成绩</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
		//题型数据转换
		String quesTypeSql="";
		List<Map> quesTypedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='tixing'");
		for (int i = 0; i < quesTypedata.size(); i++) {
			String entries_value=(String) quesTypedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) quesTypedata.get(i).get("name");
			quesTypeSql+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		
		for(int	i=0;i<ids.length;i++){
			//如果是第一个
			if(!flag){
				getDataSql+="select QUESTION_ID,(case QUESTION_TYPE "+quesTypeSql+" end) QUESTION_TYPE, decode(substr(difficulty,1,1),'.','0'||difficulty,difficulty) difficulty,TIME_USE,DEFAULTPOINT,KNOWLEDGE_POINT_ID,'"+ids[i]+"' as IBID from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				temp+="select QUESTION_ID,QUESTION_TYPE,DIFFICULTY,DEFAULTPOINT,TIME_USE,KNOWLEDGE_POINT_ID from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				for(int	j=0;j<kps.length;j++){
					temp+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
					getDataSql+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
				}
				flag=true;
			}
			//不是第一个试题库
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
		//题型数据转换
		String quesTypeSql="";
		List<Map> quesTypedata	=	dao.executeQuery("select dictionary_entries_value,name from sys_dictionary_entries_value where dictionary_entries_id='tixing'");
		for (int i = 0; i < quesTypedata.size(); i++) {
			String entries_value=(String) quesTypedata.get(i).get("dictionary_entries_value");
			String entries_name=(String) quesTypedata.get(i).get("name");
			quesTypeSql+=" when '"+entries_value+"' then '"+entries_name+"'";
			}
		
		for(int	i=0;i<ids.length;i++){
			//如果是第一个
			if(!flag){
				getDataSql+="select QUESTION_ID,QUESTION_TYPE qt,(case QUESTION_TYPE "+quesTypeSql+" end) QUESTION_TYPE,decode(substr(difficulty,1,1),'.','0'||difficulty,difficulty) difficulty,DEFAULTPOINT,KNOWLEDGE_POINT_ID,TIME_USE,'"+ids[i]+"' as IBID from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				temp+="select * from QT_BASICFIELD_"+ids[i]+" where KNOWLEDGE_POINT_ID='' ";
				for(int	j=0;j<kps.length;j++){
					temp+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
					getDataSql+="or KNOWLEDGE_POINT_ID='"+kps[j].substring(3)+"' ";
				}
				flag=true;
			}
			//不是第一个试题库
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
	 * <p>名称：getAddedQuesByIds</p>
	 * <p>说明：TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
	 * <p>@return</p>
	 */
	public	String	getAddedQuesByIds(){

		logger.debug(IBIds);
		logger.debug(KPIds);
		if("".equals(IBIds)||null==IBIds||"".equals(KPIds)||null==KPIds){
			logger.info("没有选中试题库或者试题");
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
		//题型数据转换
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
		logger.debug("选中了一些试题，下面将开始查找这些试题信息");
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
	 * <p>名称：getCurseBySpecialFieldId</p>
	 * <p>说明：课程查询，通过专业ID查询课程列表</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
		String getDataSql = "select rn,curse_id,curse_name,special_field_id,curse_credit,(case assessment_method when 0 then '考查' when 1 then '考试' end) assessment_method,(case when remarks is null then '无' else remarks end) remarks from(select rownum rn,cu.* from sys_curse cu where special_field_id='"
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
	 * <p>名称：getAllCourse</p>
	 * <p>说明：获取所有课程</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：String 返回类型</p>
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
		String getDataSql = "select rn,curse_id,curse_name,special_field_id,curse_credit,(case assessment_method when 0 then '考查' when 1 then '考试' end) assessment_method,(case when remarks is null then '无' else remarks end) remarks from(select rownum rn,cu.* from sys_curse cu "
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




