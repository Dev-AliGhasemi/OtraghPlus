package ir.mrmoshkel.framework.event.data;

import ir.mrmoshkel.valueobject.Price;
import lombok.AllArgsConstructor;
import lombok.Getter;

//These vo data are for serialization and deserialization problem of value objects
@Getter
@AllArgsConstructor
public class PriceData extends BaseEventData<Price> {
    private Long price;

    @Override
    public Price toValueObject() {
        return new Price(price);
    }
}
