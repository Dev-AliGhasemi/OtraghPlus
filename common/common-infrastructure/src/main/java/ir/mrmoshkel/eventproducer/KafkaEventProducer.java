package ir.mrmoshkel.eventproducer;

import ir.mrmoshkel.framework.event.BaseEvent;
import ir.mrmoshkel.framework.EventProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class KafkaEventProducer implements EventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void produce(String topic, BaseEvent baseEvent) throws ExecutionException, InterruptedException {
        SendResult<String, Object> sendResult = kafkaTemplate.send(topic, "test", baseEvent).get();
        System.out.println(sendResult.getProducerRecord().headers());
    }
}