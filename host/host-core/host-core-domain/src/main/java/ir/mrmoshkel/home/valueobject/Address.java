package ir.mrmoshkel.home.valueobject;

import ir.mrmoshkel.home.enumeration.City;
import ir.mrmoshkel.home.enumeration.Province;

public record Address(
        String street,
        City city,
        Province province,
        String postalCode,
        String details) {
}
