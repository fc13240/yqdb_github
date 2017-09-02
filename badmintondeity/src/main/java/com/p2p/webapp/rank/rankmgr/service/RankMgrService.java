package com.p2p.webapp.rank.rankmgr.service;

import java.util.List;

import com.p2p.webapp.city.citymgr.vo.AreaVo;
import com.p2p.webapp.city.citymgr.vo.ProvinceVo;
import com.p2p.webapp.city.citymgr.vo.CityVo;

/**
 * @description 城市查询service
 * @author
 * @date 2015-10-28 下午4:47:43
 */
public interface RankMgrService {

    /**
     * @description
     * @version
     * @title
     * @author 
     * @param provinceVo
     * @return 
    */
    public List<ProvinceVo> queryProvince(ProvinceVo provinceVo);

    /**
     * @description
     * @version
     * @title
     * @author 
     * @param cityVo
     * @return 
    */
    public List<CityVo> queryCity(CityVo cityVo);
    
    /**
     * @description 根据城市名称查询城市
     * @version
     * @title
     * @author 
     * @param cityName
     * @return 
    */
    public CityVo queryCityDetail(String cityName);

    /**
     * @description 通过city_code查询区县
     * @version
     * @title
     * @author 
     * @param areaVo
     * @return 
    */
    public List<AreaVo> queryArea(AreaVo areaVo);

}
