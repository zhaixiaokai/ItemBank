package net.ib.util.action;

import org.apache.log4j.Logger;

import net.ib.util.service.impl.TestServiceImp;
import net.ib.util.service.impl.WriteSessionImpl;

/**
 * 
 * <p>������net.ib.util.action.LogoutAction </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class LogoutAction {
	private static Logger logger = Logger.getLogger(TestServiceImp.class);
	WriteSessionImpl	wsi	=	new	WriteSessionImpl();
	/**
	 * 
	 * <p>���ƣ�logout</p>
	 * <p>˵����TODO(������һ�仰�����������������)</p>
	 * <p>������ �趨�ļ�</p>
	 * <p>����ֵ��void ��������</p>
	 * <p></p>
	 */
	public void logout(){
		logger.debug("�˳���¼@@@");
		wsi.removeSession();
	}
}
