package net.ib.util.action;

import net.ib.util.service.TestService;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {// ����̳���ActionSupport�ࡣ�����Ϳ���ֱ��ʹ��SUCCESS,
												// LOGIN�ȱ�������дexecute�ȷ���

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private	String	usertype;
	private TestService testService;
	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	private	String	check;
	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public	String	getUsertype(){
		return	this.usertype;
	}
	public	void	setUsertype(String	usertype){
		this.usertype	=	usertype;
	}

	@Override
	public String execute() throws Exception {
		testService.ValidPassword(this.username, this.password,this.usertype,this.check);
		return "success";
	}
}
