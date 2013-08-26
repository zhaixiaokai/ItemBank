
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	QuesConfig.java
 * | ������net.ib.util.data
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-20 ����5:07:37
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-20 ����5:07:37
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


  /**
 * <p>������net.ib.util.data.QuesConfig </p>
 * <p>������ÿһ���������Ͷ�Ӧ������������Ϣ</p>
 * <p></p>
 */
public class QuesConfig  implements	Serializable{
	/**
	 * ��Ӧ�������͵�������Ŀ
	 */
	private int quesNum=0;
	/**
	 * �����Ӧ��֪ʶ��
	 */
	private String knowledgePointId=null;
	
	/**
	 * �ַ������͵������Ѷ�
	 */
	private	String strQuesDifficulty=null;
	/**
	 * �������͵������Ѷ�
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
			// �����������
			ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
			oi = new ObjectInputStream(bi);
			obj = oi.readObject();
		} catch (Exception e) {
			//logger.error("��¡����", e);
			e.printStackTrace();
		}

		return (obj);
	}
}
