package com.p2p.webapp.rank.rankmgr.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.common.base.BaseAction;

/**
 * @description 排名管理
 * @author
 * @date 2015-10-28 下午4:21:01
 */
public class RankMgrAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(RankMgrAction.class);
    
    /**
     * @description 城市列表页面
     * @version
     * @title
     * @author 
     * @return 城市列表页面
    */
    public String rankListInit(){
        return "rankListIndexPage";
    }
 

}
