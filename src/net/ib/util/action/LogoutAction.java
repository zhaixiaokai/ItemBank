package net.ib.util.action;

import org.apache.log4j.Logger;

import net.ib.util.service.impl.TestServiceImp;
import net.ib.util.service.impl.WriteSessionImpl;

/**
 * 
 * <p>类名：net.ib.util.action.LogoutAction </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class LogoutAction {
	private static Logger logger = Logger.getLogger(TestServiceImp.class);
	WriteSessionImpl	wsi	=	new	WriteSessionImpl();
	/**
	 * 
	 * <p>名称：logout</p>
	 * <p>说明：TODO(这里用一句话描述这个方法的作用)</p>
	 * <p>参数： 设定文件</p>
	 * <p>返回值：void 返回类型</p>
	 * <p></p>
	 */
	public void logout(){
		logger.debug("退出登录@@@");
		wsi.removeSession();
	}
}
