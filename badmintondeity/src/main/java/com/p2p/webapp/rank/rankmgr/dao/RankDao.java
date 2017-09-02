package com.p2p.webapp.rank.rankmgr.dao;

import java.util.List;

import com.p2p.webapp.city.citymgr.entity.AreaInfo;
import com.p2p.webapp.city.citymgr.entity.CityInfo;
import com.p2p.webapp.city.citymgr.entity.ProvinceInfo;

public interface RankDao {

    public List<ProvinceInfo> queryProvince(ProvinceInfo provinceInfo);

    public List<CityInfo> queryCity(CityInfo cityInfo);
    
    public CityInfo queryCityDetail(String cityName);

    public List<AreaInfo> queryArea(AreaInfo areaInfo);
}
