
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	TemplateAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-5 ����2:32:49
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-5 ����2:32:49
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import org.apache.log4j.Logger;

import net.ib.util.service.TemplateService;


  /**
 * <p>������net.ib.util.action.TemplateAction </p>
 * <p>��������ȡģ���Action</p>
 * <p></p>
 */
public class TemplateAction {
	private static Logger logger = Logger.getLogger(KnowledgePointAction.class);
	TemplateService templateService;
	String	type;
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	public	String TemplateGetFunc(){
		logger.debug(this.type);
		if("xuanze".equals(this.type)){
			templateService.GetXZTemplate();
		}else if("tiankong".equals(this.type)){
			templateService.GetTKTemplate();
		}else if("panduan".equals(this.type)){
			templateService.GetPDTemplate();
		}else if("jianda".equals(this.type)){
			templateService.GetJDTemplate();
		}
		return null;
	}
}
