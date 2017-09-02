package com.p2p.webapp.message.messagemgr.dao;

import java.util.List;

import com.p2p.webapp.message.messagemgr.entity.MessageInfo;
import com.p2p.webapp.message.messagemgr.entity.MessageReInfo;

public interface MessageDao {
    /**
     * @description 获取下一个messageId
     * @version
     * @title
     * @author 
     * @return 
    */
    public String queryMessageId();
    
    /**
     * @description 发布消息
     * @version
     * @title
     * @author
     * @param messageVo
     */
    public void publishMessage(MessageInfo messageInfo);
    
    /**
     * @description 增加消息接收记录
     * @version
     * @title
     * @author
     * @param messageReInfo
     */
    public void addMessageRe(MessageReInfo messageReInfo);
    
    /**
     * @description 查看消息详细内容
     * @version
     * @title
     * @author
     * @param messageVo
     */
    public MessageReInfo queryMessageDetail(MessageReInfo messageReInfo);

    /**
     * @description 查询我的消息
     * @version
     * @title
     * @author
     * @param messageReInfo
     * @return
     */
    public List<MessageReInfo> queryMyMessage(MessageReInfo messageReInfo);

    /**
     * @description 更新消息状态为已读
     * @version
     * @title
     * @author
     * @param messageVo
     */
    public void updateMessageReStatus(MessageReInfo messageReInfo);
    
    /**
     * @description 查询未读信息数量
     * @version
     * @title
     * @author 
     * @return 
    */
    public int queryUnreadMessageCount(String user_id);

}
