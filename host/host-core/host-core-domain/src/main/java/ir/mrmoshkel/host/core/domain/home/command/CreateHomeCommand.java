package ir.mrmoshkel.host.core.domain.home.command;

import ir.mrmoshkel.host.core.domain.home.valueobject.Address;
import ir.mrmoshkel.valueobject.Price;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.jmolecules.architecture.cqrs.Command;


@Getter
@SuperBuilder
@Command
public class CreateHomeCommand extends HomeCommand {
    private Long hostId;
    private Price pricePerNight;
    private Address address;
}
