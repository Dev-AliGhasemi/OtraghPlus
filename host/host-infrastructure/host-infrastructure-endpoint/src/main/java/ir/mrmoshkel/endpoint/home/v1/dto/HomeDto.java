package ir.mrmoshkel.endpoint.home.v1.dto;

import ir.mrmoshkel.endpoint.validation.OnCreate;
import ir.mrmoshkel.endpoint.validation.OnUpdate;
import ir.mrmoshkel.home.valueobject.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeDto {
    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "{otragh.endpoint.validation.input_data_can_not_be_null}")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long hostId;
    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "{otragh.endpoint.validation.input_data_can_not_be_null}")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long pricePerNight;
    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 3, max = 400, message = "{otragh.endpoint.validation.address_can_not_be_too_long}")
    private Address address;
    @NotNull(groups = {OnUpdate.class}, message = "{otragh.endpoint.validation.input_data_can_not_be_null}")
    private String reserveState;
}
