package ir.mrmoshkel.framework;

import ir.mrmoshkel.framework.event.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent baseEvent);
}
