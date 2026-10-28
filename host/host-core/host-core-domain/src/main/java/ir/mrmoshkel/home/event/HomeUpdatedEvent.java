package ir.mrmoshkel.home.event;

import ir.mrmoshkel.home.event.data.AddressEventData;
import ir.mrmoshkel.framework.event.data.PriceEventData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jmolecules.event.annotation.DomainEvent;

@Getter
@NoArgsConstructor
@SuperBuilder
@DomainEvent
public class HomeUpdatedEvent extends HomeEvent {
    private PriceEventData price;
    private AddressEventData address;
}
