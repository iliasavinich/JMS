package jms.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;

@Configuration
@PropertySource("classpath:application.yml")
public class MessageProducerConfig {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProducerConfig.class);

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        LOG.info("BrokerURL - " + brokerUrl);
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        return activeMQConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory(){
        return new CachingConnectionFactory(activeMQConnectionFactory());
    }

    @Bean
    public KotlinBean kotlinBean(){
        return new KotlinBean();
    }

    @Bean(name = "myJmsTemplate")
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(cachingConnectionFactory());
    }

    @Bean
    public MessageProducer messageProducer(){
        return new MessageProducer();
    }
}
