package ir.mrmoshkel.host.core.query.home.handler.query;

import ir.mrmoshkel.host.core.domain.home.query.FindAllHomeQuery;
import ir.mrmoshkel.host.core.domain.home.query.FindByIdHomeQuery;
import ir.mrmoshkel.host.core.query.home.model.HomeModel;
import ir.mrmoshkel.host.core.query.home.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.jmolecules.architecture.cqrs.CommandHandler;

import java.util.List;

@RequiredArgsConstructor
public class HomeQueryHandlerImpl implements HomeQueryHandler {
    private final HomeRepository homeRepository;

    @CommandHandler
    public List<HomeModel> handle(FindAllHomeQuery findAllHomeQuery) {
        if (findAllHomeQuery.getOffset() != null && findAllHomeQuery.getPageSize() != null)
            return homeRepository.findAll(findAllHomeQuery.getOffset(), findAllHomeQuery.getPageSize());
        return homeRepository.findAll();
    }

    @CommandHandler
    public HomeModel handle(FindByIdHomeQuery findByIdHomeQuery) {
        return homeRepository.findById(findByIdHomeQuery.getId())
                .orElseThrow(() -> new RuntimeException("No entity found with id " + findByIdHomeQuery.getId()));
    }


}
