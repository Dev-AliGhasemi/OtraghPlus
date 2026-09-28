package ir.mrmoshkel.valueobject;

import ir.mrmoshkel.framework.valueobject.BaseValueObject;
import lombok.*;

import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Price extends BaseValueObject {

    private Long price;

    public Price(Long price) {
        this.price = price;
        validate();
    }

    private static final Long zero = 0L;

    public static Price sum(Price first, Price second){
        return new Price(first.getPrice() + second.getPrice());
    }

    public boolean isZero(){
        return Objects.equals(price, zero);
    }

    @Override
    public void validate() {
        if (price == null)
            throw new IllegalArgumentException("Price is null");
        else if (price < zero)
            throw new IllegalArgumentException("Price must be greater than zero");
    }
}
