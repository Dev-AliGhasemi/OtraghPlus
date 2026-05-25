package ir.mrmoshkel.home.event;

import ir.mrmoshkel.framework.event.BaseEvent;
import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.valueobject.Price;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class HomeCreatedEvent extends BaseEvent {
    private Long hostId;
    private Price price;
    private Address address;
}
