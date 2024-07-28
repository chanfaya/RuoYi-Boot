package org.dromara.common.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.dromara.common.mq.enums.QueueEnum;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 简单-工作模式消费者
 */
@Component
@Slf4j
public class SimpleConsumer {

    /**
     * 如果只有一个监听为简单模式
     * 如果多个则为工作队列模式 轮流消费
     * @param message
     */
    @RabbitListener(queues = "mall.order.simple")
    public void receiveA(String message) {
        log.info("simple ReceivedA message: " + message);
    }

    @RabbitListener(queues = "mall.order.simple")
    public void receiveB(String message) {
        log.info("simple ReceivedB message: " + message);
    }
}
