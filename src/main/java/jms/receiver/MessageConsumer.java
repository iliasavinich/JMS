package jms.receiver;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import java.util.concurrent.CountDownLatch;

public class MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch(){
        return latch;
    }

    @JmsListener(destination = "helloworld.q")
    public void receiveMessage(String message){
        LOGGER.info("Received message ='{}'",message);
        latch.countDown();
    }
}
