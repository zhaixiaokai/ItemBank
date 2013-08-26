
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	Utility.java
 * | ������net.ib.util.data
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-19 ����4:40:47
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-19 ����4:40:47
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

import net.ib.base.utils.Arith;


  /**
 * <p>������net.ib.util.data.Utility </p>
 * <p>������������������㷽��</p>
 * <p></p>
 */
public class Utility {
	private static Logger logger = Logger.getLogger(Utility.class);
	/**
	 * 
	 * <p>���ƣ�randomIntNumber1</p>
	 * <p>˵�������ɴ�[from, to] ����֮����������</p>
	 * <p>������@param from
	 * <p>������@param to
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��int ��������</p>
	 */
	public static int randomIntNumber1(int from, int to) {
		float a = from + (to - from) * (new Random().nextFloat());
		int b = (int) a;
		return ((a - b) > 0.5 ? 1 : 0) + b;
	}
	/**
	 * 
	 * <p>���ƣ�randomIntNumber1</p>
	 * <p>˵�������ɴ�[0,end)���������</p>
	 * <p>������@param end
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��int ��������</p>
	 * <p>@param end
	 * <p>@return</p>
	 */
	public static int randomIntNumber1(int end) {
		return new Random().nextInt(end);
	}
	/**
	 * 
	 * <p>���ƣ�randomFloatNumber</p>
	 * <p>˵��������0-1���������</p>
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��float ��������</p>
	 * <p>@return</p>
	 */
	public static float randomFloatNumber() {
		return new Random().nextFloat();
	}
	/**
	 * 
	 * <p>���ƣ�length</p>
	 * <p>˵������һ��Integer���� �ĳ���</p>
	 * <p>������@param num
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��int ��������</p>
	 * <p>@param num
	 * <p>@return</p>
	 */
	public static int length(int num) {
		return Integer.toString(num).length();
	}
	/**
	 * 
	 * <p>���ƣ�findKey</p>
	 * <p>˵��������һ��list���Ƿ���randomNumber�������</p>
	 * <p>������@param ArrayList
	 * <p>������@param randomNumber
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��boolean ��������</p>
	 * <p>@param ArrayList
	 * <p>@param randomNumber
	 * <p>@return</p>
	 */
	public static boolean findKey(ArrayList<Integer> ArrayList, int randomNumber) {
		int length = ArrayList.size();
		for (int i = 0; i < length; i++) {
			if (ArrayList.get(i) == randomNumber) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * <p>���ƣ�getDifferentNumber</p>
	 * <p>˵�����õ�һ��[from, to]���䲢����list�в��ظ���Integer��</p>
	 * <p>������@param ArrayList
	 * <p>������@param from
	 * <p>������@param to
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��int ��������</p>
	 */
	public static int getDifferentNumber(ArrayList<Integer> ArrayList, int from, int to) {
		int number = randomIntNumber1(from, to);
		while (true) {
			if (!findKey(ArrayList, number)) {
				return number;
			} else {
				number = randomIntNumber1(from, to);
			}
		}
	}
	
	/**
	 * <p>���ƣ�selection</p>
	 * <p>˵�������̶Ĳ��ķ�ʽѡ��Ⱦɫ��</p>
	 * <p>������@param popFitnessList
	 * <p>������@return �趨�ļ�Ⱦɫ�����ڵ�λ��</p>
	 * <p>����ֵ��int ��������</p>
	 * <p>@param popFitnessList
	 * <p>@return</p>
	 */
	public static int selection(ArrayList<Double>	popFitnessList) {
		double r = new Random().nextDouble();
		//logger.debug("�����㷨�������������"+r);
		/**
		 * Ⱦɫ����Ӧ�ȵ��ܺ�
		 */
		double	sum=0;
		/**
		 * Ⱦɫ����Ӧ����sum����ռ�����ĺ�
		 */
		double	sumPer=0;
		int	temp=-1;
		//������Ӧ���ܺ�
		for(int i=0;i<popFitnessList.size();i++){
			sum=Arith.add(sum, popFitnessList.get(i).doubleValue());
		}
		//logger.debug("����Ӧ�Ⱥ�Ϊ��"+sum);
		//����Ӧ��ת��Ϊ��Ӧ����sum����ռ�ı���
		for(int	i=0;i<popFitnessList.size();i++){
			sumPer	=	Arith.add(sumPer, Arith.div(popFitnessList.get(i).doubleValue(), sum));
			//sum > r;���̶ĵ�PI�ܺϴ����������ѡ���I����
			if(new BigDecimal(Double.toString(sumPer)).compareTo(new BigDecimal(
					Double.toString(r))) == 1){
				//logger.debug("����������"+sumPer);
				temp	=	i;
				break;
			}
		}
		if (temp == -1) {
			temp	=	Property.POP_NUM-1;
		}
		//logger.debug("��ѡ�л�������Ⱥ�е�λ�ã�"+temp);
		return temp;
	}
	/**
	 * 
	 * <p>���ƣ�crossover</p>
	 * <p>˵�������򽻲��㷨</p>
	 * <p>������@param finalPopulation
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��Chromosome[] ��������		����������Ⱥ</p>
	 * <p>@param finalPopulation
	 * <p>@return</p>
	 */
	public static Chromosome[] crossover(Chromosome[] finalPopulation){
		if(finalPopulation!=null){
			int length = finalPopulation.length;
			
			Chromosome[]	ret	=	new	Chromosome[length];
			//int size = finalPopulation[0].getChromosome().size();
			for (int i = 0; i < length; i++) {
				//logger.debug("***************************************��������һ��Ⱦɫ�彻�����************************************************");
				float f = randomFloatNumber();
				if (f < Property.PROBABILITY_CHANGE) {
					//����ĵ�һ������
					Chromosome chrom1 = finalPopulation[i];
					//�����������ĵڶ����������ڵ�λ��
					int random = randomIntNumber1(length);
					//����ĵڶ�������
					Chromosome chrom2 = finalPopulation[random];
					//��ÿһ�����ͽ��н���
					for(int x=0;x<finalPopulation[i].getChromosom().size();x++){
						// ������������λ��
						int crossoverLocation = randomIntNumber1(1, finalPopulation[i].getChromosom().get(x).size() - 1);
						//�ӽ��������ķֶλ��򻥻�
						for(int	pos	=	crossoverLocation;pos<finalPopulation[i].getChromosom().get(x).size();pos++){
/*							//logger.debug("����ǰ");
							//logger.debug(chrom1.getChromosom().get(x).get(pos).getQuesName());*/
							
							QuestionVo	temp	=	chrom2.getChromosom().get(x).get(pos);
							chrom1.getChromosom().get(x).set(pos, temp);
/*							//logger.debug("�����");
							//logger.debug(chrom1.getChromosom().get(x).get(pos).getQuesName());*/
						}
					}
					ret[i]	=	chrom1;
				}
			}
			print(ret);
			return	ret;
		}
		return finalPopulation;
	}
	
	
	/**
	 * 
	 * <p>���ƣ�Mutation</p>
	 * <p>˵������������</p>
	 * <p>������@param finalPopulation �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param finalPopulation</p>
	 */
	public	static void	Mutation(Chromosome[] finalPopulation){
		int	length	=	finalPopulation.length;
		if(finalPopulation!=null){
			for(int	i=0;i<length;i++){
				//logger.debug("***************************************��������һ��Ⱦɫ��������************************************************");
				//��ȡÿһ��Ⱦɫ��
				Chromosome	ch	=	finalPopulation[i];
				//��ÿ������ѭ��
				for(int	typeCount=0;typeCount<ch.getChromosom().size();typeCount++){
					ArrayList<QuestionVo>		ques=	ch.getChromosom().get(typeCount);
					//��ÿ����ѭ��
					for(int j=0;j<ques.size();j++){
						float f = randomFloatNumber();
						//����������
						if (f < Property.PROBABILITY_VARIATION) {
							String	quesType	=	ques.get(j).getQuesType();
							String	quesKnowledgePoint	=	ques.get(j).getKnowledgePointId();
							//����֪ʶ��id���������ͻ�ȡ���������������б�
							ArrayList<QuestionVo>	listForTypeAndKP	=	ch.getQuesByTypeAndKnowledgePoint(quesType, quesKnowledgePoint);
							//�����������������listForTypeAndKP�е�λ��
							int	randomQuesPos=0;
							if(listForTypeAndKP.size()-1>0){
								randomQuesPos	=	randomIntNumber1(listForTypeAndKP.size()-1);
							}
							//logger.debug("��������");
							//logger.debug("ԭ�������⣺"+finalPopulation[i].getChromosom().get(typeCount).get(j).getQuesName());
							finalPopulation[i].getChromosom().get(typeCount).set(j, listForTypeAndKP.get(randomQuesPos));
							//logger.debug("���������⣺"+finalPopulation[i].getChromosom().get(typeCount).get(j).getQuesName());
						}
					}
				}
			}
		}
		
	}
	/**
	 * 
	 * <p>���ƣ�DeEmphasis</p>
	 * <p>˵����ȥ��ÿ��Ⱦɫ�����ظ������⣬����һ���滻</p>
	 * <p>������@param finalPopulation �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p>@param finalPopulation</p>
	 */
	public	static	void	DeEmphasis(Chromosome[] finalPopulation){
		int	length	=	finalPopulation.length;
		for(int i=0;i<length;i++){
			Chromosome	ch	=	finalPopulation[i];
			//ÿ������ѭ��
			for(int	TCount=0;TCount<ch.getChromosom().size();TCount++){
				int	start=0;
				int	end=0;
				ArrayList<QuestionVo>	quesList	=	ch.getChromosom().get(TCount);
				//ÿ�������е�����ѭ��
				for(;start<quesList.size();start++){
					for(end=start+1;end<quesList.size();end++){
						//���֪ʶ��id��ͬ��Ƚ�����id
						if(quesList.get(start).getKnowledgePointId().equals(quesList.get(end).getKnowledgePointId())){
							//����֪ʶ��id
							String	quesKPID	=	quesList.get(start).getKnowledgePointId();
							String	quesType	=	quesList.get(start).getQuesType();
							//�������idҲ��ͬ
							if(quesList.get(start).getQuesId().equals(quesList.get(end).getQuesId())){
								//�Ը���������滻
								//��ȡ�����������ͺ�����֪ʶ�����������
								ArrayList<QuestionVo>	questions	=	ch.getQuesByTypeAndKnowledgePoint(quesType, quesKPID);
								while(true){
									//��ȡ����ǰ��һ��������
								}
								//Utility.getDifferentNumber(ArrayList, 0, questions.size());
								
							}
						}
						//����������ѭ��
						else{
							break;
						}
					}
				}
			}
			
			
		}
			
	}
	private static	void	print(Chromosome[] finalPopulation){
		for(int	i=0;i<finalPopulation.length;i++){
			
		}
	}
}
