package com.p2p.webapp.city.citymgr.dto;

/**
 * @description 省市Vo
 * @author
 * @String 2015-10-28 下午4:10:52
 */
public class ProvinceInfo {
    /**
     * @Fields province_id : 主键id
     */
    private String province_id;

    /**
     * @Fields province_name : 省市名
     */
    private String province_name;

    /**
     * @Fields province_code : 省市号
     */
    private String province_code;

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

}
