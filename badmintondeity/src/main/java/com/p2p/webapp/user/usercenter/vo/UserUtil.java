package com.p2p.webapp.user.usercenter.vo;

import com.p2p.webapp.user.login.entity.UserInfo;
/**
 * userInfoVO 转userinfo
 * 字段太多了，我只转目前需要的了
 * @author eoems
 *
 */
public class UserUtil {
	public static UserInfo transFromUserInfoVo(UserInfoVo userInfo){
	UserInfo userInfos =	new UserInfo();
	userInfos.setAcc_balance(userInfo.getAcc_balance());
	userInfos.setAcc_enchash(userInfo.getAcc_enchash());
	userInfos.setAcc_freeze(userInfo.getAcc_freeze());
	userInfos.setAcc_status(userInfo.getAcc_status());
	userInfos.setAcc_statusname(userInfo.getAcc_statusname());
	userInfos.setBank_acc_code(userInfo.getBank_acc_code());
	userInfos.setBank_name(userInfo.getBank_name());
	userInfos.setCertif_no(userInfo.getCertif_no());
	userInfos.setCertif_type(userInfo.getCertif_type());
	userInfos.setCreate_date(userInfo.getCreate_date());
	userInfos.setErr_count(userInfo.getErr_count());
	userInfos.setLogin_pwd(userInfo.getLogin_pwd());
	userInfos.setMail(userInfo.getMail());
	userInfos.setNewlogin_pwd(userInfo.getNewlogin_pwd());
	userInfos.setWeixin_id(userInfo.getUser_weixin_id());
	userInfos.setPhone(userInfo.getPhone());
    return   userInfos;
	}
	

		public static UserInfoVo transFromUserInfo(UserInfo  userInfo){
			UserInfoVo userInfos =	new UserInfoVo();
		userInfos.setAcc_balance(userInfo.getAcc_balance());
		userInfos.setAcc_enchash(userInfo.getAcc_enchash());
		userInfos.setAcc_freeze(userInfo.getAcc_freeze());
		userInfos.setAcc_status(userInfo.getAcc_status());
		userInfos.setAcc_statusname(userInfo.getAcc_statusname());
		userInfos.setBank_acc_code(userInfo.getBank_acc_code());
		userInfos.setBank_name(userInfo.getBank_name());
		userInfos.setCertif_no(userInfo.getCertif_no());
		userInfos.setCertif_type(userInfo.getCertif_type());
		userInfos.setCreate_date(userInfo.getCreate_date());
		userInfos.setErr_count(userInfo.getErr_count());
		userInfos.setLogin_pwd(userInfo.getLogin_pwd());
		userInfos.setMail(userInfo.getMail());
		userInfos.setNewlogin_pwd(userInfo.getNewlogin_pwd());
		userInfos.setUser_weixin_id(userInfo.getWeixin_id());
		userInfos.setPhone(userInfo.getPhone());
	    return   userInfos;
		}

}
