
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	Utility.java
 * | 包名：net.ib.util.data
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-19 下午4:40:47
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-19 下午4:40:47
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

import net.ib.base.utils.Arith;


  /**
 * <p>类名：net.ib.util.data.Utility </p>
 * <p>描述：定义随机数计算方法</p>
 * <p></p>
 */
public class Utility {
	private static Logger logger = Logger.getLogger(Utility.class);
	/**
	 * 
	 * <p>名称：randomIntNumber1</p>
	 * <p>说明：生成从[from, to] 变量之间的随机整数</p>
	 * <p>参数：@param from
	 * <p>参数：@param to
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：int 返回类型</p>
	 */
	public static int randomIntNumber1(int from, int to) {
		float a = from + (to - from) * (new Random().nextFloat());
		int b = (int) a;
		return ((a - b) > 0.5 ? 1 : 0) + b;
	}
	/**
	 * 
	 * <p>名称：randomIntNumber1</p>
	 * <p>说明：生成从[0,end)的随机整数</p>
	 * <p>参数：@param end
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：int 返回类型</p>
	 * <p>@param end
	 * <p>@return</p>
	 */
	public static int randomIntNumber1(int end) {
		return new Random().nextInt(end);
	}
	/**
	 * 
	 * <p>名称：randomFloatNumber</p>
	 * <p>说明：生成0-1的随机数。</p>
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：float 返回类型</p>
	 * <p>@return</p>
	 */
	public static float randomFloatNumber() {
		return new Random().nextFloat();
	}
	/**
	 * 
	 * <p>名称：length</p>
	 * <p>说明：求一个Integer类型 的长度</p>
	 * <p>参数：@param num
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：int 返回类型</p>
	 * <p>@param num
	 * <p>@return</p>
	 */
	public static int length(int num) {
		return Integer.toString(num).length();
	}
	/**
	 * 
	 * <p>名称：findKey</p>
	 * <p>说明：查找一个list中是否有randomNumber这个整数</p>
	 * <p>参数：@param ArrayList
	 * <p>参数：@param randomNumber
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：boolean 返回类型</p>
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
	 * <p>名称：getDifferentNumber</p>
	 * <p>说明：得到一个[from, to]区间并且与list中不重复的Integer数</p>
	 * <p>参数：@param ArrayList
	 * <p>参数：@param from
	 * <p>参数：@param to
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：int 返回类型</p>
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
	 * <p>名称：selection</p>
	 * <p>说明：轮盘赌博的方式选择染色体</p>
	 * <p>参数：@param popFitnessList
	 * <p>参数：@return 设定文件染色体所在的位置</p>
	 * <p>返回值：int 返回类型</p>
	 * <p>@param popFitnessList
	 * <p>@return</p>
	 */
	public static int selection(ArrayList<Double>	popFitnessList) {
		double r = new Random().nextDouble();
		//logger.debug("轮盘算法产生的随机数："+r);
		/**
		 * 染色体适应度的总和
		 */
		double	sum=0;
		/**
		 * 染色体适应度在sum中所占比例的和
		 */
		double	sumPer=0;
		int	temp=-1;
		//计算适应度总和
		for(int i=0;i<popFitnessList.size();i++){
			sum=Arith.add(sum, popFitnessList.get(i).doubleValue());
		}
		//logger.debug("总适应度和为："+sum);
		//将适应度转换为适应度在sum中所占的比例
		for(int	i=0;i<popFitnessList.size();i++){
			sumPer	=	Arith.add(sumPer, Arith.div(popFitnessList.get(i).doubleValue(), sum));
			//sum > r;轮盘赌的PI总合大于随机数，选择第I个数
			if(new BigDecimal(Double.toString(sumPer)).compareTo(new BigDecimal(
					Double.toString(r))) == 1){
				//logger.debug("跳出条件："+sumPer);
				temp	=	i;
				break;
			}
		}
		if (temp == -1) {
			temp	=	Property.POP_NUM-1;
		}
		//logger.debug("被选中基因在种群中的位置："+temp);
		return temp;
	}
	/**
	 * 
	 * <p>名称：crossover</p>
	 * <p>说明：基因交叉算法</p>
	 * <p>参数：@param finalPopulation
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：Chromosome[] 返回类型		交叉后的新种群</p>
	 * <p>@param finalPopulation
	 * <p>@return</p>
	 */
	public static Chromosome[] crossover(Chromosome[] finalPopulation){
		if(finalPopulation!=null){
			int length = finalPopulation.length;
			
			Chromosome[]	ret	=	new	Chromosome[length];
			//int size = finalPopulation[0].getChromosome().size();
			for (int i = 0; i < length; i++) {
				//logger.debug("***************************************下面是另一条染色体交叉过程************************************************");
				float f = randomFloatNumber();
				if (f < Property.PROBABILITY_CHANGE) {
					//交叉的第一条基因
					Chromosome chrom1 = finalPopulation[i];
					//随机产生交叉的第二条基因所在的位置
					int random = randomIntNumber1(length);
					//交叉的第二条基因
					Chromosome chrom2 = finalPopulation[random];
					//对每一种题型进行交叉
					for(int x=0;x<finalPopulation[i].getChromosom().size();x++){
						// 随机产生交叉的位置
						int crossoverLocation = randomIntNumber1(1, finalPopulation[i].getChromosom().get(x).size() - 1);
						//从交叉点往后的分段基因互换
						for(int	pos	=	crossoverLocation;pos<finalPopulation[i].getChromosom().get(x).size();pos++){
/*							//logger.debug("交叉前");
							//logger.debug(chrom1.getChromosom().get(x).get(pos).getQuesName());*/
							
							QuestionVo	temp	=	chrom2.getChromosom().get(x).get(pos);
							chrom1.getChromosom().get(x).set(pos, temp);
/*							//logger.debug("交叉后");
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
	 * <p>名称：Mutation</p>
	 * <p>说明：变异算子</p>
	 * <p>参数：@param finalPopulation 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param finalPopulation</p>
	 */
	public	static void	Mutation(Chromosome[] finalPopulation){
		int	length	=	finalPopulation.length;
		if(finalPopulation!=null){
			for(int	i=0;i<length;i++){
				//logger.debug("***************************************下面是另一条染色体变异过程************************************************");
				//获取每一条染色体
				Chromosome	ch	=	finalPopulation[i];
				//对每种题型循环
				for(int	typeCount=0;typeCount<ch.getChromosom().size();typeCount++){
					ArrayList<QuestionVo>		ques=	ch.getChromosom().get(typeCount);
					//对每道题循环
					for(int j=0;j<ques.size();j++){
						float f = randomFloatNumber();
						//满足变异概率
						if (f < Property.PROBABILITY_VARIATION) {
							String	quesType	=	ques.get(j).getQuesType();
							String	quesKnowledgePoint	=	ques.get(j).getKnowledgePointId();
							//根据知识点id和试题类型获取符合条件的试题列表
							ArrayList<QuestionVo>	listForTypeAndKP	=	ch.getQuesByTypeAndKnowledgePoint(quesType, quesKnowledgePoint);
							//随机产生变异试题在listForTypeAndKP中的位置
							int	randomQuesPos=0;
							if(listForTypeAndKP.size()-1>0){
								randomQuesPos	=	randomIntNumber1(listForTypeAndKP.size()-1);
							}
							//logger.debug("发生变异");
							//logger.debug("原来的试题："+finalPopulation[i].getChromosom().get(typeCount).get(j).getQuesName());
							finalPopulation[i].getChromosom().get(typeCount).set(j, listForTypeAndKP.get(randomQuesPos));
							//logger.debug("变异后的试题："+finalPopulation[i].getChromosom().get(typeCount).get(j).getQuesName());
						}
					}
				}
			}
		}
		
	}
	/**
	 * 
	 * <p>名称：DeEmphasis</p>
	 * <p>说明：去除每条染色体中重复的试题，用另一个替换</p>
	 * <p>参数：@param finalPopulation 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p>@param finalPopulation</p>
	 */
	public	static	void	DeEmphasis(Chromosome[] finalPopulation){
		int	length	=	finalPopulation.length;
		for(int i=0;i<length;i++){
			Chromosome	ch	=	finalPopulation[i];
			//每种题型循环
			for(int	TCount=0;TCount<ch.getChromosom().size();TCount++){
				int	start=0;
				int	end=0;
				ArrayList<QuestionVo>	quesList	=	ch.getChromosom().get(TCount);
				//每种题型中的试题循环
				for(;start<quesList.size();start++){
					for(end=start+1;end<quesList.size();end++){
						//如果知识点id相同则比较试题id
						if(quesList.get(start).getKnowledgePointId().equals(quesList.get(end).getKnowledgePointId())){
							//试题知识点id
							String	quesKPID	=	quesList.get(start).getKnowledgePointId();
							String	quesType	=	quesList.get(start).getQuesType();
							//如果试题id也相同
							if(quesList.get(start).getQuesId().equals(quesList.get(end).getQuesId())){
								//对该试题进行替换
								//获取符合试题类型和试题知识点的所有试题
								ArrayList<QuestionVo>	questions	=	ch.getQuesByTypeAndKnowledgePoint(quesType, quesKPID);
								while(true){
									//获取跟当前不一样的试题
								}
								//Utility.getDifferentNumber(ArrayList, 0, questions.size());
								
							}
						}
						//否则跳出本循环
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
