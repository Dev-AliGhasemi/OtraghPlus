package ir.mrmoshkel.facilities.entity;

import ir.mrmoshkel.framework.entity.BaseAggregateRoot;
import ir.mrmoshkel.homefacilities.entity.HomeFacilities;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class Facilities extends BaseAggregateRoot<Long> {
    private String name;
    private List<HomeFacilities> homes;
}
