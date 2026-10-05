package ir.mrmoshkel.home.model;

import ir.mrmoshkel.framework.model.DataModel;
import ir.mrmoshkel.home.enumeration.ReserveState;
import ir.mrmoshkel.home.valueobject.Address;
import ir.mrmoshkel.homefacilities.entity.HomeFacilities;
import ir.mrmoshkel.valueobject.Price;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class HomeModel extends DataModel<UUID> {
    private Long hostId;
    private Price pricePerNight;
    private Address address;
    @Builder.Default
    private ReserveState reserveState = ReserveState.READY_TO_RESERVED;
}
