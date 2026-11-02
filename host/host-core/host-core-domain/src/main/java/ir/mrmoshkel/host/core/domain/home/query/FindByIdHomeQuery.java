package ir.mrmoshkel.host.core.domain.home.query;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.jmolecules.architecture.cqrs.QueryModel;

@Getter
@SuperBuilder
@QueryModel
public class FindByIdHomeQuery extends HomeQuery {
}
