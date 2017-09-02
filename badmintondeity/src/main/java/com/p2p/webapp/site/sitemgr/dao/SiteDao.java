package com.p2p.webapp.site.sitemgr.dao;

import java.util.List;

import com.p2p.webapp.site.sitemgr.entity.SiteInfo;

public interface SiteDao {
    /*
     * 查询地点列表
     */
    public List<SiteInfo> querySite(SiteInfo siteInfo);

    /*
     * 查询地点详细信息
     */
    public SiteInfo querySiteDetailInfo(SiteInfo siteInfo);
    
    /*
     * 通过地点名查询
     */
    public SiteInfo querySiteByName(SiteInfo siteInfo);

    /*
     * 新增地点信息
     */
    public void addSite(SiteInfo siteInfo);

    /*
     * 修改地点信息
     */
    public void updateSite(SiteInfo siteInfo);

    /*
     * 删除地点信息
     */
    public void deleteSite(SiteInfo siteInfo);

}
