	
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	PaperCreateAutoServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-28 下午5:55:23
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-28 下午5:55:23
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.config.WordBeanProperty;
import net.ib.util.dao.Dao;
import net.ib.util.data.Chromosome;
import net.ib.util.data.PaperConfig;
import net.ib.util.data.QuesConfig;
import net.ib.util.data.QuestionVo;
import net.ib.util.service.PaperCreateAutoService;
import net.ib.util.service.Service;


  /**
 * <p>类名：net.ib.util.service.impl.PaperCreateAutoServiceImpl </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class PaperCreateAutoServiceImpl implements PaperCreateAutoService {
	private static Logger logger = Logger.getLogger(PaperCreateAutoServiceImpl.class);
	Dao	dao;
	Service	service;
	public Dao getDao() {
		return dao;
	}
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	@SuppressWarnings("rawtypes")
	@Override
	public String createPaper(String difficulty, String targetEPDB,
			String totalScore, String totalTime, String finCourseId,
			String useage, String IBList, String XZKPList, String XZKPCount,
			String PDKPList, String PDKPCount, String TKKPList,
			String TKKPCount, String JDKPList, String JDKPCount) {
		// TODO Auto-generated method stub
		logger.debug("+++++++++++++++++++++++++++++++++++++开始组卷过程+++++++++++++++++++++++++++++++++++++");
		logger.debug(XZKPList);
		String[]	ibList;
		if(null!=IBList){
			ibList=	IBList.split(",");
		}
		else{
			logger.info("备选试题库为空");
			this.writeResponse("{\"text\":\"没有选中试题库\"}");
			return null;
		}
		//知识点列表
		String[]	knowledgePointList;		
		if(null!=XZKPList){
			knowledgePointList=	XZKPList.split(",");
		}else{
			logger.info("知识点列表为空");
			this.writeResponse("{\"text\":\"知识点列表为空\"}");
			return null;
		}
		//如果知识点列表不为空则试题个数也当然不会为空，所以略去检查
		//选择、判断、填空、简答的对应知识点题目的个数
		String[] xzCountList	=	XZKPCount.split(",");
		String[]	pdCountList	=	PDKPCount.split(",");
		String[] tkCountList	=	TKKPCount.split(",");
		String[] jdCountList	=	JDKPCount.split(",");
		for(int i=0;i<knowledgePointList.length;i++){
			knowledgePointList[i]=knowledgePointList[i].substring(3);
			logger.debug(knowledgePointList[i]);
		}
		/**
		 * 组卷重要参数：试卷配置列表
		 */
		ArrayList<PaperConfig>	paperConfigList	=	this.createConfig(knowledgePointList, xzCountList, pdCountList, tkCountList, jdCountList);
		logger.debug("获取到了前台的配置");
		//获取试题
			//从一个试题库中获取知识点列表中所包含知识点对应的所有试题
			List<Map>	temp	=	this.getQuesByKnowledgePointAndIBId(ibList,knowledgePointList,xzCountList,tkCountList,pdCountList,jdCountList);
			//将试题列表从List<Map>类型转换为ArrayList<QuestionVo>类型
			/**
			 * 组卷重要参数：待选试题列表
			 */
			ArrayList<QuestionVo> quesList	=	this.createQuestionList(temp);
			//获取到了待选试题列表
			AutoPaper	autoPaper	=	new	AutoPaper();
			Chromosome	bestChrom	=	autoPaper.getBestChromosome(paperConfigList, quesList, Double.parseDouble(difficulty), Double.parseDouble(totalScore));
			//组卷结果为空，则返回
			if(bestChrom==null){
				logger.debug("没有足够试题，选择其他试题库");

				this.writeResponse("{\"result\":\"fail\",\"text\":\"没有足够试题，请选择其他试题库！\"}");

				return null;
			}
			logger.debug("+++++++++++++++++++++++++++++++++++++++++组卷成功+++++++++++++++++++++++++++++++++++++");

			String dir	=	createPaperDoc(bestChrom,totalScore,totalTime);
			String res	=	"{\"result\":\"succ\",\"text\":\"组卷成功\",\"dir\":\""+dir+"\"}";
			logger.debug("+++++++++++++++++++++++++++++++++++"+res);
			this.writeResponse(res);
			//{\"result\":\"failedName\",\"text\":\"该教师已经存在，请重新输入\"}
			return null;
	}
	/**
	 * 
	 * <p>名称：createConfig</p>
	 * <p>说明：通过前台传来的配置数据生成配置信息列表</p>
	 * <p>参数：@param knowledgePointList			知识点列表
	 * <p>参数：@param xzCountList						选择题个数列表
	 * <p>参数：@param pdCountList					判断题个数列表
	 * <p>参数：@param tkCountList						填空题个数列表
	 * <p>参数：@param jdCountList						简答题个数列表
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：ArrayList<PaperConfig> 返回类型</p>
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<PaperConfig>	createConfig(String[] knowledgePointList,String[]xzCountList,String[]pdCountList,String[]tkCountList,String[]jdCountList){

		ArrayList<PaperConfig> ret	=	new	ArrayList<PaperConfig>();
		PaperConfig	paperConfig	=	new	PaperConfig(null, null);
		QuesConfig	quesConfig	=	new	QuesConfig(null, null, 0, 0);
		ArrayList<QuesConfig>	 arr	=	new	ArrayList<QuesConfig>();
		//生成选择的config并且添加到list中
		for(int i=0;i<knowledgePointList.length;i++){
			quesConfig.setKnowledgePointId(knowledgePointList[i]);
			quesConfig.setQuesNum(Integer.parseInt(xzCountList[i]));
			arr.add((QuesConfig) quesConfig.deepClone());
		}
		paperConfig.setQuesConfig((ArrayList<QuesConfig>) arr.clone());
		paperConfig.setQuesType("xuanze");
		ret.add((PaperConfig) paperConfig.deepClone());
		//生成填空的config并且添加到list中
		arr	=	new	ArrayList<QuesConfig>();
		for(int i=0;i<knowledgePointList.length;i++){
			quesConfig.setKnowledgePointId(knowledgePointList[i]);
			quesConfig.setQuesNum(Integer.parseInt(tkCountList[i]));
			arr.add((QuesConfig) quesConfig.deepClone());
		}
		paperConfig.setQuesConfig((ArrayList<QuesConfig>) arr.clone());
		paperConfig.setQuesType("tiankong");
		ret.add((PaperConfig) paperConfig.deepClone());
		//生成判断的config并且添加到list中
		arr	=	new	ArrayList<QuesConfig>();
		for(int i=0;i<knowledgePointList.length;i++){
			quesConfig.setKnowledgePointId(knowledgePointList[i]);
			quesConfig.setQuesNum(Integer.parseInt(pdCountList[i]));
			arr.add((QuesConfig) quesConfig.deepClone());
		}
		paperConfig.setQuesConfig((ArrayList<QuesConfig>) arr.clone());
		paperConfig.setQuesType("panduan");
		ret.add((PaperConfig) paperConfig.deepClone());
		//生成简答的config并且添加到list中
		arr	=	new	ArrayList<QuesConfig>();
		for(int i=0;i<knowledgePointList.length;i++){
			quesConfig.setKnowledgePointId(knowledgePointList[i]);
			quesConfig.setQuesNum(Integer.parseInt(jdCountList[i]));
			arr.add((QuesConfig) quesConfig.deepClone());
		}
		paperConfig.setQuesConfig((ArrayList<QuesConfig>) arr.clone());
		paperConfig.setQuesType("jianda");
		ret.add((PaperConfig) paperConfig.deepClone());
		return ret;
		
	}
	/**
	 * 
	 * <p>名称：getQuesByKnowledgePointAndIBId</p>
	 * <p>说明：返回itembankIds中指定的试题库中符合知识点列表的所有数据</p>
	 * <p>参数：@param itembankIds				试题库列表
	 * <p>参数：@param knowledgePointIds		知识点id	
	 * <p>参数：@param xzCountList					选择题个数列表
	 * <p>参数：@param tkCountList					填空题个数列表
	 * <p>参数：@param pdCountList				判断题个数列表
	 * <p>参数：@param jdCountList					简答题个数列表
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：List<Map> 返回类型</p>
	 */
	@SuppressWarnings("rawtypes")
	private List<Map> getQuesByKnowledgePointAndIBId(String[]	itembankIds,String[]knowledgePointIds,String[]xzCountList, String[] tkCountList, String[] pdCountList, String[] jdCountList){
		
		String	sql	=	"";
		boolean	flag	=	false;
		for(int	i=0;i<itembankIds.length;i++){
			//如果是第一个
			if(!flag){
				sql+="select QUESTION_ID,QUESTION_TYPE,DIFFICULTY,DEFAULTPOINT,KNOWLEDGE_POINT_ID,'"+itembankIds[i]+"' as IBID from QT_BASICFIELD_"+itembankIds[i]+" where KNOWLEDGE_POINT_ID='' ";
				
				for(int	j=0;j<knowledgePointIds.length;j++){
					if(Integer.parseInt(xzCountList[j])==0&&Integer.parseInt(pdCountList[j])==0&&Integer.parseInt(tkCountList[j])==0&&Integer.parseInt(jdCountList[j])==0){
						
					}
					else{
						sql+="or KNOWLEDGE_POINT_ID='"+knowledgePointIds[j]+"' ";
					}
				}
				
				flag=true;
			}
			//不是第一个试题库
			else{
				sql+="union select QUESTION_ID,QUESTION_TYPE,DIFFICULTY,DEFAULTPOINT,KNOWLEDGE_POINT_ID,'"+itembankIds[i]+"' as IBID from QT_BASICFIELD_"+itembankIds[i]+" where KNOWLEDGE_POINT_ID='' ";
				
				for(int	j=0;j<knowledgePointIds.length;j++){
					if(Integer.parseInt(xzCountList[j])==0&&Integer.parseInt(pdCountList[j])==0&&Integer.parseInt(tkCountList[j])==0&&Integer.parseInt(jdCountList[j])==0){
						
					}
					else{
						sql+="or KNOWLEDGE_POINT_ID='"+knowledgePointIds[j]+"' ";
					}
				}
			}
		}
		logger.debug(sql);
		List<Map>	ret=	dao.executeQuery(sql);
		logger.debug(ret);
		return ret;
		
	}
	
	@SuppressWarnings("rawtypes")
	private	ArrayList<QuestionVo>	createQuestionList(List<Map> list){
		QuestionVo	ques	=	new	QuestionVo();
		ArrayList<QuestionVo>	ret	=	new	ArrayList<QuestionVo>();
		for(int	i=0;i<list.size();i++){
			Map<?, ?>	map	=	list.get(i);
			ques.setIBId((String) map.get("ibid"));
			ques.setKnowledgePointId((String) map.get("knowledge_point_id"));
			ques.setQuesId((String) map.get("question_id"));
			ques.setQuesType((String) map.get("question_type"));
			ques.setDefaultPoint(Double.parseDouble((String) map.get("defaultpoint")));
			ques.setQuesDifficulty(Double.parseDouble((String) map.get("difficulty")));
			
			ret.add((QuestionVo) ques.deepClone());
		}
		
		return ret;
	}
	
	
	public String	createPaperDoc(Chromosome chrom,String	totalScore,String totalTime){
		ArrayList<ArrayList<QuestionVo>>	BestQuesList	=	chrom.getChromosom();
		JacobWordBeanServiceImpl	wordBean	=new	JacobWordBeanServiceImpl();
		String uniqueSavePath = UUID.randomUUID().toString();
		String	savePath	=	"\\"+uniqueSavePath;
		String path=ServletActionContext.getServletContext().getRealPath(savePath);
		File dir = new File(path);
		dir.mkdir();
		logger.debug(path);
		wordBean.CreateDoc("paperDoc",path);
		wordBean.SetVisible(WordBeanProperty.WORD_VISABLE);
		logger.debug("文件保存路径："+path);
		wordBean.OpenFile(path+"\\paperDoc.doc");
		int	tihao=1;
		String[] tixinghao={"一、","二、","三、","四、"};
		int tixingIndex=0;
		//对每种题型循环
		for(int	i=0;i<BestQuesList.size();i++){

			if(null!=BestQuesList.get(i)){
				String temp="";
				if(0!=BestQuesList.get(i).size()){
					wordBean.InsertText(tixinghao[tixingIndex]);
					wordBean.moveEnd();
					if("xuanze".equals(BestQuesList.get(i).get(0).getQuesType())){
						temp="选择题";
					}else if("panduan".equals(BestQuesList.get(i).get(0).getQuesType())){
						temp="判断题";
					}else if("tiankong".equals(BestQuesList.get(i).get(0).getQuesType())){
						temp="填空题";
					}else if("jianda".equals(BestQuesList.get(i).get(0).getQuesType())){
						temp="简答题";
					}
					wordBean.InsertText(temp);
					wordBean.insertEnter();
					tixingIndex++;
				}
			}
			
			
			for(int j=0;j<BestQuesList.get(i).size();j++){
				//获取每道试题
				QuestionVo question	=	BestQuesList.get(i).get(j);
				String	TableName	=	question.getIBId();
				String	questionid	=	question.getQuesId();
				InputStream in=service.getBlobInputStreamFromTable("QT_BLOBFIELD_"+TableName,"question_id",questionid,"QUESTIONCONTENT");
				String	QuesFileTempId	=	UUID.randomUUID().toString();
				File f=new File(path+'\\'+ QuesFileTempId + ".doc");
				//将io流转换成文件
				  try {
					  OutputStream out=new FileOutputStream(f);
					  byte buf[]=new byte[1024];
					  int len;
					  while((len=in.read(buf))>0)
					  out.write(buf,0,len);
					  out.close();
					  in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				  wordBean.InsertText(""+tihao+".  ");
				  //处理选择题
				if(question.getQuesType().equals("xuanze")){
					wordBean.getContextFromAnotherFileTableCell(path+'\\'+ QuesFileTempId + ".doc",1,2,2);
					wordBean.moveEnd();
					wordBean.getContextFromAnotherFileTableCell(path+'\\'+ QuesFileTempId + ".doc",1,3,2);
					wordBean.moveEnd();
				}else{
					//处理选择题以外的试题
					wordBean.getContextFromAnotherFileTableCell(path+'\\'+ QuesFileTempId + ".doc",1,2,2);
					wordBean.moveEnd();
				}
				tihao++;
			}
		}
		int count	= wordBean.CountTable();
		for(int i=1;i<=count;i++){
			wordBean.setTableBorder(i, 0);
		}
		wordBean.moveEnd();
		wordBean.InsertText("------END------");
		wordBean.findTextAndCopy("------END------");
		wordBean.moveStart();
		wordBean.replaceAllText("------END------", " ");
		//关闭并且保存
		wordBean.save();
		wordBean.closeWord();
		logger.debug("uniqueSavePath:"+uniqueSavePath);
		return uniqueSavePath;
	}
	/**
	 * 
	 * <p>名称：writeResponse</p>
	 * <p>说明：为response写入返回值</p>
	 * <p>参数：@param message 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param message</p>
	 */
	private	void	writeResponse(String message){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter pw = response.getWriter();
			pw.print(message);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("unchecked")
	public String	autoPaperOnHand(String ibs,String ids){
		if(ibs==null||"".equals(ibs)||ids==null||"".equals(ids)){
			logger.debug("没有选中试题");
			this.writeResponse("{\"text\":\"没有选中试题\",\"result\":\"fail\"}");
			return null;
		}
		String[] ibids=	ibs.split(",");
		String[] quids=ids.split(",");
		String	getDataSql	=	"";
		logger.debug("+++++++++++getAddedQuesByIds++++++++++");
		boolean flag	=	false;
		for(int i=0;i<ibids.length;i++){
			if(!flag){
				getDataSql	=	"select QUESTION_ID,QUESTION_TYPE,DIFFICULTY,DEFAULTPOINT,KNOWLEDGE_POINT_ID,TIME_USE,'"+ibids[i]+"' as IBID from QT_BASICFIELD_"+ibids[i]+" where question_id='"+quids[i]+"'";
				flag	=	true;
			}else{
				getDataSql	+=	" union select QUESTION_ID,QUESTION_TYPE,DIFFICULTY,DEFAULTPOINT,KNOWLEDGE_POINT_ID,TIME_USE,'"+ibids[i]+"' as IBID from QT_BASICFIELD_"+ibids[i]+" where question_id='"+quids[i]+"'";
			}
		}
		@SuppressWarnings("rawtypes")
		List<Map> list	=	dao.executeQuery(getDataSql);
		Chromosome	ch	=	new	Chromosome();
		ArrayList<QuestionVo>	quesListXZ	=	new	ArrayList<QuestionVo>();
		ArrayList<QuestionVo>	quesListPD	=	new	ArrayList<QuestionVo>();
		ArrayList<QuestionVo>	quesListJD	=	new	ArrayList<QuestionVo>();
		ArrayList<QuestionVo>	quesListTK	=	new	ArrayList<QuestionVo>();
		QuestionVo	quesTemp	=	new	QuestionVo();
		for(int i=0;i<list.size();i++){
			String	quesType	=	(String) list.get(i).get("question_type");
			//quesTemp.setDefaultPoint((Double) list.get(i).get("defaultpoint"));
			quesTemp.setIBId((String) list.get(i).get("ibid"));
			quesTemp.setKnowledgePointId((String) list.get(i).get("knowledge_point_id"));
			quesTemp.setQuesId((String) list.get(i).get("question_id"));
			quesTemp.setQuesType(quesType);
			if("xuanze".equals(quesType)){
				quesListXZ.add((QuestionVo) quesTemp.deepClone());
			}else if("panduan".equals(quesType)){
				quesListPD.add((QuestionVo) quesTemp.deepClone());
			}else if("tiankong".equals(quesType)){
				quesListTK.add((QuestionVo) quesTemp.deepClone());
			}else if("jianda".equals(quesType)){
				quesListJD.add((QuestionVo) quesTemp.deepClone());
			}
		}
		ArrayList<ArrayList<QuestionVo>> chrome	=	new	ArrayList<ArrayList<QuestionVo>>();
		chrome.add((ArrayList<QuestionVo>) quesListXZ.clone());
		chrome.add((ArrayList<QuestionVo>) quesListTK.clone());
		chrome.add((ArrayList<QuestionVo>) quesListPD.clone());
		chrome.add((ArrayList<QuestionVo>) quesListJD.clone());
		ch.setChromosom(chrome);
		String dir	=	createPaperDoc(ch,"", "");
		String res	=	"{\"result\":\"succ\",\"text\":\"组卷成功\",\"dir\":\""+dir+"\"}";
		this.writeResponse(res);
		return null;
	}
	
}
