package ir.mrmoshkel.eventproducer;

import ir.mrmoshkel.framework.event.BaseEvent;
import ir.mrmoshkel.framework.EventProducer;
import org.jmolecules.event.annotation.DomainEventPublisher;
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

    @DomainEventPublisher
    @Override
    public <T> void produce(String topic, BaseEvent<T> baseEvent) throws ExecutionException, InterruptedException {
        kafkaTemplate.send(topic, "test", baseEvent).get();
    }
}