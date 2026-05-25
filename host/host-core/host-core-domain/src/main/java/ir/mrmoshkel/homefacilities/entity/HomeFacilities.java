package ir.mrmoshkel.homefacilities.entity;

import ir.mrmoshkel.facilities.entity.Facilities;
import ir.mrmoshkel.framework.entity.BaseEntity;
import ir.mrmoshkel.home.entity.Home;
import ir.mrmoshkel.valueobject.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeFacilities extends BaseEntity<Long> {
    private Home home;
    private Facilities facilities;
    private Price price;
}
