package ir.mrmoshkel.home.event;

import ir.mrmoshkel.home.event.data.AddressData;
import ir.mrmoshkel.framework.event.data.PriceData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@SuperBuilder
public class HomeUpdatedEvent extends HomeEvent {
    private PriceData price;
    private AddressData address;
}
