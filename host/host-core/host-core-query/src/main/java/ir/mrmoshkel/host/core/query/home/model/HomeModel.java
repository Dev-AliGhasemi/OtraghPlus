package ir.mrmoshkel.host.core.query.home.model;

import ir.mrmoshkel.common.core.framework.model.DataModel;
import ir.mrmoshkel.host.core.domain.home.enumeration.ReserveState;
import ir.mrmoshkel.host.core.domain.home.valueobject.Address;
import ir.mrmoshkel.common.core.valueobject.Price;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

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
