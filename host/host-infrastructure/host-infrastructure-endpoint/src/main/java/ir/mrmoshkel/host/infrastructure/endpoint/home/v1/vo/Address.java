package ir.mrmoshkel.host.infrastructure.endpoint.home.v1.vo;

import ir.mrmoshkel.host.infrastructure.endpoint.validation.OnCreate;
import ir.mrmoshkel.host.infrastructure.endpoint.validation.OnUpdate;
import jakarta.validation.constraints.NotBlank;

public record Address(    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
                          String country,
                          @NotBlank(groups = {OnCreate.class, OnUpdate.class})
                          String city,
                          @NotBlank(groups = {OnCreate.class, OnUpdate.class})
                          String street,
                          @NotBlank(groups = {OnCreate.class, OnUpdate.class})
                          String postalCode,
                          @NotBlank(groups = {OnCreate.class, OnUpdate.class})
                          String houseNumber) {
}