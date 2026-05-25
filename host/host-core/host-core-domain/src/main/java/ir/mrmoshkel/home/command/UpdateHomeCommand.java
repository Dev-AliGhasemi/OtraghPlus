package ir.mrmoshkel.home.command;

import ir.mrmoshkel.framework.command.BaseCommand;
import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.homefacilities.entity.HomeFacilities;
import ir.mrmoshkel.valueobject.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class UpdateHomeCommand extends BaseCommand<Long> {
    private Price pricePerNight;
    private Address address;
    private List<HomeFacilities> facilities;
}
