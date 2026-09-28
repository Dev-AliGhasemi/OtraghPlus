package ir.mrmoshkel.home.command;

import ir.mrmoshkel.framework.command.BaseCommand;
import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.homefacilities.entity.HomeFacilities;
import ir.mrmoshkel.valueobject.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@SuperBuilder
public class CreateHomeCommand extends BaseCommand<UUID> {
    private Long hostId;
    private Price pricePerNight;
    private Address address;
}
