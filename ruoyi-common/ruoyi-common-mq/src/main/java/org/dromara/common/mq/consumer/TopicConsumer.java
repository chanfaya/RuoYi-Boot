package org.dromara.common.mq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 通配符模式消费者
 */
@Component
@Slf4j
public class TopicConsumer {

    @RabbitListener(queues = "mall.order.topicA")
    public void receiverMsgA(String msg, Channel channel, Message message){
        log.info("class:{},message:{}","DirectReceiver",msg);
    }

    @RabbitListener(queues = "mall.order.topicB")
    public void receiverMsgB(String msg, Channel channel, Message message){
        log.info("class:{},message:{}","DirectReceiver",msg);
    }
}
