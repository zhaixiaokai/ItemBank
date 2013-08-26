
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	QuestionValidationAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-3-6 上午10:37:11
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-3-6 上午10:37:11
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service.impl;

import java.io.File;


  /**
 * <p>类名：net.ib.util.action.QuestionValidationAction </p>
 * <p>描述：检查试题文档的合法性</p>
 * <p></p>
 */
public class QuestionValidationServiceImpl {
	/**
	 * 
	 * <p>名称：CheckXZValidation</p>
	 * <p>说明：检查选择题的合法性</p>
	 * <p>参数：@param file
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：boolean 返回类型</p>
	 * <p>@param file
	 * <p>@return</p>
	 */
	public boolean CheckXZValidation(File file){
		
		return true;
	}
	/**
	 * 
	 * <p>名称：CheckTKValidation</p>
	 * <p>说明：检查填空题的合法性</p>
	 * <p>参数：@param file
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：boolean 返回类型</p>
	 * <p>@param file
	 * <p>@return</p>
	 */
	public boolean CheckTKValidation(File file){
		return true;
	}
	/**
	 * 
	 * <p>名称：CheckPDValidation</p>
	 * <p>说明：检查判断题的合法性</p>
	 * <p>参数：@param file
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：boolean 返回类型</p>
	 * <p>@param file
	 * <p>@return</p>
	 */
	public boolean CheckPDValidation(File file){
		return true;
	}
	/**
	 * 
	 * <p>名称：CheckJDValidation</p>
	 * <p>说明：检查简答题的合法性</p>
	 * <p>参数：@param file
	 * <p>参数：@return 设定文件</p>
	 * <p>返回值：boolean 返回类型</p>
	 * <p>@param file
	 * <p>@return</p>
	 */
	public boolean CheckJDValidation(File file){
		return true;
	}
}
