package jms.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MessageProducerConfig {

    @Value("tcp://localhost:61616")
    private String brokerUrl;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
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
