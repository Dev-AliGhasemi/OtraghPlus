package ir.mrmoshkel.host.core.domain.homefacilities.entity;

import ir.mrmoshkel.host.core.domain.facilities.entity.Facilities;
import ir.mrmoshkel.framework.entity.BaseEntity;
import ir.mrmoshkel.host.core.domain.home.entity.Home;
import ir.mrmoshkel.valueobject.Price;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class HomeFacilities extends BaseEntity<Long> {
    private Home home;
    private Facilities facilities;
    private Price price;

    @Override
    public String getName() {
        return "HOME";
    }
}
