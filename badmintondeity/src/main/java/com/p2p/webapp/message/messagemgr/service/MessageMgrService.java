package com.p2p.webapp.message.messagemgr.service;

import java.util.List;

import com.p2p.webapp.message.messagemgr.vo.MessageReVo;
import com.p2p.webapp.message.messagemgr.vo.MessageVo;

/**
 * @description 报名管理service
 * @author
 * @date 2015-10-28 下午4:47:43
 */
public interface MessageMgrService {
    /**
     * @description 发布消息
     * @version
     * @title
     * @author
     * @param messageVo
     */
    public void publishMessage(MessageVo messageVo);
    
    /**
     * @description 查看消息详细内容
     * @version
     * @title
     * @author 
     * @param messageVo
     * @return 
    */
    public MessageReVo queryMessageDetail(MessageReVo messageReVo);
    
    /**
     * @description 查询我的消息
     * @version
     * @title
     * @author 
     * @param messageReVo
     * @return 
    */
    public List<MessageReVo> queryMyMessage(MessageReVo messageReVo);

    /**
     * @description 更新消息状态为已读
     * @version
     * @title
     * @author 
     * @param messageReVo 
    */
    public void updateMessageReStatus(MessageReVo messageReVo);
    

    /**
     * @description 查询未读信息数量
     * @version
     * @title
     * @author 
     * @return 
    */
    public int queryUnreadMessageCount(String user_id);
}
