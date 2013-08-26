
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	AutoPaper.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-29 ����10:05:46
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-29 ����10:05:46
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.ib.util.data.Chromosome;
import net.ib.util.data.PaperConfig;
import net.ib.util.data.Property;
import net.ib.util.data.QuestionVo;
import net.ib.util.data.Utility;


  /**
 * <p>������net.ib.util.service.impl.AutoPaper </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class AutoPaper {
	private static Logger logger = Logger.getLogger(AutoPaper.class);
	public	Chromosome	getBestChromosome(ArrayList<PaperConfig> paperConfigList,ArrayList<QuestionVo> quesList,double	targetDifficulty,double targetTotalScore){
		long start = System.currentTimeMillis();
		//�����ʼ����Ⱥ����
		int	popNum	=	Property.POP_NUM;
		//�����ʼ����Ⱥ
		Chromosome[]	population	=	new	Chromosome[popNum];
		for(int	i=0;i<popNum;i++){
			population[i]=new Chromosome(paperConfigList, quesList,0.7,100);
			String	initResult	=	population[i].ChromosomeInit();
			if("initFailed".equals(initResult)){
				return null;
			}
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
					logger.info("��ȡ���������Ӧ�ȣ�"+bestFitness);
					bestChrom=	(Chromosome) finalPopulation[i].deepClone();
					logger.info("���������"+bestChrom.getQuesNum());
					logger.info("�����Ѷȣ�"+bestChrom.calculateDifficulty());
					logger.info("��Ӧ�ȣ�"+bestChrom.calculateFitness());
					logger.info("�����ܷ֣�"+bestChrom.getTotalScore());
					bestChrom.getChromosom();
					
				}
			}
		}
		return bestChrom;
		
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

	
	
}
