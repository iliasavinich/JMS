package jms;

import jms.producer.KotlinBean;
import jms.producer.MessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class SpringJmsApplication{

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringJmsApplication.class,args);
        MessageProducer producer = context.getBean(MessageProducer.class);
        KotlinBean bean = context.getBean(KotlinBean.class);
        producer.send("helloworld.q","Работай!");
    }
}
