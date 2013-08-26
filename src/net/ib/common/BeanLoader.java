package net.ib.common;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanLoader implements ApplicationContextAware {
	private static Logger logger = Logger.getLogger(BeanLoader.class);
	
	// Spring应用上下文环境
	private static ApplicationContext applicationContext;
	
	//获取指定的Bean实例
	public static Object getBean(String name) throws BeansException {
		logger.debug("加载组件："+name);
		if(null==name){
			logger.error("未指定组件名称");
			return null;
		}else if("".equals(name)){
			logger.error("未指定组件名称");
			return null;
		}
		
		if(null==applicationContext){
			logger.error("初始化上下文环境失败，ApplicationContext is null");
			return null;
		}
		Object obj=applicationContext.getBean(name);
		if(null==obj){
			logger.error("从ApplicationContext中获取对象["+name+"]失败.");
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
