package org.dromara.common.mq.product;

import jakarta.annotation.Resource;
import org.dromara.common.mq.enums.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class TopicProducter {
    Logger LOG = LoggerFactory.getLogger(TopicProducter.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(int i) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date =LocalDateTime.now().format(dtf);

        String content = i+":hello!"+date;
        rabbitTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_TOPICA.getName(), QueueEnum.QUEUE_ORDER_TOPICA.getRouteKey(),content);

    }
}
