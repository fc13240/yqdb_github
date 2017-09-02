package com.p2p.webapp.enroll.enrollmgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.p2p.webapp.enroll.enrollmgr.dao.EnrollDao;
import com.p2p.webapp.enroll.enrollmgr.entity.EnrollInfo;
import com.p2p.webapp.enroll.enrollmgr.service.EnrollMgrService;
import com.p2p.webapp.enroll.enrollmgr.vo.EnrollVo;

public class EnrollMgrServiceImpl implements EnrollMgrService {
    private EnrollDao enrollDao;

    /**
     * @description 查询全部报名人员
     * @version
     * @title
     * @author
     * @return List<EnrollVo>
     */
    public List<EnrollVo> queryAllEnroll(EnrollVo enrollVo) {
        EnrollInfo enrollInfo = new EnrollInfo();
        BeanUtils.copyProperties(enrollVo, enrollInfo);
        List<EnrollInfo> enrollInfoList = enrollDao.queryAllEnroll(enrollInfo);
        List<EnrollVo> enrollVoList = new ArrayList<EnrollVo>();
        EnrollVo backVo;
        for (int i = 0; i < enrollInfoList.size(); i++) {
            backVo = new EnrollVo();
            BeanUtils.copyProperties(enrollInfoList.get(i), backVo);
            enrollVoList.add(backVo);
        }
        return enrollVoList;
    }

    /**
     * @description 查询报名信息
     * @version
     * @title
     * @author
     * @return EnrollVo
     */
    public EnrollVo queryEnrollDetailInfo(EnrollVo enrollVo) {
        EnrollInfo enrollInfo = new EnrollInfo();
        BeanUtils.copyProperties(enrollVo, enrollInfo);
        EnrollInfo enrollBackInfo = enrollDao.queryEnrollDetailInfo(enrollInfo);
        EnrollVo enrollBackVo = new EnrollVo();
        if(enrollBackInfo !=null){
            BeanUtils.copyProperties(enrollBackInfo, enrollBackVo);
        }        
        return enrollBackVo;
    }
    
    /**
     * @description 查询我的报名
     * @version
     * @title
     * @author 
     * @return 
    */
    public List<EnrollVo> queryMyEnroll(EnrollVo enrollVo){
        EnrollInfo enrollInfo = new EnrollInfo();
        BeanUtils.copyProperties(enrollVo, enrollInfo);
        List<EnrollInfo> enrollInfoList = enrollDao.queryMyEnroll(enrollInfo);
        List<EnrollVo> enrollVoList = new ArrayList<EnrollVo>();
        EnrollVo backVo;
        for (int i = 0; i < enrollInfoList.size(); i++) {
            backVo = new EnrollVo();
            BeanUtils.copyProperties(enrollInfoList.get(i), backVo);
            enrollVoList.add(backVo);
        }
        return enrollVoList;
    }

    /**
     * @description 新增报名
     * @version
     * @title
     * @author
     * @return String
     */
    public String addEnroll(EnrollVo enrollVo) {
        String enroll_id = enrollDao.queryEnrollId();
        enrollVo.setEnroll_id(enroll_id);
        EnrollInfo enrollInfo = new EnrollInfo();
        BeanUtils.copyProperties(enrollVo, enrollInfo);
        enrollDao.addEnroll(enrollInfo);
        return enroll_id;
    }

    /**
     * @description 修改报名信息
     * @version
     * @title
     * @author
     * @return String
     */
    public String updateEnroll(EnrollVo enrollVo) {
        EnrollInfo enrollInfo = new EnrollInfo();
        BeanUtils.copyProperties(enrollVo, enrollInfo);
        enrollDao.updateEnroll(enrollInfo);
        return "0";
    }

    /**
     * @description 取消报名
     * @version
     * @title
     * @author
     * @return String
     */
    public String cancelEnroll(EnrollVo enrollVo) {
        try{
            EnrollInfo enrollInfo = new EnrollInfo();
            BeanUtils.copyProperties(enrollVo, enrollInfo);
            enrollDao.cancelEnroll(enrollInfo);
            return "0";
        }catch(Exception e){
            return "-1";
        }
        
    }
    
    /**
     * @description 支付报名费用
     * @version
     * @title
     * @author
     * @return String
     */
    public String payEnroll(EnrollVo enrollVo) {
        EnrollInfo enrollInfo = new EnrollInfo();
        BeanUtils.copyProperties(enrollVo, enrollInfo);
        enrollDao.payEnroll(enrollInfo);
        return "0";
    }

    /**
     * @description 查询报名人数
     * @version
     * @title
     * @author
     * @return
     */
    public String queryEnrollCount(EnrollVo enrollVo) {
        EnrollInfo enrollInfo = new EnrollInfo();
        BeanUtils.copyProperties(enrollVo, enrollInfo);
        return enrollDao.queryEnrollCount(enrollInfo);
    }
    
    /**
     * @description 查询报名费用总和
     * @version
     * @title
     * @author
     * @return
     */
    public String querySumMoney(EnrollVo enrollVo) {
        EnrollInfo enrollInfo = new EnrollInfo();
        BeanUtils.copyProperties(enrollVo, enrollInfo);
        return enrollDao.querySumMoney(enrollInfo);
    }
    
    /**
     * @description 签到
     * @version
     * @title
     * @author 
     * @param enrollInfo 
    */
    public void signIn(String enroll_id){
        enrollDao.signIn(enroll_id);
    }

    public EnrollDao getEnrollDao() {
        return enrollDao;
    }

    public void setEnrollDao(EnrollDao enrollDao) {
        this.enrollDao = enrollDao;
    }

}
