package org.dromara.common.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 广播模式消费者，每个队列都能收到相同消息
 */
@Component
@Slf4j
public class FanoutConsumer {

    @RabbitListener(queues = "mall.order.fanoutA")
    public void receiveA(String message) {
        log.info("simple ReceivedA message: " + message);
    }

    @RabbitListener(queues = "mall.order.fanoutB")
    public void receiveB(String message) {
        log.info("simple ReceivedB message: " + message);
    }

}
