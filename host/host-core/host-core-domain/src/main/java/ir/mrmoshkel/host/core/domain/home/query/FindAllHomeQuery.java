package ir.mrmoshkel.host.core.domain.home.query;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.jmolecules.architecture.cqrs.QueryModel;

@Getter
@SuperBuilder
@QueryModel
public class FindAllHomeQuery extends HomeQuery {
    private Long offset;
    private Integer pageSize;
}
