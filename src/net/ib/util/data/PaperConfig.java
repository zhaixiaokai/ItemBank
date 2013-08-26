
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	PaperConfig.java
 * | 包名：net.ib.util.data
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-19 下午3:55:59
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-19 下午3:55:59
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


  /**
 * <p>类名：net.ib.util.data.PaperConfig </p>
 * <p>描述：某一种试题类型的组卷配置类型</p>
 * <p></p>
 */
public class PaperConfig implements	Serializable{
	/**
	 * 试题类型
	 */
	private String quesType=null;
	/**
	 * 某种试题类型下题目的配置信息
	 */
	private ArrayList<QuesConfig> quesConfig=null;
	
	public PaperConfig(String quesType,ArrayList<QuesConfig> quesConfig){
		this.quesConfig=quesConfig;
		this.quesType=quesType;
	}
	public String getQuesType() {
		return quesType;
	}
	public void setQuesType(String quesType) {
		this.quesType = quesType;
	}
	public ArrayList<QuesConfig> getQuesConfig() {
		return quesConfig;
	}
	public void setQuesConfig(ArrayList<QuesConfig> quesConfig) {
		this.quesConfig = quesConfig;
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
