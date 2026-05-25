package ir.mrmoshkel.home.query;

import ir.mrmoshkel.framework.query.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class FindByIdHomeQuery extends BaseQuery<Long> {
    public FindByIdHomeQuery(Long aLong) {
        super(aLong);
    }
}
