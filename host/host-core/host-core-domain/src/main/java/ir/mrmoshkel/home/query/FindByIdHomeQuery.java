package ir.mrmoshkel.home.query;

import ir.mrmoshkel.framework.query.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FindByIdHomeQuery extends BaseQuery<Long> {
}
