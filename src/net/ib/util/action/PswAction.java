
 /**
 * -------------------------------------------------------------------------------------
 * | 文件名：	PswAction.java
 * | 包名：net.ib.util.action
 * | 描述：TODO(用一句话描述该文件做什么)
 * | 作者：xiaokai
 * | 创建日期：2013-8-20 上午11:46:13
 * | 版本号： V1.0
 * | 版权归属：EDTT_708
 * |------------------------------------------------------------------------------------
 * | 修订记录:
 * |	1、2013-8-20 上午11:46:13
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.UserPswService;


  /**
 * <p>类名：net.ib.util.action.PswAction </p>
 * <p>描述：TODO(用一句话描述该文件做什么)</p>
 * <p></p>
 */
public class PswAction {
	private String opsw;
	private String npsw1;
	private String npsw2;
	private String md5Psw;
	private String md5OldPsw;
	
	
	private UserPswService userPswService;
	public UserPswService getUserPswService() {
		return userPswService;
	}
	public void setUserPswService(UserPswService userPswService) {
		this.userPswService = userPswService;
	}
	public String getMd5OldPsw() {
		return md5OldPsw;
	}
	public void setMd5OldPsw(String md5OldPsw) {
		this.md5OldPsw = md5OldPsw;
	}
	public String getMd5Psw() {
		return md5Psw;
	}
	public void setMd5Psw(String md5Psw) {
		this.md5Psw = md5Psw;
	}
	public String getOpsw() {
		return opsw;
	}
	public void setOpsw(String opsw) {
		this.opsw = opsw;
	}
	public String getNpsw1() {
		return npsw1;
	}
	public void setNpsw1(String npsw1) {
		this.npsw1 = npsw1;
	}
	public String getNpsw2() {
		return npsw2;
	}
	public void setNpsw2(String npsw2) {
		this.npsw2 = npsw2;
	}
	
	public String update(){
		userPswService.updateUserPsw(md5OldPsw, md5Psw);
		return null;
	}
}
