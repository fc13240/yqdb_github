package com.p2p.webapp.city.citymgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.p2p.webapp.city.citymgr.dao.CityDao;
import com.p2p.webapp.city.citymgr.entity.AreaInfo;
import com.p2p.webapp.city.citymgr.entity.CityInfo;
import com.p2p.webapp.city.citymgr.entity.ProvinceInfo;
import com.p2p.webapp.city.citymgr.service.CityMgrService;
import com.p2p.webapp.city.citymgr.vo.AreaVo;
import com.p2p.webapp.city.citymgr.vo.CityVo;
import com.p2p.webapp.city.citymgr.vo.ProvinceVo;

public class CityMgrServiceImpl implements CityMgrService {
    private CityDao cityDao;

    /**
     * @description 查询省市
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.city.citymgr.service.CityMgrService#queryProvince(com.p2p.webapp.city.citymgr.vo.ProvinceVo)
     * @param provinceVo
     * @return
     */
    public List<ProvinceVo> queryProvince(ProvinceVo provinceVo) {
        ProvinceInfo provinceInfo = new ProvinceInfo();
        BeanUtils.copyProperties(provinceVo, provinceInfo);
        List<ProvinceInfo> provinceInfoList = cityDao.queryProvince(provinceInfo);
        List<ProvinceVo> provinceVoList = new ArrayList<ProvinceVo>();
        ProvinceVo backVo;
        for (int i = 0; i < provinceInfoList.size(); i++) {
            backVo = new ProvinceVo();
            BeanUtils.copyProperties(provinceInfoList.get(i), backVo);
            provinceVoList.add(backVo);
        }
        return provinceVoList;
    }

    /**
     * @description 查询城市
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.city.citymgr.service.CityMgrService#queryCity(com.p2p.webapp.city.citymgr.vo.CityVo)
     * @param cityVo
     * @return
     */
    public List<CityVo> queryCity(CityVo cityVo) {
        CityInfo cityInfo = new CityInfo();
        BeanUtils.copyProperties(cityVo, cityInfo);
        List<CityInfo> cityInfoList = cityDao.queryCity(cityInfo);
        List<CityVo> cityVoList = new ArrayList<CityVo>();
        CityVo backVo;
        for (int i = 0; i < cityInfoList.size(); i++) {
            backVo = new CityVo();
            BeanUtils.copyProperties(cityInfoList.get(i), backVo);
            cityVoList.add(backVo);
        }
        return cityVoList;
    }
    
    /**
     * @description 根据城市名称查询城市
     * @version
     * @title
     * @author 
     * @see com.p2p.webapp.city.citymgr.service.CityMgrService#queryCityDetail(com.p2p.webapp.city.citymgr.vo.CityVo)
     * @param cityVo
     * @return 
    */
    public CityVo queryCityDetail(String city_name){
        CityVo citybackVo = new CityVo();
        //jiangxian
        city_name =  "北京";
        CityInfo citybackInfo = cityDao.queryCityDetail(city_name);
        BeanUtils.copyProperties(citybackInfo, citybackVo);
        return citybackVo;
    }
    /**
     * @description 查询区县
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.city.citymgr.service.CityMgrService#queryArea(com.p2p.webapp.city.citymgr.vo.AreaVo)
     * @param areaVo
     * @return
     */
    public List<AreaVo> queryArea(AreaVo areaVo) {
        AreaInfo areaInfo = new AreaInfo();
        BeanUtils.copyProperties(areaVo, areaInfo);
        List<AreaInfo> backInfoList = cityDao.queryArea(areaInfo);
        List<AreaVo> areaVoList = new ArrayList<AreaVo>();
        AreaVo backVo;
        for (int i = 0; i < backInfoList.size(); i++) {
            backVo = new AreaVo();
            BeanUtils.copyProperties(backInfoList.get(i), backVo);
            areaVoList.add(backVo);
        }
        return areaVoList;
    }

    public CityDao getCityDao() {
        return cityDao;
    }

    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

}
