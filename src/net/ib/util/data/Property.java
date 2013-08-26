
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	Property.java
 * | 包名：net.ib.util.data
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-25 下午2:11:58
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-25 下午2:11:58
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.data;


  /**
 * <p>类名：net.ib.util.data.Property </p>
 * <p>描述：组卷算法的配置参数</p>
 * <p></p>
 */
public class Property {
	/**
	 * 初始种群数量
	 */
	public	static final	int	POP_NUM=100;
	/**
	 * 最终种群数量
	 */
	public	static final	int	FINAL_POP_NUM=60;
	/**
	 * 计算适应度时分数适应度所占比重
	 */
	public static final float PROPORTION_SCORE = 0.3f;
	/**
	 * 计算适应度时难度适应度所占比重
	 */
	public static final float PROPORTION_DIFFICULTY=  0.7f;
	
	/**
	 * 基因交叉概率
	 */
	public static final float	PROBABILITY_CHANGE = 0.75f;
	/**
	 * 基因突变概率
	 */
	public static final float PROBABILITY_VARIATION =  0.1f;
	/**
	 * 最大遗传代数
	 */
	public	static final int	MAX_GENERATION=1000;
}
