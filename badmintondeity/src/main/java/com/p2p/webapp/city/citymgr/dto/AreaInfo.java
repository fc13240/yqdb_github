package com.p2p.webapp.city.citymgr.dto;

/**
 * @description 区县Vo
 * @author
 * @String 2015-10-28 下午4:10:52
 */
public class AreaInfo {
    /**
     * @Fields area_id : 主键id
     */
    private String area_id;

    /**
     * @Fields area_name : 区县名称
     */
    private String area_name;

    /**
     * @Fields area_code : 区县号
     */
    private String area_code;

    /**
     * @Fields city_code : 城市号
     */
    private String city_code;

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

}
