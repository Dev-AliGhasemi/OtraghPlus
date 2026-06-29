package ir.mrmoshkel.facilities.entity;

import ir.mrmoshkel.framework.entity.AggregateRoot;
import ir.mrmoshkel.homefacilities.entity.HomeFacilities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Facilities extends AggregateRoot<Long> {
    private String name;
    private List<HomeFacilities> homes;
}
