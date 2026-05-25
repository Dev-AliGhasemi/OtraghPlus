package ir.mrmoshkel.eventproducer;

import ir.mrmoshkel.framework.event.BaseEvent;
import ir.mrmoshkel.framework.EventProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventProducer implements EventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void produce(String topic, BaseEvent baseEvent) {
        kafkaTemplate.send(topic, baseEvent);
    }
}
