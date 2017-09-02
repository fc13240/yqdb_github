package com.p2p.webapp.settle.settlemgr.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.activity.activitymgr.vo.ActivityVo;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.settle.settlemgr.service.SettleMgrService;
import com.p2p.webapp.settle.settlemgr.vo.SettleVo;
/**
 * @description 比赛结算信息管理
 * @author
 * @date 2015-10-28 下午4:21:01
 */
public class SettleMgrAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(SettleMgrAction.class);
    private SettleMgrService settleMgrService;
    private List<SettleVo> settledList;
    private List<ActivityVo> notSettledList;
    private SettleVo settleVo;

    /**
     * @description 查询比赛详细结算信息
     * @version
     * @title
     * @author
     * @return 比赛详细信息页面
     */
    public String queryAllSettle() {
        logger.debug("查询比赛结算页面");
        SettleVo querySettleVo = new SettleVo();
        //查询已结算比赛
        querySettleVo.setUser_id(session.get("userid").toString());
        settledList = settleMgrService.querySettledActivity(querySettleVo);
        
        //查询未结算比赛
        ActivityVo queryActivityVo = new ActivityVo();
        queryActivityVo.setActivity_status(Constant.ACTIVITY_STATUS_UNSETTLE);
        queryActivityVo.setUser_id(session.get("userid").toString());
        notSettledList = settleMgrService.queryNotsettledActivity(queryActivityVo);
        return "settleInit";
    }
    
    /**
     * @description 查询比赛详细结算信息
     * @version
     * @title
     * @author
     * @return 比赛详细信息页面
     */
    public void querySettleDetailInfo() {
        logger.debug("查询比赛详细结算信息");
        settleVo = settleMgrService.querySettleDetailInfo(settleVo);
    }

    /**
     * @description 添加新比赛结算信息
     * @version
     * @title
     * @author
     * @return
     */
    public void addSettle() {
        settleMgrService.addSettle(settleVo);
    }

    /**
     * @description 修改比赛结算信息
     * @version
     * @title
     * @author
     * @return
     */
    public void updateSettle() {
        settleMgrService.updateSettle(settleVo);
    }

    public SettleMgrService getSettleMgrService() {
        return settleMgrService;
    }

    public void setSettleMgrService(SettleMgrService settleMgrService) {
        this.settleMgrService = settleMgrService;
    }

    public SettleVo getSettleVo() {
        return settleVo;
    }

    public void setSettleVo(SettleVo settleVo) {
        this.settleVo = settleVo;
    }

    public List<SettleVo> getSettledList() {
        return settledList;
    }

    public void setSettledList(List<SettleVo> settledList) {
        this.settledList = settledList;
    }

    public List<ActivityVo> getNotSettledList() {
        return notSettledList;
    }

    public void setNotSettledList(List<ActivityVo> notSettledList) {
        this.notSettledList = notSettledList;
    }

}
