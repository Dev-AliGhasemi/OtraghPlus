package ir.mrmoshkel.home.valueobject;

import ir.mrmoshkel.home.enumeration.City;
import ir.mrmoshkel.home.enumeration.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private City city;
    private Province province;
    private String postalCode;
    private String details;
}
