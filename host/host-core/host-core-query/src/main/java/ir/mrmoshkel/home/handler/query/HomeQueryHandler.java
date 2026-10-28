package ir.mrmoshkel.home.handler.query;

import ir.mrmoshkel.framework.query.QueryHandler;
import ir.mrmoshkel.home.model.HomeModel;
import ir.mrmoshkel.home.query.FindAllHomeQuery;
import ir.mrmoshkel.home.query.FindByIdHomeQuery;
import ir.mrmoshkel.home.repository.HomeBaseRepository;
import org.jmolecules.architecture.cqrs.CommandHandler;

import java.util.List;

public class HomeQueryHandler implements QueryHandler {
    private final HomeBaseRepository homeRepository;

    public HomeQueryHandler(HomeBaseRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

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
