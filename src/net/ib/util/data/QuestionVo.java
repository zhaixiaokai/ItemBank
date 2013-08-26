
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	QuestionVo.java
 * | ������net.ib.util.data
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-20 ����9:21:27
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-20 ����9:21:27
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


  /**
 * <p>������net.ib.util.data.QuestionVo </p>
 * <p>����������������࣬һ���������ӳ��Ϊһ������</p>
 * <p></p>
 */
public class QuestionVo  implements	Serializable{
	/**
	 * ��������
	 */
	private String QuesName=null;
	/**
	 * �����Ѷ�
	 */
	private double QuesDifficulty;
	/**
	 * ����id
	 */
	private String QuesId=null;
	/**
	 * ��������
	 */
	private String QuesType=null;
	/**
	 * ���������ʱ
	 */
	private int QuesUseTime=0;
	/**
	 * ����Ĭ�Ϸ�ֵ
	 */
	private double DefaultPoint=0;
	/**
	 * ���⿼��֪ʶ��id
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
