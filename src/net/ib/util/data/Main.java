
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	Main.java
 * | ������net.ib.util.data
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-27 ����10:47:06
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-27 ����10:47:06
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.data;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;


  /**
 * <p>������net.ib.util.data.Main </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class Main {
	public static void main(String [] args){
		ArrayList<PaperConfig> paperConfigList	=	new ArrayList<PaperConfig>();
		ArrayList<QuestionVo> quesList	=	new	ArrayList<QuestionVo>();
		
		ArrayList<QuesConfig> xzQconfig	=	new	ArrayList<QuesConfig>();
		ArrayList<QuesConfig> pdQconfig	=	new	ArrayList<QuesConfig>();
		ArrayList<QuesConfig> tkQconfig	=	new	ArrayList<QuesConfig>();
		ArrayList<QuesConfig> jdQconfig	=	new	ArrayList<QuesConfig>();
		for(int i=0;i<10;i++){
			xzQconfig.add(new QuesConfig("֪ʶ��"+i, "", i/10, 1));
			tkQconfig.add(new QuesConfig("֪ʶ��"+i, "", i/10, 1));
			pdQconfig.add(new QuesConfig("֪ʶ��"+i, "", i/10, 1));
			jdQconfig.add(new QuesConfig("֪ʶ��"+i, "", i/10, 1));
		}
		
		PaperConfig	xzConfig	=	new	PaperConfig("xuanze",xzQconfig);
		PaperConfig tkConfig	=	new	PaperConfig("tiankong", tkQconfig);
		PaperConfig	jdConfig	=	new	PaperConfig("jianda", jdQconfig);
		PaperConfig pdConfig	=	new	PaperConfig("panduan", pdQconfig);
		paperConfigList.add(xzConfig);
		paperConfigList.add(tkConfig);
		paperConfigList.add(jdConfig);
		paperConfigList.add(pdConfig);
		
		QuestionVo	question	=	null;
		for(int s=0;s<10;s++){
			for(int i=0;i<10;i++){
				double	temp	=	(double)i/10;
				question	=	new	QuestionVo("֪ʶ��"+i,UUID.randomUUID().toString(),"xuanze",temp,"֪ʶ��"+i+"---�������ͣ�"+"xuanze---"+"��"+s+"����",Utility.randomIntNumber1(6));
				quesList.add(question);
				question	=	new	QuestionVo("֪ʶ��"+i,UUID.randomUUID().toString(),"panduan", temp,"֪ʶ��"+i+"---�������ͣ�"+"panduan---"+"��"+s+"����",Utility.randomIntNumber1(6));
				quesList.add(question);
				question	=	new	QuestionVo("֪ʶ��"+i,UUID.randomUUID().toString(),"tiankong",temp,"֪ʶ��"+i+"---�������ͣ�"+"tiankong---"+"��"+s+"����",Utility.randomIntNumber1(6));
				quesList.add(question);
				question	=	new	QuestionVo("֪ʶ��"+i,UUID.randomUUID().toString(),"jianda",temp,"֪ʶ��"+i+"---�������ͣ�"+"jianda---"+"��"+s+"����",Utility.randomIntNumber1(6));
				quesList.add(question);
			}	
		}
		long start = System.currentTimeMillis();
		//�����ʼ����Ⱥ����
		int	popNum	=	Property.POP_NUM;
		//�����ʼ����Ⱥ
		Chromosome[]	population	=	new	Chromosome[popNum];
		for(int	i=0;i<popNum;i++){
			population[i]=new Chromosome(paperConfigList, quesList,0.7,100);
			population[i].ChromosomeInit();
		}
		//����������Ⱥ��Ŀ
		int	finalPopNum	=	Property.FINAL_POP_NUM;
		//����������Ⱥ
		Chromosome[]	finalPopulation=new Chromosome[finalPopNum];
		//��ȺȾɫ����Ӧ�����飬�����洢ÿ��Ⱦɫ�����Ӧ��
		ArrayList<Double>	popFitnessList	=	new	ArrayList<Double>();
		//������Ⱥ��ÿ��Ⱦɫ�����Ӧ�Ȳ��Ҵ���list
		for(int i=0;i<popNum;i++){
			popFitnessList.add(population[i].calculateFitness());
		}
		//����ÿ��Ⱦɫ�����Ӧ��ʹ�������㷨ѡ���Ƚ����ʵ���Ⱥ��������Ĵ���
		for(int	i=0;i<finalPopNum;i++){
			int	point	=	Utility.selection(popFitnessList);
			finalPopulation[i]	=	population[point];
		}
		

		
		int	generation=0;
		Chromosome	bestChrom=new	Chromosome();
		double	bestFitness	=	0;
		//�Դ���ѭ������������������
		for(;generation<Property.MAX_GENERATION;generation++){
			//����
			Utility.crossover(finalPopulation);
			//����
			Utility.Mutation(finalPopulation);
			
			for(int	i=0;i<finalPopulation.length;i++){
				if(bestFitness<finalPopulation[i].calculateFitness()){
					bestFitness=finalPopulation[i].calculateFitness();
					System.out.println("��ȡ���������Ӧ�ȣ�"+bestFitness);
					bestChrom=	(Chromosome) finalPopulation[i].deepClone();
					System.out.println("���������"+bestChrom.getQuesNum());
					System.out.println("�����÷֣�"+bestChrom.calculateDifficulty());
					System.out.println("��Ӧ�ȣ�"+bestChrom.calculateFitness());
					System.out.println("�����ܷ֣�"+bestChrom.getTotalScore());
					bestChrom.getChromosom();
					
				}
			}
		}
		
		System.out.println("���������"+bestChrom.getQuesNum());
		System.out.println("�����Ѷȣ�"+bestChrom.calculateDifficulty());
		System.out.println("��Ӧ�ȣ�"+bestChrom.calculateFitness());
		System.out.println("�����ܷ֣�"+bestChrom.getTotalScore());
/*		
		Chromosome	c	=	new Chromosome(paperConfigList, quesList,0.7,100);
		
		for(int i=0;i<c.getChromosom().size();i++){
			ArrayList<QuestionVo>	listTemp=c.getChromosom().get(i);
			for(int j=0;j<listTemp.size();j++){

				//logger.debug(listTemp.get(j).getQuesName());
			}
		}*/
		long end = System.currentTimeMillis();
		System.err.println("����ʱ�䣺"+(end-start));
	}
}
