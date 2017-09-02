package com.p2p.webapp.settle.settlemgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.p2p.webapp.activity.activitymgr.entity.ActivityInfo;
import com.p2p.webapp.activity.activitymgr.vo.ActivityVo;
import com.p2p.webapp.settle.settlemgr.dao.SettleDao;
import com.p2p.webapp.settle.settlemgr.entity.SettleInfo;
import com.p2p.webapp.settle.settlemgr.service.SettleMgrService;
import com.p2p.webapp.settle.settlemgr.vo.SettleVo;

public class SettleMgrServiceImpl implements SettleMgrService {
    private SettleDao settleDao;

    public List<SettleVo> querySettledActivity(SettleVo settleVo){
        SettleInfo settleInfo = new SettleInfo();
        BeanUtils.copyProperties(settleVo, settleInfo);
        List<SettleInfo> settleInfoList = settleDao.querySettledActivity(settleInfo);
        List<SettleVo> settleVoList = new ArrayList<SettleVo>();
        SettleVo backVo;
        for (int i = 0; i < settleInfoList.size(); i++) {
            backVo = new SettleVo();
            BeanUtils.copyProperties(settleInfoList.get(i), backVo);
            settleVoList.add(backVo);
        }
        return settleVoList;
    }
    

    /**
     * @description 查询未结算的比赛
     * @version
     * @title
     * @author
     * @param activityInfo
     * @return
     */
    public List<ActivityVo> queryNotsettledActivity(ActivityVo activityVo){
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activityVo, activityInfo);
        List<ActivityInfo> activityInfoList = settleDao.queryNotsettledActivity(activityInfo);
        List<ActivityVo> activityVoList = new ArrayList<ActivityVo>();
        ActivityVo backVo;
        for (int i = 0; i < activityInfoList.size(); i++) {
            backVo = new ActivityVo();
            BeanUtils.copyProperties(activityInfoList.get(i), backVo);
            activityVoList.add(backVo);
        }
        return activityVoList;
    }
    
    /**
     * @description 查询比赛结算信息
     * @version
     * @title
     * @author
     * @return SettleVo
     */
    public SettleVo querySettleDetailInfo(SettleVo settleVo) {
        SettleInfo settleInfo = new SettleInfo();
        BeanUtils.copyProperties(settleVo, settleInfo);
        SettleInfo settleBackInfo = settleDao.querySettleDetailInfo(settleInfo);
        SettleVo settleBackVo = new SettleVo();
        if(settleBackInfo !=null){
            BeanUtils.copyProperties(settleBackInfo, settleBackVo);
        }        
        return settleBackVo;
    }

    /**
     * @description 新增比赛结算信息
     * @version
     * @title
     * @author
     * @return String
     */
    public String addSettle(SettleVo settleVo) {
        String setttleId = settleDao.querySettleId();
        settleVo.setSettle_id(setttleId);
        SettleInfo settleInfo = new SettleInfo();
        BeanUtils.copyProperties(settleVo, settleInfo);        
        settleDao.addSettle(settleInfo);        
        return setttleId;
    }

    /**
     * @description 修改比赛结算信息
     * @version
     * @title
     * @author
     * @return String
     */
    public String updateSettle(SettleVo settleVo) {
        SettleInfo settleInfo = new SettleInfo();
        BeanUtils.copyProperties(settleVo, settleInfo);        
        settleDao.updateSettle(settleInfo);
        return "0";
    }

    public SettleDao getSettleDao() {
        return settleDao;
    }

    public void setSettleDao(SettleDao settleDao) {
        this.settleDao = settleDao;
    }
}
