package ir.mrmoshkel.endpoint.home.v1.vo;

import jakarta.validation.constraints.NotBlank;

public record Address(@NotBlank String country, @NotBlank String city, @NotBlank String street,
                      @NotBlank String postalCode, @NotBlank String houseNumber) {
}