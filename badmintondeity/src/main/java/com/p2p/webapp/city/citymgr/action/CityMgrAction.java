package com.p2p.webapp.city.citymgr.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.city.citymgr.service.CityMgrService;
import com.p2p.webapp.city.citymgr.vo.AreaVo;
import com.p2p.webapp.city.citymgr.vo.CityVo;
import com.p2p.webapp.city.citymgr.vo.ProvinceVo;
import com.p2p.webapp.common.base.BaseAction;

/**
 * @description 群组管理
 * @author
 * @date 2015-10-28 下午4:21:01
 */
public class CityMgrAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private CityMgrService cityMgrService;
    private List<ProvinceVo> provinceList = new ArrayList<ProvinceVo>();
    private List<CityVo> cityList = new ArrayList<CityVo>();
    private List<AreaVo> areaList = new ArrayList<AreaVo>();
    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(CityMgrAction.class);
    
    /**
     * @description 城市列表页面
     * @version
     * @title
     * @author 
     * @return 城市列表页面
    */
    public String chooseCity(){
        return "cityIndexPage";
    }
    
    /**
     * @description 获取位置
     * @version
     * @title
     * @author 
     * @return 
    */
    public String getLocation(){
        return "location";
    }
    /**
     * @description 查询省市
     * @version
     * @title
     * @author
     * @return provinceList
     */
    public void queryProvince() {
        ProvinceVo provinceVo = new ProvinceVo();
        provinceList = cityMgrService.queryProvince(provinceVo);
        outJsonCon(provinceList);
        
    }

    /**
     * @description 查询城市
     * @version
     * @title
     * @author
     * @return cityList
     */
    public void queryCity() {
        String province_code = request.getParameter("province_code");
        CityVo cityVo = new CityVo();
        cityVo.setProvince_code(province_code);
        cityList = cityMgrService.queryCity(cityVo);
        outJsonCon(cityList);
    }

    /**
     * @description 查询区县
     * @version
     * @title
     * @author
     * @return provinceList
     */
    public String queryArea() {
        String city_code = request.getParameter("city_code");
        AreaVo areaVo = new AreaVo();
        areaVo.setCity_code(city_code);
        areaList = cityMgrService.queryArea(areaVo);
        return "areaInfo";
    }
    
    /**
     * @description 手动设定城市
     * @version
     * @title
     * @author  
    */
    public void setCity() {
        try {
            String cityName = new String(request.getParameter("cityName").getBytes("iso-8859-1"), "utf-8");
            request.getSession().setAttribute("city", cityName);
            outJson("0");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public CityMgrService getCityMgrService() {
        return cityMgrService;
    }

    public void setCityMgrService(CityMgrService cityMgrService) {
        this.cityMgrService = cityMgrService;
    }

    public List<ProvinceVo> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceVo> provinceList) {
        this.provinceList = provinceList;
    }

    public List<CityVo> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityVo> cityList) {
        this.cityList = cityList;
    }

    public List<AreaVo> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaVo> areaList) {
        this.areaList = areaList;
    }

}
