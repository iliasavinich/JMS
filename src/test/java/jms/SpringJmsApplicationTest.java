package jms;

import static org.assertj.core.api.Assertions.assertThat;
import jms.producer.MessageProducer;
import jms.receiver.MessageConsumer;
import org.apache.activemq.junit.EmbeddedActiveMQBroker;
import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootApplication
public class SpringJmsApplicationTest {

    private static ApplicationContext context;

    @Autowired
    void setContext(ApplicationContext applicationContext) {
        SpringJmsApplicationTest.context = applicationContext;
    }

    @AfterClass
    public static void afterClass(){
        ((ConfigurableApplicationContext)context).close();
    }

    @ClassRule
    public static EmbeddedActiveMQBroker broker = new EmbeddedActiveMQBroker();

    @Autowired
    private MessageProducer producer;

    @Autowired
    private MessageConsumer consumer;

    @Test
    public void testReceive()throws Exception{
        producer.send("helloworld.q","РАБОТАЕТ!");
        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(consumer.getLatch().getCount()).isEqualTo(0);
    }

}
