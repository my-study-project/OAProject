package com.js.common.util;

import com.js.config.rabbitmq.MqConfig;
import com.js.config.rabbitmq.MyChannelConfig;
import com.js.config.rabbitmq.QueueConfig;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @program: OAProject
 * @Date: 2020/7/9 17:24
 * @Author: jiangshuang
 * @Description:RabbitUtil1工具类
 */
public class RabbitUtil {


    public Connection rabbitMqgetConnByConfig(MqConfig mqConfig){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(mqConfig.getUsername());
        factory.setPassword(mqConfig.getPassword());
        factory.setVirtualHost(mqConfig.getVirtualHost());
        factory.setHost(mqConfig.getHost());
        factory.setPort(mqConfig.getPort());
        Connection conn = null;
        try {
            conn = factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
    * @Description: 推送消息
    * @param ；arguments：消息其他参数,mqConfig:交换机基础配置信息】
    * @Author: jiangshuang
    * @Date: 2020/7/9 18:03
    */

    public void publisher(MqConfig mqConfig,QueueConfig queueConfig, MyChannelConfig myChannelConfig) {
        // 创建一个连接
        Connection conn = rabbitMqgetConnByConfig(mqConfig);
        if (conn != null) {
            try {
                // 创建通道
                Channel channel = conn.createChannel();
                channel.queueDeclare(queueConfig.getQueue(), queueConfig.getDurable(), queueConfig.getExclusive(), queueConfig.getAutoDelete(), queueConfig.getArguments());
                if(myChannelConfig.getBody() == null) {
                    myChannelConfig.setBody(String.format("当前时间：%s", System.currentTimeMillis() +"生产者测试").getBytes());
                }
                channel.basicPublish(myChannelConfig.getExchange(), myChannelConfig.getRoutingKey(), myChannelConfig.getBasicProperties(), myChannelConfig.getBody());
                System.out.println("已发送消息：" + myChannelConfig.getBody());
                // 关闭连接
                channel.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 监听
     * 消费消息
     */
    public void consumer(MqConfig mqConfig,QueueConfig queueConfig, MyChannelConfig myChannelConfig) {
        // 创建一个连接
        Connection conn = rabbitMqgetConnByConfig(mqConfig);
        if (conn != null) {
            try {
                // 创建通道
                Channel channel = conn.createChannel();
                // 声明队列【参数说明：参数一：队列名称，参数二：是否持久化；参数三：是否独占模式；参数四：消费者断开连接时是否删除队列；参数五：消息其他参数】
                channel.queueDeclare(queueConfig.getQueue(), queueConfig.getDurable(), queueConfig.getExclusive(), queueConfig.getAutoDelete(), queueConfig.getArguments());

                // 创建订阅器，并接受消息
                channel.basicConsume(queueConfig.getQueue(), false, "", new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                               byte[] body) throws IOException {
                        String routingKey = envelope.getRoutingKey(); // 队列名称
                        String contentType = properties.getContentType(); // 内容类型
                        String content = new String(body, "utf-8"); // 消息正文
                        System.out.println("消息正文：" + content);
                        channel.basicAck(envelope.getDeliveryTag(), false); // 手动确认消息【参数说明：参数一：该消息的index；参数二：是否批量应答，true批量确认小于index的消息】
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
