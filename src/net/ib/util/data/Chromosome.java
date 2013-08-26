
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	Chromosome.java
 * | 包名：net.ib.util.data
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-20 下午4:54:00
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-20 下午4:54:00
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ArrayList;
import net.ib.base.utils.Arith;

import org.apache.log4j.Logger;


  /**
 * <p>类名：net.ib.util.data.Chromosome </p>
 * <p>描述：染色体</p>
 * <p></p>
 */
public class Chromosome implements	Serializable{
	//private static Logger logger = Logger.getLogger(Chromosome.class);
	/**
	 * 试卷配置，包括所有配置（选择、填空、简答、判断）
	 * list中的每个元素表示一种题型的配置
	 * 例如 paperConfigList[0]表示选择题的所有配置信息
	 */
	private ArrayList<PaperConfig> paperConfigList;
	/**
	 * 试题列表
	 */
	private ArrayList<QuestionVo> quesList;
	/**
	 * 染色体，染色体中包含几个list，每个list中存储不同题型对应的试题（基因）
	 */
	private ArrayList<ArrayList<QuestionVo>> chromosom=new ArrayList<ArrayList<QuestionVo>>();

	
	/**
	 * 目标试卷的难度系数
	 */
	double	targetDifficulty=0;
	/**
	 * 目标试卷的总分
	 */
	double	targetTotalScore=0;
	
	public	Chromosome(){
		
	}
	/**
	 * 带参数的构造函数
	 * @param paperConfigList  	试卷配置
	 * @param quesList			试题列表
	 */
/*	public Chromosome(ArrayList<PaperConfig> paperConfigList,ArrayList<QuestionVo> quesList){
		this.paperConfigList=paperConfigList;
		this.quesList=quesList;
		this.ChromosomeInit();
	}*/
	public Chromosome(ArrayList<PaperConfig> paperConfigList,ArrayList<QuestionVo> quesList,double	targetDifficulty,double targetTotalScore){
		this.paperConfigList=paperConfigList;
		this.quesList=quesList;
		//this.ChromosomeInit();
		this.targetDifficulty=targetDifficulty;
		this.targetTotalScore=targetTotalScore;
	}
	/**
	 * 
	 * <p>名称：ChromosomeInit</p>
	 * <p>说明：基因初始化</p>
	 * <p>参数： 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p></p>
	 */
	public String ChromosomeInit(){
		 //paperConfig：单个试题类型的试题配置 例如选择题的配置，包括list中的内容为选择题对应知识点难度题目数的配置
		PaperConfig paperConfig=null;
		//quesConfig:试题配置类型，对应每隔知识点配置的试题数目、难度
		QuesConfig	quesConfig = null;
		//对试卷配置循环   获取到试卷配置
		for(int i=0;i<paperConfigList.size();i++){
			paperConfig=paperConfigList.get(i);
			//configType:试题配置类型（选择，填空，简答，判断）
			String configType=paperConfig.getQuesType();
			ArrayList<QuesConfig> quesConfigList=paperConfig.getQuesConfig();
			ArrayList<QuestionVo>	oneTypeQuesList=new	ArrayList<QuestionVo>();
			this.chromosom.add(oneTypeQuesList);
			//对每种试题类型的配置循环
			for(int j=0;j<quesConfigList.size();j++){
				quesConfig=quesConfigList.get(j);
				String knowledgePointId	=	quesConfig.getKnowledgePointId();
				int quesNum=quesConfig.getQuesNum();
				//获取到符合知识点和试题类型的题目列表
				ArrayList<QuestionVo> quesList	=	this.getQuesByTypeAndKnowledgePoint(configType, knowledgePointId);
				//试题库中试题不够操作者配置的数目
				if(quesList.size()<quesNum){
					//logger.info("染色体初始化失败!");
					//logger.info("试题类型："+configType+"   知识点："+knowledgePointId+"的试题不足");
					return "initFailed";
				}
				ArrayList<Integer> randList = new ArrayList<Integer>();
				//随机初始化染色体
				
				for(int o=0;o<quesNum;o++){
					//生成随机数并且保证数字不重复
					int	ran=Utility.getDifferentNumber(randList, 0, quesList.size()-1);
					randList.add(ran);
					//为染色体中添加该基因（试题）
					//chromosom.add(quesList.get(ran));
					this.chromosom.get(i).add(quesList.get(ran));
				}
			}
		}
		return null;
	}
	/**
	 * <p>名称：getQuesNum</p>
	 * <p>说明：获取一条染色体中基因(试题)的个数</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：int 返回类型</p>
	 * <p>@return</p>
	 */
	public	int	getQuesNum(){
		int	ret=0;
		//遍历每个题型
		for(int i=0;i<this.chromosom.size();i++){
			ArrayList<QuestionVo> TypeList	=	this.chromosom.get(i);
			//遍历每个题型中的试题
			for(int	j=0;j<TypeList.size();j++){
				ret++;
			}
		}	
		return ret;
	}
	/**
	 * <p>名称：getTotalScore</p>
	 * <p>说明：获取一个染色体中题目的总分数</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：double 返回类型</p>
	 * <p>@return</p>
	 */
	public	double	getTotalScore(){
		double	ret=0.0;
		//遍历每个题型
		for(int i=0;i<this.chromosom.size();i++){
			ArrayList<QuestionVo> TypeList	=	this.chromosom.get(i);
			//遍历每个题型中的试题
			for(int	j=0;j<TypeList.size();j++){
				ret+=TypeList.get(j).getDefaultPoint();
			}
		}	
		return ret;
	}
	/**
	 * <p>名称：calculateDifficulty</p>
	 * <p>说明：计算出该染色体期望得到的分数 即最后的难度系数*试卷卷面总分</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：double 返回类型</p>
	 * <p>@return</p>
	 */
	public	double	calculateDifficulty(){
		double	ret	=	0;
		//遍历每个题型
		for(int i=0;i<this.chromosom.size();i++){
			ArrayList<QuestionVo> TypeList	=	this.chromosom.get(i);
			//遍历每个题型中的试题
			for(int	j=0;j<TypeList.size();j++){
				//存储每道试题
				QuestionVo ques	=	TypeList.get(j);
				//获取试题难度
				double	diffTemp	=	ques.getQuesDifficulty();
				double	scoreTemp	=	ques.getDefaultPoint();
				//计算一道题的期望得分并且叠加		（1-难度系数）*试题分数
				ret+=Arith.mul(1-diffTemp, scoreTemp);
			}
		}
		return ret;
	}
	
