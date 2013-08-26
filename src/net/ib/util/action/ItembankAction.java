
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	ItembankAction.java
 * | ������net.ib.util.action
 * | ���������������Action
 * | ���ߣ�xiaokai
 * | �������ڣ�2012-12-3 ����4:20:46
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2012-12-3 ����4:20:46
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.ItembankGetService;
import net.ib.util.service.impl.TestServiceImp;

import org.apache.log4j.Logger;


  /**
 * <p>������net.ib.util.action.ItembankAction </p>
 * <p>��������ȡ���ϲ�ѯ������������б�</p>
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
 * <p>���ƣ�SelectItemBankByCourseAndUsage</p>
 * <p>˵����ͨ������������Ŀγ��Լ���;����ѯ���������������</p>
 * <p>������@return �趨�ļ�</p>
 * <p>����ֵ��String ��������</p>
 * <p>@return</p>
 */
	public	String	SelectItemBankByCourseAndUsage(){
		IGS.SelectItemBankByCourseAndUsage(curseId, IBusage);
		
		return	null;
	}
}
