package net.ib.common;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanLoader implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(BeanLoader.class);
	
	// SpringӦ�������Ļ���
	private static ApplicationContext applicationContext;
	
	//��ȡָ����Beanʵ��
	public static Object getBean(String name) throws BeansException {
		logger.debug("���������"+name);
		if(null==name){
			logger.error("δָ���������");
			return null;
		}else if("".equals(name)){
			logger.error("δָ���������");
			return null;
		}
		
		if(null==applicationContext){
			logger.error("��ʼ�������Ļ���ʧ�ܣ�ApplicationContext is null");
			return null;
		}
		Object obj=applicationContext.getBean(name);
		if(null==obj){
			logger.error("��ApplicationContext�л�ȡ����["+name+"]ʧ��.");
		}
		return obj;
	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		BeanLoader.applicationContext=applicationContext;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
