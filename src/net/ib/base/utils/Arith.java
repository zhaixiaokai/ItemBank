package net.ib.base.utils;

import java.math.BigDecimal;

public class Arith {
	private static final int DEF_DIV_SCALE = 2;
	/**
	 * 
	 * <p>名称：add</p>
	 * <p>说明：两个参数相加</p>
	 * <p>参数：@param v1
	 * <p>参数：@param v2
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：double 返回类型</p>
	 */
	public static double add(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	/**
	 * 
	 * <p>名称：sub</p>
	 * <p>说明：提供精确的减法运算</p>
	 * <p>参数：@param v1
	 * <p>参数：@param v2
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：double 返回类型</p>
	 */
	public static double sub(double v1 , double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	/**
	 * 
	 * <p>名称：mul</p>
	 * <p>说明：提供精确的乘法运算</p>
	 * <p>参数：@param v1
	 * <p>参数：@param v2
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：double 返回类型</p>
	 */
	public static double mul(double v1 , double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	/**
	 * 
	 * <p>名称：div</p>
	 * <p>说明：提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入</p>
	 * <p>参数：@param v1
	 * <p>参数：@param v2
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：double 返回类型</p>
	 */
	public static double div(double v1,double v2){
		return div(v1,v2,DEF_DIV_SCALE);
	}
	/**
	 * 
	 * <p>名称：div</p>
	 * <p>说明：提供（相对）精确的除法运算，当发生除不尽的情况时，由sacle参数指定精度，以后的数字四舍五入</p>
	 * <p>参数：@param v1
	 * <p>参数：@param v2
	 * <p>参数：@param scale
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：double 返回类型</p>
	 */
	public static double div(double v1,double v2,int scale){
		if (scale < 0) {
			throw new IllegalArgumentException("The scal must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 
	 * <p>名称：round</p>
	 * <p>说明：提供精确的小数位四舍五入处理</p>
	 * <p>参数：@param v		需要四舍五入的数字
	 * <p>参数：@param scale	小数点后保留几位
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：double 返回类型</p>
	 */
	public static double round(double v , int scale){
		if (scale < 0) {
			throw new IllegalArgumentException("The scal must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static void main(String[] args) {
		System.out.println(div(35,100));
		
		System.out.println((double)35/100);
	}
}
