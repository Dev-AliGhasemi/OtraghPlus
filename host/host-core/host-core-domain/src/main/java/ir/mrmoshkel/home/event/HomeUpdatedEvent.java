package ir.mrmoshkel.home.event;

import ir.mrmoshkel.framework.event.BaseEvent;
import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.homefacilities.entity.HomeFacilities;
import ir.mrmoshkel.valueobject.Price;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class HomeUpdatedEvent extends BaseEvent {
    private Price price;
    private Address address;
}
