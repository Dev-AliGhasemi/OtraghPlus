package ir.mrmoshkel.host.infrastructure.endpoint.dispathcer;

import ir.mrmoshkel.host.core.domain.home.query.FindAllHomeQuery;
import ir.mrmoshkel.host.core.domain.home.query.FindByIdHomeQuery;
import ir.mrmoshkel.host.core.query.home.handler.query.HomeQueryHandler;
import ir.mrmoshkel.host.core.query.home.model.HomeModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.queryhandling.annotation.QueryHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("axonHomeQueryHandler")
@RequiredArgsConstructor
public class AxonHomeQueryHandlerImpl implements HomeQueryHandler {

    @Qualifier("homeQueryHandler")
    private final HomeQueryHandler homeQueryHandler;

    @Override
    @QueryHandler
    public List<HomeModel> handle(FindAllHomeQuery findAllHomeQuery) {
        return homeQueryHandler.handle(findAllHomeQuery);
    }

    @Override
    @QueryHandler
    public HomeModel handle(FindByIdHomeQuery findByIdHomeQuery) {
        return homeQueryHandler.handle(findByIdHomeQuery);
    }
}
