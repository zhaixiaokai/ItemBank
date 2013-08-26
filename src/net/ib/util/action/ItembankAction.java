
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	ItembankAction.java
 * | 包名：net.ib.util.action
 * | 描述：针对试题库的Action
 * | 作者：xiaokai
 * | 创建日期：2012-12-3 下午4:20:46
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-3 下午4:20:46
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.ItembankGetService;
import net.ib.util.service.impl.TestServiceImp;

import org.apache.log4j.Logger;


  /**
 * <p>类名：net.ib.util.action.ItembankAction </p>
 * <p>描述：获取符合查询条件的试题库列表</p>
 * <p></p>
 */
public class ItembankAction {
	private static Logger logger = Logger.getLogger(ItembankAction.class);
	private	String	curseId	=	null;
	private	String	IBusage	=	null;
	ItembankGetService	IGS	=	null;
	public ItembankGetService getIGS() {
		return IGS;
	}

	public void setIGS(ItembankGetService iGS) {
		IGS = iGS;
	}

	public String getCurseId() {
		return curseId;
	}

	public void setCurseId(String curseId) {
		this.curseId = curseId;
	}

	
	public String getIBusage() {
		return IBusage;
	}

	public void setIBusage(String iBusage) {
		IBusage = iBusage;
	}
/**
 * 
 * <p>名称：SelectItemBankByCourseAndUsage</p>
 * <p>说明：通过试题库所属的课程以及用途来查询符合条件的试题库</p>
 * <p>参数：@return 设定文件</p>
 * <p>返回值：String 返回类型</p>
 * <p>@return</p>
 */
	public	String	SelectItemBankByCourseAndUsage(){
		IGS.SelectItemBankByCourseAndUsage(curseId, IBusage);
		
		return	null;
	}
}
