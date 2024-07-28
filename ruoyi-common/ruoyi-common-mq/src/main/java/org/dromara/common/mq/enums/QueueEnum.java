package org.dromara.common.mq.enums;

import lombok.Getter;

@Getter
public enum QueueEnum {

    /**
     * 简单和工作队列模式
     */
    QUEUE_ORDER_CANCEL_SIMPLE("", "mall.order.simple", ""),

    /**
     * 路由模式
     */
    QUEUE_ORDER_DIRECT("mall.order.direct", "mall.order.direct", "mall.order.direct"),

    /**
     * 广播模式
     */

    QUEUE_ORDER_FANOUTA("mall.order.fanout", "mall.order.fanoutA", "t"),

    QUEUE_ORDER_FANOUTB("mall.order.fanout", "mall.order.fanoutB", ""),

    /**
     * 通配符模式
     */
    QUEUE_ORDER_TOPICA("mall.order.topic", "mall.order.topicA", "orange.*"),

    QUEUE_ORDER_TOPICB("mall.order.topic", "mall.order.topicB", "*.black.*")


        ;

    /**
     * 交换名称
     */
    private final String exchange;
    /**
     * 队列名称
     */
    private final String name;
    /**
     * 路由键
     */
    private final String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
