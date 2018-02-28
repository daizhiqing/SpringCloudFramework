package com.dzq.base.rabbit;

import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix="spring.rabbitmq", ignoreNestedProperties = false)
@Component
public class RabbitMQConfig {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static String host;
    private static int port;
	private static String username;
    private static String password;
    private static String virtualHost;

	public static String getVirtualHost() {
		return virtualHost;
	}

	public static void setVirtualHost(String virtualHost) {
		RabbitMQConfig.virtualHost = virtualHost;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		RabbitMQConfig.host = host;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		RabbitMQConfig.port = port;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		RabbitMQConfig.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		RabbitMQConfig.password = password;
	}

    /**
     * 配置链接信息
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(TextUtils.isEmpty(virtualHost)?"/":virtualHost);
		logger.info("===============================setVirtualHost(virtualHost);"+virtualHost);
        connectionFactory.setPublisherConfirms(true); // 必须要设置
        return connectionFactory;
    }

    /**  
     * 配置消息交换机
     * 针对消费者配置  
        FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念  
        HeadersExchange ：通过添加属性key-value匹配  
        DirectExchange:按照routingkey分发到指定队列  
        TopicExchange:多关键字匹配  
     */  
    @Bean
    public DirectExchange ZuulExchange() {
        return new DirectExchange(ExchangeQueue.Exchange.EX_ZUUL_MQ, true, false);
    }

	/**
	 * 配置事件消息队列
	 * 针对消费者配置
	 * @return
	 */
	@Bean
	public Queue queueUserAction() {
		return new Queue(ExchangeQueue.Queue.EX_ZUUL_MQ_USER_ACTION, true);
	}

	/**
	 * 将事件消息队列与交换机绑定
	 * 针对消费者配置
	 * @return
	 */
	@Bean
	public Binding bindingUserAction() {
		return BindingBuilder.bind(queueUserAction()).to(ZuulExchange()).with(ExchangeQueue.Queue.EX_ZUUL_MQ_USER_ACTION);
	}


}
