package com.p2p.webapp.user.usercenter.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.p2p.webapp.user.login.entity.UserInfo;
import com.p2p.webapp.user.usercenter.dao.UserCenterDao;
import com.p2p.webapp.user.usercenter.entity.UserBank;
import com.p2p.webapp.user.usercenter.entity.UserCreditDetailInfo;
import com.p2p.webapp.user.usercenter.entity.UserCreditInfo;
import com.p2p.webapp.user.usercenter.service.UserCenterService;
import com.p2p.webapp.user.usercenter.vo.UserBankVo;
import com.p2p.webapp.user.usercenter.vo.UserCreditDetailVo;
import com.p2p.webapp.user.usercenter.vo.UserCreditVo;
import com.p2p.webapp.user.usercenter.vo.UserInfoVo;

public class UserCenterServiceImpl implements UserCenterService {

    private UserCenterDao userCenterDao;

    /*
     * 查询用户基本信息
     */
    public UserInfoVo queryUserInfo(String userid) {
        UserInfoVo udto = new UserInfoVo();
        // TODO Auto-generated method stub
        UserInfo uinfo = userCenterDao.queryUserById(userid);
        udto.setCertif_no(uinfo.getCertif_no());
        udto.setCertif_type(uinfo.getCertif_type());
        udto.setCreate_date(uinfo.getCreate_date());
        udto.setErr_count(uinfo.getErr_count());
        udto.setLogin_pwd(uinfo.getLogin_pwd());
        udto.setMail(uinfo.getMail());
        udto.setPhone(uinfo.getPhone());
        udto.setRecod_phone(uinfo.getRecod_phone());
        udto.setSecty_level(uinfo.getSecty_level());
        udto.setTran_pwd(uinfo.getTran_pwd());
        udto.setUpdate_date(uinfo.getUpdate_date());
        udto.setUser_code(uinfo.getUser_cde());
        udto.setUser_id(uinfo.getUser_id());
        udto.setUser_name(uinfo.getUser_name());
        udto.setUser_nickname(uinfo.getUser_nickname());
        udto.setUser_status(uinfo.getUser_status());
        udto.setUser_type(uinfo.getUser_type());
        udto.setUser_credit(uinfo.getUser_credit());
        udto.setUser_weixin_id(uinfo.getWeixin_id());
        udto.setUser_headimgurl(uinfo.getUser_headimgurl());
        return udto;
    }

    /*
     * 保存用户基本信息
     */
    @SuppressWarnings("unchecked")
    public String saveUserinfo(UserInfoVo userInfoVo) {
        // TODO Auto-generated method stub
        Map paramMap = new HashMap();
        paramMap.put("user_cde", userInfoVo.getUser_code());
        paramMap.put("user_name", userInfoVo.getUser_name());
        paramMap.put("user_nickname", userInfoVo.getUser_nickname());
        paramMap.put("mail", userInfoVo.getMail());
        paramMap.put("certif_type", userInfoVo.getCertif_type());
        paramMap.put("certif_no", userInfoVo.getCertif_no());
        paramMap.put("recod_phone", userInfoVo.getRecod_phone());
        paramMap.put("userid", userInfoVo.getUser_id());
        paramMap.put("phone", userInfoVo.getPhone());
        paramMap.put("user_headimgurl", userInfoVo.getUser_headimgurl());
        userCenterDao.updateUserById(paramMap);
        return "0";
    }

    /*
     * 查询用户银行卡信息
     */
    public List<UserBankVo> queryBankInfo(String userid) {
        List<UserBankVo> returnlist = new ArrayList<UserBankVo>();
        List<UserBank> lu = userCenterDao.queryBankInfo(userid);
        if (lu != null && lu.size() > 0) {
            for (int i = 0; i < lu.size(); i++) {
                UserBankVo ubvo = new UserBankVo();
                UserBank ub = lu.get(i);
                ubvo.setBank_acc_code(ub.getBank_acc_code());
                ubvo.setBank_branch_name(ub.getBank_branch_name());
                ubvo.setBank_code(ub.getBank_code());
                ubvo.setBank_name(ub.getBank_name());
                ubvo.setCreate_date(ub.getCreate_date());
                ubvo.setDefault_flag(ub.getDefault_flag());
                ubvo.setUpdate_date(ub.getUpdate_date());
                ubvo.setUser_bank_acc_id(ub.getUser_bank_acc_id());
                ubvo.setUser_id(ub.getUser_id());
                returnlist.add(ubvo);
            }
        }
        return returnlist;
    }

