package ir.mrmoshkel.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price{

    private Long price;

    private static final Long zero = 0L;

    public static Price sum(Price first, Price second){
        return new Price(first.getPrice() + second.getPrice());
    }

    public boolean isZero(){
        return Objects.equals(price, zero);
    }
}
