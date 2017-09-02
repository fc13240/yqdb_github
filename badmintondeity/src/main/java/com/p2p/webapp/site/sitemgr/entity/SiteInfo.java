package com.p2p.webapp.site.sitemgr.entity;

import java.util.Date;

public class SiteInfo {

    /**
     * @Fields site_addr_id : 地址ID
     */
    private String site_addr_id;

    /**
     * @Fields site_name : 场地名称
     */
    private String site_name;

    /**
     * @Fields site_addr_province : 省
     */
    private String site_addr_province;

    /**
     * @Fields site_addr_city : 市
     */
    private String site_addr_city;

    /**
     * @Fields site_addr_district : 区
     */
    private String site_addr_district;

    /**
     * @Fields site_addr_detail : 详细地址
     */
    private String site_addr_detail;

    /**
     * @Fields site_type : 场地类型 0塑胶/1木板
     */
    private String site_type;

    /**
     * @Fields site_out_in : 0室内/1室外
     */
    private String site_out_in;

    /**
     * @Fields site_office_telephone : 联系电话
     */
    private Date site_office_telephone;

    /**
     * @Fields site_office_manager : 场地管理员
     */
    private String site_office_manager;

    /**
     * @Fields site_office_phone : 座机号码
     */
    private String site_office_phone;

    /**
     * @Fields site_lng : 场地坐标经度
     */
    private String site_lng;

    /**
     * @Fields site_lat : 场地坐标纬度
     */
    private String site_lat;

    /**
     * @Fields site_des : 地点描述
     */
    private String site_des;

    public String getSite_addr_id() {
        return site_addr_id;
    }

    public void setSite_addr_id(String site_addr_id) {
        this.site_addr_id = site_addr_id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getSite_addr_province() {
        return site_addr_province;
    }

    public void setSite_addr_province(String site_addr_province) {
        this.site_addr_province = site_addr_province;
    }

    public String getSite_addr_city() {
        return site_addr_city;
    }

    public void setSite_addr_city(String site_addr_city) {
        this.site_addr_city = site_addr_city;
    }

    public String getSite_addr_district() {
        return site_addr_district;
    }

    public void setSite_addr_district(String site_addr_district) {
        this.site_addr_district = site_addr_district;
    }

    public String getSite_addr_detail() {
        return site_addr_detail;
    }

    public void setSite_addr_detail(String site_addr_detail) {
        this.site_addr_detail = site_addr_detail;
    }

    public String getSite_type() {
        return site_type;
    }

    public void setSite_type(String site_type) {
        this.site_type = site_type;
    }

    public String getSite_out_in() {
        return site_out_in;
    }

    public void setSite_out_in(String site_out_in) {
        this.site_out_in = site_out_in;
    }

    public Date getSite_office_telephone() {
        return site_office_telephone;
    }

    public void setSite_office_telephone(Date site_office_telephone) {
        this.site_office_telephone = site_office_telephone;
    }

    public String getSite_office_manager() {
        return site_office_manager;
    }

    public void setSite_office_manager(String site_office_manager) {
        this.site_office_manager = site_office_manager;
    }

    public String getSite_office_phone() {
        return site_office_phone;
    }

    public void setSite_office_phone(String site_office_phone) {
        this.site_office_phone = site_office_phone;
    }

    public String getSite_des() {
        return site_des;
    }

    public void setSite_des(String site_des) {
        this.site_des = site_des;
    }

    public String getSite_lng() {
        return site_lng;
    }

    public void setSite_lng(String site_lng) {
        this.site_lng = site_lng;
    }

    public String getSite_lat() {
        return site_lat;
    }

    public void setSite_lat(String site_lat) {
        this.site_lat = site_lat;
    }
}
