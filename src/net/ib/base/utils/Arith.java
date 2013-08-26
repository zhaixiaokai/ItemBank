package net.ib.base.utils;

import java.math.BigDecimal;

public class Arith {
	private static final int DEF_DIV_SCALE = 2;
	/**
	 * 
	 * <p>���ƣ�add</p>
	 * <p>˵���������������</p>
	 * <p>������@param v1
	 * <p>������@param v2
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��double ��������</p>
	 */
	public static double add(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	/**
	 * 
	 * <p>���ƣ�sub</p>
	 * <p>˵�����ṩ��ȷ�ļ�������</p>
	 * <p>������@param v1
	 * <p>������@param v2
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��double ��������</p>
	 */
	public static double sub(double v1 , double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	/**
	 * 
	 * <p>���ƣ�mul</p>
	 * <p>˵�����ṩ��ȷ�ĳ˷�����</p>
	 * <p>������@param v1
	 * <p>������@param v2
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��double ��������</p>
	 */
	public static double mul(double v1 , double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	/**
	 * 
	 * <p>���ƣ�div</p>
	 * <p>˵�����ṩ����ԣ���ȷ�ĳ������㣬�����������������ʱ����ȷ��С�����Ժ�10λ���Ժ��������������</p>
	 * <p>������@param v1
	 * <p>������@param v2
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��double ��������</p>
	 */
	public static double div(double v1,double v2){
		return div(v1,v2,DEF_DIV_SCALE);
	}
	/**
	 * 
	 * <p>���ƣ�div</p>
	 * <p>˵�����ṩ����ԣ���ȷ�ĳ������㣬�����������������ʱ����sacle����ָ�����ȣ��Ժ��������������</p>
	 * <p>������@param v1
	 * <p>������@param v2
	 * <p>������@param scale
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��double ��������</p>
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
	 * <p>���ƣ�round</p>
	 * <p>˵�����ṩ��ȷ��С��λ�������봦��</p>
	 * <p>������@param v		��Ҫ�������������
	 * <p>������@param scale	С���������λ
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��double ��������</p>
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
