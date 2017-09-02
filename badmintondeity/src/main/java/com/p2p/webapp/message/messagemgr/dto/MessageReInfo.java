package com.p2p.webapp.message.messagemgr.dto;

import java.util.Date;

public class MessageReInfo {
    /**
     * @Fields re_id : 接收id
     */
    private String re_id;

    /**
     * @Fields message_id : 消息id
     */
    private String message_id;

    /**
     * @Fields user_id : 接收者
     */
    private String user_id;

    /**
     * @Fields status : 阅读状态
     */
    private String status;

    /**
     * @Fields publisher_id : 发布者
     */
    private String publisher_id;

    /**
     * @Fields message_type : 消息类型
     */
    private String message_type;

    /**
     * @Fields theme : 主题
     */
    private String theme;

    /**
     * @Fields content : 内容
     */
    private String content;

    /**
     * @Fields create_date : 发布时间
     */
    private Date create_date;

    public String getRe_id() {
        return re_id;
    }

    public void setRe_id(String re_id) {
        this.re_id = re_id;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(String publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

}