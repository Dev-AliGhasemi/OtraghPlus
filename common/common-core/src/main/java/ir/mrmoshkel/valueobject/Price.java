package ir.mrmoshkel.valueobject;

import java.util.Objects;

public record Price(Long price) {

    private static final Long zero = 0L;

    public static Price sum(Price first, Price second){
        return new Price(first.price() + second.price());
    }

    public boolean isZero(){
        return Objects.equals(price, zero);
    }
}
