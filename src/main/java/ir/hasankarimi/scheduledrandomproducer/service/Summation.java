package ir.hasankarimi.scheduledrandomproducer.service;

import org.apache.camel.ConsumerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Summation {

    @Value("${kafka.topic}")
    private String topic;
    private final Logger log = LoggerFactory.getLogger(Summation.class);
    private final ConsumerTemplate consumer;

    public Summation(ConsumerTemplate consumerTemplate) {
        consumer = consumerTemplate;
    }

    public void logSum() {
        int sum = 0;
        while (true) {
            Integer number = consumer.receiveBody("kafka:" + topic, 0, Integer.class);
            if (number == null) {
                log.info(Integer.toString(sum));
                break;
            }
            sum += number;
        }
    }
}
