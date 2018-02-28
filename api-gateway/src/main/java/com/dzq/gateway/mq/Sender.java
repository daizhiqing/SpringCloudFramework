package com.dzq.gateway.mq;

import com.alibaba.druid.support.json.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.dzq.base.rabbit.ExchangeQueue;

import java.util.Map;
import java.util.UUID;

@Component
public class Sender implements RabbitTemplate.ConfirmCallback{
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;
	
    /**
     * 配置发送消息的rabbitTemplate，因为是构造方法，所以不用注解Spring也会自动注入（应该是新版本的特性）
     * @param rabbitTemplate
     */
    public Sender(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        //设置消费回调
        this.rabbitTemplate.setConfirmCallback(this);
    }
    
    
    /**
     * 发送用户行为记录消息
     * @param params
     * @return
     */
    public String sendUserActionEvent(Map<String, Object> params){
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);
        logger.info("Send Msg: [{}] ,content: [{}]",correlationId , JSONUtils.toJSONString(params));
        rabbitTemplate.convertAndSend(ExchangeQueue.Exchange.EX_ZUUL_MQ, ExchangeQueue.Queue.EX_ZUUL_MQ_USER_ACTION, params,
                correlationId);
        return null;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("ZUUL发送消息回调id:" + correlationData);
        if (ack) {
            logger.info("ZUUL发送消息成功消费:"+correlationData);
        } else {
            logger.info("ZUUL发送消息消费失败:" + cause+"\n重新发送:"+correlationData);
        }
    }
}