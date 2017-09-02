package com.p2p.webapp.site.sitemgr.service;

import java.util.List;

import com.p2p.webapp.site.sitemgr.vo.SiteVo;

/** 
 * @description 地点管理service
 * @author 
 * @date 2015-10-28 下午4:47:43  
*/
public interface SiteMgrService {

    /**
     * @description 查询全部地点信息
     * @version
     * @title
     * @author 
     * @return 
    */
    public List<SiteVo> querySite(SiteVo siteVo);

    /**
     * @description 查询地点详细信息
     * @version
     * @title
     * @author 
     * @return 
    */
    public SiteVo querySiteDetailInfo(SiteVo siteVo);
    
    /**
     * @description 通过地点名查询
     * @version
     * @title
     * @author 
     * @param siteVo
     * @return 
    */
    public SiteVo querySiteByName(SiteVo siteVo);
    
    /**
     * @description 新增地点信息
     * @version
     * @title
     * @author 
     * @return 
    */
    public String addSite(SiteVo siteVo);

    /**
     * @description 修改地点信息
     * @version
     * @title
     * @author 
     * @return 
    */
    public String updateSite(SiteVo siteVo);
    
    /**
     * @description 删除地点信息
     * @version
     * @title
     * @author 
     * @return 
    */
    public String deleteSite(SiteVo siteVo);


}
