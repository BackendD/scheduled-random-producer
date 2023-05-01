package ir.hasankarimi.scheduledrandomproducer.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomProducer extends RouteBuilder {

    @Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.producer.period}")
    private String period;

    @Value("${random-number.bound}")
    private int bound;

    @Override
    public void configure() throws Exception {

        from("timer:?fixedRate=true&period=" + period)
                .process(exchange -> exchange.getIn().setBody(
                        ThreadLocalRandom.current().nextInt(bound)
                ))
                .to("kafka:" + topic);

    }
}
