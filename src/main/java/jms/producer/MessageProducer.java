package jms.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;


public class MessageProducer {

    @Autowired
    JmsTemplate myJmsTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    public void send(String destination, String message){
        LOGGER.info("Sending message ='{}' to destination ='{}'", message, destination);
        myJmsTemplate.convertAndSend(destination,message);
    }
}
