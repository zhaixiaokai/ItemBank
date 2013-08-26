
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	AutoPaper.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-29 上午10:05:46
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-29 上午10:05:46
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
 * <p>类名：net.ib.util.service.impl.AutoPaper </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class AutoPaper {
	private static Logger logger = Logger.getLogger(AutoPaper.class);
	public	Chromosome	getBestChromosome(ArrayList<PaperConfig> paperConfigList,ArrayList<QuestionVo> quesList,double	targetDifficulty,double targetTotalScore){
		long start = System.currentTimeMillis();
		//定义初始化种群数量
		int	popNum	=	Property.POP_NUM;
		//定义初始化种群
		Chromosome[]	population	=	new	Chromosome[popNum];
		for(int	i=0;i<popNum;i++){
			population[i]=new Chromosome(paperConfigList, quesList,0.7,100);
			String	initResult	=	population[i].ChromosomeInit();
			if("initFailed".equals(initResult)){
				return null;
			}
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
					logger.info("获取到更大的适应度："+bestFitness);
					bestChrom=	(Chromosome) finalPopulation[i].deepClone();
					logger.info("试题个数："+bestChrom.getQuesNum());
					logger.info("试题难度："+bestChrom.calculateDifficulty());
					logger.info("适应度："+bestChrom.calculateFitness());
					logger.info("试题总分："+bestChrom.getTotalScore());
					bestChrom.getChromosom();
					
				}
			}
		}
		return bestChrom;
		
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

	
	
}
