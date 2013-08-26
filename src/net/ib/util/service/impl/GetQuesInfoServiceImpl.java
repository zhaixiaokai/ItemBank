
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	GetQuesInfoServiceImpl.java
 * | 包名：net.ib.util.service.impl
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-20 下午1:51:32
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-20 下午1:51:32
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.util.List;
import java.util.Map;

import net.ib.util.data.PaperConfig;
import net.ib.util.data.QuestionVo;
import net.ib.util.service.GetQuesInfoService;


  /**
 * <p>类名：net.ib.util.service.impl.GetQuesInfoServiceImpl </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
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
