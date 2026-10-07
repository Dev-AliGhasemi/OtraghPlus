package ir.mrmoshkel.home.event;

import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.valueobject.Price;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Getter
@NoArgsConstructor
@SuperBuilder
public class HomeCreatedEvent extends HomeEvent {
    private Long hostId;
    private Price price;
    private Address address;
}
