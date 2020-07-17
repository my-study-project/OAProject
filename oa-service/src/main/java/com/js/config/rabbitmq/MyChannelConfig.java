package com.js.config.rabbitmq;

import com.rabbitmq.client.AMQP;
import lombok.Data;

/**
 * @program: OAProject
 * @Date: 2020/7/9 18:21
 * @Author: jiangshuang
 * @Description:交换机参数
 */
@Data
public class MyChannelConfig {
    /**
    * 交换机名称
    */
    private String exchange;

    /**
     * 队列名称
     */
    private String routingKey;

    /**
     * 消息的其他属性-routing headers
     * 此属性为MessageProperties.PERSISTENT_TEXT_PLAIN用于设置纯文本消息存储到硬盘
     */
    private AMQP.BasicProperties basicProperties;

    /**
     * 消息主体
     */
    private byte[] body;
}
