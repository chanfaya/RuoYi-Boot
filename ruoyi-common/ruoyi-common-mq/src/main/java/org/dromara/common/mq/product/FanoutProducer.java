package org.dromara.common.mq.product;

import jakarta.annotation.Resource;
import org.dromara.common.mq.enums.QueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FanoutProducer {


    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String content = i+":hello!"+date;
        rabbitTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_FANOUTA.getExchange(),null,  content);
    }

}
