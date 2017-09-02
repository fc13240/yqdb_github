package com.p2p.webapp.site.sitemgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.p2p.webapp.site.sitemgr.dao.SiteDao;
import com.p2p.webapp.site.sitemgr.entity.SiteInfo;
import com.p2p.webapp.site.sitemgr.service.SiteMgrService;
import com.p2p.webapp.site.sitemgr.vo.SiteVo;

public class SiteMgrServiceImpl implements SiteMgrService {
    private SiteDao siteDao;

    /**
     * @description 查询全部地点信息
     * @version
     * @title
     * @author
     * @return List<SiteVo>
     */
    public List<SiteVo> querySite(SiteVo siteVo) {
        SiteInfo siteInfo = new SiteInfo();
        BeanUtils.copyProperties(siteVo, siteInfo);
        List<SiteInfo> siteInfoList = siteDao.querySite(siteInfo);
        List<SiteVo> siteVoList = new ArrayList<SiteVo>();
        SiteVo backVo;
        for (int i = 0; i < siteInfoList.size(); i++) {
            backVo = new SiteVo();
            BeanUtils.copyProperties(siteInfoList.get(i), backVo);
            siteVoList.add(backVo);
        }
        return siteVoList;
    }

    /**
     * @description 查询地点信息信息
     * @version
     * @title
     * @author
     * @return SiteVo
     */
    public SiteVo querySiteDetailInfo(SiteVo siteVo) {
        SiteInfo siteInfo = new SiteInfo();
        BeanUtils.copyProperties(siteVo, siteInfo);
        SiteInfo siteBackInfo = siteDao.querySiteDetailInfo(siteInfo);
        SiteVo siteBackVo = new SiteVo();
        if(siteBackInfo !=null){
            BeanUtils.copyProperties(siteBackInfo, siteBackVo);
        }        
        return siteBackVo;
    }
    
    /**
     * @description 通过地点名查询
     * @version
     * @title
     * @author
     * @return SiteVo
     */
    public SiteVo querySiteByName(SiteVo siteVo) {
        SiteInfo siteInfo = new SiteInfo();
        BeanUtils.copyProperties(siteVo, siteInfo);
        SiteInfo siteBackInfo = siteDao.querySiteByName(siteInfo);
        SiteVo siteBackVo = new SiteVo();
        if(siteBackInfo !=null){
            BeanUtils.copyProperties(siteBackInfo, siteBackVo);
        }        
        return siteBackVo;
    }

    /**
     * @description 新增地点信息
     * @version
     * @title
     * @author
     * @return String
     */
    public String addSite(SiteVo siteVo) {
        SiteInfo siteInfo = new SiteInfo();
        BeanUtils.copyProperties(siteVo, siteInfo);
        siteDao.addSite(siteInfo);
        return "0";
    }

    /**
     * @description 修改地点信息信息
     * @version
     * @title
     * @author
     * @return String
     */
    public String updateSite(SiteVo siteVo) {
        SiteInfo siteInfo = new SiteInfo();
        BeanUtils.copyProperties(siteVo, siteInfo);
        siteDao.updateSite(siteInfo);
        return "0";
    }

    /**
     * @description 删除地点信息
     * @version
     * @title
     * @author
     * @return String
     */
    public String deleteSite(SiteVo siteVo) {
        SiteInfo siteInfo = new SiteInfo();
        BeanUtils.copyProperties(siteVo, siteInfo);
        siteDao.deleteSite(siteInfo);
        return "0";
    }


    public SiteDao getSiteDao() {
        return siteDao;
    }

    public void setSiteDao(SiteDao siteDao) {
        this.siteDao = siteDao;
    }

}
