package com.p2p.webapp.enroll.enrollmgr.dao;

import java.util.List;

import com.p2p.webapp.enroll.enrollmgr.entity.EnrollInfo;

public interface EnrollDao {
    /*
     * 查询全部报名人员
     */
    public List<EnrollInfo> queryAllEnroll(EnrollInfo enrollInfo);

    /*
     * 查询报名详细信息
     */
    public EnrollInfo queryEnrollDetailInfo(EnrollInfo enrollInfo);

    /*
     * 查询我的报名
     */
    public List<EnrollInfo> queryMyEnroll(EnrollInfo enrollInfo);

    /*
     * 查询下一个报名流水id
     */
    public String queryEnrollId();

    /*
     * 新增报名
     */
    public void addEnroll(EnrollInfo enrollInfo);

    /*
     * 修改报名
     */
    public void updateEnroll(EnrollInfo enrollInfo);

    /*
     * 取消报名
     */
    public void cancelEnroll(EnrollInfo enrollInfo);

    /*
     * 支付报名费用
     */
    public void payEnroll(EnrollInfo enrollInfo);

    /*
     * 查询报名人数
     */
    public String queryEnrollCount(EnrollInfo enrollInfo);

    /*
     * 查询报名费用总和
     */
    public String querySumMoney(EnrollInfo enrollInfo);

    /*
     * 签到
     */
    public void signIn(String enroll_id);

}
