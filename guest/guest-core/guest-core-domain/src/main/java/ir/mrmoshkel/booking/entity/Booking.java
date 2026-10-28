package ir.mrmoshkel.booking.entity;

import ir.mrmoshkel.booking.valueobject.DateRange;
import ir.mrmoshkel.framework.entity.BaseAggregateRoot;
import ir.mrmoshkel.valueobject.Price;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder

public class Booking extends BaseAggregateRoot<Long> {
    private Long guestId;
    private Long homeId;
    private Price pricePerDay;
    private Price facilitiesUpCharge;
    private BookingStatus bookingStatus;
    private DateRange duration;

    @Override
    public String getName() {
        return "BOOKING";
    }
}
