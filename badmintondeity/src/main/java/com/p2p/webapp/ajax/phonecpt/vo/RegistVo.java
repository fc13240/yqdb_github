package com.p2p.webapp.ajax.phonecpt.vo;
/**
 * 注册信息VO
 * @author Administrator
 *
 */
public class RegistVo {
	
	//手机号码
	private String cusMobile;
	//登录密码1
	private String cusPass1;
	//登录密码2
	private String cusPass2;
	//验证码
	private String cusCpt;
	
	public String getCusMobile() {
		return cusMobile;
	}
	public void setCusMobile(String cusMobile) {
		this.cusMobile = cusMobile;
	}
	public String getCusPass1() {
		return cusPass1;
	}
	public void setCusPass1(String cusPass1) {
		this.cusPass1 = cusPass1;
	}
	public String getCusPass2() {
		return cusPass2;
	}
	public void setCusPass2(String cusPass2) {
		this.cusPass2 = cusPass2;
	}
	public String getCusCpt() {
		return cusCpt;
	}
	public void setCusCpt(String cusCpt) {
		this.cusCpt = cusCpt;
	}
	
	
}
