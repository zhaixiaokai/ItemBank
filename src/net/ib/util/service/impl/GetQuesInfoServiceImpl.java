
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	GetQuesInfoServiceImpl.java
 * | ������net.ib.util.service.impl
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-3-20 ����1:51:32
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-3-20 ����1:51:32
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.util.List;
import java.util.Map;

import net.ib.util.data.PaperConfig;
import net.ib.util.data.QuestionVo;
import net.ib.util.service.GetQuesInfoService;


  /**
 * <p>������net.ib.util.service.impl.GetQuesInfoServiceImpl </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
 * <p></p>
 */
public class GetQuesInfoServiceImpl implements GetQuesInfoService {

	@Override
	public List<Map> getQuestionByInfoMap(List<PaperConfig> paperConfig,
			List<String> tableList) {
		// TODO Auto-generated method stub
		PaperConfig pc=null;
		for(int i=0,listSize=paperConfig.size();i<listSize;i++){
			pc=paperConfig.get(i);
		}
		return null;
	}

	@Override
	public List<QuestionVo> getQuestionPhraseListToClass(List<Map> quesList) {
		// TODO Auto-generated method stub
		return null;
	}

}
