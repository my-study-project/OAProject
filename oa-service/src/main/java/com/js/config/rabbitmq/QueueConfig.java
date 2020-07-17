package com.js.config.rabbitmq;

import lombok.Data;

import java.util.Map;

/**
 * @program: OAProject
 * @Date: 2020/7/9 18:27
 * @Author: jiangshuang
 * @Description:队列信息
 */
@Data
public class QueueConfig {
    /**
     * 队列名称
     */
    private String queue;
    /**
     * 是否持久化,false
     */
    private Boolean durable = false;
    /**
     * 是否独占模式，fasle
     */
    private Boolean exclusive = false;
    /**
     * 消费者断开连接时是否删除队列
     */
    private Boolean autoDelete = false;

    /**
    * 消息其他参数,
    */
    private Map<String, Object> arguments;

}
