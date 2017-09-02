package com.p2p.webapp.user.usercenter.dao;

import java.util.List;
import java.util.Map;

import com.p2p.webapp.user.login.entity.UserInfo;
import com.p2p.webapp.user.usercenter.entity.UserBank;
import com.p2p.webapp.user.usercenter.entity.UserCreditDetailInfo;
import com.p2p.webapp.user.usercenter.entity.UserCreditInfo;

public interface UserCenterDao {

    /*
     * 查询客户基本信息
     */
    public UserInfo queryUserById(String userid);

    /*
     * 根据微信openid查询客户基本信息
     */
    public UserInfo queryUserByWeixinOpenId(String weixin_open_id);

    /*
     * 更新客户基本信息
     */
    public void updateUserById(Map<String, String> paramMap);

    /*
     * 查询客户银行卡信息
     */
    public List<UserBank> queryBankInfo(String userid);

    /*
     * 插入客户银行信息
     */
    public void insertBankInfo(Map<String, Object> map);

    /*
     * 更新客户银行信息
     */
    public void updateBankInfo(Map<String, Object> map);

    /*
     * 删除客户银行信息
     */
    public void delBankInfo(String user_bank_acc_id);

    /*
     * 更新客户登录密码
     */
    public void updateLoginPwd(Map<String, Object> paramap);

    /*
     * 查询用户登录密码输入是否正确
     */
    public String queryUserByIdPd(Map<String, Object> paramap);

    /*
     * 更新客户交易密码
     */
    public void updateTranPwd(Map<String, Object> paramap);

    /*
     * 设置用户默认银行卡为否
     */
    public void updateUserBankDefaultNo(Map<String, Object> paramap);

    /*
     * 设置用户默认银行卡
     */
    public void updateUserBankDefault(Map<String, Object> paramap);

    /**
     * 根据用户的id,微信号，手机号 任意一个值来查询数数据中是否有用户的数据
     * 
     * @param userid
     * @param userPhone
     * @param weixinId
     * @return
     */
    public UserInfo queryUserInfoByPhoneNum(String userPhone);

    /*
     * 查询用户积分
     */
    public UserCreditInfo queryUserCredit(UserCreditInfo userCreditInfo);

    /*
     * 获取账户ID
     */
    public String queryAccountId();

    /*
     * 新增用户积分
     */
    public void addUserCredit(UserCreditInfo userCreditInfo);

    /*
     * 修改用户积分
     */
    public void updateUserCredit(UserCreditInfo userCreditInfo);

    /*
     * 查询用户积分明细
     */
    public List<UserCreditDetailInfo> queryUserCreditDetail(UserCreditDetailInfo userCreditDetailInfo);

    /*
     * 增加用户积分明细记录
     */
    public void addUserCreditDetail(UserCreditDetailInfo userCreditDetailInfo);

    /*
     * 通过enrollId查询当次积分变动
     */
    public UserCreditDetailInfo queryCreditDetailByEnrollId(String enroll_id);

    /*
     * 通过enrollId修改当次积分变动
     */
    public String updateCreditDetailByEnrollId(UserCreditDetailInfo userCreditDetailInfo);

    /*
     * 通过enrollId删除当次积分变动
     */
    public String delCreditDetailByEnrollId(String enroll_id);

}
