package ir.mrmoshkel.framework;

import ir.mrmoshkel.framework.event.BaseEvent;

import java.util.concurrent.ExecutionException;

public interface EventProducer {
    void produce(String topic, BaseEvent baseEvent) throws ExecutionException, InterruptedException;
}
