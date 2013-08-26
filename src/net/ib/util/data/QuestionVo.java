
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	QuestionVo.java
 * | 包名：net.ib.util.data
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-20 上午9:21:27
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-20 上午9:21:27
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


  /**
 * <p>类名：net.ib.util.data.QuestionVo </p>
 * <p>描述：定义试题的类，一个试题可以映射为一个基因</p>
 * <p></p>
 */
public class QuestionVo  implements	Serializable{
	/**
	 * 试题名称
	 */
	private String QuesName=null;
	/**
	 * 试题难度
	 */
	private double QuesDifficulty;
	/**
	 * 试题id
	 */
	private String QuesId=null;
	/**
	 * 试题类型
	 */
	private String QuesType=null;
	/**
	 * 试题估计用时
	 */
	private int QuesUseTime=0;
	/**
	 * 试题默认分值
	 */
	private double DefaultPoint=0;
	/**
	 * 试题考察知识点id
	 */
	private	String	IBId=null;
	public String getIBId() {
		return IBId;
	}
	public void setIBId(String iBId) {
		IBId = iBId;
	}
	private String KnowledgePointId=null;
	
	public	QuestionVo(){
		
	}
	public	QuestionVo(String KnowledgePointId,String QuesId,String QuesType,double QuesDifficulty,String QuesName,double Point){
		this.KnowledgePointId=KnowledgePointId;
		this.QuesId=QuesId;
		this.QuesType=QuesType;
		this.QuesDifficulty=QuesDifficulty;
		this.QuesName	=	QuesName;
		this.DefaultPoint=Point;
	}
	public String getQuesName() {
		return QuesName;
	}
	public void setQuesName(String quesName) {
		QuesName = quesName;
	}

	public String getQuesId() {
		return QuesId;
	}
	public void setQuesId(String quesId) {
		QuesId = quesId;
	}
	public String getQuesType() {
		return QuesType;
	}
	public void setQuesType(String quesType) {
		QuesType = quesType;
	}
	public int getQuesUseTime() {
		return QuesUseTime;
	}
	public void setQuesUseTime(int quesUseTime) {
		QuesUseTime = quesUseTime;
	}
	public double getQuesDifficulty() {
		return QuesDifficulty;
	}
	public void setQuesDifficulty(double quesDifficulty) {
		QuesDifficulty = quesDifficulty;
	}
	public double getDefaultPoint() {
		return DefaultPoint;
	}
	public void setDefaultPoint(double defaultPoint) {
		DefaultPoint = defaultPoint;
	}
	public String getKnowledgePointId() {
		return KnowledgePointId;
	}
	public void setKnowledgePointId(String knowledgePointId) {
		KnowledgePointId = knowledgePointId;
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
