package ir.mrmoshkel.host.core.domain.home.event;

import ir.mrmoshkel.host.core.domain.home.event.data.AddressEventData;
import ir.mrmoshkel.framework.event.data.PriceEventData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jmolecules.event.annotation.DomainEvent;


@Getter
@NoArgsConstructor
@SuperBuilder
@DomainEvent
public class HomeCreatedEvent extends HomeEvent {
    private Long hostId;
    private PriceEventData priceEventData;
    private AddressEventData addressEventData;
}
