package com.p2p.webapp.user.regist.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.user.regist.dao.RegistDao;
import com.p2p.webapp.user.regist.entity.User;
import com.p2p.webapp.user.regist.entity.UserCtl;
import com.p2p.webapp.user.regist.service.RegistService;
import com.p2p.webapp.user.usercenter.dao.UserCenterDao;
import com.p2p.webapp.user.usercenter.entity.UserCreditDetailInfo;
import com.p2p.webapp.user.usercenter.entity.UserCreditInfo;

public class RegistServiceImpl implements RegistService {

    private RegistDao registDao;
    private UserCenterDao userCenterDao;

    /**
     * @description 检查手机号码是否已注册
     * @version
     * @title
     * @author 
     * @see com.p2p.webapp.user.regist.service.RegistService#checkPhone(java.lang.String)
     * @param phoneno
     * @return 
    */
    public User checkPhone(String phoneno) {
        User user = registDao.queryUserByPhone(phoneno);
        return user;
    }

    /*
     * 微信id是否存在如果存在返回user
     */
    public User checkWeiXinUser(String weixinId) {
        User user = registDao.queryUserByWeixinId(weixinId);
        return user;
    }

    /*
     * 注册用户
     */
    public String registUser(User user) {
        // 生成用户ID
        String userid = registDao.queryUserId();
        // 用户基本信息
        user.setUser_id(userid);
        user.setCertif_no("");
        user.setCertif_type("");
        user.setUser_code("");
        // 用户状态
        user.setUser_status(Constant.getUser_status_active());
        // 用户类型 A 管理员 G普通用户
        user.setUser_type("G");
        registDao.insertUser(user);

        // 创建用户成绩账户
        UserCreditInfo userCreditInfo = new UserCreditInfo();
        String account_id = userCenterDao.queryAccountId();
        userCreditInfo.setAccount_id(account_id);
        userCreditInfo.setUser_id(userid);
        userCreditInfo.setAcc_balance("0");// 新用户成绩
        userCenterDao.addUserCredit(userCreditInfo);

        // 创建用户积分明细
        // UserCreditDetailInfo userCreditDetailInfo = new
        // UserCreditDetailInfo();
        // userCreditDetailInfo.setAccount_id(account_id);
        // userCreditDetailInfo.setUser_id(userid);
        // userCreditDetailInfo.setBs_type("0");
        // userCreditDetailInfo.setBs_no("0");
        // userCreditDetailInfo.setAcc_tran_type("0");
        // userCreditDetailInfo.setAmnt("100");
        // userCenterDao.addUserCreditDetail(userCreditDetailInfo);

        // 用户安全控制
        UserCtl uctl = new UserCtl();
        uctl.setErr_count("0");
        uctl.setLogin_pwd("000000");// 默认密码000000，未使用
        // 0有效 1冻结 2注销
        uctl.setUser_status("0");
        // 0 手机认证 1身份认证 2银行卡认证
        uctl.setSecty_level("0");
        uctl.setUser_id(userid);
        registDao.insertUserCtl(uctl);

        // 用户权限
        Map<String, Object> prvgmap = new HashMap<String, Object>();
        prvgmap.put("user_id", userid);
        prvgmap.put("role_code", "custom");
        registDao.insertUserPrvg(prvgmap);
        // 注册成功
        return userid;
    }

    public RegistDao getRegistDao() {
        return registDao;
    }

    public void setRegistDao(RegistDao registDao) {
        this.registDao = registDao;
    }

    public UserCenterDao getUserCenterDao() {
        return userCenterDao;
    }

    public void setUserCenterDao(UserCenterDao userCenterDao) {
        this.userCenterDao = userCenterDao;
    }
}
