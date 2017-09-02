package com.p2p.webapp.city.citymgr.dto;

/**
 * @description 城市Vo
 * @author
 * @String 2015-10-28 下午4:10:52
 */
public class CityInfo {
    /**
     * @Fields group_id : 主键id
     */
    private String city_id;

    /**
     * @Fields city_code : 城市号
     */
    private String city_code;

    /**
     * @Fields city_name : 城市名称
     */
    private String city_name;

    /**
     * @Fields province_code : 省市号
     */
    private String province_code;

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }
}
