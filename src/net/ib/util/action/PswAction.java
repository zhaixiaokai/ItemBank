
 /**
 * -------------------------------------------------------------------------------------
 * | �ļ�����	PswAction.java
 * | ������net.ib.util.action
 * | ������TODO(��һ�仰�������ļ���ʲô)
 * | ���ߣ�xiaokai
 * | �������ڣ�2013-8-20 ����11:46:13
 * | �汾�ţ� V1.0
 * | ��Ȩ������EDTT_708
 * |------------------------------------------------------------------------------------
 * | �޶���¼:
 * |	1��2013-8-20 ����11:46:13
 * |------------------------------------------------------------------------------------ 
 */
package net.ib.util.action;

import net.ib.util.service.UserPswService;


  /**
 * <p>������net.ib.util.action.PswAction </p>
 * <p>������TODO(��һ�仰�������ļ���ʲô)</p>
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
