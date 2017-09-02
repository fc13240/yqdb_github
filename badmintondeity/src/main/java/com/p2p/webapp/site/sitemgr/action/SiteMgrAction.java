package com.p2p.webapp.site.sitemgr.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.activity.activitymgr.action.ActivityMgrAction;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.site.sitemgr.service.SiteMgrService;
import com.p2p.webapp.site.sitemgr.vo.SiteVo;

/**
 * @description 地点管理
 * @author
 * @date 2015-10-28 下午4:21:01
 */
public class SiteMgrAction extends BaseAction {
    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(ActivityMgrAction.class);
    private static final long serialVersionUID = 1L;
    private SiteMgrService siteMgrService;
    private SiteVo siteVo;
    private List<SiteVo> siteVoList;

    /**
     * @description 查询地点列表
     * @version
     * @title
     * @author
     * @return
     */
    public String querySite() {
        siteVo = new SiteVo();
        String city;
        String area;
        try {
            city = "北京";//new String(request.getParameter("city").getBytes("iso-8859-1"), "utf-8");
            area = new String(request.getParameter("area").getBytes("iso-8859-1"), "utf-8");
            siteVo.setSite_addr_city(city);
            siteVo.setSite_addr_district(area);
            siteVoList = siteMgrService.querySite(siteVo);            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "siteList";
    }

    /**
     * @description 查询地点详细信息
     * @version
     * @title
     * @author
     * @return
     */
    public String querySiteDetailInfo() {
        siteVo = siteMgrService.querySiteDetailInfo(siteVo);
        return "siteDetailInfo";
    }

    /**
     * @description 新增地点
     * @version
     * @title
     * @author
     * @return
     */
    public String addSiteInit() {        
        return "addSiteInit";
    }
    
    /**
     * @description 新增地点
     * @version
     * @title
     * @author
     * @return
     */
    public void addSite() {
        Map<String,String> map = new HashMap<String,String>();
        SiteVo backVo = siteMgrService.querySiteByName(siteVo);
        if(backVo.getSite_addr_id()==null){
            //新增地点
            try{
                siteMgrService.addSite(siteVo);            
                map.put("result", "新地点添加成功");
                outJson(map);
            }catch(Exception e){
                logger.debug(e.toString());
                map.put("result", "新地点添加失败");
                outJson(map);
            }     
        }else{
            map.put("result", "该地点信息已收录");
            outJson(map);
        }
    }

    /**
     * @description 修改地点信息
     * @version
     * @title
     * @author
     * @return
     */
    public String updateSite() {
        siteMgrService.updateSite(siteVo);
        return "siteDetailInfo";
    }

    /**
     * @description 删除地点
     * @version
     * @title
     * @author
     * @return
     */
    public String deleteSite() {
        siteMgrService.deleteSite(siteVo);
        return "siteDetailInfo";
    }
    
    public String querySiteList() {        
        return "siteList";
    }

    public SiteMgrService getSiteMgrService() {
        return siteMgrService;
    }

    public void setSiteMgrService(SiteMgrService siteMgrService) {
        this.siteMgrService = siteMgrService;
    }

    public SiteVo getSiteVo() {
        return siteVo;
    }

    public void setSiteVo(SiteVo siteVo) {
        this.siteVo = siteVo;
    }

    public List<SiteVo> getSiteVoList() {
        return siteVoList;
    }

    public void setSiteVoList(List<SiteVo> siteVoList) {
        this.siteVoList = siteVoList;
    }
}
