
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TemplateAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-5 下午2:32:49
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-5 下午2:32:49
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import org.apache.log4j.Logger;

import net.ib.util.service.TemplateService;


  /**
 * <p>类名：net.ib.util.action.TemplateAction </p>
 * <p>描述：获取模板的Action</p>
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