	public	double	calculateFitness(){
		double	f=Math.abs(this.calculateDifficulty()-this.targetTotalScore*this.targetDifficulty)*Property.PROPORTION_DIFFICULTY
				+Math.abs(this.getTotalScore()-this.targetTotalScore)*Property.PROPORTION_SCORE;	
		//logger.debug("f="+f);
		double	fitness	=	Math.exp(-f*0.03);
		//logger.debug("fitness="+fitness);
		return fitness;
		
	}
	/**
	 * <p>名称：getQuesByTypeAndKnowledgePoint</p>
	 * <p>说明：通过试题类型与试题包含的知识点列出满足条件的试题列表</p>
	 * <p>参数：@param quesType			试题类型
	 * <p>参数：@param quesKnowledgePoint		试题包含知识点id
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：ArrayList<QuestionVo> 返回试题列表</p>
	 * <p>@param quesType
	 * <p>@param quesKnowledgePoint
	 * <p>@return</p>
	 */
	public ArrayList<QuestionVo> getQuesByTypeAndKnowledgePoint(String quesType,String quesKnowledgePoint){
		
		ArrayList<QuestionVo>	ret=new ArrayList<QuestionVo>();
		for(int i=0;i<this.quesList.size();i++){
			String quesT =	this.quesList.get(i).getQuesType();
			String quesKP=this.quesList.get(i).getKnowledgePointId();
			if(quesType.equalsIgnoreCase(quesT)&&quesKnowledgePoint.equalsIgnoreCase(quesKP)){
				ret.add(this.quesList.get(i));
			}
		}
		return ret;
	}

	public ArrayList<PaperConfig> getPaperConfigList() {
		return paperConfigList;
	}
	public void setPaperConfigList(ArrayList<PaperConfig> paperConfigList) {
		this.paperConfigList = paperConfigList;
	}
	public double getTargetDifficulty() {
		return targetDifficulty;
	}
	public void setTargetDifficulty(double targetDifficulty) {
		this.targetDifficulty = targetDifficulty;
	}
	public double getTargetTotalScore() {
		return targetTotalScore;
	}
	public void setTargetTotalScore(double targetTotalScore) {
		this.targetTotalScore = targetTotalScore;
	}
	public ArrayList<QuestionVo> getQuesList() {
		return quesList;
	}
	public void setQuesList(ArrayList<QuestionVo> quesList) {
		this.quesList = quesList;
	}
	public ArrayList<ArrayList<QuestionVo>> getChromosom() {
		return chromosom;
	}
	public void setChromosom(ArrayList<ArrayList<QuestionVo>> chromosom) {
		this.chromosom = chromosom;
	}
	public Object deepClone() {
		ObjectInputStream oi = null;
		Object obj = null;
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(this);
			// 从流里读出来
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			oi = new ObjectInputStream(bi);
			obj = oi.readObject();
		} catch (Exception e) {
			//logger.error("克隆出错：", e);
			e.printStackTrace();
		}

		return (obj);
	}
	
}


