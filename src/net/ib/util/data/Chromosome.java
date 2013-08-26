
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	Chromosome.java
 * | ������net.ib.util.data
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-20 ����4:54:00
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-20 ����4:54:00
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
 * <p>������net.ib.util.data.Chromosome </p>
 * <p>������Ⱦɫ��</p>
 * <p></p>
 */
public class Chromosome implements	Serializable{
	//private static Logger logger = Logger.getLogger(Chromosome.class);
	/**
	 * �Ծ����ã������������ã�ѡ����ա�����жϣ�
	 * list�е�ÿ��Ԫ�ر�ʾһ�����͵�����
	 * ���� paperConfigList[0]��ʾѡ���������������Ϣ
	 */
	private ArrayList<PaperConfig> paperConfigList;
	/**
	 * �����б�
	 */
	private ArrayList<QuestionVo> quesList;
	/**
	 * Ⱦɫ�壬Ⱦɫ���а�������list��ÿ��list�д洢��ͬ���Ͷ�Ӧ�����⣨����
	 */
	private ArrayList<ArrayList<QuestionVo>> chromosom=new ArrayList<ArrayList<QuestionVo>>();

	
	/**
	 * Ŀ���Ծ���Ѷ�ϵ��
	 */
	double	targetDifficulty=0;
	/**
	 * Ŀ���Ծ���ܷ�
	 */
	double	targetTotalScore=0;
	
	public	Chromosome(){
		
	}
	/**
	 * �������Ĺ��캯��
	 * @param paperConfigList  	�Ծ�����
	 * @param quesList			�����б�
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
	 * <p>���ƣ�ChromosomeInit</p>
	 * <p>˵���������ʼ��</p>
	 * <p>������ �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p></p>
	 */
	public String ChromosomeInit(){
		 //paperConfig�������������͵��������� ����ѡ��������ã�����list�е�����Ϊѡ�����Ӧ֪ʶ���Ѷ���Ŀ��������
		PaperConfig paperConfig=null;
		//quesConfig:�����������ͣ���Ӧÿ��֪ʶ�����õ�������Ŀ���Ѷ�
		QuesConfig	quesConfig = null;
		//���Ծ�����ѭ��   ��ȡ���Ծ�����
		for(int i=0;i<paperConfigList.size();i++){
			paperConfig=paperConfigList.get(i);
			//configType:�����������ͣ�ѡ����գ�����жϣ�
			String configType=paperConfig.getQuesType();
			ArrayList<QuesConfig> quesConfigList=paperConfig.getQuesConfig();
			ArrayList<QuestionVo>	oneTypeQuesList=new	ArrayList<QuestionVo>();
			this.chromosom.add(oneTypeQuesList);
			//��ÿ���������͵�����ѭ��
			for(int j=0;j<quesConfigList.size();j++){
				quesConfig=quesConfigList.get(j);
				String knowledgePointId	=	quesConfig.getKnowledgePointId();
				int quesNum=quesConfig.getQuesNum();
				//��ȡ������֪ʶ����������͵���Ŀ�б�
				ArrayList<QuestionVo> quesList	=	this.getQuesByTypeAndKnowledgePoint(configType, knowledgePointId);
				//����������ⲻ�����������õ���Ŀ
				if(quesList.size()<quesNum){
					//logger.info("Ⱦɫ���ʼ��ʧ��!");
					//logger.info("�������ͣ�"+configType+"   ֪ʶ�㣺"+knowledgePointId+"�����ⲻ��");
					return "initFailed";
				}
				ArrayList<Integer> randList = new ArrayList<Integer>();
				//�����ʼ��Ⱦɫ��
				
				for(int o=0;o<quesNum;o++){
					//������������ұ�֤���ֲ��ظ�
					int	ran=Utility.getDifferentNumber(randList, 0, quesList.size()-1);
					randList.add(ran);
					//ΪȾɫ������Ӹû������⣩
					//chromosom.add(quesList.get(ran));
					this.chromosom.get(i).add(quesList.get(ran));
				}
			}
		}
		return null;
	}
	/**
	 * <p>���ƣ�getQuesNum</p>
	 * <p>˵������ȡһ��Ⱦɫ���л���(����)�ĸ���</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��int ��������</p>
	 * <p>@return</p>
	 */
	public	int	getQuesNum(){
		int	ret=0;
		//����ÿ������
		for(int i=0;i<this.chromosom.size();i++){
			ArrayList<QuestionVo> TypeList	=	this.chromosom.get(i);
			//����ÿ�������е�����
			for(int	j=0;j<TypeList.size();j++){
				ret++;
			}
		}	
		return ret;
	}
	/**
	 * <p>���ƣ�getTotalScore</p>
	 * <p>˵������ȡһ��Ⱦɫ������Ŀ���ܷ���</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��double ��������</p>
	 * <p>@return</p>
	 */
	public	double	getTotalScore(){
		double	ret=0.0;
		//����ÿ������
		for(int i=0;i<this.chromosom.size();i++){
			ArrayList<QuestionVo> TypeList	=	this.chromosom.get(i);
			//����ÿ�������е�����
			for(int	j=0;j<TypeList.size();j++){
				ret+=TypeList.get(j).getDefaultPoint();
			}
		}	
		return ret;
	}
	/**
	 * <p>���ƣ�calculateDifficulty</p>
	 * <p>˵�����������Ⱦɫ�������õ��ķ��� �������Ѷ�ϵ��*�Ծ�����ܷ�</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��double ��������</p>
	 * <p>@return</p>
	 */
	public	double	calculateDifficulty(){
		double	ret	=	0;
		//����ÿ������
		for(int i=0;i<this.chromosom.size();i++){
			ArrayList<QuestionVo> TypeList	=	this.chromosom.get(i);
			//����ÿ�������е�����
			for(int	j=0;j<TypeList.size();j++){
				//�洢ÿ������
				QuestionVo ques	=	TypeList.get(j);
				//��ȡ�����Ѷ�
				double	diffTemp	=	ques.getQuesDifficulty();
				double	scoreTemp	=	ques.getDefaultPoint();
				//����һ����������÷ֲ��ҵ���		��1-�Ѷ�ϵ����*�������
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
	 * <p>���ƣ�getQuesByTypeAndKnowledgePoint</p>
	 * <p>˵����ͨ���������������������֪ʶ���г����������������б�</p>
	 * <p>������@param quesType			��������
	 * <p>������@param quesKnowledgePoint		�������֪ʶ��id
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��ArrayList<QuestionVo> ���������б�</p>
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
			// �����������
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			oi = new ObjectInputStream(bi);
			obj = oi.readObject();
		} catch (Exception e) {
			//logger.error("��¡����", e);
			e.printStackTrace();
		}

		return (obj);
	}
	
}


