package com.p2p.webapp.user.usercenter.service;

import java.util.List;

import com.p2p.webapp.user.login.entity.UserInfo;
import com.p2p.webapp.user.usercenter.vo.UserBankVo;
import com.p2p.webapp.user.usercenter.vo.UserCreditDetailVo;
import com.p2p.webapp.user.usercenter.vo.UserCreditVo;
import com.p2p.webapp.user.usercenter.vo.UserInfoVo;

public interface UserCenterService {
    /*
     * 查询用户基本信息
     */
    public UserInfoVo queryUserInfo(String userid);

    /**
     * 通过手机号查询用户的信息
     * 
     * @param userid
     * @return
     */
    public UserInfo queryUserInfoByPhoneNum(String phone);

    /*
     * 根据微信openid查询客户基本信息
     */
    public UserInfoVo queryUserByWeixinOpenId(String weixin_open_id);

    /*
     * 保存用户基本信息
     */
    public String saveUserinfo(UserInfoVo userInfoVo);

    /*
     * 查询用户银行卡信息
     */
    public List<UserBankVo> queryBankInfo(String userid);

    /*
     * 增加银行卡信息
     */
    public void addBankInfo(UserBankVo ubvo);

    /*
     * 更新银行卡信息
     */
    public void editBankInfo(UserBankVo ubvo);

    /*
     * 删除银行卡信息
     */
    public void delBankInfo(String user_bank_acc_id);

    /*
     * 修改客户登录密码
     */
    public void editUserLoginPwd(UserInfoVo uvo);

    /*
     * 验证客户登录密码
     */
    public boolean checkUserLogin(String user_id, String login_pwd);

    /*
     * 修改客户交易密码
     */
    public void editUserTranPwd(UserInfoVo uvo);

    /*
     * 设置默认银行卡
     */
    public void setDefaultBank(UserBankVo ubvo);

    /*
     * 查询用户积分
     */
    public UserCreditVo queryUserCredit(UserCreditVo userCreditVo);

    /*
     * 新增用户积分
     */
    public void addUserCredit(UserCreditVo userCreditVo);

    /*
     * 修改用户积分
     */
    public void updateUserCredit(UserCreditVo userCreditVo);

    /*
     * 查询用户积分明细
     */
    public List<UserCreditDetailVo> queryUserCreditDetail(UserCreditDetailVo userCreditDetailVo);

    /*
     * 增加用户积分明细记录
     */
    public void addUserCreditDetail(UserCreditDetailVo userCreditDetailVo);

    /*
     * 通过enrollId查询当次积分变动
     */
    public UserCreditDetailVo queryCreditDetailByEnrollId(String enroll_id);

    /*
     * 通过enrollId修改当次积分变动
     */
    public String updateCreditDetailByEnrollId(UserCreditDetailVo userCreditDetailVo);

    /*
     * 通过enrollId删除当次积分变动
     */
    public String delCreditDetailByEnrollId(String enroll_id);
}
