package ir.mrmoshkel.host.core.query.home.handler.query;

import ir.mrmoshkel.framework.query.BaseQueryHandler;
import ir.mrmoshkel.host.core.query.home.model.HomeModel;
import ir.mrmoshkel.host.core.domain.home.query.FindAllHomeQuery;
import ir.mrmoshkel.host.core.domain.home.query.FindByIdHomeQuery;

import java.util.List;

public interface HomeQueryHandler extends BaseQueryHandler {

    List<HomeModel> handle(FindAllHomeQuery findAllHomeQuery);
    HomeModel handle(FindByIdHomeQuery findByIdHomeQuery);

}
