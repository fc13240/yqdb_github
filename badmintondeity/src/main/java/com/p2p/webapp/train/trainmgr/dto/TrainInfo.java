package com.p2p.webapp.train.trainmgr.dto;

import java.util.Date;

public class TrainInfo {

    /**
     * @Fields train_id : 训练ID
     */
    private String train_id;

    /**
     * @Fields train_name : 训练名称
     */
    private String train_name;
    
    /**
     * @Fields user_id : 训练用户
     */
    private String user_id;
    
    /**
     * @Fields train_type : 训练类型
     */
    private String train_type;

    /**
     * @Fields train_device_name : 训练设备名称
     */
    private String train_device_name;

    /**
     * @Fields consume_calorie : 消耗卡路里
     */
    private String consume_calorie;

    /**
     * 训练量
     */
    private String train_amount;

    /**
     * @Fields train_begin : 训练开始时间
     */
    private Date train_begin;

    /**
     * @Fields train_end : 训练结束时间
     */
    private Date train_end;

    private String count;

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }
    
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTrain_type() {
        return train_type;
    }

    public void setTrain_type(String train_type) {
        this.train_type = train_type;
    }

    
    public String getTrain_device_name() {
        return train_device_name;
    }

    public void setTrain_device_name(String train_device_name) {
        this.train_device_name = train_device_name;
    }

    public String getConsume_calorie() {
        return consume_calorie;
    }

    public void setConsume_calorie(String consume_calorie) {
        this.consume_calorie = consume_calorie;
    }

    public String getTrain_amount() {
        return train_amount;
    }

    public void setTrain_amount(String train_amount) {
        this.train_amount = train_amount;
    }

    public Date getTrain_begin() {
        return train_begin;
    }

    public void setTrain_begin(Date train_begin) {
        this.train_begin = train_begin;
    }

    public Date getTrain_end() {
        return train_end;
    }

    public void setTrain_end(Date train_end) {
        this.train_end = train_end;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}
