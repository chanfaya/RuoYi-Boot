package org.dromara.common.mq.product;

import jakarta.annotation.Resource;
import org.dromara.common.mq.enums.QueueEnum;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SimpleProducer {


    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String content = i+":hello!"+date;
        rabbitTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_CANCEL_SIMPLE.getName(),  content);
    }

}
