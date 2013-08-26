
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TeachingClassService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2012-12-10 下午4:46:16
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2012-12-10 下午4:46:16
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.service;


  /**
 * <p>类名：net.ib.util.service.TeachingClassService </p>
 * <p>描述：教学班级管理的service</p>
 * <p></p>
 */
public interface TeachingClassService {
	public	String	TeachingClassAdd(String	courseId,String	className,String classId,String TeacherId,String Explain,String TeachingMaterialId);
	public	String	TeachingClassDelete(String DelId);
	public	String	TeachingClassUpdate(String OldClassId,String NewClassId,String NewClassName,String NewTeacherId,String NewTeachingMaterialId,String NewExplain);
	public	String	TeachingClassBulkDelete(String DelIds);
}
