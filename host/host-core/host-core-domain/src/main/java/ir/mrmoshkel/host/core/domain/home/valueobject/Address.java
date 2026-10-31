package ir.mrmoshkel.host.core.domain.home.valueobject;

import ir.mrmoshkel.framework.valueobject.BaseValueObject;
import ir.mrmoshkel.host.core.domain.home.event.data.AddressEventData;
import lombok.*;
import org.jmolecules.ddd.annotation.ValueObject;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@ValueObject
public class Address extends BaseValueObject<AddressEventData> {
    private final String country;
    private final String city;
    private final String street;
    private final String postalCode;
    private final String houseNumber;

    public Address(String country, String city, String street, String postalCode, String houseNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        validate();
    }

    @Override
    public void validate() {
        if (country == null)
            throw new IllegalArgumentException("country is null");
        else if (city == null)
            throw new IllegalArgumentException("city is null");
        else if (street == null)
            throw new IllegalArgumentException("street is null");
        else if (postalCode == null)
            throw new IllegalArgumentException("postal code is null");
        else if (houseNumber == null)
            throw new IllegalArgumentException("house number is null");
    }

    @Override
    public AddressEventData toData() {
        return new AddressEventData(country, city, street, postalCode, houseNumber);
    }
}