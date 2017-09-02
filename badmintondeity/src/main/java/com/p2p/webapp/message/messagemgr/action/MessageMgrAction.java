package com.p2p.webapp.message.messagemgr.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.message.messagemgr.service.MessageMgrService;
import com.p2p.webapp.message.messagemgr.vo.MessageReVo;
import com.p2p.webapp.message.messagemgr.vo.MessageVo;

/**
 * @description 消息管理
 * @author
 * @date 2015-10-28 下午4:21:01
 */
public class MessageMgrAction extends BaseAction {
    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(MessageMgrAction.class);
    private static final long serialVersionUID = 1L;
    private MessageMgrService messageMgrService;
    private List<MessageReVo> messageReList;
    private MessageVo messageVo;
    private MessageReVo messageReVo;

    /**
     * @description 发布新消息跳转方法
     * @version
     * @title
     * @author
     */
    public String publishInit() {
        return "publishMessage";
    }

    /**
     * @description 发布新消息
     * @version
     * @title
     * @author
     */
    public void publishMessage() {
        messageVo.setPublisher_id(session.get("userid").toString());
        messageMgrService.publishMessage(messageVo);
    }

    /**
     * @description 消息主页面
     * @version
     * @title
     * @author
     * @return
     */
    public String myMessageInit() {
        return "messageInit";
    }

    /**
     * @description 查询我的消息
     * @version
     * @title
     * @author
     * @return
     */
    public String queryMyMessage() {
        messageReVo = new MessageReVo();
        messageReVo.setUser_id(session.get("userid").toString());
        messageReList = messageMgrService.queryMyMessage(messageReVo);
        return "myMessage";
    }

    /**
     * @description 查询消息详细内容
     * @version
     * @title
     * @author
     * @return
     */
    public String queryMessageDetail() {
        messageReVo.setUser_id(session.get("userid").toString());
        messageReVo = messageMgrService.queryMessageDetail(messageReVo);
        if (messageReVo.getStatus().equals(Constant.MESSAGE_STATUS_UNREAD)) {
            messageReVo.setStatus(Constant.MESSAGE_STATUS_READED);
            messageMgrService.updateMessageReStatus(messageReVo);
        }
        return "messageDetailInfo";
    }

    public MessageMgrService getMessageMgrService() {
        return messageMgrService;
    }

    public void setMessageMgrService(MessageMgrService messageMgrService) {
        this.messageMgrService = messageMgrService;
    }

    public MessageVo getMessageVo() {
        return messageVo;
    }

    public void setMessageVo(MessageVo messageVo) {
        this.messageVo = messageVo;
    }

    public MessageReVo getMessageReVo() {
        return messageReVo;
    }

    public void setMessageReVo(MessageReVo messageReVo) {
        this.messageReVo = messageReVo;
    }

    public List<MessageReVo> getMessageReList() {
        return messageReList;
    }

    public void setMessageReList(List<MessageReVo> messageReList) {
        this.messageReList = messageReList;
    }
}
