package com.p2p.webapp.city.citymgr.dao;

import java.util.List;

import com.p2p.webapp.city.citymgr.entity.AreaInfo;
import com.p2p.webapp.city.citymgr.entity.CityInfo;
import com.p2p.webapp.city.citymgr.entity.ProvinceInfo;

public interface CityDao {

    public List<ProvinceInfo> queryProvince(ProvinceInfo provinceInfo);

    public List<CityInfo> queryCity(CityInfo cityInfo);
    
    public CityInfo queryCityDetail(String cityName);

    public List<AreaInfo> queryArea(AreaInfo areaInfo);
}
