package ir.hasankarimi.scheduledrandomproducer.route;

import ir.hasankarimi.scheduledrandomproducer.service.Summation;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RandomConsumer extends RouteBuilder {

    @Value("${kafka.consumer.period}")
    private String period;

    private final Summation summation;

    public RandomConsumer(Summation summation) {
        this.summation = summation;
    }

    @Override
    public void configure() throws Exception {
        from("timer:?fixedRate=true&period=" + period)
                .bean(summation, "logSum");
    }

}