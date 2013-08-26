
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	GetQuesInfoService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-19 下午3:43:37
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-19 下午3:43:37
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;

import java.util.List;
import java.util.Map;

import net.ib.util.data.PaperConfig;
import net.ib.util.data.QuestionVo;


  /**
 * <p>类名：net.ib.util.service.GetQuesInfoService </p>
 * <p>描述：通过前台选择的筛选条件查询符合条件的试题列表</p>
 * <p></p>
 */
public interface GetQuesInfoService {
	
/**
 * <p>名称：getQuestionByInfoMap</p>
 * <p>说明：通过PaperConfig数据类型的方式传递参数</p>
 * <p>参数：@param list
 * <p>参数：@return 设定文件</p>
 * <p>返回值：List<Map> 返回类型</p>
 * <p>@param list
 * <p>@return</p>
 */
	public List<Map> getQuestionByInfoMap(List<PaperConfig> paperConfig,List<String> tableList);
	/**
	 * 
	 * <p>名称：getQuestionPhraseListToClass</p>
	 * <p>说明：将试题列表从List<-Map->类型转换为List<-Question->类型</p>
	 * <p>参数：@param quesList		list<-map->类型的试题列表
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：List<QuestionVo> 返回类型</p>
	 * <p>@param quesList
	 * <p>@return</p>
	 */
	public List<QuestionVo> getQuestionPhraseListToClass(List<Map> quesList);
}
