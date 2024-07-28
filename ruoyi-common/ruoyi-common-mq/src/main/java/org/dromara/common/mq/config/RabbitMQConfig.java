package org.dromara.common.mq.config;

import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.mq.enums.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq 配置类
 */
@Configuration
@EnableRabbit
@Slf4j
public class RabbitMQConfig {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Bean
    //@Primary
    public AmqpTemplate amqpTemplate(){
        //使用jackson 消息转换器(发送对象时候才开启)
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        // 设置编码
        rabbitTemplate.setEncoding("UTF-8");
        // 开启消息退回
        rabbitTemplate.setMandatory(true);

        // 开启消息回退log    yml 需要配置publisher-returns: true
        rabbitTemplate.setReturnsCallback((returnedMessage)->{
            log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", returnedMessage.getMessage().getMessageProperties().getClusterId(), returnedMessage.getReplyCode(), returnedMessage.getReplyText(), returnedMessage.getExchange(), returnedMessage.getRoutingKey());
        });

        //开启消息确认  yml 需要配置   publisher-returns: true
        rabbitTemplate.setConfirmCallback(((correlationData, ack, cause) ->{
            if (ack) {
                log.info("消息发送到交换机成功,correlationId:{}",correlationData.getId());
            } else {
                log.info("消息发送到交换机失败,原因:{}",cause);
            }
        } ));
        return rabbitTemplate;
    }

    /**
     * 简单模式-工作队列模式
     * @return
     */
    @Bean("simpleQueue")
    public Queue queue(){
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL_SIMPLE.getName(),true,false,false);
    }

    /**
     * 路由模式，交换机
     * @return the exchange
     */
    @Bean("directDirect")
    public DirectExchange  directExchange() {
        return ExchangeBuilder.directExchange(QueueEnum.QUEUE_ORDER_DIRECT.getExchange()).durable(true).build();
    }

    /**
     * 路由模式，队列
     * @return
     */
    @Bean("directQueue")
    public Queue directQueue(){
        return new Queue(QueueEnum.QUEUE_ORDER_DIRECT.getName(), true, false, false);
    }

    /**
     * 路由模式交换机与队列绑定
     * @param directDirect
     * @param directQueue
     * @return
     */
    @Bean
    public Binding directBinding(DirectExchange directDirect,Queue directQueue){
        return BindingBuilder
            .bind(directQueue)
            .to(directDirect)
            .with(QueueEnum.QUEUE_ORDER_DIRECT.getRouteKey());
    }

    /**
     * 广播模式，队列A
     * @return
     */
    @Bean("fanoutQueueA")
    public Queue fanoutExchangeQueueA(){
        return new Queue(QueueEnum.QUEUE_ORDER_FANOUTA.getName(), true, false, true);
    }

    /**
     * 广播模式，队列B
     * @return
     */
    @Bean("fanoutQueueB")
    public Queue fanoutExchangeQueueB(){
        return new Queue(QueueEnum.QUEUE_ORDER_FANOUTB.getName(), true, false, true);
    }

    /**
     * 广播模式，交换机
     * @return
     */
    @Bean("fanoutExchange")
    public FanoutExchange rabbitmqDemoFanoutExchange() {
        //创建FanoutExchange类型交换机
        return new FanoutExchange(QueueEnum.QUEUE_ORDER_FANOUTA.getExchange(), true, false);
    }

    /**
     * 广播模式，交换机与队列A绑定
     * @param fanoutExchange
     * @param fanoutQueueA
     * @return
     */
    @Bean
    public Binding bindFanoutA(FanoutExchange fanoutExchange, Queue fanoutQueueA) {
        //队列A绑定到FanoutExchange交换机
        return BindingBuilder.bind(fanoutQueueA).to(fanoutExchange);
    }

    /**
     * 广播模式，交换机与队列B绑定
     * @param fanoutExchange
     * @param fanoutQueueB
     * @return
     */
    @Bean
    public Binding bindFanoutB(FanoutExchange fanoutExchange, Queue fanoutQueueB) {
        //队列B绑定到FanoutExchange交换机
        return BindingBuilder.bind(fanoutQueueB).to(fanoutExchange);
    }

    /**
     * 通配符模式，队列
     * @return
     */
    @Bean("topicQueuqA")
    public Queue topicQueuqA() {
        return new Queue(QueueEnum.QUEUE_ORDER_TOPICA.getName(),true,false,false);
    }

    @Bean("topicQueuqB")
    public Queue topicQueuqB() {
        return new Queue(QueueEnum.QUEUE_ORDER_TOPICB.getName(),true,false,false);
    }

    /**
     * 通配符模式，交换机
     * @return
     */
    @Bean("topicExchange")
    public TopicExchange topicExchange() {
        return new TopicExchange(QueueEnum.QUEUE_ORDER_TOPICA.getExchange(), true, false);
    }

    /**
     * 通配符模式，队列与交换机绑定
     * @param topicExchange
     * @param topicQueuqA
     * @return
     */
    @Bean
    public Binding bindTopicA(TopicExchange topicExchange, Queue topicQueuqA) {
        return BindingBuilder.bind(topicQueuqA).to(topicExchange).with(QueueEnum.QUEUE_ORDER_TOPICA.getRouteKey());
    }

    @Bean
    public Binding bindTopicB(TopicExchange topicExchange, Queue topicQueuqB) {
        return BindingBuilder.bind(topicQueuqB).to(topicExchange).with(QueueEnum.QUEUE_ORDER_TOPICB.getRouteKey());
    }
}