    /*
     * 增加银行卡信息
     */
    public void addBankInfo(UserBankVo ubvo) {
        String default_flag = "0";
        // 判断是否维护过银行卡
        List<UserBank> lu = userCenterDao.queryBankInfo(ubvo.getUser_id());
        if (lu == null || lu.size() == 0) {
            // 设为默认
            default_flag = "1";
        } else {
            default_flag = "0";
        }
        Map<String, Object> parammap = new HashMap<String, Object>();
        parammap.put("user_id", ubvo.getUser_id());
        parammap.put("bank_code", ubvo.getBank_code());
        parammap.put("bank_name", ubvo.getBank_name());
        parammap.put("bank_branch_name", ubvo.getBank_branch_name());
        parammap.put("bank_acc_code", ubvo.getBank_acc_code());
        parammap.put("default_flag", default_flag);
        userCenterDao.insertBankInfo(parammap);
    }

    /*
     * 设置默认银行卡
     */
    public void setDefaultBank(UserBankVo ubvo) {
        // 更新用户所有卡为否
        Map<String, Object> parammap = new HashMap<String, Object>();
        parammap.put("user_id", ubvo.getUser_id());
        userCenterDao.updateUserBankDefaultNo(parammap);
        // 设置默认
        parammap.put("user_bank_acc_id", ubvo.getUser_bank_acc_id());
        userCenterDao.updateUserBankDefault(parammap);
    }

    /*
     * 更新银行卡信息
     */
    @SuppressWarnings("unchecked")
    public void editBankInfo(UserBankVo ubvo) {
        Map parammap = new HashMap();
        parammap.put("user_bank_acc_id", ubvo.getUser_bank_acc_id());
        parammap.put("user_id", ubvo.getUser_id());
        parammap.put("bank_code", ubvo.getBank_code());
        parammap.put("bank_name", ubvo.getBank_name());
        parammap.put("bank_branch_name", ubvo.getBank_branch_name());
        parammap.put("bank_acc_code", ubvo.getBank_acc_code());
        userCenterDao.updateBankInfo(parammap);
    }

    /*
     * 删除银行卡信息
     */
    public void delBankInfo(String user_bank_acc_id) {
        userCenterDao.delBankInfo(user_bank_acc_id);
    }

    /*
     * 修改客户登录密码
     */
    public void editUserLoginPwd(UserInfoVo uvo) {
        String user_id = uvo.getUser_id();
        String login_pwd = uvo.getNewlogin_pwd();
        Map<String, Object> paramap = new HashMap<String, Object>();
        paramap.put("user_id", user_id);
        paramap.put("login_pwd", login_pwd);
        userCenterDao.updateLoginPwd(paramap);
    }

