package ir.mrmoshkel.home.query;

import ir.mrmoshkel.framework.query.BaseQuery;
import lombok.experimental.SuperBuilder;
import org.jmolecules.architecture.cqrs.QueryModel;

import java.util.UUID;

@SuperBuilder
@QueryModel
public class HomeQuery extends BaseQuery<UUID> {
}
