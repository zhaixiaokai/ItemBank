	
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	PaperCreateAutoServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-28 ����5:55:23
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-28 ����5:55:23
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
 * <p>������net.ib.util.service.impl.PaperCreateAutoServiceImpl </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
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
		logger.debug("+++++++++++++++++++++++++++++++++++++��ʼ������+++++++++++++++++++++++++++++++++++++");
		logger.debug(XZKPList);
		String[]	ibList;
		if(null!=IBList){
			ibList=	IBList.split(",");
		}
		else{
			logger.info("��ѡ�����Ϊ��");
			this.writeResponse("{\"text\":\"û��ѡ�������\"}");
			return null;
		}
		//֪ʶ���б�
		String[]	knowledgePointList;		
		if(null!=XZKPList){
			knowledgePointList=	XZKPList.split(",");
		}else{
			logger.info("֪ʶ���б�Ϊ��");
			this.writeResponse("{\"text\":\"֪ʶ���б�Ϊ��\"}");
			return null;
		}
		//���֪ʶ���б�Ϊ�����������Ҳ��Ȼ����Ϊ�գ�������ȥ���
		//ѡ���жϡ���ա����Ķ�Ӧ֪ʶ����Ŀ�ĸ���
		String[] xzCountList	=	XZKPCount.split(",");
		String[]	pdCountList	=	PDKPCount.split(",");
		String[] tkCountList	=	TKKPCount.split(",");
		String[] jdCountList	=	JDKPCount.split(",");
		for(int i=0;i<knowledgePointList.length;i++){
			knowledgePointList[i]=knowledgePointList[i].substring(3);
			logger.debug(knowledgePointList[i]);
		}
		/**
		 * �����Ҫ�������Ծ������б�
		 */
		ArrayList<PaperConfig>	paperConfigList	=	this.createConfig(knowledgePointList, xzCountList, pdCountList, tkCountList, jdCountList);
		logger.debug("��ȡ����ǰ̨������");
		//��ȡ����
			//��һ��������л�ȡ֪ʶ���б���������֪ʶ���Ӧ����������
			List<Map>	temp	=	this.getQuesByKnowledgePointAndIBId(ibList,knowledgePointList,xzCountList,tkCountList,pdCountList,jdCountList);
			//�������б��List<Map>����ת��ΪArrayList<QuestionVo>����
			/**
			 * �����Ҫ��������ѡ�����б�
			 */
			ArrayList<QuestionVo> quesList	=	this.createQuestionList(temp);
			//��ȡ���˴�ѡ�����б�
			AutoPaper	autoPaper	=	new	AutoPaper();
			Chromosome	bestChrom	=	autoPaper.getBestChromosome(paperConfigList, quesList, Double.parseDouble(difficulty), Double.parseDouble(totalScore));
			//�����Ϊ�գ��򷵻�
			if(bestChrom==null){
				logger.debug("û���㹻���⣬ѡ�����������");

				this.writeResponse("{\"result\":\"fail\",\"text\":\"û���㹻���⣬��ѡ����������⣡\"}");

				return null;
			}
			logger.debug("+++++++++++++++++++++++++++++++++++++++++���ɹ�+++++++++++++++++++++++++++++++++++++");

			String dir	=	createPaperDoc(bestChrom,totalScore,totalTime);
			String res	=	"{\"result\":\"succ\",\"text\":\"���ɹ�\",\"dir\":\""+dir+"\"}";
			logger.debug("+++++++++++++++++++++++++++++++++++"+res);
			this.writeResponse(res);
			//{\"result\":\"failedName\",\"text\":\"�ý�ʦ�Ѿ����ڣ�����������\"}
			return null;
	}
	/**
	 * 
	 * <p>���ƣ�createConfig</p>
	 * <p>˵����ͨ��ǰ̨������������������������Ϣ�б�</p>
	 * <p>������@param knowledgePointList			֪ʶ���б�
	 * <p>������@param xzCountList						ѡ��������б�
	 * <p>������@param pdCountList					�ж�������б�
	 * <p>������@param tkCountList						���������б�
	 * <p>������@param jdCountList						���������б�
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��ArrayList<PaperConfig> ��������</p>
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<PaperConfig>	createConfig(String[] knowledgePointList,String[]xzCountList,String[]pdCountList,String[]tkCountList,String[]jdCountList){

		ArrayList<PaperConfig> ret	=	new	ArrayList<PaperConfig>();
		PaperConfig	paperConfig	=	new	PaperConfig(null, null);
		QuesConfig	quesConfig	=	new	QuesConfig(null, null, 0, 0);
		ArrayList<QuesConfig>	 arr	=	new	ArrayList<QuesConfig>();
		//����ѡ���config������ӵ�list��
		for(int i=0;i<knowledgePointList.length;i++){
			quesConfig.setKnowledgePointId(knowledgePointList[i]);
			quesConfig.setQuesNum(Integer.parseInt(xzCountList[i]));
			arr.add((QuesConfig) quesConfig.deepClone());
		}
		paperConfig.setQuesConfig((ArrayList<QuesConfig>) arr.clone());
		paperConfig.setQuesType("xuanze");
		ret.add((PaperConfig) paperConfig.deepClone());
		//������յ�config������ӵ�list��
		arr	=	new	ArrayList<QuesConfig>();
		for(int i=0;i<knowledgePointList.length;i++){
			quesConfig.setKnowledgePointId(knowledgePointList[i]);
			quesConfig.setQuesNum(Integer.parseInt(tkCountList[i]));
			arr.add((QuesConfig) quesConfig.deepClone());
		}
		paperConfig.setQuesConfig((ArrayList<QuesConfig>) arr.clone());
		paperConfig.setQuesType("tiankong");
		ret.add((PaperConfig) paperConfig.deepClone());
		//�����жϵ�config������ӵ�list��
		arr	=	new	ArrayList<QuesConfig>();
		for(int i=0;i<knowledgePointList.length;i++){
			quesConfig.setKnowledgePointId(knowledgePointList[i]);
			quesConfig.setQuesNum(Integer.parseInt(pdCountList[i]));
			arr.add((QuesConfig) quesConfig.deepClone());
		}
		paperConfig.setQuesConfig((ArrayList<QuesConfig>) arr.clone());
		paperConfig.setQuesType("panduan");
		ret.add((PaperConfig) paperConfig.deepClone());
		//���ɼ���config������ӵ�list��
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
	 * <p>���ƣ�getQuesByKnowledgePointAndIBId</p>
	 * <p>˵��������itembankIds��ָ����������з���֪ʶ���б����������</p>
	 * <p>������@param itembankIds				������б�
	 * <p>������@param knowledgePointIds		֪ʶ��id	
	 * <p>������@param xzCountList					ѡ��������б�
	 * <p>������@param tkCountList					���������б�
	 * <p>������@param pdCountList				�ж�������б�
	 * <p>������@param jdCountList					���������б�
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��List<Map> ��������</p>
	 */
	@SuppressWarnings("rawtypes")
	private List<Map> getQuesByKnowledgePointAndIBId(String[]	itembankIds,String[]knowledgePointIds,String[]xzCountList, String[] tkCountList, String[] pdCountList, String[] jdCountList){
		
		String	sql	=	"";
		boolean	flag	=	false;
		for(int	i=0;i<itembankIds.length;i++){
			//����ǵ�һ��
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
			//���ǵ�һ�������
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
		logger.debug("�ļ�����·����"+path);
		wordBean.OpenFile(path+"\\paperDoc.doc");
		int	tihao=1;
		String[] tixinghao={"һ��","����","����","�ġ�"};
		int tixingIndex=0;
		//��ÿ������ѭ��
		for(int	i=0;i<BestQuesList.size();i++){

			if(null!=BestQuesList.get(i)){
				String temp="";
				if(0!=BestQuesList.get(i).size()){
					wordBean.InsertText(tixinghao[tixingIndex]);
					wordBean.moveEnd();
					if("xuanze".equals(BestQuesList.get(i).get(0).getQuesType())){
						temp="ѡ����";
					}else if("panduan".equals(BestQuesList.get(i).get(0).getQuesType())){
						temp="�ж���";
					}else if("tiankong".equals(BestQuesList.get(i).get(0).getQuesType())){
						temp="�����";
					}else if("jianda".equals(BestQuesList.get(i).get(0).getQuesType())){
						temp="�����";
					}
					wordBean.InsertText(temp);
					wordBean.insertEnter();
					tixingIndex++;
				}
			}
			
			
			for(int j=0;j<BestQuesList.get(i).size();j++){
				//��ȡÿ������
				QuestionVo question	=	BestQuesList.get(i).get(j);
				String	TableName	=	question.getIBId();
				String	questionid	=	question.getQuesId();
				InputStream in=service.getBlobInputStreamFromTable("QT_BLOBFIELD_"+TableName,"question_id",questionid,"QUESTIONCONTENT");
				String	QuesFileTempId	=	UUID.randomUUID().toString();
				File f=new File(path+'\\'+ QuesFileTempId + ".doc");
				//��io��ת�����ļ�
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
				  //����ѡ����
				if(question.getQuesType().equals("xuanze")){
					wordBean.getContextFromAnotherFileTableCell(path+'\\'+ QuesFileTempId + ".doc",1,2,2);
					wordBean.moveEnd();
					wordBean.getContextFromAnotherFileTableCell(path+'\\'+ QuesFileTempId + ".doc",1,3,2);
					wordBean.moveEnd();
				}else{
					//����ѡ�������������
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
		//�رղ��ұ���
		wordBean.save();
		wordBean.closeWord();
		logger.debug("uniqueSavePath:"+uniqueSavePath);
		return uniqueSavePath;
	}
	/**
	 * 
	 * <p>���ƣ�writeResponse</p>
	 * <p>˵����Ϊresponseд�뷵��ֵ</p>
	 * <p>������@param message �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
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
			logger.debug("û��ѡ������");
			this.writeResponse("{\"text\":\"û��ѡ������\",\"result\":\"fail\"}");
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
		String res	=	"{\"result\":\"succ\",\"text\":\"���ɹ�\",\"dir\":\""+dir+"\"}";
		this.writeResponse(res);
		return null;
	}
	
}
