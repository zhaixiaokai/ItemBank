
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	TeachingClassMemberService.java
 * | 包名：net.ib.util.service
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：wuzexi
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
 * <p>类名：net.ib.util.service.TeachingClassMemberService </p>
 * <p>描述：开课班级成员管理的service</p>
 * <p></p>
 */
public interface TeachingClassMemberService {
	public	String	TeachingClassMemberAdd(String addUserId,String teachingClassId,String curseId);
	public	String	TeachingClassMemberDelete(String DelId);
//	public	String	TeachingClassUpdate(String OldClassId,String NewClassId,String NewClassName,String NewTeacherId,String NewTeachingMaterialId,String NewExplain);
	public	String	TeachingClassMemberBulkDelete(String DelIds);
	public	String  TeachingClassMemberBulkAdd(String bulkAddIDs,String teachingClassId,String curseId);
}
