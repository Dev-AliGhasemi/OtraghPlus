package ir.mrmoshkel.home.model;

import ir.mrmoshkel.framework.model.DataModel;
import ir.mrmoshkel.home.enumeration.ReserveState;
import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.homefacilities.entity.HomeFacilities;
import ir.mrmoshkel.valueobject.Price;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class HomeModel extends DataModel<Long> {
    private Long hostId;
    private Price pricePerNight;
    private Address address;
    private ReserveState reserveState;
}
