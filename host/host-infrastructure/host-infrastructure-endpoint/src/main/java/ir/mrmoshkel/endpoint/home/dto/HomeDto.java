package ir.mrmoshkel.endpoint.home.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeDto {
    private Long hostId;
    private Long pricePerNight;
    private String address;
    private List<Long> homeFacilitiesId;
    private String reserveState;
}