    /*
     * 验证客户登录密码
     */
    public boolean checkUserLogin(String user_id, String login_pwd) {

        Map<String, Object> paramap = new HashMap<String, Object>();
        paramap.put("user_id", user_id);
        paramap.put("login_pwd", login_pwd);
        String userid = userCenterDao.queryUserByIdPd(paramap);
        if (userid != null && !"".equals(userid)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * 修改客户交易密码
     */
    public void editUserTranPwd(UserInfoVo uvo) {
        String user_id = uvo.getUser_id();
        String tran_pwd = uvo.getNewtran_pwd();
        Map<String, Object> paramap = new HashMap<String, Object>();
        paramap.put("user_id", user_id);
        paramap.put("tran_pwd", tran_pwd);
        userCenterDao.updateTranPwd(paramap);
    }

    public UserCenterDao getUserCenterDao() {
        return userCenterDao;
    }

    public void setUserCenterDao(UserCenterDao userCenterDao) {
        this.userCenterDao = userCenterDao;
    }

    /**
     * @description 根据微信OpenId查询用户
     * @version
     * @title
     * @author
     * @see
     * @param weixin_open_id
     * @return
     */
    public UserInfoVo queryUserByWeixinOpenId(String weixin_open_id) {
        UserInfoVo userBackVo = new UserInfoVo();
        UserInfo userBackInfo = userCenterDao.queryUserByWeixinOpenId(weixin_open_id);
        if (userBackInfo != null) {
            BeanUtils.copyProperties(userBackInfo, userBackVo);
        }
        return userBackVo;
    }

    /**
     * 
     * @param userid
     * @return
     */
    public UserInfo queryUserInfoByPhoneNum(String userPhone) {
        UserInfo user = userCenterDao.queryUserInfoByPhoneNum(userPhone);
        return user;
    }

    /**
     * @description 查询用户积分
     * @version
     * @title
     * @author
     * @param userCreditVo
     * @return
     */
    public UserCreditVo queryUserCredit(UserCreditVo userCreditVo) {
        UserCreditInfo userCreditInfo = new UserCreditInfo();
        BeanUtils.copyProperties(userCreditVo, userCreditInfo);
        UserCreditInfo userCreditBackInfo = userCenterDao.queryUserCredit(userCreditInfo);
        UserCreditVo userCreditBackVo = new UserCreditVo();
        BeanUtils.copyProperties(userCreditBackInfo, userCreditBackVo);
        return userCreditBackVo;
    }

    /**
     * @description 新增用户积分记录
     * @version
     * @title
     * @author
     * @param userCreditVo
     * @return
     */
    public void addUserCredit(UserCreditVo userCreditVo) {
        UserCreditInfo userCreditInfo = new UserCreditInfo();
        BeanUtils.copyProperties(userCreditVo, userCreditInfo);
        userCenterDao.addUserCredit(userCreditInfo);
    }

    /**
     * @description 修改用户积分记录
     * @version
     * @title
     * @author
     * @param userCreditVo
     * @return
     */
    public void updateUserCredit(UserCreditVo userCreditVo) {
        UserCreditInfo userCreditInfo = new UserCreditInfo();
        BeanUtils.copyProperties(userCreditVo, userCreditInfo);
        userCenterDao.updateUserCredit(userCreditInfo);
    }

    /**
     * @description 用户积分明细
     * @version
     * @title
     * @author
     * @param userCreditDetailVo
     * @return
     */
    public List<UserCreditDetailVo> queryUserCreditDetail(UserCreditDetailVo userCreditDetailVo) {
        UserCreditDetailInfo userCreditDetailInfo = new UserCreditDetailInfo();
        BeanUtils.copyProperties(userCreditDetailVo, userCreditDetailInfo);
        List<UserCreditDetailInfo> userCreditDetailInfoList = userCenterDao.queryUserCreditDetail(userCreditDetailInfo);
        List<UserCreditDetailVo> userCreditDetailVoList = new ArrayList<UserCreditDetailVo>();
        UserCreditDetailVo backVo;
        for (int i = 0; i < userCreditDetailInfoList.size(); i++) {
            backVo = new UserCreditDetailVo();
            BeanUtils.copyProperties(userCreditDetailInfoList.get(i), backVo);
            userCreditDetailVoList.add(backVo);
        }
        return userCreditDetailVoList;
    }

    /**
     * @description 增加用户积分明细记录
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.user.usercenter.service.UserCenterService#queryUserCreditDetail(com.p2p.webapp.user.usercenter.vo.UserCreditDetailVo)
     * @param userCreditDetailVo
     * @return
     */
    public void addUserCreditDetail(UserCreditDetailVo userCreditDetailVo) {
        UserCreditDetailInfo userCreditDetailInfo = new UserCreditDetailInfo();
        BeanUtils.copyProperties(userCreditDetailVo, userCreditDetailInfo);
        userCenterDao.addUserCreditDetail(userCreditDetailInfo);
    }

    /**
     * @description 通过enrollId查询当次积分变动
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.user.usercenter.service.UserCenterService#queryCreditDetailByEnrollId(com.p2p.webapp.user.usercenter.vo.UserCreditDetailVo)
     * @param enroll_id
     * @return
     */
    public UserCreditDetailVo queryCreditDetailByEnrollId(String enroll_id) {
        UserCreditDetailInfo creditDetailBackInfo = userCenterDao.queryCreditDetailByEnrollId(enroll_id);
        UserCreditDetailVo creditDetailBackVo = new UserCreditDetailVo();
        if(creditDetailBackInfo!=null){
            BeanUtils.copyProperties(creditDetailBackInfo, creditDetailBackVo);
        }        
        return creditDetailBackVo;
    }

    /**
     * @description 通过enrollId修改当次积分变动
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.user.usercenter.service.UserCenterService#updateCreditDetailByEnrollId(com.p2p.webapp.user.usercenter.vo.UserCreditDetailVo)
     * @param userCreditDetailVo
     * @return
     */
    public String updateCreditDetailByEnrollId(UserCreditDetailVo userCreditDetailVo) {
        try {
            UserCreditDetailInfo userCreditDetailInfo = new UserCreditDetailInfo();
            BeanUtils.copyProperties(userCreditDetailVo, userCreditDetailInfo);
            userCenterDao.updateCreditDetailByEnrollId(userCreditDetailInfo);
            return "0";
        } catch (Exception e) {
            return "-1";
        }
    }

    /*
     * 通过enrollId删除当次积分变动
     */
    public String delCreditDetailByEnrollId(String enroll_id) {
        try {
            userCenterDao.delCreditDetailByEnrollId(enroll_id);
            return "0";
        } catch (Exception e) {
            return "-1";
        }
    }

}
