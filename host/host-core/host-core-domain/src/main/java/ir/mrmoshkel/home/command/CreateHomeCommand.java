package ir.mrmoshkel.home.command;

import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.valueobject.Price;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
public class CreateHomeCommand extends HomeCommand {
    private Long hostId;
    private Price pricePerNight;
    private Address address;
}
