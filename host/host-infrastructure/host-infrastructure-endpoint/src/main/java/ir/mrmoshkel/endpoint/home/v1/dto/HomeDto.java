package ir.mrmoshkel.endpoint.home.v1.dto;

import ir.mrmoshkel.endpoint.home.v1.vo.Address;
import ir.mrmoshkel.endpoint.validation.OnCreate;
import ir.mrmoshkel.endpoint.validation.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeDto {
    @NotNull(groups = {OnCreate.class}, message = "otragh.endpoint.validation.input_data_can_not_be_null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long hostId;
    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "otragh.endpoint.validation.input_data_can_not_be_null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long pricePerNight;
    @Valid
    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Address address;
    private String reserveState;
}
