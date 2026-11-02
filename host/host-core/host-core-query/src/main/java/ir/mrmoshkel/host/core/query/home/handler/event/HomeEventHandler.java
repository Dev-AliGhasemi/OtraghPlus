package ir.mrmoshkel.host.core.query.home.handler.event;

import ir.mrmoshkel.common.core.framework.event.BaseEventHandler;
import ir.mrmoshkel.host.core.domain.home.event.HomeCreatedEvent;
import ir.mrmoshkel.host.core.domain.home.event.HomeDeletedEvent;
import ir.mrmoshkel.host.core.domain.home.event.HomeReservedEvent;
import ir.mrmoshkel.host.core.domain.home.event.HomeUpdatedEvent;
import org.jmolecules.event.annotation.DomainEventHandler;

public interface HomeEventHandler extends BaseEventHandler {
    @DomainEventHandler
    void handle(HomeCreatedEvent homeCreatedEvent);

    @DomainEventHandler
    void handle(HomeUpdatedEvent homeUpdatedEvent);

    @DomainEventHandler
    void handle(HomeDeletedEvent homeDeletedEvent);

    @DomainEventHandler
    void handle(HomeReservedEvent homeReservedEvent);
}
