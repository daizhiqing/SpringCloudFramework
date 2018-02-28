package com.dzq.base.rabbit;

/**
 * 配置交换机 队列
 * @author daizhiqing
 */
public class ExchangeQueue {

    /**
     * 交换机名称
     */
    public static class Exchange{
        /**
         * 交换机:示例网关API
         */
        public static final String EX_ZUUL_MQ = "ex-zuul-mq";

    }

    /**
     * 队列名称
     */
    public static class Queue{
        /**
         * 队列:网关API
         */
        public static final String EX_ZUUL_MQ_USER_ACTION = "xc_zuul_user_action";
    }
}
