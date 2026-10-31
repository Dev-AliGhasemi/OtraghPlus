package ir.mrmoshkel.common.infrastructure.framework;

import ir.mrmoshkel.framework.event.BaseEvent;
import org.jmolecules.event.annotation.DomainEventPublisher;

import java.util.concurrent.ExecutionException;

public interface EventProducer {
    @DomainEventPublisher
    <T> void produce(String topic, BaseEvent<T> baseEvent) throws ExecutionException, InterruptedException;
}
