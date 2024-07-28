package org.dromara.common.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 路由模式消费者
 */
@Component
@Slf4j
public class DirectConsumer {

    @RabbitListener(queues = "mall.order.direct")
    public void receiverMsg(String msg){
        log.info("class:{},message:{}","DirectReceiver",msg);
    }
}
