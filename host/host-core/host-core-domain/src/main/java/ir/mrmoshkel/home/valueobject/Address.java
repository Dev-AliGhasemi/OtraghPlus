package ir.mrmoshkel.home.valueobject;

import ir.mrmoshkel.framework.valueobject.BaseValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class Address extends BaseValueObject {
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String houseNumber;

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
}