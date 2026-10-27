package ir.mrmoshkel.home.event.data;

import ir.mrmoshkel.framework.event.data.BaseEventData;
import ir.mrmoshkel.home.valueobject.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressData extends BaseEventData<Address> {
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String houseNumber;

    @Override
    public Address toValueObject() {
        return new Address(country, city, street, postalCode, houseNumber);
    }
}

