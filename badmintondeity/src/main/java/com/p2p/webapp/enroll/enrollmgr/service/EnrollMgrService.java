package com.p2p.webapp.enroll.enrollmgr.service;

import java.util.List;

import com.p2p.webapp.enroll.enrollmgr.vo.EnrollVo;

/** 
 * @description 报名管理service
 * @author 
 * @date 2015-10-28 下午4:47:43  
*/
public interface EnrollMgrService {

    /**
     * @description 查询全部报名
     * @version
     * @title
     * @author 
     * @return 
    */
    public List<EnrollVo> queryAllEnroll(EnrollVo enrollVo);

    /**
     * @description 查询报名详细信息
     * @version
     * @title
     * @author 
     * @return 
    */
    public EnrollVo queryEnrollDetailInfo(EnrollVo enrollVo);
    
    /**
     * @description 查询我的报名
     * @version
     * @title
     * @author 
     * @return 
    */
    public List<EnrollVo> queryMyEnroll(EnrollVo enrollVo);
    
    /**
     * @description 新增报名
     * @version
     * @title
     * @author 
     * @return 
    */
    public String addEnroll(EnrollVo enrollVo);

    /**
     * @description 修改报名
     * @version
     * @title
     * @author 
     * @return 
    */
    public String updateEnroll(EnrollVo enrollVo);
    
    /**
     * @description 取消报名
     * @version
     * @title
     * @author 
     * @return 
    */
    public String cancelEnroll(EnrollVo enrollVo);
    
    /**
     * @description 支付报名费用
     * @version
     * @title
     * @author 
     * @return 
    */
    public String payEnroll(EnrollVo enrollVo);
    
    /**
     * @description 查询报名人数
     * @version
     * @title
     * @author 
     * @return 
    */
    public String queryEnrollCount(EnrollVo enrollVo);
    
    /**
     * @description 查询报名费用总和
     * @version
     * @title
     * @author 
     * @return 
    */
    public String querySumMoney(EnrollVo enrollVo);

    /**
     * @description 签到
     * @version
     * @title
     * @author 
     * @param enrollInfo 
    */
    public void signIn(String enroll_id);
}
