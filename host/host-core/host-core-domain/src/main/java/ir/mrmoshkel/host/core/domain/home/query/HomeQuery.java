package ir.mrmoshkel.host.core.domain.home.query;

import ir.mrmoshkel.framework.query.BaseQuery;
import lombok.experimental.SuperBuilder;
import org.jmolecules.architecture.cqrs.QueryModel;

import java.util.UUID;

@SuperBuilder
@QueryModel
public class HomeQuery extends BaseQuery<UUID> {
}
