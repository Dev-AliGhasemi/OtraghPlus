package ir.mrmoshkel.booking.entity;

import ir.mrmoshkel.booking.valueobject.DateRange;
import ir.mrmoshkel.framework.entity.AggregateRoot;
import ir.mrmoshkel.valueobject.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends AggregateRoot<Long> {
    private Long guestId;
    private Long homeId;
    private Price pricePerDay;
    private Price facilitiesUpCharge;
    private BookingStatus bookingStatus;
    private DateRange duration;

}
