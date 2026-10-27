package ir.mrmoshkel.home.event;

import ir.mrmoshkel.home.event.data.AddressData;
import ir.mrmoshkel.framework.event.data.PriceData;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor
@SuperBuilder
public class HomeCreatedEvent extends HomeEvent {
    private Long hostId;
    private PriceData priceData;
    private AddressData addressData;
}
