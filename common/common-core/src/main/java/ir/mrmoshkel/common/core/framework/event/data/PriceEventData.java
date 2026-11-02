package ir.mrmoshkel.common.core.framework.event.data;

import ir.mrmoshkel.common.core.valueobject.Price;
import lombok.*;

//These vo data are for serialization and deserialization problem of value objects
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PriceEventData extends BaseEventData<Price> {
    private Long price;

    @Override
    public Price toValueObject() {
        return new Price(price);
    }
}
