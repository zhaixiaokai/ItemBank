
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	Main.java
 * | 包名：net.ib.util.data
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-27 上午10:47:06
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-27 上午10:47:06
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.data;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;


  /**
 * <p>类名：net.ib.util.data.Main </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
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
			xzQconfig.add(new QuesConfig("知识点"+i, "", i/10, 1));
			tkQconfig.add(new QuesConfig("知识点"+i, "", i/10, 1));
			pdQconfig.add(new QuesConfig("知识点"+i, "", i/10, 1));
			jdQconfig.add(new QuesConfig("知识点"+i, "", i/10, 1));
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
				question	=	new	QuestionVo("知识点"+i,UUID.randomUUID().toString(),"xuanze",temp,"知识点"+i+"---试题类型："+"xuanze---"+"第"+s+"道题",Utility.randomIntNumber1(6));
				quesList.add(question);
				question	=	new	QuestionVo("知识点"+i,UUID.randomUUID().toString(),"panduan", temp,"知识点"+i+"---试题类型："+"panduan---"+"第"+s+"道题",Utility.randomIntNumber1(6));
				quesList.add(question);
				question	=	new	QuestionVo("知识点"+i,UUID.randomUUID().toString(),"tiankong",temp,"知识点"+i+"---试题类型："+"tiankong---"+"第"+s+"道题",Utility.randomIntNumber1(6));
				quesList.add(question);
				question	=	new	QuestionVo("知识点"+i,UUID.randomUUID().toString(),"jianda",temp,"知识点"+i+"---试题类型："+"jianda---"+"第"+s+"道题",Utility.randomIntNumber1(6));
				quesList.add(question);
			}	
		}
		long start = System.currentTimeMillis();
		//定义初始化种群数量
		int	popNum	=	Property.POP_NUM;
		//定义初始化种群
		Chromosome[]	population	=	new	Chromosome[popNum];
		for(int	i=0;i<popNum;i++){
			population[i]=new Chromosome(paperConfigList, quesList,0.7,100);
			population[i].ChromosomeInit();
		}
		//定义最终种群数目
		int	finalPopNum	=	Property.FINAL_POP_NUM;
		//定义最终种群
		Chromosome[]	finalPopulation=new Chromosome[finalPopNum];
		//种群染色体适应度数组，用来存储每个染色体的适应度
		ArrayList<Double>	popFitnessList	=	new	ArrayList<Double>();
		//计算种群中每个染色体的适应度并且存入list
		for(int i=0;i<popNum;i++){
			popFitnessList.add(population[i].calculateFitness());
		}
		//根据每个染色体的适应度使用轮盘算法选出比较优质的种群进行下面的处理
		for(int	i=0;i<finalPopNum;i++){
			int	point	=	Utility.selection(popFitnessList);
			finalPopulation[i]	=	population[point];
		}
		

		
		int	generation=0;
		Chromosome	bestChrom=new	Chromosome();
		double	bestFitness	=	0;
		//对代数循环，到达最大代数结束
		for(;generation<Property.MAX_GENERATION;generation++){
			//交叉
			Utility.crossover(finalPopulation);
			//变异
			Utility.Mutation(finalPopulation);
			
			for(int	i=0;i<finalPopulation.length;i++){
				if(bestFitness<finalPopulation[i].calculateFitness()){
					bestFitness=finalPopulation[i].calculateFitness();
					System.out.println("获取到更大的适应度："+bestFitness);
					bestChrom=	(Chromosome) finalPopulation[i].deepClone();
					System.out.println("试题个数："+bestChrom.getQuesNum());
					System.out.println("期望得分："+bestChrom.calculateDifficulty());
					System.out.println("适应度："+bestChrom.calculateFitness());
					System.out.println("试题总分："+bestChrom.getTotalScore());
					bestChrom.getChromosom();
					
				}
			}
		}
		
		System.out.println("试题个数："+bestChrom.getQuesNum());
		System.out.println("试题难度："+bestChrom.calculateDifficulty());
		System.out.println("适应度："+bestChrom.calculateFitness());
		System.out.println("试题总分："+bestChrom.getTotalScore());
/*		
		Chromosome	c	=	new Chromosome(paperConfigList, quesList,0.7,100);
		
		for(int i=0;i<c.getChromosom().size();i++){
			ArrayList<QuestionVo>	listTemp=c.getChromosom().get(i);
			for(int j=0;j<listTemp.size();j++){

				//logger.debug(listTemp.get(j).getQuesName());
			}
		}*/
		long end = System.currentTimeMillis();
		System.err.println("运行时间："+(end-start));
	}
}
