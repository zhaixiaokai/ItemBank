
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	QuesConfig.java
 * | 包名：net.ib.util.data
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-20 下午5:07:37
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-20 下午5:07:37
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


  /**
 * <p>类名：net.ib.util.data.QuesConfig </p>
 * <p>描述：每一种试题类型对应的试题配置信息</p>
 * <p></p>
 */
public class QuesConfig  implements	Serializable{
	/**
	 * 对应试题类型的试题数目
	 */
	private int quesNum=0;
	/**
	 * 试题对应的知识点
	 */
	private String knowledgePointId=null;
	
	/**
	 * 字符串类型的试题难度
	 */
	private	String strQuesDifficulty=null;
	/**
	 * 数字类型的试题难度
	 */
	private float floatQuesDifficulty=0.0f;
	
	
	public QuesConfig(String knowledgePointId,String strQuesDifficulty,float floatQuesDifficulty,int quesNum){
		this.knowledgePointId=knowledgePointId;
		this.strQuesDifficulty=strQuesDifficulty;
		this.floatQuesDifficulty=floatQuesDifficulty;
		this.quesNum=quesNum;
	}
	
	
	public float getFloatQuesDifficulty() {
		return floatQuesDifficulty;
	}
	public void setFloatQuesDifficulty(float floatQuesDifficulty) {
		this.floatQuesDifficulty = floatQuesDifficulty;
	}
	public int getQuesNum() {
		return quesNum;
	}
	public void setQuesNum(int quesNum) {
		this.quesNum = quesNum;
	}
	public String getKnowledgePointId() {
		return knowledgePointId;
	}
	public void setKnowledgePointId(String knowledgePointId) {
		this.knowledgePointId = knowledgePointId;
	}
	public String getStrQuesDifficulty() {
		return strQuesDifficulty;
	}
	public void setStrQuesDifficulty(String strQuesDifficulty) {
		this.strQuesDifficulty = strQuesDifficulty;
	}
	public Object deepClone() {
		ObjectInputStream oi = null;
		Object obj = null;
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(this);
			// 从流里读出来
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			oi = new ObjectInputStream(bi);
			obj = oi.readObject();
		} catch (Exception e) {
			//logger.error("克隆出错：", e);
			e.printStackTrace();
		}

		return (obj);
	}
}
