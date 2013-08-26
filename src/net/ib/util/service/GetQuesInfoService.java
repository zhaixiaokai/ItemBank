
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	GetQuesInfoService.java
 * | ������net.ib.util.service
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-19 ����3:43:37
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-19 ����3:43:37
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.util.List;
import java.util.Map;

import net.ib.util.data.PaperConfig;
import net.ib.util.data.QuestionVo;


  /**
 * <p>������net.ib.util.service.GetQuesInfoService </p>
 * <p>������ͨ��ǰ̨ѡ���ɸѡ������ѯ���������������б�</p>
 * <p></p>
 */
public interface GetQuesInfoService {
	
/**
 * <p>���ƣ�getQuestionByInfoMap</p>
 * <p>˵����ͨ��PaperConfig�������͵ķ�ʽ���ݲ���</p>
 * <p>������@param list
 * <p>������@return �趨�ļ�</p>
 * <p>����ֵ��List<Map> ��������</p>
 * <p>@param list
 * <p>@return</p>
 */
	public List<Map> getQuestionByInfoMap(List<PaperConfig> paperConfig,List<String> tableList);
	/**
	 * 
	 * <p>���ƣ�getQuestionPhraseListToClass</p>
	 * <p>˵�����������б��List<-Map->����ת��ΪList<-Question->����</p>
	 * <p>������@param quesList		list<-map->���͵������б�
	 * <p>������@return �趨�ļ�</p>
	 * <p>����ֵ��List<QuestionVo> ��������</p>
	 * <p>@param quesList
	 * <p>@return</p>
	 */
	public List<QuestionVo> getQuestionPhraseListToClass(List<Map> quesList);
}
