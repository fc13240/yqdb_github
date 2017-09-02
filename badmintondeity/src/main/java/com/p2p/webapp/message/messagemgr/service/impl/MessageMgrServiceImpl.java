package com.p2p.webapp.message.messagemgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.p2p.webapp.message.messagemgr.dao.MessageDao;
import com.p2p.webapp.message.messagemgr.entity.MessageInfo;
import com.p2p.webapp.message.messagemgr.entity.MessageReInfo;
import com.p2p.webapp.message.messagemgr.service.MessageMgrService;
import com.p2p.webapp.message.messagemgr.vo.MessageReVo;
import com.p2p.webapp.message.messagemgr.vo.MessageVo;

public class MessageMgrServiceImpl implements MessageMgrService {
    private MessageDao messageDao;

    /**
     * @description 发布新消息
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.message.messagemgr.service.MessageMgrService#publicMessage(com.p2p.webapp.message.messagemgr.vo.MessageVo)
     * @param messageVo
     */
    public void publishMessage(MessageVo messageVo) {
        MessageInfo messageInfo = new MessageInfo();
        String message_id = messageDao.queryMessageId();
        messageVo.setMessage_id(message_id);
        BeanUtils.copyProperties(messageVo, messageInfo);
        messageDao.publishMessage(messageInfo);

        // TODO 判断消息类型 决定消息发送给谁
        MessageReVo messageReVo = new MessageReVo();
        messageReVo.setMessage_id(message_id);
    }

    /**
     * @description 查询消息详细内容
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.message.messagemgr.service.MessageMgrService#queryMessageDetail(com.p2p.webapp.message.messagemgr.vo.MessageVo)
     * @param messageVo
     * @return
     */
    public MessageReVo queryMessageDetail(MessageReVo messageReVo) {
        MessageReInfo messageReInfo = new MessageReInfo();
        BeanUtils.copyProperties(messageReVo, messageReInfo);
        MessageReInfo messageReBackInfo = messageDao.queryMessageDetail(messageReInfo);
        MessageReVo messageReBackVo = new MessageReVo();
        BeanUtils.copyProperties(messageReBackInfo, messageReBackVo);
        return messageReBackVo;
    }

    /**
     * @description 查询我的消息
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.message.messagemgr.service.MessageMgrService#queryMyMessage(com.p2p.webapp.message.messagemgr.vo.MessageReVo)
     * @param messageReVo
     * @return
     */
    public List<MessageReVo> queryMyMessage(MessageReVo messageReVo) {
        MessageReInfo messageReInfo = new MessageReInfo();
        BeanUtils.copyProperties(messageReVo, messageReInfo);
        List<MessageReInfo> messageReInfoList = messageDao.queryMyMessage(messageReInfo);
        List<MessageReVo> messageReVoList = new ArrayList<MessageReVo>();
        MessageReVo backVo;
        for (int i = 0; i < messageReInfoList.size(); i++) {
            backVo = new MessageReVo();
            BeanUtils.copyProperties(messageReInfoList.get(i), backVo);
            messageReVoList.add(backVo);
        }
        return messageReVoList;
    }

    /**
     * @description 更新消息状态为已读
     * @version
     * @title
     * @author
     * @param messageReVo
     */
    public void updateMessageReStatus(MessageReVo messageReVo) {
        MessageReInfo messageReInfo = new MessageReInfo();
        BeanUtils.copyProperties(messageReVo, messageReInfo);
        messageDao.updateMessageReStatus(messageReInfo);
    }

    /**
     * @description 查询未读信息数量
     * @version
     * @title
     * @author
     * @return
     */
    public int queryUnreadMessageCount(String user_id) {
        int count = messageDao.queryUnreadMessageCount(user_id);
        return count;
    }

    public MessageDao getMessageDao() {
        return messageDao;
    }

    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

}
