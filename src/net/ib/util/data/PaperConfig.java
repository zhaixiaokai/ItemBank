
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	PaperConfig.java
 * | ������net.ib.util.data
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-19 ����3:55:59
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-19 ����3:55:59
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
 * <p>������net.ib.util.data.PaperConfig </p>
 * <p>������ĳһ���������͵������������</p>
 * <p></p>
 */
public class PaperConfig implements	Serializable{
	/**
	 * ��������
	 */
	private String quesType=null;
	/**
	 * ĳ��������������Ŀ��������Ϣ
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
